package com.rograndec.feijiayun.chain.business.system.approval.dao;

import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowActionDetail;
import com.rograndec.feijiayun.chain.business.system.approval.vo.ApprovalFlowActionDetailListVO;
import com.rograndec.feijiayun.chain.business.system.approval.vo.ApprovalFlowActionSearchVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ApprovalFlowActionDetailMapper {
	
	List<ApprovalFlowActionDetail> getActionDetailListByIdForWeb(Long actionId);
	
	List<ApprovalFlowActionDetail> getActionDetailListById(Long actionId);

	List<ApprovalFlowActionDetail> getActionDetailListByIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(ApprovalFlowActionDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ApprovalFlowActionDetail record);

    /**
     *
     * @mbg.generated
     */
    ApprovalFlowActionDetail selectByPrimaryKey(Long id);

    /**
     * 根据 审批内容, 企业id 商品id 查询价格调整单的审批操作
     * @param map
     * @return
     */
    List<ApprovalFlowActionDetail> selectAdjustByApprovalFlowAction(Map<String,Object> map);

    List<ApprovalFlowActionDetail> selectByActionIds(List<Long> list);

    List<ApprovalFlowActionDetail> selectByActionFlowId(Long flowId);
    List<ApprovalFlowActionDetailListVO> getMustApprovalActionList(@Param("vo") ApprovalFlowActionSearchVO vo);

    List<ApprovalFlowActionDetail> selectByActionFlowIdDesc(@Param("desc") String desc, @Param("flowId")Long flowId);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ApprovalFlowActionDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ApprovalFlowActionDetail record);
    
    int updateAuditResult(ApprovalFlowActionDetail record);

    /**
     *
     * @param enterpriseId
     * @param name
     * @param dataId
     * @param statusRecom ApprovalFlowAuditStatusRecom 复核状态（0-待审核；1-已完成；2-审核被驳回 -2:取消 -1:审核中）)
     * @return
     */
    List<ApprovalFlowActionDetail> selectFirstApprovalAction(@Param("enterpriseId") Long enterpriseId, @Param("name") String name, @Param("dataId") Long dataId,@Param("statusRecom")Integer statusRecom);

}