package com.rograndec.feijiayun.chain.business.aftersale.recover.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
*
* @ClassName: RecoverPlanSaveOrupdateVO
* @Description:  售后管理-追回管理-追回计划-保存明细
* @author xingjian.lan
* @version 1.0
* @date 2017-10-16 17:51:12
*/
@ApiModel(value = "RequestRecordGoodsVO", description = "售后管理-追回管理-追回记录商品")
public class RequestRecordGoodsVO implements Serializable {

   private static final long serialVersionUID = 1L;

   /**
    * 基础数据明细ID
    */
   @ApiModelProperty(required = true, value = "基础数据明细ID")
   private Long baseOrderDtlId;

    /**
     * 已追回数量
     */
    @ApiModelProperty(required = true, value = "已追回数量")
    private BigDecimal recoverQuantity;

    /**
     * 未追回数量
     */
    @ApiModelProperty(required = true, value = "未追回数量")
    private BigDecimal unrecoverQuantity;

   /**
    * 未追回原因
    */
   @ApiModelProperty(required = true, value = "未追回原因")
   private String unrecoverReason;

    /**
     * 是否造成医疗事故
     */
    @ApiModelProperty(required = true, value = "是否造成医疗事故")
    private String medicalAccident;

    /**
     * 行号
     */
    @ApiModelProperty(required = true, value = "行号")
   private Integer lineNum;

    public Long getBaseOrderDtlId() {
        return baseOrderDtlId;
    }

    public void setBaseOrderDtlId(Long baseOrderDtlId) {
        this.baseOrderDtlId = baseOrderDtlId;
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

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }
}