package com.rograndec.feijiayun.chain.business.report.finance.stock.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: chain-backend
 * @description: 基础出入库数据
 * @author: dongyang.du
 * @create: 2018-01-14 19:06
 **/
public class BaseInOutTotal  implements Serializable{



    @ApiModelProperty(value = "发出数量")
    private BigDecimal outQuantity;

    @ApiModelProperty(value = "发出金额")
    private BigDecimal outAmount;

    @ApiModelProperty(value = "收入数量")
    private BigDecimal inQuantity;

    @ApiModelProperty(value = "收入金额")
    private BigDecimal inAmount;


    @ApiModelProperty(value = "期初数量")
    private BigDecimal startQuantity;

    @ApiModelProperty(value = "期初金额")
    private BigDecimal startAmount;


    @ApiModelProperty(value = "期末数量")
    private BigDecimal endQuantity;

    @ApiModelProperty(value = "期末金额")
    private BigDecimal endAmount;

    public BigDecimal getOutQuantity() {
        return outQuantity;
    }

    public void setOutQuantity(BigDecimal outQuantity) {
        this.outQuantity = outQuantity;
    }

    public BigDecimal getOutAmount() {
        return outAmount;
    }

    public void setOutAmount(BigDecimal outAmount) {
        this.outAmount = outAmount;
    }

    public BigDecimal getInQuantity() {
        return inQuantity;
    }

    public void setInQuantity(BigDecimal inQuantity) {
        this.inQuantity = inQuantity;
    }

    public BigDecimal getInAmount() {
        return inAmount;
    }

    public void setInAmount(BigDecimal inAmount) {
        this.inAmount = inAmount;
    }

    public BigDecimal getStartQuantity() {
        return startQuantity;
    }

    public void setStartQuantity(BigDecimal startQuantity) {
        this.startQuantity = startQuantity;
    }

    public BigDecimal getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(BigDecimal startAmount) {
        this.startAmount = startAmount;
    }

    public BigDecimal getEndQuantity() {
        return endQuantity;
    }

    public void setEndQuantity(BigDecimal endQuantity) {
        this.endQuantity = endQuantity;
    }

    public BigDecimal getEndAmount() {
        return endAmount;
    }

    public void setEndAmount(BigDecimal endAmount) {
        this.endAmount = endAmount;
    }
}
