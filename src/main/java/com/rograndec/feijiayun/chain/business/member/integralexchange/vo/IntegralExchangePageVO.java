package com.rograndec.feijiayun.chain.business.member.integralexchange.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by dudy on 2017/9/23.
 */
@ApiModel(value = "IntegralExchangePageVO", description = "积分兑换清单页面VO")
public class IntegralExchangePageVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 兑换日期
     */
    @ApiModelProperty(value = "兑换日期")
    private Date exchangeDate;

    /**
     * 积分兑换单号
     */
    @ApiModelProperty(value = "积分兑换单号")
    private String code;

    /**
     * 兑换人员ID
     */
    @ApiModelProperty(value = "兑换人员ID")
    private Long exchangeManId;

    /**
     * 兑换人员编码
     */
    @ApiModelProperty(value = "兑换人员编码")
    private String exchangeManCode;

    /**
     * 兑换人员名称
     */
    @ApiModelProperty(value = "兑换人员名称")
    private String exchangeManName;

    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID")
    private Long memberId;

    /**
     * 会员卡号
     */
    @ApiModelProperty(value = "会员卡号")
    private String memberCardCode;

    /**
     * 当前积分
     */
    @ApiModelProperty(value = "当前积分")
    private BigDecimal currentIntegral;

    /**
     * 扣除积分
     */
    @ApiModelProperty(value = "扣除积分")
    private BigDecimal deductIntegral;

    /**
     * 剩余积分
     */
    @ApiModelProperty(value = "剩余积分")
    private BigDecimal remainderIntegral;


    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getExchangeManId() {
        return exchangeManId;
    }

    public void setExchangeManId(Long exchangeManId) {
        this.exchangeManId = exchangeManId;
    }

    public String getExchangeManCode() {
        return exchangeManCode;
    }

    public void setExchangeManCode(String exchangeManCode) {
        this.exchangeManCode = exchangeManCode;
    }

    public String getExchangeManName() {
        return exchangeManName;
    }

    public void setExchangeManName(String exchangeManName) {
        this.exchangeManName = exchangeManName;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberCardCode() {
        return memberCardCode;
    }

    public void setMemberCardCode(String memberCardCode) {
        this.memberCardCode = memberCardCode;
    }

    public BigDecimal getCurrentIntegral() {
        return currentIntegral;
    }

    public void setCurrentIntegral(BigDecimal currentIntegral) {
        this.currentIntegral = currentIntegral;
    }

    public BigDecimal getDeductIntegral() {
        return deductIntegral;
    }

    public void setDeductIntegral(BigDecimal deductIntegral) {
        this.deductIntegral = deductIntegral;
    }

    public BigDecimal getRemainderIntegral() {
        return remainderIntegral;
    }

    public void setRemainderIntegral(BigDecimal remainderIntegral) {
        this.remainderIntegral = remainderIntegral;
    }


}
