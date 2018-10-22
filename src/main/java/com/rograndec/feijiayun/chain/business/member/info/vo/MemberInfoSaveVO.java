package com.rograndec.feijiayun.chain.business.member.info.vo;

import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.httpclient.util.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberInfoSaveVO implements Serializable{

    private static final long serialVersionUID = 1L;

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
     * 会员卡级别名称
     */
    @ApiModelProperty(value = "会员卡级别名称")
    private String levelName;

    /**
     * 类别（0-积分+储值；1-仅积分；2-仅储值）
     */
    @ApiModelProperty(value = "类别（0-积分+储值；1-仅积分；2-仅储值）")
    private Integer type;

    /**
     * 类别（0-积分+储值；1-仅积分；2-仅储值）
     */
    @ApiModelProperty(value = "类别[用于给前台显示]（0-积分+储值；1-仅积分；2-仅储值）")
    private String typeName;

    /**
     * 价格策略（0-零售价；1-会员价）
     */
    @ApiModelProperty(value = "价格策略（0-零售价；1-会员价）")
    private Integer priceStrategy;

    /**
     * 价格策略（0-零售价；1-会员价）
     */
    @ApiModelProperty(value = "价格策略[用于给前台显示]（0-零售价；1-会员价）")
    private String priceStrategyName;

    /**
     * 价格策略
     */
    @ApiModelProperty(value = "折让策略")
    private BigDecimal discountStrategy;

    /**
     * 折让策略 + %
     */
    @ApiModelProperty(value = "[用于给前台显示]折让策略")
    private String discountStrategyName;

    /**
     * 积分策略金额
     */
    @ApiModelProperty(value = "积分策略金额")
    private BigDecimal amount;

    /**
     * 积分策略积分
     */
    @ApiModelProperty(value = "积分策略积分")
    private BigDecimal integral;

    /**
     * amount元积 + integral + 分
     */
    @ApiModelProperty(value = "积分策略[给前台显示]")
    private String amountIntegralName;

    /**
     * 会员信息对象以下******************************************************************
     */
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

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
    @ApiModelProperty(value = "发卡时间--新增时不用传")
    private Date sendCardTime;

    /**
     * 发卡时间--用于导出
     */
    @ApiModelProperty(value = "发卡时间--用于导出")
    private String sendCardTimeStr;

    /**
     * 发卡人ID
     */
    @ApiModelProperty(value = "发卡人ID")
    private Long sendCardManId;
    /**
     * 发卡人姓名
     */
    @ApiModelProperty(value = "发卡人姓名--新增时不用传")
    private String sendCardManName;

    /**
     * 发卡门店名称
     */
    @ApiModelProperty(value = "发卡门店名称--新增时不用传")
    private String sendCardStorerName;

    /**
     * 储值金额
     */
    @ApiModelProperty(value = "储值金额[在type=1的情况不显示，其他情况前台默认值给0.00]")
    private BigDecimal storedAmount;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码[在type = 1的情况下不显示，其他情况前台默认值显示8个0]")
    private String password;

    /**
     * 密码确认
     */
    @ApiModelProperty(value = "密码确认[保存之前前台确认两次填的是否一致]")
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
     * 性别（0-男；1-女；2-其它）
     */
    @ApiModelProperty(value = "性别（0-男；1-女；2-其它）---用于导出显示")
    private String sexExport;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    private Date birthDate;

    /**
     * 出生日期--用于导出
     */
    @ApiModelProperty(value = "出生日期--用于导出")
    private String birthDateStr;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idNum;

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
    private String address;

    /**
     * 累积积分
     */
    @ApiModelProperty(value = "累积积分---用于导出")
    private BigDecimal totalIntegral;

    /**
     * 当前积分
     */
    @ApiModelProperty(value = "当前积分---用于导出")
    private BigDecimal currentIntegral;

    /**
     * 累积储值
     */
    @ApiModelProperty(value = "累积储值---用于导出")
    private BigDecimal totalStoredAmount;

    /**
     * 当前储值
     */
    @ApiModelProperty(value = "当前储值---用于导出")
    private BigDecimal currentStoredAmount;

    public Long getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(Long cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getPriceStrategy() {
        return priceStrategy;
    }

    public void setPriceStrategy(Integer priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    public String getPriceStrategyName() {
        return priceStrategyName;
    }

    public void setPriceStrategyName(String priceStrategyName) {
        this.priceStrategyName = priceStrategyName;
    }

    public BigDecimal getDiscountStrategy() {
        return discountStrategy;
    }

    public void setDiscountStrategy(BigDecimal discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public String getDiscountStrategyName() {
        return discountStrategyName;
    }

    public void setDiscountStrategyName(String discountStrategyName) {
        this.discountStrategyName = discountStrategyName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public String getAmountIntegralName() {
        return amountIntegralName;
    }

    public void setAmountIntegralName(String amountIntegralName) {
        this.amountIntegralName = amountIntegralName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCardCodeType() {
        return cardCodeType;
    }

    public void setCardCodeType(Integer cardCodeType) {
        this.cardCodeType = cardCodeType;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public Date getSendCardTime() {
        return sendCardTime;
    }

    public void setSendCardTime(Date sendCardTime) {
        this.sendCardTime = sendCardTime;
    }

    public String getSendCardManName() {
        return sendCardManName;
    }

    public void setSendCardManName(String sendCardManName) {
        this.sendCardManName = sendCardManName;
    }

    public String getSendCardStorerName() {
        return sendCardStorerName;
    }

    public void setSendCardStorerName(String sendCardStorerName) {
        this.sendCardStorerName = sendCardStorerName;
    }

    public BigDecimal getStoredAmount() {
        return storedAmount;
    }

    public void setStoredAmount(BigDecimal storedAmount) {
        this.storedAmount = storedAmount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechatNum() {
        return wechatNum;
    }

    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public BigDecimal getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(BigDecimal totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public BigDecimal getCurrentIntegral() {
        return currentIntegral;
    }

    public void setCurrentIntegral(BigDecimal currentIntegral) {
        this.currentIntegral = currentIntegral;
    }

    public BigDecimal getTotalStoredAmount() {
        return totalStoredAmount;
    }

    public void setTotalStoredAmount(BigDecimal totalStoredAmount) {
        this.totalStoredAmount = totalStoredAmount;
    }

    public BigDecimal getCurrentStoredAmount() {
        return currentStoredAmount;
    }

    public void setCurrentStoredAmount(BigDecimal currentStoredAmount) {
        this.currentStoredAmount = currentStoredAmount;
    }

    public String getSexExport() {
        return sexExport;
    }

    public void setSexExport(String sexExport) {
        this.sexExport = sexExport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSendCardTimeStr() {
        return sendCardTimeStr;
    }

    public void setSendCardTimeStr(String sendCardTimeStr) {
        this.sendCardTimeStr = sendCardTimeStr;
    }

    public String getBirthDateStr() {
        return birthDateStr;
    }

    public void setBirthDateStr(String birthDateStr) {
        this.birthDateStr = birthDateStr;
    }

    public Long getSendCardManId() {
        return sendCardManId;
    }

    public void setSendCardManId(Long sendCardManId) {
        this.sendCardManId = sendCardManId;
    }

    @Override
    public String toString() {
        return "MemberInfoSaveVO{" +
                "  cardTypeId=" + cardTypeId +
                ", cardTypeName='" + cardTypeName + '\'' +
                ", levelName='" + levelName + '\'' +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                ", priceStrategy=" + priceStrategy +
                ", priceStrategyName='" + priceStrategyName + '\'' +
                ", discountStrategy=" + discountStrategy +
                ", discountStrategyName='" + discountStrategyName + '\'' +
                ", amount=" + amount +
                ", integral=" + integral +
                ", amountIntegralName='" + amountIntegralName + '\'' +
                ", id=" + id +
                ", cardCodeType=" + cardCodeType +
                ", cardCode='" + cardCode + '\'' +
                ", sendCardTime=" + sendCardTime +
                ", sendCardManName='" + sendCardManName + '\'' +
                ", sendCardStorerName='" + sendCardStorerName + '\'' +
                ", storedAmount=" + storedAmount +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", memberName='" + memberName + '\'' +
                ", sex=" + sex +
                ", birthDate=" + birthDate +
                ", idNum='" + idNum + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                ", wechatNum='" + wechatNum + '\'' +
                ", qqNum='" + qqNum + '\'' +
                '}';
    }

    public static MemberInfo convertToEntity(MemberInfoSaveVO memberInfoSaveVO, UserVO loginUser, boolean flag) throws DateParseException, ParseException {
        MemberInfo memberInfo = new MemberInfo();
        if (memberInfoSaveVO != null && loginUser != null){
            memberInfo.setMemberName(memberInfoSaveVO.getMemberName());
            memberInfo.setSex(memberInfoSaveVO.getSex());
            memberInfo.setBirthDate(memberInfoSaveVO.getBirthDate());
            memberInfo.setIdNum(memberInfoSaveVO.getIdNum());
            memberInfo.setMobilePhone(memberInfoSaveVO.getMobilePhone());
            memberInfo.setEmail(memberInfoSaveVO.getEmail());
            memberInfo.setWechatNum(memberInfoSaveVO.getWechatNum());
            memberInfo.setQqNum(memberInfoSaveVO.getQqNum());
            memberInfo.setAdderss(memberInfoSaveVO.getAddress());
            if (flag){
                /**
                 * 新增的过程中--状态变为正常
                 */
                memberInfo.setEnterpriseId(loginUser.getEnterpriseId());
                memberInfo.setParentId(loginUser.getParentId());
                memberInfo.setCardTypeId(memberInfoSaveVO.getCardTypeId());
                memberInfo.setCardTypeName(memberInfoSaveVO.getCardTypeName());
                memberInfo.setCardCodeType(memberInfoSaveVO.getCardCodeType());
                memberInfo.setCardCode(memberInfoSaveVO.getCardCode());
                memberInfo.setSendCardTime(new Date());
                memberInfo.setSendCardStorerId(loginUser.getEnterpriseId());
                memberInfo.setSendCardStorerCode(loginUser.getEnterpriseCode());
                memberInfo.setSendCardStorerName(loginUser.getEnterpriseName());
                if (1 != memberInfoSaveVO.getType()){
                    memberInfo.setStoredAmount(memberInfoSaveVO.getStoredAmount());
                    //新增过程中初始化累计储值和当前储值（在储值卡+积分储值卡）
                    memberInfo.setTotalStoredAmount(memberInfoSaveVO.getStoredAmount());
                    memberInfo.setCurrentStoredAmount(memberInfoSaveVO.getStoredAmount());
                    memberInfo.setCurrentIntegral(BigDecimal.ZERO);
                    memberInfo.setTotalIntegral(BigDecimal.ZERO);
                    memberInfo.setPassword(memberInfoSaveVO.getPassword());
                    memberInfo.setPasswordConfirm(memberInfoSaveVO.getPasswordConfirm());
                }else {
                    memberInfo.setCurrentIntegral(BigDecimal.ZERO);
                    memberInfo.setTotalIntegral(BigDecimal.ZERO);
                }
                memberInfo.setStatus(0);
                memberInfo.setCreaterId(loginUser.getUserId());
                memberInfo.setCreaterCode(loginUser.getUserCode());
                memberInfo.setCreaterName(loginUser.getUserName());
                memberInfo.setCreateTime(new Date());
                memberInfo.setModifierId(loginUser.getUserId());
                memberInfo.setModifierCode(loginUser.getUserCode());
                memberInfo.setModifierName(loginUser.getUserName());
                memberInfo.setUpdateTime(new Date());
            }else{
                /**
                 * 修改的过程中
                 */
                memberInfo.setId(memberInfoSaveVO.getId());
                memberInfo.setModifierId(loginUser.getUserId());
                memberInfo.setModifierCode(loginUser.getUserCode());
                memberInfo.setModifierName(loginUser.getUserName());
                memberInfo.setUpdateTime(new Date());
            }
        }
        return memberInfo;
    }

    public static MemberInfo convertToIssuingCards(MemberInfoSaveVO memberInfoSaveVO, UserVO loginUser) {
        MemberInfo memberInfo = new MemberInfo();
        if (memberInfoSaveVO != null){
            memberInfo.setId(memberInfoSaveVO.getId());
            memberInfo.setMemberName(memberInfoSaveVO.getMemberName());
            memberInfo.setSex(memberInfoSaveVO.getSex());
            memberInfo.setBirthDate(memberInfoSaveVO.getBirthDate());
            memberInfo.setIdNum(memberInfoSaveVO.getIdNum());
            memberInfo.setMobilePhone(memberInfoSaveVO.getMobilePhone());
            memberInfo.setEmail(memberInfoSaveVO.getEmail());
            memberInfo.setWechatNum(memberInfoSaveVO.getWechatNum());
            memberInfo.setQqNum(memberInfoSaveVO.getQqNum());
            memberInfo.setAdderss(memberInfoSaveVO.getAddress());
            memberInfo.setSendCardTime(new Date());
            memberInfo.setSendCardManId(loginUser.getUserId());
            memberInfo.setSendCardManCode(loginUser.getUserCode());
            memberInfo.setSendCardManName(loginUser.getUserName());
            memberInfo.setSendCardStorerId(loginUser.getEnterpriseId());
            memberInfo.setSendCardStorerCode(loginUser.getEnterpriseCode());
            memberInfo.setSendCardStorerName(loginUser.getEnterpriseName());
            /**
             * 发完卡后显示正常
             */
            memberInfo.setStatus(0);
        }
        return memberInfo;
    }

    public static MemberInfoSaveVO convertToSaveVO(MemberInfo memberInfo) {
        MemberInfoSaveVO memberInfoSaveVO = new MemberInfoSaveVO();
        memberInfoSaveVO.setId(memberInfo.getId());
        memberInfoSaveVO.setCardTypeId(memberInfo.getCardTypeId());
        memberInfoSaveVO.setCardTypeName(memberInfo.getCardTypeName());
        memberInfoSaveVO.setCardCodeType(memberInfo.getCardCodeType());
        memberInfoSaveVO.setCardCode(memberInfo.getCardCode());
        memberInfoSaveVO.setSendCardTime(memberInfo.getSendCardTime());
        memberInfoSaveVO.setSendCardManName(memberInfo.getSendCardManName());
        memberInfoSaveVO.setSendCardStorerName(memberInfo.getSendCardStorerName());
        memberInfoSaveVO.setTotalStoredAmount(memberInfo.getTotalStoredAmount());
        memberInfoSaveVO.setCurrentStoredAmount(memberInfo.getCurrentStoredAmount());
        memberInfoSaveVO.setTotalIntegral(memberInfo.getTotalIntegral());
        memberInfoSaveVO.setCurrentIntegral(memberInfo.getCurrentIntegral());
        memberInfoSaveVO.setPassword(memberInfo.getPassword());
        memberInfoSaveVO.setPasswordConfirm(memberInfo.getPasswordConfirm());
        memberInfoSaveVO.setMemberName(memberInfo.getMemberName());
        memberInfoSaveVO.setSex(memberInfo.getSex());
        memberInfoSaveVO.setBirthDate(memberInfo.getBirthDate());
        memberInfoSaveVO.setIdNum(memberInfo.getIdNum());
        memberInfoSaveVO.setMobilePhone(memberInfo.getMobilePhone());
        memberInfoSaveVO.setEmail(memberInfo.getEmail());
        memberInfoSaveVO.setWechatNum(memberInfo.getWechatNum());
        memberInfoSaveVO.setQqNum(memberInfo.getQqNum());
        memberInfoSaveVO.setAddress(memberInfo.getAdderss());
        return memberInfoSaveVO;
    }
}
