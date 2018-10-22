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
public class EntrepriseValidateVO implements Serializable{

	/**
     * saas_enterprise_qualification_config
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 基础数据质量控制（0关闭；1-开启）
     */
    @ApiModelProperty(value="基础数据质量控制（0关闭；1-开启）",required=true)
    private Integer qualityControl;
    

	public Integer getQualityControl() {
		return qualityControl;
	}

	public void setQualityControl(Integer qualityControl) {
		this.qualityControl = qualityControl;
	}

	
}
