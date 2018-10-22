package com.rograndec.feijiayun.chain.business.storage.inventory.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_inventory
 * 
 * 
 * @author ST
 * 
 * 2017-09-29
 */
public class InventoryForOrderDetailVO implements Serializable {
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


    /**
     * 盘点创建日期
     */
    @ApiModelProperty(value = "盘点日期")
    private Date invDate;

    /**
     * 登记日期
     */
    @ApiModelProperty(value = "登记日期")
    private Date registerDate;

    /**
     * 登记人员ID
     */
    @ApiModelProperty(value = "登记人员ID")
    private Long registerManId;

    /**
     * 登记人员名称
     */
    @ApiModelProperty(value = "登记人员名称")
    private String registerManName;

    /**
     * 登记人员名称
     */
    @ApiModelProperty(value = "登记方法登记方法(0-按账面登记；1-按实物登记)")
    private Integer registerType;

    /**
     * 登记人员ID
     */
    @ApiModelProperty(value = "盘点人员ID")
    private Long invManId;

    /**
     * 盘点人员名称
     */
    @ApiModelProperty(value = "盘点人员名称")
    private String invManName;

    /**
     * 复盘人员ID
     */
    @ApiModelProperty(value = "复盘人员ID")
    private Long secondInvManId;

    /**
     * 复盘人员名称
     */
    @ApiModelProperty(value = "复盘人员名称")
    private String secondInvManName;

    @ApiModelProperty("rediskey(值)")
    private String redisKeyValue;


    private List<InventoryDetailForOrderDetailVO> detailForAddVOList;

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

    public List<InventoryDetailForOrderDetailVO> getDetailForAddVOList() {
        return detailForAddVOList;
    }

    public void setDetailForAddVOList(List<InventoryDetailForOrderDetailVO> detailForAddVOList) {
        this.detailForAddVOList = detailForAddVOList;
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Long getRegisterManId() {
        return registerManId;
    }

    public void setRegisterManId(Long registerManId) {
        this.registerManId = registerManId;
    }

    public String getRegisterManName() {
        return registerManName;
    }

    public void setRegisterManName(String registerManName) {
        this.registerManName = registerManName;
    }

    public Integer getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }

    public Long getInvManId() {
        return invManId;
    }

    public void setInvManId(Long invManId) {
        this.invManId = invManId;
    }

    public String getInvManName() {
        return invManName;
    }

    public void setInvManName(String invManName) {
        this.invManName = invManName;
    }

    public Long getSecondInvManId() {
        return secondInvManId;
    }

    public void setSecondInvManId(Long secondInvManId) {
        this.secondInvManId = secondInvManId;
    }

    public String getSecondInvManName() {
        return secondInvManName;
    }

    public void setSecondInvManName(String secondInvManName) {
        this.secondInvManName = secondInvManName;
    }

    public String getRedisKeyValue() {
        return redisKeyValue;
    }

    public void setRedisKeyValue(String redisKeyValue) {
        this.redisKeyValue = redisKeyValue;
    }
}