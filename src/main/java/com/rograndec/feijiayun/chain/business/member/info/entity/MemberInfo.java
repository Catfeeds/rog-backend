package com.rograndec.feijiayun.chain.business.member.info.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_member_info
 * 
 * 
 * @author dudy
 * 
 * 2017-09-18
 */
public class MemberInfo implements Serializable {
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
     * 会员卡类型ID
     */
    @ApiModelProperty(value = "会员卡类型ID")
    private Long cardTypeId;

    /**
     * 会员卡类型名称
     */
    @ApiModelProperty(value = "会员卡类型名称")
    private String cardTypeName;

    /**
     * 会员卡号类型（0-会员卡；1-手机号）
     */
    @ApiModelProperty(value = "会员卡号类型（0-会员卡；1-手机号）")
    private Integer cardCodeType;

    /**
     * 会员卡号
     */
    @ApiModelProperty(value = "会员卡号")
    private String cardCode;

    /**
     * 发卡时间
     */
    @ApiModelProperty(value = "发卡时间")
    private Date sendCardTime;

    /**
     * 发卡人ID
     */
    @ApiModelProperty(value = "发卡人ID")
    private Long sendCardManId;

    /**
     * 发卡人编码
     */
    @ApiModelProperty(value = "发卡人编码")
    private String sendCardManCode;

    /**
     * 发卡人姓名
     */
    @ApiModelProperty(value = "发卡人姓名")
    private String sendCardManName;

    /**
     * 发卡门店ID
     */
    @ApiModelProperty(value = "发卡门店ID")
    private Long sendCardStorerId;

    /**
     * 发卡门店编码
     */
    @ApiModelProperty(value = "发卡门店编码")
    private String sendCardStorerCode;

    /**
     * 发卡门店名称
     */
    @ApiModelProperty(value = "发卡门店名称")
    private String sendCardStorerName;

    /**
     * 储值金额
     */
    @ApiModelProperty(value = "储值金额")
    private BigDecimal storedAmount;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 密码确认
     */
    @ApiModelProperty(value = "密码确认")
    private String passwordConfirm;

    /**
     * 会员姓名
     */
    @ApiModelProperty(value = "会员姓名")
    private String memberName;

    /**
     * 性别（0-男；1-女；2-其它）
     */
    @ApiModelProperty(value = "性别（0-男；1-女；2-其它）")
    private Integer sex;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idNum;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    private Date birthDate;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String mobilePhone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 微信
     */
    @ApiModelProperty(value = "微信")
    private String wechatNum;

    /**
     * QQ
     */
    @ApiModelProperty(value = "QQ")
    private String qqNum;

    /**
     * 住址
     */
    @ApiModelProperty(value = "住址")
    private String adderss;

    /**
     * 累积积分
     */
    @ApiModelProperty(value = "累积积分")
    private BigDecimal totalIntegral;

    /**
     * 当前积分
     */
    @ApiModelProperty(value = "当前积分")
    private BigDecimal currentIntegral;

    /**
     * 累积储值
     */
    @ApiModelProperty(value = "累积储值")
    private BigDecimal totalStoredAmount;

    /**
     * 当前储值
     */
    @ApiModelProperty(value = "当前储值")
    private BigDecimal currentStoredAmount;

    /**
     * 状态（0-正常；1-未发卡；2-挂失；3-注销）
     */
    @ApiModelProperty(value = "状态（0-正常；1-未发卡；2-挂失；3-注销）")
    private Integer status;

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
     * 最后修改人ID
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * saas_member_info
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
     * 会员卡类型ID
     * @return card_type_id 会员卡类型ID
     */
    public Long getCardTypeId() {
        return cardTypeId;
    }

    /**
     * 会员卡类型ID
     * @param cardTypeId 会员卡类型ID
     */
    public void setCardTypeId(Long cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    /**
     * 会员卡类型名称
     * @return card_type_name 会员卡类型名称
     */
    public String getCardTypeName() {
        return cardTypeName;
    }

    /**
     * 会员卡类型名称
     * @param cardTypeName 会员卡类型名称
     */
    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName == null ? null : cardTypeName.trim();
    }

