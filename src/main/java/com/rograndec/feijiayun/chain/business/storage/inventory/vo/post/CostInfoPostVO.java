package com.rograndec.feijiayun.chain.business.storage.inventory.vo.post;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 功能描述：
 * Created by ST on 2017/10/7 15:47
 */

public class CostInfoPostVO {

    @ApiModelProperty("总数量")
    private BigDecimal quantityTotal;

    @ApiModelProperty("总金额")
    private BigDecimal realAmountTotal;

    @ApiModelProperty("不含税总金额")
    private BigDecimal notaxRealAmountTotal;

    @ApiModelProperty("实际可用总金额")
    private BigDecimal usableRealAmout;

    @ApiModelProperty("不含税实际可用总金额")
    private BigDecimal notaxUsableRealAmout;

    @ApiModelProperty
    private BigDecimal notaxCostAmountTotal;


    public BigDecimal getNotaxCostAmountTotal() {
        return notaxCostAmountTotal;
    }

    public void setNotaxCostAmountTotal(BigDecimal notaxCostAmountTotal) {
        this.notaxCostAmountTotal = notaxCostAmountTotal;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
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

    public BigDecimal getUsableRealAmout() {
        return usableRealAmout;
    }

    public void setUsableRealAmout(BigDecimal usableRealAmout) {
        this.usableRealAmout = usableRealAmout;
    }

    public BigDecimal getNotaxUsableRealAmout() {
        return notaxUsableRealAmout;
    }

    public void setNotaxUsableRealAmout(BigDecimal notaxUsableRealAmout) {
        this.notaxUsableRealAmout = notaxUsableRealAmout;
    }
}