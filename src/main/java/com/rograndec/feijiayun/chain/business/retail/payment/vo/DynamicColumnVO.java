package com.rograndec.feijiayun.chain.business.retail.payment.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "DynamicColumn", description = "动态列实体类")
public class DynamicColumnVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, value = "列-key")
	private String key;
	
	private boolean sortable = false;
	
	@ApiModelProperty(required = true, value = "列-title")
	private String title;
	
	@ApiModelProperty(required = true, value = "列-title")
	private int width = 140;

	@ApiModelProperty(required = true, value = "样式")
	private String align = "center";

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}
}
