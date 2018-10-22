package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.common;

/**
 * 收款的状态:0-待收款；1-已完成；2-已冲销
 */
public enum ReceivableStatus {

    STAY_RECEIVE(0,"待收款"),
    HAS_FINISHED(1,"已完成"),
    HAS_WRITTEN(2,"已冲销");

    private int code;
    private String name;

    private ReceivableStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }
    public int getCode() {
        return code;
    }
    public void setType(int code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static String getName(int code) {
        for (ReceivableStatus c : ReceivableStatus.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }


}
