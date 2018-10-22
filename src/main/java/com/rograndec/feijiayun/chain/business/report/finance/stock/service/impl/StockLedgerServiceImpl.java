package com.rograndec.feijiayun.chain.business.report.finance.stock.service.impl;

import com.rograndec.feijiayun.chain.business.finance.set.period.dao.AccountingPeriodDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.keytable.dao.InOutRecordDetailMapper;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.LedgerGroupDateVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.LedgerVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.service.StockLedgerService;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.*;
import com.rograndec.feijiayun.chain.business.report.finance.tax.vo.AccountingPeriodDetailVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.CostInfoPostVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.constant.ValidFlag;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * @program: chain-backend
 * @description: 库存总账
 * @author: dongyang.du
 * @create: 2018-01-14 11:14
 **/
@Service
public class StockLedgerServiceImpl implements StockLedgerService {


    @Autowired
    private AccountingPeriodDetailMapper accountingPeriodDetailMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private InOutRecordDetailMapper inOutRecordDetailMapper;


    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public Page<List<StockLedgerMonthVO>> getLedgersByEtps(UserVO userVO, BasePageReqParam requestVO) {

        Integer pageNo = requestVO.getPageNo();
        Integer pageSize = requestVO.getPageSize();

        Date startDateVO = DateUtils.StringToDate(requestVO.getStartDate(), DateStyle.YYYY_MM_DD);
        Date endDateVO = DateUtils.StringToDate(requestVO.getEndDate(), DateStyle.YYYY_MM_DD);

        // 1. 获取会计月List
        List<AccountingPeriodDetailVO> apList = getPeriodList(userVO, startDateVO, endDateVO);

        // 2. 获取组织机构List
        List<Enterprise> enterpriseList = getEnterpriseList(userVO, requestVO);


        /**
         * 3. 计算起始值
         * 总数count = 会计月size * 组织机构size
         * 组织机构起始值 = (pageNo - 1)*pageSize % 组织机构size
         * 会计月 起始值 = ((pageNo - 1)*pageSize / 组织机构size ) % 会计月
         */

        Page<List<StockLedgerMonthVO>> page = new Page(pageNo, pageSize);
        Integer totalRecord = apList.size() * enterpriseList.size();//
        page.setTotalRecord(totalRecord);

        List<StockLedgerMonthVO> ledgerMonthVOList = new ArrayList<>();
        page.setResult(ledgerMonthVOList);

        // 分页总数判断
        if ((pageNo - 1) * pageSize > totalRecord || enterpriseList.size() == 0) {
            return page;
        }

        Integer enterpriseStart = ((pageNo - 1) * pageSize) % enterpriseList.size();
        Integer periodStart = ((pageNo - 1) * pageSize / enterpriseList.size()) % apList.size();

        // 4. 查询总账,组织数据

        buildDataByEtps(ledgerMonthVOList, apList, enterpriseList, periodStart, enterpriseStart, startDateVO, endDateVO, pageSize.intValue());


        return page;
    }

