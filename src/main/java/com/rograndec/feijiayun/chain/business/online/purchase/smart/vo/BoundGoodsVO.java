package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class BoundGoodsVO implements Serializable {

    @ApiModelProperty(value = "绑定商品信息", required = true)
    private String boundGoods;

    @ApiModelProperty(value = "saas商品ID", required = true)
    private Long goodsId;

    public String getBoundGoods() {
        return boundGoods;
    }

    public void setBoundGoods(String boundGoods) {
        this.boundGoods = boundGoods;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
