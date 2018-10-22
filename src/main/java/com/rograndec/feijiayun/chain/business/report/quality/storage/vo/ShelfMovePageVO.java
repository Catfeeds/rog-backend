package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import com.rograndec.feijiayun.chain.common.Page;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ShelfMovePageVO implements Serializable{

    /**
     * 当前搜索条件下合计的VO
     */
    @ApiModelProperty(value = "当前搜索条件下合计的VO")
    private BigDecimal quantity;

    /**
     * 分页的每一行数据的list
     */
    @ApiModelProperty(value = "分页的每一行数据的list")
    private Page<List<ShelfMoveVO>> page;

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Page<List<ShelfMoveVO>> getPage() {
        return page;
    }

    public void setPage(Page<List<ShelfMoveVO>> page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "ShelfMovePageVO{" +
                "quantity=" + quantity +
                ", page=" + page +
                '}';
    }
}
