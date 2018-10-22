package com.rograndec.feijiayun.chain.business.member.storedamount.dao;

import com.rograndec.feijiayun.chain.business.member.storedamount.entity.StoredAmount;

public interface StoredAmountMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(StoredAmount record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(StoredAmount record);

    /**
     *
     * @mbg.generated
     */
    StoredAmount selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StoredAmount record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StoredAmount record);
}