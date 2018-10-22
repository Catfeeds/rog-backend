package com.rograndec.feijiayun.chain.business.goods.price.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApiModel
public class AddOrUpdateAdjustDetailVO implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "价格调整明显id,修改时需要传入,新增时不需要")
    private Long id;

    /**
     * 价格调整单ID
     */
    @ApiModelProperty(value = "价格调整单ID",required = true)
    @NotNull(message = "价格调整单不能为空")
    private Long adjustId;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID",required = true)
    @NotNull(message = "商品不能为空")
    private Long goodsId;

    /**
     *配货价格
     */
    @ApiModelProperty(value = "配货价格",required = true)
    @NotNull(message = "配货价格不能为空")
    private BigDecimal distrPrice;


    /**
     * 配货税率
     */
    @ApiModelProperty(value = "原配货价格",required = true)
    @NotNull(message = "原配货价格不能为空")
    private Long distrTaxRateId;


    /**
     * 不含税配货单价
     */
    @ApiModelProperty(value = "不含税配货单价",required = true)
    @NotNull(message = "不含税配货单价不能为空")
    private BigDecimal notaxDistrPrice;


    /**
     * 零售单价
     */
    @ApiModelProperty(value = "零售单价",required = true)
    @NotNull(message = "零售单价不能为空")
    private BigDecimal retailPrice;


    /**
     * 会员单价
     */
    @ApiModelProperty(value = "会员单价",required = true)
    @NotNull(message = "会员单价不能为空")
    private BigDecimal memberPrice;


    /**
     * 销项税
     */
    @ApiModelProperty(value = "销项税",required = true)
    @NotNull(message = "销项税不能为空")
    private Long saleTaxRateId;


    /**
     * 不含税零售单价
     */
    @ApiModelProperty(value = "不含税零售单价",required = true)
    @NotNull(message = "不含税零售单价不能为空")
    private BigDecimal notaxRetailPrice;


    /**
     * 不含税会员单价
     */
    @ApiModelProperty(value = "不含税会员单价",required = true)
    @NotNull(message = "不含税会员单价不能为空")
    private BigDecimal notaxMemberPrice;

    public static Set<Long> getTaxRateIds(List<AddOrUpdateAdjustDetailVO> addOrUpdateAdjustDetailVOS){
        Set<Long> ids = new HashSet<>();

        for(AddOrUpdateAdjustDetailVO addOrUpdatePriceAdjustVO : addOrUpdateAdjustDetailVOS){
            ids.add(addOrUpdatePriceAdjustVO.getDistrTaxRateId());
            ids.add(addOrUpdatePriceAdjustVO.getSaleTaxRateId());
        }

        return ids;
    }

    public static List<Long> getGoodsIds(List<AddOrUpdateAdjustDetailVO> addOrUpdateAdjustDetailVOS){
        List<Long> ids = new ArrayList<>();

        for(AddOrUpdateAdjustDetailVO addOrUpdatePriceAdjustVO : addOrUpdateAdjustDetailVOS){
            ids.add(addOrUpdatePriceAdjustVO.getGoodsId());
        }

        return ids;
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

    public Long getDistrTaxRateId() {
        return distrTaxRateId;
    }

    public void setDistrTaxRateId(Long distrTaxRateId) {
        this.distrTaxRateId = distrTaxRateId;
    }

    public Long getSaleTaxRateId() {
        return saleTaxRateId;
    }

    public void setSaleTaxRateId(Long saleTaxRateId) {
        this.saleTaxRateId = saleTaxRateId;
    }
}