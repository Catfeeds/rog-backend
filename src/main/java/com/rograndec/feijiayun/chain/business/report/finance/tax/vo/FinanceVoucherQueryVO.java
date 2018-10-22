package com.rograndec.feijiayun.chain.business.report.finance.tax.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class FinanceVoucherQueryVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2018年1月2日 下午6:48:37 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = false, value = "凭证号码")
	private String orderCode;
	
	@ApiModelProperty(required = false, value = "单据号码")
	private String baseOrderCode;
	
	@ApiModelProperty(required = false, value = "单据类型")
	private Integer baseOrderType;
	
	@ApiModelProperty(required = false, value = "操作人员")
	private String baseOperatorName;
	
	@ApiModelProperty(required = false, value = "科目编码")
	private String accountCode;
	
	@ApiModelProperty(required = false, value = "科目名称")
	private String accountName;
	
	@ApiModelProperty(required = false, value = "明细科目编码")
	private String subAccountCode;
	
	@ApiModelProperty(required = false, value = "明细科目名称")
	private String subAccountName;
	
	@ApiModelProperty(required = true, value = "页码")
	private Integer pageNo;
	
	@ApiModelProperty(required = true, value = "每页显示的记录数")
	private Integer pageSize;
	
	@ApiModelProperty(required = false, value = "起始时间")
	private Date startDate;

	@ApiModelProperty(required = false, value = "截止时间")
	private Date endDate;
	
	

	public FinanceVoucherQueryVO() {
	}
	
	public FinanceVoucherQueryVO(String orderCode, String baseOrderCode,
			Integer baseOrderType, String baseOperatorName, String accountCode,
			String accountName, String subAccountCode, String subAccountName,
			Date startDate, Date endDate) {
		this.orderCode = orderCode;
		this.baseOrderCode = baseOrderCode;
		this.baseOrderType = baseOrderType;
		this.baseOperatorName = baseOperatorName;
		this.accountCode = accountCode;
		this.accountName = accountName;
		this.subAccountCode = subAccountCode;
		this.subAccountName = subAccountName;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getBaseOrderCode() {
		return baseOrderCode;
	}

	public void setBaseOrderCode(String baseOrderCode) {
		this.baseOrderCode = baseOrderCode;
	}

	public Integer getBaseOrderType() {
		return baseOrderType;
	}

	public void setBaseOrderType(Integer baseOrderType) {
		this.baseOrderType = baseOrderType;
	}

	public String getBaseOperatorName() {
		return baseOperatorName;
	}

	public void setBaseOperatorName(String baseOperatorName) {
		this.baseOperatorName = baseOperatorName;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getSubAccountCode() {
		return subAccountCode;
	}

	public void setSubAccountCode(String subAccountCode) {
		this.subAccountCode = subAccountCode;
	}

	public String getSubAccountName() {
		return subAccountName;
	}

	public void setSubAccountName(String subAccountName) {
		this.subAccountName = subAccountName;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
