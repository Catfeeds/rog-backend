package com.rograndec.feijiayun.chain.common.constant;

/** 
* @Description: 补货策略
 * 补货政策（0-动态存量补货；1-安全库存补货；2-人工补货；3-禁止补货）
* @return:  
* @Author: dongyang.du
* @Date: 31/01/2018 
*/ 
public enum ReplenishmentPolicyType {

    DYNAMIC_STOCK(0,"动态存量补货"),
    SAFETY_STOCK(1,"安全库存补货"),
    MANUAL(2,"人工补货"),
    PROHIBIT(3,"禁止补货");

    private int code;
    private String name;

    private ReplenishmentPolicyType(int code, String name) {
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
        for (ManagementMode c : ManagementMode.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }
    public static int getCode(String name) {
        for (ManagementMode c : ManagementMode.values()) {
            if (c.getName().equals(name)) {
                return c.getCode();
            }
        }
        return -1;
    }

}
