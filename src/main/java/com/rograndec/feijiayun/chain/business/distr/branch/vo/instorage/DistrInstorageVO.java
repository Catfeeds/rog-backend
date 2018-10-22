package com.rograndec.feijiayun.chain.business.distr.branch.vo.instorage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Validated
@ApiModel(value = "AddInstorageVO", description = "采购管理-采购入库-新增对象")
public class DistrInstorageVO implements Serializable{

	/**
	 * 1 表示 配进入库调用
	 */
	private Integer type;


	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "采购订单主键ID,引用采购订单时必须传",required = true)
	private Long purchaseOrderId;

	/**
     * 供货单位ID
     */
	@NotNull(message="供货单位ID不能为空！")
	@ApiModelProperty(value = "供货单位ID",required = true)
    private Long supplierId;

	@ApiModelProperty(required = true, value = "配进入库日期不能为空")
	private Date inDate;

	@NotNull(message="收货日期不能为空！")
	@ApiModelProperty(required = true, value = "收货日期")
    private Date receiveDate;
	
	@NotNull(message="验收日期不能为空！")
	@ApiModelProperty(value = "验收日期", required = true)
    private Date checkDate;
	
	// 人员信息
	@NotNull(message="采购人ID不能为空！")
	@ApiModelProperty(value = "采购人ID",required = true)
    private Long purchaserId;
	
	@NotNull(message="入库人员ID不能为空！")
	@ApiModelProperty(required = true, value = "入库人员ID")
    private Long storageManId;

	@ApiModelProperty(required = true, value = "入库人员")
	private String storageManName;

	@NotNull(message="收货人员ID不能为空！")
	@ApiModelProperty(required = true, value = "收货人员ID")
    private Long receiverId;
	
	@ApiModelProperty(value = "收货人2ID")
    private Long secondReceiverId;
	
	@NotNull(message="验收人员ID不能为空！")
	@ApiModelProperty(value = "验收人员ID", required = true)
    private Long checkerId;
	
	@ApiModelProperty(value = "验收人员2ID")
    private Long secondCheckerId;
	
	@ApiModelProperty(value = "供货单位名称")
	private String supplierName;
	
	@NotNull(message="供货单位销售人员ID不能为空！")
	@ApiModelProperty(value = "供货单位销售人员ID",required = true)
    private Long supplierSalerId;
	
	@ApiModelProperty(required = true, value = "流通监管码")
	private String flowCode;

	@ApiModelProperty(value = "品种数量",hidden=true)
    private Integer varietiesQuantity;

	@ApiModelProperty(value = "数量合计")
	private BigDecimal quantityTotal;
	
	@ApiModelProperty(value = "金额合计（整单优惠前的金额合计）",required = true)
    private BigDecimal amountTotal = BigDecimal.ZERO;
	
	@DecimalMin(value="0",message="整单折扣必须大于0")
	@NotNull(message="整单折扣不能为空！")
	@ApiModelProperty(value = "整单折扣（%）",required = true)
    private BigDecimal wholeDiscount;
	
	@DecimalMin(value="0",message="整单优惠金额必须大于0")
    @NotNull(message="整单优惠金额不能为空！")
	@ApiModelProperty(value = "整单优惠金额",required = true)
    private BigDecimal wholeDiscountAmount;
	
	@ApiModelProperty(value = "实际金额合计",required = true)
    private BigDecimal realAmountTotal = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "不含税金额合计",required = true)
    private BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
	
	@ApiModelProperty(value = "税额合计",required = true)
    private BigDecimal taxAmountTotal = BigDecimal.ZERO;
	
	//入库明细
	@Valid
	private List<DistrInstorageDtlVO> addInstorageDtlVOList;

	//配进入库的信息
	@ApiModelProperty(value = "配进订单ID，调用配进订单时必传")
	private Long noticeId;

	@ApiModelProperty(value = "要货计划单ID,调用要货计划时必传")
	private Long planId;
	/**
	 * 配送类型（0-总部配送；3-分店间调剂；4-直调配送）")
	 */
	@ApiModelProperty(value = "配送类型（0-总部配送；3-分店间调剂；4-直调配送）")
	private Integer distrType;
	@ApiModelProperty(value = "配送类型（0-总部配送；3-分店间调剂；4-直调配送）")
	private String distrTypeName;
	/**
	 * 配货单位ID
	 */
	@ApiModelProperty(value = "配货单位ID")
	private Long distrUnitId;

	/**
	 * 配货单位Code
	 */
	@ApiModelProperty(value = "配货单位Code")
	private String distrUnitCode;

	/**
	 * 配货单位名称
	 */
	@ApiModelProperty(value = "配货单位名称")
	private String distrUnitName;

	/**
	 * 出库单位ID
	 */
	@ApiModelProperty(value = "出库单位ID,配货类型为总部配送的不需要传这个值,配货类型为门店间调剂对应调出单位;配货类型为直调配送对应供货单位")
	private Long outboundUnitId;

	/**
	 * 出库单位
	 */
	@ApiModelProperty(value = "出库单位")
	private String outboundUnitCode;

	/**
	 * 出库单位
	 */
	@ApiModelProperty(value = "出库单位名称")
	private String outboundUnitName;

	//@NotNull(message="配进日期不能为空！")
	@ApiModelProperty(required = true, value = "配进日期")
	private Date orderDate;

	//@NotNull(message="配进人员ID不能为空！")
	@ApiModelProperty(value = "配进人员ID")
	private Long noticeStorageManId;

	@ApiModelProperty(value = "配进人员Name")
	private String noticeStorageManName;

	@ApiModelProperty(value = "备注")
	private String remark;


	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}



	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Long getPurchaserId() {
		return purchaserId;
	}

	public void setPurchaserId(Long purchaserId) {
		this.purchaserId = purchaserId;
	}

	public Long getStorageManId() {
		return storageManId;
	}

	public void setStorageManId(Long storageManId) {
		this.storageManId = storageManId;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public Long getSecondReceiverId() {
		return secondReceiverId;
	}

	public void setSecondReceiverId(Long secondReceiverId) {
		this.secondReceiverId = secondReceiverId;
	}

	public Long getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(Long checkerId) {
		this.checkerId = checkerId;
	}

	public Long getSecondCheckerId() {
		return secondCheckerId;
	}

	public void setSecondCheckerId(Long secondCheckerId) {
		this.secondCheckerId = secondCheckerId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Long getSupplierSalerId() {
		return supplierSalerId;
	}

	public void setSupplierSalerId(Long supplierSalerId) {
		this.supplierSalerId = supplierSalerId;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public Integer getVarietiesQuantity() {
		return varietiesQuantity;
	}

	public void setVarietiesQuantity(Integer varietiesQuantity) {
		this.varietiesQuantity = varietiesQuantity;
	}

	public BigDecimal getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}

	public BigDecimal getWholeDiscount() {
		return wholeDiscount;
	}

	public void setWholeDiscount(BigDecimal wholeDiscount) {
		this.wholeDiscount = wholeDiscount;
	}

	public BigDecimal getWholeDiscountAmount() {
		return wholeDiscountAmount;
	}

	public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
		this.wholeDiscountAmount = wholeDiscountAmount;
	}

	public BigDecimal getRealAmountTotal() {
		return realAmountTotal;
	}

	public void setRealAmountTotal(BigDecimal realAmountTotal) {
		this.realAmountTotal = realAmountTotal;
	}

	public BigDecimal getNotaxRealAmountTotal() {
		return notaxRealAmountTotal;
	}

	public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
		this.notaxRealAmountTotal = notaxRealAmountTotal;
	}

	public BigDecimal getTaxAmountTotal() {
		return taxAmountTotal;
	}

	public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
		this.taxAmountTotal = taxAmountTotal;
	}

	public List<DistrInstorageDtlVO> getAddInstorageDtlVOList() {
		return addInstorageDtlVOList;
	}

	public void setAddInstorageDtlVOList(List<DistrInstorageDtlVO> addInstorageDtlVOList) {
		this.addInstorageDtlVOList = addInstorageDtlVOList;
	}




	public Long getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public Integer getDistrType() {
		return distrType;
	}

	public void setDistrType(Integer distrType) {
		this.distrType = distrType;
	}

	public Long getDistrUnitId() {
		return distrUnitId;
	}

	public void setDistrUnitId(Long distrUnitId) {
		this.distrUnitId = distrUnitId;
	}

	public Long getOutboundUnitId() {
		return outboundUnitId;
	}

	public void setOutboundUnitId(Long outboundUnitId) {
		this.outboundUnitId = outboundUnitId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Long getNoticeStorageManId() {
		return noticeStorageManId;
	}

	public void setNoticeStorageManId(Long noticeStorageManId) {
		this.noticeStorageManId = noticeStorageManId;
	}

	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOutboundUnitCode() {
		return outboundUnitCode;
	}

	public void setOutboundUnitCode(String outboundUnitCode) {
		this.outboundUnitCode = outboundUnitCode;
	}

	public String getOutboundUnitName() {
		return outboundUnitName;
	}

	public void setOutboundUnitName(String outboundUnitName) {
		this.outboundUnitName = outboundUnitName;
	}

	public Integer getType() {
		if(type == null){
			return 0;
		}
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public String getDistrTypeName() {
		return distrTypeName;
	}

	public void setDistrTypeName(String distrTypeName) {
		this.distrTypeName = distrTypeName;
	}

	public String getStorageManName() {
		return storageManName;
	}

	public void setStorageManName(String storageManName) {
		this.storageManName = storageManName;
	}

	public String getNoticeStorageManName() {
		return noticeStorageManName;
	}

	public void setNoticeStorageManName(String noticeStorageManName) {
		this.noticeStorageManName = noticeStorageManName;
	}

	public String getDistrUnitCode() {
		return distrUnitCode;
	}

	public void setDistrUnitCode(String distrUnitCode) {
		this.distrUnitCode = distrUnitCode;
	}

	public String getDistrUnitName() {
		return distrUnitName;
	}

	public void setDistrUnitName(String distrUnitName) {
		this.distrUnitName = distrUnitName;
	}
}
