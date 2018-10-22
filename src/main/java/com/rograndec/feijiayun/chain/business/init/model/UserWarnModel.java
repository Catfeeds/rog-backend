package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: UserWarnModel  
 * @Description: 员工预警初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月5日 上午10:20:24  
 *
 */
public class UserWarnModel {

	private String content;
	
	public UserWarnModel(){}

	public UserWarnModel(String content) {
		super();
		this.content = content;
	}

	public static List<UserWarnModel> build(){
		List<UserWarnModel> uwList = new ArrayList<UserWarnModel>();
		uwList.add(new UserWarnModel("执业药师"));
		uwList.add(new UserWarnModel("中药调剂员"));
		return uwList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
