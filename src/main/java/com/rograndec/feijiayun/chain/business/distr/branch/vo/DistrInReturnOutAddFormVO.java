package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author dongdong.zhang
 * 2017-10-12
 */
@ApiModel
public class DistrInReturnOutAddFormVO implements Serializable {

	/**
	 * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
	 */
	@ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）(新增时必填，调用新增时不用，修改时不用)")
	private Integer distrType;

	/**
	 * 配货单位id
	 */
	@ApiModelProperty(value = "配货单位id")
	private Long distrUnitId;

	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;

	/**
	 * 退货人员ID
	 */
	@ApiModelProperty(value = "退货人员ID")
	private Long returnManId;

	/**
	 * 出库人员ID
	 */
	@ApiModelProperty(value = "出库人员ID")
	private Long outManId;

	/**
	 * 配进退出出库日期
	 */
	@ApiModelProperty(value = "配进退出出库日期")
	private Date outDate;

	/**
	 * 退货日期
	 */
	@ApiModelProperty(value = "退货日期")
	private Date returnDate;


	/**
	 * 整单折扣（%）
	 */
	@ApiModelProperty(value = "整单折扣（%）查询和初始化列表会带过去,用户可以修改",required = true)
	private BigDecimal wholeDiscount;

	/**
	 * 整单优惠金额
	 */
	@ApiModelProperty(value = "整单优惠金额,查询和初始化列表会带过去,用户可以修改",required = true)
	private BigDecimal wholeDiscountAmount;

	/**
	 * 配进入库单或者要货计划id
	 */
	@ApiModelProperty(value = " 配进入库单id,若不是调用的配进入库的单据则不用传该参数")
	private Long baseOrderId;


	@ApiModelProperty(value = "新增配进退出明细行",required = true)
	private List<DistrInReturnOutAddFormDetailVO> distrInReturnOutAddFormDetailVOS;

	public Integer getDistrType() {
		return distrType;
	}

	public void setDistrType(Integer distrType) {
		this.distrType = distrType;
	}

	public Long getDistrUnitId() {
		return distrUnitId;
	}

	public void setDistrUnitId(Long distrUnitId) {
		this.distrUnitId = distrUnitId;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getReturnManId() {
		return returnManId;
	}

	public void setReturnManId(Long returnManId) {
		this.returnManId = returnManId;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public BigDecimal getWholeDiscount() {
		return wholeDiscount;
	}

	public void setWholeDiscount(BigDecimal wholeDiscount) {
		this.wholeDiscount = wholeDiscount;
	}

	public BigDecimal getWholeDiscountAmount() {
		return wholeDiscountAmount;
	}

	public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
		this.wholeDiscountAmount = wholeDiscountAmount;
	}

	public List<DistrInReturnOutAddFormDetailVO> getDistrInReturnOutAddFormDetailVOS() {
		return distrInReturnOutAddFormDetailVOS;
	}

	public void setDistrInReturnOutAddFormDetailVOS(List<DistrInReturnOutAddFormDetailVO> distrInReturnOutAddFormDetailVOS) {
		this.distrInReturnOutAddFormDetailVOS = distrInReturnOutAddFormDetailVOS;
	}

	public Long getOutManId() {
		return outManId;
	}

	public void setOutManId(Long outManId) {
		this.outManId = outManId;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public Long getBaseOrderId() {
		return baseOrderId;
	}

	public void setBaseOrderId(Long baseOrderId) {
		this.baseOrderId = baseOrderId;
	}
}