package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.PrepayInvoiceDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrepayInvoiceDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PrepayInvoiceDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PrepayInvoiceDetail record);

    /**
     *
     * @mbg.generated
     */
    PrepayInvoiceDetail selectByPrimaryKey(Long id);

    List<PrepayInvoiceDetail> selectByInvoiceId(@Param("invoiceId") Long invoiceId);

    List<PrepayInvoiceDetail> selectByInvoiceIdAndAccountStatus(@Param("invoiceId") Long invoiceId,@Param("list") List<Integer> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PrepayInvoiceDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PrepayInvoiceDetail record);

    int updateStatusByInvoiceId(PrepayInvoiceDetail record);
}