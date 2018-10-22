package com.rograndec.feijiayun.chain.business.report.retail.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SaleAnalyClerkResultBranchTotalVO implements Serializable{
	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月16日 下午3:39:13 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value = "销售笔数")
    private Long saleQuantity = 0L;
    
    @ApiModelProperty(value = "销售金额")
    private BigDecimal realAmount = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "退货笔数")
    private Long returnQuantity = 0L;
    
    @ApiModelProperty(value = "退货金额")
    private BigDecimal returnRealAmount = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "总额")
    private BigDecimal realAmountTotal = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "不含税总额")
    private BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
    
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmountTotal = BigDecimal.ZERO;

    private List<SaleAnalyClerkResultBranchVO> branchVOList;
    
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<SaleAnalyClerkResultBranchVO> getBranchVOList() {
		return branchVOList;
	}

	public void setBranchVOList(List<SaleAnalyClerkResultBranchVO> branchVOList) {
		this.branchVOList = branchVOList;
	}
    
}
