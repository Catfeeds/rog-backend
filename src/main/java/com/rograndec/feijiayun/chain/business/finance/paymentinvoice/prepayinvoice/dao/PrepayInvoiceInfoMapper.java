package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.PrepayInvoiceInfo;
import org.apache.ibatis.annotations.Param;

public interface PrepayInvoiceInfoMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PrepayInvoiceInfo record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PrepayInvoiceInfo record);

    /**
     *
     * @mbg.generated
     */
    PrepayInvoiceInfo selectByPrimaryKey(Long id);
    PrepayInvoiceInfo selectByInvoiceId(@Param("invoiceId") Long invoiceId);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PrepayInvoiceInfo record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PrepayInvoiceInfo record);
}