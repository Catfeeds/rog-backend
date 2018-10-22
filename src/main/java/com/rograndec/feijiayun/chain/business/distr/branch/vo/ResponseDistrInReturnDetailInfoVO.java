package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
 
/**
 * 
 * saas_distr_in_return
 * 
 * 
 * @author dongdong.zhang
 * 
 * 2017-10-12
 */
public class ResponseDistrInReturnDetailInfoVO implements Serializable {
	

    /**
     * 配进退出单详情列表
     */
    @ApiModelProperty(value = "配进退出单详情列表")
	private List<ResponseDistrInReturnDetailVO> list;
	
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 配进退出单号
     */
    @ApiModelProperty(value = "配进退出单号")
    private String code;

    /**
     * 配进退出日期
     */
    @ApiModelProperty(value = "退货日期")
    private Date inReturnDate;

    /**
     * 配货单位ID
     */
    @ApiModelProperty(value = "配货单位ID")
    private Long distrUnitId;

    /**
     * 配货单位编码
     */
    @ApiModelProperty(value = "配货单位编码")
    private String distrUnitCode;

    /**
     * 配货单位名称
     */
    @ApiModelProperty(value = "配货单位名称")
    private String distrUnitName;

    /**
     * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    @ApiModelProperty(value = "配货类型ID（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;
    

    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private String distrTypeName;
    
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
     * 数量合计
     */
    @ApiModelProperty(value = "商品数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量（前端用不到）")
    private Integer varietiesQuantity;

