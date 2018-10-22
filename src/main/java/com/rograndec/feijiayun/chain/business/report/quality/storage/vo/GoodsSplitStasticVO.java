package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsSplitStasticVO implements Serializable{

    private BigDecimal quantity;

    private BigDecimal splitQuantity;

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSplitQuantity() {
        return splitQuantity;
    }

    public void setSplitQuantity(BigDecimal splitQuantity) {
        this.splitQuantity = splitQuantity;
    }
}
