package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

public class TotalReportVO<T> {
    /**
     * 单据集合
     */
    @ApiModelProperty("单据集合")
    private List<T> data;
    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 金额合计
     */
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal;

    /**
     * 实际金额合计
     */
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmoutTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "订单数量合计")
    private BigDecimal orderQuantity;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "到货数量合计")
    private BigDecimal arrivalQuantity;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "收货数量合计")
    private BigDecimal receiveQuantity;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "拒收数量合计")
    private BigDecimal refuseQuantity;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

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

    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    public BigDecimal getTaxAmoutTotal() {
        return taxAmoutTotal;
    }

    public void setTaxAmoutTotal(BigDecimal taxAmoutTotal) {
        this.taxAmoutTotal = taxAmoutTotal;
    }

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getArrivalQuantity() {
        return arrivalQuantity;
    }

    public void setArrivalQuantity(BigDecimal arrivalQuantity) {
        this.arrivalQuantity = arrivalQuantity;
    }

    public BigDecimal getReceiveQuantity() {
        return receiveQuantity;
    }

    public void setReceiveQuantity(BigDecimal receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    public BigDecimal getRefuseQuantity() {
        return refuseQuantity;
    }

    public void setRefuseQuantity(BigDecimal refuseQuantity) {
        this.refuseQuantity = refuseQuantity;
    }

    @Override
    public String toString() {
        return "TotalReportVO{" +
                "data=" + data +
                ", quantityTotal=" + quantityTotal +
                ", amountTotal=" + amountTotal +
                ", realAmountTotal=" + realAmountTotal +
                ", notaxAmountTotal=" + notaxAmountTotal +
                ", taxAmoutTotal=" + taxAmoutTotal +
                ", orderQuantity=" + orderQuantity +
                ", arrivalQuantity=" + arrivalQuantity +
                ", receiveQuantity=" + receiveQuantity +
                ", refuseQuantity=" + refuseQuantity +
                '}';
    }
}
