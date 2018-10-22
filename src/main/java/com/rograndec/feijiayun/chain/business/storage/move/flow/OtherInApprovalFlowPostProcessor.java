package com.rograndec.feijiayun.chain.business.storage.move.flow;

import com.rograndec.feijiayun.chain.business.storage.move.dao.OtherInDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.move.dao.OtherInMapper;
import com.rograndec.feijiayun.chain.business.storage.move.service.OtherInService;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatus;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PubStatus;
import com.rograndec.feijiayun.chain.common.model.ApprovalFlowPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtherInApprovalFlowPostProcessor implements ApprovalFlowPostProcessor {

    @Autowired
    private OtherInMapper otherInMapper;

    @Autowired
    private OtherInService otherInService;

    @Autowired
    private OtherInDetailMapper otherInDetailMapper;

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
    public void afterAudit(Long id, Integer status, Integer approvalStatus) throws Exception{
        //更新其他入库信息的审批状态
        otherInMapper.updateOtherInStatus(id, approvalStatus);
        otherInDetailMapper.updateOtherInDetailStatusByOtherInIdAndStatus(id,approvalStatus);
        /**
         * 当状态为审核通过时，更新关键表数据
         */
        if (ApprovalFlowAuditStatus.DETAIL_STATUS_PASS.getValue() == approvalStatus ){
            otherInService.handleKeyTable(id);
        }
    }
}
