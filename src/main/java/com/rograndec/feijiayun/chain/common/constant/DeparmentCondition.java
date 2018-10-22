package com.rograndec.feijiayun.chain.common.constant;

public enum DeparmentCondition {

    RIGHT(0,"成功！"),
    HAS_POSITION(1, "当前部门下还有岗位,无法删除该部门!"),
    HAS_DEPARTMENT(2, "当前部门下还有部门,无法删除该部门!"),
    HAS_ROLE(3,"当前部门下还有角色,无法删除该部门!"),
    HAS_USER(4,"当前部门下对应着员工，无法删除该部门!");
    private Integer code;
    private String name;
    private DeparmentCondition(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public static String getName(int code) {
        for (DistrPriceType c : DistrPriceType.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }
}
