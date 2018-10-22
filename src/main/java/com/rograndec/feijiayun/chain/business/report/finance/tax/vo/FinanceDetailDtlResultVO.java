package com.rograndec.feijiayun.chain.business.report.finance.tax.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class FinanceDetailDtlResultVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2018年1月2日 下午6:04:55 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "凭证日期-年")
	private Integer year;
	
	@ApiModelProperty(value = "凭证日期-月")
	private String month;
	
	@ApiModelProperty(value = "凭证日期-日")
	private String day;
	
	@ApiModelProperty(value = "税类型-前端不用")
	private String taxType;
	
	@ApiModelProperty(value = "税类型名称-前端不用")
	private String taxTypeName;
	
	@ApiModelProperty(value = "税率ID-前端不用")
	private Long taxId;
	
	@ApiModelProperty(value = "税率-前端不用")
	private String taxRate;
	
	@ApiModelProperty(value = "凭证日期, 当baseOrderType为-10000时，该属性为“税类型-税率”，如“进项税-0%”，需要合并单元格")
	private String orderDate;

	@ApiModelProperty(value = "凭证号码")
	private String orderCode;
	
	@ApiModelProperty(value = "单据类型，当baseOrderType为-10000时，orderDate为“税类型-税率”，如“进项税-0%”，需要合并单元格")
	private Integer baseOrderType;
	
	@ApiModelProperty(value = "单据类型名称")
	private String baseOrderTypeName;
	
	@ApiModelProperty(value = "过账人员")
	private String baseOperatorName;
	
	@ApiModelProperty(value = "摘要")
	private String remark;
	
	@ApiModelProperty(value = "借方金额")
	private BigDecimal debitAmount;
	
	@ApiModelProperty(value = "贷方金额")
	private BigDecimal creditAmount;
	
	@ApiModelProperty(value = "方向")
	private String direction;
	
	@ApiModelProperty(value = "余额")
	private BigDecimal balance;
	
	@ApiModelProperty(value = "会计年-前端不用")
	private Integer periodYear;
	
	@ApiModelProperty(value = "会计月-前端不用")
	private Integer periodMonth;

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getBaseOrderType() {
		return baseOrderType;
	}

	public void setBaseOrderType(Integer baseOrderType) {
		this.baseOrderType = baseOrderType;
	}

	public String getBaseOrderTypeName() {
		return baseOrderTypeName;
	}

	public void setBaseOrderTypeName(String baseOrderTypeName) {
		this.baseOrderTypeName = baseOrderTypeName;
	}

	public String getBaseOperatorName() {
		return baseOperatorName;
	}

	public void setBaseOperatorName(String baseOperatorName) {
		this.baseOperatorName = baseOperatorName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(BigDecimal debitAmount) {
		this.debitAmount = debitAmount;
	}

	public BigDecimal getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getPeriodYear() {
		return periodYear;
	}

	public void setPeriodYear(Integer periodYear) {
		this.periodYear = periodYear;
	}

	public Integer getPeriodMonth() {
		return periodMonth;
	}

	public void setPeriodMonth(Integer periodMonth) {
		this.periodMonth = periodMonth;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public String getTaxTypeName() {
		return taxTypeName;
	}

	public void setTaxTypeName(String taxTypeName) {
		this.taxTypeName = taxTypeName;
	}

	public Long getTaxId() {
		return taxId;
	}

	public void setTaxId(Long taxId) {
		this.taxId = taxId;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	
	
}
