package com.rograndec.feijiayun.chain.business.medicine.consult.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class MedicineGoodsVO implements Serializable{
	@ApiModelProperty(value = "商品ID")
	private Long goodsId;
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
	@ApiModelProperty(value = "生产厂商ID")
    private String manufacturerId;
	@ApiModelProperty(value = "生产厂商名称")
	private String manufacturer;
	@ApiModelProperty(value = "单价")
	private BigDecimal unitPrice;
    @ApiModelProperty(value="产地")
    private String goodsPlace;
    @ApiModelProperty(value = "限购数量")
    private BigDecimal limitQuantity;
    @ApiModelProperty(value="用法用量")
    private String usageDosage;
    @ApiModelProperty(value = "禁忌症")
    private String tabooSymptom;
    @ApiModelProperty(value = "注意事项")
    private String attentionMatter;
    @ApiModelProperty(value = "标准库ID")
    private Long standardLibraryId;
	
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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getGoodsPlace() {
		return goodsPlace;
	}
	public void setGoodsPlace(String goodsPlace) {
		this.goodsPlace = goodsPlace;
	}
	public BigDecimal getLimitQuantity() {
		return limitQuantity;
	}
	public void setLimitQuantity(BigDecimal limitQuantity) {
		this.limitQuantity = limitQuantity;
	}
	public String getUsageDosage() {
		return usageDosage;
	}
	public void setUsageDosage(String usageDosage) {
		this.usageDosage = usageDosage;
	}
	public String getTabooSymptom() {
		return tabooSymptom;
	}
	public void setTabooSymptom(String tabooSymptom) {
		this.tabooSymptom = tabooSymptom;
	}
	public String getAttentionMatter() {
		return attentionMatter;
	}
	public void setAttentionMatter(String attentionMatter) {
		this.attentionMatter = attentionMatter;
	}
	public Long getStandardLibraryId() {
		return standardLibraryId;
	}
	public void setStandardLibraryId(Long standardLibraryId) {
		this.standardLibraryId = standardLibraryId;
	}



}
