package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: PharmacySetModel  
 * @Description: 药学设置初始化数据模型  
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月9日 下午1:16:36  
 *
 */
public class PharmacySetModel {
	
	private Integer setType;
	private String code;
	private String name;
	
	public PharmacySetModel(){}

	public PharmacySetModel(Integer setType, String code, String name) {
		super();
		this.setType = setType;
		this.code = code;
		this.name = name;
	}
	
	public static List<PharmacySetModel> build(){
		List<PharmacySetModel> psList = new ArrayList<PharmacySetModel>();
		psList.add(new PharmacySetModel(0, "01", "口服"));
		psList.add(new PharmacySetModel(0, "02", "外敷"));
		psList.add(new PharmacySetModel(0, "03", "煎服"));
		psList.add(new PharmacySetModel(0, "04", "水煎"));
		psList.add(new PharmacySetModel(0, "05", "泡酒"));
		psList.add(new PharmacySetModel(0, "06", "打粉"));
		psList.add(new PharmacySetModel(0, "07", "制丸"));
		psList.add(new PharmacySetModel(0, "08", "装胶囊"));
		
		psList.add(new PharmacySetModel(1, "01", "每日1次"));
		psList.add(new PharmacySetModel(1, "02", "每日2次"));
		psList.add(new PharmacySetModel(1, "03", "每日3次"));
		psList.add(new PharmacySetModel(1, "04", "早晚2次"));
		psList.add(new PharmacySetModel(1, "05", "早晚各1次"));
		psList.add(new PharmacySetModel(1, "06", "早中晚各1次"));
		
		psList.add(new PharmacySetModel(2, "01", "0.25g"));
		psList.add(new PharmacySetModel(2, "02", "0.5g"));
		psList.add(new PharmacySetModel(2, "03", "0.75g"));
		psList.add(new PharmacySetModel(2, "04", "1g"));
		psList.add(new PharmacySetModel(2, "05", "5g"));
		psList.add(new PharmacySetModel(2, "06", "10g"));
		
		psList.add(new PharmacySetModel(3, "01", "温服"));
		psList.add(new PharmacySetModel(3, "02", "凉服"));
		psList.add(new PharmacySetModel(3, "03", "顿服"));
		psList.add(new PharmacySetModel(3, "04", "慢服"));
		psList.add(new PharmacySetModel(3, "05", "饭前服"));
		psList.add(new PharmacySetModel(3, "06", "饭后服"));
		psList.add(new PharmacySetModel(3, "07", "空腹服"));
		psList.add(new PharmacySetModel(3, "08", "空腹温服"));
		return psList;
	}

	public Integer getSetType() {
		return setType;
	}

	public void setSetType(Integer setType) {
		this.setType = setType;
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
