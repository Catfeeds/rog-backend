package com.rograndec.feijiayun.chain.inf.pos.user.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SelectPOSclerkVO", description = "POS选择营业员")
public class SelectPOSclerkVO implements Serializable{
	
	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年9月30日 下午4:33:42 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "营业员ID")
	private Long clerkId;
	
	@ApiModelProperty(value = "营业员编码")
	private String clerkCode;
	
	@ApiModelProperty(value = "营业员名称")
	private String clerkName;
	
	@ApiModelProperty(value = "柜组Id",hidden=true)
	private Long cargoAreaId;
	
	@ApiModelProperty(value = "柜组编码")
	private String cargoAreaCode;
	
	@ApiModelProperty(value = "柜组名称")
	private String cargoAreaName;

	public Long getClerkId() {
		return clerkId;
	}

	public void setClerkId(Long clerkId) {
		this.clerkId = clerkId;
	}

	public String getClerkCode() {
		return clerkCode;
	}

	public void setClerkCode(String clerkCode) {
		this.clerkCode = clerkCode;
	}

	public String getClerkName() {
		return clerkName;
	}

	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}

	public Long getCargoAreaId() {
		return cargoAreaId;
	}

	public void setCargoAreaId(Long cargoAreaId) {
		this.cargoAreaId = cargoAreaId;
	}

	public String getCargoAreaCode() {
		return cargoAreaCode;
	}

	public void setCargoAreaCode(String cargoAreaCode) {
		this.cargoAreaCode = cargoAreaCode;
	}

	public String getCargoAreaName() {
		return cargoAreaName;
	}

	public void setCargoAreaName(String cargoAreaName) {
		this.cargoAreaName = cargoAreaName;
	}
	
	
	

}
