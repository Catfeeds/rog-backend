package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: PositionModel  
 * @Description: 岗位初始化数据建模  
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月4日 下午1:19:24  
 *
 */
public class PositionModel {

	private String code;
	private String name;
	private String deptCode;
	private String deptName;
	private Integer contactDrug;// 直接接触药品（0-否；1-是）
	
	public PositionModel(){}

	public PositionModel(String code, String name, String deptCode, String deptName, Integer contactDrug) {
		super();
		this.code = code;
		this.name = name;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.contactDrug = contactDrug;
	}

	public static List<PositionModel> build(){
		List<PositionModel> positionList = new ArrayList<PositionModel>();
		
		positionList.add(new PositionModel("0101", "法定代表人", "01", "管理部", 0));
		positionList.add(new PositionModel("0102", "企业负责人", "01", "管理部", 0));
		
		positionList.add(new PositionModel("0201", "财务经理", "02", "财务部", 0));
		positionList.add(new PositionModel("0202", "会计", "02", "财务部", 0));
		positionList.add(new PositionModel("0203", "出纳", "02", "财务部", 0));
		
		positionList.add(new PositionModel("0301", "质量负责人", "03", "质量管理部", 0));
		positionList.add(new PositionModel("0302", "质量管理部门负责人", "03", "质量管理部", 0));
		positionList.add(new PositionModel("0303", "质量管理", "03", "质量管理部", 0));
		positionList.add(new PositionModel("0304", "验收", "03", "质量管理部", 1));
		positionList.add(new PositionModel("0305", "养护", "03", "质量管理部", 1));
		positionList.add(new PositionModel("0306", "处方管理", "03", "质量管理部", 0));
		positionList.add(new PositionModel("0307", "药学管理", "03", "质量管理部", 0));
		
		positionList.add(new PositionModel("0401", "采购经理", "04", "采购部", 0));
		positionList.add(new PositionModel("0402", "采购", "04", "采购部", 0));
		
		positionList.add(new PositionModel("0501", "存储经理", "05", "存储部", 0));
		positionList.add(new PositionModel("0502", "存储", "05", "存储部", 1));
		
		positionList.add(new PositionModel("0601", "配送中心经理", "06", "配送中心 ", 0));
		positionList.add(new PositionModel("0602", "配货", "06", "配送中心 ", 0));
		positionList.add(new PositionModel("0603", "运输", "06", "配送中心 ", 0));
		
		positionList.add(new PositionModel("0701", "销售经理", "07", "销售部", 0));
		positionList.add(new PositionModel("0702", "销售", "07", "销售部", 0));
		
		positionList.add(new PositionModel("0801", "店长", "08", "门店", 1));
		positionList.add(new PositionModel("0802", "店员", "08", "门店", 1));
		
		positionList.add(new PositionModel("0901", "信息管理经理", "09", "信息管理部", 0));
		positionList.add(new PositionModel("0902", "信息管理", "09", "信息管理部", 0));
		
		return positionList;
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

	public Integer getContactDrug() {
		return contactDrug;
	}

	public void setContactDrug(Integer contactDrug) {
		this.contactDrug = contactDrug;
	}
	
}
