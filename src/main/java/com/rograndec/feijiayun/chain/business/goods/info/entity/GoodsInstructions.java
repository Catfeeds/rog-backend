package com.rograndec.feijiayun.chain.business.goods.info.entity;

import java.io.Serializable;
import java.util.Date;

public class GoodsInstructions implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 企业（总部）ID
     */
    private Long enterpriseId;



    /**
     * 核准日期
     */
    private Date approvalDate;

    /**
     * 汉语拼音
     */
    private String chPinyin;

    /**
     * 英文名称
     */
    private String englishName;

    /**
     * 成分
     */
    private String component;

    /**
     * 性状
     */
    private String properties;

    /**
     * 适应症
     */
    private String indication;

    /**
     * 用法用量
     */
    private String usageDosage;

    /**
     * 不良反应
     */
    private String adverseReaction;

    /**
     * 禁忌
     */
    private String taboo;

    /**
     * 注意事项
     */
    private String notice;

    /**
     * 孕妇及哺乳期妇女用药
     */
    private String womenMedication;

    /**
     * 儿童用药
     */
    private String childrenMedication;

    /**
     * 老年用药
     */
    private String senileMedication;

    /**
     * 药物和相互作用
     */
    private String drugsInteractions;

    /**
     * 用药过量
     */
    private String overdose;

    /**
     * 药理毒理
     */
    private String pharmacologyToxicology;

    /**
     * 药代动力学
     */
    private String pharmacokinetics;

    /**
     * 包装
     */
    private String packing;

    /**
     * 执行标准
     */
    private String operativeNorm;

    /**
     * 上市企业
     */
    private String listedCompany;

    /**
     * 生产企业地址
     */
    private String manufacturerAddress;

    /**
     * 分装企业
     */
    private String repackingEnterprise;

    /**
     * 分装企业地址
     */
    private String repackingEnterpriseAddress;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 传真
     */
    private String fax;

    /**
     * 产品咨询电话
     */
    private String productTelephone;

    /**
     * 网址
     */
    private String website;

    /**
     * 附件ID集合，多个用“，”分隔
     */
    private String fileids;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人ID
     */
    private Long createrId;

    /**
     * 创建人编码
     */
    private String createrCode;

    /**
     * 创建人名称
     */
    private String createrName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人ID
     */
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    private String modifierName;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * saas_goods_instructions
     */

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
     * 商品ID
     * @return goods_id 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 商品ID
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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
     * 核准日期
     * @return approval_date 核准日期
     */
    public Date getApprovalDate() {
        return approvalDate;
    }

    /**
     * 核准日期
     * @param approvalDate 核准日期
     */
    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    /**
     * 汉语拼音
     * @return ch_pinyin 汉语拼音
     */
    public String getChPinyin() {
        return chPinyin;
    }

    /**
     * 汉语拼音
     * @param chPinyin 汉语拼音
     */
    public void setChPinyin(String chPinyin) {
        this.chPinyin = chPinyin == null ? null : chPinyin.trim();
    }

    /**
     * 英文名称
     * @return english_name 英文名称
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * 英文名称
     * @param englishName 英文名称
     */
    public void setEnglishName(String englishName) {
        this.englishName = englishName == null ? null : englishName.trim();
    }

    /**
     * 成分
     * @return component 成分
     */
    public String getComponent() {
        return component;
    }

    /**
     * 成分
     * @param component 成分
     */
    public void setComponent(String component) {
        this.component = component == null ? null : component.trim();
    }

    /**
     * 性状
     * @return properties 性状
     */
    public String getProperties() {
        return properties;
    }

    /**
     * 性状
     * @param properties 性状
     */
    public void setProperties(String properties) {
        this.properties = properties == null ? null : properties.trim();
    }

    /**
     * 适应症
     * @return indication 适应症
     */
    public String getIndication() {
        return indication;
    }

    /**
     * 适应症
     * @param indication 适应症
     */
    public void setIndication(String indication) {
        this.indication = indication == null ? null : indication.trim();
    }

    /**
     * 用法用量
     * @return usage_dosage 用法用量
     */
    public String getUsageDosage() {
        return usageDosage;
    }

    /**
     * 用法用量
     * @param usageDosage 用法用量
     */
    public void setUsageDosage(String usageDosage) {
        this.usageDosage = usageDosage == null ? null : usageDosage.trim();
    }

    /**
     * 不良反应
     * @return adverse_reaction 不良反应
     */
    public String getAdverseReaction() {
        return adverseReaction;
    }

    /**
     * 不良反应
     * @param adverseReaction 不良反应
     */
    public void setAdverseReaction(String adverseReaction) {
        this.adverseReaction = adverseReaction == null ? null : adverseReaction.trim();
    }

    /**
     * 禁忌
     * @return taboo 禁忌
     */
    public String getTaboo() {
        return taboo;
    }

    /**
     * 禁忌
     * @param taboo 禁忌
     */
    public void setTaboo(String taboo) {
        this.taboo = taboo == null ? null : taboo.trim();
    }

    /**
     * 注意事项
     * @return notice 注意事项
     */
    public String getNotice() {
        return notice;
    }

    /**
     * 注意事项
     * @param notice 注意事项
     */
    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    /**
     * 孕妇及哺乳期妇女用药
     * @return women_medication 孕妇及哺乳期妇女用药
     */
    public String getWomenMedication() {
        return womenMedication;
    }

    /**
     * 孕妇及哺乳期妇女用药
     * @param womenMedication 孕妇及哺乳期妇女用药
     */
    public void setWomenMedication(String womenMedication) {
        this.womenMedication = womenMedication == null ? null : womenMedication.trim();
    }

    /**
     * 儿童用药
     * @return children_medication 儿童用药
     */
    public String getChildrenMedication() {
        return childrenMedication;
    }

    /**
     * 儿童用药
     * @param childrenMedication 儿童用药
     */
    public void setChildrenMedication(String childrenMedication) {
        this.childrenMedication = childrenMedication == null ? null : childrenMedication.trim();
    }

    /**
     * 老年用药
     * @return senile_medication 老年用药
     */
    public String getSenileMedication() {
        return senileMedication;
    }

    /**
     * 老年用药
     * @param senileMedication 老年用药
     */
    public void setSenileMedication(String senileMedication) {
        this.senileMedication = senileMedication == null ? null : senileMedication.trim();
    }

    /**
     * 药物和相互作用
     * @return drugs_interactions 药物和相互作用
     */
    public String getDrugsInteractions() {
        return drugsInteractions;
    }

    /**
     * 药物和相互作用
     * @param drugsInteractions 药物和相互作用
     */
    public void setDrugsInteractions(String drugsInteractions) {
        this.drugsInteractions = drugsInteractions == null ? null : drugsInteractions.trim();
    }

    /**
     * 用药过量
     * @return overdose 用药过量
     */
    public String getOverdose() {
        return overdose;
    }

    /**
     * 用药过量
     * @param overdose 用药过量
     */
    public void setOverdose(String overdose) {
        this.overdose = overdose == null ? null : overdose.trim();
    }

    /**
     * 药理毒理
     * @return pharmacology_toxicology 药理毒理
     */
    public String getPharmacologyToxicology() {
        return pharmacologyToxicology;
    }

    /**
     * 药理毒理
     * @param pharmacologyToxicology 药理毒理
     */
    public void setPharmacologyToxicology(String pharmacologyToxicology) {
        this.pharmacologyToxicology = pharmacologyToxicology == null ? null : pharmacologyToxicology.trim();
    }

    /**
     * 药代动力学
     * @return pharmacokinetics 药代动力学
     */
    public String getPharmacokinetics() {
        return pharmacokinetics;
    }

    /**
     * 药代动力学
     * @param pharmacokinetics 药代动力学
     */
    public void setPharmacokinetics(String pharmacokinetics) {
        this.pharmacokinetics = pharmacokinetics == null ? null : pharmacokinetics.trim();
    }

    /**
     * 包装
     * @return packing 包装
     */
    public String getPacking() {
        return packing;
    }

    /**
     * 包装
     * @param packing 包装
     */
    public void setPacking(String packing) {
        this.packing = packing == null ? null : packing.trim();
    }

    /**
     * 执行标准
     * @return operative_norm 执行标准
     */
    public String getOperativeNorm() {
        return operativeNorm;
    }

    /**
     * 执行标准
     * @param operativeNorm 执行标准
     */
    public void setOperativeNorm(String operativeNorm) {
        this.operativeNorm = operativeNorm == null ? null : operativeNorm.trim();
    }

    /**
     * 上市企业
     * @return listed_company 上市企业
     */
    public String getListedCompany() {
        return listedCompany;
    }

    /**
     * 上市企业
     * @param listedCompany 上市企业
     */
    public void setListedCompany(String listedCompany) {
        this.listedCompany = listedCompany == null ? null : listedCompany.trim();
    }

    /**
     * 生产企业地址
     * @return manufacturer_address 生产企业地址
     */
    public String getManufacturerAddress() {
        return manufacturerAddress;
    }

    /**
     * 生产企业地址
     * @param manufacturerAddress 生产企业地址
     */
    public void setManufacturerAddress(String manufacturerAddress) {
        this.manufacturerAddress = manufacturerAddress == null ? null : manufacturerAddress.trim();
    }

    /**
     * 分装企业
     * @return repacking_enterprise 分装企业
     */
    public String getRepackingEnterprise() {
        return repackingEnterprise;
    }

    /**
     * 分装企业
     * @param repackingEnterprise 分装企业
     */
    public void setRepackingEnterprise(String repackingEnterprise) {
        this.repackingEnterprise = repackingEnterprise == null ? null : repackingEnterprise.trim();
    }

    /**
     * 分装企业地址
     * @return repacking_enterprise_address 分装企业地址
     */
    public String getRepackingEnterpriseAddress() {
        return repackingEnterpriseAddress;
    }

    /**
     * 分装企业地址
     * @param repackingEnterpriseAddress 分装企业地址
     */
    public void setRepackingEnterpriseAddress(String repackingEnterpriseAddress) {
        this.repackingEnterpriseAddress = repackingEnterpriseAddress == null ? null : repackingEnterpriseAddress.trim();
    }

    /**
     * 电话
     * @return telephone 电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 电话
     * @param telephone 电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * 邮箱
     * @return email 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 邮政编码
     * @return postal_code 邮政编码
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * 邮政编码
     * @param postalCode 邮政编码
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    /**
     * 传真
     * @return fax 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 传真
     * @param fax 传真
     */
    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    /**
     * 产品咨询电话
     * @return product_ telephone 产品咨询电话
     */
    public String getProductTelephone() {
        return productTelephone;
    }

    /**
     * 产品咨询电话
     * @param productTelephone 产品咨询电话
     */
    public void setProductTelephone(String productTelephone) {
        this.productTelephone = productTelephone == null ? null : productTelephone.trim();
    }

    /**
     * 网址
     * @return website 网址
     */
    public String getWebsite() {
        return website;
    }

    /**
     * 网址
     * @param website 网址
     */
    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    /**
     * 附件ID集合，多个用“，”分隔
     * @return fileIds 附件ID集合，多个用“，”分隔
     */
    public String getFileids() {
        return fileids;
    }

    /**
     * 附件ID集合，多个用“，”分隔
     * @param fileids 附件ID集合，多个用“，”分隔
     */
    public void setFileids(String fileids) {
        this.fileids = fileids == null ? null : fileids.trim();
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
        sb.append(", goodsId=").append(goodsId);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", approvalDate=").append(approvalDate);
        sb.append(", chPinyin=").append(chPinyin);
        sb.append(", englishName=").append(englishName);
        sb.append(", component=").append(component);
        sb.append(", properties=").append(properties);
        sb.append(", indication=").append(indication);
        sb.append(", usageDosage=").append(usageDosage);
        sb.append(", adverseReaction=").append(adverseReaction);
        sb.append(", taboo=").append(taboo);
        sb.append(", notice=").append(notice);
        sb.append(", womenMedication=").append(womenMedication);
        sb.append(", childrenMedication=").append(childrenMedication);
        sb.append(", senileMedication=").append(senileMedication);
        sb.append(", drugsInteractions=").append(drugsInteractions);
        sb.append(", overdose=").append(overdose);
        sb.append(", pharmacologyToxicology=").append(pharmacologyToxicology);
        sb.append(", pharmacokinetics=").append(pharmacokinetics);
        sb.append(", packing=").append(packing);
        sb.append(", operativeNorm=").append(operativeNorm);
        sb.append(", listedCompany=").append(listedCompany);
        sb.append(", manufacturerAddress=").append(manufacturerAddress);
        sb.append(", repackingEnterprise=").append(repackingEnterprise);
        sb.append(", repackingEnterpriseAddress=").append(repackingEnterpriseAddress);
        sb.append(", telephone=").append(telephone);
        sb.append(", email=").append(email);
        sb.append(", postalCode=").append(postalCode);
        sb.append(", fax=").append(fax);
        sb.append(", productTelephone=").append(productTelephone);
        sb.append(", website=").append(website);
        sb.append(", fileids=").append(fileids);
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
}