    /**
     * 会员卡号类型（0-会员卡；1-手机号）
     * @return card_code_type 会员卡号类型（0-会员卡；1-手机号）
     */
    public Integer getCardCodeType() {
        return cardCodeType;
    }

    /**
     * 会员卡号类型（0-会员卡；1-手机号）
     * @param cardCodeType 会员卡号类型（0-会员卡；1-手机号）
     */
    public void setCardCodeType(Integer cardCodeType) {
        this.cardCodeType = cardCodeType;
    }

    /**
     * 会员卡号
     * @return card_code 会员卡号
     */
    public String getCardCode() {
        return cardCode;
    }

    /**
     * 会员卡号
     * @param cardCode 会员卡号
     */
    public void setCardCode(String cardCode) {
        this.cardCode = cardCode == null ? null : cardCode.trim();
    }

    /**
     * 发卡时间
     * @return send_card_time 发卡时间
     */
    public Date getSendCardTime() {
        return sendCardTime;
    }

    /**
     * 发卡时间
     * @param sendCardTime 发卡时间
     */
    public void setSendCardTime(Date sendCardTime) {
        this.sendCardTime = sendCardTime;
    }

    /**
     * 发卡人ID
     * @return send_card_man_id 发卡人ID
     */
    public Long getSendCardManId() {
        return sendCardManId;
    }

    /**
     * 发卡人ID
     * @param sendCardManId 发卡人ID
     */
    public void setSendCardManId(Long sendCardManId) {
        this.sendCardManId = sendCardManId;
    }

    /**
     * 发卡人编码
     * @return send_card_man_code 发卡人编码
     */
    public String getSendCardManCode() {
        return sendCardManCode;
    }

    /**
     * 发卡人编码
     * @param sendCardManCode 发卡人编码
     */
    public void setSendCardManCode(String sendCardManCode) {
        this.sendCardManCode = sendCardManCode == null ? null : sendCardManCode.trim();
    }

    /**
     * 发卡人姓名
     * @return send_card_man_name 发卡人姓名
     */
    public String getSendCardManName() {
        return sendCardManName;
    }

    /**
     * 发卡人姓名
     * @param sendCardManName 发卡人姓名
     */
    public void setSendCardManName(String sendCardManName) {
        this.sendCardManName = sendCardManName == null ? null : sendCardManName.trim();
    }

    /**
     * 发卡门店ID
     * @return send_card_storer_id 发卡门店ID
     */
    public Long getSendCardStorerId() {
        return sendCardStorerId;
    }

    /**
     * 发卡门店ID
     * @param sendCardStorerId 发卡门店ID
     */
    public void setSendCardStorerId(Long sendCardStorerId) {
        this.sendCardStorerId = sendCardStorerId;
    }

    /**
     * 发卡门店编码
     * @return send_card_storer_code 发卡门店编码
     */
    public String getSendCardStorerCode() {
        return sendCardStorerCode;
    }

    /**
     * 发卡门店编码
     * @param sendCardStorerCode 发卡门店编码
     */
    public void setSendCardStorerCode(String sendCardStorerCode) {
        this.sendCardStorerCode = sendCardStorerCode == null ? null : sendCardStorerCode.trim();
    }

    /**
     * 发卡门店名称
     * @return send_card_storer_name 发卡门店名称
     */
    public String getSendCardStorerName() {
        return sendCardStorerName;
    }

    /**
     * 发卡门店名称
     * @param sendCardStorerName 发卡门店名称
     */
    public void setSendCardStorerName(String sendCardStorerName) {
        this.sendCardStorerName = sendCardStorerName == null ? null : sendCardStorerName.trim();
    }

