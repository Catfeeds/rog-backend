package com.rograndec.feijiayun.chain.business.online.purchase.centralized.dao;

import com.rograndec.feijiayun.chain.business.online.purchase.centralized.entity.ActivityEntreprise;

public interface ActivityEntrepriseMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(ActivityEntreprise record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ActivityEntreprise record);

    /**
     *
     * @mbg.generated
     */
    ActivityEntreprise selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ActivityEntreprise record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ActivityEntreprise record);
}