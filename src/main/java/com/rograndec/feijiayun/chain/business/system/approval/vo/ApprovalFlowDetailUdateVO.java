package com.rograndec.feijiayun.chain.business.system.approval.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlow;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.entity.Position;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysRole;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApprovalFlowDetailUdateVO implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value = "审批流明细ID", required = true)
    private Long id;

    /**
     * 审批流ID
     */
	@JsonIgnore
    private Long flowId;

    /**
     * 企业ID
     */
    @JsonIgnore
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @JsonIgnore
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
    private String roleId;

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
    @JsonIgnore
    private String approverCode;

    /**
     * 审批人名称
     */
    @ApiModelProperty(value = "审批人名称", required = true)
    private String approverName;

    /**
     * 级别（可以用来显示审批顺序）
     */
    @JsonIgnore
    private Integer level;

    /**
     * 最高级别
     */
    @JsonIgnore
    private Integer highestLevel;

    /**
     * 状态（0-禁用；1-启用）
     */
    @JsonIgnore
    private Integer status;

    /**
     * 备注
     */
    @JsonIgnore
    private String remark;

    /**
     * 创建人ID
     */
    @JsonIgnore
    private Long createrId;

    /**
     * 创建人编码
     */
    @JsonIgnore
    private String createrCode;

    /**
     * 创建人名称
     */
    @JsonIgnore
    private String createrName;

    /**
     * 创建时间
     */
    @JsonIgnore
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @JsonIgnore
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @JsonIgnore
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @JsonIgnore
    private String modifierName;

    /**
     * 更新时间
     */
    @JsonIgnore
    private Date updateTime;

    /**
     * saas_approval_flow_detail
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
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
     * 审批机构（0-总部；1-发起机构；2-指定审核机构）
     * @return approval_org 审批机构（0-总部；1-发起机构；2-指定审核机构）
     */
    public Integer getApprovalOrg() {
        return approvalOrg;
    }

    /**
     * 审批机构（0-总部；1-发起机构；2-指定审核机构）
     * @param approvalOrg 审批机构（0-总部；1-发起机构；2-指定审核机构）
     */
    public void setApprovalOrg(Integer approvalOrg) {
        this.approvalOrg = approvalOrg;
    }

    /**
     * 审批机构ID
     * @return org_id 审批机构ID
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * 审批机构ID
     * @param orgId 审批机构ID
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * 审批机构名称
     * @return org_name 审批机构名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 审批机构名称
     * @param orgName 审批机构名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    /**
     * 角色ID
     * @return role_id 角色ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 角色ID
     * @param roleId 角色ID
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 角色名称
     * @return role_name 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 角色名称
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
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

    /**
     * 审批人编码
     * @return approver_code 审批人编码
     */
    public String getApproverCode() {
        return approverCode;
    }

    /**
     * 审批人编码
     * @param approverCode 审批人编码
     */
    public void setApproverCode(String approverCode) {
        this.approverCode = approverCode == null ? null : approverCode.trim();
    }

    /**
     * 审批人名称
     * @return approver_name 审批人名称
     */
    public String getApproverName() {
        return approverName;
    }

    /**
     * 审批人名称
     * @param approverName 审批人名称
     */
    public void setApproverName(String approverName) {
        this.approverName = approverName == null ? null : approverName.trim();
    }

    /**
     * 级别（可以用来显示审批顺序）
     * @return level 级别（可以用来显示审批顺序）
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 级别（可以用来显示审批顺序）
     * @param level 级别（可以用来显示审批顺序）
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 最高级别
     * @return highest_level 最高级别
     */
    public Integer getHighestLevel() {
        return highestLevel;
    }

    /**
     * 最高级别
     * @param highestLevel 最高级别
     */
    public void setHighestLevel(Integer highestLevel) {
        this.highestLevel = highestLevel;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @return status 状态（0-禁用；1-启用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @param status 状态（0-禁用；1-启用）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static List<ApprovalFlowDetailUdateVO> getApprovalFlow4approvalFlowDetailVO(
            List<SaveOrUpdateApprovalFlowDetailVO> approvalFlowDetailDTOS
            , Enterprise userEnterprise
            , List<Enterprise> detailStartEnterprise
            , UserVO user
            , ApprovalFlow approvalFlow
            , List<Position> positions
            , List<SysRole> roles
            , List<User> approvalFlowUsers,boolean isSave) throws Exception {

        List<ApprovalFlowDetailUdateVO> resultApprovalFlowDetails = new ArrayList<>();

        int level = 0 ;
        for(SaveOrUpdateApprovalFlowDetailVO approvalFlowDetailVO : approvalFlowDetailDTOS){
            ApprovalFlowDetailUdateVO approvalFlowDetail = new ApprovalFlowDetailUdateVO();
            level++;
            setApprovalFlowDetails(approvalFlowDetailVO
                    ,approvalFlowUsers
                    ,approvalFlowDetail
                    ,approvalFlow
                    ,userEnterprise
                    ,detailStartEnterprise
                    ,positions
                    ,roles
                    ,user
                    ,approvalFlowDetailDTOS
                    ,resultApprovalFlowDetails
                    ,level,isSave);
        }

        return resultApprovalFlowDetails;

    }


    public static List<ApprovalFlowDetailUdateVO> getApprovalFlow4approvalFlowDetail(
            List<SaveOrUpdateApprovalFlowDetailVO> approvalFlowDetailDTOS
            , Enterprise userEnterprise
            , List<Enterprise> detailStartEnterprise
            , UserVO user
            , ApprovalFlow approvalFlow
            , List<Position> positions
            , List<SysRole> roles
            , List<User> approvalFlowUsers,boolean isSave) throws Exception {

        List<ApprovalFlowDetailUdateVO> resultApprovalFlowDetails = new ArrayList<>();

        int level = 0 ;
        for(SaveOrUpdateApprovalFlowDetailVO approvalFlowDetailVO : approvalFlowDetailDTOS){
                    level++;
                    ApprovalFlowDetailUdateVO approvalFlowDetail = new ApprovalFlowDetailUdateVO();
                    setApprovalFlowDetails(approvalFlowDetailVO
                            ,approvalFlowUsers
                            ,approvalFlowDetail
                            ,approvalFlow
                            ,userEnterprise
                            ,detailStartEnterprise
                            ,positions
                            ,roles
                            ,user
                            ,approvalFlowDetailDTOS
                            ,resultApprovalFlowDetails
                            ,level,isSave);

        }

        return resultApprovalFlowDetails;

    }

    private static void setApprovalFlowDetails(SaveOrUpdateApprovalFlowDetailVO approvalFlowDetailPojo
            ,List<User> approvalFlowUsers
            ,ApprovalFlowDetailUdateVO approvalFlowDetail
            ,ApprovalFlow approvalFlow
            , Enterprise userEnterprise
            , List<Enterprise> detailStartEnterprise
            , List<Position> positions
            , List<SysRole> roles
            , UserVO user
            , List<SaveOrUpdateApprovalFlowDetailVO> approvalFlowDetailDTOS
            , List<ApprovalFlowDetailUdateVO> resultApprovalFlowDetails
            ,int level,boolean isSave) throws Exception {
        approvalFlowDetail.setId(approvalFlowDetailPojo.getId());
        approvalFlowDetail.setFlowId(approvalFlow.getId());
        approvalFlowDetail.setEnterpriseId(userEnterprise.getId());
        approvalFlowDetail.setParentId(userEnterprise.getParentId());
        approvalFlowDetail.setApprovalStage(approvalFlowDetailPojo.getApprovalStage());
        approvalFlowDetail.setApprovalOrg(approvalFlowDetailPojo.getApprovalOrg());
        for(Enterprise de : detailStartEnterprise){
            if(de.getId().equals(approvalFlowDetailPojo.getOrgId())){
                approvalFlowDetail.setOrgId(de.getId());
                approvalFlowDetail.setOrgName(de.getName());
            }
        }


        for(SysRole role : roles){
            if(role.getId().equals(approvalFlowDetailPojo.getRoleId())){
                approvalFlowDetail.setRoleId(role.getId().toString());
                approvalFlowDetail.setRoleName(role.getName());
            }

        }

        for(User approvalUser : approvalFlowUsers){
            if(approvalUser.getId().equals(approvalFlowDetailPojo.getApproverId())){
                approvalFlowDetail.setApproverId(approvalUser.getId());
                approvalFlowDetail.setApproverName(approvalUser.getName());
                approvalFlowDetail.setApproverCode(approvalUser.getCreaterCode());
            }
        }

        approvalFlowDetail.setLevel(level);
        approvalFlowDetail.setHighestLevel(approvalFlowDetailDTOS.size());
        approvalFlowDetail.setStatus(approvalFlow.getStatus());
        UserEnterpriseUtils.setUserCreateOrModify(approvalFlowDetail,user,isSave);
        resultApprovalFlowDetails.add(approvalFlowDetail);
    }




}