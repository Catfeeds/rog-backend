package com.rograndec.feijiayun.chain.business.report.finance.stock.service.impl;

import com.rograndec.feijiayun.chain.business.finance.set.period.dao.AccountingPeriodDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.set.period.dao.AccountingPeriodMapper;
import com.rograndec.feijiayun.chain.business.finance.set.period.entity.AccountingPeriod;
import com.rograndec.feijiayun.chain.business.finance.set.period.entity.AccountingPeriodDetail;
import com.rograndec.feijiayun.chain.business.keytable.dao.InOutRecordDetailMapper;
import com.rograndec.feijiayun.chain.business.report.finance.stock.service.StockInOutService;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.*;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.AccountingPeriodDetailVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.CostInfoPostVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.finance.DirectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
public class StockInOutServiceImpl implements StockInOutService {

    @Autowired
    private InOutRecordDetailMapper inOutRecordDetailMapper;


    @Autowired
    private AccountingPeriodDetailMapper accountingPeriodDetailMapper;

    @Autowired
    private AccountingPeriodMapper accountingPeriodMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    /**
     * @Description:
     * @return:
     * @Author: dongyang.du
     * @Date: 15/01/2018
     */
    @Override
    public Page<List<StockInOutEnterpriseVO>> getInouts(UserVO loginUser, StockInOutReqVO requestVO) {

        Page<List<StockInOutEnterpriseVO>> page = new Page<>(requestVO.getPageNo(), requestVO.getPageSize());
        requestVO.setPageStart(page.getStart());

        Long enterpriseId = loginUser.getEnterpriseId();

        Integer type =  getRequestType(requestVO,loginUser);

        requestVO.setType(type);
        requestVO.setEnterpriseId(enterpriseId);
        requestVO.setPageStart(page.getStart());
        requestVO.setPageSize(page.getPageSize() + 1);// 多查询一条，用于校验本年累计和本月合计是否显示

        Integer count = inOutRecordDetailMapper.getFinanceInOutReportCount(requestVO);
        List<StockInOutVO> inOutVOTempList = inOutRecordDetailMapper.getFinanceInOutReport(requestVO);

        StockInOutVO lastInOutVO = null;
        if (inOutVOTempList.size() > page.getPageSize()) {// 只有当查询的数据比 单页显示的数据多时做判断
            // 多查询处理的一条记录
            lastInOutVO = inOutVOTempList.remove(inOutVOTempList.size() - 1);
        }

        //
        Date startDateVO = DateUtils.StringToDate(requestVO.getStartDate(), DateStyle.YYYY_MM_DD);
        Date endDateVO = DateUtils.StringToDate(requestVO.getEndDate(), DateStyle.YYYY_MM_DD);

        // 0.查询全部的会计区间
        // 加盟店,总部读取自己的，自营店读取总部的
        if (ChainType.Selfoperatedshop.getCode() == loginUser.getChainType()) {
            enterpriseId = loginUser.getParentId();
        }
        List<AccountingPeriodDetailVO> apList = accountingPeriodDetailMapper.selectByEnterpriseId(enterpriseId, startDateVO, endDateVO);

        // 1.按企业、商品、年、月 分组
        List<StockInOutEnterpriseVO> inOutEnterpriseVOList = groupData(inOutVOTempList, requestVO, loginUser, apList, startDateVO, endDateVO);

        // 2.判断最后年月份是否显示

        judgeLastYearMonthShow(inOutEnterpriseVOList, lastInOutVO, apList);

        page.setTotalRecord(count);
        page.setResult(inOutEnterpriseVOList);

        return page;
    }

