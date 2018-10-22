package com.rograndec.feijiayun.chain.business.storage.chgoods.dao;

import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsClearDetail;

public interface GoodsClearDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(GoodsClearDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(GoodsClearDetail record);

    /**
     *
     * @mbg.generated
     */
    GoodsClearDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsClearDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsClearDetail record);
}