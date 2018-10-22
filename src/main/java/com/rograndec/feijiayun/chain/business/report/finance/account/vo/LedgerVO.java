package com.rograndec.feijiayun.chain.business.report.finance.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: LedgerVO   
 * @Description: 应付/应收总账显示
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月8日 下午4:08:19
 */
@ApiModel(value = "LedgerVO", description = "财务管理-应付/应收账款-应付/应收总账显示")
public class LedgerVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2018年1月8日 下午4:08:16 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "是否合并单元格")
	private boolean merge;
	
	@ApiModelProperty(value = "会计期间年")
	private Integer year;
	
	@ApiModelProperty(value = "会计期间月")
	private Integer month;
	
	@ApiModelProperty(value = "分组显示日期")
	private String showOrderDate;
	
	@ApiModelProperty(value = "日期")
	private String orderDate;
	
	@ApiModelProperty(value = "供货单位编码")
	private String subAccountCode;
	
	@ApiModelProperty(value = "供货单位名称")
	private String subAccountName;
	
	@ApiModelProperty(value = "期初余额-方向")
	private String startDirection;
	
	@ApiModelProperty(value = "期初余额-金额")
	private BigDecimal startBalance;
	
	@ApiModelProperty(value = "本期发生额-借方")
	private BigDecimal debitAmount;
	
	@ApiModelProperty(value = "本期发生额-贷方")
	private BigDecimal creditAmount;
	
	@ApiModelProperty(value = "期末余额-方向")
	private String endDirection;
	
	@ApiModelProperty(value = "期末余额-余额")
	private BigDecimal endBalance;
	
	@ApiModelProperty(hidden=true)
	private Long minId;
	
	@ApiModelProperty(hidden=true)
	private Long maxId;


	public boolean isMerge() {
		return merge;
	}

	public void setMerge(boolean merge) {
		this.merge = merge;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getShowOrderDate() {
		return showOrderDate;
	}

	public void setShowOrderDate(String showOrderDate) {
		this.showOrderDate = showOrderDate;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
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

	public String getStartDirection() {
		return startDirection;
	}

	public void setStartDirection(String startDirection) {
		this.startDirection = startDirection;
	}

	public BigDecimal getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
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

	public String getEndDirection() {
		return endDirection;
	}

	public void setEndDirection(String endDirection) {
		this.endDirection = endDirection;
	}

	public BigDecimal getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}

	public Long getMinId() {
		return minId;
	}

	public void setMinId(Long minId) {
		this.minId = minId;
	}

	public Long getMaxId() {
		return maxId;
	}

	public void setMaxId(Long maxId) {
		this.maxId = maxId;
	}

	@Override
	public String toString() {
		return "LedgerVO [merge=" + merge + ", year=" + year + ", month=" + month + ", showOrderDate=" + showOrderDate
				+ ", orderDate=" + orderDate + ", subAccountCode=" + subAccountCode + ", subAccountName="
				+ subAccountName + ", startDirection=" + startDirection + ", startBalance=" + startBalance
				+ ", debitAmount=" + debitAmount + ", creditAmount=" + creditAmount + ", endDirection=" + endDirection
				+ ", endBalance=" + endBalance + ", minId=" + minId + ", maxId=" + maxId + "]";
	}

}
