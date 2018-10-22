package com.rograndec.feijiayun.chain.business.report.finance.stock.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 库存明细账: 第二层，商品层
 */
public class StockInOutGoodsVO implements Serializable {


    @ApiModelProperty(required = false, value = "企业ID")
    private Long enterpriseId;

    @ApiModelProperty(required = false, value = "商品ID")
    private Long goodsId;

    @ApiModelProperty(required = false, value = "规格")
    private String specification;
    /**
     * 商品编码
     */
    @ApiModelProperty(required = false, value = "商品编码")
    private String goodsCode;

    /**
     * 通用名称
     */
    @ApiModelProperty(required = false, value = "通用名称")
    private String goodsGenericName;

    /**
     * 生产厂商
     */
    @ApiModelProperty(required = false, value = "生产厂商")
    private String manufacturer;


    @ApiModelProperty(value = "余额数量")
    private BigDecimal balanceQuantityTotal;

    @ApiModelProperty(value = "余额金额")
    private BigDecimal balanceAmountTotal;

    @ApiModelProperty("年份层级列表")
    private List<StockInOutYearVO> stockInOutYearVOList;


    public List<StockInOutYearVO> getStockInOutYearVOList() {
        return stockInOutYearVOList;
    }

    public void setStockInOutYearVOList(List<StockInOutYearVO> stockInOutYearVOList) {
        this.stockInOutYearVOList = stockInOutYearVOList;
    }


    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getSpecification() {
        return specification == null ? "" : specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
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

    public String getManufacturer() {
        return manufacturer == null ? "" : manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public BigDecimal getBalanceQuantityTotal() {
        return balanceQuantityTotal;
    }

    public void setBalanceQuantityTotal(BigDecimal balanceQuantityTotal) {
        this.balanceQuantityTotal = balanceQuantityTotal;
    }

    public BigDecimal getBalanceAmountTotal() {
        return balanceAmountTotal;
    }

    public void setBalanceAmountTotal(BigDecimal balanceAmountTotal) {
        this.balanceAmountTotal = balanceAmountTotal;
    }
}
