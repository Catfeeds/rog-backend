package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

/**
 * Created by madong on 2017/9/6.
 * 提成方式枚举类
 */
public enum CommissionMethod {

    SALESQUANTITY(0,"销售数量"),
    SALEROOM(1,"销售金额"),
    TOTALPROFIT(2,"利润金额"),
    PROFITDRAWING(3,"利润率");

    private int code;
    private String name;
    CommissionMethod(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CommissionMethod getEnum(int code){

        CommissionMethod commissionMethod = Arrays.stream(CommissionMethod.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return commissionMethod;

    }

    public static String getValue(int code){

        CommissionMethod commissionMethod = Arrays.stream(CommissionMethod.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return commissionMethod.getName();

    }

}
