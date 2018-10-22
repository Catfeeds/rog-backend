package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: MaintanceMeasuresModel  
 * @Description: 养护措施初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月4日 下午5:21:48  
 *
 */
public class MaintanceMeasuresModel {

	private Integer type;// 养护类别：0-养护和检查；1-仅养护
	private String code;
	private String description;
	
	public MaintanceMeasuresModel(){}
	
	public MaintanceMeasuresModel(Integer type, String code, String description) {
		super();
		this.type = type;
		this.code = code;
		this.description = description;
	}

	public static List<MaintanceMeasuresModel> build(){
		List<MaintanceMeasuresModel> mmList = new ArrayList<MaintanceMeasuresModel>();
		mmList.add(new MaintanceMeasuresModel(0, "01", "外观性状"));
		mmList.add(new MaintanceMeasuresModel(0, "02", "包装情况"));
		mmList.add(new MaintanceMeasuresModel(0, "03", "标签"));
		mmList.add(new MaintanceMeasuresModel(0, "04", "说明书"));
		mmList.add(new MaintanceMeasuresModel(0, "05", "质量状况"));
		return mmList;
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
