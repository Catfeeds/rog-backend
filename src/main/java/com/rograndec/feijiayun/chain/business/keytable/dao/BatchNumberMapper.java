package com.rograndec.feijiayun.chain.business.keytable.dao;

import com.rograndec.feijiayun.chain.business.keytable.entity.BatchNumber;

import java.util.List;
import java.util.Map;

public interface BatchNumberMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(BatchNumber record);

    /**
     *
     *
     *
     * @mbg.generated
     */
    int insertSelective(BatchNumber record);

    /**
     *
     * @mbg.generated
     */
    BatchNumber selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(BatchNumber record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(BatchNumber record);

    /**
     * 获取内内部批次号
     * @param paramMap
     * @return
     */
    List<BatchNumber> selectByParamMap(Map<String,Object> paramMap);

}