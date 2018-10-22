package com.rograndec.feijiayun.chain.business.report.quality.retail.vo;

import java.util.Date;

import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsRequestPageParamVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SaleOutORreturnParamVO", description = "销售出库/退货报表请求参数")
public class SaleOutORreturnParamVO extends BaseGoodsRequestPageParamVO{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年10月23日 上午11:27:53 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     * 单据（药品处理）日期
     */
    @ApiModelProperty(value = "开始日期")
    private Date startDate;
    
    @ApiModelProperty(value = "结束日期")
    private Date endDate;
    
    @ApiModelProperty(value = "销售单号")
    private String saleCode;
    
    @ApiModelProperty(value = "销售模式 0-常规模式，1-中药模式")
    private int saleMode;
    
    @ApiModelProperty(value = "销售类型 0-销售；1-销退",hidden=true)
    private int saleType;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public int getSaleMode() {
		return saleMode;
	}

	public void setSaleMode(int saleMode) {
		this.saleMode = saleMode;
	}

	public int getSaleType() {
		return saleType;
	}

	public void setSaleType(int saleType) {
		this.saleType = saleType;
	}
    

}
