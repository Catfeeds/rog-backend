package com.rograndec.feijiayun.chain.business.report.finance.tax.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class FinanceDetailQueryVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2018年1月2日 下午6:51:05 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = false, value = "税类型")
	private String taxType;
	
	@ApiModelProperty(required = false, value = "税率ID")
	private Long taxId;
	
	@ApiModelProperty(required = true, value = "页码")
	private Integer pageNo;
	
	@ApiModelProperty(required = true, value = "每页显示的记录数")
	private Integer pageSize;
	
	@ApiModelProperty(required = false, value = "起始时间")
	private Date startDate;

	@ApiModelProperty(required = false, value = "截止时间")
	private Date endDate;

	public FinanceDetailQueryVO() {
		super();
	}
	
	public FinanceDetailQueryVO(String taxType, Long taxId, Date startDate, Date endDate) {
		super();
		this.taxType = taxType;
		this.taxId = taxId;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public FinanceDetailQueryVO(String taxType, Long taxId, Integer pageNo,
			Integer pageSize, Date startDate, Date endDate) {
		super();
		this.taxType = taxType;
		this.taxId = taxId;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public Long getTaxId() {
		return taxId;
	}

	public void setTaxId(Long taxId) {
		this.taxId = taxId;
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
