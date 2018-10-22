package com.rograndec.feijiayun.chain.business.report.quality.distr.vo;

import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsRequestParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "RequestDistrReqPlan", description = "要货计划单据请求参数")
public class RequestDistrReqPlan extends BaseGoodsRequestParam{

	private Long enterpriseId;

	private Long parentId;

	@ApiModelProperty(value = "每页数据(可不传,查询所有数据)")
	private Integer pageSize;

	@ApiModelProperty(value = "页码(可不传,查询所有数据)")
	private Integer pageNo;

	@ApiModelProperty(required = false, value = "按日期排序0-升序 1-降序")
	private Integer sortDate;

	@ApiModelProperty(required = false, value = "按单号排序0-升序 1-降序")
	private Integer sortCode;

	private String sort;

	@ApiModelProperty(value = "要货日期开始")
	private String startDate;

	@ApiModelProperty(value = "要货日期结束")
	private String endDate;

	@ApiModelProperty(value = "配货单位编码")
	private String distrUnitCode;

	@ApiModelProperty(value = "配货单位名称")
	private String distrUnitName;

	@ApiModelProperty(value = "要货计划单号")
	private String code;

	@ApiModelProperty(value = "要货人员名称")
	private String plannerName;

	@ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂）")
	private String requestType;

	@ApiModelProperty(value = "状态（21-待审核；22-审核通过；23-审核驳回；31-待配货；33-已配货；34-已取消）")
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

	public String getDistrUnitCode() {
		return distrUnitCode;
	}

	public void setDistrUnitCode(String distrUnitCode) {
		this.distrUnitCode = distrUnitCode;
	}

	public String getDistrUnitName() {
		return distrUnitName;
	}

	public void setDistrUnitName(String distrUnitName) {
		this.distrUnitName = distrUnitName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPlannerName() {
		return plannerName;
	}

	public void setPlannerName(String plannerName) {
		this.plannerName = plannerName;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
