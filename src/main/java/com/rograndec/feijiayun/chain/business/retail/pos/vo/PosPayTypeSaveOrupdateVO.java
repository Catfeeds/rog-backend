package com.rograndec.feijiayun.chain.business.retail.pos.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @ClassName: PosPayTypeSaveOrupdateVO
 * @Description:  零售管理-POS管理-支付方式-Rest接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:19:11
 */
@ApiModel(value = "PosPayTypeSaveOrupdateVO", description = "零售管理-POS管理-支付方式")
public class PosPayTypeSaveOrupdateVO implements Serializable {
	
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
     * 状态（0-禁用；1-启用）
     */
	@ApiModelProperty(required = true, value = "状态（0-禁用；1-启用）")
	private Integer status;
	
	@ApiModelProperty(value = "快捷键（0-11:F1～F2）")
    private Integer shortcutKey;
	
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

	

}