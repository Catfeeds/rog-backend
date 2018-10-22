package com.rograndec.feijiayun.chain.business.purchase.ret.approvalProcessor;

import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturn;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnDetail;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnOther;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowActionMapper;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowAction;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.model.ApprovalFlowPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaiwei on 2017/9/19.
 */
@Service
public class ReturnApprovalProcessor implements ApprovalFlowPostProcessor{

    @Autowired
    private PurchaseReturnMapper purchaseReturnMapper;

    @Autowired
    private PurchaseReturnOtherMapper purchaseReturnOtherMapper;

    @Autowired
    private PurchaseReturnDetailMapper purchaseReturnDetailMapper;

    @Autowired
    private ApprovalFlowActionMapper approvalFlowActionMapper;

    @Override
    public void afterCancel(String content,Long dataId,Long eId) {
       /* Map<String,Object> map = new HashMap<>();
        map.put("content",content);
        map.put("dataId",dataId);
        map.put("enterpriseId",eId);
        ApprovalFlowAction approvalFlowAction = approvalFlowActionMapper.selectByDataId(map);*/

        save(dataId, PurchaseStatus.CANCELED.getStatus());
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

    public void save(Long id, Integer status){
        PurchaseReturn purchaseReturn = purchaseReturnMapper.selectByPrimaryKey(id);

        List<PurchaseReturnDetail> purchaseReturnDetails = purchaseReturnDetailMapper.selectByReturnId(purchaseReturn.getId());

        PurchaseReturnOther purchaseReturnOther = purchaseReturnOtherMapper.selectByReturnId(purchaseReturn.getId());

        PurchaseReturn newPurchaseReturn = new PurchaseReturn();
        newPurchaseReturn.setStatus(status);
        newPurchaseReturn.setId(purchaseReturn.getId());
        purchaseReturnMapper.updateByPrimaryKeySelective(newPurchaseReturn);

        for(PurchaseReturnDetail purchaseReturnDetail : purchaseReturnDetails){
            PurchaseReturnDetail newPurchaseReturnDetail = new PurchaseReturnDetail();
            newPurchaseReturnDetail.setId(purchaseReturnDetail.getId());
            newPurchaseReturnDetail.setStatus(status);

            purchaseReturnDetailMapper.updateByPrimaryKeySelective(newPurchaseReturnDetail);
        }

        PurchaseReturnOther newPurchaseReturnOther = new PurchaseReturnOther();
        newPurchaseReturnOther.setId(purchaseReturnOther.getId());
        newPurchaseReturnOther.setStatus(status);
        purchaseReturnOtherMapper.updateByPrimaryKeySelective(newPurchaseReturnOther);
    }

    /**
     * 	PENDING_AUDIT(21, "待审核"),
     AUDITED(22, "审核通过"),
     AUDIT_REJECT(23, "审核拒绝"),
     AUDIT_WITHDRAWAL(24, "审核撤回"),
     * @param id
     * @param status
     * @param approvalStatus
     */
    @Override
    public void afterAudit(Long id, Integer status,Integer approvalStatus){
        if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue())) {
            save(id, status);
        }else if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue())){
            save(id, PurchaseStatus.PENDING_AUDIT.getStatus());
        }else if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue())){
            save(id, PurchaseStatus.AUDIT_WITHDRAWAL.getStatus());
        }
    }
}
