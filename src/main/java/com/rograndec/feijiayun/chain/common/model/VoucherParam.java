package com.rograndec.feijiayun.chain.common.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 凭证表参数实体类
 */
public class VoucherParam {

        private String orderCode;
        private String accountCode;
        private String accountName;
        private Integer subAccountType;
        private Long subAccountId;
        private String subAccountCode;
        private String subAccountName;
        private Date baseOrderDate;
        private Integer baseOrderType;
        private Long baseOrderId;
        private String baseOrderCode;
        private Long baseDtlId;
        private Long baseOperatorId;
        private String baseOperatorCode;
        private String baseOperatorName;
        private BigDecimal debitAmount;
        private BigDecimal creditAmount;
        private BigDecimal balanceBalance;
        private BigDecimal balance;
        private String remark;

        public String getOrderCode() {
                return orderCode;
        }

        public void setOrderCode(String orderCode) {
                this.orderCode = orderCode;
        }

        public String getAccountCode() {
                return accountCode;
        }

        public void setAccountCode(String accountCode) {
                this.accountCode = accountCode;
        }

        public String getAccountName() {
                return accountName;
        }

        public void setAccountName(String accountName) {
                this.accountName = accountName;
        }

        public Integer getSubAccountType() {
                return subAccountType;
        }

        public void setSubAccountType(Integer subAccountType) {
                this.subAccountType = subAccountType;
        }

        public Long getSubAccountId() {
                return subAccountId;
        }

        public void setSubAccountId(Long subAccountId) {
                this.subAccountId = subAccountId;
        }

        public String getSubAccountCode() {
                return subAccountCode;
        }

        public void setSubAccountCode(String subAccountCode) {
                this.subAccountCode = subAccountCode;
        }

        public String getSubAccountName() {
                return subAccountName;
        }

        public void setSubAccountName(String subAccountName) {
                this.subAccountName = subAccountName;
        }

        public Date getBaseOrderDate() {
                return baseOrderDate;
        }

        public void setBaseOrderDate(Date baseOrderDate) {
                this.baseOrderDate = baseOrderDate;
        }

        public Integer getBaseOrderType() {
                return baseOrderType;
        }

        public void setBaseOrderType(Integer baseOrderType) {
                this.baseOrderType = baseOrderType;
        }

        public Long getBaseOrderId() {
                return baseOrderId;
        }

        public void setBaseOrderId(Long baseOrderId) {
                this.baseOrderId = baseOrderId;
        }

        public String getBaseOrderCode() {
                return baseOrderCode;
        }

        public void setBaseOrderCode(String baseOrderCode) {
                this.baseOrderCode = baseOrderCode;
        }

        public Long getBaseOperatorId() {
                return baseOperatorId;
        }

        public void setBaseOperatorId(Long baseOperatorId) {
                this.baseOperatorId = baseOperatorId;
        }

        public String getBaseOperatorCode() {
                return baseOperatorCode;
        }

        public void setBaseOperatorCode(String baseOperatorCode) {
                this.baseOperatorCode = baseOperatorCode;
        }

        public Long getBaseDtlId() {
                return baseDtlId;
        }

        public void setBaseDtlId(Long baseDtlId) {
                this.baseDtlId = baseDtlId;
        }

        public String getBaseOperatorName() {
                return baseOperatorName;
        }

        public void setBaseOperatorName(String baseOperatorName) {
                this.baseOperatorName = baseOperatorName;
        }

        public BigDecimal getDebitAmount() {
                return debitAmount;
        }

        public void setDebitAmount(BigDecimal debitAmount) {
                this.debitAmount = debitAmount;
        }

        public BigDecimal getCreditAmount() {
                return creditAmount;
        }

        public void setCreditAmount(BigDecimal creditAmount) {
                this.creditAmount = creditAmount;
        }

        public BigDecimal getBalanceBalance() {
                return balanceBalance;
        }

        public void setBalanceBalance(BigDecimal balanceBalance) {
                this.balanceBalance = balanceBalance;
        }

        public BigDecimal getBalance() {
                return balance;
        }

        public void setBalance(BigDecimal balance) {
                this.balance = balance;
        }

        public String getRemark() {
                return remark;
        }

        public void setRemark(String remark) {
                this.remark = remark;
        }
}