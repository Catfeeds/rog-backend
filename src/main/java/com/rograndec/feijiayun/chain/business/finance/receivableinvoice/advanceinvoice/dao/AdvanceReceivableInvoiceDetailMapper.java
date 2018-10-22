package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoiceDetail;

public interface AdvanceReceivableInvoiceDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(AdvanceReceivableInvoiceDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(AdvanceReceivableInvoiceDetail record);

    /**
     *
     * @mbg.generated
     */
    AdvanceReceivableInvoiceDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(AdvanceReceivableInvoiceDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(AdvanceReceivableInvoiceDetail record);

    
    /**
     * 根据发票id查询发票信息集合
     * @param invoiceId
     * @return
     */
    List<AdvanceReceivableInvoiceDetail> selectByInvoiceId(Long invoiceId);


    List<AdvanceReceivableInvoiceDetail> queryByInvoiceId(@Param("invoiceId") Long invoiceId);

}