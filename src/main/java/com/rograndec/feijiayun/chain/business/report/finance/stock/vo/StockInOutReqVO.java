package com.rograndec.feijiayun.chain.business.report.finance.stock.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class StockInOutReqVO extends BasePageReqParam implements Serializable {

    /**
     * 商品编码
     */
    @ApiModelProperty(required = false, value = "商品编码")
    private String goodsCode;

    /**
     * 条形码
     */
    @ApiModelProperty(required = false, value = "条形码")
    private String barcode;

    /**
     * 通用名称
     */
    @ApiModelProperty(required = false, value = "通用名称")
    private String goodsGenericName;

    /**
     * 商品名称
     */
    @ApiModelProperty(required = false, value = "商品名称")
    private String goodsName;

    /**
     * 检索码（型如“通用名检索码—商品名检索码”）
     */
    @ApiModelProperty(required = false, value = "检索码")
    private String pinyinCode;

    /**
     * 产地
     */
    @ApiModelProperty(required = false, value = "产地")
    private String place;

    /**
     * 批准文号
     */
    @ApiModelProperty(required = false, value = "批准文号")
    private String approvalNumber;

    /**
     * 生产厂商
     */
    @ApiModelProperty(required = false, value = "生产厂商")
    private String manufacturer;

    @ApiModelProperty(required = false,value = "后台用")
    private Integer type;

    @ApiModelProperty(required = false,value = "后台用")
    private Integer limit;

    @ApiModelProperty(required = false,value = "后台用")
    private Long goodsId;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }


    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}
