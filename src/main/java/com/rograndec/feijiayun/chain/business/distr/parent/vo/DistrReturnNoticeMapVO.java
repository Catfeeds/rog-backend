package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNotice;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNoticeDetail;

import java.util.List;

public class DistrReturnNoticeMapVO {

    private DistrReturnNotice distrReturnNotice;

    private List<DistrReturnNoticeDetail> distrReturnNoticeDetails;

    private Long receiverId;
    private Long secondReceiverId;

    public DistrReturnNotice getDistrReturnNotice() {
        return distrReturnNotice;
    }

    public void setDistrReturnNotice(DistrReturnNotice distrReturnNotice) {
        this.distrReturnNotice = distrReturnNotice;
    }

    public List<DistrReturnNoticeDetail> getDistrReturnNoticeDetails() {
        return distrReturnNoticeDetails;
    }

    public void setDistrReturnNoticeDetails(List<DistrReturnNoticeDetail> distrReturnNoticeDetails) {
        this.distrReturnNoticeDetails = distrReturnNoticeDetails;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getSecondReceiverId() {
        return secondReceiverId;
    }

    public void setSecondReceiverId(Long secondReceiverId) {
        this.secondReceiverId = secondReceiverId;
    }
}
