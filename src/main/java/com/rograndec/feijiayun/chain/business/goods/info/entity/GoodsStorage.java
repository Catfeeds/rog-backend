package com.rograndec.feijiayun.chain.business.goods.info.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GoodsStorage implements Serializable {
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
     * 中包装数量
     */
    private BigDecimal inbagQuantity;

    /**
     * 大包装数量
     */
    private BigDecimal bigbagQuantity;

    /**
     * 小包装长度（cm）
     */
    private BigDecimal smallbagLength;

    /**
     * 小包装宽度（cm）
     */
    private BigDecimal smallbagWidth;

    /**
     * 小包装高度（cm）
     */
    private BigDecimal smallbagHeight;

    /**
     * 小包装体积（cm³）
     */
    private BigDecimal smallbagVolume;

    /**
     * 小包装重量（g）
     */
    private BigDecimal smallbagWeight;

    /**
     * 中包装长度（cm）
     */
    private BigDecimal inbagLength;

    /**
     * 中包装宽度（cm）
     */
    private BigDecimal inbagWidth;

    /**
     * 中包装高度（cm）
     */
    private BigDecimal inbagHeight;

    /**
     * 中包装体积（cm³）
     */
    private BigDecimal inbagVolume;

    /**
     * 中包装重量（g）
     */
    private BigDecimal inbagWeight;

    /**
     * 大包装长度（cm）
     */
    private BigDecimal bigbagLength;

    /**
     * 大包装宽度（cm）
     */
    private BigDecimal bigbagWidth;

    /**
     * 大包装高度（cm）
     */
    private BigDecimal bigbagHeight;

    /**
     * 大包装体积（cm³）
     */
    private BigDecimal bigbagVolume;

    /**
     * 大包装重量（g）
     */
    private BigDecimal bigbagWeight;

    /**
     * 配送方式（0-配送中心配送；1-委托配送）
     */
    private Integer deliveryMode;

    /**
     * 补货政策（0-动态存量补货；1-安全库存补货；2-人工补货；3-禁止补货）
     */
    private Integer replenishmentPolicy;

    /**
     * 最小采购批量
     */
    private BigDecimal minimumPurchasingBatch;

    /**
     * 最小配货批量
     */
    private BigDecimal minimumDistributionBatch;

    /**
     * 近效期
     */
    private Integer nearEffectPeriod;

    /**
     * 近效期单位（0-天；1-月；2-年）
     */
    private Integer nearEffectPeriodUnit;

    /**
     * 滞销周期
     */
    private Integer unsalableCycle;

    /**
     * 滞销周期单位（0-天；1-月；2-年）
     */
    private Integer unsalableCycleUnit;

    /**
     * 养护类型（0-常规养护；1-重点养护）
     */
    private Integer maintenanceType;

    /**
     * 养护周期
     */
    private Integer maintenanceCycle;

    /**
     * 养护周期单位（0-天；1-月；2-年）
     */
    private Integer maintenanceCycleUnit;

    /**
     * 养护标准（养护措施ID集合）
     */
    private String maintenanceMeasureIds;

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
     * saas_goods_storage
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
     * 中包装数量
     * @return inbag_quantity 中包装数量
     */
    public BigDecimal getInbagQuantity() {
        return inbagQuantity;
    }

    /**
     * 中包装数量
     * @param inbagQuantity 中包装数量
     */
    public void setInbagQuantity(BigDecimal inbagQuantity) {
        this.inbagQuantity = inbagQuantity;
    }

    /**
     * 大包装数量
     * @return bigbag_quantity 大包装数量
     */
    public BigDecimal getBigbagQuantity() {
        return bigbagQuantity;
    }

    /**
     * 大包装数量
     * @param bigbagQuantity 大包装数量
     */
    public void setBigbagQuantity(BigDecimal bigbagQuantity) {
        this.bigbagQuantity = bigbagQuantity;
    }

    /**
     * 小包装长度（cm）
     * @return smallbag_length 小包装长度（cm）
     */
    public BigDecimal getSmallbagLength() {
        return smallbagLength;
    }

    /**
     * 小包装长度（cm）
     * @param smallbagLength 小包装长度（cm）
     */
    public void setSmallbagLength(BigDecimal smallbagLength) {
        this.smallbagLength = smallbagLength;
    }

    /**
     * 小包装宽度（cm）
     * @return smallbag_width 小包装宽度（cm）
     */
    public BigDecimal getSmallbagWidth() {
        return smallbagWidth;
    }

    /**
     * 小包装宽度（cm）
     * @param smallbagWidth 小包装宽度（cm）
     */
    public void setSmallbagWidth(BigDecimal smallbagWidth) {
        this.smallbagWidth = smallbagWidth;
    }

    /**
     * 小包装高度（cm）
     * @return smallbag_height 小包装高度（cm）
     */
    public BigDecimal getSmallbagHeight() {
        return smallbagHeight;
    }

    /**
     * 小包装高度（cm）
     * @param smallbagHeight 小包装高度（cm）
     */
    public void setSmallbagHeight(BigDecimal smallbagHeight) {
        this.smallbagHeight = smallbagHeight;
    }

    /**
     * 小包装体积（cm³）
     * @return smallbag_volume 小包装体积（cm³）
     */
    public BigDecimal getSmallbagVolume() {
        return smallbagVolume;
    }

    /**
     * 小包装体积（cm³）
     * @param smallbagVolume 小包装体积（cm³）
     */
    public void setSmallbagVolume(BigDecimal smallbagVolume) {
        this.smallbagVolume = smallbagVolume;
    }

    /**
     * 小包装重量（g）
     * @return smallbag_weight 小包装重量（g）
     */
    public BigDecimal getSmallbagWeight() {
        return smallbagWeight;
    }

    /**
     * 小包装重量（g）
     * @param smallbagWeight 小包装重量（g）
     */
    public void setSmallbagWeight(BigDecimal smallbagWeight) {
        this.smallbagWeight = smallbagWeight;
    }

    /**
     * 中包装长度（cm）
     * @return inbag_length 中包装长度（cm）
     */
    public BigDecimal getInbagLength() {
        return inbagLength;
    }

    /**
     * 中包装长度（cm）
     * @param inbagLength 中包装长度（cm）
     */
    public void setInbagLength(BigDecimal inbagLength) {
        this.inbagLength = inbagLength;
    }

    /**
     * 中包装宽度（cm）
     * @return inbag_width 中包装宽度（cm）
     */
    public BigDecimal getInbagWidth() {
        return inbagWidth;
    }

    /**
     * 中包装宽度（cm）
     * @param inbagWidth 中包装宽度（cm）
     */
    public void setInbagWidth(BigDecimal inbagWidth) {
        this.inbagWidth = inbagWidth;
    }

    /**
     * 中包装高度（cm）
     * @return inbag_height 中包装高度（cm）
     */
    public BigDecimal getInbagHeight() {
        return inbagHeight;
    }

    /**
     * 中包装高度（cm）
     * @param inbagHeight 中包装高度（cm）
     */
    public void setInbagHeight(BigDecimal inbagHeight) {
        this.inbagHeight = inbagHeight;
    }

    /**
     * 中包装体积（cm³）
     * @return inbag_volume 中包装体积（cm³）
     */
    public BigDecimal getInbagVolume() {
        return inbagVolume;
    }

    /**
     * 中包装体积（cm³）
     * @param inbagVolume 中包装体积（cm³）
     */
    public void setInbagVolume(BigDecimal inbagVolume) {
        this.inbagVolume = inbagVolume;
    }

    /**
     * 中包装重量（g）
     * @return inbag_weight 中包装重量（g）
     */
    public BigDecimal getInbagWeight() {
        return inbagWeight;
    }

    /**
     * 中包装重量（g）
     * @param inbagWeight 中包装重量（g）
     */
    public void setInbagWeight(BigDecimal inbagWeight) {
        this.inbagWeight = inbagWeight;
    }

    /**
     * 大包装长度（cm）
     * @return bigbag_length 大包装长度（cm）
     */
    public BigDecimal getBigbagLength() {
        return bigbagLength;
    }

    /**
     * 大包装长度（cm）
     * @param bigbagLength 大包装长度（cm）
     */
    public void setBigbagLength(BigDecimal bigbagLength) {
        this.bigbagLength = bigbagLength;
    }

    /**
     * 大包装宽度（cm）
     * @return bigbag_width 大包装宽度（cm）
     */
    public BigDecimal getBigbagWidth() {
        return bigbagWidth;
    }

    /**
     * 大包装宽度（cm）
     * @param bigbagWidth 大包装宽度（cm）
     */
    public void setBigbagWidth(BigDecimal bigbagWidth) {
        this.bigbagWidth = bigbagWidth;
    }

    /**
     * 大包装高度（cm）
     * @return bigbag_height 大包装高度（cm）
     */
    public BigDecimal getBigbagHeight() {
        return bigbagHeight;
    }

    /**
     * 大包装高度（cm）
     * @param bigbagHeight 大包装高度（cm）
     */
    public void setBigbagHeight(BigDecimal bigbagHeight) {
        this.bigbagHeight = bigbagHeight;
    }

    /**
     * 大包装体积（cm³）
     * @return bigbag_volume 大包装体积（cm³）
     */
    public BigDecimal getBigbagVolume() {
        return bigbagVolume;
    }

    /**
     * 大包装体积（cm³）
     * @param bigbagVolume 大包装体积（cm³）
     */
    public void setBigbagVolume(BigDecimal bigbagVolume) {
        this.bigbagVolume = bigbagVolume;
    }

    /**
     * 大包装重量（g）
     * @return bigbag_weight 大包装重量（g）
     */
    public BigDecimal getBigbagWeight() {
        return bigbagWeight;
    }

    /**
     * 大包装重量（g）
     * @param bigbagWeight 大包装重量（g）
     */
    public void setBigbagWeight(BigDecimal bigbagWeight) {
        this.bigbagWeight = bigbagWeight;
    }

    /**
     * 配送方式（0-配送中心配送；1-委托配送）
     * @return delivery_mode 配送方式（0-配送中心配送；1-委托配送）
     */
    public Integer getDeliveryMode() {
        return deliveryMode;
    }

    /**
     * 配送方式（0-配送中心配送；1-委托配送）
     * @param deliveryMode 配送方式（0-配送中心配送；1-委托配送）
     */
    public void setDeliveryMode(Integer deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    /**
     * 补货政策（0-动态存量补货；1-安全库存补货；2-人工补货；3-禁止补货）
     * @return replenishment_policy 补货政策（0-动态存量补货；1-安全库存补货；2-人工补货；3-禁止补货）
     */
    public Integer getReplenishmentPolicy() {
        return replenishmentPolicy;
    }

    /**
     * 补货政策（0-动态存量补货；1-安全库存补货；2-人工补货；3-禁止补货）
     * @param replenishmentPolicy 补货政策（0-动态存量补货；1-安全库存补货；2-人工补货；3-禁止补货）
     */
    public void setReplenishmentPolicy(Integer replenishmentPolicy) {
        this.replenishmentPolicy = replenishmentPolicy;
    }

    /**
     * 最小采购批量
     * @return minimum_purchasing_batch 最小采购批量
     */
    public BigDecimal getMinimumPurchasingBatch() {
        return minimumPurchasingBatch;
    }

    /**
     * 最小采购批量
     * @param minimumPurchasingBatch 最小采购批量
     */
    public void setMinimumPurchasingBatch(BigDecimal minimumPurchasingBatch) {
        this.minimumPurchasingBatch = minimumPurchasingBatch;
    }

    /**
     * 最小配货批量
     * @return minimum_distribution_ batch 最小配货批量
     */
    public BigDecimal getMinimumDistributionBatch() {
        return minimumDistributionBatch;
    }

    /**
     * 最小配货批量
     * @param minimumDistributionBatch 最小配货批量
     */
    public void setMinimumDistributionBatch(BigDecimal minimumDistributionBatch) {
        this.minimumDistributionBatch = minimumDistributionBatch;
    }

    /**
     * 近效期
     * @return near_effect_period 近效期
     */
    public Integer getNearEffectPeriod() {
        return nearEffectPeriod;
    }

    /**
     * 近效期
     * @param nearEffectPeriod 近效期
     */
    public void setNearEffectPeriod(Integer nearEffectPeriod) {
        this.nearEffectPeriod = nearEffectPeriod;
    }

    /**
     * 近效期单位（0-天；1-月；2-年）
     * @return near_effect_period_unit 近效期单位（0-天；1-月；2-年）
     */
    public Integer getNearEffectPeriodUnit() {
        return nearEffectPeriodUnit;
    }

    /**
     * 近效期单位（0-天；1-月；2-年）
     * @param nearEffectPeriodUnit 近效期单位（0-天；1-月；2-年）
     */
    public void setNearEffectPeriodUnit(Integer nearEffectPeriodUnit) {
        this.nearEffectPeriodUnit = nearEffectPeriodUnit;
    }

    /**
     * 滞销周期
     * @return unsalable_cycle 滞销周期
     */
    public Integer getUnsalableCycle() {
        return unsalableCycle;
    }

    /**
     * 滞销周期
     * @param unsalableCycle 滞销周期
     */
    public void setUnsalableCycle(Integer unsalableCycle) {
        this.unsalableCycle = unsalableCycle;
    }

    /**
     * 滞销周期单位（0-天；1-月；2-年）
     * @return unsalable_cycle_unit 滞销周期单位（0-天；1-月；2-年）
     */
    public Integer getUnsalableCycleUnit() {
        return unsalableCycleUnit;
    }

    /**
     * 滞销周期单位（0-天；1-月；2-年）
     * @param unsalableCycleUnit 滞销周期单位（0-天；1-月；2-年）
     */
    public void setUnsalableCycleUnit(Integer unsalableCycleUnit) {
        this.unsalableCycleUnit = unsalableCycleUnit;
    }

    /**
     * 养护类型（0-常规养护；1-重点养护）
     * @return maintenance_type 养护类型（0-常规养护；1-重点养护）
     */
    public Integer getMaintenanceType() {
        return maintenanceType;
    }

    /**
     * 养护类型（0-常规养护；1-重点养护）
     * @param maintenanceType 养护类型（0-常规养护；1-重点养护）
     */
    public void setMaintenanceType(Integer maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    /**
     * 养护周期
     * @return maintenance_cycle 养护周期
     */
    public Integer getMaintenanceCycle() {
        return maintenanceCycle;
    }

    /**
     * 养护周期
     * @param maintenanceCycle 养护周期
     */
    public void setMaintenanceCycle(Integer maintenanceCycle) {
        this.maintenanceCycle = maintenanceCycle;
    }

    /**
     * 养护周期单位（0-天；1-月；2-年）
     * @return maintenance_cycle _unit 养护周期单位（0-天；1-月；2-年）
     */
    public Integer getMaintenanceCycleUnit() {
        return maintenanceCycleUnit;
    }

    /**
     * 养护周期单位（0-天；1-月；2-年）
     * @param maintenanceCycleUnit 养护周期单位（0-天；1-月；2-年）
     */
    public void setMaintenanceCycleUnit(Integer maintenanceCycleUnit) {
        this.maintenanceCycleUnit = maintenanceCycleUnit;
    }

    /**
     * 养护标准（养护措施ID集合）
     * @return maintenance_measure_ids 养护标准（养护措施ID集合）
     */
    public String getMaintenanceMeasureIds() {
        return maintenanceMeasureIds;
    }

    /**
     * 养护标准（养护措施ID集合）
     * @param maintenanceMeasureIds 养护标准（养护措施ID集合）
     */
    public void setMaintenanceMeasureIds(String maintenanceMeasureIds) {
        this.maintenanceMeasureIds = maintenanceMeasureIds == null ? null : maintenanceMeasureIds.trim();
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
        sb.append(", inbagQuantity=").append(inbagQuantity);
        sb.append(", bigbagQuantity=").append(bigbagQuantity);
        sb.append(", smallbagLength=").append(smallbagLength);
        sb.append(", smallbagWidth=").append(smallbagWidth);
        sb.append(", smallbagHeight=").append(smallbagHeight);
        sb.append(", smallbagVolume=").append(smallbagVolume);
        sb.append(", smallbagWeight=").append(smallbagWeight);
        sb.append(", inbagLength=").append(inbagLength);
        sb.append(", inbagWidth=").append(inbagWidth);
        sb.append(", inbagHeight=").append(inbagHeight);
        sb.append(", inbagVolume=").append(inbagVolume);
        sb.append(", inbagWeight=").append(inbagWeight);
        sb.append(", bigbagLength=").append(bigbagLength);
        sb.append(", bigbagWidth=").append(bigbagWidth);
        sb.append(", bigbagHeight=").append(bigbagHeight);
        sb.append(", bigbagVolume=").append(bigbagVolume);
        sb.append(", bigbagWeight=").append(bigbagWeight);
        sb.append(", deliveryMode=").append(deliveryMode);
        sb.append(", replenishmentPolicy=").append(replenishmentPolicy);
        sb.append(", minimumPurchasingBatch=").append(minimumPurchasingBatch);
        sb.append(", minimumDistributionBatch=").append(minimumDistributionBatch);
        sb.append(", nearEffectPeriod=").append(nearEffectPeriod);
        sb.append(", nearEffectPeriodUnit=").append(nearEffectPeriodUnit);
        sb.append(", unsalableCycle=").append(unsalableCycle);
        sb.append(", unsalableCycleUnit=").append(unsalableCycleUnit);
        sb.append(", maintenanceType=").append(maintenanceType);
        sb.append(", maintenanceCycle=").append(maintenanceCycle);
        sb.append(", maintenanceCycleUnit=").append(maintenanceCycleUnit);
        sb.append(", maintenanceMeasureIds=").append(maintenanceMeasureIds);
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