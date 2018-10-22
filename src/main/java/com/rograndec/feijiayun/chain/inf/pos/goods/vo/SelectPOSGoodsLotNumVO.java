package com.rograndec.feijiayun.chain.inf.pos.goods.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

@ApiModel(value = "SelectPOSGoodsLotNumVO", description = "POS选择商品批号")
public class SelectPOSGoodsLotNumVO implements Serializable {
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
    private Long lotNumberId;

    /**
     * 生产日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "生产日期")
    private Date productDate;


    /**
     * 有效期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "有效期")
    private Date validUntil;

    
    @ApiModelProperty(value = "货位ID")
    private Long shelfId;
    
    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    /**
     * 库存(商品可用数量)
     */
    @ApiModelProperty(value = "库存可用数量")
    private BigDecimal usableQuantity;
    
    @ApiModelProperty(value = "库存Id")
    private Long stId;
    
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

    public Long getLotNumberId() {
        return lotNumberId;
    }

    public void setLotNumberId(Long lotNumberId) {
        this.lotNumberId = lotNumberId;
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

	public Long getStId() {
		return stId;
	}

	public void setStId(Long stId) {
		this.stId = stId;
	}

	public String getShelfStatusDesc() {
		return shelfStatusDesc;
	}

	public void setShelfStatusDesc(String shelfStatusDesc) {
		this.shelfStatusDesc = shelfStatusDesc;
	}
	
	

	

	
    
    
}