package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class EnterpriseReqPlanVO implements Serializable{

	@ApiModelProperty(value = "配货单位ID")
	private Long id;
	
	@ApiModelProperty(value = "配货单位编码")
	private String code;
	
	@ApiModelProperty(value = "配货单位名称")
	private String name;
	
	@ApiModelProperty(value = "药店类型（0-总部；1-自营店；2-加盟店）")
	private Integer isParent;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

}