    /**
     * @param ledgerMonthVOList
     * @param apList
     * @param enterpriseList
     * @param periodStart       会计期间 起始索引
     * @param enterpriseStart   组织机构 起始索引
     * @param startDateVO
     * @param endDateVO
     * @param pageSize          分页大小
     * @Description: 构建数据 按组织机构
     * @return:
     * @Author: dongyang.du
     * @Date: 16/01/2018
     */
    private void buildDataByEtps(List<StockLedgerMonthVO> ledgerMonthVOList, List<AccountingPeriodDetailVO> apList, List<Enterprise> enterpriseList, Integer periodStart,
                                 Integer enterpriseStart, Date startDateVO, Date endDateVO, Integer pageSize) {
        int temp = 0;

        for (int i = periodStart.intValue(); i < apList.size(); i++) {

            StockLedgerMonthVO ledgerMonthVO = new StockLedgerMonthVO();
            ledgerMonthVOList.add(ledgerMonthVO);

            AccountingPeriodDetailVO periodDetailVO = apList.get(i);// 遍历到的当前会计月
            ledgerMonthVO.setYear(periodDetailVO.getYear());
            ledgerMonthVO.setMonth(periodDetailVO.getMonth());

            if(startDateVO.after(periodDetailVO.getStartDate())){
                ledgerMonthVO.setStartDate(startDateVO);
            } else {
                ledgerMonthVO.setStartDate(periodDetailVO.getStartDate());
            }

            if(endDateVO.before(periodDetailVO.getEndDate())){
                ledgerMonthVO.setEndDate(endDateVO);
            } else {
                ledgerMonthVO.setEndDate(periodDetailVO.getEndDate());
            }


            List<StockLedgerEnterpriseVO> ledgerEnterpriseVOList = new ArrayList<>();
            ledgerMonthVO.setLedgerEnterpriseVOList(ledgerEnterpriseVOList);

            for (int j = enterpriseStart.intValue(); j < enterpriseList.size(); j++) {

                Enterprise enterprise = enterpriseList.get(j);
                // 查询这个 企业 当前会计月 的出入库总账
                StockLedgerEnterpriseVO ledgerEnterpriseVO = new StockLedgerEnterpriseVO();
                ledgerEnterpriseVO.setEnterpriseId(enterprise.getId());
                ledgerEnterpriseVO.setEnterpriseCode(enterprise.getCode());
                ledgerEnterpriseVO.setEnterpriseName(enterprise.getName());
                ledgerEnterpriseVO.setChainType(enterprise.getChainType());

                ledgerEnterpriseVOList.add(ledgerEnterpriseVO);
                // 计算本月 出入库总账合计
                calculationMonthTotal(periodDetailVO, startDateVO, endDateVO, ledgerEnterpriseVO);

                if(pageSize != null){
                    if (++temp >= pageSize.intValue()) {
                        return;
                    }
                }
            }
        }
    }


    @Override
    public Page<List<StockLedgerEnterpriseByGoodsVO>> getLedgersByGoods(UserVO userVO, StockInOutReqVO requestVO) {

        Integer pageNo = requestVO.getPageNo();
        Integer pageSize = requestVO.getPageSize();

        Date startDateVO = DateUtils.StringToDate(requestVO.getStartDate(), DateStyle.YYYY_MM_DD);
        Date endDateVO = DateUtils.StringToDate(requestVO.getEndDate(), DateStyle.YYYY_MM_DD);


        // 1. 获取企业总数 -- 只有当 总部查询 自营店或加盟店时才获取总数
        List<Enterprise> enterpriseList = getEnterpriseList(userVO, requestVO);

        // 2. 获取会计月List
        List<AccountingPeriodDetailVO> apList = getPeriodList(userVO, startDateVO, endDateVO);

        // 3. 获取商品总数
        List<Goods> goodsList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(enterpriseList)){
            goodsList = getGoodsList(startDateVO, endDateVO, enterpriseList, requestVO);
        }


        /**
         * 4. 计算起始值
         * 总数count = 会计月size * 组织机构size * 商品总数size
         * 商品的起始值 = (pageNo - 1) * pageSize % 商品size
         * 月份的起始值 = (pageNo - 1) * pageSize / 商品size  % 月份size
         * 企业的起始值 = (pageNo - 1) * pageSize / 商品size / 月份size % 企业size
         *
         */

        Page<List<StockLedgerEnterpriseByGoodsVO>> page = new Page(pageNo, pageSize);
        Integer totalRecord = apList.size() * enterpriseList.size() * goodsList.size();
        page.setTotalRecord(totalRecord);

        List<StockLedgerEnterpriseByGoodsVO> enterpriseByGoodsVOS = new ArrayList<>();
        page.setResult(enterpriseByGoodsVOS);

        // 分页总数判断
        if ((pageNo - 1) * pageSize > totalRecord || goodsList.size() == 0) {
            return page;
        }

        Integer goodsStart = (pageNo - 1) * pageSize % goodsList.size();
        Integer monthStart = (pageNo - 1) * pageSize / goodsList.size() % apList.size();
        Integer enterpriseStart = (pageNo - 1) * pageSize / goodsList.size() / apList.size() % enterpriseList.size();

