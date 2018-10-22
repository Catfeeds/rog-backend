package com.rograndec.feijiayun.chain.business.report.storage.vo.goodsDtl;

import com.rograndec.feijiayun.chain.common.vo.RequestBaseParamVO;
import io.swagger.annotations.ApiModelProperty;

public class RequestParamGoodsDtlReportVO  extends RequestBaseParamVO {

    @ApiModelProperty("快速搜索的条件（编码/条形码/商品名称/通用名称/批准文号）")
    private String param;

    @ApiModelProperty(value = "企业id ")
    private Long enterpriseId;

    @ApiModelProperty(value = "商品编码0/倒序；1/正序;默认1 ")
    private Integer goodsCodeOrder;


    @ApiModelProperty("商品ID,详情需要传递")
    private Long goodsId;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Integer getGoodsCodeOrder() {
        return goodsCodeOrder == null ? 1 : goodsCodeOrder;
    }

    public void setGoodsCodeOrder(Integer goodsCodeOrder) {
        this.goodsCodeOrder = goodsCodeOrder;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}