package com.rograndec.feijiayun.chain.business.storage.lot.dao;

import com.rograndec.feijiayun.chain.business.storage.lot.entity.LotAdjustDetail;

public interface LotAdjustDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(LotAdjustDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(LotAdjustDetail record);

    /**
     *
     * @mbg.generated
     */
    LotAdjustDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LotAdjustDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LotAdjustDetail record);
}