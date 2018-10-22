package com.rograndec.feijiayun.chain.business.storage.chgoods.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsClearShelfSumVO implements Serializable {

    @ApiModelProperty(required = true, value = "金额合计")
    private BigDecimal amountTotal;

    @ApiModelProperty(required = true, value = "不含税金额合计")
    private BigDecimal notaxAmountTotal;

    @ApiModelProperty(required = true, value = "税额合计")
    private BigDecimal taxAmountTotal;

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    @Override
    public String toString() {
        return "GoodsClearShelfSumVO[" +
                "amountTotal=" + amountTotal +
                ", notaxAmountTotal=" + notaxAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ']';
    }
}
