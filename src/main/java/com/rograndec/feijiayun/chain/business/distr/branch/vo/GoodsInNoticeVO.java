package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsInNoticeVO implements Serializable {

    @ApiModelProperty(value = "商品ID")
    private Long id;
    @ApiModelProperty(value = "药店类型（0-总部；1-自营店；2-加盟店）")
    private Integer chainType;
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;
    @ApiModelProperty(value = "通用名称")
    private String goodsGenericName;
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;
    @ApiModelProperty(value = "规格")
    private String goodsSpecification;
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;
    @ApiModelProperty(value = "单位")
    private String unitName;
    @ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;
    @ApiModelProperty(value = "生产厂商名称")
    private String manufacturer;
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;
    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;
    @ApiModelProperty(value = "税率ID")
    private Long taxRateId;
    @ApiModelProperty(value = "进项税税率")
    private BigDecimal purchaseTaxRate;
    @ApiModelProperty(value = "进项税税率ID")
    private Long purchaseTaxRateId;
    @ApiModelProperty(value="零售基价")
    private BigDecimal retailPrice;
    @ApiModelProperty(value="会员基价")
    private BigDecimal memberPrice;
    @ApiModelProperty(value="产地")
    private String goodsPlace;

    /**
     * 默认货位ID
     */
    @ApiModelProperty(value="默认货位ID")
    private Long defaultShelfId;

    /**
     * 默认货位名称
     */
    @ApiModelProperty(value="默认货位名称")
    private String defaultShelfName;


    public Long getDefaultShelfId() {
        return defaultShelfId;
    }

    public void setDefaultShelfId(Long defaultShelfId) {
        this.defaultShelfId = defaultShelfId;
    }

    public String getDefaultShelfName() {
        return defaultShelfName;
    }

    public void setDefaultShelfName(String defaultShelfName) {
        this.defaultShelfName = defaultShelfName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}

	public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }


    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public BigDecimal getPurchaseTaxRate() {
        return purchaseTaxRate;
    }

    public void setPurchaseTaxRate(BigDecimal purchaseTaxRate) {
        this.purchaseTaxRate = purchaseTaxRate;
    }

    public Long getPurchaseTaxRateId() {
        return purchaseTaxRateId;
    }

    public void setPurchaseTaxRateId(Long purchaseTaxRateId) {
        this.purchaseTaxRateId = purchaseTaxRateId;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    @Override
    public String toString() {
        return "GoodsInNoticeVO[" +
                "id=" + id +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", unitName='" + unitName + '\'' +
                ", manufacturerId='" + manufacturerId + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", unitPrice=" + unitPrice +
                ", taxRate='" + taxRate + '\'' +
                ", taxRateId='" + taxRateId + '\'' +
                ", retailPrice=" + retailPrice +
                ", memberPrice=" + memberPrice +
                ", goodsPlace='" + goodsPlace + '\'' +
                ']';
    }
}
