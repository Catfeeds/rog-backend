package com.rograndec.feijiayun.chain.business.basic.store.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApiModel(value = "StoreEnterpriseVO", description = "修改门店基本信息对象")
public class StoreEnterpriseVO implements Serializable {
	
	StoreEnterpriseBusinessVO storeEnterpriseBusinessVO;
	
	public StoreEnterpriseBusinessVO getStoreEnterpriseBusinessVO() {
		return storeEnterpriseBusinessVO;
	}

	public void setStoreEnterpriseBusinessVO(
			StoreEnterpriseBusinessVO storeEnterpriseBusinessVO) {
		this.storeEnterpriseBusinessVO = storeEnterpriseBusinessVO;
	}

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
     * 药店类型（0-总部；1-自营店；2-加盟店）
     */
	@ApiModelProperty(required = true, value = "父级ID，药店类型（0-总部；1-自营店；2-加盟店）")
    private Integer chainType;

    /**
     * 编码
     */
	@ApiModelProperty(required = true, value = "编码")
    private String code;

    /**
     * 检索码
     */
	@ApiModelProperty(required = false, value = "检索码")
    private String pinyin;

    /**
     * 企业名称
     */
	@ApiModelProperty(required = true, value = "名称")
    private String name;
	
	 /**
     * 门店分组
     */
	@ApiModelProperty(required = false, value = "门店分组")
    private Long groupId;

    /**
     * 经济类型（0-国有经济；1-集体经济；2-私营经济；3-个体经济；4-联营经济；5-股份制；6-外商投资；7-港澳台投资；8-其它经济类）
     */
	@ApiModelProperty(required = false, value = "经济类型（0-国有经济；1-集体经济；2-私营经济；3-个体经济；4-联营经济；5-股份制；6-外商投资；7-港澳台投资；8-其它经济类）")
    private Integer economicType;

    /**
     * 经营方式（0-个人独自经营；1-合伙经营；2-有限责任公司；3-股份有限责任公司）
     */
	@ApiModelProperty(required = false, value = "经营方式（0-个人独自经营；1-合伙经营；2-有限责任公司；3-股份有限责任公司）")
    private Integer businessMode;

    /**
     * 注册资金（万元）
     */
	@ApiModelProperty(required = false, value = "注册资金（万元）")
    private BigDecimal registerMoney;

    /**
     * 省ID
     */
	@ApiModelProperty(required = false, value = "省ID")
    private Integer provinceId;

    /**
     * 市ID
     */
	@ApiModelProperty(required = false, value = "市ID")
    private Integer cityId;

    /**
     * 区ID
     */
	@ApiModelProperty(required = false, value = "区ID")
    private Integer areaId;

    /**
     * 公司地址
     */
	@ApiModelProperty(required = false, value = "公司地址")
    private String companyAddress;

    /**
     * 邮政编码
     */
	@ApiModelProperty(required = false, value = "邮政编码")
    private String postcode;

    /**
     * 公司电话
     */
	@ApiModelProperty(required = false, value = "公司电话")
    private String tel;

    /**
     * 公司传真
     */
	@ApiModelProperty(required = false, value = "公司传真")
    private String fax;

    /**
     * 公司邮箱
     */
	@ApiModelProperty(required = false, value = "公司邮箱")
    private String email;

    /**
     * 公司网址
     */
	@ApiModelProperty(required = false, value = "公司网址")
    private String site;

	/**
     * 销售片区
     */
	@ApiModelProperty(required = false, value = "销售片区")
    private Long saleAreaId;

    /**
     * 销售商圈
     */
	@ApiModelProperty(required = false, value = "销售商圈")
    private Long saleCircleId;
	
	/**
     * 门店级别
     */
	@ApiModelProperty(required = false, value = "门店级别")
    private Long storeLevelId;
	
    /**
     * 分店：面积（㎡）
     */
	@ApiModelProperty(required = false, value = "面积（㎡）")
    private BigDecimal acreage;

    /**
     * 分店：月租（元）
     */
	@ApiModelProperty(required = false, value = "月租（元）")
    private BigDecimal monthly;

    /**
     * 分店：开店日期
     */
	@ApiModelProperty(required = false, value = "分店：开店日期")
    private Date shopDate;

    /**
     * 最近搬迁日期
     */
	@ApiModelProperty(required = false, value = "最近搬迁日期")
    private Date relocationDate;

    /**
     * 最近改造日期
     */
	@ApiModelProperty(required = false, value = "最近改造日期")
    private Date reformDate;

    /**
     * 企业负责人ID
     */
	@ApiModelProperty(required = false, value = "企业负责人ID")
    private Long businessManId;

    /**
     * 法定代表人ID
     */
	@ApiModelProperty(required = false, value = "法定代表人ID")
    private Long legalManId;

