package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo;

import java.math.BigDecimal;

public class PerpayInvoiceCalcAmountParamVO {


    /**
     * 已清金额
     */
    private BigDecimal clearQuantityAmount ;

    /**
     * 根据实际金额和税率获取不含税金额：金额/(1+税率)  不含税已清金额
     */
    private BigDecimal clearQuantityNotTaxAmount;


    /**
     *
     * 根据实际金额和不含税金额获取税额：金额-不含税金额  已清税额
     */
    private BigDecimal clearQuantityTaxAmount;

    public BigDecimal getClearQuantityAmount() {
        return clearQuantityAmount;
    }

    public void setClearQuantityAmount(BigDecimal clearQuantityAmount) {
        this.clearQuantityAmount = clearQuantityAmount;
    }

    public BigDecimal getClearQuantityNotTaxAmount() {
        return clearQuantityNotTaxAmount;
    }

    public void setClearQuantityNotTaxAmount(BigDecimal clearQuantityNotTaxAmount) {
        this.clearQuantityNotTaxAmount = clearQuantityNotTaxAmount;
    }

    public BigDecimal getClearQuantityTaxAmount() {
        return clearQuantityTaxAmount;
    }

    public void setClearQuantityTaxAmount(BigDecimal clearQuantityTaxAmount) {
        this.clearQuantityTaxAmount = clearQuantityTaxAmount;
    }
}
