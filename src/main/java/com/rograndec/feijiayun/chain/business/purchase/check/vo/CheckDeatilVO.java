package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zeshi.sun on 2017/9/18.
 */
public class CheckDeatilVO implements Serializable {

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码", required = true)
    private String goodsCode;

    /**
     * 通用名称
     */
    @ApiModelProperty(value = "通用名称", required = true)
    private String goodsGenericName;

    /**
     * 剂型
     */
    @ApiModelProperty(value = "剂型", required = true)
    private String dosageName;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格", required = true)
    private String goodsSpecification;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商", required = true)
    private String manufacturer;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量", required = true)
    private BigDecimal quantity;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", required = true)
    private Long goodsId;

    /**
     * 条形码
     */
    @ApiModelProperty(value = "条形码", required = true)
    private String barcode;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称", required = true)
    private String goodsName;

    /**
     * 剂型ID
     */
    @ApiModelProperty(value = "剂型ID", required = true)
    private Long dosageId;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID", required = true)
    private Long unitId;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value = "生产厂商ID", required = true)
    private Long manufacturerId;

    /**
     * 商品产地
     */
    @ApiModelProperty(value = "商品产地", required = true)
    private String goodsPlace;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号", required = true)
    private String approvalNumber;

    /**
     * 基础单据明细ID
     */
    @ApiModelProperty(value = "基础单据明细ID", required = true)
    private Long baseOrderDtlId;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID", required = true)
    private Long baseOrderId;

    /**
     * 是否为特殊管理药品：（0-是  1-否）
     */
    @ApiModelProperty(value = "是否为特殊管理药品：（0-是  1-否）", required = true)
    private Integer specialDrug;

    /**
     * 近效期
     */
    @ApiModelProperty(value = "近效期", required = true)
    private Integer nearEffectPeriod;

    /**
     * 近效期单位（0-天；1-月；2-年）
     */
    @ApiModelProperty(value = "近效期单位（0-天；1-月；2-年）", required = true)
    private Integer nearEffectPeriodUnit;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称", required = true)
    private String unitName;

    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量", required = true)
    private BigDecimal qualifiedQuantity;

    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量", required = true)
    private BigDecimal unqualifiedQuantity;

    /**
     * 调用草稿时使用
     */
    List<SaveCheckDetailTwoVO> saveCheckDetailTwoVO;


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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public Long getDosageId() {
        return dosageId;
    }

    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public Long getBaseOrderDtlId() {
        return baseOrderDtlId;
    }

    public void setBaseOrderDtlId(Long baseOrderDtlId) {
        this.baseOrderDtlId = baseOrderDtlId;
    }

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public Integer getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(Integer specialDrug) {
        this.specialDrug = specialDrug;
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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public BigDecimal getQualifiedQuantity() {
        return qualifiedQuantity;
    }

    public void setQualifiedQuantity(BigDecimal qualifiedQuantity) {
        this.qualifiedQuantity = qualifiedQuantity;
    }

    public BigDecimal getUnqualifiedQuantity() {
        return unqualifiedQuantity;
    }

    public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) {
        this.unqualifiedQuantity = unqualifiedQuantity;
    }

    public List<SaveCheckDetailTwoVO> getSaveCheckDetailTwoVO() {
        return saveCheckDetailTwoVO;
    }

    public void setSaveCheckDetailTwoVO(List<SaveCheckDetailTwoVO> saveCheckDetailTwoVO) {
        this.saveCheckDetailTwoVO = saveCheckDetailTwoVO;
    }

    @Override
    public String toString() {
        return "CheckDeatilVO[" +
                "goodsCode='" + goodsCode + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", quantity=" + quantity +
                ", goodsId=" + goodsId +
                ", barcode='" + barcode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", dosageId=" + dosageId +
                ", unitId=" + unitId +
                ", manufacturerId=" + manufacturerId +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", approvalNumber='" + approvalNumber + '\'' +
                ", baseOrderDtlId=" + baseOrderDtlId +
                ", baseOrderId=" + baseOrderId +
                ", specialDrug=" + specialDrug +
                ", nearEffectPeriod=" + nearEffectPeriod +
                ", nearEffectPeriodUnit=" + nearEffectPeriodUnit +
                ", unitName='" + unitName + '\'' +
                ", qualifiedQuantity=" + qualifiedQuantity +
                ", unqualifiedQuantity=" + unqualifiedQuantity +
                ", saveCheckDetailTwoVO=" + saveCheckDetailTwoVO +
                ']';
    }
}
