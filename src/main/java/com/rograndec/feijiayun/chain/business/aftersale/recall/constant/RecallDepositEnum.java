package com.rograndec.feijiayun.chain.business.aftersale.recall.constant;

import java.util.Arrays;

public enum RecallDepositEnum {
    REBACKCOMPANY(0,"退回公司"),
    ANOTHERPLACESAFEKEEPING(1,"异地封存");
    private Integer code;
    private String name;

    RecallDepositEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RecallDepositEnum getEnum(Integer code){

        RecallDepositEnum recallDepositEnum = Arrays.stream(RecallDepositEnum.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return recallDepositEnum;

    }

    public static String getName(Integer code){

        RecallDepositEnum recallDepositEnum = Arrays.stream(RecallDepositEnum.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return recallDepositEnum.getName();

    }

    public static Integer getCode(String name){

        RecallDepositEnum recallDepositEnum = Arrays.stream(RecallDepositEnum.values())
                .filter(c -> name == (c.name)).findFirst().orElse(null);

        return recallDepositEnum.getCode();

    }

}
