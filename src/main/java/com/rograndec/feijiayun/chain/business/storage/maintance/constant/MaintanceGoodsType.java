package com.rograndec.feijiayun.chain.business.storage.maintance.constant;

public enum MaintanceGoodsType {

    PatentMedicine(0,"成药"),
    ChineseMedicine(1,"中药饮片"),
    DismountedDrugs(2,"拆零药品"),
    NearMedicine(3,"近效期药品");

    MaintanceGoodsType(Integer code, String name){
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
        for (MaintanceGoodsType c : MaintanceGoodsType.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }

    public static int getCode(String name) {
        for (MaintanceGoodsType c : MaintanceGoodsType.values()) {
            if (c.getName().equals(name)) {
                return c.getCode();
            }
        }
        return -1;
    }
}
