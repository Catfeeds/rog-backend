package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by ST on 2017/9/1.
 */
public class RequestWarehouseUpdateVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    /**
     * 名称
     */
    @ApiModelProperty(value = "名称",required = true)
    private String name;

    /**
     * 性质（0-自营；1-第三方）
     */
    @ApiModelProperty(value = "性质（0-自营；1-第三方）")
    private Integer nature;

    /**
     * 面积（㎡）
     */
    @ApiModelProperty(value = "面积（㎡）")
    private BigDecimal acreage;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     * 库区编码规则，默认2
     */
    @ApiModelProperty(value = "库区编码规则，默认2",required = true)
    private Integer areaCodeLength;

    /**
     * 货区编码规则
     */
    @ApiModelProperty(value = "货区编码规则",required = true)
    private Integer cargoAreaCodeLength;

    /**
     * 货位编码规则
     */
    @ApiModelProperty(value = "货位编码规则",required = true)
    private Integer shelfCodeLength;

    /**
     * 间隔符
     */
    @ApiModelProperty(value = "间隔符")
    private String spacer;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）",required = true)
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "负责人id",required = true)
    private Long  managerId;

    @ApiModelProperty(value = "负责人编码",required = true)
    private String  managerCode;

    @ApiModelProperty(value = "负责人名称",required = true)
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

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAreaCodeLength() {
        return areaCodeLength;
    }

    public void setAreaCodeLength(Integer areaCodeLength) {
        this.areaCodeLength = areaCodeLength;
    }

    public Integer getCargoAreaCodeLength() {
        return cargoAreaCodeLength;
    }

    public void setCargoAreaCodeLength(Integer cargoAreaCodeLength) {
        this.cargoAreaCodeLength = cargoAreaCodeLength;
    }

    public Integer getShelfCodeLength() {
        return shelfCodeLength;
    }

    public void setShelfCodeLength(Integer shelfCodeLength) {
        this.shelfCodeLength = shelfCodeLength;
    }

    public String getSpacer() {
        return spacer;
    }

    public void setSpacer(String spacer) {
        this.spacer = spacer;
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