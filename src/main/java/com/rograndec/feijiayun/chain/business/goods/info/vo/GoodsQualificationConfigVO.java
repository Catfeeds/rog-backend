package com.rograndec.feijiayun.chain.business.goods.info.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品资质信息VO
 * Created by ST on 2017/9/6.
 */
public class GoodsQualificationConfigVO implements Serializable {

    private Long id;

    /**
     * 商品资质配置表id
     */
    @ApiModelProperty(value="商品资质配置id")
    private Long qualificationConfigId;

    /**
     * 商品ID
     */
    private Long goodsId;

    @ApiModelProperty(value="企业id")
    private Long enterpriseId;



    /**
     * 资质ID
     */
    @ApiModelProperty(value="资质ID")
    private Long qualificationId;

    /**
     * 资质编号（不是资质表编码）
     */
    @ApiModelProperty(value="资质编号（不是资质表编码）")
    private String qualificationCode;

    /**
     * 有效期至
     */
    @ApiModelProperty(value="有效期至")
    private Date validUntil;

    /**
     * 附件ID
     */
    @ApiModelProperty(value="附件ID")
    private Long fileId;
    /**
     * 附件Name
     */
    @ApiModelProperty(value="附件Name")
    private String fileName;



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
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String qualificationName;

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




    public Long getQualificationConfigId() {
        return qualificationConfigId;
    }

    public void setQualificationConfigId(Long qualificationConfigId) {
        this.qualificationConfigId = qualificationConfigId;
    }

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }

    public Integer getTypeMust() {
        return typeMust;
    }

    public void setTypeMust(Integer typeMust) {
        this.typeMust = typeMust;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public Integer getControlType() {
        return controlType;
    }

    public void setControlType(Integer controlType) {
        this.controlType = controlType;
    }

    public Integer getCodeMust() {
        return codeMust;
    }

    public void setCodeMust(Integer codeMust) {
        this.codeMust = codeMust;
    }

    public Integer getValidUntilMust() {
        return validUntilMust;
    }

    public void setValidUntilMust(Integer validUntilMust) {
        this.validUntilMust = validUntilMust;
    }

    public Integer getFileMust() {
        return fileMust;
    }

    public void setFileMust(Integer fileMust) {
        this.fileMust = fileMust;
    }

    public Long getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Long qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getQualificationCode() {
        return qualificationCode;
    }

    public void setQualificationCode(String qualificationCode) {
        this.qualificationCode = qualificationCode;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}