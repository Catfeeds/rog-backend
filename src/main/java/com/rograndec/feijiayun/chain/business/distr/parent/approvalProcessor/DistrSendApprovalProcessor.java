package com.rograndec.feijiayun.chain.business.distr.parent.approvalProcessor;

import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrSendMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrSend;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.common.model.ApprovalFlowPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/13 <br/>
 * 描述：总部-配送单-审批流
 */
@Service
public class DistrSendApprovalProcessor implements ApprovalFlowPostProcessor {
    @Autowired
    private DistrSendMapper distrSendMapper;

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
        // 审批通过 更新为 待出库
        DistrSend distrSend = distrSendMapper.selectByPrimaryKey(id);

        DistrSend updateDistrSend = new DistrSend();
        updateDistrSend.setId(distrSend.getId());
        updateDistrSend.setStatus(status);
        distrSendMapper.updateByPrimaryKeySelective(updateDistrSend);
    }
}
