package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.rograndec.feijiayun.chain.common.constant.GoodsQualityPeriodUnit;
import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/26 <br/>
 * 描述：总部-配送单-商品信息
 */
public class DistrSendGoodsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "商品主键id")
    private Long id;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String code;

    /**
     * 通用名称
     */
    @ApiModelProperty(value = "通用名称")
    private String genericName;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String name;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private String specification;

    /**
     * 库存计量单位名称
     */
    @ApiModelProperty(value = "库存计量单位名称")
    private String unitName;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value = " 生产厂商ID")
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    /**
     * 产地
     */
    @ApiModelProperty(value = "产地")
    private String place;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;

    /**
     * 条形码
     */
    @ApiModelProperty(value = " 条形码")
    private String barcode;

    /**
     * 总部库存可用量
     */
    @ApiModelProperty(value = "总部库存可用量")
    private BigDecimal parentUsableQuantity;

    /**
     * 要货门店库存可用量
     */
    @ApiModelProperty(value = "要货门店库存可用量")
    private BigDecimal childUsableQuantity = new BigDecimal(0);


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

    @ApiModelProperty(value = "近效期单位描述（0-天；1-月；2-年）")
    private String nearEffectPeriodUnitDesc;

    public String getNearEffectPeriodUnitDesc() {
        if(nearEffectPeriodUnit == null){
            return "";
        }
        return GoodsQualityPeriodUnit.getName(nearEffectPeriodUnit);
    }

    public void setNearEffectPeriodUnitDesc(String nearEffectPeriodUnitDesc) {
        this.nearEffectPeriodUnitDesc = nearEffectPeriodUnitDesc;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public BigDecimal getParentUsableQuantity() {
        return parentUsableQuantity;
    }

    public void setParentUsableQuantity(BigDecimal parentUsableQuantity) {
        this.parentUsableQuantity = parentUsableQuantity;
    }

    public BigDecimal getChildUsableQuantity() {
        return childUsableQuantity;
    }

    public void setChildUsableQuantity(BigDecimal childUsableQuantity) {
        this.childUsableQuantity = childUsableQuantity;
    }

    @Override
    public String toString() {
        return "DistrSendGoodsVO{" +
                "code='" + code + '\'' +
                ", genericName='" + genericName + '\'' +
                ", name='" + name + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", specification='" + specification + '\'' +
                ", unitName='" + unitName + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", manufacturer='" + manufacturer + '\'' +
                ", place='" + place + '\'' +
                ", approvalNumber='" + approvalNumber + '\'' +
                ", barcode='" + barcode + '\'' +
                ", parentUsableQuantity=" + parentUsableQuantity +
                ", childUsableQuantity=" + childUsableQuantity +
                '}';
    }
}
