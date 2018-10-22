package com.rograndec.feijiayun.chain.business.basic.user.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @Description:  基础资料-员工管理使用
 * @author dongdong.zhang
 * @date 2017-12-15
 */
@ApiModel(value = "TeamVO", description = "员工所属pos班组")
public class TeamVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID")
	private Long id;
	
	/**
     * 名称
     */
	@ApiModelProperty(required = true, value = "名称")
	private String name;
	

	/*@ApiModelProperty(value = "修改或删除标记     ture可修改,false不可修改")
	private Boolean flag = true;*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public Boolean getFlag() {
		return flag;
	}


	public void setFlag(Boolean flag) {
		this.flag = flag;
	}*/
	
 }