package com.rograndec.feijiayun.chain.inf.pos.constant;

/**
 * 
 * @ClassName: POSDataSyncPosSetTableEnum   
 * @Description: 加盟店根据开关设置同步自己的数据
 * @author yuting.li
 * @version 1.0 
 * @date 2017年12月4日 下午3:36:48
 */
public enum POSDataSyncPosSetTableEnum {

	// 商品模块
//	SPECIALPRICESTRATEGY("saasSpecialPriceStrategy", "saas_special_price_strategy"), // 特价设置 读取自己
//	SPECIALPRICEGOODS("saasSpecialPriceGoods", "saas_special_price_goods"), // 销售管理-特价商品 读取自己
//
//
//	// 会员模块
//	MEMBERCARDTYPE("saasMemberCardType", "saas_member_card_type"), // 会员管理-会员设置-会员卡类型 读取自己
//	MEMBERINFO("saasMemberInfo", "saas_member_info"), // 会员管理-会员信息 读取自己

	// POS系统设置相关
	POSSET("saasPosSet", "saas_pos_set"), // 零售管理-POS管理-系统设置 读取自己
	POSPAYTYPE("saasPosPayType", "saas_pos_pay_type"), // 零售管理-POS管理-支付方式 读取自己
	POSBANK("saasPosBank", "saas_pos_bank"),// 零售管理-POS管理-开户银行 读取自己
	;

	private String tableCode;
	private String tableName;

	private POSDataSyncPosSetTableEnum(String tableCode, String tableName) {
		this.tableCode = tableCode;
		this.tableName = tableName;
	}

	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	

}
