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
public class InventoryForAddVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


//    /**
//     * 单据类型（5301）
//     */
//    @ApiModelProperty(value = "单据类型（5301）")
//    private Integer orderType;

//    /**
//     * 盘点日期
//     */
//    @ApiModelProperty(value = "盘点日期")
//    private Date invDate;

//    /**
//     * 盘点单号
//     */
//    @ApiModelProperty(value = "盘点单号")
//    private String code;

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
     * 货区／柜组ID
     */
    @ApiModelProperty(value = "货区／柜组ID")
    private String cargoAreaIds;


//    /**
//     * 登记日期
//     */
//    @ApiModelProperty(value = "登记日期")
//    private Date registerDate;
//
//    /**
//     * 登记人员ID
//     */
//    @ApiModelProperty(value = "登记人员ID")
//    private Long registerManId;
//
//    /**
//     * 登记人员编码
//     */
//    @ApiModelProperty(value = "登记人员编码")
//    private String registerManCode;
//
//    /**
//     * 登记人员名称
//     */
//    @ApiModelProperty(value = "登记人员名称")
//    private String registerManName;
//
//    /**
//     * 登记方法
//     */
//    @ApiModelProperty(value = "登记方法(0-按账面登记；1-按实物登记)")
//    private Integer registerType;
//
//    /**
//     * 盘点人员ID
//     */
//    @ApiModelProperty(value = "盘点人员ID")
//    private Long invManId;
//
//    /**
//     * 盘点人员编码
//     */
//    @ApiModelProperty(value = "盘点人员编码")
//    private String invManCode;
//
//    /**
//     * 盘点人员名称
//     */
//    @ApiModelProperty(value = "盘点人员名称")
//    private String invManName;
//
//
//    /**
//     * 复盘人员ID
//     */
//    @ApiModelProperty(value = "复盘人员ID")
//    private Long secondInvManId;
//
//    /**
//     * 复盘人员编码
//     */
//    @ApiModelProperty(value = "复盘人员编码")
//    private String secondInvManCode;
//
//    /**
//     * 复盘人员名称
//     */
//    @ApiModelProperty(value = "复盘人员名称")
//    private String secondInvManName;
//
//    /**
//     * 处理日期
//     */
//    @ApiModelProperty(value = "处理日期")
//    private Date handleDate;
//
//    /**
//     * 处理人员ID
//     */
//    @ApiModelProperty(value = "处理人员ID")
//    private Long handleManId;
//
//    /**
//     * 处理人员编码
//     */
//    @ApiModelProperty(value = "处理人员编码")
//    private String handleManCode;
//
//    /**
//     * 处理人员名称
//     */
//    @ApiModelProperty(value = "处理人员名称")
//    private String handleManName;
//
//    /**
//     * 过账日期
//     */
//    @ApiModelProperty(value = "过账日期")
//    private Date postDate;
//
//    /**
//     * 过账人员ID
//     */
//    @ApiModelProperty(value = "过账人员ID")
//    private Long postManId;
//
//    /**
//     * 过账人员编码
//     */
//    @ApiModelProperty(value = "过账人员编码")
//    private String postManCode;
//
//    /**
//     * 过账人员名称
//     */
//    @ApiModelProperty(value = "过账人员名称")
//    private String postManName;



