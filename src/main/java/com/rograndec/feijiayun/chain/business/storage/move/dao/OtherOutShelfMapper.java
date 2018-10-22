package com.rograndec.feijiayun.chain.business.storage.move.dao;

import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOutShelf;

import java.util.List;

public interface OtherOutShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(OtherOutShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(OtherOutShelf record);

    /**
     *
     * @mbg.generated
     */
    OtherOutShelf selectByPrimaryKey(Long id);

    List<OtherOutShelf> selectByDtlIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OtherOutShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OtherOutShelf record);
}