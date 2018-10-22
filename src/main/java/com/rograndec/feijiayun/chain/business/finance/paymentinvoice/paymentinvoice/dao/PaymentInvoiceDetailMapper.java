package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoiceDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaymentInvoiceDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PaymentInvoiceDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PaymentInvoiceDetail record);

    /**
     *
     * @mbg.generated
     */
    PaymentInvoiceDetail selectByPrimaryKey(Long id);
    List<PaymentInvoiceDetail> selectByInvoiceId(@Param("invoiceId") Long invoiceId);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PaymentInvoiceDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PaymentInvoiceDetail record);

    List<PaymentInvoiceDetail> queryByInvoiceId(Long invoiceId);
}