    /**
     * 储值金额
     * @return stored_amount 储值金额
     */
    public BigDecimal getStoredAmount() {
        return storedAmount;
    }

    /**
     * 储值金额
     * @param storedAmount 储值金额
     */
    public void setStoredAmount(BigDecimal storedAmount) {
        this.storedAmount = storedAmount;
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 密码确认
     * @return password_confirm 密码确认
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * 密码确认
     * @param passwordConfirm 密码确认
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm == null ? null : passwordConfirm.trim();
    }

    /**
     * 会员姓名
     * @return member_name 会员姓名
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * 会员姓名
     * @param memberName 会员姓名
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
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
     * 累积积分
     * @return total_integral 累积积分
     */
    public BigDecimal getTotalIntegral() {
        return totalIntegral;
    }

    /**
     * 累积积分
     * @param totalIntegral 累积积分
     */
    public void setTotalIntegral(BigDecimal totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    /**
     * 当前积分
     * @return current_integral 当前积分
     */
    public BigDecimal getCurrentIntegral() {
        return currentIntegral;
    }

    /**
     * 当前积分
     * @param currentIntegral 当前积分
     */
    public void setCurrentIntegral(BigDecimal currentIntegral) {
        this.currentIntegral = currentIntegral;
    }

    /**
     * 累积储值
     * @return total_stored_amount 累积储值
     */
    public BigDecimal getTotalStoredAmount() {
        return totalStoredAmount;
    }

    /**
     * 累积储值
     * @param totalStoredAmount 累积储值
     */
    public void setTotalStoredAmount(BigDecimal totalStoredAmount) {
        this.totalStoredAmount = totalStoredAmount;
    }

    /**
     * 当前储值
     * @return current_stored_amount 当前储值
     */
    public BigDecimal getCurrentStoredAmount() {
        return currentStoredAmount;
    }

    /**
     * 当前储值
     * @param currentStoredAmount 当前储值
     */
    public void setCurrentStoredAmount(BigDecimal currentStoredAmount) {
        this.currentStoredAmount = currentStoredAmount;
    }

    /**
     * 状态（0-正常；1-未发卡；2-挂失；3-注销）
     * @return status 状态（0-正常；1-未发卡；2-挂失；3-注销）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-正常；1-未发卡；2-挂失；3-注销）
     * @param status 状态（0-正常；1-未发卡；2-挂失；3-注销）
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
        sb.append(", parentId=").append(parentId);
        sb.append(", cardTypeId=").append(cardTypeId);
        sb.append(", cardTypeName=").append(cardTypeName);
        sb.append(", cardCodeType=").append(cardCodeType);
        sb.append(", cardCode=").append(cardCode);
        sb.append(", sendCardTime=").append(sendCardTime);
        sb.append(", sendCardManId=").append(sendCardManId);
        sb.append(", sendCardManCode=").append(sendCardManCode);
        sb.append(", sendCardManName=").append(sendCardManName);
        sb.append(", sendCardStorerId=").append(sendCardStorerId);
        sb.append(", sendCardStorerCode=").append(sendCardStorerCode);
        sb.append(", sendCardStorerName=").append(sendCardStorerName);
        sb.append(", storedAmount=").append(storedAmount);
        sb.append(", password=").append(password);
        sb.append(", passwordConfirm=").append(passwordConfirm);
        sb.append(", memberName=").append(memberName);
        sb.append(", sex=").append(sex);
        sb.append(", idNum=").append(idNum);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", mobilePhone=").append(mobilePhone);
        sb.append(", email=").append(email);
        sb.append(", wechatNum=").append(wechatNum);
        sb.append(", qqNum=").append(qqNum);
        sb.append(", adderss=").append(adderss);
        sb.append(", totalIntegral=").append(totalIntegral);
        sb.append(", currentIntegral=").append(currentIntegral);
        sb.append(", totalStoredAmount=").append(totalStoredAmount);
        sb.append(", currentStoredAmount=").append(currentStoredAmount);
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