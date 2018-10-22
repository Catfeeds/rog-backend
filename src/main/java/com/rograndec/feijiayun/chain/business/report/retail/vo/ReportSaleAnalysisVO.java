package com.rograndec.feijiayun.chain.business.report.retail.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/23 <br/>
 * 描述：销售报表-销售分析-销售基础数据
 */
public class ReportSaleAnalysisVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "店铺ID")
    private Long enterpriseId;

    @ApiModelProperty(value = "门店编码")
    private String storeCode;

    @ApiModelProperty(value = "门店名称")
    private String storeName;

    @ApiModelProperty(value = "销售笔数")
    private Long saleQuantity = 0L;

    @ApiModelProperty(value = "销售金额")
    private BigDecimal realAmount = BigDecimal.ZERO;

    @ApiModelProperty(value = "退货笔数")
    private Long returnQuantity = 0L;

    @ApiModelProperty(value = "退货金额")
    private BigDecimal returnRealAmount = BigDecimal.ZERO;

    @ApiModelProperty(value = "总额")
    private BigDecimal realAmountTotal = BigDecimal.ZERO;

    @ApiModelProperty(value = "不含税总额")
    private BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;

    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmountTotal = BigDecimal.ZERO;

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getSaleQuantity() {
        return saleQuantity;
    }

    public void setSaleQuantity(Long saleQuantity) {
        this.saleQuantity = saleQuantity;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public Long getReturnQuantity() {
        return returnQuantity;
    }

    public void setReturnQuantity(Long returnQuantity) {
        this.returnQuantity = returnQuantity;
    }

    public BigDecimal getReturnRealAmount() {
        return returnRealAmount;
    }

    public void setReturnRealAmount(BigDecimal returnRealAmount) {
        this.returnRealAmount = returnRealAmount;
    }

    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }
}
