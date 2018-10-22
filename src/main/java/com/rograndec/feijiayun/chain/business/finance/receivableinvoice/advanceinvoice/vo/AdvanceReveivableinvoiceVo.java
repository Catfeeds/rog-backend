package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.rograndec.feijiayun.chain.common.valid.annotation.ValidMax;
import com.rograndec.feijiayun.chain.common.valid.annotation.ValidNotNull;

import io.swagger.annotations.ApiModelProperty;

public class AdvanceReveivableinvoiceVo implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    @ValidMax(50)
    private String code;
    /**
     * 开票日期
     */
    @ApiModelProperty(value = "开票日期")
    @ValidNotNull()
    private Date billDate;

    /**
     * 开票人员ID
     */
    @ApiModelProperty(value = "开票人员ID")
    @ValidNotNull()
    private Long billManId;

    /**
     * 开票人员编码
     */
  /*  @ApiModelProperty(value = "开票人员编码")
    @ValidNotNull()
    @ValidMax(20)
    private String billManCode;*/

    /**
     * 开票人员名称
     */
 /*   @ApiModelProperty(value = "开票人员名称")
    @ValidNotNull()
    @ValidMax(50)
    private String billManName;*/

    /**
     * 购货单位ID
     */
    @ApiModelProperty(value = "购货单位ID")
    @ValidNotNull()
    private Long purchaseUnitId;

    /**
     * 购货单位编码
     */
    @ApiModelProperty(value = "购货单位编码")
    @ValidNotNull()
    @ValidMax(50)
    private String purchaseUnitCode;

    /**
     * 购货单位名称
     */
    @ApiModelProperty(value = "购货单位名称")
    @ValidNotNull()
    @ValidMax(100)
    private String purchaseUnitName;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    @ValidNotNull()
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
/*    @ApiModelProperty(value = "品种数量")
    @ValidNotNull()
    private Integer varietiesQuantity;*/

    /**
     * 金额合计
     */
    @ApiModelProperty(value = "金额合计")
    @ValidNotNull()
    private BigDecimal amountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    @ValidNotNull()
    private BigDecimal notaxAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    @ValidNotNull()
    private BigDecimal taxAmountTotal;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @ValidMax(200)
    private String remark;
    /**
     * 草稿id
     */
    @ApiModelProperty(value = "草稿id")
    private String redisKeyValue;
    /**
     * 发票明细集合
     */
    @ApiModelProperty(value = "发票明细集合")
    @ValidNotNull()
    private List<AdvanceReceivableInvoiceDetailVo> aridvList;
    
    /**
     * 发票开票信息
     */
    @ApiModelProperty(value = "发票开票信息")
    @ValidNotNull()
    private AdvanceReceivableInvoiceInfoVo advanceReceivableInvoiceInfoVo;

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Long getBillManId() {
		return billManId;
	}

	public void setBillManId(Long billManId) {
		this.billManId = billManId;
	}

	/*public String getBillManCode() {
		return billManCode;
	}

	public void setBillManCode(String billManCode) {
		this.billManCode = billManCode;
	}

	public String getBillManName() {
		return billManName;
	}

	public void setBillManName(String billManName) {
		this.billManName = billManName;
	}*/

	public Long getPurchaseUnitId() {
		return purchaseUnitId;
	}

	public void setPurchaseUnitId(Long purchaseUnitId) {
		this.purchaseUnitId = purchaseUnitId;
	}

	public String getPurchaseUnitCode() {
		return purchaseUnitCode;
	}

	public void setPurchaseUnitCode(String purchaseUnitCode) {
		this.purchaseUnitCode = purchaseUnitCode;
	}

	public String getPurchaseUnitName() {
		return purchaseUnitName;
	}

	public void setPurchaseUnitName(String purchaseUnitName) {
		this.purchaseUnitName = purchaseUnitName;
	}

	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	/*public Integer getVarietiesQuantity() {
		return varietiesQuantity;
	}

	public void setVarietiesQuantity(Integer varietiesQuantity) {
		this.varietiesQuantity = varietiesQuantity;
	}*/

	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}

	public BigDecimal getNotaxAmountTotal() {
		return notaxAmountTotal;
	}

	public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
		this.notaxAmountTotal = notaxAmountTotal;
	}

	public BigDecimal getTaxAmountTotal() {
		return taxAmountTotal;
	}

	public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
		this.taxAmountTotal = taxAmountTotal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<AdvanceReceivableInvoiceDetailVo> getAridvList() {
		return aridvList;
	}

	public void setAridvList(List<AdvanceReceivableInvoiceDetailVo> aridvList) {
		this.aridvList = aridvList;
	}

	public AdvanceReceivableInvoiceInfoVo getAdvanceReceivableInvoiceInfoVo() {
		return advanceReceivableInvoiceInfoVo;
	}

	public void setAdvanceReceivableInvoiceInfoVo(AdvanceReceivableInvoiceInfoVo advanceReceivableInvoiceInfoVo) {
		this.advanceReceivableInvoiceInfoVo = advanceReceivableInvoiceInfoVo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRedisKeyValue() {
		return redisKeyValue;
	}

	public void setRedisKeyValue(String redisKeyValue) {
		this.redisKeyValue = redisKeyValue;
	}
    

}
