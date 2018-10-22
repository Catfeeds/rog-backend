package com.rograndec.feijiayun.chain.common.constant.status;

import java.util.Arrays;

/**
 * 质量管理体系状态（0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁）
 */
public enum QualityManageSystemFileStatus {

    TAKEEFFECT(0,"生效"),
    INVALID(1,"失效"),
    ABOLISH(2,"废止"),
    REVOKE(3,"撤销"),
    REPLACE(4,"替换"),
    DESTROY(5,"销毁");

    private int code;
    private String value;

    QualityManageSystemFileStatus(int code, String value) {
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

    public static QualityManageSystemFileStatus getEnum(int code){

        QualityManageSystemFileStatus obj = Arrays.stream(QualityManageSystemFileStatus.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return obj;

    }

    public static String getValue(int code){

        QualityManageSystemFileStatus obj = Arrays.stream(QualityManageSystemFileStatus.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        return obj.getValue();

    }
}
