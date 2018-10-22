package com.rograndec.feijiayun.chain.business.purchase.instorage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "StayInstorageDetailVO", description = "采购管理-采购入库-待入库列表明细对象")
public class StayInstorageDetailVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, value = "验收单ID")
    private Long id;
	
	@ApiModelProperty(required = true, value = "验收明细ID")
    private Long checkDtlId;
	
	@ApiModelProperty(required = true, value = "采购订单明细ID")
    private Long purchaseOrderDtlId;
	
	@ApiModelProperty(required = true, value = "商品ID")
    private Long goodsId;
	
	@ApiModelProperty(required = true, value = "商品编码")
    private String goodsCode;
	
	@ApiModelProperty(required = true, value = "通用名称")
    private String goodsGenericName;
	
	@ApiModelProperty(required = true, value = "剂型")
    private String dosageName;
	
	@ApiModelProperty(required = true, value = "规格")
    private String goodsSpecification;
	
	@ApiModelProperty(required = true, value = "生产厂商")
	private String manufacturer;
	
	@ApiModelProperty(required = true, value = "单位名称")
    private String unitName;
	
	private List<CheckLotDetailVO> checkLotDetailList;
	
	@ApiModelProperty(required = true, value = "数量")
	private BigDecimal receiveQuantity;
	
	@ApiModelProperty(required = true, value = "单价")
	private BigDecimal unitPrice;
	
	@ApiModelProperty(required = true, value = "折扣")
	private BigDecimal lineDiscount;
	
	@ApiModelProperty(required = true, value = "金额")
	private BigDecimal amount;
	
	/*@ApiModelProperty(required = true, value = "税率")
	private BigDecimal taxRate;*/
	
	@ApiModelProperty(required = true, value = "税率ID")
	private Long taxRateId;
	
	@ApiModelProperty(required = true, value = "不含税单价")
	private BigDecimal notaxRealPrice;
	
	@ApiModelProperty(required = true, value = "不含税金额")
	private BigDecimal notaxRealAmount;
	
	@ApiModelProperty(required = true, value = "税额")
	private BigDecimal taxAmount;
	
	@ApiModelProperty(required = true, value = "备注")
	private String remark;
	
	@ApiModelProperty(required = true, value = "配货单价")
	private BigDecimal distrPrice;
	
	@ApiModelProperty(required = true, value = "零售单价")
	private BigDecimal retailPrice;
	
	@ApiModelProperty(required = true, value = "会员单价")
	private BigDecimal memberPrice;
	
	@ApiModelProperty(required = true, value = "行号")
    private Integer lineNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCheckDtlId() {
		return checkDtlId;
	}

	public void setCheckDtlId(Long checkDtlId) {
		this.checkDtlId = checkDtlId;
	}

	public Long getPurchaseOrderDtlId() {
		return purchaseOrderDtlId;
	}

	public void setPurchaseOrderDtlId(Long purchaseOrderDtlId) {
		this.purchaseOrderDtlId = purchaseOrderDtlId;
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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public List<CheckLotDetailVO> getCheckLotDetailList() {
		return checkLotDetailList;
	}

	public void setCheckLotDetailList(List<CheckLotDetailVO> checkLotDetailList) {
		this.checkLotDetailList = checkLotDetailList;
	}

	public BigDecimal getReceiveQuantity() {
		return receiveQuantity;
	}

	public void setReceiveQuantity(BigDecimal receiveQuantity) {
		this.receiveQuantity = receiveQuantity;
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

	/*public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}*/

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getDistrPrice() {
		return distrPrice;
	}

	public void setDistrPrice(BigDecimal distrPrice) {
		this.distrPrice = distrPrice;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public BigDecimal getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(BigDecimal memberPrice) {
		this.memberPrice = memberPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}
	
}
