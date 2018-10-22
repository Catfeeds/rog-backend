package com.rograndec.feijiayun.chain.business.system.opening.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 功能描述：
 * Created by ST on 2017/11/4 13:29
 */

public class OpeningGoodsGet {

    @ApiModelProperty(value = "商品id")
    private  Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String code;



    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String name;



    /**
     * 通用名称
     */
    @ApiModelProperty(value = "通用名称",required = true)
    private String genericName;

    /**
     * 剂型ID
     */
    @ApiModelProperty(value = "剂型ID")
    private Long dosageId;
    /**
     * 剂型
     */
    @ApiModelProperty(value = "剂型" ,required = true)
    private String dosageName;

    /**
     * 库存计量单位ID
     */
    @ApiModelProperty(value = "库存计量单位ID")
    private Long unitId;
    /**
     * 库存计量单位
     */
    @ApiModelProperty(value = "库存计量单位",required = true)
    private String unitName;

    /**
     * 生产企业
     */
    @ApiModelProperty(value = "生产企业")
    private String manufacturer;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private String specification;

    @ApiModelProperty(value = "零售单价")
    private BigDecimal retailPrice;

    @ApiModelProperty(value = "税率id")
    private Long taxRateId;
    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public Long getDosageId() {
        return dosageId;
    }

    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
}