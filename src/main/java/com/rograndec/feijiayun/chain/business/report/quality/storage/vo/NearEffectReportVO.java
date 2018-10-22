package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel(value = "NearEffectReportVO", description = "近期效商品报表返回对象")
public class NearEffectReportVO<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "数据集合")
	private List<T> dataList;
    
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantity;
    

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	
	
}
