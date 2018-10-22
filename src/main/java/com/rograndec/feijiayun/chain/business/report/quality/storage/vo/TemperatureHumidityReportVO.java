package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * 库房温湿度记录
 * @author kexinhao
 * 2017-09-28
 */
public class TemperatureHumidityReportVO implements Serializable {
    @ApiModelProperty(value = "记录Id")
	private String recordId;
    @ApiModelProperty(value = "组织机构编码")
    private String enterpriseCode;
    @ApiModelProperty(value = "组织机构名称")
    private String enterpriseName;
    @ApiModelProperty(value = "记录日期")
    private Date recordDate;
    @ApiModelProperty(value = "记录人员名称")
    private String recordManName;
    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;
    @ApiModelProperty(value = "库区名称")
    private String warehouseAreaName;
    @ApiModelProperty(value = "温度下限（℃）")
    private BigDecimal tempLowLimit;
    @ApiModelProperty(value = "温度上限（℃）")
    private BigDecimal tempHighLimit;
    @ApiModelProperty(value = "相对湿度下限（%）")
    private BigDecimal humidityLowLimit;
    @ApiModelProperty(value = "相对湿度上限（%）")
    private BigDecimal humidityHighLimit;
    @ApiModelProperty(value = "附件ID")
    private Long fileId;
    @ApiModelProperty(value = "详情列表")
    private List<TemperatureHumidityDetailReportVO> temperatureHumidityDetailReportVOList;
	public String getEnterpriseCode() {
		return enterpriseCode;
	}
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public String getRecordManName() {
		return recordManName;
	}
	public void setRecordManName(String recordManName) {
		this.recordManName = recordManName;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getWarehouseAreaName() {
		return warehouseAreaName;
	}
	public void setWarehouseAreaName(String warehouseAreaName) {
		this.warehouseAreaName = warehouseAreaName;
	}
	public BigDecimal getTempLowLimit() {
		return tempLowLimit;
	}
	public void setTempLowLimit(BigDecimal tempLowLimit) {
		this.tempLowLimit = tempLowLimit;
	}
	public BigDecimal getTempHighLimit() {
		return tempHighLimit;
	}
	public void setTempHighLimit(BigDecimal tempHighLimit) {
		this.tempHighLimit = tempHighLimit;
	}
	public BigDecimal getHumidityLowLimit() {
		return humidityLowLimit;
	}
	public void setHumidityLowLimit(BigDecimal humidityLowLimit) {
		this.humidityLowLimit = humidityLowLimit;
	}
	public BigDecimal getHumidityHighLimit() {
		return humidityHighLimit;
	}
	public void setHumidityHighLimit(BigDecimal humidityHighLimit) {
		this.humidityHighLimit = humidityHighLimit;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public List<TemperatureHumidityDetailReportVO> getTemperatureHumidityDetailReportVOList() {
		return temperatureHumidityDetailReportVOList;
	}
	public void setTemperatureHumidityDetailReportVOList(
			List<TemperatureHumidityDetailReportVO> temperatureHumidityDetailReportVOList) {
		this.temperatureHumidityDetailReportVOList = temperatureHumidityDetailReportVOList;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
}   