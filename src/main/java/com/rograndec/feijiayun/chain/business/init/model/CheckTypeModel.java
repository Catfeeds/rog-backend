package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: CheckTypeModel  
 * @Description: 验收初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月4日 下午3:35:30  
 *
 */
public class CheckTypeModel {

	private Integer type;// 品种类别：0-药品；1-医疗器械；2-食品；3-化妆品；4-其它
	private String code;
	private String description;
	
	public CheckTypeModel(){}
	
	public CheckTypeModel(Integer type, String code, String description) {
		super();
		this.type = type;
		this.code = code;
		this.description = description;
	}

	public static List<CheckTypeModel> build(){
		List<CheckTypeModel> ctList = new ArrayList<CheckTypeModel>();
		ctList.add(new CheckTypeModel(0, "01", "全部"));
		ctList.add(new CheckTypeModel(0, "02", "国产成药"));
		ctList.add(new CheckTypeModel(0, "03", "进口成药"));
		ctList.add(new CheckTypeModel(0, "04", "中药饮片"));
		ctList.add(new CheckTypeModel(0, "05", "中药材"));
		ctList.add(new CheckTypeModel(0, "06", "生物制品"));
		ctList.add(new CheckTypeModel(0, "07", "进口生物制品"));
		ctList.add(new CheckTypeModel(0, "08", "进口麻醉药品、精神药品以及蛋白同化制剂、肽类激素"));
		
		ctList.add(new CheckTypeModel(1, "09", "医疗器械"));
		
		ctList.add(new CheckTypeModel(2, "10", "食品"));
		
		ctList.add(new CheckTypeModel(3, "11", "化妆品"));
		
		ctList.add(new CheckTypeModel(4, "12", "其它"));
		
		return ctList;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
