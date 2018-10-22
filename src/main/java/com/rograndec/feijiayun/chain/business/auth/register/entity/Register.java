package com.rograndec.feijiayun.chain.business.auth.register.entity;

import com.rograndec.feijiayun.chain.business.auth.register.vo.RegisterEnterpriseVO;
import com.rograndec.feijiayun.chain.business.auth.register.vo.RegisterUserVO;
import com.rograndec.feijiayun.chain.business.auth.register.vo.RegisterVO;
import com.rograndec.feijiayun.chain.business.auth.register.vo.transfer.RegisterParamVO;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Location;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
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
public class Register implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    private String loginAccount;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String userName;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    private String password;

    /**
     * 密码确认
     */
    @ApiModelProperty(value = "密码确认")
    private String passwordConfirm;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobilePhone;

    /**
     * 短信验证码
     */
    @ApiModelProperty(value = "短信验证码")
    private String msgValidateCode;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    /**
     * 省ID
     */
    @ApiModelProperty(value = "省ID")
    private Integer provinceId;

    /**
     * 省名称
     */
    @ApiModelProperty(value = "省名称")
    private String provinceName;

    /**
     * 市ID
     */
    @ApiModelProperty(value = "市ID")
    private Integer cityId;

    /**
     * 市名称
     */
    @ApiModelProperty(value = "市名称")
    private String cityName;

    /**
     * 区ID
     */
    @ApiModelProperty(value = "区ID")
    private Integer areaId;

    /**
     * 区名称
     */
    @ApiModelProperty(value = "区名称")
    private String areaName;

    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String address;

    /**
     * 仓库地址
     */
    @ApiModelProperty(value = "仓库地址")
    private String warehouseAddress;

    /**
     * 企业负责人
     */
    @ApiModelProperty(value = "企业负责人")
    private String chargeMan;

    /**
     * 法定代表人
     */
    @ApiModelProperty(value = "法定代表人")
    private String legalMan;

    /**
     * 质量负责人
     */
    @ApiModelProperty(value = "质量负责人")
    private String qualityOfficer;

    /**
     * 营业执照附件ID
     */
    @ApiModelProperty(value = "营业执照附件ID")
    private Long businessFileId;

    /**
     * 营业执照编号
     */
    @ApiModelProperty(value = "营业执照编号")
    private String businessFileCode;

    /**
     * 营业执照有效期至
     */
    @ApiModelProperty(value = "营业执照有效期至")
    private Date businessValidUntil;

    /**
     * 药品经营许可证附件ID
     */
    @ApiModelProperty(value = "药品经营许可证附件ID")
    private Long permitFileId;

    /**
     * 药品经营许可证编号
     */
    @ApiModelProperty(value = "药品经营许可证编号")
    private String permitFileCode;

    /**
     * 药品经营许可证有效期至
     */
    @ApiModelProperty(value = "药品经营许可证有效期至")
    private Date permitValidUntil;

    /**
     * 药品经营质量管理规范认证证书附件ID
     */
    @ApiModelProperty(value = "药品经营质量管理规范认证证书附件ID")
    private Long qualityFileId;

    /**
     * 药品经营质量管理规范认证证书编号
     */
    @ApiModelProperty(value = "药品经营质量管理规范认证证书编号")
    private String qualityFileCode;

    /**
     * 药品经营质量管规范有效期至
     */
    @ApiModelProperty(value = "药品经营质量管规范有效期至")
    private Date qualityValidUntil;

    /**
     * saas用户id
     */
    private Long userId;

    public static Register getRegister2User(RegisterParamVO registerParamVO){

        RegisterUserVO registerVO = registerParamVO.getRegisterUserVO();

        Register register = new Register();
        /**
         * 登录账号
         */
        register.setLoginAccount(registerVO.getLoginAccount());

        /**
         * 用户姓名
         */
        register.setUserName(registerVO.getUserName());

        /**
         * 登录密码
         */
        register.setPassword(registerVO.getPassword());

        /**
         * 密码确认
         */

        register.setPasswordConfirm(registerVO.getPasswordConfirm());


        /**
         * 手机号码
         */
        register.setMobilePhone(registerVO.getMobilePhone());

        /**
         * 邮箱
         */
        register.setEmail(registerVO.getEmail());


        User user = registerParamVO.getUser();

        register.setUserId(user.getId());

        return register;
    }

    public static Register getRegister2Enterprise(RegisterParamVO registerParamVO){

        RegisterEnterpriseVO registerVO = registerParamVO.getRegisterEnterpriseVO();

        List<Location> locations = registerParamVO.getLocations();

        Register register = new Register();

        /**
         * 企业名称
         */
        register.setEnterpriseName(registerVO.getEnterpriseName());


        for(Location location : locations){

            if(location.getId().equals(registerVO.getProvinceId())){
                /**
                 * 省ID
                 */
                register.setProvinceId(location.getId());

                /**
                 * 省名称
                 */
                register.setProvinceName(location.getName());

            }

            if(location.getId().equals(registerVO.getCityId())){
                /**
                 * 市ID
                 */
                register.setCityId(location.getId());

                /**
                 * 市名称
                 */
                register.setCityName(location.getName());
            }

            if(location.getId().equals(registerVO.getAreaId())){
                /**
                 * 区ID
                 */
                register.setAreaId(location.getId());

                /**
                 * 区名称
                 */
                register.setAreaName(location.getName());

            }
        }


        /**
         * 详细地址
         */
        register.setAddress(registerVO.getAddress());

        /**
         * 仓库地址
         */
        register.setWarehouseAddress(registerVO.getWarehouseAddress());

        /**
         * 企业负责人
         */
        register.setChargeMan(registerVO.getChargeMan());

        /**
         * 法定代表人
         */
        register.setLegalMan(registerVO.getLegalMan());

        /**
         * 质量负责人
         */
        register.setQualityOfficer(registerVO.getQualityOfficer());

        /**
         * 营业执照附件ID
         */
        register.setBusinessFileId(registerVO.getBusinessFileId());

        /**
         * 营业执照编号
         */
        register.setBusinessFileCode(registerVO.getBusinessFileCode());

        /**
         * 营业执照有效期至
         */
        register.setBusinessValidUntil(registerVO.getBusinessValidUntil());

        /**
         * 药品经营许可证附件ID
         */
        register.setPermitFileId(registerVO.getPermitFileId());

        /**
         * 药品经营许可证编号
         */
        register.setPermitFileCode(registerVO.getPermitFileCode());

        /**
         * 药品经营许可证有效期至
         */
        register.setPermitValidUntil(registerVO.getPermitValidUntil());

        /**
         * 药品经营质量管理规范认证证书附件ID
         */
        register.setQualityFileId(registerVO.getQualityFileId());

        /**
         * 药品经营质量管理规范认证证书编号
         */
        register.setQualityFileCode(registerVO.getQualityFileCode());

        /**
         * 药品经营质量管规范有效期至
         */
        register.setQualityValidUntil(registerVO.getQualityValidUntil());

        return register;
    }


    public static Register getRegister(RegisterParamVO registerParamVO){

        RegisterVO registerVO = registerParamVO.getRegisterVO();

        List<Location> locations = registerParamVO.getLocations();

        Register register = new Register();
        /**
         * 登录账号
         */
        register.setLoginAccount(registerVO.getLoginAccount());

        /**
         * 用户姓名
         */
        register.setUserName(registerVO.getUserName());

        /**
         * 登录密码
         */
        register.setPassword(registerVO.getPassword());

        /**
         * 密码确认
         */

        register.setPasswordConfirm(registerVO.getPasswordConfirm());


        /**
         * 手机号码
         */
        register.setMobilePhone(registerVO.getMobilePhone());

        /**
         * 短信验证码
         */
        register.setMsgValidateCode(registerVO.getMsgValidateCode());

        /**
         * 邮箱
         */
        register.setEmail(registerVO.getEmail());

        /**
         * 企业名称
         */
        register.setEnterpriseName(registerVO.getEnterpriseName());


        for(Location location : locations){

            if(location.getId().equals(registerVO.getProvinceId())){
                /**
                 * 省ID
                 */
                register.setProvinceId(location.getId());

                /**
                 * 省名称
                 */
                register.setProvinceName(location.getName());

            }

            if(location.getId().equals(registerVO.getCityId())){
                /**
                 * 市ID
                 */
                register.setCityId(location.getId());

                /**
                 * 市名称
                 */
                register.setCityName(location.getName());
            }

            if(location.getId().equals(registerVO.getAreaId())){
                /**
                 * 区ID
                 */
                register.setAreaId(location.getId());

                /**
                 * 区名称
                 */
                register.setAreaName(location.getName());

            }
        }


        /**
         * 详细地址
         */
        register.setAddress(registerVO.getAddress());

        /**
         * 仓库地址
         */
        register.setWarehouseAddress(registerVO.getWarehouseAddress());

        /**
         * 企业负责人
         */
        register.setChargeMan(registerVO.getChargeMan());

        /**
         * 法定代表人
         */
        register.setLegalMan(registerVO.getLegalMan());

        /**
         * 质量负责人
         */
        register.setQualityOfficer(registerVO.getQualityOfficer());

        /**
         * 营业执照附件ID
         */
        register.setBusinessFileId(registerVO.getBusinessFileId());

        /**
         * 营业执照编号
         */
        register.setBusinessFileCode(registerVO.getBusinessFileCode());

        /**
         * 营业执照有效期至
         */
        register.setBusinessValidUntil(registerVO.getBusinessValidUntil());

        /**
         * 药品经营许可证附件ID
         */
        register.setPermitFileId(registerVO.getPermitFileId());

        /**
         * 药品经营许可证编号
         */
        register.setPermitFileCode(registerVO.getPermitFileCode());

        /**
         * 药品经营许可证有效期至
         */
        register.setPermitValidUntil(registerVO.getPermitValidUntil());

        /**
         * 药品经营质量管理规范认证证书附件ID
         */
        register.setQualityFileId(registerVO.getQualityFileId());

        /**
         * 药品经营质量管理规范认证证书编号
         */
        register.setQualityFileCode(registerVO.getQualityFileCode());

        /**
         * 药品经营质量管规范有效期至
         */
        register.setQualityValidUntil(registerVO.getQualityValidUntil());

        return register;
    }

    /**
     * saas_register
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 登录账号
     * @return login_account 登录账号
     */
    public String getLoginAccount() {
        return loginAccount;
    }

    /**
     * 登录账号
     * @param loginAccount 登录账号
     */
    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount == null ? null : loginAccount.trim();
    }

    /**
     * 用户姓名
     * @return user_name 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户姓名
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 登录密码
     * @return password 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 登录密码
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 密码确认
     * @return password_confirm 密码确认
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * 密码确认
     * @param passwordConfirm 密码确认
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm == null ? null : passwordConfirm.trim();
    }

    /**
     * 手机号码
     * @return mobile_phone 手机号码
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 手机号码
     * @param mobilePhone 手机号码
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * 短信验证码
     * @return msg_validate_code 短信验证码
     */
    public String getMsgValidateCode() {
        return msgValidateCode;
    }

    /**
     * 短信验证码
     * @param msgValidateCode 短信验证码
     */
    public void setMsgValidateCode(String msgValidateCode) {
        this.msgValidateCode = msgValidateCode == null ? null : msgValidateCode.trim();
    }

    /**
     * 邮箱
     * @return email 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 企业名称
     * @return enterprise_name 企业名称
     */
    public String getEnterpriseName() {
        return enterpriseName;
    }

    /**
     * 企业名称
     * @param enterpriseName 企业名称
     */
    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    /**
     * 省ID
     * @return province_id 省ID
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * 省ID
     * @param provinceId 省ID
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 省名称
     * @return province_name 省名称
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 省名称
     * @param provinceName 省名称
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * 市ID
     * @return city_id 市ID
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 市ID
     * @param cityId 市ID
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 市名称
     * @return city_name 市名称
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 市名称
     * @param cityName 市名称
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * 区ID
     * @return area_id 区ID
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * 区ID
     * @param areaId 区ID
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * 区名称
     * @return area_name 区名称
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 区名称
     * @param areaName 区名称
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * 详细地址
     * @return address 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 详细地址
     * @param address 详细地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 仓库地址
     * @return warehouse_address 仓库地址
     */
    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    /**
     * 仓库地址
     * @param warehouseAddress 仓库地址
     */
    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress == null ? null : warehouseAddress.trim();
    }

    /**
     * 企业负责人
     * @return charge_man 企业负责人
     */
    public String getChargeMan() {
        return chargeMan;
    }

    /**
     * 企业负责人
     * @param chargeMan 企业负责人
     */
    public void setChargeMan(String chargeMan) {
        this.chargeMan = chargeMan == null ? null : chargeMan.trim();
    }

    /**
     * 法定代表人
     * @return legal_man 法定代表人
     */
    public String getLegalMan() {
        return legalMan;
    }

    /**
     * 法定代表人
     * @param legalMan 法定代表人
     */
    public void setLegalMan(String legalMan) {
        this.legalMan = legalMan == null ? null : legalMan.trim();
    }

    /**
     * 质量负责人
     * @return quality_officer 质量负责人
     */
    public String getQualityOfficer() {
        return qualityOfficer;
    }

    /**
     * 质量负责人
     * @param qualityOfficer 质量负责人
     */
    public void setQualityOfficer(String qualityOfficer) {
        this.qualityOfficer = qualityOfficer == null ? null : qualityOfficer.trim();
    }

    /**
     * 营业执照附件ID
     * @return business_file_id 营业执照附件ID
     */
    public Long getBusinessFileId() {
        return businessFileId;
    }

    /**
     * 营业执照附件ID
     * @param businessFileId 营业执照附件ID
     */
    public void setBusinessFileId(Long businessFileId) {
        this.businessFileId = businessFileId;
    }

    /**
     * 营业执照编号
     * @return business_file_code 营业执照编号
     */
    public String getBusinessFileCode() {
        return businessFileCode;
    }

    /**
     * 营业执照编号
     * @param businessFileCode 营业执照编号
     */
    public void setBusinessFileCode(String businessFileCode) {
        this.businessFileCode = businessFileCode == null ? null : businessFileCode.trim();
    }

    /**
     * 营业执照有效期至
     * @return business_valid_until 营业执照有效期至
     */
    public Date getBusinessValidUntil() {
        return businessValidUntil;
    }

    /**
     * 营业执照有效期至
     * @param businessValidUntil 营业执照有效期至
     */
    public void setBusinessValidUntil(Date businessValidUntil) {
        this.businessValidUntil = businessValidUntil;
    }

    /**
     * 药品经营许可证附件ID
     * @return permit_file_id 药品经营许可证附件ID
     */
    public Long getPermitFileId() {
        return permitFileId;
    }

    /**
     * 药品经营许可证附件ID
     * @param permitFileId 药品经营许可证附件ID
     */
    public void setPermitFileId(Long permitFileId) {
        this.permitFileId = permitFileId;
    }

    /**
     * 药品经营许可证编号
     * @return permit_file_code 药品经营许可证编号
     */
    public String getPermitFileCode() {
        return permitFileCode;
    }

    /**
     * 药品经营许可证编号
     * @param permitFileCode 药品经营许可证编号
     */
    public void setPermitFileCode(String permitFileCode) {
        this.permitFileCode = permitFileCode == null ? null : permitFileCode.trim();
    }

    /**
     * 药品经营许可证有效期至
     * @return permit_valid_until 药品经营许可证有效期至
     */
    public Date getPermitValidUntil() {
        return permitValidUntil;
    }

    /**
     * 药品经营许可证有效期至
     * @param permitValidUntil 药品经营许可证有效期至
     */
    public void setPermitValidUntil(Date permitValidUntil) {
        this.permitValidUntil = permitValidUntil;
    }

    /**
     * 药品经营质量管理规范认证证书附件ID
     * @return quality_file_id 药品经营质量管理规范认证证书附件ID
     */
    public Long getQualityFileId() {
        return qualityFileId;
    }

    /**
     * 药品经营质量管理规范认证证书附件ID
     * @param qualityFileId 药品经营质量管理规范认证证书附件ID
     */
    public void setQualityFileId(Long qualityFileId) {
        this.qualityFileId = qualityFileId;
    }

    /**
     * 药品经营质量管理规范认证证书编号
     * @return quality_file_code 药品经营质量管理规范认证证书编号
     */
    public String getQualityFileCode() {
        return qualityFileCode;
    }

    /**
     * 药品经营质量管理规范认证证书编号
     * @param qualityFileCode 药品经营质量管理规范认证证书编号
     */
    public void setQualityFileCode(String qualityFileCode) {
        this.qualityFileCode = qualityFileCode == null ? null : qualityFileCode.trim();
    }

    /**
     * 药品经营质量管规范有效期至
     * @return quality_valid_until 药品经营质量管规范有效期至
     */
    public Date getQualityValidUntil() {
        return qualityValidUntil;
    }

    /**
     * 药品经营质量管规范有效期至
     * @param qualityValidUntil 药品经营质量管规范有效期至
     */
    public void setQualityValidUntil(Date qualityValidUntil) {
        this.qualityValidUntil = qualityValidUntil;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginAccount=").append(loginAccount);
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", passwordConfirm=").append(passwordConfirm);
        sb.append(", mobilePhone=").append(mobilePhone);
        sb.append(", msgValidateCode=").append(msgValidateCode);
        sb.append(", email=").append(email);
        sb.append(", enterpriseName=").append(enterpriseName);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", provinceName=").append(provinceName);
        sb.append(", cityId=").append(cityId);
        sb.append(", cityName=").append(cityName);
        sb.append(", areaId=").append(areaId);
        sb.append(", areaName=").append(areaName);
        sb.append(", address=").append(address);
        sb.append(", warehouseAddress=").append(warehouseAddress);
        sb.append(", chargeMan=").append(chargeMan);
        sb.append(", legalMan=").append(legalMan);
        sb.append(", qualityOfficer=").append(qualityOfficer);
        sb.append(", businessFileId=").append(businessFileId);
        sb.append(", businessFileCode=").append(businessFileCode);
        sb.append(", businessValidUntil=").append(businessValidUntil);
        sb.append(", permitFileId=").append(permitFileId);
        sb.append(", permitFileCode=").append(permitFileCode);
        sb.append(", permitValidUntil=").append(permitValidUntil);
        sb.append(", qualityFileId=").append(qualityFileId);
        sb.append(", qualityFileCode=").append(qualityFileCode);
        sb.append(", qualityValidUntil=").append(qualityValidUntil);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}