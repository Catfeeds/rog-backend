package com.rograndec.feijiayun.chain.business.distr.parent.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInReturnOutMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrReqPlanDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrReqPlanMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOut;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrReqPlan;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrReqPlanDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.*;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.*;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrSendService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.*;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.keytable.dao.CostMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockLockRecordMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.Cost;
import com.rograndec.feijiayun.chain.business.keytable.entity.Stock;
import com.rograndec.feijiayun.chain.business.keytable.entity.StockLockRecord;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrSendReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.OrderReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrSend;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.DistrLackStatus;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReqPlanStatus;
import com.rograndec.feijiayun.chain.common.constant.status.DistrSendStatus;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.GoodsDefTaxRateVO;
import com.rograndec.feijiayun.chain.common.vo.LockQtyArgVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.collection.DeepCloneListUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/7 <br/>
 * 描述：配送管理-总部-配送单
 */
@Service
public class DistrSendServiceImpl implements DistrSendService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;

    @Autowired
    private PriceOrderDetailMapper priceOrderDetailMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DistrSendMapper distrSendMapper;

    @Autowired
    private DistrSendDetailMapper distrSendDetailMapper;

    @Autowired
    private DistrReqPlanMapper distrReqPlanMapper;

    @Autowired
    private DistrReqPlanDetailMapper distrReqPlanDetailMapper;

    @Autowired
    private DistrLackMapper distrLackMapper;

    @Autowired
    private DistrLackDetailMapper distrLackDetailMapper;

    @Autowired
    private PurchaseInStorageMapper purchaseInStorageMapper;

    @Autowired
    private PurchaseInStorageDetailMapper purchaseInStorageDetailMapper;

    @Autowired
    private DistrReturnInMapper distrReturnInMapper;

    @Autowired
    private DistrReturnInDetailMapper distrReturnInDetailMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private DistrReturnNoticeMapper distrReturnNoticeMapper;

    @Autowired
    private DistrInReturnOutMapper distrInReturnOutMapper;

    @Autowired
    private StockLockRecordMapper  stockLockRecordMapper;

    @Autowired
    private CostMapper costMapper;

    @Override
    public List<DistrEnterpriseVO> getDistrEnterpriseList(Long enterPriseId, String param) {
        return enterpriseMapper.getEnterpriseByParentId(enterPriseId,param);
    }

    @Override
    public List<DistrSendGoodsVO> getGoodsStockList(UserVO userVO, Long requestUnitId, String param) throws Exception {

        if (requestUnitId == null) {
            throw new BusinessException("请先选择要货单位！");
        }
        //要货单位经营范围
        List<Long> businessScopeIds = getBusinessScopeIds(requestUnitId);
        if (businessScopeIds.size() == 0) {
            throw new BusinessException("要货单位经营范围为空！");
        }

        Long enterpriseId = userVO.getEnterpriseId();
        Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", enterpriseId);
        map.put("businessScopeIds", businessScopeIds);
        map.put("param", param);
        map.put("today",new Date());
    //zhuchao:根据商品id进行分组查询保证数量只有一个
        List<DistrSendGoodsVO> distrSendGoodsVOList = goodsMapper.getDistrSendGoodsList(map);
        if (distrSendGoodsVOList == null) {
            distrSendGoodsVOList = new ArrayList<>();
        }

        if (distrSendGoodsVOList.size() == 0) {
            return distrSendGoodsVOList;
        }

        //门店对应商品可用库存
        BigDecimal childUsableQuantity;
        for (DistrSendGoodsVO distrSendGoodsVO : distrSendGoodsVOList) {
            //门店可用库存
            childUsableQuantity = stockMapper.getUsableQuantityGroupByGoodsId(requestUnitId, distrSendGoodsVO.getId());
            if (childUsableQuantity != null) {
                distrSendGoodsVO.setChildUsableQuantity(childUsableQuantity);
            }
        }

        return distrSendGoodsVOList;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public DistrSend saveAddDistrSend(UserVO userVO, RequestDistrSendVO requestDistrSendVO, boolean type) throws Exception {
        DistrSend distrSend = commonComponent.generateAndSaveDistrSendOrder(userVO, requestDistrSendVO, type,type);
        if(requestDistrSendVO.getRedisKeyValue() != null){
            redisComponent.removeDraftCacheVO(userVO.getEnterpriseId(), OrderRule.DISTR_ORDER.getCodePrefix(),requestDistrSendVO.getRedisKeyValue());
        }

        /**
         * 更新调用业务单据的状态
         */
        commonComponent.updateBaseOrderStatus(userVO,distrSend,type);
        return distrSend;
    }







    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int saveTransferReqPlan(UserVO userVO, RequestTransferBaseOrderVO requestVO) throws Exception {
        //按要货单位合并配送单
        if (requestVO == null || requestVO.getBaseOrderDtlList() == null || requestVO.getBaseOrderDtlList().size() == 0) {
            throw new BusinessException("生成配送单失败，请检查参数");
        }

        //要货单位列表
        Set<Long> requestUnitIds = new HashSet<>();
        Set<Long> baseOrderIds = new HashSet<>();
        for (RequestBaseOrderVO orderDtlVO : requestVO.getBaseOrderDtlList()) {
            requestUnitIds.add(orderDtlVO.getRequestUnitId());
        }

        //封装保存配货单列表
        List<RequestDistrSendVO> requestDistrSendVOList = new ArrayList<>();
        //封装保存配货单明细列表
        List<RequestDistrGoods> requestGoodsList;
        RequestDistrSendVO requestDistrSendVO;
        RequestDistrGoods requestGoods;
        //要货计划明细信息
        DistrReqPlanDetail distrReqPlanDetail;
        //需要生成 缺配单 的要货计划明细列表
        List<DistrLackDetail> distrLackDetails = new ArrayList<>();

        //配送税
        GoodsDefTaxRateVO distrTaxRateVO;
        //可配成本列表，用于计算配送价
        Map<String,List<Cost>> usableCostMap = new HashMap<>();

        for (Long requestUnitId : requestUnitIds) {
            requestDistrSendVO = new RequestDistrSendVO(requestVO.getDistrManId(), requestVO.getDistrDate(),
                    requestUnitId, new BigDecimal(100), BigDecimal.ZERO);
            requestDistrSendVO.setDistrRule(requestVO.getDistrRule());
            requestDistrSendVO.setLackHandle(requestVO.getLackHandle());
            requestGoodsList = new ArrayList<>();
            for (RequestBaseOrderVO orderDtlVO : requestVO.getBaseOrderDtlList()) {
                if (requestUnitId.equals(orderDtlVO.getRequestUnitId())) {
                    distrReqPlanDetail = distrReqPlanDetailMapper.selectByPrimaryKey(orderDtlVO.getBaseOrderDtlId());
                    if(orderDtlVO.getQuantity().compareTo(BigDecimal.ZERO) == 1){
                        requestGoods = new RequestDistrGoods(distrReqPlanDetail.getGoodsId(), orderDtlVO.getQuantity(), distrReqPlanDetail.getQuantity(),
                                orderDtlVO.getLineNum(), orderDtlVO.getBaseOrderDtlId(), distrReqPlanDetail.getPlanId(), distrReqPlanDetail.getOrderType());

                        //税率
                        distrTaxRateVO = commonComponent.getGoodsDefTaxRateInfo(requestUnitId, distrReqPlanDetail.getGoodsId(), TaxRateType.DISTRIBUTION.getType());
                        if (distrTaxRateVO == null) {
                            throw new BusinessException("查询商品配送税错误！");
                        }
                        requestGoods.setTaxRate(distrTaxRateVO.getTaxRate());
                        requestGoods.setTaxRateId(distrTaxRateVO.getTaxRateId());

                        requestGoods.setLineDiscount(new BigDecimal(100));

                        requestGoodsList.add(requestGoods);
                    }


                    //要货计划单ID
                    baseOrderIds.add(distrReqPlanDetail.getPlanId());


                    /**
                     * 判断是否需要生成缺配单（1、缺配处理规则为生成缺配单；2、配货数量 A < 要货数量 B）
                     * 配货数量：orderDtlVO.getQuantity()  A
                     * 要货数量：PS:distrReqPlanDetail.getQuantity() B
                     * 如果A==0,只生成缺配单;
                     * 如果A<B,同时生成配货单和缺配单,配货数量==A,缺配数量==B-A
                     */
                    if (requestVO.getLackHandle() == 0 && distrReqPlanDetail.getQuantity().compareTo(orderDtlVO.getQuantity()) == 1) {
                        distrLackDetails.add(getDistLackDetail(userVO, distrReqPlanDetail, orderDtlVO.getQuantity()));
                    }
                }
            }
            if(CollectionUtils.isNotEmpty(requestGoodsList)){
                // 重算配货单价
                reCalculateDistrPrice(userVO, requestGoodsList, requestDistrSendVO, usableCostMap);

                requestDistrSendVO.setDistrGoogsList(requestGoodsList);
                requestDistrSendVOList.add(requestDistrSendVO);
            }

        }

//        if (requestDistrSendVOList.size() == 0) {
//            throw new BusinessException("生成配送单失败，请检查参数");
//        }

        //第一步：生成配货单，规则-按门店生成配货单及配货单明细
        for (RequestDistrSendVO requestSendVO : requestDistrSendVOList) {
            commonComponent.generateAndSaveDistrSendOrder(userVO, requestSendVO, true,true);
        }

        //第二步：生成缺配单，规则-按每个要货计划生成一个缺配单
        addDistrLackOrder(userVO, distrLackDetails);

        //第三部：修改要货计划单状态
        updateReqPlanOrderStatus(userVO, baseOrderIds, DistrReqPlanStatus.FINISHED);
        return 0;
    }

    /**
     * 重算配货单价
     * @param userVO
     * @param requestGoodsList
     * @param requestDistrSendVO
     * @param usableCostMap
     * @throws Exception
     */
    private void reCalculateDistrPrice(UserVO userVO, List<RequestDistrGoods> requestGoodsList,
                                       RequestDistrSendVO requestDistrSendVO, Map<String,List<Cost>> usableCostMap) throws Exception {
        // 要货单位ID
        Long reqUnitId = requestDistrSendVO.getRequestUnitId();
        for(RequestDistrGoods requestDistrGoods:requestGoodsList){
            BigDecimal distrPrice = BigDecimal.ZERO;
            // 配货单位ID,即总部ID
            Long distrUnitId = userVO.getEnterpriseId();
            // 商品ID
            Long goodsId = requestDistrGoods.getGoodsId();
            // 要货数量
            BigDecimal reqQty = requestDistrGoods.getQuantity();

            String costKey = distrUnitId + "_" + goodsId;

            EnterpriseBusiness enterpriseBusiness = commonComponent.getEnterpriseBusiness(reqUnitId);
            Integer distrPriceType = enterpriseBusiness.getDistrPriceType() == null ? 0 : enterpriseBusiness.getDistrPriceType();
            if (distrPriceType.equals(DistrPriceType.PRICE_LIST.getCode())) {
                Long distrPriceOrderId = enterpriseBusiness.getDistrPriceOrderId();
                PriceOrderDetail priceOrderDetail = commonComponent.getPriceOrderDetail(distrUnitId, distrPriceOrderId, goodsId);
                distrPrice = priceOrderDetail.getDistrPrice() == null ? BigDecimal.ZERO : priceOrderDetail.getDistrPrice();
                
                resetUsableCostMap(usableCostMap, distrUnitId, goodsId, reqQty, costKey);
            }else if (distrPriceType.equals(DistrPriceType.COST_PRICE.getCode())) {
                // 根据先进先出原则获取商品的成本单价
                distrPrice = getCostPrice(distrUnitId, goodsId, reqQty, usableCostMap, costKey);
            } else if (distrPriceType.equals(DistrPriceType.COST_PLUS.getCode())) {
                BigDecimal addRate = enterpriseBusiness.getAddRate() == null ? BigDecimal.ZERO : enterpriseBusiness.getAddRate();
                BigDecimal costPrice = getCostPrice(distrUnitId, goodsId, reqQty, usableCostMap, costKey);
                distrPrice = costPrice.multiply(addRate.divide(new BigDecimal(100)).add(BigDecimal.ONE)).setScale(6, RoundingMode.HALF_UP);
            }
            requestDistrGoods.setUnitPrice(distrPrice);
        }
    }

    private void resetUsableCostMap(Map<String, List<Cost>> usableCostMap, Long distrUnitId, Long goodsId, BigDecimal reqQty, String costKey) {
        List<Cost> costList = getCostList(distrUnitId, goodsId, usableCostMap, costKey);
        for(Cost cost:costList){
            BigDecimal usableQuantity = cost.getUsableQuantity()==null?BigDecimal.ZERO:cost.getUsableQuantity();
            if(usableQuantity.compareTo(reqQty) >= 0){
                cost.setUsableQuantity(cost.getUsableQuantity().subtract(reqQty));
                reqQty = BigDecimal.ZERO;
            }else{
                cost.setUsableQuantity(BigDecimal.ZERO);
                reqQty = reqQty.subtract(cost.getUsableQuantity());
            }
            if (reqQty.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }
        }
        usableCostMap.put(costKey,costList);
    }

    /**
     * 按照先进先出规则获取成本价
     * @param distrUnitId
     * @param goodsId
     * @param reqQty
     * @param usableCostMap
     * @return
     * @throws Exception
     */
    private BigDecimal getCostPrice(Long distrUnitId, Long goodsId, BigDecimal reqQty, Map<String, List<Cost>> usableCostMap, String costKey) throws  Exception{
        BigDecimal costPrice = BigDecimal.ZERO;
        List<Cost> costList = getCostList(distrUnitId, goodsId, usableCostMap, costKey);
        if (CollectionUtils.isNotEmpty(costList)) {
            List<Cost> usableCostList = DeepCloneListUtils.deepCopy(costList);
            BigDecimal tmpCostAmountTotal = BigDecimal.ZERO;
            BigDecimal tmpQuantityTotal = BigDecimal.ZERO;
            for (Cost cost : costList) {
                if (null == cost) {
                    continue;
                }
                if(cost.getUsableQuantity().compareTo(BigDecimal.ZERO)==0){
                    usableCostList.remove(cost);
                    continue;
                }
                if (cost.getUsableQuantity().compareTo(reqQty) >= 0) {
                    tmpCostAmountTotal = cost.getRealPrice().multiply(reqQty).add(tmpCostAmountTotal);
                    tmpQuantityTotal = reqQty.add(tmpQuantityTotal);
                    // 当前配完后，重置剩余可配数量
                    for(Cost useableCost:usableCostList){
                        if(useableCost.getId().equals(cost.getId())){
                            useableCost.setUsableQuantity(cost.getUsableQuantity().subtract(reqQty));
                        }
                    }
                    reqQty = BigDecimal.ZERO;
                } else {
                    tmpCostAmountTotal = cost.getRealPrice().multiply(cost.getUsableQuantity()).add(tmpCostAmountTotal);
                    tmpQuantityTotal = cost.getUsableQuantity().add(tmpQuantityTotal);
                    // 可配成本列表中剔除当前配完，可用数量为0的成本
                    usableCostList.remove(cost);
                    reqQty = reqQty.subtract(cost.getUsableQuantity());

                }
                if (reqQty.compareTo(BigDecimal.ZERO) <= 0) {
                    break;
                }
            }
            if (tmpQuantityTotal.compareTo(BigDecimal.ZERO) > 0) {
                costPrice = tmpCostAmountTotal.divide(tmpQuantityTotal, 6, RoundingMode.HALF_UP);
            }
            usableCostMap.put(costKey,usableCostList);
        }
        return costPrice;
    }

    private List<Cost> getCostList(Long distrUnitId, Long goodsId, Map<String, List<Cost>> usableCostMap, String costKey) {
        List<Cost> costList = new ArrayList<>();
        if(usableCostMap != null){
            costList = usableCostMap.get(costKey);
        }
        if(CollectionUtils.isEmpty(costList)){
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("enterpriseId", distrUnitId);
            paramMap.put("goodsId", goodsId);
            costList = costMapper.selectByParamMapWithFIFO(paramMap);
        }
        return costList;
    }

    @Override
    public DistrSend calculation(UserVO userVO, RequestDistrSendVO requestDistrSendVO) throws Exception {
        if (requestDistrSendVO.getRequestUnitId() == null) {
            throw new BusinessException("要货单位为空！");
        }

        if (requestDistrSendVO.getDistrGoogsList() == null || requestDistrSendVO.getDistrGoogsList().size() == 0) {
            throw new BusinessException("商品列表为空！");
        }

        GoodsDefTaxRateVO distrTaxRateVO = null;
        BigDecimal unitPrice = null;
        BigDecimal taxRate = null;
        Long taxRateId = null;
        //初次计算，获取默认配送价格及税率
        for (RequestDistrGoods goods : requestDistrSendVO.getDistrGoogsList()) {
            //默认要货数量
            if (goods.getQuantity() == null) {

                // 查询总部可用库存，如果不足 1,要货数量为总部可用库存，大于1，默认1

                Map<String,Object> paramMap = new HashMap<>();
                paramMap.put("enterpriseId",userVO.getEnterpriseId());
                paramMap.put("goodsId",goods.getGoodsId());
                paramMap.put("today",new Date());
                BigDecimal quantity = stockMapper.getQualifiedUsableQuantity(paramMap);

                if(quantity == null){
                    throw new BusinessException("总部库存可用库存不足");
                }
                if(BigDecimal.ONE.compareTo(quantity) > 0){
                    goods.setQuantity(quantity);
                } else {
                    goods.setQuantity(BigDecimal.ONE);
                }

            }
            if(goods.getUnitPrice() == null){
                // 获取待配批号列表
                List<Long> lotIds = getWaitSendLotIdList(userVO, goods,requestDistrSendVO);
                // 获取配货单价
                unitPrice = commonComponent.getDistrPrice(userVO.getEnterpriseId(), requestDistrSendVO.getRequestUnitId(), goods.getGoodsId(), lotIds, goods.getQuantity());
                // 获取配货税率信息
                EnterpriseBusiness enterpriseBusiness = commonComponent.getEnterpriseBusiness(requestDistrSendVO.getRequestUnitId());
                Integer distrPriceType = enterpriseBusiness.getDistrPriceType();
                if(distrPriceType.equals(DistrPriceType.PRICE_LIST.getCode())){
                    Long distrPriceOrderId = enterpriseBusiness.getDistrPriceOrderId();//价格清单ID
                    PriceOrderDetail priceOrderDetail = priceOrderDetailMapper.selectByEnterpriseIdAndPriceOrderIdAndGoodId(userVO.getEnterpriseId(),distrPriceOrderId,goods.getGoodsId());
                    if(priceOrderDetail == null){
                        throw new BusinessException("按价格清单方式配送，【门店管理-业务信息】中必须设置对应要货单位的配货价格清单！");
                    }
                    taxRate = priceOrderDetail.getDistrTaxRate();
                    taxRateId = priceOrderDetail.getDistrTaxRateId();
                }else if(distrPriceType.equals(DistrPriceType.COST_PRICE.getCode()) || distrPriceType.equals(DistrPriceType.COST_PLUS.getCode())){
                    distrTaxRateVO = commonComponent.getGoodsDefTaxRateInfo(userVO.getEnterpriseId(), goods.getGoodsId(), TaxRateType.PURCHASE.getType());
                    taxRate = distrTaxRateVO.getTaxRate();
                    taxRateId = distrTaxRateVO.getTaxRateId();
                }
                if (unitPrice == null || taxRate == null || taxRateId == null) {
                    throw new BusinessException("获取商品配送单价,税率错误！");
                }
                goods.setUnitPrice(unitPrice);
                goods.setTaxRate(taxRate);
                goods.setTaxRateId(taxRateId);
            }
        }

        //配送单信息
        DistrSend distrSend = commonComponent.getDistrSend(userVO, requestDistrSendVO);
        if (requestDistrSendVO.getId() != null) {
            distrSend.setId(requestDistrSendVO.getId());
        }
        //配送单明细
        DistrSendDetail distrSendDetail;
        List<DistrSendDetail> distrSendDetailList = new ArrayList<>();
        for (RequestDistrGoods distrGoods : requestDistrSendVO.getDistrGoogsList()) {
            distrSendDetail = commonComponent.getDistrSendDetail(userVO, distrSend, distrGoods);
            if (distrGoods.getId() != null) {
                distrSendDetail.setId(distrGoods.getId());
            }
            distrSendDetailList.add(distrSendDetail);
        }
        distrSend.setDistrSendDetailList(distrSendDetailList);
        return distrSend;
    }

    /**
     * 获取待配批号列表
     * @param userVO
     * @param goods
     * @param requestDistrSendVO
     * @return
     */
    private List<Long> getWaitSendLotIdList(UserVO userVO, RequestDistrGoods goods, RequestDistrSendVO requestDistrSendVO) {
        List<Long> lotIds = new ArrayList<Long>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("enterpriseId", userVO.getEnterpriseId());
        paramMap.put("parentId", userVO.getParentId());
        paramMap.put("goodsId", goods.getGoodsId());
        List<Stock> stockList = stockMapper.selectQuantityByParamMapWithFIFO(paramMap);

        // 修改时，要把这个单据锁定的数量取回来
        if(requestDistrSendVO.getId() != null){// 代表修改
            paramMap.put("baseOrderId", requestDistrSendVO.getId());
            paramMap.put("baseOrderType", OrderRule.DISTR_ORDER.getType());
            List<StockLockRecord> stockLockRecordList = stockLockRecordMapper.selectByParamMap(paramMap);

            for(Stock stock: stockList){
                for(StockLockRecord lockRecord : stockLockRecordList){
                    if(lockRecord.getLotId().equals(stock.getLotId()) && lockRecord.getShelfId().equals(stock.getShelfId())){
                        stock.setUsableQuantity(stock.getUsableQuantity().add(lockRecord.getLockQuantity()));
                    }
                }
            }
        }


        if(CollectionUtils.isNotEmpty(stockList)){
            BigDecimal tmpQty = goods.getQuantity();
            for(Stock stock:stockList){
                BigDecimal usableQuantity = stock.getUsableQuantity();
                // 先获取直接满足出库数量的库存记录
                if(usableQuantity.compareTo(goods.getQuantity())>=0){
                    tmpQty = BigDecimal.ZERO;
                    lotIds.add(stock.getLotId());
                    break;
                }else{
                    BigDecimal stockUsableQuantity = stock.getUsableQuantity()==null?BigDecimal.ZERO:stock.getUsableQuantity();
                    if(stockUsableQuantity.compareTo(BigDecimal.ZERO)==0){
                        continue;
                    }
                    lotIds.add(stock.getLotId());
                    tmpQty = tmpQty.subtract(stockUsableQuantity);
                }
                if(tmpQty.compareTo(BigDecimal.ZERO)<=0){
                    break;
                }
            }
            if(tmpQty.compareTo(BigDecimal.ZERO) > 0){
                throw new BusinessException("获取待配批号列表失败，库存不足！");
            }
        }
        return lotIds;
    }

    @Override
    public int updateDistrSend(UserVO userVO, RequestDistrSendVO updateSendVO) throws Exception {
        //配送明细更新
        if (updateSendVO.getDistrGoogsList() == null || updateSendVO.getDistrGoogsList().size() == 0) {
            throw new BusinessException("商品列表为空，请检查参数！");
        }

        DistrSend distrSend = distrSendMapper.selectByPrimaryKey(updateSendVO.getId());
        if (distrSend == null) {
            throw new BusinessException("查询配送单信息错误！");
        }
        //先释放库存，这样下面操作逻辑才是对的
        // 释放原来锁定的库存记录
        releaseStock(userVO, distrSend);

        Date updateDate = new Date();
        //重新计算价格
        commonComponent.calculationDistrSendPrice(distrSend, updateSendVO);
        //修改人、更新日期
        distrSend.setUpdateTime(updateDate);
        distrSend.setModifierId(userVO.getUserId());
        distrSend.setModifierCode(userVO.getUserCode());
        distrSend.setModifierName(userVO.getUserName());
        //配货单日期
        distrSend.setDistrDate(updateSendVO.getDistrDate());
        //配货人员信息
        User distrManInfo = userMapper.selectByPrimaryKey(updateSendVO.getDistrManId());
        if (distrManInfo == null) {
            throw new BusinessException("查询配货人信息错误！");
        }
        distrSend.setDistrManId(distrManInfo.getId());
        distrSend.setDistrManCode(distrManInfo.getCode());
        distrSend.setDistrManName(distrManInfo.getName());
        //更新配送单
        int i = distrSendMapper.updateByPrimaryKeySelective(distrSend);
        if (i != 1) {
            throw new BusinessException("更新配送单失败！");
        }

        //判断被删除的明细列
        List<Long> dtlIds = distrSendDetailMapper.distrSendDetailIds(distrSend.getId());
        for (RequestDistrGoods distrGoods : updateSendVO.getDistrGoogsList()) {
            if (distrGoods.getId() != null && dtlIds.contains(distrGoods.getId())) {
                dtlIds.remove(distrGoods.getId());
            }
        }
        //存在被删除的项
        if (dtlIds.size() > 0) {
            DistrSendDetail deleteDtl;
            for (Long dtlId : dtlIds) {
                deleteDtl = distrSendDetailMapper.selectByPrimaryKey(dtlId);
                if (deleteDtl.getBaseOrderDtlId() != null && deleteDtl.getBaseOrderType() != null) {
                    //更新缺配单状态
                    if (deleteDtl.getBaseOrderType().compareTo(OrderRule.DISTR_LACK.getType()) == 0) {
                        commonComponent.updateDistrLackStatus(userVO, deleteDtl.getBaseOrderId(), deleteDtl.getBaseOrderDtlId(), deleteDtl.getQuantity().multiply(new BigDecimal(-1)));
                    }

                    //更新要货计划状态
                    if (deleteDtl.getBaseOrderType().compareTo(OrderRule.REQUIRE_PLAN.getType()) == 0) {
                        Set<Long> planIds = new HashSet<>();
                        planIds.add(deleteDtl.getBaseOrderId());
                        updateReqPlanOrderStatus(userVO, planIds, DistrReqPlanStatus.WAIT_DISTR);
                    }
                }
               if (distrSendDetailMapper.deleteByPrimaryKey(dtlId) != 1) {
                    throw new BusinessException("删除配货单明细失败！");
               }
            }
        }

        //插入配送单明细
        DistrSendDetail distrSendDetail;
        for (RequestDistrGoods distrGoods : updateSendVO.getDistrGoogsList()) {

            if (distrGoods.getId() == null) {//该列为新增商品
                if (distrGoods.getGoodsId() == null) {
                    throw new BusinessException("商品ID为空，请检查参数！");
                }
                distrSendDetail = commonComponent.getDistrSendDetail(userVO, distrSend, distrGoods);
                //创建人信息
                distrSendDetail.setCreaterId(userVO.getUserId());
                distrSendDetail.setCreaterCode(userVO.getUserCode());
                distrSendDetail.setCreaterName(userVO.getUserName());
                distrSendDetail.setCreateTime(new Date());
                distrSendDetailMapper.insertSelective(distrSendDetail);
                continue;
            }

            distrSendDetail = distrSendDetailMapper.selectByPrimaryKey(distrGoods.getId());

            //类型为缺配单,更新缺配单状态
            if (distrSendDetail.getBaseOrderDtlId() != null && distrSendDetail.getBaseOrderType() != null) {
                //更新缺配单状态
                if (distrSendDetail.getBaseOrderType().compareTo(OrderRule.DISTR_LACK.getType()) == 0
                        && distrSendDetail.getQuantity().compareTo(distrGoods.getQuantity()) != 0) {
                    commonComponent.updateDistrLackStatus(userVO, distrSendDetail.getBaseOrderId(), distrSendDetail.getBaseOrderDtlId(),
                            distrGoods.getQuantity().subtract(distrSendDetail.getQuantity()));
                }
            }

            //更新价格
            commonComponent.calculationDistrSendDetailPrice(distrSend, distrSendDetail, distrGoods);
            distrSendDetail.setDistrDate(updateSendVO.getDistrDate());
            //更新备注
            if (StringUtils.isEmpty(distrGoods.getRemark())) {
                distrSendDetail.setRemark(distrGoods.getRemark());
            }
            //修改人、更新日期
            distrSendDetail.setUpdateTime(updateDate);
            distrSendDetail.setModifierId(userVO.getUserId());
            distrSendDetail.setModifierCode(userVO.getUserCode());
            distrSendDetail.setModifierName(userVO.getUserName());
            int j = distrSendDetailMapper.updateByPrimaryKeySelective(distrSendDetail);
            if (j != 1) {
                throw new BusinessException("更新配送单明细失败！");
            }
        }



        // 锁定库存
        List<DistrSendDetail> dtlList = distrSendDetailMapper.listDistrSendDetailList(distrSend.getId());
        for(DistrSendDetail dtl:dtlList){
            commonComponent.lockStockByDetail(userVO, dtl);
        }

        return 0;
    }

    @Override
    public Page getDistrSendPage(Page page, Map<String, Object> map) {
        //查询合计
        Integer sortDate = map.get("sortDate") == null ? null : (Integer) map.get("sortDate");
        Integer sortCode = map.get("sortCode") == null ? null : (Integer) map.get("sortCode");
        String sort = "";
        if (sortDate == null && sortCode == null) {
            sort = "";
        }
        if (sortDate != null && sortDate == 0) {
            sort += "distr_date,";
        }
        if (sortDate != null && sortDate == 1) {
            sort += "distr_date desc,";
        }
        if (sortCode != null && sortCode == 0) {
            sort += "code,";
        }
        if (sortCode != null && sortCode == 1) {
            sort += "code desc,";
        }
        if (!"".equals(sort)) {
            sort = sort.substring(0, sort.length() - 1);
        }
        map.put("sort", sort);
        DistrListTotalVO distrListTotalVO = distrSendMapper.getSearchResultTotal(map);
        if (distrListTotalVO == null) {
            distrListTotalVO = new DistrListTotalVO();
        }

        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        //查询配货单列表
        List<DistrListVO> distrListVOList = distrSendMapper.getDistrSendPage(map);

        distrListTotalVO.setDistrListVOList(distrListVOList == null ? new ArrayList<>() : distrListVOList);

        page.setResult(distrListTotalVO);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        return page;
    }

    @Override
    public DistrSend getDistrSendDetail(Long id) {
        //配送单信息
        DistrSend distrSend = distrSendMapper.selectByPrimaryKey(id);
        if (distrSend == null) {
            throw new BusinessException("查询配送单据信息错误！");
        }

        List<DistrSendDetail> detailVOList = distrSendDetailMapper.listDistrSendDetailList(id);
        if (detailVOList == null || detailVOList.size() == 0) {
            throw new BusinessException("查询配送单据明细错误！");
        }

        //查询总店可用库存、门店可用库存
        BigDecimal quantity;
        for (DistrSendDetail detailVO : detailVOList) {
            //quantity = stockMapper.getQualifiedUsableQuantity(detailVO.getEnterpriseId(), detailVO.getGoodsId());

            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("enterpriseId",detailVO.getEnterpriseId());
            paramMap.put("goodsId",detailVO.getGoodsId());
            paramMap.put("today",new Date());
            quantity = stockMapper.getQualifiedUsableQuantity(paramMap);
            detailVO.setParentUsableQuantity(quantity == null ? BigDecimal.ZERO : quantity);
            quantity = stockMapper.getUsableQuantityGroupByGoodsId(distrSend.getRequestUnitId(), detailVO.getGoodsId());
            detailVO.setUsableQuantity(quantity == null ? BigDecimal.ZERO : quantity);
        }

        distrSend.setDistrSendDetailList(detailVOList);
        return distrSend;
    }

    @Override
    public List<DistrSendReqPlanVO> getReqPlanList(Long enterpriseId, Date startTime, Date endTime, Integer sortDate, Integer sortCode) {
        String sort = "";
        if (sortDate == null && sortCode == null) {
            sort = "";
        }
        if (sortDate != null && sortDate == 0) {
            sort += "plan_date,";
        }
        if (sortDate != null && sortDate == 1) {
            sort += "plan_date desc,";
        }
        if (sortCode != null && sortCode == 0) {
            sort += "code,";
        }
        if (sortCode != null && sortCode == 1) {
            sort += "code desc,";
        }
        if (!"".equals(sort)) {
            sort = sort.substring(0, sort.length() - 1);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("sort", sort);
        map.put("enterpriseId", enterpriseId);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("status", DistrReqPlanStatus.WAIT_DISTR);
        return distrReqPlanMapper.getReqPlanByDistrUnitId(map);
    }

    @Override
    public List<DistrReqPlanGoodsVO> getReqPlanGoodsList(Long enterpriseId, String planIdsStr, Integer distrRule, Integer sortDate) {
        if (StringUtils.isEmpty(planIdsStr)) {
            throw new BusinessException("要货计划ID为空，请检查参数！");
        }

        //默认排序
        if (sortDate == null) {
            sortDate = 0;
        }

        //排序
        String sort = "req.goods_code";
        if (sortDate == 0) {
            sort += ", req.plan_date";
        }

        if (sortDate == 1) {
            sort += ", req.plan_date desc";
        }

        //默认按要货计划配货
        if (distrRule == null) {
            distrRule = 0;
        }

        //要货计划ID
        Set<Long> ids = new HashSet<>();
        if (planIdsStr.contains(",")) {
            String[] idStrings = planIdsStr.split(",");
            for (int i = 0; i < idStrings.length; i++) {
                ids.add(Long.parseLong(idStrings[i]));
            }
        } else {
            ids.add(Long.parseLong(planIdsStr));
        }

        List<DistrReqPlanGoodsVO> planList = distrReqPlanDetailMapper.getDistrReqPlanGoodsList(new ArrayList<>(ids), sort);
        if (planList == null || planList.size() == 0) {
            throw new BusinessException("查询要货计划明细为空！");
        }

        //获取总部某商品的库存可用量
        Set<Long> goodsIds = new HashSet<>();
        for (DistrReqPlanGoodsVO planGoodsVO : planList) {
            goodsIds.add(planGoodsVO.getGoodsId());
        }

        //总部可用数量
        BigDecimal usableQuantity = BigDecimal.ZERO;
        //商品要货数量总和

        for (Long goodsId : goodsIds) {
            List<DistrReqPlanGoodsVO> goodsReqList = new ArrayList<>();
            BigDecimal requestQuantityTotal = BigDecimal.ZERO;
            //该商品总部可用库存
            //usableQuantity = stockMapper.getUsableQuantityGroupByGoodsId(enterpriseId, goodsId);
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("enterpriseId",enterpriseId);
            paramMap.put("goodsId",goodsId);
            paramMap.put("today",new Date());
            usableQuantity = stockMapper.getQualifiedUsableQuantity(paramMap);


            for (DistrReqPlanGoodsVO distrReqPlanGoodsVO : planList) {
                if (distrReqPlanGoodsVO.getGoodsId().equals(goodsId)) {
                    requestQuantityTotal = requestQuantityTotal.add(distrReqPlanGoodsVO.getRequestQuantity());
                    goodsReqList.add(distrReqPlanGoodsVO);
                    distrReqPlanGoodsVO.setUsableQuantity(usableQuantity == null?BigDecimal.ZERO:usableQuantity);
                }
            }

            //总部可用库存、要货数量总和判断,根据配货规则，修改配货数量、缺配数量
            reqPlansQuantity(goodsReqList, usableQuantity == null ? BigDecimal.ZERO : usableQuantity, requestQuantityTotal, distrRule, sortDate);

        }


        return planList;
    }

    @Override
    public Page getPurchaseInStorageList(Page page, Long enterpriseId, Date startTime, Date endTime) {
        Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", enterpriseId);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("isUse",0);
        map.put("carriageMode", DistributionType.DISTRIBUTION_HEAD.getCode());
        map.put("start", page.getStart());
        map.put("pageSize", page.getPageSize());

//        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Integer count  = purchaseInStorageMapper.getParchaseInOrderListCount(map);
        //查询配货单列表
        List<PurchaseInStorage> orderList = purchaseInStorageMapper.getParchaseInOrderList(map);
        page.setResult(orderList);
        page.setTotalRecord(count);
//        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
//        page.setTotalPage(hPage.getPages());
        return page;
    }

    @Override
    public List<DistrPurchaseInstorageDetailVO> getPurchaseInStorageDtlList(Long inStorageId) {

        List<DistrPurchaseInstorageDetailVO> distrPurchaseInstorageDetailVOS = purchaseInStorageDetailMapper.selectByDistrPurchaseInStorageId(inStorageId);
        for(DistrPurchaseInstorageDetailVO distrPurchaseInstorageDetailVO : distrPurchaseInstorageDetailVOS){
            //总部库存使用量
            //BigDecimal parentUsableQuantity = stockMapper.getUsableQuantityGroupByGoodsId(distrPurchaseInstorageDetailVO.getEnterpriseId(), distrPurchaseInstorageDetailVO.getGoodsId());
           // BigDecimal parentUsableQuantity  = stockMapper.getUsableQuantityGroupByGoodsId(distrPurchaseInstorageDetailVO.getEnterpriseId(), distrPurchaseInstorageDetailVO.getGoodsId());

            Long enterpriseId = distrPurchaseInstorageDetailVO.getEnterpriseId();

            Long goodsId = distrPurchaseInstorageDetailVO.getGoodsId();
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("enterpriseId",enterpriseId);
            paramMap.put("goodsId",goodsId);
            paramMap.put("today",new Date());
            BigDecimal parentUsableQuantity = stockMapper.getQualifiedUsableQuantity(paramMap);
            if (parentUsableQuantity == null) {
                parentUsableQuantity = BigDecimal.ZERO;
            }
            distrPurchaseInstorageDetailVO.setParentUsableQuantity(parentUsableQuantity);
        }
        return distrPurchaseInstorageDetailVOS;
    }

    @Override
    public Page getDistrReturnInList(Page page, Long enterpriseId, Date startTime, Date endTime) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", enterpriseId);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("distrType", DistributionType.SWAP_BETWEEN_STORES.getCode());
        map.put("start", page.getStart());
        map.put("pageSize", page.getPageSize());

//        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        int count = distrReturnInMapper.getDistrReturnInListByEnterpiseIdCount(map);
        //查询配后退后入库单列表
        List<DistrReturnIn> orderList = distrReturnInMapper.getDistrReturnInListByEnterpiseId(map);
        if(orderList != null) {
            for (DistrReturnIn distrReturnIn : orderList) {
                if (distrReturnIn.getDistrOrderId() == null) {
                    throw new BusinessException("没有关联的配后退回通知单ID");
                }

                DistrReturnNotice distrReturnNotice = distrReturnNoticeMapper.selectByPrimaryKey(distrReturnIn.getDistrOrderId());
                if(distrReturnNotice == null){
                    throw new BusinessException("无效的配后退回通知单ID：" + distrReturnIn.getDistrOrderId());
                }

                DistrInReturnOut distrInReturnOut = distrInReturnOutMapper.selectByPrimaryKey(distrReturnNotice.getBaseOrderId());

                if(distrInReturnOut == null){
                    throw new BusinessException("无效的配进退出出库ID:" + distrReturnNotice.getBaseOrderId());
                }

                distrReturnIn.setRequestUnitId(distrInReturnOut.getOutboundUnitId());
                distrReturnIn.setRequestUnitCode(distrInReturnOut.getOutboundUnitCode());
                distrReturnIn.setRequestUnitName(distrInReturnOut.getOutboundUnitName());
            }
        }

        page.setResult(orderList);
        page.setTotalRecord(count);
//        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
//        page.setTotalPage(hPage.getPages());
        return page;
    }

    @Override
    public List<DistrReturnInDetail> getDistrReturnInDetailList(UserVO userVO, Long returnInId) {
        return  distrReturnInDetailMapper.selectByReturnId(returnInId);
    }

    @Override
    public Page getDistrLackList(Page page, UserVO userVO, Date startTime, Date endTime) {
        Map<String, Object> map = new HashMap<>();

        Long enterpriseId = userVO.getEnterpriseId();
        if(userVO.getChainType() != ChainType.Headquarters.getCode()){// 不是总部，查询自己的缺配单
            enterpriseId = userVO.getParentId();
            map.put("requestUnitId",userVO.getEnterpriseId());
        }
        map.put("enterpriseId", enterpriseId);
        map.put("startTime", startTime);
        map.put("endTime", endTime);

        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        //查询配后退后入库单列表
        List<DistrLack> orderList = distrLackMapper.getDistrLackListByEnterpriseId(map);
        page.setResult(orderList);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        return page;
    }

    @Override
    public DistrLack getDistrLackDetailList(Long lackId,UserVO loginUser) {
        DistrLack distrLack = distrLackMapper.selectByPrimaryKey(lackId);
        distrLack.setDistrLackDetailList(distrLackDetailMapper.getDistrLackDetailByLackId(lackId));
        Long parentId = 0L;
        Long enterpriseId = loginUser.getEnterpriseId();
        if(ChainType.Headquarters.getCode() != loginUser.getChainType()){ //非总部
            parentId = loginUser.getParentId();
        }else{
            parentId = loginUser.getEnterpriseId();
        }
        //增加价格
        EnterpriseBusiness enterpriseBusiness = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(distrLack.getRequestUnitId());

        for(Iterator<DistrLackDetail> it = distrLack.getDistrLackDetailList().iterator();it.hasNext();){
            DistrLackDetail temp = it.next();
            BigDecimal price = commonComponent.getDistrPrice(parentId,distrLack.getRequestUnitId(),temp.getGoodsId(),null,temp.getLackQuantity());
            //BigDecimal totalUsableQuantity = stockMapper.getUsableQuantityGroupByGoodsId(parentId,temp.getGoodsId());

            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("enterpriseId",parentId);
            paramMap.put("goodsId",temp.getGoodsId());
            paramMap.put("today",new Date());
            BigDecimal totalUsableQuantity = stockMapper.getQualifiedUsableQuantity(paramMap);

            if(totalUsableQuantity == null || totalUsableQuantity.intValue() <= 0){
                it.remove();
                continue;
            }
            temp.setParentUsableQuantity(totalUsableQuantity);
            temp.setUnitPrice(price);
            //增加税率
            if(enterpriseBusiness.getDistrPriceType() == 1){ //如果配货价格类型是成本价或者为空   使用成本价
                GoodsDefTaxRateVO distrTaxRateVO = commonComponent.getGoodsDefTaxRateInfo(loginUser.getEnterpriseId(), temp.getGoodsId(), TaxRateType.PURCHASE.getType());
                temp.setTaxRateId(distrTaxRateVO.getTaxRateId());
            }else if(enterpriseBusiness.getDistrPriceType() == 0){ //如果配货价格类型是价格清单
                Long distrPriceOrderId = enterpriseBusiness.getDistrPriceOrderId();//价格清单ID
                PriceOrderDetail priceOrderDetail = priceOrderDetailMapper.selectByEnterpriseIdAndPriceOrderIdAndGoodId(loginUser.getEnterpriseId(),distrPriceOrderId,temp.getGoodsId());
                if(priceOrderDetail == null){
                    throw new BusinessException("无法获取到价格清单！");
                }
                temp.setTaxRateId(priceOrderDetail.getDistrTaxRateId());
            }else if(enterpriseBusiness.getDistrPriceType() == 2){ //如果配货价格类型是成本加成
                BigDecimal addRate = enterpriseBusiness.getAddRate();
                GoodsDefTaxRateVO distrTaxRateVO = commonComponent.getGoodsDefTaxRateInfo(loginUser.getEnterpriseId(), temp.getGoodsId(), TaxRateType.PURCHASE.getType());
                temp.setTaxRateId(distrTaxRateVO.getTaxRateId());
            }else {
                throw new BusinessException("获取配货门店的配货价格类型错误，请先设置该配货门店的配货价格类型！");
            }
        }
        return distrLack;
    }

    @Override
    public void excelExport(OutputStream outPut, Long id, UserVO userVO) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("goodsCode", "商品编码");
        map.put("goodsGenericName", "通用名称");
        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");

        map.put("unitName", "单位");
        map.put("manufacturer", "生产厂商");
        map.put("goodsPlace", "产地");
        map.put("quantity", "数量");
        map.put("unitPrice", "单价");
        map.put("lineDiscount", "折扣");

        map.put("amount", "金额");
        map.put("taxRate", "税率");
        map.put("notaxRealPrice", "不含税单价");

        map.put("notaxRealAmount", "不含税金额");
        map.put("taxAmount", "税额");
        map.put("remark", "备注");
        DistrSend distrSend = distrSendMapper.selectByPrimaryKey(id);
        List<DistrSendDetail> distrSendDetailList = distrSendDetailMapper.listDistrSendDetailList(id);

        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("要货单位编码:");
        titleSecondRow.append(distrSend.getRequestUnitCode() == null ? "" : distrSend.getRequestUnitCode());
        titleSecondRow.append("     ");
        titleSecondRow.append("要货单位名称:");
        titleSecondRow.append(distrSend.getRequestUnitName() == null ? "" : distrSend.getRequestUnitName());
        titleSecondRow.append("     ");
        titleSecondRow.append("配货日期:");
        titleSecondRow.append(new SimpleDateFormat("yyyy-MM-dd").format(distrSend.getDistrDate()));
        titleSecondRow.append("     ");
        titleSecondRow.append("配货人员:");
        titleSecondRow.append(distrSend.getDistrManName() == null ? "" : distrSend.getDistrManName());
        titleSecondRow.append("     ");
        titleSecondRow.append("配货单号:");
        titleSecondRow.append(distrSend.getCode() == null ? "" : distrSend.getCode());
        titleSecondRow.append("     ");
        titleSecondRow.append("配货类型:");
        String distrType = "";
        switch (distrSend.getDistrType()) {
            case 0:
                distrType = "总部配送";
                break;
            case 1:
                distrType = "分店间调剂";
                break;
            case 2:
                distrType = "直调配送";
                break;
        }
        titleSecondRow.append(distrType);
        titleSecondRow.append("     ");
        List<String> name = new ArrayList<>();
        name.add(userVO.getEnterpriseName());
        name.add("配货单");
        List<String> secondTitle = new ArrayList<>();
        secondTitle.add(titleSecondRow.toString());
        StringBuilder sb = new StringBuilder();
        sb.append("总商品金额:   ");
        sb.append(distrSend.getAmountTotal());
        sb.append(";");
        sb.append("折扣:   ");
        sb.append(distrSend.getWholeDiscount().setScale(2, BigDecimal.ROUND_HALF_UP));
        sb.append("%");
        sb.append("  ");
        sb.append(distrSend.getAmountTotal().subtract(distrSend.getAmountTotal().multiply(distrSend.getWholeDiscount()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)));
        sb.append(";");
        sb.append("优惠:");
        sb.append("   ");
        sb.append(distrSend.getWholeDiscountAmount());
        sb.append(";");
        sb.append("总额:");
        sb.append("   ");
        sb.append(distrSend.getRealAmountTotal());
        sb.append(";");
        sb.append("不含税总额:");
        sb.append("   ");
        sb.append(distrSend.getNotaxRealAmountTotal());
        sb.append(";");
        sb.append("税额:");
        sb.append("   ");
        sb.append(distrSend.getTaxAmountTotal());

        StringBuilder endTotal = new StringBuilder();
        BigDecimal totalQuantity = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalNotaxRealAmount = BigDecimal.ZERO;
        BigDecimal totalTaxAmount = BigDecimal.ZERO;
        for (DistrSendDetail d : distrSendDetailList) {
            totalQuantity = totalQuantity.add(d.getQuantity());
            totalAmount = totalAmount.add(d.getAmount());
            totalNotaxRealAmount = totalNotaxRealAmount.add(d.getNotaxRealAmount());
            totalTaxAmount = totalTaxAmount.add(d.getTaxAmount());
        }
        endTotal.append(totalQuantity);
        endTotal.append(";");
        endTotal.append(totalAmount);
        endTotal.append(";");
        endTotal.append(totalNotaxRealAmount);
        endTotal.append(";");
        endTotal.append(totalTaxAmount);
        List<String> locationList = new ArrayList<String>();
        locationList.add("quantity");
        locationList.add("amount");
        locationList.add("notaxRealAmount");
        locationList.add("taxAmount");
        purchaseGeneralComponent.commExcelDistrExport(outPut, map, distrSendDetailList, name, secondTitle, sb.toString(), endTotal.toString(), locationList);

    }

    @Override
    public int cancelOrder(Long id, UserVO userVO) {
        //设置相关配送单为完成状态
        DistrSend distrSend = distrSendMapper.selectByPrimaryKey(id);
        if (distrSend == null) {
            throw new BusinessException("该配送单不存在, 请检查配送单id = " + id);
        }
        if (distrSend.getStatus() != DistrSendStatus.AUDIT_REJECT
                //&& distrSend.getStatus() != DistrSendStatus.WAIT_OUT
                && distrSend.getStatus() != DistrSendStatus.WAIT_PICK) {
            throw new BusinessException("该配送单对应的配送单状态无法取消, 请检查配送单状态id=" + id);
        }
        distrSend.setStatus(DistrSendStatus.CANCELED);
        distrSend.setUpdateTime(new Date());
        distrSend.setModifierCode(userVO.getUserCode());
        distrSend.setModifierId(userVO.getUserId());
        distrSend.setModifierName(userVO.getUserName());

        // 释放配送单锁定的库存
        releaseStock(userVO, distrSend);

        return distrSendMapper.updateByPrimaryKey(distrSend);
    }

    private void releaseStock(UserVO userVO, DistrSend distrSend) {
        LockQtyArgVO lockQtyArgVO = new LockQtyArgVO();
        lockQtyArgVO.setEnterpriseId(distrSend.getEnterpriseId());
        lockQtyArgVO.setParentId(distrSend.getParentId());
        lockQtyArgVO.setBaseOrderId(distrSend.getId());
        lockQtyArgVO.setBaseOrderType(distrSend.getOrderType());
        lockQtyArgVO.setUser(userVO);
        commonComponent.releaseStockAndCost(lockQtyArgVO);
    }











    /**
     * 获取要货单位经营范围列表
     *
     * @param requestUnitId 要货单位id
     * @return
     */
    private List<Long> getBusinessScopeIds(Long requestUnitId) {
        List<Long> businessScopeIds = new ArrayList<>();
        //获取总店下所有属于该门店经营范围的商品列表
        String businessScopeId = enterpriseMapper.getBusinessScopeId(requestUnitId);
        if (StringUtils.isEmpty(businessScopeId)) {
            return businessScopeIds;
        }

        //经营范围列表
        String[] businessScopeIdArray = businessScopeId.split(",");
        if (businessScopeIdArray == null || businessScopeIdArray.length == 0) {
            return businessScopeIds;
        }

        for (int i = 0; i < businessScopeIdArray.length; i++) {
            businessScopeIds.add(new Long(businessScopeIdArray[i]));
        }
        return businessScopeIds;
    }




    /**
     * 缺配单明细
     *
     * @param userVO        用户信息
     * @param reqPlanDetail 要货计划明细
     * @param sendQuantity  配送数量
     * @return
     */
    private DistrLackDetail getDistLackDetail(UserVO userVO, DistrReqPlanDetail reqPlanDetail, BigDecimal sendQuantity) {
        DistrLackDetail distrLackDetail = new DistrLackDetail();
        //复制商品数据
        BeanUtils.copyProperties(reqPlanDetail, distrLackDetail);
        //企业信息
        distrLackDetail.setEnterpriseId(userVO.getEnterpriseId());
        distrLackDetail.setParentId(userVO.getParentId());
        distrLackDetail.setId(null);

        //基础单据信息
        distrLackDetail.setBaseOrderDtlId(reqPlanDetail.getId());
        distrLackDetail.setBaseOrderId(reqPlanDetail.getPlanId());
        distrLackDetail.setBaseOrderType(reqPlanDetail.getOrderType());
        distrLackDetail.setBaseOrderCode(reqPlanDetail.getPlanCode());
        distrLackDetail.setBaseOrderDate(reqPlanDetail.getPlanDate());

        //要货数量
        distrLackDetail.setRequestQuantity(reqPlanDetail.getQuantity());
        //配货数量
        distrLackDetail.setSendQuantity(sendQuantity);
        //缺配数量
        BigDecimal lackQuantity = reqPlanDetail.getQuantity().subtract(sendQuantity);
        distrLackDetail.setLackQuantity(lackQuantity);

        //未清数量=缺配数量
        distrLackDetail.setUnclearQuantity(lackQuantity);
        //已清数量
        distrLackDetail.setClearQuantity(BigDecimal.ZERO);
        //状态（暂未用到，默认1）
        distrLackDetail.setStatus(1);

        //创建人员信息
        distrLackDetail.setCreaterId(userVO.getUserId());
        distrLackDetail.setCreaterCode(userVO.getUserCode());
        distrLackDetail.setCreaterName(userVO.getUserName());
        distrLackDetail.setCreateTime(new Date());

        distrLackDetail.setModifierId(null);
        distrLackDetail.setModifierCode(null);
        distrLackDetail.setModifierName(null);
        distrLackDetail.setUpdateTime(null);
        return distrLackDetail;
    }

    /**
     * 生成缺配单，按照每个要货计划生成一个缺配单
     *
     * @param userVO
     * @param distrLackDetails
     * @throws Exception
     */
    private void addDistrLackOrder(UserVO userVO, List<DistrLackDetail> distrLackDetails) throws Exception {
        if (distrLackDetails == null || distrLackDetails.size() == 0) {
            return;
        }

        //依据基础单号ID,每个要货计划生成一个缺配单
        Set<Long> reqPlanIds = new HashSet<>();
        for (DistrLackDetail distrLackDtl : distrLackDetails) {
            reqPlanIds.add(distrLackDtl.getBaseOrderId());
        }

        //日期
        Date currentDate = new Date();
        //缺配单信息
        DistrLack distrLack;
        //要货单信息
        DistrReqPlan reqPlan;
        //要货单位信息
        Enterprise enterprise;
        //行号
        int lineNum;
        //总数量、品种
        BigDecimal quantityTotal = BigDecimal.ZERO;
        Integer varietiesQuantity = 0;

        for (Long planId : reqPlanIds) {
            distrLack = new DistrLack();
            //企业信息
            distrLack.setEnterpriseId(userVO.getEnterpriseId());
            distrLack.setParentId(userVO.getParentId());
            //单据类型
            distrLack.setOrderType(OrderRule.DISTR_LACK.getType());
            //单据编号
            String code = orderCodeComponent.generate(OrderRule.DISTR_LACK.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
            distrLack.setCode(code);
            //单据日期
            distrLack.setSendDate(currentDate);

            //要货计划信息
            reqPlan = distrReqPlanMapper.selectByPrimaryKey(planId);
            distrLack.setBaseOrderId(reqPlan.getId());
            distrLack.setBaseOrderType(reqPlan.getOrderType());
            distrLack.setBaseOrderCode(reqPlan.getCode());
            distrLack.setBaseOrderDate(reqPlan.getPlanDate());
            distrLack.setRequestPlanId(reqPlan.getId());
            distrLack.setRequestPlanCode(reqPlan.getCode());

            //数量合计、品种合计
            for (DistrLackDetail distrLackDetail : distrLackDetails) {
                if (distrLackDetail.getBaseOrderId().equals(planId)) {
                    quantityTotal = quantityTotal.add(distrLackDetail.getLackQuantity());
                    varietiesQuantity++;
                }
            }
            distrLack.setQuantityTotal(quantityTotal);
            distrLack.setVarietiesQuantity(varietiesQuantity);

            //要货单位信息
            enterprise = enterpriseMapper.selectByPrimaryKey(reqPlan.getEnterpriseId());
            if (enterprise == null) {
                throw new BusinessException("查询要货单位信息错误！");
            }
            distrLack.setRequestUnitId(enterprise.getId());
            distrLack.setRequestUnitCode(enterprise.getCode());
            distrLack.setRequestUnitName(enterprise.getName());

            //要货人员信息
            distrLack.setRequesterId(reqPlan.getPlannerId());
            distrLack.setRequesterCode(reqPlan.getPlannerCode());
            distrLack.setRequesterName(reqPlan.getPlannerName());

            //配送类型
            distrLack.setDistrType(reqPlan.getRequestType());
            //状态(待配送)
            distrLack.setStatus(DistrLackStatus.WAIT_DISTR);

            //创建人员信息
            distrLack.setCreaterId(userVO.getUserId());
            distrLack.setCreaterCode(userVO.getUserCode());
            distrLack.setCreaterName(userVO.getUserName());
            distrLack.setCreateTime(new Date());
            distrLack.setModifierId(userVO.getUserId());
            distrLack.setModifierCode(userVO.getUserCode());
            distrLack.setModifierName(userVO.getUserName());
            distrLack.setUpdateTime(new Date());

            //插入缺配单
            distrLackMapper.insertSelective(distrLack);

            lineNum = 1;
            //缺配单明细
            for (DistrLackDetail distrLackDetail : distrLackDetails) {
                if (distrLackDetail.getBaseOrderId() == planId) {
                    //主单信息
                    distrLackDetail.setLackId(distrLack.getId());
                    distrLackDetail.setOrderType(distrLack.getOrderType());
                    distrLackDetail.setLackCode(distrLack.getCode());
                    distrLackDetail.setLackDate(distrLack.getSendDate());
                    distrLackDetail.setLineNum(lineNum);

                    distrLackDetail.setModifierId(userVO.getUserId());
                    distrLackDetail.setModifierCode(userVO.getUserCode());
                    distrLackDetail.setModifierName(userVO.getUserName());
                    distrLackDetail.setUpdateTime(new Date());

                    distrLackDetailMapper.insertSelective(distrLackDetail);
                    lineNum++;
                }
            }
        }

    }



    /**
     * 生成配货单后，更新要货计划单的状态
     *
     * @param planIds 要货单列表
     */
    private void updateReqPlanOrderStatus(UserVO userVO, Set<Long> planIds, Integer status) throws Exception {
        if (planIds.isEmpty()) {
            throw new BusinessException("更新要货计划单状态失败，计划单ID列表为空！");
        }

        int i;
        DistrReqPlan distrReqPlan;
        for (Long id : planIds) {
            distrReqPlan = new DistrReqPlan();
            distrReqPlan.setId(id);
            distrReqPlan.setStatus(status);
            distrReqPlan.setModifierId(userVO.getUserId());
            distrReqPlan.setModifierCode(userVO.getUserCode());
            distrReqPlan.setModifierName(userVO.getUserName());
            distrReqPlan.setUpdateTime(new Date());
            i = distrReqPlanMapper.updateStatusById(distrReqPlan);
            if (i != 1) {
                throw new BusinessException("更新要货计划单状态失败！");
            }
        }
    }



    @Override
    public void getDistrSendList(Page<OrderReportVo<DistrSendReportVo>> page, RequestDistrSend requestDistrSend) {
        if (requestDistrSend.getPageNo() != null && requestDistrSend.getPageSize() != null) {
            requestDistrSend.setPageNo(page.getStart());
        }
        int count = distrSendMapper.getDistrSendListCount(requestDistrSend);
        Integer sortDate = requestDistrSend.getSortDate();
        Integer sortCode = requestDistrSend.getSortCode();
        String sort = "";
        if (sortDate == null && sortCode == null) {
            sort = "";
        }
        if (sortDate != null && sortDate == 0) {
            sort += "a.distr_date,";
        }
        if (sortDate != null && sortDate == 1) {
            sort += "a.distr_date desc,";
        }
        if (sortCode != null && sortCode == 0) {
            sort += "a.code,";
        }
        if (sortCode != null && sortCode == 1) {
            sort += "a.code desc,";
        }
        if (!"".equals(sort)) {
            sort = sort.substring(0, sort.length() - 1);
        }
        requestDistrSend.setSort(sort);
        List<DistrSendReportVo> distrSendList = distrSendMapper.getDistrSendList(requestDistrSend);
        OrderReportVo<DistrSendReportVo> orderReportVo = new OrderReportVo<>();
        orderReportVo.setDataList(distrSendList);
        requestDistrSend.setPageNo(null);
        requestDistrSend.setPageSize(null);
        List<DistrSendReportVo> distrSendList2 = distrSendMapper.getDistrSendList(requestDistrSend);
        BigDecimal quantity = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal realAmount = BigDecimal.ZERO;
        BigDecimal notaxRealAmount = BigDecimal.ZERO;
        BigDecimal taxAmount = BigDecimal.ZERO;
        for (DistrSendReportVo d : distrSendList2) {
            quantity = quantity.add(d.getQuantity());
            amount = amount.add(d.getAmount());
            realAmount = realAmount.add(d.getRealAmount());
            notaxRealAmount = notaxRealAmount.add(d.getNotaxRealAmount());
            taxAmount = taxAmount.add(d.getTaxAmount());
        }
        orderReportVo.setQuantity(quantity);
        orderReportVo.setAmount(amount);
        orderReportVo.setRealAmount(realAmount);
        orderReportVo.setNotaxRealAmount(notaxRealAmount);
        orderReportVo.setTaxAmount(taxAmount);
        page.setTotalRecord(count);
        page.setResult(orderReportVo);
    }

    @Override
    public void excelExportReport(OutputStream output, List<DistrSendReportVo> distrSendReportVos, UserVO userVO) {
//标题数据
        List<String> names = new ArrayList<>();
        names.add(userVO.getEnterpriseName());
        names.add("配货单");
        //内容数据
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("sendDateStr", "日期");
        map.put("code", "单号");
        map.put("requestUnitCode", "要货单位编码");
        map.put("requestUnitName", "要货单位名称");
        map.put("distrManName", "配货人员");
        map.put("distrTypeStr", "配货类型");
        map.put("baseOrderCode", "基础单据编号");
        map.put("goodsCode", "商品编码");
        map.put("barcode", "条形码");
        map.put("goodsGenericName", "通用名称");
        map.put("goodsName", "商品名称");
        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");
        map.put("unitName", "单位");
        map.put("manufacturer", "生产厂商");
        map.put("goodsPlace", "产地");
        map.put("approvalNumber", "批准文号");
        map.put("quantity", "数量");
        map.put("unitPrice", "单价");
        map.put("lineDiscount", "折扣");
        map.put("amount", "金额");
        map.put("wholeDiscount", "整单折扣");
        map.put("lineDiscountAmount", "优惠分摊");
        map.put("realPrice", "实际单价");
        map.put("realAmount", "实际金额");
        map.put("taxRate", "税率");
        map.put("notaxRealPrice", "不含税单价");
        map.put("notaxRealAmount", "不含税金额");
        map.put("taxAmount", "税额");
        map.put("statusStr", "状态");
        map.put("businessVarietyName", "品种类型");
        map.put("categoryName", "商品分类");
        map.put("goodsAttributeName", "商品属性");
        map.put("domesticImportDesc", "国产/进口");
        map.put("managementScopeName", "经营范围");
        map.put("specialDrugName", "特殊管理药品");
        map.put("inChargeDrugName", "专管药品");
        map.put("limitQuantity", "限购数量");
        map.put("storageTempDesc", "贮藏温度");
        map.put("storageConditionName", "贮藏条件");
        map.put("qualityPeriodDesc", "保质期");
        StringBuilder endTotal = new StringBuilder();
        BigDecimal quantity = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal realAmount = BigDecimal.ZERO;
        BigDecimal notaxRealAmount = BigDecimal.ZERO;
        BigDecimal taxAmount = BigDecimal.ZERO;
        for (DistrSendReportVo d : distrSendReportVos) {
            quantity = quantity.add(d.getQuantity());
            amount = amount.add(d.getAmount());
            realAmount = realAmount.add(d.getRealAmount());
            notaxRealAmount = notaxRealAmount.add(d.getNotaxRealAmount());
            taxAmount = taxAmount.add(d.getTaxAmount());
        }
        endTotal.append(quantity);
        endTotal.append(";");
        endTotal.append(amount);
        endTotal.append(";");
        endTotal.append(realAmount);
        endTotal.append(";");
        endTotal.append(notaxRealAmount);
        endTotal.append(";");
        endTotal.append(taxAmount);
        List<String> locationList = new ArrayList<String>();
        locationList.add("quantity");
        locationList.add("amount");
        locationList.add("realAmount");
        locationList.add("notaxRealAmount");
        locationList.add("taxAmount");
        purchaseGeneralComponent.commExcelExport(output, map, distrSendReportVos, names, null, endTotal.toString(), false, locationList);
    }

    @Override
    public BranchStockVO getBranchStock(Long requestUnitId, Long goodsid) {

        BranchStockVO branchStockVO = new BranchStockVO();
        //门店库存可用量
        BigDecimal usableQuantity = stockMapper.getUsableQuantityGroupByGoodsId(requestUnitId, goodsid);
        if (usableQuantity == null) {
            usableQuantity = BigDecimal.ZERO;
        }
        branchStockVO.setEnterpriseId(requestUnitId);
        branchStockVO.setUsableQuantity(usableQuantity);
        return branchStockVO;
    }

    @Override
    public List<DraftCacheVO> getDraftCacheVO(UserVO userVO) throws Exception {
        Class<DistrSendCacheVO> clazz = DistrSendCacheVO.class;
        return redisComponent.getDraftCacheVO(userVO.getEnterpriseId(),OrderRule.DISTR_ORDER.getCodePrefix(),clazz);
    }

    @Override
    public DraftCacheVO saveDraftCache(UserVO userVO, DraftCacheVO<DistrSendCacheVO> draftCache) throws Exception {
        String redisKeyValue = draftCache.getId();

        DraftCacheVO<DistrSendCacheVO> draftCacheVO = new DraftCacheVO();

        draftCacheVO.setOrderCode(OrderRule.DISTR_ORDER.getCodePrefix());
        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());

        draftCacheVO.setOrderData(draftCache.getOrderData());
        draftCacheVO.setId(redisKeyValue);
        return redisComponent.saveDraftCacheVO(draftCacheVO);
    }

    @Override
    public void removeDraftCach(Long enterpriseId, String type, String redisKeyValue) throws Exception {
        redisComponent.removeDraftCacheVO(enterpriseId,type,redisKeyValue);
    }

    @Override
    public Page<List<DistrSendGoodsVO>> getBatchGoodsStockList(UserVO loginUser, Long requestUnitId, String param, Page<List<DistrSendGoodsVO>> page) throws Exception {
        if (requestUnitId == null) {
            throw new BusinessException("请先选择要货单位！");
        }
        //要货单位经营范围
        List<Long> businessScopeIds = getBusinessScopeIds(requestUnitId);
        if (businessScopeIds.size() == 0) {
            throw new BusinessException("要货单位经营范围为空！");
        }

        Long enterpriseId = loginUser.getEnterpriseId();
        Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", enterpriseId);
        map.put("businessScopeIds", businessScopeIds);
        map.put("param", param);
        map.put("start",page.getStart());
        map.put("pageSize",page.getPageSize());
        map.put("today",new Date());// 过滤掉过期商品

        //zhuchao:根据商品id进行分组查询保证数量只有一个
        List<DistrSendGoodsVO> distrSendGoodsVOList = goodsMapper.getDistrSendGoodsListByPage(map);
        //门店对应商品可用库存
        BigDecimal childUsableQuantity;
        for (DistrSendGoodsVO distrSendGoodsVO : distrSendGoodsVOList) {
            //门店可用库存
            childUsableQuantity = stockMapper.getUsableQuantityGroupByGoodsId(requestUnitId, distrSendGoodsVO.getId());
            if (childUsableQuantity != null) {
                distrSendGoodsVO.setChildUsableQuantity(childUsableQuantity);
            }
        }
        page.setResult(distrSendGoodsVOList);
        map.replace("strat",null);
        map.replace("pageSize",null);
        distrSendGoodsVOList = goodsMapper.getDistrSendGoodsListByPage(map);
        page.setTotalRecord(distrSendGoodsVOList.size());
        return page;
    }

    /**
     * 根据配货规则分配 配货数量
     * @param planList
     * @param usableQuantity 商品的库存可用数量
     * @param requestTotalQuantity
     */
    private void reqPlansQuantity(List<DistrReqPlanGoodsVO> planList, BigDecimal usableQuantity, BigDecimal requestTotalQuantity, Integer distrRule, Integer sortDate) {
        if (planList == null || planList.size() == 0) {
            return;
        }

        //按要货顺序
        if (distrRule == 0) {
            new DoGetReqPanGoodsVOByOrder(planList, usableQuantity, sortDate).invoke();
        } else if (distrRule == 1) {
            //按库存平均
            BigDecimal averageQuantity = usableQuantity.divide(new BigDecimal(planList.size()), 0,BigDecimal.ROUND_HALF_UP);
            for (DistrReqPlanGoodsVO planGoodsVO : planList) {
                BigDecimal requestQuantity = planGoodsVO.getRequestQuantity();
                if (averageQuantity.compareTo(requestQuantity) >= 0) {
                    planGoodsVO.setSendQuantity(requestQuantity);
                    planGoodsVO.setLackQuantity(BigDecimal.ZERO);
                } else {
                    //库存数量小于要货数量
                    planGoodsVO.setSendQuantity(averageQuantity);
                    planGoodsVO.setLackQuantity(requestQuantity.subtract(averageQuantity));
                }
            }
        }else if (distrRule == 2) {
            //按要货数量占比
            if(usableQuantity.compareTo(requestTotalQuantity) >= 0){
                //库存数量大于等于总的要货数量
                new DoGetReqPanGoodsVOByOrder(planList, usableQuantity, sortDate).invoke();

            } else {
                //库存数量小于总的要货数量
                BigDecimal quantityTmp = BigDecimal.ZERO;
                BigDecimal usableQuantityTmp = BigDecimal.ZERO;
                for (int i = 0; i < planList.size(); i++) {
                    DistrReqPlanGoodsVO planGoodsVO = planList.get(i);
                    BigDecimal requestQuantity = planGoodsVO.getRequestQuantity();
                    /**
                     * 向上取整根据要货数量占比计算要货数量
                     */
                    if(i < planList.size() - 1){

                        BigDecimal quantity = usableQuantity.multiply(requestQuantity).divide(requestTotalQuantity, 0, BigDecimal.ROUND_HALF_UP);
                        planGoodsVO.setSendQuantity(quantity);
                        planGoodsVO.setLackQuantity(requestQuantity.subtract(quantity));
                        quantityTmp = quantityTmp.add(quantity);

                    } else {
                        //最后一个
                        usableQuantityTmp = usableQuantity.subtract(quantityTmp);
                        planGoodsVO.setSendQuantity(usableQuantityTmp);
                        planGoodsVO.setLackQuantity(requestQuantity.subtract(usableQuantityTmp));
                    }

                }
            }

        } else {
            throw new BusinessException("配货规则不存在");
        }
    }

    private BigDecimal calculatePlanGoodsQuantity(BigDecimal usableQuantity, DistrReqPlanGoodsVO planGoodsVO, BigDecimal requestQuantity) {
        if (usableQuantity.compareTo(BigDecimal.ZERO) == 1) {
            //库存可用量大于0
            if(usableQuantity.compareTo(requestQuantity) >= 0){
                //库存数量大于要货数量
                planGoodsVO.setSendQuantity(requestQuantity);
                planGoodsVO.setLackQuantity(BigDecimal.ZERO);
            } else{
                //库存可用数量小于要货数量
                planGoodsVO.setSendQuantity(usableQuantity);
                planGoodsVO.setLackQuantity(requestQuantity.subtract(usableQuantity));
            }
            usableQuantity = usableQuantity.subtract(requestQuantity);
            if(usableQuantity.compareTo(BigDecimal.ZERO) <= 0){
                usableQuantity = BigDecimal.ZERO;
            }

        } else {
            planGoodsVO.setSendQuantity(BigDecimal.ZERO);
            planGoodsVO.setLackQuantity(requestQuantity.subtract(usableQuantity));
        }
        return usableQuantity;
    }

    private class DoGetReqPanGoodsVOByOrder {
        private List<DistrReqPlanGoodsVO> planList;
        private BigDecimal usableQuantity;
        private Integer sortDate;

        public DoGetReqPanGoodsVOByOrder(List<DistrReqPlanGoodsVO> planList, BigDecimal usableQuantity, Integer sortDate) {
            this.planList = planList;
            this.usableQuantity = usableQuantity;
            this.sortDate = sortDate;
        }

        public void invoke() {
            if (sortDate == 0) {
                for (DistrReqPlanGoodsVO planGoodsVO : planList) {
                    BigDecimal requestQuantity = planGoodsVO.getRequestQuantity();
                    usableQuantity = calculatePlanGoodsQuantity(usableQuantity, planGoodsVO, requestQuantity);
                }
            } else {
                for (int i = planList.size() - 1; i >= 0 ; i--) {
                    DistrReqPlanGoodsVO planGoodsVO = planList.get(i);
                    BigDecimal requestQuantity = planGoodsVO.getRequestQuantity();
                    usableQuantity = calculatePlanGoodsQuantity(usableQuantity, planGoodsVO, requestQuantity);
                }
            }
        }
    }
}
