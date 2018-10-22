package com.rograndec.feijiayun.chain.business.aftersale.recall.constant;

import java.util.Arrays;

public enum HandleMeasuresEnum {
    CHANGEPACKAGEANDSALE(0,"更换包装后重新销售"),
    RETURNSUPPLIER(1,"退回供货单位"),
    DISTORY(2,"销毁");
    private Integer code;
    private String name;

    HandleMeasuresEnum(Integer code, String name) {
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

    public static HandleMeasuresEnum getEnum(Integer code){

        HandleMeasuresEnum handleMeasuresEnum = Arrays.stream(HandleMeasuresEnum.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return handleMeasuresEnum;

    }

    public static String getName(Integer code){

        HandleMeasuresEnum handleMeasuresEnum = Arrays.stream(HandleMeasuresEnum.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return handleMeasuresEnum.getName();

    }

    public static Integer getCode(String name){

        HandleMeasuresEnum handleMeasuresEnum = Arrays.stream(HandleMeasuresEnum.values())
                .filter(c -> name == (c.name)).findFirst().orElse(null);

        return handleMeasuresEnum.getCode();

    }

}
