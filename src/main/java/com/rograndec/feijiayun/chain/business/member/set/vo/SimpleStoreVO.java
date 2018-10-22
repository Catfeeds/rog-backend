package com.rograndec.feijiayun.chain.business.member.set.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SimpleStoreVO", description = "企业简单信息对象")
public class SimpleStoreVO {

	/**
	 * 主键ID
	 */
	@ApiModelProperty(required = true, value = "主键ID")
	private Long id;


	/**
	 * 编码
	 */
	@ApiModelProperty(required = true, value = "编码")
	private String code;

	/**
	 * 企业名称
	 */
	@ApiModelProperty(required = true, value = "名称")
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

	@Override
	public String toString() {
		return "SimpleStoreVO [id=" + id + ", code=" + code + ", name=" + name + "]";
	}

}
