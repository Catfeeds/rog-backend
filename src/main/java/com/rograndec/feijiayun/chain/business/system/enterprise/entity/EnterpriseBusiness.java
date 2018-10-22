package com.rograndec.feijiayun.chain.business.system.enterprise.entity;

import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.constant.DistrPriceType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "enterpriseBusiness", description = "企业业务属性对象")
public class EnterpriseBusiness implements Serializable {
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
     * 结算方式（0-零售缴款；1-应收账款）
     */
	@ApiModelProperty(value = "结算方式ID（0-零售缴款；1-应收账款）")
    private Integer settlementMode;
	
	/**
     * 结算方式（0-零售缴款；1-应收账款）
     */
	@ApiModelProperty(value = "结算方式名称")
    private String settlementModeName;

    /**
     * 配货价格类型（0-价格清单；1-成本价；2-成本加成）
     */
	@ApiModelProperty(value = "配货价格类型（0-价格清单；1-成本价；2-成本加成）")
    private Integer distrPriceType;

	/**
     * 配货价格类型名称
     */
	@ApiModelProperty(value = "配货价格类型名称")
    private String distrPriceTypeName;
	
    /**
     * 配货价格清单ID
     */
	@ApiModelProperty(value = "配货价格清单ID")
    private Long distrPriceOrderId;

    /**
     * 配货价格清单名称
     */
	@ApiModelProperty(value = "配货价格清单名称")
    private String distrPriceOrderName;

    /**
     * 成本加成加点率
     */
	@ApiModelProperty(value = "成本加成加点率")
    private BigDecimal addRate;

    /**
     * 付款条款（0-现结；1-账期）
     */
	@ApiModelProperty(value = "付款条款（0-现结；1-账期）")
    private Integer paymentProvision;
	
	/**
     * 付款条款（0-现结；1-账期）
     */
	@ApiModelProperty(value = "付款条款ID（0-现结；1-账期）")
    private String paymentProvisionName;

    /**
     * 付款账期
     */
	@ApiModelProperty(value = "付款账期")
    private Integer paymentPeriod;

    /**
     * 付款账期单位（0-天；1-月）
     */
	@ApiModelProperty(value = "付款账期单位ID（0-天；1-月）")
    private Integer paymentPeriodUnit;
	
	/**
     * 付款账期单位（0-天；1-月）
     */
	@ApiModelProperty(value = "付款账期单位名称")
    private String paymentPeriodUnitName;

    /**
     * 付款时间
     */
	@ApiModelProperty(value = "付款时间")
    private Integer paymentTime;

    /**
     * 付款时间单位（0-每月；1-每周）
     */
	@ApiModelProperty(value = "付款时间单位（0-每月；1-每周）")
    private Integer paymentTimeUnit;
	
	/**
     * 付款时间单位（0-每月；1-每周）
     */
	@ApiModelProperty(value = "付款时间单位名称")
    private String paymentTimeUnitName;

