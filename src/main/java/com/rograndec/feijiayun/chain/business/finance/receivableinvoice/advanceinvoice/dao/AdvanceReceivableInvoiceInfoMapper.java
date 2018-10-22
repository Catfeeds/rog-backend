package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.dao;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoiceInfo;

public interface AdvanceReceivableInvoiceInfoMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(AdvanceReceivableInvoiceInfo record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(AdvanceReceivableInvoiceInfo record);

    /**
     *
     * @mbg.generated
     */
    AdvanceReceivableInvoiceInfo selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AdvanceReceivableInvoiceInfo record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AdvanceReceivableInvoiceInfo record);
    /**
     * 根据发票id查询开票信息
     * @param invoiceId
     * @return
     */
    AdvanceReceivableInvoiceInfo selectByInvoiceId(Long invoiceId);
    
}