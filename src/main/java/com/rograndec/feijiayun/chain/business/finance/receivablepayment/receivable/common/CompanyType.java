package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.common;

public enum CompanyType {
    //0-供货单位；1-购货单位
    SUPPLIER(0,"供货单位"),
    PURCHASE(1,"购货单位");
    private int code;
    private String name;

    private CompanyType(int code, String name) {
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
        for (CompanyType c : CompanyType.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }
}
