package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoiceModifyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentInvoiceModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PaymentInvoiceModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PaymentInvoiceModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    PaymentInvoiceModifyRecord selectByPrimaryKey(Long id);

    List<PaymentInvoiceModifyRecord> selectByEnterpriseId(@Param("enterpriseId") Long enterpriseId, @Param("keyId") Long keyId);


    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PaymentInvoiceModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PaymentInvoiceModifyRecord record);
}