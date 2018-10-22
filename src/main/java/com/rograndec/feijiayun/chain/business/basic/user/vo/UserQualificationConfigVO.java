package com.rograndec.feijiayun.chain.business.basic.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


@ApiModel
public class UserQualificationConfigVO implements Serializable {

    /**
     * user 资质id
     */
    @ApiModelProperty(value = "user 资质id,修改时需要传入,新增时不需要", required = false)
    private Long id;


    /**
     * 员工资质ID
     */
    @ApiModelProperty(value = " 员工资质ID", required = true)
    private Long qualificationId;

    /**
     * 等级
     */
    @ApiModelProperty(value = " 等级", required = false)
    private String grade = "";

    /**
     * 资格证书号
     */
    @ApiModelProperty(value = " 资格证书号", required = false)
    private String code = "";

    /**
     * 注册证书号
     */
    @ApiModelProperty(value = " 注册证书号", required = false)
    private String registerCode = "";

    /**
     * 适用地区
     */
    @ApiModelProperty(value = " 适用地区", required = false)
    private String region = "";

    /**
     * 适用类别
     */
    @ApiModelProperty(value = " 适用类别", required = false)
    private String category = "";

    /**
     * 适用范围
     */
    @ApiModelProperty(value = " 适用范围", required = false)
    private String range = "";

    /**
     * 附件ID
     */
    @ApiModelProperty(value = " 附件ID", required = false)
    private Long fileId;


    public Long getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Long qualificationId) {
        this.qualificationId = qualificationId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}