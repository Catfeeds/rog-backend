package com.rograndec.feijiayun.chain.business.report.retail.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaleFlowListDtlResultBranchTotalVO", description = "按日结标识、日期查询销售流水列表（分店）查询结果对象")
public class SaleFlowListDtlResultBranchTotalVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月16日 下午3:39:13 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "分摊抹零金额合计")
    private BigDecimal wholeDiscountAmount = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "实际金额合计")
	private BigDecimal realAmountTotal = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "不含税金额合计")
	private BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "税额合计")
	private BigDecimal taxAmountTotal = BigDecimal.ZERO;

	@ApiModelProperty(value = "列表对象")
	private List<SaleFlowListDtlResultBranchVO> resultBranchVOList;

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

	public List<SaleFlowListDtlResultBranchVO> getResultBranchVOList() {
		return resultBranchVOList;
	}

	public void setResultBranchVOList(
			List<SaleFlowListDtlResultBranchVO> resultBranchVOList) {
		this.resultBranchVOList = resultBranchVOList;
	}

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

	public BigDecimal getWholeDiscountAmount() {
		return wholeDiscountAmount;
	}

	public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
		this.wholeDiscountAmount = wholeDiscountAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
