package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vo.PaymentInvoiceResponseTotalVO;

import java.util.List;
import java.util.Map;

public interface PaymentInvoiceMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PaymentInvoice record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PaymentInvoice record);

    /**
     *
     * @mbg.generated
     */
    PaymentInvoice selectByPrimaryKey(Long id);

    List<PaymentInvoice> selectByParam(Map<String,Object> map);
    PaymentInvoiceResponseTotalVO selectSumByParam(Map<String,Object> map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PaymentInvoice record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PaymentInvoice record);
}