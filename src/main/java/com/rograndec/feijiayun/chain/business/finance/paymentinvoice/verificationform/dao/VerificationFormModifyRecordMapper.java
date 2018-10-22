package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.entity.VerificationFormModifyRecord;

import java.util.List;
import java.util.Map;

public interface VerificationFormModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(VerificationFormModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(VerificationFormModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    VerificationFormModifyRecord selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(VerificationFormModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(VerificationFormModifyRecord record);

    List<VerificationFormModifyRecord> selectByFormId(Long id);

    Integer selectByForCount(Map<String, Object> map);

    List<VerificationFormModifyRecord> selectByForPage(Map<String, Object> map);
}