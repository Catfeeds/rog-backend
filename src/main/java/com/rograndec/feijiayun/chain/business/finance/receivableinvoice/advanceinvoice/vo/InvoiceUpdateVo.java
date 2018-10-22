package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.vo;

import java.io.Serializable;
import java.util.Date;

import com.rograndec.feijiayun.chain.common.valid.annotation.ValidMax;
import com.rograndec.feijiayun.chain.common.valid.annotation.ValidNotNull;

import io.swagger.annotations.ApiModelProperty;

public class InvoiceUpdateVo implements Serializable{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	  /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @ValidNotNull()
    private Long id;
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


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
    @ValidMax(20)
    private String billManCode;*/

    /**
     * 开票人员名称
     */
  /*  @ApiModelProperty(value = "开票人员名称")
    @ValidMax(50)
    private String billManName;*/
    

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @ValidMax(200)
    private String remark;
    /**
     * 修改原因 
     */
    @ApiModelProperty(value = "修改原因 ")
    @ValidMax(100)
    @ValidNotNull()
    private String reason;
    /**
     * 修改清单
     */
    @ApiModelProperty(value = "修改清单 ")
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


/*	public String getBillManCode() {
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
	}
*/

	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public AdvanceReceivableInvoiceInfoVo getAdvanceReceivableInvoiceInfoVo() {
		return advanceReceivableInvoiceInfoVo;
	}


	public void setAdvanceReceivableInvoiceInfoVo(AdvanceReceivableInvoiceInfoVo advanceReceivableInvoiceInfoVo) {
		this.advanceReceivableInvoiceInfoVo = advanceReceivableInvoiceInfoVo;
	}
}
