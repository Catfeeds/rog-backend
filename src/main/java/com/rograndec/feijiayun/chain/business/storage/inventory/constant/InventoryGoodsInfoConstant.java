package com.rograndec.feijiayun.chain.business.storage.inventory.constant;

/**
 * Created by ST on 2017/9/4.
 */
public class InventoryGoodsInfoConstant {

    //redis中存储合格商品信息的field
    public final static String INVENTORY_QUALIFIED_FIELD = "inventory_goods_qualified";
    //redis中存储不合格商品信息的field
    public final static String INVENTORY_DISQUALIFIED_FIELD = "inventory_goods_disqualified";
    //redis中存储合格标准入库实体商品信息的field
    public final static String INVENTORY_STANDARD_QUALIFIED_FIELD = "inventory_goods_standard_qualified";
}