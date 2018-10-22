package com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ExcessSaleQueryVO implements Serializable{
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
	
	@ApiModelProperty(required = false, value = "搜索框数据")
	private String key;

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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
