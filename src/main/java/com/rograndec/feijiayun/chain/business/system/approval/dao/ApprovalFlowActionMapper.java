package com.rograndec.feijiayun.chain.business.system.approval.dao;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.index.pending.vo.ApprovalFlowPendingCountVO;
import com.rograndec.feijiayun.chain.business.index.pending.vo.ApprovalFlowPendingVO;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowAction;
import com.rograndec.feijiayun.chain.business.system.approval.vo.ApprovalFlowActionSearchVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ApprovalFlowActionMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);
    
    List<ApprovalFlowAction> getListByFlowId(Long flowId);
    
    List<ApprovalFlowAction> getListByFlowIdStatusRecom(@Param("flowId") Long flowId, @Param("statusRecom") int statusRecom,@Param("eId") Long eId);

    List<ApprovalFlowAction> getListByEnterpriseAndStatusRecom(@Param("enterpriseId") Long enterpriseId, @Param("list") List<Integer> list);

    List<ApprovalFlowAction> getActionList(@Param("vo") ApprovalFlowActionSearchVO vo);

    List<ApprovalFlowAction> getHeadquartersActionList(@Param("vo") ApprovalFlowActionSearchVO vo);

    List<ApprovalFlowAction> getActionList4FlowOrg(@Param("vo") ApprovalFlowActionSearchVO vo);

    /**
     *
     * @mbg.generated
     */
    int insert(ApprovalFlowAction record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ApprovalFlowAction record);

    /**
     *
     * @mbg.generated
     */
    ApprovalFlowAction selectByPrimaryKey(Long id);

    List<ApprovalFlowAction> selectByIds(List<Long> list);

    ApprovalFlowAction selectByDataId(Map<String,Object> map);

    List<ApprovalFlowAction> selectBystatusRecoms(@Param("dataId") Long dataId,@Param("enterpriseId") Long enterpriseId
            ,@Param("content") String content
            ,@Param("list") List<Integer> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ApprovalFlowAction record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ApprovalFlowAction record);
    
    int updateStatusRecom(@Param("id") long id, @Param("statusRecom") int statusRecom);
    
    List<User> getUserListByRoleId(@Param("eId") long eId, @Param("roleId") long roleId);


    List<ApprovalFlowPendingVO> selectPendingApprovalFlow(@Param("enterpriseId") long enterpriseId,
                                                          @Param("currentstatusRecom") Integer currentstatusRecom,
                                                          @Param("nextstatusRecom") Integer nextstatusRecom,
                                                          @Param("approverId") Long approverId,
                                                          @Param("roles") List<Long> roles
    );

    List<ApprovalFlowPendingCountVO> selectPendingApprovalFlowCount(@Param("enterpriseId") long enterpriseId,
                                                              @Param("currentstatusRecom") Integer currentstatusRecom,
                                                              @Param("nextstatusRecom") Integer nextstatusRecom,
                                                              @Param("approverId") Long approverId,
                                                              @Param("roles") List<Long> roles
    );
}