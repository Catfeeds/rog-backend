package com.rograndec.feijiayun.chain.business.goods.sale.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SpecialPriceGoods implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 策率ID
     */
    private Long strategyId;

    /**
     * 策略名称
     */
    private String stragegyName;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 剂型ID
     */
    private Long dosageId;

    /**
     * 剂型名称
     */
    private String dosageName;

    /**
     * 规格
     */
    private String specification;

    /**
     * 生产厂商ID
     */
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    private String manufacturer;

    /**
     * 单位ID
     */
    private Long unitId;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 状态（0-禁用；1-启用）
     */
    private Integer status;

    /**
     * 商品产地
     */
    private String goodsPlace;

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
     * saas_special_price_goods
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
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 策率ID
     * @return strategy_id 策率ID
     */
    public Long getStrategyId() {
        return strategyId;
    }

    /**
     * 策率ID
     * @param strategyId 策率ID
     */
    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    /**
     * 策略名称
     * @return stragegy_name 策略名称
     */
    public String getStragegyName() {
        return stragegyName;
    }

    /**
     * 策略名称
     * @param stragegyName 策略名称
     */
    public void setStragegyName(String stragegyName) {
        this.stragegyName = stragegyName == null ? null : stragegyName.trim();
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
     * 商品编码
     * @return goods_code 商品编码
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * 商品编码
     * @param goodsCode 商品编码
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    /**
     * 商品名称
     * @return goods_name 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 商品名称
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 剂型ID
     * @return dosage_id 剂型ID
     */
    public Long getDosageId() {
        return dosageId;
    }

    /**
     * 剂型ID
     * @param dosageId 剂型ID
     */
    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
    }

    /**
     * 剂型名称
     * @return dosage_name 剂型名称
     */
    public String getDosageName() {
        return dosageName;
    }

    /**
     * 剂型名称
     * @param dosageName 剂型名称
     */
    public void setDosageName(String dosageName) {
        this.dosageName = dosageName == null ? null : dosageName.trim();
    }

    /**
     * 规格
     * @return specification 规格
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * 规格
     * @param specification 规格
     */
    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    /**
     * 生产厂商ID
     * @return manufacturer_id 生产厂商ID
     */
    public Long getManufacturerId() {
        return manufacturerId;
    }

    /**
     * 生产厂商ID
     * @param manufacturerId 生产厂商ID
     */
    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * 生产厂商
     * @return manufacturer 生产厂商
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 生产厂商
     * @param manufacturer 生产厂商
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    /**
     * 单位ID
     * @return unit_id 单位ID
     */
    public Long getUnitId() {
        return unitId;
    }

    /**
     * 单位ID
     * @param unitId 单位ID
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    /**
     * 单位名称
     * @return unit_name 单位名称
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 单位名称
     * @param unitName 单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
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

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
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
        sb.append(", parentId=").append(parentId);
        sb.append(", strategyId=").append(strategyId);
        sb.append(", stragegyName=").append(stragegyName);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", dosageId=").append(dosageId);
        sb.append(", dosageName=").append(dosageName);
        sb.append(", specification=").append(specification);
        sb.append(", manufacturerId=").append(manufacturerId);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", unitId=").append(unitId);
        sb.append(", unitName=").append(unitName);
        sb.append(", goodsPlace=").append(goodsPlace);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecialPriceGoods that = (SpecialPriceGoods) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (enterpriseId != null ? !enterpriseId.equals(that.enterpriseId) : that.enterpriseId != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (strategyId != null ? !strategyId.equals(that.strategyId) : that.strategyId != null) return false;
        if (stragegyName != null ? !stragegyName.equals(that.stragegyName) : that.stragegyName != null) return false;
        if (goodsId != null ? !goodsId.equals(that.goodsId) : that.goodsId != null) return false;
        if (goodsCode != null ? !goodsCode.equals(that.goodsCode) : that.goodsCode != null) return false;
        if (goodsName != null ? !goodsName.equals(that.goodsName) : that.goodsName != null) return false;
        if (dosageId != null ? !dosageId.equals(that.dosageId) : that.dosageId != null) return false;
        if (dosageName != null ? !dosageName.equals(that.dosageName) : that.dosageName != null) return false;
        if (specification != null ? !specification.equals(that.specification) : that.specification != null)
            return false;
        if (manufacturerId != null ? !manufacturerId.equals(that.manufacturerId) : that.manufacturerId != null)
            return false;
        if (manufacturer != null ? !manufacturer.equals(that.manufacturer) : that.manufacturer != null) return false;
        if (unitId != null ? !unitId.equals(that.unitId) : that.unitId != null) return false;
        if (unitName != null ? !unitName.equals(that.unitName) : that.unitName != null) return false;
        if (goodsPlace != null ? !goodsPlace.equals(that.goodsPlace) : that.goodsPlace != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (createrId != null ? !createrId.equals(that.createrId) : that.createrId != null) return false;
        if (createrCode != null ? !createrCode.equals(that.createrCode) : that.createrCode != null) return false;
        if (createrName != null ? !createrName.equals(that.createrName) : that.createrName != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (modifierId != null ? !modifierId.equals(that.modifierId) : that.modifierId != null) return false;
        if (modifierCode != null ? !modifierCode.equals(that.modifierCode) : that.modifierCode != null) return false;
        if (modifierName != null ? !modifierName.equals(that.modifierName) : that.modifierName != null) return false;
        return updateTime != null ? updateTime.equals(that.updateTime) : that.updateTime == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (enterpriseId != null ? enterpriseId.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (strategyId != null ? strategyId.hashCode() : 0);
        result = 31 * result + (stragegyName != null ? stragegyName.hashCode() : 0);
        result = 31 * result + (goodsId != null ? goodsId.hashCode() : 0);
        result = 31 * result + (goodsCode != null ? goodsCode.hashCode() : 0);
        result = 31 * result + (goodsName != null ? goodsName.hashCode() : 0);
        result = 31 * result + (dosageId != null ? dosageId.hashCode() : 0);
        result = 31 * result + (dosageName != null ? dosageName.hashCode() : 0);
        result = 31 * result + (specification != null ? specification.hashCode() : 0);
        result = 31 * result + (manufacturerId != null ? manufacturerId.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (unitId != null ? unitId.hashCode() : 0);
        result = 31 * result + (unitName != null ? unitName.hashCode() : 0);
        result = 31 * result + (goodsPlace != null ? goodsPlace.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (createrId != null ? createrId.hashCode() : 0);
        result = 31 * result + (createrCode != null ? createrCode.hashCode() : 0);
        result = 31 * result + (createrName != null ? createrName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (modifierId != null ? modifierId.hashCode() : 0);
        result = 31 * result + (modifierCode != null ? modifierCode.hashCode() : 0);
        result = 31 * result + (modifierName != null ? modifierName.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}