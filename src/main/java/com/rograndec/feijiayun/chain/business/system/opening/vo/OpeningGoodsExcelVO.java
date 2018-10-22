package com.rograndec.feijiayun.chain.business.system.opening.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by ST on 2017/9/7.
 */
public class OpeningGoodsExcelVO implements Serializable {

    @ApiModelProperty(value="商品ID")
    private Long goodsId;
    /**
     * 商品编码
     */
    @ApiModelProperty(value="商品编码")
    private String code;


    /**
     * 原商品编码
     */
    @ApiModelProperty(value="原商品编码")
    private String oldCode;


    /**
     * 通用名称
     */
    @ApiModelProperty(value="通用名称")
    private String genericName;

    /**
     * 商品名称
     */
    @ApiModelProperty(value="商品名称")
    private String name;


    /**
     * 剂型名称
     */
    @ApiModelProperty(value="剂型名称")
    private String dosageName;

    /**
     * 规格
     */
    @ApiModelProperty(value="规格")
    private String specification;


    /**
     * 库存计量单位名称
     */
    @ApiModelProperty(value="库存计量单位名称")
    private String unitName;
    /**
     * 生产厂商
     */
    @ApiModelProperty(value="生产厂商")
    private String manufacturer;

    @ApiModelProperty(value="批号")
    private String lotNumber;
    @ApiModelProperty(value = "生成日期",required = true)
    private String productDate;//生成日期*
    /**
     * 有效期至
     */
    @ApiModelProperty(value="有效期至")
    private String validDate;

    @ApiModelProperty(value="数量")
    private String quantity;

    @ApiModelProperty(value = "单价",required = true)
    private String unitPrice;//单价*

    @ApiModelProperty(value = "税率ID")
    private Long taxRateId;//税率*
    @ApiModelProperty(value = "税率")
    private String taxRate;//税率*

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "不含税单价")
    private BigDecimal notaxPrice;//不含税单价*
    @ApiModelProperty(value = "不含税金额")
    private BigDecimal notaxAmount;//不含税金额*
    @ApiModelProperty(value = "税金额")
    private BigDecimal taxAmount;//税金额*

    @ApiModelProperty(value = "货位id")
    private Long shelfId;
    @ApiModelProperty(value = "货位名称")
    private String shelfName;//货位名称

    @ApiModelProperty(value = "零售单价")
    private String retailPrice;//零售单价*
    @ApiModelProperty(value = "会员单价")
    private String memberPrice;//会员单价*
    @ApiModelProperty(value = "供货单位id")
    private Long supplierId;//供货单位id
    @ApiModelProperty(value = "供货单位编码")
    private String supplierCode;//供货单位编码*
    @ApiModelProperty(value = "供货单位名称")
    private String supplierName;//供货单位名称*
    @ApiModelProperty(value = "数据类型,默认为价格清单1:企业,2:供应商")
    private Integer type;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOldCode() {
        return oldCode;
    }

    public void setOldCode(String oldCode) {
        this.oldCode = oldCode;
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

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(String memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getNotaxAmount() {
        return notaxAmount;
    }

    public void setNotaxAmount(BigDecimal notaxAmount) {
        this.notaxAmount = notaxAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getNotaxPrice() {
        return notaxPrice;
    }

    public void setNotaxPrice(BigDecimal notaxPrice) {
        this.notaxPrice = notaxPrice;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    @Override
    public String toString() {
        return "OpeningGoodsExcelVO{" +
                "goodsId=" + goodsId +
                ", code='" + code + '\'' +
                ", oldCode='" + oldCode + '\'' +
                ", genericName='" + genericName + '\'' +
                ", name='" + name + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", specification='" + specification + '\'' +
                ", unitName='" + unitName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate='" + productDate + '\'' +
                ", validDate='" + validDate + '\'' +
                ", quantity='" + quantity + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", taxRateId=" + taxRateId +
                ", taxRate='" + taxRate + '\'' +
                ", amount=" + amount +
                ", notaxPrice=" + notaxPrice +
                ", notaxAmount=" + notaxAmount +
                ", taxAmount=" + taxAmount +
                ", shelfId=" + shelfId +
                ", shelfName='" + shelfName + '\'' +
                ", retailPrice='" + retailPrice + '\'' +
                ", memberPrice='" + memberPrice + '\'' +
                ", supplierId=" + supplierId +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                '}';
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}