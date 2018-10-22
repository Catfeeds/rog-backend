package com.rograndec.feijiayun.chain.business.distr.branch.vo.transfer;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReturnOutDetailFormVO;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

public class DistrInReturnOutDetailParam {

    private UserVO userVO;

    private DistrInReturnOutDetailFormVO distrInReturnOutDetailFormVO;

    private List<DistrInReturnDetail> distrInReturnDetails;

    private List<GoodsTaxRate> goodsTaxRates;


    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public DistrInReturnOutDetailFormVO getDistrInReturnOutDetailFormVO() {
        return distrInReturnOutDetailFormVO;
    }

    public void setDistrInReturnOutDetailFormVO(DistrInReturnOutDetailFormVO distrInReturnOutDetailFormVO) {
        this.distrInReturnOutDetailFormVO = distrInReturnOutDetailFormVO;
    }

    public List<DistrInReturnDetail> getDistrInReturnDetails() {
        return distrInReturnDetails;
    }

    public void setDistrInReturnDetails(List<DistrInReturnDetail> distrInReturnDetails) {
        this.distrInReturnDetails = distrInReturnDetails;
    }

    public List<GoodsTaxRate> getGoodsTaxRates() {
        return goodsTaxRates;
    }

    public void setGoodsTaxRates(List<GoodsTaxRate> goodsTaxRates) {
        this.goodsTaxRates = goodsTaxRates;
    }

}
