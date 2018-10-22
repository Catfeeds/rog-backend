package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: RoleModel  
 * @Description: 角色初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月4日 下午7:40:23  
 *
 */
public class RoleModel {
	
	private String code;
	private String name;
	
	private String positionCode;
	private String positionName;
	
	private String deptCode;
	private String deptName;
	
	public RoleModel(){}

	public RoleModel(String code, String name, String positionCode, String positionName, String deptCode,
			String deptName) {
		super();
		this.code = code;
		this.name = name;
		this.positionCode = positionCode;
		this.positionName = positionName;
		this.deptCode = deptCode;
		this.deptName = deptName;
	}

	public static List<RoleModel> build(){
		List<RoleModel> rList = new ArrayList<RoleModel>();
		rList.add(new RoleModel("010101", "法定代表人", "0101", "法定代表人", "01", "管理部"));
		rList.add(new RoleModel("010201", "企业负责人", "0102", "企业负责人", "01", "管理部"));
		
		rList.add(new RoleModel("020101", "财务经理", "0201", "财务经理", "02", "财务部"));
		rList.add(new RoleModel("020201", "会计", "0202", "会计", "02", "财务部"));
		rList.add(new RoleModel("020301", "出纳", "0203", "出纳", "02", "财务部"));
		
		rList.add(new RoleModel("030101", "质量负责人", "0301", "质量负责人", "03", "质量管理部"));
		rList.add(new RoleModel("030201", "质量管理部门负责人", "0302", "质量管理部门负责人", "03", "质量管理部"));
		rList.add(new RoleModel("030301", "质量管理人员-成药", "0303", "质量管理", "03", "质量管理部"));
		rList.add(new RoleModel("030302", "质量管理人员-中药材", "0303", "质量管理", "03", "质量管理部"));
		rList.add(new RoleModel("030303", "质量管理人员-中药饮片", "0303", "质量管理", "03", "质量管理部"));
		rList.add(new RoleModel("030304", "质量管理人员-疫苗", "0303", "质量管理", "03", "质量管理部"));

		rList.add(new RoleModel("030401", "验收人员-成药", "0304", "验收", "03", "质量管理部"));
		rList.add(new RoleModel("030402", "验收人员-中药材", "0304", "验收", "03", "质量管理部"));
		rList.add(new RoleModel("030403", "验收人员-中药饮片", "0304", "验收", "03", "质量管理部"));
		rList.add(new RoleModel("030404", "验收人员-疫苗", "0304", "验收", "03", "质量管理部"));

		rList.add(new RoleModel("030501", "养护人员-成药", "0305", "养护", "03", "质量管理部"));
		rList.add(new RoleModel("030502", "养护人员-中药材", "0305", "养护", "03", "质量管理部"));
		rList.add(new RoleModel("030503", "养护人员-中药饮片", "0305", "养护", "03", "质量管理部"));

		rList.add(new RoleModel("030601", "处方审核人员-常规", "0306", "处方管理", "03", "质量管理部"));
		rList.add(new RoleModel("030602", "处方审核人员-中药", "0306", "处方管理", "03", "质量管理部"));
		rList.add(new RoleModel("030611", "处方核对人员-常规", "0306", "处方管理", "03", "质量管理部"));
		rList.add(new RoleModel("030612", "处方核对人员-中药", "0306", "处方管理", "03", "质量管理部"));
		rList.add(new RoleModel("030621", "处方调配人员-常规", "0306", "处方管理", "03", "质量管理部"));
		rList.add(new RoleModel("030622", "处方调配人员-中药", "0306", "处方管理", "03", "质量管理部"));
		rList.add(new RoleModel("030631", "处方发药人员-常规", "0306", "处方管理", "03", "质量管理部"));
		rList.add(new RoleModel("030632", "处方发药人员-中药", "0306", "处方管理", "03", "质量管理部"));

		rList.add(new RoleModel("030701", "药学管理人员", "0307", "药学管理", "03", "质量管理部"));

		rList.add(new RoleModel("0401", "采购经理", "0401", "采购经理", "04", "采购部"));
		rList.add(new RoleModel("0402", "采购人员", "0402", "采购", "04", "采购部"));
		
		rList.add(new RoleModel("0501", "储存经理", "0501", "储存经理", "05", "储存部"));
		rList.add(new RoleModel("0502", "储存人员", "0502", "储存", "05", "储存部"));
		
		rList.add(new RoleModel("0601", "配送中心经理", "0601", "配送中心经理", "06", "配送中心"));
		rList.add(new RoleModel("060201", "配货人员", "0602", "配货", "06", "配送中心"));
		rList.add(new RoleModel("060301", "运输管理人员", "0603", "运输", "06", "配送中心"));
		
		rList.add(new RoleModel("070101", "销售经理", "0701", "销售经理", "07", "销售部"));
		rList.add(new RoleModel("070201", "销售人员", "0702", "销售", "07", "销售部"));
		
		rList.add(new RoleModel("080101", "店长", "0801", "店长", "08", "门店"));
		rList.add(new RoleModel("080201", "营业人员", "0802", "店员", "08", "门店"));
		rList.add(new RoleModel("080202", "收款人员", "0802", "店员", "08", "门店"));
		
		rList.add(new RoleModel("090101", "信息管理经理", "0901", "信息管理经理", "09", "信息管理部"));
		rList.add(new RoleModel("090201", "信息管理人员", "0902", "信息管理", "09", "信息管理部"));
		
		return rList;
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

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
