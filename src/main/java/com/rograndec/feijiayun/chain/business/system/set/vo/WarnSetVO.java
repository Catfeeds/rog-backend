package com.rograndec.feijiayun.chain.business.system.set.vo;

import java.io.Serializable;
import java.util.Date;

public class WarnSetVO implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 企业类型（0-总部；1-自营店；2-加盟店）
     */
    private Integer chainType;

    /**
     * 系统默认类型（0-自定义 1-系统默认）
     */
    private Integer sysType;

    /**
     * 设置类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）注：当chain_type=1或2（自营店）时，set_type只能为（0-企业；1-员工；4-库存）三种
     */
    private Integer setType;

    /**
     * 内容
     */
    private String content;

    /**
     * 提前预警天数
     */
    private Integer warnDays;

    /**
     * 设置-状态（0-关闭；1-开启）
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
     * 角色ID集合（多个用逗号分隔）注：chain_type=0（总部）时，一个预警设置，可以同时给总部自身和分店进行角色授权；chain_type=2（加盟店），只能选择分店角色进行授权
     */
    private String roleIds;
    /**
     * 角色Name集合（多个用逗号分隔)
     */
    private String roleNames;

    /**
     * 资质ID
     */
    private Long qualificationId;

    /**
     * saas_warn_set
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
     * 企业类型（0-总部；1-自营店；2-加盟店）
     * @return chain_type 企业类型（0-总部；1-自营店；2-加盟店）
     */
    public Integer getChainType() {
        return chainType;
    }

    /**
     * 企业类型（0-总部；1-自营店；2-加盟店）
     * @param chainType 企业类型（0-总部；1-自营店；2-加盟店）
     */
    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    /**
     * 设置类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）注：当chain_type=1或2（自营店）时，set_type只能为（0-企业；1-员工；4-库存）三种
     * @return set_type 设置类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）注：当chain_type=1或2（自营店）时，set_type只能为（0-企业；1-员工；4-库存）三种
     */
    public Integer getSetType() {
        return setType;
    }

    /**
     * 设置类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）注：当chain_type=1或2（自营店）时，set_type只能为（0-企业；1-员工；4-库存）三种
     * @param setType 设置类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）注：当chain_type=1或2（自营店）时，set_type只能为（0-企业；1-员工；4-库存）三种
     */
    public void setSetType(Integer setType) {
        this.setType = setType;
    }

    /**
     * 内容
     * @return content 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 提前预警天数
     * @return warn_days 提前预警天数
     */
    public Integer getWarnDays() {
        return warnDays;
    }

    /**
     * 提前预警天数
     * @param warnDays 提前预警天数
     */
    public void setWarnDays(Integer warnDays) {
        this.warnDays = warnDays;
    }

    /**
     * 设置-状态（0-关闭；1-开启）
     * @return status 设置-状态（0-关闭；1-开启）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置-状态（0-关闭；1-开启）
     * @param status 设置-状态（0-关闭；1-开启）
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

    /**
     * 角色ID集合（多个用逗号分隔）注：chain_type=0（总部）时，一个预警设置，可以同时给总部自身和分店进行角色授权；chain_type=2（加盟店），只能选择分店角色进行授权
     * @return role_ids 角色ID集合（多个用逗号分隔）注：chain_type=0（总部）时，一个预警设置，可以同时给总部自身和分店进行角色授权；chain_type=2（加盟店），只能选择分店角色进行授权
     */
    public String getRoleIds() {
        return roleIds;
    }

    /**
     * 角色ID集合（多个用逗号分隔）注：chain_type=0（总部）时，一个预警设置，可以同时给总部自身和分店进行角色授权；chain_type=2（加盟店），只能选择分店角色进行授权
     * @param roleIds 角色ID集合（多个用逗号分隔）注：chain_type=0（总部）时，一个预警设置，可以同时给总部自身和分店进行角色授权；chain_type=2（加盟店），只能选择分店角色进行授权
     */
    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds == null ? null : roleIds.trim();
    }

    /**
     * 资质ID
     * @return qualification_id 创建人ID
     */
    public Long getQualificationId() {
        return qualificationId;
    }

    /**
     * 资质ID
     * @param qualificationID 创建人ID
     */
    public void setQualificationId(Long qualificationID) {
        this.qualificationId = qualificationID;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "WarnSetVO{" +
                "id=" + id +
                ", enterpriseId=" + enterpriseId +
                ", parentId=" + parentId +
                ", chainType=" + chainType +
                ", setType=" + setType +
                ", content='" + content + '\'' +
                ", warnDays=" + warnDays +
                ", status=" + status +
                ", sysType=" + sysType +
                ", remark='" + remark + '\'' +
                ", createrId=" + createrId +
                ", createrCode='" + createrCode + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createTime=" + createTime +
                ", modifierId=" + modifierId +
                ", modifierCode='" + modifierCode + '\'' +
                ", modifierName='" + modifierName + '\'' +
                ", updateTime=" + updateTime +
                ", roleIds='" + roleIds + '\'' +
                ", roleNames='" + roleNames + '\'' +
                ", qualificationId=" + qualificationId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarnSetVO warnSetVO = (WarnSetVO) o;

        if (id != null ? !id.equals(warnSetVO.id) : warnSetVO.id != null) return false;
        if (enterpriseId != null ? !enterpriseId.equals(warnSetVO.enterpriseId) : warnSetVO.enterpriseId != null)
            return false;
        if (parentId != null ? !parentId.equals(warnSetVO.parentId) : warnSetVO.parentId != null) return false;
        if (chainType != null ? !chainType.equals(warnSetVO.chainType) : warnSetVO.chainType != null) return false;
        if (setType != null ? !setType.equals(warnSetVO.setType) : warnSetVO.setType != null) return false;
        if (content != null ? !content.equals(warnSetVO.content) : warnSetVO.content != null) return false;
        if (warnDays != null ? !warnDays.equals(warnSetVO.warnDays) : warnSetVO.warnDays != null) return false;
        if (status != null ? !status.equals(warnSetVO.status) : warnSetVO.status != null) return false;
        if (sysType != null ? !sysType.equals(warnSetVO.sysType) : warnSetVO.sysType != null) return false;
        if (remark != null ? !remark.equals(warnSetVO.remark) : warnSetVO.remark != null) return false;
        if (createrId != null ? !createrId.equals(warnSetVO.createrId) : warnSetVO.createrId != null) return false;
        if (createrCode != null ? !createrCode.equals(warnSetVO.createrCode) : warnSetVO.createrCode != null)
            return false;
        if (createrName != null ? !createrName.equals(warnSetVO.createrName) : warnSetVO.createrName != null)
            return false;
        if (createTime != null ? !createTime.equals(warnSetVO.createTime) : warnSetVO.createTime != null) return false;
        if (modifierId != null ? !modifierId.equals(warnSetVO.modifierId) : warnSetVO.modifierId != null) return false;
        if (modifierCode != null ? !modifierCode.equals(warnSetVO.modifierCode) : warnSetVO.modifierCode != null)
            return false;
        if (modifierName != null ? !modifierName.equals(warnSetVO.modifierName) : warnSetVO.modifierName != null)
            return false;
        if (updateTime != null ? !updateTime.equals(warnSetVO.updateTime) : warnSetVO.updateTime != null) return false;
        if (roleIds != null ? !roleIds.equals(warnSetVO.roleIds) : warnSetVO.roleIds != null) return false;
        if (roleNames != null ? !roleNames.equals(warnSetVO.roleNames) : warnSetVO.roleNames != null) return false;
        return qualificationId != null ? qualificationId.equals(warnSetVO.qualificationId) : warnSetVO.qualificationId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (enterpriseId != null ? enterpriseId.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (chainType != null ? chainType.hashCode() : 0);
        result = 31 * result + (setType != null ? setType.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (warnDays != null ? warnDays.hashCode() : 0);
        result = 31 * result + (sysType != null ? sysType.hashCode() : 0);
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
        result = 31 * result + (roleIds != null ? roleIds.hashCode() : 0);
        result = 31 * result + (roleNames != null ? roleNames.hashCode() : 0);
        result = 31 * result + (qualificationId != null ? qualificationId.hashCode() : 0);
        return result;
    }
}