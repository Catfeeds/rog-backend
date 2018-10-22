package com.rograndec.feijiayun.chain.business.keytable.dao;

import com.rograndec.feijiayun.chain.business.keytable.entity.InOutRecord;

import java.util.Map;

public interface InOutRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(InOutRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(InOutRecord record);

    /**
     *
     * @mbg.generated
     */
    InOutRecord selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(InOutRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(InOutRecord record);

    InOutRecord selectByParamMap(Map<String, Object> paramMap);
}