package com.rograndec.feijiayun.chain.business.storage.lot.dao;

import com.rograndec.feijiayun.chain.business.storage.lot.entity.LotAdjustShelf;

public interface LotAdjustShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(LotAdjustShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(LotAdjustShelf record);

    /**
     *
     * @mbg.generated
     */
    LotAdjustShelf selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LotAdjustShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LotAdjustShelf record);
}