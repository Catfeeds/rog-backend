package com.rograndec.feijiayun.chain.business.online.purchase.smart.dao;

import com.rograndec.feijiayun.chain.business.online.purchase.smart.entity.SmartPurchasingPlan;

import java.util.List;

public interface SmartPurchasingPlanMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SmartPurchasingPlan record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SmartPurchasingPlan record);

    /**
     *
     * @mbg.generated
     */
    SmartPurchasingPlan selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SmartPurchasingPlan record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SmartPurchasingPlan record);


    List<SmartPurchasingPlan> selectByEnterpriseId(Long enterpriseId);
}