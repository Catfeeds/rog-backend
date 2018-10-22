package com.rograndec.feijiayun.chain.business.basic.equipment.constant;

import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrAuditType;

import java.util.Arrays;

/**
 * dongyang.du
 * 设备状况
 */
public enum  EquipmentStatusType {

    WELL(0,"良好"),
    MAINTAIN(1,"维修"),
    INVALID(2,"作废");

    private int code;
    private String value;

    EquipmentStatusType(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static EquipmentStatusType getEnum(int code){

        EquipmentStatusType equipmentStatusType = Arrays.stream(EquipmentStatusType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return equipmentStatusType;

    }

    public static String getValue(int code){

        EquipmentStatusType equipmentStatusType = Arrays.stream(EquipmentStatusType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return equipmentStatusType.getValue();

    }

}
