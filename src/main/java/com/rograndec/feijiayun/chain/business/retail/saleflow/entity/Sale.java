package com.rograndec.feijiayun.chain.business.retail.saleflow.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
 
/**
 * 
 * saas_sale
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-09-25
 */
public class Sale implements Serializable {
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
     * 单据类型（6212）
     */
    @ApiModelProperty(value = "单据类型（6212）")
    private Integer orderType;

    /**
     * 销售日期
     */
    @ApiModelProperty(value = "销售日期")
    private Date saleDate;

    /**
     * 销售单号
     */
    @ApiModelProperty(value = "销售单号")
    private String code;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID")
    private Long baseOrderId;

    /**
     * 基础单据类型
     */
    @ApiModelProperty(value = "基础单据类型")
    private Integer baseOrderType;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "基础单据编码")
    private String baseOrderCode;

    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期")
    private Date baseOrderDate;

    /**
     * 款台编码
     */
    @ApiModelProperty(value = "款台编码")
    private String standCode;

    /**
     * 收款人员ID
     */
    @ApiModelProperty(value = "收款人员ID")
    private Long payeeId;

    /**
     * 收款人编码
     */
    @ApiModelProperty(value = "收款人编码")
    private String payeeCode;

    /**
     * 收款人名称
     */
    @ApiModelProperty(value = "收款人名称")
    private String payeeName;

    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID")
    private Long memberId;

    /**
     * 会员名称
     */
    @ApiModelProperty(value = "会员名称")
    private String memberName;

    /**
     * 会员卡号
     */
    @ApiModelProperty(value = "会员卡号")
    private String memberCardCode;

    /**
     * 会员当前积分
     */
    @ApiModelProperty(value = "会员当前积分")
    private BigDecimal memberIntegral;

    /**
     * 本次销费积分合计
     */
    @ApiModelProperty(value = "本次销费积分合计")
    private BigDecimal thisIntegralTotal;
    
    /**
     * 销售模式（0-常规；1-中药）
     */
    @ApiModelProperty(value = "销售模式（0-常规；1-中药）")
    private Integer saleMode;

    /**
     * 销售类型（0-销售；1-销退）
     */
    @ApiModelProperty(value = "销售类型（0-销售；1-销退）")
    private Integer saleType;

    /**
     * 上传时间（0-否；1-是）
     */
    @ApiModelProperty(value = "上传时间（0-否；1-是）")
    private Date uploadTime;

    /**
     * 接收时间（0-否；1-是）
     */
    @ApiModelProperty(value = "接收时间（0-否；1-是）")
    private Date acceptTime;

    /**
     * 超量销售标识（0-否；1-是；2-已处理）
     */
    @ApiModelProperty(value = "超量销售标识（0-否；1-是；2-已处理）")
    private Integer excessiveSale;

    /**
     * 交班标识（0-否；1-是）
     */
    @ApiModelProperty(value = "交班标识（0-否；1-是）")
    private Integer shiftFlag;

    /**
     * 缴款标识
     */
    @ApiModelProperty(value = "缴款标识（0-否；1-是）")
    private Integer paymentFlag;

    /**
     * 日结标识
     */
    @ApiModelProperty(value = "日结标识（0-否；1-是）")
    private Integer dailySettlementFlag;
    
    /**
     * 日结时间
     */
    @ApiModelProperty(value = "日结时间")
    private Date dailyTime;

    /**
     * 剂量
     */
    @ApiModelProperty(value = "剂量")
    private BigDecimal dose;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;
    
    /**
     * 可退数量合计
     */
    @ApiModelProperty(value = "可退数量合计")
    private BigDecimal canReturnQuantityTotal;

    /**
     * 金额合计（整单优惠前的金额合计）
     */
    @ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;

    /**
     * 整单优惠金额
     */
    @ApiModelProperty(value = "整单优惠金额")
    private BigDecimal wholeDiscountAmount;

    /**
     * 实际金额合计
     */
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

    /**
     * 利润合计
     */
    @ApiModelProperty(value = "利润合计")
    private BigDecimal profitTotal;

    /**
     * 不含税利润合计
     */
    @ApiModelProperty(value = "不含税利润合计")
    private BigDecimal notaxProfitTotal;

    /**
     * 利润率
     */
    @ApiModelProperty(value = "利润率")
    private BigDecimal profitRate;

    /**
     * 不含税利润率
     */
    @ApiModelProperty(value = "不含税利润率")
    private BigDecimal notaxProfitRate;

    /**
     * 单据状态
     */
    @ApiModelProperty(value = "单据状态")
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
     * saas_sale
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
     * 单据类型（6212）
     * @return order_type 单据类型（6212）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（6212）
     * @param orderType 单据类型（6212）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 销售日期
     * @return sale_date 销售日期
     */
    public Date getSaleDate() {
        return saleDate;
    }

    /**
     * 销售日期
     * @param saleDate 销售日期
     */
    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    /**
     * 销售单号
     * @return code 销售单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 销售单号
     * @param code 销售单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 基础单据ID
     * @return base_order_id 基础单据ID
     */
    public Long getBaseOrderId() {
        return baseOrderId;
    }

    /**
     * 基础单据ID
     * @param baseOrderId 基础单据ID
     */
    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    /**
     * 基础单据类型
     * @return base_order_type 基础单据类型
     */
    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    /**
     * 基础单据类型
     * @param baseOrderType 基础单据类型
     */
    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    /**
     * 基础单据编码
     * @return base_order_code 基础单据编码
     */
    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    /**
     * 基础单据编码
     * @param baseOrderCode 基础单据编码
     */
    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode == null ? null : baseOrderCode.trim();
    }

    /**
     * 基础单据日期
     * @return base_order_date 基础单据日期
     */
    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    /**
     * 基础单据日期
     * @param baseOrderDate 基础单据日期
     */
    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    /**
     * 款台编码
     * @return stand_code 款台编码
     */
    public String getStandCode() {
        return standCode;
    }

    /**
     * 款台编码
     * @param standCode 款台编码
     */
    public void setStandCode(String standCode) {
        this.standCode = standCode == null ? null : standCode.trim();
    }

    /**
     * 收款人员ID
     * @return payee_id 收款人员ID
     */
    public Long getPayeeId() {
        return payeeId;
    }

    /**
     * 收款人员ID
     * @param payeeId 收款人员ID
     */
    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    /**
     * 收款人编码
     * @return payee_code 收款人编码
     */
    public String getPayeeCode() {
        return payeeCode;
    }

    /**
     * 收款人编码
     * @param payeeCode 收款人编码
     */
    public void setPayeeCode(String payeeCode) {
        this.payeeCode = payeeCode == null ? null : payeeCode.trim();
    }

    /**
     * 收款人名称
     * @return payee_name 收款人名称
     */
    public String getPayeeName() {
        return payeeName;
    }

    /**
     * 收款人名称
     * @param payeeName 收款人名称
     */
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    /**
     * 销售模式（0-常规；1-中药）
     * @return sale_mode 销售模式（0-常规；1-中药）
     */
    public Integer getSaleMode() {
        return saleMode;
    }

    /**
     * 销售模式（0-常规；1-中药）
     * @param saleMode 销售模式（0-常规；1-中药）
     */
    public void setSaleMode(Integer saleMode) {
        this.saleMode = saleMode;
    }

    /**
     * 销售类型（0-销售；1-销退）
     * @return sale_type 销售类型（0-销售；1-销退）
     */
    public Integer getSaleType() {
        return saleType;
    }

    /**
     * 销售类型（0-销售；1-销退）
     * @param saleType 销售类型（0-销售；1-销退）
     */
    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
    }

    /**
     * 上传时间（0-否；1-是）
     * @return upload_time 上传时间（0-否；1-是）
     */
    public Date getUploadTime() {
        return uploadTime;
    }

    /**
     * 上传时间（0-否；1-是）
     * @param uploadTime 上传时间（0-否；1-是）
     */
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * 接收时间（0-否；1-是）
     * @return accept_time 接收时间（0-否；1-是）
     */
    public Date getAcceptTime() {
        return acceptTime;
    }

    /**
     * 接收时间（0-否；1-是）
     * @param acceptTime 接收时间（0-否；1-是）
     */
    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    /**
     * 超量销售标识（0-否；1-是；2-已处理）
     * @return excessive_sale 超量销售标识（0-否；1-是；2-已处理）
     */
    public Integer getExcessiveSale() {
        return excessiveSale;
    }

    /**
     * 超量销售标识（0-否；1-是；2-已处理）
     * @param excessiveSale
     */
    public void setExcessiveSale(Integer excessiveSale) {
        this.excessiveSale = excessiveSale;
    }

    /**
     * 交班标识（0-否；1-是）
     * @return shift_flag 交班标识（0-否；1-是）
     */
    public Integer getShiftFlag() {
        return shiftFlag;
    }

    /**
     * 交班标识（0-否；1-是）
     * @param shiftFlag 交班标识（0-否；1-是）
     */
    public void setShiftFlag(Integer shiftFlag) {
        this.shiftFlag = shiftFlag;
    }

    /**
     * 缴款标识（0-否；1-是）
     * @return payment_flag 缴款标识（0-否；1-是）
     */
    public Integer getPaymentFlag() {
        return paymentFlag;
    }

    /**
     * 缴款标识（0-否；1-是）
     * @param paymentFlag 缴款标识（0-否；1-是）
     */
    public void setPaymentFlag(Integer paymentFlag) {
        this.paymentFlag = paymentFlag;
    }

    /**
     * 日结标识（0-否；1-是）
     * @return daily_settlement_flag 日结标识（0-否；1-是）
     */
    public Integer getDailySettlementFlag() {
        return dailySettlementFlag;
    }

    /**
     * 日结标识（0-否；1-是）
     * @param dailySettlementFlag 日结标识（0-否；1-是）
     */
    public void setDailySettlementFlag(Integer dailySettlementFlag) {
        this.dailySettlementFlag = dailySettlementFlag;
    }


    /**
     * 剂量
     * @return dose 剂量
     */
    public BigDecimal getDose() {
        return dose;
    }

    /**
     * 剂量
     * @param dose 剂量
     */
    public void setDose(BigDecimal dose) {
        this.dose = dose;
    }

    /**
     * 数量合计
     * @return quantity_total 数量合计
     */
    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    /**
     * 数量合计
     * @param quantityTotal 数量合计
     */
    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    /**
     * 品种数量
     * @return varieties_quantity 品种数量
     */
    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    /**
     * 品种数量
     * @param varietiesQuantity 品种数量
     */
    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @return amount_total 金额合计（整单优惠前的金额合计）
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @param amountTotal 金额合计（整单优惠前的金额合计）
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 整单折扣（%）
     * @return whole_discount 整单折扣（%）
     */
    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    /**
     * 整单折扣（%）
     * @param wholeDiscount 整单折扣（%）
     */
    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    /**
     * 整单优惠金额
     * @return whole_discount_amount 整单优惠金额
     */
    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    /**
     * 整单优惠金额
     * @param wholeDiscountAmount 整单优惠金额
     */
    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    /**
     * 实际金额合计
     * @return real_amount_total 实际金额合计
     */
    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    /**
     * 实际金额合计
     * @param realAmountTotal 实际金额合计
     */
    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    /**
     * 不含税金额合计
     * @return notax_real_amount_total 不含税金额合计
     */
    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    /**
     * 不含税金额合计
     * @param notaxRealAmountTotal 不含税金额合计
     */
    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
    }

    /**
     * 税额合计
     * @return tax_amount_total 税额合计
     */
    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    /**
     * 税额合计
     * @param taxAmountTotal 税额合计
     */
    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    /**
     * 利润合计
     * @return profit_total 利润合计
     */
    public BigDecimal getProfitTotal() {
        return profitTotal;
    }

    /**
     * 利润合计
     * @param profitTotal 利润合计
     */
    public void setProfitTotal(BigDecimal profitTotal) {
        this.profitTotal = profitTotal;
    }

    /**
     * 不含税利润合计
     * @return notax_profit_total 不含税利润合计
     */
    public BigDecimal getNotaxProfitTotal() {
        return notaxProfitTotal;
    }

    /**
     * 不含税利润合计
     * @param notaxProfitTotal 不含税利润合计
     */
    public void setNotaxProfitTotal(BigDecimal notaxProfitTotal) {
        this.notaxProfitTotal = notaxProfitTotal;
    }

    /**
     * 利润率
     * @return profit_rate 利润率
     */
    public BigDecimal getProfitRate() {
        return profitRate;
    }

    /**
     * 利润率
     * @param profitRate 利润率
     */
    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    /**
     * 不含税利润率
     * @return notax_profit_rate 不含税利润率
     */
    public BigDecimal getNotaxProfitRate() {
        return notaxProfitRate;
    }

    /**
     * 不含税利润率
     * @param notaxProfitRate 不含税利润率
     */
    public void setNotaxProfitRate(BigDecimal notaxProfitRate) {
        this.notaxProfitRate = notaxProfitRate;
    }

    /**
     * 单据状态
     * @return status 单据状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 单据状态
     * @param status 单据状态
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
        sb.append(", orderType=").append(orderType);
        sb.append(", saleDate=").append(saleDate);
        sb.append(", code=").append(code);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", baseOrderType=").append(baseOrderType);
        sb.append(", baseOrderCode=").append(baseOrderCode);
        sb.append(", baseOrderDate=").append(baseOrderDate);
        sb.append(", standCode=").append(standCode);
        sb.append(", payeeId=").append(payeeId);
        sb.append(", payeeCode=").append(payeeCode);
        sb.append(", payeeName=").append(payeeName);
        sb.append(", saleMode=").append(saleMode);
        sb.append(", saleType=").append(saleType);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", acceptTime=").append(acceptTime);
        sb.append(", excessiveSale=").append(excessiveSale);
        sb.append(", shiftFlag=").append(shiftFlag);
        sb.append(", paymentFlag=").append(paymentFlag);
        sb.append(", dailySettlementFlag=").append(dailySettlementFlag);
        sb.append(", dose=").append(dose);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", wholeDiscount=").append(wholeDiscount);
        sb.append(", wholeDiscountAmount=").append(wholeDiscountAmount);
        sb.append(", realAmountTotal=").append(realAmountTotal);
        sb.append(", notaxRealAmountTotal=").append(notaxRealAmountTotal);
        sb.append(", taxAmountTotal=").append(taxAmountTotal);
        sb.append(", profitTotal=").append(profitTotal);
        sb.append(", notaxProfitTotal=").append(notaxProfitTotal);
        sb.append(", profitRate=").append(profitRate);
        sb.append(", notaxProfitRate=").append(notaxProfitRate);
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
        sb.append(", memberId=").append(memberId);
        sb.append(", memberName=").append(memberName);
        sb.append(", memberCardCode=").append(memberCardCode);
        sb.append(", memberIntegral=").append(memberIntegral);
        sb.append("]");
        return sb.toString();
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberCardCode() {
        return memberCardCode;
    }

    public void setMemberCardCode(String memberCardCode) {
        this.memberCardCode = memberCardCode;
    }
    
    public BigDecimal getThisIntegralTotal() {
		return thisIntegralTotal;
	}

	public void setThisIntegralTotal(BigDecimal thisIntegralTotal) {
		this.thisIntegralTotal = thisIntegralTotal;
	}

	public BigDecimal getMemberIntegral() {
        return memberIntegral;
    }

    public void setMemberIntegral(BigDecimal memberIntegral) {
        this.memberIntegral = memberIntegral;
    }
    
    public BigDecimal getCanReturnQuantityTotal() {
		return canReturnQuantityTotal;
	}

	public void setCanReturnQuantityTotal(BigDecimal canReturnQuantityTotal) {
		this.canReturnQuantityTotal = canReturnQuantityTotal;
	}

	public static long getSerialVersionUID() {
        return serialVersionUID;
    }

	public Date getDailyTime() {
		return dailyTime;
	}

	public void setDailyTime(Date dailyTime) {
		this.dailyTime = dailyTime;
	}
	
}