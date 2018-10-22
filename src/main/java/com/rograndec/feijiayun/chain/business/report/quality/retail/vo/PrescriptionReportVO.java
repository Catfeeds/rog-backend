package com.rograndec.feijiayun.chain.business.report.quality.retail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel(value = "PrescriptionReportVO", description = "销售报表返回对象")
public class PrescriptionReportVO<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "数据集合")
	private List<T> dataList;

    /**
     * 总数量
     */
    @ApiModelProperty(value = "总数量合计(划价单)")
    private BigDecimal totalQuantity;
    
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量合计(专管登记)")
    private BigDecimal quantity;
    
    /**
     * 金额（整单优惠前金额）
     */
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amount;

	@ApiModelProperty(value = "实际金额合计")
	private BigDecimal realAmountTotal;

	@ApiModelProperty(value = "不含税金额合计")
	private BigDecimal notaxRealAmountTotal;

	@ApiModelProperty(value = "税额合计")
	private BigDecimal taxAmountTotal;

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
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

	public BigDecimal getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(BigDecimal totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	
	
}
