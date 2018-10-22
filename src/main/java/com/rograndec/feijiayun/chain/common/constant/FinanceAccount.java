package com.rograndec.feijiayun.chain.common.constant;

/**
 *
 * @ClassName: FinanceAccount
 * @Description: 财务科目枚举
 * @author zhongyi.li zhongyi.li@rograndec.com
 * @Date 2018年1月6日 上午11:21:47
 *
 */
public enum FinanceAccount {
    CASH_RMB("100101", "现金-人民币"),
    BANK_DEPOSIT_RMB("100201", "银行存款-人民币"),
    OTHER_MONETARY_FUNDS("101211", "其它货币基金"),
    RECEIVABLE_ACCOUNT("112201","应收账款"),
    RECEIVABLE_PAYMENT("112211","应收缴款"),
    MATERIAL_PURCHASE("140101","材料采购"),
    INVENTORY_GOODS("140601","库存商品"),
    PAYMENT_ACCOUNT("220201","应付账款"),
    PURCHASE_TAX_AMOUNT("22210101","进项税额"),
    SALE_TAX_AMOUNT("22210105","销项税额"),
    INVENTORY_DIFF_PROFIT("640104","库存差异-收益"),
    INVENTORY_DIFF_LOSS("640105","库存差异-损失"),
    SALE_COST("640101","销售成本"),
    MAIN_BUSINESS_INCOME("666101","主营业务收入"),
    CASH_DISCOUNT("660305","财务费用-现金折扣"),
    LOSS_PROFIT_ADJUST("690101","以前年度损溢调整");

    // 科目编码
    private String accountCode;

    // 科目名称
    private String accountName;

    FinanceAccount(String accountCode, String accountName){
        this.accountCode = accountCode;
        this.accountName = accountName;
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

    public static String getAccountName(String accountCode) {
        if(accountCode == null){
            return null;
        }
        for (FinanceAccount fat : FinanceAccount.values()) {
            if (fat.getAccountCode().equals(accountCode)) {
                return fat.getAccountName();
            }
        }
        return null;
    }


}