package com.rograndec.feijiayun.chain.common.constant.status;


/**
 * 
 * 药品养护状态常量
 * 
 * @author lizhongyi
 *
 */
public class GoodsMaintanceStatus {

	/**
	 * 31-计划中
	 */
	public final int IN_PLAN = 31;
	/**
	 * 32-养护中
	 */
	public final int IN_MAINTANCE = 32;
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
			case IN_MAINTANCE:
				return "养护中";
			case FINISHED:
				return "已完成";
			case CANCELED:
				return "已取消";
		}
		return "未知类型的状态";
	}
	
}
