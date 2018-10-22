package com.rograndec.feijiayun.chain.business.basic.store.vo;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel(value = "storeEnterpriseBusinessResponseVO", description = "门店业务信息返回页面属性对象")
public class StoreEnterpriseBusinessResponseVO implements Serializable {
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
	@ApiModelProperty (value = "质量管理（0-总部控制；1-自主控制）")
    private Integer qualityControl;

    /**
     * 质量控制是否可编辑
     */
    @ApiModelProperty (value = "质量管理是否可编辑 true-可编辑 false-不可编辑")
    private Boolean qualityControlFlag = true;


    /**
     * 权限设置（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "权限管理（0-总部控制；1-自主控制）")
    private Integer permissionSet;

    /**
     * 权限设置是否可编辑
     */
    @ApiModelProperty(value = "权限管理是否可编辑  true-可编辑 false-不可编辑")
    private Boolean permissionSetFlag = true;

    /**
     * 审批控制（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "审批管理（0-总部控制；1-自主控制）")
    private Integer approvalControl;
	
	 /**
     * 财务管理（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "财务管理（0-总部控制；1-自主控制）")
    private Integer financeControl;

    /**
     * 审批控制是否可编辑
     */
    @ApiModelProperty(value = "审批管理是否可编辑  true-可编辑 false-不可编辑")
    private Boolean approvalControlFlag = true;

    /**
     * 预警设置（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "预警管理（0-总部控制；1-自主控制）")
    private Integer warnSet;

    /**
     * 预警设置是否可编辑
     */
    @ApiModelProperty(value = "预警管理是否可编辑  true-可编辑 false-不可编辑")
    private Boolean warnSetFlag = true;

    /**
     * 价格管理（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "价格管理（0-总部控制；1-自主控制）")
    private Integer priceManage;

    /**
     * 价格管理是否可编辑
     */
    @ApiModelProperty(value = "价格管理是否可编辑  true-可编辑 false-不可编辑")
    private Boolean priceManageFlag = true;

    /**
     * 特价管理（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "特价管理（0-总部控制；1-自主控制）")
    private Integer specialPriceManage;

    /**
     * 特价管理是否可编辑
     */
    @ApiModelProperty(value = "特价管理是否可编辑  true-可编辑 false-不可编辑")
    private Boolean specialPriceManageFlag = true;

    /**
     * 会员信息（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "会员管理（0-总部控制；1-自主控制）")
    private Integer memberInfo;

    /**
     * 会员信息是否可编辑
     */
    @ApiModelProperty(value = "会员管理是否可编辑  true-可编辑 false-不可编辑")
    private Boolean memberInfoFlag = true;

    /**
     * 促销规则（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "促销管理（0-总部控制；1-自主控制）")
    private Integer promotionRule;

    /**
     * 促销规则是否可编辑
     */
    @ApiModelProperty(value = "促销管理是否可编辑  true-可编辑 false-不可编辑")
    private Boolean promotionRuleFlag = true;

    /**
     * Pos设置（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "Pos设置（0-总部控制；1-自主控制）")
    private Integer posSet;

    /**
     * Pos设置是否可编辑
     */
    @ApiModelProperty(value = "Pos设置是否可编辑  true-可编辑 false-不可编辑")
    private Boolean posSetFlag = true;

    /**
     * 价签打印（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "价签管理（0-总部控制；1-自主控制）")
    private Integer priceTagPrint;

    /**
     * 价签打印是否可编辑
     */
    @ApiModelProperty(value = "价签管理是否可编辑  true-可编辑 false-不可编辑")
    private Boolean priceTagPrintFlag = true;

    /**
     * 提成规则（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "提成管理（0-总部控制；1-自主控制）")
    private Integer royaltyRule;

    /**
     * 提成规则是否可编辑
     */
    @ApiModelProperty(value = "提成管理是否可编辑  true-可编辑 false-不可编辑")
    private Boolean royaltyRuleFlag = true;

    /**
     * 远程审方（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "远程审方（0-总部控制；1-自主控制）")
    private Integer remoteTrial;

    /**
     * 远程审方是否可编辑
     */
    @ApiModelProperty(value = "远程审方是否可编辑  true-可编辑 false-不可编辑")
    private Boolean remoteTrialFlag = true;

    /**
     * 合理库存（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "库存管理（0-总部控制；1-自主控制）")
    private Integer reasonableStock;

    /**
     * 合理库存是否可编辑
     */
    @ApiModelProperty(value = "库存管理是否可编辑  true-可编辑 false-不可编辑")
    private Boolean reasonableStockFlag = true;

