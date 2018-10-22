package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_distr_in_receive
 * 
 * 
 * @author Asze
 * 
 * 2017-10-09
 */
public class DistrInReceiveVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 单据类型（5414）
     */
    @ApiModelProperty(value = "单据类型（5414）")
    private Integer orderType;

    /**
     * 配进收货单号
     */
    @ApiModelProperty(value = "配进收货单号")
    private String code;

    /**
     * 配进收货日期
     */
    @ApiModelProperty(value = "配进收货日期")
    private Date receiveDate;
    
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

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
    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;

    /**
     * 配货类型名
     */
    @ApiModelProperty(value = "配货类型名")
    private String distrTypeName;

    /**
     * 收货人员ID
     */
    @ApiModelProperty(value = "收货人员ID")
    private Long receiverId;

    /**
     * 收货人员编码
     */
    @ApiModelProperty(value = "收货人员编码")
    private String receiverCode;

    /**
     * 收货人员名称
     */
    @ApiModelProperty(value = "收货人员名称")
    private String receiverName;

    /**
     * 第二收货人员ID
     */
    @ApiModelProperty(value = "第二收货人员ID")
    private Long secondReceiverId;

    /**
     * 收货人员编码
     */
    @ApiModelProperty(value = "收货人员编码")
    private String secondReceiverCode;

    /**
     * 第二收货人员名称
     */
    @ApiModelProperty(value = "第二收货人员名称")
    private String secondReceiverName;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 状态名
     */
    @ApiModelProperty(value = "状态名")
    private String statusName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 是否为特殊管理药品：（0-是  1-否）
     */
    @ApiModelProperty(value = "是否为特殊管理药品：（-1不是，其他值是）", required = true)
    private Integer specialDrug;

    /**
     * 细单集合
     */
    @ApiModelProperty(value = "细单集合")
    private List<DistrInReceiveDetailVO> distrInReceiveDetailVOList;

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
     * 到货数量
     */
    @ApiModelProperty(value = "到货数量")
    private BigDecimal arrivalQuantityTotal;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量")
    private BigDecimal receiveQuantityTotal;

    /**
     * 拒收数量
     */
    @ApiModelProperty(value = "拒收数量")
    private BigDecimal refuseQuantityTotal;
  	
  	
    /**
     * saas_distr_in_receive
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
     * 单据类型（5414）
     * @return order_type 单据类型（5414）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5414）
     * @param orderType 单据类型（5414）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 配进收货单号
     * @return code 配进收货单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 配进收货单号
     * @param code 配进收货单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 配进收货日期
     * @return receive_date 配进收货日期
     */
    public Date getReceiveDate() {
        return receiveDate;
    }

    /**
     * 配进收货日期
     * @param receiveDate 配进收货日期
     */
    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    /**
     * 基础单据ID
     * @return base_order_id 基础单据ID
     */
    public Long getBaseOrderId() {
        return baseOrderId;
    }

    /**
     * 基础单据ID
     * @param baseOrderId 基础单据ID
     */
    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    /**
     * 基础单据类型
     * @return base_order_type 基础单据类型
     */
    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    /**
     * 基础单据类型
     * @param baseOrderType 基础单据类型
     */
    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    /**
     * 基础单据编码
     * @return base_order_code 基础单据编码
     */
    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    /**
     * 基础单据编码
     * @param baseOrderCode 基础单据编码
     */
    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode == null ? null : baseOrderCode.trim();
    }

    /**
     * 基础单据日期
     * @return base_order_date 基础单据日期
     */
    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    /**
     * 基础单据日期
     * @param baseOrderDate 基础单据日期
     */
    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
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
     * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    public Integer getDistrType() {
        return distrType;
    }

    /**
     *配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    /**
     * 收货人员ID
     * @return receiver_id 收货人员ID
     */
    public Long getReceiverId() {
        return receiverId;
    }

    /**
     * 收货人员ID
     * @param receiverId 收货人员ID
     */
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * 收货人员编码
     * @return receiver_code 收货人员编码
     */
    public String getReceiverCode() {
        return receiverCode;
    }

    /**
     * 收货人员编码
     * @param receiverCode 收货人员编码
     */
    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode == null ? null : receiverCode.trim();
    }

    /**
     * 收货人员名称
     * @return receiver_name 收货人员名称
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 收货人员名称
     * @param receiverName 收货人员名称
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    /**
     * 第二收货人员ID
     * @return second_receiver_id 第二收货人员ID
     */
    public Long getSecondReceiverId() {
        return secondReceiverId;
    }

    /**
     * 第二收货人员ID
     * @param secondReceiverId 第二收货人员ID
     */
    public void setSecondReceiverId(Long secondReceiverId) {
        this.secondReceiverId = secondReceiverId;
    }

    /**
     * 收货人员编码
     * @return second_receiver_code 收货人员编码
     */
    public String getSecondReceiverCode() {
        return secondReceiverCode;
    }

    /**
     * 收货人员编码
     * @param secondReceiverCode 收货人员编码
     */
    public void setSecondReceiverCode(String secondReceiverCode) {
        this.secondReceiverCode = secondReceiverCode == null ? null : secondReceiverCode.trim();
    }

    /**
     * 第二收货人员名称
     * @return second_receiver_name 第二收货人员名称
     */
    public String getSecondReceiverName() {
        return secondReceiverName;
    }

    /**
     * 第二收货人员名称
     * @param secondReceiverName 第二收货人员名称
     */
    public void setSecondReceiverName(String secondReceiverName) {
        this.secondReceiverName = secondReceiverName == null ? null : secondReceiverName.trim();
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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<DistrInReceiveDetailVO> getDistrInReceiveDetailVOList() {
        return distrInReceiveDetailVOList;
    }

    public void setDistrInReceiveDetailVOList(List<DistrInReceiveDetailVO> distrInReceiveDetailVOList) {
        this.distrInReceiveDetailVOList = distrInReceiveDetailVOList;
    }

    public String getDistrTypeName() {
        return distrTypeName;
    }

    public void setDistrTypeName(String distrTypeName) {
        this.distrTypeName = distrTypeName;
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
	
	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public BigDecimal getArrivalQuantityTotal() {
		return arrivalQuantityTotal;
	}

	public void setArrivalQuantityTotal(BigDecimal arrivalQuantityTotal) {
		this.arrivalQuantityTotal = arrivalQuantityTotal;
	}

	public BigDecimal getReceiveQuantityTotal() {
		return receiveQuantityTotal;
	}

	public void setReceiveQuantityTotal(BigDecimal receiveQuantityTotal) {
		this.receiveQuantityTotal = receiveQuantityTotal;
	}

	public BigDecimal getRefuseQuantityTotal() {
		return refuseQuantityTotal;
	}

	public void setRefuseQuantityTotal(BigDecimal refuseQuantityTotal) {
		this.refuseQuantityTotal = refuseQuantityTotal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
        sb.append(", orderType=").append(orderType);
        sb.append(", code=").append(code);
        sb.append(", receiveDate=").append(receiveDate);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", baseOrderType=").append(baseOrderType);
        sb.append(", baseOrderCode=").append(baseOrderCode);
        sb.append(", baseOrderDate=").append(baseOrderDate);
        sb.append(", distrUnitId=").append(distrUnitId);
        sb.append(", distrUnitCode=").append(distrUnitCode);
        sb.append(", distrUnitName=").append(distrUnitName);
        sb.append(", distrType=").append(distrType);
        sb.append(", distrTypeName=").append(distrTypeName);
        sb.append(", receiverId=").append(receiverId);
        sb.append(", receiverCode=").append(receiverCode);
        sb.append(", receiverName=").append(receiverName);
        sb.append(", secondReceiverId=").append(secondReceiverId);
        sb.append(", secondReceiverCode=").append(secondReceiverCode);
        sb.append(", secondReceiverName=").append(secondReceiverName);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
        sb.append(", status=").append(status);
        sb.append(", statusName=").append(statusName);
        sb.append(", remark=").append(remark);
        sb.append(", distrInReceiveDetailVOList=").append(distrInReceiveDetailVOList);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}