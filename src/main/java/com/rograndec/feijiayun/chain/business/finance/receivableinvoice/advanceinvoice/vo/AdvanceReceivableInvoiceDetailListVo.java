package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoiceDetail;

import io.swagger.annotations.ApiModelProperty;

public class AdvanceReceivableInvoiceDetailListVo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 发票详情集合
	 */
    @ApiModelProperty(value = "发票详情集合")
	private List<AdvanceReceivableInvoiceDetail> arbidList;
    
    @ApiModelProperty(value = "商品类型总数")
	private int totalCount;
    
    @ApiModelProperty(value = "商品总数")
    private BigDecimal totalNum;
    
    /**
     * 总额
     */
    @ApiModelProperty(value = "总额")
    private BigDecimal totalMoney;
    
    /**
     * 总额不含锐
     */
    @ApiModelProperty(value = "总额不含锐")
    private BigDecimal totalMoneyOutTax;
    /**
     * 税总额
     */
    @ApiModelProperty(value = "锐总额")
    private BigDecimal totalTax;
    
    /**
     * 已清数量总额
     */
 /*   @ApiModelProperty(value = "已清数量总额")
    private BigDecimal totalClearQuantity;*/
    /**
     * 未清数量总额
     */
/*    @ApiModelProperty(value = "未清数量总额")
    private BigDecimal totalUnclearQuantity;*/
    /**
     * 已对账数量
     */
    @ApiModelProperty(value = "已对账数量")
    private BigDecimal accountQuantity;   
    /**
     * 未对账数量
     */
    @ApiModelProperty(value = "未对账数量")
    private BigDecimal unaccountQuantity;
    /**
     * 对账金额差总额
     */
    @ApiModelProperty(value = " 对账金额差总额")
    private BigDecimal totalDiffAmount;
    /**
     * 不含税金额差额总额
     */
    @ApiModelProperty(value = " 不含税金额差额总额")
    private BigDecimal totalDiffNotaxAmount;
    /**
     * 税额差额总额
     */
    @ApiModelProperty(value = " 税额差额总额")
    private BigDecimal totalDiffTaxAmount;
    
    
    
	public List<AdvanceReceivableInvoiceDetail> getArbidList() {
		return arbidList;
	}
	public void setArbidList(List<AdvanceReceivableInvoiceDetail> arbidList) {
		this.arbidList = arbidList;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public BigDecimal getTotalMoneyOutTax() {
		return totalMoneyOutTax;
	}
	public void setTotalMoneyOutTax(BigDecimal totalMoneyOutTax) {
		this.totalMoneyOutTax = totalMoneyOutTax;
	}
	public BigDecimal getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(BigDecimal totalTax) {
		this.totalTax = totalTax;
	}
	public BigDecimal getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}
	/*public BigDecimal getTotalClearQuantity() {
		return totalClearQuantity;
	}
	public void setTotalClearQuantity(BigDecimal totalClearQuantity) {
		this.totalClearQuantity = totalClearQuantity;
	}
	public BigDecimal getTotalUnclearQuantity() {
		return totalUnclearQuantity;
	}
	public void setTotalUnclearQuantity(BigDecimal totalUnclearQuantity) {
		this.totalUnclearQuantity = totalUnclearQuantity;
	}*/
	public BigDecimal getTotalDiffAmount() {
		return totalDiffAmount;
	}
	public void setTotalDiffAmount(BigDecimal totalDiffAmount) {
		this.totalDiffAmount = totalDiffAmount;
	}
	public BigDecimal getTotalDiffNotaxAmount() {
		return totalDiffNotaxAmount;
	}
	public void setTotalDiffNotaxAmount(BigDecimal totalDiffNotaxAmount) {
		this.totalDiffNotaxAmount = totalDiffNotaxAmount;
	}
	public BigDecimal getTotalDiffTaxAmount() {
		return totalDiffTaxAmount;
	}
	public void setTotalDiffTaxAmount(BigDecimal totalDiffTaxAmount) {
		this.totalDiffTaxAmount = totalDiffTaxAmount;
	}
	public BigDecimal getAccountQuantity() {
		return accountQuantity;
	}
	public void setAccountQuantity(BigDecimal accountQuantity) {
		this.accountQuantity = accountQuantity;
	}
	public BigDecimal getUnaccountQuantity() {
		return unaccountQuantity;
	}
	public void setUnaccountQuantity(BigDecimal unaccountQuantity) {
		this.unaccountQuantity = unaccountQuantity;
	}
    
}
