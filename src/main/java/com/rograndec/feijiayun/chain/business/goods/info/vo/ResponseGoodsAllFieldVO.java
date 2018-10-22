package com.rograndec.feijiayun.chain.business.goods.info.vo;


import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品所有信息查询返回实体VO
 * Created by ST on 2017/9/5.
 */
public class ResponseGoodsAllFieldVO extends ResponseGoodsVO{

    @ApiModelProperty("商品资质配置信息")
    private List<GoodsQualificationConfigVO> goodsQualificationConfigVOList;

    public List<GoodsQualificationConfigVO> getGoodsQualificationConfigVOList() {
        return goodsQualificationConfigVOList;
    }

    public void setGoodsQualificationConfigVOList(List<GoodsQualificationConfigVO> goodsQualificationConfigVOList) {
        this.goodsQualificationConfigVOList = goodsQualificationConfigVOList;
    }

    /**
     * 核准日期
     */
    @ApiModelProperty("核准日期")
    private Date approvalDate;

    /**
     * 汉语拼音
     */
    @ApiModelProperty("汉语拼音")
    private String chPinyin;

    /**
     * 英文名称
     */
    @ApiModelProperty("英文名称")
    private String englishName;

    /**
     * 成分
     */
    @ApiModelProperty("成分")
    private String component;

    /**
     * 性状
     */
    @ApiModelProperty("性状")
    private String properties;

    /**
     * 适应症
     */
    @ApiModelProperty("适应症")
    private String indication;

    /**
     * 用法用量
     */
    @ApiModelProperty("用法用量")
    private String usageDosage;

    /**
     * 不良反应
     */
    @ApiModelProperty("不良反应")
    private String adverseReaction;

    /**
     * 禁忌
     */
    @ApiModelProperty("禁忌")
    private String taboo;

    /**
     * 注意事项
     */
    @ApiModelProperty("注意事项")
    private String notice;

    /**
     * 孕妇及哺乳期妇女用药
     */
    @ApiModelProperty("孕妇及哺乳期妇女用药")
    private String womenMedication;

    /**
     * 儿童用药
     */
    @ApiModelProperty("儿童用药")
    private String childrenMedication;

    /**
     * 老年用药
     */
    @ApiModelProperty("老年用药")
    private String senileMedication;

    /**
     * 药物和相互作用
     */
    @ApiModelProperty("药物和相互作用")
    private String drugsInteractions;

    /**
     * 用药过量
     */
    @ApiModelProperty("用药过量")
    private String overdose;

    /**
     * 药理毒理
     */
    @ApiModelProperty("药理毒理")
    private String pharmacologyToxicology;

    /**
     * 药代动力学
     */
    @ApiModelProperty("药代动力学")
    private String pharmacokinetics;

    /**
     * 包装
     */
    @ApiModelProperty("包装")
    private String packing;

    /**
     * 执行标准
     */
    @ApiModelProperty("执行标准")
    private String operativeNorm;

    /**
     * 上市企业
     */
    @ApiModelProperty("上市企业")
    private String listedCompany;

    /**
     * 生产企业地址
     */
    @ApiModelProperty("生产企业地址")
    private String manufacturerAddress;

    /**
     * 分装企业
     */
    @ApiModelProperty("分装企业")
    private String repackingEnterprise;

    /**
     * 分装企业地址
     */
    @ApiModelProperty("分装企业地址")
    private String repackingEnterpriseAddress;

    /**
     * 电话
     */
    @ApiModelProperty("电话")
    private String telephone;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 邮政编码
     */
    @ApiModelProperty(" 邮政编码")
    private String postalCode;

    /**
     * 传真
     */
    @ApiModelProperty(" 传真")
    private String fax;

    /**
     * 产品咨询电话
     */
    @ApiModelProperty("产品咨询电话")
    private String productTelephone;

    /**
     * 网址
     */
    @ApiModelProperty("网址")
    private String website;

    /**
     * 附件ID集合，多个用“，”分隔
     */
    @ApiModelProperty("附件ID集合，多个用“，”分隔")
    private String fileids;


    /**
     * 提成商品（0-否；1-是）
     */
    @ApiModelProperty("提成商品（0-否；1-是）")
    private Integer commissionGoods;

    /**
     *  配货税率（%）
     */
    @ApiModelProperty("配货税率（%）")
    private BigDecimal distrTaxRate;


    /**
     * 标准库ID
     */
    @ApiModelProperty("标准库ID")
    private Long standardLibraryId;

    /**
     * 企业（总部）ID
     */
    @ApiModelProperty("企业（总部）ID")
    private Long enterpriseId;

    /**
     * MPH编码
     */
    @ApiModelProperty("MPH编码")
    private String mphCode;


    /**
     * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    @ApiModelProperty("贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）")
    private Integer storageTemp;



    /**
     * 商品图片ID
     */
    @ApiModelProperty("商品图片ID")
    private Long pictureIds;

    /**
     * 商品备注
     */
    @ApiModelProperty("商品备注")
    private String remark;


