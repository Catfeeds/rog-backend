package com.rograndec.feijiayun.chain.business.storage.storehouse.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_temperature_humidity
 * 
 * 返回详情页使用
 * @author DD
 * 
 * 2017-09-27
 */
public class ResponseTempHumVO implements Serializable {
	
	 /**
     * 主键ID
     */
    @ApiModelProperty(value = "温湿度记录单主键ID")
    private Long recordId;
    
    /**
     * 记录日期
     */
    @ApiModelProperty(value = "记录日期")
    private Date recordDate;

    /**
     * 记录人员ID
     */
    @ApiModelProperty(value = "记录人员ID")
    private Long recordManId;

    /**
     * 记录人员名称
     */
    @ApiModelProperty(value = "记录人员名称")
    private String recordManName;
    
    /**
     * 仓库ID
     */
    @ApiModelProperty(value = "仓库ID")
    private Long warehouseId;

    /**
     * 库区ID
     */
    @ApiModelProperty(value = "库区ID")
    private Long warehouseAreaId;

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
     * 附件ID
     */
    @ApiModelProperty(value = "附件ID")
    private Long fileId;
    
    /**
     * 附件的七牛文件名
     */
    @ApiModelProperty(value = "附件的七牛文件名")
    private String fileKey;

	/**
     * saas_temperature_humidity
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 每行的记录信息
     */
    @ApiModelProperty(value = "具体的每条信息")
    private List<ResponseTempHumDetVO> detailList;
    
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

    public Long getRecordManId() {
		return recordManId;
	}

	public void setRecordManId(Long recordManId) {
		this.recordManId = recordManId;
	}

	public Long getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Long getWarehouseAreaId() {
		return warehouseAreaId;
	}

	public void setWarehouseAreaId(Long warehouseAreaId) {
		this.warehouseAreaId = warehouseAreaId;
	}

	/**
     * 记录日期
     * @return record_date 记录日期
     */
    public Date getRecordDate() {
        return recordDate;
    }

    /**
     * 记录日期
     * @param recordDate 记录日期
     */
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }


    /**
     * 记录人员名称
     * @return record_man_name 记录人员名称
     */
    public String getRecordManName() {
        return recordManName;
    }

    /**
     * 记录人员名称
     * @param recordManName 记录人员名称
     */
    public void setRecordManName(String recordManName) {
        this.recordManName = recordManName == null ? null : recordManName.trim();
    }

    /**
     * 仓库名称
     * @return warehouse_name 仓库名称
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * 仓库名称
     * @param warehouseName 仓库名称
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName == null ? null : warehouseName.trim();
    }

    /**
     * 库区名称
     * @return warehouse_area_name 库区名称
     */
    public String getWarehouseAreaName() {
        return warehouseAreaName;
    }

    /**
     * 库区名称
     * @param warehouseAreaName 库区名称
     */
    public void setWarehouseAreaName(String warehouseAreaName) {
        this.warehouseAreaName = warehouseAreaName == null ? null : warehouseAreaName.trim();
    }

    /**
     * 附件ID
     * @return file_id 附件ID
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * 附件ID
     * @param fileId 附件ID
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
	public Long getRecordId() {
		return recordId;
	}

    public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public List<ResponseTempHumDetVO> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ResponseTempHumDetVO> detailList) {
		this.detailList = detailList;
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

	/**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recordDate=").append(recordDate);
        sb.append(", recordManName=").append(recordManName);
        sb.append(", warehouseName=").append(warehouseName);
        sb.append(", warehouseAreaName=").append(warehouseAreaName);
        sb.append(", fileId=").append(fileId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}