    /**
     * 质量负责人ID
     */
	@ApiModelProperty(required = false, value = "质量负责人ID")
    private Long qualityOfficerId;

    /**
     * 开户行名称
     */
	@ApiModelProperty(required = false, value = "开户银行名称")
    private String bankName;

    /**
     * 开户行账号
     */
	@ApiModelProperty(required = false, value = "开户行账号")
    private String bankAccount;

    /**
     * 开户户名
     */
	@ApiModelProperty(required = false, value = "开户户名")
    private String bankAccountName;

    /**
     * 经营品种（0-药品；1-食品；2-化妆品；3-医疗器械；4-其它，多个用逗号分隔）
     */
	@ApiModelProperty(required = false, value = "经营品种（0-药品；1-食品；2-化妆品；3-医疗器械；4-其它，多个用逗号分隔）")
    private String businessVariety;
	
	/**
     * 经营品种数组
     */
    @ApiModelProperty(value = "经营品种数组")
    private List<Integer> businessVarietyArray;

    /**
     * 分店：医保药店（0-否；1-是）
     */
	@ApiModelProperty(required = false, value = "分店：医保药店（0-否；1-是）")
    private Integer medicalFlag;

    /**
     * 分店：医保机构编码
     */
	@ApiModelProperty(required = false, value = "分店：医保机构编码")
    private String medicalCode;

    /**
     * 公司简介
     */
	@ApiModelProperty(required = false, value = "公司简介")
    private String companyAbout;

    /**
     * 经营范围ID（多个用逗号分隔）
     */
	@ApiModelProperty(required = false, value = "经营范围ID（多个用逗号分隔）")
    private String businessScopeId;
	
	/**
     * 经营范围ID数组
     */
    @ApiModelProperty(value = "经营范围ID数组")
    private List<Integer> businessScopeIdArray;
	
	/**
     * 状态（0-禁用；1-启用）
     */
	@ApiModelProperty(value = "状态（0-禁用；1-启用）")
    private Integer status;

    /**
     * 标识（0-作废；1-正常）
     */
	@ApiModelProperty(value = "标识（0-作废；1-正常）")
    private Integer validFlag;

    /**
     * saas_enterprise
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 上级企业ID，默认值为0
     * @return parent_id 上级企业ID，默认值为0
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID，默认值为0
     * @param parentId 上级企业ID，默认值为0
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 药店类型（0-总部；1-自营店；2-加盟店）
     * @return chain_type 药店类型（0-总部；1-自营店；2-加盟店）
     */
    public Integer getChainType() {
        return chainType;
    }

    /**
     * 药店类型（0-总部；1-自营店；2-加盟店）
     * @param chainType 药店类型（0-总部；1-自营店；2-加盟店）
     */
    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    /**
     * 编码
     * @return code 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 编码
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 检索码
     * @return pinyin 检索码
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * 检索码
     * @param pinyin 检索码
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    /**
     * 企业名称
     * @return name 企业名称
     */
    public String getName() {
        return name;
    }

    /**
     * 企业名称
     * @param name 企业名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 经济类型（0-国有经济；1-集体经济；2-私营经济；3-个体经济；4-联营经济；5-股份制；6-外商投资；7-港澳台投资；8-其它经济类）
     * @return economic_type 经济类型（0-国有经济；1-集体经济；2-私营经济；3-个体经济；4-联营经济；5-股份制；6-外商投资；7-港澳台投资；8-其它经济类）
     */
    public Integer getEconomicType() {
        return economicType;
    }

    /**
     * 经济类型（0-国有经济；1-集体经济；2-私营经济；3-个体经济；4-联营经济；5-股份制；6-外商投资；7-港澳台投资；8-其它经济类）
     * @param economicType 经济类型（0-国有经济；1-集体经济；2-私营经济；3-个体经济；4-联营经济；5-股份制；6-外商投资；7-港澳台投资；8-其它经济类）
     */
    public void setEconomicType(Integer economicType) {
        this.economicType = economicType;
    }

    /**
     * 经营方式（0-个人独自经营；1-合伙经营；2-有限责任公司；3-股份有限责任公司）
     * @return business_mode 经营方式（0-个人独自经营；1-合伙经营；2-有限责任公司；3-股份有限责任公司）
     */
    public Integer getBusinessMode() {
        return businessMode;
    }

    /**
     * 经营方式（0-个人独自经营；1-合伙经营；2-有限责任公司；3-股份有限责任公司）
     * @param businessMode 经营方式（0-个人独自经营；1-合伙经营；2-有限责任公司；3-股份有限责任公司）
     */
    public void setBusinessMode(Integer businessMode) {
        this.businessMode = businessMode;
    }

    /**
     * 注册资金（万元）
     * @return register_money 注册资金（万元）
     */
    public BigDecimal getRegisterMoney() {
        return registerMoney;
    }

