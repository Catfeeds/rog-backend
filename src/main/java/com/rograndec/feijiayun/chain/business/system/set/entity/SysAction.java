package com.rograndec.feijiayun.chain.business.system.set.entity;

import com.rograndec.feijiayun.chain.business.auth.menu.vo.MenuFormVO;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;

import java.io.Serializable;
import java.util.Date;

public class SysAction implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 上级功能（模块）ID
     */
    private Long parentActionId;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 功能编码
     */
    private String code;

    /**
     * 类型（0-系统菜单；1-管理菜单）
     */
    private Integer type;

    /**
     *用于总部（0-否；1-是）
     */
    private Integer forParent;

    /**
     *用于自营店（0-否；1-是）
     */
    private Integer forBranch;

    /**
     *用于加盟店（0-否；1-是）
     */
    private Integer forLeague;


    /**
     * 功能名称
     */
    private String name;

    /**
     * URL
     */
    private String url;

    /**
     * 显示顺序
     */
    private Integer showOrder;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 图标
     */
    private String icon;

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
     * 是否为父节点（0-否；1-是
     */
    private int isParent;

    /**
     * saas_sys_action
     */
    private static final long serialVersionUID = 1L;


    public static SysAction getSysAction(UserVO userVO , MenuFormVO menuFormVO) throws Exception {

        SysAction sysAction = new SysAction();

        /**
         * 主键ID
         */
        sysAction.setId(menuFormVO.getId());

        /**
         * 上级功能（模块）ID
         */
        sysAction.setParentActionId(menuFormVO.getParentId());


        /**
         * 企业ID
         */
        sysAction.setEnterpriseId(userVO.getEnterpriseId());
        if(userVO.getChainType().equals(ChainType.Headquarters.getCode())){
            sysAction.setParentId(0L);
        }else {
            sysAction.setParentId(userVO.getParentId());
        }


        /**
         * 功能编码
         */
        sysAction.setCode(menuFormVO.getCode());

        /**
         * 类型（0-系统菜单；1-管理菜单）
         */
        sysAction.setType(menuFormVO.getType());

        sysAction.setForLeague(menuFormVO.getForLeague());
        sysAction.setForBranch(menuFormVO.getForBranch());
        sysAction.setForParent(menuFormVO.getForParent());

        /**
         * 功能名称
         */
        sysAction.setName(menuFormVO.getName());

        /**
         * URL
         */
        sysAction.setUrl(menuFormVO.getUrl());

        /**
         * 显示顺序
         */
        sysAction.setShowOrder(0);

        /**
         * 状态
         */
        sysAction.setStatus(EnableStatus.ENABLE.getStatus());

        boolean isAdd = false;

        if(menuFormVO.getId() == null)
            isAdd = true;

        UserEnterpriseUtils.setUserCreateOrModify(sysAction,userVO,isAdd);

        return sysAction;
    }

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
     * 上级功能（模块）ID
     * @return parent_action_id 上级功能（模块）ID
     */
    public Long getParentActionId() {
        return parentActionId;
    }

    /**
     * 上级功能（模块）ID
     * @param parentActionId 上级功能（模块）ID
     */
    public void setParentActionId(Long parentActionId) {
        this.parentActionId = parentActionId;
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
     * 功能编码
     * @return code 功能编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 功能编码
     * @param code 功能编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 功能名称
     * @return name 功能名称
     */
    public String getName() {
        return name;
    }

    /**
     * 功能名称
     * @param name 功能名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * URL
     * @return url URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * URL
     * @param url URL
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 显示顺序
     * @return show_order 显示顺序
     */
    public Integer getShowOrder() {
        return showOrder;
    }

    /**
     * 显示顺序
     * @param showOrder 显示顺序
     */
    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    /**
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
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


    public int getIsParent() {
        return isParent;
    }

    public void setIsParent(int isParent) {
        this.isParent = isParent;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getForParent() {
        return forParent;
    }

    public void setForParent(Integer forParent) {
        this.forParent = forParent;
    }

    public Integer getForBranch() {
        return forBranch;
    }

    public void setForBranch(Integer forBranch) {
        this.forBranch = forBranch;
    }

    public Integer getForLeague() {
        return forLeague;
    }

    public void setForLeague(Integer forLeague) {
        this.forLeague = forLeague;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysAction sysAction = (SysAction) o;

        if (isParent != sysAction.isParent) return false;
        if (id != null ? !id.equals(sysAction.id) : sysAction.id != null) return false;
        if (parentActionId != null ? !parentActionId.equals(sysAction.parentActionId) : sysAction.parentActionId != null)
            return false;
        if (enterpriseId != null ? !enterpriseId.equals(sysAction.enterpriseId) : sysAction.enterpriseId != null)
            return false;
        if (parentId != null ? !parentId.equals(sysAction.parentId) : sysAction.parentId != null) return false;
        if (code != null ? !code.equals(sysAction.code) : sysAction.code != null) return false;
        if (type != null ? !type.equals(sysAction.type) : sysAction.type != null) return false;
        if (forParent != null ? !forParent.equals(sysAction.forParent) : sysAction.forParent != null) return false;
        if (forBranch != null ? !forBranch.equals(sysAction.forBranch) : sysAction.forBranch != null) return false;
        if (forLeague != null ? !forLeague.equals(sysAction.forLeague) : sysAction.forLeague != null) return false;
        if (name != null ? !name.equals(sysAction.name) : sysAction.name != null) return false;
        if (url != null ? !url.equals(sysAction.url) : sysAction.url != null) return false;
        if (showOrder != null ? !showOrder.equals(sysAction.showOrder) : sysAction.showOrder != null) return false;
        if (status != null ? !status.equals(sysAction.status) : sysAction.status != null) return false;
        if (remark != null ? !remark.equals(sysAction.remark) : sysAction.remark != null) return false;
        if (icon != null ? !icon.equals(sysAction.icon) : sysAction.icon != null) return false;
        if (createrId != null ? !createrId.equals(sysAction.createrId) : sysAction.createrId != null) return false;
        if (createrCode != null ? !createrCode.equals(sysAction.createrCode) : sysAction.createrCode != null)
            return false;
        if (createrName != null ? !createrName.equals(sysAction.createrName) : sysAction.createrName != null)
            return false;
        if (createTime != null ? !createTime.equals(sysAction.createTime) : sysAction.createTime != null) return false;
        if (modifierId != null ? !modifierId.equals(sysAction.modifierId) : sysAction.modifierId != null) return false;
        if (modifierCode != null ? !modifierCode.equals(sysAction.modifierCode) : sysAction.modifierCode != null)
            return false;
        if (modifierName != null ? !modifierName.equals(sysAction.modifierName) : sysAction.modifierName != null)
            return false;
        return updateTime != null ? updateTime.equals(sysAction.updateTime) : sysAction.updateTime == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (parentActionId != null ? parentActionId.hashCode() : 0);
        result = 31 * result + (enterpriseId != null ? enterpriseId.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (forParent != null ? forParent.hashCode() : 0);
        result = 31 * result + (forBranch != null ? forBranch.hashCode() : 0);
        result = 31 * result + (forLeague != null ? forLeague.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (showOrder != null ? showOrder.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (createrId != null ? createrId.hashCode() : 0);
        result = 31 * result + (createrCode != null ? createrCode.hashCode() : 0);
        result = 31 * result + (createrName != null ? createrName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (modifierId != null ? modifierId.hashCode() : 0);
        result = 31 * result + (modifierCode != null ? modifierCode.hashCode() : 0);
        result = 31 * result + (modifierName != null ? modifierName.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + isParent;
        return result;
    }

    @Override
    public String toString() {
        return "SysAction{" +
                "id=" + id +
                ", parentActionId=" + parentActionId +
                ", enterpriseId=" + enterpriseId +
                ", parentId=" + parentId +
                ", code='" + code + '\'' +
                ", type=" + type +
                ", forParent=" + forParent +
                ", forBranch=" + forBranch +
                ", forLeague=" + forLeague +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", showOrder=" + showOrder +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", icon='" + icon + '\'' +
                ", createrId=" + createrId +
                ", createrCode='" + createrCode + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createTime=" + createTime +
                ", modifierId=" + modifierId +
                ", modifierCode='" + modifierCode + '\'' +
                ", modifierName='" + modifierName + '\'' +
                ", updateTime=" + updateTime +
                ", isParent=" + isParent +
                '}';
    }
}