package com.rograndec.feijiayun.chain.business.storage.split.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_split_detail
 * 
 * 
 * @author rograndec
 * 
 * 2017-09-30
 */
public class SplitDetail implements Serializable {
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
     * 单据（商品拆零单）ID
     */
    @ApiModelProperty(value = "单据（商品拆零单）ID")
    private Long splitId;

    /**
     * 单据（商品拆零单）类型
     */
    @ApiModelProperty(value = "单据（商品拆零单）类型")
    private Integer orderType;

    /**
     * 单据（商品拆零单）编码
     */
    @ApiModelProperty(value = "单据（商品拆零单）编码")
    private String splitCode;

    /**
     * 单据（商品拆零单）日期
     */
    @ApiModelProperty(value = "单据（商品拆零单）日期")
    private Date splitDate;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 批号ID
     */
    @ApiModelProperty(value = "批号ID")
    private Long lotId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validDate;

    /**
     * 货位ID
     */
    @ApiModelProperty(value = "货位ID")
    private Long shelfId;

    /**
     * 货位名称
     */
    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    /**
     * 货位质量状态
     */
    @ApiModelProperty(value = "货位质量状态")
    private String shelfStatusDesc;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 拆零商品ID
     */
    @ApiModelProperty(value = "拆零商品ID")
    private Long splitGoodsId;

    /**
     * 拆零商品编码
     */
    @ApiModelProperty(value = "拆零商品编码")
    private String splitGoodsCode;

    /**
     * 拆零商品名称
     */
    @ApiModelProperty(value = "拆零商品名称")
    private String splitGoodsName;

    /**
     * 拆零货位ID
     */
    @ApiModelProperty(value = "拆零货位ID")
    private Long splitShelfId;

    /**
     * 拆零货位名称
     */
    @ApiModelProperty(value = "拆零货位名称")
    private String splitShelfName;

    /**
     * 拆零货位质量状态
     */
    @ApiModelProperty(value = "拆零货位质量状态")
    private String splitShelfStatusDesc;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal splitQuantity;

    /**
     * 目标数量
     */
    @ApiModelProperty(value = "目标数量")
    private BigDecimal targetQuantity;

    /**
     * 整盒商品单价
     */
    @ApiModelProperty(value = "整盒商品单价")
    private BigDecimal unitPrice;

    /**
     * 拆零商品单价
     */
    @ApiModelProperty(value = "拆零商品单价")
    private BigDecimal splitUnitPrice;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

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
     * 整盒商品不含税单价
     */
    @ApiModelProperty(value = "整盒商品不含税单价")
    private BigDecimal notaxPrice;

    /**
     * 拆零商品不含税单价
     */
    @ApiModelProperty(value = "拆零商品不含税单价")
    private BigDecimal splitNotaxPrice;

    /**
     * 不含税金额
     */
    @ApiModelProperty(value = "不含税金额")
    private BigDecimal notaxAmount;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
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
     * saas_split_detail
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
     * 单据（商品拆零单）ID
     * @return split_id 单据（商品拆零单）ID
     */
    public Long getSplitId() {
        return splitId;
    }

    /**
     * 单据（商品拆零单）ID
     * @param splitId 单据（商品拆零单）ID
     */
    public void setSplitId(Long splitId) {
        this.splitId = splitId;
    }

    /**
     * 单据（商品拆零单）类型
     * @return order_type 单据（商品拆零单）类型
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据（商品拆零单）类型
     * @param orderType 单据（商品拆零单）类型
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 单据（商品拆零单）编码
     * @return split_code 单据（商品拆零单）编码
     */
    public String getSplitCode() {
        return splitCode;
    }

    /**
     * 单据（商品拆零单）编码
     * @param splitCode 单据（商品拆零单）编码
     */
    public void setSplitCode(String splitCode) {
        this.splitCode = splitCode == null ? null : splitCode.trim();
    }

