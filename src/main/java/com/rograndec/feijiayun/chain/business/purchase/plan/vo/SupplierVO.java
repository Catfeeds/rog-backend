package com.rograndec.feijiayun.chain.business.purchase.plan.vo;

import io.swagger.annotations.ApiModelProperty;

public class SupplierVO {

	@ApiModelProperty(value = "主键ID")
	private Long id;

	/**
	 * 编码
	 */
	@ApiModelProperty(value = "编码")
	private String code;

	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称")
	private String name;

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

}
