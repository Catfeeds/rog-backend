package com.rograndec.feijiayun.chain.business.retail.pos.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @ClassName: PosTeamVO
 * @Description:  零售管理-POS管理-班组
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:51:24
 */
@ApiModel(value = "PosTeamVO", description = "零售管理-POS管理-班组")
public class PosTeamVO implements Serializable {
	
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
     * 开班时间
     */
	@ApiModelProperty(required = false, value = "开班时间")
	private String startTime;
	
	/**
     * 交班时间
     */
	@ApiModelProperty(required = false, value = "交班时间")
	private String endTime;
	
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
	 * 数据类型
	 */
	@ApiModelProperty(required = true, value = "数据类型（0-系统默认；1-用户自定义）")
	private Integer teamType;

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

	public String getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(String showStatus) {
		this.showStatus = showStatus;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getTeamType() {
		return teamType;
	}

	public void setTeamType(Integer teamType) {
		this.teamType = teamType;
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