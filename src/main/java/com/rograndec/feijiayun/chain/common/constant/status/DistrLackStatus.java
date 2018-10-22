package com.rograndec.feijiayun.chain.common.constant.status;

/**
 * 
 * 总部－缺配单状态
 * @author lizhongyi
 *
 */
public class DistrLackStatus {

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
			case WAIT_DISTR:
				return "待出库";
			case FINISHED:
				return "已配货";
			case CANCELED:
				return "已取消";
		}
		return "未知类型的状态";
	}
	
}
