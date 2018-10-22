package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import com.rograndec.feijiayun.chain.inf.search.vo.MphSupplier;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SelectSmartGoodsVO implements Serializable {

    @ApiModelProperty(value = "'商品id", required = true)
    private Long goodsId;

    @ApiModelProperty(value = "商品名称", required = true)
    private String goodsName;

    @ApiModelProperty(value = "规格", required = true)
    private String specification;

    @ApiModelProperty(value = "生产厂家", required = true)
    private String manufacturer;

    @ApiModelProperty(value = "采购价", required = true)
    private double purchasePrice;

    @ApiModelProperty(value = "零售价", required = true)
    private BigDecimal retailPrice;

    @ApiModelProperty(value = "限购数量", required = true)
    private int restrictedQuantity;

    @ApiModelProperty(value = "一级分类名称")
    private String gcName1;

    @ApiModelProperty(value = "二级分类名称")
    private String gcName2;

    @ApiModelProperty(value = "是否拆包销售(0否,1是) ")
    private Integer canSplit;

    @ApiModelProperty(value = "中包装数量")
    private Integer middlePackage;

    @ApiModelProperty(value = "商品图片路径")
    private String pictureAddress;

    @ApiModelProperty(value = "设置是否可以采购，0：不可以采购，1：可以采购")
    private int canPurchased;

    @ApiModelProperty(value = "效期")
    private String expiryDate;

    @ApiModelProperty(value = "生产日期")
    private String productDate ="-";

    @ApiModelProperty(value = "批号")
    private String batchNo;

    @ApiModelProperty(value = "促销类型")
    private Integer promotionType;

    @ApiModelProperty(value = "促销价格")
    private Double promotionPrice;

    @ApiModelProperty(value = "促销描述")
    private List<String> promotionDescription;

    @ApiModelProperty(value = "供应商")
    private MphSupplier supplier;

    @ApiModelProperty(value = "库存数量")
    private double inventoryQuantity;

    @ApiModelProperty(value = "购物车数量")
    private Integer quantity;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public int getRestrictedQuantity() {
        return restrictedQuantity;
    }

    public void setRestrictedQuantity(int restrictedQuantity) {
        this.restrictedQuantity = restrictedQuantity;
    }

    public String getGcName1() {
        return gcName1;
    }

    public void setGcName1(String gcName1) {
        this.gcName1 = gcName1;
    }

    public String getGcName2() {
        return gcName2;
    }

    public void setGcName2(String gcName2) {
        this.gcName2 = gcName2;
    }

    public Integer getCanSplit() {
        return canSplit;
    }

    public void setCanSplit(Integer canSplit) {
        this.canSplit = canSplit;
    }

    public Integer getMiddlePackage() {
        return middlePackage;
    }

    public void setMiddlePackage(Integer middlePackage) {
        this.middlePackage = middlePackage;
    }

    public String getPictureAddress() {
        return pictureAddress;
    }

    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress;
    }

    public int getCanPurchased() {
        return canPurchased;
    }

    public void setCanPurchased(int canPurchased) {
        this.canPurchased = canPurchased;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Integer getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }

    public Double getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public List<String> getPromotionDescription() {
        return promotionDescription;
    }

    public void setPromotionDescription(List<String> promotionDescription) {
        this.promotionDescription = promotionDescription;
    }

    public MphSupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(MphSupplier supplier) {
        this.supplier = supplier;
    }

    public double getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(double inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    @Override
    public String toString() {
        return "SelectSmartGoodsVO[" +
                " goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", specification='" + specification + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", retailPrice=" + retailPrice +
                ", restrictedQuantity=" + restrictedQuantity +
                ", gcName1='" + gcName1 + '\'' +
                ", gcName2='" + gcName2 + '\'' +
                ", canSplit=" + canSplit +
                ", middlePackage=" + middlePackage +
                ", pictureAddress='" + pictureAddress + '\'' +
                ", canPurchased=" + canPurchased +
                ", expiryDate='" + expiryDate + '\'' +
                ", productDate='" + productDate + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", promotionType=" + promotionType +
                ", promotionPrice=" + promotionPrice +
                ", promotionDescription=" + promotionDescription +
                ", supplier=" + supplier +
                ", inventoryQuantity=" + inventoryQuantity +
                ", quantity=" + quantity +
                ']';
    }
}
