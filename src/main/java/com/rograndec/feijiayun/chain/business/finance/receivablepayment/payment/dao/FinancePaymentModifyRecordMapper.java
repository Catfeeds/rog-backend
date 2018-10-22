package com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.dao;

import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.entity.FinancePaymentModifyRecord;

import java.util.List;
import java.util.Map;

public interface FinancePaymentModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(FinancePaymentModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(FinancePaymentModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    FinancePaymentModifyRecord selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(FinancePaymentModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FinancePaymentModifyRecord record);

    List<FinancePaymentModifyRecord> selectByKeyId(Map<String, Object> map);

    Integer getTotalRecord(Map<String, Object> map);
}