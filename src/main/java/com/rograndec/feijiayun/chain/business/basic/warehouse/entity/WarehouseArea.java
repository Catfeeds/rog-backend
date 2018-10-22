package com.rograndec.feijiayun.chain.business.basic.warehouse.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 库区
 */
public class WarehouseArea implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 仓库ID
     */
    @ApiModelProperty(value = "仓库ID")
    private Long warehouseId;

    /**
     * 仓库Name
     */
    @ApiModelProperty(value = "仓库Name")
    private String warehouseName;
    @ApiModelProperty(value = "0：用户自定义部门；1-系统默认部门")
    private Integer sysType;

    /**
     * 类型（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    @ApiModelProperty(value = "类型（0-常温；1-阴凉；2-冷藏；3-冷冻）")
    private Integer type;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

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

    /**
     * 创建人ID
     */
    private Long createrId;

    /**
     * 创建人编码
     */
    private String createrCode;

    /**
     * 创建人名称
     */
    private String createrName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人ID
     */
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    private String modifierName;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * saas_warehouse_area
     */
    private static final long serialVersionUID = 1L;



    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
     * 类型（0-常温；1-阴凉；2-冷藏；3-冷冻）
     * @return type 类型（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型（0-常温；1-阴凉；2-冷藏；3-冷冻）
     * @param type 类型（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 编码
     * @return code 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 编码
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 温度下限（℃）
     * @return temp_low_Limit 温度下限（℃）
     */
    public BigDecimal getTempLowLimit() {
        return tempLowLimit;
    }

    /**
     * 温度下限（℃）
     * @param tempLowLimit 温度下限（℃）
     */
    public void setTempLowLimit(BigDecimal tempLowLimit) {
        this.tempLowLimit = tempLowLimit;
    }

    /**
     * 温度上限（℃）
     * @return temp_high_Limit 温度上限（℃）
     */
    public BigDecimal getTempHighLimit() {
        return tempHighLimit;
    }

    /**
     * 温度上限（℃）
     * @param tempHighLimit 温度上限（℃）
     */
    public void setTempHighLimit(BigDecimal tempHighLimit) {
        this.tempHighLimit = tempHighLimit;
    }

    /**
     * 相对湿度下限（%）
     * @return humidity_low_limt 相对湿度下限（%）
     */
    public BigDecimal getHumidityLowLimit() {
        return humidityLowLimit;
    }

    /**
     * 相对湿度下限（%）
     * @param humidityLowLimit 相对湿度下限（%）
     */
    public void setHumidityLowLimit(BigDecimal humidityLowLimit) {
        this.humidityLowLimit = humidityLowLimit;
    }

    /**
     * 相对湿度上限（%）
     * @return humidity_high_limt 相对湿度上限（%）
     */
    public BigDecimal getHumidityHighLimit() {
        return humidityHighLimit;
    }

    /**
     * 相对湿度上限（%）
     * @param humidityHighLimit 相对湿度上限（%）
     */
    public void setHumidityHighLimit(BigDecimal humidityHighLimit) {
        this.humidityHighLimit = humidityHighLimit;
    }

    /**
     * 面积（㎡）
     * @return acreage 面积（㎡）
     */
    public BigDecimal getAcreage() {
        return acreage;
    }

    /**
     * 面积（㎡）
     * @param acreage 面积（㎡）
     */
    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @return status 状态（0-禁用；1-启用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @param status 状态（0-禁用；1-启用）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
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
        sb.append(", id=").append(id);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", warehouseId=").append(warehouseId);
        sb.append(", type=").append(type);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", tempLowLimit=").append(tempLowLimit);
        sb.append(", tempHighLimit=").append(tempHighLimit);
        sb.append(", humidityLowLimit=").append(humidityLowLimit);
        sb.append(", humidityHighLimit=").append(humidityHighLimit);
        sb.append(", acreage=").append(acreage);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }
}