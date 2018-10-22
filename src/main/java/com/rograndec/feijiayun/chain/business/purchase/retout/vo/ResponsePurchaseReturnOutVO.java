package com.rograndec.feijiayun.chain.business.purchase.retout.vo;

import com.rograndec.feijiayun.chain.business.purchase.ret.vo.ResponsePurchaseReturnVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 功能描述：
 * Created by ST on 2017/9/14 19:34
 */

public class ResponsePurchaseReturnOutVO extends ResponsePurchaseReturnVO{

    /**
     * 购进退出出库日期
     */
    @ApiModelProperty(value = "购进退出出库日期")
    private Date outDate;

    @ApiModelProperty(value = "复核人员id")
    private Long checkerId;

    @ApiModelProperty(value = "复核人员名称")
    private String checkerName;

    @ApiModelProperty(value = "复核理由")
    private String checkReason;

    @ApiModelProperty(value = "流通监管码")
    private String flowCode;

    public Long getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getCheckReason() {
        return checkReason;
    }

    public void setCheckReason(String checkReason) {
        this.checkReason = checkReason;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }
}