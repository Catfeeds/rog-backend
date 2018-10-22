package com.rograndec.feijiayun.chain.inf.pos.prescription.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PrescriptionVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月7日 下午1:44:28 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
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
     * 开具日期
     */
    @ApiModelProperty(value = "开具日期")
    private Date billingDate;
    
    /**
     * 处方单号
     */
    @ApiModelProperty(value = "处方单号")
    private String prescriptionCode;
    
    /**
     * 医疗机构编码
     */
    @ApiModelProperty(value = "医疗机构编码")
    private String medicalOrgCode;

    /**
     * 医疗机构名称
     */
    @ApiModelProperty(value = "医疗机构名称")
    private String medicalOrgName;
    
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 性别（0-女；1-男）
     */
    @ApiModelProperty(value = "性别（0-女；1-男）")
    private Integer sex;
    
    /**
     * 性别（0-女；1-男）
     */
    @ApiModelProperty(value = "性别（0-女；1-男）")
    private String sexName;
    
    /**
     * 病人标识-会员卡号
     */
    @ApiModelProperty(value = "病人标识-会员卡号")
    private String patientId;
    
    /**
     * 会员姓名
     */
    @ApiModelProperty(value = "会员姓名")
    private String memberName = "";
    
    /**
     * 当前积分
     */
    @ApiModelProperty(value = "当前积分")
    private BigDecimal currentIntegral = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;
    
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal;
    
    @ApiModelProperty(value = "整单折扣")
    private BigDecimal wholeDiscount;
    
    @ApiModelProperty(value = "整单优惠金额")
    private BigDecimal wholeDiscountAmount;
    
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;
    
    
    
    /**
     * 处方单药品明细
     */
    @ApiModelProperty(value = "处方单明细")
    private List<PrescriptionDetailVO> detailList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	public String getPrescriptionCode() {
		return prescriptionCode;
	}

	public void setPrescriptionCode(String prescriptionCode) {
		this.prescriptionCode = prescriptionCode;
	}

	public String getMedicalOrgCode() {
		return medicalOrgCode;
	}

	public void setMedicalOrgCode(String medicalOrgCode) {
		this.medicalOrgCode = medicalOrgCode;
	}

	public String getMedicalOrgName() {
		return medicalOrgName;
	}

	public void setMedicalOrgName(String medicalOrgName) {
		this.medicalOrgName = medicalOrgName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public List<PrescriptionDetailVO> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<PrescriptionDetailVO> detailList) {
		this.detailList = detailList;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public BigDecimal getCurrentIntegral() {
		return currentIntegral;
	}

	public void setCurrentIntegral(BigDecimal currentIntegral) {
		this.currentIntegral = currentIntegral;
	}

	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}

	public BigDecimal getWholeDiscount() {
		return wholeDiscount;
	}

	public void setWholeDiscount(BigDecimal wholeDiscount) {
		this.wholeDiscount = wholeDiscount;
	}

	public BigDecimal getWholeDiscountAmount() {
		return wholeDiscountAmount;
	}

	public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
		this.wholeDiscountAmount = wholeDiscountAmount;
	}

	public BigDecimal getRealAmountTotal() {
		return realAmountTotal;
	}

	public void setRealAmountTotal(BigDecimal realAmountTotal) {
		this.realAmountTotal = realAmountTotal;
	}
	
}
