package com.rograndec.feijiayun.chain.business.basic.user.entity;

import java.io.Serializable;
import java.util.Date;

public class UserModifyRecord implements Serializable {
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
     * 员工ID
     */
    private Long userId;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 主键ID
     */
    private Long keyId;

    /**
     * 修改字段英文名称
     */
    private String columnEnName;

    /**
     * 修改字段中文名称（修改项目）
     */
    private String columnChName;

    /**
     * 修改人ID
     */
    private Long modifierId;

    /**
     * 修改人编码
     */
    private String modifierCode;

    /**
     * 修改人名称
     */
    private String modifierName;

    /**
     * 修改时间
     */
    private Date updateTime;



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
     * saas_user_modify_record
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
     * 员工ID
     * @return user_id 员工ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 员工ID
     * @param userId 员工ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 表名
     * @return table_name 表名
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 表名
     * @param tableName 表名
     */
    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    /**
     * 主键ID
     * @return key_id 主键ID
     */
    public Long getKeyId() {
        return keyId;
    }

    /**
     * 主键ID
     * @param keyId 主键ID
     */
    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    /**
     * 修改字段英文名称
     * @return column_en_name 修改字段英文名称
     */
    public String getColumnEnName() {
        return columnEnName;
    }

    /**
     * 修改字段英文名称
     * @param columnEnName 修改字段英文名称
     */
    public void setColumnEnName(String columnEnName) {
        this.columnEnName = columnEnName == null ? null : columnEnName.trim();
    }

    /**
     * 修改字段中文名称（修改项目）
     * @return column_ch_name 修改字段中文名称（修改项目）
     */
    public String getColumnChName() {
        return columnChName;
    }

    /**
     * 修改字段中文名称（修改项目）
     * @param columnChName 修改字段中文名称（修改项目）
     */
    public void setColumnChName(String columnChName) {
        this.columnChName = columnChName == null ? null : columnChName.trim();
    }

    /**
     * 修改人ID
     * @return modifier_id 修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 修改人ID
     * @param modifierId 修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 修改人编码
     * @return modifier_code 修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 修改人编码
     * @param modifierCode 修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 修改人名称
     * @return modifier_name 修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 修改人名称
     * @param modifierName 修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 修改时间
     * @return update_time 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        sb.append(", userId=").append(userId);
        sb.append(", tableName=").append(tableName);
        sb.append(", keyId=").append(keyId);
        sb.append(", columnEnName=").append(columnEnName);
        sb.append(", columnChName=").append(columnChName);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
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