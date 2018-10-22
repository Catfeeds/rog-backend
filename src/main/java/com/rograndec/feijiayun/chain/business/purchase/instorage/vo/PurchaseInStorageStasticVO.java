package com.rograndec.feijiayun.chain.business.purchase.instorage.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class PurchaseInStorageStasticVO implements Serializable{

    private BigDecimal totalMoney;

    private BigDecimal noTaxTotalMoney;

    private BigDecimal taxTotalMoney;

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getNoTaxTotalMoney() {
        return noTaxTotalMoney;
    }

    public void setNoTaxTotalMoney(BigDecimal noTaxTotalMoney) {
        this.noTaxTotalMoney = noTaxTotalMoney;
    }

    public BigDecimal getTaxTotalMoney() {
        return taxTotalMoney;
    }

    public void setTaxTotalMoney(BigDecimal taxTotalMoney) {
        this.taxTotalMoney = taxTotalMoney;
    }
}
