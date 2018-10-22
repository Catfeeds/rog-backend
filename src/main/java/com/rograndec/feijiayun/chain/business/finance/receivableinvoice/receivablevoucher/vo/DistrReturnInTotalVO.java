package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class DistrReturnInTotalVO implements Serializable {

    /**
     * 列表下方数量合计
     */
    @ApiModelProperty(value = "列表下方数量合计")
    private BigDecimal tabQuantityTotal;

    /**
     * 列表下方金额合计
     */
    @ApiModelProperty(value = "列表下方金额合计")
    private BigDecimal tabAmountTotal;

    /**
     * 列表下方整单折扣金额合计
     */
    @ApiModelProperty(value = "列表下方整单折扣金额合计")
    private BigDecimal tabWholeDiscountAmountTotal;

    /**
     * 列表下方整单优惠分摊合计
     */
    @ApiModelProperty(value = "列表下方整单优惠分摊合计")
    private BigDecimal tabLineDiscountAmountTotal;

    /**
     * 列表下方总额合计
     */
    @ApiModelProperty(value = "列表下方总额合计")
    private BigDecimal tabNotaxAmountTotal;

    /**
     * 列表下方不含税金额合计
     */
    @ApiModelProperty(value = "列表下方不含税金额合计")
    private BigDecimal tabRealAmountTotal;

    /**
     * 列表下方税额合计
     */
    @ApiModelProperty(value = "列表下方税额合计")
    private BigDecimal tabTaxAmountTotal;

    /**
     * 列表下方已清数量合计
     */
    @ApiModelProperty(value = "列表下方已清数量合计")
    private BigDecimal tabClearQuantityTotal;

    /**
     * 列表下方未清数量合计
     */
    @ApiModelProperty(value = "列表下方未清数量合计")
    private BigDecimal tabUnclearQuantityTotal;

    List<DistrReturnInPageListVO> distrReturnInPageListVO;

    public BigDecimal getTabQuantityTotal() {
        return tabQuantityTotal;
    }

    public void setTabQuantityTotal(BigDecimal tabQuantityTotal) {
        this.tabQuantityTotal = tabQuantityTotal;
    }

    public BigDecimal getTabAmountTotal() {
        return tabAmountTotal;
    }

    public void setTabAmountTotal(BigDecimal tabAmountTotal) {
        this.tabAmountTotal = tabAmountTotal;
    }

    public BigDecimal getTabWholeDiscountAmountTotal() {
        return tabWholeDiscountAmountTotal;
    }

    public void setTabWholeDiscountAmountTotal(BigDecimal tabWholeDiscountAmountTotal) {
        this.tabWholeDiscountAmountTotal = tabWholeDiscountAmountTotal;
    }

    public BigDecimal getTabLineDiscountAmountTotal() {
        return tabLineDiscountAmountTotal;
    }

    public void setTabLineDiscountAmountTotal(BigDecimal tabLineDiscountAmountTotal) {
        this.tabLineDiscountAmountTotal = tabLineDiscountAmountTotal;
    }

    public BigDecimal getTabNotaxAmountTotal() {
        return tabNotaxAmountTotal;
    }

    public void setTabNotaxAmountTotal(BigDecimal tabNotaxAmountTotal) {
        this.tabNotaxAmountTotal = tabNotaxAmountTotal;
    }

    public BigDecimal getTabRealAmountTotal() {
        return tabRealAmountTotal;
    }

    public void setTabRealAmountTotal(BigDecimal tabRealAmountTotal) {
        this.tabRealAmountTotal = tabRealAmountTotal;
    }

    public BigDecimal getTabTaxAmountTotal() {
        return tabTaxAmountTotal;
    }

    public void setTabTaxAmountTotal(BigDecimal tabTaxAmountTotal) {
        this.tabTaxAmountTotal = tabTaxAmountTotal;
    }

    public BigDecimal getTabClearQuantityTotal() {
        return tabClearQuantityTotal;
    }

    public void setTabClearQuantityTotal(BigDecimal tabClearQuantityTotal) {
        this.tabClearQuantityTotal = tabClearQuantityTotal;
    }

    public BigDecimal getTabUnclearQuantityTotal() {
        return tabUnclearQuantityTotal;
    }

    public void setTabUnclearQuantityTotal(BigDecimal tabUnclearQuantityTotal) {
        this.tabUnclearQuantityTotal = tabUnclearQuantityTotal;
    }

    public List<DistrReturnInPageListVO> getDistrReturnInPageListVO() {
        return distrReturnInPageListVO;
    }

    public void setDistrReturnInPageListVO(List<DistrReturnInPageListVO> distrReturnInPageListVO) {
        this.distrReturnInPageListVO = distrReturnInPageListVO;
    }

    @Override
    public String toString() {
        return "DistrReturnInTotalVO[" +
                "tabQuantityTotal=" + tabQuantityTotal +
                ", tabAmountTotal=" + tabAmountTotal +
                ", tabWholeDiscountAmountTotal=" + tabWholeDiscountAmountTotal +
                ", tabLineDiscountAmountTotal=" + tabLineDiscountAmountTotal +
                ", tabNotaxAmountTotal=" + tabNotaxAmountTotal +
                ", tabRealAmountTotal=" + tabRealAmountTotal +
                ", tabTaxAmountTotal=" + tabTaxAmountTotal +
                ", tabClearQuantityTotal=" + tabClearQuantityTotal +
                ", tabUnclearQuantityTotal=" + tabUnclearQuantityTotal +
                ", distrReturnInPageListVO=" + distrReturnInPageListVO +
                ']';
    }
}
