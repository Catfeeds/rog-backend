package com.rograndec.feijiayun.chain.business.retail.pricing.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "SelectPricingGoodsLotShelfVO", description = "选择商品批号货位")
public class SelectPricingGoodsLotShelfVO implements Serializable {
    /**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年9月25日 下午8:04:43 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    
    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    @ApiModelProperty(value = "批号Id")
    private Long lotId;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;
    @ApiModelProperty(value = "生产日期 yyyy-MM-dd")
    private String productDateStr;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validUntil;
    @ApiModelProperty(value = "有效期 yyyy-MM-dd")
    private String validUntilStr;
    
    @ApiModelProperty(value = "货位ID")
    private Long shelfId;
    
    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    /**
     * 库存(商品可用数量)
     */
    @ApiModelProperty(value = "库存可用数量")
    private BigDecimal usableQuantity;
    
    @ApiModelProperty("货位质量状态描述")
    private String shelfStatusDesc;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
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

	public Long getShelfId() {
		return shelfId;
	}

	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}


	public String getProductDateStr() {
		return productDateStr;
	}

	public void setProductDateStr(String productDateStr) {
		this.productDateStr = productDateStr;
	}

	public String getValidUntilStr() {
		return validUntilStr;
	}

	public void setValidUntilStr(String validUntilStr) {
		this.validUntilStr = validUntilStr;
	}

	public String getShelfStatusDesc() {
		return shelfStatusDesc;
	}

	public void setShelfStatusDesc(String shelfStatusDesc) {
		this.shelfStatusDesc = shelfStatusDesc;
	}

	
	
	

	

	
    
    
}