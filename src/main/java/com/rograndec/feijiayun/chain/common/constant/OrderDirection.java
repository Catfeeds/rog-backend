package com.rograndec.feijiayun.chain.common.constant;

/**
 * 
 * @ClassName: OrderDirection  
 * @Description: 单据业务发生方向常量
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月26日 上午11:59:14  
 *
 */
public enum OrderDirection {
	IN(0, "入库"),
	OUT(1, "出库"),
	MOVE(2, "双向-货位移动"),
	LOAD(3, "双向-中药装斗"),
	SPLIT_OUT(4, "拆零出库"),
	SPLIT_IN(5, "拆零入库"),
	LOT_ADJUST_OUT(6, "批号调整出库"),
	LOT_ADJUST_IN(7, "批号调整入库"),
	INVENTORY(8, "双向-盘点");
	
	private Integer direction;// 方向
	private String desc;// 描述
	
	OrderDirection(Integer direction, String desc){
		this.direction = direction;
		this.desc = desc;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
