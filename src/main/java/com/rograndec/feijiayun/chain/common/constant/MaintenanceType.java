package com.rograndec.feijiayun.chain.common.constant;

/**
 * 国产进口
 * Created by ST on 2017/9/8.
 */
public enum MaintenanceType {

    DELIVERYMODE_A(0,"常规养护"),
    DELIVERYMODE_B(1,"重点养护");

    private int code;
    private String name;

    private MaintenanceType(int code, String name) {
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

    public static String getName(Integer code) {
    	if(code == null){
    		return null;
    	}
        for (MaintenanceType c : MaintenanceType.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }
    public static int getCode(String name) {
        for (MaintenanceType c : MaintenanceType.values()) {
            if (name.equals(c.getName())) {
                return c.getCode();
            }
        }
        return -1;
    }
}