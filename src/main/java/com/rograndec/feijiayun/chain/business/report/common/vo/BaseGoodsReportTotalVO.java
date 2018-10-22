package com.rograndec.feijiayun.chain.business.report.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/20 <br/>
 * 描述：报表返回对象
 */
public class BaseGoodsReportTotalVO<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "合计数量")
    private BigDecimal quantityTotal;

    @ApiModelProperty(value = "合计MAP")
    private Map<String,Object> total;

    @ApiModelProperty(value = "列表")
    private List<T> goodsReportList;


    public Map<String, Object> getTotal() {
        return total;
    }

    public void setTotal(Map<String, Object> total) {
        this.total = total;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public List<T> getGoodsReportList() {
        return goodsReportList;
    }

    public void setGoodsReportList(List<T> goodsReportList) {
        this.goodsReportList = goodsReportList;
    }
}