    /**
     * 质量控制（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "质量管理（0-总部控制；1-自主控制）")
    private Integer qualityControl;

    /**
     * 权限设置（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "权限管理（0-总部控制；1-自主控制）")
    private Integer permissionSet;

    /**
     * 审批控制（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "审批管理（0-总部控制；1-自主控制）")
    private Integer approvalControl;
	
    /**
     * 预警设置（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "预警管理（0-总部控制；1-自主控制）")
    private Integer warnSet;

    /**
     * 价格管理（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "价格管理（0-总部控制；1-自主控制）")
    private Integer priceManage;

    /**
     * 特价管理（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "特价管理（0-总部控制；1-自主控制）")
    private Integer specialPriceManage;

    /**
     * 会员信息（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "会员管理（0-总部控制；1-自主控制）")
    private Integer memberInfo;

    /**
     * 促销规则（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "促销管理（0-总部控制；1-自主控制）")
    private Integer promotionRule;

    /**
     * Pos设置（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "Pos设置（0-总部控制；1-自主控制）")
    private Integer posSet;

    /**
     * 价签打印（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "价签管理（0-总部控制；1-自主控制）")
    private Integer priceTagPrint;

    /**
     * 提成规则（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "提成管理（0-总部控制；1-自主控制）")
    private Integer royaltyRule;

    /**
     * 远程审方（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "远程审方（0-总部控制；1-自主控制）")
    private Integer remoteTrial;

    /**
     * 合理库存（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "库存管理（0-总部控制；1-自主控制）")
    private Integer reasonableStock;

    /**
     * 配送管理（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "配送管理（0-禁止直购；1-允许直购）")
    private Integer distributionManage;

    /**
     * 设施设备（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "设施设备（0-总部控制；1-自主控制）")
    private Integer equipmentManage;

    @ApiModelProperty(value = "企业配置")
    private ManageConfig manageConfig;

    public ManageConfig getManageConfig() {
        return manageConfig;
    }

    public void setManageConfig(ManageConfig manageConfig) {
        this.manageConfig = manageConfig;
    }

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

    public static EnterpriseBusiness initEnterpriseBusiness(Enterprise enterprise){

        EnterpriseBusiness enterpriseBusiness = new EnterpriseBusiness();
        enterpriseBusiness.setEnterpriseId(enterprise.getId());
        enterpriseBusiness.setParentId(enterprise.getParentId());
        /**
         * 初始化 配货价格类型 为成本价
         */
        enterpriseBusiness.setDistrPriceType(DistrPriceType.COST_PRICE.getCode());
        enterpriseBusiness.setCreaterId(0L);
        enterpriseBusiness.setCreaterCode("0000000");
        enterpriseBusiness.setCreaterName("0000000");
        enterpriseBusiness.setCreateTime(new Date());
        enterpriseBusiness.setModifierId(0L);
        enterpriseBusiness.setModifierCode("0000000");
        enterpriseBusiness.setModifierName("0000000");
        enterpriseBusiness.setUpdateTime(new Date());
        return enterpriseBusiness;
    }

    /**
     * saas_enterprise_business
     */
    private static final long serialVersionUID = 1L;

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
     * 结算方式（0-零售缴款；1-应收账款）
     * @return settlement_mode 结算方式（0-零售缴款；1-应收账款）
     */
    public Integer getSettlementMode() {
        return settlementMode;
    }

    /**
     * 结算方式（0-零售缴款；1-应收账款）
     * @param settlementMode 结算方式（0-零售缴款；1-应收账款）
     */
    public void setSettlementMode(Integer settlementMode) {
        this.settlementMode = settlementMode;
    }

    /**
     * 配货价格类型（0-价格清单；1-成本价；2-成本加成）
     * @return distr_price_type 配货价格类型（0-价格清单；1-成本价；2-成本加成）
     */
    public Integer getDistrPriceType() {
        return distrPriceType;
    }

    /**
     * 配货价格类型（0-价格清单；1-成本价；2-成本加成）
     * @param distrPriceType 配货价格类型（0-价格清单；1-成本价；2-成本加成）
     */
    public void setDistrPriceType(Integer distrPriceType) {
        this.distrPriceType = distrPriceType;
    }

    /**
     * 配货价格清单ID
     * @return distr_price_order_id 配货价格清单ID
     */
    public Long getDistrPriceOrderId() {
        return distrPriceOrderId;
    }

    /**
     * 配货价格清单ID
     * @param distrPriceOrderId 配货价格清单ID
     */
    public void setDistrPriceOrderId(Long distrPriceOrderId) {
        this.distrPriceOrderId = distrPriceOrderId;
    }

    /**
     * 配货价格清单名称
     * @return distr_price_order_name 配货价格清单名称
     */
    public String getDistrPriceOrderName() {
        return distrPriceOrderName;
    }

    /**
     * 配货价格清单名称
     * @param distrPriceOrderName 配货价格清单名称
     */
    public void setDistrPriceOrderName(String distrPriceOrderName) {
        this.distrPriceOrderName = distrPriceOrderName == null ? null : distrPriceOrderName.trim();
    }

    /**
     * 成本加成加点率
     * @return add_rate 成本加成加点率
     */
    public BigDecimal getAddRate() {
        return addRate;
    }

    /**
     * 成本加成加点率
     * @param addRate 成本加成加点率
     */
    public void setAddRate(BigDecimal addRate) {
        this.addRate = addRate;
    }

    /**
     * 付款条款（0-现结；1-账期）
     * @return payment_provision 付款条款（0-现结；1-账期）
     */
    public Integer getPaymentProvision() {
        return paymentProvision;
    }

    /**
     * 付款条款（0-现结；1-账期）
     * @param paymentProvision 付款条款（0-现结；1-账期）
     */
    public void setPaymentProvision(Integer paymentProvision) {
        this.paymentProvision = paymentProvision;
    }

    /**
     * 付款账期
     * @return payment_period 付款账期
     */
    public Integer getPaymentPeriod() {
        return paymentPeriod;
    }

    /**
     * 付款账期
     * @param paymentPeriod 付款账期
     */
    public void setPaymentPeriod(Integer paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

    /**
     * 付款账期单位（0-天；1-月）
     * @return payment_period_unit 付款账期单位（0-天；1-月）
     */
    public Integer getPaymentPeriodUnit() {
        return paymentPeriodUnit;
    }

    /**
     * 付款账期单位（0-天；1-月）
     * @param paymentPeriodUnit 付款账期单位（0-天；1-月）
     */
    public void setPaymentPeriodUnit(Integer paymentPeriodUnit) {
        this.paymentPeriodUnit = paymentPeriodUnit;
    }

    /**
     * 付款时间
     * @return payment_time 付款时间
     */
    public Integer getPaymentTime() {
        return paymentTime;
    }

    /**
     * 付款时间
     * @param paymentTime 付款时间
     */
    public void setPaymentTime(Integer paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * 付款时间单位（0-每月；1-每周）
     * @return payment_time_unit 付款时间单位（0-每月；1-每周）
     */
    public Integer getPaymentTimeUnit() {
        return paymentTimeUnit;
    }

    /**
     * 付款时间单位（0-每月；1-每周）
     * @param paymentTimeUnit 付款时间单位（0-每月；1-每周）
     */
    public void setPaymentTimeUnit(Integer paymentTimeUnit) {
        this.paymentTimeUnit = paymentTimeUnit;
    }

    /**
     * 质量控制（0-总部控制；1-自主控制）
     * @return quality_control 质量控制（0-总部控制；1-自主控制）
     */
    public Integer getQualityControl() {
        return qualityControl;
    }

    /**
     * 质量控制（0-总部控制；1-自主控制）
     * @param qualityControl 质量控制（0-总部控制；1-自主控制）
     */
    public void setQualityControl(Integer qualityControl) {
        this.qualityControl = qualityControl;
    }

    /**
     * 权限设置（0-总部控制；1-自主控制）
     * @return permission_set 权限设置（0-总部控制；1-自主控制）
     */
    public Integer getPermissionSet() {
        return permissionSet;
    }

    /**
     * 权限设置（0-总部控制；1-自主控制）
     * @param permissionSet 权限设置（0-总部控制；1-自主控制）
     */
    public void setPermissionSet(Integer permissionSet) {
        this.permissionSet = permissionSet;
    }

    /**
     * 审批控制（0-总部控制；1-自主控制）
     * @return approval_control 审批控制（0-总部控制；1-自主控制）
     */
    public Integer getApprovalControl() {
        return approvalControl;
    }

    /**
     * 审批控制（0-总部控制；1-自主控制）
     * @param approvalControl 审批控制（0-总部控制；1-自主控制）
     */
    public void setApprovalControl(Integer approvalControl) {
        this.approvalControl = approvalControl;
    }

    /**
     * 预警设置（0-总部控制；1-自主控制）
     * @return warn_set 预警设置（0-总部控制；1-自主控制）
     */
    public Integer getWarnSet() {
        return warnSet;
    }

    /**
     * 预警设置（0-总部控制；1-自主控制）
     * @param warnSet 预警设置（0-总部控制；1-自主控制）
     */
    public void setWarnSet(Integer warnSet) {
        this.warnSet = warnSet;
    }

    /**
     * 价格管理（0-总部控制；1-自主控制）
     * @return price_manage 价格管理（0-总部控制；1-自主控制）
     */
    public Integer getPriceManage() {
        return priceManage;
    }

    /**
     * 价格管理（0-总部控制；1-自主控制）
     * @param priceManage 价格管理（0-总部控制；1-自主控制）
     */
    public void setPriceManage(Integer priceManage) {
        this.priceManage = priceManage;
    }

    /**
     * 特价管理（0-总部控制；1-自主控制）
     * @return special_price_manage 特价管理（0-总部控制；1-自主控制）
     */
    public Integer getSpecialPriceManage() {
        return specialPriceManage;
    }

    /**
     * 特价管理（0-总部控制；1-自主控制）
     * @param specialPriceManage 特价管理（0-总部控制；1-自主控制）
     */
    public void setSpecialPriceManage(Integer specialPriceManage) {
        this.specialPriceManage = specialPriceManage;
    }

    /**
     * 会员信息（0-总部控制；1-自主控制）
     * @return member_info 会员信息（0-总部控制；1-自主控制）
     */
    public Integer getMemberInfo() {
        return memberInfo;
    }

    /**
     * 会员信息（0-总部控制；1-自主控制）
     * @param memberInfo 会员信息（0-总部控制；1-自主控制）
     */
    public void setMemberInfo(Integer memberInfo) {
        this.memberInfo = memberInfo;
    }

    /**
     * 促销规则（0-总部控制；1-自主控制）
     * @return promotion_rule 促销规则（0-总部控制；1-自主控制）
     */
    public Integer getPromotionRule() {
        return promotionRule;
    }

    /**
     * 促销规则（0-总部控制；1-自主控制）
     * @param promotionRule 促销规则（0-总部控制；1-自主控制）
     */
    public void setPromotionRule(Integer promotionRule) {
        this.promotionRule = promotionRule;
    }

    /**
     * Pos设置（0-总部控制；1-自主控制）
     * @return pos_set Pos设置（0-总部控制；1-自主控制）
     */
    public Integer getPosSet() {
        return posSet;
    }

    /**
     * Pos设置（0-总部控制；1-自主控制）
     * @param posSet Pos设置（0-总部控制；1-自主控制）
     */
    public void setPosSet(Integer posSet) {
        this.posSet = posSet;
    }

    /**
     * 价签打印（0-总部控制；1-自主控制）
     * @return price_tag_print 价签打印（0-总部控制；1-自主控制）
     */
    public Integer getPriceTagPrint() {
        return priceTagPrint;
    }

    /**
     * 价签打印（0-总部控制；1-自主控制）
     * @param priceTagPrint 价签打印（0-总部控制；1-自主控制）
     */
    public void setPriceTagPrint(Integer priceTagPrint) {
        this.priceTagPrint = priceTagPrint;
    }

    /**
     * 提成规则（0-总部控制；1-自主控制）
     * @return royalty_rule 提成规则（0-总部控制；1-自主控制）
     */
    public Integer getRoyaltyRule() {
        return royaltyRule;
    }

    /**
     * 提成规则（0-总部控制；1-自主控制）
     * @param royaltyRule 提成规则（0-总部控制；1-自主控制）
     */
    public void setRoyaltyRule(Integer royaltyRule) {
        this.royaltyRule = royaltyRule;
    }

    /**
     * 远程审方（0-总部控制；1-自主控制）
     * @return remote_trial 远程审方（0-总部控制；1-自主控制）
     */
    public Integer getRemoteTrial() {
        return remoteTrial;
    }

    /**
     * 远程审方（0-总部控制；1-自主控制）
     * @param remoteTrial 远程审方（0-总部控制；1-自主控制）
     */
    public void setRemoteTrial(Integer remoteTrial) {
        this.remoteTrial = remoteTrial;
    }

    /**
     * 合理库存（0-总部控制；1-自主控制）
     * @return reasonable_stock 合理库存（0-总部控制；1-自主控制）
     */
    public Integer getReasonableStock() {
        return reasonableStock;
    }

    /**
     * 合理库存（0-总部控制；1-自主控制）
     * @param reasonableStock 合理库存（0-总部控制；1-自主控制）
     */
    public void setReasonableStock(Integer reasonableStock) {
        this.reasonableStock = reasonableStock;
    }

    /**
     * 配送管理（0-总部控制；1-自主控制）
     * @return distribution_manage 配送管理（0-总部控制；1-自主控制）
     */
    public Integer getDistributionManage() {
        return distributionManage;
    }

    /**
     * 配送管理（0-总部控制；1-自主控制）
     * @param distributionManage 配送管理（0-总部控制；1-自主控制）
     */
    public void setDistributionManage(Integer distributionManage) {
        this.distributionManage = distributionManage;
    }

    /**
     * 设备设备（0-总部控制；1-自主控制）
     * @return equipment_manage 设备设备（0-总部控制；1-自主控制）
     */
    public Integer getEquipmentManage() {
        return equipmentManage;
    }

    /**
     * 设备设备（0-总部控制；1-自主控制）
     * @param equipmentManage 设备设备（0-总部控制；1-自主控制）
     */
    public void setEquipmentManage(Integer equipmentManage) {
        this.equipmentManage = equipmentManage;
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
    
    public String getSettlementModeName() {
		return settlementModeName;
	}

	public void setSettlementModeName(String settlementModeName) {
		this.settlementModeName = settlementModeName;
	}

	public String getDistrPriceTypeName() {
		return distrPriceTypeName;
	}

	public void setDistrPriceTypeName(String distrPriceTypeName) {
		this.distrPriceTypeName = distrPriceTypeName;
	}

	public String getPaymentProvisionName() {
		return paymentProvisionName;
	}

	public void setPaymentProvisionName(String paymentProvisionName) {
		this.paymentProvisionName = paymentProvisionName;
	}

	public String getPaymentPeriodUnitName() {
		return paymentPeriodUnitName;
	}

	public void setPaymentPeriodUnitName(String paymentPeriodUnitName) {
		this.paymentPeriodUnitName = paymentPeriodUnitName;
	}

	public String getPaymentTimeUnitName() {
		return paymentTimeUnitName;
	}

	public void setPaymentTimeUnitName(String paymentTimeUnitName) {
		this.paymentTimeUnitName = paymentTimeUnitName;
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
        sb.append(", settlementMode=").append(settlementMode);
        sb.append(", distrPriceType=").append(distrPriceType);
        sb.append(", distrPriceOrderId=").append(distrPriceOrderId);
        sb.append(", distrPriceOrderName=").append(distrPriceOrderName);
        sb.append(", addRate=").append(addRate);
        sb.append(", paymentProvision=").append(paymentProvision);
        sb.append(", paymentPeriod=").append(paymentPeriod);
        sb.append(", paymentPeriodUnit=").append(paymentPeriodUnit);
        sb.append(", paymentTime=").append(paymentTime);
        sb.append(", paymentTimeUnit=").append(paymentTimeUnit);
        sb.append(", qualityControl=").append(qualityControl);
        sb.append(", permissionSet=").append(permissionSet);
        sb.append(", approvalControl=").append(approvalControl);
        sb.append(", warnSet=").append(warnSet);
        sb.append(", priceManage=").append(priceManage);
        sb.append(", specialPriceManage=").append(specialPriceManage);
        sb.append(", memberInfo=").append(memberInfo);
        sb.append(", promotionRule=").append(promotionRule);
        sb.append(", posSet=").append(posSet);
        sb.append(", priceTagPrint=").append(priceTagPrint);
        sb.append(", royaltyRule=").append(royaltyRule);
        sb.append(", remoteTrial=").append(remoteTrial);
        sb.append(", reasonableStock=").append(reasonableStock);
        sb.append(", distributionManage=").append(distributionManage);
        sb.append(", equipmentManage=").append(equipmentManage);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}