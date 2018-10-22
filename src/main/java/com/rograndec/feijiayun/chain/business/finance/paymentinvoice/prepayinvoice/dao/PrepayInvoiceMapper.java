package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.dao;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.PrepayInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PrepayInvoiceResponseTotalVO;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.vo.PayDocumentsPageVO;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.vo.PayDocumentsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PrepayInvoiceMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PrepayInvoice record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PrepayInvoice record);

    /**
     *
     * @mbg.generated
     */
    PrepayInvoice selectByPrimaryKey(Long id);

    PrepayInvoice selectByIdAndAccountStatus(@Param("id") Long id,@Param("list") List<Integer> list );

    List<PrepayInvoice> selectByParam(Map<String,Object> map);
    PrepayInvoiceResponseTotalVO selectSumByParam(Map<String,Object> map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PrepayInvoice record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PrepayInvoice record);

    List<PayDocumentsPageVO> selectWithUnclear(Map<String, Object> map);

    Integer selectTotalRecord(Map<String, Object> map);

    PayDocumentsVO selectAmountTotal(Map<String, Object> map);
}