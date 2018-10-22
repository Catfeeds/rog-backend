package com.rograndec.feijiayun.chain.business.report.retail.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/10/23 <br/>
 * 描述：销售报表-柜组销售报表
 */
public class ReportCabinetSaleVO extends ReportSaleAnalysisVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "销售日期")
    private String saleDate;

    @ApiModelProperty(value = "日结日期")
    private String dailyTime;

    @ApiModelProperty(value = "柜组ID")
    private Long cargoAreaId;

    @ApiModelProperty(value = "柜组编码")
    private String cargoAreaCode;

    @ApiModelProperty(value = "柜组名称")
    private String cargoAreaName;

    @ApiModelProperty(value = "营业人员名称")
    private String clerkName;

    @ApiModelProperty(value = "营业人员ID")
    private Long clerkId;

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

    public Long getCargoAreaId() {
        return cargoAreaId;
    }

    public void setCargoAreaId(Long cargoAreaId) {
        this.cargoAreaId = cargoAreaId;
    }

    public String getCargoAreaCode() {
        return cargoAreaCode;
    }

    public void setCargoAreaCode(String cargoAreaCode) {
        this.cargoAreaCode = cargoAreaCode;
    }

    public String getCargoAreaName() {
        return cargoAreaName;
    }

    public void setCargoAreaName(String cargoAreaName) {
        this.cargoAreaName = cargoAreaName;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public Long getClerkId() {
        return clerkId;
    }

    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }
}
