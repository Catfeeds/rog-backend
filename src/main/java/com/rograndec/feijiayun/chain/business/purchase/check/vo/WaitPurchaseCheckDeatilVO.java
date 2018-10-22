package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by zeshi.sun on 2017/9/16.
 */
public class WaitPurchaseCheckDeatilVO implements Serializable {

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
     * 到货数量
     */
    @ApiModelProperty(value = "到货数量", required = true)
    private BigDecimal arrivalQuantity;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量", required = true)
    private BigDecimal receiveQuantity;

    /**
     * 拒收数量
     */
    @ApiModelProperty(value = "拒收数量", required = true)
    private BigDecimal refuseQuantity;

    /**
     * 拒收原因
     */
    @ApiModelProperty(value = "拒收原因", required = true)
    private String refuseReasonIds;


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

    public BigDecimal getReceiveQuantity() {
        return receiveQuantity;
    }

    public void setReceiveQuantity(BigDecimal receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    public BigDecimal getArrivalQuantity() {
        return arrivalQuantity;
    }

    public void setArrivalQuantity(BigDecimal arrivalQuantity) {
        this.arrivalQuantity = arrivalQuantity;
    }

    public BigDecimal getRefuseQuantity() {
        return refuseQuantity;
    }

    public void setRefuseQuantity(BigDecimal refuseQuantity) {
        this.refuseQuantity = refuseQuantity;
    }

    public String getRefuseReasonIds() {
        return refuseReasonIds;
    }

    public void setRefuseReasonIds(String refuseReasonIds) {
        this.refuseReasonIds = refuseReasonIds;
    }

    @Override
    public String toString() {
        return "WaitPurchaseCheckDeatilVO[" +
                "goodsCode='" + goodsCode + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", receiveQuantity=" + receiveQuantity +
                ", arrivalQuantity=" + arrivalQuantity +
                ", refuseQuantity=" + refuseQuantity +
                ", refuseReasonIds='" + refuseReasonIds + '\'' +
                ']';
    }
}
