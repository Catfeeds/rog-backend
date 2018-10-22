package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
 
/**
 * 
 * 库房温湿度记录
 * @author kexinhao
 * 2017-09-28
 */
public class RequestTemperatureHumidityReportVO implements Serializable {
    @ApiModelProperty(value = "页码") private int pageNo;
    @ApiModelProperty(value = "每页显示的记录数") private int pageSize;
    @ApiModelProperty(value = "开始日期")
    private Date startDate;
    @ApiModelProperty(value = "结束日期")
    private Date endDate;
	@ApiModelProperty(value = "组织机构类型|0-总部；1-自营店；2-加盟店") private String chainType;
	@ApiModelProperty(value = "组织机构编码") private String enterpriseCode;
	@ApiModelProperty(value = "组织结构名称") private String enterpriseName;
	@ApiModelProperty(value = "排序字段") private String sortField;                                        					
	@ApiModelProperty(value = "排序方式（升序：asc;降序：desc）") private String sort;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getChainType() {
		return chainType;
	}
	public void setChainType(String chainType) {
		this.chainType = chainType;
	}
	public String getEnterpriseCode() {
		return enterpriseCode;
	}
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
	
    
}   