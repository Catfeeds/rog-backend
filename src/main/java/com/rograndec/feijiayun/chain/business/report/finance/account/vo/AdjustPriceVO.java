package com.rograndec.feijiayun.chain.business.report.finance.account.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.rograndec.feijiayun.chain.common.constant.OrderRule;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * @author dongdong.zhang
 * 2018-01-12
 */
public class AdjustPriceVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 单据类型
     */
    @ApiModelProperty(value = "单据类型ID")
    private Integer orderType;
    
    /**
     * 单据类型
     */
    @ApiModelProperty(value = "单据类型")
    private String orderTypeName;

    /**
     * 开票日期
     */
    @ApiModelProperty(value = "开票日期")
    private Date billDate;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    /**
     * 单位编码
     */
    @ApiModelProperty(value = "单位编码")
    private String unitCode;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

    /**
     * 金额合计
     */
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal = BigDecimal.ZERO;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxAmountTotal = BigDecimal.ZERO;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal = BigDecimal.ZERO;

    /**
     * 金额差额合计
     */
    @ApiModelProperty(value = "金额差额合计")
    private BigDecimal diffAmountTotal = BigDecimal.ZERO;

    /**
     * 不含税金额差额合计
     */
    @ApiModelProperty(value = "不含税金额差额合计")
    private BigDecimal diffNotaxAmountTotal = BigDecimal.ZERO;

    /**
     * 税额差额合计
     */
    @ApiModelProperty(value = "税额差额合计")
    private BigDecimal diffTaxAmountTotal = BigDecimal.ZERO;

    /**
     * saas_advance_receivable_invoice
     */
    private static final long serialVersionUID = 1L;

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
     * 单据编码
     * @return code 单据编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 单据编码
     * @param code 单据编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 单据类型（2301）
     * @return order_type 单据类型（2301）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（2301）
     * @param orderType 单据类型（2301）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 开票日期
     * @return bill_date 开票日期
     */
    public Date getBillDate() {
        return billDate;
    }

    /**
     * 开票日期
     * @param billDate 开票日期
     */
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    /**
     * 金额合计
     * @return amount_total 金额合计
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 金额合计
     * @param amountTotal 金额合计
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 不含税金额合计
     * @return notax_amount_total 不含税金额合计
     */
    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    /**
     * 不含税金额合计
     * @param notaxAmountTotal 不含税金额合计
     */
    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
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

	public String getOrderTypeName() {
		return OrderRule.getName(orderType);
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public BigDecimal getDiffAmountTotal() {
		return diffAmountTotal;
	}

	public void setDiffAmountTotal(BigDecimal diffAmountTotal) {
		this.diffAmountTotal = diffAmountTotal;
	}

	public BigDecimal getDiffNotaxAmountTotal() {
		return diffNotaxAmountTotal;
	}

	public void setDiffNotaxAmountTotal(BigDecimal diffNotaxAmountTotal) {
		this.diffNotaxAmountTotal = diffNotaxAmountTotal;
	}

	public BigDecimal getDiffTaxAmountTotal() {
		return diffTaxAmountTotal;
	}

	public void setDiffTaxAmountTotal(BigDecimal diffTaxAmountTotal) {
		this.diffTaxAmountTotal = diffTaxAmountTotal;
	}

}