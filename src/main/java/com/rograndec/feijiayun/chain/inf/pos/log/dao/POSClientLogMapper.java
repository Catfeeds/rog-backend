package com.rograndec.feijiayun.chain.inf.pos.log.dao;

import com.rograndec.feijiayun.chain.inf.pos.log.entity.POSClientLog;

public interface POSClientLogMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(POSClientLog record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(POSClientLog record);

    /**
     *
     * @mbg.generated
     */
    POSClientLog selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(POSClientLog record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(POSClientLog record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(POSClientLog record);
}