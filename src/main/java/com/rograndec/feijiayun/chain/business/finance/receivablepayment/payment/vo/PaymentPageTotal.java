package com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class PaymentPageTotal implements Serializable{
    /**
     * 应付金额合计
     */
    @ApiModelProperty(value = "应付金额合计")
    private BigDecimal paymentAmountSummary;
    /**
     * 优惠金额合计
     */
    @ApiModelProperty(value = "优惠金额合计")
    private BigDecimal discountAmountSummary;
    /**
     * 实付金额合计
     */
    @ApiModelProperty(value = "实付金额合计")
    private BigDecimal realAmountSummary;

    public BigDecimal getPaymentAmountSummary() {
        return paymentAmountSummary;
    }

    public void setPaymentAmountSummary(BigDecimal paymentAmountSummary) {
        this.paymentAmountSummary = paymentAmountSummary;
    }

    public BigDecimal getDiscountAmountSummary() {
        return discountAmountSummary;
    }

    public void setDiscountAmountSummary(BigDecimal discountAmountSummary) {
        this.discountAmountSummary = discountAmountSummary;
    }

    public BigDecimal getRealAmountSummary() {
        return realAmountSummary;
    }

    public void setRealAmountSummary(BigDecimal realAmountSummary) {
        this.realAmountSummary = realAmountSummary;
    }
}
