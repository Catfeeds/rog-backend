package com.rograndec.feijiayun.chain.business.goods.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "GoodsHeadquartersVO", description = "商品管理-总部按商品分页展示基本对象")
public class GoodsHeadquartersVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年8月28日 下午3:43:32 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 商品ID
     */
	@ApiModelProperty(required = true, value = "商品ID")
    private Long goodsId;
	
	/**
     * 商品编码
     */
	@ApiModelProperty(required = true, value = "商品编码")
    private String goodsCode;
	
	/**
     * 品种类别
     */
	@ApiModelProperty(required = true, value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private Integer businessVariety;
	
	/**
     * 品种类别名称
     */
	@ApiModelProperty(required = true, value = "品种类别名称")
    private String businessVarietyName;
	
	/**
     * 通用名称
     */
	@ApiModelProperty(required = false, value = "通用名称")
    private String genericName;
	
	/**
     * 剂型ID
     */
	@ApiModelProperty(required = false, value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
	@ApiModelProperty(required = false, value = "剂型名称")
    private String dosageName;
	
	/**
     * 规格
     */
	@ApiModelProperty(required = false, value = "规格")
    private String specification;
	
	/**
     * 生产厂商ID
     */
	@ApiModelProperty(required = true, value = "生产厂商ID")
    private Long manufacturerId;
	
	/**
     * 生产厂商名称
     */
	@ApiModelProperty(required = true, value = "生产厂商名称")
    private String manufacturer;
	
	/**
	 * 单位ID
	 */
	@ApiModelProperty(required = true, value = "单位ID")
	private Long unitId;
	
	/**
	 * 单位名称
	 */
	@ApiModelProperty(required = true, value = "单位名称")
	private String unitName;
	
	@ApiModelProperty(required = true, value = "产地")
	private String goodsPlace;

    /**
     * 加盟店可编辑标识（true-可编辑 false-不可编辑）
     */
    @ApiModelProperty(required = false, value = "加盟店可编辑标识 true-可编辑 false-不可编辑")
    private boolean updateFlag;
	
    @ApiModelProperty(required = false, value = "企业类型")
    private String chainType;
	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public Integer getBusinessVariety() {
		return businessVariety;
	}

	public void setBusinessVariety(Integer businessVariety) {
		this.businessVariety = businessVariety;
	}

	public String getBusinessVarietyName() {
		return businessVarietyName;
	}

	public void setBusinessVarietyName(String businessVarietyName) {
		this.businessVarietyName = businessVarietyName;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public Long getDosageId() {
		return dosageId;
	}

	public void setDosageId(Long dosageId) {
		this.dosageId = dosageId;
	}

	public String getDosageName() {
		return dosageName;
	}

	public void setDosageName(String dosageName) {
		this.dosageName = dosageName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	public String getGoodsPlace() {
		return goodsPlace;
	}

	public void setGoodsPlace(String goodsPlace) {
		this.goodsPlace = goodsPlace;
	}

	public boolean getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getChainType() {
		return chainType;
	}

	public void setChainType(String chainType) {
		this.chainType = chainType;
	}

	@Override
	public String toString() {
		return "GoodsHeadquartersVO [goodsId=" + goodsId + ", goodsCode="
				+ goodsCode + ", businessVariety=" + businessVariety
				+ ", businessVarietyName=" + businessVarietyName
				+ ", genericName=" + genericName + ", dosageId=" + dosageId
				+ ", dosageName=" + dosageName + ", specification="
				+ specification + ", manufacturerId=" + manufacturerId
				+ ", manufacturer=" + manufacturer + ", unitId=" + unitId
				+ ", unitName=" + unitName + "]";
	}


}
