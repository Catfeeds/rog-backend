package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoiceInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentInvoiceInfoMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PaymentInvoiceInfo record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PaymentInvoiceInfo record);

    /**
     *
     * @mbg.generated
     */
    PaymentInvoiceInfo selectByPrimaryKey(Long id);

    PaymentInvoiceInfo selectByInvoiceId(@Param("invoiceId") Long invoiceId);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PaymentInvoiceInfo record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PaymentInvoiceInfo record);
}