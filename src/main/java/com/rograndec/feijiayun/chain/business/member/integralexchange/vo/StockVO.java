package com.rograndec.feijiayun.chain.business.member.integralexchange.vo;

import com.rograndec.feijiayun.chain.business.keytable.entity.Stock;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class StockVO extends Stock {

    private BigDecimal  exchangeQuanlity;

    public StockVO(Stock s) {

        BeanUtils.copyProperties(s,this);

    }

    public StockVO(){}

    public BigDecimal getExchangeQuanlity() {
        return exchangeQuanlity;
    }

    public void setExchangeQuanlity(BigDecimal exchangeQuanlity) {
        this.exchangeQuanlity = exchangeQuanlity;
    }

}
