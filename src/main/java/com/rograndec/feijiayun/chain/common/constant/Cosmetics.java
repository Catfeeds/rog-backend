package com.rograndec.feijiayun.chain.common.constant;

public enum Cosmetics {
    RAISING_HAIR(0,"育发"),
    HAIR_COLORING(1,"染发"),
    PERM(2,"烫发"),
    HAIR_REMOVAL(3,"脱毛"),
    BREAST(4,"美乳"),
    BODY_BUILDING(5,"健美"),
    DEODORANT(6,"除臭"),
    FRELKLE(7,"祛斑"),
    SUNSCREEN(8,"防晒");

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

    Cosmetics(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (Cosmetics c : Cosmetics.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }

    public static int getCode(String name) {
        for (Cosmetics c : Cosmetics.values()) {
            if (c.getName().equals(name)) {
                return c.getCode();
            }
        }
        return -1;
    }
}
