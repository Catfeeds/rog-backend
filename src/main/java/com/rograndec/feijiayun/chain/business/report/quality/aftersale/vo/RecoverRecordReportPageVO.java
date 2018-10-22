package com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo;

import com.rograndec.feijiayun.chain.business.aftersale.recover.constant.RecoverHandleMeasuresEnum;
import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsDetailVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RecoverRecordReportPageVO extends BaseGoodsDetailVO implements Serializable{



    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("组织机构编码")
    private String enterpriseCode;

    @ApiModelProperty("组织机构名称")
    private String enterpriseName;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 通知日期
     */
    @ApiModelProperty(value = "日期")
    private Date planDate;

    /**
     * 追回责任人
     */
    @ApiModelProperty(value = "追回责任人")
    private String recoverMan;

    /**
     * 追回责任人电话
     */
    @ApiModelProperty(value = "追回责任人电话")
    private String recoverManPhone;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validDate;

    /**
     * 追回数量
     */
    @ApiModelProperty(value = "追回数量")
    private BigDecimal recoverQuantity;

    /**
     * 未追回数量
     */
    @ApiModelProperty(value = "未追回数量")
    private BigDecimal unrecoverQuantity;

    @ApiModelProperty(value = "追回原因")
    private String recoverReason;

    @ApiModelProperty(value = "追回处理描述（0-退回供货单位；1-销毁）")
    private Integer handleMeasures;

    /**
     * 追回处理描述（0-退回供货单位；1-销毁）
     */
    @ApiModelProperty(required = false, value = "追回处理描述（0-退回供货单位；1-销毁）")
    private String handleMeasuresDesc;

    public String getHandleMeasuresDesc() {
        return RecoverHandleMeasuresEnum.getName(handleMeasures);
    }

    public void setHandleMeasuresDesc(String handleMeasuresDesc) {
        this.handleMeasuresDesc = handleMeasuresDesc;
    }


    /**
     * 未追回原因
     */
    @ApiModelProperty(value = "未追回原因")
    private String unrecoverReason;

    /**
     * 是否医疗事故
     */
    @ApiModelProperty(value = "是否医疗事故")
    private String medicalAccident;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public String getRecoverMan() {
        return recoverMan;
    }

    public void setRecoverMan(String recoverMan) {
        this.recoverMan = recoverMan;
    }

    public String getRecoverManPhone() {
        return recoverManPhone;
    }

    public void setRecoverManPhone(String recoverManPhone) {
        this.recoverManPhone = recoverManPhone;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public BigDecimal getRecoverQuantity() {
        return recoverQuantity;
    }

    public void setRecoverQuantity(BigDecimal recoverQuantity) {
        this.recoverQuantity = recoverQuantity;
    }

    public BigDecimal getUnrecoverQuantity() {
        return unrecoverQuantity;
    }

    public void setUnrecoverQuantity(BigDecimal unrecoverQuantity) {
        this.unrecoverQuantity = unrecoverQuantity;
    }

    public String getRecoverReason() {
        return recoverReason;
    }

    public void setRecoverReason(String recoverReason) {
        this.recoverReason = recoverReason;
    }

    public String getUnrecoverReason() {
        return unrecoverReason;
    }

    public void setUnrecoverReason(String unrecoverReason) {
        this.unrecoverReason = unrecoverReason;
    }

    public String getMedicalAccident() {
        return medicalAccident;
    }

    public void setMedicalAccident(String medicalAccident) {
        this.medicalAccident = medicalAccident;
    }

    public Integer getHandleMeasures() {
        return handleMeasures;
    }

    public void setHandleMeasures(Integer handleMeasures) {
        this.handleMeasures = handleMeasures;
    }
}
