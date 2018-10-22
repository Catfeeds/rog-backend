package com.rograndec.feijiayun.chain.business.report.retail.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/23 <br/>
 * 描述：销售报表-班组销售报表
 */
public class ReportTeamSaleVO extends ReportSaleAnalysisVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "销售日期")
    private String saleDate;

    @ApiModelProperty(value = "日结日期")
    private String dailyTime;

    @ApiModelProperty(value = "班组ID")
    private Long teamId;

    @ApiModelProperty(value = "班组编码")
    private String teamCode;

    @ApiModelProperty(value = "班组名称")
    private String teamName;

    @ApiModelProperty(value = "款台")
    private String standCode;

    @ApiModelProperty(value = "收款人ID")
    private Long payeeId;

    @ApiModelProperty(value = "收款人名称")
    private String payeeName;

    @ApiModelProperty(value = "开班日期")
    private Date startTime;

    @ApiModelProperty(value = "交班班日期")
    private Date endTime;

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public String getDailyTime() {
        return dailyTime;
    }

    public void setDailyTime(String dailyTime) {
        this.dailyTime = dailyTime;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getStandCode() {
        return standCode;
    }

    public void setStandCode(String standCode) {
        this.standCode = standCode;
    }

    public Long getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
