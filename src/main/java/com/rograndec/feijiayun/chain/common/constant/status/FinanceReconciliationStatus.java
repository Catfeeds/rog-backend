package com.rograndec.feijiayun.chain.common.constant.status;

/**
 * 财务 对账状态（0-待对账；1-部分对账；2-已对账；3-已冲销）
 */
public class FinanceReconciliationStatus {
	public final static int PRE_RECONCILIATION = 0;
	public final static int PARTIAL_RECONCILIATION = 1;
	public final static int RECONCILED = 2;
	public final static int WARITE_OF = 3;
	/**
	 * 返回状态描述
	 *
	 * @param status
	 * @return
	 */
	public static String getStatusDesc(int status){
		switch(status){
			case PRE_RECONCILIATION:
				return "待对账";
			case PARTIAL_RECONCILIATION:
				return "部分对账";
			case RECONCILED:
				return "已对账";
			case WARITE_OF:
				return "已冲销";
		}
		return "未知类型的状态";
	}
    
    
}
