package com.rograndec.feijiayun.chain.business.auth.register.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_register
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-21
 */
public class RegisterVO implements Serializable {

    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号",required = true)
    @NotNull(message = "登录账号不能为空")
    @Size(min = 5,max = 20,message = "登录账号长度不对")
    private String loginAccount;

    /**
     * 用户姓名
     */
    @NotNull(message = "登录密码不能为空")
    @Size(min = 5,max = 20,message = "登录密码长度不对")
    @ApiModelProperty(value = "用户姓名",required = true)
    private String userName;

    /**
     * 登录密码
     */
    @NotNull(message = "登录密码不能为空")
    @Size(min = 5,max = 20,message = "登录密码长度不对")
    @ApiModelProperty(value = "登录密码",required = true)
    private String password;

    /**
     * 密码确认
     */
    @NotNull(message = "密码确认,不能为空")
    @Size(min = 1,message = "密码确认,不能为空")
    @ApiModelProperty(value = "密码确认",required = true)
    private String passwordConfirm;

    /**
     * 手机号码
     */
    @NotNull(message = "手机号码,不能为空")
    @Size(min = 1,message = "手机号码,不能为空")
    @ApiModelProperty(value = "手机号码",required = true)
    private String mobilePhone;

    /**
     * 验证码
     */
    @NotNull(message = "验证码,不能为空")
    @Size(min = 1,message = "验证码,不能为空")
    @ApiModelProperty(value = "验证码",required = true)
    private String msgValidateCode;

    /**
     * 手机号码
     */
    @NotNull(message = "手机号码,不能为空")
    @Size(min = 1,message = "手机号码,不能为空")
    @ApiModelProperty(value = "邮箱",required = true)
    private String email;

    /**
     * 企业名称
     */
    @NotNull(message = "企业名称,不能为空")
    @Size(min = 1,message = "企业名称,不能为空")
    @ApiModelProperty(value = "企业名称",required = true)
    private String enterpriseName;

    /**
     * 省ID
     */
    @NotNull(message = "省,不能为空")
    @ApiModelProperty(value = "省ID",required = true)
    private Integer provinceId;

    /**
     * 市ID
     */
    @NotNull(message = "市,不能为空")
    @ApiModelProperty(value = "市ID",required = true)
    private Integer cityId;

    /**
     * 区ID
     */
    @NotNull(message = "区,不能为空")
    @ApiModelProperty(value = "区ID",required = true)
    private Integer areaId;

    /**
     * 详细地址
     */
    @NotNull(message = "详细地址,不能为空")
    @Size(min = 1,message = "详细地址,不能为空")
    @ApiModelProperty(value = "详细地址",required = true)
    private String address;

    /**
     * 仓库地址
     */
    @NotNull(message = "仓库地址,不能为空")
    @Size(min = 1,message = "仓库地址,不能为空")
    @ApiModelProperty(value = "仓库地址",required = true)
    private String warehouseAddress;

    /**
     * 企业负责人
     */
    @NotNull(message = "企业负责人,不能为空")
    @Size(min = 1,message = "企业负责人,不能为空")
    @ApiModelProperty(value = "企业负责人",required = true)
    private String chargeMan;

    /**
     * 法定代表人
     */
    @NotNull(message = "法定代表人,不能为空")
    @Size(min = 1,message = "法定代表人,不能为空")
    @ApiModelProperty(value = "法定代表人",required = true)
    private String legalMan;

    /**
     * 质量负责人
     */
    @NotNull(message = "质量负责人,不能为空")
    @Size(min = 1,message = "质量负责人,不能为空")
    @ApiModelProperty(value = "质量负责人",required = true)
    private String qualityOfficer;

    /**
     * 营业执照附件ID
     */
    @NotNull(message = "营业执照附件,不能为空")
    @ApiModelProperty(value = "营业执照附件ID",required = true)
    private Long businessFileId;

    /**
     * 营业执照编号
     */
    @NotNull(message = "营业执照编号,不能为空")
    @Size(min = 1,message = "营业执照编号,不能为空")
    @ApiModelProperty(value = "营业执照编号",required = true)
    private String businessFileCode;

    /**
     * 营业执照有效期至
     */
    @NotNull(message = "营业执照有效期至,不能为空")
    @ApiModelProperty(value = "营业执照有效期至",required = true)
    private Date businessValidUntil;

