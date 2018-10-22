package com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO;

import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能描述：
 * Created by ST on 2017/10/17 19:13
 */

public class QualificationConfigVO {
    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;

    @ApiModelProperty(value = "企业资质Id")
    private Long qualificationId;

    @ApiModelProperty(value = "企业资质名称")
    private String qualificationName;

    @ApiModelProperty(value = "企业资质编码")
    private String qualificationCode;

    private Date validDate;
    @ApiModelProperty(value = "有效期至")
    private String validDateString;

    @ApiModelProperty(value = "附件id")
    private String fileId;

    @ApiModelProperty(value = "附件Name")
    private String fileName;

    private String url;
    /**
     * 预警内容
     */
    @ApiModelProperty(value = "预警内容")
    private String warnContent;

    /**
     * 预警信息
     */
    @ApiModelProperty(value = "预警信息")
    private String warnInfo;


    public static List<Long> getEnterpriseIds(List<QualificationConfigVO> qualificationConfigVOS){

       return qualificationConfigVOS.stream().map(qualificationConfigVO -> qualificationConfigVO.getEnterpriseId()).collect(Collectors.toList());
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getQualificationCode() {
        return qualificationCode;
    }

    public void setQualificationCode(String qualificationCode) {
        this.qualificationCode = qualificationCode;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getValidDateString() {
        return DateUtils.DateToString(validDate, DateStyle.YYYY_MM_DD_HH_MM_SS);
    }

    public void setValidDateString(String validDateString) {
        this.validDateString = validDateString;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Long getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Long qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getWarnContent() {
        return warnContent;
    }

    public void setWarnContent(String warnContent) {
        this.warnContent = warnContent;
    }

    public String getWarnInfo() {
        return warnInfo;
    }

    public void setWarnInfo(String warnInfo) {
        this.warnInfo = warnInfo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}