    /**
     * 金额合计（整单优惠前的金额合计）
     */
    @ApiModelProperty(value = "总商品金额")
    private BigDecimal amountTotal;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "折扣（%）")
    private BigDecimal wholeDiscount;

    /**
     * 整单优惠金额
     */
    @ApiModelProperty(value = "优惠")
    private BigDecimal wholeDiscountAmount;

    /**
     * 实际金额合计
     */
    @ApiModelProperty(value = "总额")
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税总额")
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmountTotal;

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
     * 打印接口的字段
     */
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "整单折扣金额：整单金额*整单折扣")
    private BigDecimal wholeDiscountValue;

    private Long baseOrderId;
    
   	private String baseOrderCode;
    
    /**出库单位的含义在要货计划那里指的是商品出自哪里，在配进退出这里指的是商品出库到哪里
   	 * 出库单位ID（查询要货计划生成的配进退出单明细的时候，这个字段为调入单位的信息，直接调用类型的这个字段为供货单位，总部配送这个字段为调入单位为总部）
   	 */
   	@ApiModelProperty(value = "出库单位ID")
   	private Long outboundUnitId;

   	/**
   	 * 出库位单位编码（查询要货计划生成的配进退出单明细的时候，这个字段为调入单位的信息）
   	 */
   	@ApiModelProperty(value = "出库位单位编码 ")
   	private String outboundUnitCode;

   	/**
   	 * 出库单位名称（查询要货计划生成的配进退出单明细的时候，这个字段为调入单位的信息）
   	 */
   	@ApiModelProperty(value = "出库单位名称")
   	private String outboundUnitName;


    /**
     * saas_distr_in_return
     */
    private static final long serialVersionUID = 1L;

    public List<ResponseDistrInReturnDetailVO> getList() {
		return list;
	}

	public void setList(List<ResponseDistrInReturnDetailVO> list) {
		this.list = list;
	}
	
    public String getDistrTypeName() {
		return distrTypeName;
	}

	public void setDistrTypeName(String distrTypeName) {
		this.distrTypeName = distrTypeName;
	}


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
     * 配进退出单号
     * @return code 配进退出单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 配进退出单号
     * @param code 配进退出单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 配进退出日期
     * @return in_return_date 配进退出日期
     */
    public Date getInReturnDate() {
        return inReturnDate;
    }

    /**
     * 配进退出日期
     * @param inReturnDate 配进退出日期
     */
    public void setInReturnDate(Date inReturnDate) {
        this.inReturnDate = inReturnDate;
    }

    /**
     * 配货单位ID
     * @return distr_unit_id 配货单位ID
     */
    public Long getDistrUnitId() {
        return distrUnitId;
    }

    /**
     * 配货单位ID
     * @param distrUnitId 配货单位ID
     */
    public void setDistrUnitId(Long distrUnitId) {
        this.distrUnitId = distrUnitId;
    }

    /**
     * 配货单位编码
     * @return distr_unit_code 配货单位编码
     */
    public String getDistrUnitCode() {
        return distrUnitCode;
    }

    /**
     * 配货单位编码
     * @param distrUnitCode 配货单位编码
     */
    public void setDistrUnitCode(String distrUnitCode) {
        this.distrUnitCode = distrUnitCode == null ? null : distrUnitCode.trim();
    }

    /**
     * 配货单位名称
     * @return distr_unit_name 配货单位名称
     */
    public String getDistrUnitName() {
        return distrUnitName;
    }

    /**
     * 配货单位名称
     * @param distrUnitName 配货单位名称
     */
    public void setDistrUnitName(String distrUnitName) {
        this.distrUnitName = distrUnitName == null ? null : distrUnitName.trim();
    }


    public Integer getDistrType() {
        return distrType;
    }


    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    /**
     * 退货人员ID
     * @return return_man_id 退货人员ID
     */
    public Long getReturnManId() {
        return returnManId;
    }

    /**
     * 退货人员ID
     * @param returnManId 退货人员ID
     */
    public void setReturnManId(Long returnManId) {
        this.returnManId = returnManId;
    }

    /**
     * 退货人员编码
     * @return return_man_code 退货人员编码
     */
    public String getReturnManCode() {
        return returnManCode;
    }

    /**
     * 退货人员编码
     * @param returnManCode 退货人员编码
     */
    public void setReturnManCode(String returnManCode) {
        this.returnManCode = returnManCode == null ? null : returnManCode.trim();
    }

    /**
     * 退货人员名称
     * @return return_man_name 退货人员名称
     */
    public String getReturnManName() {
        return returnManName;
    }

    /**
     * 退货人员名称
     * @param returnManName 退货人员名称
     */
    public void setReturnManName(String returnManName) {
        this.returnManName = returnManName == null ? null : returnManName.trim();
    }

    /**
     * 数量合计
     * @return quantity_total 数量合计
     */
    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    /**
     * 数量合计
     * @param quantityTotal 数量合计
     */
    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    /**
     * 品种数量
     * @return varieties_quantity 品种数量
     */
    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    /**
     * 品种数量
     * @param varietiesQuantity 品种数量
     */
    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @return amount_total 金额合计（整单优惠前的金额合计）
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @param amountTotal 金额合计（整单优惠前的金额合计）
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 整单折扣（%）
     * @return whole_discount 整单折扣（%）
     */
    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    /**
     * 整单折扣（%）
     * @param wholeDiscount 整单折扣（%）
     */
    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    /**
     * 整单优惠金额
     * @return whole_discount_amount 整单优惠金额
     */
    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    /**
     * 整单优惠金额
     * @param wholeDiscountAmount 整单优惠金额
     */
    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    /**
     * 实际金额合计
     * @return real_amount_total 实际金额合计
     */
    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    /**
     * 实际金额合计
     * @param realAmountTotal 实际金额合计
     */
    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    /**
     * 不含税金额合计
     * @return notax_real_amount_total 不含税金额合计
     */
    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    /**
     * 不含税金额合计
     * @param notaxRealAmountTotal 不含税金额合计
     */
    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
    }

    /**
     * 税额合计
     * @return tax_amount_total 税额合计
     */
    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    /**
     * 税额合计
     * @param taxAmountTotal 税额合计
     */
    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
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

	public Long getBaseOrderId() {
		return baseOrderId;
	}

	public void setBaseOrderId(Long baseOrderId) {
		this.baseOrderId = baseOrderId;
	}

	public Long getOutboundUnitId() {
		return outboundUnitId;
	}

	public void setOutboundUnitId(Long outboundUnitId) {
		this.outboundUnitId = outboundUnitId;
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

	public String getBaseOrderCode() {
		return baseOrderCode;
	}

	public void setBaseOrderCode(String baseOrderCode) {
		this.baseOrderCode = baseOrderCode;
	}

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public BigDecimal getWholeDiscountValue() {
        return wholeDiscountValue;
    }

    public void setWholeDiscountValue(BigDecimal wholeDiscountValue) {
        this.wholeDiscountValue = wholeDiscountValue;
    }
}