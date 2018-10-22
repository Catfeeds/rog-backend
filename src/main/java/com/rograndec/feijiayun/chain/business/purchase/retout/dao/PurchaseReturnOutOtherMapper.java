package com.rograndec.feijiayun.chain.business.purchase.retout.dao;

import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOutOther;

public interface PurchaseReturnOutOtherMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseReturnOutOther record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseReturnOutOther record);

    /**
     *
     * @mbg.generated
     */
    PurchaseReturnOutOther selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseReturnOutOther record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseReturnOutOther record);

    int updateStatusByReturnOut(PurchaseReturnOutOther record);

    PurchaseReturnOutOther selectByOutId(Long outId);
}