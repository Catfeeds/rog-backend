package com.rograndec.feijiayun.chain.business.purchase.order.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class SaleManOrderVO implements Serializable{

	@ApiModelProperty(value = "主键ID")
	private Long id;

	/**
	 * 编码
	 */
	@ApiModelProperty(value = "销售人员编码")
	private String code;

	/**
	 * 名称
	 */
	@ApiModelProperty(value = "销售人员名称")
	private String name;
	
	/**
	 * 供货单位联系电话
	 * */
	@ApiModelProperty(value = "销售人员联系电话")
	private String telePhone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

}
