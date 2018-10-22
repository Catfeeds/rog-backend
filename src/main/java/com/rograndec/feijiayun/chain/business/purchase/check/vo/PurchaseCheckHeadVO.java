package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zeshi.sun on 2017/9/14.
 */
public class PurchaseCheckHeadVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;
    
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称", required = true)
    private String enterpriseName;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(value = "供货单位编码", required = true)
    private String supplierCode;

    /**
     * 供货单位销售人员
     */
    @ApiModelProperty(value = "供货单位销售人员", required = true)
    private String supplierSalerName;

    /**
     * 验收单号
     */
    @ApiModelProperty(value = "验收单号", required = true)
    private String code;

    /**
     * 验收人员1
     */
    @ApiModelProperty(value = "验收人员1", required = true)
    private String checkerName;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "供货单位名称", required = true)
    private String supplierName;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话", required = true)
    private String supplierSalerPhone;

    /**
     * 验收日期
     */
    @ApiModelProperty(value = "验收日期", required = true)
    private Date checkDate;

    /**
     * 验收人员2
     */
    @ApiModelProperty(value = "验收人员2", required = true)
    private String secondCheckerName;
    
    
    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量", required = true)
    private BigDecimal receiveQuantityTotal;
    
    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量", required = true)
    private BigDecimal qualifiedQuantityTotal;
    
    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量", required = true)
    private BigDecimal unqualifiedQuantityTotal;

    /**
     * 抽样数量
     */
    @ApiModelProperty(value = "抽样数量", required = true)
    private BigDecimal samplingQuantityTotal;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierSalerName() {
        return supplierSalerName;
    }

    public void setSupplierSalerName(String supplierSalerName) {
        this.supplierSalerName = supplierSalerName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierSalerPhone() {
        return supplierSalerPhone;
    }

    public void setSupplierSalerPhone(String supplierSalerPhone) {
        this.supplierSalerPhone = supplierSalerPhone;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getSecondCheckerName() {
        return secondCheckerName;
    }

    public void setSecondCheckerName(String secondCheckerName) {
        this.secondCheckerName = secondCheckerName;
    }
    
    public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public BigDecimal getReceiveQuantityTotal() {
		return receiveQuantityTotal;
	}

	public void setReceiveQuantityTotal(BigDecimal receiveQuantityTotal) {
		this.receiveQuantityTotal = receiveQuantityTotal;
	}

	public BigDecimal getQualifiedQuantityTotal() {
		return qualifiedQuantityTotal;
	}

	public void setQualifiedQuantityTotal(BigDecimal qualifiedQuantityTotal) {
		this.qualifiedQuantityTotal = qualifiedQuantityTotal;
	}

	public BigDecimal getUnqualifiedQuantityTotal() {
		return unqualifiedQuantityTotal;
	}

	public void setUnqualifiedQuantityTotal(BigDecimal unqualifiedQuantityTotal) {
		this.unqualifiedQuantityTotal = unqualifiedQuantityTotal;
	}

	public BigDecimal getSamplingQuantityTotal() {
		return samplingQuantityTotal;
	}

	public void setSamplingQuantityTotal(BigDecimal samplingQuantityTotal) {
		this.samplingQuantityTotal = samplingQuantityTotal;
	}

	@Override
    public String toString() {
        return "PurchaseCheckHeadVO[" +
                "id=" + id +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierSalerName='" + supplierSalerName + '\'' +
                ", code='" + code + '\'' +
                ", checkerName='" + checkerName + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierSalerPhone='" + supplierSalerPhone + '\'' +
                ", checkDate=" + checkDate +
                ", secondCheckerName='" + secondCheckerName + '\'' +
                ']';
    }
}
