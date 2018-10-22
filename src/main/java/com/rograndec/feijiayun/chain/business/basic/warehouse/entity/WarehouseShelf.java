package com.rograndec.feijiayun.chain.business.basic.warehouse.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WarehouseShelf implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
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
     * 库区ID
     */
    @ApiModelProperty(value = "库区ID")
    private Long warehouseAreaId;

    /**
     * 货区ID
     */
    @ApiModelProperty(value = "货区ID")
    private Long cargoAreaId;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "0：用户自定义部门；1-系统默认部门")
    private Integer sysType;

    /**
     * 编码前缀
     */
    @ApiModelProperty(value = "编码前缀")
    private String prefix;

    @ApiModelProperty(value = "起始编码")
    private String startCode;

    @ApiModelProperty(value = "结束编码")
    private String endCode;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 数量上限
     */
    @ApiModelProperty(value = "数量上限")
    private BigDecimal quantityLimit;

    /**
     * 重量上限（g）
     */
    @ApiModelProperty(value = "重量上限（g）")
    private BigDecimal weightLimit;

    /**
     * 体积-长上限（cm）
     */
    @ApiModelProperty(value = "体积-长上限（cm）")
    private BigDecimal lengthLimit;

    /**
     * 体积-宽上限（cm）
     */
    @ApiModelProperty(value = "体积-宽上限（cm）")
    private BigDecimal widthLimit;

    /**
     * 体积-高上限（cm）
     */
    @ApiModelProperty(value = "体积-高上限（cm）")
    private BigDecimal highLimit;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）")
    private Integer status;

    @ApiModelProperty(value = "所在层级")
    private Integer layer;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

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
     * saas_warehouse_shelf
     */
    private static final long serialVersionUID = 1L;

    public static List<Long> getCargoAreaIds(List<WarehouseShelf> warehouseShelfs){

        Set<Long> set = warehouseShelfs.stream().map(l -> l.getCargoAreaId()).filter(gId -> null != gId).collect(Collectors.toSet());

        return set.stream().collect(Collectors.toList());

    }

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

    /**
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
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
     * 货区ID
     * @return cargo_area_id 货区ID
     */
    public Long getCargoAreaId() {
        return cargoAreaId;
    }

    /**
     * 货区ID
     * @param cargoAreaId 货区ID
     */
    public void setCargoAreaId(Long cargoAreaId) {
        this.cargoAreaId = cargoAreaId;
    }

    /**
     * 编码
     * @return code 编码
     */
    public String getCode() {
        return code;
    }

    public String getStartCode() {
        return startCode;
    }

    public void setStartCode(String startCode) {
        this.startCode = startCode;
    }

    public String getEndCode() {
        return endCode;
    }

    public void setEndCode(String endCode) {
        this.endCode = endCode;
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
     * 数量上限
     * @return quantity_limit 数量上限
     */
    public BigDecimal getQuantityLimit() {
        return quantityLimit;
    }

    /**
     * 数量上限
     * @param quantityLimit 数量上限
     */
    public void setQuantityLimit(BigDecimal quantityLimit) {
        this.quantityLimit = quantityLimit;
    }

    /**
     * 重量上限（g）
     * @return weight_limit 重量上限（g）
     */
    public BigDecimal getWeightLimit() {
        return weightLimit;
    }

    /**
     * 重量上限（g）
     * @param weightLimit 重量上限（g）
     */
    public void setWeightLimit(BigDecimal weightLimit) {
        this.weightLimit = weightLimit;
    }

    /**
     * 体积-长上限（cm）
     * @return length_limit 体积-长上限（cm）
     */
    public BigDecimal getLengthLimit() {
        return lengthLimit;
    }

    /**
     * 体积-长上限（cm）
     * @param lengthLimit 体积-长上限（cm）
     */
    public void setLengthLimit(BigDecimal lengthLimit) {
        this.lengthLimit = lengthLimit;
    }

    /**
     * 体积-宽上限（cm）
     * @return width_limit 体积-宽上限（cm）
     */
    public BigDecimal getWidthLimit() {
        return widthLimit;
    }

    /**
     * 体积-宽上限（cm）
     * @param widthLimit 体积-宽上限（cm）
     */
    public void setWidthLimit(BigDecimal widthLimit) {
        this.widthLimit = widthLimit;
    }

    /**
     * 体积-高上限（cm）
     * @return high_limit 体积-高上限（cm）
     */
    public BigDecimal getHighLimit() {
        return highLimit;
    }

    /**
     * 体积-高上限（cm）
     * @param highLimit 体积-高上限（cm）
     */
    public void setHighLimit(BigDecimal highLimit) {
        this.highLimit = highLimit;
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

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
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
        sb.append(", warehouseAreaId=").append(warehouseAreaId);
        sb.append(", cargoAreaId=").append(cargoAreaId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", quantityLimit=").append(quantityLimit);
        sb.append(", weightLimit=").append(weightLimit);
        sb.append(", lengthLimit=").append(lengthLimit);
        sb.append(", widthLimit=").append(widthLimit);
        sb.append(", highLimit=").append(highLimit);
        sb.append(", status=").append(status);
        sb.append(", layer=").append(layer);
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