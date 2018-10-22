package com.rograndec.feijiayun.chain.business.distr.parent.entity;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnInFormVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.transfer.DistrReturnInParam;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReturnStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * saas_distr_return_in
 *
 *
 * @author zhaiwei
 *
 * 2017-10-08
 */
public class DistrReturnIn implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 单据类型（5425）
     */
    @ApiModelProperty(value = "单据类型（5425）")
    private Integer orderType;

    /**
     * 配进入库单号
     */
    @ApiModelProperty(value = "配进入库单号")
    private String code;

    /**
     * 入库日期
     */
    @ApiModelProperty(value = "入库日期")
    private Date returnInDate;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID")
    private Long baseOrderId;

    /**
     * 基础单据类型
     */
    @ApiModelProperty(value = "基础单据类型")
    private Integer baseOrderType;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "基础单据编码")
    private String baseOrderCode;

    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期")
    private Date baseOrderDate;

    /**
     * 配进订单ID
     */
    @ApiModelProperty(value = "配进订单ID")
    private Long distrOrderId;

    /**
     * 要货单位ID
     */
    @ApiModelProperty(value = "要货单位ID")
    private Long requestUnitId;

    /**
     * 要货单位编码
     */
    @ApiModelProperty(value = "要货单位编码")
    private String requestUnitCode;

    /**
     * 要货单位名称
     */
    @ApiModelProperty(value = "要货单位名称")
    private String requestUnitName;


    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;

    /**
     * 入库人员ID
     */
    @ApiModelProperty(value = "入库人员ID")
    private Long storageManId;

    /**
     * 入库人员编码
     */
    @ApiModelProperty(value = "入库人员编码")
    private String storageManCode;

    /**
     * 入库人员名称
     */
    @ApiModelProperty(value = "入库人员名称")
    private String storageManName;

    /**
     * 流通监管码
     */
    @ApiModelProperty(value = "流通监管码")
    private String flowCode;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;

    /**
     * 金额合计（整单优惠前的金额合计）
     */
    @ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;

    /**
     * 整单优惠金额
     */
    @ApiModelProperty(value = "整单优惠金额")
    private BigDecimal wholeDiscountAmount;

    /**
     * 实际金额合计
     */
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 引用单据类型
     */
    private Integer referenceOrderType;

    /**
     * 引用单据id
     */
    private Long referenceOrderId;

    public static List<Long> getIds(List<DistrReturnIn> distrReturnIns){


        List<Long> ids = distrReturnIns.stream().map(dris -> dris.getId()).collect(Collectors.toList());

        return ids;
    }

    public static DistrReturnIn afterGenerateDetailSetReturnInEnd(DistrReturnIn distrReturnIn,List<DistrReturnInDetail> distrReturnInDetails){

        BigDecimal notaxRealAmountTotal = new BigDecimal(0);

        BigDecimal taxAmountTotal = new BigDecimal(0);

        for(DistrReturnInDetail drid : distrReturnInDetails){
            notaxRealAmountTotal = notaxRealAmountTotal.add(drid.getNotaxRealAmount());
            taxAmountTotal = taxAmountTotal.add(drid.getTaxAmount());

        }

        /**
         * 不含税金额合计
         */
        distrReturnIn.setNotaxRealAmountTotal(notaxRealAmountTotal);

        /**
         * 税额合计
         */
        distrReturnIn.setTaxAmountTotal(taxAmountTotal);

        return distrReturnIn;
    }

    public static DistrReturnIn afterGenerateDetailSetReturnIn(DistrReturnIn distrReturnIn,List<DistrReturnInDetail> distrReturnInDetails){
        BigDecimal amountTotal = new BigDecimal(0);

        for(DistrReturnInDetail distrReturnInDetail : distrReturnInDetails){
            amountTotal = amountTotal.add(distrReturnInDetail.getAmount()).setScale(2,BigDecimal.ROUND_HALF_UP);
        }

        /**
         * 金额合计（整单优惠前的金额合计）
         */
        distrReturnIn.setAmountTotal(amountTotal);



        /**
         * 实际金额合计
         */
        BigDecimal realAmountTotal = calcTotalRealAmount(
                distrReturnIn.getAmountTotal()
                , distrReturnIn.getWholeDiscount()
                , distrReturnIn.getWholeDiscountAmount()
        );
        distrReturnIn.setRealAmountTotal(realAmountTotal);

        return distrReturnIn;

    }

    public static DistrReturnIn getDistrReturnIn(DistrReturnInParam distrReturnInParam) throws Exception {

        UserVO userVO = distrReturnInParam.getUserVO();

        DistrReturnInFormVO distrReturnInFormVO = distrReturnInParam.getDistrReturnInFormVO();

        String code = distrReturnInParam.getCode();

        DistrReturnCheck distrReturnCheck = distrReturnInParam.getDistrReturnCheck();

        User storageMan = distrReturnInParam.getStorageMan();

        DistrReturnIn distrReturnIn = new DistrReturnIn();


        /**
         * 企业ID
         */
        distrReturnIn.setEnterpriseId(userVO.getEnterpriseId());
        if(userVO.getChainType().equals(ChainType.Headquarters.getCode())){
            distrReturnIn.setParentId(0L);
        }else {
            distrReturnIn.setParentId(userVO.getParentId());
        }

        /**
         * 单据类型（5425）
         */
        distrReturnIn.setOrderType(OrderRule.DISTR_RETURN_IN.getType());

        /**
         * 配进入库单号
         */
        distrReturnIn.setCode(code);

        /**
         * 入库日期
         */
        distrReturnIn.setReturnInDate(distrReturnInFormVO.getReturnInDate());

        /**
         * 基础单据ID
         */
        distrReturnIn.setBaseOrderId(distrReturnInFormVO.getCheckId());

        /**
         * 基础单据类型
         */
        distrReturnIn.setBaseOrderType(distrReturnCheck.getOrderType());

        /**
         * 基础单据编码
         */
        distrReturnIn.setBaseOrderCode(distrReturnCheck.getCode());

        /**
         * 基础单据日期
         */
        distrReturnIn.setBaseOrderDate(distrReturnCheck.getCheckDate());

        /**
         * 配进订单ID
         */
        distrReturnIn.setDistrOrderId(distrReturnCheck.getNoticeId());

        /**
         * 要货单位ID
         */
        distrReturnIn.setRequestUnitId(distrReturnCheck.getRequestUnitId());

        /**
         * 要货单位编码
         */
        distrReturnIn.setRequestUnitCode(distrReturnCheck.getRequestUnitCode());

        /**
         * 要货单位名称
         */
        distrReturnIn.setRequestUnitName(distrReturnCheck.getRequestUnitName());

        /**
         *配货类型（0-总部配送；3-分店间调剂；4-直调配送）
         */
        distrReturnIn.setDistrType(distrReturnCheck.getDistrType());

        /**
         * 入库人员ID
         */
        distrReturnIn.setStorageManId(storageMan.getId());

        /**
         * 入库人员编码
         */
        distrReturnIn.setStorageManCode(storageMan.getCode());

        /**
         * 入库人员名称
         */
        distrReturnIn.setStorageManName(storageMan.getName());

        /**
         * 流通监管码
         */
        distrReturnIn.setFlowCode(distrReturnInFormVO.getFlowCode());

        /**
         * 数量合计
         */
        distrReturnIn.setQuantityTotal(distrReturnCheck.getQuantityTotal());

        /**
         * 品种数量
         */
        distrReturnIn.setVarietiesQuantity(distrReturnCheck.getVarietiesQuantity());



        /**
         * 整单折扣（%）
         */
        distrReturnIn.setWholeDiscount(distrReturnInFormVO.getWholeDiscount());

        /**
         * 整单优惠金额
         */
        distrReturnIn.setWholeDiscountAmount(distrReturnInFormVO.getWholeDiscountAmount());


        /**
         * 状态
         */
        distrReturnIn.setStatus(DistrReturnStatus.WAIT_BILL);

        /**
         * 不含税金额合计 第一次保存时为空 ,明细行生成后再从明细行取出汇总
         */
        distrReturnIn.setNotaxRealAmountTotal(new BigDecimal(0));

        /**
         * 税额合计 第一次保存时为空 ,明细行生成后再从明细行取出汇总
         */
        distrReturnIn.setTaxAmountTotal(new BigDecimal(0));

        UserEnterpriseUtils.setUserCreateOrModify(distrReturnIn,userVO,true);

        return distrReturnIn;

    }


    /**
     * 实际金额合计
     * @return
     */
    public static BigDecimal calcTotalRealAmount(BigDecimal amountTotal ,BigDecimal wholeDiscount,BigDecimal wholeDiscountAmount){
        //整单折扣金额
        BigDecimal divide = wholeDiscount.divide(new BigDecimal(100));
        BigDecimal wholeDiscountTotal = amountTotal.multiply(divide);
        BigDecimal realAmountTotal = wholeDiscountTotal
                .subtract(wholeDiscountAmount)
                .setScale(2,BigDecimal.ROUND_HALF_UP);
        return realAmountTotal;
    }

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 单据类型（5425）
     * @return order_type 单据类型（5425）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5425）
     * @param orderType 单据类型（5425）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 配进入库单号
     * @return code 配进入库单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 配进入库单号
     * @param code 配进入库单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 入库日期
     * @return return_in_date 入库日期
     */
    public Date getReturnInDate() {
        return returnInDate;
    }

    /**
     * 入库日期
     * @param returnInDate 入库日期
     */
    public void setReturnInDate(Date returnInDate) {
        this.returnInDate = returnInDate;
    }

    /**
     * 基础单据ID
     * @return base_order_id 基础单据ID
     */
    public Long getBaseOrderId() {
        return baseOrderId;
    }

    /**
     * 基础单据ID
     * @param baseOrderId 基础单据ID
     */
    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    /**
     * 基础单据类型
     * @return base_order_type 基础单据类型
     */
    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    /**
     * 基础单据类型
     * @param baseOrderType 基础单据类型
     */
    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    /**
     * 基础单据编码
     * @return base_order_code 基础单据编码
     */
    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    /**
     * 基础单据编码
     * @param baseOrderCode 基础单据编码
     */
    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode == null ? null : baseOrderCode.trim();
    }

    /**
     * 基础单据日期
     * @return base_order_date 基础单据日期
     */
    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    /**
     * 基础单据日期
     * @param baseOrderDate 基础单据日期
     */
    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    /**
     * 配进订单ID
     * @return distr_order_id 配进订单ID
     */
    public Long getDistrOrderId() {
        return distrOrderId;
    }

    /**
     * 配进订单ID
     * @param distrOrderId 配进订单ID
     */
    public void setDistrOrderId(Long distrOrderId) {
        this.distrOrderId = distrOrderId;
    }

    /**
     * 要货单位ID
     * @return request_unit_id 要货单位ID
     */
    public Long getRequestUnitId() {
        return requestUnitId;
    }

    /**
     * 要货单位ID
     * @param requestUnitId 要货单位ID
     */
    public void setRequestUnitId(Long requestUnitId) {
        this.requestUnitId = requestUnitId;
    }

    /**
     * 要货单位编码
     * @return request_unit_code 要货单位编码
     */
    public String getRequestUnitCode() {
        return requestUnitCode;
    }

    /**
     * 要货单位编码
     * @param requestUnitCode 要货单位编码
     */
    public void setRequestUnitCode(String requestUnitCode) {
        this.requestUnitCode = requestUnitCode == null ? null : requestUnitCode.trim();
    }

    /**
     * 要货单位名称
     * @return request_unit_name 要货单位名称
     */
    public String getRequestUnitName() {
        return requestUnitName;
    }

    /**
     * 要货单位名称
     * @param requestUnitName 要货单位名称
     */
    public void setRequestUnitName(String requestUnitName) {
        this.requestUnitName = requestUnitName == null ? null : requestUnitName.trim();
    }

    public Integer getDistrType() {
        return distrType;
    }


    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    /**
     * 入库人员ID
     * @return storage_man_id 入库人员ID
     */
    public Long getStorageManId() {
        return storageManId;
    }

    /**
     * 入库人员ID
     * @param storageManId 入库人员ID
     */
    public void setStorageManId(Long storageManId) {
        this.storageManId = storageManId;
    }

    /**
     * 入库人员编码
     * @return storage_man_code 入库人员编码
     */
    public String getStorageManCode() {
        return storageManCode;
    }

    /**
     * 入库人员编码
     * @param storageManCode 入库人员编码
     */
    public void setStorageManCode(String storageManCode) {
        this.storageManCode = storageManCode == null ? null : storageManCode.trim();
    }

    /**
     * 入库人员名称
     * @return storage_man_name 入库人员名称
     */
    public String getStorageManName() {
        return storageManName;
    }

    /**
     * 入库人员名称
     * @param storageManName 入库人员名称
     */
    public void setStorageManName(String storageManName) {
        this.storageManName = storageManName == null ? null : storageManName.trim();
    }

    /**
     * 流通监管码
     * @return flow_code 流通监管码
     */
    public String getFlowCode() {
        return flowCode;
    }

    /**
     * 流通监管码
     * @param flowCode 流通监管码
     */
    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode == null ? null : flowCode.trim();
    }

    /**
     * 数量合计
     * @return quantity_total 数量合计
     */
    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    /**
     * 数量合计
     * @param quantityTotal 数量合计
     */
    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    /**
     * 品种数量
     * @return varieties_quantity 品种数量
     */
    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    /**
     * 品种数量
     * @param varietiesQuantity 品种数量
     */
    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @return amount_total 金额合计（整单优惠前的金额合计）
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @param amountTotal 金额合计（整单优惠前的金额合计）
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 整单折扣（%）
     * @return whole_discount 整单折扣（%）
     */
    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    /**
     * 整单折扣（%）
     * @param wholeDiscount 整单折扣（%）
     */
    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    /**
     * 整单优惠金额
     * @return whole_discount_amount 整单优惠金额
     */
    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    /**
     * 整单优惠金额
     * @param wholeDiscountAmount 整单优惠金额
     */
    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    /**
     * 实际金额合计
     * @return real_amount_total 实际金额合计
     */
    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    /**
     * 实际金额合计
     * @param realAmountTotal 实际金额合计
     */
    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    /**
     * 不含税金额合计
     * @return notax_real_amount_total 不含税金额合计
     */
    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    /**
     * 不含税金额合计
     * @param notaxRealAmountTotal 不含税金额合计
     */
    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
    }

    /**
     * 税额合计
     * @return tax_amount_total 税额合计
     */
    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    /**
     * 税额合计
     * @param taxAmountTotal 税额合计
     */
    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    /**
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", orderType=").append(orderType);
        sb.append(", code=").append(code);
        sb.append(", returnInDate=").append(returnInDate);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", baseOrderType=").append(baseOrderType);
        sb.append(", baseOrderCode=").append(baseOrderCode);
        sb.append(", baseOrderDate=").append(baseOrderDate);
        sb.append(", distrOrderId=").append(distrOrderId);
        sb.append(", requestUnitId=").append(requestUnitId);
        sb.append(", requestUnitCode=").append(requestUnitCode);
        sb.append(", requestUnitName=").append(requestUnitName);
        sb.append(", distrType=").append(distrType);
        sb.append(", storageManId=").append(storageManId);
        sb.append(", storageManCode=").append(storageManCode);
        sb.append(", storageManName=").append(storageManName);
        sb.append(", flowCode=").append(flowCode);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", wholeDiscount=").append(wholeDiscount);
        sb.append(", wholeDiscountAmount=").append(wholeDiscountAmount);
        sb.append(", realAmountTotal=").append(realAmountTotal);
        sb.append(", notaxRealAmountTotal=").append(notaxRealAmountTotal);
        sb.append(", taxAmountTotal=").append(taxAmountTotal);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

    public Integer getReferenceOrderType() {
        return referenceOrderType;
    }

    public void setReferenceOrderType(Integer referenceOrderType) {
        this.referenceOrderType = referenceOrderType;
    }

    public Long getReferenceOrderId() {
        return referenceOrderId;
    }

    public void setReferenceOrderId(Long referenceOrderId) {
        this.referenceOrderId = referenceOrderId;
    }
}