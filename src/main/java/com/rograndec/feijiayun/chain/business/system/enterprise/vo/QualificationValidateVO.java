package com.rograndec.feijiayun.chain.business.system.enterprise.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName: EntrepriseQualificationValidateBean   
 * @Description: 资质页面验证返回对象属性
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月26日 下午1:44:49
 */
public class QualificationValidateVO implements Serializable{

	/**
     * saas_enterprise_qualification_config
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 基础数据质量控制（0关闭；1-开启）
     */
    @ApiModelProperty(value="基础数据质量控制（0关闭；1-开启）",required=true)
    private Integer qualityControl;
    
    /**
     * 资质编号是否必填（0-只读；1-必填）
     */
	@ApiModelProperty(value = "企业资质资质编号是否必填（0-只读；1-必填）||员工资质资格证书号是否必填（0-非必填；1-必填）")
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

	@ApiModelProperty(value = "等级是否必填（0-非必填；1-必填）----用于员工资质")
	private Integer gradeMust;

	@ApiModelProperty(value = "注册证书号是否必填（0-非必填；1-必填）----用于员工资质")
	private Integer registerCodeMust;

	@ApiModelProperty(value = "适用地区是否必填（0-非必填；1-必填）----用于员工资质")
	private Integer regionMust;

	@ApiModelProperty(value = "适用类别是否必填（0-非必填；1-必填）----用于员工资质")
	private Integer categoryMust;

	@ApiModelProperty(value = "适用范围是否必填（0-只读；1-必填）----用于员工资质")
	private Integer rangeMust;

	public Integer getQualityControl() {
		return qualityControl;
	}

	public void setQualityControl(Integer qualityControl) {
		this.qualityControl = qualityControl;
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

	public Integer getGradeMust() {
		return gradeMust;
	}

	public void setGradeMust(Integer gradeMust) {
		this.gradeMust = gradeMust;
	}

	public Integer getRegisterCodeMust() {
		return registerCodeMust;
	}

	public void setRegisterCodeMust(Integer registerCodeMust) {
		this.registerCodeMust = registerCodeMust;
	}

	public Integer getRegionMust() {
		return regionMust;
	}

	public void setRegionMust(Integer regionMust) {
		this.regionMust = regionMust;
	}

	public Integer getCategoryMust() {
		return categoryMust;
	}

	public void setCategoryMust(Integer categoryMust) {
		this.categoryMust = categoryMust;
	}

	public Integer getRangeMust() {
		return rangeMust;
	}

	public void setRangeMust(Integer rangeMust) {
		this.rangeMust = rangeMust;
	}
}
