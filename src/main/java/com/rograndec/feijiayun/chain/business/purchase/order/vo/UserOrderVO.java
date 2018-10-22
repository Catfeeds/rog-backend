package com.rograndec.feijiayun.chain.business.purchase.order.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class UserOrderVO implements Serializable{
	@ApiModelProperty(value = "收货人员ID")
	private Long id;
	@ApiModelProperty(value = "收货人员名称")
	private String name;
	@ApiModelProperty(value = "收货人员电话")
    private String telephone;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
}
