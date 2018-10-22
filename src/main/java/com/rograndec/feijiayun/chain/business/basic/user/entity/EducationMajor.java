package com.rograndec.feijiayun.chain.business.basic.user.entity;

import com.rograndec.feijiayun.chain.business.basic.user.constant.EducationMajorTypeEum;
import com.rograndec.feijiayun.chain.business.basic.user.vo.EducationMajorVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.Serializable;
import java.util.Date;

public class EducationMajor implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 企业（总部）ID
     */
    private Long enterpriseId;

    /**
     * 类型（0-学历；1-专业）
     */
    private Integer type;

    /**
     * 学历、专业名称
     */
    private String name;

    /**
     * 系统默认标志（0-用户自定义；1-系统默认）
     */
    private Integer sysType;

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
     * saas_education_major
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
     * 类型（0-学历；1-专业）
     * @return type 类型（0-学历；1-专业）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型（0-学历；1-专业）
     * @param type 类型（0-学历；1-专业）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 学历、专业名称
     * @return name 学历、专业名称
     */
    public String getName() {
        return name;
    }

    /**
     * 学历、专业名称
     * @param name 学历、专业名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 系统默认标志（0-用户自定义；1-系统默认）
     * @return sys_type 系统默认标志（0-用户自定义；1-系统默认）
     */
    public Integer getSysType() {
        return sysType;
    }

    /**
     * 系统默认标志（0-用户自定义；1-系统默认）
     * @param sysType 系统默认标志（0-用户自定义；1-系统默认）
     */
    public void setSysType(Integer sysType) {
        this.sysType = sysType;
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
        sb.append(", type=").append(type);
        sb.append(", name=").append(name);
        sb.append(", sysType=").append(sysType);
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


    public static EducationMajor getEducationMajor4VO(EducationMajorVO educationMajorVO, String type, UserVO userVO){

        EducationMajor educationMajor = new EducationMajor();
        educationMajor.setEnterpriseId(userVO.getEnterpriseId());
        educationMajor.setType(EducationMajorTypeEum.getEducationMajorTypeEum4Value(type).getCode());
        educationMajor.setName(educationMajorVO.getName());
        educationMajor.setStatus(educationMajorVO.getStatus());
        educationMajor.setSysType(educationMajorVO.getSysType());
        educationMajor.setCreaterCode(userVO.getUserCode());
        educationMajor.setCreaterId(userVO.getUserId());
        educationMajor.setCreaterName(userVO.getUserName());
        educationMajor.setCreateTime(new Date());
        educationMajor.setModifierId(userVO.getUserId());
        educationMajor.setModifierCode(userVO.getUserCode());
        educationMajor.setModifierName(userVO.getUserName());
        educationMajor.setUpdateTime(new Date());
        return educationMajor;
    }
}