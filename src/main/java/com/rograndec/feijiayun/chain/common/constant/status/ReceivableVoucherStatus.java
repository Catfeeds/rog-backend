package com.rograndec.feijiayun.chain.common.constant.status;

/**
 * 应收贷项凭证状态
 */
public enum ReceivableVoucherStatus {

    WAIT_PAYMENT(0,"待付款"),
    PART_PAYMENT(1,"部分付款"),
    ALREADY_PAYMENT(2,"已付款"),
    ALREADY_WRITE(3,"已冲销");

    private int status;
    private String name;

    private ReceivableVoucherStatus(int status, String name) {
        this.status = status;
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getName(int status) {
        for (ReceivableVoucherStatus es : ReceivableVoucherStatus.values()) {
            if (es.getStatus() == status) {
                return es.getName();
            }
        }
        return null;
    }
}
