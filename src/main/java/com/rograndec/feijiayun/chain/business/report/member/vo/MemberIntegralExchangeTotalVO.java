package com.rograndec.feijiayun.chain.business.report.member.vo;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MemberIntegralExchangeTotalVO", description = "积分兑换报表列表")
public class MemberIntegralExchangeTotalVO {
	
	@ApiModelProperty(value = "兑换积分总数")
	private BigDecimal integralExchangeTotal;

	@ApiModelProperty(value = "兑换数量总数")
	private BigDecimal exchangeQuantityTotal;

	@ApiModelProperty(value = "扣除积分总数")
	private BigDecimal deductIntegralTotal;
	
	@ApiModelProperty(value = "列表数据")
	private List<MemberIntegralExchangeVO> list;

	public BigDecimal getIntegralExchangeTotal() {
		return integralExchangeTotal;
	}

	public void setIntegralExchangeTotal(BigDecimal integralExchangeTotal) {
		this.integralExchangeTotal = integralExchangeTotal;
	}

	public BigDecimal getExchangeQuantityTotal() {
		return exchangeQuantityTotal;
	}

	public void setExchangeQuantityTotal(BigDecimal exchangeQuantityTotal) {
		this.exchangeQuantityTotal = exchangeQuantityTotal;
	}

	public BigDecimal getDeductIntegralTotal() {
		return deductIntegralTotal;
	}

	public void setDeductIntegralTotal(BigDecimal deductIntegralTotal) {
		this.deductIntegralTotal = deductIntegralTotal;
	}

	public List<MemberIntegralExchangeVO> getList() {
		return list;
	}

	public void setList(List<MemberIntegralExchangeVO> list) {
		this.list = list;
	}

	
}
