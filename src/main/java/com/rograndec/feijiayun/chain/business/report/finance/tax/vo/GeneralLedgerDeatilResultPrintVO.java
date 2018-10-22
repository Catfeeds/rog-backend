package com.rograndec.feijiayun.chain.business.report.finance.tax.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class GeneralLedgerDeatilResultPrintVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2018年1月2日 下午6:01:25 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	/*@ApiModelProperty(value = "是否合并")
	private boolean merge;*/
	
	@ApiModelProperty(value = "显示年月")
	private String showOrderDate;
	
	@ApiModelProperty(value = "税类型")
	private String taxType;
	
	@ApiModelProperty(value = "税类型名称")
	private String taxTypeName;
	
	@ApiModelProperty(value = "税率ID，-10000时，为年月，需要合并整行")
	private Long taxId;
	
	@ApiModelProperty(value = "税率")
	private String taxRate;
	
	@ApiModelProperty(value = "期初方向")
	private String beginDirection;
	
	@ApiModelProperty(value = "期初余额")
	private BigDecimal beginBalance;
	
	@ApiModelProperty(value = "本期借方金额")
	private BigDecimal debitAmount;
	
	@ApiModelProperty(value = "本期贷方金额")
	private BigDecimal creditAmount;
	
	@ApiModelProperty(value = "期末方向")
	private String endDirection;
	
	@ApiModelProperty(value = "期末余额")
	private BigDecimal endBalance;
	
	@ApiModelProperty(value = "前台不用")
	private Long beginBalanceId;
	
	@ApiModelProperty(value = "前台不用")
	private Long endBalanceId;
	
	private List<GeneralLedgerDeatilResultVO> printVOList;
	
	
	
	/*public boolean isMerge() {
		return merge;
	}

	public void setMerge(boolean merge) {
		this.merge = merge;
	}*/
	
	public GeneralLedgerDeatilResultPrintVO(String showOrderDate) {
		super();
		this.showOrderDate = showOrderDate;
	}

	public String getShowOrderDate() {
		return showOrderDate;
	}

	public void setShowOrderDate(String showOrderDate) {
		this.showOrderDate = showOrderDate;
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

	public String getBeginDirection() {
		return beginDirection;
	}

	public void setBeginDirection(String beginDirection) {
		this.beginDirection = beginDirection;
	}

	public BigDecimal getBeginBalance() {
		return beginBalance;
	}

	public void setBeginBalance(BigDecimal beginBalance) {
		this.beginBalance = beginBalance;
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
	
	public Long getBeginBalanceId() {
		return beginBalanceId;
	}

	public void setBeginBalanceId(Long beginBalanceId) {
		this.beginBalanceId = beginBalanceId;
	}

	public Long getEndBalanceId() {
		return endBalanceId;
	}

	public void setEndBalanceId(Long endBalanceId) {
		this.endBalanceId = endBalanceId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<GeneralLedgerDeatilResultVO> getPrintVOList() {
		return printVOList;
	}

	public void setPrintVOList(List<GeneralLedgerDeatilResultVO> printVOList) {
		this.printVOList = printVOList;
	}
	
	
}
