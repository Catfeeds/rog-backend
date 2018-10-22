package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseShelfDefVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by dongdong.zhang on 2017/10/8.
 */
public class DistrInLotNumVO implements Serializable,Comparable<DistrInLotNumVO>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 /**
     * 货位集合
     */
    @ApiModelProperty(value = "货位集合")
    List<WarehouseShelfDefVO> shelfs;
	
	 /**
     * 主键ID
     */
    @ApiModelProperty(value = "行详情   主键ID")
    private Long id;

	@ApiModelProperty(value = "商品ID")
    private Long goodsId;
    
    @ApiModelProperty(value = "批号ID")
    private Long lotId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNum;
    
    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;
    
    /**
     * 有效期至
     */
    @ApiModelProperty(value = "有效期至")
    private Date validDate;

    /**
     * 库存可用量
     */
    @ApiModelProperty(value = "库存可用量")
    private BigDecimal usableQuantity = BigDecimal.ZERO;
    
    /**
     * 批号数量（出库数量）
     */
    @ApiModelProperty(value = "批号数量（出库数量）")
    private BigDecimal outQuantity = BigDecimal.ZERO;
    
	@Override
	public int compareTo(DistrInLotNumVO o) {
		DistrInLotNumVO distrInLotNumVO = o;
		long date1 = distrInLotNumVO.getValidDate().getTime();
		long date = this.validDate.getTime();
		int result = date>date1?1:(date==date1?0:-1);
		return result;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getLotId() {
		return lotId;
	}

	public void setLotId(Long lotId) {
		this.lotId = lotId;
	}

	public String getLotNum() {
		return lotNum;
	}

	public void setLotNum(String lotNum) {
		this.lotNum = lotNum;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public BigDecimal getUsableQuantity() {
		return usableQuantity;
	}

	public void setUsableQuantity(BigDecimal usableQuantity) {
		this.usableQuantity = usableQuantity;
	}

	public BigDecimal getOutQuantity() {
		return outQuantity;
	}

	public void setOutQuantity(BigDecimal outQuantity) {
		this.outQuantity = outQuantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<WarehouseShelfDefVO> getShelfs() {
		return shelfs;
	}

	public void setShelfs(List<WarehouseShelfDefVO> shelfs) {
		this.shelfs = shelfs;
	}

}
