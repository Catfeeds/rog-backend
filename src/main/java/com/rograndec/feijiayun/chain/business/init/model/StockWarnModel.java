package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: StockWarnModel  
 * @Description: 库存预警初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月5日 上午10:31:35  
 *
 */
public class StockWarnModel {

	private String content;
	
	public StockWarnModel(){}

	public StockWarnModel(String content) {
		super();
		this.content = content;
	}

	public static List<StockWarnModel> build(){
		List<StockWarnModel> swList = new ArrayList<StockWarnModel>();
		swList.add(new StockWarnModel("过期商品"));
		swList.add(new StockWarnModel("近效期商品"));
		swList.add(new StockWarnModel("积货商品"));
		swList.add(new StockWarnModel("缺货商品"));
		swList.add(new StockWarnModel("滞销商品"));
		swList.add(new StockWarnModel("药品养护"));
		return swList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
