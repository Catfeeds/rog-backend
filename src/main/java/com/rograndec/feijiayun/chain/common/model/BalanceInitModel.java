package com.rograndec.feijiayun.chain.common.model;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.FinanceAccount;
import com.rograndec.feijiayun.chain.common.constant.FinanceAccountType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName: BalanceInitModel
 * @Description: 财务-余额初始化模型
 * @author zhongyi.li zhongyi.li@rograndec.com
 * @date 2018年02月04日 下午12:21:32
 *
 */
public class BalanceInitModel {

    // 企业ID
    private Long enterpriseId;

    // 上级企业ID
    private Long parentId;

    // 企业类型（0-总部；1-自营店；2-加盟店）
    private Integer chainType;

    // 科目编码
    private String accountCode;

    // 科目名称
    private String accountName;

    // 下级科目类型
    private Integer subAccountType;

    // 下级科目ID
    private Long subAccountId;

    // 下级科目编码
    private String subAccountCode;

    // 下级科目名称
    private String subAccountName;

    public BalanceInitModel(Long enterpriseId, Long parentId, Integer chainType,
                            String accountCode, String accountName, Integer subAccountType,Long subAccountId, String subAccountCode, String subAccountName) {
        this.enterpriseId = enterpriseId;
        this.parentId = parentId;
        this.chainType = chainType;
        this.accountCode = accountCode;
        this.accountName = accountName;
        this.subAccountType = subAccountType;
        this.subAccountId = subAccountId;
        this.subAccountCode = subAccountCode;
        this.subAccountName = subAccountName;
    }

    public static List<BalanceInitModel> buildForTax(UserVO user, GoodsTaxRate goodsTaxRate){
        List<GoodsTaxRate> goodsTaxRateList = new ArrayList<GoodsTaxRate>();
        goodsTaxRateList.add(goodsTaxRate);
        List<BalanceInitModel> modelList = new ArrayList<BalanceInitModel>();
        Long enterpriseId = user.getEnterpriseId();
        Long parentId = user.getParentId();
        Integer chainType = user.getChainType();
        /**当前操作人员为总部人员，则插入总部科目余额表；当前操作人员为加盟店人员，则插入加盟店科目余额表；*/
        // 22210101 进项税额;22210105 销项税额
        buildBalanceInitModelForTax(enterpriseId, parentId, chainType, goodsTaxRateList, modelList);
        return modelList;
    }

    public static List<BalanceInitModel> buildForSupplier(UserVO user, Supplier supplier){
        List<BalanceInitModel> modelList = new ArrayList<BalanceInitModel>();
        Long enterpriseId = user.getEnterpriseId();
        Long parentId = user.getParentId();
        /**当前操作人员为总部人员，则插入总部科目余额表；当前操作人员为加盟店人员，则插入加盟店科目余额表；*/
        // 220201 应付账款
        modelList.add(new BalanceInitModel(enterpriseId, parentId, user.getChainType(), FinanceAccount.PAYMENT_ACCOUNT.getAccountCode(), FinanceAccount.PAYMENT_ACCOUNT.getAccountName(),
                FinanceAccountType.SUPPLIER.getType(), supplier.getId(), supplier.getCode(), supplier.getName()));
        return modelList;
    }

    public static List<BalanceInitModel> buildForBranch(Enterprise branch, Enterprise parent){
        List<BalanceInitModel> modelList = new ArrayList<BalanceInitModel>();
        /**插入总部科目余额表*/
        // 112211 应收缴款
        modelList.add(new BalanceInitModel(parent.getId(), parent.getParentId(), parent.getChainType(), FinanceAccount.RECEIVABLE_PAYMENT.getAccountCode(), FinanceAccount.RECEIVABLE_PAYMENT.getAccountName(),
                FinanceAccountType.PARENT.getType(), branch.getId(), branch.getCode(), branch.getName()));
        return modelList;
    }

