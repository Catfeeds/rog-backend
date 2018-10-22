package com.rograndec.feijiayun.chain.business.storage.goodshandle.dao;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.StopSaleDetail;

public interface StopSaleDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(StopSaleDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(StopSaleDetail record);

    /**
     *
     * @mbg.generated
     */
    StopSaleDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StopSaleDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StopSaleDetail record);
}