    /**
     * 单据（商品拆零单）日期
     * @return split_date 单据（商品拆零单）日期
     */
    public Date getSplitDate() {
        return splitDate;
    }

    /**
     * 单据（商品拆零单）日期
     * @param splitDate 单据（商品拆零单）日期
     */
    public void setSplitDate(Date splitDate) {
        this.splitDate = splitDate;
    }

    /**
     * 商品ID
     * @return goods_id 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 商品ID
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 商品编码
     * @return goods_code 商品编码
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * 商品编码
     * @param goodsCode 商品编码
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    /**
     * 商品名称
     * @return goods_name 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 商品名称
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 批号ID
     * @return lot_id 批号ID
     */
    public Long getLotId() {
        return lotId;
    }

    /**
     * 批号ID
     * @param lotId 批号ID
     */
    public void setLotId(Long lotId) {
        this.lotId = lotId;
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
     * 货位ID
     * @return shelf_id 货位ID
     */
    public Long getShelfId() {
        return shelfId;
    }

    /**
     * 货位ID
     * @param shelfId 货位ID
     */
    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    /**
     * 货位名称
     * @return shelf_name 货位名称
     */
    public String getShelfName() {
        return shelfName;
    }

    /**
     * 货位名称
     * @param shelfName 货位名称
     */
    public void setShelfName(String shelfName) {
        this.shelfName = shelfName == null ? null : shelfName.trim();
    }

    /**
     * 货位质量状态
     * @return shelf_status_desc 货位质量状态
     */
    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    /**
     * 货位质量状态
     * @param shelfStatusDesc 货位质量状态
     */
    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc == null ? null : shelfStatusDesc.trim();
    }

    /**
     * 数量
     * @return quantity 数量
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * 数量
     * @param quantity 数量
     */
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * 拆零商品ID
     * @return split_goods_id 拆零商品ID
     */
    public Long getSplitGoodsId() {
        return splitGoodsId;
    }

    /**
     * 拆零商品ID
     * @param splitGoodsId 拆零商品ID
     */
    public void setSplitGoodsId(Long splitGoodsId) {
        this.splitGoodsId = splitGoodsId;
    }

    /**
     * 拆零商品编码
     * @return split_goods_code 拆零商品编码
     */
    public String getSplitGoodsCode() {
        return splitGoodsCode;
    }

    /**
     * 拆零商品编码
     * @param splitGoodsCode 拆零商品编码
     */
    public void setSplitGoodsCode(String splitGoodsCode) {
        this.splitGoodsCode = splitGoodsCode == null ? null : splitGoodsCode.trim();
    }

    /**
     * 拆零商品名称
     * @return split_goods_name 拆零商品名称
     */
    public String getSplitGoodsName() {
        return splitGoodsName;
    }

    /**
     * 拆零商品名称
     * @param splitGoodsName 拆零商品名称
     */
    public void setSplitGoodsName(String splitGoodsName) {
        this.splitGoodsName = splitGoodsName == null ? null : splitGoodsName.trim();
    }

    /**
     * 拆零货位ID
     * @return split_shelf_id 拆零货位ID
     */
    public Long getSplitShelfId() {
        return splitShelfId;
    }

    /**
     * 拆零货位ID
     * @param splitShelfId 拆零货位ID
     */
    public void setSplitShelfId(Long splitShelfId) {
        this.splitShelfId = splitShelfId;
    }

    /**
     * 拆零货位名称
     * @return split_shelf_name 拆零货位名称
     */
    public String getSplitShelfName() {
        return splitShelfName;
    }

    /**
     * 拆零货位名称
     * @param splitShelfName 拆零货位名称
     */
    public void setSplitShelfName(String splitShelfName) {
        this.splitShelfName = splitShelfName == null ? null : splitShelfName.trim();
    }

    /**
     * 拆零货位质量状态
     * @return split_shelf_status_desc 拆零货位质量状态
     */
    public String getSplitShelfStatusDesc() {
        return splitShelfStatusDesc;
    }

