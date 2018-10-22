package com.rograndec.feijiayun.chain.business.purchase.order.dao;

import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderModifyRecord;

public interface PurchaseOrderModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseOrderModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseOrderModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    PurchaseOrderModifyRecord selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseOrderModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseOrderModifyRecord record);
}