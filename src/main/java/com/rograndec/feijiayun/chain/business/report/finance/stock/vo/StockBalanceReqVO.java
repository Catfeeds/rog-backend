package com.rograndec.feijiayun.chain.business.report.finance.stock.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class StockBalanceReqVO extends BasePageReqParam implements Serializable {

    @ApiModelProperty("查询参数")
    private String param;

    @ApiModelProperty("按某一列排序(组织结构编码：enterpriseCode,商品编码:goodsCode )")
    private String order;

    @ApiModelProperty("排序方式（升序：asc,降序：desc）")
    private String sort;

    @ApiModelProperty("后台用: 0-查询自己企业 1-总部根据 chainType 查询分店")
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
