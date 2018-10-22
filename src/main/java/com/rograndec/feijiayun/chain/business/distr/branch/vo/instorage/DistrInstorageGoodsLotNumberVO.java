package com.rograndec.feijiayun.chain.business.distr.branch.vo.instorage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @ClassName: AddInstorageGoodsLotNumberVO   
 * @Description: 采购管理-采购入库-商品批号列表
 * @author yuting.li
 * @version 1.0 
 * @date 2017年11月28日 下午8:34:49
 */
@ApiModel(value = "AddInstorageGoodsLotNumberVO", description = "采购管理-采购入库-商品批号列表")
public class DistrInstorageGoodsLotNumberVO implements Serializable{
	
	

	private static final long serialVersionUID = 1L;


	@NotEmpty(message="批号不能为空！")
	@ApiModelProperty(required = true, value = "批号")
    private String lotNumber;
	
	@NotNull(message="生产日期不能为空！")
	@ApiModelProperty(required = true, value = "生产日期")
    private Date productDate;
	
	@NotNull(message="有效期不能为空！")
	@ApiModelProperty(required = true, value = "有效期")
    private Date validDate;
	
	@Range(min=0,message="收货数量不能小于0")
	@NotNull(message="收货数量不能为空！")
	@ApiModelProperty(required = true, value = "收货数量")
    private BigDecimal receiveQuantity;
	
	@Range(min=0,message="拒收数量不能小于0")
	@ApiModelProperty(required = true, value = "拒收数量")
    private BigDecimal refuseQuantity;
	
	@ApiModelProperty(required = true, value = "拒收原因ID集合，多个用逗号分隔")
    private String refuseReasonIds;
	
	@Range(min=0,message="抽样数量不能小于0")
	@ApiModelProperty(value = "抽样数量", required = true)
    private BigDecimal samplingQuantity;
	
	@ApiModelProperty(value = "检验报告书（附件ID）集合", required = true)
	private String testReportIds;
	
	@NotEmpty(message="检验项目不能为空！")
	@ApiModelProperty(value = "检验项目ID集合，多个用逗号分隔", required = true)
    private String checkProjectIds;
	
	@Range(min=0,message="验收合格数量不能小于0")
	@NotNull(message="验收合格数量不能为空！")
	@ApiModelProperty(value = "验收合格数量", required = true)
    private BigDecimal qualifiedQuantity;
	
	@NotNull(message="验收结论不能为空！")
	@ApiModelProperty(value = "验收结论ID集合，多个用逗号分隔", required = true)
    private String conclusionIds;
	
	@NotNull(message="合格品货位货位ID不能为空！")
	@ApiModelProperty(required = true, value = "合格品货位货位ID")
    private Long qualifiedShelfId;
	
	@Range(min=0,message="验收不合格数量不能小于0")
	@ApiModelProperty(value = "验收不合格数量", required = true)
    private BigDecimal unqualifiedQuantity;
	
	@ApiModelProperty(value = "验收不合格原因ID集合，多个用逗号分隔", required = true)
    private String unqualifiedReasonIds;
	
	@ApiModelProperty(value = "处置措施ID集合，多个用逗号分隔", required = true)
    private String measuresIds;
	
	@ApiModelProperty(required = true, value = "不合格品货位货位ID")
    private Long unqualifiedShelfId;
	
	@Range(min=1,message="行号必须大于0")
	@ApiModelProperty(value = "行号", required = true)
    private Integer lineNum;




	//@Range(min=0,message="单价不能小于0")
	//@NotNull(message="单价不能为空！")
	@ApiModelProperty(value = "单价",required=true,hidden=true)
	private BigDecimal unitPrice;

	@Range(min=0,message="行折扣不能小于0")
	@ApiModelProperty(value = "行折扣（%）",required=true,hidden=true)
	private BigDecimal lineDiscount;

	@ApiModelProperty(value = "金额（整单优惠前金额）",required=true,hidden=true)
	private BigDecimal amount;

	@ApiModelProperty(value = "整单折扣（%）",required=true,hidden=true)
	private BigDecimal wholeDiscount;

	@ApiModelProperty(value = "行优惠（整单优惠分摊到行的金额）",hidden=true)
	private BigDecimal lineDiscountAmount;

	@ApiModelProperty(value = "实际金额",required=true,hidden=true)
	private BigDecimal realAmount;

	//@NotNull(message="税率ID不能为空！")
	@ApiModelProperty(value = "税率ID",required=true,hidden=true)
	private Long taxRateId;
	@ApiModelProperty(value = "实际单价",hidden=true)
	private BigDecimal realPrice;
	@ApiModelProperty(value = "不含税实际单价",required=true,hidden=true)
	private BigDecimal notaxRealPrice;

	@ApiModelProperty(value = "不含税实际金额",required=true,hidden=true)
	private BigDecimal notaxRealAmount;

	@ApiModelProperty(value = "税额",required=true,hidden=true)
	private BigDecimal taxAmount;



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

	public String getRefuseReasonIds() {
		return refuseReasonIds;
	}

	public void setRefuseReasonIds(String refuseReasonIds) {
		this.refuseReasonIds = refuseReasonIds;
	}

	public BigDecimal getSamplingQuantity() {
		return samplingQuantity;
	}

	public void setSamplingQuantity(BigDecimal samplingQuantity) {
		this.samplingQuantity = samplingQuantity;
	}

	public String getTestReportIds() {
		return testReportIds;
	}

	public void setTestReportIds(String testReportIds) {
		this.testReportIds = testReportIds;
	}

	public String getCheckProjectIds() {
		return checkProjectIds;
	}

	public void setCheckProjectIds(String checkProjectIds) {
		this.checkProjectIds = checkProjectIds;
	}

	public BigDecimal getQualifiedQuantity() {
		return qualifiedQuantity;
	}

	public void setQualifiedQuantity(BigDecimal qualifiedQuantity) {
		this.qualifiedQuantity = qualifiedQuantity;
	}

	public String getConclusionIds() {
		return conclusionIds;
	}

	public void setConclusionIds(String conclusionIds) {
		this.conclusionIds = conclusionIds;
	}

	public Long getQualifiedShelfId() {
		return qualifiedShelfId;
	}

	public void setQualifiedShelfId(Long qualifiedShelfId) {
		this.qualifiedShelfId = qualifiedShelfId;
	}

	public BigDecimal getUnqualifiedQuantity() {
		return unqualifiedQuantity;
	}

	public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) {
		this.unqualifiedQuantity = unqualifiedQuantity;
	}

	public String getUnqualifiedReasonIds() {
		return unqualifiedReasonIds;
	}

	public void setUnqualifiedReasonIds(String unqualifiedReasonIds) {
		this.unqualifiedReasonIds = unqualifiedReasonIds;
	}

	public String getMeasuresIds() {
		return measuresIds;
	}

	public void setMeasuresIds(String measuresIds) {
		this.measuresIds = measuresIds;
	}

	public Long getUnqualifiedShelfId() {
		return unqualifiedShelfId;
	}

	public void setUnqualifiedShelfId(Long unqualifiedShelfId) {
		this.unqualifiedShelfId = unqualifiedShelfId;
	}

	public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}




	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
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
}
