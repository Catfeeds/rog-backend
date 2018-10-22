package com.rograndec.feijiayun.chain.common.constant.status;

/**
 * 财务 提成管理状态（1-已完成；2-已冲销）
 */
public class SaleCommissionStatus {
	public final static int OVER = 1;
	public final static int WARITE_OF = 2;
	/**
	 * 返回状态描述
	 *
	 * @param status
	 * @return
	 */
	public static String getStatusDesc(Integer status){
		if(null == status) return "";
		switch(status){
			case OVER:
				return "已完成";
			case WARITE_OF:
				return "已冲销";
		}
		return "未知类型的状态";
	}
    
    
}
