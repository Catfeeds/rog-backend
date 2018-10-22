package com.rograndec.feijiayun.chain.common.constant.status;

/**
 * 
 * @ClassName: PurchaseStatus
 * @Description: 单据状态常量 
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月23日 上午11:17:22  
 *
 */
public enum PurchaseStatus {
	PENDING_AUDIT(21, "待审核"),
	AUDITED(22, "审核通过"),
	AUDIT_REJECT(23, "审核拒绝"),
	AUDIT_WITHDRAWAL(24, "审核撤回"),
	
	FINISHED(33, "已完成"),
	CANCELED(34, "已取消"),
	CLOSED(35, "关闭"),
	
	PENDING_ORDER(41, "待订购"),
	WAIT_RECEIVE(51, "待收货"),
	RECEIVING(52, "收货中"),
	WAIT_CHECK(61, "待验收"),// 未清状态的收货
	CHECKING(62, "验收中"),// 草稿状态的验收
	WAIT_STORAGE(71, "待入库"), // 未清状态的验收,验收单显示已验收，入库单显示待入库
	IN_STORAGE(72, "入库中"),// 草稿状态的入库
	WAIT_OUT(81, "待出库"),
	OUT_ING(82, "出库中"),
	WAIT_AUDIT(83, "待复核"),
	WAIT_BILL(91, "待开票"),
	WAIT_PAY(92, "待支付"),
	PART_BILL(93, "部分开票");

	private Integer status;
	private String statusName;
	
	PurchaseStatus(Integer status, String statusName) {
		this.status = status;
		this.statusName = statusName;
	}

	public static int getCode(String name) {
		for (PurchaseStatus c : PurchaseStatus.values()) {
			if (name.equals(c.getStatusName())) {
				return c.getStatus();
			}
		}
		return -1;
	}

	public static String getName(int status) {
		for (PurchaseStatus c : PurchaseStatus.values()) {
			if (c.getStatus().equals(status)) {
				return c.getStatusName();
			}
		}
		return null;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	

}
