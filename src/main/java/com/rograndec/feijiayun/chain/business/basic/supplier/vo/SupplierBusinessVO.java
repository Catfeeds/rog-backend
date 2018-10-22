package com.rograndec.feijiayun.chain.business.basic.supplier.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class SupplierBusinessVO implements Serializable{

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

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getPurchaserId() {
		return purchaserId;
	}

	public void setPurchaserId(Long purchaserId) {
		this.purchaserId = purchaserId;
	}

	public String getPurchaserCode() {
		return purchaserCode;
	}

	public void setPurchaserCode(String purchaserCode) {
		this.purchaserCode = purchaserCode;
	}

	public String getPurchaserName() {
		return purchaserName;
	}

	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}

	public Integer getPaymentProvision() {
		return paymentProvision;
	}

	public void setPaymentProvision(Integer paymentProvision) {
		this.paymentProvision = paymentProvision;
	}

	public Integer getPaymentPeriod() {
		return paymentPeriod;
	}

	public void setPaymentPeriod(Integer paymentPeriod) {
		this.paymentPeriod = paymentPeriod;
	}

	public Integer getPaymentPeriodUnit() {
		return paymentPeriodUnit;
	}

	public void setPaymentPeriodUnit(Integer paymentPeriodUnit) {
		this.paymentPeriodUnit = paymentPeriodUnit;
	}

	public Integer getPaymentTimeUnit() {
		return paymentTimeUnit;
	}

	public void setPaymentTimeUnit(Integer paymentTimeUnit) {
		this.paymentTimeUnit = paymentTimeUnit;
	}

	public Integer getDeliveryTimeUnit() {
		return deliveryTimeUnit;
	}

	public void setDeliveryTimeUnit(Integer deliveryTimeUnit) {
		this.deliveryTimeUnit = deliveryTimeUnit;
	}

	public String getDeliveryPhone() {
		return deliveryPhone;
	}

	public void setDeliveryPhone(String deliveryPhone) {
		this.deliveryPhone = deliveryPhone;
	}

	public Integer getSendTimeUnit() {
		return sendTimeUnit;
	}

	public void setSendTimeUnit(Integer sendTimeUnit) {
		this.sendTimeUnit = sendTimeUnit;
	}

	public Integer getServiceCircle() {
		return serviceCircle;
	}

	public void setServiceCircle(Integer serviceCircle) {
		this.serviceCircle = serviceCircle;
	}

	public Integer getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(Integer transportMode) {
		this.transportMode = transportMode;
	}

	public Integer getSendPlace() {
		return sendPlace;
	}

	public void setSendPlace(Integer sendPlace) {
		this.sendPlace = sendPlace;
	}

	public Integer getAccountTimeUnit() {
		return accountTimeUnit;
	}

	public void setAccountTimeUnit(Integer accountTimeUnit) {
		this.accountTimeUnit = accountTimeUnit;
	}

	public Integer getFirst() {
		return first;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}

	public String getFirstCode() {
		return firstCode;
	}

	public void setFirstCode(String firstCode) {
		this.firstCode = firstCode;
	}

	public Long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}

	public String getApplicantCode() {
		return applicantCode;
	}

	public void setApplicantCode(String applicantCode) {
		this.applicantCode = applicantCode;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public Date getApplicationTime() {
		return applicationTime;
	}

	public void setApplicationTime(Date applicationTime) {
		this.applicationTime = applicationTime;
	}

	public String getQualityManageSys() {
		return qualityManageSys;
	}

	public void setQualityManageSys(String qualityManageSys) {
		this.qualityManageSys = qualityManageSys;
	}

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
	 * 结算方式（0-购销；1-实销实结）
	 */
	@ApiModelProperty(value = "结算方式（0-购销；1-实销实结）")
	private Integer managementMode;

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
     * 付款时间payment_time_unit=0时，payment_time含义为（1~31）日,payment_time_unit=1时，payment_time含义为每周（0~6：周一~周日）；
     */
	@ApiModelProperty(value = "付款时间payment_time_unit=0时，payment_time含义为每周（0~6：周一~周日）payment_time_unit=1时，payment_time含义为（1~31）日；")
    private String paymentTime;

	@ApiModelProperty(value = "付款时间中文值处理")
	private String paymentTimeStr;

    /**
     * 报货时间单位（0-每月；1-每周）
     */
	@ApiModelProperty(value = "报货时间单位（0-每月；1-每周）")
    private Integer deliveryTimeUnit;
	
	@ApiModelProperty(value = "报货方式 0-电话 1-传真 2-邮件 3-系统接口")
    private Integer deliveryMode;

    /**
     * 报货时间delivery_time_unit=0时，delivery _time含义为每周（0~6：周一~周日）delivery_time_unit=1时，delivery_time含义为（1~31）日；
     */
	@ApiModelProperty(value = "报货时间delivery_time_unit=0时，，delivery_time含义为（1~31）日delivery_time_unit=1时,delivery _time含义为每周（0~6：周一~周日）")
    private String deliveryTime;

	@ApiModelProperty(value = "报货时间中文值处理")
	private String deliveryTimeStr;

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
	@ApiModelProperty(value = "送货时间send_time_unit=0时，send_time含义为（1~31）日send_time_unit=1时，send_time含义为每周（0~6：周一~周日）；")
    private String sendTime;

	@ApiModelProperty(value = "送货时间中文值处理")
	private String sendTimeStr;

    /**
     * 送达周期（0-天；1-月）
     */
	@ApiModelProperty(value = "送达周期（0-天；1-月）")
    private Integer serviceCircle;
	
	/**
     * 送达时间
     */
	@ApiModelProperty(value = "送达时间")
    private Integer serviceCircleUnit;

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

	@ApiModelProperty(value = "对账时间中文值处理")
	private String accountTimeStr;

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

	
	public Integer getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(Integer deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public Integer getServiceCircleUnit() {
		return serviceCircleUnit;
	}

	public void setServiceCircleUnit(Integer serviceCircleUnit) {
		this.serviceCircleUnit = serviceCircleUnit;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public void setAccountTime(String accountTime) {
		this.accountTime = accountTime;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public String getSendTime() {
		return sendTime;
	}

	public String getAccountTime() {
		return accountTime;
	}

	public Integer getManagementMode() {
		return managementMode;
	}

	public void setManagementMode(Integer managementMode) {
		this.managementMode = managementMode;
	}

	public String getPaymentTimeStr() {
		return paymentTimeStr;
	}

	public void setPaymentTimeStr(String paymentTimeStr) {
		this.paymentTimeStr = paymentTimeStr;
	}

	public String getDeliveryTimeStr() {
		return deliveryTimeStr;
	}

	public void setDeliveryTimeStr(String deliveryTimeStr) {
		this.deliveryTimeStr = deliveryTimeStr;
	}

	public String getSendTimeStr() {
		return sendTimeStr;
	}

	public void setSendTimeStr(String sendTimeStr) {
		this.sendTimeStr = sendTimeStr;
	}

	public String getAccountTimeStr() {
		return accountTimeStr;
	}

	public void setAccountTimeStr(String accountTimeStr) {
		this.accountTimeStr = accountTimeStr;
	}
}
