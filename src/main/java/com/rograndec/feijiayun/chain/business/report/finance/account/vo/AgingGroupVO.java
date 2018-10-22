package com.rograndec.feijiayun.chain.business.report.finance.account.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "AgingGroupVO", description = "财务管理-应付/应收账款-应付/应收账款明细账分组数据显示")
public class AgingGroupVO {
	
	@ApiModelProperty(value = "单位编码")
	private String supplierCode;
	
	@ApiModelProperty(value = "单位名称")
	private String supplierName;
	
	@ApiModelProperty(value = "数据")
	private List<AgingVO> list;

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

	public List<AgingVO> getList() {
		return list;
	}

	public void setList(List<AgingVO> list) {
		this.list = list;
	}
	

}
