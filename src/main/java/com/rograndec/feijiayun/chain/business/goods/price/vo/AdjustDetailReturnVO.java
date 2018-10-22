package com.rograndec.feijiayun.chain.business.goods.price.vo;

import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceAdjustDetail;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class AdjustDetailReturnVO implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "价格调整单明细ID")
    private Long id;


    /**
     * 价格调整单ID
     */
    @ApiModelProperty(value = "价格调整单ID")
    private Long adjustId;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private Long goodsId;

    /**
     * 配货价格
     */
    @ApiModelProperty(value = "配货价格")
    private BigDecimal distrPrice;

    /**
     * 原配货价格
     */
    @ApiModelProperty(value = "原配货价格")
    private BigDecimal oldDistrPrice;
    /**
     * 配货税率
     */
    @ApiModelProperty(value = "配货税率")
    private BigDecimal distrTaxRate;

    /**
     * 原配货税率
     */
    @ApiModelProperty(value = "原配货税率")
    private BigDecimal oldDistrTaxRate;

    /**
     * 不含税配货单价
     */
    @ApiModelProperty(value = "不含税配货单价")
    private BigDecimal notaxDistrPrice;

    /**
     * 原不含税配货单价
     */
    @ApiModelProperty(value = "原不含税配货单价")
    private BigDecimal oldNotaxDistrPrice;

    /**
     * 零售单价
     */
    @ApiModelProperty(value = "零售单价")
    private BigDecimal retailPrice;

    /**
     * 原零售单价
     */
    @ApiModelProperty(value = "原零售单价")
    private BigDecimal oldRetailPrice;

    /**
     * 会员单价
     */
    @ApiModelProperty(value = "会员单价")
    private BigDecimal memberPrice;

    /**
     * 原会员单价
     */
    @ApiModelProperty(value = "原会员单价")
    private BigDecimal oldMemberPrice;

    /**
     * 销项税
     */
    @ApiModelProperty(value = "销项税")
    private BigDecimal saleTaxRate;

    /**
     * 原销项税
     */
    @ApiModelProperty(value = "原销项税")
    private BigDecimal oldSaleTaxRate;
    /**
     * 不含税零售单价
     */
    @ApiModelProperty(value = "不含税零售单价")
    private BigDecimal notaxRetailPrice;

    /**
     * 原不含税零售单价
     */
    @ApiModelProperty(value = "原不含税零售单价")
    private BigDecimal oldNotaxRetailPrice;

    /**
     * 不含税会员单价
     */
    @ApiModelProperty(value = "不含税会员单价")
    private BigDecimal notaxMemberPrice;

    /**
     * 原不含税会员单价
     */
    @ApiModelProperty(value = "原不含税会员单价")
    private BigDecimal oldNotaxMemberPrice;


    public static AdjustDetailReturnVO getPriceAdjustGoods4AdjustDetail(PriceAdjustDetail priceAdjustDetail){

        AdjustDetailReturnVO priceAdjustGoodsVO = new AdjustDetailReturnVO();
        priceAdjustGoodsVO.setId(priceAdjustDetail.getId());
        priceAdjustGoodsVO.setGoodsId(priceAdjustDetail.getGoodsId());
        priceAdjustGoodsVO.setAdjustId(priceAdjustDetail.getId());
        priceAdjustGoodsVO.setDistrPrice(priceAdjustDetail.getDistrPrice());
        priceAdjustGoodsVO.setOldDistrPrice(priceAdjustDetail.getOldDistrPrice());
        priceAdjustGoodsVO.setDistrTaxRate(priceAdjustDetail.getDistrTaxRate());
        priceAdjustGoodsVO.setOldDistrTaxRate(priceAdjustDetail.getOldDistrTaxRate());
        priceAdjustGoodsVO.setNotaxDistrPrice(priceAdjustDetail.getNotaxDistrPrice());
        priceAdjustGoodsVO.setOldNotaxDistrPrice(priceAdjustDetail.getOldNotaxDistrPrice());
        priceAdjustGoodsVO.setRetailPrice(priceAdjustDetail.getRetailPrice());
        priceAdjustGoodsVO.setOldRetailPrice(priceAdjustDetail.getOldRetailPrice());
        priceAdjustGoodsVO.setMemberPrice(priceAdjustDetail.getMemberPrice());
        priceAdjustGoodsVO.setOldMemberPrice(priceAdjustDetail.getOldMemberPrice());
        priceAdjustGoodsVO.setSaleTaxRate(priceAdjustDetail.getSaleTaxRate());
        priceAdjustGoodsVO.setOldSaleTaxRate(priceAdjustDetail.getOldSaleTaxRate());
        priceAdjustGoodsVO.setNotaxRetailPrice(priceAdjustDetail.getNotaxRetailPrice());
        priceAdjustGoodsVO.setOldNotaxRetailPrice(priceAdjustDetail.getOldNotaxRetailPrice());
        priceAdjustGoodsVO.setNotaxMemberPrice(priceAdjustDetail.getNotaxMemberPrice());
        priceAdjustGoodsVO.setOldNotaxMemberPrice(priceAdjustDetail.getOldNotaxMemberPrice());

        return priceAdjustGoodsVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdjustId() {
        return adjustId;
    }

    public void setAdjustId(Long adjustId) {
        this.adjustId = adjustId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getDistrPrice() {
        return distrPrice;
    }

    public void setDistrPrice(BigDecimal distrPrice) {
        this.distrPrice = distrPrice;
    }

    public BigDecimal getDistrTaxRate() {
        return distrTaxRate;
    }

    public void setDistrTaxRate(BigDecimal distrTaxRate) {
        this.distrTaxRate = distrTaxRate;
    }

    public BigDecimal getNotaxDistrPrice() {
        return notaxDistrPrice;
    }

    public void setNotaxDistrPrice(BigDecimal notaxDistrPrice) {
        this.notaxDistrPrice = notaxDistrPrice;
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

    public BigDecimal getSaleTaxRate() {
        return saleTaxRate;
    }

    public void setSaleTaxRate(BigDecimal saleTaxRate) {
        this.saleTaxRate = saleTaxRate;
    }

    public BigDecimal getNotaxRetailPrice() {
        return notaxRetailPrice;
    }

    public void setNotaxRetailPrice(BigDecimal notaxRetailPrice) {
        this.notaxRetailPrice = notaxRetailPrice;
    }

    public BigDecimal getNotaxMemberPrice() {
        return notaxMemberPrice;
    }

    public void setNotaxMemberPrice(BigDecimal notaxMemberPrice) {
        this.notaxMemberPrice = notaxMemberPrice;
    }

    public BigDecimal getOldDistrPrice() {
        return oldDistrPrice;
    }

    public void setOldDistrPrice(BigDecimal oldDistrPrice) {
        this.oldDistrPrice = oldDistrPrice;
    }

    public BigDecimal getOldDistrTaxRate() {
        return oldDistrTaxRate;
    }

    public void setOldDistrTaxRate(BigDecimal oldDistrTaxRate) {
        this.oldDistrTaxRate = oldDistrTaxRate;
    }

    public BigDecimal getOldNotaxDistrPrice() {
        return oldNotaxDistrPrice;
    }

    public void setOldNotaxDistrPrice(BigDecimal oldNotaxDistrPrice) {
        this.oldNotaxDistrPrice = oldNotaxDistrPrice;
    }

    public BigDecimal getOldRetailPrice() {
        return oldRetailPrice;
    }

    public void setOldRetailPrice(BigDecimal oldRetailPrice) {
        this.oldRetailPrice = oldRetailPrice;
    }

    public BigDecimal getOldMemberPrice() {
        return oldMemberPrice;
    }

    public void setOldMemberPrice(BigDecimal oldMemberPrice) {
        this.oldMemberPrice = oldMemberPrice;
    }

    public BigDecimal getOldSaleTaxRate() {
        return oldSaleTaxRate;
    }

    public void setOldSaleTaxRate(BigDecimal oldSaleTaxRate) {
        this.oldSaleTaxRate = oldSaleTaxRate;
    }

    public BigDecimal getOldNotaxRetailPrice() {
        return oldNotaxRetailPrice;
    }

    public void setOldNotaxRetailPrice(BigDecimal oldNotaxRetailPrice) {
        this.oldNotaxRetailPrice = oldNotaxRetailPrice;
    }

    public BigDecimal getOldNotaxMemberPrice() {
        return oldNotaxMemberPrice;
    }

    public void setOldNotaxMemberPrice(BigDecimal oldNotaxMemberPrice) {
        this.oldNotaxMemberPrice = oldNotaxMemberPrice;
    }
}