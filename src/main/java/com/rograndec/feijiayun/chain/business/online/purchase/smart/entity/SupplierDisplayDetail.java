package com.rograndec.feijiayun.chain.business.online.purchase.smart.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_supplier_display_detail
 * 
 * 
 * @author Administrator
 * 
 * 2017-11-22
 */
public class SupplierDisplayDetail implements Serializable {
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 
     */
    @ApiModelProperty(value = "")
    private Long saasSupplierDisplayId;

    /**
     * mph供应商ID
     */
    @ApiModelProperty(value = "mph供应商ID")
    private Long mphSupplierId;

    /**
     * mph供应商名称
     */
    @ApiModelProperty(value = "mph供应商名称")
    private String mphSupplierName;

    /**
     * 注册地址
     */
    @ApiModelProperty(value = "注册地址")
    private String address;

    /**
     * 许可证号
     */
    @ApiModelProperty(value = "许可证号")
    private String licenseCode;

    /**
     * 营业执照
     */
    @ApiModelProperty(value = "营业执照")
    private String businessLicenseCode;

    /**
     * 法人代表
     */
    @ApiModelProperty(value = "法人代表")
    private String legalPerson;

    /**
     * 优先级
     */
    @ApiModelProperty(value = "优先级")
    private Integer priority;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * saas_supplier_display_detail
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     * @return id ID
     */
    public Long getId() {
        return id;
    }

    /**
     * ID
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return saas_supplier_display_id 
     */
    public Long getSaasSupplierDisplayId() {
        return saasSupplierDisplayId;
    }

    /**
     * 
     * @param saasSupplierDisplayId 
     */
    public void setSaasSupplierDisplayId(Long saasSupplierDisplayId) {
        this.saasSupplierDisplayId = saasSupplierDisplayId;
    }

    /**
     * mph供应商ID
     * @return mph_supplier_id mph供应商ID
     */
    public Long getMphSupplierId() {
        return mphSupplierId;
    }

    /**
     * mph供应商ID
     * @param mphSupplierId mph供应商ID
     */
    public void setMphSupplierId(Long mphSupplierId) {
        this.mphSupplierId = mphSupplierId;
    }

    /**
     * mph供应商名称
     * @return mph_supplier_name mph供应商名称
     */
    public String getMphSupplierName() {
        return mphSupplierName;
    }

    /**
     * mph供应商名称
     * @param mphSupplierName mph供应商名称
     */
    public void setMphSupplierName(String mphSupplierName) {
        this.mphSupplierName = mphSupplierName == null ? null : mphSupplierName.trim();
    }

    /**
     * 注册地址
     * @return address 注册地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 注册地址
     * @param address 注册地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 许可证号
     * @return license_code 许可证号
     */
    public String getLicenseCode() {
        return licenseCode;
    }

    /**
     * 许可证号
     * @param licenseCode 许可证号
     */
    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode == null ? null : licenseCode.trim();
    }

    /**
     * 营业执照
     * @return business_license_code 营业执照
     */
    public String getBusinessLicenseCode() {
        return businessLicenseCode;
    }

    /**
     * 营业执照
     * @param businessLicenseCode 营业执照
     */
    public void setBusinessLicenseCode(String businessLicenseCode) {
        this.businessLicenseCode = businessLicenseCode == null ? null : businessLicenseCode.trim();
    }

    /**
     * 法人代表
     * @return legal_person 法人代表
     */
    public String getLegalPerson() {
        return legalPerson;
    }

    /**
     * 法人代表
     * @param legalPerson 法人代表
     */
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    /**
     * 优先级
     * @return priority 优先级
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 优先级
     * @param priority 优先级
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        sb.append(", saasSupplierDisplayId=").append(saasSupplierDisplayId);
        sb.append(", mphSupplierId=").append(mphSupplierId);
        sb.append(", mphSupplierName=").append(mphSupplierName);
        sb.append(", address=").append(address);
        sb.append(", licenseCode=").append(licenseCode);
        sb.append(", businessLicenseCode=").append(businessLicenseCode);
        sb.append(", legalPerson=").append(legalPerson);
        sb.append(", priority=").append(priority);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}