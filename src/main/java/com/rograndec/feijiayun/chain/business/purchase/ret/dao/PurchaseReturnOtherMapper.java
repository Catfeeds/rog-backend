package com.rograndec.feijiayun.chain.business.purchase.ret.dao;

import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnOther;
import org.apache.ibatis.annotations.Param;

public interface PurchaseReturnOtherMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseReturnOther record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseReturnOther record);

    /**
     *
     * @mbg.generated
     */
    PurchaseReturnOther selectByPrimaryKey(Long id);

    PurchaseReturnOther selectByReturnId(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseReturnOther record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseReturnOther record);

    void updateReturnOtherStatus(@Param("id")Long id, @Param("status") Integer status);
}