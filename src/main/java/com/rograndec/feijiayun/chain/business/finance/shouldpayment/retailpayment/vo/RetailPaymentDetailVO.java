package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_retail_payment_detail
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class RetailPaymentDetailVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 缴款清单ID
     */
    @ApiModelProperty(value = "缴款清单ID")
    private Long itemId;

    /**
     * 缴款单ID
     */
    @ApiModelProperty(value = "缴款单ID")
    private Long docId;

    /**
     * 交班单据ID
     */
    @ApiModelProperty(value = "交班单据ID")
    private Long shiftId;

    /**
     * 交班人员ID
     */
    @ApiModelProperty(value = "交班人员ID")
    private Long shiftManId;

    /**
     * 交班人编码
     */
    @ApiModelProperty(value = "交班人编码")
    private String shiftManCode;

    /**
     * 交班人名称
     */
    @ApiModelProperty(value = "交班人名称")
    private String shiftManName;

    /**
     * 应缴现金
     */
    @ApiModelProperty(value = "应缴现金")
    private BigDecimal payableCash;

    /**
     * 实缴现金
     */
    @ApiModelProperty(value = "实缴现金")
    private BigDecimal cash;

    /**
     * 现金差额
     */
    @ApiModelProperty(value = "现金差额")
    private BigDecimal diffCash;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    /**
     * 状态（0-待缴款；1-已缴款；2-已冲销）
     */
    @ApiModelProperty(value = "状态（0-待缴款；1-已缴款；2-已冲销）")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "支付方式列表")
    private List<RetailPaymentPaydtlVO> retailPaymentPaydtlVOS;

    /**
     * saas_retail_payment_detail
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
     * 缴款清单ID
     * @return item_id 缴款清单ID
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * 缴款清单ID
     * @param itemId 缴款清单ID
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * 缴款单ID
     * @return doc_id 缴款单ID
     */
    public Long getDocId() {
        return docId;
    }

    /**
     * 缴款单ID
     * @param docId 缴款单ID
     */
    public void setDocId(Long docId) {
        this.docId = docId;
    }

    /**
     * 交班单据ID
     * @return shift_id 交班单据ID
     */
    public Long getShiftId() {
        return shiftId;
    }

    /**
     * 交班单据ID
     * @param shiftId 交班单据ID
     */
    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    /**
     * 交班人员ID
     * @return shift_man_id 交班人员ID
     */
    public Long getShiftManId() {
        return shiftManId;
    }

    /**
     * 交班人员ID
     * @param shiftManId 交班人员ID
     */
    public void setShiftManId(Long shiftManId) {
        this.shiftManId = shiftManId;
    }

    /**
     * 交班人编码
     * @return shift_man_code 交班人编码
     */
    public String getShiftManCode() {
        return shiftManCode;
    }

    /**
     * 交班人编码
     * @param shiftManCode 交班人编码
     */
    public void setShiftManCode(String shiftManCode) {
        this.shiftManCode = shiftManCode == null ? null : shiftManCode.trim();
    }

    /**
     * 交班人名称
     * @return shift_man_name 交班人名称
     */
    public String getShiftManName() {
        return shiftManName;
    }

    /**
     * 交班人名称
     * @param shiftManName 交班人名称
     */
    public void setShiftManName(String shiftManName) {
        this.shiftManName = shiftManName == null ? null : shiftManName.trim();
    }

    /**
     * 应缴现金
     * @return payable_cash 应缴现金
     */
    public BigDecimal getPayableCash() {
        return payableCash;
    }

    /**
     * 应缴现金
     * @param payableCash 应缴现金
     */
    public void setPayableCash(BigDecimal payableCash) {
        this.payableCash = payableCash;
    }

    /**
     * 实缴现金
     * @return cash 实缴现金
     */
    public BigDecimal getCash() {
        return cash;
    }

    /**
     * 实缴现金
     * @param cash 实缴现金
     */
    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    /**
     * 现金差额
     * @return diff_cash 现金差额
     */
    public BigDecimal getDiffCash() {
        return diffCash;
    }

    /**
     * 现金差额
     * @param diffCash 现金差额
     */
    public void setDiffCash(BigDecimal diffCash) {
        this.diffCash = diffCash;
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
     * 状态（0-待缴款；1-已缴款；2-已冲销）
     * @return status 状态（0-待缴款；1-已缴款；2-已冲销）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-待缴款；1-已缴款；2-已冲销）
     * @param status 状态（0-待缴款；1-已缴款；2-已冲销）
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

    public List<RetailPaymentPaydtlVO> getRetailPaymentPaydtlVOS() {
        return retailPaymentPaydtlVOS;
    }

    public void setRetailPaymentPaydtlVOS(List<RetailPaymentPaydtlVO> retailPaymentPaydtlVOS) {
        this.retailPaymentPaydtlVOS = retailPaymentPaydtlVOS;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "RetailPaymentDetailVO{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", docId=" + docId +
                ", shiftId=" + shiftId +
                ", shiftManId=" + shiftManId +
                ", shiftManCode='" + shiftManCode + '\'' +
                ", shiftManName='" + shiftManName + '\'' +
                ", payableCash=" + payableCash +
                ", cash=" + cash +
                ", diffCash=" + diffCash +
                ", lineNum=" + lineNum +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", retailPaymentPaydtlVOS=" + retailPaymentPaydtlVOS +
                '}';
    }
}