package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import com.rograndec.feijiayun.chain.common.Page;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class GoodsSplitReportVO implements Serializable{

    /**
     * 数量
     */
    @ApiModelProperty(value="数量")
    private BigDecimal quantity;

    /**
     * 商品拆零数量
     */
    @ApiModelProperty(value="商品拆零数量")
    private BigDecimal splitQuantity;

    /**
     * page信息
     */
    @ApiModelProperty(value="page信息")
    private Page<List<GoodsSplitReportPageVO>> page;

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

    public Page<List<GoodsSplitReportPageVO>> getPage() {
        return page;
    }

    public void setPage(Page<List<GoodsSplitReportPageVO>> page) {
        this.page = page;
    }
}
