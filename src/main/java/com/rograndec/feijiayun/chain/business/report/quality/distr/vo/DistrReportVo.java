package com.rograndec.feijiayun.chain.business.report.quality.distr.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel(value = "DistrReportVo", description = "报表返回对象")
public class DistrReportVo<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "数据集合")
	private List<T> dataList;

	 /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量合计")
    private BigDecimal receiveQuantity;
    /**
     * 抽样数量
     */
    @ApiModelProperty(value = "抽检数量合计")
    private BigDecimal samplingQuantity;

    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量合计")
    private BigDecimal qualifiedQuantity;
    
    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量合计")
    private BigDecimal unqualifiedQuantity;
    
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量合计/配后退回数量合计")
    private BigDecimal quantity;
    
    /**
     * 配后退回数量
     */
 /*   @ApiModelProperty(value = "配后退回数量")
    private BigDecimal quantity;*/
    
    /**
     * 到货数量
     */
    @ApiModelProperty(value = "到货数量")
    private BigDecimal arrivalQuantity;

    /**
     * 收货数量
     */
  /*  @ApiModelProperty(value = "收货数量")
    private BigDecimal receiveQuantity;*/

    /**
     * 拒收数量
     */
    @ApiModelProperty(value = "拒收数量")
    private BigDecimal refuseQuantity;

	@ApiModelProperty(value = "金额合计")
	private BigDecimal amount;

	@ApiModelProperty(value = "实际金额合计")
	private BigDecimal realAmount;

	@ApiModelProperty(value = "不含税金额合计")
	private BigDecimal notaxRealAmount;

	@ApiModelProperty(value = "税额合计")
	private BigDecimal taxAmount;

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public BigDecimal getReceiveQuantity() {
		return receiveQuantity;
	}

	public void setReceiveQuantity(BigDecimal receiveQuantity) {
		this.receiveQuantity = receiveQuantity;
	}

	public BigDecimal getSamplingQuantity() {
		return samplingQuantity;
	}

	public void setSamplingQuantity(BigDecimal samplingQuantity) {
		this.samplingQuantity = samplingQuantity;
	}

	public BigDecimal getQualifiedQuantity() {
		return qualifiedQuantity;
	}

	public void setQualifiedQuantity(BigDecimal qualifiedQuantity) {
		this.qualifiedQuantity = qualifiedQuantity;
	}

	public BigDecimal getUnqualifiedQuantity() {
		return unqualifiedQuantity;
	}

	public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) {
		this.unqualifiedQuantity = unqualifiedQuantity;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public BigDecimal getNotaxRealAmount() {
		return notaxRealAmount;
	}

	public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
		this.notaxRealAmount = notaxRealAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getArrivalQuantity() {
		return arrivalQuantity;
	}

	public void setArrivalQuantity(BigDecimal arrivalQuantity) {
		this.arrivalQuantity = arrivalQuantity;
	}

	public BigDecimal getRefuseQuantity() {
		return refuseQuantity;
	}

	public void setRefuseQuantity(BigDecimal refuseQuantity) {
		this.refuseQuantity = refuseQuantity;
	}

}
