package com.rograndec.feijiayun.chain.inf.pos.report.vo;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaleDateTotalVO", description = "按日期")
public class SaleDateTotalVO {
	
	@ApiModelProperty(value = "销售笔数")
    private Long saleQuantityTotal = 0L;
    
    @ApiModelProperty(value = "销售金额")
    private BigDecimal realAmount = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "退货笔数")
    private Long returnQuantity = 0L;
    
    @ApiModelProperty(value = "退货金额")
    private BigDecimal returnRealAmount = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "应缴现金")
    private BigDecimal realAmountTotal = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "实缴现金")
    private BigDecimal cashTotal = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "销售数据list")
    List<SaleDateVO> list;

	public Long getSaleQuantityTotal() {
		return saleQuantityTotal;
	}

	public void setSaleQuantityTotal(Long saleQuantityTotal) {
		this.saleQuantityTotal = saleQuantityTotal;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public Long getReturnQuantity() {
		return returnQuantity;
	}

	public void setReturnQuantity(Long returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public BigDecimal getReturnRealAmount() {
		return returnRealAmount;
	}

	public void setReturnRealAmount(BigDecimal returnRealAmount) {
		this.returnRealAmount = returnRealAmount;
	}

	public BigDecimal getRealAmountTotal() {
		return realAmountTotal;
	}

	public void setRealAmountTotal(BigDecimal realAmountTotal) {
		this.realAmountTotal = realAmountTotal;
	}

	public BigDecimal getCashTotal() {
		return cashTotal;
	}

	public void setCashTotal(BigDecimal cashTotal) {
		this.cashTotal = cashTotal;
	}

	public List<SaleDateVO> getList() {
		return list;
	}

	public void setList(List<SaleDateVO> list) {
		this.list = list;
	}
    
	
}
