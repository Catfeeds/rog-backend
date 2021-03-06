package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DistrSendCacheVO implements Serializable {
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
     * 单据类型（5411）
     */
    @ApiModelProperty(value = "单据类型（5411）")
    private Integer orderType;

    /**
     * 配货单号
     */
    @ApiModelProperty(value = "配货单号")
    private String code;

    /**
     * 配货日期
     */
    @ApiModelProperty(value = "配货日期")
    private Date distrDate;

    /**
     * 要货单位ID
     */
    @ApiModelProperty(value = "要货单位ID")
    private Long requestUnitId;

    /**
     * 要货单位编码
     */
    @ApiModelProperty(value = "要货单位编码")
    private String requestUnitCode;

    /**
     * 要货单位名称
     */
    @ApiModelProperty(value = "要货单位名称")
    private String requestUnitName;

    /**
     * 配货人员ID
     */
    @ApiModelProperty(value = "配货人员ID")
    private Long distrManId;

    /**
     * 配货人员编码
     */
    @ApiModelProperty(value = "配货人员编码")
    private String distrManCode;

    /**
     * 配货人员名称
     */
    @ApiModelProperty(value = "配货人员名称")
    private String distrManName;

    /**
     * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;

    /**
     * 配货类型
     */
    @ApiModelProperty(value = "配货类型")
    private String distrTypeName;

    /**
     * 配货规则（0-按要货顺序；1-按库存平均；2-按要货数量占比）
     */
    @ApiModelProperty(value = "配货规则（0-按要货顺序；1-按库存平均；2-按要货数量占比）")
    private Integer distrRule;

    /**
     * 缺配处理（0-生成缺配单；1-不处理）
     */
    @ApiModelProperty(value = "缺配处理（0-生成缺配单；1-不处理）")
    private Integer lackHandle;

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
     * 金额合计（整单优惠前的金额合计）
     */
    @ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;
    @ApiModelProperty(value = "整单折扣金额：整单金额*整单折扣")
    private BigDecimal wholeDiscountValue;

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
     * 状态（21-待审核；22-审核通过；23-审核驳回；31-待出库；33-已完成；34-已取消）
     */
    @ApiModelProperty(value = "状态（21-待审核；22-审核通过；23-审核驳回；31-待出库；33-已完成；34-已取消）")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 配货单明细
     */
    @ApiModelProperty(value = "配货单明细")
    private List<DistrSendDetailCacheVO> distrSendDetailCacheVOS;

    /**
     * saas_distr_send
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
     * 单据类型（5411）
     * @return order_type 单据类型（5411）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5411）
     * @param orderType 单据类型（5411）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 配货单号
     * @return code 配货单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 配货单号
     * @param code 配货单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 配货日期
     * @return distr_date 配货日期
     */
    public Date getDistrDate() {
        return distrDate;
    }

    /**
     * 配货日期
     * @param distrDate 配货日期
     */
    public void setDistrDate(Date distrDate) {
        this.distrDate = distrDate;
    }

    /**
     * 要货单位ID
     * @return request_unit_id 要货单位ID
     */
    public Long getRequestUnitId() {
        return requestUnitId;
    }

    /**
     * 要货单位ID
     * @param requestUnitId 要货单位ID
     */
    public void setRequestUnitId(Long requestUnitId) {
        this.requestUnitId = requestUnitId;
    }

    /**
     * 要货单位编码
     * @return request_unit_code 要货单位编码
     */
    public String getRequestUnitCode() {
        return requestUnitCode;
    }

    /**
     * 要货单位编码
     * @param requestUnitCode 要货单位编码
     */
    public void setRequestUnitCode(String requestUnitCode) {
        this.requestUnitCode = requestUnitCode == null ? null : requestUnitCode.trim();
    }

    /**
     * 要货单位名称
     * @return request_unit_name 要货单位名称
     */
    public String getRequestUnitName() {
        return requestUnitName;
    }

    /**
     * 要货单位名称
     * @param requestUnitName 要货单位名称
     */
    public void setRequestUnitName(String requestUnitName) {
        this.requestUnitName = requestUnitName == null ? null : requestUnitName.trim();
    }

    /**
     * 配货人员ID
     * @return distr_man_id 配货人员ID
     */
    public Long getDistrManId() {
        return distrManId;
    }

    /**
     * 配货人员ID
     * @param distrManId 配货人员ID
     */
    public void setDistrManId(Long distrManId) {
        this.distrManId = distrManId;
    }

    /**
     * 配货人员编码
     * @return distr_man_code 配货人员编码
     */
    public String getDistrManCode() {
        return distrManCode;
    }

    /**
     * 配货人员编码
     * @param distrManCode 配货人员编码
     */
    public void setDistrManCode(String distrManCode) {
        this.distrManCode = distrManCode == null ? null : distrManCode.trim();
    }

    /**
     * 配货人员名称
     * @return distr_man_name 配货人员名称
     */
    public String getDistrManName() {
        return distrManName;
    }

    /**
     * 配货人员名称
     * @param distrManName 配货人员名称
     */
    public void setDistrManName(String distrManName) {
        this.distrManName = distrManName == null ? null : distrManName.trim();
    }

    /**
     * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    public Integer getDistrType() {
        return distrType;
    }

    /**
     * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    /**
     * 配货规则（0-按要货顺序；1-按库存平均；2-按要货数量占比）
     * @return distr_rule 配货规则（0-按要货顺序；1-按库存平均；2-按要货数量占比）
     */
    public Integer getDistrRule() {
        return distrRule;
    }

    /**
     * 配货规则（0-按要货顺序；1-按库存平均；2-按要货数量占比）
     * @param distrRule 配货规则（0-按要货顺序；1-按库存平均；2-按要货数量占比）
     */
    public void setDistrRule(Integer distrRule) {
        this.distrRule = distrRule;
    }

    /**
     * 缺配处理（0-生成缺配单；1-不处理）
     * @return lack_handle 缺配处理（0-生成缺配单；1-不处理）
     */
    public Integer getLackHandle() {
        return lackHandle;
    }

    /**
     * 缺配处理（0-生成缺配单；1-不处理）
     * @param lackHandle 缺配处理（0-生成缺配单；1-不处理）
     */
    public void setLackHandle(Integer lackHandle) {
        this.lackHandle = lackHandle;
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
     * 状态（21-待审核；22-审核通过；23-审核驳回；31-待出库；33-已完成；34-已取消）
     * @return status 状态（21-待审核；22-审核通过；23-审核驳回；31-待出库；33-已完成；34-已取消）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（21-待审核；22-审核通过；23-审核驳回；31-待出库；33-已完成；34-已取消）
     * @param status 状态（21-待审核；22-审核通过；23-审核驳回；31-待出库；33-已完成；34-已取消）
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

    public List<DistrSendDetailCacheVO> getDistrSendDetailCacheVOS() {
        return distrSendDetailCacheVOS;
    }

    public void setDistrSendDetailCacheVOS(List<DistrSendDetailCacheVO> distrSendDetailCacheVOS) {
        this.distrSendDetailCacheVOS = distrSendDetailCacheVOS;
    }

    public String getDistrTypeName() {
        if(distrType != null){
            distrTypeName = DistrType.getValue(distrType);
        }else {
            distrTypeName = "";
        }
        return distrTypeName;
    }

    public void setDistrTypeName(String distrTypeName) {
        if(distrType != null){
            distrTypeName = DistrType.getValue(distrType);
        }else {
            distrTypeName = "";
        }
        this.distrTypeName = distrTypeName;
    }

    public BigDecimal getWholeDiscountValue() {
        return wholeDiscountValue;
    }

    public void setWholeDiscountValue(BigDecimal wholeDiscountValue) {
        this.wholeDiscountValue = wholeDiscountValue;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "DistrSendCacheVO{" +
                "id=" + id +
                ", enterpriseId=" + enterpriseId +
                ", parentId=" + parentId +
                ", orderType=" + orderType +
                ", code='" + code + '\'' +
                ", distrDate=" + distrDate +
                ", requestUnitId=" + requestUnitId +
                ", requestUnitCode='" + requestUnitCode + '\'' +
                ", requestUnitName='" + requestUnitName + '\'' +
                ", distrManId=" + distrManId +
                ", distrManCode='" + distrManCode + '\'' +
                ", distrManName='" + distrManName + '\'' +
                ", distrType=" + distrType +
                ", distrTypeName='" + distrTypeName + '\'' +
                ", distrRule=" + distrRule +
                ", lackHandle=" + lackHandle +
                ", quantityTotal=" + quantityTotal +
                ", varietiesQuantity=" + varietiesQuantity +
                ", amountTotal=" + amountTotal +
                ", wholeDiscount=" + wholeDiscount +
                ", wholeDiscountValue=" + wholeDiscountValue +
                ", wholeDiscountAmount=" + wholeDiscountAmount +
                ", realAmountTotal=" + realAmountTotal +
                ", notaxRealAmountTotal=" + notaxRealAmountTotal +
                ", taxAmountTotal=" + taxAmountTotal +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", distrSendDetailCacheVOS=" + distrSendDetailCacheVOS +
                '}';
    }
}
