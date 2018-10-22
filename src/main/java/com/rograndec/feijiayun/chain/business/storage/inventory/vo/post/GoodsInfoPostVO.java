package com.rograndec.feijiayun.chain.business.storage.inventory.vo.post;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 功能描述：
 * Created by ST on 2017/10/7 15:47
 */

public class GoodsInfoPostVO {

    @ApiModelProperty("数量")
    private BigDecimal quantity;

    @ApiModelProperty("批号id")
    private Long lotId;

    @ApiModelProperty("商品id")
    private Long goodsId;


    @ApiModelProperty("单价")
    private BigDecimal realPrice;

    @ApiModelProperty("不含税实际单价")
    private BigDecimal notaxRealPrice;

    private BigDecimal taxRate;


    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }



    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public BigDecimal getNotaxRealPrice() {
        return notaxRealPrice;
    }

    public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
        this.notaxRealPrice = notaxRealPrice;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
}