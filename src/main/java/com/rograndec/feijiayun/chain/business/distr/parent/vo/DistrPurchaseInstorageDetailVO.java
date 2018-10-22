package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.CheckLotDetailVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DistrPurchaseInstorageDetailVO implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 入库单ID
     */
    private Long inStorageId;

    /**
     * 单据（入库单）类型（5214）
     */
    private Integer orderType;

    /**
     * 入库单编号
     */
    private String inStorageCode;

    /**
     * 入库日期
     */
    private Date inStorageDate;

    /**
     * 基础单据明细ID
     */
    private Long baseOrderDtlId;

    /**
     * 基础单据ID
     */
    private Long baseOrderId;

    /**
     * 基础单据类型
     */
    private Integer baseOrderType;

    /**
     * 基础单据编码
     */
    private String baseOrderCode;

    /**
     * 基础单据日期
     */
    private Date baseOrderDate;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 条形码
     */
    private String barcode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品通用名称
     */
    private String goodsGenericName;

    /**
     * 剂型ID
     */
    private Long dosageId;

    /**
     * 剂型名称
     */
    private String dosageName;

    /**
     * 单位ID
     */
    private Long unitId;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 商品规格
     */
    private String goodsSpecification;

    /**
     * 生产厂商ID
     */
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    private String manufacturer;

    /**
     * 商品产地
     */
    private String goodsPlace;

    /**
     * 批准文号
     */
    private String approvalNumber;

    /**
     * 入库数量
     */
    private BigDecimal quantity;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 行折扣（%）
     */
    private BigDecimal lineDiscount;

    /**
     * 金额（整单优惠前金额）
     */
    private BigDecimal amount;

    /**
     * 整单折扣（%）
     */
    private BigDecimal wholeDiscount;

    /**
     * 行优惠（整单优惠分摊到行的金额）
     */
    private BigDecimal lineDiscountAmount;

    /**
     * 实际单价（实际金额/数量）
     */
    private BigDecimal realPrice;

    /**
     * 实际金额
     */
    private BigDecimal realAmount;

    /**
     * 进项税id
     */
    private Long taxRateId;

    /**
     * 进项税
     */
    private BigDecimal taxRate;

    /**
     * 不含税实际单价
     */
    private BigDecimal notaxRealPrice;

    /**
     * 不含税实际金额
     */
    private BigDecimal notaxRealAmount;

    /**
     * 税额
     */
    private BigDecimal taxAmount;

    /**
     * 未清数量
     */
    private BigDecimal unclearQuantity;

    /**
     * 已清数量
     */
    private BigDecimal clearQuantity;

    /**
     * 行号
     */
    private Integer lineNum;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人ID
     */
    private Long createrId;

    /**
     * 创建人编码
     */
    private String createrCode;

    /**
     * 创建人名称
     */
    private String createrName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人ID
     */
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    private String modifierName;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 采购订单ID
     */
    private Long purchaseOrderId;

    /**
     * 采购订单明细ID
     */
    private Long purchaseOrderDtlId;

    /**
     * 总部库存可用量
     */
    private BigDecimal parentUsableQuantity;

    private List<CheckLotDetailVO> checkLotDetailVO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getInStorageId() {
        return inStorageId;
    }

    public void setInStorageId(Long inStorageId) {
        this.inStorageId = inStorageId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getInStorageCode() {
        return inStorageCode;
    }

    public void setInStorageCode(String inStorageCode) {
        this.inStorageCode = inStorageCode;
    }

    public Date getInStorageDate() {
        return inStorageDate;
    }

    public void setInStorageDate(Date inStorageDate) {
        this.inStorageDate = inStorageDate;
    }

    public Long getBaseOrderDtlId() {
        return baseOrderDtlId;
    }

    public void setBaseOrderDtlId(Long baseOrderDtlId) {
        this.baseOrderDtlId = baseOrderDtlId;
    }

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode;
    }

    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName;
    }

    public Long getDosageId() {
        return dosageId;
    }

    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLineDiscount() {
        return lineDiscount;
    }

    public void setLineDiscount(BigDecimal lineDiscount) {
        this.lineDiscount = lineDiscount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    public BigDecimal getLineDiscountAmount() {
        return lineDiscountAmount;
    }

    public void setLineDiscountAmount(BigDecimal lineDiscountAmount) {
        this.lineDiscountAmount = lineDiscountAmount;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
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

    public BigDecimal getNotaxRealPrice() {
        return notaxRealPrice;
    }

    public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
        this.notaxRealPrice = notaxRealPrice;
    }

    public BigDecimal getNotaxRealAmount() {
        return notaxRealAmount;
    }

    public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
        this.notaxRealAmount = notaxRealAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getUnclearQuantity() {
        return unclearQuantity;
    }

    public void setUnclearQuantity(BigDecimal unclearQuantity) {
        this.unclearQuantity = unclearQuantity;
    }

    public BigDecimal getClearQuantity() {
        return clearQuantity;
    }

    public void setClearQuantity(BigDecimal clearQuantity) {
        this.clearQuantity = clearQuantity;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
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

    public Long getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    public String getCreaterCode() {
        return createrCode;
    }

    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifierCode() {
        return modifierCode;
    }

    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Long getPurchaseOrderDtlId() {
        return purchaseOrderDtlId;
    }

    public void setPurchaseOrderDtlId(Long purchaseOrderDtlId) {
        this.purchaseOrderDtlId = purchaseOrderDtlId;
    }

    public BigDecimal getParentUsableQuantity() {
        return parentUsableQuantity;
    }

    public void setParentUsableQuantity(BigDecimal parentUsableQuantity) {
        this.parentUsableQuantity = parentUsableQuantity;
    }

    public List<CheckLotDetailVO> getCheckLotDetailVO() {
        return checkLotDetailVO;
    }

    public void setCheckLotDetailVO(List<CheckLotDetailVO> checkLotDetailVO) {
        this.checkLotDetailVO = checkLotDetailVO;
    }

    @Override
    public String toString() {
        return "DistrPurchaseInstorageDetailVO[" +
                "id=" + id +
                ", enterpriseId=" + enterpriseId +
                ", parentId=" + parentId +
                ", inStorageId=" + inStorageId +
                ", orderType=" + orderType +
                ", inStorageCode='" + inStorageCode + '\'' +
                ", inStorageDate=" + inStorageDate +
                ", baseOrderDtlId=" + baseOrderDtlId +
                ", baseOrderId=" + baseOrderId +
                ", baseOrderType=" + baseOrderType +
                ", baseOrderCode='" + baseOrderCode + '\'' +
                ", baseOrderDate=" + baseOrderDate +
                ", goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", barcode='" + barcode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageId=" + dosageId +
                ", dosageName='" + dosageName + '\'' +
                ", unitId=" + unitId +
                ", unitName='" + unitName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", manufacturer='" + manufacturer + '\'' +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", approvalNumber='" + approvalNumber + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", lineDiscount=" + lineDiscount +
                ", amount=" + amount +
                ", wholeDiscount=" + wholeDiscount +
                ", lineDiscountAmount=" + lineDiscountAmount +
                ", realPrice=" + realPrice +
                ", realAmount=" + realAmount +
                ", taxRateId=" + taxRateId +
                ", taxRate=" + taxRate +
                ", notaxRealPrice=" + notaxRealPrice +
                ", notaxRealAmount=" + notaxRealAmount +
                ", taxAmount=" + taxAmount +
                ", unclearQuantity=" + unclearQuantity +
                ", clearQuantity=" + clearQuantity +
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
                ", purchaseOrderId=" + purchaseOrderId +
                ", purchaseOrderDtlId=" + purchaseOrderDtlId +
                ", parentUsableQuantity=" + parentUsableQuantity +
                ", checkLotDetailVO=" + checkLotDetailVO +
                ']';
    }
}
