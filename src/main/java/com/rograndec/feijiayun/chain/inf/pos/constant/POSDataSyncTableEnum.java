package com.rograndec.feijiayun.chain.inf.pos.constant;

/**
 * 
 * @ClassName: POSDataSyncTableEnum   
 * @Description: POS同步接口相关表
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月11日 下午4:35:12
 */
public enum POSDataSyncTableEnum {
	
	// 业务相关
	ENTERPRISE("saasEnterprise","saas_enterprise"),//企业-基表
	ENTERPRISEBUSINESS("saasEnterpriseBusiness","saas_enterprise_business"),//企业-业务-子表
	// 商品模块
	GOODS("saasGoods","saas_goods"),//商品表  读取总部
	PRICEORDERDETAIL("saasPriceOrderDetail","saas_price_order_detail"),//价格管理-价格清单明细
	INCOMPATIBILITYGOODSONE("saasIncompatibilityGoodsOne","saas_incompatibility_goods_one"),//配伍禁忌-药品-子表 		读取总部
	SPECIALGOODS("saasSpecialGoods","saas_special_goods"),//专管药品		读取总部
	SPECIALPRICESTRATEGY("saasSpecialPriceStrategy","saas_special_price_strategy"),//特价设置	读取总部
	SPECIALPRICEGOODS("saasSpecialPriceGoods","saas_special_price_goods"),//销售管理-特价商品	读取总部
	GOODSBUSINESS("saasGoodsBusiness","saas_goods_business"),//商品业务属性
	QUALITYSET("saasQualitySet","saas_quality_set"),//质量管控	读取总部
	PHARMACYSET("saasPharmacySet","saas_pharmacy_set"),//药学设置	读取总部
	// 库存、批号
	LOTNUMBER("saasLotNumber","saas_lot_number"),//批号
	STOCK("saasStock","saas_stock"),//库存
	WAREHOUSESHELF("saasWarehouseShelf","saas_warehouse_shelf"),//货位
	WAREHOUSECARGOAREA("saasWarehouseCargoArea","saas_warehouse_cargo_area"),//货区
	// 会员模块
	MEMBERCARDTYPE("saasMemberCardType","saas_member_card_type"),//会员管理-会员设置-会员卡类型	读取总部
	MEMBERINFO("saasMemberInfo","saas_member_info"),//会员管理-会员信息	读取总部
	// 处方登记
	PRESCRIPTIONREGISTER("saasPrescriptionRegister","saas_prescription_register"),//零售管理-处方登记
	PRESCRIPTIONREGISTERDETAIL("saasPrescriptionRegisterDetail","saas_prescription_register_detail"),//零售管理-处方登记单品种明细
	PRESCRIPTIONREGISTERSHELF("saasPrescriptionRegisterShelf","saas_prescription_register_shelf"),//零售管理-处方登记单货位明细
	// 专管登记
	SPECIALREGISTER("saasSpecialRegister","saas_special_register"),//零售管理-专管登记
	SPECIALREGISTERDETAIL("saasSpecialRegisterDetail","saas_special_register_detail"),//零售管理-专管登记单品种明细
	SPECIALREGISTERSHELF("saasSpecialRegisterShelf","saas_special_register_shelf"),//零售管理-专管登记单货位明细
	PRESCRIPTIONSIGNATURE("saasPrescriptionSignature","saas_prescription_signature"),//零售管理-签章
	//用户相关
	USER("saasUser","saas_user"),//用户
	USERADMINISTRATION("saasUserAdministration","saas_user_administration"),//员工-行政-子表
	USERPOSITION("saasUserPosition","saas_user_position"),//员工-岗位-关联表
	POSITION("saasPosition","saas_position"),//系统设置-岗位		读取总部
	ROLE("saasSysRole","saas_sys_role"),//系统设置-角色		读取总部
	USERROLE("saasUserRole","saas_user_role"),//系统设置-员工-角色-关联表		读取总部
	// POS系统设置相关
	POSSET("saasPosSet","saas_pos_set"),//零售管理-POS管理-系统设置	读取总部
	POSPAYTYPE("saasPosPayType","saas_pos_pay_type"),//零售管理-POS管理-支付方式		读取总部
	POSBANK("saasPosBank","saas_pos_bank"),//零售管理-POS管理-开户银行	读取总部
	POSTEAM("saasPosTeam","saas_pos_team"),//零售管理-POS管理-班组
	POSCLERK("saasPosClerk","saas_pos_clerk"),//零售管理-POS管理-营业人员
	POSPAYEE("saasPosPayee","saas_pos_payee"),//零售管理-POS管理-收款人员
	POSPAYEEAUTH("saasPosPayeeAuth","saas_pos_payee_auth"),//零售管理-POS管理-款员权限
	;
	
	private String tableCode;
    private String tableName;
    
    private POSDataSyncTableEnum(String tableCode,String tableName) {
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
