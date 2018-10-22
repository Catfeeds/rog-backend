package com.rograndec.feijiayun.chain.business.aftersale.recall.constant;

import java.util.Arrays;

public enum RecallReasonEnum {
    MEDICINEDISQUALIFICATION(0,"药品留样观察中发现质量不合格情况"),
    CONTUMCOMPLAINT(1,"用户（接种者、医生、经销商）来信、来人投诉药品质量情况，经调查属实"),
    SPDACHECK(2,"药品质量监督管理部门抽检通报有质量问题的药品"),
    UNTOWARDEFFECT(3,"用户反映有未知的药品不良反应"),
    WEEDOUTMEDICINE(4,"国家已通报淘汰的药品"),
    ANOTHERNEEDRECALL(5,"其它认为需要召回的药品"),
    SPECIFICATIONDEFECT(6,"药品包装标签说明书内容或者设计印制存在缺陷，影响用药安全的"),
    EFFECTSTIPULATION(7,"执行国家有关的药品召回规定");
    private Integer code;
    private String name;

    RecallReasonEnum(Integer code,String name){
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
    public static RecallReasonEnum getEnum(Integer code){

        RecallReasonEnum recallReasonEnum = Arrays.stream(RecallReasonEnum.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return recallReasonEnum;

    }

    public static String getName(Integer code){

        RecallReasonEnum recallReasonEnum = Arrays.stream(RecallReasonEnum.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return recallReasonEnum.getName();

    }

    public static Integer getCode(String name){

        RecallReasonEnum recallReasonEnum = Arrays.stream(RecallReasonEnum.values())
                .filter(c -> name == (c.name)).findFirst().orElse(null);

        return recallReasonEnum.getCode();

    }

}
