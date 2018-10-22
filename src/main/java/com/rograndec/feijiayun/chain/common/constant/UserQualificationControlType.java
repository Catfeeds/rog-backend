package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

/**
 * 员工资质控制类型 0-质量控制；1-仅提示）
 * Created by zhaiwei on 2017/8/31.
 */
public enum UserQualificationControlType {


    QUALIFICATION_CONTROL(0,"质量控制"),
    ONLY_SHOW(1,"仅提示");

    private int code;
    private String value;

    UserQualificationControlType(int code, String value) {
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

    public static UserQualificationControlType getUserQualificationControlTypeEnum(int code){

        UserQualificationControlType userTypeEum = Arrays.stream(UserQualificationControlType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return userTypeEum;

    }
}
