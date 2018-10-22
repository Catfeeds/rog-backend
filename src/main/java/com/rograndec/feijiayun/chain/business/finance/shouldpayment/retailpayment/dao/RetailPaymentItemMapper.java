package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.dao;

import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.entity.RetailPaymentItem;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.vo.RetailPaymentItemVO;

import java.util.List;

public interface RetailPaymentItemMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RetailPaymentItem record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RetailPaymentItem record);

    /**
     *
     * @mbg.generated
     */
    RetailPaymentItem selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RetailPaymentItem record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RetailPaymentItem record);

    List<RetailPaymentItem> selectByPayMentId(Long id);

    List<RetailPaymentItemVO> selectRetailPaymentItemVOByPayMentId(Long id);
}