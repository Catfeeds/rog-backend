package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by ST on 2017/9/1.
 */
public class RequestWarehouseAreaUpdateVO {

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称",required = true)
    private String name;

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
     * 面积（㎡）
     */
    @ApiModelProperty(value = "面积（㎡）")
    private BigDecimal acreage;

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

    @ApiModelProperty(value = "负责人id")
    private Long  managerId;

    @ApiModelProperty(value = "负责人编码")
    private String  managerCode;

    @ApiModelProperty(value = "负责人名称")
    private String  managerName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
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

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}