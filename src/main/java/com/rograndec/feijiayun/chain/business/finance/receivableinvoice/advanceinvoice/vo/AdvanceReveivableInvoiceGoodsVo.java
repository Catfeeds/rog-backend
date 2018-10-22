package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo;


import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class AdvanceReveivableInvoiceGoodsVo implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键ID
     */
	@ApiModelProperty(value = "id")
    private Long id;
	/**
     * yaopinid
     */
	@ApiModelProperty(value = "goodsId")
	private Long goodsId;

    /**
     * 标准库ID
     */
	@ApiModelProperty(value = "标准库ID")
    private Long standardLibraryId;

    /**
     * 企业（总部）ID
     */
	@ApiModelProperty(value = "企业（总部）ID")
    private Long enterpriseId;


    //企业类型ID（0-总部；1-自营店；2-加盟店）
	@ApiModelProperty(value = "企业类型ID（0-总部；1-自营店；2-加盟店）")
    private Integer chainType;

    //所有企业ID
	@ApiModelProperty(value = "所有企业ID")
    private Long ownerId;
    
    /**
     * 商品编码
     */
	@ApiModelProperty(value = " 商品编码")
    private String code;
	/**
	 * 商品编码
	 */
	@ApiModelProperty(value = " 商品编码")
	private String goodsCode;

    /**
     * 条形码
     */
	@ApiModelProperty(value = " 条形码")
    private String barcode;

    /**
     * 通用名称
     */
	@ApiModelProperty(value = " 通用名称")
    private String genericName;
	/**
	 * 通用名称
	 */
	@ApiModelProperty(value = " 通用名称")
	private String goodsGenericName;

    /**
     * 商品名称
     */
	@ApiModelProperty(value = " 商品名称")
    private String name;
	
	/**
	 * 商品名称
	 */
	@ApiModelProperty(value = " 商品名称")
	private String goodsName;
    
    /**
     * 生产厂商
     */
	@ApiModelProperty(value = " 生产厂商")
    private String manufacturer;

    /**
     * 产地
     */
	@ApiModelProperty(value = " 产地")
    private String place;
	/**
	 * 产地
	 */
	@ApiModelProperty(value = " 产地")
	private String goodsPlace;
    /**
     * 批准文号
     */
	@ApiModelProperty(value = "批准文号")
    private String approvalNumber;
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
     * 规格
     */
	@ApiModelProperty(value = "规格")
    private String specification;
	/**
	 * 规格
	 */
	@ApiModelProperty(value = "规格")
	private String goodsSpecification;
    /**
     * 库存计量单位ID
     */
	@ApiModelProperty(value = "库存计量单位ID")
    private Long unitId;

    /**
     * 库存计量单位名称
     */
	@ApiModelProperty(value = "库存计量单位名称")
    private String unitName;
	   /**
     * 数量
     */
    @ApiModelProperty(value = "默认数量")
    private BigDecimal quantity;
	
	  /**
     * 单价
     */
    @ApiModelProperty(value = "默认单价")
    private BigDecimal unitPrice;

    /**
     * 金额
     */
    @ApiModelProperty(value = "默认金额")
    private BigDecimal amount;
	 /**
     * 税率ID
     */
    @ApiModelProperty(value = "默认税率ID")
    private Long taxRateId;

    /**
     * 税率
     */
    @ApiModelProperty(value = "默认税率")
    private BigDecimal taxRate;
    /**
     * 不含税单价
     */
    @ApiModelProperty(value = "不含税单价")
    private BigDecimal notaxPrice;
    /**
     * 不含税单价
     */
    @ApiModelProperty(value = "不含税单价")
    private BigDecimal notaxRealPrice;
    
    /**
     * 不含税金额
     */
    @ApiModelProperty(value = "不含税金额")
    private BigDecimal notaxAmount;
    
    /**
     * 不含税金额
     */
    @ApiModelProperty(value = "不含税金额")
    private BigDecimal notaxRealAmount;
    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStandardLibraryId() {
		return standardLibraryId;
	}

	public void setStandardLibraryId(Long standardLibraryId) {
		this.standardLibraryId = standardLibraryId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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

	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
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

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
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

	public String getGoodsPlace() {
		return goodsPlace;
	}

	public void setGoodsPlace(String goodsPlace) {
		this.goodsPlace = goodsPlace;
	}

	public String getGoodsSpecification() {
		return goodsSpecification;
	}

	public void setGoodsSpecification(String goodsSpecification) {
		this.goodsSpecification = goodsSpecification;
	}
}