    /**
     * 小包装长度（cm）
     */
    @ApiModelProperty("小包装长度（cm）")
    private BigDecimal smallbagLength;

    /**
     * 小包装宽度（cm）
     */
    @ApiModelProperty("小包装宽度（cm）")
    private BigDecimal smallbagWidth;

    /**
     * 小包装高度（cm）
     */
    @ApiModelProperty("小包装高度（cm）")
    private BigDecimal smallbagHeight;

    /**
     * 小包装体积（cm³）
     */
    @ApiModelProperty("小包装体积（cm³）")
    private BigDecimal smallbagVolume;

    /**
     * 小包装重量（g）
     */
    @ApiModelProperty("小包装重量（g）")
    private BigDecimal smallbagWeight;

    /**
     * 中包装长度（cm）
     */
    @ApiModelProperty("中包装长度（cm）")
    private BigDecimal inbagLength;

    /**
     * 中包装宽度（cm）
     */
    @ApiModelProperty("中包装宽度（cm）")
    private BigDecimal inbagWidth;

    /**
     * 中包装高度（cm）
     */
    @ApiModelProperty("中包装高度（cm）")
    private BigDecimal inbagHeight;

    /**
     * 中包装体积（cm³）
     */
    @ApiModelProperty("中包装体积（cm³）")
    private BigDecimal inbagVolume;

    /**
     * 中包装重量（g）
     */
    @ApiModelProperty("中包装重量（g）")
    private BigDecimal inbagWeight;

    /**
     * 大包装长度（cm）
     */
    @ApiModelProperty("大包装长度（cm）")
    private BigDecimal bigbagLength;

    /**
     * 大包装宽度（cm）
     */
    @ApiModelProperty("大包装宽度（cm）")
    private BigDecimal bigbagWidth;

    /**
     * 大包装高度（cm）
     */
    @ApiModelProperty("大包装高度（cm）")
    private BigDecimal bigbagHeight;

    /**
     * 大包装体积（cm³）
     */
    @ApiModelProperty("大包装体积（cm³）")
    private BigDecimal bigbagVolume;

    /**
     * 大包装重量（g）
     */
    @ApiModelProperty("大包装重量（g）")
    private BigDecimal bigbagWeight;



    /**
     * 补货政策（0-动态存量补货；1-安全库存补货；2-人工补货；3-禁止补货）
     */
    @ApiModelProperty("补货政策（0-动态存量补货；1-安全库存补货；2-人工补货；3-禁止补货）")
    private Integer replenishmentPolicy;

    private Date updateTime;


    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getChPinyin() {
        return chPinyin;
    }

