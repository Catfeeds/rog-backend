package com.rograndec.feijiayun.chain.business.online.purchase.order.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_mphgoods_relevance
 * 
 * 
 * @author Asze
 * 
 * 2017-11-21
 */
public class MphgoodsRelevance implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 药店id
     */
    @ApiModelProperty(value = "药店id")
    private Long enterpriseId;

    /**
     * MPH_商品ID
     */
    @ApiModelProperty(value = "MPH_商品ID")
    private Long mphGoodsId;

    /**
     * MPH_商品名称
     */
    @ApiModelProperty(value = "MPH_商品名称")
    private String mphGoodsName;

    /**
     * MPH_商品产地
     */
    @ApiModelProperty(value = "MPH_商品产地")
    private String mphGoodsPlace;

    /**
     * MPH_商品规格
     */
    @ApiModelProperty(value = "MPH_商品规格")
    private String mphGoodsSpecification;

    /**
     * MPH_生产企业
     */
    @ApiModelProperty(value = "MPH_生产企业")
    private String mphGoodsManufacturer;

    /**
     * MPH供应商ID
     */
    @ApiModelProperty(value = "MPH供应商ID")
    private Long mphSupplierId;

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
     * saas_mphgoods_relevance
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * @return id ID
     */
    public Long getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
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
     * 药店id
     * @return enterprise_id 药店id
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 药店id
     * @param enterpriseId 药店id
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * MPH_商品ID
     * @return mph_goods_id MPH_商品ID
     */
    public Long getMphGoodsId() {
        return mphGoodsId;
    }

    /**
     * MPH_商品ID
     * @param mphGoodsId MPH_商品ID
     */
    public void setMphGoodsId(Long mphGoodsId) {
        this.mphGoodsId = mphGoodsId;
    }

    /**
     * MPH_商品名称
     * @return mph_goods_name MPH_商品名称
     */
    public String getMphGoodsName() {
        return mphGoodsName;
    }

    /**
     * MPH_商品名称
     * @param mphGoodsName MPH_商品名称
     */
    public void setMphGoodsName(String mphGoodsName) {
        this.mphGoodsName = mphGoodsName == null ? null : mphGoodsName.trim();
    }

    /**
     * MPH_商品产地
     * @return mph_goods_place MPH_商品产地
     */
    public String getMphGoodsPlace() {
        return mphGoodsPlace;
    }

    /**
     * MPH_商品产地
     * @param mphGoodsPlace MPH_商品产地
     */
    public void setMphGoodsPlace(String mphGoodsPlace) {
        this.mphGoodsPlace = mphGoodsPlace == null ? null : mphGoodsPlace.trim();
    }

    /**
     * MPH_商品规格
     * @return mph_goods_specification MPH_商品规格
     */
    public String getMphGoodsSpecification() {
        return mphGoodsSpecification;
    }

    /**
     * MPH_商品规格
     * @param mphGoodsSpecification MPH_商品规格
     */
    public void setMphGoodsSpecification(String mphGoodsSpecification) {
        this.mphGoodsSpecification = mphGoodsSpecification == null ? null : mphGoodsSpecification.trim();
    }

    /**
     * MPH_生产企业
     * @return mph_goods_manufacturer MPH_生产企业
     */
    public String getMphGoodsManufacturer() {
        return mphGoodsManufacturer;
    }

    /**
     * MPH_生产企业
     * @param mphGoodsManufacturer MPH_生产企业
     */
    public void setMphGoodsManufacturer(String mphGoodsManufacturer) {
        this.mphGoodsManufacturer = mphGoodsManufacturer == null ? null : mphGoodsManufacturer.trim();
    }

    /**
     * MPH供应商ID
     * @return mph_supplier_id MPH供应商ID
     */
    public Long getMphSupplierId() {
        return mphSupplierId;
    }

    /**
     * MPH供应商ID
     * @param mphSupplierId MPH供应商ID
     */
    public void setMphSupplierId(Long mphSupplierId) {
        this.mphSupplierId = mphSupplierId;
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
        sb.append(", mphGoodsId=").append(mphGoodsId);
        sb.append(", mphGoodsName=").append(mphGoodsName);
        sb.append(", mphGoodsPlace=").append(mphGoodsPlace);
        sb.append(", mphGoodsSpecification=").append(mphGoodsSpecification);
        sb.append(", mphGoodsManufacturer=").append(mphGoodsManufacturer);
        sb.append(", mphSupplierId=").append(mphSupplierId);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}