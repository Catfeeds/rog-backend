package com.rograndec.feijiayun.chain.business.goods.pharmacy.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by zeshi.sun on 2017/9/8.
 */


public class SpecialGoodsVO implements Serializable {

    /**
     * ID
     */
    @ApiModelProperty(required = true, value = "ID")
    private Long id;

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty(required = true, value = "专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
    private Integer specialGoods;

    /**
     * 专管药品名称
     */
    @ApiModelProperty(required = true, value = "专管药品名称")
    private String specialGoodsName;

    /**
     * 专管类型
     */
    @ApiModelProperty(required = true, value = "专管类型")
    private String specialGoodsType;

    /**
     * 商品编码
     */
    @ApiModelProperty(required = true, value = "商品编码")
    private String goodsCode;

    /**
     * 商品名称
     */
    @ApiModelProperty(required = true, value = "商品名称")
    private String goodsName;

    /**
     * 剂型
     */
    @ApiModelProperty(required = true, value = "剂型")
    private String dosageName;

    /**
     * 规格
     */
    @ApiModelProperty(required = true, value = "规格")
    private String specification;

    /**
     * 单位
     */
    @ApiModelProperty(required = true, value = "单位")
    private String unitName;

    /**
     * 生产厂商
     */
    @ApiModelProperty(required = true, value = "生产厂商")
    private String manufacturer;

    /**
     * 产地
     */
    @ApiModelProperty(required = true, value = "产地")
    private String goodsPlace;

    /**
     * 限售数量
     */
    @ApiModelProperty(required = true, value = "限售数量")
    private BigDecimal limitSaleQuantity;

    /**
     * 含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）
     */
    @ApiModelProperty(required = true, value = "含特殊药品复方制剂类型（0-含可卡因复方口服液；1-含麻黄碱类复方制剂；2-复方地芬诺酯片；3-复方甘草片）")
    private Integer formulationType;

    /**
     * 含特殊药品复方制剂类型名称
     */
    @ApiModelProperty(required = true, value = "含特殊药品复方制剂类型名称")
    private String formulationTypeName;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(required = true, value = " 状态（0-禁用；1-启用）")
    private Integer status;
    
    private Long enterpriseId;
    
    /**
     * 加盟店判断是否可编辑（true-可编辑，false-不可编辑）
     */
    private boolean updateFlag=false;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSpecialGoods() {
        return specialGoods;
    }

    public void setSpecialGoods(Integer specialGoods) {
        this.specialGoods = specialGoods;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public BigDecimal getLimitSaleQuantity() {
        return limitSaleQuantity;
    }

    public void setLimitSaleQuantity(BigDecimal limitSaleQuantity) {
        this.limitSaleQuantity = limitSaleQuantity;
    }

    public Integer getFormulationType() {
        return formulationType;
    }

    public void setFormulationType(Integer formulationType) {
        this.formulationType = formulationType;
    }

    public String getSpecialGoodsType() {
        return specialGoodsType;
    }

    public void setSpecialGoodsType(String specialGoodsType) {
        this.specialGoodsType = specialGoodsType;
    }

    public String getSpecialGoodsName() {
        return specialGoodsName;
    }

    public void setSpecialGoodsName(String specialGoodsName) {
        this.specialGoodsName = specialGoodsName;
    }

    public String getFormulationTypeName() {
        return formulationTypeName;
    }

    public void setFormulationTypeName(String formulationTypeName) {
        this.formulationTypeName = formulationTypeName;
    }

    public String getGoodsPlace() {
        return goodsPlace;
    }

    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace;
    }

    public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	@Override
    public String toString() {
        return "SpecialGoodsVO[" +
                "id=" + id +
                ", specialGoods=" + specialGoods +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", dosageName='" + dosageName + '\'' +
                ", specification='" + specification + '\'' +
                ", unitName='" + unitName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", limitSaleQuantity=" + limitSaleQuantity +
                ", formulationType=" + formulationType +
                ", specialGoodsType=" + specialGoodsType +
                ", goodsPlace='" + goodsPlace + '\'' +
                ']';
    }
}
