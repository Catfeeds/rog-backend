package com.rograndec.feijiayun.chain.business.goods.set.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GoodsTaxRate implements Serializable {
    /**
     *  主键ID
     */
    @ApiModelProperty(value=" 主键ID",required=true)
    private Long id;

    /**
     * 企业（总部ID）
     */
    @ApiModelProperty(value=" 企业（总部ID）",required=true)
    private Long enterpriseId;

    /**
     * 编码
     */
    @ApiModelProperty(value=" 编码",required=true)
    private String code;

    /**
     * 税率
     */
    @ApiModelProperty(value=" 税率",required=true)
    private BigDecimal taxRate;


    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value=" 状态（0-禁用；1-启用）",required=true)
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value=" 备注",required=true)
    private String remark;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value=" 创建人ID",required=true)
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value=" 创建人编码",required=true)
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value=" 创建人名称",required=true)
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value=" 创建时间",required=true)
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value=" 最后修改人ID",required=true)
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value=" 最后修改人编码",required=true)
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value=" 最后修改人名称",required=true)
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value=" 更新时间",required=true)
    private Date updateTime;

    /**
     * '0：用户自定义部门；1-系统默认部门'
     */
    @ApiModelProperty(value=" '0：用户自定义部门；1-系统默认部门'",required=true)
    private Integer sysType;

    public static Map<Long,GoodsTaxRate> getGoodsTaxRateMap(List<GoodsTaxRate> goodsTaxRates){

        return goodsTaxRates.stream().collect(Collectors.toMap(GoodsTaxRate::getId, g -> g));
    }

    public static Map<BigDecimal,GoodsTaxRate> getGoodsTaxMap(List<GoodsTaxRate> goodsTaxRates){

        return goodsTaxRates.stream().collect(Collectors.toMap(GoodsTaxRate::getTaxRate, g -> g));
    }

    /**
     * saas_goods_tax_rate
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
     * 企业（总部ID）
     * @return enterprise_id 企业（总部ID）
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业（总部ID）
     * @param enterpriseId 企业（总部ID）
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 编码
     * @return code 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 编码
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 税率
     * @return tax_rate 税率
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * 税率
     * @param taxRate 税率
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @return status 状态（0-禁用；1-启用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @param status 状态（0-禁用；1-启用）
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 更新时间
     * @return sys_type '0：用户自定义部门；1-系统默认部门'
     */
    public Integer getSysType() {
        return sysType;
    }

    /**
     * 更新时间
     * @param sysType '0：用户自定义部门；1-系统默认部门'
     */
    public void setSysType(Integer sysType) {
        this.sysType = sysType;
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
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", code=").append(code);
        sb.append(", taxRate=").append(taxRate);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", sysType=").append(sysType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsTaxRate that = (GoodsTaxRate) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return taxRate != null ? taxRate.equals(that.taxRate) : that.taxRate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (taxRate != null ? taxRate.hashCode() : 0);
        return result;
    }
}