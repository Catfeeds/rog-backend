package com.rograndec.feijiayun.chain.business.system.approval.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalStartOrg;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SaveOrUpdateApprovalFlowVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ApprovalFlow implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value = "审批流ID", required = true)
    private Long id;

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
     * 药店类型（0-总部；1-自营店；2-加盟店；）
     */
	@JsonIgnore
    private Integer chainType;

    /**
     * 流程名称
     */
    @ApiModelProperty(value = "流程名称", required = true)
    private String name;

    /**
     * 审批内容
     */
    @ApiModelProperty(
    		value = "审批内容（0101-供货单位；0102-员工信息；0103-门店信息；0201-商品信息；0202-价格调整；0301-采购计划；0302-购进退出；0401-盘点；0402-其他入库；0403-药品销毁；0501-配进退出）", 
    		required = true)
    private String content;
    
    /**
     * 发起机构（0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构）
     */
    @ApiModelProperty(value = "发起机构（0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构）", required = true)
    private Integer startOrg;
    

    /**
     * 指定发起机构ID
     */
    @ApiModelProperty(value = "指定发起机构ID", required = true)
    private Long startOrgId;
    
    /**
     * 指定发起机构名称
     */
    @ApiModelProperty(value = "指定发起机构名称", required = true)
    private String startOrgName;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）", required = true)
    private Integer status;
    
    /**
     * 系统默认标识（0-系统默认；1-非系统默认）
     */
    @JsonIgnore
    private Integer defaultFlag;

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
    
    private List<ApprovalFlowDetail> aFDList;


    public static ApprovalFlow getDefPriceAdjustApprovalFlow(UserVO userVO,Enterprise enterprise) throws Exception {

        ApprovalFlow approvalFlow = new ApprovalFlow();

        /**
         * 企业ID
         */
        approvalFlow.setEnterpriseId(enterprise.getId());

        /**
         * 上级企业ID
         */
        approvalFlow.setParentId(enterprise.getParentId());

        /**
         * 药店类型（0-总部；1-自营店；2-加盟店；）
         */
        approvalFlow.setChainType(enterprise.getChainType());

        /**
         * 流程名称
         */
        approvalFlow.setName("价格调整审批流");

        /**
         * 审批内容
         */

        approvalFlow.setContent("0202");

        /**
         * 发起机构（0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构）
         */

        approvalFlow.setStartOrg(ApprovalStartOrg.QBFD.getOrgFlag());


        /**
         * 状态（0-禁用；1-启用）
         */
        approvalFlow.setStatus(EnableStatus.ENABLE.getStatus());

        /**
         * 系统默认标识（0-系统默认；1-非系统默认）
         */
        approvalFlow.setDefaultFlag(SysType.SYSTEM.getCode());

        UserEnterpriseUtils.setUserCreateOrModify(approvalFlow,userVO,true);

        return approvalFlow;
    }

    /**
     * saas_approval_flow
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
     * 药店类型（0-总部；1-自营店；2-加盟店；）
     * @return chain_type 药店类型（0-总部；1-自营店；2-加盟店；）
     */
    public Integer getChainType() {
        return chainType;
    }

    /**
     * 药店类型（0-总部；1-自营店；2-加盟店；）
     * @param chainType 药店类型（0-总部；1-自营店；2-加盟店；）
     */
    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    /**
     * 流程名称
     * @return name 流程名称
     */
    public String getName() {
        return name;
    }

    /**
     * 流程名称
     * @param name 流程名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 审批内容
     * @return content 审批内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 审批内容
     * @param content 审批内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 发起机构（0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构）
     * @return start_org 发起机构（0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构）
     */
    public Integer getStartOrg() {
        return startOrg;
    }

    /**
     * 发起机构（0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构）
     * @param startOrg 发起机构（0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构）
     */
    public void setStartOrg(Integer startOrg) {
        this.startOrg = startOrg;
    }

    /**
     * 指定发起机构ID
     * @return start_org_id 指定发起机构ID
     */
    public Long getStartOrgId() {
        return startOrgId;
    }

    /**
     * 指定发起机构ID
     * @param startOrgId 指定发起机构ID
     */
    public void setStartOrgId(Long startOrgId) {
        this.startOrgId = startOrgId;
    }
    
    public String getStartOrgName() {
		return startOrgName;
	}

	public void setStartOrgName(String startOrgName) {
		this.startOrgName = startOrgName;
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
     * 系统默认标识（0-系统默认；1-非系统默认）
     * @return defaultFlag 系统默认标识（0-系统默认；1-非系统默认）
     */
    public Integer getDefaultFlag() {
		return defaultFlag;
	}

    /**
     * 系统默认标识（0-系统默认；1-非系统默认）
     * @param defaultFlag 系统默认标识（0-系统默认；1-非系统默认）
     */
	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
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
     * 审批流明细集合
     */
    public List<ApprovalFlowDetail> getaFDList() {
		return aFDList;
	}

    /**
     * 审批流明细集合
     * @param aFDList 审批流明细集合
     */
	public void setaFDList(List<ApprovalFlowDetail> aFDList) {
		this.aFDList = aFDList;
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
        sb.append(", parentId=").append(parentId);
        sb.append(", chainType=").append(chainType);
        sb.append(", name=").append(name);
        sb.append(", content=").append(content);
        sb.append(", startOrg=").append(startOrg);
        sb.append(", startOrgId=").append(startOrgId);
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

    public static ApprovalFlow getApprovalFlow4approvalFlowVO(SaveOrUpdateApprovalFlowVO approvalFlowVO
            , Enterprise userEnterprise
            , Enterprise startEnterprise
            , UserVO user,boolean isSave) throws Exception {

        ApprovalFlow approvalFlow = new ApprovalFlow();
        approvalFlow.setEnterpriseId(userEnterprise.getId());
        approvalFlow.setParentId(userEnterprise.getParentId());
        approvalFlow.setChainType(userEnterprise.getChainType());
        approvalFlow.setName(approvalFlowVO.getName());
        approvalFlow.setContent(approvalFlowVO.getContent());
        approvalFlow.setStartOrg(approvalFlowVO.getStartOrg());
        approvalFlow.setStartOrgId(startEnterprise.getId());
        approvalFlow.setStartOrgName(startEnterprise.getName());
        approvalFlow.setStatus(approvalFlowVO.getStatus());
        approvalFlow.setDefaultFlag(SysType.CUSTOMIZE.getCode());
        UserEnterpriseUtils.setUserCreateOrModify(approvalFlow,user,isSave);
        return approvalFlow;
    }

    public static ApprovalFlow getApprovalFlowVO4approvalFlow(SaveOrUpdateApprovalFlowVO approvalFlowVO
            , Enterprise userEnterprise
            , Enterprise startEnterprise
            , UserVO user,boolean isSave) throws Exception {

        ApprovalFlow approvalFlow = new ApprovalFlow();
        approvalFlow.setId(approvalFlowVO.getId());
        approvalFlow.setEnterpriseId(userEnterprise.getId());
        approvalFlow.setParentId(userEnterprise.getParentId());
        approvalFlow.setChainType(userEnterprise.getChainType());
        approvalFlow.setName(approvalFlowVO.getName());
        approvalFlow.setContent(approvalFlowVO.getContent());
        approvalFlow.setStartOrg(approvalFlowVO.getStartOrg());
        approvalFlow.setStartOrgId(startEnterprise.getId());
        approvalFlow.setStartOrgName(startEnterprise.getName());
        approvalFlow.setStatus(approvalFlowVO.getStatus());
        approvalFlow.setDefaultFlag(SysType.CUSTOMIZE.getCode());
        UserEnterpriseUtils.setUserCreateOrModify(approvalFlow,user,isSave);
        return approvalFlow;
    }
}