package com.rograndec.feijiayun.chain.common.constant.status;

/**
 * 
 * 分店－要货计划单状态
 * @author lizhongyi
 *
 */
public class DistrReqPlanStatus {

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
	 * 31-待配货
	 */
	public final static int WAIT_DISTR = 31;
	/**
	 * 33-已配货
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
			case WAIT_DISTR:
				return "待配货";
			case FINISHED:
				return "已配货";
			case CANCELED:
				return "已取消";
		}
		return "未知类型的状态";
	}
	
}
