package com.rograndec.feijiayun.chain.common.constant.status;

/**
 * 功能描述：实销实结核销单状态
 * Created by ruifeng.jia
 */

public enum VerificationFormStatus {

    FINISH(0,"已完成"),
    WRITE_OFF(1,"已冲销");

    private Integer code;
    private String name;

    private VerificationFormStatus(Integer code, String name) {
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
        for (VerificationFormStatus c : VerificationFormStatus.values()) {
            if (c.getCode().equals(code)) {
                return c.getName();
            }
        }
        return null;
    }

    public static Integer getCode(String name) {
    	if(name == null) return -1;
        for (VerificationFormStatus c : VerificationFormStatus.values()) {
            if (name.equals(c.getName())) {
                return c.getCode();
            }
        }
        return -1;
    }
}