package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: ReturnMeasuresModel  
 * @Description: 退货处置措施初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月4日 下午3:31:15  
 *
 */
public class HandleMeasuresModel {

	private String code;
	private String description;
	
	public HandleMeasuresModel(){}
	
	public HandleMeasuresModel(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public static List<HandleMeasuresModel> build(){
		List<HandleMeasuresModel> rmList = new ArrayList<HandleMeasuresModel>();
		rmList.add(new HandleMeasuresModel("01", "现场拒收"));
		rmList.add(new HandleMeasuresModel("02", "通知采购部门处理"));
		return rmList;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
