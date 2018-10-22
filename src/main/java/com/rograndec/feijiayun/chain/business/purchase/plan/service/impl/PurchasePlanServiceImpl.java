package com.rograndec.feijiayun.chain.business.purchase.plan.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrLackDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsBusinessMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsBusiness;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.init.model.ApprovalFlowContentModel;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderDetail;
import com.rograndec.feijiayun.chain.business.purchase.order.service.PurchaseOrderService;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderDetailVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderOtherVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderVO;
import com.rograndec.feijiayun.chain.business.purchase.plan.approvalProcessor.PurchasePlanApprovalProcessor;
import com.rograndec.feijiayun.chain.business.purchase.plan.dao.PurchasePlanDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.plan.dao.PurchasePlanMapper;
import com.rograndec.feijiayun.chain.business.purchase.plan.dao.PurchasePlanModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.purchase.plan.entity.PurchasePlan;
import com.rograndec.feijiayun.chain.business.purchase.plan.entity.PurchasePlanDetail;
import com.rograndec.feijiayun.chain.business.purchase.plan.entity.PurchasePlanModifyRecord;
import com.rograndec.feijiayun.chain.business.purchase.plan.service.PurchasePlanService;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.HistoricalUnitPriceVO;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.IntellectPurchaseReqVO;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanDetailVO;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanDraftCacheVO;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanGoodsVO;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanPageVO;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanReturnVO;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanVO;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.SupplierVO;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SubmitApprovalFlowVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.service.ManageConfigService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.ApprovalFlowComponent;
import com.rograndec.feijiayun.chain.common.component.CalculateComponent;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.component.ModifyRecordCompoent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.component.UserComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.GoodsNature;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.common.constant.TaxRateType;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.GoodsDefTaxRateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.TransferCapital;
import com.rograndec.feijiayun.chain.utils.TwoTuple;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

