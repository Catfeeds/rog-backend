package com.rograndec.feijiayun.chain.business.report.retail.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaleFlowListDateResultBranchTotalVO", description = "按日结标识、日期查询销售流水列表（分店）查询结果对象")
public class SaleFlowListDateResultBranchTotalVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月16日 下午3:39:13 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "实际金额合计")
	private BigDecimal realAmountTotal = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "不含税金额合计")
	private BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "税额合计")
	private BigDecimal taxAmountTotal = BigDecimal.ZERO;

	@ApiModelProperty(value = "列表对象")
	private List<SaleFlowListDateResultBranchVO> resultBranchVOList;

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

	public List<SaleFlowListDateResultBranchVO> getResultBranchVOList() {
		return resultBranchVOList;
	}

	public void setResultBranchVOList(
			List<SaleFlowListDateResultBranchVO> resultBranchVOList) {
		this.resultBranchVOList = resultBranchVOList;
	}

}
