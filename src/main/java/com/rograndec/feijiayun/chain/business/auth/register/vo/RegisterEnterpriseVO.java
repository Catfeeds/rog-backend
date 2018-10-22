package com.rograndec.feijiayun.chain.business.auth.register.vo;

import com.alibaba.fastjson.JSONObject;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
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
public class RegisterEnterpriseVO implements Serializable {

    /**
     * 企业名称
     */
    @NotNull(message = "企业名称,不能为空")
    @Size(min = 1,message = "企业名称,不能为空")
    @ApiModelProperty(value = "企业名称",required = true)
    private String enterpriseName;

    /**
     * 药店类型（0-总部；1-自营店；2-加盟店）
     */
    @ApiModelProperty(value = "药店类型（0-总部；1-自营店；2-加盟店）")
    private Integer chainType;

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

    @NotNull(message = "营业执照附件,不能为空")
    @ApiModelProperty(value = "营业执照附件",required = true)
    private String businessFileUrl;

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

    @NotNull(message = "药品经营许可证附件,不能为空")
    @ApiModelProperty(value = "药品经营许可证附件",required = true)
    private String permitFileUrl;
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

    @NotNull(message = "药品经营质量管理规范认证证书附件,不能为空")
    @ApiModelProperty(value = "药品经营质量管理规范认证证书附件",required = true)
    private String qualityFileUrl;

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

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID,不能为空")
    @ApiModelProperty(value = "用户ID",required = true)
    private Long userId;

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id",required = false)
    private Long parentId;




    /**
     * {"appId":11,"balanceOpenUid":0,"eActionTime":1509811342000,
     * "eAddTime":1509811342000,"eAddress":"北京市朝阳区",
     * "eId":6005899,"eLicenseNo":"1233","eMobile":"17701241225"
     * ,"eName":"testzhangsasa","eParentId":0,"eStatus":2,
     * "eType":2,"eUpdated":1,"hypName":"","hypReason":"",
     * "hypStatus":0,"invitationCode":"14012904","isBalanceOpen":0,"isErp":0,
     * "isFranchise":1,"picStatus":0,"suId":0}
     * @param rgtEnterprise
     * @return
     */
    public static RegisterEnterpriseVO getRegisterEnterpriseVO2RGTEnterprise(Long userId, JSONObject rgtEnterprise){


        RegisterEnterpriseVO registerEnterpriseVO = new RegisterEnterpriseVO();

        if(null != rgtEnterprise){

            /**
             * 企业名称
             */
            registerEnterpriseVO.setEnterpriseName(rgtEnterprise.getString("eName"));

            /**
             * 药店类型（0-总部；1-自营店；2-加盟店）
             */
            Integer eType = rgtEnterprise.getInteger("eType");
            registerEnterpriseVO.setChainType(ChainType.getSaasCode(eType));

            /**
             * 省ID
             */
            Integer eProvince = rgtEnterprise.getInteger("eProvince");
            registerEnterpriseVO.setProvinceId(eProvince);

            /**
             * 市ID
             */
            Integer eCity = rgtEnterprise.getInteger("eCity");
            registerEnterpriseVO.setCityId(eCity);

            /**
             * 区ID
             */
            Integer eRegion = rgtEnterprise.getInteger("eRegion");
            registerEnterpriseVO.setAreaId(eRegion);

            /**
             * 详细地址 eAddress
             */
            String eAddress = rgtEnterprise.getString("eAddress");
            registerEnterpriseVO.setAddress(eAddress);


            /**
             * 营业执照编号
             */
            String eLicenseNo = rgtEnterprise.getString("eLicenseNo");
            registerEnterpriseVO.setBusinessFileCode(eLicenseNo);


            /**
             * 融贯通用户ID
             */
            registerEnterpriseVO.setUserId(userId);

        }

        return registerEnterpriseVO;

    }

    public static List<Integer> getLocationIds(RegisterEnterpriseVO registerVO){

        List<Integer> ids = new ArrayList<>();
        ids.add(registerVO.getProvinceId());
        ids.add(registerVO.getCityId());
        ids.add(registerVO.getAreaId());

        return ids;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getBusinessFileUrl() {
        return businessFileUrl;
    }

    public void setBusinessFileUrl(String businessFileUrl) {
        this.businessFileUrl = businessFileUrl;
    }

    public String getPermitFileUrl() {
        return permitFileUrl;
    }

    public void setPermitFileUrl(String permitFileUrl) {
        this.permitFileUrl = permitFileUrl;
    }

    public String getQualityFileUrl() {
        return qualityFileUrl;
    }

    public void setQualityFileUrl(String qualityFileUrl) {
        this.qualityFileUrl = qualityFileUrl;
    }
}