package com.rograndec.feijiayun.chain.business.retail.prescription.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * saas_prescription_signature
 * 
 * 
 * @author ST
 * 
 * 2017-09-21
 */
public class ResponsePrescriptionSignatureForAddVO implements Serializable {

    @ApiModelProperty(value = "主键(增加时id 为空)")
    private Long id;

    /**
     * 员工ID
     */
    @ApiModelProperty(value = "员工ID")
    private Long userId;


    /**
     * 处方操作类型（0-审核；1-调配；2-核对）
     */
    @ApiModelProperty(value = "处方操作类型（0-审核；1-调配；2-核对）")
    private Integer prescriptionType;

    /**
     * 签章类型（0-签名；1-印章；2- 指纹）
     */
    @ApiModelProperty(value = "签章类型（0-签名；1-印章；2- 指纹）")
    private Integer signatureType;

    /**
     * 上传方式 0-本地上传 1-base64上传
     */
    @ApiModelProperty(value = "上传方式 0-本地上传 1-base64上传")
    private Integer uploadType;

    /**
     * 签章明细集合
     */
    @ApiModelProperty(value = "签章明细集合")
    private List<PrescriptionSignatureDetailVO> prescriptionSignatureDetailVOS;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPrescriptionType() {
        return prescriptionType;
    }

    public void setPrescriptionType(Integer prescriptionType) {
        this.prescriptionType = prescriptionType;
    }

    public Integer getSignatureType() {
        return signatureType;
    }

    public void setSignatureType(Integer signatureType) {
        this.signatureType = signatureType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUploadType() {
        return uploadType;
    }

    public void setUploadType(Integer uploadType) {
        this.uploadType = uploadType;
    }

    public List<PrescriptionSignatureDetailVO> getPrescriptionSignatureDetailVOS() {
        return prescriptionSignatureDetailVOS;
    }

    public void setPrescriptionSignatureDetailVOS(List<PrescriptionSignatureDetailVO> prescriptionSignatureDetailVOS) {
        this.prescriptionSignatureDetailVOS = prescriptionSignatureDetailVOS;
    }
}