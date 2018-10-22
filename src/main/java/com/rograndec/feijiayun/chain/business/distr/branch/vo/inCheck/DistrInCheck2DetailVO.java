package com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_distr_in_check
 * 
 * 
 * @author ST
 * 
 * 2017-10-10
 */
public class DistrInCheck2DetailVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    /**
     * 配进验收单号
     */
    @ApiModelProperty(value = "配进验收单号")
    private String code;

    /**
     * 验收日期
     */
    @ApiModelProperty(value = "验收日期")
    private Date checkDate;

    /**
     * 配进订单ID
     */
    @ApiModelProperty(value = "配进订单ID")
    private Long noticeId;

    /**
     * 配货货单位ID
     */
    @ApiModelProperty(value = "配货货单位ID")
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


    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;
    
    /**
     * 配货类型名称
     */
    @ApiModelProperty(value = "配货类型名称")
    private String distrTypeName;


    /**
     * 验收人员ID
     */
    @ApiModelProperty(value = "验收人员ID")
    private Long checkerId;


    /**
     * 验收人员名称
     */
    @ApiModelProperty(value = "验收人员名称")
    private String checkerName;

    /**
     * 第二验收人员ID
     */
    @ApiModelProperty(value = "第二验收人员ID")
    private Long secondCheckerId;


    /**
     * 第二验收人员名称
     */
    @ApiModelProperty(value = "第二验收人员名称")
    private String secondCheckerName;


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
     * 是否为特殊管理药品：（0-是  1-否）
     */
    @ApiModelProperty(value = "是否为特殊管理药品：（0-是  1-否）", required = true)
    private Integer specialDrug;


    @ApiModelProperty(value = "验收品种明细")
    private List<DistrInCheckDetail2DetailVO> detailVOList;
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
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID")
    private Long baseOrderId;

    /**
     * 基础单据类型
     */
    @ApiModelProperty(value = "基础单据类型")
    private Integer baseOrderType;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "基础单据编码")
    private String baseOrderCode;

    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期")
    private Date baseOrderDate;
    
    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量")
    private BigDecimal receiveQuantityTotal;
    
    /**
     * 抽样数量
     */
    @ApiModelProperty(value = "抽样数量")
    private BigDecimal samplingQuantityTotal;
    
    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量")
    private BigDecimal qualifiedQuantityTotal;
    
    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量")
    private BigDecimal unqualifiedQuantityTotal;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }



    public Long getDistrUnitId() {
        return distrUnitId;
    }

    public void setDistrUnitId(Long distrUnitId) {
        this.distrUnitId = distrUnitId;
    }

    public String getDistrUnitCode() {
        return distrUnitCode;
    }

    public void setDistrUnitCode(String distrUnitCode) {
        this.distrUnitCode = distrUnitCode;
    }

    public String getDistrUnitName() {
        return distrUnitName;
    }

    public void setDistrUnitName(String distrUnitName) {
        this.distrUnitName = distrUnitName;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public Long getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public Long getSecondCheckerId() {
        return secondCheckerId;
    }

    public void setSecondCheckerId(Long secondCheckerId) {
        this.secondCheckerId = secondCheckerId;
    }

    public String getSecondCheckerName() {
        return secondCheckerName;
    }

    public void setSecondCheckerName(String secondCheckerName) {
        this.secondCheckerName = secondCheckerName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<DistrInCheckDetail2DetailVO> getDetailVOList() {
        return detailVOList;
    }

    public void setDetailVOList(List<DistrInCheckDetail2DetailVO> detailVOList) {
        this.detailVOList = detailVOList;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(Integer specialDrug) {
        this.specialDrug = specialDrug;
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


    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode;
    }

    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getDistrTypeName() {
		return distrTypeName;
	}

	public void setDistrTypeName(String distrTypeName) {
		this.distrTypeName = distrTypeName;
	}

	public BigDecimal getReceiveQuantityTotal() {
		return receiveQuantityTotal;
	}

	public void setReceiveQuantityTotal(BigDecimal receiveQuantityTotal) {
		this.receiveQuantityTotal = receiveQuantityTotal;
	}

	public BigDecimal getSamplingQuantityTotal() {
		return samplingQuantityTotal;
	}

	public void setSamplingQuantityTotal(BigDecimal samplingQuantityTotal) {
		this.samplingQuantityTotal = samplingQuantityTotal;
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
}