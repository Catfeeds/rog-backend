package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import com.rograndec.feijiayun.chain.common.Page;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class GoodsClearReportVO implements Serializable{

    /**
     * 合计数量
     */
    @ApiModelProperty(value = "合计数量")
    private BigDecimal quantity;

    @ApiModelProperty(value = "page信息")
    private Page<List<GoodClearReportPageVO>> page;

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Page<List<GoodClearReportPageVO>> getPage() {
        return page;
    }

    public void setPage(Page<List<GoodClearReportPageVO>> page) {
        this.page = page;
    }
}
