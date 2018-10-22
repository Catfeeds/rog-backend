package com.rograndec.feijiayun.chain.business.distr.branch.approvalProcessor;

import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInNoticeMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrReqPlanMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInNotice;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrReqPlan;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrSend;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReqPlanStatus;
import com.rograndec.feijiayun.chain.common.model.ApprovalFlowPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * zeshi.sun
 */
@Service
public class DistrInNoticeApprovalProcessor implements ApprovalFlowPostProcessor{


	@Autowired
	private DistrInNoticeMapper distrInNoticeMapper;
	
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

	@Override
	public void afterAudit(Long id, Integer status,Integer approvalStatus) {
		if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue())) {
			updateDistrSend(id, status);
		}
	}

	/**
	 * 更新状态
	 * @param id
	 * @param status
	 */
	private void updateDistrSend(Long id, Integer status){
		// 审批通过 更新
		DistrInNotice distrInNotice = distrInNoticeMapper.selectByPrimaryKey(id);

		DistrInNotice updateDistrInNotice = new DistrInNotice();
		updateDistrInNotice.setId(distrInNotice.getId());
		updateDistrInNotice.setStatus(status);
		distrInNoticeMapper.updateByPrimaryKeySelective(updateDistrInNotice);
	}
}
