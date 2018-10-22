package com.rograndec.feijiayun.chain.business.member.storedamount.vo;

import com.rograndec.feijiayun.chain.business.member.storedamount.entity.StoredAmount;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/22 <br/>
 * 描述：积分-储值管理
 */
public class StoredAmountPageVO implements Serializable {

    private static final long serialVersionUID = 1526277450628602833L;
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 卡类别（0-积分+储值；1-仅积分；2-仅储值）
     */
    @ApiModelProperty(value = "会员卡类型")
    private int type;

    /**
     * 会员卡类型名称
     */
    @ApiModelProperty(value = "会员卡类型名称")
    private String cardTypeName;

    /**
     * 级别名称
     */
    @ApiModelProperty(value = "级别名称")
    private String levelName;

    /**
     * 会员卡号
     */
    @ApiModelProperty(value = "会员卡号")
    private String cardCode;

    /**
     * 会员姓名
     */
    @ApiModelProperty(value = "会员姓名")
    private String memberName;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String mobilePhone;

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
    private BigDecimal totalStoredAmount = new BigDecimal(0.00);

    /**
     * 当前储值
     */
    @ApiModelProperty(value = "当前储值")
    private BigDecimal currentStoredAmount = new BigDecimal(0.00);

    /**
     * 发卡门店名称
     */
    @ApiModelProperty(value = "发卡门店名称")
    private String sendCardStorerName;

    /**
     * 发卡时间
     */
    @ApiModelProperty(value = "发卡时间")
    private Date sendCardTime;

    /**
     * 状态（0-正常；1-未发卡；2-挂失；3-注销）
     */
    @ApiModelProperty(value = "状态（用于前台显示）（0-正常；1-未发卡；2-挂失；3-注销）")
    private String statusName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "密码确认")
    private String passwordConfirm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
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

    public String getSendCardStorerName() {
        return sendCardStorerName;
    }

    public void setSendCardStorerName(String sendCardStorerName) {
        this.sendCardStorerName = sendCardStorerName;
    }

    public Date getSendCardTime() {
        return sendCardTime;
    }

    public void setSendCardTime(Date sendCardTime) {
        this.sendCardTime = sendCardTime;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "StoredAmountPageVO{" +
                "id=" + id +
                ", cardTypeName='" + cardTypeName + '\'' +
                ", levelName='" + levelName + '\'' +
                ", cardCode='" + cardCode + '\'' +
                ", memberName='" + memberName + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", totalIntegral=" + totalIntegral +
                ", currentIntegral=" + currentIntegral +
                ", totalStoredAmount=" + totalStoredAmount +
                ", currentStoredAmount=" + currentStoredAmount +
                ", sendCardStorerName='" + sendCardStorerName + '\'' +
                ", sendCardTime=" + sendCardTime +
                ", statusName='" + statusName + '\'' +
                '}';
    }

    /**
     * StoredAmountPageVO 转换为StoredAmount
     *
     * @param loginUser 用户信息
     * @param storedAmountPageVO 会员信息
     * @param changeType 操作类型
     * @param changeValue 改变量
     * @return 返回存入数据库的对象
     */
    public static StoredAmount convertToStoredAmount(UserVO loginUser, StoredAmountPageVO storedAmountPageVO,
                                                     int changeType, BigDecimal changeValue,
                                                     Long transferMemberId, String transferCardCode) {
        StoredAmount storedAmount = new StoredAmount();
        storedAmount.setEnterpriseId(loginUser.getEnterpriseId());
        storedAmount.setParentId(loginUser.getParentId());
        storedAmount.setMemberId(storedAmountPageVO.getId());
        storedAmount.setCardCode(storedAmountPageVO.getCardCode());
        storedAmount.setTotalStoredAmount(storedAmountPageVO.getTotalStoredAmount());
        storedAmount.setCurrentStoredAmount(storedAmountPageVO.getCurrentStoredAmount());
        storedAmount.setStatus(1);//这个状态没什么用就以‘1’来代替
        storedAmount.setCreaterId(loginUser.getUserId());
        storedAmount.setCreaterCode(loginUser.getUserCode());
        storedAmount.setCreaterName(loginUser.getUserName());
        storedAmount.setCreateTime(new Date());
        storedAmount.setChangeType(changeType);//储值改变类型
        storedAmount.setChangeStoredAmount(changeValue);//储值改变量
        storedAmount.setTransferMemberId(transferMemberId);
        storedAmount.setTransferCardCode(transferCardCode);
        storedAmount.setBaseOrderDate(new Date());
        switch (changeType) {
            case 0://充值
            case 3://转账-转入
                storedAmount.setNewTotalStoredAmount(storedAmountPageVO.getTotalStoredAmount().add(changeValue));
                storedAmount.setNewCurrentStoredAmount(storedAmountPageVO.getCurrentStoredAmount().add(changeValue));
                break;
            case 1://扣款
            case 2://转账-转出
                storedAmount.setNewTotalStoredAmount(storedAmountPageVO.getTotalStoredAmount());
                storedAmount.setNewCurrentStoredAmount(storedAmountPageVO.getCurrentStoredAmount().subtract(changeValue));
                break;
            default:
                break;
        }
        return storedAmount;
    }
}