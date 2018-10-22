package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
 
/**
 * 
 * saas_distr_in_return
 * 
 * 
 * @author rograndec
 * 
 * 2017-10-08
 */
public class ResponseDistrInReturnVO implements Serializable {
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
    @ApiModelProperty(value = "配进退出日期")
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
    
    /**
     * 配货类型名称（0-总部配送；3-分店间调剂；4-直调配送）
     */
    @ApiModelProperty(value = "配货类型名称")
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
     * 状态
     */
    @ApiModelProperty(value = "状态(21-待审核 22-审核通过 23-审核驳回 30-待出库32-待复核(向翟老师确认)33-已完成（已出库） 34-已取消")
    private Integer status;

    @ApiModelProperty(value = "状态描述")
    private String statusDesc;

    /**
     * 状态
     */
    @ApiModelProperty(value = "审核状态名称")
    private String statusName;
    
    /**
     * 状态
     */
    @ApiModelProperty(value = "判断是否可显示修改    0隐藏  1 显示")
    private Integer isHidden;
    
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
    
    
    public Integer getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(Integer isHidden) {
		this.isHidden = isHidden;
	}

	public String getDistrTypeName() {
		return distrTypeName;
	}

	public void setDistrTypeName(String distrTypeName) {
		this.distrTypeName = distrTypeName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
     * saas_distr_in_return
     */
    private static final long serialVersionUID = 1L;

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

    /**
     *配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     * @return distr_type
     */
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

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
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