package com.rograndec.feijiayun.chain.business.storage.goodshandle.dao;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.StartSaleDetail;

public interface StartSaleDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(StartSaleDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(StartSaleDetail record);

    /**
     *
     * @mbg.generated
     */
    StartSaleDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StartSaleDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StartSaleDetail record);
}