package com.rograndec.feijiayun.chain.business.goods.info.constant;

/**
 * Created by ST on 2017/9/4.
 */
public class GoodsInfoConstant {
    public final static String DEFAULT_APPROVAL_FLOW_NAME = "首营品种审批";

    public final static String APPROVALSTAGE_1 = "业务经理审批";
    public final static String APPROVALSTAGE_2 = "质量经理审批";
    public final static String APPROVALSTAGE_3 = "总经理审批";


    //redis中存储商品信息的key
    public final static String GOODS_INFO_KEY = "goods_info";
    //redis中存储合格商品信息的field
    public final static String QUALIFIED_FIELD = "goods_qualified";
    //redis中存储不合格商品信息的field
    public final static String DISQUALIFIED_FIELD = "goods_disqualified";
    //redis中存储合格标准入库实体商品信息的field
    public final static String STANDARD_QUALIFIED_FIELD = "goods_standard_qualified";
}