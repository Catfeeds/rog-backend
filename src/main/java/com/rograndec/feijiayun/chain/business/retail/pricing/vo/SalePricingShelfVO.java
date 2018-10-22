package com.rograndec.feijiayun.chain.business.retail.pricing.vo;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
 
/**
 * 
 * saas_sale_pricing_shelf
 * 
 * 
 * @author liyut
 * 
 * 2017-09-18
 */
public class SalePricingShelfVO implements Serializable {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    
    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID",hidden=true)
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID",hidden=true)
    private Long parentId;

    /**
     * 销售划价明细ID
     */
    @ApiModelProperty(value = "销售划价明细ID")
    private Long pricingDtlId;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;
    
    @NotNull(message="批号id不能为空")
    @ApiModelProperty(value = "批号Id")
    private Long lotId;

    /**
     * 批号
     */
    @NotBlank(message="批号不能为空")
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @NotNull(message="生产日期不能为空")
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    /**
     * 有效期
     */
    @NotNull(message="有效期不能为空")
    @ApiModelProperty(value = "有效期")
    private Date validDate;

    /**
     * 货位ID
     */
    @NotNull(message="货位ID不能为空")
    @ApiModelProperty(value = "货位ID")
    private Long shelfId;

    /**
     * 货位名称
     */
    @NotNull(message="货位名称不能为空")
    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    @ApiModelProperty("货位质量状态描述")
    private String shelfStatusDesc;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;
    
    @ApiModelProperty(value = "库存可用数量")
    private BigDecimal usableQuantity;
    
    /**
     * 单剂数量
     */
    @ApiModelProperty(value = "单剂数量")
    private BigDecimal singleDose;

    /**
     * 总数量
     */
    @ApiModelProperty(value = "总数量")
    private BigDecimal totalQuantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;
    
    @ApiModelProperty(value="会员单价")
    private BigDecimal memberPrice;


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
     * 销售划价明细ID
     * @return pricing_dtl_id 销售划价明细ID
     */
    public Long getPricingDtlId() {
        return pricingDtlId;
    }

    /**
     * 销售划价明细ID
     * @param pricingDtlId 销售划价明细ID
     */
    public void setPricingDtlId(Long pricingDtlId) {
        this.pricingDtlId = pricingDtlId;
    }


    /**
     * 商品ID
     * @return goods_id 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 商品ID
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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
     * 货位ID
     * @return shelf_id 货位ID
     */
    public Long getShelfId() {
        return shelfId;
    }

    /**
     * 货位ID
     * @param shelfId 货位ID
     */
    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
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
     * 单剂数量
     * @return single_dose 单剂数量
     */
    public BigDecimal getSingleDose() {
        return singleDose;
    }

    /**
     * 单剂数量
     * @param singleDose 单剂数量
     */
    public void setSingleDose(BigDecimal singleDose) {
        this.singleDose = singleDose;
    }

    /**
     * 总数量
     * @return total_quantity 总数量
     */
    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    /**
     * 总数量
     * @param totalQuantity 总数量
     */
    public void setTotalQuantity(BigDecimal totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    /**
     * 单价
     * @return unit_price 单价
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * 单价
     * @param unitPrice 单价
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

	public BigDecimal getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(BigDecimal memberPrice) {
		this.memberPrice = memberPrice;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public BigDecimal getUsableQuantity() {
		return usableQuantity;
	}

	public void setUsableQuantity(BigDecimal usableQuantity) {
		this.usableQuantity = usableQuantity;
	}

	public Long getLotId() {
		return lotId;
	}

	public void setLotId(Long lotId) {
		this.lotId = lotId;
	}

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }
}