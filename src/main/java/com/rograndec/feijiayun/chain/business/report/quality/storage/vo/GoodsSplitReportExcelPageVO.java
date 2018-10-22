package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class GoodsSplitReportExcelPageVO implements Serializable{

    private BigDecimal quantity;

    private BigDecimal splitQuantity;

    private List<GoodsSplitReportExcelVO> list;

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

    public List<GoodsSplitReportExcelVO> getList() {
        return list;
    }

    public void setList(List<GoodsSplitReportExcelVO> list) {
        this.list = list;
    }
}
