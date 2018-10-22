package com.rograndec.feijiayun.chain.common.constant.status;


/**
 * 
 * 药品陈列检查态常量
 * 
 * @author lizhongyi
 *
 */
public class GoodsDisplayCheckStatus {

	/**
	 * 31-计划中
	 */
	public final int IN_PLAN = 31;
	/**
	 * 32-检查中
	 */
	public final int IN_CHECK = 32;
	/**
	 * 33-已完成
	 */
	public final int FINISHED = 33;
	/**
	 * 34-已取消
	 */
	public final int CANCELED = 34; 
	
	/**
	 * 返回状态描述
	 * 
	 * @param status
	 * @return
	 */
	public String getStatusDesc(int status){
		switch(status){
			case IN_PLAN: 
				return "计划中";
			case IN_CHECK:
				return "检查中";
			case FINISHED:
				return "已完成";
			case CANCELED:
				return "已取消";
		}
		return "未知类型的状态";
	}
	
}
