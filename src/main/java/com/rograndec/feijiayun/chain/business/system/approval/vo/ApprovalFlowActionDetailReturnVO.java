package com.rograndec.feijiayun.chain.business.system.approval.vo;

import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditResult;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowActionDetail;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApprovalFlowActionDetailReturnVO implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value = "审批流操作明细ID", required = true)
    private Long id;

    /**
     * 企业（总部）ID
     */
    @ApiModelProperty(value = "企业（总部）ID", required = true)
    private Long enterpriseId;

    /**
     * 功能审批基表ID
     */
    @ApiModelProperty(value = "功能审批基表ID", required = true)
    private Long approvalFlowActionId;

    /**
     * 审批流ID
     */
    @ApiModelProperty(value = "审批流ID", required = true)
    private Long flowId;

    /**
     * 级别（可以用来显示审批顺序）审批流ID和本字段联合可以定位审批流明细记录
     */
    @ApiModelProperty(value = "审批流操作明细级别", required = true)
    private Integer level;

    /**
     * 发起时间
     */
    @ApiModelProperty(value = "发起时间", required = true)
    private Date startTime;

    /**
     * 审批时间
     */
    @ApiModelProperty(value = "审批时间", required = true)
    private Date approvalTime;

    /**
     * 审批阶段
     */
    @ApiModelProperty(value = "审批阶段", required = true)
    private String approvalStage;

    /**
     * 审批机构ID
     */
    @ApiModelProperty(value = "审批机构ID", required = true)
    private Long approvalOrgId;
    
    /**
     * 审批机构名称
     */
    @ApiModelProperty(value = "审批机构名称", required = true)
    private String approvalOrgName;
    
    /**
     * 审批岗位ID
     */
    @ApiModelProperty(value = "审批岗位ID", required = true)
    private Long approvalPositionId;
    
    /**
     * 审批岗位名称
     */
    @ApiModelProperty(value = "审批岗位名称", required = true)
    private String approvalPositionName;
    
    /**
     * 审批角色ID
     */
    @ApiModelProperty(value = "审批角色ID", required = true)
    private Long approvalRoleId;
    
    /**
     * 审批角色名称
     */
    @ApiModelProperty(value = "审批角色名称", required = true)
    private String approvalRoleName;

    /**
     * 审批人ID
     */
    @ApiModelProperty(value = "审批人ID", required = true)
    private Long approverId;
    
    /**
     * 审批人名称
     */
    @ApiModelProperty(value = "审批人名称", required = true)
    private String approverName;

    /**
     * 审批意见
     */
    @ApiModelProperty(value = "审批意见", required = true)
    private String approvalOpinion;


    /**
     * 审批结果（0-拒绝；1-同意；2-待审核）
     */
    @ApiModelProperty(value = "审批结果（0-拒绝；1-同意；2-待审核）", required = true)
    private Integer approvalResult;

    /**
     * 状态（0-待审核 1-审核通过；2-审核被驳回）
     */
    @ApiModelProperty(value = "状态（0-待审核 1-审核通过；2-审核被驳回）", required = true)
    private Integer status;

    /**
     * 审批描述
     */
    @ApiModelProperty(value = "审批描述", required = true)
    private String approvalDesc;

    public static String generatApprovalFlowActionDesc(ApprovalFlowActionDetail approvalFlowActionDetail){

        StringBuffer sb = new StringBuffer();

        if(null != approvalFlowActionDetail.getApprovalTime()){
            Integer approvalResult = approvalFlowActionDetail.getApprovalResult();
            String dateStr = DateUtils.DateToString(approvalFlowActionDetail.getApprovalTime(),DateUtils.FMT_DATE_TIME);
            sb.append(approvalFlowActionDetail.getApproverName()).append("在").append(dateStr).append("进行审核,审核结果:")
                    .append(ApprovalFlowAuditResult.getApprovalFlowAuditResult(approvalResult).getName());
        }


        return sb.toString();
    }


    public static List<ApprovalFlowActionDetailReturnVO> getApprovalFlowActionDetailReturnVO4sApprovalFlowActionDetails(List<ApprovalFlowActionDetail> approvalFlowActionDetails){

        List<ApprovalFlowActionDetailReturnVO> approvalFlowActionDetailReturnVOS = new ArrayList<>();
        for(ApprovalFlowActionDetail approvalFlowActionDetail : approvalFlowActionDetails){

            ApprovalFlowActionDetailReturnVO approvalFlowActionDetailReturnVO = getApprovalFlowActionDetailReturnVO4ApprovalFlowActionDetail(approvalFlowActionDetail);
            approvalFlowActionDetailReturnVOS.add(approvalFlowActionDetailReturnVO);
        }

        return approvalFlowActionDetailReturnVOS;
    }

    public static ApprovalFlowActionDetailReturnVO getApprovalFlowActionDetailReturnVO4ApprovalFlowActionDetail(ApprovalFlowActionDetail approvalFlowActionDetail){

        ApprovalFlowActionDetailReturnVO approvalFlowActionDetailReturnVO = new ApprovalFlowActionDetailReturnVO();

        approvalFlowActionDetailReturnVO.setId(approvalFlowActionDetail.getId());
        approvalFlowActionDetailReturnVO.setEnterpriseId(approvalFlowActionDetail.getEnterpriseId());
        approvalFlowActionDetailReturnVO.setApprovalFlowActionId(approvalFlowActionDetail.getApprovalFlowActionId());
        approvalFlowActionDetailReturnVO.setFlowId(approvalFlowActionDetail.getFlowId());
        approvalFlowActionDetailReturnVO.setLevel(approvalFlowActionDetail.getLevel());
        approvalFlowActionDetailReturnVO.setStartTime(approvalFlowActionDetail.getStartTime());
        approvalFlowActionDetailReturnVO.setApprovalTime(approvalFlowActionDetail.getApprovalTime());
        approvalFlowActionDetailReturnVO.setApprovalStage(approvalFlowActionDetail.getApprovalStage());
        approvalFlowActionDetailReturnVO.setApprovalOrgId(approvalFlowActionDetail.getApprovalOrgId());
        approvalFlowActionDetailReturnVO.setApprovalOrgName(approvalFlowActionDetail.getApprovalOrgName());
        approvalFlowActionDetailReturnVO.setApprovalRoleId(approvalFlowActionDetail.getApprovalRoleId());
        approvalFlowActionDetailReturnVO.setApprovalRoleName(approvalFlowActionDetail.getApprovalRoleName());
        approvalFlowActionDetailReturnVO.setApproverId(approvalFlowActionDetail.getApproverId());
        approvalFlowActionDetailReturnVO.setApproverName(approvalFlowActionDetail.getApproverName());
        approvalFlowActionDetailReturnVO.setApprovalOpinion(approvalFlowActionDetail.getApprovalOpinion());
        approvalFlowActionDetailReturnVO.setApprovalResult(approvalFlowActionDetail.getApprovalResult());
        approvalFlowActionDetailReturnVO.setStatus(approvalFlowActionDetail.getStatus());

        String approvalStage = approvalFlowActionDetail.getApprovalStage();
        Date approvalTime = approvalFlowActionDetail.getApprovalTime();
        String approvalOrgName = approvalFlowActionDetail.getApprovalOrgName();
        Long approvalOrgId = approvalFlowActionDetail.getApprovalOrgId();
        String approverName = approvalFlowActionDetail.getApproverName();
        Long approverId = approvalFlowActionDetail.getApproverId();
        Integer approvalResult = approvalFlowActionDetail.getApprovalResult();
        String approvalOpinion = approvalFlowActionDetail.getApprovalOpinion();
        approvalFlowActionDetailReturnVO.setApprovalStage(approvalStage);
        approvalFlowActionDetailReturnVO.setApprovalTime(approvalTime);
        approvalFlowActionDetailReturnVO.setApprovalOrgId(approvalOrgId);
        approvalFlowActionDetailReturnVO.setApprovalOrgName(approvalOrgName);

        if(null == approverId || 0 == approverId){
            approvalFlowActionDetailReturnVO.setApproverId(null);
            approvalFlowActionDetailReturnVO.setApproverName(approvalFlowActionDetail.getApprovalRoleName());
        }else {
            approvalFlowActionDetailReturnVO.setApproverId(approverId);
            approvalFlowActionDetailReturnVO.setApproverName(approverName);
        }

        approvalFlowActionDetailReturnVO.setApprovalResult(approvalResult);
        if(null != approvalResult){
            String reuslt = ApprovalFlowAuditResult.getApprovalFlowAuditResult(approvalResult).getName();
            approvalFlowActionDetailReturnVO.setApprovalDesc(reuslt);
        }
        approvalFlowActionDetailReturnVO.setApprovalOpinion(approvalOpinion);
        String approvalDesc = generatApprovalFlowActionDesc(approvalFlowActionDetail);
        approvalFlowActionDetailReturnVO.setApprovalDesc(approvalDesc);
        return approvalFlowActionDetailReturnVO;
    }

    /**
     * saas_approval_flow_action_detail
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 企业（总部）ID
     * @return enterprise_id 企业（总部）ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业（总部）ID
     * @param enterpriseId 企业（总部）ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 功能审批基表ID
     * @return approval_flow_action_id 功能审批基表ID
     */
    public Long getApprovalFlowActionId() {
        return approvalFlowActionId;
    }

    /**
     * 功能审批基表ID
     * @param approvalFlowActionId 功能审批基表ID
     */
    public void setApprovalFlowActionId(Long approvalFlowActionId) {
        this.approvalFlowActionId = approvalFlowActionId;
    }

    /**
     * 审批流ID
     * @return flow_id 审批流ID
     */
    public Long getFlowId() {
        return flowId;
    }

    /**
     * 审批流ID
     * @param flowId 审批流ID
     */
    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    /**
     * 级别（可以用来显示审批顺序）审批流ID和本字段联合可以定位审批流明细记录
     * @return level 级别（可以用来显示审批顺序）审批流ID和本字段联合可以定位审批流明细记录
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 级别（可以用来显示审批顺序）审批流ID和本字段联合可以定位审批流明细记录
     * @param level 级别（可以用来显示审批顺序）审批流ID和本字段联合可以定位审批流明细记录
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 发起时间
     * @return start_time 发起时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 发起时间
     * @param startTime 发起时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 审批时间
     * @return approval_time 审批时间
     */
    public Date getApprovalTime() {
        return approvalTime;
    }

    /**
     * 审批时间
     * @param approvalTime 审批时间
     */
    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    /**
     * 审批阶段
     * @return approval_stage 审批阶段
     */
    public String getApprovalStage() {
        return approvalStage;
    }

    /**
     * 审批阶段
     * @param approvalStage 审批阶段
     */
    public void setApprovalStage(String approvalStage) {
        this.approvalStage = approvalStage == null ? null : approvalStage.trim();
    }

    /**
     * 审批机构ID
     * @return approval_org_id 审批机构ID
     */
    public Long getApprovalOrgId() {
        return approvalOrgId;
    }

    /**
     * 审批机构ID
     * @param approvalOrgId 审批机构ID
     */
    public void setApprovalOrgId(Long approvalOrgId) {
        this.approvalOrgId = approvalOrgId;
    }
    

	/**
     * 审批人ID
     * @return approver_id 审批人ID
     */
    public Long getApproverId() {
        return approverId;
    }

    /**
     * 审批人ID
     * @param approverId 审批人ID
     */
    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }
    
    public String getApprovalOrgName() {
		return approvalOrgName;
	}

	public void setApprovalOrgName(String approvalOrgName) {
		this.approvalOrgName = approvalOrgName;
	}

	public Long getApprovalPositionId() {
		return approvalPositionId;
	}

	public void setApprovalPositionId(Long approvalPositionId) {
		this.approvalPositionId = approvalPositionId;
	}

	public String getApprovalPositionName() {
		return approvalPositionName;
	}

	public void setApprovalPositionName(String approvalPositionName) {
		this.approvalPositionName = approvalPositionName;
	}

	public Long getApprovalRoleId() {
		return approvalRoleId;
	}

	public void setApprovalRoleId(Long approvalRoleId) {
		this.approvalRoleId = approvalRoleId;
	}

	public String getApprovalRoleName() {
		return approvalRoleName;
	}

	public void setApprovalRoleName(String approvalRoleName) {
		this.approvalRoleName = approvalRoleName;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	/**
     * 审批意见
     * @return approval_opinion 审批意见
     */
    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    /**
     * 审批意见
     * @param approvalOpinion 审批意见
     */
    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion == null ? null : approvalOpinion.trim();
    }

    /**
     * 审批结果（0-拒绝；1-同意）
     * @return approval_result 审批结果（0-拒绝；1-同意）
     */
    public Integer getApprovalResult() {
        return approvalResult;
    }

    /**
     * 审批结果（0-拒绝；1-同意）
     * @param approvalResult 审批结果（0-拒绝；1-同意）
     */
    public void setApprovalResult(Integer approvalResult) {
        this.approvalResult = approvalResult;
    }

    /**
     * 状态（0-待审核 1-审核通过；2-审核被驳回）
     * @return status 状态（0-待审核 1-审核通过；2-审核被驳回）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-待审核 1-审核通过；2-审核被驳回）
     * @param status 状态（0-待审核 1-审核通过；2-审核被驳回）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getApprovalDesc() {
        return approvalDesc;
    }

    public void setApprovalDesc(String approvalDesc) {
        this.approvalDesc = approvalDesc;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", approvalFlowActionId=").append(approvalFlowActionId);
        sb.append(", flowId=").append(flowId);
        sb.append(", level=").append(level);
        sb.append(", startTime=").append(startTime);
        sb.append(", approvalTime=").append(approvalTime);
        sb.append(", approvalStage=").append(approvalStage);
        sb.append(", approvalOrgId=").append(approvalOrgId);
        sb.append(", approverId=").append(approverId);
        sb.append(", approvalOpinion=").append(approvalOpinion);
        sb.append(", approvalResult=").append(approvalResult);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}