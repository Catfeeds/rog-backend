package com.rograndec.feijiayun.chain.business.storage.goodshandle.dao;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsLockDetail;

import java.util.List;

public interface GoodsLockDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsLockDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsLockDetail record);

    /**
     *
     * @mbg.generated
     */
    GoodsLockDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsLockDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsLockDetail record);
    /**
     *
     * <根据锁定单id获取明细信息>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/29 17:09
     */
    List<GoodsLockDetail> getGoodsLockDetailData(Long lockId);
}