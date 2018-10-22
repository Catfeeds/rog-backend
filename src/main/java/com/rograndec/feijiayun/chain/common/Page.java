package com.rograndec.feijiayun.chain.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.servlet.http.HttpServletRequest;

@ApiModel(value = "page", description = "page分页对象")
public class Page <T>{

	//@ApiModelProperty(value = "起始条")
	private Integer start = 0;	 //起始条

	@ApiModelProperty(value = "页码，默认是第一页")
	private Integer pageNo = 1;		// 页码，默认是第一页

	@ApiModelProperty(value = "每页显示的记录数，默认10")
	private Integer pageSize = 10;// 每页显示的记录数，默认10

	@ApiModelProperty(value = "总记录数")
	private Integer totalRecord;// 总记录数

	@ApiModelProperty(value = "总页数")
	private Integer totalPage;// 总页数

	@ApiModelProperty(value = "数据")
	private T result;


	public Page(){
	}

	public Page(Integer pageNo,Integer pageSize){

		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		start = (pageNo - 1) * pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public Integer getPageNo(HttpServletRequest request) {
		int pageNo = 1;
		if (request.getAttribute("pageNo") != null) {
			pageNo = (Integer) request.getAttribute("pageNo");
		} else if (request.getParameter("pageNo") != null){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		this.setPageNo(pageNo);
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

	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
		int totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize
				: totalRecord / pageSize + 1;
		this.setTotalPage(totalPage);
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public int getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

}
