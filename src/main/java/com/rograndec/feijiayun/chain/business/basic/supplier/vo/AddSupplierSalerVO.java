package com.rograndec.feijiayun.chain.business.basic.supplier.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@ApiModel
@Validated
public class AddSupplierSalerVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "供货单位ID ,新增时不需要传递,修改时需要传递" ,required = false)
    private Long id;

    /**
     * 供货单位ID
     */
    @ApiModelProperty(value = "供货单位ID",required = true)
    @NotNull(message = "供货单位不能为空")
    private Long supplierId;


    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名",required = true)
    @NotNull(message = "姓名不能为空")
    @Size(min = 1 ,message = "姓名不能为空")
    private String name;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号",required = false)
    private String idNum;

    /**
     * 身份证有效期至
     */
    @ApiModelProperty(value = "身份证有效期",required = false)
    private Date idValidUntil;

    /**
     * 身份证附件ID
     */
    private Long idFileId;

    /**
     * 授权书号
     */
    @ApiModelProperty(value = "授权书号",required = false)
    private String certificateNum;

    /**
     * 授权书有效期至
     */
    @ApiModelProperty(value = "授权书有效期",required = false)
    private Date certificateValidUntil;

    /**
     * 授权品种ID（多个用逗号分隔）
     */
    @ApiModelProperty(value = "授权品种ID（多个用逗号分隔）",required = false)
    private String authorizedVariety;

    /**
     * 授权品种范围ID（多个用逗号分隔）
     */
    @ApiModelProperty(value = "授权品种范围ID（多个用逗号分隔）",required = false)
    private String authorizedVarietyScope;

    /**
     * 授权地域
     */
    @ApiModelProperty(value = "授权地域",required = false)
    private String authorizedRegion;

    /**
     * 授权书附件ID
     */
    @ApiModelProperty(value = "授权书附件ID")
    private Long certificateFileId;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String telephone;

    /**
     * 传真
     */
    @ApiModelProperty(value = "传真")
    private String fax;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String mobilePhone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 微信
     */
    @ApiModelProperty(value = "微信")
    private String wechatNum;

    /**
     * QQ
     */
    @ApiModelProperty(value = "QQ")
    private String qqNum;

    /**
     * 状态（0-离职；1-在职）
     */
    @ApiModelProperty(value = " 状态（0-离职；1-在职）")
    @NotNull(message = "状态必须选择")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Date getIdValidUntil() {
        return idValidUntil;
    }

    public void setIdValidUntil(Date idValidUntil) {
        this.idValidUntil = idValidUntil;
    }

    public Long getIdFileId() {
        return idFileId;
    }

    public void setIdFileId(Long idFileId) {
        this.idFileId = idFileId;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public Date getCertificateValidUntil() {
        return certificateValidUntil;
    }

    public void setCertificateValidUntil(Date certificateValidUntil) {
        this.certificateValidUntil = certificateValidUntil;
    }

    public String getAuthorizedVariety() {
        return authorizedVariety;
    }

    public void setAuthorizedVariety(String authorizedVariety) {
        this.authorizedVariety = authorizedVariety;
    }

    public String getAuthorizedVarietyScope() {
        return authorizedVarietyScope;
    }

    public void setAuthorizedVarietyScope(String authorizedVarietyScope) {
        this.authorizedVarietyScope = authorizedVarietyScope;
    }

    public Long getCertificateFileId() {
        return certificateFileId;
    }

    public void setCertificateFileId(Long certificateFileId) {
        this.certificateFileId = certificateFileId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechatNum() {
        return wechatNum;
    }

    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum;
    }

    public String getQqNum() {
        return qqNum;
    }

    public void setQqNum(String qqNum) {
        this.qqNum = qqNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAuthorizedRegion() {
        return authorizedRegion;
    }

    public void setAuthorizedRegion(String authorizedRegion) {
        this.authorizedRegion = authorizedRegion;
    }
}