package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SelectBoundGoodsVO implements Serializable {

    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    @ApiModelProperty(value = "通用名称")
    private String genericName;

    @ApiModelProperty(value = "规格")
    private String specification;

    @ApiModelProperty(value = "生产厂家")
    private String manufacturer;

    @ApiModelProperty(value = "产地")
    private String place;
    
    @ApiModelProperty(value = "税率", required = true)
    private BigDecimal taxRate;

    @ApiModelProperty(value = "税率ID", required = true)
    private Long taxRateID;

    @ApiModelProperty(value = "货位", required = true)
    private String shelfName;

    @ApiModelProperty(value = "货位ID", required = true)
    private Long shelfId;

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

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    

    public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public Long getTaxRateID() {
		return taxRateID;
	}

	public void setTaxRateID(Long taxRateID) {
		this.taxRateID = taxRateID;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

	public Long getShelfId() {
		return shelfId;
	}

	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
	}

	@Override
	public String toString() {
		return "SelectBoundGoodsVO [goodsId=" + goodsId + ", goodsCode=" + goodsCode + ", genericName=" + genericName
				+ ", specification=" + specification + ", manufacturer=" + manufacturer + ", place=" + place
				+ ", taxRate=" + taxRate + ", taxRateID=" + taxRateID + ", shelfName=" + shelfName + ", shelfId="
				+ shelfId + "]";
	}


    public static List<Long> getGoodsIds(List<SelectBoundGoodsVO> list) {
        Set<Long> set = list.stream().map(l -> l.getGoodsId()).filter(gId -> null != gId).collect(Collectors.toSet());
        return set.stream().collect(Collectors.toList());
    }
}
