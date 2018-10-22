package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.dao;

import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.entity.RetailPaymentPaydtl;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.vo.RetailPaymentPaydtlVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RetailPaymentPaydtlMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(RetailPaymentPaydtl record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(RetailPaymentPaydtl record);

    /**
     *
     * @mbg.generated
     */
    RetailPaymentPaydtl selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(RetailPaymentPaydtl record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(RetailPaymentPaydtl record);

    List<RetailPaymentPaydtlVO> selectRetailPaymentPaydtlVOByDetailId(Long dtlId);
    List<RetailPaymentPaydtl> selectByDetailId(Long dtlId);

    void updateStatus(@Param("itemId") Long itemId, @Param("status") Integer status);

    List<RetailPaymentPaydtlVO> selectByShiftId(Long shiftId);
}