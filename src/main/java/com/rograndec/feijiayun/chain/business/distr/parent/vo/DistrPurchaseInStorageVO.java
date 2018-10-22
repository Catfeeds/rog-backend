package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class DistrPurchaseInStorageVO implements Serializable {

    /**
     * 采购入库ID
     */
    @ApiModelProperty(required = true, value = "采购入库ID")
    private Long callDataId;

    /**
     * 采购入库类型ID
     */
    @ApiModelProperty(required = true, value = "采购入库类型ID")
    private Integer callDataType;

    /**
     * 数量合计
     */
    @ApiModelProperty(required = true, value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(required = true, value = "品种数量")
    private Integer varietiesQuantity;

    /**
     * 金额合计（整单优惠前的金额合计）
     */
    @ApiModelProperty(required = true, value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(required = true, value = "整单折扣（%）")
    private BigDecimal wholeDiscount;

    /**
     * 整单优惠金额
     */
    @ApiModelProperty(required = true, value = "整单优惠金额")
    private BigDecimal wholeDiscountAmount;

    /**
     * 实际金额合计
     */
    @ApiModelProperty(required = true, value = "实际金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(required = true, value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(required = true, value = "税额合计")
    private BigDecimal taxAmountTotal;

    List<DistrPurchaseInstorageDetailShelfVO> distrPurchaseInstorageDetailShelfVOList;

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    public Long getCallDataId() {
        return callDataId;
    }

    public void setCallDataId(Long callDataId) {
        this.callDataId = callDataId;
    }

    public Integer getCallDataType() {
        return callDataType;
    }

    public void setCallDataType(Integer callDataType) {
        this.callDataType = callDataType;
    }

    public List<DistrPurchaseInstorageDetailShelfVO> getDistrPurchaseInstorageDetailShelfVOList() {
        return distrPurchaseInstorageDetailShelfVOList;
    }

    public void setDistrPurchaseInstorageDetailShelfVOList(List<DistrPurchaseInstorageDetailShelfVO> distrPurchaseInstorageDetailShelfVOList) {
        this.distrPurchaseInstorageDetailShelfVOList = distrPurchaseInstorageDetailShelfVOList;
    }

    @Override
    public String toString() {
        return "DistrPurchaseInStorageVO[" +
                "callDataId=" + callDataId +
                ", amountTotal=" + amountTotal +
                ", callDataType=" + callDataType +
                ", wholeDiscount=" + wholeDiscount +
                ", quantityTotal=" + quantityTotal +
                ", varietiesQuantity=" + varietiesQuantity +
                ", wholeDiscountAmount=" + wholeDiscountAmount +
                ", realAmountTotal=" + realAmountTotal +
                ", notaxRealAmountTotal=" + notaxRealAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ", distrPurchaseInstorageDetailShelfVOList=" + distrPurchaseInstorageDetailShelfVOList +
                ']';
    }
}
