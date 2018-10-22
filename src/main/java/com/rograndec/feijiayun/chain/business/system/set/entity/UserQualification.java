package com.rograndec.feijiayun.chain.business.system.set.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserQualification implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value="主键ID",required=true)
    private Long id;

    /**
     * 企业（总部）ID
     */
	@ApiModelProperty(value="企业（总部）ID",required=true)
    private Long enterpriseId;

    /**
     * 适用机构（0-全部；1-总部；2-分店）
     */
	@ApiModelProperty(value="适用机构（0-全部；1-总部；2-分店）",required=true)
    private Integer suitableUnit;

    /**
     * 适用岗位ID
     */
	@ApiModelProperty(value="适用岗位ID",required=true)
    private String positionIds;

	/**
     * 0：用户自定义；1-系统默认
     */
	@ApiModelProperty(value = "0：用户自定义；1-系统默认")
    private Integer sysType;
	
	/**
     * 资质类型是否必选（0-可选；1-必选）
     */
	@ApiModelProperty(value = "资质类型是否必选（0-可选；1-必选）")
    private Integer typeMust;
	
    /**
     * 编码
     */
	@ApiModelProperty(value="编码",required=true)
    private String code;

    /**
     * 名称
     */
	@ApiModelProperty(value="名称",required=true)
    private String name;

    /**
     * 控制类型（0-质量控制；1-仅提示）
     */
	@ApiModelProperty(value="控制类型（0-质量控制；1-仅提示）",required=true)
    private Integer controlType;

    /**
     * 等级是否必填（0-非必填；1-必填）
     */
	@ApiModelProperty(value="等级是否必填（0-非必填；1-必填）",required=true)
    private Integer gradeMust;

    /**
     * 资格证书号是否必填（0-非必填；1-必填）
     */
	@ApiModelProperty(value="资格证书号是否必填（0-非必填；1-必填）",required=true)
    private Integer codeMust;

    /**
     * 注册证书号是否必填（0-非必填；1-必填）
     */
	@ApiModelProperty(value="注册证书号是否必填（0-非必填；1-必填）",required=true)
    private Integer registerCodeMust;

    /**
     * 适用地区是否必填（0-非必填；1-必填）
     */
	@ApiModelProperty(value="适用地区是否必填（0-非必填；1-必填）",required=true)
    private Integer regionMust;

    /**
     * 适用类别是否必填（0-非必填；1-必填）
     */
	@ApiModelProperty(value="适用类别是否必填（0-非必填；1-必填）",required=true)
    private Integer categoryMust;

    /**
     * 适用范围是否必填（0-只读；1-必填）
     */
	@ApiModelProperty(value="适用范围是否必填（0-只读；1-必填）",required=true)
    private Integer rangeMust;

    /**
     * 附件是否必填（0-非必填；1-必填）
     */
	@ApiModelProperty(value="附件是否必填（0-非必填；1-必填）",required=true)
    private Integer fileMust;

    /**
     * 状态（0-禁用；1-启用）
     */
	@ApiModelProperty(value="状态（0-禁用；1-启用）",required=true)
    private Integer status;

    /**
     * 备注
     */
	@ApiModelProperty(value="备注",required=true)
    private String remark;

    /**
     * 创建人ID
     */
	@ApiModelProperty(value="创建人ID",required=true)
    private Long createrId;

    /**
     * 创建人编码
     */
	@ApiModelProperty(value="创建人编码",required=true)
    private String createrCode;

    /**
     * 创建人名称
     */
	@ApiModelProperty(value="创建人名称",required=true)
    private String createrName;

    /**
     * 创建时间
     */
	@ApiModelProperty(value="创建时间",required=true)
    private Date createTime;

    /**
     * 最后修改人ID
     */
	@ApiModelProperty(value="最后修改人ID",required=true)
    private Long modifierId;

    /**
     * 最后修改人编码
     */
	@ApiModelProperty(value="最后修改人编码",required=true)
    private String modifierCode;

    /**
     * 最后修改人名称
     */
	@ApiModelProperty(value="最后修改人名称",required=true)
    private String modifierName;

    /**
     * 更新时间
     */
	@ApiModelProperty(value="更新时间",required=true)
    private Date updateTime;
    
    /**
     * 新增企业类型
     */
	@ApiModelProperty(value="新增企业类型",required=true)
    private Integer chainType;

    /**
     * 适用岗位名称
     */
    @ApiModelProperty(value="适用岗位名称",required=true)
    private List<String> positionName;

    /**
     * 是否可删除
     */
    private Boolean deleteFlag = true;

    /**
     * 是否可修改
     */
    private Boolean updateFlag = true;
    /**
     * saas_user_qualification
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
     * 适用机构（0-全部；1-总部；2-分店）
     * @return suitable_unit 适用机构（0-全部；1-总部；2-分店）
     */
    public Integer getSuitableUnit() {
        return suitableUnit;
    }

    /**
     * 适用机构（0-全部；1-总部；2-分店）
     * @param suitableUnit 适用机构（0-全部；1-总部；2-分店）
     */
    public void setSuitableUnit(Integer suitableUnit) {
        this.suitableUnit = suitableUnit;
    }

    public String getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(String positionIds) {
        this.positionIds = positionIds;
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
     * 控制类型（0-质量控制；1-仅提示）
     * @return control_type 控制类型（0-质量控制；1-仅提示）
     */
    public Integer getControlType() {
        return controlType;
    }

    /**
     * 控制类型（0-质量控制；1-仅提示）
     * @param controlType 控制类型（0-质量控制；1-仅提示）
     */
    public void setControlType(Integer controlType) {
        this.controlType = controlType;
    }

    /**
     * 等级是否必填（0-非必填；1-必填）
     * @return grade_must 等级是否必填（0-非必填；1-必填）
     */
    public Integer getGradeMust() {
        return gradeMust;
    }

    /**
     * 等级是否必填（0-非必填；1-必填）
     * @param gradeMust 等级是否必填（0-非必填；1-必填）
     */
    public void setGradeMust(Integer gradeMust) {
        this.gradeMust = gradeMust;
    }

    /**
     * 资格证书号是否必填（0-非必填；1-必填）
     * @return code_must 资格证书号是否必填（0-非必填；1-必填）
     */
    public Integer getCodeMust() {
        return codeMust;
    }

    /**
     * 资格证书号是否必填（0-非必填；1-必填）
     * @param codeMust 资格证书号是否必填（0-非必填；1-必填）
     */
    public void setCodeMust(Integer codeMust) {
        this.codeMust = codeMust;
    }

    /**
     * 注册证书号是否必填（0-非必填；1-必填）
     * @return register_code_must 注册证书号是否必填（0-非必填；1-必填）
     */
    public Integer getRegisterCodeMust() {
        return registerCodeMust;
    }

    /**
     * 注册证书号是否必填（0-非必填；1-必填）
     * @param registerCodeMust 注册证书号是否必填（0-非必填；1-必填）
     */
    public void setRegisterCodeMust(Integer registerCodeMust) {
        this.registerCodeMust = registerCodeMust;
    }

    /**
     * 适用地区是否必填（0-非必填；1-必填）
     * @return region_must 适用地区是否必填（0-非必填；1-必填）
     */
    public Integer getRegionMust() {
        return regionMust;
    }

    /**
     * 适用地区是否必填（0-非必填；1-必填）
     * @param regionMust 适用地区是否必填（0-非必填；1-必填）
     */
    public void setRegionMust(Integer regionMust) {
        this.regionMust = regionMust;
    }

    /**
     * 适用类别是否必填（0-非必填；1-必填）
     * @return category_must 适用类别是否必填（0-非必填；1-必填）
     */
    public Integer getCategoryMust() {
        return categoryMust;
    }

    /**
     * 适用类别是否必填（0-非必填；1-必填）
     * @param categoryMust 适用类别是否必填（0-非必填；1-必填）
     */
    public void setCategoryMust(Integer categoryMust) {
        this.categoryMust = categoryMust;
    }

    /**
     * 适用范围是否必填（0-只读；1-必填）
     * @return range_must 适用范围是否必填（0-只读；1-必填）
     */
    public Integer getRangeMust() {
        return rangeMust;
    }

    /**
     * 适用范围是否必填（0-只读；1-必填）
     * @param rangeMust 适用范围是否必填（0-只读；1-必填）
     */
    public void setRangeMust(Integer rangeMust) {
        this.rangeMust = rangeMust;
    }

    /**
     * 附件是否必填（0-非必填；1-必填）
     * @return file_must 附件是否必填（0-非必填；1-必填）
     */
    public Integer getFileMust() {
        return fileMust;
    }

    /**
     * 附件是否必填（0-非必填；1-必填）
     * @param fileMust 附件是否必填（0-非必填；1-必填）
     */
    public void setFileMust(Integer fileMust) {
        this.fileMust = fileMust;
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

    public Boolean getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Boolean updateFlag) {
        this.updateFlag = updateFlag;
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
    
    public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}
	
	public Integer getSysType() {
		return sysType;
	}

	public void setSysType(Integer sysType) {
		this.sysType = sysType;
	}

	public Integer getTypeMust() {
		return typeMust;
	}

	public void setTypeMust(Integer typeMust) {
		this.typeMust = typeMust;
	}

    public List<String> getPositionName() {
        return positionName;
    }

    public void setPositionName(List<String> positionName) {
        this.positionName = positionName;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
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
        sb.append(", suitableUnit=").append(suitableUnit);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", controlType=").append(controlType);
        sb.append(", gradeMust=").append(gradeMust);
        sb.append(", codeMust=").append(codeMust);
        sb.append(", registerCodeMust=").append(registerCodeMust);
        sb.append(", regionMust=").append(regionMust);
        sb.append(", categoryMust=").append(categoryMust);
        sb.append(", rangeMust=").append(rangeMust);
        sb.append(", fileMust=").append(fileMust);
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
}