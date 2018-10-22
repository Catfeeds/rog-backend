package com.rograndec.feijiayun.chain.inf.pos.report.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaleGoodsVO", description = "按明细")
public class SaleGoodsVO {
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "销售日期")
	private Date saleDate;
	
	@ApiModelProperty(value = "销售单据")
	private String saleCode;
	
	@ApiModelProperty(value = "类型")
	private String saleMode;
	
	@ApiModelProperty(value = "模式")
	private String saleType;
	
	/**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;
    
    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;
    
    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;
    
    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private String goodsSpecification;
    
    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;
    
    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;
    
    @ApiModelProperty(value = "产地")
    private String goodsPlace;
    
    @ApiModelProperty(value = "批号")
    private String lotNumber;
    
    @JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "生产日期")
    private Date productDate;
    
    @JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "有效日期")
    private Date validDate;
    
    @ApiModelProperty(value = "货位名称")
    private String shelfName;
    
    @ApiModelProperty(value = "批准文号")
	private String approvalNumber;
	
	@ApiModelProperty(value = "数量")
    private BigDecimal quantity = BigDecimal.ZERO;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice = BigDecimal.ZERO;

    @ApiModelProperty(value = "行折扣（%）")
    private BigDecimal lineDiscount = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "金额")
    private BigDecimal amount = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "整单折扣%")
    private BigDecimal wholeDiscount = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "抹零分摊")
    private BigDecimal lineDiscountAmount = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "总额")
    private BigDecimal realAmount = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "款台")
	private String standCode;

    @ApiModelProperty(value = "收款员名称")
    private String payeeName;
    
    @ApiModelProperty(value = "收款员名称")
    private String clerkName;
    
    @ApiModelProperty(value = "会员卡号")
    private String memberCardCode;
    
    @ApiModelProperty(value = "实际单价")
    private BigDecimal realPrice;

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

	public String getSaleMode() {
		return saleMode;
	}

	public void setSaleMode(String saleMode) {
		this.saleMode = saleMode;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
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

	public String getGoodsPlace() {
		return goodsPlace;
	}

	public void setGoodsPlace(String goodsPlace) {
		this.goodsPlace = goodsPlace;
	}

	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
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

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public String getStandCode() {
		return standCode;
	}

	public void setStandCode(String standCode) {
		this.standCode = standCode;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getClerkName() {
		return clerkName;
	}

	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}

	public String getMemberCardCode() {
		return memberCardCode;
	}

	public void setMemberCardCode(String memberCardCode) {
		this.memberCardCode = memberCardCode;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}
    
	
    
}
