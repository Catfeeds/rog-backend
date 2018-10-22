package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class GoodsClearReportExcelPageVO implements Serializable{

    private BigDecimal quantity;

    private List<GoodsClearReportExcelVO> list;

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public List<GoodsClearReportExcelVO> getList() {
        return list;
    }

    public void setList(List<GoodsClearReportExcelVO> list) {
        this.list = list;
    }
}
