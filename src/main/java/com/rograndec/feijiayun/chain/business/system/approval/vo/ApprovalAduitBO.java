package com.rograndec.feijiayun.chain.business.system.approval.vo;

import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowAction;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowDetail;

import java.util.List;

/**
 * Created by zhaiwei on 2017/9/19.
 */
public class ApprovalAduitBO {

    private ApprovalFlowAction approvalFlowAction;

    /**
     * 下一个审批集合
     */
    private List<ApprovalFlowDetail> nextDetailList;

    /**
     * 上一个审批集合
     */
    private List<ApprovalFlowDetail> prevDetailList;

    public ApprovalFlowAction getApprovalFlowAction() {
        return approvalFlowAction;
    }

    public void setApprovalFlowAction(ApprovalFlowAction approvalFlowAction) {
        this.approvalFlowAction = approvalFlowAction;
    }

    public List<ApprovalFlowDetail> getNextDetailList() {
        return nextDetailList;
    }

    public void setNextDetailList(List<ApprovalFlowDetail> nextDetailList) {
        this.nextDetailList = nextDetailList;
    }

    public List<ApprovalFlowDetail> getPrevDetailList() {
        return prevDetailList;
    }

    public void setPrevDetailList(List<ApprovalFlowDetail> prevDetailList) {
        this.prevDetailList = prevDetailList;
    }
}
