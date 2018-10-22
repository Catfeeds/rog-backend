package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: CheckPojectModel  
 * @Description: 验收项目初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月4日 下午4:00:49  
 *
 */
public class CheckPojectModel {
	
	private Integer type;// 验收类型：0-全部；1-国产成药；2-进口成药；3-中药饮品；4-中药材；5-医疗器械；6-食品；7-化妆品；8-进口麻醉药品、精神药品以及蛋白同化制剂、肽类激素；9-生物制品；10-进口生物制品；11-其它
	private String code;
	private String description;
	private Integer haveFile;
	
	public CheckPojectModel(){}

	public CheckPojectModel(Integer type, String code, String description, Integer haveFile) {
		super();
		this.type = type;
		this.code = code;
		this.description = description;
		this.haveFile = haveFile;
	}
	
	public static List<CheckPojectModel> build(){
		List<CheckPojectModel> cpList = new ArrayList<CheckPojectModel>();
		cpList.add(new CheckPojectModel(0, "01", "外观性状", 0));
		cpList.add(new CheckPojectModel(0, "02", "包装情况", 0));
		cpList.add(new CheckPojectModel(0, "03", "标签", 0));
		cpList.add(new CheckPojectModel(0, "04", "说明书", 0));
		cpList.add(new CheckPojectModel(0, "05", "外观性状", 0));
		
		cpList.add(new CheckPojectModel(1, "06", "《检验报告书》", 0));
		
		cpList.add(new CheckPojectModel(2, "07", "《进口药品注册证》", 0));
		cpList.add(new CheckPojectModel(2, "08", "《医药产品注册证》", 0));
		cpList.add(new CheckPojectModel(2, "09", "《进口药品检验报告书》", 0));
		cpList.add(new CheckPojectModel(2, "10", "《进口药品通关单》", 0));
		
		cpList.add(new CheckPojectModel(4, "11", "《进口药材批件》", 0));
		cpList.add(new CheckPojectModel(8, "12", "《进口准许证》", 0));
		cpList.add(new CheckPojectModel(9, "13", "《生物制品批签发合格证》", 0));
		cpList.add(new CheckPojectModel(10, "14", "《进口药品检验报告书》", 0));
		
		return cpList;
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

	public Integer getHaveFile() {
		return haveFile;
	}

	public void setHaveFile(Integer haveFile) {
		this.haveFile = haveFile;
	}
	
}
