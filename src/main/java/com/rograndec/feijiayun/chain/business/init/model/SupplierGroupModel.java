package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: SupplierGroupModel  
 * @Description: 供货单位初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月5日 上午11:48:20  
 *
 */
public class SupplierGroupModel {
	
	private Integer enterpriseType;
	private String code;
	private String name;
	
	public SupplierGroupModel(){}

	public SupplierGroupModel(Integer enterpriseType, String code, String name) {
		super();
		this.enterpriseType = enterpriseType;
		this.code = code;
		this.name = name;
	}
	
	public static List<SupplierGroupModel> build(){
		List<SupplierGroupModel> sgList = new ArrayList<SupplierGroupModel>();
		
		sgList.add(new SupplierGroupModel(0, "01", "药品批发企业"));
		sgList.add(new SupplierGroupModel(1, "11", "药品生产企业"));
		
		return sgList;
	}

	public Integer getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(Integer enterpriseType) {
		this.enterpriseType = enterpriseType;
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
