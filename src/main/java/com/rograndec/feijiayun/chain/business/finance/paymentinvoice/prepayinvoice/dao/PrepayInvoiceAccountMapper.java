package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.PrepayInvoiceAccount;

import java.util.List;

public interface PrepayInvoiceAccountMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PrepayInvoiceAccount record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PrepayInvoiceAccount record);

    /**
     *
     * @mbg.generated
     */
    PrepayInvoiceAccount selectByPrimaryKey(Long id);

    List<PrepayInvoiceAccount> selectByInvoiceId(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PrepayInvoiceAccount record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PrepayInvoiceAccount record);
}