package com.rograndec.feijiayun.chain.business.system.set.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "enterpriseQualification", description = "系统设置-企业资质基表对象")
public class EnterpriseQualification implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value = "主键ID&&关联着供货单位资质表中的SupplierId")
    private Long id;

    /**
     * 企业（总部）ID
     */
	@ApiModelProperty(value = "企业（总部）ID")
    private Long enterpriseId;

    /**
     * 0：用户自定义；1-系统默认
     */
	@ApiModelProperty(value = "0：用户自定义；1-系统默认")
    private Integer sysType;

    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty(value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）以逗号分隔开")
    private String businessVariety;

    /**
     * 适用机构（0-全部；1-总部；2-分店；3-供货单位）
     */
	@ApiModelProperty(value = "适用机构（0-全部；1-总部；2-分店；3-供货单位）")
    private Integer suitableUnit;

    /**
     * 资质类型是否必选（0-可选；1-必选）
     */
	@ApiModelProperty(value = "资质类型是否必选（0-可选；1-必选）")
    private Integer typeMust;

    /**
     * 编码
     */
	@ApiModelProperty(value = "编码")
    private String code;

    /**
     * 名称
     */
	@ApiModelProperty(value = "名称")
    private String name;

    /**
     * 控制类型（0-质量控制；1-仅提示）
     */
	@ApiModelProperty(value = "控制类型（0-质量控制；1-仅提示）")
    private Integer controlType;

    /**
     * 资质编号是否必填（0-只读；1-必填）
     */
	@ApiModelProperty(value = "资质编号是否必填（0-非必填；1-必填）")
    private Integer codeMust;

    /**
     * 有效期至是否必填（0-只读；1-必填）
     */
	@ApiModelProperty(value = "有效期至是否必填（0-非必填；1-必填）")
    private Integer validUntilMust;

    /**
     * 附件是否必填（0-非必填；1-必填）
     */
	@ApiModelProperty(value = "附件是否必填（0-非必填；1-必填）")
    private Integer fileMust;

    /**
     * 状态（0-禁用；1-启用）
     */
	@ApiModelProperty(value = "状态（0-禁用；1-启用）")
    private Integer status;

    /**
     * 备注
     */
	@ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建人ID
     */
	@ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
	@ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
	@ApiModelProperty(value = "创建人名称")
    private String createrName;

    /**
     * 创建时间
     */
	@ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
	@ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
	@ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
	@ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
	@ApiModelProperty(value = "更新时间")
    private Date updateTime;
    
    /**
     * 新增企业类型
     */
	@ApiModelProperty(value = "新增企业类型")
    private Integer chainType;


    /**
     * 是否可删除
     */
    private Boolean deleteFlag = true;

    /**
     * 是否可以修改
     */
    private Boolean updateFlag = true;

    /**
     * saas_enterprise_qualification
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
     * 0：用户自定义；1-系统默认
     * @return sys_type 0：用户自定义；1-系统默认
     */
    public Integer getSysType() {
        return sysType;
    }

    /**
     * 0：用户自定义；1-系统默认
     * @param sysType 0：用户自定义；1-系统默认
     */
    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }

    /**
     * 适用机构（0-全部；1-总部；2-分店；3-供货单位）
     * @return suitable_unit 适用机构（0-全部；1-总部；2-分店；3-供货单位）
     */
    public Integer getSuitableUnit() {
        return suitableUnit;
    }

    /**
     * 适用机构（0-全部；1-总部；2-分店；3-供货单位）
     * @param suitableUnit 适用机构（0-全部；1-总部；2-分店；3-供货单位）
     */
    public void setSuitableUnit(Integer suitableUnit) {
        this.suitableUnit = suitableUnit;
    }

    /**
     * 资质类型是否必选（0-可选；1-必选）
     * @return type_must 资质类型是否必选（0-可选；1-必选）
     */
    public Integer getTypeMust() {
        return typeMust;
    }

    /**
     * 资质类型是否必选（0-可选；1-必选）
     * @param typeMust 资质类型是否必选（0-可选；1-必选）
     */
    public void setTypeMust(Integer typeMust) {
        this.typeMust = typeMust;
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
     * 资质编号是否必填（0-只读；1-必填）
     * @return code_must 资质编号是否必填（0-只读；1-必填）
     */
    public Integer getCodeMust() {
        return codeMust;
    }

    /**
     * 资质编号是否必填（0-只读；1-必填）
     * @param codeMust 资质编号是否必填（0-只读；1-必填）
     */
    public void setCodeMust(Integer codeMust) {
        this.codeMust = codeMust;
    }

    /**
     * 有效期至是否必填（0-只读；1-必填）
     * @return valid_until_must 有效期至是否必填（0-只读；1-必填）
     */
    public Integer getValidUntilMust() {
        return validUntilMust;
    }

    /**
     * 有效期至是否必填（0-只读；1-必填）
     * @param validUntilMust 有效期至是否必填（0-只读；1-必填）
     */
    public void setValidUntilMust(Integer validUntilMust) {
        this.validUntilMust = validUntilMust;
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

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }


    public Boolean getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Boolean updateFlag) {
        this.updateFlag = updateFlag;
    }

    public String getBusinessVariety() {
        return businessVariety;
    }

    public void setBusinessVariety(String businessVariety) {
        this.businessVariety = businessVariety;
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
        sb.append(", sysType=").append(sysType);
        sb.append(", suitableUnit=").append(suitableUnit);
        sb.append(", typeMust=").append(typeMust);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", controlType=").append(controlType);
        sb.append(", codeMust=").append(codeMust);
        sb.append(", validUntilMust=").append(validUntilMust);
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