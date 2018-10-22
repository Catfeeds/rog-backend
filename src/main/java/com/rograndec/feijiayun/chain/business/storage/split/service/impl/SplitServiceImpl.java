package com.rograndec.feijiayun.chain.business.storage.split.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseCargoAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.CargoQualityStateVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.keytable.vo.AvgCostVO;
import com.rograndec.feijiayun.chain.business.storage.split.dao.SplitDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.split.dao.SplitMapper;
import com.rograndec.feijiayun.chain.business.storage.split.entity.Split;
import com.rograndec.feijiayun.chain.business.storage.split.entity.SplitDetail;
import com.rograndec.feijiayun.chain.business.storage.split.service.SplitService;
import com.rograndec.feijiayun.chain.business.storage.split.vo.RequestSaveGoodsVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.RequestSplitOrderVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.SplitGoodsStockVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.SplitOrderPageVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.GoodsDefTaxRateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/29 <br/>
 * 描述：拆零设置-商品拆零
 */
@Service
public class SplitServiceImpl implements SplitService {

    @Autowired
    private SplitMapper splitMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private SplitDetailMapper splitDetailMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WarehouseShelfMapper warehouseShelfMapper;

    @Autowired
    private WarehouseCargoAreaMapper warehouseCargoAreaMapper;

    @Autowired
    private InOutComponent inOutComponent;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private FinanceComponent financeComponent;

    @Override
    public List<SplitGoodsStockVO> getGoodsStockList(String param, UserVO userVO) {
        Long enterpriseId = userVO.getEnterpriseId();
        if(userVO.getChainType() == ChainType.Selfoperatedshop.getCode()){
            enterpriseId = userVO.getParentId();
        }

        Integer type = 0;
        if(ChainType.Division.getCode() == userVO.getChainType()){// 加盟店搜索自己和总部的拆零商品
            type = 1;
            enterpriseId = userVO.getParentId();
        }
        List<SplitGoodsStockVO> splitGoodsStockList = splitMapper.selectGoodsStock(enterpriseId,userVO.getEnterpriseId(), param,type);
        if (splitGoodsStockList == null) {
            splitGoodsStockList = new ArrayList<>();
        }

        if (splitGoodsStockList.size() == 0) {
            return splitGoodsStockList;
        }

        /*for (SplitGoodsStockVO stockVO : splitGoodsStockList) {
            String splitShelfStatusDesc = getShelfStatusDesc(stockVO.getSplitShelfId());
            if (StringUtils.isEmpty(splitShelfStatusDesc)) {
                stockVO.setSplitShelfId(null);
                stockVO.setSplitShelfName(null);
                stockVO.setSplitShelfStatusDesc(null);
            } else {
                stockVO.setSplitShelfStatusDesc(splitShelfStatusDesc);
            }

        }*/
        Iterator<SplitGoodsStockVO> it = splitGoodsStockList.iterator();
        while (it.hasNext()){
            SplitGoodsStockVO stockVO = it.next();
            String splitShelfStatusDesc = getShelfStatusDesc(stockVO.getSplitShelfId());
            if (StringUtils.isEmpty(splitShelfStatusDesc)) {
                stockVO.setSplitShelfId(null);
                stockVO.setSplitShelfName(null);
                stockVO.setSplitShelfStatusDesc(null);
            } else {
                stockVO.setSplitShelfStatusDesc(splitShelfStatusDesc);
            }
            Goods goods = goodsMapper.selectByPrimaryKey(stockVO.getSplitGoodsId());
            if (goods.getStatus() != EnableStatus.ENABLE.getStatus()){
                it.remove();
            }
        }

        return splitGoodsStockList;
    }

