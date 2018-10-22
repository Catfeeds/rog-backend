package com.rograndec.feijiayun.chain.common.constant.status;

/**
 * 
 * 总部－配货出库单状态
 * @author lizhongyi
 *
 */
public class DistrOutStatus {
	/**
	 * 31-待出库
	 */
	public final static int WAIT_OUT = 31;
	/**
	 * 32-待复核
	 */
	public final static int WAIT_AUDIT = 32;
	/**
	 * 33-已完成
	 */
	public final static int FINISHED = 33;
	/**
	 * 41-复核驳回
	 */
	public final static int AUDIT_REBUT= 41;
	/**
	 * 42-待开票
	 */
	public final static int WAIT_BILL = 42;
	/**
	 * 43-部分开票
	 */
	public final static int PART_BILL = 43;

	/**
	 * 返回状态描述
	 * 
	 * @param status
	 * @return
	 */
	public static String getStatusDesc(int status){
		switch(status){
			case WAIT_OUT:
				return "待出库";
			case WAIT_AUDIT:
				return "待复核";
			case FINISHED:
				return "已完成";
			case AUDIT_REBUT:
				return "复核驳回";
			case WAIT_BILL:
				return "待开票";
			case PART_BILL:
				return "部分开票";
		}
		return "未知类型的状态";
	}
	
}
