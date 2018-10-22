package com.rograndec.feijiayun.chain.business.storage.maintance.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "SelectMaintanceGoodsVO", description = "养护单选择商品")
public class SelectMaintanceGoodsVO implements Serializable {
    /**
     * @Description: TODO(描述funcion功能)
     * author yuting.li
     * @date 2017年9月25日 下午8:04:43
     * @version 1.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    @ApiModelProperty(value = "条形码")
    private String barcode;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;

    /**
     * 剂型ID
     */
    @ApiModelProperty(value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private String goodsSpecification;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    /**
     * 商品产地
     */
    @ApiModelProperty(value = "商品产地")
    private String goodsPlace;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    @ApiModelProperty(value = "批号Id")
    private Long lotId;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validUntil;

    @ApiModelProperty(value = "货位ID")
    private Long shelfId;

    @ApiModelProperty(value = "货位名称")
    private String shelfName;
    /**
     * 货位质量状态描述
     */
    @ApiModelProperty(value = "货位质量状态描述")
    private String shelfStatusDesc;

    /**
     * 库存(商品可用数量)
     */
    @ApiModelProperty(value = "库存可用数量")
    private BigDecimal usableQuantity;

    /**
     * 商品性质 值为1 是拆零商品
     *
     * @return
     */
    @ApiModelProperty(value = "商品性质 值为1 是拆零商品")
    private Integer goodsNature;

    /**
     * 商品属性（0-成药；1-中药材；2-中药饮片
     *
     * @return
     */
    @ApiModelProperty(value = "商品属性（0-成药；1-中药材；2-中药饮片)")
    private Integer goodsAttribute;

    /**
     * 养护类型（0-常规养护；1-重点养护）
     *
     * @return
     */
    @ApiModelProperty(value = "养护类型（0-常规养护；1-重点养护)")
    private Integer maintenanceType;

    /**
     * 养护周期(天数)
     *
     * @return
     */
    @ApiModelProperty(value = "养护周期(天数)")
    private Long maintenanceCycle;

    /**
     * 上次养护时间
     *
     * @return
     */
    @ApiModelProperty(value = "上次养护时间")
    private Date lastMaintainTime;

    /**
     * 商品设置的近效期天数
     *
     * @return
     */
    @ApiModelProperty(value = "商品设置的近效期天数")
    private Long nearEffectPeriod;

    /**
     * 批号表里的失效日期距离当前日期的天数
     *
     * @return
     */
    @ApiModelProperty(value = "批号表里的失效日期距离当前日期的天数")
    private Long validDays;

    /**
     * 库区ID
     *
     * @return
     */
    @ApiModelProperty(value = "库区ID")
    private Long warehouseAreaId;

    @ApiModelProperty(value = "近效期单位")
    private Integer maintenanceCycleUnit;

    @ApiModelProperty(value = "养护周期")
    private Integer nearEffectPeriodUnit;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName;
    }

    public Long getDosageId() {
        return dosageId;
    }

    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public BigDecimal getUsableQuantity() {
        return usableQuantity;
    }

