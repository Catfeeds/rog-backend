package com.rograndec.feijiayun.chain.inf.pos.prescription.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class UserParamVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月9日 下午5:57:05 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 人员角色类型（0-审核人；1-调配人；2-核对人；3-发药人）
     */
    @ApiModelProperty(value = "人员角色类型（0-审核人；1-调配人；2-核对人；3-发药人）")
	private Integer userRoleType;
    
    @ApiModelProperty(value = "销售模式（0-常规；1-中药）")
	private Integer saleMode;

	public Integer getUserRoleType() {
		return userRoleType;
	}

	public void setUserRoleType(Integer userRoleType) {
		this.userRoleType = userRoleType;
	}

	public Integer getSaleMode() {
		return saleMode;
	}

	public void setSaleMode(Integer saleMode) {
		this.saleMode = saleMode;
	}
	
}
