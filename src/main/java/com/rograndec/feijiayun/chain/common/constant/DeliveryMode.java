package com.rograndec.feijiayun.chain.common.constant;

/**
 * 国产进口
 * Created by ST on 2017/9/8.
 */
public enum DeliveryMode {

    DELIVERYMODE_A(0,"配送中心配送"),
    DELIVERYMODE_B(1,"供货单位配送");

    private int code;
    private String name;

    private DeliveryMode(int code, String name) {
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
        for (DeliveryMode c : DeliveryMode.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }
    public static int getCode(String name) {
        for (DeliveryMode c : DeliveryMode.values()) {
            if (name.equals(c.getName())) {
                return c.getCode();
            }
        }
        return -1;
    }
}