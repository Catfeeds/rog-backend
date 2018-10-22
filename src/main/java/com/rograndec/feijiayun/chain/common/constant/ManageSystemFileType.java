package com.rograndec.feijiayun.chain.common.constant;

import java.util.Arrays;

/**
 * 文件类型（0-质量管理制度；1-部门职责；2-岗位职责；3-操作规程；4-档案；5-报告；6-记录和凭证）
 */
public enum ManageSystemFileType {

    QUALITYASSURANCE(0,"质量管理制度"),
    DEPARTMENTDUTY(1,"部门职责"),
    POSTDUTY(2,"岗位职责"),
    OPERATINGPROCEDURES(3,"操作规程"),
    ARCHIVES(4,"档案"),
    PRESENTATION(5,"报告"),
    RECORDANDVOUCHER(6,"记录和凭证");


    private int code;
    private String value;

    ManageSystemFileType(int code, String value) {
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

    public static ManageSystemFileType getEnum(int code){

        ManageSystemFileType obj = Arrays.stream(ManageSystemFileType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return obj;

    }

    public static String getValue(int code){

        ManageSystemFileType obj = Arrays.stream(ManageSystemFileType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return obj.getValue();

    }
}
