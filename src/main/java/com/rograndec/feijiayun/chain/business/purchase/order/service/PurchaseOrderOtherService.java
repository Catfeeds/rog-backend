package com.rograndec.feijiayun.chain.business.purchase.order.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderDetail;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderOther;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderOtherVO;

/**
 * Created by ST on 2017/8/23.
 */
public interface PurchaseOrderOtherService {
    
    /**
     * 根据采购订单id查询采购订单配送和结算
     * @param PurchaseOrder
     * @return
     */
	PurchaseOrderOther  selectByOrderId(Long orderId);
    
   
}
