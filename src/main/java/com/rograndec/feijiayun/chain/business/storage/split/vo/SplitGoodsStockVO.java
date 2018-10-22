package com.rograndec.feijiayun.chain.business.storage.split.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/29 <br/>
 * 描述：商品拆零-可拆零商品列表查询
 */
public class SplitGoodsStockVO implements Serializable {

    private static final long serialVersionUID = 724616128505993339L;

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

    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

    @ApiModelProperty(value = "生产厂商ID")
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
    private String goodsPlace;

    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;

    /**
     * 批号ID
     */
    @ApiModelProperty(value = "批号ID")
    private Long lotId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validDate;

    /**
     * 源货位ID
     */
    @ApiModelProperty(value = "源货位ID")
    private Long shelfId;

    /**
     * 源货位名称
     */
    @ApiModelProperty(value = "源货位名称")
    private String shelfName;

    /**
     * 源货位质量状况
     */
    @ApiModelProperty(value = "源货位质量状况")
    private String shelfStatusDesc;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 拆零商品id
     */
    @ApiModelProperty(value = "拆零商品id")
    private Long splitGoodsId;

    /**
     * 拆零商品编码
     */
    @ApiModelProperty(value = "拆零商品编码")
    private String splitGoodsCode;

    /**
     * 拆零商品编码
     */
    @ApiModelProperty(value = "拆零商品名称")
    private String splitGoodsName;

    /**
     * 拆零商品规格
     */
    @ApiModelProperty(value = "拆零商品规格")
    private String splitSpecification;

    /**
     * 拆零单位
     */
    @ApiModelProperty(value = "拆零单位")
    private String splitUnitName;

    /**
     * 拆零单位
     */
    @ApiModelProperty(value = "拆零单位")
    private BigDecimal splitQuantity;

    /**
     * 拆零源货位ID
     */
    @ApiModelProperty(value = "拆零源货位ID")
    private Long splitShelfId;

    /**
     * 拆零货位名称
     */
    @ApiModelProperty(value = "拆零货位名称")
    private String splitShelfName;

    /**
     * 拆零货位质量状况
     */
    @ApiModelProperty(value = "拆零货位质量状况")
    private String splitShelfStatusDesc;

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

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
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

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
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

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Long getSplitGoodsId() {
        return splitGoodsId;
    }

    public void setSplitGoodsId(Long splitGoodsId) {
        this.splitGoodsId = splitGoodsId;
    }

    public String getSplitGoodsCode() {
        return splitGoodsCode;
    }

    public void setSplitGoodsCode(String splitGoodsCode) {
        this.splitGoodsCode = splitGoodsCode;
    }

    public String getSplitGoodsName() {
        return splitGoodsName;
    }

    public void setSplitGoodsName(String splitGoodsName) {
        this.splitGoodsName = splitGoodsName;
    }

    public String getSplitSpecification() {
        return splitSpecification;
    }

    public void setSplitSpecification(String splitSpecification) {
        this.splitSpecification = splitSpecification;
    }

    public String getSplitUnitName() {
        return splitUnitName;
    }

    public void setSplitUnitName(String splitUnitName) {
        this.splitUnitName = splitUnitName;
    }

    public BigDecimal getSplitQuantity() {
        return splitQuantity;
    }

    public void setSplitQuantity(BigDecimal splitQuantity) {
        this.splitQuantity = splitQuantity;
    }

    public Long getSplitShelfId() {
        return splitShelfId;
    }

    public void setSplitShelfId(Long splitShelfId) {
        this.splitShelfId = splitShelfId;
    }

    public String getSplitShelfName() {
        return splitShelfName;
    }

    public void setSplitShelfName(String splitShelfName) {
        this.splitShelfName = splitShelfName;
    }

    public String getSplitShelfStatusDesc() {
        return splitShelfStatusDesc;
    }

    public void setSplitShelfStatusDesc(String splitShelfStatusDesc) {
        this.splitShelfStatusDesc = splitShelfStatusDesc;
    }

    @Override
    public String toString() {
        return "SplitGoodsStockVO{" +
                "goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", barcode='" + barcode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageId=" + dosageId +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", unitId=" + unitId +
                ", unitName='" + unitName + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", manufacturer='" + manufacturer + '\'' +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", approvalNumber='" + approvalNumber + '\'' +
                ", lotId=" + lotId +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate=" + productDate +
                ", validDate=" + validDate +
                ", shelfId=" + shelfId +
                ", shelfName='" + shelfName + '\'' +
                ", shelfStatusDesc='" + shelfStatusDesc + '\'' +
                ", quantity=" + quantity +
                ", splitGoodsId=" + splitGoodsId +
                ", splitGoodsCode='" + splitGoodsCode + '\'' +
                ", splitGoodsName='" + splitGoodsName + '\'' +
                ", splitSpecification='" + splitSpecification + '\'' +
                ", splitUnitName='" + splitUnitName + '\'' +
                ", splitQuantity=" + splitQuantity +
                '}';
    }
}