    /**
     * 配送管理（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "要货管理（0-总部控制；1-自主控制）")
    private Integer distributionManage;

    /**
     * 配送管理是否可编辑
     */
    @ApiModelProperty(value = "要货管理是否可编辑  true-可编辑 false-不可编辑")
    private Boolean distributionManageFlag = true;

    /**
     * 设施设备（0-总部控制；1-自主控制）
     */
	@ApiModelProperty(value = "设施设备（0-总部控制；1-自主控制）")
    private Integer equipmentManage;

    /**
     * 设施设备是否可编辑
     */
    @ApiModelProperty(value = "设施设备是否可编辑  true-可编辑 false-不可编辑")
    private Boolean equipmentManageFlag = true;

    /**
     * 备注
     */
	@ApiModelProperty(value = "备注")
    private String remark;

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

    public Boolean getQualityControlFlag() {
        return qualityControlFlag;
    }

    public void setQualityControlFlag(Boolean qualityControlFlag) {
        this.qualityControlFlag = qualityControlFlag;
    }

    public Boolean getPermissionSetFlag() {
        return permissionSetFlag;
    }

    public void setPermissionSetFlag(Boolean permissionSetFlag) {
        this.permissionSetFlag = permissionSetFlag;
    }

    public Boolean getApprovalControlFlag() {
        return approvalControlFlag;
    }

    public void setApprovalControlFlag(Boolean approvalControlFlag) {
        this.approvalControlFlag = approvalControlFlag;
    }

    public Boolean getWarnSetFlag() {
        return warnSetFlag;
    }

    public void setWarnSetFlag(Boolean warnSetFlag) {
        this.warnSetFlag = warnSetFlag;
    }

    public Boolean getPriceManageFlag() {
        return priceManageFlag;
    }

    public void setPriceManageFlag(Boolean priceManageFlag) {
        this.priceManageFlag = priceManageFlag;
    }

    public Boolean getSpecialPriceManageFlag() {
        return specialPriceManageFlag;
    }

    public void setSpecialPriceManageFlag(Boolean specialPriceManageFlag) {
        this.specialPriceManageFlag = specialPriceManageFlag;
    }

    public Boolean getMemberInfoFlag() {
        return memberInfoFlag;
    }

    public void setMemberInfoFlag(Boolean memberInfoFlag) {
        this.memberInfoFlag = memberInfoFlag;
    }

    public Boolean getPromotionRuleFlag() {
        return promotionRuleFlag;
    }

    public void setPromotionRuleFlag(Boolean promotionRuleFlag) {
        this.promotionRuleFlag = promotionRuleFlag;
    }

    public Boolean getPosSetFlag() {
        return posSetFlag;
    }

    public void setPosSetFlag(Boolean posSetFlag) {
        this.posSetFlag = posSetFlag;
    }

    public Boolean getPriceTagPrintFlag() {
        return priceTagPrintFlag;
    }

    public void setPriceTagPrintFlag(Boolean priceTagPrintFlag) {
        this.priceTagPrintFlag = priceTagPrintFlag;
    }

    public Boolean getRoyaltyRuleFlag() {
        return royaltyRuleFlag;
    }

    public void setRoyaltyRuleFlag(Boolean royaltyRuleFlag) {
        this.royaltyRuleFlag = royaltyRuleFlag;
    }

    public Boolean getRemoteTrialFlag() {
        return remoteTrialFlag;
    }

    public void setRemoteTrialFlag(Boolean remoteTrialFlag) {
        this.remoteTrialFlag = remoteTrialFlag;
    }

    public Boolean getReasonableStockFlag() {
        return reasonableStockFlag;
    }

    public void setReasonableStockFlag(Boolean reasonableStockFlag) {
        this.reasonableStockFlag = reasonableStockFlag;
    }

    public Boolean getDistributionManageFlag() {
        return distributionManageFlag;
    }

    public void setDistributionManageFlag(Boolean distributionManageFlag) {
        this.distributionManageFlag = distributionManageFlag;
    }

    public Boolean getEquipmentManageFlag() {
        return equipmentManageFlag;
    }

    public void setEquipmentManageFlag(Boolean equipmentManageFlag) {
        this.equipmentManageFlag = equipmentManageFlag;
    }

    public Integer getFinanceControl() {
		return financeControl;
	}

	public void setFinanceControl(Integer financeControl) {
		this.financeControl = financeControl;
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
        sb.append(", financeControl=").append(financeControl);
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}