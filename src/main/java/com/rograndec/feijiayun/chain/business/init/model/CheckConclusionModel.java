package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: CheckConclusionModel  
 * @Description: 验收结论初始化数据模型 
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月4日 下午5:10:11  
 *
 */
public class CheckConclusionModel {

	private Integer chainType;
	private String code;
	private String description;
	
	public CheckConclusionModel(){}
	
	public CheckConclusionModel(Integer chainType, String code, String description) {
		super();
		this.chainType = chainType;
		this.code = code;
		this.description = description;
	}

	public static List<CheckConclusionModel> build(){
		List<CheckConclusionModel> ccList =  new ArrayList<CheckConclusionModel>();
		ccList.add(new CheckConclusionModel(0, "01", "合格"));
		ccList.add(new CheckConclusionModel(1, "02", "合格"));
		return ccList;
	}

	public Integer getChainType() {
		return chainType;
	}
	public void setChainType(Integer chainType) {
		this.chainType = chainType;
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
