package com.rograndec.feijiayun.chain.business.storage.maintance.constant;

public enum MaintanceType {

    NormalMaintanceType(0,"常规养护"),
    ImportantMaintanceType(1,"重点养护");

    MaintanceType(Integer code,String name){
        this.code = code;
        this.name = name;
    }
    private Integer code;
    private String name;
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
        for (MaintanceType c : MaintanceType.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }

    public static int getCode(String name) {
        for (MaintanceType c : MaintanceType.values()) {
            if (c.getName().equals(name)) {
                return c.getCode();
            }
        }
        return -1;
    }
}