    public static List<BalanceInitModel> buildForLeague(Enterprise branch, Enterprise parent, List<GoodsTaxRate> goodsTaxRateList){
        List<BalanceInitModel> modelList = new ArrayList<BalanceInitModel>();
        Long branchId = branch.getId();
        Long parentId = parent.getId();
        /**插入加盟店科目余额表*/
        // 100101 现金-人民币
        modelList.add(new BalanceInitModel(branchId, parentId, branch.getChainType(), FinanceAccount.CASH_RMB.getAccountCode(), FinanceAccount.CASH_RMB.getAccountName(),
                FinanceAccountType.LEAGUE.getType(),null,"", ""));
        // 100201 银行存款-人民币
        modelList.add(new BalanceInitModel(branchId, parentId, branch.getChainType(), FinanceAccount.BANK_DEPOSIT_RMB.getAccountCode(), FinanceAccount.BANK_DEPOSIT_RMB.getAccountName(),
                FinanceAccountType.LEAGUE.getType(),null, "", ""));
        // 101211 其它货币资金
        modelList.add(new BalanceInitModel(branchId, parentId, branch.getChainType(), FinanceAccount.OTHER_MONETARY_FUNDS.getAccountCode(), FinanceAccount.OTHER_MONETARY_FUNDS.getAccountName(),
                FinanceAccountType.LEAGUE.getType(),null, "", ""));
        // 112211 应收缴款
        modelList.add(new BalanceInitModel(branchId, parentId, branch.getChainType(), FinanceAccount.RECEIVABLE_PAYMENT.getAccountCode(), FinanceAccount.RECEIVABLE_PAYMENT.getAccountName(),
                FinanceAccountType.LEAGUE.getType(), branch.getId(), branch.getCode(), branch.getName()));
        // 140101 材料采购
        modelList.add(new BalanceInitModel(branchId, parentId, branch.getChainType(), FinanceAccount.MATERIAL_PURCHASE.getAccountCode(), FinanceAccount.MATERIAL_PURCHASE.getAccountName(),
                FinanceAccountType.LEAGUE.getType(),null, "", ""));
        // 140601 库存商品
        modelList.add(new BalanceInitModel(branchId, parentId, branch.getChainType(), FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.INVENTORY_GOODS.getAccountName(),
                FinanceAccountType.LEAGUE.getType(),null, "", null));
        // 220201 应付账款
        modelList.add(new BalanceInitModel(branchId, parentId, branch.getChainType(), FinanceAccount.PAYMENT_ACCOUNT.getAccountCode(), FinanceAccount.PAYMENT_ACCOUNT.getAccountName(),
                FinanceAccountType.PARENT.getType(),parent.getId(), parent.getCode(), parent.getName()));
        // 22210101 进项税额;22210105 销项税额
        buildBalanceInitModelForTax(branchId, parentId, branch.getChainType(), goodsTaxRateList, modelList);
        // 640104 库存差异-收益
        modelList.add(new BalanceInitModel(branchId, parentId, branch.getChainType(), FinanceAccount.INVENTORY_DIFF_PROFIT.getAccountCode(), FinanceAccount.INVENTORY_DIFF_PROFIT.getAccountName(),
                FinanceAccountType.LEAGUE.getType(),null, "", ""));
        // 640105 库存差异-损失
        modelList.add(new BalanceInitModel(branchId, parentId, branch.getChainType(), FinanceAccount.INVENTORY_DIFF_LOSS.getAccountCode(), FinanceAccount.INVENTORY_DIFF_LOSS.getAccountName(),
                FinanceAccountType.LEAGUE.getType(),null, "", ""));
        // 640101 销售成本
        modelList.add(new BalanceInitModel(branchId, parentId, branch.getChainType(), FinanceAccount.SALE_COST.getAccountCode(), FinanceAccount.SALE_COST.getAccountName(),
                FinanceAccountType.LEAGUE.getType(),null, "", ""));
        // 666101 主营业务收入
        modelList.add(new BalanceInitModel(branchId, parentId, branch.getChainType(), FinanceAccount.MAIN_BUSINESS_INCOME.getAccountCode(), FinanceAccount.MAIN_BUSINESS_INCOME.getAccountName(),
                FinanceAccountType.LEAGUE.getType(),null, "", ""));
        // 660305 财务费用-现金折扣
        modelList.add(new BalanceInitModel(branchId, parentId, branch.getChainType(), FinanceAccount.CASH_DISCOUNT.getAccountCode(), FinanceAccount.CASH_DISCOUNT.getAccountName(),
                FinanceAccountType.LEAGUE.getType(),null, "", ""));
        // 690101 以前年度损溢调整
        modelList.add(new BalanceInitModel(branchId, parentId, branch.getChainType(), FinanceAccount.LOSS_PROFIT_ADJUST.getAccountCode(), FinanceAccount.LOSS_PROFIT_ADJUST.getAccountName(),
                FinanceAccountType.LEAGUE.getType(),null, "", ""));
        /**插入总部科目余额表*/
        // 112201 应收账款
        modelList.add(new BalanceInitModel(parent.getId(), parent.getParentId(), parent.getChainType(), FinanceAccount.RECEIVABLE_ACCOUNT.getAccountCode(), FinanceAccount.RECEIVABLE_ACCOUNT.getAccountName(),
                FinanceAccountType.LEAGUE.getType(),branch.getId(), branch.getCode(), branch.getName()));
        return modelList;
    }

