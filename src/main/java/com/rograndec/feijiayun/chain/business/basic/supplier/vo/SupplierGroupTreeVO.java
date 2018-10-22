package com.rograndec.feijiayun.chain.business.basic.supplier.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SupplierGroupTreeVO implements Serializable{

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
     * 编码
     */
	@ApiModelProperty(value = "企业类型性质（0-批发企业；1-生产企业）")
    private Integer enterpriseType;
	
    /**
     * 名称
     */
	@ApiModelProperty(value = "名称")
    private String name;


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


	@ApiModelProperty(value = "当前分组下的所有供货单位")
	private List<SupplierBasicVO> supplierList;

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

	public Integer getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(Integer enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public List<SupplierBasicVO> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(List<SupplierBasicVO> supplierList) {
		this.supplierList = supplierList;
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

	@Override
	public String toString() {
		return "SupplierGroupTreeVO [id=" + id + ", enterpriseId=" + enterpriseId + ", code=" + code
				+ ", enterpriseType=" + enterpriseType + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((enterpriseId == null) ? 0 : enterpriseId.hashCode());
		result = prime * result + ((enterpriseType == null) ? 0 : enterpriseType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		SupplierGroupTreeVO other = (SupplierGroupTreeVO) obj;
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
		if (enterpriseType == null) {
			if (other.enterpriseType != null)
				return false;
		} else if (!enterpriseType.equals(other.enterpriseType))
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
		return true;
	}
	
	
}
