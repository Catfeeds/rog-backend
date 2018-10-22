package com.rograndec.feijiayun.chain.common.constant;

//报货方式（0-电话 1-传真 2-邮件 3-系统接口）
public enum SupplierDeliveryMode {

    TEL(0,"电话"),
    TAX(1,"传真"),
    MAIL(2,"邮件"),
    INTERFACE(3,"系统接口");

    private int code;
    private String name;

    private SupplierDeliveryMode(int code, String name) {
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
        for (SupplierDeliveryMode c : SupplierDeliveryMode.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }
    public static int getCode(String name) {
        for (SupplierDeliveryMode c : SupplierDeliveryMode.values()) {
            if (name.equals(c.getName())) {
                return c.getCode();
            }
        }
        return -1;
    }
}
