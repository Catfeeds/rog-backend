package com.rograndec.feijiayun.chain.business.system.set.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ST on 2017/9/6.
 */
public class GoodsQualificationVO {

    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 企业（总部）ID
     */
    @ApiModelProperty(value = "企业（总部）ID")
    private Long enterpriseId;

    /**
     * 验收类型ID
     */
    @ApiModelProperty(value = "验收类型ID")
    private Long checkTypeId;

    /**
     * 验收类型名称
     */
    @ApiModelProperty(value = "验收类型名称")
    private String checkTypeName;

    /**
     * 0：用户自定义；1-系统默认
     */
    @ApiModelProperty(value = "0：用户自定义；1-系统默认")
    private Integer sysType;

    /**
     * 资质类型是否必选（0-可选；1-必选）
     */
    @ApiModelProperty(value = "资质类型是否必选（0-可选；1-必选）")
    private Integer typeMust;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 控制类型（0-质量控制；1-仅提示）
     */
    @ApiModelProperty(value = "控制类型（0-质量控制；1-仅提示）")
    private Integer controlType;

    /**
     * 资质编号是否必填（0-只读；1-必填）
     */
    @ApiModelProperty(value = "资质编号是否必填（0-只读；1-必填）")
    private Integer codeMust;

    /**
     * 有效期至是否必填（0-只读；1-必填）
     */
    @ApiModelProperty(value = "有效期至是否必填（0-只读；1-必填）")
    private Integer validUntilMust;

    /**
     * 附件是否必填（0-非必填；1-必填）
     */
    @ApiModelProperty(value = "附件是否必填（0-非必填；1-必填）")
    private Integer fileMust;

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public Long getCheckTypeId() {
        return checkTypeId;
    }

    public String getCheckTypeName() {
        return checkTypeName;
    }

    public Integer getSysType() {
        return sysType;
    }

    public Integer getTypeMust() {
        return typeMust;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Integer getControlType() {
        return controlType;
    }

    public Integer getCodeMust() {
        return codeMust;
    }

    public Integer getValidUntilMust() {
        return validUntilMust;
    }

    public Integer getFileMust() {
        return fileMust;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public void setCheckTypeId(Long checkTypeId) {
        this.checkTypeId = checkTypeId;
    }

    public void setCheckTypeName(String checkTypeName) {
        this.checkTypeName = checkTypeName;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }

    public void setTypeMust(Integer typeMust) {
        this.typeMust = typeMust;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setControlType(Integer controlType) {
        this.controlType = controlType;
    }

    public void setCodeMust(Integer codeMust) {
        this.codeMust = codeMust;
    }

    public void setValidUntilMust(Integer validUntilMust) {
        this.validUntilMust = validUntilMust;
    }

    public void setFileMust(Integer fileMust) {
        this.fileMust = fileMust;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}