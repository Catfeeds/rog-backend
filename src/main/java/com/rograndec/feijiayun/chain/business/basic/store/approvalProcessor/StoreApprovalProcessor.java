package com.rograndec.feijiayun.chain.business.basic.store.approvalProcessor;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserAdministrationMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserPersonalMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserAdministration;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserPersonal;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowActionMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.component.RGTUserComponent;
import com.rograndec.feijiayun.chain.common.model.ApprovalFlowPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 
 * 员工审批流的实现类
 * Created by dongdong.zhang on 2017/10/16.
 */
@Service
public class StoreApprovalProcessor implements ApprovalFlowPostProcessor{

	@Autowired
	private EnterpriseMapper enterpriseMapper;

    @Autowired
    private ApprovalFlowActionMapper approvalFlowActionMapper;

    @Override
    public void afterCancel(String content,Long dataId,Long eId) {
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
    public void save(Long id,Integer approvalStatus){


        Enterprise enterprise = new Enterprise();
        enterprise.setId(id);
        enterprise.setApproveStatus(approvalStatus);

        enterpriseMapper.updateByPrimaryKeySelective(enterprise);



    }

    @Override
    public void afterAudit(Long id, Integer status,Integer approvalStatus){

        if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue())) {

            save(id, ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue());

        }else if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue())){

            save(id, ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue());
        }
        else if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_IN.getValue())){
            save(id, ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_IN.getValue());
        }

    }
}
