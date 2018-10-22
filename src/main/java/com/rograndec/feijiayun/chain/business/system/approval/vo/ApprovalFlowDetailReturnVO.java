package com.rograndec.feijiayun.chain.business.system.approval.vo;

import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowDetail;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApprovalFlowDetailReturnVO implements Serializable {
    /**  
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	 */  
	private static final long serialVersionUID = 8892259098088296256L;

	/**
     * 主键ID
     */
	@ApiModelProperty(value = "审批流明细ID", required = true)
    private Long id;

    /**
     * 审批流ID
     */
    @ApiModelProperty(value = "审批流ID", required = true)
    private Long flowId;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID", required = true)
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID", required = true)
    private Long parentId;

    /**
     * 审批阶段
     */
    @ApiModelProperty(value = "审批阶段", required = true)
    private String approvalStage;

    /**
     * 审批机构（0-总部；1-发起机构；2-指定审核机构）
     */
    @ApiModelProperty(value = "审批机构（0-总部；1-发起机构；2-指定审核机构）", required = true)
    private Integer approvalOrg;

    /**
     * 审批机构ID
     */
    @ApiModelProperty(value = "审批机构ID", required = true)
    private Long orgId;

    /**
     * 审批机构名称
     */
    @ApiModelProperty(value = "审批机构名称", required = true)
    private String orgName;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "审批角色ID", required = true)
    private Long roleId;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "审批角色名称", required = true)
    private String roleName;

    /**
     * 审批人ID
     */
    @ApiModelProperty(value = "审批人ID", required = true)
    private Long approverId;

    /**
     * 审批人编码
     */
    @ApiModelProperty(value = "审批人编码", required = true)
    private String approverCode;

    /**
     * 审批人名称
     */
    @ApiModelProperty(value = "审批人名称", required = true)
    private String approverName;

    /**
     * 级别（可以用来显示审批顺序）
     */
    @ApiModelProperty(value = "级别（可以用来显示审批顺序）", required = true)
    private Integer level;

    /**
     * 最高级别
     */
    @ApiModelProperty(value = "最高级别", required = true)
    private Integer highestLevel;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）", required = true)
    private Integer status;

    /**
     * 审批流程节点描述
     */
    @ApiModelProperty(value = "审批流程节点描述", required = true)
    private String approvalDesc;


    public static List<ApprovalFlowDetailReturnVO> getApprovalFlowDetailsReturnVO4ApprovalFlowDetails(List<ApprovalFlowDetail> approvalFlowDetails){

        List<ApprovalFlowDetailReturnVO> approvalFlowDetailReturnVOs = new ArrayList<>();

       for(ApprovalFlowDetail approvalFlowDetail : approvalFlowDetails){
           ApprovalFlowDetailReturnVO approvalFlowDetailReturnVO = getApprovalFlowDetailReturnVO4ApprovalFlowDetail(approvalFlowDetail);
           approvalFlowDetailReturnVOs.add(approvalFlowDetailReturnVO);
       }

        return approvalFlowDetailReturnVOs;
    }

    public static ApprovalFlowDetailReturnVO getApprovalFlowDetailReturnVO4ApprovalFlowDetail(ApprovalFlowDetail approvalFlowDetail){

        ApprovalFlowDetailReturnVO approvalFlowDetailReturnVO = new ApprovalFlowDetailReturnVO();
        approvalFlowDetailReturnVO.setApprovalOrg(approvalFlowDetail.getApprovalOrg());
        approvalFlowDetailReturnVO.setApprovalStage(approvalFlowDetail.getApprovalStage());
        approvalFlowDetailReturnVO.setApproverCode(approvalFlowDetail.getApproverCode());
        approvalFlowDetailReturnVO.setApproverId(approvalFlowDetail.getApproverId());
        approvalFlowDetailReturnVO.setApproverName(approvalFlowDetail.getApproverName());
        approvalFlowDetailReturnVO.setEnterpriseId(approvalFlowDetail.getEnterpriseId());
        approvalFlowDetailReturnVO.setFlowId(approvalFlowDetail.getFlowId());
        approvalFlowDetailReturnVO.setHighestLevel(approvalFlowDetail.getHighestLevel());
        approvalFlowDetailReturnVO.setLevel(approvalFlowDetail.getLevel());
        approvalFlowDetailReturnVO.setOrgId(approvalFlowDetail.getOrgId());
        approvalFlowDetailReturnVO.setOrgName(approvalFlowDetail.getOrgName());
        approvalFlowDetailReturnVO.setStatus(approvalFlowDetail.getStatus());
        approvalFlowDetailReturnVO.setRoleId(approvalFlowDetail.getRoleId());
        approvalFlowDetailReturnVO.setRoleName(approvalFlowDetail.getRoleName());
        approvalFlowDetailReturnVO.setId(approvalFlowDetail.getId());
        approvalFlowDetailReturnVO.setApprovalDesc(generateApprovalDesc(approvalFlowDetailReturnVO));

        return approvalFlowDetailReturnVO;
    }

    public static String generateApprovalDesc(ApprovalFlowDetailReturnVO approvalFlowDetailReturnVO){

        StringBuffer stringBuffer = new StringBuffer();
        if(!StringUtils.isEmpty(approvalFlowDetailReturnVO.getApproverName())){
            stringBuffer.append("由").append(approvalFlowDetailReturnVO.getApproverName()).append("审核");
        }else if(!StringUtils.isEmpty(approvalFlowDetailReturnVO.getRoleName())){
            stringBuffer.append("由").append(approvalFlowDetailReturnVO.getRoleName()).append("审核");
        }


        return stringBuffer.toString();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getApprovalStage() {
        return approvalStage;
    }

    public void setApprovalStage(String approvalStage) {
        this.approvalStage = approvalStage;
    }

    public Integer getApprovalOrg() {
        return approvalOrg;
    }

    public void setApprovalOrg(Integer approvalOrg) {
        this.approvalOrg = approvalOrg;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getApproverId() {
        return approverId;
    }

    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    public String getApproverCode() {
        return approverCode;
    }

    public void setApproverCode(String approverCode) {
        this.approverCode = approverCode;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getHighestLevel() {
        return highestLevel;
    }

    public void setHighestLevel(Integer highestLevel) {
        this.highestLevel = highestLevel;
    }

    public Integer getStatus() {
        return status;
    }

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
        sb.append(", flowId=").append(flowId);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", approvalStage=").append(approvalStage);
        sb.append(", approvalOrg=").append(approvalOrg);
        sb.append(", orgId=").append(orgId);
        sb.append(", orgName=").append(orgName);
        sb.append(", roleId=").append(roleId);
        sb.append(", roleName=").append(roleName);
        sb.append(", approverId=").append(approverId);
        sb.append(", approverCode=").append(approverCode);
        sb.append(", approverName=").append(approverName);
        sb.append(", level=").append(level);
        sb.append(", highestLevel=").append(highestLevel);
        sb.append(", status=").append(status);
        return sb.toString();
    }


}