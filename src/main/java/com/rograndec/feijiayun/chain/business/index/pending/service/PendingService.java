package com.rograndec.feijiayun.chain.business.index.pending.service;

import com.rograndec.feijiayun.chain.business.index.pending.vo.ApprovalFlowPendingCountVO;
import com.rograndec.feijiayun.chain.business.index.pending.vo.ApprovalFlowPendingVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

public interface PendingService {
    List<ApprovalFlowPendingVO> getPendingApprovalFlow(UserVO userVO);

    List<ApprovalFlowPendingCountVO> getPendingApprovalFlowCount(UserVO userVO);
}
