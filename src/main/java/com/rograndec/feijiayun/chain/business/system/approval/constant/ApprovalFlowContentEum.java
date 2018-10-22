package com.rograndec.feijiayun.chain.business.system.approval.constant;


/**
 * Created by zhaiwei on 2017/8/24.
 */
public enum ApprovalFlowContentEum {

    SUPPLIER("0101","供货单位"),
    STAFF("0102","员工信息"),
    STORE("0103","门店信息"),
    GOODS("0201","商品信息"),
    PRICE_ADJUST("0202","价格调整"),
    PURCHASE_PLAN("0301","采购计划"),
    BUY_QUIT("0302","购进退出"),
    CHECK("0401","盘点"),
    OTHER_STORAGE("0402","其他入库"),
    DRUGS_DESTROY("0403","药品销毁"),
    MATCH_INTO_QUIT("0501","配进退出");

    private String code;
    private String value;

    ApprovalFlowContentEum(String code, String value) {
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
}
