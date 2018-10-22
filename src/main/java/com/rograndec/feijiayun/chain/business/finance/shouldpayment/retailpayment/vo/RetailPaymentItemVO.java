package com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_retail_payment_item
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class RetailPaymentItemVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 缴款单ID
     */
    @ApiModelProperty(value = "缴款单ID")
    private Long docId;

    /**
     * 日结单据ID
     */
    @ApiModelProperty(value = "日结单据ID")
    private Long settleId;

    /**
     * 日结单编码
     */
    @ApiModelProperty(value = "日结单编码")
    private String settleCode;

    /**
     * 日结单类型
     */
    @ApiModelProperty(value = "日结单类型")
    private Integer settleOrderType;

    /**
     * 日结日期
     */
    @ApiModelProperty(value = "日结日期")
    private Date settleDate;

    /**
     * 日结人员ID
     */
    @ApiModelProperty(value = "日结人员ID")
    private Long settleManId;

    /**
     * 日结人员编码
     */
    @ApiModelProperty(value = "日结人员编码")
    private String settleManCode;

    /**
     * 日结人员姓名
     */
    @ApiModelProperty(value = "日结人员姓名")
    private String settleManName;

    /**
     * 销售笔数
     */
    @ApiModelProperty(value = "销售笔数")
    private Integer salePens;

    /**
     * 销售金额
     */
    @ApiModelProperty(value = "销售金额")
    private BigDecimal saleAmount;

    /**
     * 退货笔数
     */
    @ApiModelProperty(value = "退货笔数")
    private Integer returnPens;

    /**
     * 退货金额
     */
    @ApiModelProperty(value = "退货金额")
    private BigDecimal returnAmount;

    /**
     * 应收金额
     */
    @ApiModelProperty(value = "应收金额")
    private BigDecimal amount;

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
     * 缴款明细集合
     */
    @ApiModelProperty(value = "缴款明细集合")
    private List<RetailPaymentDetailVO> retailPaymentDetailVOList;


    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * saas_retail_payment_item
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
     * 日结单据ID
     * @return settle_id 日结单据ID
     */
    public Long getSettleId() {
        return settleId;
    }

    /**
     * 日结单据ID
     * @param settleId 日结单据ID
     */
    public void setSettleId(Long settleId) {
        this.settleId = settleId;
    }

    /**
     * 日结单编码
     * @return settle_code 日结单编码
     */
    public String getSettleCode() {
        return settleCode;
    }

    /**
     * 日结单编码
     * @param settleCode 日结单编码
     */
    public void setSettleCode(String settleCode) {
        this.settleCode = settleCode == null ? null : settleCode.trim();
    }

    /**
     * 日结单类型
     * @return settle_order_type 日结单类型
     */
    public Integer getSettleOrderType() {
        return settleOrderType;
    }

    /**
     * 日结单类型
     * @param settleOrderType 日结单类型
     */
    public void setSettleOrderType(Integer settleOrderType) {
        this.settleOrderType = settleOrderType;
    }

    /**
     * 日结日期
     * @return settle_date 日结日期
     */
    public Date getSettleDate() {
        return settleDate;
    }

    /**
     * 日结日期
     * @param settleDate 日结日期
     */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    /**
     * 应收金额
     * @return amount 应收金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 应收金额
     * @param amount 应收金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public List<RetailPaymentDetailVO> getRetailPaymentDetailVOList() {
        return retailPaymentDetailVOList;
    }

    public void setRetailPaymentDetailVOList(List<RetailPaymentDetailVO> retailPaymentDetailVOList) {
        this.retailPaymentDetailVOList = retailPaymentDetailVOList;
    }
    public Long getSettleManId() {
        return settleManId;
    }

    public void setSettleManId(Long settleManId) {
        this.settleManId = settleManId;
    }

    public String getSettleManCode() {
        return settleManCode;
    }

    public void setSettleManCode(String settleManCode) {
        this.settleManCode = settleManCode;
    }

    public String getSettleManName() {
        return settleManName;
    }

    public void setSettleManName(String settleManName) {
        this.settleManName = settleManName;
    }

    public Integer getSalePens() {
        return salePens;
    }

    public void setSalePens(Integer salePens) {
        this.salePens = salePens;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Integer getReturnPens() {
        return returnPens;
    }

    public void setReturnPens(Integer returnPens) {
        this.returnPens = returnPens;
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "RetailPaymentItemVO{" +
                "id=" + id +
                ", docId=" + docId +
                ", settleId=" + settleId +
                ", settleCode='" + settleCode + '\'' +
                ", settleOrderType=" + settleOrderType +
                ", settleDate=" + settleDate +
                ", settleManId=" + settleManId +
                ", settleManCode='" + settleManCode + '\'' +
                ", settleManName='" + settleManName + '\'' +
                ", salePens=" + salePens +
                ", saleAmount=" + saleAmount +
                ", returnPens=" + returnPens +
                ", returnAmount=" + returnAmount +
                ", amount=" + amount +
                ", lineNum=" + lineNum +
                ", status=" + status +
                ", retailPaymentDetailVOList=" + retailPaymentDetailVOList +
                ", remark='" + remark + '\'' +
                '}';
    }
}