    public void setUsableQuantity(BigDecimal usableQuantity) {
        this.usableQuantity = usableQuantity;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsNature() {
        return goodsNature;
    }

    public void setGoodsNature(Integer goodsNature) {
        this.goodsNature = goodsNature;
    }

    public Integer getGoodsAttribute() {
        return goodsAttribute;
    }

    public void setGoodsAttribute(Integer goodsAttribute) {
        this.goodsAttribute = goodsAttribute;
    }

    public Integer getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(Integer maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public Long getMaintenanceCycle() {
        return maintenanceCycle;
    }

    public void setMaintenanceCycle(Long maintenanceCycle) {
        this.maintenanceCycle = maintenanceCycle;
    }

    public Long getNearEffectPeriod() {
        return nearEffectPeriod;
    }

    public void setNearEffectPeriod(Long nearEffectPeriod) {
        this.nearEffectPeriod = nearEffectPeriod;
    }

    public Long getValidDays() {
        return validDays;
    }

    public void setValidDays(Long validDays) {
        this.validDays = validDays;
    }

    public Long getWarehouseAreaId() {
        return warehouseAreaId;
    }

    public void setWarehouseAreaId(Long warehouseAreaId) {
        this.warehouseAreaId = warehouseAreaId;
    }

    public Date getLastMaintainTime() {
        return lastMaintainTime;
    }

    public void setLastMaintainTime(Date lastMaintainTime) {
        this.lastMaintainTime = lastMaintainTime;
    }

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

    public Integer getMaintenanceCycleUnit() {
        return maintenanceCycleUnit;
    }

    public void setMaintenanceCycleUnit(Integer maintenanceCycleUnit) {
        this.maintenanceCycleUnit = maintenanceCycleUnit;
    }

    public Integer getNearEffectPeriodUnit() {
        return nearEffectPeriodUnit;
    }

    public void setNearEffectPeriodUnit(Integer nearEffectPeriodUnit) {
        this.nearEffectPeriodUnit = nearEffectPeriodUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SelectMaintanceGoodsVO that = (SelectMaintanceGoodsVO) o;

        if (goodsId != null ? !goodsId.equals(that.goodsId) : that.goodsId != null) return false;
        if (goodsCode != null ? !goodsCode.equals(that.goodsCode) : that.goodsCode != null) return false;
        if (barcode != null ? !barcode.equals(that.barcode) : that.barcode != null) return false;
        if (goodsName != null ? !goodsName.equals(that.goodsName) : that.goodsName != null) return false;
        if (goodsGenericName != null ? !goodsGenericName.equals(that.goodsGenericName) : that.goodsGenericName != null)
            return false;
        if (dosageId != null ? !dosageId.equals(that.dosageId) : that.dosageId != null) return false;
        if (dosageName != null ? !dosageName.equals(that.dosageName) : that.dosageName != null) return false;
        if (goodsSpecification != null ? !goodsSpecification.equals(that.goodsSpecification) : that.goodsSpecification != null)
            return false;
        if (manufacturerId != null ? !manufacturerId.equals(that.manufacturerId) : that.manufacturerId != null)
            return false;
        if (manufacturer != null ? !manufacturer.equals(that.manufacturer) : that.manufacturer != null) return false;
        if (goodsPlace != null ? !goodsPlace.equals(that.goodsPlace) : that.goodsPlace != null) return false;
        if (unitId != null ? !unitId.equals(that.unitId) : that.unitId != null) return false;
        if (unitName != null ? !unitName.equals(that.unitName) : that.unitName != null) return false;
        if (approvalNumber != null ? !approvalNumber.equals(that.approvalNumber) : that.approvalNumber != null)
            return false;
        if (lotNumber != null ? !lotNumber.equals(that.lotNumber) : that.lotNumber != null) return false;
        if (lotId != null ? !lotId.equals(that.lotId) : that.lotId != null) return false;
        if (productDate != null ? !productDate.equals(that.productDate) : that.productDate != null) return false;
        if (validUntil != null ? !validUntil.equals(that.validUntil) : that.validUntil != null) return false;
        if (shelfId != null ? !shelfId.equals(that.shelfId) : that.shelfId != null) return false;
        if (shelfName != null ? !shelfName.equals(that.shelfName) : that.shelfName != null) return false;
        if (shelfStatusDesc != null ? !shelfStatusDesc.equals(that.shelfStatusDesc) : that.shelfStatusDesc != null) return false;
        if (usableQuantity != null ? !usableQuantity.equals(that.usableQuantity) : that.usableQuantity != null)
            return false;
        if (goodsNature != null ? !goodsNature.equals(that.goodsNature) : that.goodsNature != null) return false;
        if (goodsAttribute != null ? !goodsAttribute.equals(that.goodsAttribute) : that.goodsAttribute != null)
            return false;
        if (maintenanceType != null ? !maintenanceType.equals(that.maintenanceType) : that.maintenanceType != null)
            return false;
        if (maintenanceCycle != null ? !maintenanceCycle.equals(that.maintenanceCycle) : that.maintenanceCycle != null)
            return false;
        if (lastMaintainTime != null ? !lastMaintainTime.equals(that.lastMaintainTime) : that.lastMaintainTime != null)
            return false;
        if (nearEffectPeriod != null ? !nearEffectPeriod.equals(that.nearEffectPeriod) : that.nearEffectPeriod != null)
            return false;
        if (validDays != null ? !validDays.equals(that.validDays) : that.validDays != null) return false;
        return warehouseAreaId != null ? warehouseAreaId.equals(that.warehouseAreaId) : that.warehouseAreaId == null;
    }

    @Override
    public int hashCode() {
        int result = goodsId != null ? goodsId.hashCode() : 0;
        result = 31 * result + (goodsCode != null ? goodsCode.hashCode() : 0);
        result = 31 * result + (barcode != null ? barcode.hashCode() : 0);
        result = 31 * result + (goodsName != null ? goodsName.hashCode() : 0);
        result = 31 * result + (goodsGenericName != null ? goodsGenericName.hashCode() : 0);
        result = 31 * result + (dosageId != null ? dosageId.hashCode() : 0);
        result = 31 * result + (dosageName != null ? dosageName.hashCode() : 0);
        result = 31 * result + (goodsSpecification != null ? goodsSpecification.hashCode() : 0);
        result = 31 * result + (manufacturerId != null ? manufacturerId.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (goodsPlace != null ? goodsPlace.hashCode() : 0);
        result = 31 * result + (unitId != null ? unitId.hashCode() : 0);
        result = 31 * result + (unitName != null ? unitName.hashCode() : 0);
        result = 31 * result + (approvalNumber != null ? approvalNumber.hashCode() : 0);
        result = 31 * result + (lotNumber != null ? lotNumber.hashCode() : 0);
        result = 31 * result + (lotId != null ? lotId.hashCode() : 0);
        result = 31 * result + (productDate != null ? productDate.hashCode() : 0);
        result = 31 * result + (validUntil != null ? validUntil.hashCode() : 0);
        result = 31 * result + (shelfId != null ? shelfId.hashCode() : 0);
        result = 31 * result + (shelfName != null ? shelfName.hashCode() : 0);
        result = 31 * result + (shelfStatusDesc != null ? shelfStatusDesc.hashCode() : 0);
        result = 31 * result + (usableQuantity != null ? usableQuantity.hashCode() : 0);
        result = 31 * result + (goodsNature != null ? goodsNature.hashCode() : 0);
        result = 31 * result + (goodsAttribute != null ? goodsAttribute.hashCode() : 0);
        result = 31 * result + (maintenanceType != null ? maintenanceType.hashCode() : 0);
        result = 31 * result + (maintenanceCycle != null ? maintenanceCycle.hashCode() : 0);
        result = 31 * result + (lastMaintainTime != null ? lastMaintainTime.hashCode() : 0);
        result = 31 * result + (nearEffectPeriod != null ? nearEffectPeriod.hashCode() : 0);
        result = 31 * result + (validDays != null ? validDays.hashCode() : 0);
        result = 31 * result + (warehouseAreaId != null ? warehouseAreaId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SelectMaintanceGoodsVO{" +
                "goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", barcode='" + barcode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageId=" + dosageId +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", manufacturer='" + manufacturer + '\'' +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", unitId=" + unitId +
                ", unitName='" + unitName + '\'' +
                ", approvalNumber='" + approvalNumber + '\'' +
                ", lotNumber='" + lotNumber + '\'' +
                ", lotId=" + lotId +
                ", productDate=" + productDate +
                ", validUntil=" + validUntil +
                ", shelfId=" + shelfId +
                ", shelfName='" + shelfName + '\'' +
                ", shelfStatusDesc='" + shelfStatusDesc + '\'' +
                ", usableQuantity=" + usableQuantity +
                ", goodsNature=" + goodsNature +
                ", goodsAttribute=" + goodsAttribute +
                ", maintenanceType=" + maintenanceType +
                ", maintenanceCycle=" + maintenanceCycle +
                ", lastMaintainTime=" + lastMaintainTime +
                ", nearEffectPeriod=" + nearEffectPeriod +
                ", validDays=" + validDays +
                ", warehouseAreaId=" + warehouseAreaId +
                ", maintenanceCycleUnit=" + maintenanceCycleUnit +
                ", nearEffectPeriodUnit=" + nearEffectPeriodUnit +
                '}';
    }
}