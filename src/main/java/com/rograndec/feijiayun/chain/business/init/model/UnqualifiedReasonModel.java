package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: UnqualifiedReasonModel  
 * @Description: 不合格原因初始化数据模型  
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月4日 下午3:18:25  
 *
 */
public class UnqualifiedReasonModel {
	private String code;
	private String description;
	
	public UnqualifiedReasonModel(){}
	
	public UnqualifiedReasonModel(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public static List<UnqualifiedReasonModel> build(){
		List<UnqualifiedReasonModel> urList = new ArrayList<UnqualifiedReasonModel>();
		urList.add(new UnqualifiedReasonModel("01", "药品包装出现破损、污染、封口不牢、衬垫不实、封条损坏等问题"));
		urList.add(new UnqualifiedReasonModel("02", "包装内有异常响动或者液体渗漏"));
		urList.add(new UnqualifiedReasonModel("03", "标签脱落、字迹模糊不清或者标识内容与实物不符"));
		urList.add(new UnqualifiedReasonModel("04", "药品已超过有效期"));
		urList.add(new UnqualifiedReasonModel("05", "其他异常情况"));
		return urList;
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
