package com.rograndec.feijiayun.chain.common.constant.status;

/**
 * 
 * 分店－配进退出状态
 * @author lizhongyi
 *
 */
public class DistrInReturnStatus {

	/**
	 * 21-待审核
	 */
	public final static int PENDING_AUDIT = 21;
	/**
	 * 22-审核通过
	 */
	public final static int AUDITED = 22;
	/**
	 * 23-审核驳回
	 */
	public final static int AUDIT_REJECT = 23;
	/**
	 * 30-待出库
	 */
	public final static int WAIT_OUT = 30;
	/**
	 * 31-出库中
	 */
	public final static int OUTTING = 31;
	/**
	 * 32-待复核
	 */
	public final static int WAIT_AUDIT = 32;
	/**
	 * 33-已完成（已出库）
	 */
	public final static int FINISHED = 33;
	/**
	 * 34-已取消
	 */
	public final static int CANCELED = 34;
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
			case PENDING_AUDIT: 
				return "待审核";
			case AUDITED:
				return "已审核";
			case AUDIT_REJECT:
				return "审核驳回";
			case WAIT_OUT:
				return "待出库";
			case OUTTING:
				return "出库中";
			case WAIT_AUDIT:
				return "待复核";
			case AUDIT_REBUT:
				return "复核驳回";
			case WAIT_BILL:
				return "待开票";
			case PART_BILL:
				return "部分开票";
			case FINISHED:
				return "已完成";
			case CANCELED:
				return "已取消";
		}
		return "未知类型的状态";
	}
	
}
