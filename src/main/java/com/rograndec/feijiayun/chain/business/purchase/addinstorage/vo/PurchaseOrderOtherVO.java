package com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: PurchaseOrderOtherVO   
 * @Description: 采购管理-采购入库-调用采购订单-配送和结算对象
 * @author yuting.li
 * @version 1.0 
 * @date 2017年11月28日 下午8:27:09
 */
@ApiModel(value = "PurchaseOrderOtherVO", description = "采购管理-采购入库-调用采购订单-配送和结算对象")
public class PurchaseOrderOtherVO implements Serializable{
	
	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年11月29日 下午2:46:02 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;

	//采购订单---------------------------------------------
	@ApiModelProperty(value = "合同单号")
    private String contractCode;
	
	@ApiModelProperty(value = "采购订单ID")
	private Long orderId;
	
	@ApiModelProperty(value = "采购计划ID")
    private Long planId;
	
	@ApiModelProperty(value = "采购计划单号")
    private String planCode;
	
	@ApiModelProperty(value = "结算方式（0-现结；1-账期)")
    private Integer settlementType;
	
	@ApiModelProperty(value = "结算单位电话")
    private String settlementUnitPhone;
	
	@ApiModelProperty(value = "结算单位ID")
    private Long settlementUnitId;
	
	@ApiModelProperty(value = "结算单位地址")
    private String settlementUnitAddress;
	
	@ApiModelProperty(value = "发票类型（0-普通发票；1-增值税发票）")
    private Integer invoiceType;
	
	@ApiModelProperty(value = "纳税人识别号")
    private String taxpayerCode;
	
	@ApiModelProperty(value = "开户户名")
    private String accountName;
	
	@ApiModelProperty(value = "开户账号")
    private String account;
	
	@ApiModelProperty(value = "开户银行")
    private String bank;
	
	@ApiModelProperty(value = "承运方式（0-总部配送；1-委托运输；2-自提）")
    private Integer carriageMode;
	
	@ApiModelProperty(value = "承运单位")
    private String carriageUnit;
	
	@ApiModelProperty(value = "运输方式（0-陆运；1-空运；2-海运）")
    private Integer transportMode;
	
	@ApiModelProperty(value = "温控方式（0-冷藏车；1-冷藏箱；2-保温箱）")
	private Integer tempControlMode;
	
	@ApiModelProperty(value = "收货单位id")
    private Long receiveUnitId;
	
	@ApiModelProperty(value = "收货单位名称")
    private String receiveUnitName;
	
	@ApiModelProperty(value = "收货地址")
    private String receiveAddress;
	
	@ApiModelProperty(value = "收货人员id")
    private Long reveiverId;

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public Integer getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(Integer settlementType) {
		this.settlementType = settlementType;
	}

	public String getSettlementUnitPhone() {
		return settlementUnitPhone;
	}

	public void setSettlementUnitPhone(String settlementUnitPhone) {
		this.settlementUnitPhone = settlementUnitPhone;
	}

	public Long getSettlementUnitId() {
		return settlementUnitId;
	}

	public void setSettlementUnitId(Long settlementUnitId) {
		this.settlementUnitId = settlementUnitId;
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

	public Integer getCarriageMode() {
		return carriageMode;
	}

	public void setCarriageMode(Integer carriageMode) {
		this.carriageMode = carriageMode;
	}

	public String getCarriageUnit() {
		return carriageUnit;
	}

	public void setCarriageUnit(String carriageUnit) {
		this.carriageUnit = carriageUnit;
	}

	public Integer getTransportMode() {
		return transportMode;
	}

	public void setTransportMode(Integer transportMode) {
		this.transportMode = transportMode;
	}

	public Integer getTempControlMode() {
		return tempControlMode;
	}

	public void setTempControlMode(Integer tempControlMode) {
		this.tempControlMode = tempControlMode;
	}

	public Long getReceiveUnitId() {
		return receiveUnitId;
	}

	public void setReceiveUnitId(Long receiveUnitId) {
		this.receiveUnitId = receiveUnitId;
	}

	public String getReceiveUnitName() {
		return receiveUnitName;
	}

	public void setReceiveUnitName(String receiveUnitName) {
		this.receiveUnitName = receiveUnitName;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public Long getReveiverId() {
		return reveiverId;
	}

	public void setReveiverId(Long reveiverId) {
		this.reveiverId = reveiverId;
	}
	

	
}
