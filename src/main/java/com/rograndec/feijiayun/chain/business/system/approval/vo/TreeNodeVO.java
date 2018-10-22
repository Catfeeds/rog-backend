package com.rograndec.feijiayun.chain.business.system.approval.vo;

import java.util.List;

import com.google.common.collect.Lists;

import io.swagger.annotations.ApiModelProperty;

public class TreeNodeVO {
	
	@ApiModelProperty(value = "节点名称", required = true)
	private String name;
	@ApiModelProperty(value = "节点值", required = true)
	private String value;
	
	@ApiModelProperty(value = "子节点集合", required = true)
	private List<TreeNodeVO> childrens = Lists.newArrayList();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<TreeNodeVO> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<TreeNodeVO> childrens) {
		this.childrens = childrens;
	}
	
}
