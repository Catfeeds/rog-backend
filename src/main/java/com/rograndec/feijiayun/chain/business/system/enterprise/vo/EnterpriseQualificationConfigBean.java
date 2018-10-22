package com.rograndec.feijiayun.chain.business.system.enterprise.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "enterpriseQualificationConfig", description = "会员对象")
public class EnterpriseQualificationConfigBean implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID，0时新建，其他值时修改")
    private Long id;

    /**
     * 企业ID
     */
	@ApiModelProperty(required = true, value = "企业ID，新增门店时传0，修改门店时传对应值")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
	@ApiModelProperty(required = true, value = "企业父级ID，新增门店时传0，修改门店时传对应值")
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
	@ApiModelProperty(required = false, value = "资质附件ID，不选时不传")
    private Long fileId;

    /**
     * 备注
     */
	@ApiModelProperty(required = false, value = "备注，不传")
    private String remark;

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
     * @param qualificationId 资质编码
     */
    public String getQualificationCode() {
		return qualificationCode;
	}

    /**
     * 资质编码
     * @param qualificationId 资质编码
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}