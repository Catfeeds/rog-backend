package com.rograndec.feijiayun.chain.inf.pos.prescription.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class PrescriptionParamVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月9日 下午5:57:05 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 设置类型（0-用法；1-用量；2-单次剂量；3-注意事项）
     */
    @ApiModelProperty(value = "设置类型（0-用法；1-用量；2-单次剂量；3-注意事项）")
	private Integer setType;

	public Integer getSetType() {
		return setType;
	}

	public void setSetType(Integer setType) {
		this.setType = setType;
	}
	
}
