package com.rograndec.feijiayun.chain.business.distr.parent.vo.pick;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "DistrSendVO", description = "配货单-捡货-待捡货返回对象")
public class DistrSendVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = false, value = "配货单ID")
	private Long id;
	
	@ApiModelProperty(required = false, value = "配货日期")
	@JsonFormat(pattern="yyyy-MM-dd")
	private String distrDate;

	@ApiModelProperty(required = false, value = "配货单号")
	private String code;
	
	@ApiModelProperty(required = false, value = "要货单位编码")
	private String requestUnitCode;
	
	@ApiModelProperty(required = false, value = "要货单位名称")
	private String requestUnitName;
	
	@ApiModelProperty(required = false, value = "配货类型")
	private Integer distrType;
	
	@ApiModelProperty(required = false, value = "配货类型名称")
	private String distrTypeName;
	
	@ApiModelProperty(required = false, value = "配货人员")
	private String distrManName;
	
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

	public String getDistrDate() {
		return distrDate;
	}

	public void setDistrDate(String distrDate) {
		this.distrDate = distrDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Integer getDistrType() {
		return distrType;
	}

	public void setDistrType(Integer distrType) {
		this.distrType = distrType;
	}

	public String getDistrTypeName() {
		return distrTypeName;
	}

	public void setDistrTypeName(String distrTypeName) {
		this.distrTypeName = distrTypeName;
	}

	public String getDistrManName() {
		return distrManName;
	}

	public void setDistrManName(String distrManName) {
		this.distrManName = distrManName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
}
