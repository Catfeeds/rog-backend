package com.rograndec.feijiayun.chain.business.retail.pos.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @ClassName: PosTeamSaveOrupdateVO
 * @Description:  零售管理-POS管理-班组-Rest接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:51:24
 */
@ApiModel(value = "PosTeamSaveOrupdateVO", description = "零售管理-POS管理-班组")
public class PosTeamSaveOrupdateVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID,修改时需要传入,新增时不需要")
	private Long id;
	
	/**
     * 编码
     */
	@NotBlank(message="编码不能为空!")
	@ApiModelProperty(required = true, value = "编码")
	private String code;
	
	/**
     * 名称
     */
	@NotBlank(message="名称不能为空!")
	@ApiModelProperty(required = true, value = "名称")
	private String name;
	
	/**
     * 开班时间
     */
	@NotBlank(message="开班时间不能为空!")
	@ApiModelProperty(required = true, value = "开班时间,日期格式：HH:mm")
	private String startTime;
	
	/**
     * 交班时间
     */
	@NotBlank(message="交班时间不能为空!")
	@ApiModelProperty(required = true, value = "交班时间,日期格式：HH:mm")
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

	
	

}