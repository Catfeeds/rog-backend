package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_retail_payment
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class RetailPaymentVO implements Serializable {
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
     * 企业编码
     */
    @ApiModelProperty(value = "企业编码")
    private String enterpriseCode;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;


    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 单据类型（6242）
     */
    @ApiModelProperty(value = "单据类型（6242）")
    private Integer orderType;

    /**
     * 缴款日期
     */
    @ApiModelProperty(value = "缴款日期")
    private Date paymentDate;

    /**
     * 缴款单号
     */
    @ApiModelProperty(value = "缴款单号")
    private String code;

    /**
     * 缴款人员ID
     */
    @ApiModelProperty(value = "缴款人员ID")
    private Long paymentManId;

    /**
     * 缴款人员编码
     */
    @ApiModelProperty(value = "缴款人员编码")
    private String paymentManCode;

    /**
     * 缴款人员名称
     */
    @ApiModelProperty(value = "缴款人员名称")
    private String paymentManName;

    /**
     * 应缴现金合计
     */
    @ApiModelProperty(value = "应缴现金合计")
    private BigDecimal cashTotal;

    /**
     * 实缴现金合计
     */
    @ApiModelProperty(value = "实缴现金合计")
    private BigDecimal realCashTotal;

    /**
     * 现金差额合计
     */
    @ApiModelProperty(value = "现金差额合计")
    private BigDecimal diffCashTotal;

    /**
     * 应缴银行存款合计
     */
    @ApiModelProperty(value = "应缴银行存款合计")
    private BigDecimal bankAmountTotal;

    /**
     * 应缴其它货币资金合计
     */
    @ApiModelProperty(value = "应缴其它货币资金合计")
    private BigDecimal otherAmountTotal;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 状态名
     */
    @ApiModelProperty(value = "状态名")
    private String statusName;

    @ApiModelProperty(value = "修改标记 ture可修改,false不可修改")
    private Boolean updateFlag;

    @ApiModelProperty(value = "冲销标记 ture可冲销,false不可冲销")
    private Boolean chargrAgainstFlag;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "修改原因")
    private String updateReason;

    @ApiModelProperty(value = "缴款清单集合")
    private List<RetailPaymentItemVO> retailPaymentItemVOS;

    /**
     * saas_retail_payment
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
     * 单据类型（6242）
     * @return order_type 单据类型（6242）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（6242）
     * @param orderType 单据类型（6242）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 缴款日期
     * @return payment_date 缴款日期
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * 缴款日期
     * @param paymentDate 缴款日期
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * 缴款单号
     * @return code 缴款单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 缴款单号
     * @param code 缴款单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 缴款人员ID
     * @return payment_man_id 缴款人员ID
     */
    public Long getPaymentManId() {
        return paymentManId;
    }

    /**
     * 缴款人员ID
     * @param paymentManId 缴款人员ID
     */
    public void setPaymentManId(Long paymentManId) {
        this.paymentManId = paymentManId;
    }

    /**
     * 缴款人员编码
     * @return payment_man_code 缴款人员编码
     */
    public String getPaymentManCode() {
        return paymentManCode;
    }

    /**
     * 缴款人员编码
     * @param paymentManCode 缴款人员编码
     */
    public void setPaymentManCode(String paymentManCode) {
        this.paymentManCode = paymentManCode == null ? null : paymentManCode.trim();
    }

    /**
     * 缴款人员名称
     * @return payment_man_name 缴款人员名称
     */
    public String getPaymentManName() {
        return paymentManName;
    }

    /**
     * 缴款人员名称
     * @param paymentManName 缴款人员名称
     */
    public void setPaymentManName(String paymentManName) {
        this.paymentManName = paymentManName == null ? null : paymentManName.trim();
    }

    /**
     * 应缴现金合计
     * @return cash_total 应缴现金合计
     */
    public BigDecimal getCashTotal() {
        return cashTotal;
    }

    /**
     * 应缴现金合计
     * @param cashTotal 应缴现金合计
     */
    public void setCashTotal(BigDecimal cashTotal) {
        this.cashTotal = cashTotal;
    }

    /**
     * 实缴现金合计
     * @return real_cash_total 实缴现金合计
     */
    public BigDecimal getRealCashTotal() {
        return realCashTotal;
    }

    /**
     * 实缴现金合计
     * @param realCashTotal 实缴现金合计
     */
    public void setRealCashTotal(BigDecimal realCashTotal) {
        this.realCashTotal = realCashTotal;
    }

    /**
     * 现金差额合计
     * @return diff_cash_total 现金差额合计
     */
    public BigDecimal getDiffCashTotal() {
        return diffCashTotal;
    }

    /**
     * 现金差额合计
     * @param diffCashTotal 现金差额合计
     */
    public void setDiffCashTotal(BigDecimal diffCashTotal) {
        this.diffCashTotal = diffCashTotal;
    }

    /**
     * 应缴银行存款合计
     * @return bank_amount_total 应缴银行存款合计
     */
    public BigDecimal getBankAmountTotal() {
        return bankAmountTotal;
    }

    /**
     * 应缴银行存款合计
     * @param bankAmountTotal 应缴银行存款合计
     */
    public void setBankAmountTotal(BigDecimal bankAmountTotal) {
        this.bankAmountTotal = bankAmountTotal;
    }

    /**
     * 应缴其它货币资金合计
     * @return other_amount_total 应缴其它货币资金合计
     */
    public BigDecimal getOtherAmountTotal() {
        return otherAmountTotal;
    }

    /**
     * 应缴其它货币资金合计
     * @param otherAmountTotal 应缴其它货币资金合计
     */
    public void setOtherAmountTotal(BigDecimal otherAmountTotal) {
        this.otherAmountTotal = otherAmountTotal;
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

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public List<RetailPaymentItemVO> getRetailPaymentItemVOS() {
        return retailPaymentItemVOS;
    }

    public void setRetailPaymentItemVOS(List<RetailPaymentItemVO> retailPaymentItemVOS) {
        this.retailPaymentItemVOS = retailPaymentItemVOS;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Boolean getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Boolean updateFlag) {
        this.updateFlag = updateFlag;
    }

    public Boolean getChargrAgainstFlag() {
        return chargrAgainstFlag;
    }

    public void setChargrAgainstFlag(Boolean chargrAgainstFlag) {
        this.chargrAgainstFlag = chargrAgainstFlag;
    }

    public String getUpdateReason() {
        return updateReason;
    }

    public void setUpdateReason(String updateReason) {
        this.updateReason = updateReason;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "RetailPaymentVO{" +
                "id=" + id +
                ", enterpriseId=" + enterpriseId +
                ", enterpriseCode='" + enterpriseCode + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", parentId=" + parentId +
                ", orderType=" + orderType +
                ", paymentDate=" + paymentDate +
                ", code='" + code + '\'' +
                ", paymentManId=" + paymentManId +
                ", paymentManCode='" + paymentManCode + '\'' +
                ", paymentManName='" + paymentManName + '\'' +
                ", cashTotal=" + cashTotal +
                ", realCashTotal=" + realCashTotal +
                ", diffCashTotal=" + diffCashTotal +
                ", bankAmountTotal=" + bankAmountTotal +
                ", otherAmountTotal=" + otherAmountTotal +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", updateFlag=" + updateFlag +
                ", chargrAgainstFlag=" + chargrAgainstFlag +
                ", updateReason='" + updateReason + '\'' +
                ", remark='" + remark + '\'' +
                ", retailPaymentItemVOS=" + retailPaymentItemVOS +
                '}';
    }
}