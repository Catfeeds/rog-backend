package com.rograndec.feijiayun.chain.business.retail.pos.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @ClassName: PosStandSaveOrupdateVO
 * @Description:  零售管理-POS管理-款台-Rest接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:40:37
 */
@ApiModel(value = "PosStandSaveOrupdateVO", description = "零售管理-POS管理-款台")
public class PosStandSaveOrupdateVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID,修改时需要传入,新增时不需要")
	private Long id;
	
	/**
     * 上级企业ID
     */
	@ApiModelProperty(required = false, value = "上级企业ID")
	private Long parentId;
	
	/**
     * 编码
     */
	@NotBlank(message="编码不能为空!")
	@ApiModelProperty(required = true, value = "编码")
	private String code;
	
	/**
     * mac地址
     */
	@NotBlank(message="mac地址不能为空!")
	@ApiModelProperty(required = true, value = "mac地址")
	private String mac;
	
	/**
     * 状态（0-禁用；1-启用）
     */
	@ApiModelProperty(required = false, value = "状态（0-禁用；1-启用）")
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
	 * 上级企业ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 上级企业ID
	 */
	public Long getParentId() {
		return parentId;
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
	 * mac地址
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	/**
	 * mac地址
	 */
	public String getMac() {
		return mac;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}