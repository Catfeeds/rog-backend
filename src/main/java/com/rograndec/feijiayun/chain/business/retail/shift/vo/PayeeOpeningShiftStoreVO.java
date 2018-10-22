package com.rograndec.feijiayun.chain.business.retail.shift.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zeshi.sun on 2017/9/22.
 */
public class PayeeOpeningShiftStoreVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(required = true, value = "主键ID")
    private Long id;

    /**
     * 日期
     */
    @ApiModelProperty(required = true, value = "日期")
    private Date createTime;

    /**
     * 款台
     */
    @ApiModelProperty(required = true, value = "款台")
    private String stand;

    /**
     * 班组
     */
    @ApiModelProperty(required = true, value = "班组")
    private String team;

    /**
     * 收款人员
     */
    @ApiModelProperty(required = true, value = "收款人员")
    private String payeeName;

    /**
     * 开班时间
     */
    @ApiModelProperty(required = true, value = "开班时间")
    private Date openingTime;

    /**
     * 交班时间
     */
    @ApiModelProperty(required = true, value = "交班时间")
    private Date shiftTime;

    /**
     * 销售笔数
     */
    @ApiModelProperty(required = true, value = "销售笔数")
    private Integer salePens;

    /**
     * 销售金额
     */
    @ApiModelProperty(required = true, value = "销售金额")
    private BigDecimal saleAmount;

    /**
     * 退货笔数
     */
    @ApiModelProperty(required = true, value = "退货笔数")
    private Integer returnPens;

    /**
     * 退货金额
     */
    @ApiModelProperty(required = true, value = "退货金额")
    private BigDecimal returnAmount;


    /**
     * 接收备用金
     */
    @ApiModelProperty(required = true, value = "接收备用金")
    private BigDecimal acceptSpareMoney;

    /**
     * 下放备用金
     */
    @ApiModelProperty(required = true, value = "下放备用金")
    private BigDecimal sendSpareMoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public Date getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Date openingTime) {
        this.openingTime = openingTime;
    }

    public Date getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(Date shiftTime) {
        this.shiftTime = shiftTime;
    }

    public Integer getSalePens() {
        return salePens;
    }

    public void setSalePens(Integer salePens) {
        this.salePens = salePens;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Integer getReturnPens() {
        return returnPens;
    }

    public void setReturnPens(Integer returnPens) {
        this.returnPens = returnPens;
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }


    public BigDecimal getAcceptSpareMoney() {
        return acceptSpareMoney;
    }

    public void setAcceptSpareMoney(BigDecimal acceptSpareMoney) {
        this.acceptSpareMoney = acceptSpareMoney;
    }

    public BigDecimal getSendSpareMoney() {
        return sendSpareMoney;
    }

    public void setSendSpareMoney(BigDecimal sendSpareMoney) {
        this.sendSpareMoney = sendSpareMoney;
    }

    @Override
    public String toString() {
        return "PayeeOpeningShiftStoreVO[" +
                "id=" + id +
                ", createTime=" + createTime +
                ", stand='" + stand + '\'' +
                ", team='" + team + '\'' +
                ", payeeName='" + payeeName + '\'' +
                ", openingTime=" + openingTime +
                ", shiftTime=" + shiftTime +
                ", salePens=" + salePens +
                ", saleAmount=" + saleAmount +
                ", returnPens=" + returnPens +
                ", returnAmount=" + returnAmount +
                ", acceptSpareMoney=" + acceptSpareMoney +
                ", sendSpareMoney=" + sendSpareMoney +
                ']';
    }
}
