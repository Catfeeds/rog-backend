package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_distr_req_plan
 * 
 * 
 * @author kexinhao
 * 
 * 2017-10-07
 */
public class DistrReqPlanVO implements Serializable {
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
     * 单据类型（5401）
     */
    @ApiModelProperty(value = "单据类型（5401）")
    private Integer orderType;


	@ApiModelProperty(value = "基础单据ID")
	private Long baseOrderId;

    /**
     * 要货计划单号
     */
    @ApiModelProperty(value = "要货计划单号")
    private String code;

    /**
     * 计划日期
     */
    @ApiModelProperty(value = "计划日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date planDate;

    /**
     * 配货单位ID（在配进退出调用要货计划的时候，这个字段的含义为调入单位）
     */
    @ApiModelProperty(value = "配货单位ID")
    private Long distrUnitId;

    /**
     * 配货单位编码（在配进退出调用要货计划的时候，这个字段的含义为调入单位）
     */
    @ApiModelProperty(value = "配货单位编码")
    private String distrUnitCode;

    /**
     * 配货单位名称（在配进退出调用要货计划的时候，这个字段的含义为调入单位）
     */
    @ApiModelProperty(value = "配货单位名称")
    private String distrUnitName;

    /**
     * 计划人员ID
     */
    @ApiModelProperty(value = "计划人员ID")
    private Long plannerId;

    /**
     * 计划人员编码
     */
    @ApiModelProperty(value = "计划人员编码")
    private String plannerCode;

    /**
     * 计划人员名称
     */
    @ApiModelProperty(value = "计划人员名称")
    private String plannerName;

    /**
     * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）",required=true )
    private Integer requestType;
    
    /**
     * 配货类型名称
     */
    @ApiModelProperty(value = "配货类型名称",required=true )
    private String requestTypeName;
    
    /**
     * 要货类型描述
     */
    
    @ApiModelProperty(value = "要货类型描述")
    private String requestTypeDesc;
    
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
    @ApiModelProperty(value = "状态   21-待审核 22-审核通过 23-审核驳回 31-待配货 33-已配货 34-已取消")
    private Integer status;

    @ApiModelProperty(value = "状态描述")
    private String statusDesc;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    
    /**
   	 * 出库单位ID
   	 */
   	@ApiModelProperty(value = "出库单位ID（调出单位，供货单位）")
   	private Long outboundUnitId;

   	/**
   	 * 出库位单位编码
   	 */
   	@ApiModelProperty(value = "出库位单位编码 （调出单位，供货单位）")
   	private String outboundUnitCode;

   	/**
   	 * 出库单位名称
   	 */
   	@ApiModelProperty(value = "出库单位名称（调出单位，供货单位）")
   	private String outboundUnitName;
   	
	/**
	 * 草稿ID
	 * */
	@ApiModelProperty(value = "草稿ID")
	private String redisKeyValue;
	
    /**
     * 要货计划明细列表
     */
    @ApiModelProperty(value = "要货计划明细列表")
    private List<DistrReqPlanDetailVO> distrReqPlanDetailVOList;


	public Long getBaseOrderId() {
		return baseOrderId;
	}

	public void setBaseOrderId(Long baseOrderId) {
		this.baseOrderId = baseOrderId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
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

	public Long getPlannerId() {
		return plannerId;
	}

	public void setPlannerId(Long plannerId) {
		this.plannerId = plannerId;
	}

	public String getPlannerCode() {
		return plannerCode;
	}

	public void setPlannerCode(String plannerCode) {
		this.plannerCode = plannerCode;
	}

	public String getPlannerName() {
		return plannerName;
	}

	public void setPlannerName(String plannerName) {
		this.plannerName = plannerName;
	}

	public Integer getRequestType() {
		return requestType;
	}

	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
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

	public List<DistrReqPlanDetailVO> getDistrReqPlanDetailVOList() {
		return distrReqPlanDetailVOList;
	}

	public void setDistrReqPlanDetailVOList(List<DistrReqPlanDetailVO> distrReqPlanDetailVOList) {
		this.distrReqPlanDetailVOList = distrReqPlanDetailVOList;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getRequestTypeDesc() {
		return requestTypeDesc;
	}

	public void setRequestTypeDesc(String requestTypeDesc) {
		this.requestTypeDesc = requestTypeDesc;
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

	public String getRedisKeyValue() {
		return redisKeyValue;
	}

	public void setRedisKeyValue(String redisKeyValue) {
		this.redisKeyValue = redisKeyValue;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getRequestTypeName() {
		return requestTypeName;
	}

	public void setRequestTypeName(String requestTypeName) {
		this.requestTypeName = requestTypeName;
	}
	
}