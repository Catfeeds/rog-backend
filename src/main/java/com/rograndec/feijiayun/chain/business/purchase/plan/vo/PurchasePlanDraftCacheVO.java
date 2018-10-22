package com.rograndec.feijiayun.chain.business.purchase.plan.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 采购计划VO
 * 
 * @author dongyang.du 2017-09-13 16:52:47
 *
 */
public class PurchasePlanDraftCacheVO implements Serializable {

	/**
	 * 计划日期
	 */
	@ApiModelProperty(value = "计划日期(必传)")
	private Date planDate;
	/**
	 * 计划人员ID
	 */
	@ApiModelProperty(value = "计划人员ID(必传)")
	private Long pannerId;

	/**
	 * 计划人员编码
	 */
	@ApiModelProperty(value = "计划人员编码(必传)")
	private String pannerCode;

	/**
	 * 计划人员名称
	 */
	@ApiModelProperty(value = "计划人员名称(必传)")
	private String pannerName;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注(必传)")
	private String remark;

	/**
	 * 数量合计
	 */
	@ApiModelProperty(value = "数量 （合计）")
	private BigDecimal quantityTotal;

	/**
	 * 金额（合计）
	 */
	@ApiModelProperty(value = "金额（合计）")
	private BigDecimal amountTotal;

	/**
	 * 无税金额（合计）
	 */
	@ApiModelProperty(value = "无税金额（合计）")
	private BigDecimal notaxAmountTotal;

	/**
	 * 税额（合计）
	 */
	@ApiModelProperty(value = "税额（合计）")
	private BigDecimal taxAmountTotal;


	/**
	 * 采购计划明细
	 */
	@ApiModelProperty(value = "采购计划明细")
	private List<PurchasePlanDetailDraftCacheVO> planDetails;

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public Long getPannerId() {
		return pannerId;
	}

	public void setPannerId(Long pannerId) {
		this.pannerId = pannerId;
	}

	public String getPannerCode() {
		return pannerCode;
	}

	public void setPannerCode(String pannerCode) {
		this.pannerCode = pannerCode;
	}

	public String getPannerName() {
		return pannerName;
	}

	public void setPannerName(String pannerName) {
		this.pannerName = pannerName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public List<PurchasePlanDetailDraftCacheVO> getPlanDetails() {
		return planDetails;
	}

	public void setPlanDetails(List<PurchasePlanDetailDraftCacheVO> planDetails) {
		this.planDetails = planDetails;
	}

}
