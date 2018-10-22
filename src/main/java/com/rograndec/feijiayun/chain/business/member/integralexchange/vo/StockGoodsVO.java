package com.rograndec.feijiayun.chain.business.member.integralexchange.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by dudy on 2017/9/22.
 */
@ApiModel(value = "StockGoodsVO", description = "商品VO，带库存(或库存，积分)")
public class StockGoodsVO  implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value="商品主键id")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value="商品编码")
    private String goodsCode;

    /**
     * 通用名称
     */
    @ApiModelProperty(value="通用名称")
    private String goodsGenericName;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value="剂型名称")
    private String dosageName;

    /**
     * 规格
     */
    @ApiModelProperty(value="规格")
    private String goodsSpecification;



    /**
     * 生产厂商
     */
    @ApiModelProperty(value="生产厂商")
    private String manufacturer;


    /**
     * 库存计量单位名称
     */
    @ApiModelProperty(value="单位")
    private String unitName;

    /**
     * 兑换积分
     */
    @ApiModelProperty(value = "兑换积分(在搜索积分商品时用)")
    private BigDecimal integralExchange;

    /**
     * 库存可用量
     */
    @ApiModelProperty(value = "库存可用量")
    private BigDecimal useableQuantity;


    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public BigDecimal getIntegralExchange() {
        return integralExchange;
    }

    public void setIntegralExchange(BigDecimal integralExchange) {
        this.integralExchange = integralExchange;
    }

    public BigDecimal getUseableQuantity() {
        return useableQuantity;
    }

    public void setUseableQuantity(BigDecimal useableQuantity) {
        this.useableQuantity = useableQuantity;
    }

    @Override
    public String toString() {
        return "StockGoodsVO{" +
                "goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", unitName='" + unitName + '\'' +
                ", integralExchange=" + integralExchange +
                ", useableQuantity=" + useableQuantity +
                '}';
    }
}
