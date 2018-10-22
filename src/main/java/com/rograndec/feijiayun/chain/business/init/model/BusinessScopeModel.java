package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: BusinessScope  
 * @Description: 经营范围初始化数据模型 
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月4日 下午5:36:29  
 *
 */
public class BusinessScopeModel {
	
	private Integer businessVariety;// 品种类别：0-药品；1-医疗器械；2-食品；3-化妆品；4-其它
	private String code;
	private String description;
	
	public BusinessScopeModel(){}

	public BusinessScopeModel(Integer businessVariety, String code, String description) {
		super();
		this.businessVariety = businessVariety;
		this.code = code;
		this.description = description;
	}
	
	public static List<BusinessScopeModel> build(){
		List<BusinessScopeModel> bsList = new ArrayList<BusinessScopeModel>();
		bsList.add(new BusinessScopeModel(0, "01", "中成药"));
		bsList.add(new BusinessScopeModel(0, "02", "中药材"));
		bsList.add(new BusinessScopeModel(0, "03", "中药饮片"));
		bsList.add(new BusinessScopeModel(0, "04", "化学原料药"));
		bsList.add(new BusinessScopeModel(0, "05", "化学药制剂"));
		bsList.add(new BusinessScopeModel(0, "06", "抗生素"));
		bsList.add(new BusinessScopeModel(0, "07", "生化药品"));
		bsList.add(new BusinessScopeModel(0, "08", "放射性药品"));
		bsList.add(new BusinessScopeModel(0, "09", "生物制品"));
		bsList.add(new BusinessScopeModel(0, "10", "血液制品"));
		bsList.add(new BusinessScopeModel(0, "11", "诊断药品"));
		bsList.add(new BusinessScopeModel(0, "12", "其它"));
		bsList.add(new BusinessScopeModel(1, "01", "第一类"));
		bsList.add(new BusinessScopeModel(1, "02", "第二类"));
		bsList.add(new BusinessScopeModel(1, "03", "第三类"));
		bsList.add(new BusinessScopeModel(2, "01", "定型包装食品"));
		bsList.add(new BusinessScopeModel(2, "02", "保健食品"));
		bsList.add(new BusinessScopeModel(3, "01", "化妆品"));
		bsList.add(new BusinessScopeModel(4, "01", "其它"));
		return bsList;
	}
	
	public Integer getBusinessVariety() {
		return businessVariety;
	}

	public void setBusinessVariety(Integer businessVariety) {
		this.businessVariety = businessVariety;
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
