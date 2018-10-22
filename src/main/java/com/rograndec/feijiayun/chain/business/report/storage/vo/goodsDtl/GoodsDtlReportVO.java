package com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/10/26 13:46
 */

public class GoodsDtlReportVO {
    /**
     * 主键ID
     */
    @ApiModelProperty(value="商品主键id")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value="商品编码")
    private String goodsCode;

    /**
     * 通用名称
     */
    @ApiModelProperty(value="通用名称")
    private String goodsGenericName;

    /**
     * 商品名称
     */
    @ApiModelProperty(value="商品名称")
    private String goodsName;


    /**
     * 剂型名称
     */
    @ApiModelProperty(value="剂型名称")
    private String dosageName;

    /**
     * 规格
     */
    @ApiModelProperty(value="规格")
    private String goodsSpecification;


    /**
     * 库存计量单位名称
     */
    @ApiModelProperty(value="库存计量单位名称")
    private String unitName;


    /**
     * 生产厂商
     */
    @ApiModelProperty(value="生产厂商")
    private String manufacturer;

    /**
     * 产地
     */
    @ApiModelProperty(value="产地")
    private String goodsPlace;

    @ApiModelProperty(value="组织结构名称")
    private String enterpriseName;

    @ApiModelProperty(value="期间")
    private String period;

    @ApiModelProperty(value="收入合计")
    private BigDecimal inQuantityTotal;

    @ApiModelProperty(value="发出合计")
    private BigDecimal outQuantityTotal;

    @ApiModelProperty(value="结存")
    private BigDecimal storageBalance;

    private List<InOutRecordDetailReportVO> inOutRecordDetailReportVOS;

    private boolean _expanded = true;

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

    public List<InOutRecordDetailReportVO> getInOutRecordDetailReportVOS() {
        return inOutRecordDetailReportVOS;
    }

    public void setInOutRecordDetailReportVOS(List<InOutRecordDetailReportVO> inOutRecordDetailReportVOS) {
        this.inOutRecordDetailReportVOS = inOutRecordDetailReportVOS;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public BigDecimal getInQuantityTotal() {
        return inQuantityTotal;
    }

    public void setInQuantityTotal(BigDecimal inQuantityTotal) {
        this.inQuantityTotal = inQuantityTotal;
    }

    public BigDecimal getOutQuantityTotal() {
        return outQuantityTotal;
    }

    public void setOutQuantityTotal(BigDecimal outQuantityTotal) {
        this.outQuantityTotal = outQuantityTotal;
    }

    public BigDecimal getStorageBalance() {
        return storageBalance;
    }

    public void setStorageBalance(BigDecimal storageBalance) {
        this.storageBalance = storageBalance;
    }

    public boolean is_expanded() {
        return _expanded;
    }

    public void set_expanded(boolean _expanded) {
        this._expanded = _expanded;
    }
}