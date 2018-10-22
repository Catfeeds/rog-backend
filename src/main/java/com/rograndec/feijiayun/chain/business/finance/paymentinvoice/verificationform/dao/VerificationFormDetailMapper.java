package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.entity.VerificationFormDetail;

import java.util.List;

public interface VerificationFormDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(VerificationFormDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(VerificationFormDetail record);

    /**
     *
     * @mbg.generated
     */
    VerificationFormDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(VerificationFormDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(VerificationFormDetail record);

    List<VerificationFormDetail> selectDetailByItemId(Long itemId);
}