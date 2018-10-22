package com.rograndec.feijiayun.chain.inf.rogrand.constant;

/**
 * 功能描述：
 * Created by ST on 2017/10/31 19:47
 */

public enum  RGTQualification {

    GPS(3,"GSP证书"),
    MEDICAL_DEVICES(6,"医疗器械经营许可证"),
    FOOD_DISTRIBUTION_LICENSE(7,"食品流通许可证"),
    DRUG_QUALITY(14,"药品经营质量保证协议 ")//质量协议书
    ;



    RGTQualification(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private int code;

    private String name;

    public static String getName(int code) {
        for (RGTQualification c : RGTQualification.values()) {
            if (c.getCode()== code) {
                return c.getName();
            }
        }
        return null;
    }

    public static String getSaaSQualityName(int code) {
        for (RGTQualification c : RGTQualification.values()) {
            if (c.getCode()== code) {
                String name = c.getName();
                if("医疗器械经营许可证".equals(name)){
                    return name;
                }
                if("食品流通许可证".equals(name)){
                    return name;
                }
                if("药品经营质量保证协议".equals(name)){
                    return "质量协议书";
                }
            }
        }
        return null;
    }

    public static int getCode(String name) {
        for (RGTQualification c : RGTQualification.values()) {
            if (c.getName().equals(name)) {
                return c.getCode();
            }
        }
        return -1;
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
}