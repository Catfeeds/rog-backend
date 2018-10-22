package com.rograndec.feijiayun.chain.business.keytable.vo;


import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;

import java.math.BigDecimal;

/**
 * 平均成本值对象，用于功能
 * 装斗／货位移动
 * 其它出库／销毁／清斗
 * 商品拆零等
 *
 *
 * @Author zhongyi.li
 * @Date 2018-01-02 12:08:54
 */
public class AvgCostVO {

    // 平均成本价
    private BigDecimal avgCostPrice;
    // 平均不含税成本价
    private BigDecimal avgNotaxCostPrice;

    public BigDecimal getAvgCostPrice() {
        return avgCostPrice;
    }

    public void setAvgCostPrice(BigDecimal avgCostPrice) {
        this.avgCostPrice = avgCostPrice;
    }

    public BigDecimal getAvgNotaxCostPrice() {
        return avgNotaxCostPrice;
    }

    public void setAvgNotaxCostPrice(BigDecimal avgNotaxCostPrice) {
        this.avgNotaxCostPrice = avgNotaxCostPrice;
    }

}