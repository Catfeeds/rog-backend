package com.rograndec.feijiayun.chain.business.report.retail.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaleAnalyPayeeBranchDoubleClickResultTotalVO", description = "按销售日期查询收款人员双击分页对象")
public class SaleAnalyPayeeBranchDoubleClickResultTotalVO implements Serializable{

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

	@ApiModelProperty(value = "列表对象")
	private List<SaleAnalyPayeeBranchDoubleClickResultVO> resultBranchVOList;

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

	public List<SaleAnalyPayeeBranchDoubleClickResultVO> getResultBranchVOList() {
		return resultBranchVOList;
	}

	public void setResultBranchVOList(
			List<SaleAnalyPayeeBranchDoubleClickResultVO> resultBranchVOList) {
		this.resultBranchVOList = resultBranchVOList;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
