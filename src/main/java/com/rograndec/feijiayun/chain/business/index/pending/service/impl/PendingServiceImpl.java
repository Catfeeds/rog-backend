package com.rograndec.feijiayun.chain.business.index.pending.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserRoleMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserRole;
import com.rograndec.feijiayun.chain.business.index.pending.service.PendingService;
import com.rograndec.feijiayun.chain.business.index.pending.vo.ApprovalFlowPendingCountVO;
import com.rograndec.feijiayun.chain.business.index.pending.vo.ApprovalFlowPendingVO;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowActionMapper;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PendingServiceImpl implements PendingService{

    @Autowired
    private ApprovalFlowActionMapper approvalFlowActionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<ApprovalFlowPendingVO>  getPendingApprovalFlow(UserVO userVO){

        List<UserRole> userRoles = userRoleMapper.selectByUserId(userVO.getUserId());

        List<Long> roles = userRoles.stream().map(r -> r.getRoleId()).collect(Collectors.toList());

        List<ApprovalFlowPendingVO> approvalFlowPendingVOS = approvalFlowActionMapper.selectPendingApprovalFlow(
                userVO.getEnterpriseId()
                , ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue()
                , ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_IN.getValue()
                ,userVO.getUserId()
                ,roles
        );
        return approvalFlowPendingVOS;
    }

    @Override
    public List<ApprovalFlowPendingCountVO> getPendingApprovalFlowCount(UserVO userVO){
    	if(null != userVO && userVO.getUserId() != null) {
    		List<UserRole> userRoles = userRoleMapper.selectByUserId(userVO.getUserId());

            List<Long> roles = userRoles.stream().map(r -> r.getRoleId()).collect(Collectors.toList());

            List<ApprovalFlowPendingCountVO> count = approvalFlowActionMapper.selectPendingApprovalFlowCount(
                    userVO.getEnterpriseId()
                    , ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue()
                    , ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_IN.getValue()
                    ,userVO.getUserId()
                    ,roles
            );
            
            return count;
    	}
       return null;
    }
}
