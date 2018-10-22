package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo;

import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.entity.FinanceReceivableModifyRecord;

import java.io.Serializable;

public class ReceiveUpdateRecordWithBLOBs extends FinanceReceivableModifyRecord implements Serializable{

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
