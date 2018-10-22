package com.rograndec.feijiayun.chain.business.basic.user.constant;

import java.util.Arrays;

/**
 * Created by zhaiwei on 2017/8/28.
 */
public enum QueryDepartmentType {

    QUERY_TYPE_ENTERPRISE(0,"enterprise"),
    ALL(1,"all");

    private int code;
    private String value;

    QueryDepartmentType(int code, String value) {
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

    public static QueryDepartmentType getQueryDepartmentType4Code(int code){

        QueryDepartmentType userTypeEum = Arrays.stream(QueryDepartmentType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return userTypeEum;

    }

    public static QueryDepartmentType getQueryDepartmentType4Value(String value){

        QueryDepartmentType userTypeEum = Arrays.stream(QueryDepartmentType.values())
                .filter(c -> value.equals(c.value)).findFirst().orElse(null);

        return userTypeEum;

    }
}
