package com.rograndec.feijiayun.chain.business.report.quality.retail.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class RequestSpecialRegisterVO  implements Serializable {

	/**
     */
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty("快速搜索的条件（编码/条形码/商品名称/通用名称/批准文号）")
    private String param;
    /**
     *   0 '药品'1'医疗器械' 2  '食品' 3 '化妆品'4  '其它'
     */
    @ApiModelProperty(value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private Integer businessVariety;

    @ApiModelProperty(value = "当前页")
    private Integer pageNo;
    
    @ApiModelProperty(value = "每页显示数据")
    private Integer pageSize;
    
    @ApiModelProperty(value = "按日期排序  0倒序  1正序  默认0")
    private Integer dateOrder=0;
    @ApiModelProperty(value = "按单号排序  0倒序  1正序  默认0")
    private Integer codeOrder=0;
    
    private Long enterpriseId;
    
    private Integer start;
    
    /**
     * 起始日期
     */
    @ApiModelProperty(value = "划价日期(从)")
    private Date startDate;
    
    /**
     * 截至日期
     */
    @ApiModelProperty(value = "划价日期(至)")
    private Date endDate;
    
    /**
     * 组织机构类型
     */
    @ApiModelProperty("组织机构类型   0-总部；1-自营店；2-加盟店")
    private Integer chainType;
    
    /**
     * 
     */
    @ApiModelProperty("组织机构编码")
    private String enterpriseCode;
    
    /**
     * 
     */
    @ApiModelProperty("组织结构名称")
    private String enterpriseName;
    
    /**
     * 单据（专管登记单）编码
     */
    @ApiModelProperty(value = "登记单号")
    private String registerCode;
    /**
     * 登记人员名称
     */
    @ApiModelProperty(value = "登记人员")
    private String registerManName;
    
    /**
     * 会员卡号
     */
    @ApiModelProperty(value = "会员卡号")
    private String memberCardCode;

    /**
     * 购药者姓名
     */
    @ApiModelProperty(value = "购药者姓名")
    private String consumerName;
    
    /**
     * 状态,34-已取消  92-待收款 33-已完成
     */
    @ApiModelProperty(value = "状态(34-已取消 92-待收款 33-已完成)")
    private Integer status;
    
    /**
     * 商品分类ID
     */
    @ApiModelProperty("商品分类")
    private Long categoryId;
    

    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty("国产/进口（0-国产；1-进口）")
    private Integer domesticImport;
    
    /**
     * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻
     */
    @ApiModelProperty("贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）")
    private Integer storageTemp;
    
    /**
     * 贮藏条件名称
     */
    @ApiModelProperty("贮藏条件")
    private String storageConditionName;
    
    /**
     * 注册商标
     */
    @ApiModelProperty("注册商标")
    private String registeredTrademark;

    /**
     * 品牌
     */
    @ApiModelProperty("品牌")
    private String brand;

    /**
     * 商品属性（0-成药；1-中药材；2-中药饮片）
     */
    @ApiModelProperty("商品属性（0-成药；1-中药材；2-中药饮片）")
    private Integer goodsAttribute;

    /**
     * 商品属性-是否为处方药，0：非处方药，1：处方药
     */
    @ApiModelProperty("商品属性-是否为处方药，0：非处方药，1：处方药")
    private Integer prescriptionDrug;

    /**
     * 商品属性-非处方药类别：0-甲类；1-乙类
     */
    @ApiModelProperty("商品属性-非处方药类别：0-甲类；1-乙类")
    private Integer otcType;
    
    /**
     * 品种类别为3-化妆品，商品属性为1-特殊用途化妆品时，化妆品类别［0-育发；1-染发；2-烫发；3-脱毛；4-美乳；5-健美；6-除臭；7-祛斑；8-防嗮］
     */
    @ApiModelProperty(value="品种类别为3-化妆品，商品属性为1-特殊用途化妆品时，化妆品类别［0-育发；1-染发；2-烫发；3-脱毛；4-美乳；5-健美；6-除臭；7-祛斑；8-防嗮］")
    private Integer cosmetics;

    /**
     * 经营范围ID
     */
    @ApiModelProperty("经营范围ID")
    private Long managementScopeId;

    /**
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty("特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
    private Integer specialDrug;
    /**
     * 精神药品分类（0-一类；1-二类）
     */
    @ApiModelProperty("精神药品分类（0-一类；1-二类）")
    private Integer spiritDrugType;
    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty("专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
    private Integer inChargeDrug;
    /**
     *  含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）
     */
    @ApiModelProperty("含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）")
    private Integer formulationType;

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Integer dateOrder) {
		this.dateOrder = dateOrder;
	}

	public Integer getCodeOrder() {
		return codeOrder;
	}

	public void setCodeOrder(Integer codeOrder) {
		this.codeOrder = codeOrder;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getDomesticImport() {
		return domesticImport;
	}

	public void setDomesticImport(Integer domesticImport) {
		this.domesticImport = domesticImport;
	}

	public Integer getStorageTemp() {
		return storageTemp;
	}

	public void setStorageTemp(Integer storageTemp) {
		this.storageTemp = storageTemp;
	}

	public String getStorageConditionName() {
		return storageConditionName;
	}

	public void setStorageConditionName(String storageConditionName) {
		this.storageConditionName = storageConditionName;
	}

	public String getRegisteredTrademark() {
		return registeredTrademark;
	}

	public void setRegisteredTrademark(String registeredTrademark) {
		this.registeredTrademark = registeredTrademark;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getGoodsAttribute() {
		return goodsAttribute;
	}

	public void setGoodsAttribute(Integer goodsAttribute) {
		this.goodsAttribute = goodsAttribute;
	}

	public Integer getPrescriptionDrug() {
		return prescriptionDrug;
	}

	public void setPrescriptionDrug(Integer prescriptionDrug) {
		this.prescriptionDrug = prescriptionDrug;
	}

	public Integer getOtcType() {
		return otcType;
	}

	public void setOtcType(Integer otcType) {
		this.otcType = otcType;
	}

	public Long getManagementScopeId() {
		return managementScopeId;
	}

	public void setManagementScopeId(Long managementScopeId) {
		this.managementScopeId = managementScopeId;
	}

	public Integer getSpecialDrug() {
		return specialDrug;
	}

	public void setSpecialDrug(Integer specialDrug) {
		this.specialDrug = specialDrug;
	}

	public Integer getInChargeDrug() {
		return inChargeDrug;
	}

	public void setInChargeDrug(Integer inChargeDrug) {
		this.inChargeDrug = inChargeDrug;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getMemberCardCode() {
		return memberCardCode;
	}

	public void setMemberCardCode(String memberCardCode) {
		this.memberCardCode = memberCardCode;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getBusinessVariety() {
		return businessVariety;
	}

	public void setBusinessVariety(Integer businessVariety) {
		this.businessVariety = businessVariety;
	}

	public String getRegisterCode() {
		return registerCode;
	}

	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}

	public String getRegisterManName() {
		return registerManName;
	}

	public void setRegisterManName(String registerManName) {
		this.registerManName = registerManName;
	}

	public Integer getSpiritDrugType() {
		return spiritDrugType;
	}

	public void setSpiritDrugType(Integer spiritDrugType) {
		this.spiritDrugType = spiritDrugType;
	}

	public Integer getFormulationType() {
		return formulationType;
	}

	public void setFormulationType(Integer formulationType) {
		this.formulationType = formulationType;
	}

	public Integer getCosmetics() {
		return cosmetics;
	}

	public void setCosmetics(Integer cosmetics) {
		this.cosmetics = cosmetics;
	}
    
}
