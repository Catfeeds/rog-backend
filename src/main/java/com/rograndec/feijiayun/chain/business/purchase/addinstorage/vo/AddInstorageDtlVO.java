package com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo;

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
 * @ClassName: AddInstorageDtlVO   
 * @Description: 采购管理-采购入库-订单详情列表
 * @author yuting.li
 * @version 1.0 
 * @date 2017年11月28日 下午8:28:14
 */
@ApiModel(value = "AddInstorageDtlVO", description = "采购管理-采购入库-订单详情列表")
public class AddInstorageDtlVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年11月29日 上午11:45:42 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="商品ID不能为空！")
	@ApiModelProperty(value = "商品ID",required =true)
    private Long goodsId;

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
	
	@Valid
	@ApiModelProperty(value = "批号信息")
	private List<AddInstorageGoodsLotNumberVO> goodsLotNumberVOList;

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
		if(refuseQuantity == null){
			refuseQuantity = BigDecimal.ZERO;
		}
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

	public List<AddInstorageGoodsLotNumberVO> getGoodsLotNumberVOList() {
		return goodsLotNumberVOList;
	}

	public void setGoodsLotNumberVOList(List<AddInstorageGoodsLotNumberVO> goodsLotNumberVOList) {
		this.goodsLotNumberVOList = goodsLotNumberVOList;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }


}
