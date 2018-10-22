package com.rograndec.feijiayun.chain.business.storage.split.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_split
 * 
 * 
 * @author lanxj
 * 
 * 2017-09-29
 */
public class ResponseSplitVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 拆零日期
     */
    @ApiModelProperty(value = "拆零日期")
    private Date splitDate;

    /**
     * 拆零单号
     */
    @ApiModelProperty(value = "拆零单号")
    private String code;

    /**
     * 拆零人员名称
     */
    @ApiModelProperty(value = "拆零人员名称")
    private String splitManName;

    /**
     * 复核人员名称
     */
    @ApiModelProperty(value = "复核人员名称")
    private String auditManName;

    /**
     * 流向监管码
     */
    @ApiModelProperty(value = "流向监管码")
    private String flowCode;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "原商品数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "拆分商品数量合计")
    private BigDecimal splitQuantityTotal;
    
    /**
     * 拆零记录
     */
    @ApiModelProperty(value = "拆零记录")
    private List<ResponseSplitDetailVO>  splitDetailList;

    /**
     * saas_split
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
     * 拆零日期
     * @return split_date 拆零日期
     */
    public Date getSplitDate() {
        return splitDate;
    }

    /**
     * 拆零日期
     * @param splitDate 拆零日期
     */
    public void setSplitDate(Date splitDate) {
        this.splitDate = splitDate;
    }

    /**
     * 拆零单号
     * @return code 拆零单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 拆零单号
     * @param code 拆零单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 拆零人员名称
     * @return split_man_name 拆零人员名称
     */
    public String getSplitManName() {
        return splitManName;
    }

    /**
     * 拆零人员名称
     * @param splitManName 拆零人员名称
     */
    public void setSplitManName(String splitManName) {
        this.splitManName = splitManName == null ? null : splitManName.trim();
    }

    /**
     * 复核人员名称
     * @return audit_man_name 复核人员名称
     */
    public String getAuditManName() {
        return auditManName;
    }

    /**
     * 复核人员名称
     * @param auditManName 复核人员名称
     */
    public void setAuditManName(String auditManName) {
        this.auditManName = auditManName == null ? null : auditManName.trim();
    }

    /**
     * 流向监管码
     * @return flow_code 流向监管码
     */
    public String getFlowCode() {
        return flowCode;
    }

    /**
     * 流向监管码
     * @param flowCode 流向监管码
     */
    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode == null ? null : flowCode.trim();
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

    public BigDecimal getSplitQuantityTotal() {
		return splitQuantityTotal;
	}

	public void setSplitQuantityTotal(BigDecimal splitQuantityTotal) {
		this.splitQuantityTotal = splitQuantityTotal;
	}

	public List<ResponseSplitDetailVO> getSplitDetailList() {
		return splitDetailList;
	}

	public void setSplitDetailList(List<ResponseSplitDetailVO> splitDetailList) {
		this.splitDetailList = splitDetailList;
	}

	/**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", splitDate=").append(splitDate);
        sb.append(", code=").append(code);
        sb.append(", splitManName=").append(splitManName);
        sb.append(", auditManName=").append(auditManName);
        sb.append(", flowCode=").append(flowCode);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}