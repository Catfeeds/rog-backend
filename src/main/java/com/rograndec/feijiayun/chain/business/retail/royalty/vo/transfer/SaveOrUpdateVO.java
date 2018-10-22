package com.rograndec.feijiayun.chain.business.retail.royalty.vo.transfer;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.retail.royalty.vo.SaveOrUpdateSaleRoyaltyVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * Created by zhaiwei on 2017/9/25.
 */
public class SaveOrUpdateVO {

    private UserVO userVO;
    private SaveOrUpdateSaleRoyaltyVO saveOrUpdateSaleRoyaltyVO;
    private String code;
    private User royaltyMan;

    /**
     * 企业
     */
    private Enterprise enterprise;

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public SaveOrUpdateSaleRoyaltyVO getSaveOrUpdateSaleRoyaltyVO() {
        return saveOrUpdateSaleRoyaltyVO;
    }

    public void setSaveOrUpdateSaleRoyaltyVO(SaveOrUpdateSaleRoyaltyVO saveOrUpdateSaleRoyaltyVO) {
        this.saveOrUpdateSaleRoyaltyVO = saveOrUpdateSaleRoyaltyVO;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getRoyaltyMan() {
        return royaltyMan;
    }

    public void setRoyaltyMan(User royaltyMan) {
        this.royaltyMan = royaltyMan;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }
}
