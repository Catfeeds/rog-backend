package com.rograndec.feijiayun.chain.business.retail.pos.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @ClassName: PosClerkVO
 * @Description:  零售管理-POS管理-营业人员
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:51:52
 */
@ApiModel(value = "PosClerkVO", description = "零售管理-POS管理-营业人员")
public class PosClerkVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID")
	private Long id;	
	
	/**
     * 营业人员ID
     */
	@ApiModelProperty(required = true, value = "营业人员ID")
	private Long clerkId;
	
	/**
     * 营业人员编码
     */
	@ApiModelProperty(required = true, value = "营业人员编码")
	private String clerkCode;
	
	/**
     * 营业人员名称
     */
	@ApiModelProperty(required = false, value = "营业人员名称")
	private String clerkName;
	
	/**
     * 柜组（货区）ID
     */
	@ApiModelProperty(required = true, value = "柜组（货区）ID")
	private Long cargoAreaId;
	
	/**
     * 柜组（货区）编码
     */
	@ApiModelProperty(required = true, value = "柜组（货区）编码")
	private String cargoAreaCode;
	
	/**
     * 柜组（货区）名称
     */
	@ApiModelProperty(required = true, value = "柜组（货区）名称")
	private String cargoAreaName;
	
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
	
	@ApiModelProperty(required = true, value = "状态（0-禁用；1-启用）")
	private String showStatus;

	 /**
	  * 是否可以修改
	  */
	 @ApiModelProperty(required = true, value = "是否可以修改")
	 private Boolean updateFlag = true;

	 /**
	  * 是否可以删除
	  */
	 @ApiModelProperty(required = true, value = "是否可以删除")
	 private Boolean deleteFlag = true;

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
	 * 营业人员ID
	 */
	public void setClerkId(Long clerkId) {
		this.clerkId = clerkId;
	}
	
	/**
	 * 营业人员ID
	 */
	public Long getClerkId() {
		return clerkId;
	}
	
	/**
	 * 营业人员编码
	 */
	public void setClerkCode(String clerkCode) {
		this.clerkCode = clerkCode;
	}
	
	/**
	 * 营业人员编码
	 */
	public String getClerkCode() {
		return clerkCode;
	}
	
	/**
	 * 营业人员名称
	 */
	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}
	
	/**
	 * 营业人员名称
	 */
	public String getClerkName() {
		return clerkName;
	}
	
	/**
	 * 柜组（货区）ID
	 */
	public void setCargoAreaId(Long cargoAreaId) {
		this.cargoAreaId = cargoAreaId;
	}
	
	/**
	 * 柜组（货区）ID
	 */
	public Long getCargoAreaId() {
		return cargoAreaId;
	}
	
	/**
	 * 柜组（货区）编码
	 */
	public void setCargoAreaCode(String cargoAreaCode) {
		this.cargoAreaCode = cargoAreaCode;
	}
	
	/**
	 * 柜组（货区）编码
	 */
	public String getCargoAreaCode() {
		return cargoAreaCode;
	}
	
	/**
	 * 柜组（货区）名称
	 */
	public void setCargoAreaName(String cargoAreaName) {
		this.cargoAreaName = cargoAreaName;
	}
	
	/**
	 * 柜组（货区）名称
	 */
	public String getCargoAreaName() {
		return cargoAreaName;
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

	 public Boolean getUpdateFlag() {
		 return updateFlag;
	 }

	 public void setUpdateFlag(Boolean updateFlag) {
		 this.updateFlag = updateFlag;
	 }

	 public Boolean getDeleteFlag() {
		 return deleteFlag;
	 }

	 public void setDeleteFlag(Boolean deleteFlag) {
		 this.deleteFlag = deleteFlag;
	 }
 }