package com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.dao;

import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.entity.FinancePaymentDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinancePaymentDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(FinancePaymentDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(FinancePaymentDetail record);

    /**
     *
     * @mbg.generated
     */
    FinancePaymentDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(FinancePaymentDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FinancePaymentDetail record);

    List<FinancePaymentDetail> selectByPaymentId(Long id);

    void updateStatus(@Param("id") Long id, @Param("status") int status);
}