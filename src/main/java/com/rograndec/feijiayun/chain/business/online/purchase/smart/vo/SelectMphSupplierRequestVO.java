package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;

public class SelectMphSupplierRequestVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年11月24日 下午1:32:03 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 56514004638507803L;

	@ApiModelProperty(value = "MPH商品ID")
	private Integer mphGoodsId;
	
	@ApiModelProperty(value = "MPH供应商ID")
	private Integer mphSupplierId;
	
	@ApiModelProperty(value = "融贯通用户ID")
	private Long rgtUserId;
	
	@ApiModelProperty(value = "MPH用户端接口URL-申请采购关系时使用")
	private String mphUserUrl;
	
	@ApiModelProperty(value = "MPH主站接口URL")
	private String mphImplUrl;
	
	@ApiModelProperty(value = "MPH商品名称")
	private String mphGoodsName;
	
	@ApiModelProperty(value = "MPH商品规格")
	private String mphSpecification;
	
	@ApiModelProperty(value = "MPH生产企业")
	private String mphManufacturer;
	
	@ApiModelProperty(value = "MPH name VIEW")
	private String nameView;
	
	//0：不可以采购，1：可以采购
	@ApiModelProperty(value = "是否可以采购")
	private int isProcurement;
	
	@ApiModelProperty(value = "采购价")
	private Double retailPrice;
	
	@ApiModelProperty(value = "MPH供应商名称")
	private String mphSupplierName;
	
	@ApiModelProperty(value = "MPH供应商起采金额限制")
	private Double matchingAmount;
	
	@ApiModelProperty(value = "MPH供应商起采金额限制显示")
	private String matchingAmountName;
	
	@ApiModelProperty(value = "MPH库存数量")
	private Double inventoryQuantity;
	
	//是否拆零 0 否 1 是
	@ApiModelProperty(value = "是否拆零")
	private Integer gCanSplit;
	
	@ApiModelProperty(value = "中包装")
	private Integer gMiddlePackage;

	@ApiModelProperty(value = "按钮所在行供应商ID", required = true)
	private Long supplierId;
	
	@ApiParam(value = "按钮所在行商品ID", required = true)
	private Long goodsId;
	
	@ApiParam(value = "按钮所在行MPH商品ID", required = true) 
	private Long gId;

	public Integer getMphGoodsId() {
		return mphGoodsId;
	}

	public void setMphGoodsId(Integer mphGoodsId) {
		this.mphGoodsId = mphGoodsId;
	}

	public Integer getMphSupplierId() {
		return mphSupplierId;
	}

	public void setMphSupplierId(Integer mphSupplierId) {
		this.mphSupplierId = mphSupplierId;
	}

	public Long getRgtUserId() {
		return rgtUserId;
	}

	public void setRgtUserId(Long rgtUserId) {
		this.rgtUserId = rgtUserId;
	}

	public String getMphUserUrl() {
		return mphUserUrl;
	}

	public void setMphUserUrl(String mphUserUrl) {
		this.mphUserUrl = mphUserUrl;
	}

	public String getMphImplUrl() {
		return mphImplUrl;
	}

	public void setMphImplUrl(String mphImplUrl) {
		this.mphImplUrl = mphImplUrl;
	}

	public String getMphGoodsName() {
		return mphGoodsName;
	}

	public void setMphGoodsName(String mphGoodsName) {
		this.mphGoodsName = mphGoodsName;
	}

	public String getMphSpecification() {
		return mphSpecification;
	}

	public void setMphSpecification(String mphSpecification) {
		this.mphSpecification = mphSpecification;
	}

	public String getMphManufacturer() {
		return mphManufacturer;
	}

	public void setMphManufacturer(String mphManufacturer) {
		this.mphManufacturer = mphManufacturer;
	}

	public String getNameView() {
		return nameView;
	}

	public void setNameView(String nameView) {
		this.nameView = nameView;
	}

	public int getIsProcurement() {
		return isProcurement;
	}

	public void setIsProcurement(int isProcurement) {
		this.isProcurement = isProcurement;
	}

	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public String getMphSupplierName() {
		return mphSupplierName;
	}

	public void setMphSupplierName(String mphSupplierName) {
		this.mphSupplierName = mphSupplierName;
	}

	public Double getMatchingAmount() {
		return matchingAmount;
	}

	public void setMatchingAmount(Double matchingAmount) {
		this.matchingAmount = matchingAmount;
	}

	public String getMatchingAmountName() {
		return matchingAmountName;
	}

	public void setMatchingAmountName(String matchingAmountName) {
		this.matchingAmountName = matchingAmountName;
	}

	public Double getInventoryQuantity() {
		return inventoryQuantity;
	}

	public void setInventoryQuantity(Double inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}

	public Integer getgCanSplit() {
		return gCanSplit;
	}

	public void setgCanSplit(Integer gCanSplit) {
		this.gCanSplit = gCanSplit;
	}

	public Integer getgMiddlePackage() {
		return gMiddlePackage;
	}

	public void setgMiddlePackage(Integer gMiddlePackage) {
		this.gMiddlePackage = gMiddlePackage;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getgId() {
		return gId;
	}

	public void setgId(Long gId) {
		this.gId = gId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