    /**
     * 拆零货位质量状态
     * @param splitShelfStatusDesc 拆零货位质量状态
     */
    public void setSplitShelfStatusDesc(String splitShelfStatusDesc) {
        this.splitShelfStatusDesc = splitShelfStatusDesc == null ? null : splitShelfStatusDesc.trim();
    }

    /**
     * 数量
     * @return split_quantity 数量
     */
    public BigDecimal getSplitQuantity() {
        return splitQuantity;
    }

    /**
     * 数量
     * @param splitQuantity 数量
     */
    public void setSplitQuantity(BigDecimal splitQuantity) {
        this.splitQuantity = splitQuantity;
    }

    /**
     * 目标数量
     * @return target_quantity 目标数量
     */
    public BigDecimal getTargetQuantity() {
        return targetQuantity;
    }

    /**
     * 目标数量
     * @param targetQuantity 目标数量
     */
    public void setTargetQuantity(BigDecimal targetQuantity) {
        this.targetQuantity = targetQuantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSplitUnitPrice() {
        return splitUnitPrice;
    }

    public void setSplitUnitPrice(BigDecimal splitUnitPrice) {
        this.splitUnitPrice = splitUnitPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getNotaxPrice() {
        return notaxPrice;
    }

    public void setNotaxPrice(BigDecimal notaxPrice) {
        this.notaxPrice = notaxPrice;
    }

    public BigDecimal getSplitNotaxPrice() {
        return splitNotaxPrice;
    }

    public void setSplitNotaxPrice(BigDecimal splitNotaxPrice) {
        this.splitNotaxPrice = splitNotaxPrice;
    }

    public BigDecimal getNotaxAmount() {
        return notaxAmount;
    }

    public void setNotaxAmount(BigDecimal notaxAmount) {
        this.notaxAmount = notaxAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

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

    @Override
    public String toString() {
        return "SplitDetail{" +
                "id=" + id +
                ", enterpriseId=" + enterpriseId +
                ", parentId=" + parentId +
                ", splitId=" + splitId +
                ", orderType=" + orderType +
                ", splitCode='" + splitCode + '\'' +
                ", splitDate=" + splitDate +
                ", goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", lotId=" + lotId +
                ", lotNumber='" + lotNumber + '\'' +
                ", productDate=" + productDate +
                ", validDate=" + validDate +
                ", shelfId=" + shelfId +
                ", shelfName='" + shelfName + '\'' +
                ", shelfStatusDesc='" + shelfStatusDesc + '\'' +
                ", quantity=" + quantity +
                ", splitGoodsId=" + splitGoodsId +
                ", splitGoodsCode='" + splitGoodsCode + '\'' +
                ", splitGoodsName='" + splitGoodsName + '\'' +
                ", splitShelfId=" + splitShelfId +
                ", splitShelfName='" + splitShelfName + '\'' +
                ", splitShelfStatusDesc='" + splitShelfStatusDesc + '\'' +
                ", splitQuantity=" + splitQuantity +
                ", targetQuantity=" + targetQuantity +
                ", unitPrice=" + unitPrice +
                ", splitUnitPrice=" + splitUnitPrice +
                ", amount=" + amount +
                ", taxRateId=" + taxRateId +
                ", taxRate=" + taxRate +
                ", notaxPrice=" + notaxPrice +
                ", splitNotaxPrice=" + splitNotaxPrice +
                ", notaxAmount=" + notaxAmount +
                ", taxAmount=" + taxAmount +
                ", lineNum=" + lineNum +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", createrId=" + createrId +
                ", createrCode='" + createrCode + '\'' +
                ", createrName='" + createrName + '\'' +
                ", createTime=" + createTime +
                ", modifierId=" + modifierId +
                ", modifierCode='" + modifierCode + '\'' +
                ", modifierName='" + modifierName + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}