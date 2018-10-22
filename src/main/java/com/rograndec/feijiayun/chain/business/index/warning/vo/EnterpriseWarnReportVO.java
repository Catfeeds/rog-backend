package com.rograndec.feijiayun.chain.business.index.warning.vo;

import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.EnterpriseQualificationReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.QualificationConfigVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EnterpriseWarnReportVO implements Serializable {
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
	private String chainTypeDesc;


    private String groupName;

    /**
     * 组织机构编码
     */
    @ApiModelProperty(value = "组织机构编码")
    private String code;

    /**
     * 组织机构名称
     */
    @ApiModelProperty(value = "组织机构名称")
    private String name;

    @ApiModelProperty(value = "企业资质Id")
    private Long qualificationId;

    @ApiModelProperty(value = "企业资质名称")
    private String qualificationName;

    @ApiModelProperty(value = "企业资质编码")
    private String qualificationCode;

    @ApiModelProperty(value = "有效期至")
    private Date validDate;

    @ApiModelProperty(value = "附件id")
    private String fileId;

    /**
     * 预警内容
     */
    @ApiModelProperty(value = "预警内容")
    private String warningContext;

    /**
     * 预警信息
     */
    @ApiModelProperty(value = "预警信息")
    private String warningMessage;

    public static EnterpriseWarnReportVO getEnterpriseWarnReportVO(QualificationConfigVO qualificationConfigVO,Enterprise enterprise){

        EnterpriseWarnReportVO enterpriseWarnReportVO = new EnterpriseWarnReportVO();
        /**
         * 主键ID
         */
        enterpriseWarnReportVO.setId(enterprise.getId());

        /**
         * 药店类型（0-总部；1-自营店；2-加盟店）
         */
        enterpriseWarnReportVO.setChainType(enterprise.getChainType());

        String name = ChainType.getName(enterprise.getChainType());
        enterpriseWarnReportVO.setChainTypeDesc(name);

        enterpriseWarnReportVO.setGroupName(enterprise.getGroupName());

        /**
         * 组织机构编码
         */
        enterpriseWarnReportVO.setCode(enterprise.getCode());

        /**
         * 组织机构名称
         */
        enterpriseWarnReportVO.setName(enterprise.getName());

        enterpriseWarnReportVO.setQualificationId(qualificationConfigVO.getQualificationId());

        enterpriseWarnReportVO.setQualificationName(qualificationConfigVO.getQualificationName());

        enterpriseWarnReportVO.setQualificationCode(qualificationConfigVO.getQualificationCode());

        enterpriseWarnReportVO.setValidDate(qualificationConfigVO.getValidDate());

        enterpriseWarnReportVO.setFileId(qualificationConfigVO.getFileId());

        /**
         * 预警内容
         */
        enterpriseWarnReportVO.setWarningContext(qualificationConfigVO.getWarnContent());

        /**
         * 预警信息
         */
        enterpriseWarnReportVO.setWarningMessage(qualificationConfigVO.getWarnInfo());

        return enterpriseWarnReportVO;

    }

    public static List<EnterpriseWarnReportVO> getEnterpriseWarnReportVOs(List<EnterpriseQualificationReportVO> enterpriseQualificationWarnReports){

        List<EnterpriseWarnReportVO> all = new ArrayList<>();

        enterpriseQualificationWarnReports.stream().forEach(qr -> {
            List<EnterpriseWarnReportVO> enterpriseWarnReportVO = getEnterpriseWarnReportVO(qr);
            all.addAll(enterpriseWarnReportVO);
        });

        return all;
    }


    public static List<EnterpriseWarnReportVO> getEnterpriseWarnReportVO(EnterpriseQualificationReportVO enterpriseQualificationWarnReport){

        List<EnterpriseWarnReportVO> enterpriseWarnReportVOS = new ArrayList<>();

        List<QualificationConfigVO> qualificationConfigVOS = enterpriseQualificationWarnReport.getQualificationConfigVOS();


        for(QualificationConfigVO qualificationConfigVO : qualificationConfigVOS){

            EnterpriseWarnReportVO enterpriseWarnReportVO = new EnterpriseWarnReportVO();
            /**
             * 主键ID
             */
            enterpriseWarnReportVO.setId(enterpriseQualificationWarnReport.getId());

            /**
             * 药店类型（0-总部；1-自营店；2-加盟店）
             */
            enterpriseWarnReportVO.setChainType(enterpriseQualificationWarnReport.getChainType());

            enterpriseWarnReportVO.setChainTypeDesc(enterpriseQualificationWarnReport.getChainTypeName());

            enterpriseWarnReportVO.setGroupName(enterpriseQualificationWarnReport.getGroupName());

            /**
             * 组织机构编码
             */
            enterpriseWarnReportVO.setCode(enterpriseQualificationWarnReport.getCode());

            /**
             * 组织机构名称
             */
            enterpriseWarnReportVO.setName(enterpriseQualificationWarnReport.getName());

            enterpriseWarnReportVO.setQualificationId(qualificationConfigVO.getQualificationId());

            enterpriseWarnReportVO.setQualificationName(qualificationConfigVO.getQualificationName());

            enterpriseWarnReportVO.setQualificationCode(qualificationConfigVO.getQualificationCode());

            enterpriseWarnReportVO.setValidDate(qualificationConfigVO.getValidDate());

            enterpriseWarnReportVO.setFileId(qualificationConfigVO.getFileId());

            /**
             * 预警内容
             */
            enterpriseWarnReportVO.setWarningContext(qualificationConfigVO.getWarnContent());

            /**
             * 预警信息
             */
            enterpriseWarnReportVO.setWarningMessage(qualificationConfigVO.getWarnInfo());

            enterpriseWarnReportVOS.add(enterpriseWarnReportVO);
        }


        return enterpriseWarnReportVOS;

    }

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

    public String getChainTypeDesc() {
        return chainTypeDesc;
    }

    public void setChainTypeDesc(String chainTypeDesc) {
        this.chainTypeDesc = chainTypeDesc;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getWarningContext() {
        return warningContext;
    }

    public void setWarningContext(String warningContext) {
        this.warningContext = warningContext;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    public Long getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Long qualificationId) {
        this.qualificationId = qualificationId;
    }
}