    /**
     * @Description: 判断最后的年月份是否显示
     * @return:
     * @Author: dongyang.du
     * @Date: 15/01/2018
     */
    private void judgeLastYearMonthShow(List<StockInOutEnterpriseVO> inOutEnterpriseVOList, StockInOutVO lastInOutVO, List<AccountingPeriodDetailVO> apList) {

        if (null == lastInOutVO || CollectionUtils.isEmpty(inOutEnterpriseVOList)) {
            return;
        }

        StockInOutEnterpriseVO inOutEnterpriseVO = inOutEnterpriseVOList.get(inOutEnterpriseVOList.size() - 1);// 企业层级
        Long enterpriseId = inOutEnterpriseVO.getEnterpriseId();

        List<StockInOutGoodsVO> stockInOutGoodsVOList = inOutEnterpriseVO.getStockInOutGoodsVOList();
        if (!CollectionUtils.isEmpty(stockInOutGoodsVOList)) {
            StockInOutGoodsVO inOutGoodsVO = stockInOutGoodsVOList.get(stockInOutGoodsVOList.size() - 1);// 商品层级
            Long goodsId = inOutGoodsVO.getGoodsId();
            List<StockInOutYearVO> stockInOutYearVOList = inOutGoodsVO.getStockInOutYearVOList();
            if (!CollectionUtils.isEmpty(stockInOutYearVOList)) {

                StockInOutYearVO inOutYearVO = stockInOutYearVOList.get(stockInOutYearVOList.size() - 1);//年份层级

                List<StockInOutMonthVO> stockInOutMonthVOList = inOutYearVO.getStockInOutMonthVOList();


                if (!CollectionUtils.isEmpty(stockInOutMonthVOList)) {
                    StockInOutMonthVO inOutMonthVO = stockInOutMonthVOList.get(stockInOutMonthVOList.size() - 1);// 月份层级

                    if (lastInOutVO.getEnterpriseId().equals(enterpriseId)
                            && lastInOutVO.getGoodsId().equals(goodsId)) {// 企业相同,商品相同


                        //获取 财务 年，月
                        AccountingPeriodDetailVO periodDetail = DirectionUtils.getBetweenPeriodDetail(apList, lastInOutVO.getOrderDate());

                        Integer year = periodDetail.getYear();
                        Integer month = periodDetail.getMonth();

                        if (inOutMonthVO.getMonth().equals(month)) {
                            inOutMonthVO.setShowMonthTotal(Boolean.FALSE);
                        }

                    }
                }


            }
        }


    }

