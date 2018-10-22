package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ClickCheckDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配进收货明细ID
     */
    @ApiModelProperty(value = "配进收货明细ID", required = true)
    private Long distrReturnReceiveDetailId;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", required = true)
    private Long goodsId;

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
    private BigDecimal receiveQuantity;

    /**
     * 基础单据明细ID
     */
    @ApiModelProperty(value = "基础单据明细ID", required = true)
    private Long baseOrderDtlId;

    /**
     * 是否为特殊管理药品：（0-是  1-否）
     */
    @ApiModelProperty(value = "是否为特殊管理药品：（0-是  1-否）", required = true)
    private Integer specialDrug;

    List<ClickCheckDetailOneVO> clickCheckDetailOneVO;

    public Long getDistrReturnReceiveDetailId() {
        return distrReturnReceiveDetailId;
    }

    public void setDistrReturnReceiveDetailId(Long distrReturnReceiveDetailId) {
        this.distrReturnReceiveDetailId = distrReturnReceiveDetailId;
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

    public BigDecimal getReceiveQuantity() {
        return receiveQuantity;
    }

    public void setReceiveQuantity(BigDecimal receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    public Integer getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(Integer specialDrug) {
        this.specialDrug = specialDrug;
    }

    public Long getBaseOrderDtlId() {
        return baseOrderDtlId;
    }

    public void setBaseOrderDtlId(Long baseOrderDtlId) {
        this.baseOrderDtlId = baseOrderDtlId;
    }

    public List<ClickCheckDetailOneVO> getClickCheckDetailOneVO() {
        return clickCheckDetailOneVO;
    }

    public void setClickCheckDetailOneVO(List<ClickCheckDetailOneVO> clickCheckDetailOneVO) {
        this.clickCheckDetailOneVO = clickCheckDetailOneVO;
    }

    @Override
    public String toString() {
        return "ClickCheckDetailVO[" +
                "distrReturnReceiveDetailId=" + distrReturnReceiveDetailId +
                ", goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", unitName='" + unitName + '\'' +
                ", baseOrderDtlId='" + baseOrderDtlId + '\'' +
                ", receiveQuantity=" + receiveQuantity +
                ", specialDrug=" + specialDrug +
                ", clickCheckDetailOneVO=" + clickCheckDetailOneVO +
                ']';
    }
}
