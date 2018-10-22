package com.rograndec.feijiayun.chain.inf.search.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MPH根据供应商分组商品检索结果
 *
 */
public class SearchMphSupplierGoodsResult implements Serializable {
	private static final long serialVersionUID = 56504371824767008L;

	private int start;

	private int rows;

	@JsonProperty("total_count")
	private int totalCount;

	private MphSupplier supplier;

	@JsonProperty("category_1_name_facet")
	private List<String> category1NameFacet;

	@JsonProperty("category_2_name_facet")
	private List<String> category2NameFacet;

	@JsonProperty("manufacturer_facet")
	private List<String> manufacturerFacet;

	@JsonProperty("dosage_form_name_facet")
	private List<String> dosageFormNameFacet;

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
	 * 获取一级分类名称Facet
	 * 
	 * @return 一级分类名称Facet
	 */
	public List<String> getCategory1NameFacet() {
		return category1NameFacet;
	}

	/**
	 * 设置一级分类名称Facet
	 * 
	 * @param category1NameFacet
	 *            一级分类名称Facet
	 */
	public void setCategory1NameFacet(List<String> category1NameFacet) {
		this.category1NameFacet = category1NameFacet;
	}

	/**
	 * 获取二级分类名称Facet
	 * 
	 * @return 二级分类名称Facet
	 */
	public List<String> getCategory2NameFacet() {
		return category2NameFacet;
	}

	/**
	 * 设置二级分类名称Facet
	 * 
	 * @param category2NameFacet
	 *            二级分类名称Facet
	 */
	public void setCategory2NameFacet(List<String> category2NameFacet) {
		this.category2NameFacet = category2NameFacet;
	}

	/**
	 * 获取生产厂家Facet
	 * 
	 * @return 生产厂家Facet
	 */
	public List<String> getManufacturerFacet() {
		return manufacturerFacet;
	}

	/**
	 * 设置生产厂家Facet
	 * 
	 * @param manufacturerFacet
	 *            生产厂家Facet
	 */
	public void setManufacturerFacet(List<String> manufacturerFacet) {
		this.manufacturerFacet = manufacturerFacet;
	}

	/**
	 * 获取剂型分类名称Facet
	 * 
	 * @return 剂型名称Facet
	 */
	public List<String> getDosageFormNameFacet() {
		return dosageFormNameFacet;
	}

	/**
	 * 设置剂型名称Facet
	 * 
	 * @param dosageFormNameFacet
	 *            剂型名称Facet
	 */
	public void setDosageFormNameFacet(List<String> dosageFormNameFacet) {
		this.dosageFormNameFacet = dosageFormNameFacet;
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
