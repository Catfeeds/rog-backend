package com.rograndec.feijiayun.chain.business.basic.user.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_health_check_detail
 * 
 * 
 * @author kexinhao
 * 
 * 2017-10-12
 */
public class HealthCheckDetail implements Serializable {
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

    /**
     * 检查ID
     */
    @ApiModelProperty(value = "检查ID")
    private Long checkId;

    /**
     * 员工ID
     */
    @ApiModelProperty(value = "员工ID")
    private Long userId;

    /**
     * 员工编码
     */
    @ApiModelProperty(value = "员工编码")
    private String userCode;

    /**
     * 员工名称
     */
    @ApiModelProperty(value = "员工名称")
    private String userName;

    /**
     * 检查日期
     */
    @ApiModelProperty(value = "检查日期")
    private Date checkDate;

    /**
     * 检查结果
     */
    @ApiModelProperty(value = "检查结果")
    private String checkResult;

    /**
     * 采取措施
     */
    @ApiModelProperty(value = "采取措施")
    private String measures;

    /**
     * 附件ID
     */
    @ApiModelProperty(value = "附件ID")
    private Long fileId;

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
     * 修改人ID
     */
    @ApiModelProperty(value = "修改人ID")
    private Long modifierId;

    /**
     * 修改人编码
     */
    @ApiModelProperty(value = "修改人编码")
    private String modifierCode;

    /**
     * 修改人名称
     */
    @ApiModelProperty(value = "修改人名称")
    private String modifierName;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * saas_health_check_detail
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
     * 检查ID
     * @return check_id 检查ID
     */
    public Long getCheckId() {
        return checkId;
    }

    /**
     * 检查ID
     * @param checkId 检查ID
     */
    public void setCheckId(Long checkId) {
        this.checkId = checkId;
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
     * 员工编码
     * @return user_code 员工编码
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 员工编码
     * @param userCode 员工编码
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * 员工名称
     * @return user_name 员工名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 员工名称
     * @param userName 员工名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 检查日期
     * @return check_date 检查日期
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * 检查日期
     * @param checkDate 检查日期
     */
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * 检查结果
     * @return check_result 检查结果
     */
    public String getCheckResult() {
        return checkResult;
    }

    /**
     * 检查结果
     * @param checkResult 检查结果
     */
    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult == null ? null : checkResult.trim();
    }

    /**
     * 采取措施
     * @return measures 采取措施
     */
    public String getMeasures() {
        return measures;
    }

    /**
     * 采取措施
     * @param measures 采取措施
     */
    public void setMeasures(String measures) {
        this.measures = measures == null ? null : measures.trim();
    }

    /**
     * 附件ID
     * @return file_id 附件ID
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * 附件ID
     * @param fileId 附件ID
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
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
        sb.append(", checkId=").append(checkId);
        sb.append(", userId=").append(userId);
        sb.append(", userCode=").append(userCode);
        sb.append(", userName=").append(userName);
        sb.append(", checkDate=").append(checkDate);
        sb.append(", checkResult=").append(checkResult);
        sb.append(", measures=").append(measures);
        sb.append(", fileId=").append(fileId);
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