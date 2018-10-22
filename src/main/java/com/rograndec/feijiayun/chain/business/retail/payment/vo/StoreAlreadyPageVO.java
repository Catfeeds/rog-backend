package com.rograndec.feijiayun.chain.business.retail.payment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "StoreAlreadyPageVO", description = "零售管理-零售缴款-门店-已缴款分页")
public class StoreAlreadyPageVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, value = "零售缴款ID")
	private Long id;
	
	@ApiModelProperty(required = true, value = "缴款日期")
	private Date paymentTime;
	
	@ApiModelProperty(required = true, value = "缴款日期")
	private String paymentDate;
	
	@ApiModelProperty(required = true, value = "单号")
	private String salePaymentCode;
	
	@ApiModelProperty(required = true, value = "收款人员")
	private String createrName;
	
	@ApiModelProperty(required = false, value = "缴款人员")
	private String paymentManName;
	
	@ApiModelProperty(required = true, value = "开班时间")
	private Date openingTime;
	
	@ApiModelProperty(required = true, value = "交班时间")
	private Date shiftTime;
	
	@ApiModelProperty(required = true, value = "应缴金额")
	private BigDecimal payAmountTotal;
	
	@ApiModelProperty(required = true, value = "实缴金额")
	private BigDecimal realAmountTotal;
	
	@ApiModelProperty(required = true, value = "缴款差异")
	private BigDecimal diffAmountTotal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getSalePaymentCode() {
		return salePaymentCode;
	}

	public void setSalePaymentCode(String salePaymentCode) {
		this.salePaymentCode = salePaymentCode;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getPaymentManName() {
		return paymentManName;
	}

	public void setPaymentManName(String paymentManName) {
		this.paymentManName = paymentManName;
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

	public BigDecimal getPayAmountTotal() {
		return payAmountTotal;
	}

	public void setPayAmountTotal(BigDecimal payAmountTotal) {
		this.payAmountTotal = payAmountTotal;
	}

	public BigDecimal getRealAmountTotal() {
		return realAmountTotal;
	}

	public void setRealAmountTotal(BigDecimal realAmountTotal) {
		this.realAmountTotal = realAmountTotal;
	}

	public BigDecimal getDiffAmountTotal() {
		return diffAmountTotal;
	}

	public void setDiffAmountTotal(BigDecimal diffAmountTotal) {
		this.diffAmountTotal = diffAmountTotal;
	}
	

}
