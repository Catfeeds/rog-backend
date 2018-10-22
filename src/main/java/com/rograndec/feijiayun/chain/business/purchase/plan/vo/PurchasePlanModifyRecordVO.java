package com.rograndec.feijiayun.chain.business.purchase.plan.vo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class PurchasePlanModifyRecordVO {

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID")
	private Long id;

	/**
	 * 修改字段中文名称（修改项目）
	 */
	@ApiModelProperty(value = "修改字段中文名称（修改项目）")
	private String columnChName;

	/**
	 * 原内容
	 */
	@ApiModelProperty(value = "原内容")
	private String oldContent;

	/**
	 * 新内容
	 */
	@ApiModelProperty(value = "新内容")
	private String newContent;

	/**
	 * 修改人名称
	 */
	@ApiModelProperty(value = "修改人名称")
	private String modifierName;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;

	/**
	 * 备注(修改原因)
	 */
	@ApiModelProperty(value = "备注(修改原因)")
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColumnChName() {
		return columnChName;
	}

	public void setColumnChName(String columnChName) {
		this.columnChName = columnChName;
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

	public String getModifierName() {
		return modifierName;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "PurchasePlanModifyRecordVO [id=" + id + ", columnChName=" + columnChName + ", oldContent=" + oldContent + ", newContent="
				+ newContent + ", modifierName=" + modifierName + ", updateTime=" + updateTime + ", remark=" + remark + "]";
	}

}
