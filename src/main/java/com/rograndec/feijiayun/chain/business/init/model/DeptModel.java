package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: DeptModel  
 * @Description: 部门初始化数据模型  
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月4日 下午1:07:55  
 *
 */
public class DeptModel {

	private String code;
	private String name;
	
	public DeptModel(){}

	public DeptModel(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public static List<DeptModel> build(){
		List<DeptModel> deptList = new ArrayList<DeptModel>();
		DeptModel d01 = new DeptModel("01", "管理部");
		DeptModel d02 = new DeptModel("02", "财务部");
		DeptModel d03 = new DeptModel("03", "质量管理部");
		DeptModel d04 = new DeptModel("04", "采购部");
		DeptModel d05 = new DeptModel("05", "存储部");
		DeptModel d06 = new DeptModel("06", "配送中心");
		DeptModel d07 = new DeptModel("07", "销售部");
		DeptModel d08 = new DeptModel("08", "门店");
		DeptModel d09 = new DeptModel("09", "信息管理部");
		
		deptList.add(d01);
		deptList.add(d02);
		deptList.add(d03);
		deptList.add(d04);
		deptList.add(d05);
		deptList.add(d06);
		deptList.add(d07);
		deptList.add(d08);
		deptList.add(d09);
		return deptList;
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
	
}
