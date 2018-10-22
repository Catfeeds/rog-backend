package com.rograndec.feijiayun.chain.business.purchase.order.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class RecordVO implements Serializable{
	/**
	 * 修改时间
	 * */
	@ApiModelProperty(value = "修改时间")
	private Date updateDate;
	/**
	 * 修改人员
	 * */
	@ApiModelProperty(value = "修改人员")
	private String updateUser;
	/**
	 * 修改项目
	 * */
	@ApiModelProperty(value = "修改项目")
	private String updateProject;
	/**
	 * 原内容
	 * */
	@ApiModelProperty(value = "原内容")
	private String oldContent;
	/**
	 * 新内容
	 * */
	@ApiModelProperty(value = "新内容")
	private String newContent;
	
	/**
	 * 新内容
	 * */
	@ApiModelProperty(value = "修改原因")
	private String reason;
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateProject() {
		return updateProject;
	}
	public void setUpdateProject(String updateProject) {
		this.updateProject = updateProject;
	}
	public String getOldContent() {
		return oldContent;
	}
	public void setOldContent(String oldContent) {
		this.oldContent = oldContent;
	}
	public String getNewContent() {
		return newContent;
	}
	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
