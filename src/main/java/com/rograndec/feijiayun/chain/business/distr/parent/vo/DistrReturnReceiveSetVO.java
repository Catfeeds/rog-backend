package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnCheck;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnCheckLot;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnReceiveDetail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * saas_distr_return_receive_detail
 * 
 * 
 * @author Asze
 * 
 * 2017-10-08
 */
public class DistrReturnReceiveSetVO implements Serializable {


    private DistrReturnCheck distrReturnCheck;

    private List<DistrReturnReceiveDetail> distrReturnReceiveDetails = new ArrayList<>();

    private List<DistrReturnCheckLot> distrReturnCheckLots = new ArrayList<>();

    public DistrReturnCheck getDistrReturnCheck() {
        return distrReturnCheck;
    }

    public void setDistrReturnCheck(DistrReturnCheck distrReturnCheck) {
        this.distrReturnCheck = distrReturnCheck;
    }

    public List<DistrReturnReceiveDetail> getDistrReturnReceiveDetails() {
        return distrReturnReceiveDetails;
    }

    public void setDistrReturnReceiveDetails(List<DistrReturnReceiveDetail> distrReturnReceiveDetails) {
        this.distrReturnReceiveDetails = distrReturnReceiveDetails;
    }

    public List<DistrReturnCheckLot> getDistrReturnCheckLots() {
        return distrReturnCheckLots;
    }

    public void setDistrReturnCheckLots(List<DistrReturnCheckLot> distrReturnCheckLots) {
        this.distrReturnCheckLots = distrReturnCheckLots;
    }
}