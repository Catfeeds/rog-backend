package com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PaymentPageVO implements Serializable{

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private Date paymentDate;
    /**
     * 编码
     */
    @ApiModelProperty(value = "单号")
    private String code;

    /**
     * 收款单位类型
     */
    @ApiModelProperty(value = "收款单位类型 0-供货单位；1-购货单位")
    private Integer companyType;

    /**
     * 收款单位编码
     */
    @ApiModelProperty(value = "收款单位编码")
    private String companyCode;

    /**
     * 收款单位名称
     */
    @ApiModelProperty(value = "收款单位名称")
    private String companyName;

    /**
     * 付款人员
     */
    @ApiModelProperty(value = "付款人员")
    private String paymentManName;

    /**
     * 应付金额
     */
    @ApiModelProperty(value = "应付金额")
    private BigDecimal paymentAmountTotal;

    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    private BigDecimal discountAmountTotal;

    /**
     * 实付金额
     */
    @ApiModelProperty(value = "实付金额")
    private BigDecimal realAmountTotal;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 状态
     */
    @ApiModelProperty(value = "0-待付款；1-已完成；2-已冲销")
    private Integer status;

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

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPaymentManName() {
        return paymentManName;
    }

    public void setPaymentManName(String paymentManName) {
        this.paymentManName = paymentManName;
    }

    public BigDecimal getPaymentAmountTotal() {
        return paymentAmountTotal;
    }

    public void setPaymentAmountTotal(BigDecimal paymentAmountTotal) {
        this.paymentAmountTotal = paymentAmountTotal;
    }

    public BigDecimal getDiscountAmountTotal() {
        return discountAmountTotal;
    }

    public void setDiscountAmountTotal(BigDecimal discountAmountTotal) {
        this.discountAmountTotal = discountAmountTotal;
    }

    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PaymentPageVO{" +
                "id=" + id +
                ", paymentDate=" + paymentDate +
                ", code='" + code + '\'' +
                ", companyType=" + companyType +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", paymentManName='" + paymentManName + '\'' +
                ", paymentAmountTotal=" + paymentAmountTotal +
                ", discountAmountTotal=" + discountAmountTotal +
                ", realAmountTotal=" + realAmountTotal +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                '}';
    }
}
