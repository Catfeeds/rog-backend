package com.rograndec.feijiayun.chain.business.distr.branch.constant;

import java.util.Arrays;

/**
 * Created by dudy on 2017/10/8.
 */
public enum DistrType {

    DISTRIBUTION_HEAD(0, "总部配送"), ENTRUST_TRANSPORT(1, "委托运输"), SELF(2, "自提"), SWAP_BETWEEN_STORES(3,"门店间调剂"),
    DIRECT_DISTRIBUTION(4,"直调配送");
    private int code;
    private String value;

    DistrType(int code, String value) {
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

    public static DistrType getEnum(int code){

        DistrType distrInNoticeType = Arrays.stream(DistrType.values())
                .filter(c -> code == c.code).findFirst().orElse(null);

        return distrInNoticeType;

    }

    public static String getValue(int code){

        DistrType distrInNoticeType = Arrays.stream(DistrType.values())
                .filter(c -> code == (c.code)).findFirst().orElse(null);

        if(null != distrInNoticeType){
            return distrInNoticeType.getValue();
        }else {
            return null;
        }


    }
}
