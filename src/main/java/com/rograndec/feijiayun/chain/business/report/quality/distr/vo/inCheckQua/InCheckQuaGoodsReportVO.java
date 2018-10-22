package com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inCheckQua;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.ReportCommonGoodsVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 功能描述:
 * Created by ST on 2017/10/21 19:47
 */

public class InCheckQuaGoodsReportVO extends ReportCommonGoodsVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    @ApiModelProperty(value = "配进验收单")
    private String code;


    @ApiModelProperty(value = "验收日期")
    private Date checkDate;

    /**
     * 供货单位ID
     */
    @ApiModelProperty(value = "配货单位ID")
    private Long distrUnitId;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(value = "配货单位编码")
    private String distrUnitCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "配货单位名称")
    private String distrUnitName;

    /**
     * 检验报告书（附件ID）集合
     */
    @ApiModelProperty(value = "检验报告书（附件ID）集合")
    private String testReportIds;

    /**
     * 检验项目ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "检验项目ID集合，多个用逗号分隔")
    private String checkProjectIds;

    @ApiModelProperty(value = "检验项目Name集合，多个用逗号分隔")
    private String checkProjectIdNames;


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

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Long getDistrUnitId() {
        return distrUnitId;
    }

    public void setDistrUnitId(Long distrUnitId) {
        this.distrUnitId = distrUnitId;
    }

    public String getDistrUnitCode() {
        return distrUnitCode;
    }

    public void setDistrUnitCode(String distrUnitCode) {
        this.distrUnitCode = distrUnitCode;
    }

    public String getDistrUnitName() {
        return distrUnitName;
    }

    public void setDistrUnitName(String distrUnitName) {
        this.distrUnitName = distrUnitName;
    }

    public String getTestReportIds() {
        return testReportIds;
    }

    public void setTestReportIds(String testReportIds) {
        this.testReportIds = testReportIds;
    }

    public String getCheckProjectIds() {
        return checkProjectIds;
    }

    public void setCheckProjectIds(String checkProjectIds) {
        this.checkProjectIds = checkProjectIds;
    }

    public String getCheckProjectIdNames() {
        return checkProjectIdNames;
    }

    public void setCheckProjectIdNames(String checkProjectIdNames) {
        this.checkProjectIdNames = checkProjectIdNames;
    }
}