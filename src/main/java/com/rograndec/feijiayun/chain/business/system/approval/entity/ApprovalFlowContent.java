package com.rograndec.feijiayun.chain.business.system.approval.entity;

import java.io.Serializable;
import java.util.Date;

public class ApprovalFlowContent implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 审批内容ID
     */
    private String contentId;

    /**
     * 审批内容PID（如果为根节点，该字段为0）
     */
    private String contentPid;

    /**
     * 审批内容名称
     */
    private String name;

    /**
     * 排序字段
     */
    private Integer sort;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 是否叶子节点（0-否；1-是）
     */
    private Integer isLeaf;

    /**
     * 状态（0-启用 1-禁用；）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * saas_approval_flow_content
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
     * 审批内容ID
     * @return content_id 审批内容ID
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * 审批内容ID
     * @param contentId 审批内容ID
     */
    public void setContentId(String contentId) {
        this.contentId = contentId == null ? null : contentId.trim();
    }

    /**
     * 审批内容PID（如果为根节点，该字段为0）
     * @return content_pid 审批内容PID（如果为根节点，该字段为0）
     */
    public String getContentPid() {
        return contentPid;
    }

    /**
     * 审批内容PID（如果为根节点，该字段为0）
     * @param contentPid 审批内容PID（如果为根节点，该字段为0）
     */
    public void setContentPid(String contentPid) {
        this.contentPid = contentPid == null ? null : contentPid.trim();
    }

    /**
     * 审批内容名称
     * @return name 审批内容名称
     */
    public String getName() {
        return name;
    }

    /**
     * 审批内容名称
     * @param name 审批内容名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 排序字段
     * @return sort 排序字段
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序字段
     * @param sort 排序字段
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 级别
     * @return leveld 级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 级别
     * @param leveld 级别
     */
    public void setLevel(Integer leveld) {
        this.level = leveld;
    }

    /**
     * 是否叶子节点（0-否；1-是）
     * @return is_leaf 是否叶子节点（0-否；1-是）
     */
    public Integer getIsLeaf() {
        return isLeaf;
    }

    /**
     * 是否叶子节点（0-否；1-是）
     * @param isLeaf 是否叶子节点（0-否；1-是）
     */
    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * 状态（0-启用 1-禁用；）
     * @return status 状态（0-启用 1-禁用；）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-启用 1-禁用；）
     * @param status 状态（0-启用 1-禁用；）
     */
    public void setStatus(Integer status) {
        this.status = status;
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
        sb.append(", contentId=").append(contentId);
        sb.append(", contentPid=").append(contentPid);
        sb.append(", name=").append(name);
        sb.append(", sort=").append(sort);
        sb.append(", leveld=").append(level);
        sb.append(", isLeaf=").append(isLeaf);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}