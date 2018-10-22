package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.entity.VerificationFormItem;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo.VerificationFormItemVO;

import java.util.List;

public interface VerificationFormItemMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(VerificationFormItem record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(VerificationFormItem record);

    /**
     *
     * @mbg.generated
     */
    VerificationFormItem selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(VerificationFormItem record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(VerificationFormItem record);

    List<VerificationFormItem> selectItemByFormId(Long id);
}