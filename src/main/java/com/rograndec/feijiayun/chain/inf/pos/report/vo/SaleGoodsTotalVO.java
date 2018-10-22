package com.rograndec.feijiayun.chain.inf.pos.report.vo;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaleGoodsTotalVO", description = "按明细，合计")
public class SaleGoodsTotalVO {

	@ApiModelProperty(value = "数量合计")
	private BigDecimal quantityTotal = BigDecimal.ZERO;

	@ApiModelProperty(value = "金额合计")
	private BigDecimal amountTotal = BigDecimal.ZERO;

	@ApiModelProperty(value = "抹零分摊")
	private BigDecimal lineDiscountAmount = BigDecimal.ZERO;

	@ApiModelProperty(value = "总额")
	private BigDecimal realAmount = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "数据list")
	private List<SaleGoodsVO> list;

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

	public BigDecimal getLineDiscountAmount() {
		return lineDiscountAmount;
	}

	public void setLineDiscountAmount(BigDecimal lineDiscountAmount) {
		this.lineDiscountAmount = lineDiscountAmount;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public List<SaleGoodsVO> getList() {
		return list;
	}

	public void setList(List<SaleGoodsVO> list) {
		this.list = list;
	}
	

}
