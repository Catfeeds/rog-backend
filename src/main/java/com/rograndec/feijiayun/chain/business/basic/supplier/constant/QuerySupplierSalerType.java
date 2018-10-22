package com.rograndec.feijiayun.chain.business.basic.supplier.constant;

import java.util.Arrays;

/**
 * 查询供应商列表的查询条件类别
 * Created by zhaiwei on 2017/9/2.
 */
public enum QuerySupplierSalerType {

    SUPPLIER_SALER(0,"供应商单位"),
    SALER(1,"销售人员"),;

    private int code;
    private String value;

    QuerySupplierSalerType(int code, String value) {
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

    public static QuerySupplierSalerType getQuerySupplierSalerT4Code(int code){

        QuerySupplierSalerType userTypeEum = Arrays.stream(QuerySupplierSalerType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return userTypeEum;

    }

}
