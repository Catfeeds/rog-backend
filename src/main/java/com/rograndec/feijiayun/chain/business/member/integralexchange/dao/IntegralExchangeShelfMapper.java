package com.rograndec.feijiayun.chain.business.member.integralexchange.dao;

import com.rograndec.feijiayun.chain.business.member.integralexchange.entity.IntegralExchangeShelf;

public interface IntegralExchangeShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(IntegralExchangeShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(IntegralExchangeShelf record);

    /**
     *
     * @mbg.generated
     */
    IntegralExchangeShelf selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(IntegralExchangeShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(IntegralExchangeShelf record);
}