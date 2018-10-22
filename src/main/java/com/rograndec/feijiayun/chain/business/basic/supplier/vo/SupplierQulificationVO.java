package com.rograndec.feijiayun.chain.business.basic.supplier.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class SupplierQulificationVO implements Serializable{

	/**
     * 主键ID
     */
	@ApiModelProperty(value = "主键ID")
    private Long id;

	/**
	 * 品种范围
	 */
	@ApiModelProperty(value = "品种范围")
	private String businessVariety;

    /**
     * 供货单位ID
     */
	@ApiModelProperty(value = "供货单位ID")
    private Long supplierId;

    /**
     * 企业ID
     */
	@ApiModelProperty(value = "企业ID")
    private Long enterpriseId;
	/**
     * 资质ID
     */
	@ApiModelProperty(value = "资质ID")
    private Long qualificationId;

    /**
     * 有效期至
     */
	@ApiModelProperty(value = "有效期至")
    private Date validUntil;

	/**
	 * 起始日期
	 */
	@ApiModelProperty(value = "起始日期")
	private Date startDate;

    /**
     * 资质附件ID
     */
	@ApiModelProperty(value = "资质附件ID")
    private Long fileId;
	
	/**
	 * 资质附件名称
	 */
	@ApiModelProperty(value = "资质附件名称")
    private String fileName;
	
	/**
	 * 七牛空间ID
	 */
	@ApiModelProperty(value = "七牛空间ID")
	private Integer bucketId;
	
	/**
	 * 七牛空间链接地址
	 */
	@ApiModelProperty(value = "七牛空间链接地址")
	private Integer scheme;
	/**
	 * 资质类型是否必选（0-可选；1-必选）
	 */
	@ApiModelProperty(value = "资质类型是否必选（0-可选；1-必选）")
    private Integer typeMust;
	
	/**
	 * 资质描述
	 */
	@ApiModelProperty(value = "资质描述")
	private String qulificationDes;
	
	/**
	 * 资质编号(不是资质表编码)
	 */
	@ApiModelProperty(value = " 资质类型(不是资质表编码)")
	private String qualificationCode;
	
	
	/**
	 * 控制类型（0-质量控制；1-仅提示）
	 */
	@ApiModelProperty(value = "控制类型（0-质量控制；1-仅提示）")
	private Integer controlType;
	
	/**
	 * 0-自定义资质 1-系统默认的
	 */
	@ApiModelProperty(value = "0-自定义资质 1-系统默认的")
	private Integer sysType;

	/**
	 */
	@ApiModelProperty(value = "资质编号是否必填（0-只读；1-必填）")
	private Integer codeMust;
	
	@ApiModelProperty(value = "有效期至是否必填（0-只读；1-必填）")
	private Integer validUntilMust;
	
	@ApiModelProperty(value = "附件是否必填（0-非必填；1-必填）")
	private Integer fileMust;
	
	public Integer getCodeMust() {
		return codeMust;
	}


	public void setCodeMust(Integer codeMust) {
		this.codeMust = codeMust;
	}


	public Integer getValidUntilMust() {
		return validUntilMust;
	}


	public void setValidUntilMust(Integer validUntilMust) {
		this.validUntilMust = validUntilMust;
	}


	public Integer getFileMust() {
		return fileMust;
	}


	public void setFileMust(Integer fileMust) {
		this.fileMust = fileMust;
	}


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


	public Long getQualificationId() {
		return qualificationId;
	}


	public void setQualificationId(Long qualificationId) {
		this.qualificationId = qualificationId;
	}


	public Date getValidUntil() {
		return validUntil;
	}


	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}


	public Long getFileId() {
		return fileId;
	}


	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public Integer getBucketId() {
		return bucketId;
	}


	public void setBucketId(Integer bucketId) {
		this.bucketId = bucketId;
	}


	public Integer getScheme() {
		return scheme;
	}


	public void setScheme(Integer scheme) {
		this.scheme = scheme;
	}


	public Integer getTypeMust() {
		return typeMust;
	}


	public void setTypeMust(Integer typeMust) {
		this.typeMust = typeMust;
	}


	public String getQulificationDes() {
		return qulificationDes;
	}


	public void setQulificationDes(String qulificationDes) {
		this.qulificationDes = qulificationDes;
	}


	public String getQualificationCode() {
		return qualificationCode;
	}


	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}


	public Integer getControlType() {
		return controlType;
	}


	public void setControlType(Integer controlType) {
		this.controlType = controlType;
	}
	
	public Integer getSysType() {
		return sysType;
	}


	public void setSysType(Integer sysType) {
		this.sysType = sysType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getBusinessVariety() {
		return businessVariety;
	}

	public void setBusinessVariety(String businessVariety) {
		this.businessVariety = businessVariety;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bucketId == null) ? 0 : bucketId.hashCode());
		result = prime * result + ((controlType == null) ? 0 : controlType.hashCode());
		result = prime * result + ((enterpriseId == null) ? 0 : enterpriseId.hashCode());
		result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((qualificationCode == null) ? 0 : qualificationCode.hashCode());
		result = prime * result + ((qualificationId == null) ? 0 : qualificationId.hashCode());
		result = prime * result + ((qulificationDes == null) ? 0 : qulificationDes.hashCode());
		result = prime * result + ((scheme == null) ? 0 : scheme.hashCode());
		result = prime * result + ((supplierId == null) ? 0 : supplierId.hashCode());
		result = prime * result + ((typeMust == null) ? 0 : typeMust.hashCode());
		result = prime * result + ((validUntil == null) ? 0 : validUntil.hashCode());
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
		SupplierQulificationVO other = (SupplierQulificationVO) obj;
		if (bucketId == null) {
			if (other.bucketId != null)
				return false;
		} else if (!bucketId.equals(other.bucketId))
			return false;
		if (controlType == null) {
			if (other.controlType != null)
				return false;
		} else if (!controlType.equals(other.controlType))
			return false;
		if (enterpriseId == null) {
			if (other.enterpriseId != null)
				return false;
		} else if (!enterpriseId.equals(other.enterpriseId))
			return false;
		if (fileId == null) {
			if (other.fileId != null)
				return false;
		} else if (!fileId.equals(other.fileId))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (qualificationCode == null) {
			if (other.qualificationCode != null)
				return false;
		} else if (!qualificationCode.equals(other.qualificationCode))
			return false;
		if (qualificationId == null) {
			if (other.qualificationId != null)
				return false;
		} else if (!qualificationId.equals(other.qualificationId))
			return false;
		if (qulificationDes == null) {
			if (other.qulificationDes != null)
				return false;
		} else if (!qulificationDes.equals(other.qulificationDes))
			return false;
		if (scheme == null) {
			if (other.scheme != null)
				return false;
		} else if (!scheme.equals(other.scheme))
			return false;
		if (supplierId == null) {
			if (other.supplierId != null)
				return false;
		} else if (!supplierId.equals(other.supplierId))
			return false;
		if (typeMust == null) {
			if (other.typeMust != null)
				return false;
		} else if (!typeMust.equals(other.typeMust))
			return false;
		if (validUntil == null) {
			if (other.validUntil != null)
				return false;
		} else if (!validUntil.equals(other.validUntil))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "SupplierQulificationVO [id=" + id + ", supplierId=" + supplierId + ", enterpriseId=" + enterpriseId
				+ ", qualificationId=" + qualificationId + ", validUntil=" + validUntil + ", fileId=" + fileId
				+ ", fileName=" + fileName + ", bucketId=" + bucketId + ", scheme=" + scheme + ", typeMust=" + typeMust
				+ ", qulificationDes=" + qulificationDes + ", qualificationCode=" + qualificationCode + ", controlType="
				+ controlType + "]";
	}
	
	
}
