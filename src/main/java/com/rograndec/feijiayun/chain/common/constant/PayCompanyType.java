package com.rograndec.feijiayun.chain.common.constant;

public enum PayCompanyType {

    SUPPLIER(0,"供货单位"),
    PURCHASEUNIT(1,"购货单位");

    private int code;
    private String name;

    private PayCompanyType(int code, String name) {
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
        for (PayCompanyType c : PayCompanyType.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }
}
