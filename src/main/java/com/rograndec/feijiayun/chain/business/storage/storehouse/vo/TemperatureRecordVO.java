package com.rograndec.feijiayun.chain.business.storage.storehouse.vo;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by DD on 2017/9/26.
 */
public class TemperatureRecordVO {
	
	
	 /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    
    /**
     * 记录日期
     */
    @ApiModelProperty(value = "记录日期")
    private Date recordDate;
    
    /**
     * 记录人员名称
     */
    @ApiModelProperty(value = "记录人员名称")
    private String recordManName;
    
    /**
     * 仓库名称
     */
    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
    
    


}