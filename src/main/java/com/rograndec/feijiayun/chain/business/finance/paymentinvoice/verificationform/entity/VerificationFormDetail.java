package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_verification_form_detail
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class VerificationFormDetail implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 清单ID
     */
    @ApiModelProperty(value = "清单ID")
    private Long itemId;

    /**
     * 总单ID
     */
    @ApiModelProperty(value = "总单ID")
    private Long formId;

    /**
     * 单据ID
     */
    @ApiModelProperty(value = "单据ID")
    private Long baseOrderId;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String baseOrderCode;

    /**
     * 单据类型
     */
    @ApiModelProperty(value = "单据类型")
    private Integer baseOrderType;

    /**
     * 单据日期
     */
    @ApiModelProperty(value = "单据日期")
    private Date baseOrderDate;

    /**
     * 单据商品明细ID
     */
    @ApiModelProperty(value = "单据商品明细ID")
    private Long baseDtlId;

    /**
     * 单据货位明细ID
     */
    @ApiModelProperty(value = "单据货位明细ID")
    private Long baseShelfDtlId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validDate;

    /**
     * 入库数量
     */
    @ApiModelProperty(value = "入库数量")
    private BigDecimal inQuantity;

    /**
     * 已核销数量
     */
    @ApiModelProperty(value = "已核销数量")
    private BigDecimal verificationQuantity;

    /**
     * 未核销数量
     */
    @ApiModelProperty(value = "未核销数量")
    private BigDecimal unverificationQuantity;

    /**
     * 本次核销数量
     */
    @ApiModelProperty(value = "本次核销数量")
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    /**
     * 税率ID
     */
    @ApiModelProperty(value = "税率ID")
    private Long taxRateId;

    /**
     * 税率
     */
    @ApiModelProperty(value = "税率")
    private BigDecimal taxRate;

    /**
     * 不含税单价
     */
    @ApiModelProperty(value = "不含税单价")
    private BigDecimal notaxPrice;

    /**
     * 本次核销金额
     */
    @ApiModelProperty(value = "本次核销金额")
    private BigDecimal amount;

    /**
     * 本次核销不含税金额
     */
    @ApiModelProperty(value = "本次核销不含税金额")
    private BigDecimal notaxAmount;

    /**
     * 本次核销税额
     */
    @ApiModelProperty(value = "本次核销税额")
    private BigDecimal taxAmount;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

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
     * saas_verification_form_detail
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
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 清单ID
     * @return item_id 清单ID
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * 清单ID
     * @param itemId 清单ID
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * 总单ID
     * @return form_id 总单ID
     */
    public Long getFormId() {
        return formId;
    }

    /**
     * 总单ID
     * @param formId 总单ID
     */
    public void setFormId(Long formId) {
        this.formId = formId;
    }

    /**
     * 单据ID
     * @return base_order_id 单据ID
     */
    public Long getBaseOrderId() {
        return baseOrderId;
    }

    /**
     * 单据ID
     * @param baseOrderId 单据ID
     */
    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    /**
     * 单据编码
     * @return base_order_code 单据编码
     */
    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    /**
     * 单据编码
     * @param baseOrderCode 单据编码
     */
    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode == null ? null : baseOrderCode.trim();
    }

    /**
     * 单据类型
     * @return base_order_type 单据类型
     */
    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    /**
     * 单据类型
     * @param baseOrderType 单据类型
     */
    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    /**
     * 单据日期
     * @return base_order_date 单据日期
     */
    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    /**
     * 单据日期
     * @param baseOrderDate 单据日期
     */
    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    /**
     * 单据商品明细ID
     * @return base_dtl_id 单据商品明细ID
     */
    public Long getBaseDtlId() {
        return baseDtlId;
    }

    /**
     * 单据商品明细ID
     * @param baseDtlId 单据商品明细ID
     */
    public void setBaseDtlId(Long baseDtlId) {
        this.baseDtlId = baseDtlId;
    }

    /**
     * 单据货位明细ID
     * @return base_shelf_dtl_id 单据货位明细ID
     */
    public Long getBaseShelfDtlId() {
        return baseShelfDtlId;
    }

    /**
     * 单据货位明细ID
     * @param baseShelfDtlId 单据货位明细ID
     */
    public void setBaseShelfDtlId(Long baseShelfDtlId) {
        this.baseShelfDtlId = baseShelfDtlId;
    }

    /**
     * 批号
     * @return lot_number 批号
     */
    public String getLotNumber() {
        return lotNumber;
    }

    /**
     * 批号
     * @param lotNumber 批号
     */
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber == null ? null : lotNumber.trim();
    }

    /**
     * 生产日期
     * @return product_date 生产日期
     */
    public Date getProductDate() {
        return productDate;
    }

    /**
     * 生产日期
     * @param productDate 生产日期
     */
    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    /**
     * 有效期
     * @return valid_date 有效期
     */
    public Date getValidDate() {
        return validDate;
    }

    /**
     * 有效期
     * @param validDate 有效期
     */
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    /**
     * 入库数量
     * @return in_quantity 入库数量
     */
    public BigDecimal getInQuantity() {
        return inQuantity;
    }

    /**
     * 入库数量
     * @param inQuantity 入库数量
     */
    public void setInQuantity(BigDecimal inQuantity) {
        this.inQuantity = inQuantity;
    }

    /**
     * 已核销数量
     * @return verification_quantity 已核销数量
     */
    public BigDecimal getVerificationQuantity() {
        return verificationQuantity;
    }

    /**
     * 已核销数量
     * @param verificationQuantity 已核销数量
     */
    public void setVerificationQuantity(BigDecimal verificationQuantity) {
        this.verificationQuantity = verificationQuantity;
    }

    /**
     * 未核销数量
     * @return unverification_quantity 未核销数量
     */
    public BigDecimal getUnverificationQuantity() {
        return unverificationQuantity;
    }

    /**
     * 未核销数量
     * @param unverificationQuantity 未核销数量
     */
    public void setUnverificationQuantity(BigDecimal unverificationQuantity) {
        this.unverificationQuantity = unverificationQuantity;
    }

    /**
     * 本次核销数量
     * @return quantity 本次核销数量
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * 本次核销数量
     * @param quantity 本次核销数量
     */
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * 单价
     * @return price 单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 单价
     * @param price 单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 税率ID
     * @return tax_rate_id 税率ID
     */
    public Long getTaxRateId() {
        return taxRateId;
    }

    /**
     * 税率ID
     * @param taxRateId 税率ID
     */
    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
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
     * 不含税单价
     * @return notax_price 不含税单价
     */
    public BigDecimal getNotaxPrice() {
        return notaxPrice;
    }

    /**
     * 不含税单价
     * @param notaxPrice 不含税单价
     */
    public void setNotaxPrice(BigDecimal notaxPrice) {
        this.notaxPrice = notaxPrice;
    }

    /**
     * 本次核销金额
     * @return amount 本次核销金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 本次核销金额
     * @param amount 本次核销金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 本次核销不含税金额
     * @return notax_amount 本次核销不含税金额
     */
    public BigDecimal getNotaxAmount() {
        return notaxAmount;
    }

    /**
     * 本次核销不含税金额
     * @param notaxAmount 本次核销不含税金额
     */
    public void setNotaxAmount(BigDecimal notaxAmount) {
        this.notaxAmount = notaxAmount;
    }

    /**
     * 本次核销税额
     * @return tax_amount 本次核销税额
     */
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    /**
     * 本次核销税额
     * @param taxAmount 本次核销税额
     */
    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    /**
     * 行号
     * @return line_num 行号
     */
    public Integer getLineNum() {
        return lineNum;
    }

    /**
     * 行号
     * @param lineNum 行号
     */
    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    /**
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
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
        sb.append(", parentId=").append(parentId);
        sb.append(", itemId=").append(itemId);
        sb.append(", formId=").append(formId);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", baseOrderCode=").append(baseOrderCode);
        sb.append(", baseOrderType=").append(baseOrderType);
        sb.append(", baseOrderDate=").append(baseOrderDate);
        sb.append(", baseDtlId=").append(baseDtlId);
        sb.append(", baseShelfDtlId=").append(baseShelfDtlId);
        sb.append(", lotNumber=").append(lotNumber);
        sb.append(", productDate=").append(productDate);
        sb.append(", validDate=").append(validDate);
        sb.append(", inQuantity=").append(inQuantity);
        sb.append(", verificationQuantity=").append(verificationQuantity);
        sb.append(", unverificationQuantity=").append(unverificationQuantity);
        sb.append(", quantity=").append(quantity);
        sb.append(", price=").append(price);
        sb.append(", taxRateId=").append(taxRateId);
        sb.append(", taxRate=").append(taxRate);
        sb.append(", notaxPrice=").append(notaxPrice);
        sb.append(", amount=").append(amount);
        sb.append(", notaxAmount=").append(notaxAmount);
        sb.append(", taxAmount=").append(taxAmount);
        sb.append(", lineNum=").append(lineNum);
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}