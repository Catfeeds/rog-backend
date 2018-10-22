package com.rograndec.feijiayun.chain.business.purchase.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class SupplierOrderVO implements Serializable{

	@ApiModelProperty(value = "主键ID")
	private Long id;

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
    * 供货单位销售人员
     * */
	@ApiModelProperty(value = "供货单位销售人员")
	private String saleManName;
	/**
	 * 供货单位联系电话
	 * */
	@ApiModelProperty(value = "供货单位联系电话")
	private String telePhone;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSaleManName() {
		return saleManName;
	}

	public void setSaleManName(String saleManName) {
		this.saleManName = saleManName;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SupplierOrderVO that = (SupplierOrderVO) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (code != null ? !code.equals(that.code) : that.code != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (saleManName != null ? !saleManName.equals(that.saleManName) : that.saleManName != null) return false;
		return telePhone != null ? telePhone.equals(that.telePhone) : that.telePhone == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (code != null ? code.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (saleManName != null ? saleManName.hashCode() : 0);
		result = 31 * result + (telePhone != null ? telePhone.hashCode() : 0);
		return result;
	}
}
