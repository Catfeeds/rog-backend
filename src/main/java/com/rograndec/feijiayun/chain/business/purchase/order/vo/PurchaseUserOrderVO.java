package com.rograndec.feijiayun.chain.business.purchase.order.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class PurchaseUserOrderVO implements Serializable{
	@ApiModelProperty(value = "采购人员ID")
	private Long id;
	@ApiModelProperty(value = "采购人员名称")
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
