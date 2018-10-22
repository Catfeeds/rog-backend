package com.rograndec.feijiayun.chain.business.system.approval.constant;

import java.util.Arrays;

/**
 * Created by zhaiwei on 2017/8/24.
 */
public enum ApprovalFlowStatusEum {

    ENABLE(1,"启用"),
    DISABLE(0,"禁用");

    private int code;
    private String value;

    ApprovalFlowStatusEum(int code, String value) {
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

    public static ApprovalFlowStatusEum getUserEum(int code){

        ApprovalFlowStatusEum userTypeEum = Arrays.stream(ApprovalFlowStatusEum.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return userTypeEum;

    }
}
