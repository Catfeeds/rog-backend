package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.entity.VerificationForm;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.entity.VerificationFormItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel
public class VerificationFormVO extends VerificationForm {

    public List<VerificationFormItemVO> getVerificationFormItemVOList() {
        return verificationFormItemVOList;
    }

    public void setVerificationFormItemVOList(List<VerificationFormItemVO> verificationFormItemVOList) {
        this.verificationFormItemVOList = verificationFormItemVOList;
    }

    public List<VerificationFormItemVO> verificationFormItemVOList;


}