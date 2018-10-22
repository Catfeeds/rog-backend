package com.rograndec.feijiayun.chain.business.system.enterprise.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@ApiModel(value = "SimpleEnterpriseVO", description = "企业简单信息对象")
public class SimpleEnterpriseVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年8月31日 上午11:30:00 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 300831014010555722L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID，0时新建，其他值时修改")
    private Long id;

    /**
     * 上级企业ID，默认值为0
     */
	@ApiModelProperty(required = true, value = "父级ID，总部为0")
    private Long parentId;
	
	 /**
     * 编码
     */
	@ApiModelProperty(required = true, value = "编码")
    private String code;
	
	/**
     * 企业名称
     */
	@ApiModelProperty(required = true, value = "名称")
    private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
