package com.rograndec.feijiayun.chain.business.purchase.ret.vo;

import com.rograndec.feijiayun.chain.business.purchase.ret.constant.ReturnType;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturn;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnDetail;
import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnOther;
import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述：
 * Created by zhaiwei on 2017/9/15 15:09
 */

public class PurchaseReturnShowVO implements Serializable{

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    /**
     * 购进退出单号
     */
    @ApiModelProperty(value = "购进退出单号")
    private String code;

    /**
     * 退货人员ID
     */
    @ApiModelProperty(value = "退货人员ID")
    private Long returnManId;

    /**
     * 退货人员编码
     */
    @ApiModelProperty(value = "退货人员编码")
    private String returnManCode;

    /**
     * 退货人员名称
     */
    @ApiModelProperty(value = "退货人员名称")
    private String returnManName;

    /**
     * 购进退出单ID
     */
    @ApiModelProperty(value = "购进退出单ID")
    private Long returnId;

    /**
     * 购进退出单编号
     */
    @ApiModelProperty(value = "购进退出单编号")
    private String returnCode;

    /**
     * 购进退出单日期
     */
    @ApiModelProperty(value = "购进退出单日期")
    private Date returnDate;

    /**
     * 供货单位ID
     */
    @ApiModelProperty(value = "供货单位ID")
    private Long supplierId;
    /**
     * 供货单位所属企业的ID
     */
    @ApiModelProperty(value = "供货单位所属企业的ID,用于判断本供货是否属于当前登录企业")
    private Long enterpriseId;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(value = "供货单位编码")
    private String supplierCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "供货单位名称")
    private String supplierName;


    @ApiModelProperty(value = "供货单位人员")
    private Long supplierSalerId;
    @ApiModelProperty(value = "供货单位编码")
    private String supplierSalerCode;
    @ApiModelProperty(value = "供货单位姓名")
    private String supplierSalerName;
    @ApiModelProperty(value = "供货单位电话")
    private String supplierSalerPhone;

    /**
     * 金额合计（整单优惠前的金额合计）
     */
    @ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal;

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
     * 实际金额合计
     */
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

    /**
     * 利润合计
     */
    @ApiModelProperty(value = "利润合计")
    private BigDecimal profitTotal;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 不含税利润合计
     */
    @ApiModelProperty(value = "不含税利润合计")
    private BigDecimal notaxProfitTotal;

    /**
     * 利润率
     */
    @ApiModelProperty(value = "利润率")
    private BigDecimal profitRate;

    /**
     * 不含税利润率
     */
    @ApiModelProperty(value = "不含税利润率")
    private BigDecimal notaxProfitRate;

    /**
     * 退货类型（0-质量问题退货；1-非质量问题退货）
     */
    @ApiModelProperty(value = "退货类型（0-质量问题退货；1-非质量问题退货）")
    private Integer returnType;

    @ApiModelProperty(value = "退货类型描述（0-质量问题退货；1-非质量问题退货）")
    private String returnTypeDesc;


    @ApiModelProperty(value = "审批状态")
    private Integer approvalStatus;

    @ApiModelProperty(value = "审批状态描述")
    private String approvalStatusDesc;

    @ApiModelProperty(value = "退进退出明细集合")
    private List<PurchaseReturnDetailShowVO> purchaseReturnDetailShowVOS;

    @ApiModelProperty(value = "退进退出配送和结算")
    private PurchaseReturnOtherShowVO purchaseReturnOtherShowVO;
    
    /**
     * 配货类型   0-总部配送；1-委托运输；2-自提  ；4-直调配送
     */
    @ApiModelProperty(value = " 配货类型ID  （ 0-总部配送；1-委托运输；2-自提  ；4-直调配送）" )
    private Integer carriageMode;
    
    /**
     * 配货类型   0-总部配送；1-委托运输；2-自提  ；4-直调配送
     */
    @ApiModelProperty(value = " 配货类型  （ 0-总部配送；1-委托运输；2-自提  ；4-直调配送）" )
    private String carriageModeName;
    
    /**
     * 药店类型（0-总部；1-自营店；2-加盟店）
     */
	@ApiModelProperty(value = "药店类型（0-总部；1-自营店；2-加盟店）")
    private Integer chainType; 
	
	/**
     * 结算单位ID,若该值与当前登录人企业id不同则特殊显示
     */
	@ApiModelProperty(value = "结算单位ID,若该值与当前登录人企业id不同则特殊显示")
    private Long settlementUnitId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    public static PurchaseReturnShowVO getPurchaseReturnShowVO(
            PurchaseReturn purchaseReturn
            , List<PurchaseReturnDetail> purchaseReturnDetails
            , PurchaseReturnOther purchaseReturnOther
            , List<QualitySet> qualitySets
    ){

        PurchaseReturnShowVO purchaseReturnShowVO = getPurchaseReturnShowVO(purchaseReturn);

        List<PurchaseReturnDetailShowVO> purchaseReturnDetailShowVOS = new ArrayList<>();
        for (PurchaseReturnDetail prd : purchaseReturnDetails){
            PurchaseReturnDetailShowVO purchaseReturnDetailShowVO = PurchaseReturnDetailShowVO.getPurchaseReturnDetailShowVOs(prd, qualitySets);
            purchaseReturnDetailShowVOS.add(purchaseReturnDetailShowVO);
        }

        PurchaseReturnOtherShowVO purchaseReturnOtherShowVO = PurchaseReturnOtherShowVO.getPurchaseReturnOtherShowVO(purchaseReturnOther);

        purchaseReturnShowVO.setPurchaseReturnDetailShowVOS(purchaseReturnDetailShowVOS);
        purchaseReturnShowVO.setPurchaseReturnOtherShowVO(purchaseReturnOtherShowVO);
        return purchaseReturnShowVO;
    }


    public static PurchaseReturnShowVO getPurchaseReturnShowVO(PurchaseReturn purchaseReturn){

        PurchaseReturnShowVO pvo = new PurchaseReturnShowVO();

        pvo.setId(purchaseReturn.getId());
        pvo.setCode(purchaseReturn.getCode());
        pvo.setReturnDate(purchaseReturn.getReturnDate());
        pvo.setSupplierId(purchaseReturn.getSupplierId());
        pvo.setSupplierCode(purchaseReturn.getSupplierCode());
        pvo.setSupplierName(purchaseReturn.getSupplierName());
        pvo.setReturnManId(purchaseReturn.getReturnManId());
        pvo.setReturnManCode(purchaseReturn.getReturnManCode());
        pvo.setReturnManName(purchaseReturn.getReturnManName());
        pvo.setAmountTotal(purchaseReturn.getAmountTotal());
        pvo.setWholeDiscount(purchaseReturn.getWholeDiscount());
        pvo.setWholeDiscountAmount(purchaseReturn.getWholeDiscountAmount());
        pvo.setRealAmountTotal(purchaseReturn.getRealAmountTotal());
        pvo.setNotaxRealAmountTotal(purchaseReturn.getNotaxRealAmountTotal());
        pvo.setTaxAmountTotal(purchaseReturn.getTaxAmountTotal());
        pvo.setApprovalStatus(purchaseReturn.getStatus());
        pvo.setApprovalStatusDesc(PurchaseStatus.getName(purchaseReturn.getStatus()));
        pvo.setReturnId(purchaseReturn.getId());
        pvo.setReturnCode(purchaseReturn.getCode());
        pvo.setReturnType(purchaseReturn.getReturnType());
        pvo.setReturnTypeDesc(ReturnType.getValue(purchaseReturn.getReturnType()).getValue());
        pvo.setSupplierSalerId(purchaseReturn.getSupplierSalerId());
        pvo.setSupplierSalerName(purchaseReturn.getSupplierName());
        pvo.setSupplierSalerCode(purchaseReturn.getSupplierCode());
        pvo.setSupplierSalerPhone(purchaseReturn.getSupplierSalerPhone());
        pvo.setQuantityTotal(purchaseReturn.getQuantityTotal());
        pvo.setCarriageMode(purchaseReturn.getCarriageMode());
        pvo.setChainType(purchaseReturn.getChainType());
        pvo.setCarriageModeName(DistributionType.getName(purchaseReturn.getCarriageMode()) == null?"":DistributionType.getName(purchaseReturn.getCarriageMode()));
        pvo.setSettlementUnitId(purchaseReturn.getSettlementUnitId());
        pvo.setRemark(purchaseReturn.getRemark());
        return pvo;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Long getReturnManId() {
        return returnManId;
    }

    public void setReturnManId(Long returnManId) {
        this.returnManId = returnManId;
    }

    public String getReturnManCode() {
        return returnManCode;
    }

    public void setReturnManCode(String returnManCode) {
        this.returnManCode = returnManCode;
    }

    public String getReturnManName() {
        return returnManName;
    }

    public void setReturnManName(String returnManName) {
        this.returnManName = returnManName;
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

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalStatusDesc() {
        return approvalStatusDesc;
    }

    public void setApprovalStatusDesc(String approvalStatusDesc) {
        this.approvalStatusDesc = approvalStatusDesc;
    }

    public List<PurchaseReturnDetailShowVO> getPurchaseReturnDetailShowVOS() {
        return purchaseReturnDetailShowVOS;
    }

    public void setPurchaseReturnDetailShowVOS(List<PurchaseReturnDetailShowVO> purchaseReturnDetailShowVOS) {
        this.purchaseReturnDetailShowVOS = purchaseReturnDetailShowVOS;
    }

    public PurchaseReturnOtherShowVO getPurchaseReturnOtherShowVO() {
        return purchaseReturnOtherShowVO;
    }

    public void setPurchaseReturnOtherShowVO(PurchaseReturnOtherShowVO purchaseReturnOtherShowVO) {
        this.purchaseReturnOtherShowVO = purchaseReturnOtherShowVO;
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

    public BigDecimal getProfitTotal() {
        return profitTotal;
    }

    public void setProfitTotal(BigDecimal profitTotal) {
        this.profitTotal = profitTotal;
    }

    public BigDecimal getNotaxProfitTotal() {
        return notaxProfitTotal;
    }

    public void setNotaxProfitTotal(BigDecimal notaxProfitTotal) {
        this.notaxProfitTotal = notaxProfitTotal;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public BigDecimal getNotaxProfitRate() {
        return notaxProfitRate;
    }

    public void setNotaxProfitRate(BigDecimal notaxProfitRate) {
        this.notaxProfitRate = notaxProfitRate;
    }

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
    }

    public String getReturnTypeDesc() {
        return returnTypeDesc;
    }

    public void setReturnTypeDesc(String returnTypeDesc) {
        this.returnTypeDesc = returnTypeDesc;
    }

    public Long getSupplierSalerId() {
        return supplierSalerId;
    }

    public void setSupplierSalerId(Long supplierSalerId) {
        this.supplierSalerId = supplierSalerId;
    }

    public String getSupplierSalerCode() {
        return supplierSalerCode;
    }

    public void setSupplierSalerCode(String supplierSalerCode) {
        this.supplierSalerCode = supplierSalerCode;
    }

    public String getSupplierSalerName() {
        return supplierSalerName;
    }

    public void setSupplierSalerName(String supplierSalerName) {
        this.supplierSalerName = supplierSalerName;
    }

    public String getSupplierSalerPhone() {
        return supplierSalerPhone;
    }

    public void setSupplierSalerPhone(String supplierSalerPhone) {
        this.supplierSalerPhone = supplierSalerPhone;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

	public Integer getCarriageMode() {
		return carriageMode;
	}

	public void setCarriageMode(Integer carriageMode) {
		this.carriageMode = carriageMode;
	}

	public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}


	public String getCarriageModeName() {
		return carriageModeName;
	}


	public void setCarriageModeName(String carriageModeName) {
		this.carriageModeName = carriageModeName;
	}


	public Long getSettlementUnitId() {
		return settlementUnitId;
	}


	public void setSettlementUnitId(Long settlementUnitId) {
		this.settlementUnitId = settlementUnitId;
	}


	public Long getEnterpriseId() {
		return enterpriseId;
	}


	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}