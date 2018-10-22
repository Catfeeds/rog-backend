package com.rograndec.feijiayun.chain.business.basic.supplier.vo;

import com.rograndec.feijiayun.chain.common.constant.*;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SupplierDetailVO implements Serializable{
	/**
     * 主键ID
     */
	@ApiModelProperty(value = "主键ID")
    private Long id;

	/**
	 * 标准库ID
	 */
	@ApiModelProperty(value="MPH库供应商ID")
	private Long standardLibraryId;

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
     * 性质（0-批发企业；1-生产企业）
     */
	@ApiModelProperty(value = "性质（0-批发企业；1-生产企业）")
    private Integer nature;
	@ApiModelProperty(value = "性质（0-批发企业；1-生产企业）")
	private String natureName;

    /**
     * 检索码
     */
	@ApiModelProperty(value = "检索码")
    private String pinyin;

    /**
     * 分组ID
     */
	@ApiModelProperty(value = "分组ID")
    private Long groupId;


	/**
     *
     */
	@ApiModelProperty(value = "基础数据控制（0-关闭 1- 开启）")
	private Integer qualityControl;
	/**
	 * 供货单位分组名称
	 */
	@ApiModelProperty(value = "分组名称")
	private String groupName;
    /**
     * 经济类型（0-国有经济；1-集体经济；2-私营经济；3-个体经济；4-联营经济；5-股份制；6-外商投资；7-港澳台投资；8-其它经济类）
     */
	@ApiModelProperty(value = "经济类型（0-国有经济；1-集体经济；2-私营经济；3-个体经济；4-联营经济；5-股份制；6-外商投资；7-港澳台投资；8-其它经济类）")
    private Integer economicType;

	@ApiModelProperty(value = "经济类型（0-国有经济；1-集体经济；2-私营经济；3-个体经济；4-联营经济；5-股份制；6-外商投资；7-港澳台投资；8-其它经济类）")
	private String economicTypeName;

    /**
     * 经营方式（0-个人独自经营；1-合伙经营；2-有限责任公司；3-股份有限责任公司）
     */
	@ApiModelProperty(value = "经营方式（0-个人独自经营；1-合伙经营；2-有限责任公司；3-股份有限责任公司）")
    private Integer businessMode;

	@ApiModelProperty(value = "经营方式（0-个人独自经营；1-合伙经营；2-有限责任公司；3-股份有限责任公司）")
	private String businessModeName;

    /**
     * 注册资金（万元）
     */
	@ApiModelProperty(value = "注册资金（万元）")
    private BigDecimal registeredCapital;

    /**
     * 省ID
     */
	@ApiModelProperty(value = "省ID")
    private Integer provinceId;

    /**
     * 省名称
     */
	@ApiModelProperty(value = "省名称")
    private String provinceName;

    /**
     * 市ID
     */
	@ApiModelProperty(value = "市ID")
    private Integer cityId;

    /**
     * 市名称
     */
	@ApiModelProperty(value = "市名称")
    private String cityName;

	/**
	 * 纳税人识别号
	 */
	@ApiModelProperty(value = "纳税人识别号")
	private String taxpayerCode;

    /**
     * 区ID
     */
	@ApiModelProperty(value = "区ID")
    private Integer areaId;

    /**
     * 区名称
     */
	@ApiModelProperty(value = "区名称")
    private String areaName;

	@ApiModelProperty(value = "行政区域")
	private String regionalism;

    /**
     * 公司地址
     */
	@ApiModelProperty(value = "公司地址")
    private String companyAddress;

    /**
     * 仓库地址
     */
	@ApiModelProperty(value = "仓库地址")
    private String storageAddress;

    /**
     * 邮政编码
     */
	@ApiModelProperty(value = "邮政编码")
    private String postcode;

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
     * 销售人员ID
     */
	@ApiModelProperty(value = "销售人员ID")
    private Long saleManId;

    /**
     * 销售人员编码
     */
	@ApiModelProperty(value = "销售人员编码")
    private String saleManCode;

    /**
     * 销售人员名称
     */
	@ApiModelProperty(value = "销售人员名称")
    private String saleManName;

    /**
     * 企业负责人ID
     */
	@ApiModelProperty(value = "企业负责人ID")
    private Long businessManId;

    /**
     * 企业负责人编码
     */
	@ApiModelProperty(value = "企业负责人编码")
    private String businessManCode;

    /**
     * 企业负责人名称
     */
	@ApiModelProperty(value = "企业负责人名称")
    private String businessManName;

    /**
     * 法定代表人ID
     */
	@ApiModelProperty(value = "法定代表人ID")
    private Long legalManId;

    /**
     * 法定代表人编码
     */
	@ApiModelProperty(value = "法定代表人编码")
    private String legalManCode;

    /**
     * 法定代表人名称
     */
	@ApiModelProperty(value = "法定代表人名称")
    private String legalManName;

    /**
     * 质量负责人ID
     */
	@ApiModelProperty(value = "质量负责人ID")
    private Long qualityOfficerId;

    /**
     * 质量负责人编码
     */
	@ApiModelProperty(value = "质量负责人编码")
    private String qualityOfficerCode;

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

	@ApiModelProperty(value = "经营品种（0-药品；1-食品；2-化妆品；3-医疗器械；4-其它，多个用逗号分隔）")
	private String businessVarietyName;
    /**
     * 经营范围ID（多个用逗号分隔）
     */
	@ApiModelProperty(value = "经营范围ID（多个用逗号分隔）")
    private String businessScopeId;

	/**
	 * 经营范围名称（以分号隔开）
	 */
	@ApiModelProperty(value = "经营范围名称（多个用分号分隔）")
	private String businessScopeName;
	
	@ApiModelProperty(value = "销售人员实体（新增时需要前端传过来）")
	private SupplierSalerVO supplierSalerVO;
	

	/**
     * 公司简介
     */
	@ApiModelProperty(value = "公司简介")
    private String supplierAbout;

    /**
     * 状态
     */
	@ApiModelProperty(value = "状态")
    private Integer status;

	@ApiModelProperty(value = "状态")
	private String statusName;



    /**
     * 标识（0-作废；1-正常）
     */
	@ApiModelProperty(value = "标识（0-作废；1-正常）")
    private Integer validFlag;

	@ApiModelProperty(value = "标识（0-作废；1-正常）")
	private String validFlagName;

    /**
     * 备注
     */
	@ApiModelProperty(value = "备注")
    private String remark;

	
	/**
	 * 
	 * @Description:业务字表的字段 
	 */
	@ApiModelProperty(value = " 供货单位业务模块")
	private SupplierBusinessVO supplierBusinessVO;
	/**
	 * @Description:资质信息表的字段 
	 */
	@ApiModelProperty(value = " 供货单位资质模块")
	private List<SupplierQulificationVO> supplierQulificationVO;
	/**
	 * @Description: 资质信息表的字段 以上
	 */
	
	/**
     * 员工资质删除id集合
     */
    @ApiModelProperty(value = " 供货单位资质删除id集合 如果修改时,有删除时需要传递,新增时不需要传递")
    private List<Long> deleteSupplierQualificationConfigIds = new ArrayList<Long>();

    /**
     * 
     * 修改原因(从前台传入)如果是增加不需要,如果是修改需要
     */
    @ApiModelProperty(value = "修改原因(从前台传入)如果是增加不需要,如果是修改需要")
    private String updateDetail;
    
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

	public Integer getNature() {
		return nature;
	}

	public void setNature(Integer nature) {
		this.nature = nature;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getEconomicType() {
		return economicType;
	}

	public void setEconomicType(Integer economicType) {
		this.economicType = economicType;
	}

	public Integer getBusinessMode() {
		return businessMode;
	}

	public void setBusinessMode(Integer businessMode) {
		this.businessMode = businessMode;
	}

	public BigDecimal getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(BigDecimal registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getStorageAddress() {
		return storageAddress;
	}

	public void setStorageAddress(String storageAddress) {
		this.storageAddress = storageAddress;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
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

	public Long getSaleManId() {
		return saleManId;
	}

	public void setSaleManId(Long saleManId) {
		this.saleManId = saleManId;
	}

	public String getSaleManCode() {
		return saleManCode;
	}

	public void setSaleManCode(String saleManCode) {
		this.saleManCode = saleManCode;
	}

	public String getSaleManName() {
		return saleManName;
	}

	public void setSaleManName(String saleManName) {
		this.saleManName = saleManName;
	}

	public Long getBusinessManId() {
		return businessManId;
	}

	public void setBusinessManId(Long businessManId) {
		this.businessManId = businessManId;
	}

	public String getBusinessManCode() {
		return businessManCode;
	}

	public void setBusinessManCode(String businessManCode) {
		this.businessManCode = businessManCode;
	}

	public String getBusinessManName() {
		return businessManName;
	}

	public void setBusinessManName(String businessManName) {
		this.businessManName = businessManName;
	}

	public Long getLegalManId() {
		return legalManId;
	}

	public void setLegalManId(Long legalManId) {
		this.legalManId = legalManId;
	}

	public String getLegalManCode() {
		return legalManCode;
	}

	public void setLegalManCode(String legalManCode) {
		this.legalManCode = legalManCode;
	}

	public String getLegalManName() {
		return legalManName;
	}

	public void setLegalManName(String legalManName) {
		this.legalManName = legalManName;
	}

	public Long getQualityOfficerId() {
		return qualityOfficerId;
	}

	public void setQualityOfficerId(Long qualityOfficerId) {
		this.qualityOfficerId = qualityOfficerId;
	}

	public String getQualityOfficerCode() {
		return qualityOfficerCode;
	}

	public void setQualityOfficerCode(String qualityOfficerCode) {
		this.qualityOfficerCode = qualityOfficerCode;
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

	public String getSupplierAbout() {
		return supplierAbout;
	}

	public void setSupplierAbout(String supplierAbout) {
		this.supplierAbout = supplierAbout;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public SupplierBusinessVO getSupplierBusinessVO() {
		return supplierBusinessVO;
	}

	public void setSupplierBusinessVO(SupplierBusinessVO supplierBusinessVO) {
		this.supplierBusinessVO = supplierBusinessVO;
	}

	public List<SupplierQulificationVO> getSupplierQulificationVO() {
		return supplierQulificationVO;
	}

	public void setSupplierQulificationVO(List<SupplierQulificationVO> supplierQulificationVO) {
		this.supplierQulificationVO = supplierQulificationVO;
	}
	
	

	public List<Long> getDeleteSupplierQualificationConfigIds() {
		return deleteSupplierQualificationConfigIds;
	}

	public void setDeleteSupplierQualificationConfigIds(List<Long> deleteSupplierQualificationConfigIds) {
		this.deleteSupplierQualificationConfigIds = deleteSupplierQualificationConfigIds;
	}
	
	

	public String getUpdateDetail() {
		return updateDetail;
	}

	public void setUpdateDetail(String updateDetail) {
		this.updateDetail = updateDetail;
	}

	
	public Integer getQualityControl() {
		return qualityControl;
	}

	public void setQualityControl(Integer qualityControl) {
		this.qualityControl = qualityControl;
	}
	
	

	public SupplierSalerVO getSupplierSalerVO() {
		return supplierSalerVO;
	}

	public void setSupplierSalerVO(SupplierSalerVO supplierSalerVO) {
		this.supplierSalerVO = supplierSalerVO;
	}

	public Long getStandardLibraryId() {
		return standardLibraryId;
	}

	public void setStandardLibraryId(Long standardLibraryId) {
		this.standardLibraryId = standardLibraryId;
	}

	public String getTaxpayerCode() {
		return taxpayerCode;
	}

	public void setTaxpayerCode(String taxpayerCode) {
		this.taxpayerCode = taxpayerCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((areaId == null) ? 0 : areaId.hashCode());
		result = prime * result + ((areaName == null) ? 0 : areaName.hashCode());
		result = prime * result + ((bankAccount == null) ? 0 : bankAccount.hashCode());
		result = prime * result + ((bankAccountName == null) ? 0 : bankAccountName.hashCode());
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((businessManCode == null) ? 0 : businessManCode.hashCode());
		result = prime * result + ((businessManId == null) ? 0 : businessManId.hashCode());
		result = prime * result + ((businessManName == null) ? 0 : businessManName.hashCode());
		result = prime * result + ((businessMode == null) ? 0 : businessMode.hashCode());
		result = prime * result + ((businessScopeId == null) ? 0 : businessScopeId.hashCode());
		result = prime * result + ((businessScopeName == null) ? 0 : businessScopeName.hashCode());
		result = prime * result + ((businessVariety == null) ? 0 : businessVariety.hashCode());
		result = prime * result + ((cityId == null) ? 0 : cityId.hashCode());
		result = prime * result + ((cityName == null) ? 0 : cityName.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((companyAddress == null) ? 0 : companyAddress.hashCode());
		result = prime * result + ((economicType == null) ? 0 : economicType.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enterpriseId == null) ? 0 : enterpriseId.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((legalManCode == null) ? 0 : legalManCode.hashCode());
		result = prime * result + ((legalManId == null) ? 0 : legalManId.hashCode());
		result = prime * result + ((legalManName == null) ? 0 : legalManName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nature == null) ? 0 : nature.hashCode());
		result = prime * result + ((pinyin == null) ? 0 : pinyin.hashCode());
		result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
		result = prime * result + ((provinceId == null) ? 0 : provinceId.hashCode());
		result = prime * result + ((provinceName == null) ? 0 : provinceName.hashCode());
		result = prime * result + ((qualityOfficerCode == null) ? 0 : qualityOfficerCode.hashCode());
		result = prime * result + ((qualityOfficerId == null) ? 0 : qualityOfficerId.hashCode());
		result = prime * result + ((qualityOfficerName == null) ? 0 : qualityOfficerName.hashCode());
		result = prime * result + ((registeredCapital == null) ? 0 : registeredCapital.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((saleManCode == null) ? 0 : saleManCode.hashCode());
		result = prime * result + ((saleManId == null) ? 0 : saleManId.hashCode());
		result = prime * result + ((saleManName == null) ? 0 : saleManName.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((storageAddress == null) ? 0 : storageAddress.hashCode());
		result = prime * result + ((supplierAbout == null) ? 0 : supplierAbout.hashCode());
		result = prime * result + ((supplierBusinessVO == null) ? 0 : supplierBusinessVO.hashCode());
		result = prime * result + ((supplierQulificationVO == null) ? 0 : supplierQulificationVO.hashCode());
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
		SupplierDetailVO other = (SupplierDetailVO) obj;
		if (areaId == null) {
			if (other.areaId != null)
				return false;
		} else if (!areaId.equals(other.areaId))
			return false;
		if (areaName == null) {
			if (other.areaName != null)
				return false;
		} else if (!areaName.equals(other.areaName))
			return false;
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
		if (businessManCode == null) {
			if (other.businessManCode != null)
				return false;
		} else if (!businessManCode.equals(other.businessManCode))
			return false;
		if (businessManId == null) {
			if (other.businessManId != null)
				return false;
		} else if (!businessManId.equals(other.businessManId))
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
		if (businessScopeName == null) {
			if (other.businessScopeName != null)
				return false;
		} else if (!businessScopeName.equals(other.businessScopeName))
			return false;
		if (businessVariety == null) {
			if (other.businessVariety != null)
				return false;
		} else if (!businessVariety.equals(other.businessVariety))
			return false;
		if (cityId == null) {
			if (other.cityId != null)
				return false;
		} else if (!cityId.equals(other.cityId))
			return false;
		if (cityName == null) {
			if (other.cityName != null)
				return false;
		} else if (!cityName.equals(other.cityName))
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
		if (enterpriseId == null) {
			if (other.enterpriseId != null)
				return false;
		} else if (!enterpriseId.equals(other.enterpriseId))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
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
		if (legalManCode == null) {
			if (other.legalManCode != null)
				return false;
		} else if (!legalManCode.equals(other.legalManCode))
			return false;
		if (legalManId == null) {
			if (other.legalManId != null)
				return false;
		} else if (!legalManId.equals(other.legalManId))
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
		if (nature == null) {
			if (other.nature != null)
				return false;
		} else if (!nature.equals(other.nature))
			return false;
		if (pinyin == null) {
			if (other.pinyin != null)
				return false;
		} else if (!pinyin.equals(other.pinyin))
			return false;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		if (provinceId == null) {
			if (other.provinceId != null)
				return false;
		} else if (!provinceId.equals(other.provinceId))
			return false;
		if (provinceName == null) {
			if (other.provinceName != null)
				return false;
		} else if (!provinceName.equals(other.provinceName))
			return false;
		if (qualityOfficerCode == null) {
			if (other.qualityOfficerCode != null)
				return false;
		} else if (!qualityOfficerCode.equals(other.qualityOfficerCode))
			return false;
		if (qualityOfficerId == null) {
			if (other.qualityOfficerId != null)
				return false;
		} else if (!qualityOfficerId.equals(other.qualityOfficerId))
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
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (saleManCode == null) {
			if (other.saleManCode != null)
				return false;
		} else if (!saleManCode.equals(other.saleManCode))
			return false;
		if (saleManId == null) {
			if (other.saleManId != null)
				return false;
		} else if (!saleManId.equals(other.saleManId))
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
		if (supplierAbout == null) {
			if (other.supplierAbout != null)
				return false;
		} else if (!supplierAbout.equals(other.supplierAbout))
			return false;
		if (supplierBusinessVO == null) {
			if (other.supplierBusinessVO != null)
				return false;
		} else if (!supplierBusinessVO.equals(other.supplierBusinessVO))
			return false;
		if (supplierQulificationVO == null) {
			if (other.supplierQulificationVO != null)
				return false;
		} else if (!supplierQulificationVO.equals(other.supplierQulificationVO))
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

	@Override
	public String toString() {
		return "SupplierDetailVO [id=" + id + ", enterpriseId=" + enterpriseId + ", code=" + code + ", name=" + name
				+ ", nature=" + nature + ", pinyin=" + pinyin + ", groupId=" + groupId + ", groupName=" + groupName
				+ ", economicType=" + economicType + ", businessMode=" + businessMode + ", registeredCapital="
				+ registeredCapital + ", provinceId=" + provinceId + ", provinceName=" + provinceName + ", cityId="
				+ cityId + ", cityName=" + cityName + ", areaId=" + areaId + ", areaName=" + areaName
				+ ", companyAddress=" + companyAddress + ", storageAddress=" + storageAddress + ", postcode=" + postcode
				+ ", tel=" + tel + ", fax=" + fax + ", email=" + email + ", site=" + site + ", saleManId=" + saleManId
				+ ", saleManCode=" + saleManCode + ", saleManName=" + saleManName + ", businessManId=" + businessManId
				+ ", businessManCode=" + businessManCode + ", businessManName=" + businessManName + ", legalManId="
				+ legalManId + ", legalManCode=" + legalManCode + ", legalManName=" + legalManName
				+ ", qualityOfficerId=" + qualityOfficerId + ", qualityOfficerCode=" + qualityOfficerCode
				+ ", qualityOfficerName=" + qualityOfficerName + ", bankName=" + bankName + ", bankAccount="
				+ bankAccount + ", bankAccountName=" + bankAccountName + ", businessVariety=" + businessVariety
				+ ", businessScopeId=" + businessScopeId + ", businessScopeName=" + businessScopeName
				+ ", supplierAbout=" + supplierAbout + ", status=" + status + ", validFlag=" + validFlag + ", remark="
				+ remark + ",supplierBusinessVO="
				+ supplierBusinessVO + ", supplierQulificationVO=" + supplierQulificationVO + "]";
	}

	public String getBusinessScopeName() {
		return businessScopeName;
	}

	public void setBusinessScopeName(String businessScopeName) {
		this.businessScopeName = businessScopeName;
	}

	public String getEconomicTypeName() {
		if(economicType != null){
			return EconomyType.getName(economicType);
		}
		return "";
	}

	public void setEconomicTypeName(String economicTypeName) {
		this.economicTypeName = economicTypeName;
	}

	public String getBusinessModeName()
	{
		if(businessMode != null){
			return BusinessMode.getName(businessMode);
		}
		return "";
	}

	public void setBusinessModeName(String businessModeName) {
		this.businessModeName = businessModeName;
	}

	public String getRegionalism() {
		return regionalism;
	}

	public void setRegionalism(String regionalism) {
		this.regionalism = regionalism;
	}

	public String getBusinessVarietyName() {
		return businessVarietyName;
	}

	public void setBusinessVarietyName(String businessVarietyName) {
		this.businessVarietyName = businessVarietyName;
	}

	public String getStatusName() {
		if(status != null){
			return EnableStatus.getName(status);
		}
		return "";
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getValidFlagName() {
		if(validFlag != null){
			return ValidFlag.getName(validFlag);
		}
		return "";
	}

	public void setValidFlagName(String validFlagName) {
		this.validFlagName = validFlagName;
	}

	public String getNatureName() {
		if(nature != null){
			return Nature.getName(nature);
		}
		return "";
	}

	public void setNatureName(String natureName) {
		this.natureName = natureName;
	}
}
