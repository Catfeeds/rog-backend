package com.rograndec.feijiayun.chain.business.storage.storehouse.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_temperature_humidity
 * 添加记录数据时使用
 * 
 * @author DD
 * 
 * 2017-09-27
 */
public class TemperatureHumidityVO implements Serializable {

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
     * 记录人员编码
     */
    @ApiModelProperty(value = "记录人员编码")
    private String recordManCode;

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
     * 仓库名称
     */
    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;

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
     * 附件ID
     */
    @ApiModelProperty(value = "附件ID")
    private Long fileId;

    /**
     * 状态
     */
   /* @ApiModelProperty(value = "状态")
    private Integer status;*/

    /**
     * 备注
     */
   /* @ApiModelProperty(value = "备注")
    private String remark;*/


    /**
     * saas_temperature_humidity
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 每行的记录信息
     */
    @ApiModelProperty(value = "具体的每条信息")
    private List<TemperatureHumidityDetailVO> detailList;

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
     * 记录人员ID
     * @return record_man_id 记录人员ID
     */
    public Long getRecordManId() {
        return recordManId;
    }

    /**
     * 记录人员ID
     * @param recordManId 记录人员ID
     */
    public void setRecordManId(Long recordManId) {
        this.recordManId = recordManId;
    }

    /**
     * 记录人员编码
     * @return record_man_code 记录人员编码
     */
    public String getRecordManCode() {
        return recordManCode;
    }

    /**
     * 记录人员编码
     * @param recordManCode 记录人员编码
     */
    public void setRecordManCode(String recordManCode) {
        this.recordManCode = recordManCode == null ? null : recordManCode.trim();
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
     * 仓库ID
     * @return warehouse_id 仓库ID
     */
    public Long getWarehouseId() {
        return warehouseId;
    }

    /**
     * 仓库ID
     * @param warehouseId 仓库ID
     */
    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
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
     * 库区ID
     * @return warehouse_area_id 库区ID
     */
    public Long getWarehouseAreaId() {
        return warehouseAreaId;
    }

    /**
     * 库区ID
     * @param warehouseAreaId 库区ID
     */
    public void setWarehouseAreaId(Long warehouseAreaId) {
        this.warehouseAreaId = warehouseAreaId;
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

    /**
     * 状态
     * @return status 状态
     */
  /*  public Integer getStatus() {
        return status;
    }*/

    /**
     * 状态
     * @param status 状态
     */
   /* public void setStatus(Integer status) {
        this.status = status;
    }
*/
    /**
     * 备注
     * @return remark 备注
     */
   /* public String getRemark() {
        return remark;
    }*/

    /**
     * 备注
     * @param remark 备注
     */
    /*public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
*/
    
    public List<TemperatureHumidityDetailVO> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<TemperatureHumidityDetailVO> detailList) {
		this.detailList = detailList;
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
        sb.append(", recordManId=").append(recordManId);
        sb.append(", recordManCode=").append(recordManCode);
        sb.append(", recordManName=").append(recordManName);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", warehouseName=").append(warehouseName);
        sb.append(", warehouseAreaId=").append(warehouseAreaId);
        sb.append(", warehouseAreaName=").append(warehouseAreaName);
        sb.append(", fileId=").append(fileId);
       /* sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);*/
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}