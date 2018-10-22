package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

public class TotalPurchaseInStorageReportVO<T> {

    /**
     * 单据集合
     */
    @ApiModelProperty("单据集合")
    private List<T> data;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantityAll;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal amountAll;

    /**
     * 实际金额
     */
    @ApiModelProperty(value = "实际金额")
    private BigDecimal realAmountAll;

    /**
     * 不含税金额
     */
    @ApiModelProperty(value = "不含税金额")
    private BigDecimal notaxRealAmountAll;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmountAll;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public BigDecimal getQuantityAll() {
        return quantityAll;
    }

    public void setQuantityAll(BigDecimal quantityAll) {
        this.quantityAll = quantityAll;
    }

    public BigDecimal getAmountAll() {
        return amountAll;
    }

    public void setAmountAll(BigDecimal amountAll) {
        this.amountAll = amountAll;
    }

    public BigDecimal getRealAmountAll() {
        return realAmountAll;
    }

    public void setRealAmountAll(BigDecimal realAmountAll) {
        this.realAmountAll = realAmountAll;
    }

    public BigDecimal getNotaxRealAmountAll() {
        return notaxRealAmountAll;
    }

    public void setNotaxRealAmountAll(BigDecimal notaxRealAmountAll) {
        this.notaxRealAmountAll = notaxRealAmountAll;
    }

    public BigDecimal getTaxAmountAll() {
        return taxAmountAll;
    }

    public void setTaxAmountAll(BigDecimal taxAmountAll) {
        this.taxAmountAll = taxAmountAll;
    }

    @Override
    public String toString() {
        return "TotalPurchaseInStorageReportVO[" +
                "data=" + data +
                ", quantityAll=" + quantityAll +
                ", amountAll=" + amountAll +
                ", realAmountAll=" + realAmountAll +
                ", notaxRealAmountAll=" + notaxRealAmountAll +
                ", taxAmountAll=" + taxAmountAll +
                ']';
    }
}
