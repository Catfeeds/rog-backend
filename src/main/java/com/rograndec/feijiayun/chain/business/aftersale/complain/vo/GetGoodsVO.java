package com.rograndec.feijiayun.chain.business.aftersale.complain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zeshi.sun on 2017/9/7.
 */

@ApiModel(value = "SelectGoodsVO", description = "查询货品信息对象")
public class GetGoodsVO implements Serializable {

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
                ']';
    }
}
