package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import java.math.BigDecimal;

/**
 * 功能描述：
 * Created by ST on 2017/9/23 14:04
 */

public class DistrGoodsParamVO {
    //企业id
    private Long enterpriseId;
    //商品id
    private Long goodsId;
    //出库数量
    private BigDecimal quantity;

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}