package com.rograndec.feijiayun.chain.common.vo;

import javax.validation.constraints.NotNull;

import com.rograndec.feijiayun.chain.utils.string.StringUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: PageVO   
 * @Description: 分页参数VO
 * @author yuting.li
 * @version 1.0 
 * @date 2017年9月20日 下午5:50:17
 */
@ApiModel(value = "PageVO", description = "分页参数对象")
public class PageVO {
	
	@ApiModelProperty(value = "起始条",hidden=true)
	private Integer start = 0;	 //起始条
	
	@NotNull(message="当前页不能为空")
	@ApiModelProperty(required = true, value = "当前页,默认是第一页")
	private Integer pageNo = 1;		// 页码，默认是第一页;
	
	@NotNull(message="每页显示数据不能为空")
	@ApiModelProperty(required = true, value = "每页显示数据,默认10")
	private Integer pageSize = 10;// 每页显示的记录数，默认10
	
	@ApiModelProperty(required = false, value = "排序字段")
	private String order;
	
	@ApiModelProperty(required = false, value = "升序（asc）、降序（desc）")
	private String sort;
	
	@ApiModelProperty(value = "按企业id查询数据",hidden=true)
	private Long enterpriseId;
	
	@ApiModelProperty(value = "按总部id查询数据",hidden=true)
	private Long parentId = 0L;
	
	public PageVO(){
	}

	public PageVO(Integer pageNo,Integer pageSize){
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		start = (pageNo - 1) * pageSize;
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

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = StringUtil.trimStr(order);
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = StringUtil.trimStr(sort);
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	
}