    /**
     * @Description: 把数据按照 企业、商品、年、月 分组
     * @Param:
     * @return:
     * @Author: dongyang.du
     * @Date: 10/01/2018
     */
    private List<StockInOutEnterpriseVO> groupData(List<StockInOutVO> inOutVOTempList, StockInOutReqVO requestVO, UserVO loginUser, List<AccountingPeriodDetailVO> apList, Date startDateVO, Date endDateVO) {
        List<StockInOutEnterpriseVO> inOutEnterpriseVOList = new ArrayList<>();
        // 辅助 Map
        Map<Long, StockInOutEnterpriseVO> inOutEnterpriseMap = new HashMap<>();
        Map<Long, StockInOutGoodsVO> inOutGoodsMap = new HashMap<>();
        Map<Integer, StockInOutYearVO> inOutYearMap = new HashMap<>();
        Map<Integer, StockInOutMonthVO> inOutMonthMap = new HashMap<>();

        for (StockInOutVO inOutVO : inOutVOTempList) {

            Long eId = inOutVO.getEnterpriseId();
            Long goodsId = inOutVO.getGoodsId();
            Date orderDate = inOutVO.getOrderDate();

            //获取 财务 年，月
            AccountingPeriodDetailVO periodDetail = DirectionUtils.getBetweenPeriodDetail(apList, orderDate);
            if (periodDetail == null) {//
                throw new BusinessException("当前选择时间范围内，有没有设置的会计期间，请设置");
            }

            Integer year = periodDetail.getYear();
            Integer month = periodDetail.getMonth();


            if (inOutEnterpriseMap.containsKey(eId)) {// 企业 key 存在

                if (inOutGoodsMap.containsKey(goodsId)) {// goods key 存在

                    if (inOutYearMap.containsKey(year)) {// 年份 key

                        if (inOutMonthMap.containsKey(month)) { // 月份 key

                            StockInOutMonthVO inOutMonthVO = inOutMonthMap.get(month);
                            List<StockInOutVO> inOutVOList = inOutMonthVO.getInOutVOList();
                            inOutVOList.add(inOutVO);

                        } else { // 月份层级不存在
                            StockInOutYearVO yearVO = inOutYearMap.get(year);

                            // 月份层级
                            StockInOutMonthVO monthVO = createMonthLevel(yearVO, month, inOutVO, periodDetail);

                            inOutMonthMap.put(month, monthVO);

                        }
                    } else { //年份不存在

                        StockInOutGoodsVO goodsVO = inOutGoodsMap.get(goodsId);

                        // 年份层级
                        StockInOutYearVO yearVO = createYearLevel(goodsVO, inOutVO, year);

                        // 月份层级
                        StockInOutMonthVO monthVO = createMonthLevel(yearVO, month, inOutVO, periodDetail);

                        inOutYearMap.put(year, yearVO);
                        inOutMonthMap.put(month, monthVO);

                    }
                } else {// 商品层级不存在

                    StockInOutEnterpriseVO enterpriseVO = inOutEnterpriseMap.get(eId);

                    // 商品层级
                    StockInOutGoodsVO goodsVO = createGoodsLevel(enterpriseVO, inOutVO);

                    // 年份层级
                    StockInOutYearVO yearVO = createYearLevel(goodsVO, inOutVO, year);

                    // 月份层级

                    StockInOutMonthVO monthVO = createMonthLevel(yearVO, month, inOutVO, periodDetail);

                    inOutGoodsMap.put(goodsId, goodsVO);
                    inOutYearMap.put(year, yearVO);
                    inOutMonthMap.put(month, monthVO);

                }
            } else {  //企业层级不存在

                // 企业层级
                StockInOutEnterpriseVO enterpriseVO = createEnterpriseLevel(inOutVO);
                inOutEnterpriseVOList.add(enterpriseVO);

                // 商品层级
                StockInOutGoodsVO goodsVO = createGoodsLevel(enterpriseVO, inOutVO);

                // 年份层级
                StockInOutYearVO yearVO = createYearLevel(goodsVO, inOutVO, year);

                // 月份层级

                StockInOutMonthVO monthVO = createMonthLevel(yearVO, month, inOutVO, periodDetail);

                inOutEnterpriseMap.put(eId, enterpriseVO);
                inOutGoodsMap.put(goodsId, goodsVO);
                inOutYearMap.put(year, yearVO);
                inOutMonthMap.put(month, monthVO);

            }
        }

        // 计算明细的额度
        calculationAmount(inOutGoodsMap, loginUser, requestVO, apList, startDateVO, endDateVO);

        return inOutEnterpriseVOList;
    }

