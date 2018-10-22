package com.rograndec.feijiayun.chain.business.distr.parent.vo.pick;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "DistrSendViewVO", description = "配货单-捡货-查看对象")
public class DistrSendViewVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = false, value = "配货单ID")
	private Long id;
	
	@ApiModelProperty(required = false, value = "配货日期")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date distrDate;

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
	
	@ApiModelProperty(required = false, value = "配货明细")
	private List<DistrSendViewDtlVO> dtlList = new ArrayList<DistrSendViewDtlVO>();
	
	@ApiModelProperty(required = false, value = "配货数量合计")
	private BigDecimal quantityTotal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDistrDate() {
		return distrDate;
	}

	public void setDistrDate(Date distrDate) {
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

	public List<DistrSendViewDtlVO> getDtlList() {
		return dtlList;
	}

	public void setDtlList(List<DistrSendViewDtlVO> dtlList) {
		this.dtlList = dtlList;
	}

	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
