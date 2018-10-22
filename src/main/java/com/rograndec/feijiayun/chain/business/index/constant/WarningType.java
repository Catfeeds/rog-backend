package com.rograndec.feijiayun.chain.business.index.constant;

import java.util.Arrays;

/**
 * 预警类型
 */
public enum WarningType {


    ENTERPRISE("enterpris","组织机构"),
    SUPPLIER("supplier","供货单位"),
    USER("user","员工"),
    GOODS("GOODS","商品"),
    STOCK_EXPIRE_GOODS("stockExpireGoods","库存过期"),
    STOCK_NEAR_PERIOD("stockNearPeriodGoods","库存近效期"),
    STOCK_BACKLOG_GOODS("stockBacklogGoods","库存积货"),
    STOCK_LACK_GOODS("stockLackGoods","库存缺货"),
    STOCK_LAGSALE_GOODS("stockLagSaleGoods","库存滞销"),
    STOCK_CONSERVATION("stockConservation","库存养护");
    private String code;
    private String value;

    WarningType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static WarningType getEnum(String code){

        WarningType menus = Arrays.stream(WarningType.values())
                .filter(c -> code.equals(c.code)).findFirst().orElse(null);

        return menus;

    }

    public static String getValue(String code){

        WarningType menus = Arrays.stream(WarningType.values())
                .filter(c -> code.equals(c.code)).findFirst().orElse(null);

        return menus.getValue();

    }

    public static boolean comper(String code){

        WarningType menus = Arrays.stream(WarningType.values())
                .filter(c -> code.equals(c.code)).findFirst().orElse(null);

        return menus != null;
    }
}
