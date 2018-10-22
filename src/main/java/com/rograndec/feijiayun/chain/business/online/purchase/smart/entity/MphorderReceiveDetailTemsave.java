package com.rograndec.feijiayun.chain.business.online.purchase.smart.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_mphorder_receive_detail_temsave
 * 
 * 
 * @author Administrator
 * 
 * 2017-11-30
 */
public class MphorderReceiveDetailTemsave implements Serializable {
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
     * 暂存主表ID
     */
    @ApiModelProperty(value = "暂存主表ID")
    private Long receiveTemsaveId;

    /**
     * mph商品ID
     */
    @ApiModelProperty(value = "mph商品ID")
    private Long mphGoodsId;

    /**
     * MPH商品名称
     */
    @ApiModelProperty(value = "MPH商品名称")
    private String mphGoodsName;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String mphManufacturer;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private String mphSpecification;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    /**
     * 采购数量
     */
    @ApiModelProperty(value = "采购数量")
    private BigDecimal purchaseQuantity;

    /**
     * 发货数量
     */
    @ApiModelProperty(value = "发货数量")
    private BigDecimal sendQuantity;

    /**
     * 发货金额
     */
    @ApiModelProperty(value = "发货金额")
    private BigDecimal sendAmount;

    /**
     * 绑定商品ID
     */
    @ApiModelProperty(value = "绑定商品ID")
    private Long goodsId;

    /**
     * 绑定商品名称
     */
    @ApiModelProperty(value = "绑定商品名称")
    private String goodsName;

    /**
     * 绑定生产厂商ID
     */
    @ApiModelProperty(value = "绑定生产厂商ID")
    private Long manufacturerId;

    /**
     * 绑定生产厂商
     */
    @ApiModelProperty(value = "绑定生产厂商")
    private String manufacturer;

    /**
     * 订单明细ID
     */
    @ApiModelProperty(value = "订单明细ID")
    private Long orderDetailId;

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
     * 不含税单价
     */
    @ApiModelProperty(value = "不含税单价")
    private BigDecimal notaxRealPrice;

    /**
     * 不含税金额
     */
    @ApiModelProperty(value = "不含税金额")
    private BigDecimal notaxRealAmount;

    /**
     * 税率ID
     */
    @ApiModelProperty(value = "税率ID")
    private Long taxRateId;

    /**
     * 进项税
     */
    @ApiModelProperty(value = "进项税")
    private BigDecimal taxRate;

    /**
     * saas_mphorder_receive_detail_temsave
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
     * 暂存主表ID
     * @return receive_temsave_id 暂存主表ID
     */
    public Long getReceiveTemsaveId() {
        return receiveTemsaveId;
    }

    /**
     * 暂存主表ID
     * @param receiveTemsaveId 暂存主表ID
     */
    public void setReceiveTemsaveId(Long receiveTemsaveId) {
        this.receiveTemsaveId = receiveTemsaveId;
    }

    /**
     * mph商品ID
     * @return mph_goods_id mph商品ID
     */
    public Long getMphGoodsId() {
        return mphGoodsId;
    }

    /**
     * mph商品ID
     * @param mphGoodsId mph商品ID
     */
    public void setMphGoodsId(Long mphGoodsId) {
        this.mphGoodsId = mphGoodsId;
    }

    /**
     * MPH商品名称
     * @return mph_goods_name MPH商品名称
     */
    public String getMphGoodsName() {
        return mphGoodsName;
    }

    /**
     * MPH商品名称
     * @param mphGoodsName MPH商品名称
     */
    public void setMphGoodsName(String mphGoodsName) {
        this.mphGoodsName = mphGoodsName == null ? null : mphGoodsName.trim();
    }

    /**
     * 生产厂商
     * @return mph_manufacturer 生产厂商
     */
    public String getMphManufacturer() {
        return mphManufacturer;
    }

