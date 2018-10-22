package com.rograndec.feijiayun.chain.business.storage.split.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_split_detail
 * 
 * 
 * @author dongdong.zhang@rograndec.com
 * 
 * 2017-09-30
 */
public class ResponseSplitDetailVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "详情记录主键ID")
    private Long id;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsName;
    
    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "商品剂型名称")
    private String dosageName;

    /**
     * 规格
     */
    @ApiModelProperty(value = "商品 规格")
    private String specification;

    /**
     * 库存计量单位名称
     */
    @ApiModelProperty(value = "库存计量单位名称")
    private String unitName;
    
    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    /**
     * 产地
     */
    @ApiModelProperty(value = "产地")
    private String place; 

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validDate;

    /**
     * 货位名称
     */
    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    /**
     * 货位质量状态
     */
    @ApiModelProperty(value = "货位质量状态")
    private String shelfStatusDesc;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 拆零商品编码
     */
    @ApiModelProperty(value = "拆零商品编码")
    private String splitGoodsCode;

    /**
     * 规格
     */
    @ApiModelProperty(value = "拆零商品规格")
    private String splitSpecification;

    /**
     * 库存计量单位名称
     */
    @ApiModelProperty(value = "拆零商品库存计量单位名称")
    private String splitUnitName;
    

    /**
     * 拆零货位名称
     */
    @ApiModelProperty(value = "拆零货位名称")
    private String splitShelfName;

    /**
     * 拆零货位质量状态
     */
    @ApiModelProperty(value = "拆零货位质量状态")
    private String splitShelfStatusDesc;

    /**
     * 目标数量
     */
    @ApiModelProperty(value = "目标数量")
    private BigDecimal targetQuantity;

    /**
     * saas_split_detail
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * 商品编码
     * @return goods_code 商品编码
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * 商品编码
     * @param goodsCode 商品编码
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    /**
     * 商品名称
     * @return goods_name 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 商品名称
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 批号
     * @return lot_number 批号
     */
    public String getLotNumber() {
        return lotNumber;
    }

    /**
     * 批号
     * @param lotNumber 批号
     */
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber == null ? null : lotNumber.trim();
    }

    /**
     * 生产日期
     * @return product_date 生产日期
     */
    public Date getProductDate() {
        return productDate;
    }

    /**
     * 生产日期
     * @param productDate 生产日期
     */
    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    /**
     * 有效期
     * @return valid_date 有效期
     */
    public Date getValidDate() {
        return validDate;
    }

    /**
     * 有效期
     * @param validDate 有效期
     */
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    /**
     * 货位名称
     * @return shelf_name 货位名称
     */
    public String getShelfName() {
        return shelfName;
    }

    /**
     * 货位名称
     * @param shelfName 货位名称
     */
    public void setShelfName(String shelfName) {
        this.shelfName = shelfName == null ? null : shelfName.trim();
    }

    /**
     * 货位质量状态
     * @return shelf_status_desc 货位质量状态
     */
    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    /**
     * 货位质量状态
     * @param shelfStatusDesc 货位质量状态
     */
    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc == null ? null : shelfStatusDesc.trim();
    }

    /**
     * 数量
     * @return quantity 数量
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * 数量
     * @param quantity 数量
     */
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * 拆零商品编码
     * @return split_goods_code 拆零商品编码
     */
    public String getSplitGoodsCode() {
        return splitGoodsCode;
    }

    /**
     * 拆零商品编码
     * @param splitGoodsCode 拆零商品编码
     */
    public void setSplitGoodsCode(String splitGoodsCode) {
        this.splitGoodsCode = splitGoodsCode == null ? null : splitGoodsCode.trim();
    }

    /**
     * 拆零货位名称
     * @return split_shelf_name 拆零货位名称
     */
    public String getSplitShelfName() {
        return splitShelfName;
    }

    /**
     * 拆零货位名称
     * @param splitShelfName 拆零货位名称
     */
    public void setSplitShelfName(String splitShelfName) {
        this.splitShelfName = splitShelfName == null ? null : splitShelfName.trim();
    }

    /**
     * 拆零货位质量状态
     * @return split_shelf_status_desc 拆零货位质量状态
     */
    public String getSplitShelfStatusDesc() {
        return splitShelfStatusDesc;
    }

    /**
     * 拆零货位质量状态
     * @param splitShelfStatusDesc 拆零货位质量状态
     */
    public void setSplitShelfStatusDesc(String splitShelfStatusDesc) {
        this.splitShelfStatusDesc = splitShelfStatusDesc == null ? null : splitShelfStatusDesc.trim();
    }

    /**
     * 目标数量
     * @return target_quantity 目标数量
     */
    public BigDecimal getTargetQuantity() {
        return targetQuantity;
    }

    /**
     * 目标数量
     * @param targetQuantity 目标数量
     */
    public void setTargetQuantity(BigDecimal targetQuantity) {
        this.targetQuantity = targetQuantity;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getSplitSpecification() {
		return splitSpecification;
	}

	public void setSplitSpecification(String splitSpecification) {
		this.splitSpecification = splitSpecification;
	}

	public String getSplitUnitName() {
		return splitUnitName;
	}

	public void setSplitUnitName(String splitUnitName) {
		this.splitUnitName = splitUnitName;
	}

	/**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", lotNumber=").append(lotNumber);
        sb.append(", productDate=").append(productDate);
        sb.append(", validDate=").append(validDate);
        sb.append(", shelfName=").append(shelfName);
        sb.append(", shelfStatusDesc=").append(shelfStatusDesc);
        sb.append(", quantity=").append(quantity);
        sb.append(", splitGoodsCode=").append(splitGoodsCode);
        sb.append(", splitShelfName=").append(splitShelfName);
        sb.append(", splitShelfStatusDesc=").append(splitShelfStatusDesc);
        sb.append(", targetQuantity=").append(targetQuantity);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}