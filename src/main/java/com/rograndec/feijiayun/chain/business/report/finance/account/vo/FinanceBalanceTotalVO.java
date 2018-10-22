package com.rograndec.feijiayun.chain.business.report.finance.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "FinanceBalanceTotalVO", description = "财务管理-应付/应收账款-应付/应收账款余额合计显示")
public class FinanceBalanceTotalVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2018年1月3日 下午5:16:51 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "借方合计")
	private BigDecimal debitAmountTotal = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "贷方合计")
	private BigDecimal creditAmountTotal = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "方向")
	private String direction;
	
	@ApiModelProperty(value = "余额合计")
	private BigDecimal balanceTotal = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "借方小计")
	private BigDecimal debitAmountSubtotal;
	
	@ApiModelProperty(value = "贷方小计")
	private BigDecimal creditAmountTSubtotal;
	
	@ApiModelProperty(value = "余额小计")
	private BigDecimal balanceSubtotal;
	
	@ApiModelProperty(value = "应付/应收列表数据")
	private List<FinanceBalanceVO> list;

	public BigDecimal getDebitAmountTotal() {
		return debitAmountTotal;
	}

	public void setDebitAmountTotal(BigDecimal debitAmountTotal) {
		this.debitAmountTotal = debitAmountTotal;
	}

	public BigDecimal getCreditAmountTotal() {
		return creditAmountTotal;
	}

	public void setCreditAmountTotal(BigDecimal creditAmountTotal) {
		this.creditAmountTotal = creditAmountTotal;
	}

	public BigDecimal getBalanceTotal() {
		return balanceTotal;
	}

	public void setBalanceTotal(BigDecimal balanceTotal) {
		this.balanceTotal = balanceTotal;
	}

	public BigDecimal getDebitAmountSubtotal() {
		return debitAmountSubtotal;
	}

	public void setDebitAmountSubtotal(BigDecimal debitAmountSubtotal) {
		this.debitAmountSubtotal = debitAmountSubtotal;
	}

	public BigDecimal getCreditAmountTSubtotal() {
		return creditAmountTSubtotal;
	}

	public void setCreditAmountTSubtotal(BigDecimal creditAmountTSubtotal) {
		this.creditAmountTSubtotal = creditAmountTSubtotal;
	}

	public BigDecimal getBalanceSubtotal() {
		return balanceSubtotal;
	}

	public void setBalanceSubtotal(BigDecimal balanceSubtotal) {
		this.balanceSubtotal = balanceSubtotal;
	}

	public List<FinanceBalanceVO> getList() {
		return list;
	}

	public void setList(List<FinanceBalanceVO> list) {
		this.list = list;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	

}
