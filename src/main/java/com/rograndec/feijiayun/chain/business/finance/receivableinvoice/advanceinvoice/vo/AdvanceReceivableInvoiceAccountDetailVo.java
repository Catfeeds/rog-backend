package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
public class AdvanceReceivableInvoiceAccountDetailVo implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 预收发票总单ID
	 */
	@ApiModelProperty(value = "明细ID")
	private Long id;
	/**
     * 预收发票总单ID
     */
    @ApiModelProperty(value = "预收发票总单ID")
    private Long invoiceId;

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
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    /**
     * 税率ID
     */
    @ApiModelProperty(value = "税率ID")
    private Long taxRateId;

    /**
     * 税率
     */
    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;

    /**
     * 不含税单价
     */
    @ApiModelProperty(value = "不含税单价")
    private BigDecimal notaxPrice;

    /**
     * 不含税金额
     */
    @ApiModelProperty(value = "不含税金额")
    private BigDecimal notaxAmount;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;
  
    /**
     * 已对账数量
     */
    @ApiModelProperty(value = "已对账数量")
    private BigDecimal accountQuantity;

    
    /**
     * 未对账数量
     */
    @ApiModelProperty(value = "未对账数量")
    private BigDecimal unaccountQuantity;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    /**
     * 状态（0-待收款；1-部分收款；2-已收款；3-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待收款；1-部分收款；2-已收款；3-已冲销）")
    private Integer status;

    /**
     * 对账状态（0-待对账；1-部分对账；2-已对账；3-已冲销）
     */
    @ApiModelProperty(value = "对账状态（0-待对账；1-部分对账；2-已对账；3-已冲销）")
    private Integer accountStatus;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    /**
     * 金额差额
     */
    @ApiModelProperty(value = "金额差额")
    private BigDecimal diffAmount;

    /**
     * 不含税金额差额
     */
    @ApiModelProperty(value = "不含税金额差额")
    private BigDecimal diffNotaxAmount;

    /**
     * 税额差额
     */
    @ApiModelProperty(value = "税额差额")
    private BigDecimal diffTaxAmount;
    /**
     * 已清数量
     */
    @ApiModelProperty(value = "已清数量")
    private BigDecimal clearQuantity;

    /**
     * 未清数量
     */
    @ApiModelProperty(value = "未清数量")
    private BigDecimal unclearQuantity;
    /**
     * 本次对账数量
     */
    @ApiModelProperty(value = "本次对账数量")
    private BigDecimal timeCheckAccountQty;
    /**
     * 本次对账金额
     */
    @ApiModelProperty(value = "本次对账金额")
    private BigDecimal timeCheckAccountAmount;
    /**
     * 本次对账不含税金额
     */
    @ApiModelProperty(value = "本次对账不含税金额")
    private BigDecimal timeCheckAccountNotaxAmount;
    /**
     * 本次对账税额
     */
    @ApiModelProperty(value = "本次对账税额")
    private BigDecimal timeCheckAccountTaxAmount;
    
   
    
    
    @ApiModelProperty(value = "出库单")
    private List<InvoiceDistrOutShelfVo> shelfList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getDiffAmount() {
		return diffAmount;
	}

	public void setDiffAmount(BigDecimal diffAmount) {
		this.diffAmount = diffAmount;
	}

	public BigDecimal getDiffNotaxAmount() {
		return diffNotaxAmount;
	}

	public void setDiffNotaxAmount(BigDecimal diffNotaxAmount) {
		this.diffNotaxAmount = diffNotaxAmount;
	}

	public BigDecimal getDiffTaxAmount() {
		return diffTaxAmount;
	}

	public void setDiffTaxAmount(BigDecimal diffTaxAmount) {
		this.diffTaxAmount = diffTaxAmount;
	}

	public BigDecimal getAccountQuantity() {
		return accountQuantity;
	}

	public void setAccountQuantity(BigDecimal accountQuantity) {
		this.accountQuantity = accountQuantity;
	}

	public BigDecimal getUnaccountQuantity() {
		return unaccountQuantity;
	}

	public void setUnaccountQuantity(BigDecimal unaccountQuantity) {
		this.unaccountQuantity = unaccountQuantity;
	}

	public BigDecimal getClearQuantity() {
		return clearQuantity;
	}

	public void setClearQuantity(BigDecimal clearQuantity) {
		this.clearQuantity = clearQuantity;
	}

	public BigDecimal getUnclearQuantity() {
		return unclearQuantity;
	}

	public void setUnclearQuantity(BigDecimal unclearQuantity) {
		this.unclearQuantity = unclearQuantity;
	}

	public BigDecimal getTimeCheckAccountQty() {
		return timeCheckAccountQty;
	}

	public void setTimeCheckAccountQty(BigDecimal timeCheckAccountQty) {
		this.timeCheckAccountQty = timeCheckAccountQty;
	}

	public BigDecimal getTimeCheckAccountAmount() {
		return timeCheckAccountAmount;
	}

	public void setTimeCheckAccountAmount(BigDecimal timeCheckAccountAmount) {
		this.timeCheckAccountAmount = timeCheckAccountAmount;
	}

	public BigDecimal getTimeCheckAccountNotaxAmount() {
		return timeCheckAccountNotaxAmount;
	}

	public void setTimeCheckAccountNotaxAmount(BigDecimal timeCheckAccountNotaxAmount) {
		this.timeCheckAccountNotaxAmount = timeCheckAccountNotaxAmount;
	}

	public BigDecimal getTimeCheckAccountTaxAmount() {
		return timeCheckAccountTaxAmount;
	}

	public void setTimeCheckAccountTaxAmount(BigDecimal timeCheckAccountTaxAmount) {
		this.timeCheckAccountTaxAmount = timeCheckAccountTaxAmount;
	}

	public List<InvoiceDistrOutShelfVo> getShelfList() {
		return shelfList;
	}

	public void setShelfList(List<InvoiceDistrOutShelfVo> shelfList) {
		this.shelfList = shelfList;
	}
}
