package com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class FieldVO implements Serializable {

    @ApiModelProperty(value = "ID", required = true)
    private int key;

    @ApiModelProperty(value = "对应数据", required = true)
    private String data;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FieldVO[" +
                "key=" + key +
                ", data='" + data + '\'' +
                ']';
    }
}
