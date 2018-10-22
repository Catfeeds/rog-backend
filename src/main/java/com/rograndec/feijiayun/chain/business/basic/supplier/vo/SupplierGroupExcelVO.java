package com.rograndec.feijiayun.chain.business.basic.supplier.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class SupplierGroupExcelVO implements Serializable{

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
	
	 /**
     * 名称
     */
	@ApiModelProperty(value = "名称")
    private String name;
	
	/**
     * 分组编码
     */
	@ApiModelProperty(value = "分组编码")
    private String groupCode;
	
	/**
     * 名称
     */
	@ApiModelProperty(value = "分组名称")
    private String groupName;
	
	/**
	 * 供应商名称
	 */
	@ApiModelProperty(value = "供应商名称")
	private String nature;

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

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((enterpriseId == null) ? 0 : enterpriseId.hashCode());
		result = prime * result + ((groupCode == null) ? 0 : groupCode.hashCode());
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nature == null) ? 0 : nature.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SupplierGroupExcelVO other = (SupplierGroupExcelVO) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (enterpriseId == null) {
			if (other.enterpriseId != null)
				return false;
		} else if (!enterpriseId.equals(other.enterpriseId))
			return false;
		if (groupCode == null) {
			if (other.groupCode != null)
				return false;
		} else if (!groupCode.equals(other.groupCode))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nature == null) {
			if (other.nature != null)
				return false;
		} else if (!nature.equals(other.nature))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SupplierGroupExcelVO [id=" + id + ", enterpriseId=" + enterpriseId + ", code=" + code + ", name=" + name
				+ ", groupCode=" + groupCode + ", groupName=" + groupName + ", nature=" + nature + "]";
	}
	
	
}