    public static List<BalanceInitModel> buildForParent(Long enterpriseId, Long parentId, List<GoodsTaxRate> goodsTaxRateList){
        List<BalanceInitModel> modelList = new ArrayList<BalanceInitModel>();
        /**插入总部科目余额表*/
        // 100101 现金-人民币
        modelList.add(new BalanceInitModel(enterpriseId, parentId, ChainType.Headquarters.getCode(), FinanceAccount.CASH_RMB.getAccountCode(), FinanceAccount.CASH_RMB.getAccountName(),
                FinanceAccountType.PARENT.getType(),null, "", ""));
        // 100201 银行存款-人民币
        modelList.add(new BalanceInitModel(enterpriseId, parentId, ChainType.Headquarters.getCode(), FinanceAccount.BANK_DEPOSIT_RMB.getAccountCode(), FinanceAccount.BANK_DEPOSIT_RMB.getAccountName(),
                FinanceAccountType.PARENT.getType(),null, "", ""));
        // 101211 其它货币资金
        modelList.add(new BalanceInitModel(enterpriseId, parentId, ChainType.Headquarters.getCode(), FinanceAccount.OTHER_MONETARY_FUNDS.getAccountCode(), FinanceAccount.OTHER_MONETARY_FUNDS.getAccountName(),
                FinanceAccountType.PARENT.getType(),null, "", ""));
        // 140101 材料采购
        modelList.add(new BalanceInitModel(enterpriseId, parentId, ChainType.Headquarters.getCode(), FinanceAccount.MATERIAL_PURCHASE.getAccountCode(), FinanceAccount.MATERIAL_PURCHASE.getAccountName(),
                FinanceAccountType.PARENT.getType(),null, "", ""));
        // 140601 库存商品
        modelList.add(new BalanceInitModel(enterpriseId, parentId, ChainType.Headquarters.getCode(), FinanceAccount.INVENTORY_GOODS.getAccountCode(), FinanceAccount.INVENTORY_GOODS.getAccountName(),
                FinanceAccountType.PARENT.getType(),null, "", null));
        // 22210101 进项税额;22210105 销项税额
        buildBalanceInitModelForTax(enterpriseId, parentId, ChainType.Headquarters.getCode(), goodsTaxRateList, modelList);
        // 640104 库存差异-收益
        modelList.add(new BalanceInitModel(enterpriseId, parentId, ChainType.Headquarters.getCode(), FinanceAccount.INVENTORY_DIFF_PROFIT.getAccountCode(), FinanceAccount.INVENTORY_DIFF_PROFIT.getAccountName(),
                FinanceAccountType.PARENT.getType(),null, "", ""));
        // 640105 库存差异-损失
        modelList.add(new BalanceInitModel(enterpriseId, parentId, ChainType.Headquarters.getCode(), FinanceAccount.INVENTORY_DIFF_LOSS.getAccountCode(), FinanceAccount.INVENTORY_DIFF_LOSS.getAccountName(),
                FinanceAccountType.PARENT.getType(),null, "", ""));
        // 640101 销售成本
        modelList.add(new BalanceInitModel(enterpriseId, parentId, ChainType.Headquarters.getCode(), FinanceAccount.SALE_COST.getAccountCode(), FinanceAccount.SALE_COST.getAccountName(),
                FinanceAccountType.PARENT.getType(),null,"", ""));
        // 666101 主营业务收入
        modelList.add(new BalanceInitModel(enterpriseId, parentId, ChainType.Headquarters.getCode(), FinanceAccount.MAIN_BUSINESS_INCOME.getAccountCode(), FinanceAccount.MAIN_BUSINESS_INCOME.getAccountName(),
                FinanceAccountType.PARENT.getType(),null, "", ""));
        // 660305 财务费用-现金折扣
        modelList.add(new BalanceInitModel(enterpriseId, parentId, ChainType.Headquarters.getCode(), FinanceAccount.CASH_DISCOUNT.getAccountCode(), FinanceAccount.CASH_DISCOUNT.getAccountName(),
                FinanceAccountType.PARENT.getType(),null, "", ""));
        // 690101 以前年度损溢调整
        modelList.add(new BalanceInitModel(enterpriseId, parentId, ChainType.Headquarters.getCode(), FinanceAccount.LOSS_PROFIT_ADJUST.getAccountCode(), FinanceAccount.LOSS_PROFIT_ADJUST.getAccountName(),
                FinanceAccountType.PARENT.getType(),null, "", ""));
        return modelList;
    }


    private static void buildBalanceInitModelForTax(Long enterpriseId, Long parentId, Integer chainType, List<GoodsTaxRate> goodsTaxRateList, List<BalanceInitModel> modelList) {
        Long subAccountId;
        String subAccountCode;
        String subAccountName;
        if(CollectionUtils.isNotEmpty(goodsTaxRateList)){
            for(GoodsTaxRate goodsTaxRate:goodsTaxRateList){
                subAccountId = goodsTaxRate.getId();
                subAccountCode = goodsTaxRate.getCode();
                subAccountName = String.valueOf(goodsTaxRate.getTaxRate());

                modelList.add(new BalanceInitModel(enterpriseId, parentId, chainType, FinanceAccount.PURCHASE_TAX_AMOUNT.getAccountCode(), FinanceAccount.PURCHASE_TAX_AMOUNT.getAccountName(),
                        FinanceAccountType.TAX.getType(),subAccountId, subAccountCode, subAccountName));
                modelList.add(new BalanceInitModel(enterpriseId, parentId, chainType, FinanceAccount.SALE_TAX_AMOUNT.getAccountCode(), FinanceAccount.SALE_TAX_AMOUNT.getAccountName(),
                        FinanceAccountType.TAX.getType(),subAccountId, subAccountCode, subAccountName));
            }
        }
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

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
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
}