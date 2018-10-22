package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class CodeVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "码")
	private int code;
	@ApiModelProperty(value = "名称")
	private String name;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
