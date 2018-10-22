package com.rograndec.feijiayun.chain.business.storage.goodshandle.dao;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.StopSaleShelf;

public interface StopSaleShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(StopSaleShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(StopSaleShelf record);

    /**
     *
     * @mbg.generated
     */
    StopSaleShelf selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StopSaleShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StopSaleShelf record);
}