    /**
     * 计算 明细的额度
     *
     * @param inOutGoodsMap
     * @param loginUser
     * @param requestVO
     * @param apList
     * @param startDateVO
     * @param endDateVO
     */
    private void calculationAmount(Map<Long, StockInOutGoodsVO> inOutGoodsMap, UserVO loginUser, StockInOutReqVO requestVO,
                                   List<AccountingPeriodDetailVO> apList, Date startDateVO, Date endDateVO) {


        Set<Map.Entry<Long, StockInOutGoodsVO>> entries = inOutGoodsMap.entrySet();

        entries.parallelStream().forEach(entry -> {

            StockInOutGoodsVO inOutGoodsVO = entry.getValue();
            Long goodsId = inOutGoodsVO.getGoodsId();
            Long enterpriseId = inOutGoodsVO.getEnterpriseId();

            BigDecimal balanceAmount = BigDecimal.ZERO;// 如果没有选择时间区间，默认为0
            // 2.余额计算
            String startDate = requestVO.getStartDate();

            Map<String, Object> param = new HashMap<>();
            param.put("enterpriseId", enterpriseId);
            param.put("goodsId", goodsId);

            if (startDate != null) {
                // 根据 时间节点 查询 到 当前时间总数量，总金额

                param.put("direction", OrderDirection.IN.getDirection());
                param.put("endDateLT", startDate);//获取当前节点 之前 总数

                CostInfoPostVO inCost = inOutRecordDetailMapper.getInOutDetailTotalByParam(param);

                param.put("direction", OrderDirection.OUT.getDirection());

                CostInfoPostVO outCost = inOutRecordDetailMapper.getInOutDetailTotalByParam(param);

                BigDecimal inNotaxRealAmount = inCost == null ? BigDecimal.ZERO : inCost.getNotaxCostAmountTotal();
                BigDecimal outNotaxRealAmount = outCost == null ? BigDecimal.ZERO : outCost.getNotaxCostAmountTotal();


                BigDecimal inQuantity = inCost == null ? BigDecimal.ZERO : inCost.getQuantityTotal();
                BigDecimal outQuantity = outCost == null ? BigDecimal.ZERO : outCost.getQuantityTotal();

                balanceAmount = inNotaxRealAmount.subtract(outNotaxRealAmount);


                // 计算分页之前的,可能
                Integer pageNo = requestVO.getPageNo();

                if (pageNo > 1) {

                    // 在上边查询的时候pageSize + 1,多查询了一条，在这里减去
                    requestVO.setLimit((pageNo - 1) * (requestVO.getPageSize()-1));
                    requestVO.setGoodsId(goodsId);
                    CostInfoPostVO beforePageCost = inOutRecordDetailMapper.getBeforePageAmountQuantity(requestVO);

                    if (beforePageCost != null) {
                        balanceAmount = balanceAmount.add(beforePageCost.getNotaxCostAmountTotal() == null ? BigDecimal.ZERO : beforePageCost.getNotaxCostAmountTotal());
                        inQuantity = inQuantity.add(beforePageCost.getQuantityTotal() == null ? BigDecimal.ZERO : beforePageCost.getQuantityTotal());
                    }
                }


                inOutGoodsVO.setBalanceAmountTotal(balanceAmount);

                inOutGoodsVO.setBalanceQuantityTotal(inQuantity.subtract(outQuantity));
            }

            // 余额计算赋值
            for (StockInOutYearVO inOutYearVO : inOutGoodsVO.getStockInOutYearVOList()) {// 年份层级

                List<StockInOutMonthVO> inOutMonthVOList = inOutYearVO.getStockInOutMonthVOList();
                for (int j = 0; j < inOutMonthVOList.size(); j++) { // 月份层级

                    StockInOutMonthVO inOutMonthVO = inOutMonthVOList.get(j);

                    List<StockInOutVO> inOutVOList = inOutMonthVO.getInOutVOList();
                    for (int i = 0; i < inOutVOList.size(); i++) {// 具体列表

                        // 1.余额计算
                        StockInOutVO inOutVO = inOutVOList.get(i);
                        Integer direction = inOutVO.getDirection();
                        BigDecimal amount = inOutVO.getAmount();// 不含税成本 金额
                        if (direction.equals(OrderDirection.OUT.getDirection())) {
                            balanceAmount = balanceAmount.subtract(amount);
                            inOutVO.setBalanceAmount(balanceAmount);

                        } else if (direction.equals(OrderDirection.IN.getDirection())) {
                            balanceAmount = balanceAmount.add(amount);
                            inOutVO.setBalanceAmount(balanceAmount);
                        }

                        calculationMonthTotal(inOutVO, inOutMonthVO, apList, startDateVO, endDateVO, enterpriseId, goodsId);
                        calculationYearTotal(inOutYearVO, inOutMonthVO, apList, enterpriseId, goodsId, startDateVO, endDateVO,loginUser);

                    }

                }
            }

        });
    }