    /**
     * 注册资金（万元）
     * @param registerMoney 注册资金（万元）
     */
    public void setRegisterMoney(BigDecimal registerMoney) {
        this.registerMoney = registerMoney;
    }

    /**
     * 省ID
     * @return province_id 省ID
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * 省ID
     * @param provinceId 省ID
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 市ID
     * @return city_id 市ID
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 市ID
     * @param cityId 市ID
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 区ID
     * @return area_id 区ID
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * 区ID
     * @param areaId 区ID
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * 公司地址
     * @return company_address 公司地址
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * 公司地址
     * @param companyAddress 公司地址
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    /**
     * 邮政编码
     * @return postcode 邮政编码
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * 邮政编码
     * @param postcode 邮政编码
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    /**
     * 公司电话
     * @return tel 公司电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 公司电话
     * @param tel 公司电话
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * 公司传真
     * @return fax 公司传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 公司传真
     * @param fax 公司传真
     */
    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    /**
     * 公司邮箱
     * @return email 公司邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 公司邮箱
     * @param email 公司邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 公司网址
     * @return site 公司网址
     */
    public String getSite() {
        return site;
    }

    /**
     * 公司网址
     * @param site 公司网址
     */
    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    /**
     * 分店：面积（㎡）
     * @return acreage 分店：面积（㎡）
     */
    public BigDecimal getAcreage() {
        return acreage;
    }

