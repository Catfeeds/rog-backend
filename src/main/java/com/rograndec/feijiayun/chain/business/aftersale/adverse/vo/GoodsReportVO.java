package com.rograndec.feijiayun.chain.business.aftersale.adverse.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class GoodsReportVO  implements Serializable{

    @ApiModelProperty(value = "商品ID")
    private Long id;
    @ApiModelProperty(value = "商品编码")
    private String code;
    @ApiModelProperty(value = "通用名称")
    private String genericName;
    @ApiModelProperty(value = "商品名称")
    private String name;
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;
    @ApiModelProperty(value = "规格")
    private String goodsSpecification;
    @ApiModelProperty(value = "生产厂商ID")
    private String manufacturerId;
    @ApiModelProperty(value = "生产厂商名称")
    private String manufacturer;
    @ApiModelProperty(value="产地")
    private String place;

    @ApiModelProperty(value = "单位")
    private String unitName;

    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;


    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
