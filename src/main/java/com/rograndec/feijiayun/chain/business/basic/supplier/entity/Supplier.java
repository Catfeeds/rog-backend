package com.rograndec.feijiayun.chain.business.basic.supplier.entity;

import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierDetailVO;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Supplier implements Serializable {
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
     *  所属企业id
     */
    @ApiModelProperty(value = "所属企业id")
	private Long ownerId;

    /**
     * 药店类型（0-总部；1-自营店；2-加盟店）
     */
    @ApiModelProperty(value = "药店类型（0-总部；1-自营店；2-加盟店）")
    private Integer chainType;

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
     * 经济类型（0-国有经济；1-集体经济；2-私营经济；3-个体经济；4-联营经济；5-股份制；6-外商投资；7-港澳台投资；8-其它经济类）
     */
	@ApiModelProperty(value = "经济类型（0-国有经济；1-集体经济；2-私营经济；3-个体经济；4-联营经济；5-股份制；6-外商投资；7-港澳台投资；8-其它经济类）")
    private Integer economicType;

    /**
     * 经营方式（0-个人独自经营；1-合伙经营；2-有限责任公司；3-股份有限责任公司）
     */
	@ApiModelProperty(value = "经营方式（0-个人独自经营；1-合伙经营；2-有限责任公司；3-股份有限责任公司）")
    private Integer businessMode;

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
     * 纳税人识别号
     */
    @ApiModelProperty(value = "纳税人识别号")
    private String taxpayerCode;

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

    /**
     * 经营范围ID（多个用逗号分隔）
     */
	@ApiModelProperty(value = "经营范围ID（多个用逗号分隔）")
    private String businessScopeId;

    /**
     * 公司简介
     */
	@ApiModelProperty(value = "公司简介")
    private String supplierAbout;

    /**
     * 状态
     */
	@ApiModelProperty(value = "状态 0-禁用 1-启用")
    private Integer status;

    /**
     * 标识（0-作废；1-正常）
     */
	@ApiModelProperty(value = "标识（0-作废；1-正常）")
    private Integer validFlag;

    /**
     * 审批状态（0-待审核；1-审核通过；2-审核拒绝；-2-审核撤回；-1-审核中）
     */
    @ApiModelProperty(value = "审批状态（0-待审核；1-审核通过；2-审核拒绝；-2-审核撤回；-1-审核中） tips：0和 -1的情况不可以修改")
    private Integer approveStatus;

    /**
     * 备注
     */
	@ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建人ID
     */
	@ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
	@ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
	@ApiModelProperty(value = "创建人名称")
    private String createrName;

    /**
     * 创建时间
     */
	@ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
	@ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
	@ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
	@ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
	@ApiModelProperty(value = "更新时间")
    private Date updateTime;

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
     * 企业（总部）ID
     * @return enterprise_id 企业（总部）ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业（总部）ID
     * @param enterpriseId 企业（总部）ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 性质（0-批发企业；1-生产企业）
     * @return nature 性质（0-批发企业；1-生产企业）
     */
    public Integer getNature() {
        return nature;
    }

    /**
     * 性质（0-批发企业；1-生产企业）
     * @param nature 性质（0-批发企业；1-生产企业）
     */
    public void setNature(Integer nature) {
        this.nature = nature;
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
     * 分组ID
     * @return group_id 分组ID
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * 分组ID
     * @param groupId 分组ID
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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
     * @return registered_capital 注册资金（万元）
     */
    public BigDecimal getRegisteredCapital() {
        return registeredCapital;
    }

    /**
     * 注册资金（万元）
     * @param registeredCapital 注册资金（万元）
     */
    public void setRegisteredCapital(BigDecimal registeredCapital) {
        this.registeredCapital = registeredCapital;
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
     * 省名称
     * @return province_name 省名称
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 省名称
     * @param provinceName 省名称
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
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
     * 市名称
     * @return city_name 市名称
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 市名称
     * @param cityName 市名称
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
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
     * 区名称
     * @return area_name 区名称
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 区名称
     * @param areaName 区名称
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
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
     * 仓库地址
     * @return storage_address 仓库地址
     */
    public String getStorageAddress() {
        return storageAddress;
    }

    /**
     * 仓库地址
     * @param storageAddress 仓库地址
     */
    public void setStorageAddress(String storageAddress) {
        this.storageAddress = storageAddress == null ? null : storageAddress.trim();
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
     * 销售人员ID
     * @return sale_man_id 销售人员ID
     */
    public Long getSaleManId() {
        return saleManId;
    }

    /**
     * 销售人员ID
     * @param saleManId 销售人员ID
     */
    public void setSaleManId(Long saleManId) {
        this.saleManId = saleManId;
    }

    /**
     * 销售人员编码
     * @return sale_man_code 销售人员编码
     */
    public String getSaleManCode() {
        return saleManCode;
    }

    /**
     * 销售人员编码
     * @param saleManCode 销售人员编码
     */
    public void setSaleManCode(String saleManCode) {
        this.saleManCode = saleManCode == null ? null : saleManCode.trim();
    }

    /**
     * 销售人员名称
     * @return sale_man_name 销售人员名称
     */
    public String getSaleManName() {
        return saleManName;
    }

    /**
     * 销售人员名称
     * @param saleManName 销售人员名称
     */
    public void setSaleManName(String saleManName) {
        this.saleManName = saleManName == null ? null : saleManName.trim();
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
     * 企业负责人编码
     * @return business_man_code 企业负责人编码
     */
    public String getBusinessManCode() {
        return businessManCode;
    }

    /**
     * 企业负责人编码
     * @param businessManCode 企业负责人编码
     */
    public void setBusinessManCode(String businessManCode) {
        this.businessManCode = businessManCode == null ? null : businessManCode.trim();
    }

    /**
     * 企业负责人名称
     * @return business_man_name 企业负责人名称
     */
    public String getBusinessManName() {
        return businessManName;
    }

    /**
     * 企业负责人名称
     * @param businessManName 企业负责人名称
     */
    public void setBusinessManName(String businessManName) {
        this.businessManName = businessManName == null ? null : businessManName.trim();
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
     * 法定代表人编码
     * @return legal_man_code 法定代表人编码
     */
    public String getLegalManCode() {
        return legalManCode;
    }

    /**
     * 法定代表人编码
     * @param legalManCode 法定代表人编码
     */
    public void setLegalManCode(String legalManCode) {
        this.legalManCode = legalManCode == null ? null : legalManCode.trim();
    }

    /**
     * 法定代表人名称
     * @return legal_man_name 法定代表人名称
     */
    public String getLegalManName() {
        return legalManName;
    }

    /**
     * 法定代表人名称
     * @param legalManName 法定代表人名称
     */
    public void setLegalManName(String legalManName) {
        this.legalManName = legalManName == null ? null : legalManName.trim();
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
     * 质量负责人编码
     * @return quality_officer_code 质量负责人编码
     */
    public String getQualityOfficerCode() {
        return qualityOfficerCode;
    }

    /**
     * 质量负责人编码
     * @param qualityOfficerCode 质量负责人编码
     */
    public void setQualityOfficerCode(String qualityOfficerCode) {
        this.qualityOfficerCode = qualityOfficerCode == null ? null : qualityOfficerCode.trim();
    }

    /**
     * 质量负责人名称
     * @return quality_officer_name 质量负责人名称
     */
    public String getQualityOfficerName() {
        return qualityOfficerName;
    }

    /**
     * 质量负责人名称
     * @param qualityOfficerName 质量负责人名称
     */
    public void setQualityOfficerName(String qualityOfficerName) {
        this.qualityOfficerName = qualityOfficerName == null ? null : qualityOfficerName.trim();
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

    /**
     * 公司简介
     * @return supplier_about 公司简介
     */
    public String getSupplierAbout() {
        return supplierAbout;
    }

    /**
     * 公司简介
     * @param supplierAbout 公司简介
     */
    public void setSupplierAbout(String supplierAbout) {
        this.supplierAbout = supplierAbout == null ? null : supplierAbout.trim();
    }

    /**
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 标识（0-作废；1-正常）
     * @return valid_flag 标识（0-作废；1-正常）
     */
    public Integer getValidFlag() {
        return validFlag;
    }

    /**
     * 标识（0-作废；1-正常）
     * @param validFlag 标识（0-作废；1-正常）
     */
    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
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
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", nature=").append(nature);
        sb.append(", pinyin=").append(pinyin);
        sb.append(", groupId=").append(groupId);
        sb.append(", economicType=").append(economicType);
        sb.append(", businessMode=").append(businessMode);
        sb.append(", registeredCapital=").append(registeredCapital);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", provinceName=").append(provinceName);
        sb.append(", cityId=").append(cityId);
        sb.append(", cityName=").append(cityName);
        sb.append(", areaId=").append(areaId);
        sb.append(", areaName=").append(areaName);
        sb.append(", companyAddress=").append(companyAddress);
        sb.append(", storageAddress=").append(storageAddress);
        sb.append(", postcode=").append(postcode);
        sb.append(", tel=").append(tel);
        sb.append(", fax=").append(fax);
        sb.append(", email=").append(email);
        sb.append(", site=").append(site);
        sb.append(", saleManId=").append(saleManId);
        sb.append(", saleManCode=").append(saleManCode);
        sb.append(", saleManName=").append(saleManName);
        sb.append(", businessManId=").append(businessManId);
        sb.append(", businessManCode=").append(businessManCode);
        sb.append(", businessManName=").append(businessManName);
        sb.append(", legalManId=").append(legalManId);
        sb.append(", legalManCode=").append(legalManCode);
        sb.append(", legalManName=").append(legalManName);
        sb.append(", qualityOfficerId=").append(qualityOfficerId);
        sb.append(", qualityOfficerCode=").append(qualityOfficerCode);
        sb.append(", qualityOfficerName=").append(qualityOfficerName);
        sb.append(", bankName=").append(bankName);
        sb.append(", bankAccount=").append(bankAccount);
        sb.append(", bankAccountName=").append(bankAccountName);
        sb.append(", businessVariety=").append(businessVariety);
        sb.append(", businessScopeId=").append(businessScopeId);
        sb.append(", supplierAbout=").append(supplierAbout);
        sb.append(", status=").append(status);
        sb.append(", validFlag=").append(validFlag);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

	public static Supplier getSupplierAddSupplierVO(SupplierDetailVO supplierDetailVO, UserVO user, boolean b) {
		Supplier supplier = new Supplier();
		supplier.setId(supplierDetailVO.getId());
        Long headEnterpriseId = ChainType.getHeadEnterpriseId(user);
        supplier.setEnterpriseId(headEnterpriseId);
		supplier.setCode(supplierDetailVO.getCode());
		supplier.setName(supplierDetailVO.getName());
		supplier.setNature(supplierDetailVO.getNature());
		supplier.setPinyin(supplierDetailVO.getPinyin());
		supplier.setGroupId(supplierDetailVO.getGroupId());
		supplier.setEconomicType(supplierDetailVO.getEconomicType());
		supplier.setBusinessMode(supplierDetailVO.getBusinessMode());
		supplier.setRegisteredCapital(supplierDetailVO.getRegisteredCapital());
		supplier.setProvinceId(supplierDetailVO.getProvinceId());
		supplier.setProvinceName(supplierDetailVO.getProvinceName());
		supplier.setCityId(supplierDetailVO.getCityId());
		supplier.setCityName(supplierDetailVO.getCityName());
		supplier.setTaxpayerCode(supplierDetailVO.getTaxpayerCode());
		supplier.setAreaId(supplierDetailVO.getAreaId());
		supplier.setAreaName(supplierDetailVO.getAreaName());
		supplier.setCompanyAddress(supplierDetailVO.getCompanyAddress());
		supplier.setStorageAddress(supplierDetailVO.getStorageAddress());
		supplier.setPostcode(supplierDetailVO.getPostcode());
		supplier.setTel(supplierDetailVO.getTel());
		supplier.setFax(supplierDetailVO.getFax());
		supplier.setEmail(supplierDetailVO.getEmail());
		supplier.setSite(supplierDetailVO.getSite());
		supplier.setSaleManId(supplierDetailVO.getSaleManId());
		supplier.setSaleManCode(supplierDetailVO.getSaleManCode());
		supplier.setSaleManName(supplierDetailVO.getSaleManName());
		supplier.setBusinessManId(supplierDetailVO.getBusinessManId());
		supplier.setBusinessManCode(supplierDetailVO.getBusinessManCode());
		supplier.setBusinessManName(supplierDetailVO.getBusinessManName());
		supplier.setLegalManId(supplierDetailVO.getLegalManId());
		supplier.setLegalManCode(supplierDetailVO.getLegalManCode());
		supplier.setLegalManName(supplierDetailVO.getLegalManName());
		supplier.setQualityOfficerId(supplierDetailVO.getQualityOfficerId());
		supplier.setQualityOfficerCode(supplierDetailVO.getQualityOfficerCode());
		supplier.setQualityOfficerName(supplierDetailVO.getQualityOfficerName());
		supplier.setBankName(supplierDetailVO.getBankName());
		supplier.setBankAccount(supplierDetailVO.getBankAccount());
		supplier.setBankAccountName(supplierDetailVO.getBankAccountName());
		supplier.setBusinessVariety(supplierDetailVO.getBusinessVariety());
		supplier.setBusinessScopeId(supplierDetailVO.getBusinessScopeId());
		supplier.setSupplierAbout(supplierDetailVO.getSupplierAbout());
		supplier.setStatus(supplierDetailVO.getStatus());
		supplier.setValidFlag(supplierDetailVO.getValidFlag());
		supplier.setRemark(supplierDetailVO.getRemark());
		if (b){
			supplier.setCreaterId(user.getUserId());
			supplier.setCreaterCode(user.getUserCode());
			supplier.setCreaterName(user.getUserName());
			supplier.setCreateTime(new Date());
            supplier.setModifierId(user.getUserId());
            supplier.setModifierCode(user.getUserCode());
            supplier.setModifierName(user.getUserName());
            supplier.setUpdateTime(new Date());
		}else{
			supplier.setModifierId(user.getUserId());
			supplier.setModifierCode(user.getUserCode());
			supplier.setModifierName(user.getUserName());
			supplier.setUpdateTime(new Date());
		}
		
		return supplier;
	}
}