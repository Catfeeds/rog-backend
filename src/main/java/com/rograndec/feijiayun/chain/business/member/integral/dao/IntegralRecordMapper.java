package com.rograndec.feijiayun.chain.business.member.integral.dao;

import com.rograndec.feijiayun.chain.business.member.integral.entity.IntegralRecord;

public interface IntegralRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(IntegralRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(IntegralRecord record);

    /**
     *
     * @mbg.generated
     */
    IntegralRecord selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(IntegralRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(IntegralRecord record);
}