    /**
     * @param inOutYearVO
     * @param inOutMonthVO
     * @param apList
     * @param enterpriseId
     * @param goodsId
     * @param startDateVO
     * @param endDateVO
     * @param loginUser
     * @Description: 计算本年累计
     * @return:
     * @Author: dongyang.du
     * @Date: 13/01/2018
     */
    private void calculationYearTotal(StockInOutYearVO inOutYearVO, StockInOutMonthVO inOutMonthVO, List<AccountingPeriodDetailVO> apList, Long enterpriseId, Long goodsId, Date startDateVO, Date endDateVO, UserVO loginUser) {

        // 获取当年 会计期间 起始时间
        Integer year = inOutYearVO.getYear();

        Long userEnterpriseId = loginUser.getEnterpriseId();
        if(ChainType.Selfoperatedshop.getCode() == loginUser.getChainType()){
            userEnterpriseId = loginUser.getParentId();
        }

        AccountingPeriod period = accountingPeriodMapper.selectByEnterpriseIdAndYear(userEnterpriseId, year, null);

        List<AccountingPeriodDetail> periodDetails = accountingPeriodDetailMapper.selectByPeriodId(period.getId());


        Date startDate = periodDetails.get(0).getStartDate();
        Date endDate = inOutMonthVO.getEndDate();// 获取当前月份的endDate值

        // 请求的时间范围点  和  会计期间区间不同
        if (startDateVO != null && startDateVO.getTime() > startDate.getTime()) {
            startDate = startDateVO;
        }
        if (endDateVO != null && endDateVO.getTime() < endDateVO.getTime()) {
            endDate = endDateVO;
        }

        Map<String, Object> param = new HashMap<>();
        param.put("enterpriseId", enterpriseId);
        param.put("goodsId", goodsId);

        param.put("direction", OrderDirection.IN.getDirection());
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        CostInfoPostVO inCost = inOutRecordDetailMapper.getInOutDetailTotalByParam(param);

        param.put("direction", OrderDirection.OUT.getDirection());

        CostInfoPostVO outCost = inOutRecordDetailMapper.getInOutDetailTotalByParam(param);

        BigDecimal inAmountTotal = inCost == null ? BigDecimal.ZERO : inCost.getNotaxCostAmountTotal();
        BigDecimal outAmountTotal = outCost == null ? BigDecimal.ZERO : outCost.getNotaxCostAmountTotal();

        BigDecimal inQuantityTotal = inCost == null ? BigDecimal.ZERO : inCost.getQuantityTotal();
        BigDecimal outQuantityTotal = outCost == null ? BigDecimal.ZERO : outCost.getQuantityTotal();

        StockYearMonthTotal yearMonthTotal = new StockYearMonthTotal();
        yearMonthTotal.setInQuantityTotal(inQuantityTotal);
        yearMonthTotal.setOutQuantityTotal(outQuantityTotal);
        yearMonthTotal.setInAmountTotal(inAmountTotal);
        yearMonthTotal.setOutAmountTotal(outAmountTotal);

        //inOutYearVO.setShowYearTotal(Boolean.TRUE);
        //inOutYearVO.setStockYearTotal(yearMonthTotal);
        inOutMonthVO.setStockYearTotal(yearMonthTotal);
    }


