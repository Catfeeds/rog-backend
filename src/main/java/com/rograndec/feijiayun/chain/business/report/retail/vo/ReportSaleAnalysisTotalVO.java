package com.rograndec.feijiayun.chain.business.report.retail.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/23 <br/>
 * 描述：销售报表-销售分析-销售合计相关
 */
public class ReportSaleAnalysisTotalVO<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "销售明细列表")
    List<T> saleList;

    @ApiModelProperty(value = "销售笔数合计")
    private Long saleQuantity = 0L;

    @ApiModelProperty(value = "销售金额合计")
    private BigDecimal realAmount = BigDecimal.ZERO;

    @ApiModelProperty(value = "退货笔数合计")
    private Long returnQuantity = 0L;

    @ApiModelProperty(value = "退货金额合计")
    private BigDecimal returnRealAmount = BigDecimal.ZERO;

    @ApiModelProperty(value = "总额合计")
    private BigDecimal realAmountTotal = BigDecimal.ZERO;

    @ApiModelProperty(value = "不含税总额合计")
    private BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;

    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal = BigDecimal.ZERO;

    public List<T> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<T> saleList) {
        this.saleList = saleList;
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
