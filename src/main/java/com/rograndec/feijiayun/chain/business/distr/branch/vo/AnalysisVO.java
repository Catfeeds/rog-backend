package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品列表
 * @author 孙帮祥
 * */
import io.swagger.annotations.ApiModelProperty;

public class AnalysisVO implements Serializable{
	
	@ApiModelProperty(value = "配货单位ID")
	private Long distrUnitId;
	
	@ApiModelProperty(value = "配货单位编码")
	private String distrUnitCode;
	
	@ApiModelProperty(value = "配货单位名称")
	private String distrUnitName;
	
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
	
	@ApiModelProperty(value = "要货类型")
	private int requestType;
	
	@ApiModelProperty(value = "要货类型描述")
	private String requestTypeDesc;
	@ApiModelProperty(value = "分析得到商品列表")
    private List<GoodsDistrReqPlanAnalysisVO> distrReqPlanDetailVOList;
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
	public int getRequestType() {
		return requestType;
	}
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}
	public String getRequestTypeDesc() {
		return requestTypeDesc;
	}
	public void setRequestTypeDesc(String requestTypeDesc) {
		this.requestTypeDesc = requestTypeDesc;
	}
	public List<GoodsDistrReqPlanAnalysisVO> getDistrReqPlanDetailVOList() {
		return distrReqPlanDetailVOList;
	}
	public void setDistrReqPlanDetailVOList(List<GoodsDistrReqPlanAnalysisVO> distrReqPlanDetailVOList) {
		this.distrReqPlanDetailVOList = distrReqPlanDetailVOList;
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
