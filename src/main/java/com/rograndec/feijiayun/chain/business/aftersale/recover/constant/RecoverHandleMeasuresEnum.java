package com.rograndec.feijiayun.chain.business.aftersale.recover.constant;

import java.util.Arrays;

public enum RecoverHandleMeasuresEnum {
    RETURNSUPPLIER(0,"退回供货单位"),
    DISTORY(1,"销毁");
    private Integer code;
    private String name;

    RecoverHandleMeasuresEnum(Integer code, String name) {
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

    public static RecoverHandleMeasuresEnum getEnum(Integer code){

        RecoverHandleMeasuresEnum handleMeasuresEnum = Arrays.stream(RecoverHandleMeasuresEnum.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return handleMeasuresEnum;

    }

    public static String getName(Integer code){

        RecoverHandleMeasuresEnum handleMeasuresEnum = Arrays.stream(RecoverHandleMeasuresEnum.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return handleMeasuresEnum.getName();

    }

    public static Integer getCode(String name){

        RecoverHandleMeasuresEnum handleMeasuresEnum = Arrays.stream(RecoverHandleMeasuresEnum.values())
                .filter(c -> name == (c.name)).findFirst().orElse(null);

        return handleMeasuresEnum.getCode();

    }

}
