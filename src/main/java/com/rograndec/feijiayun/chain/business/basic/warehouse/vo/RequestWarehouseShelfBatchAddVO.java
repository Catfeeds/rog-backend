package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by ST on 2017/9/1.
 */
public class RequestWarehouseShelfBatchAddVO {

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID",required = true)
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID",required = true)
    private Long parentId;
    /**
     * 仓库ID
     */
    @ApiModelProperty(value = "仓库ID",required = true)
    private Long warehouseId;

    /**
     * 库区ID
     */
    @ApiModelProperty(value = "库区ID",required = true)
    private Long warehouseAreaId;

    /**
     * 货区ID
     */
    @ApiModelProperty(value = "货区ID",required = true)
    private Long cargoAreaId;


    /**
     * 编码前缀
     */
    @ApiModelProperty(value = "编码前缀",required = true)
    private String prefix;

    @ApiModelProperty(value = "起始编码",required = true)
    private String startCode;

    @ApiModelProperty(value = "结束编码",required = true)
    private String endCode;

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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
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

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }
}