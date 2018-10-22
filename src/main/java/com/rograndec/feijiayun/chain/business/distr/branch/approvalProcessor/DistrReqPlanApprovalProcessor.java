package com.rograndec.feijiayun.chain.business.distr.branch.approvalProcessor;

import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrReqPlanMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrReqPlan;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReqPlanStatus;
import com.rograndec.feijiayun.chain.common.model.ApprovalFlowPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created by 孙帮祥 on 2017/11/10.
 */
@Service
public class DistrReqPlanApprovalProcessor implements ApprovalFlowPostProcessor{


	@Autowired
	private DistrReqPlanMapper distrReqPlanMapper;
	
    @Autowired
    private RedisComponent redisComponent;

	@Override
	public void afterReapply(String content, Long dataId) {
		// TODO Auto-generated method stub

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
	/**
	 * @author 孙帮祥
	 * @param status
	 */
	@Override
	public void afterAudit(Long id, Integer status,Integer approvalStatus) {//审批之后的任何操作都调用
		DistrReqPlan plan=new DistrReqPlan();
		plan.setId(id);
		if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue())) {
			plan.setStatus(DistrReqPlanStatus.WAIT_DISTR);
		}else  if (approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue())){
			plan.setStatus(DistrReqPlanStatus.AUDIT_REJECT);
		}
		distrReqPlanMapper.updateByPrimaryKeySelective(plan);
	}
}
