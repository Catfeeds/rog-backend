package com.rograndec.feijiayun.chain.business.purchase.ret.vo;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierSaler;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.math.BigDecimal;

/**
 * Created by zhaiwei on 2017/9/20.
 */
public class SaveOrUpdateBzVO {

    private UserVO userVO;
    private PurchaseReturnSaveOrUpdateVO purchaseReturnVO;
    private String code;
    private PurchaseInStorage purchaseInStorage;
    private Supplier supplier;
    private SupplierSaler supplierUser;
    private User user;

    /**
     * 不含税利润合计
     */
    private BigDecimal notaxProfitTotal = new BigDecimal(0);

    /**
     * 利润率
     */
    private BigDecimal profitRate = new BigDecimal(0);

    /**
     * 不含税利润率
     */
    private BigDecimal notaxProfitRate = new BigDecimal(0);
    /**
     * 利润合计
     * @param returnDetails
     * @return
     */
    private BigDecimal profitTotal = new BigDecimal(0);


    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public PurchaseReturnSaveOrUpdateVO getPurchaseReturnVO() {
        return purchaseReturnVO;
    }

    public void setPurchaseReturnVO(PurchaseReturnSaveOrUpdateVO purchaseReturnVO) {
        this.purchaseReturnVO = purchaseReturnVO;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PurchaseInStorage getPurchaseInStorage() {
        return purchaseInStorage;
    }

    public void setPurchaseInStorage(PurchaseInStorage purchaseInStorage) {
        this.purchaseInStorage = purchaseInStorage;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public SupplierSaler getSupplierUser() {
        return supplierUser;
    }

    public void setSupplierUser(SupplierSaler supplierUser) {
        this.supplierUser = supplierUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getNotaxProfitTotal() {
        return notaxProfitTotal;
    }

    public void setNotaxProfitTotal(BigDecimal notaxProfitTotal) {
        this.notaxProfitTotal = notaxProfitTotal;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public BigDecimal getNotaxProfitRate() {
        return notaxProfitRate;
    }

    public void setNotaxProfitRate(BigDecimal notaxProfitRate) {
        this.notaxProfitRate = notaxProfitRate;
    }

    public BigDecimal getProfitTotal() {
        return profitTotal;
    }

    public void setProfitTotal(BigDecimal profitTotal) {
        this.profitTotal = profitTotal;
    }
}
