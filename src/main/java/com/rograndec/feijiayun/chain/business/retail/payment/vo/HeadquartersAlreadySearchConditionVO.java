package com.rograndec.feijiayun.chain.business.retail.payment.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "HeadquartersAlreadySearchConditionVO", description = "零售管理-零售缴款-总部已缴款搜索条件对象")
public class HeadquartersAlreadySearchConditionVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, value = "页码")
	private Integer pageNo;
	
	@ApiModelProperty(required = true, value = "每页显示的记录数")
	private Integer pageSize;
	
	@ApiModelProperty(required = false, value = "开始日期")
	private Date startDate;
	
	@ApiModelProperty(required = false, value = "结束日期")
	private Date endDate;
	
	@ApiModelProperty(required = false, value = "门店类型1-自营店，2-加盟店")
	private Integer chainType;
	
	@ApiModelProperty(required = false, value = "门店编码")
	private String storeCode;
	
	@ApiModelProperty(required = false, value = "门店名称")
	private String storeName;
	
	@ApiModelProperty(required = false, value = "单号")
	private String salePaymentCode;
	
	@ApiModelProperty(required = false, value = "收款人员")
	private String createrName;
	
	@ApiModelProperty(required = false, value = "缴款人员")
	private String paymentManName;
	
	@ApiModelProperty(required = false, value = "按某一列排序")
	private String order;
	
	@ApiModelProperty(required = false, value = "排序方式（升序：asc,降序：desc）")
	private String sort;
	
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

	public String getSalePaymentCode() {
		return salePaymentCode;
	}

	public void setSalePaymentCode(String salePaymentCode) {
		this.salePaymentCode = salePaymentCode;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getPaymentManName() {
		return paymentManName;
	}

	public void setPaymentManName(String paymentManName) {
		this.paymentManName = paymentManName;
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

	public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	
}
