package com.rograndec.feijiayun.chain.business.member.integralexchange.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by dudy on 2017/9/21.
 */

@ApiModel(value = "IntegralExchangeGoodsRequestVO", description = "新增请求对象")
public class IntegralExchangeGoodsRequestVO implements Serializable {

    @ApiModelProperty(value = "主键ID(新增不需要，修改必传)")
    private  Long id;

    @ApiModelProperty(value = "商品ID")
    private  Long goodsId;

    /**
     * 兑换积分
     */
    @ApiModelProperty(value = "兑换积分")
    private BigDecimal integralExchange;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getIntegralExchange() {
        return integralExchange;
    }

    public void setIntegralExchange(BigDecimal integralExchange) {
        this.integralExchange = integralExchange;
    }

    @Override
    public String toString() {
        return "IntegralExchangeGoodsRequestVO{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", integralExchange=" + integralExchange +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntegralExchangeGoodsRequestVO requestVO = (IntegralExchangeGoodsRequestVO) o;

        return id != null ? id.equals(requestVO.id) : requestVO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
