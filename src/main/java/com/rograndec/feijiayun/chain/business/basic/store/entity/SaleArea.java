package com.rograndec.feijiayun.chain.business.basic.store.entity;

import java.io.Serializable;
import java.util.Date;

public class SaleArea implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 上级销售片区ID
     */
    private Long parentAreaId;

    /**
     * 企业（总部）ID
     */
    private Long enterpriseId;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 状态（0-禁用；1-启用）
     */
    private Integer status;

    /**
     * 备注
     */
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
     * saas_sale_area
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
     * 上级销售片区ID
     * @return parent_area_id 上级销售片区ID
     */
    public Long getParentAreaId() {
        return parentAreaId;
    }

    /**
     * 上级销售片区ID
     * @param parentAreaId 上级销售片区ID
     */
    public void setParentAreaId(Long parentAreaId) {
        this.parentAreaId = parentAreaId;
    }

    /**
     * 企业（总部）ID
     * @return enterprise_id 企业（总部）ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业（总部）ID
     * @param enterpriseId 企业（总部）ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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
     * 关联门店ID集合
     * @return store_ids 关联门店ID集合
     */

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
        sb.append(", parentAreaId=").append(parentAreaId);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", level=").append(level);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaleArea saleArea = (SaleArea) o;

        if (id != null ? !id.equals(saleArea.id) : saleArea.id != null) return false;
        if (parentAreaId != null ? !parentAreaId.equals(saleArea.parentAreaId) : saleArea.parentAreaId != null)
            return false;
        if (enterpriseId != null ? !enterpriseId.equals(saleArea.enterpriseId) : saleArea.enterpriseId != null)
            return false;
        if (code != null ? !code.equals(saleArea.code) : saleArea.code != null) return false;
        if (name != null ? !name.equals(saleArea.name) : saleArea.name != null) return false;
        if (level != null ? !level.equals(saleArea.level) : saleArea.level != null) return false;
        if (status != null ? !status.equals(saleArea.status) : saleArea.status != null) return false;
        if (remark != null ? !remark.equals(saleArea.remark) : saleArea.remark != null) return false;
        if (createrId != null ? !createrId.equals(saleArea.createrId) : saleArea.createrId != null) return false;
        if (createrCode != null ? !createrCode.equals(saleArea.createrCode) : saleArea.createrCode != null)
            return false;
        if (createrName != null ? !createrName.equals(saleArea.createrName) : saleArea.createrName != null)
            return false;
        if (createTime != null ? !createTime.equals(saleArea.createTime) : saleArea.createTime != null) return false;
        if (modifierId != null ? !modifierId.equals(saleArea.modifierId) : saleArea.modifierId != null) return false;
        if (modifierCode != null ? !modifierCode.equals(saleArea.modifierCode) : saleArea.modifierCode != null)
            return false;
        if (modifierName != null ? !modifierName.equals(saleArea.modifierName) : saleArea.modifierName != null)
            return false;
        return updateTime != null ? updateTime.equals(saleArea.updateTime) : saleArea.updateTime == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (parentAreaId != null ? parentAreaId.hashCode() : 0);
        result = 31 * result + (enterpriseId != null ? enterpriseId.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (createrId != null ? createrId.hashCode() : 0);
        result = 31 * result + (createrCode != null ? createrCode.hashCode() : 0);
        result = 31 * result + (createrName != null ? createrName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (modifierId != null ? modifierId.hashCode() : 0);
        result = 31 * result + (modifierCode != null ? modifierCode.hashCode() : 0);
        result = 31 * result + (modifierName != null ? modifierName.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}