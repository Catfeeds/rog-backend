package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.dao;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.entity.ReceivableInvoiceDetail;

import java.util.List;

public interface ReceivableInvoiceDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(ReceivableInvoiceDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ReceivableInvoiceDetail record);

    /**
     *
     * @mbg.generated
     */
    ReceivableInvoiceDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ReceivableInvoiceDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ReceivableInvoiceDetail record);

    List<ReceivableInvoiceDetail> getReceivableInvoiceDetailList(Long id);
}