package com.rograndec.feijiayun.chain.inf.pos.report.vo;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaleCodeTotalVO", description = "按单据,合计列表")
public class SaleCodeTotalVO {
	
	@ApiModelProperty(value = "整单折扣")
    private BigDecimal wholeDiscountTotal = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "抹零")
    private BigDecimal wholeDiscountAmountTotal = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "应收")
    private BigDecimal realAmountTotal = BigDecimal.ZERO;
    
    List<SaleCodeVO> list;

	public BigDecimal getWholeDiscountTotal() {
		return wholeDiscountTotal;
	}

	public void setWholeDiscountTotal(BigDecimal wholeDiscountTotal) {
		this.wholeDiscountTotal = wholeDiscountTotal;
	}

	public BigDecimal getWholeDiscountAmountTotal() {
		return wholeDiscountAmountTotal;
	}

	public void setWholeDiscountAmountTotal(BigDecimal wholeDiscountAmountTotal) {
		this.wholeDiscountAmountTotal = wholeDiscountAmountTotal;
	}

	public BigDecimal getRealAmountTotal() {
		return realAmountTotal;
	}

	public void setRealAmountTotal(BigDecimal realAmountTotal) {
		this.realAmountTotal = realAmountTotal;
	}

	public List<SaleCodeVO> getList() {
		return list;
	}

	public void setList(List<SaleCodeVO> list) {
		this.list = list;
	}
    
    
	
}
