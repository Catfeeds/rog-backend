package com.rograndec.feijiayun.chain.business.system.approval.constant;


/**
 * Created by zhaiwei on 2017/8/24.
 */
public enum ApprovalFlowOrgEum {

    SUPPLIER(0,"总部"),
    STAFF(1,"发起机构"),
    STORE(2,"指定审核机构");

    private int code;
    private String value;

    ApprovalFlowOrgEum(int code, String value) {
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
}
