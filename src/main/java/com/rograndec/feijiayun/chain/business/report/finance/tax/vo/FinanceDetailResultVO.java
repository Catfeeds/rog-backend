package com.rograndec.feijiayun.chain.business.report.finance.tax.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class FinanceDetailResultVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2018年1月2日 下午6:04:55 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "税类型")
	private String taxType;
	
	@ApiModelProperty(value = "税类型名称")
	private String taxTypeName;
	
	@ApiModelProperty(value = "税率ID")
	private Long taxId;
	
	@ApiModelProperty(value = "税率")
	private String taxRate;
	
	@ApiModelProperty(value = "借方金额")
	private BigDecimal debitAmount;
	
	@ApiModelProperty(value = "贷方金额")
	private BigDecimal creditAmount;
	
	@ApiModelProperty(value = "方向")
	private String direction;
	
	@ApiModelProperty(value = "余额")
	private BigDecimal balance;
	
	private List<FinanceDetailDtlResultVO> dtlResultList;

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

	public List<FinanceDetailDtlResultVO> getDtlResultList() {
		return dtlResultList;
	}

	public void setDtlResultList(List<FinanceDetailDtlResultVO> dtlResultList) {
		this.dtlResultList = dtlResultList;
	}
	
}
