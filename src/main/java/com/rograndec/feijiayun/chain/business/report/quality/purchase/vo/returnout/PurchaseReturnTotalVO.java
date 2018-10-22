package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/10/23 15:03
 */

public class PurchaseReturnTotalVO {


    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;


    @ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal;

    /**
     * 实际金额
     */
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 不含税实际金额
     */
    @ApiModelProperty(value = "不含税实际金额合计")
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;



    private List<PurchaseReturnGoodsReportVO> returnGoodsReportVOList;


    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
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

    public List<PurchaseReturnGoodsReportVO> getReturnGoodsReportVOList() {
        return returnGoodsReportVOList;
    }

    public void setReturnGoodsReportVOList(List<PurchaseReturnGoodsReportVO> returnGoodsReportVOList) {
        this.returnGoodsReportVOList = returnGoodsReportVOList;
    }
}