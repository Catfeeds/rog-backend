package com.rograndec.feijiayun.chain.business.goods.price.constant;

/**
 * Created by ST on 2017/9/4.
 */
public class PriceOrderConstant {

    //redis中存储商品信息的key
    public final static String GOODS_INFO_KEY = "price_order_info";
    //redis中存储合格商品信息的field
    public final static String QUALIFIED_FIELD = "price_order_qualified";
    //redis中存储不合格商品信息的field
    public final static String DISQUALIFIED_FIELD = "price_order_disqualified";
    //redis中存储合格标准入库实体商品信息的field
    public final static String STANDARD_QUALIFIED_FIELD = "price_order_standard_qualified";
}