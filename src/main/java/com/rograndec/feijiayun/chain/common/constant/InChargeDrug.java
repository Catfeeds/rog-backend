package com.rograndec.feijiayun.chain.common.constant;

public enum InChargeDrug {
    SPECIAL(0,"含特殊药品复方制剂"),
    PROTEIN(1,"蛋白同化制剂"),
    PEPTIDES(2,"肽类激素");

    private int code;
    private String name;

    private InChargeDrug(int code, String name) {
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
        for (InChargeDrug c : InChargeDrug.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }

    public static int getCode(String name) {
        for (InChargeDrug c : InChargeDrug.values()) {
            if (c.getName().equals(name)) {
                return c.getCode();
            }
        }
        return -1;
    }
}
