package com.rograndec.feijiayun.chain.business.goods.pharmacy.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zeshi.sun on 2017/9/7.
 */

public class SelectGoodsVO implements Serializable {

    /**
     * 商品ID
     */
    @ApiModelProperty(required = true, value = "商品ID")
    private Long id;

    /**
     * 商品编码
     */
    @ApiModelProperty(required = true, value = "商品编码")
    private String code;

    /**
     * 通用名称
     */
    @ApiModelProperty(required = true, value = "通用名称")
    private String genericName;


    /**
     * 剂型
     */
    @ApiModelProperty(required = false, value = "剂型")
    private String dosageName;

    /**
     * 剂型ID
     */
    @ApiModelProperty(required = false, value = "剂型ID")
    private long dosageId;

    /**
     * 规格
     */
    @ApiModelProperty(required = false, value = "规格")
    private String specification;

    /**
     * 生产厂商
     */
    @ApiModelProperty(required = false, value = "生产厂商")
    private String manufacturer;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(required = false, value = "生产厂商ID")
    private Long manufacturerId;

    /**
     * 单位
     */
    @ApiModelProperty(required = false, value = "单位")
    private String unitName;

    /**
     * 单位ID
     */
    @ApiModelProperty(required = false, value = "单位ID")
    private Long unitId;

    /**
     * 专属药品ID
     */
    @ApiModelProperty(required = false, value = "专属药品ID")
    private Long inChargeDrug;

    /**
     * 含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）
     */
    @ApiModelProperty(required = true, value = "含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）")
    private Integer formulationType;


    @ApiModelProperty(required = false, value = "所属企业")
    private Long ownerId;

    private static final long serialVersionUID = 1L;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public long getDosageId() {
        return dosageId;
    }

    public void setDosageId(long dosageId) {
        this.dosageId = dosageId;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getInChargeDrug() {
        return inChargeDrug;
    }

    public void setInChargeDrug(Long inChargeDrug) {
        this.inChargeDrug = inChargeDrug;
    }

    public Integer getFormulationType() {
        return formulationType;
    }

    public void setFormulationType(Integer formulationType) {
        this.formulationType = formulationType;
    }

	@Override
    public String toString() {
        return "SelectGoodsVO[" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", genericName='" + genericName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", dosageId='" + dosageId + '\'' +
                ", specification='" + specification + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", manufacturerId='" + manufacturerId + '\'' +
                ", unitName='" + unitName + '\'' +
                ", unitId='" + unitId + '\'' +
                ", inChargeDrug='" + inChargeDrug + '\'' +
                ", formulationType='" + formulationType + '\'' +
                ']';
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

}
