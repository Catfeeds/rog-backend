package com.rograndec.feijiayun.chain.business.retail.special.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

 /**
 * 
 * @ClassName: SpecialRegisterVO
 * @Description:  零售管理-专管登记
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-22 16:25:37
 */
@ApiModel(value = "SpecialRegisterVO", description = "零售管理-专管登记")
public class SpecialRegisterVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID")
	private Long id;
	
	/**
     * 企业ID
     */
	@ApiModelProperty(required = true, value = "企业ID")
	private Long enterpriseId;
	
	/**
     * 上级企业ID
     */
	@ApiModelProperty(required = false, value = "上级企业ID")
	private Long parentId;
	
	/**
     * 专管登记单类型（6202）
     */
	@ApiModelProperty(required = true, value = "专管登记单类型（6202）")
	private Integer orderType;
	
	/**
     * 登记日期
     */
	@ApiModelProperty(required = true, value = "登记日期")
	private Date registerDate;
	
	/**
     * 登记单号
     */
	@ApiModelProperty(required = true, value = "登记单号")
	private String code;
	
	/**
     * 基础单据ID
     */
	@ApiModelProperty(required = false, value = "基础单据ID")
	private Long baseOrderId;
	
	/**
     * 基础单据类型
     */
	@ApiModelProperty(required = false, value = "基础单据类型")
	private Integer baseOrderType;
	
	/**
     * 基础单据编码
     */
	@ApiModelProperty(required = false, value = "基础单据编码")
	private String baseOrderCode;
	
	/**
     * 基础单据日期
     */
	@ApiModelProperty(required = false, value = "基础单据日期")
	private Date baseOrderDate;
	
	/**
     * 登记人员ID
     */
	@ApiModelProperty(required = true, value = "登记人员ID")
	private Long registerManId;
	
	/**
     * 登记人员编码
     */
	@ApiModelProperty(required = true, value = "登记人员编码")
	private String registerManCode;
	
	/**
     * 登记人员名称
     */
	@ApiModelProperty(required = true, value = "登记人员名称")
	private String registerManName;
	
	/**
     * 购药者姓名
     */
	@ApiModelProperty(required = false, value = "购药者姓名")
	private String consumerName;
	
	/**
     * 性别（0-女；1-男）
     */
	@ApiModelProperty(required = false, value = "性别（0-女；1-男）")
	private Integer sex;
	
	/**
     * 年龄
     */
	@ApiModelProperty(required = false, value = "年龄")
	private Integer age;
	
	/**
     * 身份证号
     */
	@ApiModelProperty(required = false, value = "身份证号")
	private String idNum;
	
	/**
     * 数量合计
     */
	@ApiModelProperty(required = true, value = "数量合计")
	private BigDecimal quantityTotal;
	
	/**
     * 品种数量
     */
	@ApiModelProperty(required = true, value = "品种数量")
	private Integer varietiesQuantity;
	
	/**
     * 金额合计（整单优惠前的金额合计）
     */
	@ApiModelProperty(required = true, value = "金额合计（整单优惠前的金额合计）")
	private BigDecimal amountTotal;
	
	/**
     * 整单折扣（%）
     */
	@ApiModelProperty(required = true, value = "整单折扣（%）")
	private BigDecimal wholeDiscount;
	
	/**
     * 整单优惠金额
     */
	@ApiModelProperty(required = true, value = "整单优惠金额")
	private BigDecimal wholeDiscountAmount;
	
	/**
     * 实际金额合计
     */
	@ApiModelProperty(required = true, value = "实际金额合计")
	private BigDecimal realAmountTotal;
	
	/**
     * 不含税金额合计
     */
	@ApiModelProperty(required = true, value = "不含税金额合计")
	private BigDecimal notaxRealAmountTotal;
	
	/**
     * 税额合计
     */
	@ApiModelProperty(required = true, value = "税额合计")
	private BigDecimal taxAmountTotal;
	
	/**
     * 利润合计
     */
	@ApiModelProperty(required = true, value = "利润合计")
	private BigDecimal profitTotal;
	
	/**
     * 不含税利润合计
     */
	@ApiModelProperty(required = true, value = "不含税利润合计")
	private BigDecimal notaxProfitTotal;
	
	/**
     * 利润率
     */
	@ApiModelProperty(required = true, value = "利润率")
	private BigDecimal profitRate;
	
	/**
     * 不含税利润率
     */
	@ApiModelProperty(required = true, value = "不含税利润率")
	private BigDecimal notaxProfitRate;
	
	/**
     * 92-待收款 33-已完成
     */
	@ApiModelProperty(required = true, value = "-1-已取消 92-待收款 33-已完成")
	private Integer status;
	
	/**
     * 备注
     */
	@ApiModelProperty(required = false, value = "备注")
	private String remark;
	/**
     * 会员卡号
     */
	@ApiModelProperty(required = false, value = "会员卡号")
	private String memberCardCode;
	/**
     * 销售单号
     */
	@ApiModelProperty(required = false, value = "销售单号")
	private String saleCode;

	@ApiModelProperty(required = false,value = "销售单商品明细")
	private List<SpecialRegisterDetailVO> specialRegisterDetailVOList;

	 public List<SpecialRegisterDetailVO> getSpecialRegisterDetailVOList() {
		 return specialRegisterDetailVOList;
	 }

	 public void setSpecialRegisterDetailVOList(List<SpecialRegisterDetailVO> specialRegisterDetailVOList) {
		 this.specialRegisterDetailVOList = specialRegisterDetailVOList;
	 }

	 public String getMemberCardCode() {
		 return memberCardCode;
	 }

	 public void setMemberCardCode(String memberCardCode) {
		 this.memberCardCode = memberCardCode;
	 }

	 public String getSaleCode() {
		 return saleCode;
	 }

	 public void setSaleCode(String saleCode) {
		 this.saleCode = saleCode;
	 }

	 /**
	 * 主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 主键ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 企业ID
	 */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	/**
	 * 企业ID
	 */
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	
	/**
	 * 上级企业ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 上级企业ID
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/**
	 * 专管登记单类型（6202）
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * 专管登记单类型（6202）
	 */
	public Integer getOrderType() {
		return orderType;
	}
	
	/**
	 * 登记日期
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	/**
	 * 登记日期
	 */
	public Date getRegisterDate() {
		return registerDate;
	}
	
	/**
	 * 登记单号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 登记单号
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 基础单据ID
	 */
	public void setBaseOrderId(Long baseOrderId) {
		this.baseOrderId = baseOrderId;
	}
	
	/**
	 * 基础单据ID
	 */
	public Long getBaseOrderId() {
		return baseOrderId;
	}
	
	/**
	 * 基础单据类型
	 */
	public void setBaseOrderType(Integer baseOrderType) {
		this.baseOrderType = baseOrderType;
	}
	
	/**
	 * 基础单据类型
	 */
	public Integer getBaseOrderType() {
		return baseOrderType;
	}
	
	/**
	 * 基础单据编码
	 */
	public void setBaseOrderCode(String baseOrderCode) {
		this.baseOrderCode = baseOrderCode;
	}
	
	/**
	 * 基础单据编码
	 */
	public String getBaseOrderCode() {
		return baseOrderCode;
	}
	
	/**
	 * 基础单据日期
	 */
	public void setBaseOrderDate(Date baseOrderDate) {
		this.baseOrderDate = baseOrderDate;
	}
	
	/**
	 * 基础单据日期
	 */
	public Date getBaseOrderDate() {
		return baseOrderDate;
	}
	
	/**
	 * 登记人员ID
	 */
	public void setRegisterManId(Long registerManId) {
		this.registerManId = registerManId;
	}
	
	/**
	 * 登记人员ID
	 */
	public Long getRegisterManId() {
		return registerManId;
	}
	
	/**
	 * 登记人员编码
	 */
	public void setRegisterManCode(String registerManCode) {
		this.registerManCode = registerManCode;
	}
	
	/**
	 * 登记人员编码
	 */
	public String getRegisterManCode() {
		return registerManCode;
	}
	
	/**
	 * 登记人员名称
	 */
	public void setRegisterManName(String registerManName) {
		this.registerManName = registerManName;
	}
	
	/**
	 * 登记人员名称
	 */
	public String getRegisterManName() {
		return registerManName;
	}
	
	/**
	 * 购药者姓名
	 */
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	
	/**
	 * 购药者姓名
	 */
	public String getConsumerName() {
		return consumerName;
	}
	
	/**
	 * 性别（0-女；1-男）
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	/**
	 * 性别（0-女；1-男）
	 */
	public Integer getSex() {
		return sex;
	}
	
	/**
	 * 年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	
	/**
	 * 年龄
	 */
	public Integer getAge() {
		return age;
	}
	
	/**
	 * 身份证号
	 */
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	
	/**
	 * 身份证号
	 */
	public String getIdNum() {
		return idNum;
	}
	
	/**
	 * 数量合计
	 */
	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}
	
	/**
	 * 数量合计
	 */
	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}
	
	/**
	 * 品种数量
	 */
	public void setVarietiesQuantity(Integer varietiesQuantity) {
		this.varietiesQuantity = varietiesQuantity;
	}
	
	/**
	 * 品种数量
	 */
	public Integer getVarietiesQuantity() {
		return varietiesQuantity;
	}
	
	/**
	 * 金额合计（整单优惠前的金额合计）
	 */
	public void setAmountTotal(BigDecimal amountTotal) {
		this.amountTotal = amountTotal;
	}
	
	/**
	 * 金额合计（整单优惠前的金额合计）
	 */
	public BigDecimal getAmountTotal() {
		return amountTotal;
	}
	
	/**
	 * 整单折扣（%）
	 */
	public void setWholeDiscount(BigDecimal wholeDiscount) {
		this.wholeDiscount = wholeDiscount;
	}
	
	/**
	 * 整单折扣（%）
	 */
	public BigDecimal getWholeDiscount() {
		return wholeDiscount;
	}
	
	/**
	 * 整单优惠金额
	 */
	public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
		this.wholeDiscountAmount = wholeDiscountAmount;
	}
	
	/**
	 * 整单优惠金额
	 */
	public BigDecimal getWholeDiscountAmount() {
		return wholeDiscountAmount;
	}
	
	/**
	 * 实际金额合计
	 */
	public void setRealAmountTotal(BigDecimal realAmountTotal) {
		this.realAmountTotal = realAmountTotal;
	}
	
	/**
	 * 实际金额合计
	 */
	public BigDecimal getRealAmountTotal() {
		return realAmountTotal;
	}
	
	/**
	 * 不含税金额合计
	 */
	public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
		this.notaxRealAmountTotal = notaxRealAmountTotal;
	}
	
	/**
	 * 不含税金额合计
	 */
	public BigDecimal getNotaxRealAmountTotal() {
		return notaxRealAmountTotal;
	}
	
	/**
	 * 税额合计
	 */
	public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
		this.taxAmountTotal = taxAmountTotal;
	}
	
	/**
	 * 税额合计
	 */
	public BigDecimal getTaxAmountTotal() {
		return taxAmountTotal;
	}
	
	/**
	 * 利润合计
	 */
	public void setProfitTotal(BigDecimal profitTotal) {
		this.profitTotal = profitTotal;
	}
	
	/**
	 * 利润合计
	 */
	public BigDecimal getProfitTotal() {
		return profitTotal;
	}
	
	/**
	 * 不含税利润合计
	 */
	public void setNotaxProfitTotal(BigDecimal notaxProfitTotal) {
		this.notaxProfitTotal = notaxProfitTotal;
	}
	
	/**
	 * 不含税利润合计
	 */
	public BigDecimal getNotaxProfitTotal() {
		return notaxProfitTotal;
	}
	
	/**
	 * 利润率
	 */
	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}
	
	/**
	 * 利润率
	 */
	public BigDecimal getProfitRate() {
		return profitRate;
	}
	
	/**
	 * 不含税利润率
	 */
	public void setNotaxProfitRate(BigDecimal notaxProfitRate) {
		this.notaxProfitRate = notaxProfitRate;
	}
	
	/**
	 * 不含税利润率
	 */
	public BigDecimal getNotaxProfitRate() {
		return notaxProfitRate;
	}
	
	/**
	 * 92-待收款 33-已完成
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 92-待收款 33-已完成
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 备注
	 */
	public String getRemark() {
		return remark;
	}
	

}