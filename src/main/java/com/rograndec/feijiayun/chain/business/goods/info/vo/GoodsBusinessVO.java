package com.rograndec.feijiayun.chain.business.goods.info.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品业务表
 * Created by ST on 2017/9/8.
 */
public class GoodsBusinessVO implements Serializable {

    private Long id;
    /**
     * 商品ID
     */
    private Long goodsId;

    @ApiModelProperty(value="进项税（%）ID")
    private Long purchaseTaxRateId;
    /**
     * 进项税（%）
     */
    @ApiModelProperty(value="进项税（%）")
    private BigDecimal purchaseTaxRate;

    /**
     * 销项税（%）
     */
    @ApiModelProperty(value="销项税（%）Id")
    private Long saleTaxRateId;
    /**
     * 销项税（%）
     */
    @ApiModelProperty(value="销项税（%）")
    private BigDecimal saleTaxRate;
    /**
     * 配货税
     */
    @ApiModelProperty(value="配货税（%）Id")
    private Long distrTaxRateId;
    /**
     * 配货税
     */
    @ApiModelProperty(value="配货税（%）")
    private BigDecimal distrTaxRate;

    /**
     * 经营方式（0-购销；1-实销实结）
     */
    @ApiModelProperty(value="经营方式（0-购销；1-实销实结）")
    private Integer managementMode;

    /**
     * 积分商品（0-否；1-是）
     */
    @ApiModelProperty(value="积分商品（0-否；1-是）")
    private Integer integralGoods;

    /**
     * 积分倍数
     */
    @ApiModelProperty(value="积分倍数")
    private BigDecimal integralMultiple;

    /**
     * 特价商品（0-否；1-是）
     */
    @ApiModelProperty(value="特价商品（0-否；1-是）")
    private Integer bargainGoods;

    /**
     * 提成商品（0-否；1-是）
     */
    @ApiModelProperty(value="提成商品（0-否；1-是）")
    private Integer commissionGoods;

    /**
     * 零售基价
     */
    @ApiModelProperty(value="零售基价")
    private BigDecimal retailPrice;

    /**
     * 会员基价
     */
    @ApiModelProperty(value="会员基价")
    private BigDecimal memberPrice;

    /**
     * 配货基价
     */
    @ApiModelProperty(value="配货基价")
    private BigDecimal distrPrice;

    /**
     * 销售定价策略：0总部统一定价,1允许门店自主定价.总部添加的商品默认总部统一定价， 加盟店添加的商品默认允许门店自主定价且只读
     */
    @ApiModelProperty(value="销售定价策略：0总部统一定价,1允许门店自主定价.总部添加的商品默认总部统一定价， 加盟店添加的商品默认允许门店自主定价且只读")
    private Integer pricingPolicy;

    public BigDecimal getPurchaseTaxRate() {
        return purchaseTaxRate;
    }

    public void setPurchaseTaxRate(BigDecimal purchaseTaxRate) {
        this.purchaseTaxRate = purchaseTaxRate;
    }

    public BigDecimal getSaleTaxRate() {
        return saleTaxRate;
    }

    public void setSaleTaxRate(BigDecimal saleTaxRate) {
        this.saleTaxRate = saleTaxRate;
    }

    public BigDecimal getDistrTaxRate() {
        return distrTaxRate;
    }

    public void setDistrTaxRate(BigDecimal distrTaxRate) {
        this.distrTaxRate = distrTaxRate;
    }

    public Integer getManagementMode() {
        return managementMode;
    }

    public void setManagementMode(Integer managementMode) {
        this.managementMode = managementMode;
    }

    public Integer getIntegralGoods() {
        return integralGoods;
    }

    public void setIntegralGoods(Integer integralGoods) {
        this.integralGoods = integralGoods;
    }

    public BigDecimal getIntegralMultiple() {
        return integralMultiple;
    }

    public void setIntegralMultiple(BigDecimal integralMultiple) {
        this.integralMultiple = integralMultiple;
    }

    public Integer getBargainGoods() {
        return bargainGoods;
    }

    public void setBargainGoods(Integer bargainGoods) {
        this.bargainGoods = bargainGoods;
    }

    public Integer getCommissionGoods() {
        return commissionGoods;
    }

    public void setCommissionGoods(Integer commissionGoods) {
        this.commissionGoods = commissionGoods;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(BigDecimal memberPrice) {
        this.memberPrice = memberPrice;
    }

    public BigDecimal getDistrPrice() {
        return distrPrice;
    }

    public void setDistrPrice(BigDecimal distrPrice) {
        this.distrPrice = distrPrice;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPurchaseTaxRateId() {
        return purchaseTaxRateId;
    }

    public void setPurchaseTaxRateId(Long purchaseTaxRateId) {
        this.purchaseTaxRateId = purchaseTaxRateId;
    }

    public Long getSaleTaxRateId() {
        return saleTaxRateId;
    }

    public void setSaleTaxRateId(Long saleTaxRateId) {
        this.saleTaxRateId = saleTaxRateId;
    }

    public Long getDistrTaxRateId() {
        return distrTaxRateId;
    }

    public void setDistrTaxRateId(Long distrTaxRateId) {
        this.distrTaxRateId = distrTaxRateId;
    }

    public Integer getPricingPolicy() {
        return pricingPolicy;
    }

    public void setPricingPolicy(Integer pricingPolicy) {
        this.pricingPolicy = pricingPolicy;
    }
}