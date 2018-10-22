package com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.vo;

import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.entity.FinancePayment;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.entity.FinancePaymentDetail;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayFormVO implements Serializable{

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 单号
     */
    @ApiModelProperty(value = "单号")
    private String code;

    /**
     * 付款日期
     */
    @ApiModelProperty(value = "付款日期")
    private Date paymentDate;

    /**
     * 付款人员ID
     */
    @ApiModelProperty(value = "付款人员ID")
    private Long paymentManId;

    /**
     * 付款人员编码
     */
    @ApiModelProperty(value = "付款人员编码")
    private String paymentManCode;

    /**
     * 付款人员名称
     */
    @ApiModelProperty(value = "付款人员名称")
    private String paymentManName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 收款单位类型（0-供货单位；1-购货单位）
     */
    @ApiModelProperty(value = "收款单位类型（0-供货单位；1-购货单位）")
    private Integer companyType;

    /**
     * 草稿ID
     * */
    @ApiModelProperty(value = "草稿ID")
    private String redisKeyValue;

    /**
     * 收款单位ID
     */
    @ApiModelProperty(value = "收款单位ID")
    private Long companyId;

    /**
     * 收款单位编码
     */
    @ApiModelProperty(value = "收款单位编码")
    private String companyCode;

    /**
     * 收款单位名称
     */
    @ApiModelProperty(value = "收款单位名称")
    private String companyName;

    //********************************************
    //右下角页面字段
    //收款总额 前端算
    /**
     * 付款总额
     */
    @ApiModelProperty(value = "付款总额")
    private BigDecimal totalPay;
    /**
     * 转账日期
     */
    @ApiModelProperty(value = "转账日期")
    private Date transferDate;

    /**
     * 转账银行ID
     */
    @ApiModelProperty(value = "转账银行ID")
    private Long bankId;

    /**
     * 转账银行名称
     */
    @ApiModelProperty(value = "转账银行名称")
    private String bankName;

    /**
     * 转账银行账号
     */
    @ApiModelProperty(value = "转账银行账号")
    private String bankAccount;

    /**
     * 银行支付金额
     */
    @ApiModelProperty(value = "银行支付金额")
    private BigDecimal bankAmountTotal;

    /**
     * 现金支付金额
     */
    @ApiModelProperty(value = "现金支付金额")
    private BigDecimal cashAmountTotal;

    /**
     * 未清余额
     */
    @ApiModelProperty(value = "未清余额")
    private BigDecimal unclearBalanceTotal;

    //***********************************************
    /**
     * 明细行内容
     */
    @ApiModelProperty(value = "明细行内容")
    private List<PayDetailVO> list;

    /**
     * 修改原因
     */
    @ApiModelProperty(value = "修改原因")
    private String updateDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getPaymentManId() {
        return paymentManId;
    }

    public void setPaymentManId(Long paymentManId) {
        this.paymentManId = paymentManId;
    }

    public String getPaymentManCode() {
        return paymentManCode;
    }

    public void setPaymentManCode(String paymentManCode) {
        this.paymentManCode = paymentManCode;
    }

    public String getPaymentManName() {
        return paymentManName;
    }

    public void setPaymentManName(String paymentManName) {
        this.paymentManName = paymentManName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(BigDecimal totalPay) {
        this.totalPay = totalPay;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BigDecimal getBankAmountTotal() {
        return bankAmountTotal;
    }

    public void setBankAmountTotal(BigDecimal bankAmountTotal) {
        this.bankAmountTotal = bankAmountTotal;
    }

    public BigDecimal getCashAmountTotal() {
        return cashAmountTotal;
    }

    public void setCashAmountTotal(BigDecimal cashAmountTotal) {
        this.cashAmountTotal = cashAmountTotal;
    }

    public BigDecimal getUnclearBalanceTotal() {
        return unclearBalanceTotal;
    }

    public void setUnclearBalanceTotal(BigDecimal unclearBalanceTotal) {
        this.unclearBalanceTotal = unclearBalanceTotal;
    }

    public List<PayDetailVO> getList() {
        return list;
    }

    public void setList(List<PayDetailVO> list) {
        this.list = list;
    }

    public String getUpdateDetail() {
        return updateDetail;
    }

    public void setUpdateDetail(String updateDetail) {
        this.updateDetail = updateDetail;
    }

    public String getRedisKeyValue() {
        return redisKeyValue;
    }

    public void setRedisKeyValue(String redisKeyValue) {
        this.redisKeyValue = redisKeyValue;
    }

    @Override
    public String toString() {
        return "PayFormVO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", paymentDate=" + paymentDate +
                ", paymentManId=" + paymentManId +
                ", paymentManCode='" + paymentManCode + '\'' +
                ", paymentManName='" + paymentManName + '\'' +
                ", remark='" + remark + '\'' +
                ", companyType=" + companyType +
                ", companyId=" + companyId +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", totalPay=" + totalPay +
                ", transferDate=" + transferDate +
                ", bankId=" + bankId +
                ", bankName='" + bankName + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", bankAmountTotal=" + bankAmountTotal +
                ", cashAmountTotal=" + cashAmountTotal +
                ", unclearBalanceTotal=" + unclearBalanceTotal +
                ", list=" + list +
                ", updateDetail='" + updateDetail + '\'' +
                '}';
    }

    public PayFormVO convertToVO(FinancePayment financePayment, List<FinancePaymentDetail> detailList) {
        PayFormVO formVO = new PayFormVO();
        formVO.setId(financePayment.getId());
        formVO.setCode(financePayment.getCode());
        formVO.setPaymentDate(financePayment.getPaymentDate());
        formVO.setPaymentManId(financePayment.getPaymentManId());
        formVO.setPaymentManCode(financePayment.getPaymentManCode());
        formVO.setPaymentManName(financePayment.getPaymentManName());
        formVO.setRemark(financePayment.getRemark());
        formVO.setCompanyId(financePayment.getCompanyId());
        formVO.setCompanyCode(financePayment.getCompanyCode());
        formVO.setCompanyName(financePayment.getCompanyName());
        formVO.setCompanyType(financePayment.getCompanyType());
        formVO.setTransferDate(financePayment.getTransferDate());
        formVO.setBankId(financePayment.getBankId());
        formVO.setBankName(financePayment.getBankName());
        formVO.setBankAccount(financePayment.getBankAccount());
        formVO.setBankAmountTotal(financePayment.getBankAmountTotal());
        formVO.setCashAmountTotal(financePayment.getCashAmountTotal());
        formVO.setUnclearBalanceTotal(financePayment.getUnclearBalanceTotal());
        List<PayDetailVO> list = new ArrayList();
        BigDecimal totalPay = BigDecimal.ZERO;
        for (FinancePaymentDetail detail : detailList) {
            PayDetailVO detailVO = new PayDetailVO();
            detailVO.setBaseOrderDate(detail.getBaseOrderDate());
            detailVO.setBaseOrderId(detail.getBaseOrderId());
            detailVO.setBaseOrderCode(detail.getBaseOrderCode());
            detailVO.setBaseOrderType(detail.getBaseOrderType());
            detailVO.setOrderTypeName(OrderRule.getName(detail.getBaseOrderType()));
            detailVO.setAmount(detail.getAmount());
            detailVO.setClearAmount(detail.getClearAmount());
            detailVO.setUnclearAmount(detail.getUnclearAmount());
            detailVO.setPaymentAmount(detail.getPaymentAmount());
            detailVO.setDiscountAmount(detail.getDiscountAmount());
            detailVO.setRealAmount(detail.getRealAmount());
            detailVO.setUnclearBalance(detail.getUnclearBalance());
            detailVO.setLineNum(detail.getLineNum());
            detailVO.setRemark(detail.getRemark());
            totalPay = totalPay.add(detail.getRealAmount());
            list.add(detailVO);
        }
        formVO.setTotalPay(totalPay);
        formVO.setList(list);
        return formVO;
    }
}
