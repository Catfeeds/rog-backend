package com.rograndec.feijiayun.chain.inf.pos.shift.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "POSCommitShiftVO", description = "POS交班提交数据")
public class POSCommitShiftVO implements Serializable {

	private static final long serialVersionUID = 1L;
	

	@ApiModelProperty(value = "交班主键id，离线时不用传")
	private Long id;
	
	@NotNull(message="交班人员id不能为空！")
	@ApiModelProperty(value = "款员Id")
	private Long payeeId;

	@ApiModelProperty(value = "交班人员",hidden=true)
	private String payeeName;
	
	@ApiModelProperty(value = "开班人员账号",hidden=true)
	private String openingAccount;
	
	@ApiModelProperty(value = "款员编码",hidden=true)
	private String payeeCode;

	@NotNull(message="开班时间不能为空!")
	@ApiModelProperty(value = "开班时间,yyyy-MM-dd HH:mm:ss")
	private Date openingTime;
	
	@NotNull(message="交班时间不能为空!")
	@ApiModelProperty(value = "交班时间,yyyy-MM-dd HH:mm:ss")
	private Date shiftTime;
	
	@NotNull(message="销售笔数不能为空!")
	@ApiModelProperty(value = "销售笔数")
	private int salePens;
	
	@NotNull(message="销售笔数不能为空!")
	@ApiModelProperty(value = "销售金额")
	private BigDecimal saleAmount;
	
	@NotNull(message="退货笔数不能为空!")
	@ApiModelProperty(value = "退货笔数")
	private int returnPens;
	
	@NotNull(message="退货金额不能为空!")
	@ApiModelProperty(value = "退货金额")
	private BigDecimal returnAmount;
	
	@NotNull(message="接收备用金不能为空!")
	@ApiModelProperty(value = "接收备用金")
	private BigDecimal acceptSpareMoney;
	
	@NotNull(message="应缴现金不能为空!")
	@ApiModelProperty(value = "应缴现金")
	private BigDecimal payableCash;
	
	@NotNull(message="上缴现金不能为空!")
	@ApiModelProperty(value = "上缴现金")
	private BigDecimal cash;
	
	@NotNull(message="下放备用金不能为空!")
	@ApiModelProperty(value = "下放备用金")
	private BigDecimal sendSpareMoney;
	
	@ApiModelProperty(value = "销售单数据,有销售则传，无则为空")
	List<POSCommitShiftDetailVO> orderList = new ArrayList<>();
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Long payeeId) {
		this.payeeId = payeeId;
	}

	public List<POSCommitShiftDetailVO> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<POSCommitShiftDetailVO> orderList) {
		this.orderList = orderList;
	}

	public String getOpeningAccount() {
		return openingAccount;
	}

	public void setOpeningAccount(String openingAccount) {
		this.openingAccount = openingAccount;
	}

	public String getPayeeCode() {
		return payeeCode;
	}

	public void setPayeeCode(String payeeCode) {
		this.payeeCode = payeeCode;
	}
	
	
}
