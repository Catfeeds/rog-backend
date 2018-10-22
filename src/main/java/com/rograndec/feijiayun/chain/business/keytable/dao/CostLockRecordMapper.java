package com.rograndec.feijiayun.chain.business.keytable.dao;

import com.rograndec.feijiayun.chain.business.keytable.entity.CostLockRecord;

import java.util.List;
import java.util.Map;

public interface CostLockRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(CostLockRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(CostLockRecord record);

    /**
     *
     * @mbg.generated
     */
    CostLockRecord selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CostLockRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CostLockRecord record);

    List<CostLockRecord> selectByParamMap(Map<String, Object> paramMap);
}