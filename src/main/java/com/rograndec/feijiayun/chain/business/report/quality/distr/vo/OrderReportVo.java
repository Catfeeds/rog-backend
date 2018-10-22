package com.rograndec.feijiayun.chain.business.report.quality.distr.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel(value = "OrderReportVo", description = "报表返回对象")
public class OrderReportVo<T> implements Serializable{

	@ApiModelProperty(value = "数据集合")
	private List<T> dataList;

	@ApiModelProperty(value = "商品数量合计/配后退回数量")
	private BigDecimal quantity;
	@ApiModelProperty(value = "要货数量合计")
	private BigDecimal requestQuantity;
	@ApiModelProperty(value = "配货数量合计")
	private BigDecimal sendQuantity;
	@ApiModelProperty(value = "缺配数量合计")
	private BigDecimal lackQuantity;
	@ApiModelProperty(value = "到货数量合计")
	private BigDecimal arrivalQuantity;
	@ApiModelProperty(value = "收货数量合计")
	private BigDecimal receiveQuantity;
	@ApiModelProperty(value = "拒收数量合计")
	private BigDecimal refuseQuantity;

	@ApiModelProperty(value = "金额合计")
	private BigDecimal amount;

	@ApiModelProperty(value = "实际金额合计")
	private BigDecimal realAmount;

	@ApiModelProperty(value = "不含税金额合计")
	private BigDecimal notaxRealAmount;

	@ApiModelProperty(value = "税额合计")
	private BigDecimal taxAmount;

	public BigDecimal getArrivalQuantity() {
		return arrivalQuantity;
	}

	public void setArrivalQuantity(BigDecimal arrivalQuantity) {
		this.arrivalQuantity = arrivalQuantity;
	}

	public BigDecimal getReceiveQuantity() {
		return receiveQuantity;
	}

	public void setReceiveQuantity(BigDecimal receiveQuantity) {
		this.receiveQuantity = receiveQuantity;
	}

	public BigDecimal getRefuseQuantity() {
		return refuseQuantity;
	}

	public void setRefuseQuantity(BigDecimal refuseQuantity) {
		this.refuseQuantity = refuseQuantity;
	}

	public BigDecimal getRequestQuantity() {
		return requestQuantity;
	}

	public void setRequestQuantity(BigDecimal requestQuantity) {
		this.requestQuantity = requestQuantity;
	}

	public BigDecimal getSendQuantity() {
		return sendQuantity;
	}

	public void setSendQuantity(BigDecimal sendQuantity) {
		this.sendQuantity = sendQuantity;
	}

	public BigDecimal getLackQuantity() {
		return lackQuantity;
	}

	public void setLackQuantity(BigDecimal lackQuantity) {
		this.lackQuantity = lackQuantity;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
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
}
