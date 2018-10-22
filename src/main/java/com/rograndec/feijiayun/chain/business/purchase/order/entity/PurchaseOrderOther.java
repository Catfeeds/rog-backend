package com.rograndec.feijiayun.chain.business.purchase.order.entity;

import java.io.Serializable;
import java.util.Date;

public class PurchaseOrderOther implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 单据（采购订单）ID
     */
    private Long orderId;

    /**
     * 合同单号
     */
    private String contractCode;

    /**
     * 采购计划ID
     */
    private Long planId;

    /**
     * 采购计划单号
     */
    private String planCode;

    /**
     * 结算方式（0-现结；1-账期）
     */
    private Integer settlementType;

    /**
     * 结算单位
     */
    private String settlementUnit;

    /**
     * 结算单位ID
     */
    private Long settlementUnitId;

    /**
     * 结算单位名称
     */
    private String settlementUnitName;
    /**
     * 结算单位电话
     */
    private String settlementUnitPhone;

    /**
     * 结算单位地址
     */
    private String settlementUnitAddress;

    /**
     * 开户户名
     */
    private String accountName;

    /**
     * 开户账号
     */
    private String account;

    /**
     * 开户银行
     */
    private String bank;

    /**
     * 发票类型（0-普通发票；1-增值税发票）
     */
    private Integer invoiceType;

    /**
     * 纳税人识别号
     */
    private String taxpayerCode;

    /**
     * 配送方式（0-总部配送；1-委托运输；2-自提）
     */
    private Integer carriageMode;

    /**
     * 承运单位
     */
    private String carriageUnit;

    /**
     * 运输方式（0-陆运；1-空运；2-海运）
     */
    private Integer transportMode;

    /**
     * 温控方式（0-冷藏车；1-冷藏箱；2-保温箱）
     */
    private Integer tempControlMode;
    /**
     * 收货人员
     * */
    private String receiver;
    /**
     * 收货单位
     */
    private String receiveUnit;

    /**
     * 收货单位名称
     * */
    private String receiveUnitName;
    /**
     * 收货单位编码
     * */
    private String receiveUnitCode;
    /**
     * 收货人员编码
     * */
    private String receiverCode;
    /**
     * 收货人员姓名
     * */
    private String receiverName;
    /**
     * 收货人员id
     * */
    private Long reveiverId;
    /**
     * 收货单位
     * */
    private Long receiveUnitId;
    
    /**
     * 收货人员电话
     */
    private String reveiverPhone;

    /**
     * 收货地址
     */
    private String receiveAddress;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人ID
     */
    private Long createrId;

    /**
     * 创建人编码
     */
    private String createrCode;

    /**
     * 创建人名称
     */
    private String createrName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人ID
     */
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    private String modifierName;

    /**
     * 更新时间
     */
    private Date updateTime;

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
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 单据（采购订单）ID
     * @return order_id 单据（采购订单）ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 单据（采购订单）ID
     * @param orderId 单据（采购订单）ID
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    /**
     * 收货单位
     * @return receive_unit 收货单位
     */
    public String getReceiveUnit() {
        return receiveUnit;
    }

    /**
     * 收货单位
     * @param receiveUnit 收货单位
     */
    public void setReceiveUnit(String receiveUnit) {
        this.receiveUnit = receiveUnit == null ? null : receiveUnit.trim();
    }

    /**
     * 收货人员
     * @return receiver 收货人员
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * 收货人员
     * @param receiver 收货人员
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
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
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
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

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

	/**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", orderId=").append(orderId);
        sb.append(", contractCode=").append(contractCode);
        sb.append(", planId=").append(planId);
        sb.append(", planCode=").append(planCode);
        sb.append(", settlementType=").append(settlementType);
        sb.append(", settlementUnit=").append(settlementUnit);
        sb.append(", settlementUnitId=").append(settlementUnitId);
        sb.append(", settlementUnitName=").append(settlementUnitName);
        sb.append(", settlementUnitPhone=").append(settlementUnitPhone);
        sb.append(", settlementUnitAddress=").append(settlementUnitAddress);
        sb.append(", accountName=").append(accountName);
        sb.append(", account=").append(account);
        sb.append(", bank=").append(bank);
        sb.append(", invoiceType=").append(invoiceType);
        sb.append(", taxpayerCode=").append(taxpayerCode);
        sb.append(", carriageMode=").append(carriageMode);
        sb.append(", carriageUnit=").append(carriageUnit);
        sb.append(", transportMode=").append(transportMode);
        sb.append(", tempControlMode=").append(tempControlMode);
        sb.append(", receiveUnit=").append(receiveUnit);
        sb.append(", receiver=").append(receiver);
        sb.append(", receiveUnitName=").append(receiveUnitName);
        sb.append(", receiveUnitCode=").append(receiveUnitCode);
        sb.append(", receiverCode=").append(receiverCode);
        sb.append(", receiverName=").append(receiverName);
        sb.append(", reveiverId=").append(reveiverId);
        sb.append(", receiveUnitId=").append(receiveUnitId);
        sb.append(", reveiverPhone=").append(reveiverPhone);
        sb.append(", receiveAddress=").append(receiveAddress);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}