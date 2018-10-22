package com.rograndec.feijiayun.chain.inf.pos.sale.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class POSOrderVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月10日 下午4:10:55 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;
	
	/**
     * 销售日期
     */
    @ApiModelProperty(value = "销售日期")
    private Date saleDate;

    /**
     * 销售单号
     */
    @ApiModelProperty(value = "销售单号")
    private String code;
    
    /**
     * 款台编码
     */
    @ApiModelProperty(value = "款台编码")
    private String standCode;
    
    /**
     * 收款人名称
     */
    @ApiModelProperty(value = "收款人名称")
    private String payeeName;
    
    /**
     * 实际金额合计
     */
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;
    
    /**
     * 订单可退详情
     */
    @ApiModelProperty(value = "订单可退详情")
    private List<POSOrderDetailVO> pOSOrderDetailVOList;

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStandCode() {
		return standCode;
	}

	public void setStandCode(String standCode) {
		this.standCode = standCode;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public BigDecimal getRealAmountTotal() {
		return realAmountTotal;
	}

	public void setRealAmountTotal(BigDecimal realAmountTotal) {
		this.realAmountTotal = realAmountTotal;
	}

	public List<POSOrderDetailVO> getpOSOrderDetailVOList() {
		return pOSOrderDetailVOList;
	}

	public void setpOSOrderDetailVOList(List<POSOrderDetailVO> pOSOrderDetailVOList) {
		this.pOSOrderDetailVOList = pOSOrderDetailVOList;
	}
	

}
