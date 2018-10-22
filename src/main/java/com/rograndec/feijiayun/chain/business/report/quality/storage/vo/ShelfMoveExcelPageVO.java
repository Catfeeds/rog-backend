package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ShelfMoveExcelPageVO implements Serializable{

    private BigDecimal quantity;

    private List<ShelfMoveExcelVO> list;

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public List<ShelfMoveExcelVO> getList() {
        return list;
    }

    public void setList(List<ShelfMoveExcelVO> list) {
        this.list = list;
    }
}
