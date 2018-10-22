package com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.vo;

import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.entity.FinancePaymentModifyRecord;

import java.io.Serializable;

public class PayUpdateRecordWithBLOBs extends FinancePaymentModifyRecord implements Serializable{

    /**
     * 原内容
     */
    private String oldContent;

    /**
     * 新内容
     */
    private String newContent;

    /**
     * saas_user_modify_record
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String getOldContent() {
        return oldContent;
    }

    @Override
    public void setOldContent(String oldContent) {
        this.oldContent = oldContent;
    }

    @Override
    public String getNewContent() {
        return newContent;
    }

    @Override
    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }
}
