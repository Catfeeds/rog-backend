package com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class SelectActivityGoodsVO implements Serializable {

    @ApiModelProperty(value = "活动ID", required = true)
    private Long id;

    @ApiModelProperty(value = "'商品id", required = true)
    private Long goodsId;

    @ApiModelProperty(value = "商品名称", required = true)
    private String goodsName;

    @ApiModelProperty(value = "规格", required = true)
    private String specification;

    @ApiModelProperty(value = "生产厂家", required = true)
    private String manufacturer;

    @ApiModelProperty(value = "秒杀价", required = true)
    private BigDecimal policyPrice;

    @ApiModelProperty(value = "成本价", required = true)
    private BigDecimal costPrice;

    @ApiModelProperty(value = "零售价", required = true)
    private BigDecimal retailPrice;

    @ApiModelProperty(value = "建议零售价", required = true)
    private String proposalRetailPrice;

    @ApiModelProperty(value = "限购数量", required = true)
    private Integer restrictedQuantity;

    @ApiModelProperty(value = "一级分类名称")
    private String gcName1;

    @ApiModelProperty(value = "二级分类名称")
    private String gcName2;

    @ApiModelProperty(value = "是否拆包销售(0否,1是) ")
    private Integer canSplit;

    @ApiModelProperty(value = "中包装数量")
    private Integer middlePackage;

    @ApiModelProperty(value = "批号", required = true)
    private String lotNum = "-";

    @ApiModelProperty(value = "有效期至", required = true)
    private String validUntil = "-";

    @ApiModelProperty(value = "库存数量", required = true)
    private Integer inventoryQuantity ;

    @ApiModelProperty(value = "商品图片路径", required = true)
    private String pictureAddress ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public BigDecimal getPolicyPrice() {
        return policyPrice;
    }

    public void setPolicyPrice(BigDecimal policyPrice) {
        this.policyPrice = policyPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Integer getRestrictedQuantity() {
        return restrictedQuantity;
    }

    public void setRestrictedQuantity(Integer restrictedQuantity) {
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

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    public String getProposalRetailPrice() {
        return proposalRetailPrice;
    }

    public void setProposalRetailPrice(String proposalRetailPrice) {
        this.proposalRetailPrice = proposalRetailPrice;
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

    public Integer getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(Integer inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public String getPictureAddress() {
        return pictureAddress;
    }

    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress;
    }

    @Override
    public String toString() {
        return "SelectActivityGoodsVO[" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", specification='" + specification + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", policyPrice=" + policyPrice +
                ", costPrice=" + costPrice +
                ", retailPrice=" + retailPrice +
                ", proposalRetailPrice='" + proposalRetailPrice + '\'' +
                ", restrictedQuantity=" + restrictedQuantity +
                ", gcName1='" + gcName1 + '\'' +
                ", gcName2='" + gcName2 + '\'' +
                ", canSplit=" + canSplit +
                ", middlePackage=" + middlePackage +
                ", lotNum='" + lotNum + '\'' +
                ", validUntil='" + validUntil + '\'' +
                ", inventoryQuantity='" + inventoryQuantity + '\'' +
                ", pictureAddress='" + pictureAddress + '\'' +
                ']';
    }
}
