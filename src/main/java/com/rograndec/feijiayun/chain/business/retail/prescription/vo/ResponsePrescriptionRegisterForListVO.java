package com.rograndec.feijiayun.chain.business.retail.prescription.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * saas_prescription_register
 * 
 * 处方登记列表查询返回实体类
 * @author ST
 * 
 * 2017-09-22
 */
public class ResponsePrescriptionRegisterForListVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "组织结构编码")
    private String enterpriseCode;

    @ApiModelProperty(value = "组织结构名称")
    private String enterpriseName;


    /**
     * 登记日期
     */
    @ApiModelProperty(value = "登记日期")
    private Date registerDate;

    /**
     * 登记单号
     */
    @ApiModelProperty(value = "登记单号")
    private String prescriptionCode;

    /**
     * 处方单号
     */
    @ApiModelProperty(value = "处方单号")
    private String code;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "基础单据编码")
    private String baseOrderCode;

    /**
     * 登记人员ID
     */
    @ApiModelProperty(value = "登记人员ID")
    private Long registerManId;

    /**
     * 登记人员编码
     */
    @ApiModelProperty(value = "登记人员编码")
    private String registerManCode;

    /**
     * 登记人员名称
     */
    @ApiModelProperty(value = "登记人员名称")
    private String registerManName;

    /**
     * 开具日期
     */
    @ApiModelProperty(value = "开具日期")
    private Date billingDate;

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
    @ApiModelProperty(value = "患者姓名")
    private String name;

    /**
     * 实际金额合计
     */
    @ApiModelProperty(value = "金额）")
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
     * 审核人名称
     */
    @ApiModelProperty(value = "审核人名称")
    private String auditorName;


    /**
     * 调剂人名称
     */
    @ApiModelProperty(value = "调剂人名称")
    private String swapManName;

    /**
     * 核对人名称
     */
    @ApiModelProperty(value = "核对人名称")
    private String checkerName;


    /**
     * 发药人名称
     */
    @ApiModelProperty(value = "发药人名称")
    private String sendName;

    /**
     *
     */
    @ApiModelProperty(value = "状态（21-待审核 31-待调配 32-待收款 33-已完成 34-已取消）")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getPrescriptionCode() {
        return prescriptionCode;
    }

    public void setPrescriptionCode(String prescriptionCode) {
        this.prescriptionCode = prescriptionCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getRegisterManId() {
        return registerManId;
    }

    public void setRegisterManId(Long registerManId) {
        this.registerManId = registerManId;
    }

    public String getRegisterManCode() {
        return registerManCode;
    }

    public void setRegisterManCode(String registerManCode) {
        this.registerManCode = registerManCode;
    }

    public String getRegisterManName() {
        return registerManName;
    }

    public void setRegisterManName(String registerManName) {
        this.registerManName = registerManName;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
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


    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }


    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getSwapManName() {
        return swapManName;
    }

    public void setSwapManName(String swapManName) {
        this.swapManName = swapManName;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}