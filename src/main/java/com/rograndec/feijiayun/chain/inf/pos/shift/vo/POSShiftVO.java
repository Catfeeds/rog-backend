package com.rograndec.feijiayun.chain.inf.pos.shift.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "POSShiftVO", description = "POS交班查看数据")
public class POSShiftVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键id")
	private Long id;
	
	@ApiModelProperty(value = "是否交班，交班为true,未交班为false")
	private boolean flagShift;
	
	//默认当前登录人员
	@ApiModelProperty(value = "交班人员")
	private String payeeName;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "开班时间")
	private Date openingTime;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "交班时间")
	private Date shiftTime;
	
	@ApiModelProperty(value = "销售笔数")
	private int salePens;
	
	@ApiModelProperty(value = "销售金额")
	private BigDecimal saleAmount;
	
	@ApiModelProperty(value = "退货笔数")
	private int returnPens;
	
	@ApiModelProperty(value = "退货金额")
	private BigDecimal returnAmount;
	
	@ApiModelProperty(value = "接收备用金")
	private BigDecimal acceptSpareMoney;
	
	@ApiModelProperty(value = "应缴现金")
	private BigDecimal payableCash;
	
	@ApiModelProperty(value = "上缴现金")
	private BigDecimal cash;
	
	@ApiModelProperty(value = "下放备用金")
	private BigDecimal sendSpareMoney;
	
	@ApiModelProperty(value = "支付方式")
	private List<POSPaymentListVO> paymentList = new ArrayList<>();
	
	@ApiModelProperty(value = "应收金额")
	private BigDecimal totalPay;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isFlagShift() {
		return flagShift;
	}

	public void setFlagShift(boolean flagShift) {
		this.flagShift = flagShift;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public Date getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(Date openingTime) {
		this.openingTime = openingTime;
	}

	public Date getShiftTime() {
		return shiftTime;
	}

	public void setShiftTime(Date shiftTime) {
		this.shiftTime = shiftTime;
	}

	public int getSalePens() {
		return salePens;
	}

	public void setSalePens(int salePens) {
		this.salePens = salePens;
	}

	public BigDecimal getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}

	public int getReturnPens() {
		return returnPens;
	}

	public void setReturnPens(int returnPens) {
		this.returnPens = returnPens;
	}

	public BigDecimal getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(BigDecimal returnAmount) {
		this.returnAmount = returnAmount;
	}

	public BigDecimal getAcceptSpareMoney() {
		return acceptSpareMoney;
	}

	public void setAcceptSpareMoney(BigDecimal acceptSpareMoney) {
		this.acceptSpareMoney = acceptSpareMoney;
	}

	public BigDecimal getPayableCash() {
		return payableCash;
	}

	public void setPayableCash(BigDecimal payableCash) {
		this.payableCash = payableCash;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public BigDecimal getSendSpareMoney() {
		return sendSpareMoney;
	}

	public void setSendSpareMoney(BigDecimal sendSpareMoney) {
		this.sendSpareMoney = sendSpareMoney;
	}

	public List<POSPaymentListVO> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<POSPaymentListVO> paymentList) {
		this.paymentList = paymentList;
	}

	public BigDecimal getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(BigDecimal totalPay) {
		this.totalPay = totalPay;
	}
	
	
	
}
