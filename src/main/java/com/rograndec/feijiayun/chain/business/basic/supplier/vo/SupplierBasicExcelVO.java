package com.rograndec.feijiayun.chain.business.basic.supplier.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class SupplierBasicExcelVO implements Serializable{

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
     * 经济类型（0-国有经济；1-集体经济；2-私营经济；3-个体经济；4-联营经济；5-股份制；6-外商投资；7-港澳台投资；8-其它经济类）
     */
	@ApiModelProperty(value = "经济类型（0-国有经济；1-集体经济；2-私营经济；3-个体经济；4-联营经济；5-股份制；6-外商投资；7-港澳台投资；8-其它经济类）")
    private String economicType;
	
	/**
     * 公司电话
     */
	@ApiModelProperty(value = "公司电话")
    private String tel;

    /**
     * 公司传真
     */
	@ApiModelProperty(value = "公司传真")
    private String fax;
	
	/**
     * 公司地址
     */
	@ApiModelProperty(value = "公司地址")
    private String companyAddress;
	
	/**
     * 状态
     */
	@ApiModelProperty(value = "状态")
    private String status;
	
	 /**
     * 标识（0-作废；1-正常）
     */
	@ApiModelProperty(value = "标识（0-作废；1-正常）")
    private String validFlag;
	
	/**
     * 经营方式（0-个人独自经营；1-合伙经营；2-有限责任公司；3-股份有限责任公司）
     */
	@ApiModelProperty(value = "经营方式（0-个人独自经营；1-合伙经营；2-有限责任公司；3-股份有限责任公司）")
    private String businessMode;
	
	/**
     * 注册资金（万元）
     */
	@ApiModelProperty(value = "注册资金（万元）")
    private BigDecimal registeredCapital;
	
	/**
     * 邮政编码
     */
	@ApiModelProperty(value = "邮政编码")
    private String postcode;
	
	/**
     * 公司邮箱
     */
	@ApiModelProperty(value = "公司邮箱")
    private String email;

    /**
     * 公司网址
     */
	@ApiModelProperty(value = "公司网址")
    private String site;
	
	/**
     * 仓库地址
     */
	@ApiModelProperty(value = "仓库地址")
    private String storageAddress;
	
	/**
     * 销售人员名称
     */
	@ApiModelProperty(value = "销售人员名称")
    private String saleManName;


	/**
     * 企业负责人名称
     */
	@ApiModelProperty(value = "企业负责人名称")
    private String businessManName;
	
    /**
     * 法定代表人名称
     */
	@ApiModelProperty(value = "法定代表人名称")
    private String legalManName;


    /**
     * 质量负责人名称
     */
	@ApiModelProperty(value = "质量负责人名称")
    private String qualityOfficerName;
	
	/**
     * 开户行名称
     */
	@ApiModelProperty(value = "开户行名称")
    private String bankName;

    /**
     * 开户行账号
     */
	@ApiModelProperty(value = "开户行账号")
    private String bankAccount;

    /**
     * 开户户名
     */
	@ApiModelProperty(value = "开户户名")
    private String bankAccountName;
	
	/**
     * 经营品种（0-药品；1-食品；2-化妆品；3-医疗器械；4-其它，多个用逗号分隔）
     */
	@ApiModelProperty(value = "经营品种（0-药品；1-食品；2-化妆品；3-医疗器械；4-其它，多个用逗号分隔）")
    private String businessVariety;

    /**
     * 经营范围ID（多个用逗号分隔）
     */
	@ApiModelProperty(value = "经营范围ID（多个用逗号分隔）")
    private String businessScopeId;


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

	public String getEconomicType() {
		return economicType;
	}

	public void setEconomicType(String economicType) {
		this.economicType = economicType;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getBusinessMode() {
		return businessMode;
	}

	public void setBusinessMode(String businessMode) {
		this.businessMode = businessMode;
	}

	public BigDecimal getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(BigDecimal registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getStorageAddress() {
		return storageAddress;
	}

	public void setStorageAddress(String storageAddress) {
		this.storageAddress = storageAddress;
	}

	public String getSaleManName() {
		return saleManName;
	}

	public void setSaleManName(String saleManName) {
		this.saleManName = saleManName;
	}

	public String getBusinessManName() {
		return businessManName;
	}

	public void setBusinessManName(String businessManName) {
		this.businessManName = businessManName;
	}

	public String getLegalManName() {
		return legalManName;
	}

	public void setLegalManName(String legalManName) {
		this.legalManName = legalManName;
	}

	public String getQualityOfficerName() {
		return qualityOfficerName;
	}

	public void setQualityOfficerName(String qualityOfficerName) {
		this.qualityOfficerName = qualityOfficerName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBusinessVariety() {
		return businessVariety;
	}

	public void setBusinessVariety(String businessVariety) {
		this.businessVariety = businessVariety;
	}

	public String getBusinessScopeId() {
		return businessScopeId;
	}

	public void setBusinessScopeId(String businessScopeId) {
		this.businessScopeId = businessScopeId;
	}

	@Override
	public String toString() {
		return "SupplierBasicExcelVO [ code=" + code + ", name=" + name + ", economicType=" + economicType
				+ ", tel=" + tel + ", fax=" + fax + ", companyAddress=" + companyAddress + ", status=" + status
				+ ", validFlag=" + validFlag + ", businessMode=" + businessMode + ", registeredCapital="
				+ registeredCapital + ", postcode=" + postcode + ", email=" + email + ", site=" + site
				+ ", storageAddress=" + storageAddress + ", saleManName=" + saleManName + ", businessManName="
				+ businessManName + ", legalManName=" + legalManName + ", qualityOfficerName=" + qualityOfficerName
				+ ", bankName=" + bankName + ", bankAccount=" + bankAccount + ", bankAccountName=" + bankAccountName
				+ ", businessVariety=" + businessVariety + ", businessScopeId=" + businessScopeId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bankAccount == null) ? 0 : bankAccount.hashCode());
		result = prime * result + ((bankAccountName == null) ? 0 : bankAccountName.hashCode());
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((businessManName == null) ? 0 : businessManName.hashCode());
		result = prime * result + ((businessMode == null) ? 0 : businessMode.hashCode());
		result = prime * result + ((businessScopeId == null) ? 0 : businessScopeId.hashCode());
		result = prime * result + ((businessVariety == null) ? 0 : businessVariety.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((companyAddress == null) ? 0 : companyAddress.hashCode());
		result = prime * result + ((economicType == null) ? 0 : economicType.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + ((legalManName == null) ? 0 : legalManName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
		result = prime * result + ((qualityOfficerName == null) ? 0 : qualityOfficerName.hashCode());
		result = prime * result + ((registeredCapital == null) ? 0 : registeredCapital.hashCode());
		result = prime * result + ((saleManName == null) ? 0 : saleManName.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((storageAddress == null) ? 0 : storageAddress.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		result = prime * result + ((validFlag == null) ? 0 : validFlag.hashCode());
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
		SupplierBasicExcelVO other = (SupplierBasicExcelVO) obj;
		if (bankAccount == null) {
			if (other.bankAccount != null)
				return false;
		} else if (!bankAccount.equals(other.bankAccount))
			return false;
		if (bankAccountName == null) {
			if (other.bankAccountName != null)
				return false;
		} else if (!bankAccountName.equals(other.bankAccountName))
			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (businessManName == null) {
			if (other.businessManName != null)
				return false;
		} else if (!businessManName.equals(other.businessManName))
			return false;
		if (businessMode == null) {
			if (other.businessMode != null)
				return false;
		} else if (!businessMode.equals(other.businessMode))
			return false;
		if (businessScopeId == null) {
			if (other.businessScopeId != null)
				return false;
		} else if (!businessScopeId.equals(other.businessScopeId))
			return false;
		if (businessVariety == null) {
			if (other.businessVariety != null)
				return false;
		} else if (!businessVariety.equals(other.businessVariety))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (companyAddress == null) {
			if (other.companyAddress != null)
				return false;
		} else if (!companyAddress.equals(other.companyAddress))
			return false;
		if (economicType == null) {
			if (other.economicType != null)
				return false;
		} else if (!economicType.equals(other.economicType))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (legalManName == null) {
			if (other.legalManName != null)
				return false;
		} else if (!legalManName.equals(other.legalManName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		if (qualityOfficerName == null) {
			if (other.qualityOfficerName != null)
				return false;
		} else if (!qualityOfficerName.equals(other.qualityOfficerName))
			return false;
		if (registeredCapital == null) {
			if (other.registeredCapital != null)
				return false;
		} else if (!registeredCapital.equals(other.registeredCapital))
			return false;
		if (saleManName == null) {
			if (other.saleManName != null)
				return false;
		} else if (!saleManName.equals(other.saleManName))
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (storageAddress == null) {
			if (other.storageAddress != null)
				return false;
		} else if (!storageAddress.equals(other.storageAddress))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		if (validFlag == null) {
			if (other.validFlag != null)
				return false;
		} else if (!validFlag.equals(other.validFlag))
			return false;
		return true;
	}

	
}
