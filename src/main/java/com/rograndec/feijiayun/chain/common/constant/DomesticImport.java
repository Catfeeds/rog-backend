package com.rograndec.feijiayun.chain.common.constant;

/**
 * 国产进口
 * Created by ST on 2017/9/8.
 */
public enum  DomesticImport {

    DOMESTIC(0,"国产"),
    ENABLE(1,"进口");

    private int code;
    private String name;

    private DomesticImport(int code, String name) {
        this.code = code;
        this.name = name;
    }
    public int getCode() {
        return code;
    }
    public void setType(int code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static String getName(int code) {
        for (DomesticImport c : DomesticImport.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }
    public static int getCode(String name) {
        for (DomesticImport c : DomesticImport.values()) {
            if (name.equals(c.getName())) {
                return c.getCode();
            }
        }
        return -1;
    }
}