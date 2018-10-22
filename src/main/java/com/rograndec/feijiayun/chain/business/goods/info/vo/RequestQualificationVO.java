package com.rograndec.feijiayun.chain.business.goods.info.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：
 * Created by ST on 2017/9/13 16:47
 */
public class RequestQualificationVO {

    @ApiModelProperty(value = "验收分类ID")
    private Long checkTypeId;

    @ApiModelProperty(value = "资质类型是否必选（0-可选；1-必选）")
    private Integer type;

    @ApiModelProperty(value = "商品id ,当goodsId为空时，表示查询可选的商品资质；不为空查询的是该商品id未绑定的可选的商品资质")
    private Long goodsId;


    public Long getCheckTypeId() {
        return checkTypeId;
    }

    public void setCheckTypeId(Long checkTypeId) {
        this.checkTypeId = checkTypeId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}