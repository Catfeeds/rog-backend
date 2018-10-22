package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.dao;

import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.entity.FinanceReceivableModifyRecord;

import java.util.List;
import java.util.Map;

public interface FinanceReceivableModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(FinanceReceivableModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(FinanceReceivableModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    FinanceReceivableModifyRecord selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(FinanceReceivableModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FinanceReceivableModifyRecord record);

    List<FinanceReceivableModifyRecord> selectByKeyId(Map<String, Object> map);

    Integer getTotalRecord(Map<String, Object> map);
}