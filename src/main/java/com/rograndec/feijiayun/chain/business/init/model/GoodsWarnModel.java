package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: GoodsWarnModel  
 * @Description: 商品预警初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月5日 上午10:28:34  
 *
 */
public class GoodsWarnModel {

	private String content;
	
	public GoodsWarnModel(){}

	public GoodsWarnModel(String content) {
		super();
		this.content = content;
	}

	public static List<GoodsWarnModel> build(){
		List<GoodsWarnModel> gwList = new ArrayList<GoodsWarnModel>();
		gwList.add(new GoodsWarnModel("生产企业营业执照"));
		gwList.add(new GoodsWarnModel("药品生产许可证"));
		gwList.add(new GoodsWarnModel("药品生产质量管理规范认证证书"));
		gwList.add(new GoodsWarnModel("新药证书"));
		gwList.add(new GoodsWarnModel("药品(再)注册批件"));
		gwList.add(new GoodsWarnModel("药品注册证"));
		return gwList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
