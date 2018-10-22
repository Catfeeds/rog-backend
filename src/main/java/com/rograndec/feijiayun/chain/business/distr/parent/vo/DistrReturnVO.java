package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturn;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnDetail;

import java.io.Serializable;
import java.util.List;

public class DistrReturnVO implements Serializable {

    private DistrInReturn distrInReturn;
    private List<DistrInReturnDetail> paramList;

    public DistrInReturn getDistrInReturn() {
        return distrInReturn;
    }

    public void setDistrInReturn(DistrInReturn distrInReturn) {
        this.distrInReturn = distrInReturn;
    }

    public List<DistrInReturnDetail> getParamList() {
        return paramList;
    }

    public void setParamList(List<DistrInReturnDetail> paramList) {
        this.paramList = paramList;
    }
}
