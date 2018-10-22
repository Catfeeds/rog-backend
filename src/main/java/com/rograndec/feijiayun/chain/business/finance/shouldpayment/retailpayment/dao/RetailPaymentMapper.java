package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.dao;

import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.entity.RetailPayment;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.vo.RetailPayMentRageParamVO;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.vo.RetailPaymentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RetailPaymentMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RetailPayment record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RetailPayment record);

    /**
     *
     * @mbg.generated
     */
    RetailPayment selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RetailPayment record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RetailPayment record);

    List<RetailPaymentVO> selectCountByPageParam(@Param("param")RetailPayMentRageParamVO param);

    List<RetailPaymentVO> selectByPageParam(@Param("param")RetailPayMentRageParamVO param);

    List<RetailPayment> selectByIds(List<Long> ids);

    List<RetailPayment> selectByPayDate(@Param("payDate") String payDate, @Param("enterpriseId") Long enterpriseId);
}