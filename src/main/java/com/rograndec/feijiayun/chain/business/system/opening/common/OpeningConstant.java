package com.rograndec.feijiayun.chain.business.system.opening.common;

/**
 * 期初操作常量
 */
public class OpeningConstant {

    //redis中存储期初应付合格数据
    public final static String OPENING_PAYMENT_QUALIFIED = "opening_payment_qualified";
    //redis中存储期初应付不合格数据
    public final static String OPENING_PAYMENT_DISQUALIFIED = "opening_payment_disqualified";
    //redis中存储期初应收合格数据
    public final static String OPENING_RECEIVABLE_QUALIFIED = "opening_receivable_qualified";
    //redis中存储期初应收不合格数据
    public final static String OPENING_RECEIVABLE_DISQUALIFIED = "opening_receivable_disqualified";
    //redis中存储期初税额合格数据
    public final static String OPENING_TAX_QUALIFIED = "opening_tax_qualified";
    //redis中存储期初税额不合格数据
    public final static String OPENING_TAX_DISQUALIFIED = "opening_tax_disqualified";

}