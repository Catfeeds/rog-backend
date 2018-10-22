package com.rograndec.feijiayun.chain.business.online.purchase.constant;

public enum OperationType {

    SYSTEM_ADD(0, "系统添加"),
    AUTO_ADD(1, "手动添加");

    private Integer type;
    private String typeName;

    OperationType(Integer type, String typeName){
        this.type = type;
        this.typeName = typeName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public static String getName(int code) {
        for (OnlineType c : OnlineType.values()) {
            if (c.getType() == code) {
                return c.getTypeName();
            }
        }
        return null;
    }

    public static int getCode(String name) {
        for (OnlineType c : OnlineType.values()) {
            if (c.getTypeName().equals(name)) {
                return c.getType();
            }
        }
        return -1;
    }
}
