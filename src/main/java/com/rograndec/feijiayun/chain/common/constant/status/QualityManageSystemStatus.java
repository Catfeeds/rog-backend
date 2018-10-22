package com.rograndec.feijiayun.chain.common.constant.status;

import com.rograndec.feijiayun.chain.common.constant.FileType;

import java.util.Arrays;

/**
 * 质量管理体系状态（0-生效；1-失效；2-废止）
 */
public enum QualityManageSystemStatus {

    TAKEEFFECT(0,"生效"),
    INVALID(1,"失效"),
    ABOLISH(2,"废止");

    private int code;
    private String value;

    QualityManageSystemStatus(int code, String value) {
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

    public static QualityManageSystemStatus getEnum(int code){

        QualityManageSystemStatus obj = Arrays.stream(QualityManageSystemStatus.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return obj;

    }

    public static String getValue(int code){

        QualityManageSystemStatus obj = Arrays.stream(QualityManageSystemStatus.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return obj.getValue();

    }
}