    /**
     * 生产厂商
     * @param mphManufacturer 生产厂商
     */
    public void setMphManufacturer(String mphManufacturer) {
        this.mphManufacturer = mphManufacturer == null ? null : mphManufacturer.trim();
    }

    /**
     * 规格
     * @return mph_specification 规格
     */
    public String getMphSpecification() {
        return mphSpecification;
    }

    /**
     * 规格
     * @param mphSpecification 规格
     */
    public void setMphSpecification(String mphSpecification) {
        this.mphSpecification = mphSpecification == null ? null : mphSpecification.trim();
    }

    /**
     * 单价
     * @return unit_price 单价
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * 单价
     * @param unitPrice 单价
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * 采购数量
     * @return purchase_quantity 采购数量
     */
    public BigDecimal getPurchaseQuantity() {
        return purchaseQuantity;
    }

    /**
     * 采购数量
     * @param purchaseQuantity 采购数量
     */
    public void setPurchaseQuantity(BigDecimal purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    /**
     * 发货数量
     * @return send_quantity 发货数量
     */
    public BigDecimal getSendQuantity() {
        return sendQuantity;
    }

    /**
     * 发货数量
     * @param sendQuantity 发货数量
     */
    public void setSendQuantity(BigDecimal sendQuantity) {
        this.sendQuantity = sendQuantity;
    }

    /**
     * 发货金额
     * @return send_amount 发货金额
     */
    public BigDecimal getSendAmount() {
        return sendAmount;
    }

    /**
     * 发货金额
     * @param sendAmount 发货金额
     */
    public void setSendAmount(BigDecimal sendAmount) {
        this.sendAmount = sendAmount;
    }

    /**
     * 绑定商品ID
     * @return goods_id 绑定商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 绑定商品ID
     * @param goodsId 绑定商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 绑定商品名称
     * @return goods_name 绑定商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 绑定商品名称
     * @param goodsName 绑定商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 绑定生产厂商ID
     * @return manufacturer_id 绑定生产厂商ID
     */
    public Long getManufacturerId() {
        return manufacturerId;
    }

    /**
     * 绑定生产厂商ID
     * @param manufacturerId 绑定生产厂商ID
     */
    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * 绑定生产厂商
     * @return manufacturer 绑定生产厂商
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 绑定生产厂商
     * @param manufacturer 绑定生产厂商
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
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
     * 不含税单价
     * @return notax_real_price 不含税单价
     */
    public BigDecimal getNotaxRealPrice() {
        return notaxRealPrice;
    }

    /**
     * 不含税单价
     * @param notaxRealPrice 不含税单价
     */
    public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
        this.notaxRealPrice = notaxRealPrice;
    }

    /**
     * 不含税金额
     * @return notax_real_amount 不含税金额
     */
    public BigDecimal getNotaxRealAmount() {
        return notaxRealAmount;
    }

    /**
     * 不含税金额
     * @param notaxRealAmount 不含税金额
     */
    public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
        this.notaxRealAmount = notaxRealAmount;
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
     * 进项税
     * @return tax_rate 进项税
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * 进项税
     * @param taxRate 进项税
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
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
        sb.append(", receiveTemsaveId=").append(receiveTemsaveId);
        sb.append(", mphGoodsId=").append(mphGoodsId);
        sb.append(", mphGoodsName=").append(mphGoodsName);
        sb.append(", mphManufacturer=").append(mphManufacturer);
        sb.append(", mphSpecification=").append(mphSpecification);
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", purchaseQuantity=").append(purchaseQuantity);
        sb.append(", sendQuantity=").append(sendQuantity);
        sb.append(", sendAmount=").append(sendAmount);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", manufacturerId=").append(manufacturerId);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", notaxRealPrice=").append(notaxRealPrice);
        sb.append(", notaxRealAmount=").append(notaxRealAmount);
        sb.append(", taxRateId=").append(taxRateId);
        sb.append(", taxRate=").append(taxRate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}