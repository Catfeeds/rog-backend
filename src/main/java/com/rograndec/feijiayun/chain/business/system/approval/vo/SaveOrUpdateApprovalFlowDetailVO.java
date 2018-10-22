package com.rograndec.feijiayun.chain.business.system.approval.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@ApiModel
public class SaveOrUpdateApprovalFlowDetailVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "审批流ID(新增时候不需要填入,修改需要)", required = true)
    private Long id;

    /**
     * 审批阶段
     */
    @NotNull(message = "审批阶段,不能为空")
    @NotBlank(message = "审批阶段,不能为空")
    @ApiModelProperty(value = "审批阶段", required = true)
    private String approvalStage;

    /**
     * 审批机构（0-总部；1-发起机构；2-指定审核机构）
     */
    @NotNull(message = "审批机构,不能为空")
    @ApiModelProperty(value = "审批机构（0-总部；1-发起机构；2-指定审核机构）", required = true)
    private Integer approvalOrg;

    /**
     * 审批机构ID
     */
    @ApiModelProperty(value = "审批机构ID(审批机构为2时,需要填入)", required = false)
    private Long orgId;

    /**
     * 角色ID
     */
    @NotNull(message = "角色ID,不能为空")
    @ApiModelProperty(value = "角色ID", required = true)
    private Long roleId;

    /**
     * 审批人Id
     */
    @NotNull(message = "审批人Id,不能为空")
    @ApiModelProperty(value = "审批人Id", required = true)
    private Long approverId;

    /**
     * 级别（可以用来显示审批顺序）
     */
    @NotNull(message = "级别,不能为空")
    @ApiModelProperty(value = "级别（可以用来显示审批顺序）", required = true)
    private Integer level;

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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getApproverId() {
        return approverId;
    }

    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}