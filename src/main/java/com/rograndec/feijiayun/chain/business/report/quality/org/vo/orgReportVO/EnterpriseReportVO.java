package com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO;

import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.MedicalFlag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "enterprise", description = "企业基本信息对象")
public class EnterpriseReportVO implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value = "企业id")
    private Long id;
	


    /**
     * 上级企业ID，默认值为0
     */
	@ApiModelProperty(value = "企业父级id")
    private Long parentId;

    /**
     * 药店类型（0-总部；1-自营店；2-加盟店）
     */
	@ApiModelProperty(value = "药店类型（0-总部；1-自营店；2-加盟店）")
    private Integer chainType;

    @ApiModelProperty(value = "药店类型（0-总部；1-自营店；2-加盟店）")
	private String chainTypeName;
	

    /**
     * 编码
     */
	@ApiModelProperty(value = "组织机构编码")
    private String code;

    /**
     * 检索码
     */
	@ApiModelProperty(value = "检索码")
    private String pinyin;

    /**
     * 企业名称
     */
	@ApiModelProperty(value = "组织机构名称")
    private String name;

    /**
     * 门店分组
     */
	@ApiModelProperty(value = "门店分组ID")
    private Long groupId;
	
	 /**
     * 门店分组
     */
	@ApiModelProperty(value = "门店分组名称")
    private String groupName;

    /**
     * 销售片区
     */
	@ApiModelProperty(value = "销售片区ID")
    private Long saleAreaId;
	
	/**
     * 销售片区
     */
	@ApiModelProperty(value = "销售片区名称")
    private String saleAreaName;

    /**
     * 销售商圈
     */
	@ApiModelProperty(value = "销售商圈ID")
    private Long saleCircleId;
	
	/**
     * 销售商圈
     */
	@ApiModelProperty(value = "销售商圈名称")
    private String saleCircleName;
	
	/**
     * 门店级别
     */
	@ApiModelProperty(value = "门店级别ID")
    private Long storeLevelId;
	
	/**
     * 门店级别
     */
	@ApiModelProperty(value = "门店级别名称")
    private String storeLevelName;

    /**
     * 经济类型（0-国有经济；1-集体经济；2-私营经济；3-个体经济；4-联营经济；5-股份制；6-外商投资；7-港澳台投资；8-其它经济类）
     */
	@ApiModelProperty(value = "经济类型（0-国有经济；1-集体经济；2-私营经济；3-个体经济；4-联营经济；5-股份制；6-外商投资；7-港澳台投资；8-其它经济类）")
    private Integer economicType;
	
	/**
     * 经济类型名称
     */
	@ApiModelProperty(value = "经济类型名称")
    private String economicTypeName;

    /**
     * 经营方式（0-个人独自经营；1-合伙经营；2-有限责任公司；3-股份有限责任公司）
     */
	@ApiModelProperty(value = "经营方式（0-个人独自经营；1-合伙经营；2-有限责任公司；3-股份有限责任公司）")
    private Integer businessMode;
	
	/**
     * 经营方式名称
     */
	@ApiModelProperty(value = "经营方式名称")
    private String businessModeName;

    /**
     * 注册资金（万元）
     */
	@ApiModelProperty(value = "注册资金（万元）")
    private BigDecimal registerMoney;

    @ApiModelProperty(value = "行政区域")
	private String regionalism;

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
     * 区ID
     */
	@ApiModelProperty(value = "区ID")
    private Integer areaId;

    /**
     * 区名称
     */
	@ApiModelProperty(value = "区名称")
    private String areaName;

    /**
     * 公司地址
     */
	@ApiModelProperty(value = "公司地址")
    private String companyAddress;


    /**
     * 经营范围ID（多个用逗号分隔）
     */
    @ApiModelProperty(value = "经营范围ID（多个用逗号分隔）")
    private String businessScopeId;



    /**
     * 经营范围名称（多个用分号分隔）
     */
    @ApiModelProperty(value = "经营范围名称")
    private String businessScopeName;


    /**
     * 邮政编码
     */
    @ApiModelProperty(value = "邮政编码")
    private String postcode;



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
     * 分店：面积（㎡）
     */
	@ApiModelProperty(value = "分店：面积（㎡）")
    private BigDecimal acreage;

    /**
     * 分店：月租（元）
     */
	@ApiModelProperty(value = "分店：月租（元）")
    private BigDecimal monthly;



    /**
     * 分店：开店日期
     */
	@ApiModelProperty(value = "分店：开店日期")
    private Date shopDate;



    /**
     * 企业负责人ID
     */
	@ApiModelProperty(value = "企业负责人ID")
    private Long businessManId;


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
     * 经营品种（0-药品；1-食品；2-化妆品；3-医疗器械；4-其它，多个用逗号分隔）
     */
	@ApiModelProperty(value = "经营品种（0-药品；1-食品；2-化妆品；3-医疗器械；4-其它，多个用逗号分隔）")
    private String businessVarietyName;
	
    /**
     * 分店：医保药店（0-否；1-是）
     */
	@ApiModelProperty(value = "分店：是否为医保药店（0-否；1-是）")
    private Integer medicalFlag;

    @ApiModelProperty(value = "分店：是否为医保药店名称（0-否；1-是）")
	private String medicalFlagName;

    /**
     * 分店：医保机构编码
     */
    @ApiModelProperty(value = "分店：医保机构编码")
    private String medicalCode;


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


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getSaleAreaId() {
        return saleAreaId;
    }

    public void setSaleAreaId(Long saleAreaId) {
        this.saleAreaId = saleAreaId;
    }

    public String getSaleAreaName() {
        return saleAreaName;
    }

    public void setSaleAreaName(String saleAreaName) {
        this.saleAreaName = saleAreaName;
    }

    public Long getSaleCircleId() {
        return saleCircleId;
    }

    public void setSaleCircleId(Long saleCircleId) {
        this.saleCircleId = saleCircleId;
    }

    public String getSaleCircleName() {
        return saleCircleName;
    }

    public void setSaleCircleName(String saleCircleName) {
        this.saleCircleName = saleCircleName;
    }

    public Long getStoreLevelId() {
        return storeLevelId;
    }

    public void setStoreLevelId(Long storeLevelId) {
        this.storeLevelId = storeLevelId;
    }

    public String getStoreLevelName() {
        return storeLevelName;
    }

    public void setStoreLevelName(String storeLevelName) {
        this.storeLevelName = storeLevelName;
    }

    public Integer getEconomicType() {
        return economicType;
    }

    public void setEconomicType(Integer economicType) {
        this.economicType = economicType;
    }

    public String getEconomicTypeName() {
        return economicTypeName;
    }

    public void setEconomicTypeName(String economicTypeName) {
        this.economicTypeName = economicTypeName;
    }

    public Integer getBusinessMode() {
        return businessMode;
    }

    public void setBusinessMode(Integer businessMode) {
        this.businessMode = businessMode;
    }

    public String getBusinessModeName() {
        return businessModeName;
    }

    public void setBusinessModeName(String businessModeName) {
        this.businessModeName = businessModeName;
    }

    public BigDecimal getRegisterMoney() {
        return registerMoney;
    }

    public void setRegisterMoney(BigDecimal registerMoney) {
        this.registerMoney = registerMoney;
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



    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }

    public BigDecimal getMonthly() {
        return monthly;
    }

    public void setMonthly(BigDecimal monthly) {
        this.monthly = monthly;
    }

    public Date getShopDate() {
        return shopDate;
    }

    public void setShopDate(Date shopDate) {
        this.shopDate = shopDate;
    }

    public Long getBusinessManId() {
        return businessManId;
    }

    public void setBusinessManId(Long businessManId) {
        this.businessManId = businessManId;
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


    public String getBusinessVarietyName() {
        return businessVarietyName;
    }

    public void setBusinessVarietyName(String businessVarietyName) {
        this.businessVarietyName = businessVarietyName;
    }

    public Integer getMedicalFlag() {
        return medicalFlag;
    }

    public void setMedicalFlag(Integer medicalFlag) {
        this.medicalFlag = medicalFlag;
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

    public String getBusinessScopeId() {
        return businessScopeId;
    }

    public void setBusinessScopeId(String businessScopeId) {
        this.businessScopeId = businessScopeId;
    }

    public String getBusinessScopeName() {
        return businessScopeName;
    }

    public void setBusinessScopeName(String businessScopeName) {
        this.businessScopeName = businessScopeName;
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

    public String getMedicalCode() {
        return medicalCode;
    }

    public void setMedicalCode(String medicalCode) {
        this.medicalCode = medicalCode;
    }

    public String getRegionalism() {
        return regionalism;
    }

    public void setRegionalism(String regionalism) {
        this.regionalism = regionalism;
    }

    public String getChainTypeName() {
        return ChainType.getName(chainType);
    }

    public void setChainTypeName(String chainTypeName) {
        this.chainTypeName = chainTypeName;
    }

    public String getMedicalFlagName() {
        return MedicalFlag.getName(medicalFlag);
    }

    public void setMedicalFlagName(String medicalFlagName) {
        this.medicalFlagName = medicalFlagName;
    }
}