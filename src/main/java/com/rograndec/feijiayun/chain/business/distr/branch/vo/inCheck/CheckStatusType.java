package com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck;

import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：
 * Created by ST on 2017/10/10 12:49
 */

public class CheckStatusType {



    @ApiModelProperty("0-查询待入库；1-已验收之后的所有状态")
    private Integer type;

    /**
     * 32-待入库（已验收）
     */
    private int WAIT_IN = 32;

    /**
     * 33-已完成
     */
    private   int FINISHED = 33;
    /**
     * 34-已取消
     */
    private int CANCELED = 34;
    /**
     * 41-待开票【已完成（已入库）】
     */
    private int WAIT_BILL = 41;
    /**
     * 42-待支付（已开票）
     */
    private  int WAIT_PAY = 42;
    /**
     * 43-已支付
     */
    private  int PAYED = 43;


    public CheckStatusType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public int getFINISHED() {
        return FINISHED;
    }

    public void setFINISHED(int FINISHED) {
        this.FINISHED = FINISHED;
    }

    public int getCANCELED() {
        return CANCELED;
    }

    public void setCANCELED(int CANCELED) {
        this.CANCELED = CANCELED;
    }

    public int getWAIT_BILL() {
        return WAIT_BILL;
    }

    public void setWAIT_BILL(int WAIT_BILL) {
        this.WAIT_BILL = WAIT_BILL;
    }

    public int getWAIT_PAY() {
        return WAIT_PAY;
    }

    public void setWAIT_PAY(int WAIT_PAY) {
        this.WAIT_PAY = WAIT_PAY;
    }

    public int getPAYED() {
        return PAYED;
    }

    public void setPAYED(int PAYED) {
        this.PAYED = PAYED;
    }

    public int getWAIT_IN() {
        return WAIT_IN;
    }

    public void setWAIT_IN(int WAIT_IN) {
        this.WAIT_IN = WAIT_IN;
    }
}