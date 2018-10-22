package com.rograndec.feijiayun.chain.business.storage.inventory.constant;

import java.util.Arrays;

/**
 * 功能描述：
 * Created by ST on 2017/10/28 11:55
 */

public enum  InventoryBizExceptionEnum {

    EXCEPTION_1(1,"盘点单中不存在该商品"),
    EXCEPTION_2(2,"盘点单缺失商品"),
    EXCEPTION_3(3,"盘点单商品重复");

    private int code;
    private String value;

    InventoryBizExceptionEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static InventoryBizExceptionEnum getEnum(int code){
        InventoryBizExceptionEnum flowUnitType = Arrays.stream(InventoryBizExceptionEnum.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return flowUnitType;

    }

    public static String getValue(int code){

        InventoryBizExceptionEnum flowUnitType = Arrays.stream(InventoryBizExceptionEnum.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return flowUnitType.getValue();

    }
}