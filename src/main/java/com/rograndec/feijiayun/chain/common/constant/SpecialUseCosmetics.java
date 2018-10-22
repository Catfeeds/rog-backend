package com.rograndec.feijiayun.chain.common.constant;

/**
 * 特殊化妆品分类
 * 0-育发；1-染发；2-烫发；3-脱毛；4-美乳；5-健美；6-除臭；7-祛斑；8-防嗮
 * @author dongyang.du
 * @version 1.0
 * @date 2017年10月20日
 */
public enum SpecialUseCosmetics {


    HAIR(0,"育发"),
    HAIR_DYE(1,"染发"),
    PERM(2,"烫发"),
    SHED(3,"脱毛"),
    BEAUTIFUL_BREAST(4,"美乳"),
    STRONGANDHANDSOME(5,"健美"),
    DEODORIZATION(6,"除臭"),
    FRECKLE(7,"祛斑"),
    SUNSCREEN(8,"防嗮");


    private int code;
    private String name;

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

    SpecialUseCosmetics(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (SpecialUseCosmetics c : SpecialUseCosmetics.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }

    public static int getCode(String name) {
        for (SpecialUseCosmetics c : SpecialUseCosmetics.values()) {
            if (c.getName().equals(name)) {
                return c.getCode();
            }
        }
        return -1;
    }
}
