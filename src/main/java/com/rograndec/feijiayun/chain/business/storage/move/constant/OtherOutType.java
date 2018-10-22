package com.rograndec.feijiayun.chain.business.storage.move.constant;

import java.util.Arrays;

/**
 *0-赠送；1-报损；2-内部领用；3-抽检出库；4-其它
 * Created by zhaiwei on 2017/9/29.
 */
public enum OtherOutType {

    GIFT_OUT(0,"赠送"),
    DAMAGED_OUT(1,"报损"),
    INTERNAL_USE_OUT(2,"内部领用"),
    SAMPLING_OUT(3,"抽检出库"),
    OTHER_OUT(4,"其它");

    private int code;
    private String value;

    OtherOutType(int code, String value) {
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

    public static OtherOutType getEnum(int code){

        OtherOutType type = Arrays.stream(OtherOutType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return type;

    }

    public static String getValue(int code){

        OtherOutType type = Arrays.stream(OtherOutType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return type.getValue();

    }
}
