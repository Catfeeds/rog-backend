package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: GoodsDosageModel  
 * @Description: 商品剂型初始化数据模型  
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月5日 下午1:13:05  
 *
 */
public class GoodsDosageModel {

	private String code;
	private String name;
	
	public GoodsDosageModel(){}

	public GoodsDosageModel(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public static List<GoodsDosageModel> build(){
		List<GoodsDosageModel> gdList = new ArrayList<GoodsDosageModel>();
		gdList.add(new GoodsDosageModel("01", "丸剂（大蜜丸）"));
		gdList.add(new GoodsDosageModel("02", "丸剂（小蜜丸）"));
		gdList.add(new GoodsDosageModel("03", "丸剂（水丸）"));
		gdList.add(new GoodsDosageModel("04", "丸剂（水蜜丸）"));
		gdList.add(new GoodsDosageModel("05", "丸剂（浓缩丸）"));
//		gdList.add(new GoodsDosageModel("06", "丸剂（水丸）"));
		gdList.add(new GoodsDosageModel("07", "乳胶剂"));
		gdList.add(new GoodsDosageModel("08", "其他"));
		gdList.add(new GoodsDosageModel("09", "凝胶剂"));
		gdList.add(new GoodsDosageModel("10", "分散片剂"));
		gdList.add(new GoodsDosageModel("11", "包"));
		gdList.add(new GoodsDosageModel("12", "口服液"));
		gdList.add(new GoodsDosageModel("13", "口服溶液"));
		gdList.add(new GoodsDosageModel("14", "合剂"));
		gdList.add(new GoodsDosageModel("15", "含片"));
		gdList.add(new GoodsDosageModel("16", "器械"));
		gdList.add(new GoodsDosageModel("17", "外用"));
		gdList.add(new GoodsDosageModel("18", "外用剂"));
		gdList.add(new GoodsDosageModel("19", "妇科洗液"));
		gdList.add(new GoodsDosageModel("20", "帖剂"));
		gdList.add(new GoodsDosageModel("21", "搽剂"));
		gdList.add(new GoodsDosageModel("22", "散剂"));
		gdList.add(new GoodsDosageModel("23", "栓剂"));
		gdList.add(new GoodsDosageModel("24", "橡胶膏剂"));
		gdList.add(new GoodsDosageModel("25", "气雾剂"));
		gdList.add(new GoodsDosageModel("26", "水丸"));
		gdList.add(new GoodsDosageModel("27", "水剂"));
		gdList.add(new GoodsDosageModel("28", "水蜜丸"));
		gdList.add(new GoodsDosageModel("29", "泡腾片剂"));
		gdList.add(new GoodsDosageModel("30", "洗剂"));
		gdList.add(new GoodsDosageModel("31", "液体"));
		gdList.add(new GoodsDosageModel("32", "混悬剂"));
		gdList.add(new GoodsDosageModel("33", "混悬液"));
		gdList.add(new GoodsDosageModel("34", "溶液剂"));
		gdList.add(new GoodsDosageModel("35", "滴剂"));
		gdList.add(new GoodsDosageModel("36", "滴眼液"));
		gdList.add(new GoodsDosageModel("37", "滴鼻剂"));
		gdList.add(new GoodsDosageModel("38", "煎膏剂"));
		gdList.add(new GoodsDosageModel("39", "片剂"));
		gdList.add(new GoodsDosageModel("40", "眼用制剂（滴眼剂）"));
		gdList.add(new GoodsDosageModel("41", "粉剂"));
		gdList.add(new GoodsDosageModel("42", "糖浆"));
		gdList.add(new GoodsDosageModel("43", "糖浆剂"));
		gdList.add(new GoodsDosageModel("44", "糖衣片"));
		gdList.add(new GoodsDosageModel("45", "缓释胶囊剂"));
		gdList.add(new GoodsDosageModel("46", "耳用制剂（滴耳剂）"));
		gdList.add(new GoodsDosageModel("47", "肠溶片剂"));
		gdList.add(new GoodsDosageModel("48", "胶囊"));
		gdList.add(new GoodsDosageModel("49", "胶囊剂"));
		gdList.add(new GoodsDosageModel("50", "膏剂"));
		gdList.add(new GoodsDosageModel("51", "茶剂（袋泡茶）"));
		gdList.add(new GoodsDosageModel("52", "蜜丸"));
		gdList.add(new GoodsDosageModel("53", "贴膏剂"));
		gdList.add(new GoodsDosageModel("54", "软膏"));
		gdList.add(new GoodsDosageModel("55", "软膏剂"));
		gdList.add(new GoodsDosageModel("56", "酊剂"));
		gdList.add(new GoodsDosageModel("57", "霜"));
		gdList.add(new GoodsDosageModel("58", "露剂"));
		gdList.add(new GoodsDosageModel("59", "颗粒"));
		gdList.add(new GoodsDosageModel("60", "颗粒剂"));
		
		return gdList;
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
