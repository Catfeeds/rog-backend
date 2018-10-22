package com.rograndec.feijiayun.chain.business.purchase.ret.vo;

import com.rograndec.feijiayun.chain.business.purchase.ret.entity.PurchaseReturnDetail;
import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.string.StringSplit;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_purchase_return_detail
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-09-15
 */
public class PurchaseReturnDetailShowVO implements Serializable {
    /**
     * 购进退出明细行ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;
    /**
     * 商品所属企业的ID
     */
    @ApiModelProperty(value = "商品所属企业的ID,用于判断本商品是否属于当前登录企业")
    private Long enterpriseId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

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
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    @ApiModelProperty(value = "批号id" ,required = true)
    private Long lotNumberId;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    @ApiModelProperty(value = "生产日期描述")
    private String productDateDesc;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validDate;

    @ApiModelProperty(value = "有效期描述")
    private String validDateDesc;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    /**
     * 行折扣（%）
     */
    @ApiModelProperty(value = "行折扣（%）")
    private BigDecimal lineDiscount;

    /**
     * 金额（整单优惠前金额）
     */
    @ApiModelProperty(value = "金额（整单优惠前金额）")
    private BigDecimal amount;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;

    /**
     * 行优惠（整单优惠分摊到行的金额）
     */
    @ApiModelProperty(value = "行优惠（整单优惠分摊到行的金额）")
    private BigDecimal lineDiscountAmount;

    /**
     * 实际单价（实际金额/数量）
     */
    @ApiModelProperty(value = "实际单价（实际金额/数量）")
    private BigDecimal realPrice;

    /**
     * 实际金额
     */
    @ApiModelProperty(value = "实际金额")
    private BigDecimal realAmount;

    /**
     * 进项税
     */
    @ApiModelProperty(value = "进项税")
    private BigDecimal taxRate;

    @ApiModelProperty(value = "进项税Id")
    private Long taxRateId;
    /**
     * 不含税实际单价
     */
    @ApiModelProperty(value = "不含税实际单价")
    private BigDecimal notaxRealPrice;

    /**
     * 不含税实际金额
     */
    @ApiModelProperty(value = "不含税实际金额")
    private BigDecimal notaxRealAmount;

    /**
     * 税额
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmount;

    /**
     * 利润金额
     */
    @ApiModelProperty(value = "利润金额")
    private BigDecimal profit;

    /**
     * 不含税利润金额
     */
    @ApiModelProperty(value = "不含税利润金额")
    private BigDecimal notaxProfit;

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
     * 退货原因ID集合，多个用逗号隔开
     */
    @ApiModelProperty(value = "退货原因ID集合")
    private List<Long> returnReasonIds;

    @ApiModelProperty(value = "退货原因描述集合，多个用逗号隔开")
    private String returnReasonIdsDesc;

    /**
      * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "状态描述")
    private String statusDesc;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    public static PurchaseReturnDetailShowVO getPurchaseReturnDetailShowVOs(PurchaseReturnDetail prd, List<QualitySet> qualitySets){

        PurchaseReturnDetailShowVO pvo = new PurchaseReturnDetailShowVO();
        pvo.setId(prd.getId());
        pvo.setGoodsId(prd.getGoodsId());
        pvo.setGoodsCode(prd.getGoodsCode());
        pvo.setGoodsGenericName(prd.getGoodsGenericName());
        pvo.setDosageId(prd.getDosageId());
        pvo.setDosageName(prd.getDosageName());
        pvo.setGoodsSpecification(prd.getGoodsSpecification());
        pvo.setManufacturerId(prd.getManufacturerId());
        pvo.setManufacturer(prd.getManufacturer());
        pvo.setApprovalNumber(prd.getApprovalNumber());
        pvo.setLotNumber(prd.getLotNumber());
        pvo.setLotNumberId(prd.getLotId());
        pvo.setProductDate(prd.getProductDate());
        pvo.setValidDate(prd.getValidDate());
        pvo.setQuantity(prd.getQuantity());
        pvo.setUnitPrice(prd.getUnitPrice());
        pvo.setLineDiscount(prd.getLineDiscount());
        pvo.setAmount(prd.getAmount());
        pvo.setWholeDiscount(prd.getWholeDiscount());
        pvo.setLineDiscountAmount(prd.getLineDiscountAmount());
        pvo.setRealPrice(prd.getRealPrice());
        pvo.setRealAmount(prd.getRealAmount());
        pvo.setTaxRate(prd.getTaxRate());
        pvo.setTaxRateId(prd.getTaxRateId());
        pvo.setNotaxRealPrice(prd.getNotaxRealPrice());
        pvo.setNotaxRealAmount(prd.getNotaxRealAmount());
        pvo.setTaxAmount(prd.getTaxAmount());
        String returnReasonIdsStr = prd.getReturnReasonIds();
        List<Long> longs = StringSplit.strSplit(returnReasonIdsStr);
        pvo.setReturnReasonIds(longs);

        StringBuffer sb = new StringBuffer();
        int i = 0;
        for(Long id : longs){
            for(QualitySet qualitySet : qualitySets){
                if(qualitySet.getId().equals(id)){
                    if(i != 0){
                        sb.append(",");
                    }
                    sb.append(qualitySet.getDescription());

                    i++;
                }

            }
        }

        pvo.setReturnReasonIdsDesc(sb.toString());
        pvo.setStatus(prd.getStatus());
        pvo.setStatusDesc(PurchaseStatus.getName(prd.getStatus()));
        pvo.setUnitId(prd.getUnitId());
        pvo.setUnitName(prd.getUnitName());
        pvo.setProductDateDesc(DateUtils.getDate(prd.getProductDate()));
        pvo.setValidDateDesc(DateUtils.getDate(prd.getValidDate()));
        pvo.setLineNum(prd.getLineNum());
        return pvo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
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

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
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

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
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

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getNotaxProfit() {
        return notaxProfit;
    }

    public void setNotaxProfit(BigDecimal notaxProfit) {
        this.notaxProfit = notaxProfit;
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

    public List<Long> getReturnReasonIds() {
        return returnReasonIds;
    }

    public void setReturnReasonIds(List<Long> returnReasonIds) {
        this.returnReasonIds = returnReasonIds;
    }

    public String getReturnReasonIdsDesc() {
        return returnReasonIdsDesc;
    }

    public void setReturnReasonIdsDesc(String returnReasonIdsDesc) {
        this.returnReasonIdsDesc = returnReasonIdsDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
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

    public String getProductDateDesc() {
        return productDateDesc;
    }

    public void setProductDateDesc(String productDateDesc) {
        this.productDateDesc = productDateDesc;
    }

    public String getValidDateDesc() {
        return validDateDesc;
    }

    public void setValidDateDesc(String validDateDesc) {
        this.validDateDesc = validDateDesc;
    }

    public Long getTaxRateId() {
        return taxRateId;
    }

    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    public Long getLotNumberId() {
        return lotNumberId;
    }

    public void setLotNumberId(Long lotNumberId) {
        this.lotNumberId = lotNumberId;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
    
}