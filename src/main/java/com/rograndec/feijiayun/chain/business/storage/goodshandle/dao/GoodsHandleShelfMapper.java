package com.rograndec.feijiayun.chain.business.storage.goodshandle.dao;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsHandleShelf;

import java.util.List;

public interface GoodsHandleShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsHandleShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsHandleShelf record);

    /**
     *
     * @mbg.generated
     */
    GoodsHandleShelf selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsHandleShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsHandleShelf record);
    /**
     *
     * <根据明细单获取货位信息>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/30 14:48
     */
    List<GoodsHandleShelf> getGoodsHandleShelfByDtId(Long id);
}