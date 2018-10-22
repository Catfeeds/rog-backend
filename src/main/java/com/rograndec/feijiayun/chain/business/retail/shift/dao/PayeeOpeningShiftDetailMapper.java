package com.rograndec.feijiayun.chain.business.retail.shift.dao;

import com.rograndec.feijiayun.chain.business.retail.shift.entity.PayeeOpeningShiftDetail;

import java.util.List;

public interface PayeeOpeningShiftDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PayeeOpeningShiftDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PayeeOpeningShiftDetail record);

    /**
     *
     * @mbg.generated
     */
    PayeeOpeningShiftDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PayeeOpeningShiftDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PayeeOpeningShiftDetail record);


    List<PayeeOpeningShiftDetail> selectByIds(List<Long> ids);
}