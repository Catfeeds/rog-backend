package com.rograndec.feijiayun.chain.business.index.warning.vo;

import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.QualificationConfigVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierQualificationReportVO;
import com.rograndec.feijiayun.chain.common.constant.Nature;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SupplierWarnReportVO implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value = "企业id")
    private Long id;

    /**
     * 性质（0-批发企业；1-生产企业）
     */
    @ApiModelProperty(value = "性质（0-批发企业；1-生产企业）")
    private Integer nature;

    @ApiModelProperty(value = "性质（0-批发企业；1-生产企业）")
    private String natureDesc;

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

    public static List<SupplierWarnReportVO> getEnterpriseWarnReportVOs(List<SupplierQualificationReportVO> enterpriseQualificationWarnReports){

        List<SupplierWarnReportVO> all = new ArrayList<>();

        enterpriseQualificationWarnReports.stream().forEach(qr -> {
            List<SupplierWarnReportVO> enterpriseWarnReportVO = getEnterpriseWarnReportVO(qr);
            all.addAll(enterpriseWarnReportVO);
        });

        return all;
    }


    public static List<SupplierWarnReportVO> getEnterpriseWarnReportVO(SupplierQualificationReportVO enterpriseQualificationWarnReport){

        List<SupplierWarnReportVO> enterpriseWarnReportVOS = new ArrayList<>();

        List<QualificationConfigVO> qualificationConfigVOS = enterpriseQualificationWarnReport.getQualificationConfigVOS();


        for(QualificationConfigVO qualificationConfigVO : qualificationConfigVOS){

            SupplierWarnReportVO enterpriseWarnReportVO = new SupplierWarnReportVO();
            /**
             * 主键ID
             */
            enterpriseWarnReportVO.setId(enterpriseQualificationWarnReport.getId());

            /**
             * 性质（0-批发企业；1-生产企业）
             */
            enterpriseWarnReportVO.setNature(enterpriseQualificationWarnReport.getNature());

            enterpriseWarnReportVO.setNatureDesc(Nature.getName(enterpriseQualificationWarnReport.getNature()));

            /**
             * 组织机构编码
             */
            enterpriseWarnReportVO.setCode(enterpriseQualificationWarnReport.getCode());

            /**
             * 组织机构名称
             */
            enterpriseWarnReportVO.setName(enterpriseQualificationWarnReport.getName());

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

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public String getNatureDesc() {
        return natureDesc;
    }

    public void setNatureDesc(String natureDesc) {
        this.natureDesc = natureDesc;
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
}