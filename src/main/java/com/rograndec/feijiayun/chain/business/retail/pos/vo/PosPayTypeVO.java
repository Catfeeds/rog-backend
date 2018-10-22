package com.rograndec.feijiayun.chain.business.retail.pos.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @ClassName: PosPayTypeVO
 * @Description:  零售管理-POS管理-支付方式
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:19:11
 */
@ApiModel(value = "PosPayTypeVO", description = "零售管理-POS管理-支付方式")
public class PosPayTypeVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
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
     * 名称
     */
	@ApiModelProperty(required = true, value = "名称")
	private String name;
	
	@ApiModelProperty(value = "快捷键（0-11:F1～F12）")
	private Integer shortcutKey;
	
	/**
     * 状态（0-禁用；1-启用）
     */
	@ApiModelProperty(required = true, value = "状态（0-禁用；1-启用）")
	private Integer status;
	
	/**
     * 备注
     */
	@ApiModelProperty(required = false, value = "备注")
	private String remark;
	
	/**
     * 状态（0-禁用；1-启用）
     */
	@ApiModelProperty(required = true, value = "状态（0-禁用；1-启用）")
	private String showStatus;
	
	@ApiModelProperty(value = "快捷键（F1～F12）")
	private String showShortcutKey;
	
	/**
	 * 数据类型
	 */
	@ApiModelProperty(required = true, value = "数据类型（0-系统默认；1-用户自定义）")
	private Integer payType;

	@ApiModelProperty(value = "删除标志 ture可删除,false不可删")
	private Boolean deleteFlag = true;

	@ApiModelProperty(value = "修改标记 ture可修改,false不可修改")
	private Boolean updateFlag = true;

	/**
	 * 主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 主键ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 编码
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 名称
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 状态（0-禁用；1-启用）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 状态（0-禁用；1-启用）
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 备注
	 */
	public String getRemark() {
		return remark;
	}

	public Integer getShortcutKey() {
		return shortcutKey;
	}

	public void setShortcutKey(Integer shortcutKey) {
		this.shortcutKey = shortcutKey;
	}

	public String getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	public String getShowShortcutKey() {
		return showShortcutKey;
	}

	public void setShowShortcutKey(String showShortcutKey) {
		this.showShortcutKey = showShortcutKey;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	 public Boolean getDeleteFlag() {
		 return deleteFlag;
	 }

	 public void setDeleteFlag(Boolean deleteFlag) {
		 this.deleteFlag = deleteFlag;
	 }

	 public Boolean getUpdateFlag() {
		 return updateFlag;
	 }

	 public void setUpdateFlag(Boolean updateFlag) {
		 this.updateFlag = updateFlag;
	 }
 }