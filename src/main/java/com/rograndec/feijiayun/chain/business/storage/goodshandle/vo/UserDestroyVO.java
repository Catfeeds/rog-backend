package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
/**
 * 商品列表
 * @author 孙帮祥
 * */
public class UserDestroyVO implements Serializable{
	@ApiModelProperty(value = "人员ID")
	private Long id;
	@ApiModelProperty(value = "人员名称")
	private String name;
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
}