    /**
     * @param inOutVO
     * @param inOutMonthVO
     * @param apList
     * @param startDateVO
     * @param endDateVO
     * @Description: 计算本月合计
     * @return:
     * @Author: dongyang.du
     * @Date: 13/01/2018
     */
    private void calculationMonthTotal(StockInOutVO inOutVO, StockInOutMonthVO inOutMonthVO, List<AccountingPeriodDetailVO> apList,
                                       Date startDateVO, Date endDateVO, Long enterpriseId, Long goodsId) {

        AccountingPeriodDetailVO periodDetail = DirectionUtils.getBetweenPeriodDetail(apList, inOutVO.getOrderDate());
        Date startDate = periodDetail.getStartDate();
        Date endDate = periodDetail.getEndDate();

        // 请求的时间范围点  和  会计期间区间不同
        if (startDateVO != null && startDateVO.getTime() > startDate.getTime()) {
            startDate = startDateVO;
        }

        if (endDateVO != null && endDateVO.getTime() < endDateVO.getTime()) {
            endDate = endDateVO;
        }

        Map<String, Object> param = new HashMap<>();
        param.put("enterpriseId", enterpriseId);
        param.put("goodsId", goodsId);

        param.put("direction", OrderDirection.IN.getDirection());
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        CostInfoPostVO inCost = inOutRecordDetailMapper.getInOutDetailTotalByParam(param);

        param.put("direction", OrderDirection.OUT.getDirection());

        CostInfoPostVO outCost = inOutRecordDetailMapper.getInOutDetailTotalByParam(param);

        BigDecimal inAmountTotal = inCost == null ? BigDecimal.ZERO : inCost.getNotaxCostAmountTotal();
        BigDecimal outAmountTotal = outCost == null ? BigDecimal.ZERO : outCost.getNotaxCostAmountTotal();

        BigDecimal inQuantityTotal = inCost == null ? BigDecimal.ZERO : inCost.getQuantityTotal();
        BigDecimal outQuantityTotal = outCost == null ? BigDecimal.ZERO : outCost.getQuantityTotal();

        StockYearMonthTotal yearMonthTotal = new StockYearMonthTotal();
        yearMonthTotal.setInQuantityTotal(inQuantityTotal);
        yearMonthTotal.setOutQuantityTotal(outQuantityTotal);
        yearMonthTotal.setInAmountTotal(inAmountTotal);
        yearMonthTotal.setOutAmountTotal(outAmountTotal);
        inOutMonthVO.setStockMonthTotal(yearMonthTotal);
        inOutMonthVO.setShowMonthTotal(Boolean.TRUE);

    }

    /**
     * @Description: 创建月份层级
     * @return:
     * @Author: dongyang.du
     * @Date: 10/01/2018
     */
    private StockInOutMonthVO createMonthLevel(StockInOutYearVO yearVO, Integer month, StockInOutVO inOutVO, AccountingPeriodDetailVO periodDetail) {
        StockInOutMonthVO monthVO = new StockInOutMonthVO();
        monthVO.setMonth(month);
        monthVO.setShowMonthTotal(Boolean.FALSE);
        monthVO.setStartDate(periodDetail.getStartDate());
        monthVO.setEndDate(periodDetail.getEndDate());

        yearVO.getStockInOutMonthVOList().add(monthVO);
        List<StockInOutVO> stockInOutVOList = new ArrayList<>();
        monthVO.setInOutVOList(stockInOutVOList);
        stockInOutVOList.add(inOutVO);
        return monthVO;
    }

    /**
     * @Description:创建 年份层级
     * @return:
     * @Author: dongyang.du
     * @Date: 10/01/2018
     */
    private StockInOutYearVO createYearLevel(StockInOutGoodsVO goodsVO, StockInOutVO inOutVO, Integer year) {
        StockInOutYearVO yearVO = new StockInOutYearVO();
        goodsVO.getStockInOutYearVOList().add(yearVO);
        List<StockInOutMonthVO> monthVOList = new ArrayList<>();
        yearVO.setStockInOutMonthVOList(monthVOList);
        yearVO.setYear(year);
        return yearVO;
    }

