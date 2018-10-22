package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.entity.VerificationFormDetail;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.entity.VerificationFormItem;

import java.util.List;

public class VerificationFormItemVO extends VerificationFormItem{

    public List<VerificationFormDetailVO> getVerificationFormDetailList() {
        return verificationFormDetailList;
    }

    public void setVerificationFormDetailList(List<VerificationFormDetailVO> verificationFormDetailList) {
        this.verificationFormDetailList = verificationFormDetailList;
    }

    public List<VerificationFormDetailVO> verificationFormDetailList;

}
