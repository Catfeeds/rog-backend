package com.rograndec.feijiayun.chain.business.report.retail.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaleFlowListDoubleClickQuerySumVO", description = "按日结标识、日期查询销售流水列表（分店）查询参数对象")
public class SaleFlowListDoubleClickQuerySumVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月16日 下午3:39:13 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, value = "页码")
	private Integer pageNo;
	
	@ApiModelProperty(required = true, value = "每页显示的记录数")
	private Integer pageSize;
	
	@ApiModelProperty(required = false, value = "按某一列排序")
	private String order;
	
	@ApiModelProperty(required = false, value = "排序方式（升序：asc,降序：desc）")
	private String sort;
	
	@ApiModelProperty(required = true, value = "门店ID")
	private Long enterpriseId;
	
	@ApiModelProperty(required = true, value = "销售日期")
	private String saleDate;
	
	@ApiModelProperty(required = true, value = "日结标识（0-未日结；1-已日结）")
	private Integer dailySettlementFlag;
	

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

	public Integer getDailySettlementFlag() {
		return dailySettlementFlag;
	}

	public void setDailySettlementFlag(Integer dailySettlementFlag) {
		this.dailySettlementFlag = dailySettlementFlag;
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

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
