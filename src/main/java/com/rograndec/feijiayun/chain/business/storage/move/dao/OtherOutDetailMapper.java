package com.rograndec.feijiayun.chain.business.storage.move.dao;

import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOutDetail;

import java.util.List;

public interface OtherOutDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(OtherOutDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(OtherOutDetail record);

    /**
     *
     * @mbg.generated
     */
    OtherOutDetail selectByPrimaryKey(Long id);

    List<OtherOutDetail> selectByOutId(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OtherOutDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OtherOutDetail record);
}