package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo;

import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.entity.FinanceReceivable;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.entity.FinanceReceivableDetail;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReceiveFormVO implements Serializable{
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
     * 收款日期
     */
    @ApiModelProperty(value = "收款日期")
    private Date receivableDate;

    /**
     * 收款人员ID
     */
    @ApiModelProperty(value = "收款人员ID")
    private Long receivableManId;

    /**
     * 收款人员编码
     */
    @ApiModelProperty(value = "收款人员编码")
    private String receivableManCode;

    /**
     * 收款人员名称
     */
    @ApiModelProperty(value = "收款人员名称")
    private String receivableManName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 付款单位类型（0-供货单位；1-购货单位）
     */
    @ApiModelProperty(value = "付款单位类型（0-供货单位；1-购货单位）")
    private Integer companyType;

    /**
     * 付款单位ID
     */
    @ApiModelProperty(value = "付款单位ID")
    private Long companyId;

    /**
     * 付款单位编码
     */
    @ApiModelProperty(value = "付款单位编码")
    private String companyCode;

    /**
     * 付款单位名称
     */
    @ApiModelProperty(value = "付款单位名称")
    private String companyName;

    //********************************************
    //右下角页面字段
    //收款总额 前端算
    /**
     * 收款总额
     */
    @ApiModelProperty(value = "收款总额")
    private BigDecimal totalReceipts;
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
     * 银行收入金额
     */
    @ApiModelProperty(value = "银行收入金额")
    private BigDecimal bankAmountTotal;

    /**
     * 现金收入金额
     */
    @ApiModelProperty(value = "现金收入金额")
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
    private List<ReceiveDetailVO> list;

    /**
     * 修改原因
     */
    @ApiModelProperty(value = "修改原因")
    private String updateDetail;

    /**
     * 草稿ID
     * */
    @ApiModelProperty(value = "草稿ID")
    private String redisKeyValue;

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

    public Date getReceivableDate() {
        return receivableDate;
    }

    public void setReceivableDate(Date receivableDate) {
        this.receivableDate = receivableDate;
    }

    public Long getReceivableManId() {
        return receivableManId;
    }

    public void setReceivableManId(Long receivableManId) {
        this.receivableManId = receivableManId;
    }

    public String getReceivableManCode() {
        return receivableManCode;
    }

    public void setReceivableManCode(String receivableManCode) {
        this.receivableManCode = receivableManCode;
    }

    public String getReceivableManName() {
        return receivableManName;
    }

    public void setReceivableManName(String receivableManName) {
        this.receivableManName = receivableManName;
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

    public List<ReceiveDetailVO> getList() {
        return list;
    }

    public void setList(List<ReceiveDetailVO> list) {
        this.list = list;
    }

    public String getUpdateDetail() {
        return updateDetail;
    }

    public void setUpdateDetail(String updateDetail) {
        this.updateDetail = updateDetail;
    }

    public BigDecimal getTotalReceipts() {
        return totalReceipts;
    }

    public void setTotalReceipts(BigDecimal totalReceipts) {
        this.totalReceipts = totalReceipts;
    }

    public String getRedisKeyValue() {
        return redisKeyValue;
    }

    public void setRedisKeyValue(String redisKeyValue) {
        this.redisKeyValue = redisKeyValue;
    }

    @Override
    public String toString() {
        return "ReceiveFormVO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", receivableDate=" + receivableDate +
                ", receivableManId=" + receivableManId +
                ", receivableManCode='" + receivableManCode + '\'' +
                ", receivableManName='" + receivableManName + '\'' +
                ", remark='" + remark + '\'' +
                ", companyType=" + companyType +
                ", companyId=" + companyId +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", totalReceipts=" + totalReceipts +
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

    public ReceiveFormVO convertToVO(FinanceReceivable financeReceivable, List<FinanceReceivableDetail> detailList) {
        ReceiveFormVO formVO = new ReceiveFormVO();
        formVO.setId(financeReceivable.getId());
        formVO.setCode(financeReceivable.getCode());
        formVO.setReceivableDate(financeReceivable.getReceivableDate());
        formVO.setReceivableManId(financeReceivable.getReceivableManId());
        formVO.setReceivableManCode(financeReceivable.getReceivableManCode());
        formVO.setReceivableManName(financeReceivable.getReceivableManName());
        formVO.setRemark(financeReceivable.getRemark());
        formVO.setCompanyId(financeReceivable.getCompanyId());
        formVO.setCompanyCode(financeReceivable.getCompanyCode());
        formVO.setCompanyName(financeReceivable.getCompanyName());
        formVO.setCompanyType(financeReceivable.getCompanyType());
        formVO.setTransferDate(financeReceivable.getTransferDate());
        formVO.setBankId(financeReceivable.getBankId());
        formVO.setBankName(financeReceivable.getBankName());
        formVO.setBankAccount(financeReceivable.getBankAccount());
        formVO.setBankAmountTotal(financeReceivable.getBankAmountTotal());
        formVO.setCashAmountTotal(financeReceivable.getCashAmountTotal());
        formVO.setUnclearBalanceTotal(financeReceivable.getUnclearBalanceTotal());
        List<ReceiveDetailVO> list = new ArrayList();
        BigDecimal totalReceive = BigDecimal.ZERO;
        for (FinanceReceivableDetail detail : detailList) {
            ReceiveDetailVO detailVO = new ReceiveDetailVO();
            detailVO.setBaseOrderDate(detail.getBaseOrderDate());
            detailVO.setBaseOrderId(detail.getBaseOrderId());
            detailVO.setBaseOrderCode(detail.getBaseOrderCode());
            detailVO.setBaseOrderType(detail.getBaseOrderType());
            detailVO.setOrderTypeName(OrderRule.getName(detail.getBaseOrderType()));
            detailVO.setAmount(detail.getAmount());
            detailVO.setClearAmount(detail.getClearAmount());
            detailVO.setUnclearAmount(detail.getUnclearAmount());
            detailVO.setReceivableAmount(detail.getReceivableAmount());
            detailVO.setDiscountAmount(detail.getDiscountAmount());
            detailVO.setRealAmount(detail.getRealAmount());
            detailVO.setUnclearBalance(detail.getUnclearBalance());
            detailVO.setLineNum(detail.getLineNum());
            detailVO.setRemark(detail.getRemark());
            totalReceive = totalReceive.add(detail.getRealAmount());
            list.add(detailVO);
        }
        formVO.setList(list);
        formVO.setTotalReceipts(totalReceive);
        return formVO;
    }
}
