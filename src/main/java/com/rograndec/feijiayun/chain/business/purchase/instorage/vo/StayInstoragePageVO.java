package com.rograndec.feijiayun.chain.business.purchase.instorage.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "StayInstoragePageVO", description = "采购管理-采购入库-待入库分页对象")
public class StayInstoragePageVO implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
     * 验收单ID
     */
	@ApiModelProperty(required = true, value = "验收单ID")
    private Long id;
	
	/**
     * 验收日期
     */
	@ApiModelProperty(required = true, value = "验收日期")
    private String checkDate;
	
	/**
     * 验收单号
     */
	@ApiModelProperty(required = true, value = "验收单号")
    private String checkCode;
    
    /**
     * 供货单位编码
     */
	@ApiModelProperty(required = true, value = "供货单位编码")
    private String supplierCode;
	
	/**
     * 供货单位名称
     */
	@ApiModelProperty(required = true, value = "供货单位名称")
    private String supplierName;
    
    /**
     * 验收人员名称
     */
	@ApiModelProperty(required = true, value = "验收人员名称")
    private String checkerName;
	
	/**
     * 状态
     */
	@ApiModelProperty(required = true, value = "状态")
    private String statusName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "StayInstoragePageVO [id=" + id + ", checkDate=" + checkDate
				+ ", checkCode=" + checkCode + ", supplierCode=" + supplierCode
				+ ", supplierName=" + supplierName + ", checkerName="
				+ checkerName + ", statusName=" + statusName + "]";
	}
	
	
}
