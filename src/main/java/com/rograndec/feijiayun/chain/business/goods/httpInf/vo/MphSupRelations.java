package com.rograndec.feijiayun.chain.business.goods.httpInf.vo;

import java.io.Serializable;

public class MphSupRelations implements Serializable {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 683126118571339197L;

	private String supplierName;
	private String supplierAllowCode;
	private String supplierLicenseNo;
	private String supplierAddress;
	private String supplierContactor;
	private Integer supplierId;

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierAllowCode() {
		return supplierAllowCode;
	}

	public void setSupplierAllowCode(String supplierAllowCode) {
		this.supplierAllowCode = supplierAllowCode;
	}

	public String getSupplierLicenseNo() {
		return supplierLicenseNo;
	}

	public void setSupplierLicenseNo(String supplierLicenseNo) {
		this.supplierLicenseNo = supplierLicenseNo;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getSupplierContactor() {
		return supplierContactor;
	}

	public void setSupplierContactor(String supplierContactor) {
		this.supplierContactor = supplierContactor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

}
