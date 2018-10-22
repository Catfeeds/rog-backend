package com.rograndec.feijiayun.chain.common.constant;

/**
 *
 * @ClassName: FinanceAccountType
 * @Description: 财务明细科目类型枚举
 * @author zhongyi.li zhongyi.li@rograndec.com
 * @Date 2018年1月12日 上午13:37:47
 *
 */
public enum FinanceAccountType {
    PARENT(0, "总部"),
    BRANCH(1, "自营店"),
    LEAGUE(2, "加盟店"),
    SUPPLIER(3, "供货单位"),
    PURCHASE_COMPANY(4, "购货单位"),
    TAX(5, "税");

    // 类型
    private int type;

    // 描述
    private String desc;

    FinanceAccountType(int type, String desc){
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getDesc(int type) {
        for (FinanceAccountType fat : FinanceAccountType.values()) {
            if (fat.getType() == type) {
                return fat.getDesc();
            }
        }
        return null;
    }

}