package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class ReveivableInvoiceListParamVo implements Serializable{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 金额
     */
    @ApiModelProperty(value = "总金额")
    private BigDecimal totalMoney;
    /**
     * 不含税金额
     */
    @ApiModelProperty(value = "不含税金额")
    private BigDecimal totalMoneyOutTax;
    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal totalTax;
    
    /**
     * 税额
     */
    @ApiModelProperty(value = "详情列表")
    private List<AdvanceReveivableInvoiceListVo> list;

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

	public List<AdvanceReveivableInvoiceListVo> getList() {
		return list;
	}

	public void setList(List<AdvanceReveivableInvoiceListVo> list) {
		this.list = list;
	}
    
    
}
