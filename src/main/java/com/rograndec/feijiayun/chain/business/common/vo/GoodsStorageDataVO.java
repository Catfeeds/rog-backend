package com.rograndec.feijiayun.chain.business.common.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsStorageDataVO implements Serializable {

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 中包装数量
     */
    @ApiModelProperty(value = "中包装数量")
    private BigDecimal inbagQuantity;

    /**
     * 大包装数量
     */
    @ApiModelProperty(value = "大包装数量")
    private BigDecimal bigbagQuantity;

    /**
     * 小包装长度（cm）
     */
    @ApiModelProperty(value = "小包装长度（cm）")
    private BigDecimal smallbagLength;

    /**
     * 小包装宽度（cm）
     */
    @ApiModelProperty(value = "小包装宽度（cm）")
    private BigDecimal smallbagWidth;

    /**
     * 小包装高度（cm）
     */
    @ApiModelProperty(value = "小包装高度（cm）")
    private BigDecimal smallbagHeight;

    /**
     * 小包装体积（cm³）
     */
    @ApiModelProperty(value = "小包装体积（cm³）")
    private BigDecimal smallbagVolume;

    /**
     * 小包装重量（g）
     */
    @ApiModelProperty(value = "小包装重量（g）")
    private BigDecimal smallbagWeight;

    /**
     * 中包装长度（cm）
     */
    @ApiModelProperty(value = "中包装长度（cm）")
    private BigDecimal inbagLength;

    /**
     * 中包装宽度（cm）
     */
    @ApiModelProperty(value = "中包装宽度（cm）")
    private BigDecimal inbagWidth;

    /**
     * 中包装高度（cm）
     */
    @ApiModelProperty(value = "中包装高度（cm）")
    private BigDecimal inbagHeight;

    /**
     * 中包装体积（cm³）
     */
    @ApiModelProperty(value = "中包装体积（cm³）")
    private BigDecimal inbagVolume;

    /**
     * 中包装重量（g）
     */
    @ApiModelProperty(value = "中包装重量（g）")
    private BigDecimal inbagWeight;

    /**
     * 大包装长度（cm）
     */
    @ApiModelProperty(value = "大包装长度（cm）")
    private BigDecimal bigbagLength;

    /**
     * 大包装宽度（cm）
     */
    @ApiModelProperty(value = "大包装宽度（cm）")
    private BigDecimal bigbagWidth;

    /**
     * 大包装高度（cm）
     */
    @ApiModelProperty(value = "大包装高度（cm）")
    private BigDecimal bigbagHeight;

    /**
     * 大包装体积（cm³）
     */
    @ApiModelProperty(value = "大包装体积（cm³）")
    private BigDecimal bigbagVolume;

    /**
     * 大包装重量（g）
     */
    @ApiModelProperty(value = "大包装重量（g）")
    private BigDecimal bigbagWeight;

    /**
     * 配送方式（0-配送中心配送；1-委托配送）
     */
    @ApiModelProperty(value = "配送方式（0-配送中心配送；1-委托配送）")
    private Integer deliveryMode;

    /**
     * 补货政策（0-动态存量补货；1-安全库存补货；2-人工补货；3-禁止补货）
     */
    @ApiModelProperty(value = "补货政策（0-动态存量补货；1-安全库存补货；2-人工补货；3-禁止补货）")
    private Integer replenishmentPolicy;

    /**
     * 最小采购批量
     */
    @ApiModelProperty(value = "最小采购批量")
    private BigDecimal minimumPurchasingBatch;

    /**
     * 最小配货批量
     */
    @ApiModelProperty(value = "最小配货批量")
    private BigDecimal minimumDistributionBatch;

    /**
     * 近效期
     */
    @ApiModelProperty(value = "近效期")
    private Integer nearEffectPeriod;

    /**
     * 近效期单位（0-天；1-月；2-年）
     */
    @ApiModelProperty(value = "近效期单位（0-天；1-月；2-年）")
    private Integer nearEffectPeriodUnit;

    /**
     * 滞销周期
     */
    @ApiModelProperty(value = "滞销周期")
    private Integer unsalableCycle;

    /**
     * 滞销周期单位（0-天；1-月；2-年）
     */
    @ApiModelProperty(value = "滞销周期单位（0-天；1-月；2-年）")
    private Integer unsalableCycleUnit;

    /**
     * 养护类型（0-常规养护；1-重点养护）
     */
    @ApiModelProperty(value = "养护类型（0-常规养护；1-重点养护）")
    private Integer maintenanceType;

    /**
     * 养护周期
     */
    @ApiModelProperty(value = "养护周期")
    private Integer maintenanceCycle;

    /**
     * 养护周期单位（0-天；1-月；2-年）
     */
    @ApiModelProperty(value = "养护周期单位（0-天；1-月；2-年）")
    private Integer maintenanceCycleUnit;

    /**
     * 养护标准（养护措施ID集合）
     */
    @ApiModelProperty(value = "养护标准（养护措施ID集合）")
    private String maintenanceMeasureIds;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getInbagQuantity() {
        return inbagQuantity;
    }

    public void setInbagQuantity(BigDecimal inbagQuantity) {
        this.inbagQuantity = inbagQuantity;
    }

    public BigDecimal getBigbagQuantity() {
        return bigbagQuantity;
    }

    public void setBigbagQuantity(BigDecimal bigbagQuantity) {
        this.bigbagQuantity = bigbagQuantity;
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

    public Integer getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(Integer deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public Integer getReplenishmentPolicy() {
        return replenishmentPolicy;
    }

    public void setReplenishmentPolicy(Integer replenishmentPolicy) {
        this.replenishmentPolicy = replenishmentPolicy;
    }

    public BigDecimal getMinimumPurchasingBatch() {
        return minimumPurchasingBatch;
    }

    public void setMinimumPurchasingBatch(BigDecimal minimumPurchasingBatch) {
        this.minimumPurchasingBatch = minimumPurchasingBatch;
    }

    public BigDecimal getMinimumDistributionBatch() {
        return minimumDistributionBatch;
    }

    public void setMinimumDistributionBatch(BigDecimal minimumDistributionBatch) {
        this.minimumDistributionBatch = minimumDistributionBatch;
    }

    public Integer getNearEffectPeriod() {
        return nearEffectPeriod;
    }

    public void setNearEffectPeriod(Integer nearEffectPeriod) {
        this.nearEffectPeriod = nearEffectPeriod;
    }

    public Integer getNearEffectPeriodUnit() {
        return nearEffectPeriodUnit;
    }

    public void setNearEffectPeriodUnit(Integer nearEffectPeriodUnit) {
        this.nearEffectPeriodUnit = nearEffectPeriodUnit;
    }

    public Integer getUnsalableCycle() {
        return unsalableCycle;
    }

    public void setUnsalableCycle(Integer unsalableCycle) {
        this.unsalableCycle = unsalableCycle;
    }

    public Integer getUnsalableCycleUnit() {
        return unsalableCycleUnit;
    }

    public void setUnsalableCycleUnit(Integer unsalableCycleUnit) {
        this.unsalableCycleUnit = unsalableCycleUnit;
    }

    public Integer getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(Integer maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public Integer getMaintenanceCycle() {
        return maintenanceCycle;
    }

    public void setMaintenanceCycle(Integer maintenanceCycle) {
        this.maintenanceCycle = maintenanceCycle;
    }

    public Integer getMaintenanceCycleUnit() {
        return maintenanceCycleUnit;
    }

    public void setMaintenanceCycleUnit(Integer maintenanceCycleUnit) {
        this.maintenanceCycleUnit = maintenanceCycleUnit;
    }

    public String getMaintenanceMeasureIds() {
        return maintenanceMeasureIds;
    }

    public void setMaintenanceMeasureIds(String maintenanceMeasureIds) {
        this.maintenanceMeasureIds = maintenanceMeasureIds;
    }

    @Override
    public String toString() {
        return "GoodsStorageDataVO[" +
                "goodsId=" + goodsId +
                ", inbagQuantity=" + inbagQuantity +
                ", bigbagQuantity=" + bigbagQuantity +
                ", smallbagLength=" + smallbagLength +
                ", smallbagWidth=" + smallbagWidth +
                ", smallbagHeight=" + smallbagHeight +
                ", smallbagVolume=" + smallbagVolume +
                ", smallbagWeight=" + smallbagWeight +
                ", inbagLength=" + inbagLength +
                ", inbagWidth=" + inbagWidth +
                ", inbagHeight=" + inbagHeight +
                ", inbagVolume=" + inbagVolume +
                ", inbagWeight=" + inbagWeight +
                ", bigbagLength=" + bigbagLength +
                ", bigbagWidth=" + bigbagWidth +
                ", bigbagHeight=" + bigbagHeight +
                ", bigbagVolume=" + bigbagVolume +
                ", bigbagWeight=" + bigbagWeight +
                ", deliveryMode=" + deliveryMode +
                ", replenishmentPolicy=" + replenishmentPolicy +
                ", minimumPurchasingBatch=" + minimumPurchasingBatch +
                ", minimumDistributionBatch=" + minimumDistributionBatch +
                ", nearEffectPeriod=" + nearEffectPeriod +
                ", nearEffectPeriodUnit=" + nearEffectPeriodUnit +
                ", unsalableCycle=" + unsalableCycle +
                ", unsalableCycleUnit=" + unsalableCycleUnit +
                ", maintenanceType=" + maintenanceType +
                ", maintenanceCycle=" + maintenanceCycle +
                ", maintenanceCycleUnit=" + maintenanceCycleUnit +
                ", maintenanceMeasureIds='" + maintenanceMeasureIds + '\'' +
                ']';
    }
}
