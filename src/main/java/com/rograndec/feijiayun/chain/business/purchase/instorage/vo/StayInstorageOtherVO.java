package com.rograndec.feijiayun.chain.business.purchase.instorage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "StayInstorageOtherVO", description = "采购管理-采购入库-配送和结算对象")
public class StayInstorageOtherVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, value = "合同单号")
	private String contractCode;
	
	@ApiModelProperty(required = true, value = "订单单号")
	private String orderCode;
	
	@ApiModelProperty(required = true, value = "采购人员")
	private String purchaserName;
	
	@ApiModelProperty(required = true, value = "收货人员1")
	private String receiverName;
	
	@ApiModelProperty(required = true, value = "收货人员2")
	private String secondReceiverName;
	
	@ApiModelProperty(required = true, value = "验收人员1")
	private String checkerName;
	
	@ApiModelProperty(required = true, value = "验收人员2")
	private String secondCheckerName;
	
	@ApiModelProperty(required = true, value = "结算单位")
	private String settlementUnit;
	
	@ApiModelProperty(required = true, value = "结算单位电话")
	private String settlementUnitPhone;
	
	@ApiModelProperty(required = true, value = "结算方式（0-现结；1-账期）")
	private Integer settlementType;
	
	@ApiModelProperty(required = true, value = "结算方式名称")
	private String settlementTypeName;
	
	@ApiModelProperty(required = true, value = "结算单位地址")
	private String settlementUnitAddress;
	
	@ApiModelProperty(required = true, value = "发票类型（0-普通发票；1-增值税发票）")
	private Integer invoiceType;
	
	@ApiModelProperty(required = true, value = "发票类型名称")
	private String invoiceTypeName;
	
	@ApiModelProperty(required = true, value = "纳税人识别号")
	private String taxpayerCode;
	
	@ApiModelProperty(required = true, value = "开户户名")
	private String accountName;
	
	@ApiModelProperty(required = true, value = "开户账号")
	private String account;
	
	@ApiModelProperty(required = true, value = "开户银行")
	private String bank;

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getPurchaserName() {
		return purchaserName;
	}

	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getSecondReceiverName() {
		return secondReceiverName;
	}

	public void setSecondReceiverName(String secondReceiverName) {
		this.secondReceiverName = secondReceiverName;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String getSecondCheckerName() {
		return secondCheckerName;
	}

	public void setSecondCheckerName(String secondCheckerName) {
		this.secondCheckerName = secondCheckerName;
	}

	public String getSettlementUnit() {
		return settlementUnit;
	}

	public void setSettlementUnit(String settlementUnit) {
		this.settlementUnit = settlementUnit;
	}

	public String getSettlementUnitPhone() {
		return settlementUnitPhone;
	}

	public void setSettlementUnitPhone(String settlementUnitPhone) {
		this.settlementUnitPhone = settlementUnitPhone;
	}

	public Integer getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(Integer settlementType) {
		this.settlementType = settlementType;
	}

	public String getSettlementUnitAddress() {
		return settlementUnitAddress;
	}

	public void setSettlementUnitAddress(String settlementUnitAddress) {
		this.settlementUnitAddress = settlementUnitAddress;
	}

	public Integer getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getTaxpayerCode() {
		return taxpayerCode;
	}

	public void setTaxpayerCode(String taxpayerCode) {
		this.taxpayerCode = taxpayerCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getSettlementTypeName() {
		return settlementTypeName;
	}

	public void setSettlementTypeName(String settlementTypeName) {
		this.settlementTypeName = settlementTypeName;
	}

	public String getInvoiceTypeName() {
		return invoiceTypeName;
	}

	public void setInvoiceTypeName(String invoiceTypeName) {
		this.invoiceTypeName = invoiceTypeName;
	}
	
}
