package com.rograndec.feijiayun.chain.common.constant.status;
/*
应收发票状态
 */
public enum ReceivableInvoiceStatus {

    WAIT_RECEIVABLES(0,"待收款"),
    PART_RECEIVABLES(1,"部分收款"),
    ALREADY_RECEIVABLES(2,"已收款"),
    ALREADY_WRITE(3,"已冲销");

    private int status;
    private String name;

    private ReceivableInvoiceStatus(int status, String name) {
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
        for (ReceivableInvoiceStatus es : ReceivableInvoiceStatus.values()) {
            if (es.getStatus() == status) {
                return es.getName();
            }
        }
        return null;
    }
}
