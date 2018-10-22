package com.rograndec.feijiayun.chain.business.storage.goodshandle.dao;

import java.util.List;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsDestroyShelf;

public interface GoodsDestroyShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsDestroyShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsDestroyShelf record);

    /**
     *
     * @mbg.generated
     */
    GoodsDestroyShelf selectByPrimaryKey(Long id);
    List<GoodsDestroyShelf> selectByDestroyId(Long id);
    
    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsDestroyShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsDestroyShelf record);

    void deleteByDestroyId(Long DestroyId);
}