package com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO;

import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class EnterpriseQualificationReport2ExcelVO implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value = "企业id")
    private Long id;
	
    /**
     * 药店类型（0-总部；1-自营店；2-加盟店）
     */
	@ApiModelProperty(value = "药店类型（0-总部；1-自营店；2-加盟店）")
    private Integer chainType;

    @ApiModelProperty(value = "药店类型（0-总部；1-自营店；2-加盟店）")
	private String chainTypeName;


    private String groupName;

    /**
     * 编码
     */
	@ApiModelProperty(value = "组织机构编码")
    private String code;

    /**
     * 企业名称
     */
	@ApiModelProperty(value = "组织机构名称")
    private String name;

    @ApiModelProperty(value = "企业资质名称")
    private String qualificationName;

    @ApiModelProperty(value = "企业资质编码")
    private String qualificationCode;

    private Date validDate;
    @ApiModelProperty(value = "有效期至")
    private String validDateString;

    @ApiModelProperty(value = "附件id")
    private String fileId;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    public String getChainTypeName() {
        return ChainType.getName(chainType);
    }

    public void setChainTypeName(String chainTypeName) {
        this.chainTypeName = chainTypeName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
}