package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo;

import java.io.Serializable;
import java.util.Date;

import com.rograndec.feijiayun.chain.common.valid.annotation.ValidNotNull;

import io.swagger.annotations.ApiModelProperty;
public class InvoiceListrequestVo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "页码")
    @ValidNotNull()
    private Integer pageNo;
    @ApiModelProperty(value = "每页显示的记录数")
    @ValidNotNull()
    private Integer pageSize;
    @ApiModelProperty(value = "日期范围-开始时间")
    private Date startTime;
    @ApiModelProperty(value = "日期范围-结束时间")
    private Date endTime;
    @ApiModelProperty(value = "购货单位编码")
    private String purchaseUnitCode;
    @ApiModelProperty(value = "购货单位名称")
    private String purchaseUnitName;
    @ApiModelProperty(value = "过账单号")
    private String code;
    @ApiModelProperty(value = "过账人员")
    private String postManName;
    @ApiModelProperty(value = "单据状态")
    private Integer status;
    @ApiModelProperty(value = "对账状态")
    private Integer accountStatus;
    @ApiModelProperty(value = "排序参数,就是列名（目前支持code，createTime）")
    private String orderName;
    @ApiModelProperty(value = "排序方式,ASC或者DESC")
    private String orderType;
    
    private Long enterpriseId;
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getPurchaseUnitCode() {
		return purchaseUnitCode;
	}
	public void setPurchaseUnitCode(String purchaseUnitCode) {
		this.purchaseUnitCode = purchaseUnitCode;
	}
	public String getPurchaseUnitName() {
		return purchaseUnitName;
	}
	public void setPurchaseUnitName(String purchaseUnitName) {
		this.purchaseUnitName = purchaseUnitName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPostManName() {
		return postManName;
	}
	public void setPostManName(String postManName) {
		this.postManName = postManName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

}
