package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class InvoiceDistrOutShelfVo implements Serializable{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 出库单ID
     */
    @ApiModelProperty(value = "出库单ID")
    private Long baseOrderId;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String baseOrderCode;
    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;
    /**
     * 出库类型
     */
    @ApiModelProperty(value = "出库类型")
    private Integer baseOrderType;
    /**
     * 出库日期
     */
    @ApiModelProperty(value = "出库日期")
    private Date baseOrderDate;
    /**
     * 出库日期
     */
    @ApiModelProperty(value = "日期")
    private Date orderDate;

    /**
     * 出库商品明细ID
     */
    @ApiModelProperty(value = "出库商品明细ID")
    private Long baseDtlId;

    /**
     * 出库货位明细ID
     */
    @ApiModelProperty(value = "出库货位明细ID")
    private Long baseShelfDtlId;
    
    /**
     * 出库货位明细ID
     */
    @ApiModelProperty(value = "出库货位明细ID")
    private Long orderShelfId ;
    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;
    
    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;
    /**
     *有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validDate;
    /**
     *有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validUntil;
    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;
    /**
     * 不含税实际单价
     */
    @ApiModelProperty(value = "不含税实际单价")
    private BigDecimal notaxRealPrice;
    /**
     * 税率
     */
    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;
    /**
     * 税率ID
     */
    @ApiModelProperty(value = "税率ID")
    private BigDecimal taxRateId;
    /**
     * 出库数量
     */
    @ApiModelProperty(value = " 出库数量")
    private BigDecimal quantity;
    
    /**
     * 未清数量
     */
    @ApiModelProperty(value = "未清数量")
    private BigDecimal unclearQuantity;
    
    /**
     * 已清数量
     */
    @ApiModelProperty(value = "已清数量")
    private BigDecimal clearQuantity;
    /**
     * 本次对账数量
     */
    @ApiModelProperty(value = "本次对账数量")
    private BigDecimal accountQuantity;
    /**
     * 本次对账金额
     */
    @ApiModelProperty(value = "本次对账金额")
    private BigDecimal accountAmount;
    /**
     * 本次对账不含税金额
     */
    @ApiModelProperty(value = "本次对账不含税金额")
    private BigDecimal accountNotaxAmount;
    /**
     *本次对账税额
     */
    @ApiModelProperty(value = "本次对账税额")
    private BigDecimal accountTaxAmount;
    
  

	public Long getBaseOrderId() {
		return baseOrderId;
	}

	public void setBaseOrderId(Long baseOrderId) {
		this.baseOrderId = baseOrderId;
	}

	public String getBaseOrderCode() {
		return baseOrderCode;
	}

	public void setBaseOrderCode(String baseOrderCode) {
		this.baseOrderCode = baseOrderCode;
	}

	public Integer getBaseOrderType() {
		return baseOrderType;
	}

	public void setBaseOrderType(Integer baseOrderType) {
		this.baseOrderType = baseOrderType;
	}

	public Date getBaseOrderDate() {
		return baseOrderDate;
	}

	public void setBaseOrderDate(Date baseOrderDate) {
		this.baseOrderDate = baseOrderDate;
	}

	public Long getBaseDtlId() {
		return baseDtlId;
	}

	public void setBaseDtlId(Long baseDtlId) {
		this.baseDtlId = baseDtlId;
	}

	public Long getBaseShelfDtlId() {
		return baseShelfDtlId;
	}

	public void setBaseShelfDtlId(Long baseShelfDtlId) {
		this.baseShelfDtlId = baseShelfDtlId;
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

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getNotaxRealPrice() {
		return notaxRealPrice;
	}

	public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
		this.notaxRealPrice = notaxRealPrice;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getTaxRateId() {
		return taxRateId;
	}

	public void setTaxRateId(BigDecimal taxRateId) {
		this.taxRateId = taxRateId;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnclearQuantity() {
		return unclearQuantity;
	}

	public void setUnclearQuantity(BigDecimal unclearQuantity) {
		this.unclearQuantity = unclearQuantity;
	}

	public BigDecimal getClearQuantity() {
		return clearQuantity;
	}

	public void setClearQuantity(BigDecimal clearQuantity) {
		this.clearQuantity = clearQuantity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	public BigDecimal getAccountQuantity() {
		return accountQuantity;
	}

	public void setAccountQuantity(BigDecimal accountQuantity) {
		this.accountQuantity = accountQuantity;
	}

	public Long getOrderShelfId() {
		return orderShelfId;
	}

	public void setOrderShelfId(Long orderShelfId) {
		this.orderShelfId = orderShelfId;
	}

	public BigDecimal getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(BigDecimal accountAmount) {
		this.accountAmount = accountAmount;
	}

	public BigDecimal getAccountNotaxAmount() {
		return accountNotaxAmount;
	}

	public void setAccountNotaxAmount(BigDecimal accountNotaxAmount) {
		this.accountNotaxAmount = accountNotaxAmount;
	}

	public BigDecimal getAccountTaxAmount() {
		return accountTaxAmount;
	}

	public void setAccountTaxAmount(BigDecimal accountTaxAmount) {
		this.accountTaxAmount = accountTaxAmount;
	}
    

    
}
