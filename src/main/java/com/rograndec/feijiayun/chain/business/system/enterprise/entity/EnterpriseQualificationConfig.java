package com.rograndec.feijiayun.chain.business.system.enterprise.entity;

import com.rograndec.feijiayun.chain.business.auth.register.entity.Register;
import com.rograndec.feijiayun.chain.business.system.set.entity.EnterpriseQualification;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel(value = "enterpriseQualificationConfig", description = "企业资质对象")
public class EnterpriseQualificationConfig implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID，0时新建，其他值时修改")
    private Long id;

    /**
     * 企业ID
     */
	@ApiModelProperty(required = false, value = "企业ID,后台赋值")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
	@ApiModelProperty(required = false, value = "企业父级ID,后台赋值")
    private Long parentId;

    /**
     * 资质ID
     */
	@ApiModelProperty(required = true, value = "资质ID，新建时取自下拉框，修改时取自列表")
    private Long qualificationId;

	/**
     * 资质编码
     */
	@ApiModelProperty(required = true, value = "资质编码，不填时不传")
    private String qualificationCode;
	
    /**
     * 有效期至
     */
	@ApiModelProperty(required = false, value = "有效期至,不填时不传")
    private Date validUntil;

    /**
     * 资质附件ID
     */
	@ApiModelProperty(required = false, value = "资质ID，不选时不传")
    private Long fileId;

    /**
     * 备注
     */
	@ApiModelProperty(required = false, value = "备注，不传")
    private String remark;

    /**
     * 创建人ID
     */
	@ApiModelProperty(required = false, value = "创建人ID，不传")
    private Long createrId;

    /**
     * 创建人编码
     */
	@ApiModelProperty(required = false, value = "创建人编码，不传")
    private String createrCode;

    /**
     * 创建人名称
     */
	@ApiModelProperty(required = false, value = "创建人名称，不传")
    private String createrName;

    /**
     * 创建时间
     */
	@ApiModelProperty(required = false, value = "创建时间，不传")
    private Date createTime;

    /**
     * 最后修改人ID
     */
	@ApiModelProperty(required = false, value = "修改人ID，不传")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
	@ApiModelProperty(required = false, value = "修改人编码，不传")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
	@ApiModelProperty(required = false, value = "修改人名称，不传")
    private String modifierName;

    /**
     * 更新时间
     */
	@ApiModelProperty(required = false, value = "修改时间，不传")
    private Date updateTime;

    public static List<EnterpriseQualificationConfig> initEnterpriseQualificationConfigs(
            Register register
            ,Enterprise enterprise
            ,List<EnterpriseQualification> defaultEnterpriseQulifications
    ){

        List<EnterpriseQualificationConfig> collect = defaultEnterpriseQulifications.stream().map(sq ->
                initEnterpriseQualificationConfig(register,enterprise,sq )
        ).collect(Collectors.toList());

        return collect;
    }

    public static EnterpriseQualificationConfig initEnterpriseQualificationConfig(
            Register register
            ,Enterprise enterprise
            ,EnterpriseQualification enterpriseQualification
    ){

        EnterpriseQualificationConfig enterpriseQualificationConfig = new EnterpriseQualificationConfig();

        /**
         * 企业ID
         */
        enterpriseQualificationConfig.setEnterpriseId(enterprise.getId());

        /**
         * 上级企业ID
         */
        enterpriseQualificationConfig.setParentId(enterprise.getParentId());

        /**
         * 资质ID
         */
        enterpriseQualificationConfig.setQualificationId(enterpriseQualification.getId());


        /**
         * 01 : 营业制造
         * 06 : 药品经营许可证
         * 07 : 药品经营质量管理规范认证证书
         */

        if(enterpriseQualification.getCode().equals("01")){
            /**
             * 资质编码
             */
            enterpriseQualificationConfig.setQualificationCode(register.getBusinessFileCode());

            /**
             * 有效期至
             */
            enterpriseQualificationConfig.setValidUntil(register.getBusinessValidUntil());

            /**
             * 资质附件ID
             */
            enterpriseQualificationConfig.setFileId(register.getBusinessFileId());

        }else if(enterpriseQualification.getCode().equals("06")){
            /**
             * 资质编码
             */
            enterpriseQualificationConfig.setQualificationCode(register.getPermitFileCode());

            /**
             * 有效期至
             */
            enterpriseQualificationConfig.setValidUntil(register.getPermitValidUntil());

            /**
             * 资质附件ID
             */
            enterpriseQualificationConfig.setFileId(register.getPermitFileId());

        }else if(enterpriseQualification.getCode().equals("07")){
            /**
             * 资质编码
             */
            enterpriseQualificationConfig.setQualificationCode(register.getQualityFileCode());

            /**
             * 有效期至
             */
            enterpriseQualificationConfig.setValidUntil(register.getQualityValidUntil());


            /**
             * 资质附件ID
             */
            enterpriseQualificationConfig.setFileId(register.getQualityFileId());
        }

        enterpriseQualificationConfig.setCreateTime(new Date());
        enterpriseQualificationConfig.setUpdateTime(new Date());

        return enterpriseQualificationConfig;
    }


    /**
     * saas_enterprise_qualification_config
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
     * 资质ID
     * @return qualification_id 资质ID
     */
    public Long getQualificationId() {
        return qualificationId;
    }

    /**
     * 资质ID
     * @param qualificationId 资质ID
     */
    public void setQualificationId(Long qualificationId) {
        this.qualificationId = qualificationId;
    }

    /**
     * 资质编码
     * @param  资质编码
     */
    public String getQualificationCode() {
		return qualificationCode;
	}

    /**
     * 资质编码
     * @param
     */
	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}

	/**
     * 有效期至
     * @return valid_until 有效期至
     */
    public Date getValidUntil() {
        return validUntil;
    }

    /**
     * 有效期至
     * @param validUntil 有效期至
     */
    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    /**
     * 资质附件ID
     * @return file_id 资质附件ID
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * 资质附件ID
     * @param fileId 资质附件ID
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
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
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", qualificationId=").append(qualificationId);
        sb.append(", validUntil=").append(validUntil);
        sb.append(", fileId=").append(fileId);
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