package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by ST on 2017/9/1.
 */
public class RequestWarehouseShelfVO {

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
     * 仓库name
     */
    @ApiModelProperty(value = "仓库name")
    private String warehouseName;

    /**
     * 库区ID
     */
    @ApiModelProperty(value = "库区ID")
    private Long warehouseAreaId;

    /**
     * 库区Name
     */
    @ApiModelProperty(value = "库区Name")
    private String warehouseAreaName;

    /**
     * 货区ID
     */
    @ApiModelProperty(value = "货区ID")
    private Long cargoAreaId;

    /**
     * 货区Name
     */
    @ApiModelProperty(value = "货区Name")
    private String cargoAreaName;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;



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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public Long getCargoAreaId() {
        return cargoAreaId;
    }

    public void setCargoAreaId(Long cargoAreaId) {
        this.cargoAreaId = cargoAreaId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getQuantityLimit() {
        return quantityLimit;
    }

    public void setQuantityLimit(BigDecimal quantityLimit) {
        this.quantityLimit = quantityLimit;
    }

    public BigDecimal getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(BigDecimal weightLimit) {
        this.weightLimit = weightLimit;
    }

    public BigDecimal getLengthLimit() {
        return lengthLimit;
    }

    public void setLengthLimit(BigDecimal lengthLimit) {
        this.lengthLimit = lengthLimit;
    }

    public BigDecimal getWidthLimit() {
        return widthLimit;
    }

    public void setWidthLimit(BigDecimal widthLimit) {
        this.widthLimit = widthLimit;
    }

    public BigDecimal getHighLimit() {
        return highLimit;
    }

    public void setHighLimit(BigDecimal highLimit) {
        this.highLimit = highLimit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getCargoAreaName() {
        return cargoAreaName;
    }

    public void setCargoAreaName(String cargoAreaName) {
        this.cargoAreaName = cargoAreaName;
    }
}