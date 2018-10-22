package com.rograndec.feijiayun.chain.business.retail.receipt.dao;

import java.math.BigDecimal;

import com.rograndec.feijiayun.chain.business.retail.receipt.entity.Receipt;

public interface ReceiptMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Receipt record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Receipt record);

    /**
     *
     * @mbg.generated
     */
    Receipt selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Receipt record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Receipt record);

	BigDecimal getSumRealAmountTotalByShiftId(Long shiftId);
}