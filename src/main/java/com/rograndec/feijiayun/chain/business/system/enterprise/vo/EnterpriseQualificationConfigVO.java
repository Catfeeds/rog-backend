package com.rograndec.feijiayun.chain.business.system.enterprise.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @ClassName: EnterpriseQualificationConfigVO   
 * @Description: TODO(展示企业信息页面资质列表)
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月23日 上午11:59:36
 */
@ApiModel(value = "enterpriseQualificationConfigVO", description = "企业信息页面资质列表展示对象")
public class EnterpriseQualificationConfigVO {

	/**
     * 企业资质ID
     */
	@ApiModelProperty(value = "企业资质配置ID")
    private Long id;
	
	/**
     * 企业ID
     */
	@ApiModelProperty(value = "企业ID")
    private Long enterpriseId;
	
	/**
     * 总部ID
     */
	@ApiModelProperty(value = "总部ID")
    private Long parentId;
	
	/**
     * 资质ID
     */
	@ApiModelProperty(value = "资质ID")
    private Long qualificationId;
    
    /**
     * 资质编号
     */
	@ApiModelProperty(value = "资质编号")
    private String qualificationCode;
    
    /**
     * 资质类型是否必选（0-可选；1-必选）
     */
	@ApiModelProperty(value = "资质类型是否必选（0-可选；1-必选）")
    private Integer typeMust;
	
	/**
     * 资质类型是否必选（0-可选；1-必选）
     */
	@ApiModelProperty(value = "资质类型名称")
    private String typeMustName;
    
    /**
     * 控制类型（0-质量控制；1-仅提示）
     */
	@ApiModelProperty(value = "控制类型（0-质量控制；1-仅提示）")
    private Integer controlType;
	
	/**
     * 控制类型名称
     */
	@ApiModelProperty(value = "控制类型名称")
    private String controlTypeName;
    
    /**
     * 资质描述 名称
     */
	@ApiModelProperty(value = "资质描述 名称")
    private String name;
    
    /**
     * 编码
     */
//    private String code;
    
    /**
     * 有效期至
     */
	@ApiModelProperty(value = "有效期至")
    private Date validUntil;
    
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
     * 来源 （0-资质基表，1-企业资质表）
     */
	@ApiModelProperty(value = "来源 （0-资质基表，1-企业资质表）")
    private Integer source;
    
	
    
    public EnterpriseQualificationConfigVO() {
		super();
	}
	
	public EnterpriseQualificationConfigVO(Long qualificationId,
			Integer typeMust, Integer controlType, String name, 
			Date validUntil, Long fileId, String fileName, Integer source) {
		super();
		this.qualificationId = qualificationId;
		this.typeMust = typeMust;
		this.controlType = controlType;
		this.name = name;
		this.validUntil = validUntil;
		this.fileId = fileId;
		this.fileName = fileName;
		this.source = source;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(Long qualificationId) {
		this.qualificationId = qualificationId;
	}

	public String getQualificationCode() {
		return qualificationCode;
	}

	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}

	public Integer getTypeMust() {
		return typeMust;
	}

	public void setTypeMust(Integer typeMust) {
		this.typeMust = typeMust;
	}

	public Integer getControlType() {
		return controlType;
	}

	public void setControlType(Integer controlType) {
		this.controlType = controlType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getTypeMustName() {
		return typeMustName;
	}

	public void setTypeMustName(String typeMustName) {
		this.typeMustName = typeMustName;
	}

	public String getControlTypeName() {
		return controlTypeName;
	}

	public void setControlTypeName(String controlTypeName) {
		this.controlTypeName = controlTypeName;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
