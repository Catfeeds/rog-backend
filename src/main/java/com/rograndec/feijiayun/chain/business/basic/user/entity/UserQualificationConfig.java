package com.rograndec.feijiayun.chain.business.basic.user.entity;

import com.rograndec.feijiayun.chain.business.basic.user.vo.UserQualificationConfigVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.UserQualification;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserQualificationConfig implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 员工ID
     */
    private Long userId;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 员工资质ID
     */
    private Long qualificationId;

    /**
     * 等级
     */
    private String grade;

    /**
     * 资格证书号
     */
    private String code;

    /**
     * 注册证书号
     */
    private String registerCode;

    /**
     * 适用地区
     */
    private String region;

    /**
     * 适用类别
     */
    private String category;

    /**
     * 适用范围
     */
    private String range;

    /**
     * 附件ID
     */
    private Long fileId;

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
     * saas_user_qualification_config
     */
    private static final long serialVersionUID = 1L;

    public static List<Long> getFileIds(List<UserQualificationConfig> userQualificationConfigs){
        List<Long> ids = new ArrayList<>();

        for(UserQualificationConfig config : userQualificationConfigs){
            ids.add(config.getFileId());
        }

        return ids;
    }

    public static List<Long> getUserQualificationIds(List<UserQualificationConfig> userQualificationConfigs){
        List<Long> ids = new ArrayList<>();

        for(UserQualificationConfig config : userQualificationConfigs){
            ids.add(config.getQualificationId());
        }

        return ids;
    }

    public static List<UserQualificationConfig> getUserQualificationConfigs4VOs(List<UserQualificationConfigVO> configVOs, UserVO userVO, User user){
        List<UserQualificationConfig> list = new ArrayList<>();
        for(UserQualificationConfigVO configVO : configVOs){
            UserQualificationConfig userQualificationConfig = getUserQualificationConfig4VO(configVO,userVO,user);
            list.add(userQualificationConfig);
        }
        return list;
    }

    public static UserQualificationConfig getUserQualificationConfig4VO(UserQualificationConfigVO configVO, UserVO userVO, User user){
        UserQualificationConfig userQualificationConfig = new UserQualificationConfig();

        userQualificationConfig.setId(configVO.getId());
        userQualificationConfig.setUserId(user.getId());
        userQualificationConfig.setParentId(user.getParentId());
        userQualificationConfig.setEnterpriseId(user.getEnterpriseId());

        userQualificationConfig.setQualificationId(configVO.getQualificationId());
        userQualificationConfig.setGrade(configVO.getGrade());
        userQualificationConfig.setCode(configVO.getCode());

        userQualificationConfig.setRegisterCode(configVO.getRegisterCode());
        userQualificationConfig.setRange(configVO.getRange());
        userQualificationConfig.setCategory(configVO.getCategory());
        userQualificationConfig.setFileId(configVO.getFileId());
        userQualificationConfig.setRegion(configVO.getRegion());
        userQualificationConfig.setStatus(EnableStatus.ENABLE.getStatus());
/*        userQualificationConfig.setCreaterId(userVO.getUserId());
        userQualificationConfig.setCreaterCode(userVO.getUserCode());
        userQualificationConfig.setCreaterName(userVO.getUserName());
        userQualificationConfig.setCreateTime(new Date())*/;

        if(null == configVO.getId()){
            userQualificationConfig.setCreaterId(userVO.getUserId());
            userQualificationConfig.setCreaterCode(userVO.getUserCode());
            userQualificationConfig.setCreaterName(userVO.getUserName());
            userQualificationConfig.setCreateTime(new Date());
            userQualificationConfig.setModifierId(userVO.getUserId());
            userQualificationConfig.setModifierCode(userVO.getUserCode());
            userQualificationConfig.setModifierName(userVO.getUserName());
            userQualificationConfig.setUpdateTime(new Date());
        }else {
            userQualificationConfig.setModifierId(userVO.getUserId());
            userQualificationConfig.setModifierCode(userVO.getUserCode());
            userQualificationConfig.setModifierName(userVO.getUserName());
            userQualificationConfig.setUpdateTime(new Date());
        }

        return userQualificationConfig;
    }

    public static UserQualificationConfig getUserQualificationConfig4Qualification(UserQualification userQualification, UserVO userVO, User user){
        UserQualificationConfig userQualificationConfig = new UserQualificationConfig();

        userQualificationConfig.setUserId(user.getId());
        userQualificationConfig.setParentId(user.getParentId());
        userQualificationConfig.setEnterpriseId(user.getEnterpriseId());

        userQualificationConfig.setQualificationId(userQualification.getId());
        userQualificationConfig.setStatus(EnableStatus.ENABLE.getStatus());
/*        userQualificationConfig.setCreaterId(userVO.getUserId());
        userQualificationConfig.setCreaterCode(userVO.getUserCode());
        userQualificationConfig.setCreaterName(userVO.getUserName());
        userQualificationConfig.setCreateTime(new Date())*/;
        userQualificationConfig.setCreaterId(userVO.getUserId());
        userQualificationConfig.setCreaterCode(userVO.getUserCode());
        userQualificationConfig.setCreaterName(userVO.getUserName());
        userQualificationConfig.setCreateTime(new Date());
        userQualificationConfig.setModifierId(userVO.getUserId());
        userQualificationConfig.setModifierCode(userVO.getUserCode());
        userQualificationConfig.setModifierName(userVO.getUserName());
        userQualificationConfig.setUpdateTime(new Date());

        return userQualificationConfig;
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
     * 员工资质ID
     * @return qualification_id 员工资质ID
     */
    public Long getQualificationId() {
        return qualificationId;
    }

    /**
     * 员工资质ID
     * @param qualificationId 员工资质ID
     */
    public void setQualificationId(Long qualificationId) {
        this.qualificationId = qualificationId;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
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
        sb.append(", userId=").append(userId);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", qualificationId=").append(qualificationId);
        sb.append(", fileId=").append(fileId);
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
}