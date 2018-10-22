package com.rograndec.feijiayun.chain.business.basic.warehouse.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Warehouse implements Serializable {
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

    @ApiModelProperty(value = "0：用户自定义部门；1-系统默认部门")
    private Integer sysType;

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
    @ApiModelProperty(value = "状态（0-禁用；1-启用）")
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
     * saas_warehouse
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
     * 性质（0-自营；1-第三方）
     * @return nature 性质（0-自营；1-第三方）
     */
    public Integer getNature() {
        return nature;
    }

    /**
     * 性质（0-自营；1-第三方）
     * @param nature 性质（0-自营；1-第三方）
     */
    public void setNature(Integer nature) {
        this.nature = nature;
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
     * 地址
     * @return address 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 地址
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 库区编码规则，默认2
     * @return area_code_length 库区编码规则，默认2
     */
    public Integer getAreaCodeLength() {
        return areaCodeLength;
    }

    /**
     * 库区编码规则，默认2
     * @param areaCodeLength 库区编码规则，默认2
     */
    public void setAreaCodeLength(Integer areaCodeLength) {
        this.areaCodeLength = areaCodeLength;
    }

    /**
     * 货区编码规则
     * @return cargo_area_code_length 货区编码规则
     */
    public Integer getCargoAreaCodeLength() {
        return cargoAreaCodeLength;
    }

    /**
     * 货区编码规则
     * @param cargoAreaCodeLength 货区编码规则
     */
    public void setCargoAreaCodeLength(Integer cargoAreaCodeLength) {
        this.cargoAreaCodeLength = cargoAreaCodeLength;
    }

    public Integer getShelfCodeLength() {
        return shelfCodeLength;
    }

    public void setShelfCodeLength(Integer shelfCodeLength) {
        this.shelfCodeLength = shelfCodeLength;
    }

    /**
     * 间隔符
     * @return spacer 间隔符
     */
    public String getSpacer() {
        return spacer;
    }

    /**
     * 间隔符
     * @param spacer 间隔符
     */
    public void setSpacer(String spacer) {
        this.spacer = spacer == null ? null : spacer.trim();
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
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", nature=").append(nature);
        sb.append(", acreage=").append(acreage);
        sb.append(", address=").append(address);
        sb.append(", areaCodeLength=").append(areaCodeLength);
        sb.append(", cargoAreaCodeLength=").append(cargoAreaCodeLength);
        sb.append(", shelfCodeLength=").append(shelfCodeLength);
        sb.append(", spacer=").append(spacer);
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