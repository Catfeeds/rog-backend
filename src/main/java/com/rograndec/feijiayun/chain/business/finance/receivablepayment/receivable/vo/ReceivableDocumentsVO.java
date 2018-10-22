package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ReceivableDocumentsVO implements Serializable{

    /**
     * 单据金额
     */
    @ApiModelProperty(value = "单据金额")
    private BigDecimal amountTotal;

    /**
     * 已清金额合计
     */
    @ApiModelProperty(value = "已清金额合计")
    private BigDecimal clearAmountTotal;

    /**
     * 未清金额合计
     */
    @ApiModelProperty(value = "未清金额合计")
    private BigDecimal unclearAmountTotal;

    /**
     * 单据list
     */
    @ApiModelProperty(value = "单据list")
    private List<ReceivableDocumentsPageVO> list;

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getClearAmountTotal() {
        return clearAmountTotal;
    }

    public void setClearAmountTotal(BigDecimal clearAmountTotal) {
        this.clearAmountTotal = clearAmountTotal;
    }

    public BigDecimal getUnclearAmountTotal() {
        return unclearAmountTotal;
    }

    public void setUnclearAmountTotal(BigDecimal unclearAmountTotal) {
        this.unclearAmountTotal = unclearAmountTotal;
    }

    public List<ReceivableDocumentsPageVO> getList() {
        return list;
    }

    public void setList(List<ReceivableDocumentsPageVO> list) {
        this.list = list;
    }
}
