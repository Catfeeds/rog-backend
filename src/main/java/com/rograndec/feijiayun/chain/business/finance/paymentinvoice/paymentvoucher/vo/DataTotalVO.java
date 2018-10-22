package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 功能描述：数据合计VO
 * Created by ST on 2018/1/10 14:03
 */

public class DataTotalVO {

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantityTotal;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal amountTotal;

    /**
     * 不含税金额
     */
    @ApiModelProperty(value = "不含税金额")
    private BigDecimal notaxAmountTotal;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmountTotal;

//    /**
//     * 金额差额
//     */
//    @ApiModelProperty(value = "金额差额")
//    private BigDecimal diffAmountTotal;
//
//    /**
//     * 不含税金额差额
//     */
//    @ApiModelProperty(value = "不含税金额差额")
//    private BigDecimal diffNotaxAmountTotal;
//
//    /**
//     * 税额差额
//     */
//    @ApiModelProperty(value = "税额差额")
//    private BigDecimal diffTaxAmountTotal;

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

//    public BigDecimal getDiffAmountTotal() {
//        return diffAmountTotal;
//    }
//
//    public void setDiffAmountTotal(BigDecimal diffAmountTotal) {
//        this.diffAmountTotal = diffAmountTotal;
//    }
//
//    public BigDecimal getDiffNotaxAmountTotal() {
//        return diffNotaxAmountTotal;
//    }
//
//    public void setDiffNotaxAmountTotal(BigDecimal diffNotaxAmountTotal) {
//        this.diffNotaxAmountTotal = diffNotaxAmountTotal;
//    }
//
//    public BigDecimal getDiffTaxAmountTotal() {
//        return diffTaxAmountTotal;
//    }
//
//    public void setDiffTaxAmountTotal(BigDecimal diffTaxAmountTotal) {
//        this.diffTaxAmountTotal = diffTaxAmountTotal;
//    }
}