package com.rograndec.feijiayun.chain.business.system.opening.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * @author dongdongzhang
 * 
 * 2018-01-15
 */
public class OpeningTaxVO implements Serializable {
	
	 /**
     * 期初应付(应收)表是否已存在数据（0：未存在；1:已存在）
     */
    @ApiModelProperty(value = "期初应付表是否已存在数据（0：未存在；1:已存在）")
    private Integer isGenerate;
    
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
     * 日期
     */
    @ApiModelProperty(value = "日期")
    @NotNull(message = "日期不能为空")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date taxDate;

    /**
     * 人员ID
     */
    @ApiModelProperty(value = "人员ID")
    @NotNull(message = "人员不能为空")
    private Long operatorId;

    /**
     * 人员编码
     */
    @ApiModelProperty(value = "人员编码")
    private String operatorCode;

    /**
     * 人员名称
     */
    @ApiModelProperty(value = "人员名称")
    private String operatorName;

    /**
     * 金额合计
     */
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal;
    
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    
    /**
     * 详情列表
     */
    @ApiModelProperty(value = "详情列表")
    List<OpeningTaxDetailVO> detailList;


    /**
     * saas_opening_tax
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
     * 日期
     * @return tax_date 日期
     */
    public Date getTaxDate() {
        return taxDate;
    }

    /**
     * 日期
     * @param taxDate 日期
     */
    public void setTaxDate(Date taxDate) {
        this.taxDate = taxDate;
    }

    /**
     * 人员ID
     * @return operator_id 人员ID
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * 人员ID
     * @param operatorId 人员ID
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 人员编码
     * @return operator_code 人员编码
     */
    public String getOperatorCode() {
        return operatorCode;
    }

    /**
     * 人员编码
     * @param operatorCode 人员编码
     */
    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode == null ? null : operatorCode.trim();
    }

    /**
     * 人员名称
     * @return operator_name 人员名称
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * 人员名称
     * @param operatorName 人员名称
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
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

	public List<OpeningTaxDetailVO> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OpeningTaxDetailVO> detailList) {
		this.detailList = detailList;
	}

	public Integer getIsGenerate() {
		return isGenerate;
	}

	public void setIsGenerate(Integer isGenerate) {
		this.isGenerate = isGenerate;
	}

	public String getRemark() {
		return remark == null ? "":remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}