    @Override
    public Page getSplitOrderList(Page page, Date startTime, Date endTime, String code, String splitManName, String auditManName, String order, String sort, UserVO loginUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("code", code);
        map.put("splitManName", splitManName);
        map.put("auditManName", auditManName);
        map.put("enterpriseId", loginUser.getEnterpriseId());

        //排序
        if ("splitDate".equals(order)) {
            order = "split_date";
        } else if ("code".equals(order)) {
            order = "code";
        }
        map.put("order", order);
        map.put("sort", sort);

        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<SplitOrderPageVO> splitOrderList = splitMapper.getSplitOrderList(map);
        page.setResult(splitOrderList);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        return page;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int saveSplitOrder(UserVO loginUser, RequestSplitOrderVO requestSaveSplitVO) throws Exception {
        //拆零明细列表
        List<SplitDetail> splitDetailList = getSplitDetailList(loginUser, requestSaveSplitVO);

        //插入商品拆零单据记录
        Split split = insertSplitOrder(loginUser, requestSaveSplitVO, splitDetailList);

        //插入商品拆零明细记录
        List<SplitDetail> splitDetails = new ArrayList<>();
        for (SplitDetail splitDetail : splitDetailList) {
            //单据id
            splitDetail.setSplitId(split.getId());
            //单据编码
            splitDetail.setSplitCode(split.getCode());

            //插入拆零明细
            splitDetailMapper.insertSelective(splitDetail);
            splitDetails.add(splitDetail);
        }

        //整盒商品、拆零商品出入库
        OrderModelBuilder builder = new OrderModelBuilder(loginUser);
        OrderModel outOrderModel = builder.buildSplitModel(OrderRule.SPLIT, split, splitDetails, OrderDirection.SPLIT_OUT.getDirection());
        OrderModel inOrderModel = builder.buildSplitModel(OrderRule.SPLIT, split, splitDetails, OrderDirection.SPLIT_IN.getDirection());
        inOutComponent.generateKeyTableDatas(OrderDirection.OUT, outOrderModel);
        inOutComponent.generateKeyTableDatas(OrderDirection.IN, inOrderModel);
        //财务通用接口
        financeComponent.splitToBalanceAndVoucher(loginUser,split);
        return 0;
    }

    /**
     * 获取拆零商品id列表
     *
     * @param splitGoodsStockList
     * @return
     */
    private List<Long> getSplitGoodsIds(List<SplitGoodsStockVO> splitGoodsStockList) {
        if (splitGoodsStockList == null || splitGoodsStockList.size() == 0) {
            return new ArrayList<>();
        }

        Set<Long> ids = new HashSet<>();
        for (SplitGoodsStockVO splitGoodsStockVO : splitGoodsStockList) {
            ids.add(splitGoodsStockVO.getSplitGoodsId());
        }

        return new ArrayList<>(ids);
    }

    /**
     * 插入拆零单据
     */
    private Split insertSplitOrder(UserVO userVO, RequestSplitOrderVO requestSaveSplitVO, List<SplitDetail> splitDetailList) throws Exception {
        if (requestSaveSplitVO == null || requestSaveSplitVO.getSplitGoods() == null
                || requestSaveSplitVO.getSplitGoods().size() == 0) {
            throw new BusinessException("生成拆零单错误，请检查参数！");
        }

        Split split = new Split();
        split = (Split)EntityUtils.reflectAddSetDefaultValue(split.getClass(), userVO);
        split.setEnterpriseId(userVO.getEnterpriseId());
        split.setParentId(userVO.getParentId());
        split.setOrderType(OrderRule.SPLIT.getType());
        split.setSplitDate(requestSaveSplitVO.getSplitDate());
        //拆零单据编号生成规则
        String code = orderCodeComponent.generate(OrderRule.SPLIT.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
        split.setCode(code);

        //拆单人信息
        User splitMan = userMapper.selectByPrimaryKey(requestSaveSplitVO.getSplitManId());
        split.setSplitManId(splitMan.getId());
        split.setSplitManCode(splitMan.getCode());
        split.setSplitManName(splitMan.getName());

        //复核人信息
        User auditMan = userMapper.selectByPrimaryKey(requestSaveSplitVO.getAuditManId());
        split.setAuditManId(auditMan.getId());
        split.setAuditManCode(auditMan.getCode());
        split.setAuditManName(auditMan.getName());
        split.setFlowCode(requestSaveSplitVO.getFlowCode());
        split.setStatus(1);//暂不维护，默认1

        //计算价格
        calculateSplitPrice(split, splitDetailList);
        
        splitMapper.insertSelective(split);
        return split;
    }

    /**
     * 拆零单据 价格相关计算
     * @param split
     */
    private void calculateSplitPrice(Split split, List<SplitDetail> splitDetailList) {
        BigDecimal quantityTotal = BigDecimal.ZERO;
        BigDecimal splitQuantityTotal = BigDecimal.ZERO;
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal notaxAmountTotal = BigDecimal.ZERO;
        for (SplitDetail splitDetail : splitDetailList) {
            quantityTotal = quantityTotal.add(splitDetail.getQuantity());
            splitQuantityTotal = splitQuantityTotal.add(splitDetail.getTargetQuantity());
            amountTotal = amountTotal.add(splitDetail.getAmount());
            notaxAmountTotal = notaxAmountTotal.add(splitDetail.getNotaxAmount());
        }

        //整盒商品数量合计
        split.setQuantityTotal(quantityTotal);
        //拆零商品数量合计
        split.setSplitQuantityTotal(splitQuantityTotal);
        //整盒商品品种、拆零商品品种
        int varietiesQuantity = splitDetailList.size();
        split.setVarietiesQuantity(varietiesQuantity);
        split.setSplitVarietiesQuantity(varietiesQuantity);
        //合计金额
        split.setAmountTotal(amountTotal);
        //不含税金额合计
        split.setNotaxAmountTotal(notaxAmountTotal);
        //税额合计
        split.setTaxAmountTotal(amountTotal.subtract(notaxAmountTotal));
    }

    /**
     * 插入拆零明细
     * @throws Exception 
     */
    private List<SplitDetail> getSplitDetailList(UserVO userVO, RequestSplitOrderVO requestSaveSplitVO) throws Exception {
        if (requestSaveSplitVO == null || requestSaveSplitVO.getSplitGoods() == null
                || requestSaveSplitVO.getSplitGoods().size() == 0) {
            throw new BusinessException("生成拆零明细错误，请检查参数！");
        }

        List<SplitDetail> splitDetails = new ArrayList<>();
        SplitDetail splitDetail;
        for (RequestSaveGoodsVO saveGoodsVO : requestSaveSplitVO.getSplitGoods()) {
            splitDetail = new SplitDetail();
            splitDetail = (SplitDetail)EntityUtils.reflectAddSetDefaultValue(splitDetail.getClass(), userVO);
            //企业信息
            splitDetail.setEnterpriseId(userVO.getEnterpriseId());
            splitDetail.setParentId(userVO.getParentId());
            //单据信息
//            splitDetail.setSplitId(splitId);//单据id
            splitDetail.setOrderType(OrderRule.SPLIT.getType());//单据类型
//            splitDetail.setSplitCode(splitCode);//单据编码
            splitDetail.setSplitDate(requestSaveSplitVO.getSplitDate());//拆单日期

            //商品相关信息（完整信息）
            Map<String, Object> map = new HashMap<>();
            map.put("enterpriseId", userVO.getEnterpriseId());
            map.put("goodsId", saveGoodsVO.getGoodsId());
            map.put("splitGoodsId", saveGoodsVO.getSplitGoodsId());
            map.put("lotId", saveGoodsVO.getLotId());
            map.put("shelfId", saveGoodsVO.getShelfId());
            SplitGoodsStockVO splitGoodsStockVO = splitMapper.selectGoodsStockById(map);
            if (splitGoodsStockVO == null) {
                throw new BusinessException("查询拆零商品信息错误！");
            }

            Goods splitGoods = goodsMapper.selectByPrimaryKey(saveGoodsVO.getSplitGoodsId());
            if (splitGoods == null) {
                throw new BusinessException("查询商品信息错误！");
            }
            splitGoodsStockVO.setSplitGoodsCode(splitGoods.getCode());
            splitGoodsStockVO.setSplitGoodsName(splitGoods.getName());

            //整盒商品信息
            splitDetail.setGoodsId(splitGoodsStockVO.getGoodsId());
            splitDetail.setGoodsCode(splitGoodsStockVO.getGoodsCode());
            splitDetail.setGoodsName(splitGoodsStockVO.getGoodsName());
            splitDetail.setLotId(splitGoodsStockVO.getLotId());
            splitDetail.setLotNumber(splitGoodsStockVO.getLotNumber());
            splitDetail.setProductDate(splitGoodsStockVO.getProductDate());
            splitDetail.setValidDate(splitGoodsStockVO.getValidDate());
            splitDetail.setShelfId(splitGoodsStockVO.getShelfId());
            splitDetail.setShelfName(splitGoodsStockVO.getShelfName());
            splitDetail.setShelfStatusDesc(splitGoodsStockVO.getShelfStatusDesc());

            //整盒商品拆零数量
            splitDetail.setQuantity(saveGoodsVO.getQuantity());

            //拆零商品信息
            splitDetail.setSplitGoodsId(splitGoodsStockVO.getSplitGoodsId());
            splitDetail.setSplitGoodsCode(splitGoodsStockVO.getSplitGoodsCode());
            splitDetail.setSplitGoodsName(splitGoodsStockVO.getSplitGoodsName());
            //拆零商品货位信息
            WarehouseShelf shelfInfo = warehouseShelfMapper.selectByPrimaryKey(saveGoodsVO.getSplitShelfId());
            if (shelfInfo == null) {
                throw new BusinessException("所选拆零商品货位不存在！");
            }
            splitDetail.setSplitShelfId(shelfInfo.getId());
            splitDetail.setSplitShelfName(shelfInfo.getName());

            // 获取货位质量状态
            String shelfStatusDesc = getShelfStatusDesc(shelfInfo.getId());
            splitDetail.setSplitShelfStatusDesc(shelfStatusDesc);

            //拆零商品拆零信息
            splitDetail.setSplitQuantity(splitGoodsStockVO.getSplitQuantity());
            splitDetail.setTargetQuantity(saveGoodsVO.getQuantity().multiply(splitGoodsStockVO.getSplitQuantity()));
            splitDetail.setLineNum(saveGoodsVO.getLineNum());
            splitDetail.setStatus(1);//暂不维护，默认1

            //拆零单据明细价格计算
            calculateSplitDetailPrice(splitDetail);

            //创建人信息
            splitDetail.setCreaterId(userVO.getUserId());
            splitDetail.setCreaterCode(userVO.getUserCode());
            splitDetail.setCreaterName(userVO.getUserName());
            splitDetail.setCreateTime(new Date());
            splitDetails.add(splitDetail);
        }

        return splitDetails;
    }

    /**
     * 计算拆零明细中的价格信息
     * @param splitDetail
     */
    private void calculateSplitDetailPrice(SplitDetail splitDetail) {
        // 取成本价
        Long enterpriseId = splitDetail.getEnterpriseId();
        Long goodsId = splitDetail.getGoodsId();
       // BigDecimal costPrice = commonComponent.getCostPriceWithLotId(enterpriseId, goodsId, splitDetail.getLotId(), splitDetail.getQuantity());
       List<Long> lotIds = new ArrayList<>();
       lotIds.add(splitDetail.getLotId());
       AvgCostVO avgCostVO = commonComponent.getAvgCostVO(enterpriseId, goodsId, lotIds, splitDetail.getQuantity());
       BigDecimal costPrice = avgCostVO.getAvgCostPrice();
        
        // 取商品的默认的进项税
        GoodsDefTaxRateVO goodsDefTaxRateVO = commonComponent.getGoodsDefTaxRateInfo(enterpriseId, goodsId, TaxRateType.PURCHASE.getType());
        BigDecimal taxRate = BigDecimal.ZERO;
        Long taxRateId = new Long(0);
        if (goodsDefTaxRateVO != null) {
            taxRateId = goodsDefTaxRateVO.getTaxRateId() == null ? new Long(0) : goodsDefTaxRateVO.getTaxRateId();
            taxRate = goodsDefTaxRateVO.getTaxRate() == null ? BigDecimal.ZERO : goodsDefTaxRateVO.getTaxRate();
        }
        //整盒商品单价
        splitDetail.setUnitPrice(costPrice);
        //金额
        BigDecimal amount = costPrice.multiply(splitDetail.getQuantity());
        splitDetail.setAmount(amount);
        //拆零商品单价
        splitDetail.setSplitUnitPrice(amount.divide(splitDetail.getTargetQuantity(), 6, BigDecimal.ROUND_HALF_UP));
        //税率ID
        splitDetail.setTaxRateId(taxRateId);
        //税率
        splitDetail.setTaxRate(taxRate);
        //整盒商品不含税单价
        //BigDecimal notaxPrice = costPrice.divide(BigDecimal.ONE.add(taxRate.divide(new BigDecimal(100))), 6, BigDecimal.ROUND_UP);
        BigDecimal notaxPrice = avgCostVO.getAvgNotaxCostPrice();
        splitDetail.setNotaxPrice(notaxPrice);
        //拆零商品不含税单价
        splitDetail.setSplitNotaxPrice(notaxPrice.multiply(splitDetail.getQuantity()).divide(splitDetail.getTargetQuantity(), 6, BigDecimal.ROUND_HALF_UP));
        //不含税金额
        splitDetail.setNotaxAmount(notaxPrice.multiply(splitDetail.getQuantity()));
        //税额
        splitDetail.setTaxAmount(amount.subtract(splitDetail.getNotaxAmount()));
    }

    /**
     * 根据货位ID获取货区的质量状态
     *
     * @param shelfId
     * @return
     */
    public String getShelfStatusDesc(Long shelfId) {
        String shelfStatusDesc = "";
        CargoQualityStateVO cargoQulityState = warehouseCargoAreaMapper.getCargoByShelfId(shelfId);

        //说明该货位不存在或者已被禁用，前台不显示
        if (cargoQulityState == null) {
            return null;
        }
        Integer jobType = cargoQulityState.getJobType();
        Integer jobArea = cargoQulityState.getJobArea();
        if (0 == jobType && 0 == jobArea) {
            shelfStatusDesc = "合格";
        } else if (0 == jobType && 2 == jobArea) {
            shelfStatusDesc = "不合格";
        } else {
            shelfStatusDesc = "其它";
        }
        return shelfStatusDesc;
    }
}
