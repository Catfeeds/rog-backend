package com.rograndec.feijiayun.chain.business.report.quality.distr.vo;

import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsRequestParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "RequestDistrReqPlan", description = "配货单据请求参数")
public class RequestDistrOut extends BaseGoodsRequestParam{

	private Long enterpriseId;

	@ApiModelProperty(value = "每页数据(可不传,查询所有数据)")
	private Integer pageSize;

	@ApiModelProperty(value = "页码(可不传,查询所有数据)")
	private Integer pageNo;

	@ApiModelProperty(required = false, value = "按日期排序0-升序 1-降序")
	private Integer sortDate;

	@ApiModelProperty(required = false, value = "按单号排序0-升序 1-降序")
	private Integer sortCode;

	private String sort;

	@ApiModelProperty(value = "出库日期开始")
	private String startDate;

	@ApiModelProperty(value = "出库日期结束")
	private String endDate;

	@ApiModelProperty(value = "要货单位编码")
	private String requestUnitCode;

	@ApiModelProperty(value = "要货单位名称")
	private String requestUnitName;

	@ApiModelProperty(value = "出库单号")
	private String code;

	@ApiModelProperty(value = "出库人员名称")
	private String outManName;

	@ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
	private String distrType;

	@ApiModelProperty(value = "状态（33-待复核；34-已完成）")
	private String status;

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getSortDate() {
		return sortDate;
	}

	public void setSortDate(Integer sortDate) {
		this.sortDate = sortDate;
	}

	public Integer getSortCode() {
		return sortCode;
	}

	public void setSortCode(Integer sortCode) {
		this.sortCode = sortCode;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRequestUnitCode() {
		return requestUnitCode;
	}

	public void setRequestUnitCode(String requestUnitCode) {
		this.requestUnitCode = requestUnitCode;
	}

	public String getRequestUnitName() {
		return requestUnitName;
	}

	public void setRequestUnitName(String requestUnitName) {
		this.requestUnitName = requestUnitName;
	}

	public String getDistrType() {
		return distrType;
	}

	public void setDistrType(String distrType) {
		this.distrType = distrType;
	}

	public String getStatus() {
		return status;
	}

	public String getOutManName() {
		return outManName;
	}

	public void setOutManName(String outManName) {
		this.outManName = outManName;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
