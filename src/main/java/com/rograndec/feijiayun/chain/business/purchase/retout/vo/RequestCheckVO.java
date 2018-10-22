package com.rograndec.feijiayun.chain.business.purchase.retout.vo;

import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOut;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：
 * Created by ST on 2017/9/18 17:58
 */

public class RequestCheckVO {

    @ApiModelProperty(value = "购进退出出库id")
    private Long id;

    @ApiModelProperty(value = "复核人员id")
    private Long checkId;

    @ApiModelProperty(value = "复核意见")
    private String checkReason;

    @ApiModelProperty(value = "复核结果（0/驳回；1/同意）")
    private Integer checkStatus;


    public static RequestCheckVO getRequestCheckVO(PurchaseReturnOut distrInReturnOut){
        RequestCheckVO requestCheckVO = new RequestCheckVO();
        requestCheckVO.setId(distrInReturnOut.getId());
        requestCheckVO.setCheckId(distrInReturnOut.getCreaterId());
        requestCheckVO.setCheckReason("同意");
        requestCheckVO.setCheckStatus(1);
        return requestCheckVO;
    }

    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }

    public String getCheckReason() {
        return checkReason;
    }

    public void setCheckReason(String checkReason) {
        this.checkReason = checkReason;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}