package com.rograndec.feijiayun.chain.inf.pos.sale.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class POSSaleVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月8日 上午11:20:02 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
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
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID(包含处方单、划价单、专管登记单，从后台调取处方单、划价单、专管登记单传对应ID，POS新增时传-1，未关联处方单、划价单、销售单传空或0)")
    private Long baseOrderId;
    
    /**
     * 基础单据类型
     */
    @ApiModelProperty(value = "基础单据类型(划价单-6201、处方单-6202、专管药品单-6203)。其他值传空或0")
    private Integer baseOrderType;
    
    /**
     * POS新增专管登记单对象
     */
    @ApiModelProperty(value = "POS新增专管登记单对象，baseOrderId传-1时需要传")
    private POSNewSpecialRegisterVO pOSNewSpecialRegisterVO;
    
    /**
     * POS新增处方单对象
     */
    @ApiModelProperty(value = "POS新增处方单对象，baseOrderId传-1时需要传")
    private POSPrescriptionRegisterVO pOSPrescriptionRegisterVO;
    
    
    @ApiModelProperty(value = "收款单对象")
    private POSReceiptVO receipt;
    
    /**
     * 销售明细对象
     */
    private List<POSSaleDetailVO> saleDetailVOList;
    
    /**
     * 款台编码
     */
    @ApiModelProperty(value = "款台编码")
    private String standCode;

    /**
     * 收款人员ID
     */
    @ApiModelProperty(value = "收款人员ID")
    private Long payeeId;
    
    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID")
    private Long memberId;
    
    /**
     * 会员当前积分
     */
    @ApiModelProperty(value = "此单产生积分")
    private BigDecimal thisIntegralTotal;

    /**
     * 销售模式（0-常规；1-中药）
     */
    @ApiModelProperty(value = "销售模式（0-常规；1-中药）")
    private Integer saleMode;

    /**
     * 上传时间（0-否；1-是）
     */
    @ApiModelProperty(value = "上传时间")
    private Date uploadTime;
    
    /**
     * 剂量
     */
    @ApiModelProperty(value = "剂量")
    private BigDecimal dose;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;
    
    /**
     * 整单优惠金额
     */
    @ApiModelProperty(value = "整单优惠金额")
    private BigDecimal wholeDiscountAmount;
    
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

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	public Long getBaseOrderId() {
		return baseOrderId;
	}

	public void setBaseOrderId(Long baseOrderId) {
		this.baseOrderId = baseOrderId;
	}
	
	public Integer getBaseOrderType() {
		return baseOrderType;
	}

	public void setBaseOrderType(Integer baseOrderType) {
		this.baseOrderType = baseOrderType;
	}

	public POSPrescriptionRegisterVO getpOSPrescriptionRegisterVO() {
		return pOSPrescriptionRegisterVO;
	}

	public void setpOSPrescriptionRegisterVO(
			POSPrescriptionRegisterVO pOSPrescriptionRegisterVO) {
		this.pOSPrescriptionRegisterVO = pOSPrescriptionRegisterVO;
	}

	public List<POSSaleDetailVO> getSaleDetailVOList() {
		return saleDetailVOList;
	}

	public void setSaleDetailVOList(List<POSSaleDetailVO> saleDetailVOList) {
		this.saleDetailVOList = saleDetailVOList;
	}

	public String getStandCode() {
		return standCode;
	}

	public void setStandCode(String standCode) {
		this.standCode = standCode;
	}

	public Long getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Long payeeId) {
		this.payeeId = payeeId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	/*public String getMemberCardCode() {
		return memberCardCode;
	}

	public void setMemberCardCode(String memberCardCode) {
		this.memberCardCode = memberCardCode;
	}*/

	
	public Integer getSaleMode() {
		return saleMode;
	}

	public BigDecimal getThisIntegralTotal() {
		return thisIntegralTotal;
	}

	public void setThisIntegralTotal(BigDecimal thisIntegralTotal) {
		this.thisIntegralTotal = thisIntegralTotal;
	}

	public void setSaleMode(Integer saleMode) {
		this.saleMode = saleMode;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public BigDecimal getDose() {
		return dose;
	}

	public void setDose(BigDecimal dose) {
		this.dose = dose;
	}

	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public Integer getVarietiesQuantity() {
		return varietiesQuantity;
	}

	public void setVarietiesQuantity(Integer varietiesQuantity) {
		this.varietiesQuantity = varietiesQuantity;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	public POSNewSpecialRegisterVO getpOSNewSpecialRegisterVO() {
		return pOSNewSpecialRegisterVO;
	}

	public void setpOSNewSpecialRegisterVO(
			POSNewSpecialRegisterVO pOSNewSpecialRegisterVO) {
		this.pOSNewSpecialRegisterVO = pOSNewSpecialRegisterVO;
	}

	public POSReceiptVO getReceipt() {
		return receipt;
	}

	public void setReceipt(POSReceiptVO receipt) {
		this.receipt = receipt;
	}

}
