package com.rograndec.feijiayun.chain.business.distr.parent.vo.transfer;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnCheckDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnInDetailFormVO;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

public class DistrReturnInDetailParam {

    private UserVO userVO;

    private DistrReturnInDetailFormVO distrReturnInFormVO;

    private List<DistrReturnCheckDetail> distrReturnCheckDetails;

    private List<GoodsTaxRate> goodsTaxRates;

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public DistrReturnInDetailFormVO getDistrReturnInFormVO() {
        return distrReturnInFormVO;
    }

    public void setDistrReturnInFormVO(DistrReturnInDetailFormVO distrReturnInFormVO) {
        this.distrReturnInFormVO = distrReturnInFormVO;
    }

    public List<DistrReturnCheckDetail> getDistrReturnCheckDetails() {
        return distrReturnCheckDetails;
    }

    public void setDistrReturnCheckDetails(List<DistrReturnCheckDetail> distrReturnCheckDetails) {
        this.distrReturnCheckDetails = distrReturnCheckDetails;
    }

    public List<GoodsTaxRate> getGoodsTaxRates() {
        return goodsTaxRates;
    }

    public void setGoodsTaxRates(List<GoodsTaxRate> goodsTaxRates) {
        this.goodsTaxRates = goodsTaxRates;
    }

}
