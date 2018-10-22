package com.rograndec.feijiayun.chain.business.basic.supplier.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class DefaultQulificationVO implements Serializable{
	
	/**
     * 主键ID
     */
	@ApiModelProperty(value = "企业资质表的ID&&关联着供货单位资质表的supplierId")
    private Long supplierId;

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
	@ApiModelProperty(value = "资质编号是否必填（0-只读；1-必填）")
    private Integer codeMust;

    /**
     * 有效期至是否必填（0-只读；1-必填）
     */
	@ApiModelProperty(value = "有效期至是否必填（0-只读；1-必填）")
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

	public Integer getSysType() {
		return sysType;
	}

	public void setSysType(Integer sysType) {
		this.sysType = sysType;
	}

	public Integer getSuitableUnit() {
		return suitableUnit;
	}

	public void setSuitableUnit(Integer suitableUnit) {
		this.suitableUnit = suitableUnit;
	}

	public Integer getTypeMust() {
		return typeMust;
	}

	public void setTypeMust(Integer typeMust) {
		this.typeMust = typeMust;
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

	public Integer getControlType() {
		return controlType;
	}

	public void setControlType(Integer controlType) {
		this.controlType = controlType;
	}

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	

}
