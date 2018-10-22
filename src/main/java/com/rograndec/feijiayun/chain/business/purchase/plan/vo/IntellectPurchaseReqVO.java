package com.rograndec.feijiayun.chain.business.purchase.plan.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 智能采购请求VO
 */
public class IntellectPurchaseReqVO implements Serializable {

    @ApiModelProperty(value = "根据安全库存分析")
    private Boolean safety;

    @ApiModelProperty(value = "按照缺断货数量分析")
    private Boolean lack;

    @ApiModelProperty(value = "{lackDays}天内有销售，无库存的商品")
    private Integer lackDays;

    @ApiModelProperty(value = "按照动态存量要货")
    private Boolean dynamicStock;

    @ApiModelProperty(value = "{dynamicStockDays}天内有销售")
    private Integer dynamicStockDays;

    @ApiModelProperty(value = "不满足{undynamicStockQuantity}天内销售")
    private Integer undynamicStockDays;

    @ApiModelProperty(value = "是否考虑在途库存")
    private Boolean onPassage;


    public Boolean getSafety() {
        return safety;
    }

    public void setSafety(Boolean safety) {
        this.safety = safety;
    }

    public Boolean getLack() {
        return lack;
    }

    public void setLack(Boolean lack) {
        this.lack = lack;
    }

    public Integer getLackDays() {
        return lackDays;
    }

    public void setLackDays(Integer lackDays) {
        this.lackDays = lackDays;
    }

    public Boolean getDynamicStock() {
        return dynamicStock;
    }

    public void setDynamicStock(Boolean dynamicStock) {
        this.dynamicStock = dynamicStock;
    }

    public Integer getDynamicStockDays() {
        return dynamicStockDays;
    }

    public void setDynamicStockDays(Integer dynamicStockDays) {
        this.dynamicStockDays = dynamicStockDays;
    }

    public Integer getUndynamicStockDays() {
        return undynamicStockDays;
    }

    public void setUndynamicStockDays(Integer undynamicStockDays) {
        this.undynamicStockDays = undynamicStockDays;
    }

    public Boolean getOnPassage() {
        return onPassage;
    }

    public void setOnPassage(Boolean onPassage) {
        this.onPassage = onPassage;
    }
}


