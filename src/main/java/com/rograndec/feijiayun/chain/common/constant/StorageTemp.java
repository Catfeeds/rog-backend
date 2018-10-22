package com.rograndec.feijiayun.chain.common.constant;

/**
 * 国产进口 0-常温；1-阴凉；2-冷藏；3-冷冻
 * Created by ST on 2017/9/8.
 */
public enum StorageTemp {

    STORAGETEMP_A(0,"常温"),
    STORAGETEMP_B(1,"阴凉"),
    STORAGETEMP_C(2,"冷藏"),
    STORAGETEMP_D(3,"冷冻");

    private int code;
    private String name;

    private StorageTemp(int code, String name) {
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
        for (StorageTemp c : StorageTemp.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }
    public static int getCode(String name) {
        for (StorageTemp c : StorageTemp.values()) {
            if (name.equals(c.getName())) {
                return c.getCode();
            }
        }
        return -1;
    }
}