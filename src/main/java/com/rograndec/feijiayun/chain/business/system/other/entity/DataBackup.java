package com.rograndec.feijiayun.chain.business.system.other.entity;

import java.io.Serializable;
import java.util.Date;

public class DataBackup implements Serializable {
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
     * 备份时间
     */
    private Date backupTime;

    /**
     * 备份类型（0-完全备份；1-增量备份）
     */
    private Integer backupType;

    /**
     * 备份名称
     */
    private String backupName;

    /**
     * 备份路径
     */
    private String backupPath;

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
     * saas_data_backup
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return Id 主键ID
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

    public Long getEnterpriseId() {
        return enterpriseId;
    }

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
     * 备份时间
     * @return backup_time 备份时间
     */
    public Date getBackupTime() {
        return backupTime;
    }

    /**
     * 备份时间
     * @param backupTime 备份时间
     */
    public void setBackupTime(Date backupTime) {
        this.backupTime = backupTime;
    }

    /**
     * 备份类型（0-完全备份；1-增量备份）
     * @return backup_type 备份类型（0-完全备份；1-增量备份）
     */
    public Integer getBackupType() {
        return backupType;
    }

    /**
     * 备份类型（0-完全备份；1-增量备份）
     * @param backupType 备份类型（0-完全备份；1-增量备份）
     */
    public void setBackupType(Integer backupType) {
        this.backupType = backupType;
    }

    /**
     * 备份名称
     * @return backup_name 备份名称
     */
    public String getBackupName() {
        return backupName;
    }

    /**
     * 备份名称
     * @param backupName 备份名称
     */
    public void setBackupName(String backupName) {
        this.backupName = backupName == null ? null : backupName.trim();
    }

    /**
     * 备份路径
     * @return backup_path 备份路径
     */
    public String getBackupPath() {
        return backupPath;
    }

    /**
     * 备份路径
     * @param backupPath 备份路径
     */
    public void setBackupPath(String backupPath) {
        this.backupPath = backupPath == null ? null : backupPath.trim();
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
        sb.append(", backupTime=").append(backupTime);
        sb.append(", backupType=").append(backupType);
        sb.append(", backupName=").append(backupName);
        sb.append(", backupPath=").append(backupPath);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}