    /**
     * 药品经营许可证附件ID
     */
    @NotNull(message = "药品经营许可证附件,不能为空")
    @ApiModelProperty(value = "药品经营许可证附件ID",required = true)
    private Long permitFileId;

    /**
     * 药品经营许可证编号
     */
    @NotNull(message = "药品经营许可证编号,不能为空")
    @Size(min = 1,message = "药品经营许可证编号,不能为空")
    @ApiModelProperty(value = "药品经营许可证编号",required = true)
    private String permitFileCode;

    /**
     * 药品经营许可证有效期至
     */
    @NotNull(message = "药品经营许可证有效期至,不能为空")
    @ApiModelProperty(value = "药品经营许可证有效期至",required = true)
    private Date permitValidUntil;

    /**
     * 药品经营质量管理规范认证证书附件ID
     */
    @NotNull(message = "药品经营质量管理规范认证证书附件,不能为空")
    @ApiModelProperty(value = "药品经营质量管理规范认证证书附件ID",required = true)
    private Long qualityFileId;

    /**
     * 药品经营质量管理规范认证证书编号
     */
    @NotNull(message = "药品经营质量管理规范认证证书编号,不能为空")
    @Size(min = 1,message = "药品经营质量管理规范认证证书编号,不能为空")
    @ApiModelProperty(value = "药品经营质量管理规范认证证书编号",required = true)
    private String qualityFileCode;

    /**
     * 药品经营质量管规范有效期至
     */
    @NotNull(message = "药品经营质量管规范有效期至,不能为空")
    @ApiModelProperty(value = "药品经营质量管规范有效期至",required = true)
    private Date qualityValidUntil;


    public static List<Integer> getLocationIds(RegisterVO registerVO){

        List<Integer> ids = new ArrayList<>();
        ids.add(registerVO.getProvinceId());
        ids.add(registerVO.getCityId());
        ids.add(registerVO.getAreaId());

        return ids;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMsgValidateCode() {
        return msgValidateCode;
    }

    public void setMsgValidateCode(String msgValidateCode) {
        this.msgValidateCode = msgValidateCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getChargeMan() {
        return chargeMan;
    }

    public void setChargeMan(String chargeMan) {
        this.chargeMan = chargeMan;
    }

    public String getLegalMan() {
        return legalMan;
    }

    public void setLegalMan(String legalMan) {
        this.legalMan = legalMan;
    }

    public String getQualityOfficer() {
        return qualityOfficer;
    }

    public void setQualityOfficer(String qualityOfficer) {
        this.qualityOfficer = qualityOfficer;
    }

    public Long getBusinessFileId() {
        return businessFileId;
    }

    public void setBusinessFileId(Long businessFileId) {
        this.businessFileId = businessFileId;
    }

    public String getBusinessFileCode() {
        return businessFileCode;
    }

    public void setBusinessFileCode(String businessFileCode) {
        this.businessFileCode = businessFileCode;
    }

    public Date getBusinessValidUntil() {
        return businessValidUntil;
    }

    public void setBusinessValidUntil(Date businessValidUntil) {
        this.businessValidUntil = businessValidUntil;
    }

    public Long getPermitFileId() {
        return permitFileId;
    }

    public void setPermitFileId(Long permitFileId) {
        this.permitFileId = permitFileId;
    }

    public String getPermitFileCode() {
        return permitFileCode;
    }

    public void setPermitFileCode(String permitFileCode) {
        this.permitFileCode = permitFileCode;
    }

    public Date getPermitValidUntil() {
        return permitValidUntil;
    }

    public void setPermitValidUntil(Date permitValidUntil) {
        this.permitValidUntil = permitValidUntil;
    }

    public Long getQualityFileId() {
        return qualityFileId;
    }

    public void setQualityFileId(Long qualityFileId) {
        this.qualityFileId = qualityFileId;
    }

    public String getQualityFileCode() {
        return qualityFileCode;
    }

    public void setQualityFileCode(String qualityFileCode) {
        this.qualityFileCode = qualityFileCode;
    }

    public Date getQualityValidUntil() {
        return qualityValidUntil;
    }

    public void setQualityValidUntil(Date qualityValidUntil) {
        this.qualityValidUntil = qualityValidUntil;
    }
}