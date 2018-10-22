package com.rograndec.feijiayun.chain.business.aftersale.recall.constant;

import java.util.Arrays;

public enum RecallLevelEnum {
    FIRST(0,"一级召回"),
    SECOND(1,"二级召回"),
    THIRD(2,"三级召回");
    private Integer code;
    private String name;

    RecallLevelEnum(Integer code, String name) {
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

    public static RecallLevelEnum getEnum(Integer code){

        RecallLevelEnum recallLevelEnum = Arrays.stream(RecallLevelEnum.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return recallLevelEnum;

    }

    public static String getName(Integer code){

        RecallLevelEnum recallLevelEnum = Arrays.stream(RecallLevelEnum.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return recallLevelEnum.getName();

    }

    public static Integer getCode(String name){

        RecallLevelEnum recallLevelEnum = Arrays.stream(RecallLevelEnum.values())
                .filter(c -> name == (c.name)).findFirst().orElse(null);

        return recallLevelEnum.getCode();

    }
}
