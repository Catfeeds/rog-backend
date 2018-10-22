package com.rograndec.feijiayun.chain.business.purchase.addinstorage.entity;

import java.math.BigDecimal;

/**
 * 
 * @ClassName: PurchaseCalcul
 * @Description: 主表属性
 * @author yuting.li
 * @version 1.0
 * @date 2017年12月5日 下午4:47:28
 */
public class PurchaseCalcul {

	private BigDecimal quantityTotal = BigDecimal.ZERO;// 数量合计
	private BigDecimal amountTotal = BigDecimal.ZERO;// 金额合计
	private BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;// 不含税总额
	private BigDecimal taxAmountTotal = BigDecimal.ZERO;// 税额
	private BigDecimal realAmountTotal = BigDecimal.ZERO;// 实际金额合计

	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}

	public BigDecimal getNotaxRealAmountTotal() {
		return notaxRealAmountTotal;
	}

	public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
		this.notaxRealAmountTotal = notaxRealAmountTotal;
	}

	public BigDecimal getTaxAmountTotal() {
		return taxAmountTotal;
	}

	public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
		this.taxAmountTotal = taxAmountTotal;
	}

	public BigDecimal getRealAmountTotal() {
		return realAmountTotal;
	}

	public void setRealAmountTotal(BigDecimal realAmountTotal) {
		this.realAmountTotal = realAmountTotal;
	}

}
