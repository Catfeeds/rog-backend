package com.rograndec.feijiayun.chain.business.storage.inventory.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * saas_inventory
 * 
 * 
 * @author ST
 * 
 * 2017-09-29
 */
public class InventoryForOrderListVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    /**
     * 盘点创建日期
     */
    @ApiModelProperty(value = "盘点创建日期")
    private Date createTime;
    //创建人

    /**
     * 盘点单号
     */
    @ApiModelProperty(value = "盘点单号")
    private String code;

    /**
     * 盘点方法（0-按货位；1-按商品）
     */
    @ApiModelProperty(value = "盘点方法（0-按货位；1-按商品）")
    private Integer invType;

    /**
     * 盘点范围（0-全盘；1-抽盘）
     */
    @ApiModelProperty(value = "盘点范围（0-全盘；1-抽盘）")
    private Integer invRange;

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
    private String warehouseAreaIds;

    /**
     * 库区名称
     */
    @ApiModelProperty(value = "库区名称")
    private String warehouseAreaNames;

    /**
     * 货区／柜组ID
     */
    @ApiModelProperty(value = "货区／柜组ID")
    private String cargoAreaIds;

    /**
     * 货区／柜组名称
     */
    @ApiModelProperty(value = "货区／柜组名称")
    private String cargoAreaNames;

    /**
     * 状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）
     */
    @ApiModelProperty(value = "状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createrName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getInvType() {
        return invType;
    }

    public void setInvType(Integer invType) {
        this.invType = invType;
    }

    public Integer getInvRange() {
        return invRange;
    }

    public void setInvRange(Integer invRange) {
        this.invRange = invRange;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseAreaIds() {
        return warehouseAreaIds;
    }

    public void setWarehouseAreaIds(String warehouseAreaIds) {
        this.warehouseAreaIds = warehouseAreaIds;
    }

    public String getWarehouseAreaNames() {
        return warehouseAreaNames;
    }

    public void setWarehouseAreaNames(String warehouseAreaNames) {
        this.warehouseAreaNames = warehouseAreaNames;
    }

    public String getCargoAreaIds() {
        return cargoAreaIds;
    }

    public void setCargoAreaIds(String cargoAreaIds) {
        this.cargoAreaIds = cargoAreaIds;
    }

    public String getCargoAreaNames() {
        return cargoAreaNames;
    }

    public void setCargoAreaNames(String cargoAreaNames) {
        this.cargoAreaNames = cargoAreaNames;
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

    public Long getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    public String getCreaterCode() {
        return createrCode;
    }

    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }
}