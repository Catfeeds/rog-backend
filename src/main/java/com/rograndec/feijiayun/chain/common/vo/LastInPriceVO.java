package com.rograndec.feijiayun.chain.common.vo;

import java.math.BigDecimal;

/**
 * 最近入库价更新参数对象
 *
 * @author lizhongyi
 *
 * 2017-10-26 11:46:53
 */
public class LastInPriceVO {

    private Long enterpriseId;// 企业ID
    private Long parentId;// 上级企业ID
    private Integer chainType;// 企业类型
    private Long goodsId;// 商品ID
    private Long supplierId;// 供货单位ID
    private Long purTaxRateId;// 采购税率ID
    private BigDecimal purTaxRate;// 采购税率
    private BigDecimal purPrice;// 采购单价
    private Long inTaxRateId;// 入库税率ID
    private BigDecimal inTaxRate;// 入库税率
    private BigDecimal inPrice;// 入库单价

    public LastInPriceVO() {
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getPurTaxRateId() {
        return purTaxRateId;
    }

    public void setPurTaxRateId(Long purTaxRateId) {
        this.purTaxRateId = purTaxRateId;
    }

    public BigDecimal getPurTaxRate() {
        return purTaxRate;
    }

    public void setPurTaxRate(BigDecimal purTaxRate) {
        this.purTaxRate = purTaxRate;
    }

    public BigDecimal getPurPrice() {
        return purPrice;
    }

    public void setPurPrice(BigDecimal purPrice) {
        this.purPrice = purPrice;
    }

    public Long getInTaxRateId() {
        return inTaxRateId;
    }

    public void setInTaxRateId(Long inTaxRateId) {
        this.inTaxRateId = inTaxRateId;
    }

    public BigDecimal getInTaxRate() {
        return inTaxRate;
    }

    public void setInTaxRate(BigDecimal inTaxRate) {
        this.inTaxRate = inTaxRate;
    }

    public BigDecimal getInPrice() {
        return inPrice;
    }

    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }
}