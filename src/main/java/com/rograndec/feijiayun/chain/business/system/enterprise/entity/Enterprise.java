package com.rograndec.feijiayun.chain.business.system.enterprise.entity;

import com.rograndec.feijiayun.chain.business.auth.register.vo.RegisterEnterpriseVO;
import com.rograndec.feijiayun.chain.business.auth.register.vo.RegisterVO;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.utils.pinyin.PinYinUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ApiModel(value = "enterprise", description = "企业基本信息对象")
public class Enterprise implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value = "企业id")
    private Long id;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(value = "企业id")
    private Long enterpriseId;

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
	
	/**
     * 药店类型名称
     */
	@ApiModelProperty(value = "药店类型名称")
	private String chainTypeName;

    /**
     * 编码
     */
	@ApiModelProperty(value = "编码")
    private String code;

    /**
     * 检索码
     */
	@ApiModelProperty(value = "检索码")
    private String pinyin;

    /**
     * 企业名称
     */
	@ApiModelProperty(value = "企业名称")
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
     * 注册地址
     */
	@ApiModelProperty(value = "注册地址")
    private String registerAddress;

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
     * 分店：雇员人数
     */
	@ApiModelProperty(value = "分店：雇员人数")
    private Integer employeeNum;

    /**
     * 分店：开店日期
     */
	@ApiModelProperty(value = "分店：开店日期")
    private Date shopDate;

    /**
     * 最近搬迁日期
     */
	@ApiModelProperty(value = "最近搬迁日期")
    private Date relocationDate;

    /**
     * 最近改造日期
     */
	@ApiModelProperty(value = "最近改造日期")
    private Date reformDate;

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
     * 税收登记号
     */
	@ApiModelProperty(value = "税收登记号")
    private String taxCode;

    /**
     * 经营品种（0-药品；1-食品；2-化妆品；3-医疗器械；4-其它，多个用逗号分隔）
     */
	@ApiModelProperty(value = "经营品种（0-药品；1-食品；2-化妆品；3-医疗器械；4-其它，多个用逗号分隔）")
    private String businessVariety;
	
	/**
     * 经营品种数组
     */
    @ApiModelProperty(value = "经营品种数组")
    private List<Integer> businessVarietyArray;

	/**
     * 经营品种（0-药品；1-食品；2-化妆品；3-医疗器械；4-其它，多个用逗号分隔）
     */
	@ApiModelProperty(value = "经营品种（0-药品；1-食品；2-化妆品；3-医疗器械；4-其它，多个用逗号分隔）")
    private String businessVarietyName;
	
    /**
     * 分店：医保药店（0-否；1-是）
     */
	@ApiModelProperty(value = "分店：医保药店（0-否；1-是）")
    private Integer medicalFlag;
	
	/**
     * 分店：医保药店名称
     */
	@ApiModelProperty(value = "分店：医保药店名称")
    private String medicalFlagName;

    /**
     * 分店：医保机构编码
     */
	@ApiModelProperty(value = "分店：医保机构编码")
    private String medicalCode;

    /**
     * 公司简介
     */
	@ApiModelProperty(value = "公司简介")
    private String companyAbout;

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
     * 经营范围ID（多个用逗号分隔）
     */
    @ApiModelProperty(value = "经营范围ID（多个用逗号分隔）")
    private String businessScopeId;
    
    /**
     * 经营范围ID数组
     */
    @ApiModelProperty(value = "经营范围ID数组")
    private List<Integer> businessScopeIdArray;
    
    /**
     * 经营范围名称（多个用分号分隔）
     */
    @ApiModelProperty(value = "经营范围名称")
    private String businessScopeName;
    
    /**
	 * 管理员账号
	 */
	@ApiModelProperty(required = true, value = "管理员账号")
    private String userName;
	
	/**
	 * 登录密码
	 */
	@ApiModelProperty(required = true, value = "登录密码")
    private String passWord;
	
	/**
	 * 密码确认
	 */
	@ApiModelProperty(required = true, value = "密码确认")
    private String passWordConfirmation;
	
	/**
	 * 管理员手机号
	 */
	@ApiModelProperty(required = true, value = "管理员手机号")
    private String adminPhone;
	
	/**
	 * 管理员邮箱
	 */
	@ApiModelProperty(required = true, value = "管理员邮箱")
    private String adminEmail;
	
	/**
	 * 管理员用户ID
	 */
	private Long adminId;

    @ApiModelProperty(required = true, value = "管理员邮箱")
    private Integer rgtEnterpriseId;

    /**
     * 审核状态（0-待审核；1-审核通过；2-审核拒绝；-2-审核撤回；-1-审核中）
     */
    private Integer approveStatus;

	public static Enterprise initAfterSetUser(User chargeMan , User legalMan,User qualityOfficer){

        Enterprise enterprise = new Enterprise();

        /**
         * 企业负责人ID ,初始化是0 ,初始化过程中 初始化完企业负责人信息后再修改
         */
        enterprise.setBusinessManId(chargeMan.getId());

        /**
         * 企业负责人编码
         */
        enterprise.setBusinessManCode(chargeMan.getCode());

        /**
         * 企业负责人名称
         */
        enterprise.setBusinessManName(chargeMan.getName());

        /**
         * 法定代表人ID 初始化是0 ,初始化过程中 初始化完法定代表人信息后再修改
         */
        enterprise.setLegalManId(legalMan.getId());

        /**
         * 法定代表人编码
         */
        enterprise.setLegalManCode(legalMan.getCode());

        /**
         * 法定代表人名称
         */
        enterprise.setLegalManName(legalMan.getName());

        /**
         * 质量负责人ID 初始化是0 ,初始化过程中 初始化完质量负责人信息后再修改
         */
        enterprise.setQualityOfficerId(qualityOfficer.getId());

        /**
         * 质量负责人编码
         */
        enterprise.setQualityOfficerCode(qualityOfficer.getCode());

        /**
         * 质量负责人名称
         */
        enterprise.setQualityOfficerName(qualityOfficer.getName());

        return enterprise;
    }


    public static Enterprise initEnterprise(
            RegisterEnterpriseVO registerVO
            , List<Location> locations,String defScopId
    ){

        Enterprise enterprise = new Enterprise();

        /**
         * 经营品种
         */
        enterprise.setBusinessVariety("0,1,2,3,4");

        enterprise.setBusinessScopeId(defScopId);

        if(registerVO.getChainType() == ChainType.Headquarters.getCode()){

            /**
             * 企业只有系统生成,parentid都为0
             */
            enterprise.setParentId(0L);
        }else {
            enterprise.setParentId(registerVO.getParentId());
        }

        /**
         * 编码 默认值0000 保存完信息后再修改
         */
        UUID uuid = UUID.randomUUID();
        enterprise.setCode(uuid.toString());

        enterprise.setChainType(registerVO.getChainType());
        /**
         * 检索码
         */
        enterprise.setPinyin(PinYinUtils.getFirstSpell(registerVO.getEnterpriseName()));

        /**
         * 企业名称
         */
        enterprise.setName(registerVO.getEnterpriseName());

        for(Location location : locations){

            if(location.getId().equals(registerVO.getProvinceId())){
                /**
                 * 省ID
                 */
                enterprise.setProvinceId(location.getId());

                /**
                 * 省名称
                 */
                enterprise.setProvinceName(location.getName());

            }

            if(location.getId().equals(registerVO.getCityId())){
                /**
                 * 市ID
                 */
                enterprise.setCityId(location.getId());

                /**
                 * 市名称
                 */
                enterprise.setCityName(location.getName());
            }

            if(location.getId().equals(registerVO.getAreaId())){
                /**
                 * 区ID
                 */
                enterprise.setAreaId(location.getId());

                /**
                 * 区名称
                 */
                enterprise.setAreaName(location.getName());

            }
        }


        /**
         * 公司地址
         */
        enterprise.setCompanyAddress(registerVO.getAddress());

        /**
         * 注册地址
         */
        enterprise.setRegisterAddress(registerVO.getAddress());

        /**
         * 仓库地址
         */
        enterprise.setStorageAddress(registerVO.getWarehouseAddress());

        /**
         * 企业负责人ID ,初始化是0 ,初始化过程中 初始化完企业负责人信息后再修改
         */
        enterprise.setBusinessManId(0L);

        /**
         * 企业负责人编码
         */
        enterprise.setBusinessManCode("0000");

        /**
         * 企业负责人名称
         */
        enterprise.setBusinessManName(registerVO.getChargeMan());

        /**
         * 法定代表人ID 初始化是0 ,初始化过程中 初始化完法定代表人信息后再修改
         */
        enterprise.setLegalManId(0L);

        /**
         * 法定代表人编码
         */
        enterprise.setLegalManCode("0000");

        /**
         * 法定代表人名称
         */
        enterprise.setLegalManName(registerVO.getLegalMan());

        /**
         * 质量负责人ID 初始化是0 ,初始化过程中 初始化完质量负责人信息后再修改
         */
        enterprise.setQualityOfficerId(0L);

        /**
         * 质量负责人编码
         */
        enterprise.setQualityOfficerCode("0000");

        /**
         * 质量负责人名称
         */
        enterprise.setQualityOfficerName(registerVO.getQualityOfficer());


        enterprise.setStatus(EnableStatus.DISABLE.getStatus());
        enterprise.setCreaterId(0L);
        enterprise.setCreaterCode("0000");
        enterprise.setCreaterName("0000");
        enterprise.setCreateTime(new Date());
        enterprise.setModifierId(0L);
        enterprise.setModifierCode("0000");
        enterprise.setModifierName("0000");
        enterprise.setUpdateTime(new Date());
        //管理员id 为注册用户id
        enterprise.setAdminId(registerVO.getUserId());
        return enterprise;
    }


	public static Enterprise initEnterprise(
	        RegisterVO registerVO
            , List<Location> locations,String defScopId
            ){

        Enterprise enterprise = new Enterprise();


        /**
         * 企业只有系统生成,parentid都为0
         */
        enterprise.setParentId(0L);
        /**
         * 编码 默认值0000 保存完信息后再修改
         */
        enterprise.setCode("0000");

        enterprise.setBusinessScopeId("0");
        enterprise.setChainType(ChainType.Headquarters.getCode());
        /**
         * 检索码
         */
        enterprise.setPinyin(PinYinUtils.getFirstSpell(registerVO.getEnterpriseName()));

        /**
         * 企业名称
         */
        enterprise.setName(registerVO.getEnterpriseName());

        for(Location location : locations){

            if(location.getId().equals(registerVO.getProvinceId())){
                /**
                 * 省ID
                 */
                enterprise.setProvinceId(location.getId());

                /**
                 * 省名称
                 */
                enterprise.setProvinceName(location.getName());

            }

            if(location.getId().equals(registerVO.getCityId())){
                /**
                 * 市ID
                 */
                enterprise.setCityId(location.getId());

                /**
                 * 市名称
                 */
                enterprise.setCityName(location.getName());
            }

            if(location.getId().equals(registerVO.getAreaId())){
                /**
                 * 区ID
                 */
                enterprise.setAreaId(location.getId());

                /**
                 * 区名称
                 */
                enterprise.setAreaName(location.getName());

            }
        }


        /**
         * 公司地址
         */
        enterprise.setCompanyAddress(registerVO.getAddress());

        /**
         * 注册地址
         */
        enterprise.setRegisterAddress(registerVO.getAddress());

        /**
         * 仓库地址
         */
        enterprise.setStorageAddress(registerVO.getWarehouseAddress());

        /**
         * 企业负责人ID ,初始化是0 ,初始化过程中 初始化完企业负责人信息后再修改
         */
        enterprise.setBusinessManId(0L);

        /**
         * 企业负责人编码
         */
        enterprise.setBusinessManCode("0000");

        /**
         * 企业负责人名称
         */
        enterprise.setBusinessManName(registerVO.getChargeMan());

        /**
         * 法定代表人ID 初始化是0 ,初始化过程中 初始化完法定代表人信息后再修改
         */
        enterprise.setLegalManId(0L);

        /**
         * 法定代表人编码
         */
        enterprise.setLegalManCode("0000");

        /**
         * 法定代表人名称
         */
        enterprise.setLegalManName(registerVO.getLegalMan());

        /**
         * 质量负责人ID 初始化是0 ,初始化过程中 初始化完质量负责人信息后再修改
         */
        enterprise.setQualityOfficerId(0L);

        /**
         * 质量负责人编码
         */
        enterprise.setQualityOfficerCode("000000");

        /**
         * 质量负责人名称
         */
        enterprise.setQualityOfficerName(registerVO.getQualityOfficer());

        enterprise.setStatus(EnableStatus.DISABLE.getStatus());
        enterprise.setCreateTime(new Date());
        enterprise.setUpdateTime(new Date());
	    return enterprise;
    }

	public static List<Long> getIds(List<Enterprise> enterprises){
        List<Long> ids = new ArrayList<>();

        for(Enterprise enterprise : enterprises){
            ids.add(enterprise.getId());
        }
        return ids;
    }

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
     * 门店分组
     * @return group_id 门店分组
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * 门店分组
     * @param groupId 门店分组
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * 销售片区
     * @return sale_area_id 销售片区
     */
    public Long getSaleAreaId() {
        return saleAreaId;
    }

    /**
     * 销售片区
     * @param saleAreaId 销售片区
     */
    public void setSaleAreaId(Long saleAreaId) {
        this.saleAreaId = saleAreaId;
    }

    /**
     * 销售商圈
     * @return sale_circle_id 销售商圈
     */
    public Long getSaleCircleId() {
        return saleCircleId;
    }

    /**
     * 销售商圈
     * @param saleCircleId 销售商圈
     */
    public void setSaleCircleId(Long saleCircleId) {
        this.saleCircleId = saleCircleId;
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
     * 注册地址
     * @return register_address 注册地址
     */
    public String getRegisterAddress() {
        return registerAddress;
    }

    /**
     * 注册地址
     * @param registerAddress 注册地址
     */
    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress == null ? null : registerAddress.trim();
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
     * 分店：雇员人数
     * @return employee_num 分店：雇员人数
     */
    public Integer getEmployeeNum() {
        return employeeNum;
    }

    /**
     * 分店：雇员人数
     * @param employeeNum 分店：雇员人数
     */
    public void setEmployeeNum(Integer employeeNum) {
        this.employeeNum = employeeNum;
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
     * 税收登记号
     * @return tax_code 税收登记号
     */
    public String getTaxCode() {
        return taxCode;
    }

    /**
     * 税收登记号
     * @param taxCode 税收登记号
     */
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode == null ? null : taxCode.trim();
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
     * 状态（0-禁用；1-启用）
     * @return status 状态（0-禁用；1-启用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @param status 状态（0-禁用；1-启用）
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

    public Long getStoreLevelId() {
		return storeLevelId;
	}

	public void setStoreLevelId(Long storeLevelId) {
		this.storeLevelId = storeLevelId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPassWordConfirmation() {
		return passWordConfirmation;
	}

	public void setPassWordConfirmation(String passWordConfirmation) {
		this.passWordConfirmation = passWordConfirmation;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	
	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	
	public String getChainTypeName() {
		return chainTypeName;
	}

	public void setChainTypeName(String chainTypeName) {
		this.chainTypeName = chainTypeName;
	}
	
	public String getEconomicTypeName() {
		return economicTypeName;
	}

	public void setEconomicTypeName(String economicTypeName) {
		this.economicTypeName = economicTypeName;
	}

	public String getBusinessModeName() {
		return businessModeName;
	}

	public void setBusinessModeName(String businessModeName) {
		this.businessModeName = businessModeName;
	}

	public String getMedicalFlagName() {
		return medicalFlagName;
	}

	public void setMedicalFlagName(String medicalFlagName) {
		this.medicalFlagName = medicalFlagName;
	}

	public String getBusinessScopeName() {
		return businessScopeName;
	}

	public void setBusinessScopeName(String businessScopeName) {
		this.businessScopeName = businessScopeName;
	}
	
	public String getSaleAreaName() {
		return saleAreaName;
	}

	public void setSaleAreaName(String saleAreaName) {
		this.saleAreaName = saleAreaName;
	}

	public String getSaleCircleName() {
		return saleCircleName;
	}

	public void setSaleCircleName(String saleCircleName) {
		this.saleCircleName = saleCircleName;
	}

	public String getStoreLevelName() {
		return storeLevelName;
	}

	public void setStoreLevelName(String storeLevelName) {
		this.storeLevelName = storeLevelName;
	}
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public List<Integer> getBusinessScopeIdArray() {
		return businessScopeIdArray;
	}

	public void setBusinessScopeIdArray(List<Integer> businessScopeIdArray) {
		this.businessScopeIdArray = businessScopeIdArray;
	}
	
	public String getBusinessVarietyName() {
		return businessVarietyName;
	}

	public void setBusinessVarietyName(String businessVarietyName) {
		this.businessVarietyName = businessVarietyName;
	}
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public List<Integer> getBusinessVarietyArray() {
		return businessVarietyArray;
	}

	public void setBusinessVarietyArray(List<Integer> businessVarietyArray) {
		this.businessVarietyArray = businessVarietyArray;
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
        sb.append(", groupId=").append(groupId);
        sb.append(", saleAreaId=").append(saleAreaId);
        sb.append(", saleCircleId=").append(saleCircleId);
        sb.append(", storeLevelId=").append(storeLevelId);
        sb.append(", economicType=").append(economicType);
        sb.append(", businessMode=").append(businessMode);
        sb.append(", registerMoney=").append(registerMoney);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", provinceName=").append(provinceName);
        sb.append(", cityId=").append(cityId);
        sb.append(", cityName=").append(cityName);
        sb.append(", areaId=").append(areaId);
        sb.append(", areaName=").append(areaName);
        sb.append(", companyAddress=").append(companyAddress);
        sb.append(", registerAddress=").append(registerAddress);
        sb.append(", storageAddress=").append(storageAddress);
        sb.append(", postcode=").append(postcode);
        sb.append(", tel=").append(tel);
        sb.append(", fax=").append(fax);
        sb.append(", email=").append(email);
        sb.append(", site=").append(site);
        sb.append(", acreage=").append(acreage);
        sb.append(", monthly=").append(monthly);
        sb.append(", employeeNum=").append(employeeNum);
        sb.append(", shopDate=").append(shopDate);
        sb.append(", relocationDate=").append(relocationDate);
        sb.append(", reformDate=").append(reformDate);
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
        sb.append(", taxCode=").append(taxCode);
        sb.append(", businessVariety=").append(businessVariety);
        sb.append(", medicalFlag=").append(medicalFlag);
        sb.append(", medicalCode=").append(medicalCode);
        sb.append(", companyAbout=").append(companyAbout);
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
        sb.append(", businessScopeId=").append(businessScopeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Integer getRgtEnterpriseId() {
        return rgtEnterpriseId;
    }

    public void setRgtEnterpriseId(Integer rgtEnterpriseId) {
        this.rgtEnterpriseId = rgtEnterpriseId;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }
}