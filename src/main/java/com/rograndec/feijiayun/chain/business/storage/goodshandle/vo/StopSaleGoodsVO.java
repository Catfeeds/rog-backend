package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by dudy on 2017/9/30.
 */
public class StopSaleGoodsVO  implements Serializable {


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
    private Long lotNumberId;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;
    @ApiModelProperty(value = "生产日期 yyyy-MM-dd")
    private String productDateStr;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validUntil;
    @ApiModelProperty(value = "有效期 yyyy-MM-dd")
    private String validUntilStr;

    @ApiModelProperty(value = "货位ID")
    private Long shelfId;

    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    /**
     * 库存(商品可用数量)
     */
    @ApiModelProperty(value = "库存可用数量")
    private BigDecimal usableQuantity;

    @ApiModelProperty(value = "库存Id")
    private Long stId;


    @ApiModelProperty(value = "税率")
    private String goodsTaxRate;
    @ApiModelProperty(value = "税率ID")
    private Long goodsTaxRateId;

    /**
     * 货位质量状态描述
     */
    @ApiModelProperty(value = "货位质量状态描述")
    private String shelfStatusDesc;

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

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

    public Long getLotNumberId() {
        return lotNumberId;
    }

    public void setLotNumberId(Long lotNumberId) {
        this.lotNumberId = lotNumberId;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public String getProductDateStr() {
        return productDateStr;
    }

    public void setProductDateStr(String productDateStr) {
        this.productDateStr = productDateStr;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public String getValidUntilStr() {
        return validUntilStr;
    }

    public void setValidUntilStr(String validUntilStr) {
        this.validUntilStr = validUntilStr;
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

    public BigDecimal getUsableQuantity() {
        return usableQuantity;
    }

    public void setUsableQuantity(BigDecimal usableQuantity) {
        this.usableQuantity = usableQuantity;
    }

    public Long getStId() {
        return stId;
    }

    public void setStId(Long stId) {
        this.stId = stId;
    }

    public String getGoodsTaxRate() {
        return goodsTaxRate;
    }

    public void setGoodsTaxRate(String goodsTaxRate) {
        this.goodsTaxRate = goodsTaxRate;
    }

    public Long getGoodsTaxRateId() {
        return goodsTaxRateId;
    }

    public void setGoodsTaxRateId(Long goodsTaxRateId) {
        this.goodsTaxRateId = goodsTaxRateId;
    }
}
