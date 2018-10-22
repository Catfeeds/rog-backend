package com.rograndec.feijiayun.chain.business.aftersale.adverse.constant;

import java.util.Arrays;

/**
 * 0-吸烟史；1-饮酒史；2-妊娠史；3-肝病史；4-肾病史；5-过敏史；6-其它
 */
public enum ImportantInfoType {

    SMOKING_HISTORY(0,"吸烟史"),
    DRINKING_HISTORY(1,"饮酒史"),
    PREGNANCY_HISTORY(2,"妊娠史"),
    LIVER_DISEASE_HISTORY(3,"肝病史"),
    NEPHROPATHY_HISTORY(4,"肾病史"),
    ALLERGY_HISTORY(5,"过敏史"),
    OTHER(6,"其他");




    private int code;
    private String value;

    ImportantInfoType(int code, String value) {
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

    public static ImportantInfoType getEnum(int code){

        ImportantInfoType obj = Arrays.stream(ImportantInfoType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return obj;

    }

    public static String getValue(int code){

        ImportantInfoType obj = Arrays.stream(ImportantInfoType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return obj.getValue();

    }

}