    /**
     * @param enterpriseVO
     * @param inOutVO
     * @Description: 创建商品层级
     * @return:
     * @Author: dongyang.du
     * @Date: 10/01/2018
     */
    private StockInOutGoodsVO createGoodsLevel(StockInOutEnterpriseVO enterpriseVO, StockInOutVO inOutVO) {

        StockInOutGoodsVO goodsVO = new StockInOutGoodsVO();
        BeanUtils.copyProperties(inOutVO, goodsVO);
        List<StockInOutYearVO> inOutYearVOList = new ArrayList<>();
        goodsVO.setStockInOutYearVOList(inOutYearVOList);
        enterpriseVO.getStockInOutGoodsVOList().add(goodsVO);

        return goodsVO;
    }

    /**
     * @param inOutVO
     * @Description: 创建企业层级
     * @return:
     * @Author: dongyang.du
     * @Date: 10/01/2018
     */
    private StockInOutEnterpriseVO createEnterpriseLevel(StockInOutVO inOutVO) {

        // 企业层级
        StockInOutEnterpriseVO enterpriseVO = new StockInOutEnterpriseVO();
        enterpriseVO.setEnterpriseId(inOutVO.getEnterpriseId());
        enterpriseVO.setEnterpriseName(inOutVO.getEnterpriseName());
        enterpriseVO.setEnterpriseCode(inOutVO.getEnterpriseCode());

        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseVO.getEnterpriseId());
        if (enterprise != null) {
            enterpriseVO.setChainType(enterprise.getChainType());
        }

