package com.rograndec.feijiayun.chain.business.report.retail.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/23 <br/>
 * 描述：销售报表-销售分析-销售明细
 */
public class ReportSaleDetailVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "销售类型：0-销售；1-销退")
    private Long saleId;

    @ApiModelProperty(value = "销售类型：0-销售；1-销退")
    private Integer saleType;

    @ApiModelProperty(value = "实际金额")
    private BigDecimal realAmount;

    @ApiModelProperty(value = "不含税实际金额")
    private BigDecimal notaxRealAmount;

    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Integer getSaleType() {
        return saleType;
    }

    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getNotaxRealAmount() {
        return notaxRealAmount;
    }

    public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
        this.notaxRealAmount = notaxRealAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }
}
