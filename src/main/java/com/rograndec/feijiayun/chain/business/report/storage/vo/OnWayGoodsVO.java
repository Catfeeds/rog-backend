package com.rograndec.feijiayun.chain.business.report.storage.vo;

import com.rograndec.feijiayun.chain.common.Page;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OnWayGoodsVO implements Serializable{

    /**
     * 合计库存数量
     */
    @ApiModelProperty(value = "合计库存数量")
    private BigDecimal quantity;

    /**
     * 合计在途库存
     */
    @ApiModelProperty(value = "合计在途库存")
    private BigDecimal onWayQuantity;

    private Page<List<OnWayGoodsPageVO>> page;

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getOnWayQuantity() {
        return onWayQuantity;
    }

    public void setOnWayQuantity(BigDecimal onWayQuantity) {
        this.onWayQuantity = onWayQuantity;
    }

    public Page<List<OnWayGoodsPageVO>> getPage() {
        return page;
    }

    public void setPage(Page<List<OnWayGoodsPageVO>> page) {
        this.page = page;
    }
}
