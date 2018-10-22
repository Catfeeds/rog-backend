package com.rograndec.feijiayun.chain.business.distr.branch.vo.instorage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @ClassName: DistrInstorageDtlVO
 * @Description: 采购管理-采购入库-订单详情列表
 * @author yuting.li
 * @version 1.0 
 * @date 2017年11月28日 下午8:28:14
 */
@ApiModel(value = "AddInstorageDtlVO", description = "采购管理-采购入库-订单详情列表")
public class DistrInstorageDtlVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull(message="商品ID不能为空！")
	@ApiModelProperty(value = "商品ID",required =true)
    private Long goodsId;

	/**
	 * 商品编码
	 */
	@ApiModelProperty(value = "商品编码")
	private String goodsCode;

	/**
	 * 条形码
	 */
	@ApiModelProperty(value = "条形码")
	private String barcode;

	/**
	 * 商品名称
	 */
	@ApiModelProperty(value = "商品名称")
	private String goodsName;

	/**
	 * 商品通用名称
	 */
	@ApiModelProperty(value = "商品通用名称")
	private String goodsGenericName;

	/**
	 * 剂型ID
	 */
	@ApiModelProperty(value = "剂型ID")
	private Long dosageId;

	/**
	 * 剂型名称
	 */
	@ApiModelProperty(value = "剂型名称")
	private String dosageName;

	/**
	 * 单位ID
	 */
	@ApiModelProperty(value = "单位ID")
	private Long unitId;

	/**
	 * 单位名称
	 */
	@ApiModelProperty(value = "单位名称")
	private String unitName;

	/**
	 * 商品规格
	 */
	@ApiModelProperty(value = "商品规格")
	private String goodsSpecification;

	/**
	 * 生产厂商ID
	 */
	@ApiModelProperty(value = "生产厂商ID")
	private Long manufacturerId;

	/**
	 * 生产厂商
	 */
	@ApiModelProperty(value = "生产厂商")
	private String manufacturer;

	/**
	 * 商品产地
	 */
	@ApiModelProperty(value = "商品产地")
	private String goodsPlace;

	/**
	 * 批准文号
	 */
	@ApiModelProperty(value = "批准文号")
	private String approvalNumber;
	
	@Range(min=0,message="收货数量必须大于0")
	@NotNull(message="收货数量不能为空！")
	@ApiModelProperty(required = true, value = "收货数量")
    private BigDecimal receiveQuantity;
	
	@Range(min=0,message="拒收数量必须大于0")
	@ApiModelProperty(required = true, value = "拒收数量")
    private BigDecimal refuseQuantity;
	
	@Range(min=0,message="验收合格数量必须大于0")
	@NotNull(message="验收合格数量不能为空！")
	@ApiModelProperty(value = "验收合格数量", required = true)
    private BigDecimal qualifiedQuantity;
	
	@Range(min=0,message="验收不合格数量必须大于0")
	@ApiModelProperty(value = "验收不合格数量", required = true)
    private BigDecimal unqualifiedQuantity;
	
	@Range(min=0,message="入库数量必须大于0")
	@NotNull(message="入库数量不能为空！")
	@ApiModelProperty(value = "入库数量",required=true)
	private BigDecimal quantity;
	
	@Range(min=0,message="单价不能小于0")
	@NotNull(message="单价不能为空！")
	@ApiModelProperty(value = "单价",required=true)
    private BigDecimal unitPrice;
	
	@Range(min=0,message="行折扣不能小于0")
	@ApiModelProperty(value = "行折扣（%）",required=true)
    private BigDecimal lineDiscount;
	
	@ApiModelProperty(value = "金额（整单优惠前金额）",required=true)
    private BigDecimal amount;
	
	@ApiModelProperty(value = "整单折扣（%）",required=true)
    private BigDecimal wholeDiscount;
	
	@ApiModelProperty(value = "行优惠（整单优惠分摊到行的金额）")
    private BigDecimal lineDiscountAmount;
	
	@ApiModelProperty(value = "实际金额",required=true)
    private BigDecimal realAmount;
	
	@NotNull(message="税率ID不能为空！")
	@ApiModelProperty(value = "税率ID",required=true)
    private Long taxRateId;
	@ApiModelProperty(value = "税率",required=true)
	private BigDecimal taxRate;
	@ApiModelProperty(value = "实际单价",hidden=true)
	private BigDecimal realPrice;
	@ApiModelProperty(value = "不含税实际单价",required=true)
    private BigDecimal notaxRealPrice;
	
	@ApiModelProperty(value = "不含税实际金额",required=true)
    private BigDecimal notaxRealAmount;
	
	@ApiModelProperty(value = "税额",required=true)
    private BigDecimal taxAmount;
	
	@Range(min=1,message="行号必须大于0")
	@NotNull(message="行号不能为空！")
	@ApiModelProperty(value = "行号",required=true)
    private Integer lineNum;
	
	@ApiModelProperty(value = "备注")
    private String remark;



	/**
	 * 默认货位ID
	 */
	@ApiModelProperty(value="默认货位ID")
	private Long defaultShelfId;

	/**
	 * 默认货位名称
	 */
	@ApiModelProperty(value="默认货位名称")
	private String defaultShelfName;


	public Long getDefaultShelfId() {
		return defaultShelfId;
	}

	public void setDefaultShelfId(Long defaultShelfId) {
		this.defaultShelfId = defaultShelfId;
	}

	public String getDefaultShelfName() {
		return defaultShelfName;
	}

	public void setDefaultShelfName(String defaultShelfName) {
		this.defaultShelfName = defaultShelfName;
	}




	@Valid
	@ApiModelProperty(value = "批号信息")
	private List<DistrInstorageGoodsLotNumberVO> goodsLotNumberVOList;

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public BigDecimal getReceiveQuantity() {
		return receiveQuantity;
	}

	public void setReceiveQuantity(BigDecimal receiveQuantity) {
		this.receiveQuantity = receiveQuantity;
	}

	public BigDecimal getRefuseQuantity() {
		return refuseQuantity;
	}

	public void setRefuseQuantity(BigDecimal refuseQuantity) {
		this.refuseQuantity = refuseQuantity;
	}

	public BigDecimal getQualifiedQuantity() {
		return qualifiedQuantity;
	}

	public void setQualifiedQuantity(BigDecimal qualifiedQuantity) {
		this.qualifiedQuantity = qualifiedQuantity;
	}

	public BigDecimal getUnqualifiedQuantity() {
		return unqualifiedQuantity;
	}

	public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) {
		this.unqualifiedQuantity = unqualifiedQuantity;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getLineDiscount() {
		return lineDiscount;
	}

	public void setLineDiscount(BigDecimal lineDiscount) {
		this.lineDiscount = lineDiscount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getWholeDiscount() {
		return wholeDiscount;
	}

	public void setWholeDiscount(BigDecimal wholeDiscount) {
		this.wholeDiscount = wholeDiscount;
	}

	public BigDecimal getLineDiscountAmount() {
		return lineDiscountAmount;
	}

	public void setLineDiscountAmount(BigDecimal lineDiscountAmount) {
		this.lineDiscountAmount = lineDiscountAmount;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public Long getTaxRateId() {
		return taxRateId;
	}

	public void setTaxRateId(Long taxRateId) {
		this.taxRateId = taxRateId;
	}

	public BigDecimal getNotaxRealPrice() {
		return notaxRealPrice;
	}

	public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
		this.notaxRealPrice = notaxRealPrice;
	}

	public BigDecimal getNotaxRealAmount() {
		return notaxRealAmount;
	}

	public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
		this.notaxRealAmount = notaxRealAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<DistrInstorageGoodsLotNumberVO> getGoodsLotNumberVOList() {
		return goodsLotNumberVOList;
	}

	public void setGoodsLotNumberVOList(List<DistrInstorageGoodsLotNumberVO> goodsLotNumberVOList) {
		this.goodsLotNumberVOList = goodsLotNumberVOList;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsGenericName() {
		return goodsGenericName;
	}

	public void setGoodsGenericName(String goodsGenericName) {
		this.goodsGenericName = goodsGenericName;
	}

	public Long getDosageId() {
		return dosageId;
	}

	public void setDosageId(Long dosageId) {
		this.dosageId = dosageId;
	}

	public String getDosageName() {
		return dosageName;
	}

	public void setDosageName(String dosageName) {
		this.dosageName = dosageName;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getGoodsSpecification() {
		return goodsSpecification;
	}

	public void setGoodsSpecification(String goodsSpecification) {
		this.goodsSpecification = goodsSpecification;
	}

	public Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getGoodsPlace() {
		return goodsPlace;
	}

	public void setGoodsPlace(String goodsPlace) {
		this.goodsPlace = goodsPlace;
	}

	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
}
