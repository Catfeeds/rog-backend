package com.rograndec.feijiayun.chain.business.goods.info.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品说明书
 * Created by ST on 2017/9/8.
 */
public class GoodsInstructionsVO implements Serializable {

    private Long id;
    /**
     * 商品ID
     */
    private Long goodsId;

    //说明书
    /**
     * 核准日期
     */
    @ApiModelProperty(value="核准日期")
    private Date approvalDate;

    /**
     * 修改日期
     */
    @ApiModelProperty(value="修改日期")
    private Date updateTime;

    /**
     * 汉语拼音
     */
    @ApiModelProperty(value="汉语拼音")
    private String chPinyin;

    /**
     * 英文名称
     */
    @ApiModelProperty(value="英文名称")
    private String englishName;

    /**
     * 成分
     */
    @ApiModelProperty(value="成分")
    private String component;

    /**
     * 性状
     */
    @ApiModelProperty(value="性状")
    private String properties;

    /**
     * 适应症
     */
    @ApiModelProperty(value="适应症")
    private String indication;

    /**
     * 用法用量
     */
    @ApiModelProperty(value="用法用量")
    private String usageDosage;

    /**
     * 不良反应
     */
    @ApiModelProperty(value="不良反应")
    private String adverseReaction;

    /**
     * 禁忌
     */
    @ApiModelProperty(value="禁忌")
    private String taboo;

    /**
     * 注意事项
     */
    @ApiModelProperty(value=" 注意事项")
    private String notice;

    /**
     * 孕妇及哺乳期妇女用药
     */
    @ApiModelProperty(value=" 孕妇及哺乳期妇女用药")
    private String womenMedication;

    /**
     * 儿童用药
     */
    @ApiModelProperty(value="儿童用药")
    private String childrenMedication;

    /**
     * 老年用药
     */
    @ApiModelProperty(value="老年用药")
    private String senileMedication;

    /**
     * 药物和相互作用
     */
    @ApiModelProperty(value="药物和相互作用")
    private String drugsInteractions;

    /**
     * 用药过量
     */
    @ApiModelProperty(value="用药过量")
    private String overdose;

    /**
     * 药理毒理
     */
    @ApiModelProperty(value="药理毒理")
    private String pharmacologyToxicology;

    /**
     * 药代动力学
     */
    @ApiModelProperty(value="药代动力学")
    private String pharmacokinetics;

    /**
     * 包装
     */
    @ApiModelProperty(value="包装")
    private String packing;

    /**
     * 执行标准
     */
    @ApiModelProperty(value="执行标准")
    private String operativeNorm;

    /**
     * 上市企业
     */
    @ApiModelProperty(value="上市企业")
    private String listedCompany;

    /**
     * 生产企业地址
     */
    @ApiModelProperty(value="生产企业地址")
    private String manufacturerAddress;

    /**
     * 分装企业
     */
    @ApiModelProperty(value="分装企业")
    private String repackingEnterprise;

    /**
     * 分装企业地址
     */
    @ApiModelProperty(value="分装企业地址")
    private String repackingEnterpriseAddress;

    /**
     * 电话
     */
    @ApiModelProperty(value="电话")
    private String telephone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
     * 邮政编码
     */
    @ApiModelProperty(value="邮政编码")
    private String postalCode;

    /**
     * 传真
     */
    @ApiModelProperty(value="传真")
    private String fax;

    /**
     * 产品咨询电话
     */
    @ApiModelProperty(value="产品咨询电话")
    private String productTelephone;

    /**
     * 网址
     */
    @ApiModelProperty(value="网址")
    private String website;

    /**
     * 附件ID集合，多个用“，”分隔
     */
    @ApiModelProperty(value="附件ID集合，多个用“，”分隔")
    private String fileids;




    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}