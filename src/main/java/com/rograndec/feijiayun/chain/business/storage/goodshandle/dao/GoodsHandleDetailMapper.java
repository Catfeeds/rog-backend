package com.rograndec.feijiayun.chain.business.storage.goodshandle.dao;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsHandleDetail;

import java.util.List;

public interface GoodsHandleDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsHandleDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsHandleDetail record);

    /**
     *
     * @mbg.generated
     */
    GoodsHandleDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsHandleDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsHandleDetail record);
    /**
     *
     * <根据处理单获取明细信息>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/30 14:48
     */
    List<GoodsHandleDetail> getGoodsHandleDetailByHandleId(Long handleId);
}