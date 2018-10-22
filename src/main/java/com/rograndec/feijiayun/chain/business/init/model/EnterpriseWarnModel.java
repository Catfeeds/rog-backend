package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: EnterpriseWarnModel  
 * @Description: 企业预警初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月5日 上午10:13:07  
 *
 */
public class EnterpriseWarnModel {

	private String content;
	
	public EnterpriseWarnModel(){}

	public EnterpriseWarnModel(String content) {
		super();
		this.content = content;
	}

	public static List<EnterpriseWarnModel> build(){
		List<EnterpriseWarnModel> ewList = new ArrayList<EnterpriseWarnModel>();
		ewList.add(new EnterpriseWarnModel("营业执照统一社会信用代码"));
		ewList.add(new EnterpriseWarnModel("年检证明文件"));
		ewList.add(new EnterpriseWarnModel("随货通行单(票)样式"));
		ewList.add(new EnterpriseWarnModel("相关印章"));
		ewList.add(new EnterpriseWarnModel("质量协议书"));
		ewList.add(new EnterpriseWarnModel("药品经营许可证"));
		ewList.add(new EnterpriseWarnModel("药品经营质量管理规范认证证书"));
		ewList.add(new EnterpriseWarnModel("医疗器械经营企业许可证"));
		ewList.add(new EnterpriseWarnModel("食品流通许可证"));
		ewList.add(new EnterpriseWarnModel("食品卫生许可证"));
		return ewList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
