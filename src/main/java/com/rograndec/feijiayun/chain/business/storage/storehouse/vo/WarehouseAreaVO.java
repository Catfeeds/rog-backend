package com.rograndec.feijiayun.chain.business.storage.storehouse.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by DD on 2017/9/26.
 */
public class WarehouseAreaVO {
	
 	/**
     * 库区ID
     */
    @ApiModelProperty(value = "库区ID")
    private Long warehouseAreaId;

    /**
     * 库区名称
     */
    @ApiModelProperty(value = "库区名称")
    private String warehouseAreaName;
    /**
     * 温度下限（℃）
     */
    @ApiModelProperty(value = "温度下限（℃）")
    private BigDecimal tempLowLimit;

    /**
     * 温度上限（℃）
     */
    @ApiModelProperty(value = "温度上限（℃）")
    private BigDecimal tempHighLimit;

    /**
     * 相对湿度下限（%）
     */
    @ApiModelProperty(value = "相对湿度下限（%）")
    private BigDecimal humidityLowLimit;

    /**
     * 相对湿度上限（%）
     */
    @ApiModelProperty(value = "相对湿度上限（%）")
    private BigDecimal humidityHighLimit;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
	 
	 
	 
	public Long getWarehouseAreaId() {
		return warehouseAreaId;
	}
	public void setWarehouseAreaId(Long warehouseAreaId) {
		this.warehouseAreaId = warehouseAreaId;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	

}