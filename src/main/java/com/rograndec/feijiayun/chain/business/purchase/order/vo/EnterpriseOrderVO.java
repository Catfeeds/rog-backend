package com.rograndec.feijiayun.chain.business.purchase.order.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class EnterpriseOrderVO implements Serializable{

	@ApiModelProperty(value = "收货单位ID")
	private Long id;
	
	/**
	 * 名称
	 */
	@ApiModelProperty(value = "收货单位名称")
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
