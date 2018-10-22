package com.rograndec.feijiayun.chain.common.constant;

public enum PrescriptionDrug {
    OTC(0,"非处方药"),
    RX_DRUG(1,"处方药");

    private int code;
    private String name;

    PrescriptionDrug(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public static String getName(int code) {
        for (PrescriptionDrug c : PrescriptionDrug.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }

    public static int getCode(String name) {
        for (PrescriptionDrug c : PrescriptionDrug.values()) {
            if (c.getName().equals(name)) {
                return c.getCode();
            }
        }
        return -1;
    }
}