//    /**
//     * 状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）
//     */
//    @ApiModelProperty(value = "状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）")
//    private Integer status;

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
     * 创建人名称
     */
    @ApiModelProperty(value = "创建日期")
    private Date createTime;



    private List<InventoryDetailForAddVO> detailForAddVOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Integer getOrderType() {
//        return orderType;
//    }
//
//    public void setOrderType(Integer orderType) {
//        this.orderType = orderType;
//    }
//
//    public Date getInvDate() {
//        return invDate;
//    }
//
//    public void setInvDate(Date invDate) {
//        this.invDate = invDate;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }

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

//    public String getWarehouseAreaNames() {
//        return warehouseAreaNames;
//    }
//
//    public void setWarehouseAreaNames(String warehouseAreaNames) {
//        this.warehouseAreaNames = warehouseAreaNames;
//    }
//
//    public String getCargoAreaNames() {
//        return cargoAreaNames;
//    }
//
//    public void setCargoAreaNames(String cargoAreaNames) {
//        this.cargoAreaNames = cargoAreaNames;
//    }

    public String getCargoAreaIds() {
        return cargoAreaIds;
    }

    public void setCargoAreaIds(String cargoAreaIds) {
        this.cargoAreaIds = cargoAreaIds;
    }



//    public Date getRegisterDate() {
//        return registerDate;
//    }
//
//    public void setRegisterDate(Date registerDate) {
//        this.registerDate = registerDate;
//    }
//
//    public Long getRegisterManId() {
//        return registerManId;
//    }
//
//    public void setRegisterManId(Long registerManId) {
//        this.registerManId = registerManId;
//    }
//
//    public String getRegisterManCode() {
//        return registerManCode;
//    }
//
//    public void setRegisterManCode(String registerManCode) {
//        this.registerManCode = registerManCode;
//    }
//
//    public String getRegisterManName() {
//        return registerManName;
//    }
//
//    public void setRegisterManName(String registerManName) {
//        this.registerManName = registerManName;
//    }
//
//    public Long getInvManId() {
//        return invManId;
//    }
//
//    public void setInvManId(Long invManId) {
//        this.invManId = invManId;
//    }
//
//    public String getInvManCode() {
//        return invManCode;
//    }
//
//    public void setInvManCode(String invManCode) {
//        this.invManCode = invManCode;
//    }
//
//    public String getInvManName() {
//        return invManName;
//    }
//
//    public void setInvManName(String invManName) {
//        this.invManName = invManName;
//    }
//
//    public Long getSecondInvManId() {
//        return secondInvManId;
//    }
//
//    public void setSecondInvManId(Long secondInvManId) {
//        this.secondInvManId = secondInvManId;
//    }
//
//    public String getSecondInvManCode() {
//        return secondInvManCode;
//    }
//
//    public void setSecondInvManCode(String secondInvManCode) {
//        this.secondInvManCode = secondInvManCode;
//    }
//
//    public String getSecondInvManName() {
//        return secondInvManName;
//    }
//
//    public void setSecondInvManName(String secondInvManName) {
//        this.secondInvManName = secondInvManName;
//    }
//
//    public Date getHandleDate() {
//        return handleDate;
//    }
//
//    public void setHandleDate(Date handleDate) {
//        this.handleDate = handleDate;
//    }
//
//    public Long getHandleManId() {
//        return handleManId;
//    }
//
//    public void setHandleManId(Long handleManId) {
//        this.handleManId = handleManId;
//    }
//
//    public String getHandleManCode() {
//        return handleManCode;
//    }
//
//    public void setHandleManCode(String handleManCode) {
//        this.handleManCode = handleManCode;
//    }
//
//    public String getHandleManName() {
//        return handleManName;
//    }
//
//    public void setHandleManName(String handleManName) {
//        this.handleManName = handleManName;
//    }
//
//    public Date getPostDate() {
//        return postDate;
//    }
//
//    public void setPostDate(Date postDate) {
//        this.postDate = postDate;
//    }
//
//    public Long getPostManId() {
//        return postManId;
//    }
//
//    public void setPostManId(Long postManId) {
//        this.postManId = postManId;
//    }
//
//    public String getPostManCode() {
//        return postManCode;
//    }
//
//    public void setPostManCode(String postManCode) {
//        this.postManCode = postManCode;
//    }
//
//    public String getPostManName() {
//        return postManName;
//    }
//
//    public void setPostManName(String postManName) {
//        this.postManName = postManName;
//    }


//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }

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

    public List<InventoryDetailForAddVO> getDetailForAddVOList() {
        return detailForAddVOList;
    }

    public void setDetailForAddVOList(List<InventoryDetailForAddVO> detailForAddVOList) {
        this.detailForAddVOList = detailForAddVOList;
    }

//    public Integer getRegisterType() {
//        return registerType;
//    }
//
//    public void setRegisterType(Integer registerType) {
//        this.registerType = registerType;
//    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}