    public void setChPinyin(String chPinyin) {
        this.chPinyin = chPinyin;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getUsageDosage() {
        return usageDosage;
    }

    public void setUsageDosage(String usageDosage) {
        this.usageDosage = usageDosage;
    }

    public String getAdverseReaction() {
        return adverseReaction;
    }

    public void setAdverseReaction(String adverseReaction) {
        this.adverseReaction = adverseReaction;
    }

    public String getTaboo() {
        return taboo;
    }

    public void setTaboo(String taboo) {
        this.taboo = taboo;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getWomenMedication() {
        return womenMedication;
    }

    public void setWomenMedication(String womenMedication) {
        this.womenMedication = womenMedication;
    }

    public String getChildrenMedication() {
        return childrenMedication;
    }

    public void setChildrenMedication(String childrenMedication) {
        this.childrenMedication = childrenMedication;
    }

    public String getSenileMedication() {
        return senileMedication;
    }

    public void setSenileMedication(String senileMedication) {
        this.senileMedication = senileMedication;
    }

    public String getDrugsInteractions() {
        return drugsInteractions;
    }

    public void setDrugsInteractions(String drugsInteractions) {
        this.drugsInteractions = drugsInteractions;
    }

    public String getOverdose() {
        return overdose;
    }

    public void setOverdose(String overdose) {
        this.overdose = overdose;
    }

    public String getPharmacologyToxicology() {
        return pharmacologyToxicology;
    }

    public void setPharmacologyToxicology(String pharmacologyToxicology) {
        this.pharmacologyToxicology = pharmacologyToxicology;
    }

    public String getPharmacokinetics() {
        return pharmacokinetics;
    }

    public void setPharmacokinetics(String pharmacokinetics) {
        this.pharmacokinetics = pharmacokinetics;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getOperativeNorm() {
        return operativeNorm;
    }

    public void setOperativeNorm(String operativeNorm) {
        this.operativeNorm = operativeNorm;
    }

    public String getListedCompany() {
        return listedCompany;
    }

    public void setListedCompany(String listedCompany) {
        this.listedCompany = listedCompany;
    }

    public String getManufacturerAddress() {
        return manufacturerAddress;
    }

    public void setManufacturerAddress(String manufacturerAddress) {
        this.manufacturerAddress = manufacturerAddress;
    }

    public String getRepackingEnterprise() {
        return repackingEnterprise;
    }

    public void setRepackingEnterprise(String repackingEnterprise) {
        this.repackingEnterprise = repackingEnterprise;
    }

    public String getRepackingEnterpriseAddress() {
        return repackingEnterpriseAddress;
    }

    public void setRepackingEnterpriseAddress(String repackingEnterpriseAddress) {
        this.repackingEnterpriseAddress = repackingEnterpriseAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getProductTelephone() {
        return productTelephone;
    }

    public void setProductTelephone(String productTelephone) {
        this.productTelephone = productTelephone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFileids() {
        return fileids;
    }

    public void setFileids(String fileids) {
        this.fileids = fileids;
    }

    public Integer getCommissionGoods() {
        return commissionGoods;
    }

    public void setCommissionGoods(Integer commissionGoods) {
        this.commissionGoods = commissionGoods;
    }

    public BigDecimal getDistrTaxRate() {
        return distrTaxRate;
    }

    public void setDistrTaxRate(BigDecimal distrTaxRate) {
        this.distrTaxRate = distrTaxRate;
    }

    public Long getStandardLibraryId() {
        return standardLibraryId;
    }

    public void setStandardLibraryId(Long standardLibraryId) {
        this.standardLibraryId = standardLibraryId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getMphCode() {
        return mphCode;
    }

    public void setMphCode(String mphCode) {
        this.mphCode = mphCode;
    }

    public Integer getStorageTemp() {
        return storageTemp;
    }

    public void setStorageTemp(Integer storageTemp) {
        this.storageTemp = storageTemp;
    }


    public Long getPictureIds() {
        return pictureIds;
    }

    public void setPictureIds(Long pictureIds) {
        this.pictureIds = pictureIds;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getSmallbagLength() {
        return smallbagLength;
    }

    public void setSmallbagLength(BigDecimal smallbagLength) {
        this.smallbagLength = smallbagLength;
    }

    public BigDecimal getSmallbagWidth() {
        return smallbagWidth;
    }

    public void setSmallbagWidth(BigDecimal smallbagWidth) {
        this.smallbagWidth = smallbagWidth;
    }

    public BigDecimal getSmallbagHeight() {
        return smallbagHeight;
    }

    public void setSmallbagHeight(BigDecimal smallbagHeight) {
        this.smallbagHeight = smallbagHeight;
    }

    public BigDecimal getSmallbagVolume() {
        return smallbagVolume;
    }

    public void setSmallbagVolume(BigDecimal smallbagVolume) {
        this.smallbagVolume = smallbagVolume;
    }

    public BigDecimal getSmallbagWeight() {
        return smallbagWeight;
    }

    public void setSmallbagWeight(BigDecimal smallbagWeight) {
        this.smallbagWeight = smallbagWeight;
    }

    public BigDecimal getInbagLength() {
        return inbagLength;
    }

    public void setInbagLength(BigDecimal inbagLength) {
        this.inbagLength = inbagLength;
    }

    public BigDecimal getInbagWidth() {
        return inbagWidth;
    }

    public void setInbagWidth(BigDecimal inbagWidth) {
        this.inbagWidth = inbagWidth;
    }

    public BigDecimal getInbagHeight() {
        return inbagHeight;
    }

    public void setInbagHeight(BigDecimal inbagHeight) {
        this.inbagHeight = inbagHeight;
    }

    public BigDecimal getInbagVolume() {
        return inbagVolume;
    }

    public void setInbagVolume(BigDecimal inbagVolume) {
        this.inbagVolume = inbagVolume;
    }

    public BigDecimal getInbagWeight() {
        return inbagWeight;
    }

    public void setInbagWeight(BigDecimal inbagWeight) {
        this.inbagWeight = inbagWeight;
    }

    public BigDecimal getBigbagLength() {
        return bigbagLength;
    }

    public void setBigbagLength(BigDecimal bigbagLength) {
        this.bigbagLength = bigbagLength;
    }

    public BigDecimal getBigbagWidth() {
        return bigbagWidth;
    }

    public void setBigbagWidth(BigDecimal bigbagWidth) {
        this.bigbagWidth = bigbagWidth;
    }

    public BigDecimal getBigbagHeight() {
        return bigbagHeight;
    }

    public void setBigbagHeight(BigDecimal bigbagHeight) {
        this.bigbagHeight = bigbagHeight;
    }

    public BigDecimal getBigbagVolume() {
        return bigbagVolume;
    }

    public void setBigbagVolume(BigDecimal bigbagVolume) {
        this.bigbagVolume = bigbagVolume;
    }

    public BigDecimal getBigbagWeight() {
        return bigbagWeight;
    }

    public void setBigbagWeight(BigDecimal bigbagWeight) {
        this.bigbagWeight = bigbagWeight;
    }

    public Integer getReplenishmentPolicy() {
        return replenishmentPolicy;
    }

    public void setReplenishmentPolicy(Integer replenishmentPolicy) {
        this.replenishmentPolicy = replenishmentPolicy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}