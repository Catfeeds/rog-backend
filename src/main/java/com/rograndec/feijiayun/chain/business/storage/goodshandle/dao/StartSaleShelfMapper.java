package com.rograndec.feijiayun.chain.business.storage.goodshandle.dao;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.StartSaleShelf;

public interface StartSaleShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(StartSaleShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(StartSaleShelf record);

    /**
     *
     * @mbg.generated
     */
    StartSaleShelf selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StartSaleShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StartSaleShelf record);
}