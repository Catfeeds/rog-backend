package com.rograndec.feijiayun.chain.common.constant.status;

/**
 * 
 * 总部－配后退回通知单状态
 * @author lizhongyi
 *
 */
public class DistrReturnStatus {
	
	/**
	 * 30-待收货
	 */
	public final static int WAIT_RECEIVE = 30;
	/**
	 * 31-待验收（已收货）
	 */
	public final static int WAIT_CHECK = 31;
	/**
	 * 32-待入库（已验收）
	 */
	public final static int WAIT_IN = 32;
	/**
	 * 33-已完成
	 */
	public final static int FINISHED = 33;
	/**
	 * 41-待开票
	 */
	public final static int WAIT_BILL = 42;
	/**
	 * 42-部分开票
	 */
	public final static int PART_BILL = 43;
	/**
	 * 51-待配货
	 */
	public final static int WAIT_SEND = 51;

	/**
	 * 返回状态描述
	 * 
	 * @param status
	 * @return
	 */
	public static String getStatusDesc(int status){
		switch(status){
			case WAIT_RECEIVE:
				return "待收货";
			case WAIT_CHECK:
				return "待验收";
			case WAIT_IN:
				return "待入库";
			case WAIT_SEND:
				return "待配货";
			case WAIT_BILL:
				return "待开票";
			case PART_BILL:
				return "部分开票";
			case FINISHED:
				return "已完成";
		}
		return "未知类型的状态";
	}
	
}
