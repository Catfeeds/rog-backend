package com.rograndec.feijiayun.chain.business.storage.goodshandle.dao;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsDestroyDetail;

public interface GoodsDestroyDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsDestroyDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsDestroyDetail record);

    /**
     *
     * @mbg.generated
     */
    GoodsDestroyDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsDestroyDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsDestroyDetail record);

    /**
     * 根据销毁单删除明细表
     * */
    void deleteByDestroyId(Long DestroyId);
}