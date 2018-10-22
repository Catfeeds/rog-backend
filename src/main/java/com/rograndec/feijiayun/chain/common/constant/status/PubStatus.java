
package com.rograndec.feijiayun.chain.common.constant.status;

/**
 * 
 * 单据状态常量
 * 
 * @author lizhongyi
 *
 */
public class PubStatus {


	/**
	 * 处方管理状态变量
	 */
	public static PrescriptionStatus prescriptionStatus = new PrescriptionStatus();
	/**
	 * 药品养护状态
	 */
	public static GoodsMaintanceStatus goodsMaintanceStatus = new GoodsMaintanceStatus();
	/**
	 * 药品陈列检查状态
	 */
	public static GoodsDisplayCheckStatus goodsDisplayCheckStatus = new GoodsDisplayCheckStatus();
	/**
	 * 盘点登记状态
	 */
	public static InventoryOrderStatus inventoryOrderStatus = new InventoryOrderStatus();
	
	/**
	 * 总部－配货单状态
	 */
	public static DistrSendStatus distrSendStatus = new DistrSendStatus();
	/**
	 * 总部－配货出库单状态
	 */
	public static DistrOutStatus distrOutStatus = new DistrOutStatus();
	/**
	 * 总部－缺配单状态
	 */
	public static DistrLackStatus distrLackStatus = new DistrLackStatus();
	/**
	 * 总部－配后退回状态（配后退回、配后退回收货、配后退回验收、配后退回入库）
	 */
	public static DistrReturnStatus distrReturnNoticeStatus = new DistrReturnStatus();
	/**
	 * 分店－要货计划单状态
	 */
	public static DistrReqPlanStatus distrReqPlanStatus = new DistrReqPlanStatus();
	/**
	 * 分店－配进状态（配进订单、配进收货、配进验收、配进入库）
	 */
	public static DistrInStatus distrInStatus = new DistrInStatus();
	/**
	 * 分店－配进退出状态（配进退出、配进退出出库）
	 */
	public static DistrInReturnStatus distrInReturnStatus = new DistrInReturnStatus();
	
}
