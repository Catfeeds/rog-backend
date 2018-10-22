package com.rograndec.feijiayun.chain.business.basic.user.entity;

import com.rograndec.feijiayun.chain.business.auth.register.entity.Register;
import com.rograndec.feijiayun.chain.business.basic.user.vo.AddUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.UserPersonalVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

public class UserPersonal implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户ID
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
     * 性别（0-男；1-女；2-其它）
     */
    private Integer sex;

    /**
     * 身份证号
     */
    private String idNum;

    /**
     * 出生日期
     */
    private Date birthDate;

    /**
     * 婚姻状况（0-未婚；1-已婚）
     */
    private Integer maritalStatus;

    /**
     * 民族ID
     */
    private Long nationId;

    /**
     * 政治面貌
     */
    private String politicalOutlook;

    /**
     * 籍贯
     */
    private String originPlace;

    /**
     * 住址
     */
    private String adderss;

    /**
     * 手机
     */
    private String mobilePhone;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 微信
     */
    private String wechatNum;

    /**
     * QQ
     */
    private String qqNum;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 照片附件ID
     */
    private Long photoId;

    /**
     * 状态
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

    public static UserPersonal initUserPersonal(
            User registerUser
            ,Nation nation
            ,String loginName
    ){

        UserPersonal userPersonal = new UserPersonal();


        /**
         * 用户ID
         */
        userPersonal.setUserId(registerUser.getId());

        /**
         * 企业ID
         */
        userPersonal.setEnterpriseId(registerUser.getEnterpriseId());

        /**
         * 上级企业ID
         */
        userPersonal.setParentId(registerUser.getParentId());

        /**
         * 民族ID
         */
        userPersonal.setNationId(nation.getId());

        /**
         * 默认为登录账号的@rograndec.com 邮箱
         */
        userPersonal.setEmail(loginName+"@rograndec.com");

        /**
         * 默认为时间戳
         */
        userPersonal.setMobilePhone(String.valueOf(Instant.now().toEpochMilli()));

        userPersonal.setStatus(EnableStatus.ENABLE.getStatus());
        userPersonal.setSex(0);
        userPersonal.setCreaterId(0L);
        userPersonal.setCreaterCode("0000000");
        userPersonal.setCreaterName("0000000");
        userPersonal.setCreateTime(new Date());
        userPersonal.setModifierId(0L);
        userPersonal.setModifierCode("0000000");
        userPersonal.setModifierName("0000000");
        userPersonal.setUpdateTime(new Date());

        return userPersonal;
    }



    public static UserPersonal initUserPersonal(
            Register register
            ,User registerUser
            ,Nation nation
    ){

        UserPersonal userPersonal = new UserPersonal();


        /**
         * 用户ID
         */
        userPersonal.setUserId(registerUser.getId());

        /**
         * 企业ID
         */
        userPersonal.setEnterpriseId(registerUser.getEnterpriseId());

        /**
         * 上级企业ID
         */
        userPersonal.setParentId(registerUser.getParentId());

        /**
         * 民族ID
         */
        userPersonal.setNationId(nation.getId());

        userPersonal.setEmail(register.getEmail());

        userPersonal.setMobilePhone(register.getMobilePhone());

        userPersonal.setStatus(EnableStatus.ENABLE.getStatus());
        userPersonal.setSex(0);
        userPersonal.setCreaterId(0L);
        userPersonal.setCreaterCode("0000000");
        userPersonal.setCreaterName("0000000");
        userPersonal.setCreateTime(new Date());
        userPersonal.setModifierId(0L);
        userPersonal.setModifierCode("0000000");
        userPersonal.setModifierName("0000000");
        userPersonal.setUpdateTime(new Date());

        return userPersonal;
    }

    public static UserPersonal getUserPersonal4UserPersonalVO(AddUserVO addUserVO, UserVO userVO, User user, Enterprise enterprise, boolean isAdd){
        UserPersonalVO userPersonalVO = addUserVO.getUserPersonalDTOS();

        UserPersonal userPersonal = new UserPersonal();
        userPersonal.setId(userPersonalVO.getId());
        userPersonal.setUserId(user.getId());
        userPersonal.setEnterpriseId(enterprise.getId());
        userPersonal.setParentId(enterprise.getParentId());

        userPersonal.setSex(userPersonalVO.getSex());
        userPersonal.setIdNum(userPersonalVO.getIdNum());
        // 出生日期 转换为yyyy-MM-dd 00:00:00便于修改记录判断 zhangyu 2018-2-1 10:26:59
        userPersonal.setBirthDate(DateUtils.timeToDate(userPersonalVO.getBirthDate()));


        userPersonal.setMaritalStatus(userPersonalVO.getMaritalStatus());
        userPersonal.setNationId(userPersonalVO.getNationId());
        userPersonal.setPoliticalOutlook(userPersonalVO.getPoliticalOutlook());
        userPersonal.setOriginPlace(userPersonalVO.getOriginPlace());

        userPersonal.setAdderss(userPersonalVO.getAdderss());
        userPersonal.setMobilePhone(userPersonalVO.getMobilePhone());
        userPersonal.setTelephone(userPersonalVO.getTelephone());
        userPersonal.setWechatNum(userPersonalVO.getWechatNum());
        userPersonal.setQqNum(userPersonalVO.getQqNum());

        userPersonal.setEmail(userPersonalVO.getEmail());
        userPersonal.setPhotoId(userPersonalVO.getPhotoId());
        userPersonal.setStatus(EnableStatus.ENABLE.getStatus());

        if(isAdd){
            userPersonal.setCreaterId(userVO.getUserId());
            userPersonal.setCreaterCode(userVO.getUserCode());
            userPersonal.setCreaterName(userVO.getUserName());
            userPersonal.setCreateTime(new Date());
            userPersonal.setModifierId(userVO.getUserId());
            userPersonal.setModifierCode(userVO.getUserCode());
            userPersonal.setModifierName(userVO.getUserName());
            userPersonal.setUpdateTime(new Date());
        }else {
            userPersonal.setModifierId(userVO.getUserId());
            userPersonal.setModifierCode(userVO.getUserCode());
            userPersonal.setModifierName(userVO.getUserName());
            userPersonal.setUpdateTime(new Date());
        }
        return userPersonal;
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
     * 用户ID
     * @return user_id 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户ID
     * @param userId 用户ID
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
     * 性别（0-男；1-女；2-其它）
     * @return sex 性别（0-男；1-女；2-其它）
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 性别（0-男；1-女；2-其它）
     * @param sex 性别（0-男；1-女；2-其它）
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 身份证号
     * @return id_num 身份证号
     */
    public String getIdNum() {
        return idNum;
    }

    /**
     * 身份证号
     * @param idNum 身份证号
     */
    public void setIdNum(String idNum) {
        this.idNum = idNum == null ? null : idNum.trim();
    }

    /**
     * 出生日期
     * @return birth_date 出生日期
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * 出生日期
     * @param birthDate 出生日期
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * 婚姻状况（0-未婚；1-已婚）
     * @return marital_status 婚姻状况（0-未婚；1-已婚）
     */
    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * 婚姻状况（0-未婚；1-已婚）
     * @param maritalStatus 婚姻状况（0-未婚；1-已婚）
     */
    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * 民族ID
     * @return nation_id 民族ID
     */
    public Long getNationId() {
        return nationId;
    }

    /**
     * 民族ID
     * @param nationId 民族ID
     */
    public void setNationId(Long nationId) {
        this.nationId = nationId;
    }

    /**
     * 政治面貌
     * @return political_outlook 政治面貌
     */
    public String getPoliticalOutlook() {
        return politicalOutlook;
    }

    /**
     * 政治面貌
     * @param politicalOutlook 政治面貌
     */
    public void setPoliticalOutlook(String politicalOutlook) {
        this.politicalOutlook = politicalOutlook == null ? null : politicalOutlook.trim();
    }

    /**
     * 籍贯
     * @return origin_place 籍贯
     */
    public String getOriginPlace() {
        return originPlace;
    }

    /**
     * 籍贯
     * @param originPlace 籍贯
     */
    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace == null ? null : originPlace.trim();
    }

    /**
     * 住址
     * @return adderss 住址
     */
    public String getAdderss() {
        return adderss;
    }

    /**
     * 住址
     * @param adderss 住址
     */
    public void setAdderss(String adderss) {
        this.adderss = adderss == null ? null : adderss.trim();
    }

    /**
     * 手机
     * @return mobile_phone 手机
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 手机
     * @param mobilePhone 手机
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * 电话
     * @return telephone 电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 电话
     * @param telephone 电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * 微信
     * @return wechat_num 微信
     */
    public String getWechatNum() {
        return wechatNum;
    }

    /**
     * 微信
     * @param wechatNum 微信
     */
    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum == null ? null : wechatNum.trim();
    }

    /**
     * QQ
     * @return qq_num QQ
     */
    public String getQqNum() {
        return qqNum;
    }

    /**
     * QQ
     * @param qqNum QQ
     */
    public void setQqNum(String qqNum) {
        this.qqNum = qqNum == null ? null : qqNum.trim();
    }

    /**
     * 邮箱
     * @return email 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 照片附件ID
     * @return photo_id 照片附件ID
     */
    public Long getPhotoId() {
        return photoId;
    }

    /**
     * 照片附件ID
     * @param photoId 照片附件ID
     */
    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
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
        sb.append(", sex=").append(sex);
        sb.append(", idNum=").append(idNum);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", maritalStatus=").append(maritalStatus);
        sb.append(", nationId=").append(nationId);
        sb.append(", politicalOutlook=").append(politicalOutlook);
        sb.append(", originPlace=").append(originPlace);
        sb.append(", adderss=").append(adderss);
        sb.append(", mobilePhone=").append(mobilePhone);
        sb.append(", telephone=").append(telephone);
        sb.append(", wechatNum=").append(wechatNum);
        sb.append(", qqNum=").append(qqNum);
        sb.append(", email=").append(email);
        sb.append(", photoId=").append(photoId);
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
        sb.append("]");
        return sb.toString();
    }
}