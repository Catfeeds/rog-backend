package com.rograndec.feijiayun.chain.business.purchase.receive.dao;

import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceive;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PurchaseReceiveMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseReceive record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseReceive record);

    /**
     *
     * @mbg.generated
     */
    PurchaseReceive selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseReceive record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseReceive record);

    PurchaseReceive selectByEnterpriseId(@Param("enterpriseId") Long enterpriseId, @Param("purchaseOrderId") Long purchaseOrderId);

    List<PurchaseReceive> getReceived(Map param);

	void updateStatusFinishedById(@Param("status")Integer status, @Param("id")Long id);

    List<PurchaseReceive> selectByEnterpriseIdBybaseOrderId(@Param("enterpriseId") Long enterpriseId, @Param("baseOrderId") Long baseOrderId);

    Integer getReceivedCount(Map param);
}