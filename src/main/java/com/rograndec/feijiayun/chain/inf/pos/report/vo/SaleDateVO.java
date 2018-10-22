package com.rograndec.feijiayun.chain.inf.pos.report.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaleDateVO", description = "按日期")
public class SaleDateVO {
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "销售日期")
	private Date saleDate;
	
	@ApiModelProperty(value = "款台")
	private String standCode;

    @ApiModelProperty(value = "收款员名称")
    private String payeeName;
    
    @ApiModelProperty(value = "销售笔数")
    private Long saleQuantity = 0L;
    
    @ApiModelProperty(value = "销售金额")
    private BigDecimal realAmount = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "退货笔数")
    private Long returnQuantity = 0L;
    
    @ApiModelProperty(value = "退货金额")
    private BigDecimal returnRealAmount = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "应缴现金")
    private BigDecimal realAmountTotal = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "实缴现金")
    private BigDecimal cash = BigDecimal.ZERO;

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public String getStandCode() {
		return standCode;
	}

	public void setStandCode(String standCode) {
		this.standCode = standCode;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public Long getSaleQuantity() {
		return saleQuantity;
	}

	public void setSaleQuantity(Long saleQuantity) {
		this.saleQuantity = saleQuantity;
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

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	
}
