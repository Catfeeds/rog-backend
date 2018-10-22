package com.rograndec.feijiayun.chain.business.system.approval.vo;

import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowAction;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApprovalFlowActionReturnVO implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value = "审批流操作基表ID", required = true)
    private Long id;

    /**
     * 企业（总部）ID
     */
    @ApiModelProperty(value = "企业（总部）ID", required = true)
    private Long enterpriseId;

    /**
     * 审批内容（0101-供货单位；0102-员工信息；0103-门店信息；0201-商品信息；0202-价格调整；0301-采购计划；0302-购进退出；0401-盘点；0402-其他入库；0403-药品销毁；0501-配进退出）
     */
	@ApiModelProperty(
    		value = "审批内容（0101-供货单位；0102-员工信息；0103-门店信息；0201-商品信息；0202-价格调整；0301-采购计划；0302-购进退出；0401-盘点；0402-其他入库；0403-药品销毁；0501-配进退出）", 
    		required = true)
    private String content;

    @ApiModelProperty(
            value = "审批内容描述（0101-供货单位；0102-员工信息；0103-门店信息；0201-商品信息；0202-价格调整；0301-采购计划；0302-购进退出；0401-盘点；0402-其他入库；0403-药品销毁；0501-配进退出）",
            required = true)
    private String contentDesc;

    /**
     * 审批流ID
     */
	@ApiModelProperty(value = "审批流ID", required = true)
    private Long flowId;

    /**
     * 操作类型（0-新增；1-修改）
     */
    @ApiModelProperty(value = "操作类型（0-新增；1-修改）", required = true)
    private Integer operationType;

    /**
     * 根据审批内容不同具体关联数据ID，如审核内容表示商品信息，则该字段表示商品ID
     */
	@ApiModelProperty(value = "根据审批内容不同具体关联数据ID，如审核内容表示商品信息，则该字段表示商品ID", required = true)
    private Long dataId;

    /**
     * 编码
     */
	@ApiModelProperty(value = "编码", required = true)
    private String code;

    /**
     * 名称
     */
	@ApiModelProperty(value = "名称", required = true)
    private String name;

    /**
     * 发起时间
     */
	@ApiModelProperty(value = "发起时间", required = true)
    private Date startTime;

    /**
     * 发起机构ID
     */
	@ApiModelProperty(value = "发起机构ID", required = true)
    private Long startOrgId;
    
    /**
     * 发起机构名称
     */
	@ApiModelProperty(value = "发起机构名称", required = true)
    private String startOrgName;

    /**
     * 发起人ID
     */
	@ApiModelProperty(value = "发起人ID", required = true)
    private Long starterId;
    
    /**
     * 发起人名称
     */
	@ApiModelProperty(value = "发起人名称", required = true)
    private String starterName;
    
    /**
     * 复合状态（0-待审核；1-已完成；2-审核被驳回）
     */
	@ApiModelProperty(value = "复合状态（0-待审核；1-已完成；2-审核被驳回）", required = true)
    private Long statusRecom;

    @ApiModelProperty(value = "复合状态（0-待审核；1-已完成；2-审核被驳回）", required = true)
    private String statusRecomDesc;
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

    @ApiModelProperty(value = "审批结果描述（0-拒绝；1-同意；2-待审核）", required = true)
    private String approvalResultDesc;

    @ApiModelProperty(value = "需要审批的审批流id", required = true)
    private Long lastActionId;

    @ApiModelProperty(value = "是否是当前人审批", required = true)
    private boolean isApproval;

	@ApiModelProperty(value = "审批流操作明细数据集合", required = true)
    private List<ApprovalFlowActionDetailReturnVO> aFADList = new ArrayList<>();


    public static List<ApprovalFlowActionReturnVO> getApprovalFlowActionReturnVOs4ApprovalFlowActions(List<ApprovalFlowAction> approvalFlowActions){

        List<ApprovalFlowActionReturnVO> approvalFlowActionReturnVOS = new ArrayList<>();

        for (ApprovalFlowAction approvalFlowAction : approvalFlowActions){
            ApprovalFlowActionReturnVO approvalFlowActionReturnVO = getApprovalFlowActionReturnVO4ApprovalFlowAction(approvalFlowAction);
            approvalFlowActionReturnVOS.add(approvalFlowActionReturnVO);
        }

        return approvalFlowActionReturnVOS;
    }


	public static ApprovalFlowActionReturnVO getApprovalFlowActionReturnVO4ApprovalFlowAction(ApprovalFlowAction approvalFlowAction){
        ApprovalFlowActionReturnVO approvalFlowActionReturnVO = new ApprovalFlowActionReturnVO();

        approvalFlowActionReturnVO.setId(approvalFlowAction.getId());
        approvalFlowActionReturnVO.setEnterpriseId(approvalFlowAction.getEnterpriseId());
        approvalFlowActionReturnVO.setContent(approvalFlowAction.getContent());
        approvalFlowActionReturnVO.setFlowId(approvalFlowAction.getFlowId());
        approvalFlowActionReturnVO.setOperationType(approvalFlowAction.getOperationType());
        approvalFlowActionReturnVO.setDataId(approvalFlowAction.getDataId());
        approvalFlowActionReturnVO.setCode(approvalFlowAction.getCode());
        approvalFlowActionReturnVO.setName(approvalFlowAction.getName());
        approvalFlowActionReturnVO.setStartTime(approvalFlowAction.getStartTime());
        approvalFlowActionReturnVO.setStartOrgId(approvalFlowAction.getStartOrgId());
        approvalFlowActionReturnVO.setStartOrgName(approvalFlowAction.getStartOrgName());
        approvalFlowActionReturnVO.setStarterId(approvalFlowAction.getStarterId());
        approvalFlowActionReturnVO.setStarterName(approvalFlowAction.getStarterName());
        approvalFlowActionReturnVO.setStatusRecom(approvalFlowAction.getStatusRecom());
        approvalFlowActionReturnVO.setContentDesc(approvalFlowAction.getContentDesc());
/*
        approvalFlowActionReturnVO.setApprovalStage(approvalFlowAction.getCurrentApprovalStage());
*/
        String statusRecom = ApprovalFlowAuditStatusRecom.getApprovalFlowAuditStatusRecomEnum(approvalFlowAction.getStatusRecom().intValue()).getName();
        approvalFlowActionReturnVO.setStatusRecomDesc(statusRecom);

        return approvalFlowActionReturnVO;
    }


    /**
     * saas_approval_flow_action
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
     * 审批内容（）
     * @return content 审批内容（）
     */
    public String getContent() {
        return content;
    }

    /**
     * 审批内容（）
     * @param content 审批内容（）
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
     * 操作类型（0-新增；1-修改）
     * @return operation_type 操作类型（0-新增；1-修改）
     */
    public Integer getOperationType() {
        return operationType;
    }

    /**
     * 操作类型（0-新增；1-修改）
     * @param operationType 操作类型（0-新增；1-修改）
     */
    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    /**
     * 根据审批内容不同具体关联数据ID，如审核内容表示商品信息，则该字段表示商品ID
     * @return data_id 根据审批内容不同具体关联数据ID，如审核内容表示商品信息，则该字段表示商品ID
     */
    public Long getDataId() {
        return dataId;
    }

    /**
     * 根据审批内容不同具体关联数据ID，如审核内容表示商品信息，则该字段表示商品ID
     * @param dataId 根据审批内容不同具体关联数据ID，如审核内容表示商品信息，则该字段表示商品ID
     */
    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    /**
     * 编码
     * @return code 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 编码
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
     * 发起机构ID
     * @return start_org_id 发起机构ID
     */
    public Long getStartOrgId() {
        return startOrgId;
    }

    /**
     * 发起机构ID
     * @param startOrgId 发起机构ID
     */
    public void setStartOrgId(Long startOrgId) {
        this.startOrgId = startOrgId;
    }

    /**
     * 发起人ID
     * @return starter_id 发起人ID
     */
    public Long getStarterId() {
        return starterId;
    }

    /**
     * 发起人ID
     * @param starterId 发起人ID
     */
    public void setStarterId(Long starterId) {
        this.starterId = starterId;
    }
    
    public String getStartOrgName() {
		return startOrgName;
	}

	public void setStartOrgName(String startOrgName) {
		this.startOrgName = startOrgName;
	}

	public String getStarterName() {
		return starterName;
	}

	public void setStarterName(String starterName) {
		this.starterName = starterName;
	}

	public Long getStatusRecom() {
		return statusRecom;
	}

	public void setStatusRecom(Long statusRecom) {
		this.statusRecom = statusRecom;
	}

    public List<ApprovalFlowActionDetailReturnVO> getaFADList() {
        return aFADList;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public String getApprovalStage() {
        return approvalStage;
    }

    public void setApprovalStage(String approvalStage) {
        this.approvalStage = approvalStage;
    }

    public Long getApprovalOrgId() {
        return approvalOrgId;
    }

    public void setApprovalOrgId(Long approvalOrgId) {
        this.approvalOrgId = approvalOrgId;
    }

    public String getApprovalOrgName() {
        return approvalOrgName;
    }

    public void setApprovalOrgName(String approvalOrgName) {
        this.approvalOrgName = approvalOrgName;
    }

    public Long getApproverId() {
        return approverId;
    }

    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

    public Integer getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(Integer approvalResult) {
        this.approvalResult = approvalResult;
    }

    public void setaFADList(List<ApprovalFlowActionDetailReturnVO> aFADList) {
        this.aFADList = aFADList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getApprovalResultDesc() {
        return approvalResultDesc;
    }

    public void setApprovalResultDesc(String approvalResultDesc) {
        this.approvalResultDesc = approvalResultDesc;
    }

    public String getStatusRecomDesc() {
        return statusRecomDesc;
    }

    public void setStatusRecomDesc(String statusRecomDesc) {
        this.statusRecomDesc = statusRecomDesc;
    }


    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
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
        sb.append(", content=").append(content);
        sb.append(", flowId=").append(flowId);
        sb.append(", operationType=").append(operationType);
        sb.append(", dataId=").append(dataId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", startTime=").append(startTime);
        sb.append(", startOrgId=").append(startOrgId);
        sb.append(", starterId=").append(starterId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }


    public Long getLastActionId() {
        return lastActionId;
    }

    public void setLastActionId(Long lastActionId) {
        this.lastActionId = lastActionId;
    }

    public boolean isApproval() {
        return isApproval;
    }

    public void setApproval(boolean approval) {
        isApproval = approval;
    }
}