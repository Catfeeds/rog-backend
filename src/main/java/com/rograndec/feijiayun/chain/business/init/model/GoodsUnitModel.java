package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: GoodsUnitModel  
 * @Description: 商品单位初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月5日 下午1:04:24  
 *
 */
public class GoodsUnitModel {
	
	private String code;
	private String name;
	
	public GoodsUnitModel(){}

	public GoodsUnitModel(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public static List<GoodsUnitModel> build(){
		List<GoodsUnitModel> guList = new ArrayList<GoodsUnitModel>();
		
		guList.add(new GoodsUnitModel("01", "盒"));
		guList.add(new GoodsUnitModel("02", "板"));
		guList.add(new GoodsUnitModel("03", "支"));
		guList.add(new GoodsUnitModel("04", "片"));
		guList.add(new GoodsUnitModel("05", "袋"));
		guList.add(new GoodsUnitModel("06", "粒"));
		guList.add(new GoodsUnitModel("07", "瓶"));
		guList.add(new GoodsUnitModel("08", "卷"));
		guList.add(new GoodsUnitModel("09", "台"));
		guList.add(new GoodsUnitModel("10", "提"));
		guList.add(new GoodsUnitModel("11", "把"));
		guList.add(new GoodsUnitModel("12", "包"));
		guList.add(new GoodsUnitModel("13", "贴"));
		guList.add(new GoodsUnitModel("14", "桶"));
		guList.add(new GoodsUnitModel("15", "个"));
		guList.add(new GoodsUnitModel("16", "罐"));
		guList.add(new GoodsUnitModel("17", "只"));
		guList.add(new GoodsUnitModel("18", "条"));
		guList.add(new GoodsUnitModel("19", "筒"));
		guList.add(new GoodsUnitModel("20", "克"));
		guList.add(new GoodsUnitModel("21", "碗"));
		guList.add(new GoodsUnitModel("22", "张"));
		guList.add(new GoodsUnitModel("23", "箱"));
		guList.add(new GoodsUnitModel("24", "双"));
		guList.add(new GoodsUnitModel("25", "盆"));
		guList.add(new GoodsUnitModel("26", "套"));
		guList.add(new GoodsUnitModel("27", "轴"));
		
		return guList;
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
