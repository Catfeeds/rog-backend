package com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.dao;

import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.entity.FinancePayment;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.vo.PaymentPageTotal;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.vo.PaymentPageVO;

import java.util.List;
import java.util.Map;

public interface FinancePaymentMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(FinancePayment record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(FinancePayment record);

    /**
     *
     * @mbg.generated
     */
    FinancePayment selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(FinancePayment record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FinancePayment record);

    List<PaymentPageVO> getPayPage(Map<String, Object> map);

    PaymentPageTotal selectTotal(Map<String, Object> map);

    Integer getTotalRecord(Map<String, Object> map);
}