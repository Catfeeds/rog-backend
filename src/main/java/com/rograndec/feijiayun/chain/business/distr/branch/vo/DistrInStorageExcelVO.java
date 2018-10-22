package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DistrInStorageExcelVO implements Serializable{
    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;

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
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

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
     * 货位质量状态描述
     */
    @ApiModelProperty(value = "货位质量状态描述")
    private String shelfStatusDesc;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private String productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private String validDate;

    /**
     * 货位名称
     */
    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价---用于导出EXCEL")
    private BigDecimal unitPrice;

    /**
     * 行折扣（%）
     */
    @ApiModelProperty(value = "行折扣（%）---用于导出EXCEL")
    private String lineDiscount;

    /**
     * 金额（整单优惠前金额）
     */
    @ApiModelProperty(value = "金额（整单优惠前金额）---用于导出EXCEL")
    private BigDecimal amount;

    /**
     * 税率
     */
    @ApiModelProperty(value = "税率---用于导出EXCEL")
    private String taxRate;

    /**
     * 不含税实际单价
     */
    @ApiModelProperty(value = "不含税实际单价---用于导出EXCEL")
    private BigDecimal notaxRealPrice;

    /**
     * 不含税实际金额
     */
    @ApiModelProperty(value = "不含税实际金额---用于导出EXCEL")
    private BigDecimal notaxRealAmount;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额---用于导出EXCEL")
    private BigDecimal taxAmount;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注---用于导出EXCEL")
    private String remark;

    /**
     * 零售单价
     */
    @ApiModelProperty(value = "零售单价")
    private BigDecimal retailPrice;

    /**
     * 会员单价
     */
    @ApiModelProperty(value = "会员单价")
    private BigDecimal memberPrice;

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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
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

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getLineDiscount() {
        return lineDiscount;
    }

    public void setLineDiscount(String lineDiscount) {
        this.lineDiscount = lineDiscount;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }



    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getNotaxRealPrice() {
        return notaxRealPrice;
    }

    public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
        this.notaxRealPrice = notaxRealPrice;
    }

    public BigDecimal getNotaxRealAmount() {
        return notaxRealAmount;
    }

    public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
        this.notaxRealAmount = notaxRealAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(BigDecimal memberPrice) {
        this.memberPrice = memberPrice;
    }

    @Override
    public String toString() {
        return "DistrInStorageExcelVO{" +
                "goodsCode='" + goodsCode + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", unitName='" + unitName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", shelfStatusDesc='" + shelfStatusDesc + '\'' +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate=" + productDate +
                ", validDate=" + validDate +
                ", shelfName='" + shelfName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", lineDiscount=" + lineDiscount +
                ", amount=" + amount +
                ", taxRate=" + taxRate +
                ", notaxRealPrice=" + notaxRealPrice +
                ", notaxRealAmount=" + notaxRealAmount +
                ", taxAmount=" + taxAmount +
                ", remark='" + remark + '\'' +
                ", retailPrice=" + retailPrice +
                ", memberPrice=" + memberPrice +
                '}';
    }


    public static DistrInStorageExcelVO convertToVO(DistrInStorageShelfDtlVO shelfVO, DistrInstorageDtlVO dtlVO) {
        DistrInStorageExcelVO excelVO = new DistrInStorageExcelVO();
        excelVO.setGoodsCode(dtlVO.getGoodsCode());
        excelVO.setGoodsGenericName(dtlVO.getGoodsGenericName());
        excelVO.setDosageName(dtlVO.getDosageName());
        excelVO.setGoodsSpecification(dtlVO.getGoodsSpecification());
        excelVO.setUnitName(dtlVO.getUnitName());
        excelVO.setManufacturer(dtlVO.getManufacturer());
        excelVO.setGoodsPlace(dtlVO.getGoodsPlace());
        excelVO.setShelfStatusDesc(shelfVO.getShelfStatusDesc());
        excelVO.setLotNumber(shelfVO.getLotNumber());
        excelVO.setProductDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(shelfVO.getProductDate()));
        excelVO.setValidDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(shelfVO.getValidDate()));
        excelVO.setShelfName(shelfVO.getShelfName());
        excelVO.setQuantity(shelfVO.getQuantity());
        excelVO.setUnitPrice(shelfVO.getUnitPrice());
        excelVO.setLineDiscount((shelfVO.getLineDiscount() == null ? "无数据" : shelfVO.getLineDiscount()) + "%");
        excelVO.setAmount(shelfVO.getAmount());
        excelVO.setTaxRate((shelfVO.getTaxRate() == null ? "无数据" : shelfVO.getTaxRate()) + "%");
        excelVO.setNotaxRealPrice(shelfVO.getNotaxRealPrice());
        excelVO.setNotaxRealAmount(shelfVO.getNotaxRealAmount());
        excelVO.setTaxAmount(shelfVO.getTaxAmount());
        excelVO.setRemark(shelfVO.getRemark());
        excelVO.setRetailPrice(dtlVO.getRetailPrice());
        excelVO.setMemberPrice(dtlVO.getMemberPrice());
        return excelVO;
    }
}
