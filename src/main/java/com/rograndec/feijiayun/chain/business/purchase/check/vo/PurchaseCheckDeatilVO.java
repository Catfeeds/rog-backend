package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * Created by zeshi.sun on 2017/9/18.
 */

public class PurchaseCheckDeatilVO implements Serializable {

    /**
     * 主键ID(验收单ID)
     */
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码", required = true)
    private String goodsCode;

    /**
     * 通用名称
     */
    @ApiModelProperty(value = "通用名称", required = true)
    private String goodsGenericName;

    /**
     * 剂型
     */
    @ApiModelProperty(value = "剂型", required = true)
    private String dosageName;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格", required = true)
    private String goodsSpecification;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商", required = true)
    private String manufacturer;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位", required = true)
    private String unitName;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量", required = true)
    private BigDecimal quantity;

    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量", required = true)
    private BigDecimal qualifiedQuantity;

    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量", required = true)
    private BigDecimal unqualifiedQuantity;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号", required = true)
    private Integer lineNum;


    private List<PurchaseCheckDeatilTwoVO> purchaseCheckDeatilTwoVO;

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

    public BigDecimal getQualifiedQuantity() {
        return qualifiedQuantity;
    }

    public void setQualifiedQuantity(BigDecimal qualifiedQuantity) {
        this.qualifiedQuantity = qualifiedQuantity;
    }

    public BigDecimal getUnqualifiedQuantity() {
        return unqualifiedQuantity;
    }

    public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) {
        this.unqualifiedQuantity = unqualifiedQuantity;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    public List<PurchaseCheckDeatilTwoVO> getPurchaseCheckDeatilTwoVO() {
        return purchaseCheckDeatilTwoVO;
    }

    public void setPurchaseCheckDeatilTwoVO(List<PurchaseCheckDeatilTwoVO> purchaseCheckDeatilTwoVO) {
        this.purchaseCheckDeatilTwoVO = purchaseCheckDeatilTwoVO;
    }

    @Override
    public String toString() {
        return "PurchaseCheckDeatilVO[" +
                "id=" + id +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", unitName='" + unitName + '\'' +
                ", quantity=" + quantity +
                ", lineNum=" + lineNum +
                ", qualifiedQuantity=" + qualifiedQuantity +
                ", unqualifiedQuantity=" + unqualifiedQuantity +
                ", purchaseCheckDeatilTwoVO=" + purchaseCheckDeatilTwoVO +
                ']';
    }
}
