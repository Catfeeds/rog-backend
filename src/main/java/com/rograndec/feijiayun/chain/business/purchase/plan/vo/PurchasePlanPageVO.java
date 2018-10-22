package com.rograndec.feijiayun.chain.business.purchase.plan.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

public class PurchasePlanPageVO {

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID")
	private Long id;

	/**
	 * 计划单号
	 */
	@ApiModelProperty(value = "计划单号")
	private String code;

	/**
	 * 计划日期
	 */
	@ApiModelProperty(value = "计划日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date planDate;

	/**
	 * 计划人员名称
	 */
	@ApiModelProperty(value = "计划人员名称")
	private String pannerName;

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
	 * 单据状态
	 */
	@ApiModelProperty(value = "单据状态: 41-待订购,51-已订购 ,34-已取消 ,21-待审核,23-审核拒绝")
	private Integer status;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public String getPannerName() {
		return pannerName;
	}

	public void setPannerName(String pannerName) {
		this.pannerName = pannerName;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PurchasePlanPageVO [id=" + id + ", code=" + code + ", planDate=" + planDate + ", pannerName=" + pannerName
				+ ", amountTotal=" + amountTotal + ", notaxAmountTotal=" + notaxAmountTotal + ", taxAmountTotal=" + taxAmountTotal
				+ ", status=" + status + "]";
	}

}
