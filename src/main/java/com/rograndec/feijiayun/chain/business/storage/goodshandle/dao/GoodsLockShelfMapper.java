package com.rograndec.feijiayun.chain.business.storage.goodshandle.dao;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsLockShelf;

import java.util.List;

public interface GoodsLockShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsLockShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsLockShelf record);

    /**
     *
     * @mbg.generated
     */
    GoodsLockShelf selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsLockShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsLockShelf record);
    /**
     *
     * <根据锁定单明细id获取货位信息>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/29 17:09
     */
    List<GoodsLockShelf> getGoodsLockShelfData(Long id);

    List<GoodsLockShelf> selectByLockId(Long id);
}