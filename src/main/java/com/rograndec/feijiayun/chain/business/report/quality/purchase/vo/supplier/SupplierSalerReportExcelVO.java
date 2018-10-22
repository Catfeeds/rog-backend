package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier;

import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


/**
 * 功能描述：
 * Created by ST on 2017/10/18 15:44
 */

public class SupplierSalerReportExcelVO implements Serializable{

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String supplierName;

    /**
     * 性质（0-批发企业；1-生产企业）
     */
    @ApiModelProperty(value = "性质（0-批发企业；1-生产企业）")
    private Integer nature;

    @ApiModelProperty(value = "性质（0-批发企业；1-生产企业）")
    private String natureName;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idNum;

    /**
     * 身份证有效期至
     */
    @ApiModelProperty(value = "身份证有效期至")
    private Date idValidUntil;
    @ApiModelProperty(value = "身份证有效期至")
    private String idValidUntilString;

    /**
     * 身份证附件ID
     */
    @ApiModelProperty(value = "身份证附件ID")
    private Long idFileId;

    /**
     * 授权书号
     */
    @ApiModelProperty(value = "授权书号")
    private String certificateNum;

    /**
     * 授权书有效期至
     */
    @ApiModelProperty(value = "授权书有效期至")
    private Date certificateValidUntil;

    @ApiModelProperty(value = "授权书有效期至")
    private String certificateValidUntilString;


    /**
     * 授权品种ID（多个用逗号分隔）
     */
    @ApiModelProperty(value = "授权品种ID（多个用逗号分隔）")
    private String authorizedVariety;

    @ApiModelProperty(value = "授权品种ID（多个用逗号分隔）")
    private String authorizedVarietyNames;
    /**
     * 授权品种范围ID（多个用逗号分隔）
     */
    @ApiModelProperty(value = "授权品种范围ID（多个用逗号分隔）")
    private String authorizedVarietyScope;

    @ApiModelProperty(value = "授权品种范围Name（多个用逗号分隔）")
    private String authorizedVarietyScopeNames;

    /**
     * 授权书附件ID
     */
    @ApiModelProperty(value = "授权书附件ID")
    private Long certificateFileId;

    @ApiModelProperty(value = "授权地域")
    private String authorizedRegion;

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
    @ApiModelProperty(value = "状态（0-离职；1-在职）")
    private Integer status;

    private String statusName;

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

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public String getNatureName() {
        return natureName;
    }

    public void setNatureName(String natureName) {
        this.natureName = natureName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getIdValidUntilString() {
        return DateUtils.DateToString(idValidUntil,DateStyle.YYYY_MM_DD_HH_MM_SS);
    }

    public void setIdValidUntilString(String idValidUntilString) {
        this.idValidUntilString = idValidUntilString;
    }

    public String getCertificateValidUntilString() {
        return DateUtils.DateToString(certificateValidUntil, DateStyle.YYYY_MM_DD_HH_MM_SS);
    }

    public void setCertificateValidUntilString(String certificateValidUntilString) {
        this.certificateValidUntilString = certificateValidUntilString;
    }

    public String getAuthorizedVarietyNames() {
        return authorizedVarietyNames;
    }

    public void setAuthorizedVarietyNames(String authorizedVarietyNames) {
        this.authorizedVarietyNames = authorizedVarietyNames;
    }

    public String getAuthorizedVarietyScopeNames() {
        return authorizedVarietyScopeNames;
    }

    public void setAuthorizedVarietyScopeNames(String authorizedVarietyScopeNames) {
        this.authorizedVarietyScopeNames = authorizedVarietyScopeNames;
    }

    public String getStatusName() {
        if(status == 0){
            return "离职";
        } else if(status == 1){
            return "在职";
        }
        return "";
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getAuthorizedRegion() {
        return authorizedRegion;
    }

    public void setAuthorizedRegion(String authorizedRegion) {
        this.authorizedRegion = authorizedRegion;
    }
}