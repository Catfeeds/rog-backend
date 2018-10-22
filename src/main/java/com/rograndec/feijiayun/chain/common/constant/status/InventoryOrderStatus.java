package com.rograndec.feijiayun.chain.common.constant.status;

/**
 * 功能描述：
 * Created by ST on 2017/10/6 13:37
 */

public class InventoryOrderStatus {

    /**
     * 待登记
     */
    public  final int WAIT_REGISTER = 0;
    /**
     * 待处理
     */
    public  final int PENDING = 1;
    /**
     * 待过账
     */
    public  final int WAIT_POSTING = 2;
    /**
     * 3-已完成
     */
    public  final int FINISHED = 3;

    /**
     * 4-已取消
     */
    public  final int CANCELED = 4;

    /**
     * 5-待审核 (处理完之后变成待审核)
     */
    public  final int WAIT_AUDIT = 5;
    /**
     * 6-审核拒绝
     */
    public  final int AUDIT_REJECT= 6;

}