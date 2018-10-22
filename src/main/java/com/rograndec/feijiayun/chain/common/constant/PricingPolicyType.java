package com.rograndec.feijiayun.chain.common.constant;

/**
 * @program: chain-backend
 * @description: 销售定价策略 0总部统一定价,1允许门店自主定价
 * @author: dongyang.du
 * @create: 2018-01-31 19:00
 **/
public enum PricingPolicyType {

    HEADQUARTERS_UNIFORM_PRICING(0,"总部统一定价"),
    AUTOMATICALLY_PRICED_STORES(1,"允许门店自主定价");


    private int code;
    private String name;

    private PricingPolicyType(int code, String name) {
        this.code = code;
        this.name = name;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static String getName(int code) {
        for (PricingPolicyType c : PricingPolicyType.values()) {
            if (c.getCode() == code) {
                return c.getName();
            }
        }
        return null;
    }
    public static int getCode(String name) {
        for (PricingPolicyType c : PricingPolicyType.values()) {
            if (c.getName().equals(name)) {
                return c.getCode();
            }
        }
        return -1;
    }
}
