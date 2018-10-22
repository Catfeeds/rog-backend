package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MenuModel  
 * @Description: 菜单初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月4日 下午1:02:55  
 *
 */
public class MenuModel {
	
	private String code;
	private String name;
	private String parentCode;
	private String parentName;
	private String url;
	private Integer type;// 0-管理菜单；1-系统菜单
	private Integer forParent;// 用于总部（0-否；1-是）
	private Integer forBranch;// 用于自营店（0-否；1-是）
	private Integer forLeague;// 用于加盟店（0-否；1-是）
	private String icon;// 菜单图标
	private Integer isParent;// 是否为父节点

	public MenuModel(){}

	public MenuModel(String code, String name, String parentCode, String parentName, String url, Integer type,
					 Integer forParent, Integer forBranch, Integer forLeague, String icon, Integer isParent) {
		this.code = code;
		this.name = name;
		this.parentCode = parentCode;
		this.parentName = parentName;
		this.url = url;
		this.type = type;
		this.forParent = forParent;
		this.forBranch = forBranch;
		this.forLeague = forLeague;
		this.icon = icon;
		this.isParent = isParent;
	}

	public static List<MenuModel> build(){
		List<MenuModel> menuModelList = new ArrayList<MenuModel>();

		MenuModel m01 = new MenuModel("01", "系统登录", "", "", "", 0, 0, 0, 0,"",1);
		MenuModel m0101 = new MenuModel("0101", "系统注册", "01", "系统登录", "###", 1, 1, 0, 0,"",0);
		MenuModel m0102 = new MenuModel("0102", "系统登录", "01", "系统登录", "###", 1, 1, 1, 1,"",0);
		MenuModel m0103 = new MenuModel("0103", "忘记密码", "01", "系统登录", "###", 1, 1, 1, 1,"",0);
		MenuModel m0104 = new MenuModel("0104", "系统首页", "01", "系统登录", "###", 1, 1, 1, 1,"",0);
		menuModelList.add(m01);
		menuModelList.add(m0101);
		menuModelList.add(m0102);
		menuModelList.add(m0103);
		menuModelList.add(m0104);

		MenuModel m02 = new MenuModel("02", "菜单管理", "", "", "", 0, 0, 0, 0,"",1);
		MenuModel m0201 = new MenuModel("0201", "管理配置", "02", "菜单管理", "###", 0, 0, 0, 0,"",0);
		MenuModel m0202 = new MenuModel("0202", "菜单配置", "02", "菜单管理", "###", 0, 0, 0, 0,"",0);
		MenuModel m0203 = new MenuModel("0203", "菜单权限", "02", "菜单管理", "###", 0, 0, 0, 0,"",0);
		menuModelList.add(m02);
		menuModelList.add(m0201);
		menuModelList.add(m0202);
		menuModelList.add(m0203);

		MenuModel m03 = new MenuModel("03", "系统运营", "", "", "", 0, 0, 0, 0,"",1);
		MenuModel m0301 = new MenuModel("0301", "系统管理", "03", "系统运营", "", 0, 0, 0, 0,"fa fa-navicon",1);
		MenuModel m030101 = new MenuModel("030101", "菜单管理", "0301", "系统管理", "###", 0, 0, 0, 0,"",0);
		MenuModel m030102 = new MenuModel("030102", "角色管理", "0301", "系统管理", "###", 0, 0, 0, 0,"",0);
		MenuModel m030103 = new MenuModel("030103", "员工管理", "0301", "系统管理", "###", 0, 0, 0, 0,"",0);
		MenuModel m0302 = new MenuModel("0302", "客户服务", "03", "系统运营", "", 0, 0, 0, 0,"",1);
		MenuModel m030201 = new MenuModel("030201", "注册管理", "0302", "客户服务", "###", 0, 0, 0, 0,"",0);
		menuModelList.add(m03);
		menuModelList.add(m0301);
		menuModelList.add(m030101);
		menuModelList.add(m030102);
		menuModelList.add(m030103);
		menuModelList.add(m0302);
		menuModelList.add(m030201);

		MenuModel m11 = new MenuModel("11", "系统管理", "", "", "", 1, 1, 1, 1,"fa fa-navicon",1);
		MenuModel mC1101 = new MenuModel("C1101", "企业信息", "11", "系统管理", "/system/enterprise/enterprise-index", 1, 1, 0, 0 ,"",0);
		MenuModel mS1101 = new MenuModel("S1101", "企业信息", "11", "系统管理", "/system/enterprise/branch-info", 1, 0, 1, 1,"",0);
		MenuModel m1102 = new MenuModel("1102", "系统设置", "11", "系统管理", "", 1, 1, 1, 1,"",1);
		MenuModel mC110201 = new MenuModel("C110201", "管理设置", "1102", "系统设置", "/system/config/manage/show", 1, 1, 0, 0 ,"",0);
		MenuModel mS110201 = new MenuModel("S110201", "管理设置", "1102", "系统设置", "/systemBr/config/manage/branch_show", 1, 0, 1, 1 ,"",0);
		MenuModel mC110202 = new MenuModel("C110202", "组织机构", "1102", "系统设置", "/system/config/organization", 1, 1, 0, 0 ,"",0);
		MenuModel mS110202 = new MenuModel("S110202", "组织机构", "1102", "系统设置", "/systemBr/config/organization", 1, 0, 1, 1 ,"",0);
		MenuModel m110203 = new MenuModel("110203", "质量设置", "1102", "系统设置", "/system/config/quality/index", 1, 1, 0, 0 ,"",0);
		MenuModel m110204 = new MenuModel("110204", "范围和资质", "1102", "系统设置", "/system/config/qualification/index", 1, 1, 0, 0 ,"",0);
		MenuModel mC110205 = new MenuModel("C110205", "权限设置", "1102", "系统设置", "/system/config/permission/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS110205 = new MenuModel("S110205", "权限设置", "1102", "系统设置", "/systemBr/config/permission/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC110206 = new MenuModel("C110206", "预警设置", "1102", "系统设置", "/system/config/warn_set/main_index", 1, 1, 0, 0 ,"",0);
		MenuModel mS110206 = new MenuModel("S110206", "预警设置", "1102", "系统设置", "/systemBr/config/warn_set/branch_index", 1, 0, 1, 1 ,"",0);
		MenuModel m1103 = new MenuModel("1103", "审批管理", "11", "系统管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC110301 = new MenuModel("C110301", "审批流程", "1103", "审批管理", "/system/approve/approval-index", 1, 1, 0, 0 ,"",0);
		MenuModel mS110301 = new MenuModel("S110301", "审批流程", "1103", "审批管理", "/systemBr/approve/approval-index", 1, 0, 1, 1 ,"",0);
		MenuModel mC110302 = new MenuModel("C110302", "审核操作", "1103", "审批管理", "/system/approve/operation-index", 1, 1, 0, 0 ,"",0);
		MenuModel mS110302 = new MenuModel("S110302", "审核操作", "1103", "审批管理", "/systemBr/approve/operation-index", 1, 0, 1, 1 ,"",0);
		MenuModel m1104 = new MenuModel("1104", "期初数据", "11", "系统管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC110401 = new MenuModel("C110401", "期初库存", "1104", "期初数据", "/system/beginningData/inventory", 1, 1, 0, 0 ,"",0);
		MenuModel mS110401 = new MenuModel("S110401", "期初库存", "1104", "期初数据", "/systemBr/beginningData/inventory", 1, 0, 1, 1 ,"",0);
		MenuModel m1105 = new MenuModel("1105", "数据管理", "11", "系统管理", "", 1, 0, 0, 0 ,"",1);
		MenuModel m110501 = new MenuModel("110501", "数据备份", "1105", "数据管理", "/system/data_backup", 1, 1, 0, 0 ,"",0);
		MenuModel mC1106 = new MenuModel("C1106", "修改密码", "11", "系统管理", "/system/modify_password", 1, 1, 0, 0 ,"",0);
		MenuModel mS1106 = new MenuModel("S1106", "修改密码", "11", "系统管理", "/systemBr/modify_password", 1, 0, 1, 1 ,"",0);
		MenuModel mC1107 = new MenuModel("C1107", "系统日志", "11", "系统管理", "/system/system_log", 1, 1, 0, 0 ,"",0);
		MenuModel mS1107 = new MenuModel("S1107", "系统日志", "11", "系统管理", "/systemBr/system_log", 1, 0, 1, 1 ,"",0);
		menuModelList.add(m11);
		menuModelList.add(mC1101);
		menuModelList.add(mS1101);
		menuModelList.add(m1102);
		menuModelList.add(mC110201);
		menuModelList.add(mS110201);
		menuModelList.add(mC110202);
		menuModelList.add(mS110202);
		menuModelList.add(m110203);
		menuModelList.add(m110204);
		menuModelList.add(mC110205);
		menuModelList.add(mS110205);
		menuModelList.add(mC110206);
		menuModelList.add(mS110206);
		menuModelList.add(m1103);
		menuModelList.add(mC110301);
		menuModelList.add(mS110301);
		menuModelList.add(mC110302);
		menuModelList.add(mS110302);
		menuModelList.add(m1104);
		menuModelList.add(mC110401);
		menuModelList.add(mS110401);
		menuModelList.add(m1105);
		menuModelList.add(m110501);
		menuModelList.add(mC1106);
		menuModelList.add(mS1106);
		menuModelList.add(mC1107);
		menuModelList.add(mS1107);

		MenuModel m21 = new MenuModel("21", "基础资料", "", "", "", 1, 1, 1, 1 ,"ivu-icon ivu-icon-cl",1);
		MenuModel m2101 = new MenuModel("2101", "门店管理", "21", "基础资料", "", 1, 1, 0, 0 ,"",1);
		MenuModel m210101 = new MenuModel("210101", "门店信息", "2101", "门店管理", "/bf/stores/stores_info", 1, 1, 0, 0 ,"",0);
		MenuModel m210102 = new MenuModel("210102", "门店分组", "2101", "门店管理", "/bf/stores/groupIndex", 1, 1, 0, 0 ,"",0);
		MenuModel m210103 = new MenuModel("210103", "门店级别", "2101", "门店管理", "/bf/stores/storesRank", 1, 1, 0, 0 ,"",0);
		MenuModel m210104 = new MenuModel("210104", "销售片区", "2101", "门店管理", "/bf/stores/saleArea", 1, 1, 0, 0 ,"",0);
		MenuModel m210105 = new MenuModel("210105", "销售商圈", "2101", "门店管理", "/bf/stores/saleCircle", 1, 1, 0, 0 ,"",0);
		MenuModel m2102 = new MenuModel("2102", "员工管理", "21", "基础资料", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC210201 = new MenuModel("C210201", "员工信息", "2102", "员工管理", "/bf/employee/employee", 1, 1, 0, 0 ,"",0);
		MenuModel mS210201 = new MenuModel("S210201", "员工信息", "2102", "员工管理", "/bfBr/employee/employee", 1, 0, 1, 1 ,"",0);
		MenuModel mC210202 = new MenuModel("C210202", "培训计划", "2102", "员工管理", "/bf/employee/trainPlan/trainPlan", 1, 1, 0, 0 ,"",0);
		MenuModel mS210202 = new MenuModel("S210202", "培训计划", "2102", "员工管理", "/bfBr/employee/trainPlan/trainPlan", 1, 0, 1, 1 ,"",0);
		MenuModel mC210203 = new MenuModel("C210203", "健康检查", "2102", "员工管理", "/bf/employee/healthcheck/healthcheck", 1, 1, 0, 0 ,"",0);
		MenuModel mS210203 = new MenuModel("S210203", "健康检查", "2102", "员工管理", "/bfBr/employee/healthcheck/healthcheck", 1, 0, 1, 1 ,"",0);
		MenuModel m2103 = new MenuModel("2103", "供货单位", "21", "基础资料", "", 1, 1, 0, 0 ,"",1);
		MenuModel m210301 = new MenuModel("210301", "供货单位", "2103", "供货单位", "/bf/supplier/sp", 1, 1, 0, 0 ,"",0);
		MenuModel m210302 = new MenuModel("210302", "供货单位分组", "2103", "供货单位", "/bf/supplier/gp", 1, 1, 0, 0 ,"",0);
		MenuModel m210303 = new MenuModel("210303", "供货单位销售人员", "2103", "供货单位", "/bf/supplier/slps", 1, 1, 0, 0 ,"",0);
		MenuModel m210304 = new MenuModel("210304", "供货单位经营品种", "2103", "供货单位", "/bf/supplier/spkd", 1, 1, 0, 0 ,"",0);
		MenuModel m2104 = new MenuModel("2104", "库房管理", "21", "基础资料", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC210401 = new MenuModel("C210401", "仓库", "2104", "库房管理", "/bf/stock/warehouse", 1, 1, 0, 0 ,"",0);
		MenuModel mS210401 = new MenuModel("S210401", "仓库", "2104", "库房管理", "/bfBr/stock/warehouse", 1, 0, 1, 1 ,"",0);
		MenuModel mC210402 = new MenuModel("C210402", "库区", "2104", "库房管理", "/bf/stock/warehouse_area", 1, 1, 0, 0 ,"",0);
		MenuModel mS210402 = new MenuModel("S210402", "库区", "2104", "库房管理", "/bfBr/stock/warehouse_area", 1, 0, 1, 1 ,"",0);
		MenuModel mC210403 = new MenuModel("C210403", "货区", "2104", "库房管理", "/bf/stock/goods_area", 1, 1, 0, 0 ,"",0);
		MenuModel mS210403 = new MenuModel("S210403", "柜组", "2104", "库房管理", "/bfBr/stock/goods_area", 1, 0, 1, 1 ,"",0);
		MenuModel mC210404 = new MenuModel("C210404", "货位", "2104", "库房管理", "/bf/stock/goods_position", 1, 1, 0, 0 ,"",0);
		MenuModel mS210404 = new MenuModel("S210404", "货位", "2104", "库房管理", "/bfBr/stock/goods_position", 1, 0, 1, 1 ,"",0);
		MenuModel m2105 = new MenuModel("2105", "设施设备", "21", "基础资料", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC210501 = new MenuModel("C210501", "设施设备", "2105", "设施设备", "/bf/eq/eq", 1, 1, 0, 0 ,"",0);
		MenuModel mS210501 = new MenuModel("S210501", "设施设备", "2105", "设施设备", "/bf/eq/eqb", 1, 0, 1, 1 ,"",0);
		MenuModel mC210502 = new MenuModel("C210502", "检查、清洁和维护", "2105", "设施设备", "/bf/eq/eqmt", 1, 1, 0, 0 ,"",0);
		MenuModel mS210502 = new MenuModel("S210502", "检查、清洁和维护", "2105", "设施设备", "/bf/eq/eqmtb", 1, 0, 1, 1 ,"",0);
		MenuModel mC210503 = new MenuModel("C210503", "校验或检定", "2105", "设施设备", "/bf/eq/eqck", 1, 1, 0, 0 ,"",0);
		MenuModel mS210503 = new MenuModel("S210503", "校验或检定", "2105", "设施设备", "/bf/eq/eqckb", 1, 0, 1, 1 ,"",0);
		MenuModel mC210504 = new MenuModel("C210504", "验证", "2105", "设施设备", "/bf/eq/eqvf", 1, 1, 0, 0 ,"",0);
		MenuModel mS210504 = new MenuModel("S210504", "验证", "2105", "设施设备", "/bf/eq/eqvfb", 1, 0, 1, 1 ,"",0);
		menuModelList.add(m21);
		menuModelList.add(m2101);
		menuModelList.add(m210101);
		menuModelList.add(m210102);
		menuModelList.add(m210103);
		menuModelList.add(m210104);
		menuModelList.add(m210105);
		menuModelList.add(m2102);
		menuModelList.add(mC210201);
		menuModelList.add(mS210201);
		menuModelList.add(mC210202);
		menuModelList.add(mS210202);
		menuModelList.add(mC210203);
		menuModelList.add(mS210203);
		menuModelList.add(m2103);
		menuModelList.add(m210301);
		menuModelList.add(m210302);
		menuModelList.add(m210303);
		menuModelList.add(m210304);
		menuModelList.add(m2104);
		menuModelList.add(mC210401);
		menuModelList.add(mS210401);
		menuModelList.add(mC210402);
		menuModelList.add(mS210402);
		menuModelList.add(mC210403);
		menuModelList.add(mS210403);
		menuModelList.add(mC210404);
		menuModelList.add(mS210404);
		menuModelList.add(m2105);
		menuModelList.add(mC210501);
		menuModelList.add(mS210501);
		menuModelList.add(mC210502);
		menuModelList.add(mS210502);
		menuModelList.add(mC210503);
		menuModelList.add(mS210503);
		menuModelList.add(mC210504);
		menuModelList.add(mS210504);

		MenuModel m51 = new MenuModel("51", "商品管理", "", "", "", 1, 1, 1, 1 ,"ivu-icon ivu-icon-gr",1);
		MenuModel mC5101 = new MenuModel("C5101", "商品信息", "51", "商品管理", "/pro/info/list", 1, 1, 0, 01 ,"",0);
		MenuModel mS5101 = new MenuModel("S5101", "商品信息", "51", "商品管理", "/proBr/info/list", 1, 0, 1, 1 ,"",0);
		MenuModel m5102 = new MenuModel("5102", "商品设置", "51", "商品管理", "/pro/setting", 1, 1, 0, 0 ,"",0);
		MenuModel mC5103 = new MenuModel("C5103", "商品管理", "51", "商品管理", "/pro/goodsmanagehead", 1, 1, 0, 0 ,"",0);
		MenuModel mS5103 = new MenuModel("S5103", "商品管理", "51", "商品管理", "/pro/goodsmanagebranch", 1, 0, 1, 1 ,"",0);
		MenuModel m5104 = new MenuModel("5104", "价格管理", "51", "商品管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel m510401 = new MenuModel("510401", "价格清单", "5104", "价格管理", "/pro/priceManage/list", 1, 1, 0, 0 ,"",0);
		MenuModel mC510402 = new MenuModel("C510402", "价格调整", "5104", "价格管理", "/pro/priceManage/adjustment", 1, 1, 0, 0 ,"",0);
		MenuModel mS510402 = new MenuModel("S510402", "价格调整", "5104", "价格管理", "/proBr/priceManage/adjustment", 1, 0, 1, 1 ,"",0);
		MenuModel m5105 = new MenuModel("5105", "销售管理", "51", "商品管理", "", 1, 1, 0, 0 ,"",1);
		MenuModel m510501 = new MenuModel("510501", "特价设置", "5105", "销售管理", "/pro/sell/salesetting", 1, 1, 0, 0 ,"",0);
		MenuModel m510502 = new MenuModel("510502", "特价商品", "5105", "销售管理", "/pro/sell/salegoods", 1, 1, 0, 0 ,"",0);
		MenuModel m510503 = new MenuModel("510503", "提成设置", "5105", "销售管理", "/pro/sell/comsetting", 1, 1, 0, 0 ,"",0);
		MenuModel m510504 = new MenuModel("510504", "提成商品", "5105", "销售管理", "/pro/sell/comgoods", 1, 1, 0, 0 ,"",0);
		MenuModel m5106 = new MenuModel("5106", "药学管理", "51", "商品管理", "", 1, 1, 0, 0 ,"",1);
		MenuModel m510601 = new MenuModel("510601", "药学设置", "5106", "药学管理", "/pro/drugManage/drugSetting", 1, 1, 0, 0 ,"",0);
		MenuModel m510602 = new MenuModel("510602", "配伍禁忌", "5106", "药学管理", "/pro/drugManage/incompaty", 1, 1, 0, 0 ,"",0);
		MenuModel m510603 = new MenuModel("510603", "专管商品", "5106", "药学管理", "/pro/drugManage/special", 1, 1, 0, 0 ,"",0);
		menuModelList.add(m51);
		menuModelList.add(mC5101);
		menuModelList.add(mS5101);
		menuModelList.add(m5102);
		menuModelList.add(mC5103);
		menuModelList.add(mS5103);
		menuModelList.add(m5104);
		menuModelList.add(m510401);
		menuModelList.add(mC510402);
		menuModelList.add(mS510402);
		menuModelList.add(m5105);
		menuModelList.add(m510501);
		menuModelList.add(m510502);
		menuModelList.add(m510503);
		menuModelList.add(m510504);
		menuModelList.add(m5106);
		menuModelList.add(m510601);
		menuModelList.add(m510602);
		menuModelList.add(m510603);

		MenuModel m52 = new MenuModel("52", "采购管理", "", "", "", 1, 1, 1, 1 ,"ivu-icon ivu-icon-io",1);
		MenuModel m5201 = new MenuModel("5201", "采购计划", "52", "采购管理", "/ph/plan/list", 1, 1, 0, 0 ,"",0);
		MenuModel m5202 = new MenuModel("5202", "采购订单", "52", "采购管理", "/ph/order/index", 1, 1, 0, 0 ,"",0);
		MenuModel m5203 = new MenuModel("5203", "采购收货", "52", "采购管理", "/ph/takeGoods/list", 1, 1, 0, 0 ,"",0);
		MenuModel m5204 = new MenuModel("5204", "采购验收", "52", "采购管理", "/ph/acceptance/index", 1, 1, 0, 0 ,"",0);
		MenuModel m5205 = new MenuModel("5205", "采购入库", "52", "采购管理", "/ph/storage/index", 1, 1, 0, 0 ,"",0);
		MenuModel m5206 = new MenuModel("5206", "购进退出", "52", "采购管理", "/ph/purchaseExit/list", 1, 1, 0, 0 ,"",0);
		MenuModel m5207 = new MenuModel("5207", "购进退出出库", "52", "采购管理", "/ph/purchaseExitOutStock/list", 1, 1, 0, 0 ,"",0);
		menuModelList.add(m52);
		menuModelList.add(m5201);
		menuModelList.add(m5202);
		menuModelList.add(m5203);
		menuModelList.add(m5204);
		menuModelList.add(m5205);
		menuModelList.add(m5206);
		menuModelList.add(m5207);

		MenuModel m53 = new MenuModel("53", "储存管理", "", "", "", 1, 1, 1, 1 ,"ivu-icon ivu-icon-cu",1);
		MenuModel m5301 = new MenuModel("5301", "货物移动", "53", "储存管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC530101 = new MenuModel("C530101", "货位移动", "5301", "货物移动", "/st/gv/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS530101 = new MenuModel("S530101", "货位移动", "5301", "货物移动", "/stBr/gv/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC530102 = new MenuModel("C530102", "其他入库", "5301", "货物移动", "/st/gv/otherin/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS530102 = new MenuModel("S530102", "其他入库", "5301", "货物移动", "/stBr/gv/otherin/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC530103 = new MenuModel("C530103", "其他出库", "5301", "货物移动", "/st/gv/otherout/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS530103 = new MenuModel("S530103", "其他出库", "5301", "货物移动", "/stBr/gv/otherout/index", 1, 0, 1, 1 ,"",0);
		MenuModel m5302 = new MenuModel("5302", "饮片管理", "53", "储存管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel m530201 = new MenuModel("530201", "饮片装斗", "5302", "饮片管理", "/stBr/medicines/package", 1, 0, 1, 1 ,"",0);
		MenuModel m530202 = new MenuModel("530202", "饮片清斗", "5302", "饮片管理", "/stBr/medicines/clear", 1, 0, 1, 1 ,"",0);
		MenuModel m5303 = new MenuModel("5303", "拆零管理", "53", "储存管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel m530301 = new MenuModel("530301", "拆零设置", "5303", "拆零管理", "/stBr/sz/index", 1, 0, 1, 1 ,"",0);
		MenuModel m530302 = new MenuModel("530302", "商品拆零", "5303", "拆零管理", "/stBr/sz/productSz", 1, 0, 1, 1 ,"",0);
		MenuModel m5304 = new MenuModel("5304", "商品处理", "53", "储存管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC530401 = new MenuModel("C530401", "商品锁定", "5304", "商品处理", "/st/ch/locking", 1, 1, 0, 0 ,"",0);
		MenuModel mS530401 = new MenuModel("S530401", "商品锁定", "5304", "商品处理", "/stBr/ch/locking", 1, 0, 1, 1 ,"",0);
		MenuModel mC530402 = new MenuModel("C530402", "商品处理", "5304", "商品处理", "/st/ch/handling", 1, 1, 0, 0 ,"",0);
		MenuModel mS530402 = new MenuModel("S530402", "商品处理", "5304", "商品处理", "/stBr/ch/handling", 1, 0, 1, 1 ,"",0);
		MenuModel mC530403 = new MenuModel("C530403", "商品销毁", "5304", "商品处理", "/st/ch/destory", 1, 1, 0, 0 ,"",0);
		MenuModel mS530403 = new MenuModel("S530403", "商品销毁", "5304", "商品处理", "/stBr/ch/destory", 1, 0, 1, 1 ,"",0);
		MenuModel mC530404 = new MenuModel("C530404", "停售通知", "5304", "商品处理", "/st/ch/stopsales", 1, 1, 0, 0 ,"",0);
		MenuModel mS530404 = new MenuModel("S530404", "停售通知", "5304", "商品处理", "/stBr/ch/stopsales", 1, 0, 1, 1 ,"",0);
		MenuModel mC530405 = new MenuModel("C530405", "解停通知", "5304", "商品处理", "/st/ch/unblocksales", 1, 1, 0, 0 ,"",0);
		MenuModel mS530405 = new MenuModel("S530405", "解停通知", "5304", "商品处理", "/stBr/ch/unblocksales", 1, 0, 1, 1 ,"",0);
		MenuModel m5305 = new MenuModel("5305", "盘点管理", "53", "储存管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC530501 = new MenuModel("C530501", "盘点单", "5305", "盘点管理", "/st/iv/orderindex", 1, 1, 0, 0 ,"",0);
		MenuModel mS530501 = new MenuModel("S530501", "盘点单", "5305", "盘点管理", "/stBr/iv/orderindex", 1, 0, 1, 1 ,"",0);
		MenuModel mC530502 = new MenuModel("C530502", "盘点登记", "5305", "盘点管理", "/st/iv/registerindex", 1, 1, 0, 0 ,"",0);
		MenuModel mS530502 = new MenuModel("S530502", "盘点登记", "5305", "盘点管理", "/stBr/iv/registerindex", 1, 0, 1, 1 ,"",0);
		MenuModel mC530503 = new MenuModel("C530503", "差异处理", "5305", "盘点管理", "/st/iv/difflist", 1, 1, 0, 0 ,"",0);
		MenuModel mS530503 = new MenuModel("S530503", "差异处理", "5305", "盘点管理", "/stBr/iv/difflist", 1, 0, 1, 1 ,"",0);
		MenuModel mC530504 = new MenuModel("C530504", "盘点过账", "5305", "盘点管理", "/st/iv/account", 1, 1, 0, 0 ,"",0);
		MenuModel mS530504 = new MenuModel("S530504", "盘点过账", "5305", "盘点管理", "/stBr/iv/account", 1, 0, 1, 1 ,"",0);
		MenuModel mC5306 = new MenuModel("C5306", "批号管理", "53", "储存管理", "/st/sb/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS5306 = new MenuModel("S5306", "批号管理", "53", "储存管理", "/stBr/sb/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC5307 = new MenuModel("C5307", "药品养护", "53", "储存管理", "/st/sd/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS5307 = new MenuModel("S5307", "药品养护", "53", "储存管理", "/stBr/sd/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC5308 = new MenuModel("C5308", "陈列检查", "53", "储存管理", "/st/displaycheck/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS5308 = new MenuModel("S5308", "陈列检查", "53", "储存管理", "/stBr/displaycheck/index", 1, 0, 1, 1 ,"",0);
		MenuModel m5309 = new MenuModel("5309", "库房管理", "53", "储存管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC530901 = new MenuModel("C530901", "温湿度记录", "5309", "库房管理", "", 1, 1, 0, 0 ,"",0);
		MenuModel mS530901 = new MenuModel("S530901", "温湿度记录", "5309", "库房管理", "", 1, 0, 1, 1 ,"",0);
		MenuModel m5310 = new MenuModel("5310", "库存报表", "53", "储存管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC531001 = new MenuModel("C531001", "库存清单", "5310", "库存报表", "/st/rp/iv", 1, 1, 0, 0 ,"",0);
		MenuModel mS531001 = new MenuModel("S531001", "库存清单", "5310", "库存报表", "/st/rp/biv", 1, 0, 1, 1 ,"",0);
		MenuModel mC531002 = new MenuModel("C531002", "库存预警", "5310", "库存报表", "/st/rp/wn", 1, 1, 0, 0 ,"",0);
		MenuModel mS531002 = new MenuModel("S531002", "库存预警", "5310", "库存报表", "/st/rp/bwn", 1, 0, 1, 1 ,"",0);
		MenuModel m531003 = new MenuModel("531003", "超量销售商品", "5310", "库存报表", "/st/rp/es", 1, 0, 1, 1 ,"",0);
		MenuModel mC531004 = new MenuModel("C531004", "库存占用", "5310", "库存报表", "/st/rp/su", 1, 1, 0, 0 ,"",0);
		MenuModel mS531004 = new MenuModel("S531004", "库存占用", "5310", "库存报表", "/st/rp/bsu", 1, 0, 1, 1 ,"",0);
		MenuModel mC531005 = new MenuModel("C531005", "在途库存", "5310", "库存报表", "/st/rp/os", 1, 1, 0, 0 ,"",0);
		MenuModel mS531005 = new MenuModel("S531005", "在途库存", "5310", "库存报表", "/st/rp/bos", 1, 0, 1, 1 ,"",0);
//		MenuModel m531006 = new MenuModel("531006", "历史库存", "5310", "库存报表", "###", 1, 1, 1, 1 ,"",0);
		MenuModel mC531007 = new MenuModel("C531007", "库存明细账", "5310", "库存报表", "/st/rp/detail", 1, 1, 0, 0 ,"",0);
		MenuModel mS531007 = new MenuModel("S531007", "库存明细账", "5310", "库存报表", "/st/rp/bdetail", 1, 0, 1, 1 ,"",0);
//		MenuModel m531008 = new MenuModel("531008", "商品进销存台账", "5310", "库存报表", "###", 1, 1, 1, 1 ,"",0);
		menuModelList.add(m53);
		menuModelList.add(m5301);
		menuModelList.add(mC530101);
		menuModelList.add(mS530101);
		menuModelList.add(mC530102);
		menuModelList.add(mS530102);
		menuModelList.add(mC530103);
		menuModelList.add(mS530103);
		menuModelList.add(m5302);
		menuModelList.add(m530201);
		menuModelList.add(m530202);
		menuModelList.add(m5303);
		menuModelList.add(m530301);
		menuModelList.add(m530302);
		menuModelList.add(m5304);
		menuModelList.add(mC530401);
		menuModelList.add(mS530401);
		menuModelList.add(mC530402);
		menuModelList.add(mS530402);
		menuModelList.add(mC530403);
		menuModelList.add(mS530403);
		menuModelList.add(mC530404);
		menuModelList.add(mS530404);
		menuModelList.add(mC530405);
		menuModelList.add(mS530405);
		menuModelList.add(m5305);
		menuModelList.add(mC530501);
		menuModelList.add(mS530501);
		menuModelList.add(mC530502);
		menuModelList.add(mS530502);
		menuModelList.add(mC530503);
		menuModelList.add(mS530503);
		menuModelList.add(mC530504);
		menuModelList.add(mS530504);
		menuModelList.add(mC5306);
		menuModelList.add(mS5306);
		menuModelList.add(mC5307);
		menuModelList.add(mS5307);
		menuModelList.add(mC5308);
		menuModelList.add(mS5308);
		menuModelList.add(m5309);
		menuModelList.add(mC530901);
		menuModelList.add(mS530901);
		menuModelList.add(m5310);
		menuModelList.add(mC531001);
		menuModelList.add(mS531001);
		menuModelList.add(mC531002);
		menuModelList.add(mS531002);
		menuModelList.add(m531003);
		menuModelList.add(mC531004);
		menuModelList.add(mS531004);
		menuModelList.add(mC531005);
		menuModelList.add(mS531005);
		menuModelList.add(mC531007);
		menuModelList.add(mS531007);

		MenuModel m54 = new MenuModel("54", "配送管理", "", "", "", 1, 1, 1, 1 ,"fa fa-ambulance",1);
		MenuModel m5401 = new MenuModel("5401", "要货计划", "54", "配送管理", "/distrStore/entry/plan/index", 1, 0, 1, 1 ,"",0);
		MenuModel m5402 = new MenuModel("5402", "配货出库", "54", "配送管理", "", 1, 1, 0, 0 ,"",1);
		MenuModel m540201 = new MenuModel("540201", "配货单", "5402", "配货出库", "/distrMain/outstock/order/index", 1, 1, 0, 0 ,"",0);
		MenuModel m540202 = new MenuModel("540202", "配货出库", "5402", "配货出库", "/distrMain/outstock/out/index", 1, 1, 0, 0 ,"",0);
		MenuModel m540203 = new MenuModel("540203", "缺配单", "5402", "配货出库", "/distrMain/outstock/lackMissing/index", 1, 1, 0, 0 ,"",0);
		MenuModel m5403 = new MenuModel("5403", "配货入库", "54", "配送管理", "", 1, 0, 1, 1 ,"",1);
		MenuModel m540301 = new MenuModel("540301", "配进订单", "5403", "配货入库", "/distrStore/entry/order/index", 1, 0, 1, 1 ,"",0);
		MenuModel m540302 = new MenuModel("540302", "配进收货", "5403", "配货入库", "/distrStore/entry/receive/index", 1, 0, 1, 1 ,"",0);
		MenuModel m540303 = new MenuModel("540303", "配进验收", "5403", "配货入库", "/distrStore/entry/acceptance/index", 1, 0, 1, 1 ,"",0);
		MenuModel m540304 = new MenuModel("540304", "配进入库", "5403", "配货入库", "/distrStore/entry/entry/index", 1, 0, 1, 1 ,"",0);
		MenuModel m5404 = new MenuModel("5404", "配进退出", "54", "配送管理", "", 1, 0, 1, 1 ,"",1);
		MenuModel m540401 = new MenuModel("540401", "配进退出", "5404", "配进退出", "/distrStore/back/quit/index", 1, 0, 1, 1 ,"",0);
		MenuModel m540402 = new MenuModel("540402", "配进退出出库", "5404", "配进退出", "/distrStore/back/quitOut/index", 1, 0, 1, 1 ,"",0);
		MenuModel m5405 = new MenuModel("5405", "配后退回", "54", "配送管理", "", 1, 1, 0, 0 ,"",1);
		MenuModel m540501 = new MenuModel("540501", "配后退回", "5405", "配后退回", "/distrMain/quitstock/return/index", 1, 1, 0, 0 ,"",0);
		MenuModel m540502 = new MenuModel("540502", "配后退回收货", "5405", "配后退回", "/distrMain/quitstock/take/index", 1, 1, 0, 0 ,"",0);
		MenuModel m540503 = new MenuModel("540503", "配后退回验收", "5405", "配后退回", "/distrMain/quitstock/check/index", 1, 1, 0, 0 ,"",0);
		MenuModel m540504 = new MenuModel("540504", "配后退回入库", "5405", "配后退回", "/distrMain/quitstock/enterStorehouse/index", 1, 1, 0, 0 ,"",0);
		menuModelList.add(m54);
		menuModelList.add(m5401);
		menuModelList.add(m5402);
		menuModelList.add(m540201);
		menuModelList.add(m540202);
		menuModelList.add(m540203);
		menuModelList.add(m5403);
		menuModelList.add(m540301);
		menuModelList.add(m540302);
		menuModelList.add(m540303);
		menuModelList.add(m540304);
		menuModelList.add(m5404);
		menuModelList.add(m540401);
		menuModelList.add(m540402);
		menuModelList.add(m5405);
		menuModelList.add(m540501);
		menuModelList.add(m540502);
		menuModelList.add(m540503);
		menuModelList.add(m540504);

		MenuModel m61 = new MenuModel("61", "会员管理", "", "", "", 1, 1, 1, 1 ,"ivu-icon ivu-icon-ca",1);
		MenuModel m6101 = new MenuModel("6101", "会员设置", "61", "会员管理", "", 1, 1, 0, 0 ,"",1);
		MenuModel m610101 = new MenuModel("610101", "会员卡级别", "6101", "会员设置", "/member/setting/level", 1, 1, 0, 1 ,"",0);
		MenuModel m610102 = new MenuModel("610102", "会员卡类型", "6101", "会员设置", "/member/setting/type", 1, 1, 0, 1 ,"",0);
		MenuModel mC6102 = new MenuModel("C6102", "会员信息", "61", "会员管理", "/member/info/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS6102 = new MenuModel("S6102", "会员信息", "61", "会员管理", "/memberBr/info/index", 1, 0, 1, 1 ,"",0);
		MenuModel m6103 = new MenuModel("6103", "积分管理", "61", "会员管理", "/memberBr/pointsmanage/index", 1, 0, 1, 1 ,"",0);
		MenuModel m6104 = new MenuModel("6104", "储值管理", "61", "会员管理", "/member/storevalue/index", 1, 0, 1, 1 ,"",0);
		MenuModel m6105 = new MenuModel("6105", "积分兑换", "61", "会员管理", "", 1, 1, 0, 0 ,"",1);
		MenuModel m610501 = new MenuModel("610501", "积分商品", "6105", "积分兑换", "/member/pointsexchange/goods", 1, 1, 0, 0 ,"",0);
		MenuModel m610502 = new MenuModel("610502", "积分兑换", "6105", "积分兑换", "/member/pointsexchange/exchange", 1, 1, 0, 0 ,"",0);
		MenuModel m6106 = new MenuModel("6106", "会员报表", "61", "会员管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC610601 = new MenuModel("C610601", "积分兑换", "6106", "会员报表", "/member/report/exchange", 1, 1, 0, 0 ,"",0);
		MenuModel mS610601 = new MenuModel("S610601", "积分兑换", "6106", "会员报表", "/memberBr/report/exchange", 1, 0, 1, 1 ,"",0);
		MenuModel mC610602 = new MenuModel("C610602", "会员销售", "6106", "会员报表", "/member/report/sale", 1, 1, 0, 0 ,"",0);
		MenuModel mS610602 = new MenuModel("S610602", "会员销售", "6106", "会员报表", "/memberBr/report/sale", 1, 0, 1, 1 ,"",0);
		MenuModel mC610603 = new MenuModel("C610603", "积分明细", "6106", "会员报表", "/member/report/points", 1, 1, 0, 0 ,"",0);
		MenuModel mS610603 = new MenuModel("S610603", "积分明细", "6106", "会员报表", "/memberBr/report/points", 1, 0, 1, 1 ,"",0);
		MenuModel mC610604 = new MenuModel("C610604", "储值付款", "6106", "会员报表", "/member/report/payment", 1, 1, 0, 0 ,"",0);
		MenuModel mS610604 = new MenuModel("S610604", "储值付款", "6106", "会员报表", "/memberBr/report/payment", 1, 0, 1, 1 ,"",0);
		MenuModel mC610605 = new MenuModel("C610605", "储值明细", "6106", "会员报表", "/member/report/stored", 1, 1, 0, 0 ,"",0);
		MenuModel mS610605 = new MenuModel("S610605", "储值明细", "6106", "会员报表", "/memberBr/report/stored", 1, 0, 1, 1 ,"",0);
		MenuModel mC610606 = new MenuModel("C610606", "会员排行", "6106", "会员报表", "/member/report/rank", 1, 1, 0, 0 ,"",0);
		MenuModel mS610606 = new MenuModel("S610606", "会员排行", "6106", "会员报表", "/memberBr/report/rank", 1, 0, 1, 1 ,"",0);
		MenuModel mC610607 = new MenuModel("C610607", "会员活跃度", "6106", "会员报表", "/member/report/liveness", 1, 1, 0, 0 ,"",0);
		MenuModel mS610607 = new MenuModel("S610607", "会员活跃度", "6106", "会员报表", "/memberBr/report/liveness", 1, 0, 1, 1 ,"",0);
		MenuModel mC610608 = new MenuModel("C610608", "会员生日", "6106", "会员报表", "/member/report/birthday", 1, 1, 0, 0 ,"",0);
		MenuModel mS610608 = new MenuModel("S610608", "会员生日", "6106", "会员报表", "/memberBr/report/birthday", 1, 0, 1, 1 ,"",0);
		menuModelList.add(m61);
		menuModelList.add(m6101);
		menuModelList.add(m610101);
		menuModelList.add(m610102);
		menuModelList.add(mC6102);
		menuModelList.add(mS6102);
		menuModelList.add(m6103);
		menuModelList.add(m6104);
		menuModelList.add(m6105);
		menuModelList.add(m610501);
		menuModelList.add(m610502);
		menuModelList.add(m6106);
		menuModelList.add(mC610601);
		menuModelList.add(mS610601);
		menuModelList.add(mC610602);
		menuModelList.add(mS610602);
		menuModelList.add(mC610603);
		menuModelList.add(mS610603);
		menuModelList.add(mC610604);
		menuModelList.add(mS610604);
		menuModelList.add(mC610605);
		menuModelList.add(mS610605);
		menuModelList.add(mC610606);
		menuModelList.add(mS610606);
		menuModelList.add(mC610607);
		menuModelList.add(mS610607);
		menuModelList.add(mC610608);
		menuModelList.add(mS610608);

		MenuModel m63 = new MenuModel("63", "POS系统", "", "", "", 0, 0, 0, 0 ,"",1);
		MenuModel m6301 = new MenuModel("6301", "系统登录", "63", "POS系统", "###", 1, 0, 1, 1 ,"",0);
		MenuModel m6302 = new MenuModel("6302", "开单收银", "63", "POS系统", "###", 1, 0, 1, 1 ,"",0);
		MenuModel m6303 = new MenuModel("6303", "款员交班", "63", "POS系统", "###", 1, 0, 1, 1 ,"",0);
		MenuModel m6304 = new MenuModel("6304", "报表查询", "63", "POS系统", "###", 1, 0, 1, 1 ,"",0);
		MenuModel m6305 = new MenuModel("6305", "预售开票", "63", "POS系统", "###", 1, 0, 1, 1 ,"",0);
		MenuModel m6306 = new MenuModel("6306", "会员开卡", "63", "POS系统", "###", 1, 0, 1, 1 ,"",0);
		MenuModel m6307 = new MenuModel("6307", "收银练习", "63", "POS系统", "###", 1, 0, 1, 1 ,"",0);
		MenuModel m6308 = new MenuModel("6308", "操作日志", "63", "POS系统", "###", 1, 0, 1, 1 ,"",0);
		MenuModel m6309 = new MenuModel("6309", "修改密码", "63", "POS系统", "###", 1, 0, 1, 1 ,"",0);
		MenuModel m6310 = new MenuModel("6310", "系统设置", "63", "POS系统", "###", 1, 0, 1, 1 ,"",0);
		menuModelList.add(m63);
		menuModelList.add(m6301);
		menuModelList.add(m6302);
		menuModelList.add(m6303);
		menuModelList.add(m6304);
		menuModelList.add(m6305);
		menuModelList.add(m6306);
		menuModelList.add(m6307);
		menuModelList.add(m6308);
		menuModelList.add(m6309);
		menuModelList.add(m6310);

		MenuModel m64 = new MenuModel("64", "零售管理", "", "", "", 1, 1, 1, 1 ,"icon-puzzle",1);
		MenuModel m6401 = new MenuModel("6401", "POS设置", "64", "零售管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC640101 = new MenuModel("C640101", "系统设置", "6401", "POS设置", "/retail/pos/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS640101 = new MenuModel("S640101", "系统设置", "6401", "POS设置", "/retailBr/pos/index", 1, 0, 1, 1 ,"",0);
		MenuModel m640102 = new MenuModel("640102", "支付方式", "6401", "POS设置", "/retail/pos/pay", 1, 1, 0, 0 ,"",0);
		MenuModel m640103 = new MenuModel("640103", "开户银行", "6401", "POS设置", "/retail/pos/bank", 1, 1, 0, 0 ,"",0);
		MenuModel m640104 = new MenuModel("640104", "款台", "6401", "POS设置", "/retailBr/pos/cashdesk", 1, 0, 1, 1 ,"",0);
		MenuModel m640105 = new MenuModel("640105", "班组", "6401", "POS设置", "/retailBr/pos/class", 1, 0, 1, 1 ,"",0);
		MenuModel m640106 = new MenuModel("640106", "柜组", "6401", "POS设置", "/retailBr/pos/cupboard", 1, 0, 1, 1 ,"",0);
		MenuModel m640107 = new MenuModel("640107", "营业人员", "6401", "POS设置", "/retailBr/pos/bp", 1, 0, 1, 1 ,"",0);
		MenuModel m640108 = new MenuModel("640108", "收款人员", "6401", "POS设置", "/retailBr/pos/payee", 1, 0, 1, 1 ,"",0);
		MenuModel m640109 = new MenuModel("640109", "款员权限", "6401", "POS设置", "/retailBr/pos/pp", 1, 0, 1, 1 ,"",0);
		MenuModel m640110 = new MenuModel("640110", "销售时段", "6401", "POS设置", "/retail/pos/sp", 1, 1, 0, 0 ,"",0);
		MenuModel m6402 = new MenuModel("6402", "划价单", "64", "零售管理", "/retailBr/pricing/index", 1, 0, 1, 1 ,"",0);
		MenuModel m6403 = new MenuModel("6403", "专管登记", "64", "零售管理", "/retailBr/register/index", 1, 0, 1, 1 ,"",0);
		MenuModel m6404 = new MenuModel("6404", "处方登记", "64", "零售管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC640401 = new MenuModel("C640401", "签章管理", "6404", "处方登记", "/retail/prescription/signature", 1, 1, 0, 0 ,"",0);
		MenuModel mS640401 = new MenuModel("S640401", "签章管理", "6404", "处方登记", "/retailBr/prescription/signature", 1, 0, 1, 1 ,"",0);
		MenuModel m640402 = new MenuModel("640402", "处方登记", "6404", "处方登记", "/retailBr/prescription/register/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC640403 = new MenuModel("C640403", "处方审核", "6404", "处方登记", "/retail/prescription/audit/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS640403 = new MenuModel("S640403", "处方审核", "6404", "处方登记", "/retailBr/prescription/audit/index", 1, 0, 1, 1 ,"",0);
		MenuModel m640404 = new MenuModel("640404", "处方调剂", "6404", "处方登记", "/retailBr/prescription/adjust", 1, 0, 1, 1 ,"",0);
		MenuModel mC6405 = new MenuModel("C6405", "款员交班", "64", "零售管理", "/retail/shift/main", 1, 1, 0, 0 ,"",0);
		MenuModel mS6405 = new MenuModel("S6405", "款员交班", "64", "零售管理", "/retail/shift/branch", 1, 0, 1, 1 ,"",0);
		MenuModel mC6406 = new MenuModel("C6406", "销售日结", "64", "零售管理", "/retail/sales/main", 1, 1, 0, 0 ,"",0);
		MenuModel mS6406 = new MenuModel("S6406", "销售日结", "64", "零售管理", "/retail/sales/branch", 1, 0, 1, 1 ,"",0);
		MenuModel mC6407 = new MenuModel("C6407", "零售缴款", "64", "零售管理", "/retail/payIn/main", 1, 1, 0, 0 ,"",0);
		MenuModel mS6407 = new MenuModel("S6407", "零售缴款", "64", "零售管理", "/retail/payIn/branch", 1, 0, 1, 1 ,"",0);
		MenuModel m6408 = new MenuModel("6408", "销售报表", "64", "零售管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel m640801 = new MenuModel("640801", "超量销售", "6408", "销售报表", "/retail/sellStatementStore/es", 1, 0, 1, 1 ,"",0);
		MenuModel m640802 = new MenuModel("640802", "时段销售", "6408", "销售报表", "/retail/sellStatementStore/tFrame", 1, 0, 1, 1 ,"",0);
		MenuModel mC640803 = new MenuModel("C640803", "销售流水", "6408", "销售报表", "/retail/sellStatement/turnover/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS640803 = new MenuModel("S640803", "销售流水", "6408", "销售报表", "/retailBr/sellStatement/turnover/index", 1, 0, 1, 1 ,"",0);
		MenuModel m6409 = new MenuModel("6409", "销售分析", "64", "零售管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC640901 = new MenuModel("C640901", "柜组销售", "6409", "销售分析", "/retail/sellStatement/sellAnalyze/groupSell/dailySettlementIndex", 1, 1, 0, 0 ,"",0);
		MenuModel mS640901 = new MenuModel("S640901", "柜组销售", "6409", "销售分析", "/retail/sellStatementStore/sellAnalyze/groupSell/dailySettlementIndex", 1, 1, 0, 0 ,"",0);
		MenuModel mC640902 = new MenuModel("C640902", "班组销售", "6409", "销售分析", "/retail/sellStatement/sellAnalyze/teamSell/dailySettlementIndex", 1, 1, 1, 1 ,"",0);
		MenuModel mS640902 = new MenuModel("S640902", "班组销售", "6409", "销售分析", "/retail/sellStatementStore/sellAnalyze/teamSell/dailySettlementIndex", 1, 0, 1, 1 ,"",0);
		MenuModel mC640903 = new MenuModel("C640903", "营业人员销售", "6409", "销售分析", "/retail/sellStatement/sellAnalyze/businessSell/dailySettlementIndex", 1, 1, 0, 0 ,"",0);
		MenuModel mS640903 = new MenuModel("S640903", "营业人员销售", "6409", "销售分析", "/retail/sellStatementStore/sellAnalyze/businessSell/dailySettlementIndex", 1, 0, 1, 1 ,"",0);
		MenuModel mC640904 = new MenuModel("C640904", "收款人员销售", "6409", "销售分析", "/retail/sellStatement/sellAnalyze/payeeSell/dailySettlementIndex", 1, 1, 0, 0 ,"",0);
		MenuModel mS640904 = new MenuModel("S640904", "收款人员销售", "6409", "销售分析", "/retail/sellStatementStore/sellAnalyze/payeeSell/dailySettlementIndex", 1, 0, 1, 1 ,"",0);
		MenuModel mC640905 = new MenuModel("C640905", "商品分类销售", "6409", "销售分析", "/retail/sellStatement/sellAnalyze/goodsCategorySell/dailySettlementIndex", 1, 1, 0, 0 ,"",0);
		MenuModel mS640905 = new MenuModel("S640905", "商品分类销售", "6409", "销售分析", "/retail/sellStatementStore/sellAnalyze/goodsCategorySell/dailySettlementIndex", 1, 0, 1, 1 ,"",0);
		MenuModel mC6410 = new MenuModel("C6410", "提成管理", "64", "零售管理", "/retail/commission/main", 1, 1, 0, 0 ,"",0);
		MenuModel mS6410 = new MenuModel("S6410", "提成管理", "64", "零售管理", "/retail/commission/branch", 1, 0, 1, 1 ,"",0);
		menuModelList.add(m64);
		menuModelList.add(m6401);
		menuModelList.add(mC640101);
		menuModelList.add(mS640101);
		menuModelList.add(m640102);
		menuModelList.add(m640103);
		menuModelList.add(m640104);
		menuModelList.add(m640105);
		menuModelList.add(m640106);
		menuModelList.add(m640107);
		menuModelList.add(m640108);
		menuModelList.add(m640109);
		menuModelList.add(m640110);
		menuModelList.add(m6402);
		menuModelList.add(m6403);
		menuModelList.add(m6404);
		menuModelList.add(mC640401);
		menuModelList.add(mS640401);
		menuModelList.add(m640402);
		menuModelList.add(mC640403);
		menuModelList.add(mS640403);
		menuModelList.add(m640404);
		menuModelList.add(mC6405);
		menuModelList.add(mS6405);
		menuModelList.add(mC6406);
		menuModelList.add(mS6406);
		menuModelList.add(mC6407);
		menuModelList.add(mS6407);
		menuModelList.add(m6408);
		menuModelList.add(m640801);
		menuModelList.add(m640802);
		menuModelList.add(mC640803);
		menuModelList.add(mS640803);
		menuModelList.add(m6409);
		menuModelList.add(mC640901);
		menuModelList.add(mS640901);
		menuModelList.add(mC640902);
		menuModelList.add(mS640902);
		menuModelList.add(mC640903);
		menuModelList.add(mS640903);
		menuModelList.add(mC640904);
		menuModelList.add(mS640904);
		menuModelList.add(mC640905);
		menuModelList.add(mS640905);
		menuModelList.add(mC6410);
		menuModelList.add(mS6410);

		MenuModel m65 = new MenuModel("65", "售后管理", "", "", "", 1, 1, 1, 1 ,"ivu-icon ivu-icon-so",1);
		MenuModel mC6501 = new MenuModel("C6501", "投诉管理", "65", "售后管理", "/as/complain/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS6501 = new MenuModel("S6501", "投诉管理", "65", "售后管理", "/asBr/complain/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC6502 = new MenuModel("C6502", "药品追回", "65", "售后管理", "/as/retrieve/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS6502 = new MenuModel("S6502", "药品追回", "65", "售后管理", "/asBr/retrieve/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC6503 = new MenuModel("C6503", "药品召回", "65", "售后管理", "/as/recall/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS6503 = new MenuModel("S6503", "药品召回", "65", "售后管理", "/asBr/recall/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC6504 = new MenuModel("C6504", "不良反应报告", "65", "售后管理", "/as/untoward/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS6504 = new MenuModel("S6504", "不良反应报告", "65", "售后管理", "/asBr/untoward/index", 1, 0, 1, 1 ,"",0);
		menuModelList.add(m65);
		menuModelList.add(mC6501);
		menuModelList.add(mS6501);
		menuModelList.add(mC6502);
		menuModelList.add(mS6502);
		menuModelList.add(mC6503);
		menuModelList.add(mS6503);
		menuModelList.add(mC6504);
		menuModelList.add(mS6504);

		MenuModel m66 = new MenuModel("66", "药学服务", "", "", "", 1, 0, 1, 1 ,"",1);
		MenuModel m6601 = new MenuModel("6601", "药品词典", "66", "药学服务", "/phar/dictionary", 1, 0, 1, 1 ,"",0);
		MenuModel m6602 = new MenuModel("6602", "用药咨询", "66", "药学服务", "/phar/consult/list", 1, 0, 1, 1 ,"",0);
		MenuModel m6603 = new MenuModel("6603", "选药指导", "66", "药学服务", "/phar/guidance", 1, 0, 1, 1 ,"",0);
		menuModelList.add(m66);
		menuModelList.add(m6601);
		menuModelList.add(m6602);
		menuModelList.add(m6603);

		MenuModel m71 = new MenuModel("71", "质量管理", "", "", "", 1, 1, 1, 1 ,"ivu-icon ivu-icon-fl",1);
		MenuModel mC7101 = new MenuModel("C7101", "质量管理体系", "71", "质量管理", "/qm/qmSystem/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS7101 = new MenuModel("S7101", "质量管理体系", "71", "质量管理", "/qmBr/qmSystem/index", 1, 0, 1, 1 ,"",0);
		MenuModel m7102 = new MenuModel("7102", "组织机构与质量管理职责", "71", "质量管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel m710201 = new MenuModel("710201", "组织机构", "7102", "组织机构与质量管理职责", "/qm/orgQCR/institutionalFramework/index", 1, 1, 0, 0 ,"",0);
		MenuModel mC710202 = new MenuModel("C710202", "组织机构资质", "7102", "组织机构与质量管理职责", "/qm/orgQCR/qualifications/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710202 = new MenuModel("S710202", "企业资质", "7102", "组织机构与质量管理职责", "/qmBr/orgQCR/qualifications/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC710203 = new MenuModel("C710203", "组织机构资质预警", "7102", "组织机构与质量管理职责", "/qm/orgQCR/qualificationsWarning/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710203 = new MenuModel("S710203", "企业资质预警", "7102", "组织机构与质量管理职责", "/qmBr/orgQCR/qualificationsWarning/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC710204 = new MenuModel("C710204", "部门", "7102", "组织机构与质量管理职责", "/qm/orgQCR/dept", 1, 1, 0, 0 ,"",0);
		MenuModel mS710204 = new MenuModel("S710204", "部门", "7102", "组织机构与质量管理职责", "/qmBr/orgQCR/dept", 1, 0, 1, 1 ,"",0);
		MenuModel mC710205 = new MenuModel("C710205", "岗位", "7102", "组织机构与质量管理职责", "/qm/orgQCR/job", 1, 1, 0, 0 ,"",0);
		MenuModel mS710205 = new MenuModel("S710205", "岗位", "7102", "组织机构与质量管理职责", "/qmBr/orgQCR/job", 1, 0, 1, 1 ,"",0);
		MenuModel mC710206 = new MenuModel("C710206", "角色", "7102", "组织机构与质量管理职责", "/qm/orgQCR/role", 1, 1, 0, 0 ,"",0);
		MenuModel mS710206 = new MenuModel("S710206", "角色", "7102", "组织机构与质量管理职责", "/qmBr/orgQCR/role", 1, 0, 1, 1 ,"",0);
		MenuModel m7103 = new MenuModel("7103", "人员与培训", "71", "质量管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC710301 = new MenuModel("C710301", "员工信息", "7103", "人员与培训", "/qm/personnelTrain/employeeInfo", 1, 1, 0, 0 ,"",0);
		MenuModel mS710301 = new MenuModel("S710301", "员工信息", "7103", "人员与培训", "/qmBr/personnelTrain/employeeInfo/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC710302 = new MenuModel("C710302", "员工资质", "7103", "人员与培训", "/qm/personnelTrain/employeeAptitude", 1, 1, 0, 0 ,"",0);
		MenuModel mS710302 = new MenuModel("S710302", "员工资质", "7103", "人员与培训", "/qmBr/personnelTrain/aptitude/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC710303 = new MenuModel("C710303", "员工资质预警", "7103", "人员与培训", "/qm/personnelTrain/employeeAptitudeWar", 1, 1, 0, 0 ,"",0);
		MenuModel mS710303 = new MenuModel("S710303", "员工资质预警", "7103", "人员与培训", "/qmBr/personnelTrain/aptitudeWarning/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC710304 = new MenuModel("C710304", "培训计划", "7103", "人员与培训", "/qm/personnelTrain/trainPlan/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710304 = new MenuModel("S710304", "培训计划", "7103", "人员与培训", "/qmBr/personnelTrain/trainPlan/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC710305 = new MenuModel("C710305", "培训记录", "7103", "人员与培训", "/qm/personnelTrain/trainRecord/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710305 = new MenuModel("S710305", "培训记录", "7103", "人员与培训", "/qmBr/personnelTrain/trainRecord/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC710306 = new MenuModel("C710306", "健康检查", "7103", "人员与培训", "/qm/personnelTrain/healthCheck/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710306 = new MenuModel("S710306", "健康检查", "7103", "人员与培训", "/qmBr/personnelTrain/healthCheck/index", 1, 0, 1, 1 ,"",0);
		menuModelList.add(m71);
		menuModelList.add(mC7101);
		menuModelList.add(mS7101);
		menuModelList.add(m7102);
		menuModelList.add(m710201);
		menuModelList.add(mC710202);
		menuModelList.add(mS710202);
		menuModelList.add(mC710203);
		menuModelList.add(mS710203);
		menuModelList.add(mC710204);
		menuModelList.add(mS710204);
		menuModelList.add(mC710205);
		menuModelList.add(mS710205);
		menuModelList.add(mC710206);
		menuModelList.add(mS710206);
		menuModelList.add(m7103);
		menuModelList.add(mC710301);
		menuModelList.add(mS710301);
		menuModelList.add(mC710302);
		menuModelList.add(mS710302);
		menuModelList.add(mC710303);
		menuModelList.add(mS710303);
		menuModelList.add(mC710304);
		menuModelList.add(mS710304);
		menuModelList.add(mC710305);
		menuModelList.add(mS710305);
		menuModelList.add(mC710306);
		menuModelList.add(mS710306);

		MenuModel mC7104 = new MenuModel("C7104", "质量管理体系文件", "71", "质量管理", "/qm/qmSystemFile/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS7104 = new MenuModel("S7104", "质量管理体系文件", "71", "质量管理", "/qmBr/qmSystemFile/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC7105 = new MenuModel("C7105", "计算机管理系统", "71", "质量管理", "/qm/computerManagementSystem/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS7105 = new MenuModel("S7105", "计算机管理系统", "71", "质量管理", "/qmBr/computerManagementSystem/index", 1, 0, 1, 1 ,"",0);
		MenuModel m7106 = new MenuModel("7106", "设施与设备", "71", "质量管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC710601 = new MenuModel("C710601", "设施设备", "7106", "设施与设备", "/qm/facilityEquipment/facilityEqu/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710601 = new MenuModel("S710601", "设施设备", "7106", "设施与设备", "/qmBr/facilityEquipment/facilityEqu/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC710602 = new MenuModel("C710602", "检查、清洁和维护", "7106", "设施与设备", "/qm/facilityEquipment/checkClearMaintain/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710602 = new MenuModel("S710602", "检查、清洁和维护", "7106", "设施与设备", "/qmBr/facilityEquipment/checkClearMaintain/index", 1, 0, 1, 1 ,"",0);
		menuModelList.add(mC7104);
		menuModelList.add(mS7104);
		menuModelList.add(mC7105);
		menuModelList.add(mS7105);
		menuModelList.add(m7106);
		menuModelList.add(mC710601);
		menuModelList.add(mS710601);
		menuModelList.add(mC710602);
		menuModelList.add(mS710602);

		MenuModel m7107 = new MenuModel("7107", "校准与验证", "71", "质量管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC710701 = new MenuModel("C710701", "校准或检定", "7107", "校准与验证", "/qm/adjustVerify/adjust/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710701 = new MenuModel("S710701", "校准或检定", "7107", "校准与验证", "/qmBr/adjustVerify/adjust/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC710702 = new MenuModel("C710702", "验证", "7107", "校准与验证", "/qm/adjustVerify/verify/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710702 = new MenuModel("S710702", "验证", "7107", "校准与验证", "/qmBr/adjustVerify/verify/index", 1, 0, 1, 1 ,"",0);
		menuModelList.add(m7107);
		menuModelList.add(mC710701);
		menuModelList.add(mS710701);
		menuModelList.add(mC710702);
		menuModelList.add(mS710702);

		MenuModel m7108 = new MenuModel("7108", "采购", "71", "质量管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC710801 = new MenuModel("C710801", "首营企业审批", "7108", "采购", "/qm/purchase/firstBusinessApproval/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710801 = new MenuModel("S710801", "首营企业审批", "7108", "采购", "/qmBr/purchase/firstCampCompAppr/index", 1, 0, 1, 1 ,"",0);
		MenuModel m710802 = new MenuModel("710802", "供货单位资质", "7108", "采购", "/qm/purchase/qualificationSuppliers/index", 1, 1, 0, 0 ,"",0);
		MenuModel m710803 = new MenuModel("710803", "供货单位资质预警", "7108", "采购", "/qm/purchase/quaSW/index", 1, 1, 0, 0 ,"",0);
		MenuModel m710804 = new MenuModel("710804", "供货单位销售人员", "7108", "采购", "/qm/purchase/sUS/index", 1, 1, 0, 0 ,"",0);
		MenuModel m710805 = new MenuModel("710805", "供货单位经营品种", "7108", "采购", "/qm/purchase/managementVarieties/index", 1, 1, 0, 0 ,"",0);
		MenuModel mC710806 = new MenuModel("C710806", "供货单位质量档案", "7108", "采购", "/qm/purchase/qualityFile/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710806 = new MenuModel("S710806", "供货单位质量档案", "7108", "采购", "/qmBr/purchase/qualityFile/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC710807 = new MenuModel("C710807", "首营品种审批", "7108", "采购", "/qm/purchase/firstTypeApproval/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710807 = new MenuModel("S710807", "首营品种审批", "7108", "采购", "/qmBr/purchase/firstTypeApproval/index", 1, 0, 1, 1 ,"",0);
		MenuModel m710808 = new MenuModel("710808", "品种资质", "7108", "采购", "/qm/purchase/varietyQualification/index", 1, 1, 0, 0 ,"",0);
		MenuModel m710809 = new MenuModel("710809", "品种资质预警", "7108", "采购", "/qm/purchase/varQuaWarn/index", 1, 1, 0, 1 ,"",0);
		MenuModel mC710810 = new MenuModel("C710810", "药品质量评审", "7108", "采购", "/qm/purchase/drugQuaRev/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710810 = new MenuModel("S710810", "药品质量评审", "7108", "采购", "/qmBr/purchase/drugQuaRev/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC710811 = new MenuModel("C710811", "采购计划", "7108", "采购", "/qm/purchase/plan/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710811 = new MenuModel("S710811", "要货计划", "7108", "采购", "/qmBr/purchase/plan/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC710812 = new MenuModel("C710812", "采购记录", "7108", "采购", "/qm/purchase/order/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710812 = new MenuModel("S710812", "配进订单", "7108", "采购", "/qmBr/purchase/order/index", 1, 0, 1, 1 ,"",0);
		menuModelList.add(m7108);
		menuModelList.add(mC710801);
		menuModelList.add(mS710801);
		menuModelList.add(m710802);
		menuModelList.add(m710803);
		menuModelList.add(m710804);
		menuModelList.add(m710805);
		menuModelList.add(mC710806);
		menuModelList.add(mS710806);
		menuModelList.add(mC710807);
		menuModelList.add(mS710807);
		menuModelList.add(m710808);
		menuModelList.add(m710809);
		menuModelList.add(mC710810);
		menuModelList.add(mS710810);
		menuModelList.add(mC710811);
		menuModelList.add(mS710811);
		menuModelList.add(mC710812);
		menuModelList.add(mS710812);

		MenuModel m7109 = new MenuModel("7109", "收货与验收", "71", "质量管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC710901 = new MenuModel("C710901", "采购收货", "7109", "质量管理", "/qm/receivingCheck/takeGoods/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710901 = new MenuModel("S710901", "配进收货", "7109", "质量管理", "/qmBr/receivingCheck/receipt/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC710902 = new MenuModel("C710902", "采购验收", "7109", "质量管理", "/qm/receivingCheck/purchaseAcceptance/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710902 = new MenuModel("S710902", "配进验收", "7109", "质量管理", "/qmBr/receivingCheck/acceptance/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC710903 = new MenuModel("C710903", "采购入库", "7109", "质量管理", "/qm/receivingCheck/purchaseStorage/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710903 = new MenuModel("S710903", "配进入库", "7109", "质量管理", "/qmBr/receivingCheck/warehousing/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC710904 = new MenuModel("C710904", "购进退出", "7109", "质量管理", "/qm/receivingCheck/purchaseExit/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710904 = new MenuModel("S710904", "配进退出", "7109", "质量管理", "/qmBr/receivingCheck/quit/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC710905 = new MenuModel("C710905", "购进退出出库", "7109", "质量管理", "/qm/receivingCheck/purchaseExitOut/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710905 = new MenuModel("S710905", "配进退出出库", "7109", "质量管理", "/qmBr/receivingCheck/quitOut/index", 1, 0, 1, 1 ,"",0);
		MenuModel m710906 = new MenuModel("710906", "拒收单", "7109", "质量管理", "/qm/receivingCheck/drugRejection/index", 1, 1, 0, 0 ,"",0);
		MenuModel m710907 = new MenuModel("710907", "质量复查单", "7109", "质量管理", "/qm/receivingCheck/drugReviewOrder/index", 1, 1, 0, 0 ,"",0);
		MenuModel mC710908 = new MenuModel("C710908", "验收资质表", "7109", "质量管理", "/qm/receivingCheck/drugAcceptanceQualification/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS710908 = new MenuModel("S710908", "药品验收资质", "7109", "质量管理", "/qmBr/receivingCheck/acceptanceQualification/index", 1, 0, 1, 1 ,"",0);
		menuModelList.add(m7109);
		menuModelList.add(mC710901);
		menuModelList.add(mS710901);
		menuModelList.add(mC710902);
		menuModelList.add(mS710902);
		menuModelList.add(mC710903);
		menuModelList.add(mS710903);
		menuModelList.add(mC710904);
		menuModelList.add(mS710904);
		menuModelList.add(mC710905);
		menuModelList.add(mS710905);
		menuModelList.add(m710906);
		menuModelList.add(m710907);
		menuModelList.add(mC710908);
		menuModelList.add(mS710908);

		MenuModel m7110 = new MenuModel("7110", "储存与养护", "71", "质量管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC711001 = new MenuModel("C711001", "货位移动", "7110", "储存与养护", "/qm/storageConserve/goodsAllocationMove/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711001 = new MenuModel("S711001", "货位移动", "7110", "储存与养护", "/qmBr/storageConserve/goodsAllocationMove/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711002 = new MenuModel("C711002", "其他入库", "7110", "储存与养护", "/qm/storageConserve/othersPutIntoStore/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711002 = new MenuModel("S711002", "其他入库", "7110", "储存与养护", "/qmBr/storageConserve/othersPutIntoStore/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711003 = new MenuModel("C711003", "其他出库", "7110", "储存与养护", "/qm/storageConserve/oTOStore/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711003 = new MenuModel("S711003", "其他出库", "7110", "储存与养护", "/qmBr/storageConserve/oTOStore/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711004 = new MenuModel("C711004", "中药饮片装斗", "7110", "储存与养护", "/qm/storageConserve/mPIBucket/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711004 = new MenuModel("S711004", "中药饮片装斗", "7110", "储存与养护", "/qmBr/storageConserve/mPIBucket/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711005 = new MenuModel("C711005", "中药饮片清斗", "7110", "储存与养护", "/qm/storageConserve/mCOBucket/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711005 = new MenuModel("S711005", "中药饮片清斗", "7110", "储存与养护", "/qmBr/storageConserve/mCOBucket/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711006 = new MenuModel("C711006", "药品拆零", "7110", "储存与养护", "/qm/storageConserve/gSZero/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711006 = new MenuModel("S711006", "药品拆零", "7110", "储存与养护", "/qmBr/storageConserve/gSZero/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711007 = new MenuModel("C711007", "药品锁定", "7110", "储存与养护", "/qm/storageConserve/goodsLock/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711007 = new MenuModel("S711007", "药品锁定", "7110", "储存与养护", "/qmBr/storageConserve/goodsLock/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711008 = new MenuModel("C711008", "药品处理", "7110", "储存与养护", "/qm/storageConserve/goodsHandle/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711008 = new MenuModel("S711008", "药品处理", "7110", "储存与养护", "/qmBr/storageConserve/goodsHandle/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711009 = new MenuModel("C711009", "药品销毁", "7110", "储存与养护", "/qm/storageConserve/goodsDestroy/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711009 = new MenuModel("S711009", "药品销毁", "7110", "储存与养护", "/qmBr/storageConserve/goodsDestroy/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711010 = new MenuModel("C711010", "药品停售通知单", "7110", "储存与养护", "/qm/storageConserve/goodsStartSale/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711010 = new MenuModel("S711010", "药品停售通知单", "7110", "储存与养护", "/qmBr/storageConserve/goodsStartSale/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711011 = new MenuModel("C711011", "解除停售通知单", "7110", "储存与养护", "/qm/storageConserve/goodsStopSale/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711011 = new MenuModel("S711011", "解除停售通知单", "7110", "储存与养护", "/qmBr/storageConserve/goodsStopSale/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711012 = new MenuModel("C711012", "盘点记录", "7110", "储存与养护", "/qm/storageConserve/inventory/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711012 = new MenuModel("S711012", "盘点记录", "7110", "储存与养护", "/qmBr/storageConserve/inventory/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711013 = new MenuModel("C711013", "批号调整", "7110", "储存与养护", "/qm/storageConserve/lotAdjust/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711013 = new MenuModel("S711013", "批号调整", "7110", "储存与养护", "/qmBr/storageConserve/lotAdjust/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711014 = new MenuModel("C711014", "药品养护", "7110", "储存与养护", "/qm/storageConserve/goodsMaintance/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711014 = new MenuModel("S711014", "药品养护", "7110", "储存与养护", "/qmBr/storageConserve/goodsMaintance/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711015 = new MenuModel("C711015", "陈列检查", "7110", "储存与养护", "/qm/storageConserve/goodsDisplayCheck/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711015 = new MenuModel("S711015", "陈列检查", "7110", "储存与养护", "/qmBr/storageConserve/goodsDisplayCheck/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711016 = new MenuModel("C711016", "温湿度监控记录", "7110", "储存与养护", "/qm/storageConserve/temperatureHumidity/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711016 = new MenuModel("S711016", "温湿度监控记录", "7110", "储存与养护", "/qmBr/storageConserve/temperatureHumidity/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711017 = new MenuModel("C711017", "不合格商品", "7110", "储存与养护", "/qm/storageConserve/goodsUnqualified/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711017 = new MenuModel("S711017", "不合格商品", "7110", "储存与养护", "/qmBr/storageConserve/goodsUnqualified/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711018 = new MenuModel("C711018", "过期商品", "7110", "储存与养护", "/qm/storageConserve/goodsExpire/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711018 = new MenuModel("S711018", "过期商品", "7110", "储存与养护", "/qmBr/storageConserve/goodsExpire/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711019 = new MenuModel("C711019", "近效期商品", "7110", "储存与养护", "/qm/storageConserve/nearTermGoods/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711019 = new MenuModel("S711019", "近效期商品", "7110", "储存与养护", "/qmBr/storageConserve/nearTermGoods/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711020 = new MenuModel("C711020", "滞销商品", "7110", "储存与养护", "/qm/storageConserve/unsalableGoods/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711020 = new MenuModel("S711020", "滞销商品", "7110", "储存与养护", "/qmBr/storageConserve/unsalableGoods/index", 1, 0, 1, 1 ,"",0);
		menuModelList.add(m7110);
		menuModelList.add(mC711001);
		menuModelList.add(mS711001);
		menuModelList.add(mC711002);
		menuModelList.add(mS711002);
		menuModelList.add(mC711003);
		menuModelList.add(mS711003);
		menuModelList.add(mC711004);
		menuModelList.add(mS711004);
		menuModelList.add(mC711005);
		menuModelList.add(mS711005);
		menuModelList.add(mC711006);
		menuModelList.add(mS711006);
		menuModelList.add(mC711007);
		menuModelList.add(mS711007);
		menuModelList.add(mC711008);
		menuModelList.add(mS711008);
		menuModelList.add(mC711009);
		menuModelList.add(mS711009);
		menuModelList.add(mC711010);
		menuModelList.add(mS711010);
		menuModelList.add(mC711011);
		menuModelList.add(mS711011);
		menuModelList.add(mC711012);
		menuModelList.add(mS711012);
		menuModelList.add(mC711013);
		menuModelList.add(mS711013);
		menuModelList.add(mC711014);
		menuModelList.add(mS711014);
		menuModelList.add(mC711015);
		menuModelList.add(mS711015);
		menuModelList.add(mC711016);
		menuModelList.add(mS711016);
		menuModelList.add(mC711017);
		menuModelList.add(mS711017);
		menuModelList.add(mC711018);
		menuModelList.add(mS711018);
		menuModelList.add(mC711019);
		menuModelList.add(mS711019);
		menuModelList.add(mC711020);
		menuModelList.add(mS711020);

		MenuModel m7111 = new MenuModel("7111", "销售", "71", "质量管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC711101 = new MenuModel("C711101", "划价单", "7111", "销售", "/qm/sell/pricing/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711101 = new MenuModel("S711101", "划价单", "7111", "销售", "/qmBr/sell/pricing/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711102 = new MenuModel("C711102", "处方登记", "7111", "销售", "/qm/sell/prescriptionRegister/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711102 = new MenuModel("S711102", "处方登记", "7111", "销售", "/qmBr/sell/prescriptionRegister/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711103 = new MenuModel("C711103", "专管登记", "7111", "销售", "/qm/sell/chargeRegister/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711103 = new MenuModel("S711103", "专管登记", "7111", "销售", "/qmBr/sell/chargeRegister/index", 1, 0, 1, 1 ,"",0);
		menuModelList.add(m7111);
		menuModelList.add(mC711101);
		menuModelList.add(mS711101);
		menuModelList.add(mC711102);
		menuModelList.add(mS711102);
		menuModelList.add(mC711103);
		menuModelList.add(mS711103);

		MenuModel m7112 = new MenuModel("7112", "出库", "71", "质量管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC711201 = new MenuModel("C711201", "销售出库", "7112", "出库", "/qm/stockRemoval/sellRemoval/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711201 = new MenuModel("S711201", "销售出库", "7112", "出库", "/qmBr/stockRemoval/sellRemoval/index", 1, 0, 1, 1 ,"",0);
		MenuModel mC711202 = new MenuModel("C711202", "销售退货", "7112", "出库", "/qm/stockRemoval/sellReturn/index", 1, 1, 0, 0 ,"",0);
		MenuModel mS711202 = new MenuModel("S711202", "销售退货", "7112", "出库", "/qmBr/stockRemoval/sellReturn/index", 1, 0, 1, 1 ,"",0);
		menuModelList.add(m7112);
		menuModelList.add(mC711201);
		menuModelList.add(mS711201);
		menuModelList.add(mC711202);
		menuModelList.add(mS711202);

		MenuModel m7113 = new MenuModel("7113", "运输与配送", "71", "质量管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel m711301 = new MenuModel("711301", "要货计划", "7113", "运输与配送", "/qm/stockRemoval/distrPlan/index", 1, 1, 0, 0 ,"",0);
		MenuModel m711302 = new MenuModel("711302", "缺配单", "7113", "运输与配送", "/qm/stockRemoval/distrLack/index", 1, 1, 0, 0 ,"",0);
		MenuModel m711303 = new MenuModel("711303", "配货单", "7113", "运输与配送", "/qm/stockRemoval/distrAllocation/index", 1, 1, 0, 0 ,"",0);
		MenuModel m711304 = new MenuModel("711304", "配货出库", "7113", "运输与配送", "/qm/stockRemoval/distrOutStock/index", 1, 1, 0, 0 ,"",0);
		MenuModel m711305 = new MenuModel("711305", "配进订单", "7113", "运输与配送", "/qm/stockRemoval/distrOrder/index", 1, 1, 0, 0 ,"",0);
		MenuModel m711306 = new MenuModel("711306", "配进收货", "7113", "运输与配送", "/qm/stockRemoval/distrReceive/index", 1, 1, 0, 0 ,"",0);
		MenuModel m711307 = new MenuModel("711307", "配进验收", "7113", "运输与配送", "/qm/stockRemoval/distrAccept/index", 1, 1, 0, 0 ,"",0);
		MenuModel m711308 = new MenuModel("711308", "配进入库", "7113", "运输与配送", "/qm/stockRemoval/distrInStorage/index", 1, 1, 0, 0 ,"",0);
		MenuModel m711309 = new MenuModel("711309", "配后退回", "7113", "运输与配送", "/qm/stockRemoval/distrReturn/index", 1, 1, 0, 0 ,"",0);
		MenuModel m711310 = new MenuModel("711310", "配后退回收货", "7113", "运输与配送", "/qm/stockRemoval/distrReturnReceive/index", 1, 1, 0, 0 ,"",0);
		MenuModel m711311 = new MenuModel("711311", "配后退回验收", "7113", "运输与配送", "/qm/stockRemoval/distrReturnAccept/index", 1, 1, 0, 0 ,"",0);
		MenuModel m711312 = new MenuModel("711312", "配后退回入库", "7113", "运输与配送", "/qm/stockRemoval/distrReturnIn/index", 1, 1, 0, 0 ,"",0);
//		MenuModel m711313 = new MenuModel("711313", "运输记录", "7113", "运输与配送", "###", 1, 1, 0, 0 ,"");
		menuModelList.add(m7113);
		menuModelList.add(m711301);
		menuModelList.add(m711302);
		menuModelList.add(m711303);
		menuModelList.add(m711304);
		menuModelList.add(m711305);
		menuModelList.add(m711306);
		menuModelList.add(m711307);
		menuModelList.add(m711308);
		menuModelList.add(m711309);
		menuModelList.add(m711310);
		menuModelList.add(m711311);
		menuModelList.add(m711312);

		MenuModel m7114 = new MenuModel("7114", "售后管理", "71", "质量管理", "", 1, 1, 1, 1 ,"",1);
		MenuModel mC711401 = new MenuModel("C711401", "客户投诉", "7114", "售后管理", "/qm/afterSaleManagement/complaintManagement/ts", 1, 1, 0, 0 ,"",0);
		MenuModel mS711401 = new MenuModel("S711401", "客户投诉", "7114", "售后管理", "/qmBr/afterSaleManagement/complaintManagement/ts", 1, 0, 1, 1 ,"",0);
		MenuModel mC711402 = new MenuModel("C711402", "药品追回", "7114", "售后管理", "/qm/afterSaleManagement/complaintManagement/zhj", 1, 1, 0, 0 ,"",0);
		MenuModel mS711402 = new MenuModel("S711402", "药品追回", "7114", "售后管理", "/qmBr/afterSaleManagement/complaintManagement/zhj", 1, 0, 1, 1 ,"",0);
		MenuModel mC711403 = new MenuModel("C711403", "药品召回", "7114", "售后管理", "/qm/afterSaleManagement/complaintManagement/zhr", 1, 1, 0, 0 ,"",0);
		MenuModel mS711403 = new MenuModel("S711403", "药品召回", "7114", "售后管理", "/qmBr/afterSaleManagement/complaintManagement/zhr", 1, 0, 1, 1 ,"",0);
		MenuModel mC711404 = new MenuModel("C711404", "不良反应报告", "7114", "售后管理", "/qm/afterSaleManagement/complaintManagement/bb", 1, 1, 0, 0 ,"",0);
		MenuModel mS711404 = new MenuModel("S711404", "不良反应报告", "7114", "售后管理", "/qmBr/afterSaleManagement/complaintManagement/bb", 1, 0, 1, 1 ,"",0);
		menuModelList.add(m7114);
		menuModelList.add(mC711401);
		menuModelList.add(mS711401);
		menuModelList.add(mC711402);
		menuModelList.add(mS711402);
		menuModelList.add(mC711403);
		menuModelList.add(mS711403);
		menuModelList.add(mC711404);
		menuModelList.add(mS711404);

		return menuModelList;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getForParent() {
		return forParent;
	}

	public void setForParent(Integer forParent) {
		this.forParent = forParent;
	}

	public Integer getForBranch() {
		return forBranch;
	}

	public void setForBranch(Integer forBranch) {
		this.forBranch = forBranch;
	}

	public Integer getForLeague() {
		return forLeague;
	}

	public void setForLeague(Integer forLeague) {
		this.forLeague = forLeague;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

	@Override
	public String toString() {
		return "MenuModel{" +
				"code='" + code + '\'' +
				", name='" + name + '\'' +
				", parentCode='" + parentCode + '\'' +
				", parentName='" + parentName + '\'' +
				", url='" + url + '\'' +
				", type=" + type +
				", forParent=" + forParent +
				", forBranch=" + forBranch +
				", forLeague=" + forLeague +
				", icon=" + icon +
				", isParent=" + isParent +
				'}';
	}

	public static void main(String[] args) {
	   List<MenuModel> buildModel = MenuModel.build();
	   System.out.println(buildModel);
	}
	
	
}
