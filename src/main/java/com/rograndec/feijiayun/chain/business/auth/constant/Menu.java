package com.rograndec.feijiayun.chain.business.auth.constant;

import java.util.Arrays;

/**
 * 菜单枚举
 */
public enum Menu {


    SYSTEM_LOGIN("01","系统登录"),
    MENU_MANAGER("02","菜单管理"),
    SYSTEM_OPERATION("03","系统运营"),
    POS("63","POS系统");
    private String code;
    private String value;

    Menu(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static Menu getEnum(String code){

        Menu menus = Arrays.stream(Menu.values())
                .filter(c -> code.equals(c.code)).findFirst().orElse(null);

        return menus;

    }

    public static String getValue(String code){

        Menu menus = Arrays.stream(Menu.values())
                .filter(c -> code.equals(c.code)).findFirst().orElse(null);

        return menus.getValue();

    }

    public static boolean comper(String code,Integer chainType){

        Menu menus = Arrays.stream(Menu.values())
                .filter(c -> code.equals(c.code)).findFirst().orElse(null);

        return menus != null;

    }
}
