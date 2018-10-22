package com.rograndec.feijiayun.chain.business.member.info.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rograndec.feijiayun.chain.business.member.integral.entity.IntegralRecord;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MemberInfoPageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


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
    private BigDecimal totalStoredAmount;

    /**
     * 当前储值
     */
    @ApiModelProperty(value = "当前储值")
    private BigDecimal currentStoredAmount;

    /**
     * 统计的累计积分
     */
    @ApiModelProperty(value = "用于右下角显示当前搜索条件下累计积分的合计")
    private BigDecimal stasticTotalIntegral;

    /**s
     * 统计的当前积分
     */
    @ApiModelProperty(value = "用于右下角显示当前搜索条件下当前积分的合计")
    private BigDecimal stasticCurrentIntegral;

    /**
     * 统计的累计储值
     */
    @ApiModelProperty(value = "用于右下角显示当前搜索条件下累计储值的合计")
    private BigDecimal stasticTotalStoredAmount;
    /**
     * 统计的当前储值
     */
    @ApiModelProperty(value = "用于右下角显示当前搜索条件下当前储值的合计")
    private BigDecimal stasticCurrentStoredAmount;
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
     * 发卡时间---针对于导出所用
     */
    @ApiModelProperty(value = "发卡时间---针对于导出所用")
    private String sendCardTimeStr;

    /**
     * 状态（0-正常；1-未发卡；2-挂失；3-注销）
     */
    @ApiModelProperty(value = "状态（0-正常；1-未发卡；2-挂失；3-注销）")
    private Integer status;

    /**
     * 状态（0-正常；1-未发卡；2-挂失；3-注销）
     */
    @ApiModelProperty(value = "状态（用于前台显示）（0-正常；1-未发卡；2-挂失；3-注销）")
    private String statusName;

    /**
     * 用于积分管理中增加积分的填写
     */
    @ApiModelProperty(value = "用于积分管理中增加积分的填写")
    private BigDecimal addPoint;

    /**
     * 用于积分管理中减少积分的填写
     */
    @ApiModelProperty(value = "用于积分管理中减少积分的填写")
    private BigDecimal subPoint;

    /**
     * 用于积分管理中换卡的卡号的ID
     */
    @ApiModelProperty(value = "用于积分管理中换卡的卡号的ID[前端不用传给我]")
    private Long changeCardId;

    /**
     * 用于积分管理中换卡的卡号
     */
    @ApiModelProperty(value = "用于积分管理中换卡的卡号")
    private String changeCardCode;

    /**
     * 用于积分管理里面的操作方式--操作类型（增分传add 减分传sub 清零传zero 换卡传change）
     */
    @ApiModelProperty(value = "用于积分管理里面的操作方式--操作类型（增分传add 减分传sub 清零传zero 换卡传change）")
    private String operation;

    public String getSendCardTimeStr() {
        return sendCardTimeStr;
    }

    public void setSendCardTimeStr(String sendCardTimeStr) {
        this.sendCardTimeStr = sendCardTimeStr;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public BigDecimal getAddPoint() {
        return addPoint;
    }

    public void setAddPoint(BigDecimal addPoint) {
        this.addPoint = addPoint;
    }

    public BigDecimal getSubPoint() {
        return subPoint;
    }

    public void setSubPoint(BigDecimal subPoint) {
        this.subPoint = subPoint;
    }

    public String getChangeCardCode() {
        return changeCardCode;
    }

    public void setChangeCardCode(String changeCardCode) {
        this.changeCardCode = changeCardCode;
    }

    public Long getChangeCardId() {
        return changeCardId;
    }

    public void setChangeCardId(Long changeCardId) {
        this.changeCardId = changeCardId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public BigDecimal getStasticTotalIntegral() {
        return stasticTotalIntegral;
    }

    public void setStasticTotalIntegral(BigDecimal stasticTotalIntegral) {
        this.stasticTotalIntegral = stasticTotalIntegral;
    }

    public BigDecimal getStasticCurrentIntegral() {
        return stasticCurrentIntegral;
    }

    public void setStasticCurrentIntegral(BigDecimal stasticCurrentIntegral) {
        this.stasticCurrentIntegral = stasticCurrentIntegral;
    }

    public BigDecimal getStasticTotalStoredAmount() {
        return stasticTotalStoredAmount;
    }

    public void setStasticTotalStoredAmount(BigDecimal stasticTotalStoredAmount) {
        this.stasticTotalStoredAmount = stasticTotalStoredAmount;
    }

    public BigDecimal getStasticCurrentStoredAmount() {
        return stasticCurrentStoredAmount;
    }

    public void setStasticCurrentStoredAmount(BigDecimal stasticCurrentStoredAmount) {
        this.stasticCurrentStoredAmount = stasticCurrentStoredAmount;
    }

    @Override
    public String toString() {
        return "MemberInfoPageVO{" +
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
                ", status=" + status +
                '}';
    }



    public static IntegralRecord convertToIntegralRecord(UserVO loginUser, MemberInfoPageVO memberInfoPageVO, String operation) throws Exception{
        IntegralRecord i = new IntegralRecord();
        i.setEnterpriseId(loginUser.getEnterpriseId());
        i.setParentId(loginUser.getParentId());
        i.setMemberId(memberInfoPageVO.getId());
        i.setCardCode(memberInfoPageVO.getCardCode());
        i.setTotalIntegral(memberInfoPageVO.getTotalIntegral());
        i.setCurrentIntegral(memberInfoPageVO.getCurrentIntegral());
        i.setBaseOrderDate(new Date());
        //这个状态没什么用就以‘1’来代替
        i.setStatus(1);
        UserEnterpriseUtils.setUserCreateOrModify(i,loginUser,true);
        if ("add".equals(operation)){
            i.setChangeType(0);
            i.setChangeIntegral(memberInfoPageVO.getAddPoint());
            i.setNewTotalIntegral(memberInfoPageVO.getTotalIntegral().add(memberInfoPageVO.getAddPoint()));
            i.setNewCurrentIntegral(memberInfoPageVO.getCurrentIntegral().add(memberInfoPageVO.getAddPoint()));
            i.setRemark("会员增分");
        }else if ("sub".equals(operation)){
            i.setChangeType(1);
            i.setChangeIntegral(memberInfoPageVO.getSubPoint());
            i.setNewTotalIntegral(memberInfoPageVO.getTotalIntegral());
            i.setNewCurrentIntegral(memberInfoPageVO.getCurrentIntegral().subtract(memberInfoPageVO.getSubPoint()));
            i.setRemark("会员减分");
        }else if ("zero".equals(operation)){
            i.setChangeType(3);
            i.setChangeIntegral(memberInfoPageVO.getCurrentIntegral());
            i.setNewTotalIntegral(memberInfoPageVO.getTotalIntegral());
            i.setNewCurrentIntegral(new BigDecimal(0));
            i.setRemark("会员清零");
        }else if ("change".equals(operation)){
            i.setChangeType(2);
            i.setNewMemberId(memberInfoPageVO.getChangeCardId());
            i.setNewCardCode(memberInfoPageVO.getChangeCardCode());
            //无变动积分 所以变动积分为0
            i.setChangeIntegral(new BigDecimal(0));
            i.setNewTotalIntegral(memberInfoPageVO.getTotalIntegral());
            i.setNewCurrentIntegral(memberInfoPageVO.getCurrentIntegral());
            i.setRemark("会员换卡");
        }
        return i;
    }
}
