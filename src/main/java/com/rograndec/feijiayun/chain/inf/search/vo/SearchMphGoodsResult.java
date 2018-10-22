package com.rograndec.feijiayun.chain.inf.search.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MPH商品检索结果
 *
 */
public class SearchMphGoodsResult implements Serializable {
	private static final long serialVersionUID = -8404284810786308923L;

	private int start;

	private int rows;

	@JsonProperty("total_count")
	private int totalCount;

	@JsonProperty("goods_list")
	private List<MphGoods> goodsList;

	/**
	 * 获取开始记录数
	 * 
	 * @return 开始记录数
	 */
	public int getStart() {
		return start;
	}

	/**
	 * 设置开始记录数
	 * 
	 * @param start
	 *            开始记录数
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * 获取行数
	 * 
	 * @return 行数
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * 设置行数
	 * 
	 * @param rows
	 *            行数
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * 获取总数量
	 * 
	 * @return 总数量
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总数量
	 * 
	 * @param totalCount
	 *            总数量
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 获取商品
	 * 
	 * @return 商品
	 */
	public List<MphGoods> getGoodsList() {
		return goodsList;
	}

	/**
	 * 设置商品
	 * 
	 * @param goodsList
	 *            商品
	 */
	public void setGoodsList(List<MphGoods> goodsList) {
		this.goodsList = goodsList;
	}
}
