package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.dao;

import java.util.List;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoiceAccount;

public interface AdvanceReceivableInvoiceAccountMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(AdvanceReceivableInvoiceAccount record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(AdvanceReceivableInvoiceAccount record);

    /**
     *
     * @mbg.generated
     */
    AdvanceReceivableInvoiceAccount selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AdvanceReceivableInvoiceAccount record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AdvanceReceivableInvoiceAccount record);
    
    /**
     * 根据invoiceId查询对账表
     * @param invoiceId
     * @return
     */
    List<AdvanceReceivableInvoiceAccount> selectByInvoiceId(Long invoiceId);
}