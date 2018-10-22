package com.rograndec.feijiayun.chain.business.retail.pos.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @ClassName: PosBankVO
 * @Description:  零售管理-POS管理-开户银行
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:34:42
 */
@ApiModel(value = "PosBankVO", description = "零售管理-POS管理-开户银行")
public class PosBankVO implements Serializable {
	
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
	
	/**
     * 账号
     */
	@ApiModelProperty(required = false, value = "账号")
	private String account;
	
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
	
	//EnableStatus
	@ApiModelProperty(required = true, value = "状态（0-禁用；1-启用）")
	private String showStatus;
	
	/**
	 * 数据类型
	 */
	@ApiModelProperty(required = true, value = "数据类型（0-系统默认；1-用户自定义）")
	private Integer bankType;


	 /**
	  * 是否可以修改
	  */
	 private boolean updateFlag = true;

	 /**
	  * 是否可以删除
	  */
	 private boolean deleteFlag = true;

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
	 * 账号
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	
	/**
	 * 账号
	 */
	public String getAccount() {
		return account;
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

	public String getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	public Integer getBankType() {
		return bankType;
	}

	public void setBankType(Integer bankType) {
		this.bankType = bankType;
	}

	 public boolean isUpdateFlag() {
		 return updateFlag;
	 }

	 public void setUpdateFlag(boolean updateFlag) {
		 this.updateFlag = updateFlag;
	 }

	 public boolean isDeleteFlag() {
		 return deleteFlag;
	 }

	 public void setDeleteFlag(boolean deleteFlag) {
		 this.deleteFlag = deleteFlag;
	 }
 }