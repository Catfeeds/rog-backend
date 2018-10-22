package com.rograndec.feijiayun.chain.business.basic.supplier.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class SupplierSalerVO implements Serializable{

	/**
     * 主键ID
     */
	@ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 供货单位ID
     */
	@ApiModelProperty(value = "供货单位ID")
    private Long supplierId;

    /**
     * 企业（总部）ID
     */
	@ApiModelProperty(value = "企业（总部）ID")
    private Long enterpriseId;

    /**
     * 编码
     */
	@ApiModelProperty(value = "编码")
    private String code;

    /**
     * 姓名
     */
	@ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 身份证号
     */
	@ApiModelProperty(value = "身份证号")
    private String idNum;

    /**
     * 身份证有效期至
     */
	@ApiModelProperty(value = "身份证有效期至")
    private Date idValidUntil;

    /**
     * 身份证附件ID
     */
	@ApiModelProperty(value = "身份证附件ID")
    private Long idFileId;

	private String idFileName;

	private String idFileUrl;

    /**
     * 授权书号
     */
	@ApiModelProperty(value = "授权书号")
    private String certificateNum;

    /**
     * 授权书有效期至
     */
	@ApiModelProperty(value = "授权书有效期至")
    private Date certificateValidUntil;

    /**
     * 授权品种ID（多个用逗号分隔）
     */
	@ApiModelProperty(value = "授权品种ID（多个用逗号分隔）")
    private String authorizedVariety;

	@ApiModelProperty(value = "授权品种Name（多个用逗号分隔）")
	private String authorizedVarietyNames;
    /**
     * 授权品种范围ID（多个用逗号分隔）
     */
	@ApiModelProperty(value = "授权品种范围ID（多个用逗号分隔）")
    private String authorizedVarietyScope;

	@ApiModelProperty(value = "授权品种范围Name（多个用逗号分隔）")
	private String authorizedVarietyScopeNames;
    /**
     * 授权书附件ID
     */
	@ApiModelProperty(value = "授权书附件ID")
    private Long certificateFileId;

	private String certificateFileName;

	private String certificateFileUrl;

	@ApiModelProperty(value = "授权地域")
	private String authorizedRegion;

    /**
     * 电话
     */
	@ApiModelProperty(value = "电话")
    private String telephone;

    /**
     * 传真
     */
	@ApiModelProperty(value = "传真")
    private String fax;

    /**
     * 手机
     */
	@ApiModelProperty(value = "手机")
    private String mobilePhone;

    /**
     * 邮箱
     */
	@ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 微信
     */
	@ApiModelProperty(value = "微信")
    private String wechatNum;

    /**
     * QQ
     */
	@ApiModelProperty(value = "QQ")
    private String qqNum;

    /**
     * 状态（0-离职；1-在职）
     */
	@ApiModelProperty(value = "状态（0-离职；1-在职）")
    private Integer status;

    /**
     * 备注
     */
	@ApiModelProperty(value = "备注")
    private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public Date getIdValidUntil() {
		return idValidUntil;
	}

	public void setIdValidUntil(Date idValidUntil) {
		this.idValidUntil = idValidUntil;
	}

	public Long getIdFileId() {
		return idFileId;
	}

	public void setIdFileId(Long idFileId) {
		this.idFileId = idFileId;
	}

	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public Date getCertificateValidUntil() {
		return certificateValidUntil;
	}

	public void setCertificateValidUntil(Date certificateValidUntil) {
		this.certificateValidUntil = certificateValidUntil;
	}

	public String getAuthorizedVariety() {
		return authorizedVariety;
	}

	public void setAuthorizedVariety(String authorizedVariety) {
		this.authorizedVariety = authorizedVariety;
	}

	public String getAuthorizedVarietyScope() {
		return authorizedVarietyScope;
	}

	public void setAuthorizedVarietyScope(String authorizedVarietyScope) {
		this.authorizedVarietyScope = authorizedVarietyScope;
	}

	public Long getCertificateFileId() {
		return certificateFileId;
	}

	public void setCertificateFileId(Long certificateFileId) {
		this.certificateFileId = certificateFileId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWechatNum() {
		return wechatNum;
	}

	public void setWechatNum(String wechatNum) {
		this.wechatNum = wechatNum;
	}

	public String getQqNum() {
		return qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "SupplierSalerVO [id=" + id + ", supplierId=" + supplierId + ", enterpriseId=" + enterpriseId + ", code="
				+ code + ", name=" + name + ", idNum=" + idNum + ", idValidUntil=" + idValidUntil + ", idFileId="
				+ idFileId + ", certificateNum=" + certificateNum + ", certificateValidUntil=" + certificateValidUntil
				+ ", authorizedVariety=" + authorizedVariety + ", authorizedVarietyScope=" + authorizedVarietyScope
				+ ", certificateFileId=" + certificateFileId + ", telephone=" + telephone + ", fax=" + fax
				+ ", mobilePhone=" + mobilePhone + ", email=" + email + ", wechatNum=" + wechatNum + ", qqNum=" + qqNum
				+ ", status=" + status + ", remark=" + remark + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorizedVariety == null) ? 0 : authorizedVariety.hashCode());
		result = prime * result + ((authorizedVarietyScope == null) ? 0 : authorizedVarietyScope.hashCode());
		result = prime * result + ((certificateFileId == null) ? 0 : certificateFileId.hashCode());
		result = prime * result + ((certificateNum == null) ? 0 : certificateNum.hashCode());
		result = prime * result + ((certificateValidUntil == null) ? 0 : certificateValidUntil.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enterpriseId == null) ? 0 : enterpriseId.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idFileId == null) ? 0 : idFileId.hashCode());
		result = prime * result + ((idNum == null) ? 0 : idNum.hashCode());
		result = prime * result + ((idValidUntil == null) ? 0 : idValidUntil.hashCode());
		result = prime * result + ((mobilePhone == null) ? 0 : mobilePhone.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((qqNum == null) ? 0 : qqNum.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((supplierId == null) ? 0 : supplierId.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		result = prime * result + ((wechatNum == null) ? 0 : wechatNum.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SupplierSalerVO other = (SupplierSalerVO) obj;
		if (authorizedVariety == null) {
			if (other.authorizedVariety != null)
				return false;
		} else if (!authorizedVariety.equals(other.authorizedVariety))
			return false;
		if (authorizedVarietyScope == null) {
			if (other.authorizedVarietyScope != null)
				return false;
		} else if (!authorizedVarietyScope.equals(other.authorizedVarietyScope))
			return false;
		if (certificateFileId == null) {
			if (other.certificateFileId != null)
				return false;
		} else if (!certificateFileId.equals(other.certificateFileId))
			return false;
		if (certificateNum == null) {
			if (other.certificateNum != null)
				return false;
		} else if (!certificateNum.equals(other.certificateNum))
			return false;
		if (certificateValidUntil == null) {
			if (other.certificateValidUntil != null)
				return false;
		} else if (!certificateValidUntil.equals(other.certificateValidUntil))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enterpriseId == null) {
			if (other.enterpriseId != null)
				return false;
		} else if (!enterpriseId.equals(other.enterpriseId))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idFileId == null) {
			if (other.idFileId != null)
				return false;
		} else if (!idFileId.equals(other.idFileId))
			return false;
		if (idNum == null) {
			if (other.idNum != null)
				return false;
		} else if (!idNum.equals(other.idNum))
			return false;
		if (idValidUntil == null) {
			if (other.idValidUntil != null)
				return false;
		} else if (!idValidUntil.equals(other.idValidUntil))
			return false;
		if (mobilePhone == null) {
			if (other.mobilePhone != null)
				return false;
		} else if (!mobilePhone.equals(other.mobilePhone))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (qqNum == null) {
			if (other.qqNum != null)
				return false;
		} else if (!qqNum.equals(other.qqNum))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (supplierId == null) {
			if (other.supplierId != null)
				return false;
		} else if (!supplierId.equals(other.supplierId))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		if (wechatNum == null) {
			if (other.wechatNum != null)
				return false;
		} else if (!wechatNum.equals(other.wechatNum))
			return false;
		return true;
	}

	public String getAuthorizedVarietyNames() {
		return authorizedVarietyNames;
	}

	public void setAuthorizedVarietyNames(String authorizedVarietyNames) {
		this.authorizedVarietyNames = authorizedVarietyNames;
	}

	public String getAuthorizedVarietyScopeNames() {
		return authorizedVarietyScopeNames;
	}

	public void setAuthorizedVarietyScopeNames(String authorizedVarietyScopeNames) {
		this.authorizedVarietyScopeNames = authorizedVarietyScopeNames;
	}

	public String getAuthorizedRegion() {
		return authorizedRegion;
	}

	public void setAuthorizedRegion(String authorizedRegion) {
		this.authorizedRegion = authorizedRegion;
	}

	public String getIdFileName() {
		return idFileName;
	}

	public void setIdFileName(String idFileName) {
		this.idFileName = idFileName;
	}

	public String getIdFileUrl() {
		return idFileUrl;
	}

	public void setIdFileUrl(String idFileUrl) {
		this.idFileUrl = idFileUrl;
	}

	public String getCertificateFileName() {
		return certificateFileName;
	}

	public void setCertificateFileName(String certificateFileName) {
		this.certificateFileName = certificateFileName;
	}

	public String getCertificateFileUrl() {
		return certificateFileUrl;
	}

	public void setCertificateFileUrl(String certificateFileUrl) {
		this.certificateFileUrl = certificateFileUrl;
	}
}
