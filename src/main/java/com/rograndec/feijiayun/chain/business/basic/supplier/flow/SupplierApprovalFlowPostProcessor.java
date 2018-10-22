package com.rograndec.feijiayun.chain.business.basic.supplier.flow;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatus;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.model.ApprovalFlowPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierApprovalFlowPostProcessor implements ApprovalFlowPostProcessor {

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public void afterApply(String content, Long dataId) {

    }

    @Override
    public void afterReapply(String content, Long dataId) {

    }

    @Override
    public void afterCancel(String content, Long dataId, Long eId) {

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

    @Override
    public void afterAudit(Long id, Integer status, Integer approvalStatus) {
        //更新商品信息的审批状态
        if(ApprovalFlowAuditStatus.DETAIL_STATUS_PASS.getValue() == approvalStatus){
            supplierMapper.updateSupplierStatus(id,approvalStatus);
        } else {
            supplierMapper.updateSupplierStatus(id,approvalStatus);
        }
    }
}
