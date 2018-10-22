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
public class PurchasePlanVO implements Serializable {

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID")
	private Long id;

	/**
	 * 企业（总部）ID
	 */
	@ApiModelProperty(value = "企业（总部）ID")
	private Long enterpriseId;

	/**
	 * 计划单号
	 */
	@ApiModelProperty(value = "计划单号")
	private String code;

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
	 * 单据状态
	 */
	@ApiModelProperty(value = "单据状态: 41-待订购,51-已订购 ,34-已取消 ,21-待审核,23-审核拒绝")
	private Integer status;

	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注(必传)")
	private String remark;

	/**
	 * 大写
	 */
	@ApiModelProperty(value = "大写(打印)")
	private String amountCapital;

	/**
	 * 企业（总部）
	 */
	@ApiModelProperty(value = "企业名称")
	private String enterpriseName;

	/**
	 * 修改原因
	 */
	@ApiModelProperty(value = "修改原因(新增不需要)")
	private String updateDetail;


	@ApiModelProperty(value = "草稿缓存id,修改草稿时需要传递")
	private String redisKeyValue;

	/**
	 * 采购计划明细
	 */
	@ApiModelProperty(value = "采购计划明细")
	private List<PurchasePlanDetailVO> planDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<PurchasePlanDetailVO> getPlanDetails() {
		return planDetails;
	}

	public void setPlanDetails(List<PurchasePlanDetailVO> planDetails) {
		this.planDetails = planDetails;
	}

	public String getUpdateDetail() {
		return updateDetail;
	}

	public void setUpdateDetail(String updateDetail) {
		this.updateDetail = updateDetail;
	}

	public String getRedisKeyValue() {
		return redisKeyValue;
	}

	public void setRedisKeyValue(String redisKeyValue) {
		this.redisKeyValue = redisKeyValue;
	}


	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public String getAmountCapital() {
		return amountCapital;
	}

	public void setAmountCapital(String amountCapital) {
		this.amountCapital = amountCapital;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	@Override
	public String toString() {
		return "PurchasePlanVO[" +
				"id=" + id +
				", enterpriseId=" + enterpriseId +
				", code='" + code + '\'' +
				", planDate=" + planDate +
				", pannerId=" + pannerId +
				", pannerCode='" + pannerCode + '\'' +
				", pannerName='" + pannerName + '\'' +
				", quantityTotal=" + quantityTotal +
				", amountTotal=" + amountTotal +
				", notaxAmountTotal=" + notaxAmountTotal +
				", taxAmountTotal=" + taxAmountTotal +
				", status=" + status +
				", remark='" + remark + '\'' +
				", amountCapital='" + amountCapital + '\'' +
				", enterpriseName='" + enterpriseName + '\'' +
				", updateDetail='" + updateDetail + '\'' +
				", redisKeyValue='" + redisKeyValue + '\'' +
				", planDetails=" + planDetails +
				']';
	}
}
