package com.rograndec.feijiayun.chain.business.system.opening.dao;

import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningPayment;
import org.apache.ibatis.annotations.Param;

public interface OpeningPaymentMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(OpeningPayment record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(OpeningPayment record);

    /**
     *
     * @mbg.generated
     */
    OpeningPayment selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OpeningPayment record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OpeningPayment record);

    OpeningPayment selectByEnterpriseId(@Param("enterpriseId") Long enterpriseId);
}