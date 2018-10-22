package com.rograndec.feijiayun.chain.common.constant;

/**
 *
 * @ClassName: TaxRateType
 * @Description: 税率类型常量
 * @author zhongyi.li zhongyi.li@rograndec.com
 * @date 2017年10月12日 下午8:40:22
 *
 */
public enum TaxRateType {

    PURCHASE(0, "进项税"),
    SALE(1, "销项"),
    DISTRIBUTION(2, "配送");

    private int type;
    private String typeName;

    TaxRateType(int type, String typeName){
        this.type = type;
        this.typeName = typeName;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }


}
