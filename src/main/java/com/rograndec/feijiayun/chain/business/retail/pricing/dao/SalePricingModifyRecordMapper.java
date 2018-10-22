package com.rograndec.feijiayun.chain.business.retail.pricing.dao;

import com.rograndec.feijiayun.chain.business.retail.pricing.entity.SalePricingModifyRecord;

public interface SalePricingModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SalePricingModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SalePricingModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    SalePricingModifyRecord selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SalePricingModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SalePricingModifyRecord record);
}