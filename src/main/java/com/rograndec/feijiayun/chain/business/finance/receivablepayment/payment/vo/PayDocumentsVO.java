package com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.vo;

import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo.ReceivableDocumentsPageVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class PayDocumentsVO implements Serializable{

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
    private List<PayDocumentsPageVO> list;

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

    public List<PayDocumentsPageVO> getList() {
        return list;
    }

    public void setList(List<PayDocumentsPageVO> list) {
        this.list = list;
    }
}
