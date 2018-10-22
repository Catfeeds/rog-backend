package com.rograndec.feijiayun.chain.business.distr.parent.vo.pick;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
@ApiModel(value = "DistrSendRequestVO", description = "配货单-捡货-待捡货搜索条件对象")
public class DistrSendRequestVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, value = "页码")
	private Integer pageNo;
	
	@ApiModelProperty(required = true, value = "每页显示的记录数")
	private Integer pageSize;
	
	@ApiModelProperty(required = false, value = "按某一列排序")
	private String order;
	
	@ApiModelProperty(required = false, value = "排序方式（升序：asc,降序：desc）")
	private String sort;
	
	@ApiModelProperty(required = false, value = "开始日期")
	private Date startDate;
	
	@ApiModelProperty(required = false, value = "结束日期")
	private Date endDate;
	
	@ApiModelProperty(required = false, value = "要货单位编码")
	private String requestUnitCode;
	
	@ApiModelProperty(required = false, value = "要货单位名称")
	private String requestUnitName;
	
	@ApiModelProperty(required = false, value = "配货单号")
	private String code;
	
	@ApiModelProperty(required = false, value = "配货人员")
	private String distrManName;
	
	@ApiModelProperty(required = false, value = "前端不传")
	private Long enterpriseId;
	
	@ApiModelProperty(required = false, value = "前端不传")
	private Integer start;
	
	@ApiModelProperty(required = false, value = "前端不传")
	private Integer status;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDistrManName() {
		return distrManName;
	}

	public void setDistrManName(String distrManName) {
		this.distrManName = distrManName;
	}
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
