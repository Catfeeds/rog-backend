package com.rograndec.feijiayun.chain.business.report.finance.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "AdjustPriceTotalVO", description = "财务管理-应付/应收调价分析合计显示")
public class AdjustPriceTotalVO implements Serializable{

	private static final long serialVersionUID = 1L;

	 /**
     * 金额合计
     */
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

    /**
     * 金额差额合计
     */
    @ApiModelProperty(value = "金额差额合计")
    private BigDecimal diffAmountTotal;

    /**
     * 不含税金额差额合计
     */
    @ApiModelProperty(value = "不含税金额差额合计")
    private BigDecimal diffNotaxAmountTotal;

    /**
     * 税额差额合计
     */
    @ApiModelProperty(value = "税额差额合计")
    private BigDecimal diffTaxAmountTotal;

    /**
     * 列表数据
     */
    @ApiModelProperty(value = "列表数据")
	private List<AdjustPriceVO> list;

	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}

	public BigDecimal getNotaxAmountTotal() {
		return notaxAmountTotal;
	}

	public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
		this.notaxAmountTotal = notaxAmountTotal;
	}

	public BigDecimal getTaxAmountTotal() {
		return taxAmountTotal;
	}

	public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
		this.taxAmountTotal = taxAmountTotal;
	}

	public BigDecimal getDiffAmountTotal() {
		return diffAmountTotal;
	}

	public void setDiffAmountTotal(BigDecimal diffAmountTotal) {
		this.diffAmountTotal = diffAmountTotal;
	}

	public BigDecimal getDiffNotaxAmountTotal() {
		return diffNotaxAmountTotal;
	}

	public void setDiffNotaxAmountTotal(BigDecimal diffNotaxAmountTotal) {
		this.diffNotaxAmountTotal = diffNotaxAmountTotal;
	}

	public BigDecimal getDiffTaxAmountTotal() {
		return diffTaxAmountTotal;
	}

	public void setDiffTaxAmountTotal(BigDecimal diffTaxAmountTotal) {
		this.diffTaxAmountTotal = diffTaxAmountTotal;
	}

	public List<AdjustPriceVO> getList() {
		return list;
	}

	public void setList(List<AdjustPriceVO> list) {
		this.list = list;
	}
	
}
