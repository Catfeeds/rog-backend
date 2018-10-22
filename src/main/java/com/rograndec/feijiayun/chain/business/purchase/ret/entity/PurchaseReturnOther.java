package com.rograndec.feijiayun.chain.business.purchase.ret.entity;

import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.ret.constant.OutFlag;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.PurchaseReturnOtherSaveOrUpdateVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.SaveOrUpdateOtherBzVO;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
 
/**
 * 
 * saas_purchase_return_other
 * 
 * 
 * @author ST
 * 
 * 2017-09-15
 */
public class PurchaseReturnOther implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 购进退出单ID
     */
    @ApiModelProperty(value = "购进退出单ID")
    private Long returnId;

    /**
     * 结算方式（0-现结；1-账期）
     */
    @ApiModelProperty(value = "结算方式（0-现结；1-账期）")
    private Integer settlementType;
    
    /**
     * 结算单位ID
     */
    @ApiModelProperty(value = "结算单位ID")
    private Long settlementUnitId;

    /**
     * 结算单位
     */
    @ApiModelProperty(value = "结算单位")
    private String settlementUnit;

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
     * 收货人员
     */
    @ApiModelProperty(value = "收货人员")
    private String receiver;

    /**
     * 收货人员电话
     */
    @ApiModelProperty(value = "收货人员电话")
    private String receivePhone;

    /**
     * 收货地址
     */
    @ApiModelProperty(value = "收货地址")
    private String receiveAddress;

    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    /**
     * 订单单号
     */
    @ApiModelProperty(value = "订单单号")
    private String orderCode;

    /**
     * 订单日期
     */
    @ApiModelProperty(value = "订单日期")
    private Date orderDate;

    /**
     * 出库标志（0-未出库；1-已出库）
     */
    @ApiModelProperty(value = "出库标志（0-未出库；1-已出库）")
    private Integer outFlag;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


    public static PurchaseReturnOther getPurchaseReturnOther4VO(
            SaveOrUpdateOtherBzVO saveOrUpdateOtherBzVO
        ){

        PurchaseReturnOther purchaseReturnOther = new PurchaseReturnOther();
        PurchaseReturnOtherSaveOrUpdateVO otherVO = saveOrUpdateOtherBzVO.getOtherVO();
        purchaseReturnOther.setId(otherVO.getId());
        purchaseReturnOther.setEnterpriseId(saveOrUpdateOtherBzVO.getUserVO().getEnterpriseId());
        if(saveOrUpdateOtherBzVO.getUserVO().getChainType().equals(ChainType.Headquarters.getCode())){
            purchaseReturnOther.setParentId(0L);
        }else {
            purchaseReturnOther.setParentId(saveOrUpdateOtherBzVO.getUserVO().getParentId());
        }
        PurchaseReturn purchaseReturn = saveOrUpdateOtherBzVO.getPurchaseReturn();
        purchaseReturnOther.setReturnId(purchaseReturn.getId());
        /**
         * 结算方式（0-现结；1-账期）
         */
        purchaseReturnOther.setSettlementType(otherVO.getSettlementType());
        /**
         * 结算单位
         */
        purchaseReturnOther.setSettlementUnitId(otherVO.getSettlementUnitId());
        /**
         * 结算单位
         */
        purchaseReturnOther.setSettlementUnit(otherVO.getSettlementUnit());
        /**
         * 结算单位电话
         */
        purchaseReturnOther.setSettlementUnitPhone(otherVO.getSettlementUnitPhone());
        /**
         * 结算单位地址
         */
        purchaseReturnOther.setSettlementUnitAddress(otherVO.getSettlementUnitAddress());
        /**
         * 开户户名
         */
        purchaseReturnOther.setAccountName(otherVO.getAccountName());
        /**
         * 开户账号
         */
        purchaseReturnOther.setAccount(otherVO.getAccount());

        /**
         * 开户银行
         */
        purchaseReturnOther.setBank(otherVO.getBank());

        /**
         * 发票类型（0-普通发票；1-增值税发票）
         */
        purchaseReturnOther.setInvoiceType(otherVO.getInvoiceType());

        /**
         * 纳税人识别号
         */
        purchaseReturnOther.setTaxpayerCode(otherVO.getTaxpayerCode());

        /**
         * 承运方式（0-配送；1-委托运输；2-自提）
         */
        purchaseReturnOther.setCarriageMode(otherVO.getCarriageMode());
        /**
         * 承运单位
         */
        purchaseReturnOther.setCarriageUnit(otherVO.getCarriageUnit());

        /**
         * 运输方式（0-陆运；1-空运；2-海运）
         */
        purchaseReturnOther.setTransportMode(otherVO.getTransportMode());

        /**
         * 温控方式（0-冷藏车；1-冷藏箱；2-保温箱）
         */
        purchaseReturnOther.setTempControlMode(otherVO.getTempControlMode());


//        /**
//         * 收货单位
//         */
//        purchaseReturnOther.setReceiveUnit(otherVO.getReceiveUnit());

        /**
         * 收货人员
         */
        purchaseReturnOther.setReceiver(otherVO.getReceiver());

        /**
         * 收货人员电话
         */
        purchaseReturnOther.setReceivePhone(otherVO.getReceivePhone());

        /**
         * 收货地址
         */
        purchaseReturnOther.setReceiveAddress(otherVO.getReceiveAddress());

        PurchaseOrder purchaseOrder = saveOrUpdateOtherBzVO.getPurchaseOrder();
        if(null != purchaseOrder){
            purchaseReturnOther.setOrderId(purchaseOrder.getId());
            purchaseReturnOther.setOrderCode(purchaseOrder.getCode());
            purchaseReturnOther.setOrderDate(purchaseOrder.getOrderDate());
            purchaseReturnOther.setOutFlag(OutFlag.NOT_OUT.getCode());
        }
        purchaseReturnOther.setStatus(PurchaseStatus.PENDING_AUDIT.getStatus());

        return purchaseReturnOther;
    }

    /**
     * saas_purchase_return_other
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
     * 购进退出单ID
     * @return return_id 购进退出单ID
     */
    public Long getReturnId() {
        return returnId;
    }

    /**
     * 购进退出单ID
     * @param returnId 购进退出单ID
     */
    public void setReturnId(Long returnId) {
        this.returnId = returnId;
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
     * @return receive_phone 收货人员电话
     */
    public String getReceivePhone() {
        return receivePhone;
    }

    /**
     * 收货人员电话
     * @param receivePhone 收货人员电话
     */
    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone == null ? null : receivePhone.trim();
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
     * 订单ID
     * @return order_id 订单ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 订单ID
     * @param orderId 订单ID
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 订单单号
     * @return order_code 订单单号
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 订单单号
     * @param orderCode 订单单号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * 订单日期
     * @return order_date 订单日期
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * 订单日期
     * @param orderDate 订单日期
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * 出库标志（0-未出库；1-已出库）
     * @return out_flag 出库标志（0-未出库；1-已出库）
     */
    public Integer getOutFlag() {
        return outFlag;
    }

    /**
     * 出库标志（0-未出库；1-已出库）
     * @param outFlag 出库标志（0-未出库；1-已出库）
     */
    public void setOutFlag(Integer outFlag) {
        this.outFlag = outFlag;
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

    public Long getSettlementUnitId() {
		return settlementUnitId;
	}

	public void setSettlementUnitId(Long settlementUnitId) {
		this.settlementUnitId = settlementUnitId;
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
        sb.append(", returnId=").append(returnId);
        sb.append(", settlementType=").append(settlementType);
        sb.append(", settlementUnit=").append(settlementUnit);
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
        sb.append(", receiver=").append(receiver);
        sb.append(", receivePhone=").append(receivePhone);
        sb.append(", receiveAddress=").append(receiveAddress);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", outFlag=").append(outFlag);
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