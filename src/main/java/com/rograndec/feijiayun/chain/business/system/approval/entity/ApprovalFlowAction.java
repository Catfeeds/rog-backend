package com.rograndec.feijiayun.chain.business.system.approval.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SubmitApprovalFlowVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApprovalFlowAction implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value = "审批流操作基表ID", required = true)
    private Long id;

    /**
     * 企业（总部）ID
     */
	@JsonIgnore
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
	@JsonIgnore
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
     * 复合状态（-2:取消 -1:审核中 0-待审核；1-已完成；2-审核被驳回
     */
	@ApiModelProperty(value = "复合状态（-2:取消 -1:审核中 0-待审核；1-已完成；2-审核被驳回）", required = true)
    private Long statusRecom;

    /**
     * 当前审批人
     */
	private Long currentApprover;

    /**
     * 当前审批人name
     */
	private String currentApproverName;

    /**
     * 当前审批阶段
     */
	private String currentApprovalStage;

    /**
     * 前一个审批人
     */
	private Long previousApprover;

    /**
     * 前一个审批人name
     */
    private String previousApproverName;

    /**
     * 前一个审批阶段
     */
	private String previousApprovalStage;

    /**
     * 下一个审批人
     */
	private Long nextApprover;

    /**
     * 下个审批人name
     */
	private String nextApproverName;

    /**
     * 下一个审批阶段
     */
	private String nextApprovalStage;

    /**
     * 审批流是否已经完结(0:未完结,1:已完结)
     */
	private Integer flowEnd;

    /**
     * 当前审批时间
     */
	private Date currentApprovalDate;
    /**
     * 上一个审批时间
     */
	private Date previousApprovalDate;

    /**
     * 下一个审批时间
     */
	private Date nextApprovalDate;

    /**
     * 当前审批角色
     */
    private Long currentApprovalRole;
    /**
     * 上一个审批角色
     */
    private Long previousApprovalRole;

    /**
     * 下一个审批角色
     */
    private Long nextApprovalRole;



    @ApiModelProperty(value = "审批流操作明细数据集合", required = true)
    private List<ApprovalFlowActionDetail> aFADList;

    /**
     * saas_approval_flow_action
     */
    private static final long serialVersionUID = 1L;


    public static ApprovalFlowAction getApprovalFlowAction4SubmitApprovalFlow( Long enterpriseId,SubmitApprovalFlowVO submitApprovalFlowVO,Long flowId){

        ApprovalFlowAction approvalFlowAction = new ApprovalFlowAction();
     /*   approvalFlowAction.setEnterpriseId(submitApprovalFlowVO.getStartOrgId());*/
       /* approvalFlowAction.setEnterpriseId(submitApprovalFlowVO.getHeadquartersEnterpriseId());*/
        approvalFlowAction.setEnterpriseId(enterpriseId);
        approvalFlowAction.setContent(submitApprovalFlowVO.getContent());
        approvalFlowAction.setFlowId(flowId);
        approvalFlowAction.setOperationType(0);
        approvalFlowAction.setDataId(submitApprovalFlowVO.getDataId());
        approvalFlowAction.setCode(submitApprovalFlowVO.getCode());
        approvalFlowAction.setName(submitApprovalFlowVO.getName());
        approvalFlowAction.setStartTime(new Date());
        approvalFlowAction.setStartOrgId(submitApprovalFlowVO.getStartOrgId());
        approvalFlowAction.setStartOrgName(submitApprovalFlowVO.getStartOrgName());
        approvalFlowAction.setStarterId(submitApprovalFlowVO.getStarterId());
        approvalFlowAction.setStarterName(submitApprovalFlowVO.getStarterName());
        approvalFlowAction.setStatusRecom(Long.parseLong(String.valueOf(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue())));

        return approvalFlowAction;
    }

    public String getCurrentApproverName() {
        return currentApproverName;
    }

    public void setCurrentApproverName(String currentApproverName) {
        this.currentApproverName = currentApproverName;
    }

    public String getPreviousApproverName() {
        return previousApproverName;
    }

    public void setPreviousApproverName(String previousApproverName) {
        this.previousApproverName = previousApproverName;
    }

    public String getNextApproverName() {
        return nextApproverName;
    }

    public void setNextApproverName(String nextApproverName) {
        this.nextApproverName = nextApproverName;
    }

    /**
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

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
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

	public List<ApprovalFlowActionDetail> getaFADList() {
		return aFADList;
	}

	public void setaFADList(List<ApprovalFlowActionDetail> aFADList) {
		this.aFADList = aFADList;
	}


    public Long getCurrentApprover() {
        return currentApprover;
    }

    public void setCurrentApprover(Long currentApprover) {
        this.currentApprover = currentApprover;
    }

    public String getCurrentApprovalStage() {
        return currentApprovalStage;
    }

    public void setCurrentApprovalStage(String currentApprovalStage) {
        this.currentApprovalStage = currentApprovalStage;
    }

    public Long getPreviousApprover() {
        return previousApprover;
    }

    public void setPreviousApprover(Long previousApprover) {
        this.previousApprover = previousApprover;
    }

    public String getPreviousApprovalStage() {
        return previousApprovalStage;
    }

    public void setPreviousApprovalStage(String previousApprovalStage) {
        this.previousApprovalStage = previousApprovalStage;
    }

    public Long getNextApprover() {
        return nextApprover;
    }

    public void setNextApprover(Long nextApprover) {
        this.nextApprover = nextApprover;
    }

    public String getNextApprovalStage() {
        return nextApprovalStage;
    }

    public void setNextApprovalStage(String nextApprovalStage) {
        this.nextApprovalStage = nextApprovalStage;
    }

    public Integer getFlowEnd() {
        return flowEnd;
    }

    public void setFlowEnd(Integer flowEnd) {
        this.flowEnd = flowEnd;
    }

    public Date getCurrentApprovalDate() {
        return currentApprovalDate;
    }

    public void setCurrentApprovalDate(Date currentApprovalDate) {
        this.currentApprovalDate = currentApprovalDate;
    }

    public Date getPreviousApprovalDate() {
        return previousApprovalDate;
    }

    public void setPreviousApprovalDate(Date previousApprovalDate) {
        this.previousApprovalDate = previousApprovalDate;
    }

    public Date getNextApprovalDate() {
        return nextApprovalDate;
    }

    public void setNextApprovalDate(Date nextApprovalDate) {
        this.nextApprovalDate = nextApprovalDate;
    }

    public static List<Long> getIds(List<ApprovalFlowAction> approvalFlowActions){

        List<Long> ids = new ArrayList<>();

        for(ApprovalFlowAction approvalFlowAction : approvalFlowActions){
            ids.add(approvalFlowAction.getId());
        }

        return ids;

    }

    public static List<Long> getFlowIds(List<ApprovalFlowAction> approvalFlowActions){

        List<Long> ids = new ArrayList<>();

        for(ApprovalFlowAction approvalFlowAction : approvalFlowActions){
            ids.add(approvalFlowAction.getFlowId());
        }

        return ids;

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

    public Long getCurrentApprovalRole() {
        return currentApprovalRole;
    }

    public void setCurrentApprovalRole(Long currentApprovalRole) {
        this.currentApprovalRole = currentApprovalRole;
    }

    public Long getPreviousApprovalRole() {
        return previousApprovalRole;
    }

    public void setPreviousApprovalRole(Long previousApprovalRole) {
        this.previousApprovalRole = previousApprovalRole;
    }

    public Long getNextApprovalRole() {
        return nextApprovalRole;
    }

    public void setNextApprovalRole(Long nextApprovalRole) {
        this.nextApprovalRole = nextApprovalRole;
    }
}