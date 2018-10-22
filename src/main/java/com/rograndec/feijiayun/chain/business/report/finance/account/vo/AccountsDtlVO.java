package com.rograndec.feijiayun.chain.business.report.finance.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.rograndec.feijiayun.chain.common.constant.OrderRule;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: AccountsDtlVO   
 * @Description: TODO(描述该类做什么)
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月11日 下午2:24:20
 */
@ApiModel(value = "AccountsDtlVO", description = "财务管理-应付/应收账款-应付/应收账款明细账显示")
public class AccountsDtlVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2018年1月11日 下午2:24:05 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	private String subAccountCodeAndName;
	
	@ApiModelProperty(value = "是否合并单元格")
	private boolean merge;
	
	@ApiModelProperty(value = "会计期间年")
	private Integer kjyear;
	
	@ApiModelProperty(value = "会计期间月")
	private Integer kjmonth;
	
	@ApiModelProperty(value = "年")
	private String year;
	
	@ApiModelProperty(value = "月")
	private String month;
	
	@ApiModelProperty(value = "日")
	private String day;
	
	@ApiModelProperty(value = "凭证日期")
	private String orderDate;
	
	@ApiModelProperty(value = "凭证号码")
	private String orderCode;
	
	@ApiModelProperty(value = "单据类型")
	private Integer orderType;
	
	@ApiModelProperty(value = "单据类型名称")
	private String orderTypeName;
	
	@ApiModelProperty(value = "过账人员")
	private String baseOperatorName;
	
	@ApiModelProperty(value = "摘要")
	private String remark;
	
	@ApiModelProperty(value = "供应商编码")
	private String subAccountCode;
	
	@ApiModelProperty(value = "供应商名称")
	private String subAccountName;
	
	@ApiModelProperty(value = "借方")
	private BigDecimal debitAmount;
	
	@ApiModelProperty(value = "贷方")
	private BigDecimal creditAmount;
	
	@ApiModelProperty(value = "方向")
	private String direction;
	
	@ApiModelProperty(value = "余额")
	private BigDecimal balance;
	
	public boolean isMerge() {
		return merge;
	}

	public void setMerge(boolean merge) {
		this.merge = merge;
	}
	
	public Integer getKjyear() {
		return kjyear;
	}

	public void setKjyear(Integer kjyear) {
		this.kjyear = kjyear;
	}

	public Integer getKjmonth() {
		return kjmonth;
	}

	public void setKjmonth(Integer kjmonth) {
		this.kjmonth = kjmonth;
	}

	public String getSubAccountCodeAndName() {
		return subAccountCodeAndName;
	}

	public void setSubAccountCodeAndName(String subAccountCodeAndName) {
		this.subAccountCodeAndName = subAccountCodeAndName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
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

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getOrderTypeName() {
		if(orderType != null) {
			orderTypeName = OrderRule.getName(orderType);
		}
		return orderTypeName;
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
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

	public String getSubAccountCode() {
		return subAccountCode;
	}

	public void setSubAccountCode(String subAccountCode) {
		this.subAccountCode = subAccountCode;
	}

	public String getSubAccountName() {
		return subAccountName;
	}

	public void setSubAccountName(String subAccountName) {
		this.subAccountName = subAccountName;
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

}
