package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: StoreGroupModel  
 * @Description: 门店分组初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月5日 上午11:16:52  
 *
 */
public class StoreGroupModel {
	
	private Integer type;
	private String code;
	private String name;
	
	public StoreGroupModel(){}
	
	public StoreGroupModel(Integer type, String code, String name) {
		super();
		this.type = type;
		this.code = code;
		this.name = name;
	}

	public static List<StoreGroupModel> build(){
		List<StoreGroupModel> sgList = new ArrayList<StoreGroupModel>();
		sgList.add(new StoreGroupModel(0, "01", "总部直营"));
		sgList.add(new StoreGroupModel(1, "11", "加盟门店"));
		return sgList;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
