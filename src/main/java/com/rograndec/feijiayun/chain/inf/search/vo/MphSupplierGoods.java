package com.rograndec.feijiayun.chain.inf.search.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MPH供应商商品
 *
 */
public class MphSupplierGoods implements Serializable {
	private static final long serialVersionUID = 5251656614536919482L;

	private MphSupplier supplier;

	@JsonProperty("goods_list")
	private List<MphGoods> goodsList;

	/**
	 * 获取供应商
	 * 
	 * @return 供应商
	 */
	public MphSupplier getSupplier() {
		return supplier;
	}

	/**
	 * 设置供应商
	 * 
	 * @param supplier
	 *            供应商
	 */
	public void setSupplier(MphSupplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * 获取供应商下商品
	 * 
	 * @return 供应商下商品
	 */
	public List<MphGoods> getGoodsList() {
		return goodsList;
	}

	/**
	 * 设置供应商下商品
	 * 
	 * @param goodsList
	 *            供应商下商品
	 */
	public void setGoodsList(List<MphGoods> goodsList) {
		this.goodsList = goodsList;
	}
}
