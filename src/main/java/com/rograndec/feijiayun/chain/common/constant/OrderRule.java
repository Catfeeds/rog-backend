package com.rograndec.feijiayun.chain.common.constant;

/**
 * 
 * @ClassName: OrderRule
 * @Description: 单据规则常量
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月22日 上午11:07:29  
 *
 */
public enum OrderRule {
	// 期初
	OPENING_INVENTORY(1101, "期初库存", "KQ"),
	OPENING_PAYMENT(1102, "期初应付", "WQ"),
	OPENING_RECEIVABLE(1103, "期初应收", "SI"),
	OPENING_TAX(1104, "期初税额", "WT"),
	// 财务
	FINANCE_VOUCHER(2101,"记账凭证","WP"),
	FINAL_SETTLE(2111,"期末结账","WZ"),
	PREPAY_INVOICE(2201,"预付发票","WY"),
	PAYMENT_INVOICE(2202,"应付发票","WU"),
	PAYMENT_VOUCHER(2203,"应付贷项凭证","WD"),
	VERIFICATION_FORM(2204,"实销实结核销","WH"),
	ADVANCE_RECEIVABLE_INVOICE(2301,"预收发票","WO"),
	RECEIVABLE_INVOICE(2302,"应收发票","WK"),
	RECEIVABLE_VOUCHER(2303,"应收贷项凭证","WX"),
	RETAIL_DAILY_SETTLE(2401, "销售日结", "WR"),
	RETAIL_PAYMENT(2402, "零售缴款", "WJ"),
	FINANCE_PAYMENT(2501,"付款","WF"),
	FINANCE_RECEIVABLE(2502,"收款","WS"),
	COMMISSION(2601, "提成管理", "WA"),
	// 质量
	TRAIN_PLAN(3141, "培训计划", "RP"),
	HEALTHY_CHECK(3142, "健康检查", "RJ"),
	// 价格
	PRICE_ADJUST(5131, "价格调整", "JT"),
	// 采购
	PURCHASE_PLAN(5201, "采购计划", "CJ"),
	PURCHASE_ORDER(5211, "采购订单", "CD"),
	PURCHASE_RECEIVE(5212, "采购收货", "CS"),
	PURCHASE_CHECK(5213, "采购验收", "CY"),
	PURCHASE_IN(5214, "采购入库", "CR"),
	// 购进退出
	PURCHASE_RETURN(5221, "购进退出", "CT"),
	PURCHASE_RETURN_OUT(5222, "购进退出出库", "CC"),
	// 存储管理
	INVENTORY(5301, "盘点", "KP"),
	SHELF_MOVE(5302, "货位移动", "KY"),
	RECEIVE(5303, "其它入库", "KR"),
	SEND(5304, "其它出库", "KC"),
	LOAD_BUCKET(5305, "中药饮片装斗", "KZ"),
	CLEAR_BUCKET(5306, "中药饮片清斗", "KD"),
	SPLIT(5307, "药品拆零", "KL"),
	DESTROY(5308, "药品销毁", "KX"),
	LOCK(5311, "药品锁定", "KS"),
	HANDLE(5312, "药品处理", "KI"),
	STOP_SALE(5313, "药品停售通知", "KT"),
	START_SALE(5314, "解除停售通知", "KJ"),
	LOT_ADJUST(5321, "批号调整", "KP"),
	PICK(5322, "拣配管理", "KE"),
	MAINTANCE(5331, "药品养护", "KH"),
	DISPLAY(5332, "陈列检查", "KN"),
	TEMPERATURE_HUMIDITY(5341, "温湿度纪录", "KW"),
	// 配送申请
	REQUIRE_PLAN(5401, "要货计划", "PJ"),
	DISTR_LACK(5402, "缺配单", "TQ"),
	// 配货出库
	DISTR_ORDER(5411, "配货单", "PD"),
	DISTR_OUT(5412, "配货出库", "PC"),
	// 配进入库
	DISTR_IN_ORDER(5413, "配进订单", "PT"),
	DISTR_IN_RECEIVE(5414, "配进收货", "PS"),
	DISTR_IN_CHECK(5415, "配进验收", "PY"),
	DISTR_IN(5416, "配进入库", "PR"),
	// 配进退出
	DISTR_IN_RETURN(5421, "配进退出", "HD"),
	DISTR_IN_RETURN_OUT(5422, "配进退出出库", "HC"),
	// 配后退回
	DISTR_RETURN_NOTICE(5423, "配后退回通知", "HT"),
	DISTR_RETURN_RECEIVE(5424, "配后退回收货", "HS"),
	DISTR_RETURN_CHECK(5425, "配后退回验收", "HY"),
	DISTR_RETURN_IN(5426, "配后退回入库", "HR"),
	// 销售
	SALES_PRICE(6201, "销售划价", "XH"),
	PRESCRIPTION_REGISTER(6202, "处方登记", "XF"),
	SPECIAL_REGISTER(6203, "专管药品登记", "XZ"),
	SALES_OUT(6204, "销售出库", "XC"),
	SALES_RETURN_IN(6205, "销售退回入库", "XR"),
	INTEGRAL_EXCHANGE(6211, "积分兑换", "XD"),
	// 售后
	COMPLAINT(6301, "投诉管理", "SS"),
	RECALL_PLAN(6302, "药品召回计划", "SZ"),
	RECALL(6303, "药品召回记录", "SJ"),
	RECOVER_PLAN(6304, "药品追回计划", "SH"),
	RECOVER(6305, "药品追回记录", "SL"),
	ADVERSE_REACTION(6306, "不良反应报告", "SB"),
	// 药学
	CONSULT(6401, "药品咨询", "YZ"),
	// 会员
	ADD_POINT(9001,"会员增分","VZ"),
	SUB_POINT(9002,"会员减分","VJ"),
	ZERO_POINT(9003,"会员清零","VQ"),
	CHANGHE_CARD(9004,"会员换卡","VH"),
	RECHARGE(9005,"会员充值","VC"),
	CHARGEBACK(9006,"会员扣款","VK"),
    TRANSFER(9007,"会员转账","VZ");

	private Integer type;
    private String typeName;
    private String codePrefix;
    
	OrderRule(Integer type, String typeName, String codePrefix){
    	this.type = type;
    	this.typeName = typeName;
    	this.codePrefix = codePrefix;
    }
    
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCodePrefix() {
		return codePrefix;
	}

	public void setCodePrefix(String codePrefix) {
		this.codePrefix = codePrefix;
	}

	public static String getName(int code) {
		for (OrderRule c : OrderRule.values()) {
			if (c.getType() == code) {
				return c.getTypeName();
			}
		}
		return null;
	}

	public static int getCode(String name) {
		for (OrderRule c : OrderRule.values()) {
			if (c.getTypeName().equals(name)) {
				return c.getType();
			}
		}
		return -1;
	}


}
