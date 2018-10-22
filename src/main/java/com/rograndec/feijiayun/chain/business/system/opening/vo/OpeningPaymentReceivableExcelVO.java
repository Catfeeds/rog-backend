package com.rograndec.feijiayun.chain.business.system.opening.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


public class OpeningPaymentReceivableExcelVO implements Serializable {

    /**
     * 供货（购货）单位编码
     */
    @ApiModelProperty(value = "供货（购货）单位编码")
    private String code;

    /**
     * 供货（购货）单位名称
     */
    @ApiModelProperty(value = "供货（购货）单位名称")
    private String name;
    /**
     * 方向
     */
    @ApiModelProperty(value = "方向")
    private String direction ;
    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private String amount;

    public OpeningPaymentReceivableExcelVO() {
    }
    public OpeningPaymentReceivableExcelVO(String code, String name, String direction, String amount) {
        this.code=code;
        this.name=name;
        this.direction=direction;
        this.amount=amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}