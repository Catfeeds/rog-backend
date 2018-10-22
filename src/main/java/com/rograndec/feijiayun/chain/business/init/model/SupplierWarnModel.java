package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: SupplierWarnModel  
 * @Description: 供货单位预警初始化数据模型 
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月5日 上午10:23:11  
 *
 */
public class SupplierWarnModel {

	private String content;
	
	public SupplierWarnModel(){}

	public SupplierWarnModel(String content) {
		super();
		this.content = content;
	}

	public static List<SupplierWarnModel> build(){
		List<SupplierWarnModel> swList = new ArrayList<SupplierWarnModel>();
		swList.add(new SupplierWarnModel("营业执照统一社会信用代码"));
		swList.add(new SupplierWarnModel("年检证明文件"));
		swList.add(new SupplierWarnModel("随货通行单(票)样式"));
		swList.add(new SupplierWarnModel("相关印章"));
		swList.add(new SupplierWarnModel("质量协议书"));
		swList.add(new SupplierWarnModel("药品经营许可证"));
		swList.add(new SupplierWarnModel("药品经营质量管理规范认证证书"));
		swList.add(new SupplierWarnModel("医疗器械经营企业许可证"));
		swList.add(new SupplierWarnModel("食品流通许可证"));
		swList.add(new SupplierWarnModel("食品卫生许可证"));
		return swList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
