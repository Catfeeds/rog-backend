package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.rograndec.feijiayun.chain.common.valid.annotation.ValidMax;
import com.rograndec.feijiayun.chain.common.valid.annotation.ValidNotNull;

import io.swagger.annotations.ApiModelProperty;

public class AdvanceReceivableInvoiceDetailVo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 //所有企业ID
	@ApiModelProperty(value = "所有企业ID")
    @ValidNotNull()
    private Long ownerId;

	/**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    @ValidNotNull()
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    @ValidNotNull()
    @ValidMax(20)
    private String goodsCode;

    /**
     * 条形码
     */
    @ApiModelProperty(value = "条形码")
    @ValidMax(50)
    private String barcode;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    @ValidNotNull()
    @ValidMax(50)
    private String goodsName;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    @ValidNotNull()
    @ValidMax(200)
    private String goodsGenericName;

    /**
     * 剂型ID
     */
    @ApiModelProperty(value = "剂型ID")
    @ValidNotNull()
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    @ValidNotNull()
    @ValidMax(50)
    private String dosageName;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    @ValidNotNull()
    private Long unitId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    @ValidNotNull()
    @ValidMax(50)
    private String unitName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    @ValidMax(100)
    private String goodsSpecification;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    @ValidMax(100)
    private String manufacturer;

    /**
     * 商品产地
     */
    @ApiModelProperty(value = "商品产地")
    @ValidMax(100)
    private String goodsPlace;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    @ValidMax(50)
    private String approvalNumber;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    @ValidNotNull()
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    @ValidNotNull()
    private BigDecimal unitPrice;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    @ValidNotNull()
    private BigDecimal amount;

    /**
     * 税率ID
     */
    @ApiModelProperty(value = "税率ID")
    @ValidNotNull()
    private Long taxRateId;

    /**
     * 税率
     */
    @ApiModelProperty(value = "税率")
    @ValidNotNull()
    private BigDecimal taxRate;

    /**
     * 不含税单价
     */
    @ApiModelProperty(value = "不含税单价")
    @ValidNotNull()
    private BigDecimal notaxPrice;

    /**
     * 不含税金额
     */
    @ApiModelProperty(value = "不含税金额")
    @ValidNotNull()
    private BigDecimal notaxAmount;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    @ValidNotNull()
    private BigDecimal taxAmount;


    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    @ValidNotNull()
    private Integer lineNum;
    
    
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @ValidMax(200)
    private String remark;

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


	public String getBarcode() {
		return barcode;
	}


	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}


	public String getGoodsName() {
		return goodsName;
	}


	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	public String getGoodsGenericName() {
		return goodsGenericName;
	}


	public void setGoodsGenericName(String goodsGenericName) {
		this.goodsGenericName = goodsGenericName;
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


	public String getGoodsSpecification() {
		return goodsSpecification;
	}


	public void setGoodsSpecification(String goodsSpecification) {
		this.goodsSpecification = goodsSpecification;
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


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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


	public BigDecimal getNotaxPrice() {
		return notaxPrice;
	}


	public void setNotaxPrice(BigDecimal notaxPrice) {
		this.notaxPrice = notaxPrice;
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


	public Integer getLineNum() {
		return lineNum;
	}


	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}


	public Long getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}



}
