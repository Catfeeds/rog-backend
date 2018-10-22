package com.rograndec.feijiayun.chain.business.purchase.order.approvalProcessor;

import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.model.ApprovalFlowPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created by zhaiwei on 2017/9/19.
 */
@Service
public class PurchaseOrderCancelApprovalProcessor implements ApprovalFlowPostProcessor{


	@Autowired
	private PurchaseOrderMapper orderMapper;
	


	@Override
	public void afterReapply(String content, Long dataId) {

	}

	/**
	 * 取消成功后更新为取消状态
	 */
	@Override
	public void afterCancel(String content, Long dataId, Long eId) {


	}

	@Override
	public void afterApply(String content, Long dataId) {
	
	}
	@Override
	public void afterReapply() {
	}
	@Override
	public void afterApply() {
	}
	@Override
	public void afterCancel() {

	}
	/**
	 * @author 孙帮祥
	 * @param status
	 * 	AUDIT_REJECT(23, "审核拒绝")
	 *  AUDITED(22, "审核通过")
	 */
	@Override
	public void afterAudit(Long id, Integer status,Integer approvalStatus) {//审批之后的任何操作都调用
		PurchaseOrder order=new PurchaseOrder();
		order.setId(id);
		if(status.equals(23)){//如果状态等于1是审批通过，如果状态是0是审批被驳回
			order.setStatus(PurchaseStatus.AUDIT_REJECT.getStatus());
		}else if(status.equals(22)){
			order.setStatus(PurchaseStatus.WAIT_RECEIVE.getStatus());
		}
		orderMapper.updateByPrimaryKeySelective(order);
	}
}
