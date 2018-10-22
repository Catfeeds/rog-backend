package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: ReturnReasonModel  
 * @Description: 退货原因初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月4日 下午3:22:55  
 *
 */
public class ReturnReasonModel {
	
	private Integer type;// 0-质量问题退货；1-非质量问题退货
	private String code;
	private String description;
	
	public ReturnReasonModel(){}
	
	public ReturnReasonModel(Integer type, String code, String description) {
		super();
		this.type = type;
		this.code = code;
		this.description = description;
	}

	public static List<ReturnReasonModel> build(){
		List<ReturnReasonModel> rrList = new ArrayList<ReturnReasonModel>();
		
		rrList.add(new ReturnReasonModel(0, "01", "药品包装出现破损、污染、封口不牢、衬垫不实、封条损坏等问题"));
		rrList.add(new ReturnReasonModel(0, "02", "包装内有异常响动或者液体渗漏"));
		rrList.add(new ReturnReasonModel(0, "03", "标签脱落、字迹模糊不清或者标识内容与实物不符"));
		rrList.add(new ReturnReasonModel(0, "04", "药品已超过有效期"));
		rrList.add(new ReturnReasonModel(0, "05", "其他异常情况"));
		
		rrList.add(new ReturnReasonModel(1, "06", "滞销品退货"));
		rrList.add(new ReturnReasonModel(1, "07", "正常退货"));
		
		return rrList;
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
