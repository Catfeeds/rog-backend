package com.rograndec.feijiayun.chain.business.purchase.receive.dao;

import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceiveOther;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseReceiveOtherMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseReceiveOther record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseReceiveOther record);

    /**
     *
     * @mbg.generated
     */
    PurchaseReceiveOther selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseReceiveOther record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseReceiveOther record);


    PurchaseReceiveOther selectByEnterpriseIdByReceiveId(@Param("enterpriseId") Long enterpriseId, @Param("receiveId") Long id);

	void updateStatusFinishedByReceiveId(@Param("status")Integer status, @Param("receiveId")Long receiveId);
}