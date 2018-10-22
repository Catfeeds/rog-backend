package com.rograndec.feijiayun.chain.common.constant.status;

/**
 * 
 * 商品销毁状态
 * @author 孙帮祥
 *
 */
public class GoodsDestroyStatus {

	/**
	 * 21-待审核
	 */
	public final static int PENDING_AUDIT = 21;
	/**
	 * 23-审核驳回
	 */
	public final static int AUDIT_REJECT = 23;
	/**
	 * 33-已销毁
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
			case AUDIT_REJECT:
				return "审核驳回";
			case FINISHED:
				return "已销毁";
			case CANCELED:
				return "已取消";
		}
		return "未知类型的状态";
	}
	
}
