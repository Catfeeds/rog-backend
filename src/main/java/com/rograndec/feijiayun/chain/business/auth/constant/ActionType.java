package com.rograndec.feijiayun.chain.business.auth.constant;

import java.util.Arrays;

/**
 * 类型（0-系统菜单；1-管理菜单）
 */
public enum ActionType {

    //0-总部配送；3-分店间调剂；4-直调配送

    SYSTEM_MENU(0,"系统菜单"),
    MANAGER_MENU(1,"管理菜单");
    private int code;
    private String value;

    ActionType(int code, String value) {
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

    public static ActionType getEnum(int code){

        ActionType distrInNoticeType = Arrays.stream(ActionType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return distrInNoticeType;

    }

    public static String getValue(int code){

        ActionType distrInNoticeType = Arrays.stream(ActionType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return distrInNoticeType.getValue();

    }
}
