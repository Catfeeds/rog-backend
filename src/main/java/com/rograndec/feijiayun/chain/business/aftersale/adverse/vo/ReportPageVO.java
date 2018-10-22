package com.rograndec.feijiayun.chain.business.aftersale.adverse.vo;

import com.rograndec.feijiayun.chain.business.aftersale.adverse.constant.FirstReportType;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.constant.ReportType;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.constant.ReportUnitType;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class ReportPageVO implements Serializable {


    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty("组织机构编码")
    private String enterpriseCode;

    @ApiModelProperty("组织机构名称")
    private String enterpriseName;

    /**
     * 是否首次报告（0-首次报告；1-跟踪报告）
     */
    @ApiModelProperty(value = "是否首次报告（0-首次报告；1-跟踪报告）")
    private Integer firstReport;

    @ApiModelProperty(value = "是否首次报告名称")
    private String firstReportName;

    /**
     * 报告类型（0-新的；1-严重；2-一般）
     */
    @ApiModelProperty(value = "报告类型（0-新的；1-严重；2-一般）")
    private Integer reportType;

    @ApiModelProperty(value = "报告类型名称")
    private String  reportTypeName;
    /**
     * 报告单位类型（0-医疗机构；1-生产企业；2-经营企业；3-个人；4-其它）
     */
    @ApiModelProperty(value = "报告单位类型（0-医疗机构；1-生产企业；2-经营企业；3-个人；4-其它）")
    private Integer reportUnitType;

    @ApiModelProperty(value = "报告单位类型名称")
    private String reportUnitTypeName;

    /**
     * 编码
     */
    @ApiModelProperty(value = "单号")
    private String code;

    /**
     * 报告时间
     */
    @ApiModelProperty(value = "报告时间")
    private Date reportTime;

    @ApiModelProperty(value = "报告时间名称")
    private String reportTimeName;

    /**
     * 报告单位名称
     */
    @ApiModelProperty(value = "报告单位名称")
    private String reportUnitName;

    /**
     * 报告人姓名
     */
    @ApiModelProperty(value = "报告人姓名")
    private String reportManName;

    public String getFirstReportName() {

        if(firstReport == null){
            return "";
        }


        return FirstReportType.getValue(firstReport);
    }

    public void setFirstReportName(String firstReportName) {
        this.firstReportName = firstReportName;
    }

    public String getReportTypeName() {

        if(reportType == null){
            return "";
        }

        return ReportType.getValue(reportType);
    }

    public void setReportTypeName(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }

    public String getReportUnitTypeName() {

        if(reportUnitType == null){
            return "";
        }

        return ReportUnitType.getValue(reportUnitType);
    }

    public void setReportUnitTypeName(String reportUnitTypeName) {
        this.reportUnitTypeName = reportUnitTypeName;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFirstReport() {
        return firstReport;
    }

    public void setFirstReport(Integer firstReport) {
        this.firstReport = firstReport;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public Integer getReportUnitType() {
        return reportUnitType;
    }

    public void setReportUnitType(Integer reportUnitType) {
        this.reportUnitType = reportUnitType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportUnitName() {
        return reportUnitName;
    }

    public void setReportUnitName(String reportUnitName) {
        this.reportUnitName = reportUnitName;
    }

    public String getReportManName() {
        return reportManName;
    }

    public void setReportManName(String reportManName) {
        this.reportManName = reportManName;
    }

    public String getReportTimeName() {

        if (reportTime == null) return "";
        return DateUtils.DateToString(reportTime, DateStyle.YYYY_MM_DD);
    }

    public void setReportTimeName(String reportTimeName) {
        this.reportTimeName = reportTimeName;
    }
}
