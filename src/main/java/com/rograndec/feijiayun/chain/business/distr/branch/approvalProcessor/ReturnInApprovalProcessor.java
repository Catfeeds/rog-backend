package com.rograndec.feijiayun.chain.business.distr.branch.approvalProcessor;

import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInReturnDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInReturnMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturn;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnDetail;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowActionMapper;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowAction;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInReturnStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.model.ApprovalFlowPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 添加配进退出审批流的实现类
 * Created by dongdong.zhang on 2017/10/16.
 */
@Service
public class ReturnInApprovalProcessor implements ApprovalFlowPostProcessor{

	@Autowired
	private DistrInReturnMapper distrInReturnMapper;

	@Autowired
	private DistrInReturnDetailMapper distrInReturnDetailMapper;

    @Autowired
    private ApprovalFlowActionMapper approvalFlowActionMapper;

    @Override
    public void afterCancel(String content,Long dataId,Long eId) {
        Map<String,Object> map = new HashMap<>();
        map.put("content",content);
        map.put("dataId",dataId);
        map.put("enterpriseId",eId);
        ApprovalFlowAction approvalFlowAction = approvalFlowActionMapper.selectByDataId(map);
        save(approvalFlowAction.getDataId(), DistrInReturnStatus.CANCELED);
    }

    @Override
    public void afterReapply(String content,Long dataId) {

    }

    @Override
    public void afterApply(String content,Long dataId) {

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

    //更新状态
    public void save(Long id, Integer status){
    	//DistrInReturn distrInReturn= distrInReturnMapper.selectByPrimaryKey(id);
        List<Long> ids = distrInReturnDetailMapper.selectByReturnInId(id);
        
        DistrInReturn newDistrInReturn=new DistrInReturn(); 
        newDistrInReturn.setId(id);
        newDistrInReturn.setStatus(status);
        distrInReturnMapper.updateByPrimaryKeySelective(newDistrInReturn);
        
        for(Long detailId : ids){
        	DistrInReturnDetail newDistrInReturnDetail = new DistrInReturnDetail();
        	newDistrInReturnDetail.setId(detailId);
        	newDistrInReturnDetail.setStatus(status);
        	distrInReturnDetailMapper.updateByPrimaryKeySelective(newDistrInReturnDetail);
        }
    }

    @Override
    public void afterAudit(Long id, Integer status,Integer approvalStatus){

        if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue())){
            save(id,status);
        }else if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue())){
        	save(id,DistrInReturnStatus.PENDING_AUDIT);
        }else if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue())){
        	save(id,DistrInReturnStatus.AUDIT_REJECT);
        }

    }
}
