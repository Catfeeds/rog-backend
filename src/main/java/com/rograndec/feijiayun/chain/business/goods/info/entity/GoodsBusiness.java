package com.rograndec.feijiayun.chain.business.goods.info.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GoodsBusiness implements Serializable {
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



    private Long purchaseTaxRateId;
    /**
     * 进项税（%）
     */
    private BigDecimal purchaseTaxRate;

    /**
     * 销售税（%）
     */
    private BigDecimal saleTaxRate;
    private Long saleTaxRateId;

    /**
     * 经营方式（0-购销；1-实销实结）
     */
    private Integer managementMode;

    /**
     * 积分商品（0-否；1-是）
     */
    private Integer integralGoods;

    /**
     * 积分倍数
     */
    private BigDecimal integralMultiple;

    /**
     * 特价商品（0-否；1-是）
     */
    private Integer bargainGoods;

    /**
     * 提成商品（0-否；1-是）
     */
    private Integer commissionGoods;

    /**
     * 零售基价
     */
    private BigDecimal retailPrice;

    /**
     * 会员基价
     */
    private BigDecimal memberPrice;

    /**
     * 配货基价
     */
    private BigDecimal distrPrice;

    /**
     *  配货税率（%）
     */
    private BigDecimal distrTaxRate;
    private Long distrTaxRateId;

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
     * 销售定价策略：0总部统一定价,1允许门店自主定价.总部添加的商品默认总部统一定价， 加盟店添加的商品默认允许门店自主定价且只读
     */
    private Integer pricingPolicy;

    /**
     * saas_goods_business
     */


    public static Set<Long> getTaxRateIds(List<GoodsBusiness> goodsBusinesses){
        Set<Long> ids = new HashSet<>();

        for(GoodsBusiness goodsBusiness : goodsBusinesses){
            ids.add(goodsBusiness.getDistrTaxRateId());
            ids.add(goodsBusiness.getSaleTaxRateId());
        }

        return ids;

    }

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
     * 进项税（%）
     * @return purchase_tax_rate 进项税（%）
     */
    public BigDecimal getPurchaseTaxRate() {
        return purchaseTaxRate;
    }

    /**
     * 进项税（%）
     * @param purchaseTaxRate 进项税（%）
     */
    public void setPurchaseTaxRate(BigDecimal purchaseTaxRate) {
        this.purchaseTaxRate = purchaseTaxRate;
    }

    /**
     * 销售税（%）
     * @return sale_tax_rate 销售税（%）
     */
    public BigDecimal getSaleTaxRate() {
        return saleTaxRate;
    }

    /**
     * 销售税（%）
     * @param saleTaxRate 销售税（%）
     */
    public void setSaleTaxRate(BigDecimal saleTaxRate) {
        this.saleTaxRate = saleTaxRate;
    }

    /**
     * 经营方式（0-购销；1-实销实结）
     * @return management_mode 经营方式（0-购销；1-实销实结）
     */
    public Integer getManagementMode() {
        return managementMode;
    }

    /**
     * 经营方式（0-购销；1-实销实结）
     * @param managementMode 经营方式（0-购销；1-实销实结）
     */
    public void setManagementMode(Integer managementMode) {
        this.managementMode = managementMode;
    }

    /**
     * 积分商品（0-否；1-是）
     * @return integral_goods 积分商品（0-否；1-是）
     */
    public Integer getIntegralGoods() {
        return integralGoods;
    }

    /**
     * 积分商品（0-否；1-是）
     * @param integralGoods 积分商品（0-否；1-是）
     */
    public void setIntegralGoods(Integer integralGoods) {
        this.integralGoods = integralGoods;
    }

    /**
     * 积分倍数
     * @return integral_multiple 积分倍数
     */
    public BigDecimal getIntegralMultiple() {
        return integralMultiple;
    }

    /**
     * 积分倍数
     * @param integralMultiple 积分倍数
     */
    public void setIntegralMultiple(BigDecimal integralMultiple) {
        this.integralMultiple = integralMultiple;
    }

    /**
     * 特价商品（0-否；1-是）
     * @return bargain_goods 特价商品（0-否；1-是）
     */
    public Integer getBargainGoods() {
        return bargainGoods;
    }

    /**
     * 特价商品（0-否；1-是）
     * @param bargainGoods 特价商品（0-否；1-是）
     */
    public void setBargainGoods(Integer bargainGoods) {
        this.bargainGoods = bargainGoods;
    }

    /**
     * 提成商品（0-否；1-是）
     * @return commission_goods 提成商品（0-否；1-是）
     */
    public Integer getCommissionGoods() {
        return commissionGoods;
    }

    /**
     * 提成商品（0-否；1-是）
     * @param commissionGoods 提成商品（0-否；1-是）
     */
    public void setCommissionGoods(Integer commissionGoods) {
        this.commissionGoods = commissionGoods;
    }

    /**
     * 零售基价
     * @return retail_price 零售基价
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    /**
     * 零售基价
     * @param retailPrice 零售基价
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * 会员基价
     * @return member_price 会员基价
     */
    public BigDecimal getMemberPrice() {
        return memberPrice;
    }

    /**
     * 会员基价
     * @param memberPrice 会员基价
     */
    public void setMemberPrice(BigDecimal memberPrice) {
        this.memberPrice = memberPrice;
    }

    /**
     * 配货基价
     * @return distr_price 配货基价
     */
    public BigDecimal getDistrPrice() {
        return distrPrice;
    }

    /**
     * 配货基价
     * @param distrPrice 配货基价
     */
    public void setDistrPrice(BigDecimal distrPrice) {
        this.distrPrice = distrPrice;
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

    public BigDecimal getDistrTaxRate() {
        return distrTaxRate;
    }

    public void setDistrTaxRate(BigDecimal distrTaxRate) {
        this.distrTaxRate = distrTaxRate;
    }


    public Integer getPricingPolicy() {
        return pricingPolicy;
    }

    public void setPricingPolicy(Integer pricingPolicy) {
        this.pricingPolicy = pricingPolicy;
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
        sb.append(", purchaseTaxRate=").append(purchaseTaxRate);
        sb.append(", saleTaxRate=").append(saleTaxRate);
        sb.append(", managementMode=").append(managementMode);
        sb.append(", integralGoods=").append(integralGoods);
        sb.append(", integralMultiple=").append(integralMultiple);
        sb.append(", bargainGoods=").append(bargainGoods);
        sb.append(", commissionGoods=").append(commissionGoods);
        sb.append(", retailPrice=").append(retailPrice);
        sb.append(", memberPrice=").append(memberPrice);
        sb.append(", distrPrice=").append(distrPrice);
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

    public Long getSaleTaxRateId() {
        return saleTaxRateId;
    }

    public void setSaleTaxRateId(Long saleTaxRateId) {
        this.saleTaxRateId = saleTaxRateId;
    }

    public Long getDistrTaxRateId() {
        return distrTaxRateId;
    }

    public void setDistrTaxRateId(Long distrTaxRateId) {
        this.distrTaxRateId = distrTaxRateId;
    }

    public Long getPurchaseTaxRateId() {
        return purchaseTaxRateId;
    }

    public void setPurchaseTaxRateId(Long purchaseTaxRateId) {
        this.purchaseTaxRateId = purchaseTaxRateId;
    }
}