        // 5. 遍历组装数据

        buildDataByGoods(enterpriseByGoodsVOS, enterpriseList, apList, goodsList, enterpriseStart, monthStart, goodsStart, pageSize, startDateVO, endDateVO, userVO);


        return page;
    }

    /**
     * @param enterpriseByGoodsVOS
     * @param enterpriseList
     * @param apList
     * @param goodsList
     * @param enterpriseStart      组织机构 索引值
     * @param monthStart           会计月  索引值
     * @param goodsStart           商品    索引值
     * @param startDateVO
     * @param endDateVO
     * @param userVO
     * @Description: 构建数据 按商品
     * @return:
     * @Author: dongyang.du
     * @Date: 16/01/2018
     */
    private void buildDataByGoods(List<StockLedgerEnterpriseByGoodsVO> enterpriseByGoodsVOS, List<Enterprise> enterpriseList, List<AccountingPeriodDetailVO> apList,
                                  List<Goods> goodsList, Integer enterpriseStart, Integer monthStart, Integer goodsStart,
                                  Integer pageSize, Date startDateVO, Date endDateVO, UserVO userVO) {

        int temp = 0;
        for (int i = enterpriseStart.intValue(); i < enterpriseList.size(); i++) {//企业层级

            Enterprise enterprise = enterpriseList.get(i);

            StockLedgerEnterpriseByGoodsVO enterpriseVO = new StockLedgerEnterpriseByGoodsVO();
            enterpriseVO.setEnterpriseId(enterprise.getId());
            enterpriseVO.setEnterpriseCode(enterprise.getCode());
            enterpriseVO.setEnterpriseName(enterprise.getName());
            enterpriseVO.setChainType(enterprise.getChainType());
            enterpriseByGoodsVOS.add(enterpriseVO);

            List<StockLedgerMonthByGoodsVO> monthList = new ArrayList<>();
            enterpriseVO.setMonthList(monthList);

            for (int j = monthStart.intValue(); j < apList.size(); j++) {// 会计月层级

                AccountingPeriodDetailVO periodDetailVO = apList.get(j);

                StockLedgerMonthByGoodsVO monthVO = new StockLedgerMonthByGoodsVO();
                monthVO.setYear(periodDetailVO.getYear());
                monthVO.setMonth(periodDetailVO.getMonth());
                monthVO.setStartDate(periodDetailVO.getStartDate());
                monthVO.setEndDate(periodDetailVO.getEndDate());


                monthList.add(monthVO);

                List<StockLedgerGoodsVO> goodsVOList = new ArrayList<>();
                monthVO.setGoodsVOList(goodsVOList);

                for (int k = goodsStart.intValue(); k < goodsList.size(); k++) {// 商品层级

                    Goods goods = goodsList.get(k);

                    StockLedgerGoodsVO goodsVO = new StockLedgerGoodsVO();
                    // copy 属性
                    copyGoodsVO(goodsVO, goods);
                    goodsVOList.add(goodsVO);

                    // 计算商品 在 会计期间出入库总账
                    calculationGoodsTotal(enterpriseVO, periodDetailVO, goodsVO, userVO, startDateVO, endDateVO);


                    if(pageSize != null){
                        if (++temp >= pageSize.intValue()) {
                            return;
                        }
                    }
                }
            }
        }
    }


    /**
     * @param enterpriseByGoodsVOS
     * @param enterpriseList
     * @param apList
     * @param goodsList
     * @param startDateVO
     * @param endDateVO
     * @param userVO
     * @Description: 构建数据 按商品
     * @return:
     * @Author: dongyang.du
     * @Date: 16/01/2018
     */
    private void buildDataByGoodsParallel(List<StockLedgerEnterpriseByGoodsVO> enterpriseByGoodsVOS, List<Enterprise> enterpriseList, List<AccountingPeriodDetailVO> apList,
                                          List<Goods> goodsList, Date startDateVO, Date endDateVO, UserVO userVO) {

        for (Enterprise enterprise : enterpriseList) {//企业层级

            StockLedgerEnterpriseByGoodsVO enterpriseVO = new StockLedgerEnterpriseByGoodsVO();
            enterpriseVO.setEnterpriseId(enterprise.getId());
            enterpriseVO.setEnterpriseCode(enterprise.getCode());
            enterpriseVO.setEnterpriseName(enterprise.getName());
            enterpriseVO.setChainType(enterprise.getChainType());
            enterpriseByGoodsVOS.add(enterpriseVO);

            List<StockLedgerMonthByGoodsVO> monthList = new ArrayList<>();
            enterpriseVO.setMonthList(monthList);

            for (AccountingPeriodDetailVO periodDetailVO:apList) {// 会计月层级

                StockLedgerMonthByGoodsVO monthVO = new StockLedgerMonthByGoodsVO();
                monthVO.setYear(periodDetailVO.getYear());
                monthVO.setMonth(periodDetailVO.getMonth());
                monthVO.setStartDate(periodDetailVO.getStartDate());
                monthVO.setEndDate(periodDetailVO.getEndDate());

                monthList.add(monthVO);

                List<StockLedgerGoodsVO> goodsVOList = new ArrayList<>();
                monthVO.setGoodsVOList(goodsVOList);

                goodsList.parallelStream().forEach(item -> {// 商品层级

                    StockLedgerGoodsVO goodsVO = new StockLedgerGoodsVO();
                    // copy 属性
                    copyGoodsVO(goodsVO, item);
                    goodsVOList.add(goodsVO);

                    // 计算商品 在 会计期间出入库总账
                    calculationGoodsTotal(enterpriseVO, periodDetailVO, goodsVO, userVO, startDateVO, endDateVO);

                });

            }
        }
    }
    @Override
    public void exportByGoods(OutputStream output, StockInOutReqVO requestVO, UserVO userVO) {

        Date startDateVO = DateUtils.StringToDate(requestVO.getStartDate(), DateStyle.YYYY_MM_DD);
        Date endDateVO = DateUtils.StringToDate(requestVO.getEndDate(), DateStyle.YYYY_MM_DD);


        // 1. 获取企业总数 -- 只有当 总部查询 自营店或加盟店时才获取总数
        List<Enterprise> enterpriseList = getEnterpriseList(userVO, requestVO);

        // 2. 获取会计月List
        List<AccountingPeriodDetailVO> apList = getPeriodList(userVO, startDateVO, endDateVO);

        // 3. 获取商品总数
        List<Goods> goodsList = getGoodsList(startDateVO, endDateVO, enterpriseList, requestVO);

        List<StockLedgerEnterpriseByGoodsVO> enterpriseByGoodsVOS = new ArrayList<>();

        // 5. 遍历组装数据

        buildDataByGoodsParallel(enterpriseByGoodsVOS, enterpriseList, apList, goodsList, startDateVO, endDateVO, userVO);

        List<String> headerList = new ArrayList<>(3);
        headerList.add(userVO.getEnterpriseName());
        headerList.add("库存总账");
        headerList.add("期间:" + DateUtils.DateToString(startDateVO, DateUtils.FMT_DATE) + "-" + DateUtils.DateToString(endDateVO, DateUtils.FMT_DATE));

        LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>(8);
        rowHeaderMap.put("goodsCode", "商品编码");
        rowHeaderMap.put("barcode", "条形码");
        rowHeaderMap.put("goodsName", "商品名称");
        rowHeaderMap.put("goodsGenericName", "商品通用名称");
        rowHeaderMap.put("dosageName", "剂型名称");
        rowHeaderMap.put("unitName", "单位名称");
        rowHeaderMap.put("specification", "商品规格");
        rowHeaderMap.put("manufacturer", "生产厂商");
        rowHeaderMap.put("goodsPlace", "商品产地");
        rowHeaderMap.put("approvalNumber", "批准文号");

        rowHeaderMap.put("startQuantity", "期初结存数量");
        rowHeaderMap.put("startAmount", "期初结存金额");
        rowHeaderMap.put("inQuantity", "期间收入数量");
        rowHeaderMap.put("inAmount", "期间收入金额");
        rowHeaderMap.put("outQuantity", "期间发出数量");
        rowHeaderMap.put("outAmount", "期间发出金额");
        rowHeaderMap.put("endQuantity", "期末余额数量");
        rowHeaderMap.put("endAmount", "期末余额金额");


        List<String> excelTitle = new ArrayList<>();
        List<List<StockLedgerGoodsVO>> excelList = new ArrayList<>();
        for (StockLedgerEnterpriseByGoodsVO enterpriseVO : enterpriseByGoodsVOS) {

            List<StockLedgerMonthByGoodsVO> monthList = enterpriseVO.getMonthList();

            for (StockLedgerMonthByGoodsVO monthVO : monthList) {
                excelTitle.add(enterpriseVO.getEnterpriseCode() + "-" + enterpriseVO.getEnterpriseName() + ":"
                    + monthVO.getYear() + "年" + monthVO.getMonth() + "月");
                excelList.add(monthVO.getGoodsVOList());
            }

        }

        purchaseGeneralComponent.commMultipleExcelExport(output, rowHeaderMap, excelList, headerList, excelTitle, null, false, new ArrayList<>());

    }

    @Override
    public void exportByEtps(OutputStream output, BasePageReqParam requestVO, UserVO userVO) {


        Date startDateVO = DateUtils.StringToDate(requestVO.getStartDate(), DateStyle.YYYY_MM_DD);
        Date endDateVO = DateUtils.StringToDate(requestVO.getEndDate(), DateStyle.YYYY_MM_DD);

        // 1. 获取会计月List
        List<AccountingPeriodDetailVO> apList = getPeriodList(userVO, startDateVO, endDateVO);

        // 2. 获取组织机构List
        List<Enterprise> enterpriseList = getEnterpriseList(userVO, requestVO);

        List<StockLedgerMonthVO> ledgerMonthVOList = new ArrayList<>();

        buildDataByEtps(ledgerMonthVOList, apList, enterpriseList, 0, 0, startDateVO, endDateVO, null);


        List<String> headerList = new ArrayList<>(3);
        headerList.add(userVO.getEnterpriseName());
        headerList.add("库存总账");
        headerList.add("期间:" + DateUtils.DateToString(startDateVO, DateUtils.FMT_DATE) + "-" + DateUtils.DateToString(endDateVO, DateUtils.FMT_DATE));

        LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>(8);
        rowHeaderMap.put("chainTypeName", "组织机构类型");
        rowHeaderMap.put("enterpriseCode", "组织机构编码");
        rowHeaderMap.put("enterpriseName", "组织机构名称");
        rowHeaderMap.put("startQuantity", "期初结存数量");
        rowHeaderMap.put("startAmount", "期初结存金额");
        rowHeaderMap.put("inQuantity", "期间收入数量");
        rowHeaderMap.put("inAmount", "期间收入金额");
        rowHeaderMap.put("outQuantity", "期间发出数量");
        rowHeaderMap.put("outAmount", "期间发出金额");

        rowHeaderMap.put("endQuantity", "期末余额数量");
        rowHeaderMap.put("endAmount", "期末余额金额");


        List<String> excelTitle = new ArrayList<>();
        List<List<StockLedgerEnterpriseVO>> excelList = new ArrayList<>();
        for (StockLedgerMonthVO monthVO : ledgerMonthVOList) {
            excelTitle.add(monthVO.getYear() + "年" + monthVO.getMonth() + "月");
            excelList.add(monthVO.getLedgerEnterpriseVOList());
        }

        purchaseGeneralComponent.commMultipleExcelExport(output, rowHeaderMap, excelList, headerList, excelTitle, null, false, new ArrayList<>());


    }


    /**
     * 计算当前商品 期间 收入总账
     *
     * @param enterpriseVO
     * @param periodDetailVO
     * @param goodsVO
     * @param userVO
     * @param startDateVO
     * @param endDateVO
     */
    private void calculationGoodsTotal(StockLedgerEnterpriseByGoodsVO enterpriseVO, AccountingPeriodDetailVO periodDetailVO, StockLedgerGoodsVO goodsVO, UserVO userVO, Date startDateVO, Date endDateVO) {

        Long enterpriseId = enterpriseVO.getEnterpriseId();
        Long goodsId = goodsVO.getGoodsId();
        Date startDate = periodDetailVO.getStartDate();
        Date endDate = periodDetailVO.getEndDate();
        // 请求的时间范围点  和   会计期间区间不同
        if (startDateVO != null && startDateVO.getTime() > periodDetailVO.getStartDate().getTime()) {
            startDate = startDateVO;
        }
        if (endDateVO != null && endDateVO.getTime() < endDateVO.getTime()) {
            endDate = endDateVO;
        }

        // 1. 计算期间收入 和 期间发出
        BaseInOutTotal inOutTotal = calculationInOutTotal(enterpriseId, goodsId, startDate, endDate);


        goodsVO.setInAmount(inOutTotal.getInAmount());
        goodsVO.setOutAmount(inOutTotal.getOutAmount());
        goodsVO.setInQuantity(inOutTotal.getInQuantity());
        goodsVO.setOutQuantity(inOutTotal.getOutQuantity());


        // 2. 计算期初结存和 和 期末余额

        BaseInOutTotal startTotal = calculationStartEndTotal(enterpriseId, goodsId, startDate);

        goodsVO.setStartAmount(startTotal.getStartAmount());
        goodsVO.setEndAmount(startTotal.getStartAmount().add(inOutTotal.getInAmount()).subtract(inOutTotal.getOutAmount()));
        goodsVO.setStartQuantity(startTotal.getStartQuantity());
        goodsVO.setEndQuantity(startTotal.getStartQuantity().add(inOutTotal.getInQuantity()).subtract(inOutTotal.getOutQuantity()));

    }


    /**
     * 商品属性转换
     *
     * @param goodsVO
     * @param goods
     */
    private void copyGoodsVO(StockLedgerGoodsVO goodsVO, Goods goods) {

        goodsVO.setApprovalNumber(goods.getApprovalNumber());
        goodsVO.setBarcode(goods.getBarcode());
        goodsVO.setDosageName(goods.getDosageName());
        goodsVO.setEnterpriseId(goods.getEnterpriseId());
        goodsVO.setGoodsCode(goods.getCode());
        goodsVO.setGoodsId(goods.getId());
        goodsVO.setGoodsGenericName(goods.getGenericName());
        goodsVO.setGoodsName(goods.getName());
        goodsVO.setGoodsPlace(goods.getPlace());
        goodsVO.setManufacturer(goods.getManufacturer());
        goodsVO.setUnitName(goods.getUnitName());
        goodsVO.setSpecification(goods.getSpecification());
    }

    /**
     * @Description: 从出入库明细中  获取期间 商品集合
     * @return:
     * @Author: dongyang.du
     * @Date: 14/01/2018
     */
    private List<Goods> getGoodsList(Date startDateVO, Date endDateVO, List<Enterprise> enterpriseList, StockInOutReqVO requestVO) {

        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("enterpriseList", enterpriseList);
        paramMap.put("goodsCode", requestVO.getGoodsCode());
        paramMap.put("barcode", requestVO.getBarcode());

        paramMap.put("pinyinCode", requestVO.getPinyinCode());
        paramMap.put("approvalNumber", requestVO.getApprovalNumber());
        paramMap.put("manufacturer", requestVO.getManufacturer());
        paramMap.put("goodsGenericName", requestVO.getGoodsGenericName());
        paramMap.put("goodsName", requestVO.getGoodsName());
        paramMap.put("place", requestVO.getPlace());

        List<Goods> goods = inOutRecordDetailMapper.getGoodsListByEnterpriseList(paramMap);

        return goods;
    }


    /**
     * @Description: 获取会计 期间会计月
     * @return:
     * @Author: dongyang.du
     * @Date: 14/01/2018
     */
    private List<AccountingPeriodDetailVO> getPeriodList(UserVO userVO, Date startDateVO, Date endDateVO) {

        // 查询全部的会计区间
        Long enterpriseId = userVO.getEnterpriseId();
        // 加盟店,总部读取自己的，自营店读取总部的
        if(ChainType.Selfoperatedshop.getCode() == userVO.getChainType()){
            enterpriseId = userVO.getParentId();
        }
        List<AccountingPeriodDetailVO> apList = accountingPeriodDetailMapper.selectByEnterpriseId(enterpriseId, startDateVO, endDateVO);

        if (apList.size() == 0) {
            throw new BusinessException("当前时间区间没有设置会计月,请先设置");
        }

        return apList;
    }


    /**
     * @Description: 获取 企业List
     * @return:
     * @Author: dongyang.du
     * @Date: 14/01/2018
     */
    private List<Enterprise> getEnterpriseList(UserVO userVO, BasePageReqParam requestVO) {

        List<Enterprise> enterpriseList = new ArrayList<>();

        if(requestVO.getEnterpriseId() != null){// 从企业跳转到商品时
            enterpriseList.add(enterpriseMapper.selectByPrimaryKey(requestVO.getEnterpriseId()));// 自己企业的
            return  enterpriseList;
        }

        Integer type = 0;// 默认查询自己企业(总部，自营店，加盟店)的
        if(requestVO.getChainType() != null){
            if(userVO.getChainType().equals(ChainType.Headquarters.getCode())
                    && 0 != requestVO.getChainType()){// 当前用户为总部，查询其他 加盟店或者自营店的数据时候
                type = 1; // 总部查询分店的
            }
        } else if(StringUtils.isNotEmpty(requestVO.getEnterpriseCode()) ||
                StringUtils.isNotEmpty(requestVO.getEnterpriseName())) {
            type = 2;// 查询
        }

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("type",type);
        paramMap.put("enterpriseId", userVO.getEnterpriseId());
        paramMap.put("chainType", requestVO.getChainType());
        paramMap.put("code", requestVO.getEnterpriseCode());
        paramMap.put("name", requestVO.getEnterpriseName());
        paramMap.put("status", EnableStatus.ENABLE.getStatus());
        paramMap.put("validFlag", ValidFlag.NORMAL.getCode());
        enterpriseList = enterpriseMapper.getEnterpriseByParam(paramMap);


        return enterpriseList;
    }


    /**
     * @param periodDetailVO
     * @param startDateVO
     * @param endDateVO
     * @param ledgerEnterpriseVO
     * @Description: 计算本月合计
     * @return:
     * @Author: dongyang.du
     * @Date: 14/01/2018
     */
    private void calculationMonthTotal(AccountingPeriodDetailVO periodDetailVO, Date startDateVO, Date endDateVO, StockLedgerEnterpriseVO ledgerEnterpriseVO) {
        Date startDate = periodDetailVO.getStartDate();
        Date endDate = periodDetailVO.getEndDate();
        // 请求的时间范围点  和   会计期间区间不同
        if (startDateVO != null && startDateVO.getTime() > periodDetailVO.getStartDate().getTime()) {
            startDate = startDateVO;
        }
        if (endDateVO != null && endDateVO.getTime() < endDateVO.getTime()) {
            endDate = endDateVO;
        }

        // 1. 计算期间收入 和 期间发出
        BaseInOutTotal inOutTotal = calculationInOutTotal(ledgerEnterpriseVO.getEnterpriseId(), null, startDate, endDate);


        ledgerEnterpriseVO.setInAmount(inOutTotal.getInAmount());
        ledgerEnterpriseVO.setOutAmount(inOutTotal.getOutAmount());
        ledgerEnterpriseVO.setInQuantity(inOutTotal.getInQuantity());
        ledgerEnterpriseVO.setOutQuantity(inOutTotal.getOutQuantity());


        // 2. 计算期初结存和 和 期末余额

        BaseInOutTotal startTotal = calculationStartEndTotal(ledgerEnterpriseVO.getEnterpriseId(), null, startDate);

        ledgerEnterpriseVO.setStartAmount(startTotal.getStartAmount());
        ledgerEnterpriseVO.setEndAmount(startTotal.getStartAmount().add(inOutTotal.getInAmount()).subtract(inOutTotal.getOutAmount()));
        ledgerEnterpriseVO.setStartQuantity(startTotal.getStartQuantity());
        ledgerEnterpriseVO.setEndQuantity(startTotal.getStartQuantity().add(inOutTotal.getInQuantity()).subtract(inOutTotal.getOutQuantity()));


    }

    /**
     * @param enterpriseId
     * @param goodsId
     * @param startDate
     * @Description: 计算 期初 总和
     * @return:
     * @Author: dongyang.du
     * @Date: 14/01/2018
     */
    private BaseInOutTotal calculationStartEndTotal(Long enterpriseId, Long goodsId, Date startDate) {


        Map<String, Object> param = new HashMap<>();
        param.put("enterpriseId", enterpriseId);
        param.put("goodsId", goodsId);
        param.put("endDateLT", startDate);
        param.put("direction", OrderDirection.OUT.getDirection());

        // 当前时间之前 全部出的
        CostInfoPostVO outBalance = inOutRecordDetailMapper.getInOutDetailTotalByParam(param);

        // 当前时间之前 全部入的
        param.put("direction", OrderDirection.IN.getDirection());

        CostInfoPostVO inBalance = inOutRecordDetailMapper.getInOutDetailTotalByParam(param);

        BigDecimal inNotaxCostAmount = inBalance == null ? BigDecimal.ZERO : inBalance.getNotaxCostAmountTotal();
        BigDecimal outNotaxCostAmount = outBalance == null ? BigDecimal.ZERO : outBalance.getNotaxCostAmountTotal();

        BigDecimal startAmount = inNotaxCostAmount.subtract(outNotaxCostAmount);

        BigDecimal inBalanceQuantity = inBalance == null ? BigDecimal.ZERO : inBalance.getQuantityTotal();
        BigDecimal outBalanceQuantity = outBalance == null ? BigDecimal.ZERO : outBalance.getQuantityTotal();

        BigDecimal startQuantity = inBalanceQuantity.subtract(outBalanceQuantity);

        BaseInOutTotal baseInOutTotal = new BaseInOutTotal();
        baseInOutTotal.setStartAmount(startAmount);
        baseInOutTotal.setStartQuantity(startQuantity);

        return baseInOutTotal;

    }

    /**
     * @param enterpriseId
     * @param startDate
     * @param endDate
     * @Description: 计算期间出入总和
     * @return:
     * @Author: dongyang.du
     * @Date: 14/01/2018
     */
    private BaseInOutTotal calculationInOutTotal(Long enterpriseId, Long goodsId, Date startDate, Date endDate) {

        Map<String, Object> param = new HashMap<>();
        param.put("enterpriseId", enterpriseId);
        param.put("goodsId", goodsId);

        param.put("direction", OrderDirection.IN.getDirection());
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        // 期间入了多少
        CostInfoPostVO inCost = inOutRecordDetailMapper.getInOutDetailTotalByParam(param);

        param.put("direction", OrderDirection.OUT.getDirection());

        // 期间出了多少
        CostInfoPostVO outCost = inOutRecordDetailMapper.getInOutDetailTotalByParam(param);

        BigDecimal inAmountTotal = inCost == null ? BigDecimal.ZERO : inCost.getNotaxCostAmountTotal();
        BigDecimal outAmountTotal = outCost == null ? BigDecimal.ZERO : outCost.getNotaxCostAmountTotal();

        BigDecimal inQuantityTotal = inCost == null ? BigDecimal.ZERO : inCost.getQuantityTotal();
        BigDecimal outQuantityTotal = outCost == null ? BigDecimal.ZERO : outCost.getQuantityTotal();


        BaseInOutTotal inOutTotal = new BaseInOutTotal();
        inOutTotal.setInAmount(inAmountTotal);
        inOutTotal.setInQuantity(inQuantityTotal);
        inOutTotal.setOutAmount(outAmountTotal);
        inOutTotal.setOutQuantity(outQuantityTotal);
        return inOutTotal;
    }
}
