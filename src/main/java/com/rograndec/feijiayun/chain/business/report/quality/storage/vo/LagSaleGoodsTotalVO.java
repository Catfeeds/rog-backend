package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "LagSaleGoodsTotalVO", description = "滞销商品列表")
public class LagSaleGoodsTotalVO{

	
	@ApiModelProperty(value = "数量合计")
	private BigDecimal usableQuantityTotal;
	
	@ApiModelProperty(value = "本页小计(打印数据)")
	private BigDecimal usableQuantitySubtotal;
	
	@ApiModelProperty(value = "滞销商品列表")
	private List<LagSaleGoodsVO> list;

	public BigDecimal getUsableQuantityTotal() {
		return usableQuantityTotal;
	}

	public void setUsableQuantityTotal(BigDecimal usableQuantityTotal) {
		this.usableQuantityTotal = usableQuantityTotal;
	}
	
	public BigDecimal getUsableQuantitySubtotal() {
		return usableQuantitySubtotal;
	}

	public void setUsableQuantitySubtotal(BigDecimal usableQuantitySubtotal) {
		this.usableQuantitySubtotal = usableQuantitySubtotal;
	}

	public List<LagSaleGoodsVO> getList() {
		return list;
	}

	public void setList(List<LagSaleGoodsVO> list) {
		this.list = list;
	}
	
}
