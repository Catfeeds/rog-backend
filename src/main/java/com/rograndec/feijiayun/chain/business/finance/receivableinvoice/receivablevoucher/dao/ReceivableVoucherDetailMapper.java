package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.dao;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.entity.ReceivableVoucherDetail;

import java.util.List;

public interface ReceivableVoucherDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(ReceivableVoucherDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ReceivableVoucherDetail record);

    /**
     *
     * @mbg.generated
     */
    ReceivableVoucherDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ReceivableVoucherDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ReceivableVoucherDetail record);

    List<ReceivableVoucherDetail> getReceivableInvoiceDetailList(Long id);
}