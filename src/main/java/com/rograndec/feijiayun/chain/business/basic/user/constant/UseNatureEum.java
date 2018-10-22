package com.rograndec.feijiayun.chain.business.basic.user.constant;

import java.util.Arrays;

/**
 * 用工性质
 * Created by zhangyu on 2018-1-31.
 */
public enum UseNatureEum {

    PART_TIME(0,"兼职"),
    FULL_TIME(1,"全职");


    private int code;
    private String value;

    UseNatureEum(int code, String value) {
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

    public static UseNatureEum getUseNatureEumEum4Code(int code){

        UseNatureEum useNatureEum = Arrays.stream(UseNatureEum.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return useNatureEum;

    }

    public static UseNatureEum getUseNatureEumEum4Value(String value){

        UseNatureEum useNatureEum = Arrays.stream(UseNatureEum.values())
                .filter(c -> value.equals(c.value)).findFirst().orElse(null);

        return useNatureEum;

    }
    public static String getValue(Integer code){
        for (UseNatureEum c : UseNatureEum.values()) {
            if (c.getCode() == code) {
                return c.getValue();
            }
        }
        return null;
    }
}
