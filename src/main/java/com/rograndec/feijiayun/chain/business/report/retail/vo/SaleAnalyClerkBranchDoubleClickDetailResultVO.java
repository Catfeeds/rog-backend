package com.rograndec.feijiayun.chain.business.report.retail.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class SaleAnalyClerkBranchDoubleClickDetailResultVO implements Serializable{

private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "门店编码")
	private String storeCode;

	@ApiModelProperty(value = "门店名称")
	private String storeName;
	
	@ApiModelProperty(value = "日结日期")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dailyDate;
	
	@ApiModelProperty(value = "销售时间")
	private Date saleDate;
	
	@ApiModelProperty(value = "销售单号")
	private String saleCode;
	
	private Integer saleType;
	
	@ApiModelProperty(value = "销售类型")
	private String saleTypeName;

	private Integer saleMode;
	
	@ApiModelProperty(value = "销售模式")
	private String saleModeName;
	
	@ApiModelProperty(value = "柜组")
	private String cargoAreaName;
	
	@ApiModelProperty(value = "营业员名称")
    private String clerkName;
	
	@ApiModelProperty(value = "商品编码")
    private String goodsCode;
	
	@ApiModelProperty(value = "通用名称")
    private String goodsGenericName;
	
	@ApiModelProperty(value = "剂型名称")
    private String dosageName;
	
	@ApiModelProperty(value = "商品规格")
    private String goodsSpecification;
	
	@ApiModelProperty(value = "单位名称")
    private String unitName;
	
	@ApiModelProperty(value = "生产厂商")
    private String manufacturer;
	
	@ApiModelProperty(value = "数量")
    private BigDecimal quantity;
	
	@ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;
	
	@ApiModelProperty(value = "行折扣（%）")
    private BigDecimal lineDiscount;
	
	@ApiModelProperty(value = "金额（整单优惠前金额）")
    private BigDecimal amount;
	
	@ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;
	
	@ApiModelProperty(value = "行优惠（整单优惠分摊到行的金额）")
    private BigDecimal lineDiscountAmount;
	
	@ApiModelProperty(value = "实际单价（实际金额/数量）")
    private BigDecimal realPrice;
	
	@ApiModelProperty(value = "总额/实际金额")
    private BigDecimal realAmount;
	
	@ApiModelProperty(value = "税率")
	private BigDecimal taxRate;
	
	@ApiModelProperty(value = "不含税总额")
    private BigDecimal notaxRealAmount;
	
	@ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Date getDailyDate() {
		return dailyDate;
	}

	public void setDailyDate(Date dailyDate) {
		this.dailyDate = dailyDate;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public Integer getSaleType() {
		return saleType;
	}

	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}

	public String getSaleTypeName() {
		return saleTypeName;
	}

	public void setSaleTypeName(String saleTypeName) {
		this.saleTypeName = saleTypeName;
	}

	public Integer getSaleMode() {
		return saleMode;
	}

	public void setSaleMode(Integer saleMode) {
		this.saleMode = saleMode;
	}

	public String getSaleModeName() {
		return saleModeName;
	}

	public void setSaleModeName(String saleModeName) {
		this.saleModeName = saleModeName;
	}

	public String getCargoAreaName() {
		return cargoAreaName;
	}

	public void setCargoAreaName(String cargoAreaName) {
		this.cargoAreaName = cargoAreaName;
	}

	public String getClerkName() {
		return clerkName;
	}

	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
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

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getLineDiscount() {
		return lineDiscount;
	}

	public void setLineDiscount(BigDecimal lineDiscount) {
		this.lineDiscount = lineDiscount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getWholeDiscount() {
		return wholeDiscount;
	}

	public void setWholeDiscount(BigDecimal wholeDiscount) {
		this.wholeDiscount = wholeDiscount;
	}

	public BigDecimal getLineDiscountAmount() {
		return lineDiscountAmount;
	}

	public void setLineDiscountAmount(BigDecimal lineDiscountAmount) {
		this.lineDiscountAmount = lineDiscountAmount;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getNotaxRealAmount() {
		return notaxRealAmount;
	}

	public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
		this.notaxRealAmount = notaxRealAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
