package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_retail_payment
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class RetailPaymentExportVO implements Serializable {

    /**
     * 第一行标题
     */
    @ApiModelProperty(value = "第一行标题")
    private String titleFirstRow;

    /**
     * 第二行标题
     */
    @ApiModelProperty(value = "第二行标题")
    private String titleSecondRow;

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
     * 企业编码
     */
    @ApiModelProperty(value = "企业编码")
    private String enterpriseCode;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    /**
     * 缴款日期
     */
    @ApiModelProperty(value = "缴款日期")
    private Date paymentDate;

    /**
     * 缴款单号
     */
    @ApiModelProperty(value = "缴款单号")
    private String code;

    /**
     * 缴款人员ID
     */
    @ApiModelProperty(value = "缴款人员ID")
    private Long paymentManId;

    /**
     * 缴款人员编码
     */
    @ApiModelProperty(value = "缴款人员编码")
    private String paymentManCode;

    /**
     * 缴款人员名称
     */
    @ApiModelProperty(value = "缴款人员名称")
    private String paymentManName;

    /**
     * 应缴现金合计
     */
    @ApiModelProperty(value = "应缴现金合计")
    private BigDecimal cashTotal;

    /**
     * 实缴现金合计
     */
    @ApiModelProperty(value = "实缴现金合计")
    private BigDecimal realCashTotal;

    /**
     * 现金差额合计
     */
    @ApiModelProperty(value = "现金差额合计")
    private BigDecimal diffCashTotal;

    /**
     * 应缴银行存款合计
     */
    @ApiModelProperty(value = "应缴银行存款合计")
    private BigDecimal bankAmountTotal;

    /**
     * 应缴其它货币资金合计
     */
    @ApiModelProperty(value = "应缴其它货币资金合计")
    private BigDecimal otherAmountTotal;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "缴款明细集合")
    private List<RetailPaymentDetailExportVO> retailPaymentDetailExportVOS;

    /**
     * saas_retail_payment
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

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getPaymentManId() {
        return paymentManId;
    }

    public void setPaymentManId(Long paymentManId) {
        this.paymentManId = paymentManId;
    }

    public String getPaymentManCode() {
        return paymentManCode;
    }

    public void setPaymentManCode(String paymentManCode) {
        this.paymentManCode = paymentManCode;
    }

    public String getPaymentManName() {
        return paymentManName;
    }

    public void setPaymentManName(String paymentManName) {
        this.paymentManName = paymentManName;
    }

    public BigDecimal getCashTotal() {
        return cashTotal;
    }

    public void setCashTotal(BigDecimal cashTotal) {
        this.cashTotal = cashTotal;
    }

    public BigDecimal getRealCashTotal() {
        return realCashTotal;
    }

    public void setRealCashTotal(BigDecimal realCashTotal) {
        this.realCashTotal = realCashTotal;
    }

    public BigDecimal getDiffCashTotal() {
        return diffCashTotal;
    }

    public void setDiffCashTotal(BigDecimal diffCashTotal) {
        this.diffCashTotal = diffCashTotal;
    }

    public BigDecimal getBankAmountTotal() {
        return bankAmountTotal;
    }

    public void setBankAmountTotal(BigDecimal bankAmountTotal) {
        this.bankAmountTotal = bankAmountTotal;
    }

    public BigDecimal getOtherAmountTotal() {
        return otherAmountTotal;
    }

    public void setOtherAmountTotal(BigDecimal otherAmountTotal) {
        this.otherAmountTotal = otherAmountTotal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<RetailPaymentDetailExportVO> getRetailPaymentDetailExportVOS() {
        return retailPaymentDetailExportVOS;
    }

    public void setRetailPaymentDetailExportVOS(List<RetailPaymentDetailExportVO> retailPaymentDetailExportVOS) {
        this.retailPaymentDetailExportVOS = retailPaymentDetailExportVOS;
    }

    public String getTitleFirstRow() {
        return titleFirstRow;
    }

    public void setTitleFirstRow(String titleFirstRow) {
        this.titleFirstRow = titleFirstRow;
    }

    public String getTitleSecondRow() {
        return titleSecondRow;
    }

    public void setTitleSecondRow(String titleSecondRow) {
        this.titleSecondRow = titleSecondRow;
    }

    @Override
    public String toString() {
        return "RetailPaymentExportVO{" +
                "id=" + id +
                ", titleFirstRow=" + titleFirstRow +
                ", titleSecondRow=" + titleSecondRow +
                ", enterpriseId=" + enterpriseId +
                ", enterpriseCode='" + enterpriseCode + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", paymentDate=" + paymentDate +
                ", code='" + code + '\'' +
                ", paymentManId=" + paymentManId +
                ", paymentManCode='" + paymentManCode + '\'' +
                ", paymentManName='" + paymentManName + '\'' +
                ", cashTotal=" + cashTotal +
                ", realCashTotal=" + realCashTotal +
                ", diffCashTotal=" + diffCashTotal +
                ", bankAmountTotal=" + bankAmountTotal +
                ", otherAmountTotal=" + otherAmountTotal +
                ", remark='" + remark + '\'' +
                ", retailPaymentDetailExportVOS=" + retailPaymentDetailExportVOS +
                '}';
    }
}