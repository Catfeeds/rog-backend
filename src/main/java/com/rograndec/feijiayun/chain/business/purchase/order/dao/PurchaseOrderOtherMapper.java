package com.rograndec.feijiayun.chain.business.purchase.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderOther;

public interface PurchaseOrderOtherMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PurchaseOrderOther record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PurchaseOrderOther record);

    /**
     *
     * @mbg.generated
     */
    PurchaseOrderOther selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PurchaseOrderOther record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PurchaseOrderOther record);
    
    /**
     * 
     * 根据订单id获取采购订单配送和结算
     * */
    PurchaseOrderOther selectByOrderId(Long orderId);

    PurchaseOrderOther selectByEnterPriseId(@Param("enterpriseId")Long enterpriseId, @Param("purchaseOrderId")Long purchaseOrderId);

    PurchaseOrderOther selectByEnterpriseIdByOrderId(@Param("enterpriseId") Long enterpriseId, @Param("orderId") Long id);

	void updateStatusFinishedByOrderId(@Param("status")Integer status, @Param("orderId")Long orderId);
	
	/**
	 * 
	 * @Description: 返回配送信息
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年12月1日 下午4:18:16 
	 * @param enterpriseId
	 * @param list
	 * @return 
	 * @return List<PurchaseOrderOther>
	 */
	List<PurchaseOrderOther> selectByEnterpriseIdAndOrderIds(@Param("enterpriseId") Long enterpriseId, @Param("list") List<Long> list);
}