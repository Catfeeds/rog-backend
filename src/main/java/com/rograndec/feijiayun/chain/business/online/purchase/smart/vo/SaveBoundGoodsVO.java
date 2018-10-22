package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SaveBoundGoodsVO implements Serializable {

    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    @ApiModelProperty(value = "MPH_商品ID")
    private Long mphGoodsId;

    @ApiModelProperty(value = "MPH_商品名称")
    private String mphGoodsName;

    @ApiModelProperty(value = "MPH_商品产地")
    private String mphGoodsPlace;

    @ApiModelProperty(value = "MPH_商品规格")
    private String mphGoodsSpecification;

    @ApiModelProperty(value = "MPH_生产企业")
    private String mphGoodsManufacturer;

    @ApiModelProperty(value = "MPH供应商ID")
    private Long mphSupplierId;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getMphGoodsId() {
        return mphGoodsId;
    }

    public void setMphGoodsId(Long mphGoodsId) {
        this.mphGoodsId = mphGoodsId;
    }

    public String getMphGoodsName() {
        return mphGoodsName;
    }

    public void setMphGoodsName(String mphGoodsName) {
        this.mphGoodsName = mphGoodsName;
    }

    public String getMphGoodsPlace() {
        return mphGoodsPlace;
    }

    public void setMphGoodsPlace(String mphGoodsPlace) {
        this.mphGoodsPlace = mphGoodsPlace;
    }

    public String getMphGoodsSpecification() {
        return mphGoodsSpecification;
    }

    public void setMphGoodsSpecification(String mphGoodsSpecification) {
        this.mphGoodsSpecification = mphGoodsSpecification;
    }

    public String getMphGoodsManufacturer() {
        return mphGoodsManufacturer;
    }

    public void setMphGoodsManufacturer(String mphGoodsManufacturer) {
        this.mphGoodsManufacturer = mphGoodsManufacturer;
    }

    public Long getMphSupplierId() {
        return mphSupplierId;
    }

    public void setMphSupplierId(Long mphSupplierId) {
        this.mphSupplierId = mphSupplierId;
    }

    @Override
    public String toString() {
        return "SaveBoundGoodsVO[" +
                "goodsId=" + goodsId +
                ", mphGoodsId=" + mphGoodsId +
                ", mphGoodsName='" + mphGoodsName + '\'' +
                ", mphGoodsPlace='" + mphGoodsPlace + '\'' +
                ", mphGoodsSpecification='" + mphGoodsSpecification + '\'' +
                ", mphGoodsManufacturer='" + mphGoodsManufacturer + '\'' +
                ", mphSupplierId=" + mphSupplierId +
                ']';
    }
}
