package com.rograndec.feijiayun.chain.business.purchase.ret.dao;

import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnModifyRecord;

import java.util.List;

public interface PurchaseReturnModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseReturnModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseReturnModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    PurchaseReturnModifyRecord selectByPrimaryKey(Long id);

    List<PurchaseReturnModifyRecord> selectByReturnId(Long returnId);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseReturnModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseReturnModifyRecord record);
}