    /**
     * 分店：面积（㎡）
     * @param acreage 分店：面积（㎡）
     */
    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }

    /**
     * 分店：月租（元）
     * @return monthly 分店：月租（元）
     */
    public BigDecimal getMonthly() {
        return monthly;
    }

    /**
     * 分店：月租（元）
     * @param monthly 分店：月租（元）
     */
    public void setMonthly(BigDecimal monthly) {
        this.monthly = monthly;
    }

    /**
     * 分店：开店日期
     * @return shop_date 分店：开店日期
     */
    public Date getShopDate() {
        return shopDate;
    }

    /**
     * 分店：开店日期
     * @param shopDate 分店：开店日期
     */
    public void setShopDate(Date shopDate) {
        this.shopDate = shopDate;
    }

    /**
     * 最近搬迁日期
     * @return relocation_date 最近搬迁日期
     */
    public Date getRelocationDate() {
        return relocationDate;
    }

    /**
     * 最近搬迁日期
     * @param relocationDate 最近搬迁日期
     */
    public void setRelocationDate(Date relocationDate) {
        this.relocationDate = relocationDate;
    }

    /**
     * 最近改造日期
     * @return reform_date 最近改造日期
     */
    public Date getReformDate() {
        return reformDate;
    }

    /**
     * 最近改造日期
     * @param reformDate 最近改造日期
     */
    public void setReformDate(Date reformDate) {
        this.reformDate = reformDate;
    }

    /**
     * 企业负责人ID
     * @return business_man_id 企业负责人ID
     */
    public Long getBusinessManId() {
        return businessManId;
    }

    /**
     * 企业负责人ID
     * @param businessManId 企业负责人ID
     */
    public void setBusinessManId(Long businessManId) {
        this.businessManId = businessManId;
    }

    /**
     * 法定代表人ID
     * @return legal_man_id 法定代表人ID
     */
    public Long getLegalManId() {
        return legalManId;
    }

    /**
     * 法定代表人ID
     * @param legalManId 法定代表人ID
     */
    public void setLegalManId(Long legalManId) {
        this.legalManId = legalManId;
    }

    /**
     * 质量负责人ID
     * @return quality_officer_id 质量负责人ID
     */
    public Long getQualityOfficerId() {
        return qualityOfficerId;
    }

    /**
     * 质量负责人ID
     * @param qualityOfficerId 质量负责人ID
     */
    public void setQualityOfficerId(Long qualityOfficerId) {
        this.qualityOfficerId = qualityOfficerId;
    }

    /**
     * 开户行名称
     * @return bank_name 开户行名称
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 开户行名称
     * @param bankName 开户行名称
     */
    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    /**
     * 开户行账号
     * @return bank_account 开户行账号
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * 开户行账号
     * @param bankAccount 开户行账号
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    /**
     * 开户户名
     * @return bank_account_name 开户户名
     */
    public String getBankAccountName() {
        return bankAccountName;
    }

    /**
     * 开户户名
     * @param bankAccountName 开户户名
     */
    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName == null ? null : bankAccountName.trim();
    }

    /**
     * 经营品种（0-药品；1-食品；2-化妆品；3-医疗器械；4-其它，多个用逗号分隔）
     * @return business_variety 经营品种（0-药品；1-食品；2-化妆品；3-医疗器械；4-其它，多个用逗号分隔）
     */
    public String getBusinessVariety() {
        return businessVariety;
    }

    /**
     * 经营品种（0-药品；1-食品；2-化妆品；3-医疗器械；4-其它，多个用逗号分隔）
     * @param businessVariety 经营品种（0-药品；1-食品；2-化妆品；3-医疗器械；4-其它，多个用逗号分隔）
     */
    public void setBusinessVariety(String businessVariety) {
        this.businessVariety = businessVariety == null ? null : businessVariety.trim();
    }

    /**
     * 分店：医保药店（0-否；1-是）
     * @return medical_flag 分店：医保药店（0-否；1-是）
     */
    public Integer getMedicalFlag() {
        return medicalFlag;
    }

    /**
     * 分店：医保药店（0-否；1-是）
     * @param medicalFlag 分店：医保药店（0-否；1-是）
     */
    public void setMedicalFlag(Integer medicalFlag) {
        this.medicalFlag = medicalFlag;
    }

    /**
     * 分店：医保机构编码
     * @return medical_code 分店：医保机构编码
     */
    public String getMedicalCode() {
        return medicalCode;
    }

    /**
     * 分店：医保机构编码
     * @param medicalCode 分店：医保机构编码
     */
    public void setMedicalCode(String medicalCode) {
        this.medicalCode = medicalCode == null ? null : medicalCode.trim();
    }

    /**
     * 公司简介
     * @return company_about 公司简介
     */
    public String getCompanyAbout() {
        return companyAbout;
    }

    /**
     * 公司简介
     * @param companyAbout 公司简介
     */
    public void setCompanyAbout(String companyAbout) {
        this.companyAbout = companyAbout == null ? null : companyAbout.trim();
    }

    /**
     * 经营范围ID（多个用逗号分隔）
     * @return business_scope_id 经营范围ID（多个用逗号分隔）
     */
    public String getBusinessScopeId() {
        return businessScopeId;
    }

    /**
     * 经营范围ID（多个用逗号分隔）
     * @param businessScopeId 经营范围ID（多个用逗号分隔）
     */
    public void setBusinessScopeId(String businessScopeId) {
        this.businessScopeId = businessScopeId == null ? null : businessScopeId.trim();
    }

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getSaleAreaId() {
		return saleAreaId;
	}

	public void setSaleAreaId(Long saleAreaId) {
		this.saleAreaId = saleAreaId;
	}

	public Long getSaleCircleId() {
		return saleCircleId;
	}

	public void setSaleCircleId(Long saleCircleId) {
		this.saleCircleId = saleCircleId;
	}

	public Long getStoreLevelId() {
		return storeLevelId;
	}

	public void setStoreLevelId(Long storeLevelId) {
		this.storeLevelId = storeLevelId;
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
	
	public List<Integer> getBusinessVarietyArray() {
		return businessVarietyArray;
	}

	public void setBusinessVarietyArray(List<Integer> businessVarietyArray) {
		this.businessVarietyArray = businessVarietyArray;
	}

	public List<Integer> getBusinessScopeIdArray() {
		return businessScopeIdArray;
	}

	public void setBusinessScopeIdArray(List<Integer> businessScopeIdArray) {
		this.businessScopeIdArray = businessScopeIdArray;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", chainType=").append(chainType);
        sb.append(", code=").append(code);
        sb.append(", pinyin=").append(pinyin);
        sb.append(", name=").append(name);
        sb.append(", economicType=").append(economicType);
        sb.append(", businessMode=").append(businessMode);
        sb.append(", registerMoney=").append(registerMoney);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", cityId=").append(cityId);
        sb.append(", areaId=").append(areaId);
        sb.append(", companyAddress=").append(companyAddress);
        sb.append(", postcode=").append(postcode);
        sb.append(", tel=").append(tel);
        sb.append(", fax=").append(fax);
        sb.append(", email=").append(email);
        sb.append(", site=").append(site);
        sb.append(", acreage=").append(acreage);
        sb.append(", monthly=").append(monthly);
        sb.append(", shopDate=").append(shopDate);
        sb.append(", relocationDate=").append(relocationDate);
        sb.append(", reformDate=").append(reformDate);
        sb.append(", businessManId=").append(businessManId);
        sb.append(", legalManId=").append(legalManId);
        sb.append(", qualityOfficerId=").append(qualityOfficerId);
        sb.append(", bankName=").append(bankName);
        sb.append(", bankAccount=").append(bankAccount);
        sb.append(", bankAccountName=").append(bankAccountName);
//        sb.append(", businessVariety=").append(businessVariety);
        sb.append(", medicalFlag=").append(medicalFlag);
        sb.append(", medicalCode=").append(medicalCode);
        sb.append(", companyAbout=").append(companyAbout);
        sb.append(", businessScopeId=").append(businessScopeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}