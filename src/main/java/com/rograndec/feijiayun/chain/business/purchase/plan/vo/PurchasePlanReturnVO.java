package com.rograndec.feijiayun.chain.business.purchase.plan.vo;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author dongyang.du 2017-09-18
 */
public class PurchasePlanReturnVO {

	/**
	 * 金额（合计）
	 */
	@ApiModelProperty(value = "金额总体合计）")
	private BigDecimal amountAllTotal;

	/**
	 * 无税金额（合计）
	 */
	@ApiModelProperty(value = "无税金额总体合计")
	private BigDecimal notaxAmountAllTotal;

	/**
	 * 税额（合计）
	 */
	@ApiModelProperty(value = "税额总体合计")
	private BigDecimal taxAmountAllTotal;

	@ApiModelProperty(value = "计划列表")
	private List<PurchasePlanPageVO> planVOs;

	public BigDecimal getAmountAllTotal() {
		return amountAllTotal;
	}

	public void setAmountAllTotal(BigDecimal amountAllTotal) {
		this.amountAllTotal = amountAllTotal;
	}

	public BigDecimal getNotaxAmountAllTotal() {
		return notaxAmountAllTotal;
	}

	public void setNotaxAmountAllTotal(BigDecimal notaxAmountAllTotal) {
		this.notaxAmountAllTotal = notaxAmountAllTotal;
	}

	public BigDecimal getTaxAmountAllTotal() {
		return taxAmountAllTotal;
	}

	public void setTaxAmountAllTotal(BigDecimal taxAmountAllTotal) {
		this.taxAmountAllTotal = taxAmountAllTotal;
	}

	public List<PurchasePlanPageVO> getPlanVOs() {
		return planVOs;
	}

	public void setPlanVOs(List<PurchasePlanPageVO> planVOs) {
		this.planVOs = planVOs;
	}

}
