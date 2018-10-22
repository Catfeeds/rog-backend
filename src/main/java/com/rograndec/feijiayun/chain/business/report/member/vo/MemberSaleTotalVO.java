package com.rograndec.feijiayun.chain.business.report.member.vo;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MemberSaleTotalVO", description = "会员销售报表列表")
public class MemberSaleTotalVO {
	
	@ApiModelProperty(value = "数量合计")
	private BigDecimal quantityTotal;
	@ApiModelProperty(value = "金额合计")
	private BigDecimal amountTotal;
	@ApiModelProperty(value = "积分合计")
	private BigDecimal currentIntegralTotal;
	
	@ApiModelProperty(value = "会员销售list")
	private List<MemberSaleVO> list;

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

	public BigDecimal getCurrentIntegralTotal() {
		return currentIntegralTotal;
	}

	public void setCurrentIntegralTotal(BigDecimal currentIntegralTotal) {
		this.currentIntegralTotal = currentIntegralTotal;
	}

	public List<MemberSaleVO> getList() {
		return list;
	}

	public void setList(List<MemberSaleVO> list) {
		this.list = list;
	}
	
	
	
}
