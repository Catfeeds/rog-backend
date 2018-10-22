package com.rograndec.feijiayun.chain.inf.pos.member.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * @ClassName: POSSetMemberInfoVO   
 * @Description: POS 会员开卡
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月6日 下午1:38:39
 */
@ApiModel(value = "POSSetMemberInfoVO", description = "POS会员开卡")
public class POSSetMemberInfoVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    
    /**
     * 企业ID
     */
    @NotNull(message="企业ID不能为空！")
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId = 0L;


    /**
     * 会员卡类型ID
     */
    @NotNull(message="会员卡类型ID不能为空！")
    @ApiModelProperty(value = "会员卡类型ID")
    private Long cardTypeId;

    /**
     * 会员卡类型名称
     */
    @ApiModelProperty(value = "会员卡类型名称",hidden=true)
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
    @ApiModelProperty(value = "发卡时间",hidden=true)
    private Date sendCardTime;

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
    //@NotBlank(message="身份证号不能为空！")
    @ApiModelProperty(value = "身份证号")
    private String idNum;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message="出生日期不能为空！")
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
    @ApiModelProperty(value = "累积积分",hidden=true)
    private BigDecimal totalIntegral;

    /**
     * 当前积分
     */
    @ApiModelProperty(value = "当前积分",hidden=true)
    private BigDecimal currentIntegral;

    /**
     * 累积储值
     */
    @ApiModelProperty(value = "累积储值",hidden=true)
    private BigDecimal totalStoredAmount;

    /**
     * 当前储值
     */
    @ApiModelProperty(value = "当前储值",hidden=true)
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
    
    public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getParentId() {
		return parentId;
	}

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

   

    
}