package com.rograndec.feijiayun.chain.business.purchase.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class PurchaseOrderCountVO implements Serializable {
    /**
     * 金额合计（整单优惠前的金额合计）
     */
	@ApiModelProperty(value = "金额合计）")
    private BigDecimal amountTotal;
    /**
     * 不含税金额合计
     */
	@ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;
    /**
     * 税额合计
     */
	@ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;
	
	/**
	 * 订单列表
	 * */
	@ApiModelProperty(value = "订单列表")
	private List<PurchaseOrderResponseVO> purchaseOrderResponseListVO;
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
	public List<PurchaseOrderResponseVO> getPurchaseOrderResponseListVO() {
		return purchaseOrderResponseListVO;
	}
	public void setPurchaseOrderResponseListVO(List<PurchaseOrderResponseVO> purchaseOrderResponseListVO) {
		this.purchaseOrderResponseListVO = purchaseOrderResponseListVO;
	}
	
}