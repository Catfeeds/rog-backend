package com.rograndec.feijiayun.chain.business.distr.parent.vo.pick;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "DistrPickVO", description = "配货单-捡货-已捡货返回对象")
public class DistrPickVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = false, value = "拣货单ID")
	private Long id;
	
	@ApiModelProperty(required = false, value = "拣货日期")
	@JsonFormat(pattern="yyyy-MM-dd")
	private String pickDate;

	@ApiModelProperty(required = false, value = "拣货单号")
	private String code;
	
	@ApiModelProperty(required = false, value = "拣货人员")
	private String pickManName;
	
	@ApiModelProperty(required = false, value = "配货单号")
	private String distrSendCode;
	
	@ApiModelProperty(required = false, value = "要货单位编码")
	private String requestUnitCode;
	
	@ApiModelProperty(required = false, value = "要货单位名称")
	private String requestUnitName;
	
	@ApiModelProperty(required = false, value = "状态")
	private Integer status;
	
	@ApiModelProperty(required = false, value = "状态名称")
	private String statusName;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPickDate() {
		return pickDate;
	}



	public void setPickDate(String pickDate) {
		this.pickDate = pickDate;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getPickManName() {
		return pickManName;
	}



	public void setPickManName(String pickManName) {
		this.pickManName = pickManName;
	}



	public String getDistrSendCode() {
		return distrSendCode;
	}



	public void setDistrSendCode(String distrSendCode) {
		this.distrSendCode = distrSendCode;
	}



	public String getRequestUnitCode() {
		return requestUnitCode;
	}



	public void setRequestUnitCode(String requestUnitCode) {
		this.requestUnitCode = requestUnitCode;
	}



	public String getRequestUnitName() {
		return requestUnitName;
	}



	public void setRequestUnitName(String requestUnitName) {
		this.requestUnitName = requestUnitName;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}



	public String getStatusName() {
		return statusName;
	}



	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
