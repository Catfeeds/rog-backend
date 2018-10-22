package com.rograndec.feijiayun.chain.business.purchase.plan.approvalProcessor;

import com.rograndec.feijiayun.chain.business.purchase.plan.dao.PurchasePlanDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.plan.dao.PurchasePlanMapper;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.model.ApprovalFlowPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchasePlanApprovalProcessor implements ApprovalFlowPostProcessor {

	@Autowired
	private PurchasePlanMapper planMapper;

	@Autowired
	private PurchasePlanDetailMapper planDetailMapper;

	/**
	 * 取消成功后更新为取消状态
	 */
	@Override
	public void afterCancel(String content, Long dataId,Long eId) {

	}

	@Override
	public void afterReapply(String content, Long dataId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterApply(String content, Long dataId) {

	}

	@Override
	public void afterReapply() {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterApply() {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCancel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterAudit(Long id, Integer status,Integer approvalStatus) {
		if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue())) {
			planMapper.updateStatus(id, PurchaseStatus.PENDING_ORDER.getStatus());
			planDetailMapper.updateStatusByPlanId(id, PurchaseStatus.PENDING_ORDER.getStatus());
		} else  if (approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue())){
			planMapper.updateStatus(id, PurchaseStatus.AUDIT_REJECT.getStatus());
			planDetailMapper.updateStatusByPlanId(id, PurchaseStatus.AUDIT_REJECT.getStatus());
		}
	}

}
