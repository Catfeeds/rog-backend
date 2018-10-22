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
public class ResponsePrescriptionSignatureForListVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    /**
     * 员工ID
     */
    @ApiModelProperty(value = "员工ID")
    private Long userId;

    /**
     * 员工编码
     */
    @ApiModelProperty(value = "员工编码")
    private String userCode;

    /**
     * 员工名称
     */
    @ApiModelProperty(value = "员工名称")
    private String userName;

    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    private String loginAccount;

    /**
     * 处方操作类型（0-审核；1-调配；2-核对）
     */
    @ApiModelProperty(value = "处方操作类型（0-审核；1-调配；2-核对;3-发药）")
    private Integer prescriptionType;

    /**
     * 签章类型（0-签名；1-印章；2- 指纹）
     */
    @ApiModelProperty(value = "签章类型（0-签名；1-印章；2- 指纹）")
    private Integer signatureType;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    @ApiModelProperty(value = "是否可以删除（0/否；1/是）")
    private Integer isDelete;

    @ApiModelProperty(value = "是否可以修改（true/false）")
    private Boolean canUpdate;


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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete > 0 ? 0 : 1;
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

    public boolean getCanUpdate() {
        if(canUpdate == null) return true;
        return canUpdate;
    }

    public void setCanUpdate(Boolean canUpdate) {
        this.canUpdate = canUpdate;
    }
}