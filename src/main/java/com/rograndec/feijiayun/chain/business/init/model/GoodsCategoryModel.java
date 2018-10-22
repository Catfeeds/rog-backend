package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: GoodsCategoryModel  
 * @Description: 商品分类初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月5日 上午11:56:36  
 *
 */
public class GoodsCategoryModel {
	
	private Integer type;
	private String code;
	private String name;
	
	public GoodsCategoryModel(){}

	public GoodsCategoryModel(Integer type, String code, String name) {
		super();
		this.type = type;
		this.code = code;
		this.name = name;
	}

	public static List<GoodsCategoryModel> build(){
		List<GoodsCategoryModel> gcList = new ArrayList<GoodsCategoryModel>();
		
		gcList.add(new GoodsCategoryModel(1, "01", "药品"));
		gcList.add(new GoodsCategoryModel(2, "02", "医疗器械"));
		gcList.add(new GoodsCategoryModel(3, "03", "食品"));
		gcList.add(new GoodsCategoryModel(4, "04", "化妆品"));
		gcList.add(new GoodsCategoryModel(5, "05", "其它"));
		
		return gcList;
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
