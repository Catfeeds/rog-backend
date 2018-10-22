package com.rograndec.feijiayun.chain.business.purchase.order.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class PurchaseOrderOtherRequestVO implements Serializable {
	 /**
     * 主键ID
     */
	  @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 合同单号
     */
	  @ApiModelProperty(value = "合同单号")
    private String contractCode;

    /**
     * 采购计划ID
     */
	  @ApiModelProperty(value = "采购计划ID")
    private Long planId;

    /**
     * 采购计划单号
     */
	  @ApiModelProperty(value = "采购计划单号")
    private String planCode;

    /**
     * 结算方式（0-现结；1-账期）
     */
	  @ApiModelProperty(value = "结算方式（0-现结；1-账期)")
    private Integer settlementType;

    /**
     * 结算单位
     */
	  @ApiModelProperty(value = "结算单位")
       private String settlementUnit;
	   /**
	      * 结算单位ID
	      */
	     @ApiModelProperty(value = "结算单位iD")
	     private Long settlementUnitId;

	     /**
	      * 结算单位名称
	      */
	     @ApiModelProperty(value = "结算单位名称")
	     private String settlementUnitName;
    /**
     * 结算单位电话
     */
	  @ApiModelProperty(value = "结算单位电话")
    private String settlementUnitPhone;

    /**
     * 结算单位地址
     */
	  @ApiModelProperty(value = "结算单位地址")
    private String settlementUnitAddress;

    /**
     * 开户户名
     */
	  @ApiModelProperty(value = "开户户名")
    private String accountName;

    /**
     * 开户账号
     */
	  @ApiModelProperty(value = "开户账号")
    private String account;

    /**
     * 开户银行
     */
	  @ApiModelProperty(value = "开户银行")
    private String bank;

    /**
     * 发票类型（0-普通发票；1-增值税发票）
     */
	  @ApiModelProperty(value = "发票类型（0-普通发票；1-增值税发票）")
    private Integer invoiceType;

    /**
     * 纳税人识别号
     */
	  @ApiModelProperty(value = "纳税人识别号")
    private String taxpayerCode;

    /**
     * 承运方式（0-配送；1-委托运输；2-自提）
     */
	  @ApiModelProperty(value = "承运方式（0-配送；1-委托运输；2-自提）")
    private Integer carriageMode;

    /**
     * 承运单位
     */
	  @ApiModelProperty(value = "承运单位")
    private String carriageUnit;

    /**
     * 运输方式（0-陆运；1-空运；2-海运）
     */
	  @ApiModelProperty(value = "运输方式（0-陆运；1-空运；2-海运）")
    private Integer transportMode;

    /**
     * 温控方式（0-冷藏车；1-冷藏箱；2-保温箱）
     */
	  @ApiModelProperty(value = "温控方式（0-冷藏车；1-冷藏箱；2-保温箱）")
    private Integer tempControlMode;
	  /**
	     * 收货单位名称
	     * */
	  @ApiModelProperty(value = "收货单位名称")
	    private String receiveUnitName;
	    /**
	     * 收货单位编码
	     * */
	  @ApiModelProperty(value = "收货单位编码")
	    private String receiveUnitCode;
	    /**
	     * 收货人员编码
	     * */
	  @ApiModelProperty(value = " 收货人员编码")
	    private String receiverCode;
	    /**
	     * 收货人员姓名
	     * */
	  @ApiModelProperty(value = "收货人员姓名")
	    private String receiverName;
	    /**
	     * 收货人员id
	     * */
	  @ApiModelProperty(value = "收货人员id")
	    private Long reveiverId;
	    /**
	     * 收货单位id
	     * */
	  @ApiModelProperty(value = "收货单位id")
	    private Long receiveUnitId;

    /**
     * 收货人员电话
     */
	  @ApiModelProperty(value = "收货人员电话")
    private String reveiverPhone;

    /**
     * 收货地址
     */
	  @ApiModelProperty(value = "收货地址")
    private String receiveAddress;


    /**
     * 备注
     */
	  @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * saas_purchase_order_other
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
     * 合同单号
     * @return contract_code 合同单号
     */
    public String getContractCode() {
        return contractCode;
    }

    /**
     * 合同单号
     * @param contractCode 合同单号
     */
    public void setContractCode(String contractCode) {
        this.contractCode = contractCode == null ? null : contractCode.trim();
    }

    /**
     * 采购计划ID
     * @return plan_id 采购计划ID
     */
    public Long getPlanId() {
        return planId;
    }

    /**
     * 采购计划ID
     * @param planId 采购计划ID
     */
    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    /**
     * 采购计划单号
     * @return plan_code 采购计划单号
     */
    public String getPlanCode() {
        return planCode;
    }

    /**
     * 采购计划单号
     * @param planCode 采购计划单号
     */
    public void setPlanCode(String planCode) {
        this.planCode = planCode == null ? null : planCode.trim();
    }

    /**
     * 结算方式（0-现结；1-账期）
     * @return settlement_type 结算方式（0-现结；1-账期）
     */
    public Integer getSettlementType() {
        return settlementType;
    }

    /**
     * 结算方式（0-现结；1-账期）
     * @param settlementType 结算方式（0-现结；1-账期）
     */
    public void setSettlementType(Integer settlementType) {
        this.settlementType = settlementType;
    }

    /**
     * 结算单位
     * @return settlement_unit 结算单位
     */
    public String getSettlementUnit() {
        return settlementUnit;
    }

    /**
     * 结算单位
     * @param settlementUnit 结算单位
     */
    public void setSettlementUnit(String settlementUnit) {
        this.settlementUnit = settlementUnit == null ? null : settlementUnit.trim();
    }

    /**
     * 结算单位电话
     * @return settlement_unit_phone 结算单位电话
     */
    public String getSettlementUnitPhone() {
        return settlementUnitPhone;
    }

    /**
     * 结算单位电话
     * @param settlementUnitPhone 结算单位电话
     */
    public void setSettlementUnitPhone(String settlementUnitPhone) {
        this.settlementUnitPhone = settlementUnitPhone == null ? null : settlementUnitPhone.trim();
    }

    /**
     * 结算单位地址
     * @return settlement_unit_address 结算单位地址
     */
    public String getSettlementUnitAddress() {
        return settlementUnitAddress;
    }

    /**
     * 结算单位地址
     * @param settlementUnitAddress 结算单位地址
     */
    public void setSettlementUnitAddress(String settlementUnitAddress) {
        this.settlementUnitAddress = settlementUnitAddress == null ? null : settlementUnitAddress.trim();
    }

    /**
     * 开户户名
     * @return account_name 开户户名
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 开户户名
     * @param accountName 开户户名
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    /**
     * 开户账号
     * @return account 开户账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 开户账号
     * @param account 开户账号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 开户银行
     * @return bank 开户银行
     */
    public String getBank() {
        return bank;
    }

    /**
     * 开户银行
     * @param bank 开户银行
     */
    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    /**
     * 发票类型（0-普通发票；1-增值税发票）
     * @return invoice_type 发票类型（0-普通发票；1-增值税发票）
     */
    public Integer getInvoiceType() {
        return invoiceType;
    }

    /**
     * 发票类型（0-普通发票；1-增值税发票）
     * @param invoiceType 发票类型（0-普通发票；1-增值税发票）
     */
    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * 纳税人识别号
     * @return taxpayer_code 纳税人识别号
     */
    public String getTaxpayerCode() {
        return taxpayerCode;
    }

    /**
     * 纳税人识别号
     * @param taxpayerCode 纳税人识别号
     */
    public void setTaxpayerCode(String taxpayerCode) {
        this.taxpayerCode = taxpayerCode == null ? null : taxpayerCode.trim();
    }

    /**
     * 承运方式（0-配送；1-委托运输；2-自提）
     * @return carriage_mode 承运方式（0-配送；1-委托运输；2-自提）
     */
    public Integer getCarriageMode() {
        return carriageMode;
    }

    /**
     * 承运方式（0-配送；1-委托运输；2-自提）
     * @param carriageMode 承运方式（0-配送；1-委托运输；2-自提）
     */
    public void setCarriageMode(Integer carriageMode) {
        this.carriageMode = carriageMode;
    }

    /**
     * 承运单位
     * @return carriage_unit 承运单位
     */
    public String getCarriageUnit() {
        return carriageUnit;
    }

    /**
     * 承运单位
     * @param carriageUnit 承运单位
     */
    public void setCarriageUnit(String carriageUnit) {
        this.carriageUnit = carriageUnit == null ? null : carriageUnit.trim();
    }

    /**
     * 运输方式（0-陆运；1-空运；2-海运）
     * @return transport_mode 运输方式（0-陆运；1-空运；2-海运）
     */
    public Integer getTransportMode() {
        return transportMode;
    }

    /**
     * 运输方式（0-陆运；1-空运；2-海运）
     * @param transportMode 运输方式（0-陆运；1-空运；2-海运）
     */
    public void setTransportMode(Integer transportMode) {
        this.transportMode = transportMode;
    }

    /**
     * 温控方式（0-冷藏车；1-冷藏箱；2-保温箱）
     * @return temp_control_mode 温控方式（0-冷藏车；1-冷藏箱；2-保温箱）
     */
    public Integer getTempControlMode() {
        return tempControlMode;
    }

    /**
     * 温控方式（0-冷藏车；1-冷藏箱；2-保温箱）
     * @param tempControlMode 温控方式（0-冷藏车；1-冷藏箱；2-保温箱）
     */
    public void setTempControlMode(Integer tempControlMode) {
        this.tempControlMode = tempControlMode;
    }


    public String getReceiveUnitName() {
		return receiveUnitName;
	}

	public void setReceiveUnitName(String receiveUnitName) {
		this.receiveUnitName = receiveUnitName;
	}

	public String getReceiveUnitCode() {
		return receiveUnitCode;
	}

	public void setReceiveUnitCode(String receiveUnitCode) {
		this.receiveUnitCode = receiveUnitCode;
	}

	public String getReceiverCode() {
		return receiverCode;
	}

	public void setReceiverCode(String receiverCode) {
		this.receiverCode = receiverCode;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Long getReveiverId() {
		return reveiverId;
	}

	public void setReveiverId(Long reveiverId) {
		this.reveiverId = reveiverId;
	}

	public Long getReceiveUnitId() {
		return receiveUnitId;
	}

	public void setReceiveUnitId(Long receiveUnitId) {
		this.receiveUnitId = receiveUnitId;
	}


    /**
     * 收货人员电话
     * @return reveiver_phone 收货人员电话
     */
    public String getReveiverPhone() {
        return reveiverPhone;
    }

    /**
     * 收货人员电话
     * @param reveiverPhone 收货人员电话
     */
    public void setReveiverPhone(String reveiverPhone) {
        this.reveiverPhone = reveiverPhone == null ? null : reveiverPhone.trim();
    }

    /**
     * 收货地址
     * @return receive_address 收货地址
     */
    public String getReceiveAddress() {
        return receiveAddress;
    }

    /**
     * 收货地址
     * @param receiveAddress 收货地址
     */
    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress == null ? null : receiveAddress.trim();
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public Long getSettlementUnitId() {
		return settlementUnitId;
	}

	public void setSettlementUnitId(Long settlementUnitId) {
		this.settlementUnitId = settlementUnitId;
	}

	public String getSettlementUnitName() {
		return settlementUnitName;
	}

	public void setSettlementUnitName(String settlementUnitName) {
		this.settlementUnitName = settlementUnitName;
	}
   
}