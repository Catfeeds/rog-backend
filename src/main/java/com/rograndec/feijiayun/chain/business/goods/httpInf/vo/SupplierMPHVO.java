package com.rograndec.feijiayun.chain.business.goods.httpInf.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SupplierMPHVO", description = "标准库返回供应商信息")
public class SupplierMPHVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年11月1日 下午2:59:05 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "标准库ID")
	private Long standardLibraryId;
	
	@ApiModelProperty(value = "供应商名称")
	private String supplierName;
	
	@ApiModelProperty(value = "公司地址")
	private String companyAddress;
	
	@ApiModelProperty(value = "许可证号")
	private String licenseCode;
	
	@ApiModelProperty(value = "营业执照号")
	private String businessLicenseCode;
	
	@ApiModelProperty(value = "法定代表人名称")
    private String legalManName;
	

	
	public Long getStandardLibraryId() {
		return standardLibraryId;
	}

	public void setStandardLibraryId(Long standardLibraryId) {
		this.standardLibraryId = standardLibraryId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public String getBusinessLicenseCode() {
		return businessLicenseCode;
	}

	public void setBusinessLicenseCode(String businessLicenseCode) {
		this.businessLicenseCode = businessLicenseCode;
	}

	public String getLegalManName() {
		return legalManName;
	}

	public void setLegalManName(String legalManName) {
		this.legalManName = legalManName;
	}
	
	
}
