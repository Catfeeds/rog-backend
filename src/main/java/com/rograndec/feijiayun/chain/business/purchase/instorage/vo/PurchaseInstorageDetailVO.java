package com.rograndec.feijiayun.chain.business.purchase.instorage.vo;

import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel(value = "PurchaseInstorageDetailVO", description = "采购管理-采购入库-已入库详细对象")
public class PurchaseInstorageDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ApiModelProperty(required = true, value = "id")
    private Long id;

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
    private BigDecimal lineDiscount;

    /**
     * 金额（整单优惠前金额）
     */
    @ApiModelProperty(required = true, value = "金额（整单优惠前金额）")
    private BigDecimal amount;

    /**
     * 进项税
     */
    @ApiModelProperty(required = true, value = "进项税")
    private BigDecimal taxRate;

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

    /**
     * 相关货位信息
     */
    @ApiModelProperty(required = true, value = "相关货位信息集合")
    private List<PurchaseInstorageShelfVO> purchaseInstorageShelfVO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getLineDiscount() {
        return lineDiscount;
    }

    public void setLineDiscount(BigDecimal lineDiscount) {
        this.lineDiscount = lineDiscount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
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

    public List<PurchaseInstorageShelfVO> getPurchaseInstorageShelfVO() {
        return purchaseInstorageShelfVO;
    }

    public void setPurchaseInstorageShelfVO(List<PurchaseInstorageShelfVO> purchaseInstorageShelfVO) {
        this.purchaseInstorageShelfVO = purchaseInstorageShelfVO;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "PurchaseInstorageDetailVO{" +
                "goodsCode='" + goodsCode + '\'' +
                ", id='" + id + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", unitName='" + unitName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", lineDiscount=" + lineDiscount +
                ", amount=" + amount +
                ", taxRate=" + taxRate +
                ", notaxRealPrice=" + notaxRealPrice +
                ", notaxRealAmount=" + notaxRealAmount +
                ", taxAmount=" + taxAmount +
                ", purchaseInstorageShelfVO=" + purchaseInstorageShelfVO +
                '}';
    }

    public PurchaseInstorageDetailVO convertToVO(PurchaseInStorageDetail pd) {
        PurchaseInstorageDetailVO vo = new PurchaseInstorageDetailVO();
        vo.setGoodsCode(pd.getGoodsCode());
        vo.setGoodsName(pd.getGoodsName());
        vo.setDosageName(pd.getDosageName());
        vo.setGoodsSpecification(pd.getGoodsSpecification());
        vo.setManufacturer(pd.getManufacturer());
        vo.setUnitName(pd.getUnitName());
        vo.setQuantity(pd.getQuantity());
        vo.setUnitPrice(pd.getUnitPrice());
        vo.setLineDiscount(pd.getLineDiscount());
        vo.setAmount(pd.getAmount());
        vo.setTaxRate(pd.getTaxRate());
        vo.setNotaxRealPrice(pd.getNotaxRealPrice());
        vo.setNotaxRealAmount(pd.getNotaxRealAmount());
        vo.setTaxAmount(pd.getTaxAmount());
        vo.setRemark(pd.getRemark());
        return vo;
    }
}
