package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class GoodsLoadReportExcelPageVO implements Serializable{

    /**
     * 数量
     */
    private BigDecimal quantity;

    /**
     * excel List信息
     */
    private List<GoodsLoadExcelVO> list;

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public List<GoodsLoadExcelVO> getList() {
        return list;
    }

    public void setList(List<GoodsLoadExcelVO> list) {
        this.list = list;
    }
}
