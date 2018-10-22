package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class TabableTotalVO implements Serializable {

    /**
     * 列表下方金额合计
     */
    @ApiModelProperty(value = "列表下方金额合计")
    private BigDecimal tabAmountTotal;

    /**
     * 列表下方不含税金额合计
     */
    @ApiModelProperty(value = "列表下方不含税金额合计")
    private BigDecimal tabNotaxAmountTotal;

    /**
     * 列表下方税额合计
     */
    @ApiModelProperty(value = "列表下方税额合计")
    private BigDecimal tabTaxAmountTotal;

    List<ReveivableInvoicePageVO> reveivableInvoicePageVO;

    public BigDecimal getTabAmountTotal() {
        return tabAmountTotal;
    }

    public void setTabAmountTotal(BigDecimal tabAmountTotal) {
        this.tabAmountTotal = tabAmountTotal;
    }

    public BigDecimal getTabNotaxAmountTotal() {
        return tabNotaxAmountTotal;
    }

    public void setTabNotaxAmountTotal(BigDecimal tabNotaxAmountTotal) {
        this.tabNotaxAmountTotal = tabNotaxAmountTotal;
    }

    public BigDecimal getTabTaxAmountTotal() {
        return tabTaxAmountTotal;
    }

    public void setTabTaxAmountTotal(BigDecimal tabTaxAmountTotal) {
        this.tabTaxAmountTotal = tabTaxAmountTotal;
    }

    public List<ReveivableInvoicePageVO> getReveivableInvoicePageVO() {
        return reveivableInvoicePageVO;
    }

    public void setReveivableInvoicePageVO(List<ReveivableInvoicePageVO> reveivableInvoicePageVO) {
        this.reveivableInvoicePageVO = reveivableInvoicePageVO;
    }

    @Override
    public String toString() {
        return "TabableTotalVO[" +
                "tabAmountTotal=" + tabAmountTotal +
                ", tabNotaxAmountTotal=" + tabNotaxAmountTotal +
                ", tabTaxAmountTotal=" + tabTaxAmountTotal +
                ", reveivableInvoicePageVO=" + reveivableInvoicePageVO +
                ']';
    }
}
