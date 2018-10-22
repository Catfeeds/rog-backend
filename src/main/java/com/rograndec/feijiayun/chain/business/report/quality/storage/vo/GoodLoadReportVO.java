package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.valid.RequestParamValid;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class GoodLoadReportVO implements Serializable{

    /**
     * 合计数量
     */
    @ApiModelProperty(value = "合计数量")
    private BigDecimal quantity;

    /**
     * page信息
     */
    @ApiModelProperty(value = "page信息")
    private Page<List<GoodLoadReportPageVO>> page;

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Page<List<GoodLoadReportPageVO>> getPage() {
        return page;
    }

    public void setPage(Page<List<GoodLoadReportPageVO>> page) {
        this.page = page;
    }
}
