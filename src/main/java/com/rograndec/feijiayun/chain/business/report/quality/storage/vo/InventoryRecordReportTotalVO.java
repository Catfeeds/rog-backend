package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 盘点记录报表
 * xingjian.lan
 */
public class InventoryRecordReportTotalVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "盘点明细列表")
    private List<InventoryRecordReportVO> dataList;

    @ApiModelProperty(value = "账面数量合计")
    private BigDecimal quantityTotal;

    @ApiModelProperty(value = "实盘数量合计")
    private BigDecimal invQuantityTotal;

    @ApiModelProperty(value = "损溢数量合计")
    private BigDecimal diffQuantityTotal;

    @ApiModelProperty(value = "账面金额合计")
    private BigDecimal amountTotal;

    @ApiModelProperty(value = "实盘金额合计")
    private BigDecimal realAmountTotal;

    @ApiModelProperty(value = "损溢金额合计")
    private BigDecimal diffAmountTotal;

    @ApiModelProperty(value = "不含税账面金额合计")
    private BigDecimal notaxAmountTotal;

    @ApiModelProperty(value = "不含税实盘金额合计")
    private BigDecimal realNotaxAmountTotal;

    @ApiModelProperty(value = "不含税损溢金额合计")
    private BigDecimal diffNotaxAmountTotal;

    @ApiModelProperty(value = "账面税额合计")
    private BigDecimal taxAmountTotal;

    @ApiModelProperty(value = "实盘税额合计")
    private BigDecimal realTaxAmountTotal;

    @ApiModelProperty(value = "损益税额合计")
    private BigDecimal diffTaxAmountTotal;

    @ApiModelProperty(value = "账面零售金额合计")
    private BigDecimal retailAmountTotal;

    @ApiModelProperty(value = "实盘零售金额合计")
    private BigDecimal realRetailAmountTotal;

    @ApiModelProperty(value = "损益零售金额合计")
    private BigDecimal diffRetailAmountTotal;

    public List<InventoryRecordReportVO> getDataList() {
        return dataList;
    }

    public void setDataList(List<InventoryRecordReportVO> dataList) {
        this.dataList = dataList;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public BigDecimal getInvQuantityTotal() {
        return invQuantityTotal;
    }

    public void setInvQuantityTotal(BigDecimal invQuantityTotal) {
        this.invQuantityTotal = invQuantityTotal;
    }

    public BigDecimal getDiffQuantityTotal() {
        return diffQuantityTotal;
    }

    public void setDiffQuantityTotal(BigDecimal diffQuantityTotal) {
        this.diffQuantityTotal = diffQuantityTotal;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    public BigDecimal getDiffAmountTotal() {
        return diffAmountTotal;
    }

    public void setDiffAmountTotal(BigDecimal diffAmountTotal) {
        this.diffAmountTotal = diffAmountTotal;
    }

    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    public BigDecimal getRealNotaxAmountTotal() {
        return realNotaxAmountTotal;
    }

    public void setRealNotaxAmountTotal(BigDecimal realNotaxAmountTotal) {
        this.realNotaxAmountTotal = realNotaxAmountTotal;
    }

    public BigDecimal getDiffNotaxAmountTotal() {
        return diffNotaxAmountTotal;
    }

    public void setDiffNotaxAmountTotal(BigDecimal diffNotaxAmountTotal) {
        this.diffNotaxAmountTotal = diffNotaxAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public BigDecimal getRealTaxAmountTotal() {
        return realTaxAmountTotal;
    }

    public void setRealTaxAmountTotal(BigDecimal realTaxAmountTotal) {
        this.realTaxAmountTotal = realTaxAmountTotal;
    }

    public BigDecimal getDiffTaxAmountTotal() {
        return diffTaxAmountTotal;
    }

    public void setDiffTaxAmountTotal(BigDecimal diffTaxAmountTotal) {
        this.diffTaxAmountTotal = diffTaxAmountTotal;
    }

    public BigDecimal getRetailAmountTotal() {
        return retailAmountTotal;
    }

    public void setRetailAmountTotal(BigDecimal retailAmountTotal) {
        this.retailAmountTotal = retailAmountTotal;
    }

    public BigDecimal getRealRetailAmountTotal() {
        return realRetailAmountTotal;
    }

    public void setRealRetailAmountTotal(BigDecimal realRetailAmountTotal) {
        this.realRetailAmountTotal = realRetailAmountTotal;
    }

    public BigDecimal getDiffRetailAmountTotal() {
        return diffRetailAmountTotal;
    }

    public void setDiffRetailAmountTotal(BigDecimal diffRetailAmountTotal) {
        this.diffRetailAmountTotal = diffRetailAmountTotal;
    }
}