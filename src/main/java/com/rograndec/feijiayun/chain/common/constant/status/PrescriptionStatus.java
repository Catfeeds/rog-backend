package com.rograndec.feijiayun.chain.common.constant.status;


/**
 * 
 * 处方管理状态常量
 * 
 * @author lizhongyi
 *
 */
public  class PrescriptionStatus {

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
	 * 31-待调配
	 */
	public final static int WAIT_MIX = 31;
	/**
	 * 32-已调配
	 */
	public final static int MIXED = 32;
	/**
	 * 33-已完成
	 */
	public final static int FINISHED = 33;
	/**
	 * 34-已取消
	 */
	public final static int CANCELED = 34;


	
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
			case WAIT_MIX:
				return "待调配";
			case MIXED:
				return "已调配";
			case FINISHED:
				return "已完成";
			case CANCELED:
				return "已取消";
		}
		return "未知类型的状态";
	}
	
}
