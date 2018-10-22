package com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "PurchaseOrderGoodsVO", description = "采购管理-采购入库-调用采购订单-商品对象")
public class PurchaseOrderGoodsVO implements Serializable {
	
	/**
     * PurchaseOrderGoodsVO
     */
    private static final long serialVersionUID = 1L;
	
	 /**
     * 主键ID
     */
	@ApiModelProperty(value = "采购订单细表主键ID")
    private Long id;

	/**
     * 商品ID
     */
	@ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 商品编码
     */
	@ApiModelProperty(value = "商品编码")
    private String goodsCode;

    /**
     * 条形码
     */
	@ApiModelProperty(value = "条形码")
    private String barcode;

    /**
     * 商品名称
     */
	@ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品通用名称
     */
	@ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;

    /**
     * 剂型ID
     */
	@ApiModelProperty(value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
	@ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 单位ID
     */
	@ApiModelProperty(value = "单位ID")
    private Long unitId;

    /**
     * 单位名称
     */
	@ApiModelProperty(value = "单位名称")
    private String unitName;

    /**
     * 商品规格
     */
	@ApiModelProperty(value = "商品规格")
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
    private String manufacturer;

    /**
     * 商品产地
     */
	@ApiModelProperty(value = "商品产地")
    private String goodsPlace;

    /**
     * 批准文号
     */
	@ApiModelProperty(value = "批准文号")
    private String approvalNumber;

    /**
     * 数量
     */
	@ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 单价
     */
	@ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;
	
    /**
     * 行折扣（%）
     */
	@ApiModelProperty(value = " 行折扣（%）")
    private BigDecimal lineDiscount;

    /**
     * 金额（整单优惠前金额）
     */
	@ApiModelProperty(value = " 金额（整单优惠前金额）")
    private BigDecimal amount;

    /**
     * 整单折扣（%）
     */
	@ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;

    /**
     * 行优惠（整单优惠分摊到行的金额）
     */
	@ApiModelProperty(value = "行优惠（整单优惠分摊到行的金额）")
    private BigDecimal lineDiscountAmount;

    /**
     * 实际单价（实际金额/数量）
     */
	@ApiModelProperty(value = "实际单价（实际金额/数量）")
    private BigDecimal realPrice;

    /**
     * 实际金额
     */
	@ApiModelProperty(value = "实际金额")
    private BigDecimal realAmount;

    /**
     * 进项税
     */
	@ApiModelProperty(value = "进项税")
    private BigDecimal taxRate;
	
    /**
     * 进项税ID
     * */
	@ApiModelProperty(value = "进项税ID")
    private Long taxRateId;
	
    /**
     * 不含税实际单价
     */
	@ApiModelProperty(value = "不含税实际单价")
    private BigDecimal notaxRealPrice;

    /**
     * 不含税实际金额
     */
	@ApiModelProperty(value = "不含税实际金额")
    private BigDecimal notaxRealAmount;

    /**
     * 税额
     */
	@ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;
	
	/**
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value="特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
    private Integer specialDrug;
    
    /**
     * 默认货位ID
     */
    private Long defaultShelfId;

    /**
     * 默认货位名称
     */
    private String defaultShelfName;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Long getTaxRateId() {
		return taxRateId;
	}

	public void setTaxRateId(Long taxRateId) {
		this.taxRateId = taxRateId;
	}

	public BigDecimal getNotaxRealPrice() {
		return notaxRealPrice;
	}

	public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
		this.notaxRealPrice = notaxRealPrice;
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

	public Integer getSpecialDrug() {
		return specialDrug;
	}

	public void setSpecialDrug(Integer specialDrug) {
		this.specialDrug = specialDrug;
	}

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

    
}