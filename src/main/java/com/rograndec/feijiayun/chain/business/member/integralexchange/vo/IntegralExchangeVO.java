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
@ApiModel(value = "IntegralExchangeVO", description = "积分兑换")
public class IntegralExchangeVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    /**
     * 兑换日期
     */
    @ApiModelProperty(value = "兑换日期(业务流程控制开启,必传)")
    private Date exchangeDate;

    /**
     * 兑换人员ID
     */
    @ApiModelProperty(value = "兑换人员ID(业务流程控制开启,必传)")
    private Long exchangeManId;

    /**
     * 兑换人员名称
     */
    @ApiModelProperty(value = "兑换人员名称")
    private String exchangeManName;

    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID(必传)")
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


    @ApiModelProperty(value = "积分兑换明细")
    private List<IntegralExchangeDtlVO> exchangeDtlVOList;


    /**
     * 兑换数量
     */
    @ApiModelProperty(value = "兑换数量总和")
    private BigDecimal exchangeQuantityTotal;

    /**
     * 扣除积分
     */
    @ApiModelProperty(value = "扣除积分")
    private BigDecimal deductIntegralTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public Long getExchangeManId() {
        return exchangeManId;
    }

    public void setExchangeManId(Long exchangeManId) {
        this.exchangeManId = exchangeManId;
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

    public List<IntegralExchangeDtlVO> getExchangeDtlVOList() {
        return exchangeDtlVOList;
    }

    public void setExchangeDtlVOList(List<IntegralExchangeDtlVO> exchangeDtlVOList) {
        this.exchangeDtlVOList = exchangeDtlVOList;
    }

    public BigDecimal getExchangeQuantityTotal() {
        return exchangeQuantityTotal;
    }

    public void setExchangeQuantityTotal(BigDecimal exchangeQuantityTotal) {
        this.exchangeQuantityTotal = exchangeQuantityTotal;
    }

    public BigDecimal getDeductIntegralTotal() {
        return deductIntegralTotal;
    }

    public void setDeductIntegralTotal(BigDecimal deductIntegralTotal) {
        this.deductIntegralTotal = deductIntegralTotal;
    }
}
