package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.dao;

import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.entity.RetailPaymentDetail;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.vo.RetailPaymentDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RetailPaymentDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RetailPaymentDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RetailPaymentDetail record);

    /**
     *
     * @mbg.generated
     */
    RetailPaymentDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RetailPaymentDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RetailPaymentDetail record);

    List<RetailPaymentDetailVO> selectRetailPaymentDetailVOByItemId(Long id);
    List<RetailPaymentDetail> selectByItemId(Long id);

    void updateStatus(@Param("itemId") Long itemId, @Param("status") Integer status);
}