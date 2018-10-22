package com.rograndec.feijiayun.chain.common.constant;


import java.util.Arrays;

/**
 *文件类型（0-质量策划；1-质量控制；2-质量保证；3-质量改进；4-质量风险）
 */
public enum FileType {

    PLAN(0,"质量策划"),
    CONTROL(1,"质量控制"),
    ENSURE(2,"质量保证"),
    IMPROVEMENT(3,"质量改进"),
    RISK(4,"质量风险");


    private int code;
    private String value;

    FileType(int code, String value) {
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

    public static FileType getEnum(int code){

        FileType obj = Arrays.stream(FileType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return obj;

    }

    public static String getValue(int code){

        FileType obj = Arrays.stream(FileType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return obj.getValue();

    }
}
