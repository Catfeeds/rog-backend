package com.rograndec.feijiayun.chain.business.basic.supplier.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class SupplierGroupVO implements Serializable{

	/**
     * 主键ID
     */
	@ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业（总部）ID
     */
	@ApiModelProperty(value = "企业（总部）ID")
    private Long enterpriseId;

    /**
     * 编码
     */
	@ApiModelProperty(value = "编码")
    private String code;
	
	@ApiModelProperty(value = "名称")
	private String name;
	
	/**
     * 分组ID
     */
	@ApiModelProperty(value = "分组ID")
    private Long groupId;
	
	
	@ApiModelProperty(value = "性质（0-批发企业；1-生产企业）")
	private Integer nature;
	
	/**
     * 状态（0-禁用；1-启用）
     */
	@ApiModelProperty(value = "状态（0-禁用；1-启用）")
    private Integer status;

    /**
     * 备注
     */
	@ApiModelProperty(value = "备注")
    private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Integer getNature() {
		return nature;
	}

	public void setNature(Integer nature) {
		this.nature = nature;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
