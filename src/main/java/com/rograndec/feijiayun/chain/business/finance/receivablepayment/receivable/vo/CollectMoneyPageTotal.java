package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class CollectMoneyPageTotal implements Serializable{

    /**
     * 应收金额合计
     */
    @ApiModelProperty(value = "应收金额合计")
    private BigDecimal receivableAmountSummary;
    /**
     * 优惠金额合计
     */
    @ApiModelProperty(value = "优惠金额合计")
    private BigDecimal discountAmountSummary;
    /**
     * 实收金额合计
     */
    @ApiModelProperty(value = "实收金额合计")
    private BigDecimal realAmountSummary;

    public BigDecimal getReceivableAmountSummary() {
        return receivableAmountSummary;
    }

    public void setReceivableAmountSummary(BigDecimal receivableAmountSummary) {
        this.receivableAmountSummary = receivableAmountSummary;
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
