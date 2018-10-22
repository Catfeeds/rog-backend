package com.rograndec.feijiayun.chain.business.goods.httpInf.entity;

/**
 * 
 * 版权：融贯资讯 <br/>
 * 作者：jian.mei@rogrand.com <br/>
 * 生成日期：2015-01-27 <br/>
 * 描述：中西成药标准表类
 */
public class NormDrug {


	// ID
	private Long nd_id;
	// 适应症
	private String nd_indications;
	// 不良反应
	private String nd_adverse_events;
	// 存储注意事项
	private String nd_store_note;
	// 用法与用量
	private String nd_usage_dosage;
	// 禁忌症
	private String nd_contraindication;
	// 注意事项
	private String nd_note_events;
	// 保质期
	private String nd_shelf_life;
	// 存储条件
	private String nd_storage_conditions;
	// 药品成分
	private String nd_drug_details;

	// 存储条件（new）
	private Integer nd_holding_conditions;
	// 特殊成分
	private Integer nd_special_composition;

	/**
	 * 获得ID
	 * 
	 * @return Long
	 */
	public Long getNd_id() {
		return this.nd_id;
	}

	/**
	 * 设置ID
	 * 
	 * @param nd_id
	 *            ID
	 */
	public void setNd_id(Long nd_id) {
		this.nd_id = nd_id;
	}

	/**
	 * 获得适应症
	 * 
	 * @return String
	 */
	public String getNd_indications() {
		return this.nd_indications;
	}

	/**
	 * 设置适应症
	 * 
	 * @param nd_indications
	 *            适应症
	 */
	public void setNd_indications(String nd_indications) {
		this.nd_indications = nd_indications;
	}

	/**
	 * 获得不良反应
	 * 
	 * @return String
	 */
	public String getNd_adverse_events() {
		return this.nd_adverse_events;
	}

	/**
	 * 设置不良反应
	 * 
	 * @param nd_adverse_events
	 *            不良反应
	 */
	public void setNd_adverse_events(String nd_adverse_events) {
		this.nd_adverse_events = nd_adverse_events;
	}

	/**
	 * 获得存储注意事项
	 * 
	 * @return String
	 */
	public String getNd_store_note() {
		return this.nd_store_note;
	}

	/**
	 * 设置存储注意事项
	 * 
	 * @param nd_store_note
	 *            存储注意事项
	 */
	public void setNd_store_note(String nd_store_note) {
		this.nd_store_note = nd_store_note;
	}

	/**
	 * 获得用法与用量
	 * 
	 * @return String
	 */
	public String getNd_usage_dosage() {
		return this.nd_usage_dosage;
	}

	/**
	 * 设置用法与用量
	 * 
	 * @param nd_usage_dosage
	 *            用法与用量
	 */
	public void setNd_usage_dosage(String nd_usage_dosage) {
		this.nd_usage_dosage = nd_usage_dosage;
	}

	/**
	 * 获得禁忌症
	 * 
	 * @return String
	 */
	public String getNd_contraindication() {
		return this.nd_contraindication;
	}

	/**
	 * 设置禁忌症
	 * 
	 * @param nd_contraindication
	 *            禁忌症
	 */
	public void setNd_contraindication(String nd_contraindication) {
		this.nd_contraindication = nd_contraindication;
	}

	/**
	 * 获得注意事项
	 * 
	 * @return String
	 */
	public String getNd_note_events() {
		return this.nd_note_events;
	}

	/**
	 * 设置注意事项
	 * 
	 * @param nd_note_events
	 *            注意事项
	 */
	public void setNd_note_events(String nd_note_events) {
		this.nd_note_events = nd_note_events;
	}

	/**
	 * 获得保质期
	 * 
	 * @return String
	 */
	public String getNd_shelf_life() {
		return this.nd_shelf_life;
	}

	/**
	 * 设置保质期
	 * 
	 * @param nd_shelf_life
	 *            保质期
	 */
	public void setNd_shelf_life(String nd_shelf_life) {
		this.nd_shelf_life = nd_shelf_life;
	}

	/**
	 * 获得存储条件
	 * 
	 * @return String
	 */
	public String getNd_storage_conditions() {
		return this.nd_storage_conditions;
	}

	/**
	 * 设置存储条件
	 * 
	 * @param nd_storage_conditions
	 *            存储条件
	 */
	public void setNd_storage_conditions(String nd_storage_conditions) {
		this.nd_storage_conditions = nd_storage_conditions;
	}

	/**
	 * 获得药品成分
	 * 
	 * @return String
	 */
	public String getNd_drug_details() {
		return this.nd_drug_details;
	}

	/**
	 * 设置药品成分
	 * 
	 * @param nd_drug_details
	 *            药品成分
	 */
	public void setNd_drug_details(String nd_drug_details) {
		this.nd_drug_details = nd_drug_details;
	}

	public Integer getNd_holding_conditions() {
		return nd_holding_conditions;
	}

	public void setNd_holding_conditions(Integer nd_holding_conditions) {
		this.nd_holding_conditions = nd_holding_conditions;
	}

	public Integer getNd_special_composition() {
		return nd_special_composition;
	}

	public void setNd_special_composition(Integer nd_special_composition) {
		this.nd_special_composition = nd_special_composition;
	}

}
