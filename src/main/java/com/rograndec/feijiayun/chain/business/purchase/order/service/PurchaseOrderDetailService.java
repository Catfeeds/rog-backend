package com.rograndec.feijiayun.chain.business.purchase.order.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderDetail;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderOther;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderDetailVO;

/**
 * Created by ST on 2017/8/23.
 */
public interface PurchaseOrderDetailService {
    /**
     * 根据订单id查询订单详情
     * @param PurchaseOrder
     * @return
     */
    List<PurchaseOrderDetail> selectByOrderId(Long orderId);
}
