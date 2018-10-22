package com.rograndec.feijiayun.chain.common.constant.status;

/**
 * 功能描述：会计期间
 * Created by dongdong.zhang on 2018/01/01 15:16
 */

public enum PeriodStatus {

    OPEN(0,"已激活"),
    CLOSE(1,"已结账");

    private Integer code;
    private String name;

    private PeriodStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    public Integer getCode() {
        return code;
    }
    public void setType(Integer code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static String getName(Integer code) {
    	if(code == null) return null;
        for (PeriodStatus c : PeriodStatus.values()) {
            if (c.getCode().equals(code)) {
                return c.getName();
            }
        }
        return null;
    }

    public static Integer getCode(String name) {
    	if(name == null) return -1;
        for (PeriodStatus c : PeriodStatus.values()) {
            if (name.equals(c.getName())) {
                return c.getCode();
            }
        }
        return -1;
    }
}