/**
 * @author dongyang.du 2017年09月13日17:25:57
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PurchasePlanServiceImpl implements PurchasePlanService {
    private static final Log logger = LogFactory.getLog(PurchasePlanServiceImpl.class);

    @Autowired
    private PurchasePlanMapper planMapper;

    @Autowired
    private PurchasePlanDetailMapper planDetailMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private ModifyRecordCompoent modifyRecordCompoent;

    @Autowired
    private PurchasePlanModifyRecordMapper planModifyRecordMapper;

    @Autowired
    private PurchaseOrderService orderService;

    @Autowired
    private ManageConfigService manageConfigService;

    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private ApprovalFlowComponent approvalFlowComponent;

    @Autowired
    private PurchasePlanApprovalProcessor purchasePlanApprovalProcessor;

    @Autowired
    private UserComponent userComponent;

    @Autowired
    private GoodsTaxRateMapper taxRateMapper;

    @Autowired
    private DistrLackDetailMapper distrLackDetailMapper;

    @Autowired
    private CommonComponent  commonComponent;


    @Autowired
    private SupplierMapper  supplierMapper;


    @Autowired
    private PriceOrderMapper priceOrderMapper;

    @Autowired
    private PriceOrderDetailMapper priceOrderDetailMapper;

    @Autowired
    private ExcelComponent excelComponent;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private GoodsBusinessMapper goodsBusinessMapper;


    /**
     * 新增 采购计划 一对多
     * <p>
     * 有审批流的 走审批， 没有直接待订购
     *
     * @throws Exception
     */
    @Override
    public Result<String> save(PurchasePlanVO purchasePlanVO, UserVO userVO) throws Exception {
        Result<String> result = new Result<String>();
        ManageConfig manageConfig = manageConfigService.getManageConfig(userVO);
        Integer status = PurchaseStatus.PENDING_ORDER.getStatus();// 代订购状态

        if (manageConfig.getApprovalControl() == 1) {// 如果审批流开启
            status = PurchaseStatus.PENDING_AUDIT.getStatus();// 待审核状态
        }

        Long eId = userVO.getEnterpriseId();
        String eCode = userVO.getEnterpriseCode();

        PurchasePlan plan = new PurchasePlan();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(purchasePlanVO, plan);
        plan.setOrderType(OrderRule.PURCHASE_PLAN.getType());// 固定 单据类型
        String code = orderCodeComponent.generate(OrderRule.PURCHASE_PLAN.getCodePrefix(), eId, eCode);// 单号

        // 获取人员信息
        if (manageConfig.getBusinessControl() == 0) { // 质量流程控制关闭
            User panner = userComponent.findUserByUserId(plan.getPannerId());
            if (panner == null) {
                throw new BusinessException(SysCode.FAIL.getCode(), "采购计划人员为空，请检查id = " + plan.getPannerId());
            }
            plan.setPannerCode(panner.getCode());
            plan.setPannerName(panner.getName());
        } else {
            plan.setPannerId(userVO.getUserId());
            plan.setPannerCode(userVO.getUserCode());
            plan.setPannerName(userVO.getUserName());
        }


        plan.setCode(code);
        plan.setEnterpriseId(eId);
        plan.setCreaterId(userVO.getUserId());
        plan.setCreaterCode(userVO.getUserCode());
        plan.setCreaterName(userVO.getUserName());
        plan.setCreateTime(new Date());
        plan.setUpdateTime(new Date());
        plan.setModifierId(userVO.getUserId());
        plan.setModifierCode(userVO.getUserCode());
        plan.setModifierName(userVO.getUserName());
        plan.setUpdateTime(new Date());
        plan.setStatus(status);

        // 相关计算
        relatedCalc(purchasePlanVO, plan);

        plan.setId(null);
        planMapper.insertSelective(plan);

        for (PurchasePlanDetailVO detailVO : purchasePlanVO.getPlanDetails()) {

            PurchasePlanDetail planDetail = new PurchasePlanDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(detailVO, planDetail);
            planDetail.setId(null);
            // 重要属性
            planDetail.setPlanId(plan.getId());
            planDetail.setStatus(status);
            planDetail.setRemark(plan.getRemark());

            // 商品的一些属性赋值
            assignmentGoodsField(planDetail, detailVO.getGoodsId());

            // 其他默认属性
            planDetail.setEnterpriseId(eId);
            planDetail.setCreaterId(userVO.getUserId());
            planDetail.setCreaterCode(userVO.getUserCode());
            planDetail.setCreaterName(userVO.getUserName());
            planDetail.setCreateTime(new Date());
            planDetail.setModifierId(userVO.getUserId());
            planDetail.setModifierCode(userVO.getUserCode());
            planDetail.setModifierName(userVO.getUserName());
            planDetail.setUpdateTime(new Date());

            planDetailMapper.insertSelective(planDetail);
        }

        // 提交流程审批

        if (manageConfig.getApprovalControl() == 1) {// 如果审批流开启
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getEnterpriseId());

            SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(userVO.getEnterpriseId(), enterprise.getName(),
                    userVO.getUserId(), userVO.getUserName(), userVO.getChainType(), userVO.getParentId(),
                    userVO.getChainType().equals(ChainType.Headquarters.getCode()) ? userVO.getEnterpriseId() : userVO.getParentId(),
                    ApprovalFlowContentModel.getPurchasePlanCode(), plan.getId(), "", "");

            approvalFlowComponent.apply(submitApprovalFlowVO, userVO);

        }

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, code);


        redisComponent.removeDraftCacheVO(userVO.getEnterpriseId(),OrderRule.PURCHASE_PLAN.getCodePrefix(),purchasePlanVO.getRedisKeyValue());

        return result;
    }

    /**
     * 查询商品的一些属性并赋值
     *
     * @param planDetail
     */
    private void assignmentGoodsField(PurchasePlanDetail planDetail, Long goodsId) {
        // 查询商品，获取一些默认属性
        //GoodsVO goodsVO = goodsMapper.getGoodsInfoById(goodsId);
        Goods goodsVO = goodsMapper.selectByPrimaryKey(goodsId);
        planDetail.setGoodsCode(goodsVO.getCode());
        planDetail.setApprovalNumber(goodsVO.getApprovalNumber());// 批准文号
        planDetail.setUnitId(goodsVO.getUnitId());
        planDetail.setUnitName(goodsVO.getUnitName());
        planDetail.setDosageId(goodsVO.getDosageId());
        planDetail.setDosageName(goodsVO.getDosageName());
        planDetail.setBarcode(goodsVO.getBarcode());// 条形码
        planDetail.setGoodsName(goodsVO.getName());
        planDetail.setGoodsGenericName(goodsVO.getGenericName());
        planDetail.setGoodsSpecification(goodsVO.getSpecification());
        planDetail.setManufacturer(goodsVO.getManufacturer());// 生产厂商
        planDetail.setManufacturerId(goodsVO.getManufacturerId());
        planDetail.setGoodsPlace(goodsVO.getPlace());// 产地

    }

    /**
     * 获取计划及明细
     *
     * @param planId plan的主键id
     * @param userVO
     */
    @Override
    public PurchasePlanVO selectDetailByPlanId(Long planId, UserVO userVO) throws Exception{
        PurchasePlanVO purchasePlanVO = planDetailMapper.selectDetailByPlanId(planId);

        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(purchasePlanVO.getEnterpriseId());
        if(enterprise == null){
            throw new BusinessException("无效的企业ID： " + purchasePlanVO.getEnterpriseId());
        }

        purchasePlanVO.setEnterpriseName(enterprise.getName());
        purchasePlanVO.setAmountCapital(TransferCapital.transfer(purchasePlanVO.getAmountTotal()));
        if (purchasePlanVO.getPlanDetails() != null) {
            for (PurchasePlanDetailVO detailVO : purchasePlanVO.getPlanDetails()) {
                detailVO.setCarriageName(DistributionType.getName(detailVO.getCarriageMode()));

                List<SupplierVO> supplierVOList = getBasicSupplierByBusinessScope(userVO, detailVO.getGoodsId());
                detailVO.setSupplierVOList(supplierVOList);
            }
        }
        return purchasePlanVO;
    }

    /**
     * 取消采购计划
     */
    @Override
    public void cancelPlan(Long planId, UserVO userVO) {
        planMapper.updateStatus(planId, PurchaseStatus.CANCELED.getStatus());
        planDetailMapper.updateStatusByPlanId(planId, PurchaseStatus.CANCELED.getStatus());

    }

    /**
     * 删除明细
     */
    @Override
    public void delete(Long planDetailId, UserVO loginUser) {
        PurchasePlanDetail planDetail = planDetailMapper.selectByPrimaryKey(planDetailId);
        planDetailMapper.deleteByPrimaryKey(planDetailId);
        // 判断明细表是否都删除，如果都删除了 删除计划表
        PurchasePlanVO purchasePlanVO = planDetailMapper.selectDetailByPlanId(planDetail.getPlanId());
        if (purchasePlanVO == null) {// join 查询，如果明细表都删除了，则查不到任何信息
            planMapper.deleteByPrimaryKey(planDetail.getPlanId());
        }

        addPlanModifyRecord(planDetail.getEnterpriseId(), planDetail.getPlanId(), planDetailId, "删除一条明细", loginUser);

    }

    /**
     * 计划分页查询
     */
    @Override
    public Page<PurchasePlanReturnVO> getPurchasePlanPage(Page<PurchasePlanReturnVO> page, Integer status, String code, String pannerName,
                                                          Date startDate, Date endDate, String order, String sort, UserVO userVO) {

        Long eId = userVO.getEnterpriseId();
        if (ChainType.Headquarters.getCode() != userVO.getChainType()) {
            eId = userVO.getParentId();
        }
        if (!"asc".equals(sort) && !"desc".equals(sort)) {
            sort = "desc";
        }
        if (StringUtils.isEmpty(order)) {
            order = "plan_date"; // 默认计划日期倒序
        }
        if ("planDate".equals(order)) {
            order = "plan_date";
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);

        map.put("startDate", startDate);
        map.put("endDate", endDate);
        if (!StringUtils.isEmpty(code)) {
            map.put("code", code);// 计划单号
        }
        if (!StringUtils.isEmpty(pannerName)) {
            map.put("pannerName", pannerName);// 计划人员
        }
        map.put("order", order);
        map.put("sort", sort);
        map.put("enterpriseId", eId);
        map.put("pageStart",page.getStart());
        map.put("pageSize",page.getPageSize());

        ManageConfig manageConfig = manageConfigService.getManageConfig(userVO);

        if (manageConfig != null){ // 可能企业质量配置没有初始化
            if (manageConfig.getApprovalControl() != 1) {// 如果审批流没开启
                map.put("approval", Boolean.TRUE);
            }
        }

        List<PurchasePlanPageVO> planVOs = planMapper.selectPurchasePlanPage(map);
        Integer  totalCount  =  planMapper.selectPurchasePlanPageCount(map);

        Map<String, Object> allTotal = planMapper.selectTotal(map);
        PurchasePlanReturnVO planReturnVO = new PurchasePlanReturnVO();
        planReturnVO.setPlanVOs(planVOs);
        planReturnVO.setAmountAllTotal(allTotal == null ? new BigDecimal(0) : (BigDecimal) allTotal.get("amountAllToal"));
        planReturnVO.setNotaxAmountAllTotal(allTotal == null ? new BigDecimal(0) : (BigDecimal) allTotal.get("notaxAmountAllTotal"));
        planReturnVO.setTaxAmountAllTotal(allTotal == null ? new BigDecimal(0) : (BigDecimal) allTotal.get("taxAmountAllTotal"));
        page.setResult(planReturnVO);
        page.setTotalRecord(Integer.parseInt(totalCount + ""));
        //page.setTotalPage();
        return page;
    }

    /**
     * 更新采购计划, 记录修改记录 注意：更新时可能会新增一条明细
     */
    @Override
    public Result<String> update(PurchasePlanVO purchasePlanVO, UserVO loginUser) throws Exception {
        Result<String> result = new Result<String>();

        // 校验 只有审核被 驳回的 状态才可以修改

        PurchasePlan purchasePlan = planMapper.selectByPrimaryKey(purchasePlanVO.getId());
        if(!purchasePlan.getStatus().equals(PurchaseStatus.AUDIT_REJECT.getStatus())
                && !purchasePlan.getStatus().equals(PurchaseStatus.PENDING_ORDER.getStatus())){
            throw new BusinessException("非审核驳回状态，不允许修改");
        }

        // 更新计划表
        logger.debug("purchasePlanVO:" + purchasePlanVO);
        PurchasePlan plan = new PurchasePlan();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(purchasePlanVO, plan);
        logger.debug("plan: " + plan);

        // 获取人员信息
        User panner = userComponent.findUserByUserId(plan.getPannerId());
        plan.setEnterpriseId(purchasePlan.getEnterpriseId());
        plan.setOrderType(purchasePlan.getOrderType());
        plan.setCode(purchasePlan.getCode());
        plan.setPannerCode(panner.getCode());
        plan.setPannerName(panner.getName());
        plan.setModifierId(loginUser.getUserId());
        plan.setModifierCode(loginUser.getUserCode());
        plan.setModifierName(loginUser.getUserName());
        plan.setUpdateTime(new Date());

        relatedCalc(purchasePlanVO, plan);

        planMapper.updateByPrimaryKeySelective(plan);



        // 更新计划的修改记录
        updateModifyRecord(purchasePlanVO, loginUser,plan,purchasePlan);

        // 更新明细表
        List<PurchasePlanDetailVO> planDetailVOs = purchasePlanVO.getPlanDetails();


        // 处理删除的商品
        PurchasePlanVO oldPlan = planDetailMapper.selectDetailByPlanId(purchasePlanVO.getId());
        List<PurchasePlanDetailVO> oldPlanPlanDetails = oldPlan.getPlanDetails();
        boolean b = oldPlanPlanDetails.removeAll(planDetailVOs);

        for (PurchasePlanDetailVO deleteDetail:oldPlanPlanDetails) {
            planDetailMapper.deleteByPrimaryKey(deleteDetail.getId());
            addPlanModifyRecord(purchasePlanVO.getEnterpriseId(), purchasePlanVO.getId(), deleteDetail.getId(), "删除一条【"+deleteDetail.getGoodsGenericName()+"】商品明细", loginUser);
        }

        for (PurchasePlanDetailVO planDetailVO : planDetailVOs) {
            PurchasePlanDetail oldplanDetail = planDetailMapper.selectByPrimaryKey(planDetailVO.getId());

            // 检查商品是否更换，如果更换 重新查
            PurchasePlanDetail newPlanDetail = new PurchasePlanDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(planDetailVO, newPlanDetail);

            // ===============↓↓↓ 单独处理 新增的 ↓↓↓ =======================
            if (newPlanDetail.getId() == null) {

                // 重要属性
                newPlanDetail.setPlanId(purchasePlan.getId());
                newPlanDetail.setStatus(purchasePlan.getStatus());
                newPlanDetail.setRemark(purchasePlan.getRemark());
                // 商品的一些属性赋值
                assignmentGoodsField(newPlanDetail, newPlanDetail.getGoodsId());

                newPlanDetail.setTaxRateId(planDetailVO.getTaxRateId());
                // 获取税率
                newPlanDetail.setTaxRate(taxRateMapper.selectByPrimaryKey(planDetailVO.getTaxRateId()).getTaxRate());


                // 其他默认属性
                newPlanDetail.setEnterpriseId(loginUser.getEnterpriseId());
                newPlanDetail.setCreaterId(loginUser.getUserId());
                newPlanDetail.setCreaterCode(loginUser.getUserCode());
                newPlanDetail.setCreaterName(loginUser.getUserName());
                newPlanDetail.setCreateTime(new Date());

                planDetailMapper.insertSelective(newPlanDetail);
                // 修改记录添加
                addPlanModifyRecord(newPlanDetail.getEnterpriseId(), newPlanDetail.getPlanId(), newPlanDetail.getId(), "新增一条【"+newPlanDetail.getGoodsGenericName()+"】商品明细", loginUser);

                continue;
            }
            // ==================== ↑↑↑ 新增的结束 ↑↑↑ =====================
            if (!newPlanDetail.getGoodsId().equals(oldplanDetail.getGoodsId())) {
                // 商品的一些属性赋值
                assignmentGoodsField(newPlanDetail, planDetailVO.getGoodsId());
            }
            // 更新明细修改记录
            Map<String, Object> newPlanDetailMap = modifyRecordCompoent.getFieldsMap(planDetailVO);

            Map<String, Object> oldPlanDetailMap = modifyRecordCompoent.getFieldsMap(oldplanDetail);
            // 记录计划的修改记录
            List<PurchasePlanModifyRecord> planDetailModifyRecords = getModifyRecordList(loginUser, plan, "saas_purchase_plan_detail",
                    planDetailVO.getId(), new Date(), oldPlanDetailMap, newPlanDetailMap, detailFieldMustMap(),
                    purchasePlanVO.getUpdateDetail());

            for (PurchasePlanModifyRecord planModifyRecord : planDetailModifyRecords) {
                planModifyRecordMapper.insertSelective(planModifyRecord);

            }

            // 默认值
            newPlanDetail.setUpdateTime(new Date());
            newPlanDetail.setModifierId(loginUser.getUserId());
            newPlanDetail.setModifierCode(loginUser.getUserCode());
            newPlanDetail.setModifierName(loginUser.getUserName());
            newPlanDetail.setUpdateTime(new Date());
            planDetailMapper.updateByPrimaryKeySelective(newPlanDetail);
        }


        // 重新提交审批，并更新状态为 待审批
        if(purchasePlan.getStatus().equals(PurchaseStatus.AUDIT_REJECT.getStatus())){// 审核拒绝的
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(loginUser.getEnterpriseId());

            SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(loginUser.getEnterpriseId(), enterprise.getName(),
                    loginUser.getUserId(), loginUser.getUserName(), loginUser.getChainType(), loginUser.getParentId(),
                    loginUser.getChainType().equals(ChainType.Headquarters.getCode()) ? loginUser.getEnterpriseId() : loginUser.getParentId(),
                    ApprovalFlowContentModel.getPurchasePlanCode(), plan.getId(), "", "");

            approvalFlowComponent.reapply(submitApprovalFlowVO, purchasePlanApprovalProcessor, loginUser);

            planMapper.updateStatus(purchasePlanVO.getId(), PurchaseStatus.PENDING_AUDIT.getStatus());
            planDetailMapper.updateStatusByPlanId(purchasePlanVO.getId(), PurchaseStatus.PENDING_AUDIT.getStatus());
        }



        result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchasePlanVO.getCode());

        return result;
    }

    /**
     * 相关统计计算
     *
     * @param purchasePlanVO
     * @param plan
     */
    private void relatedCalc(PurchasePlanVO purchasePlanVO, PurchasePlan plan) {
        // 统计品种种类, 合计
        Set<Long> goodsIdSet = new HashSet<Long>();// 统计商品种类数量
        BigDecimal amountTotal = new BigDecimal(0);
        BigDecimal taxAmountTotal = new BigDecimal(0);
        BigDecimal quantityTotal = new BigDecimal(0);
        BigDecimal notaxAmountTotal = new BigDecimal(0);
        for (PurchasePlanDetailVO detailVO : purchasePlanVO.getPlanDetails()) {
            // ================ 相关计算 =====================


            detailVO.setTaxRateId(detailVO.getTaxRateId());
            // 获取税率
            detailVO.setTaxRate(taxRateMapper.selectByPrimaryKey(detailVO.getTaxRateId()).getTaxRate());

            BigDecimal quantity = detailVO.getQuantity();// 数量
            BigDecimal price = detailVO.getUnitPrice();// 单价
            BigDecimal lineDiscount = new BigDecimal(100);
            BigDecimal taxRate = detailVO.getTaxRate();
            detailVO.setAmount(CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(quantity, price, lineDiscount)); // 金额
            detailVO.setNotaxAmount(CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(detailVO.getAmount(), taxRate));// 不含税金额
            detailVO.setNotaxPrice(CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(detailVO.getNotaxAmount(), quantity)); // 不含税单价
            detailVO.setTaxAmout(
                    CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(detailVO.getAmount(), detailVO.getNotaxAmount()));// 税额
            // ============== 相关计算结束 =================
            goodsIdSet.add(detailVO.getGoodsId());
            amountTotal = amountTotal.add(detailVO.getAmount());
            taxAmountTotal = taxAmountTotal.add(detailVO.getTaxAmout());
            quantityTotal = quantityTotal.add(detailVO.getQuantity());
            notaxAmountTotal = notaxAmountTotal.add(detailVO.getNotaxAmount());
        }
        plan.setAmountTotal(amountTotal);// 金额 合计
        plan.setTaxAmountTotal(taxAmountTotal);// 税额 合计
        plan.setNotaxAmountTotal(notaxAmountTotal);// 无税金额 合计
        plan.setQuantityTotal(quantityTotal);// 数量合计
        plan.setVarietiesQuantity(goodsIdSet.size());// 品种合计
    }

    /**
     * @param enterpriseId
     * @param planId
     * @param planDetailId
     * @param remark
     * @param loginUser
     */
    private void addPlanModifyRecord(Long enterpriseId, Long planId, Long planDetailId, String remark, UserVO loginUser) {
        PurchasePlanModifyRecord planModify = new PurchasePlanModifyRecord();
        planModify.setEnterpriseId(loginUser.getEnterpriseId());
        planModify.setParentId(loginUser.getParentId());
        planModify.setTableName("saas_purchase_plan");
        planModify.setKeyId(planDetailId);
        planModify.setColumnEnName(remark);
        planModify.setColumnChName(remark);
        planModify.setUpdateTime(new Date());
        planModify.setCreaterId(loginUser.getUserId());
        planModify.setCreaterCode(loginUser.getUserCode());
        planModify.setCreaterName(loginUser.getUserName());
        planModify.setCreateTime(new Date());
        planModify.setOldContent(remark);
        planModify.setNewContent(remark);
        planModify.setPlanId(planId);// 计划ID
        planModify.setModifierId(loginUser.getUserId());
        planModify.setModifierCode(loginUser.getUserCode());
        planModify.setModifierName(loginUser.getUserName());
        planModify.setRemark(remark);
        planModifyRecordMapper.insertSelective(planModify);

    }

    private void updateModifyRecord(PurchasePlanVO purchasePlanVO, UserVO userVO , PurchasePlan newPlan,PurchasePlan oldPlan) throws Exception {
        Map<String, Object> newPlanMap = modifyRecordCompoent.getFieldsMap(newPlan);


        Map<String, Object> oldPlanMap = modifyRecordCompoent.getFieldsMap(oldPlan);
        // 记录计划的修改记录
        List<PurchasePlanModifyRecord> planModifyRecords = getModifyRecordList(userVO, oldPlan, "saas_purchase_plan", purchasePlanVO.getId(),
                new Date(), oldPlanMap, newPlanMap, fieldMustMap(), purchasePlanVO.getUpdateDetail());

        for (PurchasePlanModifyRecord planModifyRecord : planModifyRecords) {
            planModifyRecordMapper.insertSelective(planModifyRecord);
        }
    }

    private List<PurchasePlanModifyRecord> getModifyRecordList(UserVO userVO, PurchasePlan plan, String tableName, Long keyId,
                                                               Date updateTime, Map<String, Object> valMap, Map<String, Object> newMap, Map<String, String> fieldMustMap,
                                                               String updateDetail) {// 修改原因


        List<PurchasePlanModifyRecord> modifyRecordWithBLOBs = new ArrayList<>();

        for (Map.Entry<String, String> entry : fieldMustMap.entrySet()) {
            Object obj = valMap.get(entry.getKey());
            Object newObj = newMap.get(entry.getKey());

            if (null != obj ) {

                if(obj instanceof BigDecimal && newObj instanceof BigDecimal && ((BigDecimal) obj).compareTo((BigDecimal) newObj) == 0){
                    continue;
                } else if (obj instanceof Date && newObj instanceof Date){// 时间格式化
                    if(!obj.equals(newObj)){
                        obj =  DateUtils.DateToString((Date) obj,DateUtils.FMT_DATE);
                        newObj =   DateUtils.DateToString((Date) newObj,DateUtils.FMT_DATE);
                    }
                }

                if (!obj.equals(newObj)){
                    // 构造修改记录
                    buildModifyRecord(userVO,tableName,keyId,entry,obj,newObj,updateDetail,plan,modifyRecordWithBLOBs);
                }

            } else if (obj == null && newObj != null){
                buildModifyRecord(userVO,tableName,keyId,entry,obj,newObj,updateDetail,plan,modifyRecordWithBLOBs);
            }
        }

        return modifyRecordWithBLOBs;
    }

    private void buildModifyRecord(UserVO userVO, String tableName, Long keyId, Map.Entry<String, String> entry, Object obj,
                                   Object newObj, String updateDetail, PurchasePlan plan, List<PurchasePlanModifyRecord> modifyRecordWithBLOBs) {
        PurchasePlanModifyRecord planModify = new PurchasePlanModifyRecord();
        planModify.setEnterpriseId(userVO.getEnterpriseId());
        planModify.setParentId(userVO.getParentId());
        planModify.setTableName(tableName);
        planModify.setKeyId(keyId);
        planModify.setColumnEnName(entry.getKey());
        planModify.setColumnChName(entry.getValue());
        planModify.setUpdateTime(new Date());
        planModify.setCreaterId(userVO.getUserId());
        planModify.setCreaterCode(userVO.getUserCode());
        planModify.setCreaterName(userVO.getUserName());
        planModify.setCreateTime(new Date());
        planModify.setOldContent(obj == null ? "" : obj.toString());
        planModify.setNewContent( newObj == null ? "" : newObj.toString());
        planModify.setPlanId(plan.getId());// 计划ID
        planModify.setModifierId(userVO.getUserId());
        planModify.setModifierCode(userVO.getUserCode());
        planModify.setModifierName(userVO.getUserName());
        planModify.setRemark(updateDetail);
        modifyRecordWithBLOBs.add(planModify);
    }

    /*
     * 计划明细 必要字段  
     */
    private Map<String, String> detailFieldMustMap() {
        Map<String, String> fieldNames = new HashMap<>();
        fieldNames.put("goodsId", "商品ID");
        fieldNames.put("goodsCode", "商品编码");
        fieldNames.put("goodsName", "商品名称");
        fieldNames.put("goodsGenericName", "商品通用名称");
        fieldNames.put("dosageId", "剂型ID");
        fieldNames.put("dosageName", "剂型名称");
        fieldNames.put("goodsSpecification", "商品规格");
        fieldNames.put("manufacturer", "生产厂商");
        fieldNames.put("quantity", "数量");
        fieldNames.put("unitPrice", "单价");
        fieldNames.put("amount", "金额");
        fieldNames.put("taxRate", "税率");
        fieldNames.put("notaxPrice", "不含税单价");
        fieldNames.put("notaxAmount", "不含税金额");
        fieldNames.put("taxAmout", "税额");
        fieldNames.put("supplierId", "供货单位ID");
        fieldNames.put("supplierCode", "供货位单位编码");
        fieldNames.put("supplierName", "供货单位名称");
        fieldNames.put("carriageMode", "承运方式");

        return fieldNames;
    }

    /**
     * 计划 必要字段
     *
     * @return
     */
    private Map<String, String> fieldMustMap() {
        Map<String, String> fieldNames = new HashMap<>();

        fieldNames.put("planDate", "计划日期");
        fieldNames.put("pannerId", "计划人员ID");
        fieldNames.put("pannerCode", "计划人员编码");
        fieldNames.put("pannerName", "计划人员名称");
        fieldNames.put("amountTotal", "金额（合计）");
        fieldNames.put("notaxAmountTotal", "无税金额（合计");
        fieldNames.put("taxAmountTotal", "税额（合计）");
        fieldNames.put("remark", "备注");

        return fieldNames;
    }

    @Override
    public List<String> createOrder(Long planId, UserVO userVO) throws Exception {

        List<String> orderCodes = new ArrayList<>();
        Integer carriageMode = null;
        PurchasePlan plan = planMapper.selectByPrimaryKey(planId);
        // 查询当前订单有多少供货 单位：
        List<PurchasePlanDetail> suppliers = planDetailMapper.selectDistinctSupplierIdByPlanId(planId);
        // 遍历 供货单位 和 订单ID 查询 该单该供货单位 采购多少商品
        for (PurchasePlanDetail supplier : suppliers) {
            List<PurchasePlanDetail> planDetails = planDetailMapper.selectBySupplierIdAndPlanId(supplier.getSupplierId(), planId);

            // 组装数据 生成订单
            PurchaseOrderVO purchaseOrderVO = new PurchaseOrderVO();
            List<PurchaseOrderDetailVO> purchaseOrderDetailVOs = new ArrayList<>();
            // orderdetail 信息
            BigDecimal amountTotal = new BigDecimal(0);// 金额合计
            BigDecimal quantityTotal = new BigDecimal(0);// 数量合计
            BigDecimal taxAmountTotal = new BigDecimal(0);// 税额合计

            BigDecimal notaxAmountTotal = BigDecimal.ZERO;// 不含税金额合计

            for (int i = 0; i < planDetails.size(); i++) {

                PurchasePlanDetail planDetail = planDetails.get(i);
                carriageMode = planDetail.getCarriageMode();

                PurchaseOrderDetailVO orderDetailVO = new PurchaseOrderDetailVO();

                orderDetailVO.setGoodsId(planDetail.getGoodsId());// 商品ID

                BigDecimal quantity = planDetail.getQuantity();// 数量
                BigDecimal unitPrice = planDetail.getUnitPrice();// 单价
                BigDecimal amount = planDetail.getAmount();//金额
                BigDecimal taxRate = planDetail.getTaxRate();// 税率

                orderDetailVO.setQuantity(quantity);// 数量
                orderDetailVO.setUnitPrice(unitPrice);// 单价
                orderDetailVO.setAmount(amount);// 金额(整单优化前的金额)

                quantityTotal = quantityTotal.add(planDetail.getQuantity());
                amountTotal =  amountTotal.add(planDetail.getAmount());

                orderDetailVO.setLineDiscount(new BigDecimal(100));// 行折扣（%）
                orderDetailVO.setWholeDiscount(new BigDecimal(100));// 整单折扣（%）
                orderDetailVO.setLineDiscountAmount(new BigDecimal(0));// 行优惠（整单优惠分摊到行的金额）
                orderDetailVO.setRealPrice(planDetail.getUnitPrice());// 实际单价
                orderDetailVO.setRealAmount(planDetail.getAmount());// 实际金额
                orderDetailVO.setTaxRateId(planDetail.getTaxRateId());// 进项税ID
                orderDetailVO.setTaxRate(taxRate); // 进项税


                orderDetailVO.setNotaxRealAmount(planDetail.getNotaxAmount());// 不含税实际金额
                notaxAmountTotal = notaxAmountTotal.add(planDetail.getNotaxAmount());

                orderDetailVO.setNotaxRealPrice(planDetail.getNotaxPrice());// 不含税实际单价
                orderDetailVO.setTaxAmount(planDetail.getTaxAmout());// 税额
                taxAmountTotal = taxAmountTotal.add(planDetail.getTaxAmout());


                orderDetailVO.setUnclearQuantity(planDetail.getQuantity());// 未清数量
                orderDetailVO.setClearQuantity(new BigDecimal(0)); // 已清数量
                orderDetailVO.setLineNum(i); // 行号

                orderDetailVO.setBaseOrderId(planId);// 基础单据ID
                orderDetailVO.setBaseOrderDate(plan.getPlanDate());// 基础单据日期
                orderDetailVO.setBaseOrderCode(plan.getCode());// 基础单据编码
                orderDetailVO.setBaseOrderType(plan.getOrderType());// 基础单据类型
                orderDetailVO.setBaseOrderDtlId(planDetail.getId());// 基础单据明细ID


                purchaseOrderDetailVOs.add(orderDetailVO);
                purchaseOrderVO.setSupplierId(planDetail.getSupplierId());
                purchaseOrderVO.setSupplierCode(planDetail.getSupplierCode());// 供货位单位编码
                purchaseOrderVO.setSupplierName(planDetail.getSupplierName());// 供货单位名称

            }

            purchaseOrderVO.setPurchaseOrderDetailVO(purchaseOrderDetailVOs);
            // order 信息
            purchaseOrderVO.setOrderDate(new Date());// 计划日期
            purchaseOrderVO.setAmountTotal(amountTotal);// 金额合计（整单优惠前的金额合计）
            purchaseOrderVO.setVarietiesQuantity(purchaseOrderDetailVOs.size());// 品种数量
            purchaseOrderVO.setQuantityTotal(quantityTotal);// 数量合计
            purchaseOrderVO.setBaseOrderId(planId);// 基础单据ID
            purchaseOrderVO.setBaseOrderDate(plan.getPlanDate());// 基础单据日期
            purchaseOrderVO.setBaseOrderCode(plan.getCode());// 基础单据编码
            purchaseOrderVO.setBaseOrderType(plan.getOrderType());// 基础单据类型

            purchaseOrderVO.setWholeDiscount(new BigDecimal(100));// 整单折扣
            purchaseOrderVO.setWholeDiscountAmount(new BigDecimal(0));// 整单优惠金额
            purchaseOrderVO.setRealAmountTotal(amountTotal);// 实际金额合计
            purchaseOrderVO.setNotaxRealAmountTotal(notaxAmountTotal);// 不含税金额合计
            purchaseOrderVO.setTaxAmountTotal(taxAmountTotal);// 税额合计
            purchaseOrderVO.setProfitRate(new BigDecimal(0)); // 利润合计
            purchaseOrderVO.setNotaxProfitRate(new BigDecimal(0));// 不含税利润合计
            purchaseOrderVO.setProfitRate(new BigDecimal(0));// 利润率
            purchaseOrderVO.setProfitTotal(new BigDecimal(0));// 利润合计
            purchaseOrderVO.setNotaxProfitTotal(new BigDecimal(0));// 不含税利润合计
            purchaseOrderVO.setNotaxProfitRate(new BigDecimal(0));// 不含税利润率
            // 采购人ID 获取系统默认的
            ManageConfig manageConfig = manageConfigService.getManageConfig(userVO);

            if(manageConfig.getPurchaserId() == null){// 系统默认不存在，取采购计划的
                purchaseOrderVO.setPurchaserId(plan.getPannerId());
                purchaseOrderVO.setPurchaserCode(plan.getPannerCode());
                purchaseOrderVO.setPurchaserName(plan.getPannerName());

            } else {
                purchaseOrderVO.setPurchaserId(manageConfig.getPurchaserId());
                purchaseOrderVO.setPurchaserCode(manageConfig.getPurchaserCode());
                purchaseOrderVO.setPurchaserName(manageConfig.getPurchaserName());

            }

            // 配送和结算
            PurchaseOrderOtherVO purchaseOrderOtherVO = new PurchaseOrderOtherVO();

            purchaseOrderOtherVO.setPlanId(planId);
            purchaseOrderOtherVO.setCarriageMode(carriageMode);
            purchaseOrderOtherVO.setPlanCode(plan.getCode());
            purchaseOrderVO.setPurchaseOrderOtherVO(purchaseOrderOtherVO);

            String code = orderService.addOrderByPlan(userVO, purchaseOrderVO);
            orderCodes.add(code);
        }

        // 生成订单后 状态改为已订购(待收货)
        planMapper.updateStatus(planId, PurchaseStatus.WAIT_RECEIVE.getStatus());
        planDetailMapper.updateStatusByPlanId(planId, PurchaseStatus.WAIT_RECEIVE.getStatus());

        return orderCodes;
    }

    @Override
    public List<HistoricalUnitPriceVO> selectHistoryUnitPrice(Long goodsId, Integer limit) {
        return planMapper.selectHistoryUnitPrice(goodsId, limit);
    }

    /**
     * 导出
     */
    @Override
    public void exportDetail(OutputStream output, Long planId, UserVO userVO) throws Exception {
        PurchasePlanVO purchasePlanVO = planDetailMapper.selectDetailByPlanId(planId);
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("goodsCode", "商品编码");
        map.put("goodsGenericName", "商品通用名称");
        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");
        map.put("manufacturer", "生产厂商");
        map.put("quantity", "数量");
        map.put("unitPrice", "单价");
        map.put("amount", "金额");
        map.put("taxRate", "税率");
        map.put("notaxPrice", "不含税单价");
        map.put("notaxAmount", "不含税金额");
        map.put("taxAmout", "税额");
        map.put("supplierCode", "供货位单位编码");
        map.put("supplierName", "供货单位名称");
        map.put("carriageName", "承运方式");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // 格式化下日期


        List<TwoTuple<String,String>> subHeadingList  = new ArrayList<>();
        subHeadingList.add(new TwoTuple<>(" 计划单号",purchasePlanVO.getCode()));
        subHeadingList.add(new TwoTuple<>(" 计划日期",format.format(purchasePlanVO.getPlanDate())));
        subHeadingList.add(new TwoTuple<>(" 采购人员",purchasePlanVO.getPannerName()));
        subHeadingList.add(new TwoTuple<>(" 备注",purchasePlanVO.getRemark()));

        Map<String, String> totalMap = new HashMap<>();
        totalMap.put("quantity",purchasePlanVO.getQuantityTotal()+"");
        totalMap.put("amount",purchasePlanVO.getAmountTotal() + "");
        totalMap.put("notaxAmount",purchasePlanVO.getNotaxAmountTotal() + "");
        totalMap.put("taxAmout",purchasePlanVO.getTaxAmountTotal()+"");
        excelComponent.exportExcel(output, userVO.getEnterpriseName(), "采购计划单", subHeadingList, map, purchasePlanVO.getPlanDetails(),totalMap);


    }

    @Override
    public List<SupplierVO> getBasicSupplier(UserVO userVO) {

        Long eId = userVO.getEnterpriseId();
        Map<String, Object> param = new HashMap<>();
        param.put("status", EnableStatus.ENABLE.getStatus());
        param.put("enterpriseId", eId);
        return planMapper.selectSupplier(param);
    }

    /**
     * 调用缺配单生成采购计划
     *
     * @param lackIds
     * @return
     */
    @Override
    public List<PurchasePlanGoodsVO> getByDistrLack(String lackIds, UserVO userVO) {

        // 1.获取所有的缺配单明细
        String[] ids = lackIds.split(",");
        List<PurchasePlanGoodsVO> goodsVOList = distrLackDetailMapper.getByLackIdGroupByGoodsId(ids);

        // 2. 组装采购计划商品数据
        for (PurchasePlanGoodsVO goodsVO: goodsVOList){

            // 默认单价税率和供货单位
            getPriceTaxRateAndSupplier(goodsVO,userVO);

        }
        return goodsVOList;
    }

    @Override
    public List<PurchasePlanGoodsVO> intelligentPurchase(IntellectPurchaseReqVO intellectReqVO, UserVO userVO) {

        Map<String,Object> param = new HashMap<>();
        param.put("enterpriseId",userVO.getEnterpriseId());
        param.put("status",EnableStatus.ENABLE.getStatus());

        List<PurchasePlanGoodsVO> planDetailVOList = new ArrayList<>();

        if(intellectReqVO.getSafety()){// 安全库存
            // 取出 所有设置安全库存的商品
            List<PurchasePlanGoodsVO> safetyDtlList  =   planMapper.getPlanGoodsBySafety(param);

            for (PurchasePlanGoodsVO safetyVO: safetyDtlList) {

                if(safetyVO.getUsableQuantity() == null ){
                    safetyVO.setQuantity(safetyVO.getNeedQuantity());
                    planDetailVOList.add(safetyVO);

                }  else  if (safetyVO.getNeedQuantity().compareTo(safetyVO.getUsableQuantity()) > 0){
                    safetyVO.setQuantity(safetyVO.getNeedQuantity().subtract(safetyVO.getUsableQuantity()));
                    planDetailVOList.add(safetyVO);
                }
            }

        }

        List<PurchasePlanGoodsVO> saleList = null;// 销售list
        List<PurchasePlanGoodsVO> saleReturnList = null;// 销退list
        List<PurchasePlanGoodsVO> distrOutList = null;
        List<PurchasePlanGoodsVO> distrReturnList = null;
        if (intellectReqVO.getDynamicStock() ){// 动态库存

            // 取出时间范围内 销售 的商品
            param.put("parentId",userVO.getEnterpriseId());
            // {30}天 之前
            Date date = DateUtils.addDay(new Date(), 0 - intellectReqVO.getDynamicStockDays());
            param.put("time",date);
            param.put("saleType",0);// 销售 - 自营店
            saleList =  planMapper.getPlanGoodsBySale(param);
            param.put("saleType",1);// 销退 - 自营店
            saleReturnList =  planMapper.getPlanGoodsBySale(param);

            distrOutList =  planMapper.getPlanGoodsByDistrOut(param);// 配出 到加盟店

            distrReturnList = planMapper.getPlanGoodsByDistrReturnIn(param); // 加盟店退回

            reduceReturnQuantity(saleList,saleReturnList,true,intellectReqVO,planDetailVOList);
            reduceReturnQuantity(distrOutList,distrReturnList,true, intellectReqVO, planDetailVOList);

        }

        if (intellectReqVO.getLack()){//按照缺断货数量分析

            param.put("parentId",userVO.getEnterpriseId());

            // 30天 之前
            Date date = DateUtils.addDay(new Date(), 0 - intellectReqVO.getLackDays());
            param.put("time",date);
            param.put("saleType",0);// 销售 - 自营店

            if( !(intellectReqVO.getDynamicStock() && intellectReqVO.getDynamicStockDays().compareTo(intellectReqVO.getLackDays()) == 0)){
                saleList =  planMapper.getPlanGoodsBySale(param);
                param.put("saleType",1);// 销退 - 自营店
                saleReturnList =  planMapper.getPlanGoodsBySale(param);
                distrOutList =  planMapper.getPlanGoodsByDistrOut(param);// 配出 到加盟店

                distrReturnList = planMapper.getPlanGoodsByDistrReturnIn(param); // 加盟店退回

            }


            reduceReturnQuantity(saleList,saleReturnList,false,intellectReqVO,planDetailVOList);
            reduceReturnQuantity(distrOutList,distrReturnList,false, intellectReqVO, planDetailVOList);


        }


        // 去重
        ArrayList<PurchasePlanGoodsVO> dataList = planDetailVOList.
                stream().
                collect(Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(
                                Comparator.comparingLong(PurchasePlanGoodsVO::getId)
                        )),
                ArrayList::new));

        // 数量重新赋值
        if(dataList.size() < planDetailVOList.size()){//有去重数据时才重新赋值

            for (PurchasePlanGoodsVO dtlVO:dataList){


                for (PurchasePlanGoodsVO tmpVO:planDetailVOList) {

                    if(tmpVO.getId().equals( dtlVO.getId())){// 商品ID相等

                        BigDecimal subtract = dtlVO.getQuantity().subtract(tmpVO.getQuantity());
                        if(subtract.compareTo(BigDecimal.ZERO) > 0){
                            dtlVO.setQuantity(subtract);
                        }
                    }

                }
            }
            // 赋值单价，税率，供货单位
            //getPriceTaxRateAndSupplier(dtlVO,userVO);

        }



        List<PurchaseOrderDetail> orderDetailList = planMapper.getOnPassage(param);

        List<PurchasePlanGoodsVO> removeList = new ArrayList<>();
        for (PurchasePlanGoodsVO dtlVO:dataList){

            for (PurchaseOrderDetail orderDetail: orderDetailList){
                if(orderDetail.getGoodsId().equals( dtlVO.getId())){// 商品ID相等

                    BigDecimal subtract = dtlVO.getQuantity().subtract(orderDetail.getQuantity());
                    if(subtract.compareTo(BigDecimal.ZERO) > 0){
                        dtlVO.setQuantity(subtract);
                    } else {// 在途库存 大于 预定采购计划的数量
                        removeList.add(dtlVO);
                    }

                }
            }
        }

        //先移除掉
        dataList.removeAll(removeList);

        // 数量重新赋值
        for (PurchasePlanGoodsVO dtlVO:dataList){

            // 赋值单价，税率，供货单位
            getPriceTaxRateAndSupplier(dtlVO,userVO);

        }

        return dataList;
    }

    /**
     * 减去退回的数量并计算需要的数量
     * @param saleList
     * @param saleReturnList
     * @param lack
     * @param intellectReqVO
     * @param planDetailVOList
     */
    private void reduceReturnQuantity(List<PurchasePlanGoodsVO> saleList, List<PurchasePlanGoodsVO> saleReturnList, boolean lack, IntellectPurchaseReqVO intellectReqVO, List<PurchasePlanGoodsVO> planDetailVOList) {

        for (PurchasePlanGoodsVO saleReturnVO :saleReturnList) {

            for (PurchasePlanGoodsVO saleVO:saleList) {

                if(saleReturnVO.getId().equals(saleVO.getId())){

                    saleVO.setNeedQuantity(saleVO.getNeedQuantity().subtract(saleReturnVO.getNeedQuantity()));
                }
            }
        }


        if(lack){
            for(PurchasePlanGoodsVO  saleVO: saleList){

                // 预计{15}天的销售数量
                BigDecimal needQuantity = saleVO.getNeedQuantity().multiply(
                        new BigDecimal(intellectReqVO.getDynamicStockDays() / intellectReqVO.getUndynamicStockDays()));

                if(saleVO.getUsableQuantity().compareTo(needQuantity) < 0){
                    saleVO.setQuantity(needQuantity.subtract(saleVO.getUsableQuantity()));
                    planDetailVOList.add(saleVO);
                }


            }
        } else {

            for(PurchasePlanGoodsVO  saleVO: saleList){

                if(saleVO.getUsableQuantity().compareTo(BigDecimal.ZERO) <= 0){
                    saleVO.setQuantity(saleVO.getNeedQuantity());
                    planDetailVOList.add(saleVO);
                }
            }
        }

    }

    /**
     * 获取商品的默认税率，单价，供货单位和 商品供货单位列表
     * @param goodsVO
     * @param userVO
     */
    private void getPriceTaxRateAndSupplier(PurchasePlanGoodsVO goodsVO, UserVO userVO) {

        Long enterpriseId = userVO.getEnterpriseId();
        Long parentId = userVO.getParentId();

        Map<String, Object> map = new HashMap<>();

        map.put("enterpriseId", userVO.getEnterpriseId());
        map.put("status", EnableStatus.ENABLE.getStatus());
        map.put("managementMode", goodsVO.getManagementMode());

        Long priceOrderId = null;

        // 1. 获取企业默认价格清单
        PriceOrder priceOrder = priceOrderMapper.selectByCodeAndEnterpriceIdAndParentId(SysType.SYSTEM.getCode(), enterpriseId, parentId);// 0000 总部

        if(priceOrder != null){
            priceOrderId = priceOrder.getId();
            PriceOrderDetail priceOrderDetail = priceOrderDetailMapper.selectByEnterpriseIdAndPriceOrderIdAndGoodId(enterpriseId, priceOrderId, goodsVO.getId());

            if(priceOrderDetail != null){
                goodsVO.setUnitPrice(priceOrderDetail.getPurPrice() == null?BigDecimal.ZERO:priceOrderDetail.getPurPrice());


                if(priceOrderDetail.getPurTaxRateId() == null){// 税率为空
                    GoodsDefTaxRateVO goodsDefTaxRateInfo = commonComponent.getGoodsDefTaxRateInfo(enterpriseId, goodsVO.getId(), TaxRateType.PURCHASE.getType());
                    goodsVO.setTaxRateId(goodsDefTaxRateInfo.getTaxRateId());
                    goodsVO.setTaxRate(goodsDefTaxRateInfo.getTaxRate());

                } else {
                    goodsVO.setTaxRateId(priceOrderDetail.getPurTaxRateId());
                    goodsVO.setTaxRate(priceOrderDetail.getPurTaxRate());
                }

                if(priceOrderDetail.getSupplierId() != null){
                    goodsVO.setSupplierId(priceOrderDetail.getSupplierId());

                    Supplier supplier = supplierMapper.selectByPrimaryKey(priceOrderDetail.getSupplierId());
                    if(supplier != null){
                        goodsVO.setSupplierCode(supplier.getCode());
                        goodsVO.setSupplierName(supplier.getName());
                    }
                }

            } else{

                // 如果没有记录，单价为0, 税率获取默认税率
                goodsVO.setUnitPrice(BigDecimal.ZERO);

                GoodsDefTaxRateVO goodsDefTaxRateInfo = commonComponent.getGoodsDefTaxRateInfo(enterpriseId, goodsVO.getId(), TaxRateType.PURCHASE.getType());
                goodsVO.setTaxRateId(goodsDefTaxRateInfo.getTaxRateId());
                goodsVO.setTaxRate(goodsDefTaxRateInfo.getTaxRate());

            }

        } else {
            // 如果没有记录，单价为0, 税率获取默认税率
            goodsVO.setUnitPrice(BigDecimal.ZERO);

            GoodsDefTaxRateVO goodsDefTaxRateInfo = commonComponent.getGoodsDefTaxRateInfo(enterpriseId, goodsVO.getId(), TaxRateType.PURCHASE.getType());
            goodsVO.setTaxRateId(goodsDefTaxRateInfo.getTaxRateId());
            goodsVO.setTaxRate(goodsDefTaxRateInfo.getTaxRate());

        }

        // 2. 获取商品经营范围内的供货单位
        if( goodsVO.getManagementScopeId() != null){
            map.put("scopeId",goodsVO.getManagementScopeId());
            List<SupplierVO> supplierVOS = planMapper.selectSupplier(map);

            // 如果默认供应商 不供该品种了，移除默认供应商
            Boolean flag = Boolean.FALSE;// 默认不供
            if(goodsVO.getSupplierId() != null){
                for (SupplierVO supplierVO:supplierVOS) {
                    if(supplierVO.getId().equals(goodsVO.getSupplierId())){
                        flag = Boolean.TRUE;
                    }
                }
            }
            if(!flag){
                goodsVO.setSupplierId(null);
                goodsVO.setSupplierCode(null);
                goodsVO.setSupplierName(null);
            }

            goodsVO.setSupplierVOList(supplierVOS);
        }

    }

    @Override
    public List<PurchasePlanGoodsVO> getGoodsList(String param, UserVO userVO) {
        // 查询商品

        Map<String, Object> map = new HashMap<>();
        
        Long ownerId = 0L;
        if(userVO .getChainType() == ChainType.Selfoperatedshop.getCode()){
        	ownerId = userVO.getParentId();
        }else {
        	ownerId = userVO.getEnterpriseId();
        }
        Long enterpriseId = (userVO .getChainType() == ChainType.Headquarters.getCode() ? userVO.getEnterpriseId() : userVO.getParentId());
        map.put("enterpriseId", enterpriseId);
        map.put("ownerId", ownerId);
        map.put("param", param);
        map.put("status", EnableStatus.ENABLE.getStatus());
        map.put("goodsNature",GoodsNature.GOODSNATURE_B.getCode());// 非拆零商品

        List<PurchasePlanGoodsVO> goodsList =  planMapper.getGoodsList(map);

        for (PurchasePlanGoodsVO goodsVO: goodsList) {
            // 查询供货单位、采购单价和采购税率
            getPriceTaxRateAndSupplier(goodsVO,userVO);
            goodsVO.setQuantity(BigDecimal.ONE);
        }

        return goodsList;
    }

    @Override
    public List<SupplierVO> getBasicSupplierByBusinessScope(UserVO userVO, Long goodsId) {


        List<SupplierVO>  supplierVOList = new ArrayList<>();
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        if(goods == null){
            throw  new BusinessException("商品未搜索到，商品ID:" + goodsId);
        }
        GoodsBusiness goodsBusiness = goodsBusinessMapper.selectByGoodsId(goodsId);

        if(goods.getManagementScopeId() != null){

            Long eId = userVO.getEnterpriseId();
            Map<String, Object> param = new HashMap<>();
            param.put("status", EnableStatus.ENABLE.getStatus());
            param.put("enterpriseId", eId);
            param.put("scopeId",goods.getManagementScopeId());
            param.put("managementMode", goodsBusiness.getManagementMode());

            return planMapper.selectSupplier(param);
        }

        return supplierVOList;
    }

    @Override
    public DraftCacheVO<PurchasePlanDraftCacheVO> saveDraftCache(UserVO userVO, DraftCacheVO<PurchasePlanDraftCacheVO> draftCache){

        String redisKeyValue = draftCache.getId();

        DraftCacheVO<PurchasePlanDraftCacheVO> draftCacheVO = new DraftCacheVO();

        draftCacheVO.setOrderCode(OrderRule.PURCHASE_PLAN.getCodePrefix());

        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());

        draftCacheVO.setOrderData(draftCache.getOrderData());
        draftCacheVO.setId(redisKeyValue);
        PurchasePlanDraftCacheVO orderData = draftCacheVO.getOrderData();
        Long pannerId = orderData.getPannerId();

        User panner = userComponent.findUserByUserId(pannerId);
        if (panner == null) {
            throw new BusinessException(SysCode.FAIL.getCode(), "采购计划人员为空，请检查id = " + pannerId);
        }
        orderData.setPannerCode(panner.getCode());
        orderData.setPannerName(panner.getName());
        draftCacheVO = redisComponent.saveDraftCacheVO(draftCacheVO);

        return draftCacheVO;

    }

    @Override
    public void removeDraftCach(Long enterpriseId, String type, String redisKeyValue){

        redisComponent.removeDraftCacheVO(enterpriseId,type,redisKeyValue);
    }

    @Override
    public List<DraftCacheVO> getDraftCacheVO(UserVO userVO){
        Class<PurchasePlanDraftCacheVO> clazz = PurchasePlanDraftCacheVO.class;
        return redisComponent.getDraftCacheVO(userVO.getEnterpriseId(),OrderRule.PURCHASE_PLAN.getCodePrefix(),clazz );
    }

    @Override
    public Page<List<PurchasePlanGoodsVO>> getGoodsListPage(Page<List<PurchasePlanGoodsVO>> page, String param, UserVO userVO) {

        // 查询商品

        Map<String, Object> map = new HashMap<>();
        Long enterpriseId = (userVO .getChainType() == ChainType.Headquarters.getCode() ? userVO.getEnterpriseId() : userVO.getParentId());
        map.put("enterpriseId", enterpriseId);
        map.put("param", param);
        map.put("status", EnableStatus.ENABLE.getStatus());
        map.put("goodsNature",GoodsNature.GOODSNATURE_B.getCode());// 非拆零商品

        map.put("pageStart",page.getStart());
        map.put("pageSize",page.getPageSize());

        List<PurchasePlanGoodsVO> goodsList =  planMapper.getGoodsListPage(map);

        Integer count =  planMapper.getGoodsListPageCount(map);

        page.setResult(goodsList);
        page.setTotalRecord(count);

        for (PurchasePlanGoodsVO goodsVO: goodsList) {
            // 查询供货单位、采购单价和采购税率
            getPriceTaxRateAndSupplier(goodsVO,userVO);
            goodsVO.setQuantity(BigDecimal.ONE);
        }

        return page;


    }
}
