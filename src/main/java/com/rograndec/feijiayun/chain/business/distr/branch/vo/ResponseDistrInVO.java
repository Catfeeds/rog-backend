package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
 
/**
 * @author dongdong.zhang
 * 2017-10-08
 */
public class ResponseDistrInVO implements Serializable {
	
	/**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 单据类型（5416）
     */
    @ApiModelProperty(value = "单据类型（5416）")
    private Integer orderType;

    /**
     * 配进入库单号
     */
    @ApiModelProperty(value = "单号")
    private String code;

    /**
     * 入库日期
     */
    @ApiModelProperty(value = "日期")
    private Date inDate;

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
    @ApiModelProperty(value = "配货单位")
    private String distrUnitName;

    /**
     * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    @ApiModelProperty(value = "配货类型ID（0-总部配送；3-分店间调剂；4-直调配送），前端不用显示")
    private Integer distrType;
    
    /**
     * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送），前端不用显示")
    private String distrTypeName;

    /**
     * 入库人员ID
     */
    @ApiModelProperty(value = "入库人员ID")
    private Long storageManId;

    /**
     * 入库人员编码
     */
    @ApiModelProperty(value = "入库人员编码")
    private String storageManCode;

    /**
     * 入库人员名称
     */
    @ApiModelProperty(value = "入库人员名称")
    private String storageManName;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计，前端不用")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量，前端不用")
    private Integer varietiesQuantity;

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
    @ApiModelProperty(value = "金额")
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额")
    private BigDecimal notaxRealAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额")
    private BigDecimal taxAmountTotal;

    /**
  	 * 出库单位ID
  	 */
  	@ApiModelProperty(value = "出库单位ID")
  	private Long outboundUnitId;

  	/**
  	 * 出库位单位编码
  	 */
  	@ApiModelProperty(value = "出库位单位编码 ")
  	private String outboundUnitCode;

  	/**
  	 * 出库单位名称
  	 */
  	@ApiModelProperty(value = "出库单位名称")
  	private String outboundUnitName;

    /**
     * saas_distr_in
     */
    private static final long serialVersionUID = 1L;

    public String getStorageManName() {
		return storageManName;
	}

	public void setStorageManName(String storageManName) {
		this.storageManName = storageManName;
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
     * 配进入库单号
     * @return code 配进入库单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 配进入库单号
     * @param code 配进入库单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 入库日期
     * @return in_date 入库日期
     */
    public Date getInDate() {
        return inDate;
    }

    /**
     * 入库日期
     * @param inDate 入库日期
     */
    public void setInDate(Date inDate) {
        this.inDate = inDate;
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


	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Long getDistrUnitId() {
		return distrUnitId;
	}

	public void setDistrUnitId(Long distrUnitId) {
		this.distrUnitId = distrUnitId;
	}

	public Integer getDistrType() {
		return distrType;
	}

	public void setDistrType(Integer distrType) {
		this.distrType = distrType;
	}

	public Long getStorageManId() {
		return storageManId;
	}

	public void setStorageManId(Long storageManId) {
		this.storageManId = storageManId;
	}

	public String getStorageManCode() {
		return storageManCode;
	}

	public void setStorageManCode(String storageManCode) {
		this.storageManCode = storageManCode;
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

	public String getDistrTypeName() {
		return distrTypeName;
	}

	public void setDistrTypeName(String distrTypeName) {
		this.distrTypeName = distrTypeName;
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


}