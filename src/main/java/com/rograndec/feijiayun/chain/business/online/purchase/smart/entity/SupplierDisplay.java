package com.rograndec.feijiayun.chain.business.online.purchase.smart.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_supplier_display
 * 
 * 
 * @author Administrator
 * 
 * 2017-11-22
 */
public class SupplierDisplay implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String bewrite;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private Date configDate;

    /**
     * 所属区域（省）
     */
    @ApiModelProperty(value = "所属区域（省）")
    private Integer provinceId;

    /**
     * 所属区域（省）
     */
    @ApiModelProperty(value = "所属区域（省）")
    private String provinceName;

    /**
     * 所属区域（市）
     */
    @ApiModelProperty(value = "所属区域（市）")
    private Integer cityId;

    /**
     * 所属区域（市）
     */
    @ApiModelProperty(value = "所属区域（市）")
    private String cityName;

    /**
     * 所属区域（区）
     */
    @ApiModelProperty(value = "所属区域（区）")
    private Integer areaId;

    /**
     * 所属区域（区）
     */
    @ApiModelProperty(value = "所属区域（区）")
    private String areaName;

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
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * saas_supplier_display
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * @return id ID
     */
    public Long getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 描述
     * @return bewrite 描述
     */
    public String getBewrite() {
        return bewrite;
    }

    /**
     * 描述
     * @param bewrite 描述
     */
    public void setBewrite(String bewrite) {
        this.bewrite = bewrite == null ? null : bewrite.trim();
    }

    /**
     * 日期
     * @return config_date 日期
     */
    public Date getConfigDate() {
        return configDate;
    }

    /**
     * 日期
     * @param configDate 日期
     */
    public void setConfigDate(Date configDate) {
        this.configDate = configDate;
    }

    /**
     * 所属区域（省）
     * @return province_id 所属区域（省）
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * 所属区域（省）
     * @param provinceId 所属区域（省）
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 所属区域（省）
     * @return province_name 所属区域（省）
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 所属区域（省）
     * @param provinceName 所属区域（省）
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * 所属区域（市）
     * @return city_id 所属区域（市）
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 所属区域（市）
     * @param cityId 所属区域（市）
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 所属区域（市）
     * @return city_name 所属区域（市）
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 所属区域（市）
     * @param cityName 所属区域（市）
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * 所属区域（区）
     * @return area_id 所属区域（区）
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * 所属区域（区）
     * @param areaId 所属区域（区）
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * 所属区域（区）
     * @return area_name 所属区域（区）
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 所属区域（区）
     * @param areaName 所属区域（区）
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
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
        sb.append(", bewrite=").append(bewrite);
        sb.append(", configDate=").append(configDate);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", provinceName=").append(provinceName);
        sb.append(", cityId=").append(cityId);
        sb.append(", cityName=").append(cityName);
        sb.append(", areaId=").append(areaId);
        sb.append(", areaName=").append(areaName);
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
}