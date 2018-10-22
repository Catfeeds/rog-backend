package com.rograndec.feijiayun.chain.business.basic.supplier.entity;

import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierBusinessVO;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierDetailVO;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class SupplierBusiness implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 供货单位ID
     */
	@ApiModelProperty(value = "供货单位ID")
    private Long supplierId;

    /**
     * 企业（总部）ID
     */
	@ApiModelProperty(value = "企业（总部）ID")
    private Long enterpriseId;

    /**
     * 采购员ID
     */
	@ApiModelProperty(value = "采购员ID")
    private Long purchaserId;
	
	@ApiModelProperty(value="报货方式（0-电话；1-传真；2-邮件；3-系统接口）")
	private Integer deliveryMode;
	
    

	/**
     * 采购员编码
     */
	@ApiModelProperty(value = "采购员编码")
    private String purchaserCode;

    /**
     * 采购员名称
     */
	@ApiModelProperty(value = "采购员名称")
    private String purchaserName;

    /**
     * 付款条款（0-现结；1-账期）
     */
	@ApiModelProperty(value = "付款条款（0-现结；1-账期）")
    private Integer paymentProvision;

    /**
     * 付款账期
     */
	@ApiModelProperty(value = "付款账期")
    private Integer paymentPeriod;

    /**
     * 付款账期单位（0-天；1-月）
     */
	@ApiModelProperty(value = "付款账期单位（0-天；1-月）")
    private Integer paymentPeriodUnit;

    /**
     * 付款时间（0-每月；1-每周）
     */
	@ApiModelProperty(value = "付款时间（0-每月；1-每周）")
    private Integer paymentTimeUnit;

    /**
     * 付款时间payment_time_unit=0时，payment_time含义为每周（0~6：周一~周日）payment_time_unit=1时，payment_time含义为（1~31）日；
     */
	@ApiModelProperty(value = "付款时间payment_time_unit=0时，payment_time含义为每周（0~6：周一~周日）payment_time_unit=1时，payment_time含义为（1~31）日；")
    private String paymentTime;

    /**
     * 报货时间单位（0-每月；1-每周）
     */
	@ApiModelProperty(value = "报货时间单位（0-每月；1-每周）")
    private Integer deliveryTimeUnit;

    /**
     * 报货时间delivery_time_unit=0时，delivery _time含义为每周（0~6：周一~周日）delivery_time_unit=1时，delivery_time含义为（1~31）日；
     */
	@ApiModelProperty(value = "报货时间delivery_time_unit=0时，delivery _time含义为每周（0~6：周一~周日）delivery_time_unit=1时，delivery_time含义为（1~31）日；")
    private String deliveryTime;

    /**
     * 报货电话
     */
	@ApiModelProperty(value = "报货电话")
    private String deliveryPhone;

    /**
     * 送货时间单位（0-每月；1-每周）
     */
	@ApiModelProperty(value = "送货时间单位（0-每月；1-每周）")
    private Integer sendTimeUnit;

    /**
     * 送货时间send_time_unit=0时，send_time含义为每周（0~6：周一~周日）send_time_unit=1时，send_time含义为（1~31）日；
     */
	@ApiModelProperty(value = "送货时间send_time_unit=0时，send_time含义为每周（0~6：周一~周日）send_time_unit=1时，send_time含义为（1~31）日；")
    private String sendTime;

    /**
     * 送达周期（0-天；1-月）
     */
	@ApiModelProperty(value = "送达周期单位（0-天；1-月）")
    private Integer serviceCircleUnit;

	
	@ApiModelProperty(value = "送达周期")
    private Integer serviceCircle;
    /**
     * 运输方式（0-陆运；1-空运；2-海运）
     */
	@ApiModelProperty(value = "运输方式（0-陆运；1-空运；2-海运）")
    private Integer transportMode;

    /**
     * 送达地址（0-配送中心仓库；1-门店仓库）
     */
	@ApiModelProperty(value = "送达地址（0-配送中心仓库；1-门店仓库）")
    private Integer sendPlace;

    /**
     * 对账时间单位（0-每月；1-每周）
     */
	@ApiModelProperty(value = "对账时间单位（0-每月；1-每周）")
    private Integer accountTimeUnit;

    /**
     * 对账时间account_time_unit=0时，account_time含义为每周（0~6：周一~周日）account_time_unit=1时，account_time含义为（1~31）日；
     */
	@ApiModelProperty(value = "对账时间account_time_unit=0时，account_time含义为每周（0~6：周一~周日）account_time_unit=1时，account_time含义为（1~31）日；")
    private String accountTime;

    /**
     * 首营企业（0-否；1-是）
     */
	@ApiModelProperty(value = "首营企业（0-否；1-是）")
    private Integer first;

    /**
     * 首营企业编号
     */
	@ApiModelProperty(value = "首营企业编号")
    private String firstCode;

    /**
     * 申请人ID
     */
	@ApiModelProperty(value = "申请人ID")
    private Long applicantId;

    /**
     * 申请人员编码
     */
	@ApiModelProperty(value = "申请人员编码")
    private String applicantCode;

    /**
     * 申请人员
     */
	@ApiModelProperty(value = "申请人员")
    private String applicantName;

    /**
     * 申请日期
     */
	@ApiModelProperty(value = "申请日期")
    private Date applicationTime;

    /**
     * 质量管理体系
     */
	@ApiModelProperty(value = "质量管理体系")
    private String qualityManageSys;

    /**
     * 结算方式（0-购销；1-实销实结）
     */
    @ApiModelProperty(value = "结算方式（0-购销；1-实销实结）")
    private Integer managementMode;

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

    public Integer getManagementMode() {
        return managementMode;
    }

    public void setManagementMode(Integer managementMode) {
        this.managementMode = managementMode;
    }

    /**
     * 供货单位ID
     * @return supplier_id 供货单位ID
     */
    public Long getSupplierId() {
        return supplierId;
    }

    /**
     * 供货单位ID
     * @param supplierId 供货单位ID
     */
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * 企业（总部）ID
     * @return enterprise_id 企业（总部）ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业（总部）ID
     * @param enterpriseId 企业（总部）ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 采购员ID
     * @return purchaser_id 采购员ID
     */
    public Long getPurchaserId() {
        return purchaserId;
    }

    /**
     * 采购员ID
     * @param purchaserId 采购员ID
     */
    public void setPurchaserId(Long purchaserId) {
        this.purchaserId = purchaserId;
    }

    /**
     * 采购员编码
     * @return purchaser_code 采购员编码
     */
    public String getPurchaserCode() {
        return purchaserCode;
    }

    /**
     * 采购员编码
     * @param purchaserCode 采购员编码
     */
    public void setPurchaserCode(String purchaserCode) {
        this.purchaserCode = purchaserCode == null ? null : purchaserCode.trim();
    }

    /**
     * 采购员名称
     * @return purchaser_name 采购员名称
     */
    public String getPurchaserName() {
        return purchaserName;
    }

    /**
     * 采购员名称
     * @param purchaserName 采购员名称
     */
    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName == null ? null : purchaserName.trim();
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
     * 付款时间（0-每月；1-每周）
     * @return payment_time_unit 付款时间（0-每月；1-每周）
     */
    public Integer getPaymentTimeUnit() {
        return paymentTimeUnit;
    }

    /**
     * 付款时间（0-每月；1-每周）
     * @param paymentTimeUnit 付款时间（0-每月；1-每周）
     */
    public void setPaymentTimeUnit(Integer paymentTimeUnit) {
        this.paymentTimeUnit = paymentTimeUnit;
    }



    /**
     * 报货时间单位（0-每月；1-每周）
     * @return delivery_time_unit 报货时间单位（0-每月；1-每周）
     */
    public Integer getDeliveryTimeUnit() {
        return deliveryTimeUnit;
    }

    /**
     * 报货时间单位（0-每月；1-每周）
     * @param deliveryTimeUnit 报货时间单位（0-每月；1-每周）
     */
    public void setDeliveryTimeUnit(Integer deliveryTimeUnit) {
        this.deliveryTimeUnit = deliveryTimeUnit;
    }



    /**
     * 报货电话
     * @return delivery_phone 报货电话
     */
    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    /**
     * 报货电话
     * @param deliveryPhone 报货电话
     */
    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone == null ? null : deliveryPhone.trim();
    }

    /**
     * 送货时间单位（0-每月；1-每周）
     * @return send_time_unit 送货时间单位（0-每月；1-每周）
     */
    public Integer getSendTimeUnit() {
        return sendTimeUnit;
    }

    /**
     * 送货时间单位（0-每月；1-每周）
     * @param sendTimeUnit 送货时间单位（0-每月；1-每周）
     */
    public void setSendTimeUnit(Integer sendTimeUnit) {
        this.sendTimeUnit = sendTimeUnit;
    }



    /**
     * 送达周期（0-天；1-月）
     * @return service_circle 送达周期（0-天；1-月）
     */
    public Integer getServiceCircle() {
        return serviceCircle;
    }

    /**
     * 送达周期（0-天；1-月）
     * @param serviceCircle 送达周期（0-天；1-月）
     */
    public void setServiceCircle(Integer serviceCircle) {
        this.serviceCircle = serviceCircle;
    }

    /**
     * 运输方式（0-陆运；1-空运；2-海运）
     * @return transport_mode 运输方式（0-陆运；1-空运；2-海运）
     */
    public Integer getTransportMode() {
        return transportMode;
    }

    /**
     * 运输方式（0-陆运；1-空运；2-海运）
     * @param transportMode 运输方式（0-陆运；1-空运；2-海运）
     */
    public void setTransportMode(Integer transportMode) {
        this.transportMode = transportMode;
    }

    /**
     * 送达地址（0-配送中心仓库；1-门店仓库）
     * @return send_place 送达地址（0-配送中心仓库；1-门店仓库）
     */
    public Integer getSendPlace() {
        return sendPlace;
    }

    /**
     * 送达地址（0-配送中心仓库；1-门店仓库）
     * @param sendPlace 送达地址（0-配送中心仓库；1-门店仓库）
     */
    public void setSendPlace(Integer sendPlace) {
        this.sendPlace = sendPlace;
    }

    /**
     * 对账时间单位（0-每月；1-每周）
     * @return account_time_unit 对账时间单位（0-每月；1-每周）
     */
    public Integer getAccountTimeUnit() {
        return accountTimeUnit;
    }

    /**
     * 对账时间单位（0-每月；1-每周）
     * @param accountTimeUnit 对账时间单位（0-每月；1-每周）
     */
    public void setAccountTimeUnit(Integer accountTimeUnit) {
        this.accountTimeUnit = accountTimeUnit;
    }



    /**
     * 首营企业（0-否；1-是）
     * @return first 首营企业（0-否；1-是）
     */
    public Integer getFirst() {
        return first;
    }

    /**
     * 首营企业（0-否；1-是）
     * @param first 首营企业（0-否；1-是）
     */
    public void setFirst(Integer first) {
        this.first = first;
    }

    /**
     * 首营企业编号
     * @return first_code 首营企业编号
     */
    public String getFirstCode() {
        return firstCode;
    }

    /**
     * 首营企业编号
     * @param firstCode 首营企业编号
     */
    public void setFirstCode(String firstCode) {
        this.firstCode = firstCode == null ? null : firstCode.trim();
    }

    /**
     * 申请人ID
     * @return applicant_id 申请人ID
     */
    public Long getApplicantId() {
        return applicantId;
    }

    /**
     * 申请人ID
     * @param applicantId 申请人ID
     */
    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    /**
     * 申请人员编码
     * @return applicant_code 申请人员编码
     */
    public String getApplicantCode() {
        return applicantCode;
    }

    /**
     * 申请人员编码
     * @param applicantCode 申请人员编码
     */
    public void setApplicantCode(String applicantCode) {
        this.applicantCode = applicantCode == null ? null : applicantCode.trim();
    }

    /**
     * 申请人员
     * @return applicant_name 申请人员
     */
    public String getApplicantName() {
        return applicantName;
    }

    /**
     * 申请人员
     * @param applicantName 申请人员
     */
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName == null ? null : applicantName.trim();
    }

    /**
     * 申请日期
     * @return application_time 申请日期
     */
    public Date getApplicationTime() {
        return applicationTime;
    }

    /**
     * 申请日期
     * @param applicationTime 申请日期
     */
    public void setApplicationTime(Date applicationTime) {
        this.applicationTime = applicationTime;
    }

    /**
     * 质量管理体系
     * @return quality_manage_sys 质量管理体系
     */
    public String getQualityManageSys() {
        return qualityManageSys;
    }

    /**
     * 质量管理体系
     * @param qualityManageSys 质量管理体系
     */
    public void setQualityManageSys(String qualityManageSys) {
        this.qualityManageSys = qualityManageSys == null ? null : qualityManageSys.trim();
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

    public Integer getServiceCircleUnit() {
		return serviceCircleUnit;
	}

	public void setServiceCircleUnit(Integer serviceCircleUnit) {
		this.serviceCircleUnit = serviceCircleUnit;
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
    
    

    public Integer getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(Integer deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(String accountTime) {
        this.accountTime = accountTime;
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
        sb.append(", supplierId=").append(supplierId);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", purchaserId=").append(purchaserId);
        sb.append(", purchaserCode=").append(purchaserCode);
        sb.append(", purchaserName=").append(purchaserName);
        sb.append(", paymentProvision=").append(paymentProvision);
        sb.append(", paymentPeriod=").append(paymentPeriod);
        sb.append(", paymentPeriodUnit=").append(paymentPeriodUnit);
        sb.append(", paymentTimeUnit=").append(paymentTimeUnit);
        sb.append(", paymentTime=").append(paymentTime);
        sb.append(", deliveryTimeUnit=").append(deliveryTimeUnit);
        sb.append(", deliveryTime=").append(deliveryTime);
        sb.append(", deliveryPhone=").append(deliveryPhone);
        sb.append(", sendTimeUnit=").append(sendTimeUnit);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", serviceCircle=").append(serviceCircle);
        sb.append(", transportMode=").append(transportMode);
        sb.append(", sendPlace=").append(sendPlace);
        sb.append(", accountTimeUnit=").append(accountTimeUnit);
        sb.append(", accountTime=").append(accountTime);
        sb.append(", first=").append(first);
        sb.append(", firstCode=").append(firstCode);
        sb.append(", applicantId=").append(applicantId);
        sb.append(", applicantCode=").append(applicantCode);
        sb.append(", applicantName=").append(applicantName);
        sb.append(", applicationTime=").append(applicationTime);
        sb.append(", qualityManageSys=").append(qualityManageSys);
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

	public static SupplierBusiness getSupplierBusinessAddSupplierBusinessVO(SupplierDetailVO supplierDetailVO, SupplierBusinessVO supplierBusinessVO,
                                                                            UserVO user, boolean b) {
		SupplierBusiness supplierBusiness = new SupplierBusiness();
		supplierBusiness.setId(supplierBusinessVO.getId());
		supplierBusiness.setSupplierId(supplierDetailVO.getId());
        Long headEnterpriseId = ChainType.getHeadEnterpriseId(user);
        supplierBusiness.setEnterpriseId(headEnterpriseId);
		supplierBusiness.setPurchaserId(supplierBusinessVO.getPurchaserId());
		supplierBusiness.setPurchaserCode(supplierBusinessVO.getPurchaserCode());
		supplierBusiness.setPurchaserName(supplierBusinessVO.getPurchaserName());
		supplierBusiness.setPaymentProvision(supplierBusinessVO.getPaymentProvision());
		supplierBusiness.setPaymentPeriod(supplierBusinessVO.getPaymentPeriod());
		supplierBusiness.setPaymentPeriodUnit(supplierBusinessVO.getPaymentPeriodUnit());
		supplierBusiness.setPaymentTimeUnit(supplierBusinessVO.getPaymentTimeUnit());
		supplierBusiness.setPaymentTime(supplierBusinessVO.getPaymentTime());
		supplierBusiness.setDeliveryTimeUnit(supplierBusinessVO.getDeliveryTimeUnit());
		supplierBusiness.setDeliveryTime(supplierBusinessVO.getDeliveryTime());
		supplierBusiness.setDeliveryMode(supplierBusinessVO.getDeliveryMode());
		supplierBusiness.setDeliveryPhone(supplierBusinessVO.getDeliveryPhone());
		supplierBusiness.setSendTimeUnit(supplierBusinessVO.getSendTimeUnit());
		supplierBusiness.setSendTime(supplierBusinessVO.getSendTime());
        supplierBusiness.setManagementMode(supplierBusinessVO.getManagementMode());
		supplierBusiness.setServiceCircle(supplierBusinessVO.getServiceCircle());
		supplierBusiness.setServiceCircleUnit(supplierBusinessVO.getServiceCircleUnit());
		supplierBusiness.setTransportMode(supplierBusinessVO.getTransportMode());
		supplierBusiness.setSendPlace(supplierBusinessVO.getSendPlace());
		supplierBusiness.setAccountTimeUnit(supplierBusinessVO.getAccountTimeUnit());
		supplierBusiness.setAccountTime(supplierBusinessVO.getAccountTime());
		supplierBusiness.setFirst(supplierBusinessVO.getFirst());
		supplierBusiness.setFirstCode(supplierBusinessVO.getFirstCode());
		supplierBusiness.setApplicantId(supplierBusinessVO.getApplicantId());
		supplierBusiness.setApplicantCode(supplierBusinessVO.getApplicantCode());
		supplierBusiness.setApplicantName(supplierBusinessVO.getApplicantName());
		supplierBusiness.setApplicationTime(supplierBusinessVO.getApplicationTime());
		supplierBusiness.setQualityManageSys(supplierBusinessVO.getQualityManageSys());
		if(b){
			supplierBusiness.setCreaterId(user.getUserId());
			supplierBusiness.setCreaterCode(user.getUserCode());
			supplierBusiness.setCreaterName(user.getUserName());
			supplierBusiness.setCreateTime(new Date());
            supplierBusiness.setModifierId(user.getUserId());
            supplierBusiness.setModifierCode(user.getUserCode());
            supplierBusiness.setModifierName(user.getUserName());
            supplierBusiness.setUpdateTime(new Date());
        }else {
        	supplierBusiness.setModifierId(user.getUserId());
        	supplierBusiness.setModifierCode(user.getUserCode());
        	supplierBusiness.setModifierName(user.getUserName());
        	supplierBusiness.setUpdateTime(new Date());
        }
		
		return supplierBusiness;
	}
}