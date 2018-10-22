package com.rograndec.feijiayun.chain.business.purchase.order.service.impl;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderDetail;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderOther;
import com.rograndec.feijiayun.chain.business.purchase.order.service.PurchaseOrderOtherService;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderOtherVO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
/**
 * 采购订单服务接口
 * @author 孙帮祥
 */
@Service
public class PurchaseOrderOtherServiceImpl implements PurchaseOrderOtherService {

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    private PurchaseOrderDetailMapper purchaseOrderDetailMapper;
    @Autowired
    private PurchaseOrderOtherMapper purchaseOrderOtherMapper;
	@Override
	public PurchaseOrderOther selectByOrderId(Long orderId) {
		PurchaseOrderOther vo=purchaseOrderOtherMapper.selectByOrderId(orderId);
		return vo;
	}
  
}