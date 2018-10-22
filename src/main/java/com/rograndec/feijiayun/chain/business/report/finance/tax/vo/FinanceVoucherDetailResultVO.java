package com.rograndec.feijiayun.chain.business.report.finance.tax.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class FinanceVoucherDetailResultVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2018年1月12日 下午3:17:31 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = false, value = "科目编码")
	private String accountCode;
	
	@ApiModelProperty(required = false, value = "科目名称")
	private String accountName;
	
	@ApiModelProperty(value = "明细科目类型")
	private Integer subAccountType;
	
	@ApiModelProperty(required = false, value = "明细科目编码")
	private String subAccountCode;
	
	@ApiModelProperty(required = false, value = "明细科目名称")
	private String subAccountName;
	
	@ApiModelProperty(value = "借方金额")
	private BigDecimal debitAmount;
	
	@ApiModelProperty(value = "贷方金额")
	private BigDecimal creditAmount;

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getSubAccountType() {
		return subAccountType;
	}

	public void setSubAccountType(Integer subAccountType) {
		this.subAccountType = subAccountType;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
