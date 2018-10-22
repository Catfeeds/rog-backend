package com.rograndec.feijiayun.chain.business.purchase.instorage.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PurchaseInstorageExcelVO implements Serializable{

    /**
     * 商品编码
     */
    @ApiModelProperty(required = true, value = "商品编码")
    private String goodsCode;

    /**
     * 商品名称
     */
    @ApiModelProperty(required = true, value = "商品名称")
    private String goodsName;

    /**
     * 剂型名称
     */
    @ApiModelProperty(required = true, value = "剂型名称")
    private String dosageName;

    /**
     * 商品规格
     */
    @ApiModelProperty(required = true, value = "商品规格")
    private String goodsSpecification;

    /**
     * 生产厂商
     */
    @ApiModelProperty(required = true, value = "生产厂商")
    private String manufacturer;

    /**
     * 库存计量单位名称
     */
    @ApiModelProperty(required = true, value = "库存计量单位名称")
    private String unitName;

    @ApiModelProperty(value = "质量状况：\n" +
            " job_area=0，含义为“合格品”；\n" +
            " job_area=2，含义为“不合格品”；\n"
    )
    private String jobArea;

    /**
     * 批号
     */
    @ApiModelProperty(required = true, value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(required = true, value = "生产日期")
    private String productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(required = true, value = "有效期")
    private String validDate;

    /**
     * 货位名称
     */
    @ApiModelProperty(required = true, value = "货位名称")
    private String shelfName;

    /**
     * 入库数量
     */
    @ApiModelProperty(required = true, value = "入库数量")
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(required = true, value = "单价")
    private BigDecimal unitPrice;

    /**
     * 行折扣（%）
     */
    @ApiModelProperty(required = true, value = "行折扣（%）")
    private String lineDiscount;

    /**
     * 金额（整单优惠前金额）
     */
    @ApiModelProperty(required = true, value = "金额（整单优惠前金额）")
    private BigDecimal amount;

    /**
     * 进项税
     */
    @ApiModelProperty(required = true, value = "进项税")
    private String taxRate;

    /**
     * 不含税实际单价
     */
    @ApiModelProperty(required = true, value = "不含税实际单价")
    private BigDecimal notaxRealPrice;

    /**
     * 不含税实际金额
     */
    @ApiModelProperty(required = true, value = "不含税实际金额")
    private BigDecimal notaxRealAmount;

    /**
     * 税额
     */
    @ApiModelProperty(required = true, value = "税额")
    private BigDecimal taxAmount;

    /**
     * 备注
     */
    @ApiModelProperty(required = true, value = "备注")
    private String remark;

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getJobArea() {
        return jobArea;
    }

    public void setJobArea(String jobArea) {
        this.jobArea = jobArea;
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

    @Override
    public String toString() {
        return "PurchaseInstorageExcelVO{" +
                "goodsCode='" + goodsCode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", unitName='" + unitName + '\'' +
                ", jobArea='" + jobArea + '\'' +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate='" + productDate + '\'' +
                ", validDate='" + validDate + '\'' +
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
                '}';
    }

    public static PurchaseInstorageExcelVO convertTO(PurchaseInstorageShelfVO shelfVO, PurchaseInstorageDetailVO detailVO) {
        PurchaseInstorageExcelVO vo = new PurchaseInstorageExcelVO();
        vo.setGoodsCode(detailVO.getGoodsCode());
        vo.setGoodsName(detailVO.getGoodsName());
        vo.setDosageName(detailVO.getDosageName());
        vo.setGoodsSpecification(detailVO.getGoodsSpecification());
        vo.setManufacturer(detailVO.getManufacturer());
        vo.setUnitName(detailVO.getUnitName());
        vo.setJobArea(shelfVO.getJobAreaName());
        vo.setLotNumber(shelfVO.getLotNumber());
        vo.setProductDate(new SimpleDateFormat("yyyy-MM-dd").format(shelfVO.getProductDate()));
        vo.setValidDate(new SimpleDateFormat("yyyy-MM-dd").format(shelfVO.getValidDate()));
        vo.setShelfName(shelfVO.getShelfName());
        vo.setQuantity(shelfVO.getQuantity());
        vo.setUnitPrice(shelfVO.getUnitPrice());
        vo.setLineDiscount((shelfVO.getLineDiscount() == null ? "无数据" : shelfVO.getLineDiscount()) + "%");
        vo.setAmount(shelfVO.getAmount());
        vo.setTaxRate((shelfVO.getTaxRate() == null ? "无数据" : shelfVO.getTaxRate()) + "%");
        vo.setNotaxRealPrice(shelfVO.getNotaxRealPrice());
        vo.setNotaxRealAmount(shelfVO.getNotaxRealAmount());
        vo.setTaxAmount(shelfVO.getTaxAmount());
        vo.setRemark(shelfVO.getRemark());
        return vo;
    }

}
