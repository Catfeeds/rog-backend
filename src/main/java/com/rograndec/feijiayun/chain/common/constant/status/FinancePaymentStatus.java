package com.rograndec.feijiayun.chain.common.constant.status;

/**
 * 财务 付款状态 0-待付款；1-部分付款；2-已付款；3-已冲销）
 */
public class FinancePaymentStatus {
	public final static int PRE_PAYMENT = 0;
	public final static int PARTIAL_PAYMENT = 1;
	public final static int PAID = 2;
	public final static int WARITE_OF = 3;

	/**
	 * 返回状态描述
	 *
	 * @param status
	 * @return
	 */
	public static String getStatusDesc(int status){
		switch(status){
			case PRE_PAYMENT:
				return "待付款";
			case PARTIAL_PAYMENT:
				return "部分付款";
			case PAID:
				return "已付款";
			case WARITE_OF:
				return "已冲销";
		}
		return "未知类型的状态";
	}
    
}
