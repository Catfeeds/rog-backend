package com.rograndec.feijiayun.chain.business.system.approval.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@ApiModel
public class SaveOrUpdateApprovalFlowVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "审批流ID(新增时候不需要填入,修改需要)", required = true)
    private Long id;
    /**
     * 流程名称
     */
    @ApiModelProperty(value = "流程名称", required = true)
    @NotNull(message = "流程名称,不能为空")
    @NotBlank(message = "流程名称,不能为空")
    private String name;

    /**
     * 审批内容
     */
    @NotNull(message = "审批内容,不能为空")
    @NotBlank(message = "审批内容,不能为空")
    @ApiModelProperty(
            value = "审批内容",
            required = true)
    private String content;

    /**
     * 发起机构（0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构）
     */
    @NotNull(message = "发起机构,不能为空")
    @ApiModelProperty(value = "发起机构（0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构）", required = true)
    private Integer startOrg;

    /**
     * 指定发起机构ID
     */
    @ApiModelProperty(value = "指定发起机构ID(选择发起机构为4 的时候需要填入)", required = false)
    private Long startOrgId;

    /**
     * 状态（0-禁用；1-启用）
     */
    @NotNull(message = "状态,不能为空")
    @ApiModelProperty(value = "状态（0-禁用；1-启用）", required = true)
    private Integer status = 1;

    /**
     *
     */
    @ApiModelProperty(value = "审批明细删除id的集合(新增时不需要填,在修改时如果有删除审批明细,则需要填入)", required = true)
    private List<Long> deleteApprovalFlowIds;

    /**
     * 审批明细
     */
    @NotNull(message = "审批明细,不能为空")
    @Size(min = 1,message = "审批明细,数量不能为0")
    @ApiModelProperty(value = "审批明细集合", required = true)
    private List<SaveOrUpdateApprovalFlowDetailVO> approvalFlowDetailDTOS;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStartOrg() {
        return startOrg;
    }

    public void setStartOrg(Integer startOrg) {
        this.startOrg = startOrg;
    }

    public Long getStartOrgId() {
        return startOrgId;
    }

    public void setStartOrgId(Long startOrgId) {
        this.startOrgId = startOrgId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Long> getDeleteApprovalFlowIds() {
        return deleteApprovalFlowIds;
    }

    public void setDeleteApprovalFlowIds(List<Long> deleteApprovalFlowIds) {
        this.deleteApprovalFlowIds = deleteApprovalFlowIds;
    }

    public List<SaveOrUpdateApprovalFlowDetailVO> getApprovalFlowDetailDTOS() {
        return approvalFlowDetailDTOS;
    }

    public void setApprovalFlowDetailDTOS(List<SaveOrUpdateApprovalFlowDetailVO> approvalFlowDetailDTOS) {
        this.approvalFlowDetailDTOS = approvalFlowDetailDTOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}