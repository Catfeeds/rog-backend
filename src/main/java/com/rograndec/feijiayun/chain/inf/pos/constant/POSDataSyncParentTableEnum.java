package com.rograndec.feijiayun.chain.inf.pos.constant;

/**
 * 
 * @ClassName: POSDataSyncParentTableEnum
 * @Description: 读取总部数据  15
 * @author yuting.li
 * @version 1.0
 * @date 2017年11月4日 下午5:25:28
 */
public enum POSDataSyncParentTableEnum {

	// 商品模块
	GOODS("saasGoods", "saas_goods"), // 商品表 读取总部
	INCOMPATIBILITYGOODSONE("saasIncompatibilityGoodsOne", "saas_incompatibility_goods_one"), // 配伍禁忌-药品-子表 读取总部
	SPECIALGOODS("saasSpecialGoods", "saas_special_goods"), // 专管药品 读取总部
	SPECIALPRICESTRATEGY("saasSpecialPriceStrategy", "saas_special_price_strategy"), // 特价设置 读取总部
	SPECIALPRICEGOODS("saasSpecialPriceGoods", "saas_special_price_goods"), // 销售管理-特价商品 读取总部
	QUALITYSET("saasQualitySet", "saas_quality_set"), // 质量管控 读取总部
	PHARMACYSET("saasPharmacySet", "saas_pharmacy_set"), // 药学设置 读取总部

	// 会员模块
	MEMBERCARDTYPE("saasMemberCardType", "saas_member_card_type"), // 会员管理-会员设置-会员卡类型 读取总部
	MEMBERINFO("saasMemberInfo", "saas_member_info"), // 会员管理-会员信息 读取总部

	// 用户相关
	POSITION("saasPosition", "saas_position"), // 系统设置-岗位 读取总部
	ROLE("saasSysRole", "saas_sys_role"), // 系统设置-角色 读取总部
	USERROLE("saasUserRole", "saas_user_role"), // 系统设置-员工-角色-关联表 读取总部

	// POS系统设置相关
	POSSET("saasPosSet", "saas_pos_set"), // 零售管理-POS管理-系统设置 读取总部
	POSPAYTYPE("saasPosPayType", "saas_pos_pay_type"), // 零售管理-POS管理-支付方式 读取总部
	POSBANK("saasPosBank", "saas_pos_bank"),// 零售管理-POS管理-开户银行 读取总部
	;

	private String tableCode;
	private String tableName;

	private POSDataSyncParentTableEnum(String tableCode, String tableName) {
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