        List<StockInOutGoodsVO> inOutGoodsVOList = new ArrayList<>();
        enterpriseVO.setStockInOutGoodsVOList(inOutGoodsVOList);
        return enterpriseVO;
    }


    /**
     * TODO: 后期优化 下构造数据，和分页不同的是 ,同一个商品的 本月合计和本年累计可以不用查询计算了
     *
     * @param output
     * @param requestVO
     * @param userVO
     */
    @Override
    public void export(OutputStream output, StockInOutReqVO requestVO, UserVO userVO) {

        Long enterpriseId = userVO.getEnterpriseId();
        Integer type =  getRequestType(requestVO,userVO);
        
        requestVO.setType(type);
        requestVO.setEnterpriseId(enterpriseId);

        List<StockInOutVO> inOutVOTempList = inOutRecordDetailMapper.getFinanceInOutReport(requestVO);

        Date startDateVO = DateUtils.StringToDate(requestVO.getStartDate(), DateStyle.YYYY_MM_DD);
        Date endDateVO = DateUtils.StringToDate(requestVO.getEndDate(), DateStyle.YYYY_MM_DD);


        // 0.查询全部的会计区间
        // 加盟店,总部读取自己的，自营店读取总部的
        if (ChainType.Selfoperatedshop.getCode() == userVO.getChainType()) {
            enterpriseId = userVO.getParentId();
        }
        List<AccountingPeriodDetailVO> apList = accountingPeriodDetailMapper.selectByEnterpriseId(enterpriseId, startDateVO, endDateVO);

        // 1.按企业、商品、年、月 分组
        List<StockInOutEnterpriseVO> inOutEnterpriseVOList = groupData(inOutVOTempList, requestVO, userVO, apList, startDateVO, endDateVO);

        List<String> headerList = new ArrayList<>(3);
        headerList.add(userVO.getEnterpriseName());
        headerList.add("库存明细账");
        headerList.add("期间:" + DateUtils.DateToString(startDateVO, DateUtils.FMT_DATE) + "-" + DateUtils.DateToString(endDateVO, DateUtils.FMT_DATE));

        LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>(8);

        rowHeaderMap.put("orderDate", "单据日期");
        rowHeaderMap.put("orderTypeName", "单据类型");
        rowHeaderMap.put("orderCode", "单据编码");
        rowHeaderMap.put("manName", "人员");
        rowHeaderMap.put("remark", "备注");

        rowHeaderMap.put("inQuantity", "收入数量");
        rowHeaderMap.put("inAmount", "收入金额");
        rowHeaderMap.put("outQuantity", "发出数量");
        rowHeaderMap.put("outAmount", "发出金额");
        rowHeaderMap.put("balanceQuantity", "余额数量");
        rowHeaderMap.put("balanceAmount", "余额金额");


        List<String> excelTitle = new ArrayList<>();
        List<List<StockInOutVO>> excelList = new ArrayList<>();
        for (StockInOutEnterpriseVO enterpriseVO : inOutEnterpriseVOList) {

            List<StockInOutGoodsVO> stockInOutGoodsVOList = enterpriseVO.getStockInOutGoodsVOList();

            for (StockInOutGoodsVO goodsVO : stockInOutGoodsVOList) {


                excelTitle.add(enterpriseVO.getEnterpriseCode() + "-" + enterpriseVO.getEnterpriseName() + "-" + enterpriseVO.getChainTypeName() + "  :  "
                        + goodsVO.getGoodsCode() + "-" + goodsVO.getGoodsGenericName() + "-" + goodsVO.getSpecification() + "-" + goodsVO.getManufacturer());

                List<StockInOutVO> yearList = new ArrayList<>();
                for (StockInOutYearVO yearVO : goodsVO.getStockInOutYearVOList()) {

                    for (StockInOutMonthVO monthVO : yearVO.getStockInOutMonthVOList()) {

                        // 处理期初余额
                        StockInOutVO inOutVO = new StockInOutVO();
                        inOutVO.setRemark("期初余额");
                        inOutVO.setBalanceAmount(goodsVO.getBalanceAmountTotal());
                        inOutVO.setBalanceQuantity(goodsVO.getBalanceQuantityTotal());
                        monthVO.getInOutVOList().add(0, inOutVO);

                        if (monthVO.getShowMonthTotal()) {
                            StockYearMonthTotal stockMonthTotal = monthVO.getStockMonthTotal();
                            dealYearMonthTotal(monthVO, stockMonthTotal, "本月合计");

                            StockYearMonthTotal stockYearTotal = monthVO.getStockYearTotal();
                            dealYearMonthTotal(monthVO, stockYearTotal, "本年累计");

                        }
                        yearList.addAll(monthVO.getInOutVOList());
                    }
                }
                excelList.add(yearList);
            }

        }

        purchaseGeneralComponent.commMultipleExcelExport(output, rowHeaderMap, excelList, headerList, excelTitle, null, false, new ArrayList<>());


    }

    private void dealYearMonthTotal(StockInOutMonthVO monthVO, StockYearMonthTotal stockMonthTotal, String desc) {

        StockInOutVO inOutVO = new StockInOutVO();

        inOutVO.setRemark(desc);
        inOutVO.setInAmount(stockMonthTotal.getInAmountTotal());
        inOutVO.setOutAmount(stockMonthTotal.getOutAmountTotal());
        inOutVO.setInQuantity(stockMonthTotal.getInQuantityTotal());
        inOutVO.setOutQuantity(stockMonthTotal.getOutQuantityTotal());

        monthVO.getInOutVOList().add(inOutVO);
    }

    /** 
    * @Description: 获取请求类型
    * @return:  
    * @Author: dongyang.du
    * @Date: 30/01/2018 
    */ 
    public Integer getRequestType(StockInOutReqVO requestVO, UserVO loginUser) {

        Integer type = 0;// 默认查询自己企业(总部，自营店，加盟店)的
        if(requestVO.getChainType() != null){
            if(loginUser.getChainType().equals(ChainType.Headquarters.getCode())
                    && 0 != requestVO.getChainType()){// 当前用户为总部，查询其他 加盟店或者自营店的数据时候
                type = 1; // 总部查询分店的
            }
        } else if(StringUtils.isNotEmpty(requestVO.getEnterpriseCode()) ||
                StringUtils.isNotEmpty(requestVO.getEnterpriseName())) {
            type = 2;// 查询
        }

        return  type;
    }
}

