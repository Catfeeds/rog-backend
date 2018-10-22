package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: RejectReasonModel  
 * @Description: 拒收原因初始化数据模型 
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月4日 下午3:03:45  
 *
 */
public class RejectReasonModel {

	private String code;
	private String description;
	
	public RejectReasonModel(){}
	
	public RejectReasonModel(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public static List<RejectReasonModel> build(){
		List<RejectReasonModel> rrList = new ArrayList<RejectReasonModel>();
		rrList.add(new RejectReasonModel("01", "未采用规定的冷藏设备运输或温度不符合"));
		rrList.add(new RejectReasonModel("02", "无随货同行单（票）或无采购记录"));
		rrList.add(new RejectReasonModel("03", "随货同行单（票）与采购记录以及本企业实际情况不符"));
		rrList.add(new RejectReasonModel("04", "随货同行单（票）与药品实物不符"));
		rrList.add(new RejectReasonModel("05", "药品外包装出现破损、污染、标识不清等情况"));
		rrList.add(new RejectReasonModel("06", "其它情况"));
		return rrList;
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
