package com.rograndec.feijiayun.chain.inf.search.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MPH供应商
 *
 */
public class MphSupplier implements Serializable {
	private static final long serialVersionUID = 5251656614536919482L;

	private int id;

	private String name;

	@JsonProperty("min_purchase_amount")
	private double minPurchaseAmount;

	@JsonProperty("is_min_purchased")
	private int isMinPurchased;

	/**
	 * 获取ID
	 * 
	 * @return ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置ID
	 * 
	 * @param id
	 *            ID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取最少采购金额
	 * 
	 * @return 最少采购金额
	 */
	public double getMinPurchaseAmount() {
		return minPurchaseAmount;
	}

	/**
	 * 设置最少采购金额
	 * 
	 * @param minPurchaseAmount
	 *            最少采购金额
	 */
	public void setMinPurchaseAmount(double minPurchaseAmount) {
		this.minPurchaseAmount = minPurchaseAmount;
	}

	/**
	 * 获取是否设置最少采购金额，0：未设置，1：设置
	 * 
	 * @return 是否设置最少采购金额
	 */
	public int getIsMinPurchased() {
		return isMinPurchased;
	}

	/**
	 * 设置是否设置最少采购金额，0：未设置，1：设置
	 * 
	 * @param isMinPurchased
	 *            是否设置最少采购金额
	 */
	public void setIsMinPurchased(int isMinPurchased) {
		this.isMinPurchased = isMinPurchased;
	}

}
