package com.rograndec.feijiayun.chain.business.system.opening.common;

/**
 * Created by ST on 2017/9/4.
 */
public class OpeningGoodsInfoConstant {

    //redis中存储商品信息的key
    public final static String OPENING_GOODS_INFO_KEY = "opening_goods_info";
    //redis中存储合格商品信息的field
    public final static String OPENING_QUALIFIED_FIELD = "opening_goods_qualified";
    //redis中存储不合格商品信息的field
    public final static String OPENING_DISQUALIFIED_FIELD = "opening_goods_disqualified";
    //redis中存储合格标准入库实体商品信息的field
    public final static String opening_STANDARD_QUALIFIED_FIELD = "goods_standard_qualified";
}